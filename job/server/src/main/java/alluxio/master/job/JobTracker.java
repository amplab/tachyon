package alluxio.master.job;

import alluxio.conf.PropertyKey;
import alluxio.conf.ServerConfiguration;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.JobDoesNotExistException;
import alluxio.exception.status.ResourceExhaustedException;
import alluxio.job.JobConfig;
import alluxio.job.JobServerContext;
import alluxio.job.meta.JobIdGenerator;
import alluxio.job.meta.JobInfo;
import alluxio.job.wire.Status;
import alluxio.master.job.command.CommandManager;
import alluxio.util.CommonUtils;
import alluxio.wire.WorkerInfo;

import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Nullable;

/**
 * The {@link JobTracker} is used to create, remove, and provide access to the set of currently
 * scheduled or finished jobs.
 *
 * All modification of the status of jobs should occur within this class,
 */
@ThreadSafe
public class JobTracker {
  private static final Logger LOG = LoggerFactory.getLogger(JobTracker.class);

  /**
   * The maximum amount of jobs that will get purged when removing jobs from the queue.
   *
   * By default this value will be -1 (unlimited), however situations may arise where it is
   * desirable to cap the amount of jobs removed. A scenario where the max capacity is large
   * (10M+) could cause an RPC to the job master to time out due to significant time to remove a
   * large number of jobs. While one RPC of 10M may seem insignificant, providing guarantees that
   * RPCs won't fail on long-running production clusters is probably something that we want to
   * provide.
   *
   * One caveat to setting this value is that there will be a lower bound on the amount of
   * memory that the job master will use when it is at capacity. This may or may not be a
   * reasonable trade-off to guarantee that RPCs don't time out due to job eviction.
   * @see PropertyKey#JOB_MASTER_FINISHED_JOB_PURGE_COUNT
   */
  private final long mMaxJobPurgeCount;

  /**
   * The maximum amount of jobs that can be tracked at any one time.
   */
  private final long mCapacity;

  /**
   * The minimum amount of time that finished jobs should be retained for.
   */
  private final long mRetentionMs;

  /**
   * Used to generate Id for new jobs.
   */
  private final JobIdGenerator mJobIdGenerator;

  /**
   * The main index to track jobs through their Job Id.
   */
  private final ConcurrentHashMap<Long, JobCoordinator> mCoordinators;

  /**
   * A FIFO queue used to track jobs which have status {@link Status#isFinished()} as true.
   */
  private final LinkedBlockingQueue<JobInfo> mFinished;

  /**
   * Create a new instance of {@link JobTracker}.
   *
   * @param capacity the capacity of jobs that can be handled
   * @param retentionMs the minimum amount of time to retain jobs
   */
  public JobTracker(long capacity, long retentionMs, long maxJobPurgeCount) {
    mCapacity = capacity;
    mRetentionMs = retentionMs;
    mCoordinators = new ConcurrentHashMap<>(0,
        0.95f, ServerConfiguration.getInt(PropertyKey.MASTER_RPC_EXECUTOR_PARALLELISM));
    mFinished = new LinkedBlockingQueue<>();
    mJobIdGenerator = new JobIdGenerator();
    mMaxJobPurgeCount = maxJobPurgeCount <= 0 ? Long.MAX_VALUE : maxJobPurgeCount;
  }

  private void statusChangeCallback(JobInfo jobInfo) {
    if (jobInfo == null) {
      return;
    }
    Status status = jobInfo.getStatus();
    if (status.isFinished()) {
      if (mFinished.offer(jobInfo)) {
        LOG.warn("Failed to add job to finished queue");
        // TODO(zac) If adding to the finished queue fails, should we wait and retry?
        // another option is to at this point just immediately remove from tracking so we don't
        // end up with ghost jobs, or provide some way through the REST API to remove jobs from
        // mCoordinators at runtime
      }
    }
  }

