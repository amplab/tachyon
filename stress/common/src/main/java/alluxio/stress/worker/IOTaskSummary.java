package alluxio.stress.worker;

import alluxio.collections.Pair;
import alluxio.stress.BaseParameters;
import alluxio.stress.JsonSerializable;
import alluxio.stress.Parameters;
import alluxio.stress.Summary;
import alluxio.stress.graph.BarGraph;
import alluxio.stress.graph.Graph;
import alluxio.stress.job.IOConfig;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The summary for the UFS I/O throughput test.
 */
public class IOTaskSummary implements Summary {
  private static final Logger LOG = LoggerFactory.getLogger(IOTaskSummary.class);
  private List<IOTaskResult.Point> mPoints;
  private List<String> mErrors;
  private BaseParameters mBaseParameters;
  private WorkerBenchParameters mParameters;
  private SpeedStat mReadSpeedStat;
  private SpeedStat mWriteSpeedStat;

  /**
   * Used for deserialization.
   * */
  @JsonCreator
  public IOTaskSummary() {}

  /**
   * @param result the {@link IOTaskResult} to summarize
   * */
  public IOTaskSummary(IOTaskResult result) {
    mPoints = new ArrayList<>(result.getPoints());
    mErrors = new ArrayList<>(result.getErrors());
    mBaseParameters = result.getBaseParameters();
    mParameters = result.getParameters();
    calculateStats();
  }

  /**
   * @return the points recorded
   * */
  public List<IOTaskResult.Point> getPoints() {
    return mPoints;
  }

  /**
   * @param points the data points
   * */
  public void setPoints(List<IOTaskResult.Point> points) {
    mPoints = points;
  }

  /**
   * @return the errors recorded
   * */
  public List<String> getErrors() {
    return mErrors;
  }

  /**
   * @param errors the errors
   * */
  public void setErrors(List<String> errors) {
    mErrors = errors;
  }

  /**
   * @return the {@link BaseParameters}
   * */
  public BaseParameters getBaseParameters() {
    return mBaseParameters;
  }

  /**
   * @param baseParameters the {@link BaseParameters}
   * */
  public void setBaseParameters(BaseParameters baseParameters) {
    mBaseParameters = baseParameters;
  }

  /**
   * @return the task specific {@link WorkerBenchParameters}
   * */
  public WorkerBenchParameters getParameters() {
    return mParameters;
  }

  /**
   * @param parameters the {@link WorkerBenchParameters}
   * */
  public void setParameters(WorkerBenchParameters parameters) {
    mParameters = parameters;
  }

  /**
   * @return the {@link SpeedStat} for read speed
   * */
  public SpeedStat getReadSpeedStat() {
    return mReadSpeedStat;
  }

  /**
   * @param stat the {@link SpeedStat} for read stats
   * */
  public void setReadSpeedStat(SpeedStat stat) {
    mReadSpeedStat = stat;
  }

  /**
   * @return the {@link SpeedStat} for write speed
   * */
  public SpeedStat getWriteSpeedStat() {
    return mWriteSpeedStat;
  }

  /**
   * @param stat the {@link SpeedStat} for write stats
   * */
  public void setWriteSpeedStat(SpeedStat stat) {
    mWriteSpeedStat = stat;
  }

  /**
   * An object representation of all the statistics we need
   * from this I/O test.
   * */
  @JsonSerialize(using = StatSerializer.class)
  @JsonDeserialize(using = StatDeserializer.class)
  public static class SpeedStat implements JsonSerializable {
    public double mTotalDuration; // in second
    public int mTotalSize; // in MB
    public double mMaxSpeed; // in MB/s
    public double mMinSpeed; // in MB/s
    public double mAvgSpeed; // in MB/s
    public double mStdDev; // in MB/s

    /**
     * An empty constructor.
     * */
    public SpeedStat() {}

    @Override
    public String toString() {
      return String.format("{totalDuration=%ss, totalSize=%sMB, maxSpeed=%sMB/s, "
                      + "minSpeed=%sMB/s, " + "avgSpeed=%sMB/s, stdDev=%s}",
              mTotalDuration, mTotalSize, mMaxSpeed, mMinSpeed, mAvgSpeed, mStdDev);
    }
  }

  /**
   * The serializer for {@link SpeedStat}.
   * */
  public static class StatSerializer extends StdSerializer<SpeedStat> {
    /**
     * An empty constructor.
     * */
    public StatSerializer() {
      super(SpeedStat.class);
    }

    @Override
    public void serialize(SpeedStat value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
      jgen.writeStartObject();
      jgen.writeStringField("totalDuration", value.mTotalDuration + "s");
      jgen.writeStringField("totalSize", value.mTotalSize + "MB");
      jgen.writeStringField("maxSpeed", value.mMaxSpeed + "MB/s");
      jgen.writeStringField("minSpeed", value.mMinSpeed + "MB/s");
      jgen.writeStringField("avgSpeed", value.mAvgSpeed + "MB/s");
      jgen.writeNumberField("stdDev", value.mStdDev);
      jgen.writeEndObject();
    }
  }

  /**
   * The deserializer for {@link SpeedStat}.
   * */
  private class StatDeserializer extends StdDeserializer<SpeedStat> {
    /**
     * An empty constructor.
     * */
    public StatDeserializer() {
      super(SpeedStat.class);
    }

    private double speedToNumber(String speed) {
      return Double.parseDouble(speed.substring(0, speed.length() - "MB/s".length()));
    }

    private double timeToNumber(String time) {
      return Double.parseDouble(time.substring(0, time.length() - "s".length()));
    }

