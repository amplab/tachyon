// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/metric_master.proto

package alluxio.grpc;

public final class MetricMasterProto {
  private MetricMasterProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_ClearMetricsPRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_ClearMetricsPRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_ClearMetricsPResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_ClearMetricsPResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_MetricsHeartbeatPOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_ClientMetrics_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_ClientMetrics_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_MetricsHeartbeatPRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_MetricsHeartbeatPResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_MetricValue_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_MetricValue_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_GetMetricsPOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_GetMetricsPOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_GetMetricsPResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_MetricsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_metric_GetMetricsPResponse_MetricsEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030grpc/metric_master.proto\022\023alluxio.grpc" +
      ".metric\032\021grpc/common.proto\"\026\n\024ClearMetri" +
      "csPRequest\"\027\n\025ClearMetricsPResponse\"U\n\030M" +
      "etricsHeartbeatPOptions\0229\n\rclientMetrics" +
      "\030\001 \003(\0132\".alluxio.grpc.metric.ClientMetri" +
      "cs\"F\n\rClientMetrics\022\016\n\006source\030\001 \001(\t\022%\n\007m" +
      "etrics\030\002 \003(\0132\024.alluxio.grpc.Metric\"Z\n\030Me" +
      "tricsHeartbeatPRequest\022>\n\007options\030\001 \001(\0132" +
      "-.alluxio.grpc.metric.MetricsHeartbeatPO" +
      "ptions\"\033\n\031MetricsHeartbeatPResponse\"e\n\013M" +
      "etricValue\022\023\n\013doubleValue\030\001 \001(\001\022\023\n\013strin" +
      "gValue\030\002 \001(\t\022,\n\nmetricType\030\006 \001(\0162\030.allux" +
      "io.grpc.MetricType\"\024\n\022GetMetricsPOptions" +
      "\"\257\001\n\023GetMetricsPResponse\022F\n\007metrics\030\001 \003(" +
      "\01325.alluxio.grpc.metric.GetMetricsPRespo" +
      "nse.MetricsEntry\032P\n\014MetricsEntry\022\013\n\003key\030" +
      "\001 \001(\t\022/\n\005value\030\002 \001(\0132 .alluxio.grpc.metr" +
      "ic.MetricValue:\0028\0012\327\002\n\032MetricsMasterClie" +
      "ntService\022e\n\014ClearMetrics\022).alluxio.grpc" +
      ".metric.ClearMetricsPRequest\032*.alluxio.g" +
      "rpc.metric.ClearMetricsPResponse\022q\n\020Metr" +
      "icsHeartbeat\022-.alluxio.grpc.metric.Metri" +
      "csHeartbeatPRequest\032..alluxio.grpc.metri" +
      "c.MetricsHeartbeatPResponse\022_\n\nGetMetric" +
      "s\022\'.alluxio.grpc.metric.GetMetricsPOptio" +
      "ns\032(.alluxio.grpc.metric.GetMetricsPResp" +
      "onseB#\n\014alluxio.grpcB\021MetricMasterProtoP" +
      "\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          alluxio.grpc.CommonProto.getDescriptor(),
        });
    internal_static_alluxio_grpc_metric_ClearMetricsPRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_alluxio_grpc_metric_ClearMetricsPRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_ClearMetricsPRequest_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_metric_ClearMetricsPResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_alluxio_grpc_metric_ClearMetricsPResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_ClearMetricsPResponse_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPOptions_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_MetricsHeartbeatPOptions_descriptor,
        new java.lang.String[] { "ClientMetrics", });
    internal_static_alluxio_grpc_metric_ClientMetrics_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_alluxio_grpc_metric_ClientMetrics_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_ClientMetrics_descriptor,
        new java.lang.String[] { "Source", "Metrics", });
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_MetricsHeartbeatPRequest_descriptor,
        new java.lang.String[] { "Options", });
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_alluxio_grpc_metric_MetricsHeartbeatPResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_MetricsHeartbeatPResponse_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_metric_MetricValue_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_alluxio_grpc_metric_MetricValue_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_MetricValue_descriptor,
        new java.lang.String[] { "DoubleValue", "StringValue", "MetricType", });
    internal_static_alluxio_grpc_metric_GetMetricsPOptions_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_alluxio_grpc_metric_GetMetricsPOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_GetMetricsPOptions_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_GetMetricsPResponse_descriptor,
        new java.lang.String[] { "Metrics", });
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_MetricsEntry_descriptor =
      internal_static_alluxio_grpc_metric_GetMetricsPResponse_descriptor.getNestedTypes().get(0);
    internal_static_alluxio_grpc_metric_GetMetricsPResponse_MetricsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_metric_GetMetricsPResponse_MetricsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    alluxio.grpc.CommonProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
