// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/table/table_master.proto

package alluxio.grpc.table;

public interface GetAllTablesPResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:alluxio.grpc.table.GetAllTablesPResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string table = 1;</code>
   * @return A list containing the table.
   */
  java.util.List<java.lang.String>
      getTableList();
  /**
   * <code>repeated string table = 1;</code>
   * @return The count of table.
   */
  int getTableCount();
  /**
   * <code>repeated string table = 1;</code>
   * @param index The index of the element to return.
   * @return The table at the given index.
   */
  java.lang.String getTable(int index);
  /**
   * <code>repeated string table = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the table at the given index.
   */
  com.google.protobuf.ByteString
      getTableBytes(int index);
}
