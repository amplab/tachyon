// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/job_master.proto

package alluxio.grpc;

public interface RegisterJobWorkerPRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.job.RegisterJobWorkerPRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .alluxio.grpc.WorkerNetAddress workerNetAddress = 1;</code>
   */
  boolean hasWorkerNetAddress();
  /**
   * <code>optional .alluxio.grpc.WorkerNetAddress workerNetAddress = 1;</code>
   */
  alluxio.grpc.WorkerNetAddress getWorkerNetAddress();
  /**
   * <code>optional .alluxio.grpc.WorkerNetAddress workerNetAddress = 1;</code>
   */
  alluxio.grpc.WorkerNetAddressOrBuilder getWorkerNetAddressOrBuilder();

  /**
   * <code>optional .alluxio.grpc.job.RegisterJobWorkerPOptions options = 2;</code>
   */
  boolean hasOptions();
  /**
   * <code>optional .alluxio.grpc.job.RegisterJobWorkerPOptions options = 2;</code>
   */
  alluxio.grpc.RegisterJobWorkerPOptions getOptions();
  /**
   * <code>optional .alluxio.grpc.job.RegisterJobWorkerPOptions options = 2;</code>
   */
  alluxio.grpc.RegisterJobWorkerPOptionsOrBuilder getOptionsOrBuilder();
}
