// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

public interface BackupPOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.meta.BackupPOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string targetDirectory = 1;</code>
   */
  boolean hasTargetDirectory();
  /**
   * <code>optional string targetDirectory = 1;</code>
   */
  java.lang.String getTargetDirectory();
  /**
   * <code>optional string targetDirectory = 1;</code>
   */
  com.google.protobuf.ByteString
      getTargetDirectoryBytes();

  /**
   * <code>optional bool useRootUnderFileSystem = 2;</code>
   */
  boolean hasUseRootUnderFileSystem();
  /**
   * <code>optional bool useRootUnderFileSystem = 2;</code>
   */
  boolean getUseRootUnderFileSystem();
}
