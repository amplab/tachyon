// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/job_master.proto

package alluxio.grpc;

/**
 * Protobuf enum {@code alluxio.grpc.job.JobType}
 */
public enum JobType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>PLAN = 1;</code>
   */
  PLAN(1),
  /**
   * <code>TASK = 2;</code>
   */
  TASK(2),
  ;

  /**
   * <code>PLAN = 1;</code>
   */
  public static final int PLAN_VALUE = 1;
  /**
   * <code>TASK = 2;</code>
   */
  public static final int TASK_VALUE = 2;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static JobType valueOf(int value) {
    return forNumber(value);
  }

  public static JobType forNumber(int value) {
    switch (value) {
      case 1: return PLAN;
      case 2: return TASK;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<JobType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      JobType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<JobType>() {
          public JobType findValueByNumber(int number) {
            return JobType.forNumber(number);
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
    return alluxio.grpc.JobMasterProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final JobType[] VALUES = values();

  public static JobType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private JobType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alluxio.grpc.job.JobType)
}

