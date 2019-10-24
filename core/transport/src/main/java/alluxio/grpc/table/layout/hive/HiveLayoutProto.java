// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/table/layout/hive/hive.proto

package alluxio.grpc.table.layout.hive;

public final class HiveLayoutProto {
  private HiveLayoutProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_StorageFormat_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_StorageFormat_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_HiveBucketProperty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_HiveBucketProperty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_SortingColumn_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_SortingColumn_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_Storage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_Storage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_Storage_SerdeParametersEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_Storage_SerdeParametersEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_PartitionInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_PartitionInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_table_layout_PartitionInfo_ParametersEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_PartitionInfo_ParametersEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!grpc/table/layout/hive/hive.proto\022\031all" +
      "uxio.grpc.table.layout\032\021grpc/common.prot" +
      "o\032\035grpc/table/table_master.proto\"K\n\rStor" +
      "ageFormat\022\r\n\005serde\030\001 \001(\t\022\024\n\014input_format" +
      "\030\002 \001(\t\022\025\n\routput_format\030\003 \001(\t\"|\n\022HiveBuc" +
      "ketProperty\022\023\n\013bucketed_by\030\001 \003(\t\022\024\n\014buck" +
      "et_count\030\002 \001(\003\022;\n\tsorted_by\030\003 \003(\0132(.allu" +
      "xio.grpc.table.layout.SortingColumn\"\231\001\n\r" +
      "SortingColumn\022\023\n\013column_name\030\001 \002(\t\022D\n\005or" +
      "der\030\002 \002(\01625.alluxio.grpc.table.layout.So" +
      "rtingColumn.SortingOrder\"-\n\014SortingOrder" +
      "\022\r\n\tASCENDING\020\000\022\016\n\nDESCENDING\020\001\"\300\002\n\007Stor" +
      "age\022@\n\016storage_format\030\001 \001(\0132(.alluxio.gr" +
      "pc.table.layout.StorageFormat\022\020\n\010locatio" +
      "n\030\002 \001(\t\022F\n\017bucket_property\030\003 \001(\0132-.allux" +
      "io.grpc.table.layout.HiveBucketProperty\022" +
      "\016\n\006skewed\030\004 \001(\010\022Q\n\020serde_parameters\030\005 \003(" +
      "\01327.alluxio.grpc.table.layout.Storage.Se" +
      "rdeParametersEntry\0326\n\024SerdeParametersEnt" +
      "ry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"\306\002\n\rP" +
      "artitionInfo\022\016\n\006values\030\001 \003(\t\022\026\n\016partitio" +
      "n_name\030\002 \001(\t\022\017\n\007db_name\030\003 \001(\t\022\022\n\ntable_n" +
      "ame\030\004 \001(\t\0223\n\007storage\030\005 \001(\0132\".alluxio.grp" +
      "c.table.layout.Storage\0222\n\tdata_cols\030\006 \003(" +
      "\0132\037.alluxio.grpc.table.FieldSchema\022L\n\npa" +
      "rameters\030\007 \003(\01328.alluxio.grpc.table.layo" +
      "ut.PartitionInfo.ParametersEntry\0321\n\017Para" +
      "metersEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:" +
      "\0028\001B3\n\036alluxio.grpc.table.layout.hiveB\017H" +
      "iveLayoutProtoP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          alluxio.grpc.CommonProto.getDescriptor(),
          alluxio.grpc.table.TableMasterProto.getDescriptor(),
        }, assigner);
    internal_static_alluxio_grpc_table_layout_StorageFormat_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_alluxio_grpc_table_layout_StorageFormat_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_StorageFormat_descriptor,
        new java.lang.String[] { "Serde", "InputFormat", "OutputFormat", });
    internal_static_alluxio_grpc_table_layout_HiveBucketProperty_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_alluxio_grpc_table_layout_HiveBucketProperty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_HiveBucketProperty_descriptor,
        new java.lang.String[] { "BucketedBy", "BucketCount", "SortedBy", });
    internal_static_alluxio_grpc_table_layout_SortingColumn_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_alluxio_grpc_table_layout_SortingColumn_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_SortingColumn_descriptor,
        new java.lang.String[] { "ColumnName", "Order", });
    internal_static_alluxio_grpc_table_layout_Storage_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_alluxio_grpc_table_layout_Storage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_Storage_descriptor,
        new java.lang.String[] { "StorageFormat", "Location", "BucketProperty", "Skewed", "SerdeParameters", });
    internal_static_alluxio_grpc_table_layout_Storage_SerdeParametersEntry_descriptor =
      internal_static_alluxio_grpc_table_layout_Storage_descriptor.getNestedTypes().get(0);
    internal_static_alluxio_grpc_table_layout_Storage_SerdeParametersEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_Storage_SerdeParametersEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_alluxio_grpc_table_layout_PartitionInfo_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_alluxio_grpc_table_layout_PartitionInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_PartitionInfo_descriptor,
        new java.lang.String[] { "Values", "PartitionName", "DbName", "TableName", "Storage", "DataCols", "Parameters", });
    internal_static_alluxio_grpc_table_layout_PartitionInfo_ParametersEntry_descriptor =
      internal_static_alluxio_grpc_table_layout_PartitionInfo_descriptor.getNestedTypes().get(0);
    internal_static_alluxio_grpc_table_layout_PartitionInfo_ParametersEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_PartitionInfo_ParametersEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    alluxio.grpc.CommonProto.getDescriptor();
    alluxio.grpc.table.TableMasterProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
