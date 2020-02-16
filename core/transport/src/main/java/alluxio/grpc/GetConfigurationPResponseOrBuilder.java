// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

public interface GetConfigurationPResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.meta.GetConfigurationPResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .alluxio.grpc.ConfigProperty clusterConfigs = 1;</code>
   */
  java.util.List<alluxio.grpc.ConfigProperty> 
      getClusterConfigsList();
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty clusterConfigs = 1;</code>
   */
  alluxio.grpc.ConfigProperty getClusterConfigs(int index);
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty clusterConfigs = 1;</code>
   */
  int getClusterConfigsCount();
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty clusterConfigs = 1;</code>
   */
  java.util.List<? extends alluxio.grpc.ConfigPropertyOrBuilder> 
      getClusterConfigsOrBuilderList();
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty clusterConfigs = 1;</code>
   */
  alluxio.grpc.ConfigPropertyOrBuilder getClusterConfigsOrBuilder(
      int index);

  /**
   * <code>map&lt;string, .alluxio.grpc.meta.ConfigProperties&gt; pathConfigs = 2;</code>
   */
  int getPathConfigsCount();
  /**
   * <code>map&lt;string, .alluxio.grpc.meta.ConfigProperties&gt; pathConfigs = 2;</code>
   */
  boolean containsPathConfigs(
      java.lang.String key);
  /**
   * Use {@link #getPathConfigsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, alluxio.grpc.ConfigProperties>
  getPathConfigs();
  /**
   * <code>map&lt;string, .alluxio.grpc.meta.ConfigProperties&gt; pathConfigs = 2;</code>
   */
  java.util.Map<java.lang.String, alluxio.grpc.ConfigProperties>
  getPathConfigsMap();
  /**
   * <code>map&lt;string, .alluxio.grpc.meta.ConfigProperties&gt; pathConfigs = 2;</code>
   */

  alluxio.grpc.ConfigProperties getPathConfigsOrDefault(
      java.lang.String key,
      alluxio.grpc.ConfigProperties defaultValue);
  /**
   * <code>map&lt;string, .alluxio.grpc.meta.ConfigProperties&gt; pathConfigs = 2;</code>
   */

  alluxio.grpc.ConfigProperties getPathConfigsOrThrow(
      java.lang.String key);

  /**
   * <code>optional string clusterConfigHash = 3;</code>
   * @return Whether the clusterConfigHash field is set.
   */
  boolean hasClusterConfigHash();
  /**
   * <code>optional string clusterConfigHash = 3;</code>
   * @return The clusterConfigHash.
   */
  java.lang.String getClusterConfigHash();
  /**
   * <code>optional string clusterConfigHash = 3;</code>
   * @return The bytes for clusterConfigHash.
   */
  com.google.protobuf.ByteString
      getClusterConfigHashBytes();

  /**
   * <code>optional string pathConfigHash = 4;</code>
   * @return Whether the pathConfigHash field is set.
   */
  boolean hasPathConfigHash();
  /**
   * <code>optional string pathConfigHash = 4;</code>
   * @return The pathConfigHash.
   */
  java.lang.String getPathConfigHash();
  /**
   * <code>optional string pathConfigHash = 4;</code>
   * @return The bytes for pathConfigHash.
   */
  com.google.protobuf.ByteString
      getPathConfigHashBytes();
}
