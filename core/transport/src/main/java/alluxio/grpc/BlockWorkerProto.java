// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/block_worker.proto

package alluxio.grpc;

public final class BlockWorkerProto {
  private BlockWorkerProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_CheckRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_CheckRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_CheckResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_CheckResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_Chunk_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_Chunk_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_ReadRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_ReadRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_ReadResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_ReadResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_WriteRequestCommand_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_WriteRequestCommand_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_WriteRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_WriteRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_WriteResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_WriteResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_AsyncCacheRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_AsyncCacheRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_AsyncCacheResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_AsyncCacheResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_OpenLocalBlockRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_OpenLocalBlockRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_OpenLocalBlockResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_OpenLocalBlockResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_CreateLocalBlockRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_CreateLocalBlockRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_CreateLocalBlockResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_CreateLocalBlockResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_RemoveBlockRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_RemoveBlockRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alluxio_grpc_block_RemoveBlockResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alluxio_grpc_block_RemoveBlockResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027grpc/block_worker.proto\022\022alluxio.grpc." +
      "block\032\037proto/dataserver/protocol.proto\"\016" +
      "\n\014CheckRequest\"\017\n\rCheckResponse\"\025\n\005Chunk" +
      "\022\014\n\004data\030\001 \001(\014\"\314\001\n\013ReadRequest\022\020\n\010block_" +
      "id\030\001 \001(\003\022\016\n\006offset\030\002 \001(\003\022\016\n\006length\030\003 \001(\003" +
      "\022\017\n\007promote\030\004 \001(\010\022\022\n\nchunk_size\030\005 \001(\003\022M\n" +
      "\026open_ufs_block_options\030\006 \001(\0132-.alluxio." +
      "proto.dataserver.OpenUfsBlockOptions\022\027\n\017" +
      "offset_received\030\007 \001(\003\"8\n\014ReadResponse\022(\n" +
      "\005chunk\030\001 \001(\0132\031.alluxio.grpc.block.Chunk\"" +
      "\241\002\n\023WriteRequestCommand\022-\n\004type\030\001 \001(\0162\037." +
      "alluxio.grpc.block.RequestType\022\n\n\002id\030\002 \001" +
      "(\003\022\016\n\006offset\030\003 \001(\003\022\014\n\004tier\030\004 \001(\005\022\r\n\005flus" +
      "h\030\005 \001(\010\022O\n\027create_ufs_file_options\030\006 \001(\013" +
      "2..alluxio.proto.dataserver.CreateUfsFil" +
      "eOptions\022Q\n\030create_ufs_block_options\030\007 \001" +
      "(\0132/.alluxio.proto.dataserver.CreateUfsB" +
      "lockOptions\"\177\n\014WriteRequest\022:\n\007command\030\001" +
      " \001(\0132\'.alluxio.grpc.block.WriteRequestCo" +
      "mmandH\000\022*\n\005chunk\030\002 \001(\0132\031.alluxio.grpc.bl" +
      "ock.ChunkH\000B\007\n\005value\"\037\n\rWriteResponse\022\016\n" +
      "\006offset\030\001 \001(\003\"\256\001\n\021AsyncCacheRequest\022\020\n\010b" +
      "lock_id\030\001 \001(\003\022\023\n\013source_host\030\002 \001(\t\022\023\n\013so" +
      "urce_port\030\003 \001(\005\022M\n\026open_ufs_block_option" +
      "s\030\004 \001(\0132-.alluxio.proto.dataserver.OpenU" +
      "fsBlockOptions\022\016\n\006length\030\005 \001(\003\"\024\n\022AsyncC" +
      "acheResponse\":\n\025OpenLocalBlockRequest\022\020\n" +
      "\010block_id\030\001 \001(\003\022\017\n\007promote\030\002 \001(\010\"&\n\026Open" +
      "LocalBlockResponse\022\014\n\004path\030\001 \001(\t\"\213\001\n\027Cre" +
      "ateLocalBlockRequest\022\020\n\010block_id\030\001 \001(\003\022\014" +
      "\n\004tier\030\003 \001(\005\022\030\n\020space_to_reserve\030\004 \001(\003\022\032" +
      "\n\022only_reserve_space\030\005 \001(\010\022\032\n\022cleanup_on" +
      "_failure\030\006 \001(\010\"(\n\030CreateLocalBlockRespon" +
      "se\022\014\n\004path\030\001 \001(\t\"&\n\022RemoveBlockRequest\022\020" +
      "\n\010block_id\030\001 \001(\003\"\025\n\023RemoveBlockResponse*" +
      "F\n\013RequestType\022\021\n\rALLUXIO_BLOCK\020\000\022\014\n\010UFS" +
      "_FILE\020\001\022\026\n\022UFS_FALLBACK_BLOCK\020\0022\325\004\n\013Bloc" +
      "kWorker\022R\n\tReadBlock\022\037.alluxio.grpc.bloc" +
      "k.ReadRequest\032 .alluxio.grpc.block.ReadR" +
      "esponse(\0010\001\022U\n\nWriteBlock\022 .alluxio.grpc" +
      ".block.WriteRequest\032!.alluxio.grpc.block" +
      ".WriteResponse(\0010\001\022k\n\016OpenLocalBlock\022).a" +
      "lluxio.grpc.block.OpenLocalBlockRequest\032" +
      "*.alluxio.grpc.block.OpenLocalBlockRespo" +
      "nse(\0010\001\022q\n\020CreateLocalBlock\022+.alluxio.gr" +
      "pc.block.CreateLocalBlockRequest\032,.allux" +
      "io.grpc.block.CreateLocalBlockResponse(\001" +
      "0\001\022[\n\nAsyncCache\022%.alluxio.grpc.block.As" +
      "yncCacheRequest\032&.alluxio.grpc.block.Asy" +
      "ncCacheResponse\022^\n\013RemoveBlock\022&.alluxio" +
      ".grpc.block.RemoveBlockRequest\032\'.alluxio" +
      ".grpc.block.RemoveBlockResponseB\"\n\014allux" +
      "io.grpcB\020BlockWorkerProtoP\001"
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
          alluxio.proto.dataserver.Protocol.getDescriptor(),
        }, assigner);
    internal_static_alluxio_grpc_block_CheckRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_alluxio_grpc_block_CheckRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_CheckRequest_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_block_CheckResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_alluxio_grpc_block_CheckResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_CheckResponse_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_block_Chunk_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_alluxio_grpc_block_Chunk_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_Chunk_descriptor,
        new java.lang.String[] { "Data", });
    internal_static_alluxio_grpc_block_ReadRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_alluxio_grpc_block_ReadRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_ReadRequest_descriptor,
        new java.lang.String[] { "BlockId", "Offset", "Length", "Promote", "ChunkSize", "OpenUfsBlockOptions", "OffsetReceived", });
    internal_static_alluxio_grpc_block_ReadResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_alluxio_grpc_block_ReadResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_ReadResponse_descriptor,
        new java.lang.String[] { "Chunk", });
    internal_static_alluxio_grpc_block_WriteRequestCommand_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_alluxio_grpc_block_WriteRequestCommand_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_WriteRequestCommand_descriptor,
        new java.lang.String[] { "Type", "Id", "Offset", "Tier", "Flush", "CreateUfsFileOptions", "CreateUfsBlockOptions", });
    internal_static_alluxio_grpc_block_WriteRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_alluxio_grpc_block_WriteRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_WriteRequest_descriptor,
        new java.lang.String[] { "Command", "Chunk", "Value", });
    internal_static_alluxio_grpc_block_WriteResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_alluxio_grpc_block_WriteResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_WriteResponse_descriptor,
        new java.lang.String[] { "Offset", });
    internal_static_alluxio_grpc_block_AsyncCacheRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_alluxio_grpc_block_AsyncCacheRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_AsyncCacheRequest_descriptor,
        new java.lang.String[] { "BlockId", "SourceHost", "SourcePort", "OpenUfsBlockOptions", "Length", });
    internal_static_alluxio_grpc_block_AsyncCacheResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_alluxio_grpc_block_AsyncCacheResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_AsyncCacheResponse_descriptor,
        new java.lang.String[] { });
    internal_static_alluxio_grpc_block_OpenLocalBlockRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_alluxio_grpc_block_OpenLocalBlockRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_OpenLocalBlockRequest_descriptor,
        new java.lang.String[] { "BlockId", "Promote", });
    internal_static_alluxio_grpc_block_OpenLocalBlockResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_alluxio_grpc_block_OpenLocalBlockResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_OpenLocalBlockResponse_descriptor,
        new java.lang.String[] { "Path", });
    internal_static_alluxio_grpc_block_CreateLocalBlockRequest_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_alluxio_grpc_block_CreateLocalBlockRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_CreateLocalBlockRequest_descriptor,
        new java.lang.String[] { "BlockId", "Tier", "SpaceToReserve", "OnlyReserveSpace", "CleanupOnFailure", });
    internal_static_alluxio_grpc_block_CreateLocalBlockResponse_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_alluxio_grpc_block_CreateLocalBlockResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_CreateLocalBlockResponse_descriptor,
        new java.lang.String[] { "Path", });
    internal_static_alluxio_grpc_block_RemoveBlockRequest_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_alluxio_grpc_block_RemoveBlockRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_RemoveBlockRequest_descriptor,
        new java.lang.String[] { "BlockId", });
    internal_static_alluxio_grpc_block_RemoveBlockResponse_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_alluxio_grpc_block_RemoveBlockResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alluxio_grpc_block_RemoveBlockResponse_descriptor,
        new java.lang.String[] { });
    alluxio.proto.dataserver.Protocol.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
