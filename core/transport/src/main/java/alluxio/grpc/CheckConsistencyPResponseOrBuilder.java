// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

public interface CheckConsistencyPResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.file.CheckConsistencyPResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string inconsistentPaths = 1;</code>
   */
  java.util.List<java.lang.String>
      getInconsistentPathsList();
  /**
   * <code>repeated string inconsistentPaths = 1;</code>
   */
  int getInconsistentPathsCount();
  /**
   * <code>repeated string inconsistentPaths = 1;</code>
   */
  java.lang.String getInconsistentPaths(int index);
  /**
   * <code>repeated string inconsistentPaths = 1;</code>
   */
  com.google.protobuf.ByteString
      getInconsistentPathsBytes(int index);
}
