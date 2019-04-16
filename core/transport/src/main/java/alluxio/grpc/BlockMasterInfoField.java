// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/block_master.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.block.BlockMasterInfoField}
 */
public enum BlockMasterInfoField
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CAPACITY_BYTES = 1;</code>
   */
  CAPACITY_BYTES(1),
  /**
   * <code>CAPACITY_BYTES_ON_TIERS = 2;</code>
   */
  CAPACITY_BYTES_ON_TIERS(2),
  /**
   * <code>FREE_BYTES = 3;</code>
   */
  FREE_BYTES(3),
  /**
   * <code>LIVE_WORKER_NUM = 4;</code>
   */
  LIVE_WORKER_NUM(4),
  /**
   * <code>LOST_WORKER_NUM = 5;</code>
   */
  LOST_WORKER_NUM(5),
  /**
   * <code>USED_BYTES = 6;</code>
   */
  USED_BYTES(6),
  /**
   * <code>USED_BYTES_ON_TIERS = 7;</code>
   */
  USED_BYTES_ON_TIERS(7),
  ;

  /**
   * <code>CAPACITY_BYTES = 1;</code>
   */
  public static final int CAPACITY_BYTES_VALUE = 1;
  /**
   * <code>CAPACITY_BYTES_ON_TIERS = 2;</code>
   */
  public static final int CAPACITY_BYTES_ON_TIERS_VALUE = 2;
  /**
   * <code>FREE_BYTES = 3;</code>
   */
  public static final int FREE_BYTES_VALUE = 3;
  /**
   * <code>LIVE_WORKER_NUM = 4;</code>
   */
  public static final int LIVE_WORKER_NUM_VALUE = 4;
  /**
   * <code>LOST_WORKER_NUM = 5;</code>
   */
  public static final int LOST_WORKER_NUM_VALUE = 5;
  /**
   * <code>USED_BYTES = 6;</code>
   */
  public static final int USED_BYTES_VALUE = 6;
  /**
   * <code>USED_BYTES_ON_TIERS = 7;</code>
   */
  public static final int USED_BYTES_ON_TIERS_VALUE = 7;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static BlockMasterInfoField valueOf(int value) {
    return forNumber(value);
  }

  public static BlockMasterInfoField forNumber(int value) {
    switch (value) {
      case 1: return CAPACITY_BYTES;
      case 2: return CAPACITY_BYTES_ON_TIERS;
      case 3: return FREE_BYTES;
      case 4: return LIVE_WORKER_NUM;
      case 5: return LOST_WORKER_NUM;
      case 6: return USED_BYTES;
      case 7: return USED_BYTES_ON_TIERS;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<BlockMasterInfoField>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      BlockMasterInfoField> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<BlockMasterInfoField>() {
          public BlockMasterInfoField findValueByNumber(int number) {
            return BlockMasterInfoField.forNumber(number);
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
    return alluxio.grpc.BlockMasterProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final BlockMasterInfoField[] VALUES = values();

  public static BlockMasterInfoField valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private BlockMasterInfoField(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.block.BlockMasterInfoField)
}

