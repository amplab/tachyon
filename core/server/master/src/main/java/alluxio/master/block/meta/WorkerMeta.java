package alluxio.master.block.meta;

import alluxio.StorageTierAssoc;
import alluxio.util.CommonUtils;
import alluxio.wire.WorkerNetAddress;
import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class WorkerMeta {
  /** Worker's address. */
  public final WorkerNetAddress mWorkerAddress;
  /** The id of the worker. */
  public final long mId;
  /** Start time of the worker in ms. */
  public final long mStartTimeMs;
  /** Worker's last updated time in ms. */
  public final AtomicLong mLastUpdatedTimeMs;
  /** If true, the worker is considered registered. */
  public boolean mIsRegistered;

  public WorkerMeta(long id, WorkerNetAddress address) {
    mId = id;
    mWorkerAddress = Preconditions.checkNotNull(address, "address");
    mIsRegistered = false;
    mStartTimeMs = CommonUtils.getCurrentMs();
    mLastUpdatedTimeMs = new AtomicLong(CommonUtils.getCurrentMs());
  }

  public void updateLastUpdatedTimeMs() {
    mLastUpdatedTimeMs.set(CommonUtils.getCurrentMs());
  }
}
