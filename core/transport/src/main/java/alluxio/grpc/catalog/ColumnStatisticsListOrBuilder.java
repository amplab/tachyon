// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/catalog/catalog_master.proto

package alluxio.grpc.catalog;

public interface ColumnStatisticsListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.catalog.ColumnStatisticsList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .alluxio.grpc.catalog.ColumnStatisticsInfo statistics = 1;</code>
   */
  java.util.List<alluxio.grpc.catalog.ColumnStatisticsInfo> 
      getStatisticsList();
  /**
   * <code>repeated .alluxio.grpc.catalog.ColumnStatisticsInfo statistics = 1;</code>
   */
  alluxio.grpc.catalog.ColumnStatisticsInfo getStatistics(int index);
  /**
   * <code>repeated .alluxio.grpc.catalog.ColumnStatisticsInfo statistics = 1;</code>
   */
  int getStatisticsCount();
  /**
   * <code>repeated .alluxio.grpc.catalog.ColumnStatisticsInfo statistics = 1;</code>
   */
  java.util.List<? extends alluxio.grpc.catalog.ColumnStatisticsInfoOrBuilder> 
      getStatisticsOrBuilderList();
  /**
   * <code>repeated .alluxio.grpc.catalog.ColumnStatisticsInfo statistics = 1;</code>
   */
  alluxio.grpc.catalog.ColumnStatisticsInfoOrBuilder getStatisticsOrBuilder(
      int index);
}
