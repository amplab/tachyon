// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

public interface FreePOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.file.FreePOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional bool recursive = 1;</code>
   * @return Whether the recursive field is set.
   */
  boolean hasRecursive();
  /**
   * <code>optional bool recursive = 1;</code>
   * @return The recursive.
   */
  boolean getRecursive();

  /**
   * <code>optional bool forced = 2;</code>
   * @return Whether the forced field is set.
   */
  boolean hasForced();
  /**
   * <code>optional bool forced = 2;</code>
   * @return The forced.
   */
  boolean getForced();

  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   * @return Whether the commonOptions field is set.
   */
  boolean hasCommonOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   * @return The commonOptions.
   */
  alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   */
  alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder();
}
