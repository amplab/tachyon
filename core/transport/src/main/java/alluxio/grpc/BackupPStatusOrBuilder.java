// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

public interface BackupPStatusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.meta.BackupPStatus)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .alluxio.grpc.meta.BackupState backupState = 1;</code>
   */
  boolean hasBackupState();
  /**
   * <code>optional .alluxio.grpc.meta.BackupState backupState = 1;</code>
   */
  alluxio.grpc.BackupState getBackupState();

  /**
   * <code>optional string backupHost = 2;</code>
   */
  boolean hasBackupHost();
  /**
   * <code>optional string backupHost = 2;</code>
   */
  java.lang.String getBackupHost();
  /**
   * <code>optional string backupHost = 2;</code>
   */
  com.google.protobuf.ByteString
      getBackupHostBytes();

  /**
   * <code>optional string backupUri = 3;</code>
   */
  boolean hasBackupUri();
  /**
   * <code>optional string backupUri = 3;</code>
   */
  java.lang.String getBackupUri();
  /**
   * <code>optional string backupUri = 3;</code>
   */
  com.google.protobuf.ByteString
      getBackupUriBytes();

  /**
   * <code>optional int64 entryCount = 4;</code>
   */
  boolean hasEntryCount();
  /**
   * <code>optional int64 entryCount = 4;</code>
   */
  long getEntryCount();

  /**
   * <code>optional bytes backupError = 5;</code>
   */
  boolean hasBackupError();
  /**
   * <code>optional bytes backupError = 5;</code>
   */
  com.google.protobuf.ByteString getBackupError();
}
