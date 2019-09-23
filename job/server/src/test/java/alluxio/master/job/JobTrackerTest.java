package alluxio.master.job;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import alluxio.exception.status.ResourceExhaustedException;
import alluxio.job.JobServerContext;
import alluxio.job.SleepJobConfig;
import alluxio.master.job.command.CommandManager;
import alluxio.util.FormatUtils;
import alluxio.wire.WorkerInfo;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.List;

public class JobTrackerTest {

  private static final long CAPACITY = 25;
  private static final long RETENTION_TIME = 0;
  private static final long PURGE_CONUT = -1;
  private List<WorkerInfo> mWorkers;
  private JobTracker mTracker;
  private CommandManager mMockCommandManager;
  private JobServerContext mMockJobServerContext;

  @Rule
  public ExpectedException mException = ExpectedException.none();

  @Before
  public void before() {
    mTracker = new JobTracker(CAPACITY, RETENTION_TIME, PURGE_CONUT);
    mMockCommandManager = new CommandManager();
    mMockJobServerContext = Mockito.mock(JobServerContext.class);
    mWorkers = Lists.newArrayList(new WorkerInfo());
  }

  @After
  public void after() {

  }

  @Test
  public void testAddJobIncreaesCount() throws Exception {
    assertEquals("tracker should be empty", 0, mTracker.coordinators().size());
    addJob(100);
    assertEquals("tracker should have one job", 1, mTracker.coordinators().size());
  }

  @Test
  public void testAddJobUpToCapacity() throws Exception {
    assertEquals("tracker should be empty", 0, mTracker.coordinators().size());
    for (int i = 0; i < CAPACITY; i++) {
      addJob(100);
      assertEquals(String.format("tracker should have %d job(s)", i+1), i+1,
          mTracker.coordinators().size());
    }
    mException.expect(ResourceExhaustedException.class);
    addJob(100);
  }

  @Test
  public void testAddAndPurge() throws Exception {
    assertEquals("tracker should be empty", 0, mTracker.coordinators().size());
    fillJobTracker(CAPACITY);
    try {
      addJob(100);
      fail("Should have failed to add a job over capacity");
    } catch (ResourceExhaustedException e) {
      // Empty on purpose
    }
    finishAllJobs();
    try {
      addJob(100);
    } catch (ResourceExhaustedException e) {
      fail("Should not have failed to add a job over capacity when all are finished");
    }
  }

  @Test
  public void testPurgeCount() throws Exception {
    JobTracker tracker = new JobTracker(10, 0, 5);
    assertEquals("tracker should be empty", 0, tracker.coordinators().size());
    fillJobTracker(tracker, 10);
    finishAllJobs(tracker);
    addJob(tracker, 100);
    assertEquals(6, tracker.coordinators().size());
  }

  @Test
  public void testRetentionTime() throws Exception {
    JobTracker tracker = new JobTracker(10, FormatUtils.parseTimeSize("24h"), -1);
    assertEquals("tracker should be empty", 0, tracker.coordinators().size());
    fillJobTracker(tracker, 10);
    finishAllJobs(tracker);
    mException.expect(ResourceExhaustedException.class);
    addJob(tracker, 100);
  }

  long addJob(int sleepTimeMs) throws Exception {
    return addJob(mTracker, sleepTimeMs);
  }

  long addJob(JobTracker tracker, int sleepTimeMs) throws Exception {
    return tracker.addJob(new SleepJobConfig(sleepTimeMs), mMockCommandManager,
        mMockJobServerContext, mWorkers);
  }

  void fillJobTracker(long nJobs) throws Exception {
    fillJobTracker(mTracker, nJobs);
  }

  void fillJobTracker(JobTracker tracker,  long nJobs) throws Exception {
    int initial = tracker.coordinators().size();
    for (int i = 1; i <= nJobs; i++) {
      addJob(tracker, 100);
      int expectedCount = initial + i;
      assertEquals(String.format("tracker should have %d job(s)", expectedCount), expectedCount,
          tracker.coordinators().size());
    }
  }

  void finishAllJobs() {
    // Put all jobs in a failed state
    finishAllJobs(mTracker);
  }

  void finishAllJobs(JobTracker tracker) {
    // Put all jobs in a failed state
    tracker.coordinators().forEach(c -> c.setJobAsFailed("failed for test"));
  }

}
