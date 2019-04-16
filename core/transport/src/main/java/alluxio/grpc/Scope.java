// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.meta.Scope}
 */
public enum Scope
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>MASTER = 1;</code>
   */
  MASTER(1),
  /**
   * <code>WORKER = 2;</code>
   */
  WORKER(2),
  /**
   * <code>CLIENT = 4;</code>
   */
  CLIENT(4),
  /**
   * <code>SERVER = 3;</code>
   */
  SERVER(3),
  /**
   * <code>ALL = 7;</code>
   */
  ALL(7),
  /**
   * <code>NONE = 0;</code>
   */
  NONE(0),
  ;

  /**
   * <code>MASTER = 1;</code>
   */
  public static final int MASTER_VALUE = 1;
  /**
   * <code>WORKER = 2;</code>
   */
  public static final int WORKER_VALUE = 2;
  /**
   * <code>CLIENT = 4;</code>
   */
  public static final int CLIENT_VALUE = 4;
  /**
   * <code>SERVER = 3;</code>
   */
  public static final int SERVER_VALUE = 3;
  /**
   * <code>ALL = 7;</code>
   */
  public static final int ALL_VALUE = 7;
  /**
   * <code>NONE = 0;</code>
   */
  public static final int NONE_VALUE = 0;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Scope valueOf(int value) {
    return forNumber(value);
  }

  public static Scope forNumber(int value) {
    switch (value) {
      case 1: return MASTER;
      case 2: return WORKER;
      case 4: return CLIENT;
      case 3: return SERVER;
      case 7: return ALL;
      case 0: return NONE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Scope>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Scope> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Scope>() {
          public Scope findValueByNumber(int number) {
            return Scope.forNumber(number);
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
    return alluxio.grpc.MetaMasterProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final Scope[] VALUES = values();

  public static Scope valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Scope(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.meta.Scope)
}

