// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/metric_master.proto

package alluxio.grpc;

public interface MetricValueOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.metric.MetricValue)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional double doubleValue = 1;</code>
   */
  boolean hasDoubleValue();
  /**
   * <code>optional double doubleValue = 1;</code>
   */
  double getDoubleValue();

  /**
   * <code>optional string stringValue = 2;</code>
   */
  boolean hasStringValue();
  /**
   * <code>optional string stringValue = 2;</code>
   */
  java.lang.String getStringValue();
  /**
   * <code>optional string stringValue = 2;</code>
   */
  com.google.protobuf.ByteString
      getStringValueBytes();

  /**
   * <code>optional .alluxio.grpc.MetricType metricType = 6;</code>
   */
  boolean hasMetricType();
  /**
   * <code>optional .alluxio.grpc.MetricType metricType = 6;</code>
   */
  alluxio.grpc.MetricType getMetricType();
}
