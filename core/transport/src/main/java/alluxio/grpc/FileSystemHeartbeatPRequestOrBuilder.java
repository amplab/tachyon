// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

public interface FileSystemHeartbeatPRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.file.FileSystemHeartbeatPRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   ** the id of the worker 
   * </pre>
   *
   * <code>optional int64 workerId = 1;</code>
   * @return Whether the workerId field is set.
   */
  boolean hasWorkerId();
  /**
   * <pre>
   ** the id of the worker 
   * </pre>
   *
   * <code>optional int64 workerId = 1;</code>
   * @return The workerId.
   */
  long getWorkerId();

  /**
   * <pre>
   ** the list of persisted files 
   * </pre>
   *
   * <code>repeated int64 persistedFiles = 2;</code>
   * @return A list containing the persistedFiles.
   */
  java.util.List<java.lang.Long> getPersistedFilesList();
  /**
   * <pre>
   ** the list of persisted files 
   * </pre>
   *
   * <code>repeated int64 persistedFiles = 2;</code>
   * @return The count of persistedFiles.
   */
  int getPersistedFilesCount();
  /**
   * <pre>
   ** the list of persisted files 
   * </pre>
   *
   * <code>repeated int64 persistedFiles = 2;</code>
   * @param index The index of the element to return.
   * @return The persistedFiles at the given index.
   */
  long getPersistedFiles(int index);

  /**
   * <code>optional .alluxio.grpc.file.FileSystemHeartbeatPOptions options = 3;</code>
   * @return Whether the options field is set.
   */
  boolean hasOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemHeartbeatPOptions options = 3;</code>
   * @return The options.
   */
  alluxio.grpc.FileSystemHeartbeatPOptions getOptions();
  /**
   * <code>optional .alluxio.grpc.file.FileSystemHeartbeatPOptions options = 3;</code>
   */
  alluxio.grpc.FileSystemHeartbeatPOptionsOrBuilder getOptionsOrBuilder();
}
