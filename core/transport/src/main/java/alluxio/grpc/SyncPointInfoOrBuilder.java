// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

public interface SyncPointInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.file.SyncPointInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string syncPointUri = 1;</code>
   */
  boolean hasSyncPointUri();
  /**
   * <code>optional string syncPointUri = 1;</code>
   */
  java.lang.String getSyncPointUri();
  /**
   * <code>optional string syncPointUri = 1;</code>
   */
  com.google.protobuf.ByteString
      getSyncPointUriBytes();

  /**
   * <code>optional .alluxio.grpc.file.SyncPointStatus syncStatus = 2;</code>
   */
  boolean hasSyncStatus();
  /**
   * <code>optional .alluxio.grpc.file.SyncPointStatus syncStatus = 2;</code>
   */
  alluxio.grpc.SyncPointStatus getSyncStatus();
}
