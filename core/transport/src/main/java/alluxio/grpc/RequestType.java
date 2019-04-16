// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/block_worker.proto

package alluxio.grpc;

/**
 * <pre>
 * The read/write request type. It can either be an Alluxio block operation or a UFS file operation.
 * next available id: 3
 * </pre>
 *
 * Protobuf enum {@code alluxio.grpc.block.RequestType}
 */
public enum RequestType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ALLUXIO_BLOCK = 0;</code>
   */
  ALLUXIO_BLOCK(0),
  /**
   * <code>UFS_FILE = 1;</code>
   */
  UFS_FILE(1),
  /**
   * <code>UFS_FALLBACK_BLOCK = 2;</code>
   */
  UFS_FALLBACK_BLOCK(2),
  ;

  /**
   * <code>ALLUXIO_BLOCK = 0;</code>
   */
  public static final int ALLUXIO_BLOCK_VALUE = 0;
  /**
   * <code>UFS_FILE = 1;</code>
   */
  public static final int UFS_FILE_VALUE = 1;
  /**
   * <code>UFS_FALLBACK_BLOCK = 2;</code>
   */
  public static final int UFS_FALLBACK_BLOCK_VALUE = 2;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static RequestType valueOf(int value) {
    return forNumber(value);
  }

  public static RequestType forNumber(int value) {
    switch (value) {
      case 0: return ALLUXIO_BLOCK;
      case 1: return UFS_FILE;
      case 2: return UFS_FALLBACK_BLOCK;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<RequestType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      RequestType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<RequestType>() {
          public RequestType findValueByNumber(int number) {
            return RequestType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return alluxio.grpc.BlockWorkerProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final RequestType[] VALUES = values();

  public static RequestType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private RequestType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.block.RequestType)
}