    private int sizeToNumber(String size) {
      return Integer.parseInt(size.substring(0, size.length() - "MB".length()));
    }

    @Override
    public SpeedStat deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
      JsonNode node = jp.getCodec().readTree(jp);
      SpeedStat stat = new SpeedStat();
      stat.mTotalDuration = timeToNumber(node.get("totalDuration").asText());
      stat.mTotalSize = sizeToNumber(node.get("totalSize").asText());
      stat.mMaxSpeed = speedToNumber(node.get("maxSpeed").asText());
      stat.mMinSpeed = speedToNumber(node.get("minSpeed").asText());
      stat.mAvgSpeed = speedToNumber(node.get("avgSpeed").asText());
      stat.mStdDev = node.get("stdDev").asDouble();

      return stat;
    }
  }

  /**
   * The points must be valid (duration not equal to 0).
   */
  private static SpeedStat calculateStat(List<IOTaskResult.Point> points) {
    SpeedStat result = new SpeedStat();
    if (points.size() == 0) {
      return result;
    }

    double totalDuration = 0.0;
    int totalSize = 0;
    double[] speeds = new double[points.size()];
    double maxSpeed = 0.0;
    double minSpeed = Double.MAX_VALUE;
    int i = 0;
    for (IOTaskResult.Point p : points) {
      totalDuration += p.mDuration;
      totalSize += p.mDataSizeMB;
      double speed = p.mDataSizeMB / p.mDuration;
      maxSpeed = Math.max(maxSpeed, speed);
      minSpeed = Math.min(minSpeed, speed);
      speeds[i++] = p.mDataSizeMB / p.mDuration;
    }
    double avgSpeed = totalSize / totalDuration;
    double var = 0;
    for (double s : speeds) {
      var += (s - avgSpeed) * (s - avgSpeed);
    }

    result.mTotalDuration = totalDuration;
    result.mTotalSize = totalSize;
    result.mMaxSpeed = maxSpeed;
    result.mMinSpeed = Double.compare(minSpeed, Double.MAX_VALUE) == 0 ? 0.0 : minSpeed;
    result.mAvgSpeed = avgSpeed;
    result.mStdDev = Math.sqrt(var);

    return result;
  }

  private void calculateStats() {
    List<IOTaskResult.Point> readPoints = mPoints.stream().filter((p) ->
            p.mMode == IOConfig.IOMode.READ && p.mDuration > 0)
            .collect(Collectors.toList());
    mReadSpeedStat = calculateStat(readPoints);

    List<IOTaskResult.Point> writePoints = mPoints.stream().filter((p) ->
            p.mMode == IOConfig.IOMode.WRITE && p.mDuration > 0)
            .collect(Collectors.toList());
    mWriteSpeedStat = calculateStat(writePoints);
  }

  @Override
  public GraphGenerator graphGenerator() {
    return new GraphGenerator();
  }

  @Override
  public String toString() {
    return String.format("IOTaskSummary: {Points=%s, Errors=%s}%n",
            mPoints, mErrors);
  }

  /**
   * A graph generator for the statistics collected.
   * */
  public static final class GraphGenerator extends alluxio.stress.GraphGenerator {
    @Override
    public List<Graph> generate(List<? extends Summary> results) {
      List<Graph> graphs = new ArrayList<>();
      // only examine MaxThroughputSummary
      List<IOTaskSummary> summaries =
              results.stream().map(x -> (IOTaskSummary) x).collect(Collectors.toList());

      if (summaries.isEmpty()) {
        LOG.info("No summaries");
        return graphs;
      }

      // TODO(jiacheng): how to include params in this?
      // first() is the list of common field names, second() is the list of unique field names
      Pair<List<String>, List<String>> fieldNames = Parameters.partitionFieldNames(
              summaries.stream().map(x -> x.mParameters).collect(Collectors.toList()));
      System.out.format("FieldNames: %s%n", fieldNames);

      // Split up common description into 100 character chunks, for the sub title
      List<String> subTitle = new ArrayList<>(Splitter.fixedLength(100).splitToList(
              summaries.get(0).mParameters.getDescription(fieldNames.getFirst())));
      System.out.format("subtitle: %s%n", subTitle);

      for (IOTaskSummary summary : summaries) {
        String series = summary.mParameters.getDescription(fieldNames.getSecond());
        subTitle.add(series);
      }

      BarGraph speedGraph = new BarGraph("Read/Write speed",
              subTitle, "Avg speed in MB/s");
      BarGraph stdDevGraph = new BarGraph("Read/Write speed standard deviation",
              subTitle, "Standard deviation in speed");
      for (IOTaskSummary summary : summaries) {
        String series = summary.mParameters.getDescription(fieldNames.getSecond());
        // read stat
        BarGraph.Data readSpeed = new BarGraph.Data();
        BarGraph.Data readStdDev = new BarGraph.Data();
        SpeedStat readStat = summary.getReadSpeedStat();
        readSpeed.addData(readStat.mAvgSpeed);
        readStdDev.addData(readStat.mStdDev);
        speedGraph.addDataSeries(series, readSpeed);
        stdDevGraph.addDataSeries(series, readStdDev);

        // write stat
        BarGraph.Data writeSpeed = new BarGraph.Data();
        BarGraph.Data writeStdDev = new BarGraph.Data();
        SpeedStat writeStat = summary.getWriteSpeedStat();
        writeSpeed.addData(writeStat.mAvgSpeed);
        writeStdDev.addData(writeStat.mStdDev);
        speedGraph.addDataSeries(series, writeSpeed);
        stdDevGraph.addDataSeries(series, writeStdDev);
      }
      graphs.add(speedGraph);

      return graphs;
    }
  }
}
