// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/common.proto

package alluxio.grpc;

public interface BlockInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.BlockInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 blockId = 1;</code>
   * @return Whether the blockId field is set.
   */
  boolean hasBlockId();
  /**
   * <code>optional int64 blockId = 1;</code>
   * @return The blockId.
   */
  long getBlockId();

  /**
   * <code>optional int64 length = 2;</code>
   * @return Whether the length field is set.
   */
  boolean hasLength();
  /**
   * <code>optional int64 length = 2;</code>
   * @return The length.
   */
  long getLength();

  /**
   * <code>repeated .alluxio.grpc.BlockLocation locations = 3;</code>
   */
  java.util.List<alluxio.grpc.BlockLocation> 
      getLocationsList();
  /**
   * <code>repeated .alluxio.grpc.BlockLocation locations = 3;</code>
   */
  alluxio.grpc.BlockLocation getLocations(int index);
  /**
   * <code>repeated .alluxio.grpc.BlockLocation locations = 3;</code>
   */
  int getLocationsCount();
  /**
   * <code>repeated .alluxio.grpc.BlockLocation locations = 3;</code>
   */
  java.util.List<? extends alluxio.grpc.BlockLocationOrBuilder> 
      getLocationsOrBuilderList();
  /**
   * <code>repeated .alluxio.grpc.BlockLocation locations = 3;</code>
   */
  alluxio.grpc.BlockLocationOrBuilder getLocationsOrBuilder(
      int index);
}
