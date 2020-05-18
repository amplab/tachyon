package alluxio.stress.master;

import alluxio.Constants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.HdrHistogram.Histogram;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MasterBenchTaskResultStatistics {
  public static final int MAX_RESPONSE_TIME_COUNT = 20;

  /** The response time histogram can record values up to this amount. */
  public static final long RESPONSE_TIME_HISTOGRAM_MAX = Constants.SECOND_NANO * 60 * 30;
  public static final int RESPONSE_TIME_HISTOGRAM_PRECISION = 3;

  static final int COMPRESSION_LEVEL = 9;
  static final int RESPONSE_TIME_99_COUNT = 6;

  public long mNumSuccess;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  public byte[] mResponseTimeNsRaw;
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  public long[] mMaxResponseTimeNs;

  /**
   * Creates an instance.
   */
  public MasterBenchTaskResultStatistics() {
    // Default constructor required for json deserialization
    mMaxResponseTimeNs = new long[MAX_RESPONSE_TIME_COUNT];
    Arrays.fill(mMaxResponseTimeNs, -1);
  }

  /**
   * Merges (updates) a task result statistics with this statistics.
   *
   * @param statistics  the task result statistics to merge
   */
  public void merge(MasterBenchTaskResultStatistics statistics) throws Exception {
    mNumSuccess += statistics.mNumSuccess;

    Histogram responseTime = new Histogram(RESPONSE_TIME_HISTOGRAM_MAX,
        RESPONSE_TIME_HISTOGRAM_PRECISION);
    if (mResponseTimeNsRaw != null) {
      responseTime.add(Histogram
          .decodeFromCompressedByteBuffer(ByteBuffer.wrap(mResponseTimeNsRaw),
              RESPONSE_TIME_HISTOGRAM_MAX));
    }
    if (statistics.mResponseTimeNsRaw != null) {
      responseTime.add(Histogram
          .decodeFromCompressedByteBuffer(ByteBuffer.wrap(statistics.mResponseTimeNsRaw),
              RESPONSE_TIME_HISTOGRAM_MAX));
    }
    encodeResponseTimeNsRaw(responseTime);
    for (int i = 0; i < mMaxResponseTimeNs.length; i++) {
      if (statistics.mMaxResponseTimeNs[i] > mMaxResponseTimeNs[i]) {
        mMaxResponseTimeNs[i] = statistics.mMaxResponseTimeNs[i];
      }
    }
  }

  /**
   * Encodes the histogram into the internal byte array.
   *
   * @param responseTimeNs the histogram (in ns)
   */
  public void encodeResponseTimeNsRaw(Histogram responseTimeNs) {
    ByteBuffer bb = ByteBuffer.allocate(responseTimeNs.getEstimatedFootprintInBytes());
    responseTimeNs.encodeIntoCompressedByteBuffer(bb, COMPRESSION_LEVEL);
    bb.flip();
    mResponseTimeNsRaw = new byte[bb.limit()];
    bb.get(mResponseTimeNsRaw);
  }

}
