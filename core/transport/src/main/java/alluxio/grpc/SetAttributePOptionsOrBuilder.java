// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

public interface SetAttributePOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.file.SetAttributePOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional bool pinned = 1;</code>
   */
  boolean hasPinned();
  /**
   * <code>optional bool pinned = 1;</code>
   */
  boolean getPinned();

  /**
   * <code>optional bool persisted = 2;</code>
   */
  boolean hasPersisted();
  /**
   * <code>optional bool persisted = 2;</code>
   */
  boolean getPersisted();

  /**
   * <code>optional string owner = 3;</code>
   */
  boolean hasOwner();
  /**
   * <code>optional string owner = 3;</code>
   */
  java.lang.String getOwner();
  /**
   * <code>optional string owner = 3;</code>
   */
  com.google.protobuf.ByteString
      getOwnerBytes();

  /**
   * <code>optional string group = 4;</code>
   */
  boolean hasGroup();
  /**
   * <code>optional string group = 4;</code>
   */
  java.lang.String getGroup();
  /**
   * <code>optional string group = 4;</code>
   */
  com.google.protobuf.ByteString
      getGroupBytes();

  /**
   * <code>optional .alluxio.grpc.PMode mode = 5;</code>
   */
  boolean hasMode();
  /**
   * <code>optional .alluxio.grpc.PMode mode = 5;</code>
   */
  alluxio.grpc.PMode getMode();
  /**
   * <code>optional .alluxio.grpc.PMode mode = 5;</code>
   */
  alluxio.grpc.PModeOrBuilder getModeOrBuilder();

  /**
   * <code>optional bool recursive = 6;</code>
   */
  boolean hasRecursive();
  /**
   * <code>optional bool recursive = 6;</code>
   */
  boolean getRecursive();

  /**
   * <code>optional int32 replicationMax = 7;</code>
   */
  boolean hasReplicationMax();
  /**
   * <code>optional int32 replicationMax = 7;</code>
   */
  int getReplicationMax();

  /**
   * <code>optional int32 replicationMin = 8;</code>
   */
  boolean hasReplicationMin();
  /**
   * <code>optional int32 replicationMin = 8;</code>
   */
  int getReplicationMin();

  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 9;</code>
   */
  boolean hasCommonOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 9;</code>
   */
  alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 9;</code>
   */
  alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder();
}
