// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: block_worker.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.LockBlockStatus}
 */
public enum LockBlockStatus
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   ** The Alluxio block is acquired. 
   * </pre>
   *
   * <code>ALLUXIO_BLOCK_LOCKED = 1;</code>
   */
  ALLUXIO_BLOCK_LOCKED(1),
  /**
   * <pre>
   ** The block is not in Alluxio but a UFS access token is acquired for this block. 
   * </pre>
   *
   * <code>UFS_TOKEN_ACQUIRED = 2;</code>
   */
  UFS_TOKEN_ACQUIRED(2),
  /**
   * <pre>
   ** The block is not in Alluxio and a UFS access token is not acquired. 
   * </pre>
   *
   * <code>UFS_TOKEN_NOT_ACQUIRED = 3;</code>
   */
  UFS_TOKEN_NOT_ACQUIRED(3),
  ;

  /**
   * <pre>
   ** The Alluxio block is acquired. 
   * </pre>
   *
   * <code>ALLUXIO_BLOCK_LOCKED = 1;</code>
   */
  public static final int ALLUXIO_BLOCK_LOCKED_VALUE = 1;
  /**
   * <pre>
   ** The block is not in Alluxio but a UFS access token is acquired for this block. 
   * </pre>
   *
   * <code>UFS_TOKEN_ACQUIRED = 2;</code>
   */
  public static final int UFS_TOKEN_ACQUIRED_VALUE = 2;
  /**
   * <pre>
   ** The block is not in Alluxio and a UFS access token is not acquired. 
   * </pre>
   *
   * <code>UFS_TOKEN_NOT_ACQUIRED = 3;</code>
   */
  public static final int UFS_TOKEN_NOT_ACQUIRED_VALUE = 3;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static LockBlockStatus valueOf(int value) {
    return forNumber(value);
  }

  public static LockBlockStatus forNumber(int value) {
    switch (value) {
      case 1: return ALLUXIO_BLOCK_LOCKED;
      case 2: return UFS_TOKEN_ACQUIRED;
      case 3: return UFS_TOKEN_NOT_ACQUIRED;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<LockBlockStatus>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      LockBlockStatus> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<LockBlockStatus>() {
          public LockBlockStatus findValueByNumber(int number) {
            return LockBlockStatus.forNumber(number);
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

  private static final LockBlockStatus[] VALUES = values();

  public static LockBlockStatus valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private LockBlockStatus(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.LockBlockStatus)
}

