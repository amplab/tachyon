// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/table/table_master.proto

package alluxio.grpc.table;

public interface GetTablePRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.table.GetTablePRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string db_name = 1;</code>
   * @return Whether the dbName field is set.
   */
  boolean hasDbName();
  /**
   * <code>optional string db_name = 1;</code>
   * @return The dbName.
   */
  java.lang.String getDbName();
  /**
   * <code>optional string db_name = 1;</code>
   * @return The bytes for dbName.
   */
  com.google.protobuf.ByteString
      getDbNameBytes();

  /**
   * <code>optional string table_name = 2;</code>
   * @return Whether the tableName field is set.
   */
  boolean hasTableName();
  /**
   * <code>optional string table_name = 2;</code>
   * @return The tableName.
   */
  java.lang.String getTableName();
  /**
   * <code>optional string table_name = 2;</code>
   * @return The bytes for tableName.
   */
  com.google.protobuf.ByteString
      getTableNameBytes();
}
