// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/block_master.proto

package alluxio.grpc;

public interface GetWorkerReportPOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.block.GetWorkerReportPOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   ** addresses are only valid when workerRange is SPECIFIED 
   * </pre>
   *
   * <code>repeated string addresses = 1;</code>
   */
  java.util.List<java.lang.String>
      getAddressesList();
  /**
   * <pre>
   ** addresses are only valid when workerRange is SPECIFIED 
   * </pre>
   *
   * <code>repeated string addresses = 1;</code>
   */
  int getAddressesCount();
  /**
   * <pre>
   ** addresses are only valid when workerRange is SPECIFIED 
   * </pre>
   *
   * <code>repeated string addresses = 1;</code>
   */
  java.lang.String getAddresses(int index);
  /**
   * <pre>
   ** addresses are only valid when workerRange is SPECIFIED 
   * </pre>
   *
   * <code>repeated string addresses = 1;</code>
   */
  com.google.protobuf.ByteString
      getAddressesBytes(int index);

  /**
   * <code>repeated .alluxio.grpc.block.WorkerInfoField fieldRanges = 2;</code>
   */
  java.util.List<alluxio.grpc.WorkerInfoField> getFieldRangesList();
  /**
   * <code>repeated .alluxio.grpc.block.WorkerInfoField fieldRanges = 2;</code>
   */
  int getFieldRangesCount();
  /**
   * <code>repeated .alluxio.grpc.block.WorkerInfoField fieldRanges = 2;</code>
   */
  alluxio.grpc.WorkerInfoField getFieldRanges(int index);

  /**
   * <code>optional .alluxio.grpc.block.WorkerRange workerRange = 3;</code>
   */
  boolean hasWorkerRange();
  /**
   * <code>optional .alluxio.grpc.block.WorkerRange workerRange = 3;</code>
   */
  alluxio.grpc.WorkerRange getWorkerRange();
}
