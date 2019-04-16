// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/sasl_server.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.sasl.SaslMessageType}
 */
public enum SaslMessageType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CHALLENGE = 0;</code>
   */
  CHALLENGE(0),
  /**
   * <code>SUCCESS = 1;</code>
   */
  SUCCESS(1),
  ;

  /**
   * <code>CHALLENGE = 0;</code>
   */
  public static final int CHALLENGE_VALUE = 0;
  /**
   * <code>SUCCESS = 1;</code>
   */
  public static final int SUCCESS_VALUE = 1;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static SaslMessageType valueOf(int value) {
    return forNumber(value);
  }

  public static SaslMessageType forNumber(int value) {
    switch (value) {
      case 0: return CHALLENGE;
      case 1: return SUCCESS;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<SaslMessageType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      SaslMessageType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<SaslMessageType>() {
          public SaslMessageType findValueByNumber(int number) {
            return SaslMessageType.forNumber(number);
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
    return alluxio.grpc.AuthenticationServerProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final SaslMessageType[] VALUES = values();

  public static SaslMessageType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private SaslMessageType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.sasl.SaslMessageType)
}