  /**
   * Gets a {@link JobCoordinator} associated with the given job Id.
   *
   * @param jobId the job id associated with the {@link JobCoordinator}
   * @return The {@link JobCoordinator} associated with the id, or null if there is no association
   */
  @Nullable
  public JobCoordinator getCoordinator(long jobId) {
    return mCoordinators.get(jobId);
  }

  /**
   * Adds a job with the given {@link JobConfig} to the job tracker.
   *
   * @param jobConfig configuration for the job
   * @param manager command manager for jobs
   * @param ctx the {@link JobServerContext} from the job master
   * @param workers a list of available workers
   * @return the job id of the newly added job
   * @throws JobDoesNotExistException   if the job type does not exist
   * @throws ResourceExhaustedException if there is no more space available in the job master
   */
  public synchronized long addJob(JobConfig jobConfig, CommandManager manager,
      JobServerContext ctx, List<WorkerInfo> workers) throws
      JobDoesNotExistException, ResourceExhaustedException {
    if (removeFinished()) {
      long jobId = mJobIdGenerator.getNewJobId();
      JobCoordinator jobCoordinator = JobCoordinator.create(manager, ctx,
          workers, jobId, jobConfig, this::statusChangeCallback);
      mCoordinators.put(jobId, jobCoordinator);
      return jobId;
    } else {
      throw new ResourceExhaustedException(
          ExceptionMessage.JOB_MASTER_FULL_CAPACITY.getMessage(mCapacity));
    }
  }

  /**
   * Removes all finished jobs outside of the retention time from the queue.
   *
   * @return true if at least one job was removed, or if the initial
   */
  private synchronized boolean removeFinished() {
    boolean removedJob = false;
    boolean isFull = mCoordinators.size() >= mCapacity;
    if (isFull) { // coordinators at max capacity
      // Try to clear the queue
      if (mFinished.isEmpty()) {
        // The job master is at full capacity and no job has finished.
        return false;
      }
      int removeCount = 0;
      while (!mFinished.isEmpty() || removeCount > mMaxJobPurgeCount) {
        JobInfo oldestJob = mFinished.peek();
        if (oldestJob == null) { // no items to remove
          break;
        }
        long timeSinceCompletion = CommonUtils.getCurrentMs() - oldestJob.getLastStatusChangeMs();
        // Once inserted into mFinished, the status of a job should not change - so the peek()
        // /poll() methods guarantee to some extent that the job at the top of the queue is one
        // of the oldest jobs. Thus, if it is still within retention time here, we likely can't
        // remove anything else from the queue. Though it should be noted that it is not strictly
        // guaranteed that the job at the top of is the oldest.
        if (timeSinceCompletion < mRetentionMs) {
          break;
        }

        // Remove the top item since we know it's old enough now.
        // Assumes there are no concurrent poll() operations taking place between here and the
        // first peek()
        if (mFinished.poll() == null) {
          // This should not happen because peek() returned an element
          // there should be no other concurrent operations that remove from mFinished
          LOG.warn("Polling the queue resulted in a null element");
          break;
        }
        // Remove from the job coordinator
        if (mCoordinators.remove(oldestJob.getId()) == null) {
          LOG.warn("Did not find a coordinator with id {}", oldestJob.getId());
        } else {
          removedJob = true;
          removeCount++;
        }
      }
    }
    return removedJob || !isFull;
  }

  /**
   * A collection of all job Ids currently tracked in the job master. Jobs may be in a finished
   * state.
   *
   * @return An unmodifiable collection of all tracked Job Ids
   */
  public Collection<Long> jobs() {
    return Collections.unmodifiableCollection(mCoordinators.keySet());
  }

  /**
   * A collection of all {@link JobCoordinator} currently tracked by the job master. May contain
   * coordinators for jobs which have finished.
   *
   * @return An unmodifiable collection of all tracked {@link JobCoordinator}
   */
  public Collection<JobCoordinator> coordinators() {
    return Collections.unmodifiableCollection(mCoordinators.values());
  }
}
