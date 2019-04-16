// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.file.UfsPMode}
 */
public enum UfsPMode
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>NO_ACCESS = 1;</code>
   */
  NO_ACCESS(1),
  /**
   * <code>READ_ONLY = 2;</code>
   */
  READ_ONLY(2),
  /**
   * <code>READ_WRITE = 3;</code>
   */
  READ_WRITE(3),
  ;

  /**
   * <code>NO_ACCESS = 1;</code>
   */
  public static final int NO_ACCESS_VALUE = 1;
  /**
   * <code>READ_ONLY = 2;</code>
   */
  public static final int READ_ONLY_VALUE = 2;
  /**
   * <code>READ_WRITE = 3;</code>
   */
  public static final int READ_WRITE_VALUE = 3;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static UfsPMode valueOf(int value) {
    return forNumber(value);
  }

  public static UfsPMode forNumber(int value) {
    switch (value) {
      case 1: return NO_ACCESS;
      case 2: return READ_ONLY;
      case 3: return READ_WRITE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<UfsPMode>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      UfsPMode> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<UfsPMode>() {
          public UfsPMode findValueByNumber(int number) {
            return UfsPMode.forNumber(number);
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
    return alluxio.grpc.FileSystemMasterProto.getDescriptor().getEnumTypes().get(7);
  }

  private static final UfsPMode[] VALUES = values();

  public static UfsPMode valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private UfsPMode(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.file.UfsPMode)
}

