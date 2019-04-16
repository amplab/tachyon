// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/common.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.CommandType}
 */
public enum CommandType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>Unknown = 0;</code>
   */
  Unknown(0),
  /**
   * <code>Nothing = 1;</code>
   */
  Nothing(1),
  /**
   * <pre>
   * Ask the worker to re-register.
   * </pre>
   *
   * <code>Register = 2;</code>
   */
  Register(2),
  /**
   * <pre>
   * Ask the worker to free files.
   * </pre>
   *
   * <code>Free = 3;</code>
   */
  Free(3),
  /**
   * <pre>
   * Ask the worker to delete files.
   * </pre>
   *
   * <code>Delete = 4;</code>
   */
  Delete(4),
  /**
   * <pre>
   * Ask the worker to persist a file for lineage
   * </pre>
   *
   * <code>Persist = 5;</code>
   */
  Persist(5),
  ;

  /**
   * <code>Unknown = 0;</code>
   */
  public static final int Unknown_VALUE = 0;
  /**
   * <code>Nothing = 1;</code>
   */
  public static final int Nothing_VALUE = 1;
  /**
   * <pre>
   * Ask the worker to re-register.
   * </pre>
   *
   * <code>Register = 2;</code>
   */
  public static final int Register_VALUE = 2;
  /**
   * <pre>
   * Ask the worker to free files.
   * </pre>
   *
   * <code>Free = 3;</code>
   */
  public static final int Free_VALUE = 3;
  /**
   * <pre>
   * Ask the worker to delete files.
   * </pre>
   *
   * <code>Delete = 4;</code>
   */
  public static final int Delete_VALUE = 4;
  /**
   * <pre>
   * Ask the worker to persist a file for lineage
   * </pre>
   *
   * <code>Persist = 5;</code>
   */
  public static final int Persist_VALUE = 5;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static CommandType valueOf(int value) {
    return forNumber(value);
  }

  public static CommandType forNumber(int value) {
    switch (value) {
      case 0: return Unknown;
      case 1: return Nothing;
      case 2: return Register;
      case 3: return Free;
      case 4: return Delete;
      case 5: return Persist;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<CommandType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      CommandType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<CommandType>() {
          public CommandType findValueByNumber(int number) {
            return CommandType.forNumber(number);
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
    return alluxio.grpc.CommonProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final CommandType[] VALUES = values();

  public static CommandType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private CommandType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.CommandType)
}

