// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/catalog/catalog_master.proto

package alluxio.grpc.catalog;

public interface TransformTablePRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.catalog.TransformTablePRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string db_name = 1;</code>
   */
  boolean hasDbName();
  /**
   * <code>optional string db_name = 1;</code>
   */
  java.lang.String getDbName();
  /**
   * <code>optional string db_name = 1;</code>
   */
  com.google.protobuf.ByteString
      getDbNameBytes();

  /**
   * <code>optional string table_name = 2;</code>
   */
  boolean hasTableName();
  /**
   * <code>optional string table_name = 2;</code>
   */
  java.lang.String getTableName();
  /**
   * <code>optional string table_name = 2;</code>
   */
  com.google.protobuf.ByteString
      getTableNameBytes();

  /**
   * <code>optional string type = 3;</code>
   */
  boolean hasType();
  /**
   * <code>optional string type = 3;</code>
   */
  java.lang.String getType();
  /**
   * <code>optional string type = 3;</code>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>map&lt;string, string&gt; partitions = 4;</code>
   */
  int getPartitionsCount();
  /**
   * <code>map&lt;string, string&gt; partitions = 4;</code>
   */
  boolean containsPartitions(
      java.lang.String key);
  /**
   * Use {@link #getPartitionsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getPartitions();
  /**
   * <code>map&lt;string, string&gt; partitions = 4;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getPartitionsMap();
  /**
   * <code>map&lt;string, string&gt; partitions = 4;</code>
   */

  java.lang.String getPartitionsOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; partitions = 4;</code>
   */

  java.lang.String getPartitionsOrThrow(
      java.lang.String key);
}
