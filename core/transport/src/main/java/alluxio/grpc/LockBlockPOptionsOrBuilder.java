// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: block_worker.proto

package alluxio.grpc;

public interface LockBlockPOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.LockBlockPOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string ufsPath = 1;</code>
   */
  boolean hasUfsPath();
  /**
   * <code>optional string ufsPath = 1;</code>
   */
  java.lang.String getUfsPath();
  /**
   * <code>optional string ufsPath = 1;</code>
   */
  com.google.protobuf.ByteString
      getUfsPathBytes();

  /**
   * <code>optional int64 offset = 2;</code>
   */
  boolean hasOffset();
  /**
   * <code>optional int64 offset = 2;</code>
   */
  long getOffset();

  /**
   * <code>optional int64 blockSize = 3;</code>
   */
  boolean hasBlockSize();
  /**
   * <code>optional int64 blockSize = 3;</code>
   */
  long getBlockSize();

  /**
   * <code>optional int32 maxUfsReadConcurrency = 4;</code>
   */
  boolean hasMaxUfsReadConcurrency();
  /**
   * <code>optional int32 maxUfsReadConcurrency = 4;</code>
   */
  int getMaxUfsReadConcurrency();

  /**
   * <code>optional int64 mountId = 5;</code>
   */
  boolean hasMountId();
  /**
   * <code>optional int64 mountId = 5;</code>
   */
  long getMountId();
}
