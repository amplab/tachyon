// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

public interface MasterHeartbeatPRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.meta.MasterHeartbeatPRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 masterId = 1;</code>
   */
  boolean hasMasterId();
  /**
   * <code>optional int64 masterId = 1;</code>
   */
  long getMasterId();

  /**
   * <code>optional .alluxio.grpc.meta.MasterHeartbeatPOptions options = 2;</code>
   */
  boolean hasOptions();
  /**
   * <code>optional .alluxio.grpc.meta.MasterHeartbeatPOptions options = 2;</code>
   */
  alluxio.grpc.MasterHeartbeatPOptions getOptions();
  /**
   * <code>optional .alluxio.grpc.meta.MasterHeartbeatPOptions options = 2;</code>
   */
  alluxio.grpc.MasterHeartbeatPOptionsOrBuilder getOptionsOrBuilder();
}
