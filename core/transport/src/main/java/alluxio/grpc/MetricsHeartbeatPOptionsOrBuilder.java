// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/metric_master.proto

package alluxio.grpc;

public interface MetricsHeartbeatPOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.metric.MetricsHeartbeatPOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .alluxio.grpc.Metric metrics = 1;</code>
   */
  java.util.List<alluxio.grpc.Metric> 
      getMetricsList();
  /**
   * <code>repeated .alluxio.grpc.Metric metrics = 1;</code>
   */
  alluxio.grpc.Metric getMetrics(int index);
  /**
   * <code>repeated .alluxio.grpc.Metric metrics = 1;</code>
   */
  int getMetricsCount();
  /**
   * <code>repeated .alluxio.grpc.Metric metrics = 1;</code>
   */
  java.util.List<? extends alluxio.grpc.MetricOrBuilder> 
      getMetricsOrBuilderList();
  /**
   * <code>repeated .alluxio.grpc.Metric metrics = 1;</code>
   */
  alluxio.grpc.MetricOrBuilder getMetricsOrBuilder(
      int index);
}
