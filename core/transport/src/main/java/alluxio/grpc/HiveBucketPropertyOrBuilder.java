// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/catalog_master.proto

package alluxio.grpc;

public interface HiveBucketPropertyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.HiveBucketProperty)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string bucketed_by = 1;</code>
   */
  java.util.List<java.lang.String>
      getBucketedByList();
  /**
   * <code>repeated string bucketed_by = 1;</code>
   */
  int getBucketedByCount();
  /**
   * <code>repeated string bucketed_by = 1;</code>
   */
  java.lang.String getBucketedBy(int index);
  /**
   * <code>repeated string bucketed_by = 1;</code>
   */
  com.google.protobuf.ByteString
      getBucketedByBytes(int index);

  /**
   * <code>optional int64 bucket_count = 2;</code>
   */
  boolean hasBucketCount();
  /**
   * <code>optional int64 bucket_count = 2;</code>
   */
  long getBucketCount();

  /**
   * <code>repeated .alluxio.grpc.SortingColumn sorted_by = 3;</code>
   */
  java.util.List<alluxio.grpc.SortingColumn> 
      getSortedByList();
  /**
   * <code>repeated .alluxio.grpc.SortingColumn sorted_by = 3;</code>
   */
  alluxio.grpc.SortingColumn getSortedBy(int index);
  /**
   * <code>repeated .alluxio.grpc.SortingColumn sorted_by = 3;</code>
   */
  int getSortedByCount();
  /**
   * <code>repeated .alluxio.grpc.SortingColumn sorted_by = 3;</code>
   */
  java.util.List<? extends alluxio.grpc.SortingColumnOrBuilder> 
      getSortedByOrBuilderList();
  /**
   * <code>repeated .alluxio.grpc.SortingColumn sorted_by = 3;</code>
   */
  alluxio.grpc.SortingColumnOrBuilder getSortedByOrBuilder(
      int index);
}
