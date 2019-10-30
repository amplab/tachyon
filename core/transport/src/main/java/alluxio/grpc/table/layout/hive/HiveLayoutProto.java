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
    internal_static_alluxio_grpc_table_layout_StorageFormat_SerdelibParametersEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_table_layout_StorageFormat_SerdelibParametersEntry_fieldAccessorTable;
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
      "o\032\035grpc/table/table_master.proto\"\345\001\n\rSto" +
      "rageFormat\022\r\n\005serde\030\001 \001(\t\022\024\n\014input_forma" +
      "t\030\002 \001(\t\022\025\n\routput_format\030\003 \001(\t\022]\n\023serdel" +
      "ib_parameters\030\004 \003(\0132@.alluxio.grpc.table" +
      ".layout.StorageFormat.SerdelibParameters" +
      "Entry\0329\n\027SerdelibParametersEntry\022\013\n\003key\030" +
      "\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"|\n\022HiveBucketPr" +
      "operty\022\023\n\013bucketed_by\030\001 \003(\t\022\024\n\014bucket_co" +
      "unt\030\002 \001(\003\022;\n\tsorted_by\030\003 \003(\0132(.alluxio.g" +
      "rpc.table.layout.SortingColumn\"\231\001\n\rSorti" +
      "ngColumn\022\023\n\013column_name\030\001 \002(\t\022D\n\005order\030\002" +
      " \002(\01625.alluxio.grpc.table.layout.Sorting" +
      "Column.SortingOrder\"-\n\014SortingOrder\022\r\n\tA" +
      "SCENDING\020\000\022\016\n\nDESCENDING\020\001\"\300\002\n\007Storage\022@" +
      "\n\016storage_format\030\001 \001(\0132(.alluxio.grpc.ta" +
      "ble.layout.StorageFormat\022\020\n\010location\030\002 \001" +
      "(\t\022F\n\017bucket_property\030\003 \001(\0132-.alluxio.gr" +
      "pc.table.layout.HiveBucketProperty\022\016\n\006sk" +
      "ewed\030\004 \001(\010\022Q\n\020serde_parameters\030\005 \003(\01327.a" +
      "lluxio.grpc.table.layout.Storage.SerdePa" +
      "rametersEntry\0326\n\024SerdeParametersEntry\022\013\n" +
      "\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"\306\002\n\rPartit" +
      "ionInfo\022\016\n\006values\030\001 \003(\t\022\026\n\016partition_nam" +
      "e\030\002 \001(\t\022\017\n\007db_name\030\003 \001(\t\022\022\n\ntable_name\030\004" +
      " \001(\t\0223\n\007storage\030\005 \001(\0132\".alluxio.grpc.tab" +
      "le.layout.Storage\0222\n\tdata_cols\030\006 \003(\0132\037.a" +
      "lluxio.grpc.table.FieldSchema\022L\n\nparamet" +
      "ers\030\007 \003(\01328.alluxio.grpc.table.layout.Pa" +
      "rtitionInfo.ParametersEntry\0321\n\017Parameter" +
      "sEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001B3" +
      "\n\036alluxio.grpc.table.layout.hiveB\017HiveLa" +
      "youtProtoP\001"
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
        new java.lang.String[] { "Serde", "InputFormat", "OutputFormat", "SerdelibParameters", });
    internal_static_alluxio_grpc_table_layout_StorageFormat_SerdelibParametersEntry_descriptor =
      internal_static_alluxio_grpc_table_layout_StorageFormat_descriptor.getNestedTypes().get(0);
    internal_static_alluxio_grpc_table_layout_StorageFormat_SerdelibParametersEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_table_layout_StorageFormat_SerdelibParametersEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
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
