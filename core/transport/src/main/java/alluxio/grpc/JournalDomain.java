// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/journal_master.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.journal.JournalDomain}
 */
public enum JournalDomain
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>MASTER = 1;</code>
   */
  MASTER(1),
  /**
   * <code>JOB_MASTER = 2;</code>
   */
  JOB_MASTER(2),
  ;

  /**
   * <code>MASTER = 1;</code>
   */
  public static final int MASTER_VALUE = 1;
  /**
   * <code>JOB_MASTER = 2;</code>
   */
  public static final int JOB_MASTER_VALUE = 2;


  public final int getNumber() {
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static JournalDomain valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static JournalDomain forNumber(int value) {
    switch (value) {
      case 1: return MASTER;
      case 2: return JOB_MASTER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<JournalDomain>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      JournalDomain> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<JournalDomain>() {
          public JournalDomain findValueByNumber(int number) {
            return JournalDomain.forNumber(number);
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
    return alluxio.grpc.JournalMasterProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final JournalDomain[] VALUES = values();

  public static JournalDomain valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private JournalDomain(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.journal.JournalDomain)
}

