// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: file_system_master.proto

package alluxio.grpc;

public interface GetMountTablePResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.GetMountTablePResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, .alluxio.grpc.MountPointInfo&gt; mountTable = 1;</code>
   */
  int getMountTableCount();
  /**
   * <code>map&lt;string, .alluxio.grpc.MountPointInfo&gt; mountTable = 1;</code>
   */
  boolean containsMountTable(
      java.lang.String key);
  /**
   * Use {@link #getMountTableMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo>
  getMountTable();
  /**
   * <code>map&lt;string, .alluxio.grpc.MountPointInfo&gt; mountTable = 1;</code>
   */
  java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo>
  getMountTableMap();
  /**
   * <code>map&lt;string, .alluxio.grpc.MountPointInfo&gt; mountTable = 1;</code>
   */

  alluxio.grpc.MountPointInfo getMountTableOrDefault(
      java.lang.String key,
      alluxio.grpc.MountPointInfo defaultValue);
  /**
   * <code>map&lt;string, .alluxio.grpc.MountPointInfo&gt; mountTable = 1;</code>
   */

  alluxio.grpc.MountPointInfo getMountTableOrThrow(
      java.lang.String key);
}
