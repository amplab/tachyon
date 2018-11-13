// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: file_system_master_options.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.CreateDirectoryPOptions}
 */
public  final class CreateDirectoryPOptions extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.CreateDirectoryPOptions)
    CreateDirectoryPOptionsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CreateDirectoryPOptions.newBuilder() to construct.
  private CreateDirectoryPOptions(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateDirectoryPOptions() {
    persisted_ = false;
    recursive_ = false;
    allowExist_ = false;
    mode_ = 0;
    ttlNotUsed_ = 0L;
    ttlActionNotUsed_ = 0;
    writeType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CreateDirectoryPOptions(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            persisted_ = input.readBool();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            recursive_ = input.readBool();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            allowExist_ = input.readBool();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            mode_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            ttlNotUsed_ = input.readInt64();
            break;
          }
          case 48: {
            int rawValue = input.readEnum();
            alluxio.grpc.TtlAction value = alluxio.grpc.TtlAction.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(6, rawValue);
            } else {
              bitField0_ |= 0x00000020;
              ttlActionNotUsed_ = rawValue;
            }
            break;
          }
          case 56: {
            int rawValue = input.readEnum();
            alluxio.grpc.WritePType value = alluxio.grpc.WritePType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(7, rawValue);
            } else {
              bitField0_ |= 0x00000040;
              writeType_ = rawValue;
            }
            break;
          }
          case 66: {
            alluxio.grpc.FileSystemMasterCommonPOptions.Builder subBuilder = null;
            if (((bitField0_ & 0x00000080) == 0x00000080)) {
              subBuilder = commonOptions_.toBuilder();
            }
            commonOptions_ = input.readMessage(alluxio.grpc.FileSystemMasterCommonPOptions.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(commonOptions_);
              commonOptions_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000080;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return alluxio.grpc.FileSystemMasterOptionsProto.internal_static_alluxio_grpc_CreateDirectoryPOptions_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemMasterOptionsProto.internal_static_alluxio_grpc_CreateDirectoryPOptions_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.CreateDirectoryPOptions.class, alluxio.grpc.CreateDirectoryPOptions.Builder.class);
  }

  private int bitField0_;
  public static final int PERSISTED_FIELD_NUMBER = 1;
  private boolean persisted_;
  /**
   * <code>optional bool persisted = 1;</code>
   */
  public boolean hasPersisted() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional bool persisted = 1;</code>
   */
  public boolean getPersisted() {
    return persisted_;
  }

  public static final int RECURSIVE_FIELD_NUMBER = 2;
  private boolean recursive_;
  /**
   * <code>optional bool recursive = 2;</code>
   */
  public boolean hasRecursive() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional bool recursive = 2;</code>
   */
  public boolean getRecursive() {
    return recursive_;
  }

  public static final int ALLOWEXIST_FIELD_NUMBER = 3;
  private boolean allowExist_;
  /**
   * <code>optional bool allowExist = 3;</code>
   */
  public boolean hasAllowExist() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional bool allowExist = 3;</code>
   */
  public boolean getAllowExist() {
    return allowExist_;
  }

  public static final int MODE_FIELD_NUMBER = 4;
  private int mode_;
  /**
   * <code>optional int32 mode = 4;</code>
   */
  public boolean hasMode() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional int32 mode = 4;</code>
   */
  public int getMode() {
    return mode_;
  }

  public static final int TTLNOTUSED_FIELD_NUMBER = 5;
  private long ttlNotUsed_;
  /**
   * <pre>
   * deprecated from 1.8
   * </pre>
   *
   * <code>optional int64 ttlNotUsed = 5;</code>
   */
  public boolean hasTtlNotUsed() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * deprecated from 1.8
   * </pre>
   *
   * <code>optional int64 ttlNotUsed = 5;</code>
   */
  public long getTtlNotUsed() {
    return ttlNotUsed_;
  }

  public static final int TTLACTIONNOTUSED_FIELD_NUMBER = 6;
  private int ttlActionNotUsed_;
  /**
   * <pre>
   * deprecated from 1.8
   * </pre>
   *
   * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
   */
  public boolean hasTtlActionNotUsed() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * deprecated from 1.8
   * </pre>
   *
   * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
   */
  public alluxio.grpc.TtlAction getTtlActionNotUsed() {
    alluxio.grpc.TtlAction result = alluxio.grpc.TtlAction.valueOf(ttlActionNotUsed_);
    return result == null ? alluxio.grpc.TtlAction.DELETE : result;
  }

  public static final int WRITETYPE_FIELD_NUMBER = 7;
  private int writeType_;
  /**
   * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
   */
  public boolean hasWriteType() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
   */
  public alluxio.grpc.WritePType getWriteType() {
    alluxio.grpc.WritePType result = alluxio.grpc.WritePType.valueOf(writeType_);
    return result == null ? alluxio.grpc.WritePType.WRITE_MUST_CACHE : result;
  }

  public static final int COMMONOPTIONS_FIELD_NUMBER = 8;
  private alluxio.grpc.FileSystemMasterCommonPOptions commonOptions_;
  /**
   * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
   */
  public boolean hasCommonOptions() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
   */
  public alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions() {
    return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
  }
  /**
   * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
   */
  public alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder() {
    return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeBool(1, persisted_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBool(2, recursive_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeBool(3, allowExist_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, mode_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, ttlNotUsed_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeEnum(6, ttlActionNotUsed_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeEnum(7, writeType_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeMessage(8, getCommonOptions());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, persisted_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, recursive_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, allowExist_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, mode_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, ttlNotUsed_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(6, ttlActionNotUsed_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(7, writeType_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(8, getCommonOptions());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof alluxio.grpc.CreateDirectoryPOptions)) {
      return super.equals(obj);
    }
    alluxio.grpc.CreateDirectoryPOptions other = (alluxio.grpc.CreateDirectoryPOptions) obj;

    boolean result = true;
    result = result && (hasPersisted() == other.hasPersisted());
    if (hasPersisted()) {
      result = result && (getPersisted()
          == other.getPersisted());
    }
    result = result && (hasRecursive() == other.hasRecursive());
    if (hasRecursive()) {
      result = result && (getRecursive()
          == other.getRecursive());
    }
    result = result && (hasAllowExist() == other.hasAllowExist());
    if (hasAllowExist()) {
      result = result && (getAllowExist()
          == other.getAllowExist());
    }
    result = result && (hasMode() == other.hasMode());
    if (hasMode()) {
      result = result && (getMode()
          == other.getMode());
    }
    result = result && (hasTtlNotUsed() == other.hasTtlNotUsed());
    if (hasTtlNotUsed()) {
      result = result && (getTtlNotUsed()
          == other.getTtlNotUsed());
    }
    result = result && (hasTtlActionNotUsed() == other.hasTtlActionNotUsed());
    if (hasTtlActionNotUsed()) {
      result = result && ttlActionNotUsed_ == other.ttlActionNotUsed_;
    }
    result = result && (hasWriteType() == other.hasWriteType());
    if (hasWriteType()) {
      result = result && writeType_ == other.writeType_;
    }
    result = result && (hasCommonOptions() == other.hasCommonOptions());
    if (hasCommonOptions()) {
      result = result && getCommonOptions()
          .equals(other.getCommonOptions());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasPersisted()) {
      hash = (37 * hash) + PERSISTED_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getPersisted());
    }
    if (hasRecursive()) {
      hash = (37 * hash) + RECURSIVE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getRecursive());
    }
    if (hasAllowExist()) {
      hash = (37 * hash) + ALLOWEXIST_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getAllowExist());
    }
    if (hasMode()) {
      hash = (37 * hash) + MODE_FIELD_NUMBER;
      hash = (53 * hash) + getMode();
    }
    if (hasTtlNotUsed()) {
      hash = (37 * hash) + TTLNOTUSED_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTtlNotUsed());
    }
    if (hasTtlActionNotUsed()) {
      hash = (37 * hash) + TTLACTIONNOTUSED_FIELD_NUMBER;
      hash = (53 * hash) + ttlActionNotUsed_;
    }
    if (hasWriteType()) {
      hash = (37 * hash) + WRITETYPE_FIELD_NUMBER;
      hash = (53 * hash) + writeType_;
    }
    if (hasCommonOptions()) {
      hash = (37 * hash) + COMMONOPTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getCommonOptions().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CreateDirectoryPOptions parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(alluxio.grpc.CreateDirectoryPOptions prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code alluxio.grpc.CreateDirectoryPOptions}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.CreateDirectoryPOptions)
      alluxio.grpc.CreateDirectoryPOptionsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemMasterOptionsProto.internal_static_alluxio_grpc_CreateDirectoryPOptions_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemMasterOptionsProto.internal_static_alluxio_grpc_CreateDirectoryPOptions_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.CreateDirectoryPOptions.class, alluxio.grpc.CreateDirectoryPOptions.Builder.class);
    }

    // Construct using alluxio.grpc.CreateDirectoryPOptions.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCommonOptionsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      persisted_ = false;
      bitField0_ = (bitField0_ & ~0x00000001);
      recursive_ = false;
      bitField0_ = (bitField0_ & ~0x00000002);
      allowExist_ = false;
      bitField0_ = (bitField0_ & ~0x00000004);
      mode_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      ttlNotUsed_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      ttlActionNotUsed_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      writeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = null;
      } else {
        commonOptionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemMasterOptionsProto.internal_static_alluxio_grpc_CreateDirectoryPOptions_descriptor;
    }

    public alluxio.grpc.CreateDirectoryPOptions getDefaultInstanceForType() {
      return alluxio.grpc.CreateDirectoryPOptions.getDefaultInstance();
    }

    public alluxio.grpc.CreateDirectoryPOptions build() {
      alluxio.grpc.CreateDirectoryPOptions result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.CreateDirectoryPOptions buildPartial() {
      alluxio.grpc.CreateDirectoryPOptions result = new alluxio.grpc.CreateDirectoryPOptions(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.persisted_ = persisted_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.recursive_ = recursive_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.allowExist_ = allowExist_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.mode_ = mode_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.ttlNotUsed_ = ttlNotUsed_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.ttlActionNotUsed_ = ttlActionNotUsed_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.writeType_ = writeType_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      if (commonOptionsBuilder_ == null) {
        result.commonOptions_ = commonOptions_;
      } else {
        result.commonOptions_ = commonOptionsBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof alluxio.grpc.CreateDirectoryPOptions) {
        return mergeFrom((alluxio.grpc.CreateDirectoryPOptions)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.CreateDirectoryPOptions other) {
      if (other == alluxio.grpc.CreateDirectoryPOptions.getDefaultInstance()) return this;
      if (other.hasPersisted()) {
        setPersisted(other.getPersisted());
      }
      if (other.hasRecursive()) {
        setRecursive(other.getRecursive());
      }
      if (other.hasAllowExist()) {
        setAllowExist(other.getAllowExist());
      }
      if (other.hasMode()) {
        setMode(other.getMode());
      }
      if (other.hasTtlNotUsed()) {
        setTtlNotUsed(other.getTtlNotUsed());
      }
      if (other.hasTtlActionNotUsed()) {
        setTtlActionNotUsed(other.getTtlActionNotUsed());
      }
      if (other.hasWriteType()) {
        setWriteType(other.getWriteType());
      }
      if (other.hasCommonOptions()) {
        mergeCommonOptions(other.getCommonOptions());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      alluxio.grpc.CreateDirectoryPOptions parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.CreateDirectoryPOptions) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private boolean persisted_ ;
    /**
     * <code>optional bool persisted = 1;</code>
     */
    public boolean hasPersisted() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional bool persisted = 1;</code>
     */
    public boolean getPersisted() {
      return persisted_;
    }
    /**
     * <code>optional bool persisted = 1;</code>
     */
    public Builder setPersisted(boolean value) {
      bitField0_ |= 0x00000001;
      persisted_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool persisted = 1;</code>
     */
    public Builder clearPersisted() {
      bitField0_ = (bitField0_ & ~0x00000001);
      persisted_ = false;
      onChanged();
      return this;
    }

    private boolean recursive_ ;
    /**
     * <code>optional bool recursive = 2;</code>
     */
    public boolean hasRecursive() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bool recursive = 2;</code>
     */
    public boolean getRecursive() {
      return recursive_;
    }
    /**
     * <code>optional bool recursive = 2;</code>
     */
    public Builder setRecursive(boolean value) {
      bitField0_ |= 0x00000002;
      recursive_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool recursive = 2;</code>
     */
    public Builder clearRecursive() {
      bitField0_ = (bitField0_ & ~0x00000002);
      recursive_ = false;
      onChanged();
      return this;
    }

    private boolean allowExist_ ;
    /**
     * <code>optional bool allowExist = 3;</code>
     */
    public boolean hasAllowExist() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional bool allowExist = 3;</code>
     */
    public boolean getAllowExist() {
      return allowExist_;
    }
    /**
     * <code>optional bool allowExist = 3;</code>
     */
    public Builder setAllowExist(boolean value) {
      bitField0_ |= 0x00000004;
      allowExist_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool allowExist = 3;</code>
     */
    public Builder clearAllowExist() {
      bitField0_ = (bitField0_ & ~0x00000004);
      allowExist_ = false;
      onChanged();
      return this;
    }

    private int mode_ ;
    /**
     * <code>optional int32 mode = 4;</code>
     */
    public boolean hasMode() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 mode = 4;</code>
     */
    public int getMode() {
      return mode_;
    }
    /**
     * <code>optional int32 mode = 4;</code>
     */
    public Builder setMode(int value) {
      bitField0_ |= 0x00000008;
      mode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 mode = 4;</code>
     */
    public Builder clearMode() {
      bitField0_ = (bitField0_ & ~0x00000008);
      mode_ = 0;
      onChanged();
      return this;
    }

    private long ttlNotUsed_ ;
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional int64 ttlNotUsed = 5;</code>
     */
    public boolean hasTtlNotUsed() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional int64 ttlNotUsed = 5;</code>
     */
    public long getTtlNotUsed() {
      return ttlNotUsed_;
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional int64 ttlNotUsed = 5;</code>
     */
    public Builder setTtlNotUsed(long value) {
      bitField0_ |= 0x00000010;
      ttlNotUsed_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional int64 ttlNotUsed = 5;</code>
     */
    public Builder clearTtlNotUsed() {
      bitField0_ = (bitField0_ & ~0x00000010);
      ttlNotUsed_ = 0L;
      onChanged();
      return this;
    }

    private int ttlActionNotUsed_ = 0;
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
     */
    public boolean hasTtlActionNotUsed() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
     */
    public alluxio.grpc.TtlAction getTtlActionNotUsed() {
      alluxio.grpc.TtlAction result = alluxio.grpc.TtlAction.valueOf(ttlActionNotUsed_);
      return result == null ? alluxio.grpc.TtlAction.DELETE : result;
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
     */
    public Builder setTtlActionNotUsed(alluxio.grpc.TtlAction value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000020;
      ttlActionNotUsed_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * deprecated from 1.8
     * </pre>
     *
     * <code>optional .alluxio.grpc.TtlAction ttlActionNotUsed = 6;</code>
     */
    public Builder clearTtlActionNotUsed() {
      bitField0_ = (bitField0_ & ~0x00000020);
      ttlActionNotUsed_ = 0;
      onChanged();
      return this;
    }

    private int writeType_ = 0;
    /**
     * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
     */
    public boolean hasWriteType() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
     */
    public alluxio.grpc.WritePType getWriteType() {
      alluxio.grpc.WritePType result = alluxio.grpc.WritePType.valueOf(writeType_);
      return result == null ? alluxio.grpc.WritePType.WRITE_MUST_CACHE : result;
    }
    /**
     * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
     */
    public Builder setWriteType(alluxio.grpc.WritePType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000040;
      writeType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.WritePType writeType = 7;</code>
     */
    public Builder clearWriteType() {
      bitField0_ = (bitField0_ & ~0x00000040);
      writeType_ = 0;
      onChanged();
      return this;
    }

    private alluxio.grpc.FileSystemMasterCommonPOptions commonOptions_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder> commonOptionsBuilder_;
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public boolean hasCommonOptions() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions() {
      if (commonOptionsBuilder_ == null) {
        return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
      } else {
        return commonOptionsBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public Builder setCommonOptions(alluxio.grpc.FileSystemMasterCommonPOptions value) {
      if (commonOptionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        commonOptions_ = value;
        onChanged();
      } else {
        commonOptionsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000080;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public Builder setCommonOptions(
        alluxio.grpc.FileSystemMasterCommonPOptions.Builder builderForValue) {
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = builderForValue.build();
        onChanged();
      } else {
        commonOptionsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000080;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public Builder mergeCommonOptions(alluxio.grpc.FileSystemMasterCommonPOptions value) {
      if (commonOptionsBuilder_ == null) {
        if (((bitField0_ & 0x00000080) == 0x00000080) &&
            commonOptions_ != null &&
            commonOptions_ != alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance()) {
          commonOptions_ =
            alluxio.grpc.FileSystemMasterCommonPOptions.newBuilder(commonOptions_).mergeFrom(value).buildPartial();
        } else {
          commonOptions_ = value;
        }
        onChanged();
      } else {
        commonOptionsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000080;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public Builder clearCommonOptions() {
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = null;
        onChanged();
      } else {
        commonOptionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptions.Builder getCommonOptionsBuilder() {
      bitField0_ |= 0x00000080;
      onChanged();
      return getCommonOptionsFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder() {
      if (commonOptionsBuilder_ != null) {
        return commonOptionsBuilder_.getMessageOrBuilder();
      } else {
        return commonOptions_ == null ?
            alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
      }
    }
    /**
     * <code>optional .alluxio.grpc.FileSystemMasterCommonPOptions commonOptions = 8;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder> 
        getCommonOptionsFieldBuilder() {
      if (commonOptionsBuilder_ == null) {
        commonOptionsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder>(
                getCommonOptions(),
                getParentForChildren(),
                isClean());
        commonOptions_ = null;
      }
      return commonOptionsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.CreateDirectoryPOptions)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.CreateDirectoryPOptions)
  private static final alluxio.grpc.CreateDirectoryPOptions DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.CreateDirectoryPOptions();
  }

  public static alluxio.grpc.CreateDirectoryPOptions getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CreateDirectoryPOptions>
      PARSER = new com.google.protobuf.AbstractParser<CreateDirectoryPOptions>() {
    public CreateDirectoryPOptions parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CreateDirectoryPOptions(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CreateDirectoryPOptions> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateDirectoryPOptions> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.CreateDirectoryPOptions getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

