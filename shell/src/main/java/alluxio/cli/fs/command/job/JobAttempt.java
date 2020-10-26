package alluxio.cli.fs.command.job;

import alluxio.client.job.JobMasterClient;
import alluxio.job.JobConfig;
import alluxio.job.wire.JobInfo;
import alluxio.job.wire.Status;
import alluxio.retry.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

abstract public class JobAttempt {
  private static final Logger LOG = LoggerFactory.getLogger(JobAttempt.class);

  protected final JobMasterClient mClient;
  protected final RetryPolicy mRetryPolicy;

  private Long mJobId;

  public JobAttempt(JobMasterClient client, RetryPolicy retryPolicy) {
    mClient = client;
    mRetryPolicy = retryPolicy;
  }

  public boolean run() {
    if (mRetryPolicy.attempt()) {
      mJobId = null;
      try {
        mJobId = mClient.run(getJobConfig());
      } catch (IOException e) {
        LOG.warn("Failed to get status for job (jobId={})", mJobId, e);
        // Do nothing. This will be counted as a failed attempt
      }
      return true;
    }
    logFailed();
    return false;
  }

  /**
   * Returns the status of the job attempt.
   * @return True if finished successfully or cancelled, False if FAILED and should be retried,
   *              null if the status should be checked again later
   */
  public Status check() {
    if (mJobId == null) {
      return Status.FAILED;
    }

    JobInfo jobInfo;
    try {
      jobInfo = mClient.getJobStatus(mJobId);
    } catch (IOException e) {
      LOG.warn("Failed to get status for job (jobId={})", mJobId, e);
      return Status.FAILED;
    }

    // This make an assumption that this job tree only goes 1 level deep
    boolean finished = true;
    for (JobInfo child : jobInfo.getChildren()) {
      if (!child.getStatus().isFinished()) {
        finished = false;
        break;
      }
    }

    if (finished) {
      if (jobInfo.getStatus().equals(Status.FAILED)) {
        logFailedAttempt();
      } else if (jobInfo.getStatus().equals(Status.COMPLETED)) {
        logCompleted();
      }
      return jobInfo.getStatus();
    }
    return Status.RUNNING;
  }

  abstract protected JobConfig getJobConfig();

  abstract protected void logFailedAttempt(JobInfo jobInfo);

  abstract protected void logFailed();

  abstract protected void logCompleted();
}