// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/meta_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.meta.BackupPStatus}
 */
public  final class BackupPStatus extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.meta.BackupPStatus)
    BackupPStatusOrBuilder {
private static final long serialVersionUID = 0L;
  // Use BackupPStatus.newBuilder() to construct.
  private BackupPStatus(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BackupPStatus() {
    backupId_ = "";
    backupState_ = 1;
    backupHost_ = "";
    backupUri_ = "";
    entryCount_ = 0L;
    backupError_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BackupPStatus(
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
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            backupId_ = bs;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();
            alluxio.grpc.BackupState value = alluxio.grpc.BackupState.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(2, rawValue);
            } else {
              bitField0_ |= 0x00000002;
              backupState_ = rawValue;
            }
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            backupHost_ = bs;
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            backupUri_ = bs;
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            entryCount_ = input.readInt64();
            break;
          }
          case 50: {
            bitField0_ |= 0x00000020;
            backupError_ = input.readBytes();
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
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_meta_BackupPStatus_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_meta_BackupPStatus_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.BackupPStatus.class, alluxio.grpc.BackupPStatus.Builder.class);
  }

  private int bitField0_;
  public static final int BACKUPID_FIELD_NUMBER = 1;
  private volatile java.lang.Object backupId_;
  /**
   * <code>optional string backupId = 1;</code>
   */
  public boolean hasBackupId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional string backupId = 1;</code>
   */
  public java.lang.String getBackupId() {
    java.lang.Object ref = backupId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        backupId_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string backupId = 1;</code>
   */
  public com.google.protobuf.ByteString
      getBackupIdBytes() {
    java.lang.Object ref = backupId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      backupId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BACKUPSTATE_FIELD_NUMBER = 2;
  private int backupState_;
  /**
   * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
   */
  public boolean hasBackupState() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
   */
  public alluxio.grpc.BackupState getBackupState() {
    alluxio.grpc.BackupState result = alluxio.grpc.BackupState.valueOf(backupState_);
    return result == null ? alluxio.grpc.BackupState.None : result;
  }

  public static final int BACKUPHOST_FIELD_NUMBER = 3;
  private volatile java.lang.Object backupHost_;
  /**
   * <code>optional string backupHost = 3;</code>
   */
  public boolean hasBackupHost() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string backupHost = 3;</code>
   */
  public java.lang.String getBackupHost() {
    java.lang.Object ref = backupHost_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        backupHost_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string backupHost = 3;</code>
   */
  public com.google.protobuf.ByteString
      getBackupHostBytes() {
    java.lang.Object ref = backupHost_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      backupHost_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BACKUPURI_FIELD_NUMBER = 4;
  private volatile java.lang.Object backupUri_;
  /**
   * <code>optional string backupUri = 4;</code>
   */
  public boolean hasBackupUri() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional string backupUri = 4;</code>
   */
  public java.lang.String getBackupUri() {
    java.lang.Object ref = backupUri_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        backupUri_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string backupUri = 4;</code>
   */
  public com.google.protobuf.ByteString
      getBackupUriBytes() {
    java.lang.Object ref = backupUri_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      backupUri_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENTRYCOUNT_FIELD_NUMBER = 5;
  private long entryCount_;
  /**
   * <code>optional int64 entryCount = 5;</code>
   */
  public boolean hasEntryCount() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional int64 entryCount = 5;</code>
   */
  public long getEntryCount() {
    return entryCount_;
  }

  public static final int BACKUPERROR_FIELD_NUMBER = 6;
  private com.google.protobuf.ByteString backupError_;
  /**
   * <code>optional bytes backupError = 6;</code>
   */
  public boolean hasBackupError() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>optional bytes backupError = 6;</code>
   */
  public com.google.protobuf.ByteString getBackupError() {
    return backupError_;
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, backupId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeEnum(2, backupState_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, backupHost_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, backupUri_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, entryCount_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeBytes(6, backupError_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, backupId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, backupState_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, backupHost_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, backupUri_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, entryCount_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(6, backupError_);
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
    if (!(obj instanceof alluxio.grpc.BackupPStatus)) {
      return super.equals(obj);
    }
    alluxio.grpc.BackupPStatus other = (alluxio.grpc.BackupPStatus) obj;

    boolean result = true;
    result = result && (hasBackupId() == other.hasBackupId());
    if (hasBackupId()) {
      result = result && getBackupId()
          .equals(other.getBackupId());
    }
    result = result && (hasBackupState() == other.hasBackupState());
    if (hasBackupState()) {
      result = result && backupState_ == other.backupState_;
    }
    result = result && (hasBackupHost() == other.hasBackupHost());
    if (hasBackupHost()) {
      result = result && getBackupHost()
          .equals(other.getBackupHost());
    }
    result = result && (hasBackupUri() == other.hasBackupUri());
    if (hasBackupUri()) {
      result = result && getBackupUri()
          .equals(other.getBackupUri());
    }
    result = result && (hasEntryCount() == other.hasEntryCount());
    if (hasEntryCount()) {
      result = result && (getEntryCount()
          == other.getEntryCount());
    }
    result = result && (hasBackupError() == other.hasBackupError());
    if (hasBackupError()) {
      result = result && getBackupError()
          .equals(other.getBackupError());
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
    if (hasBackupId()) {
      hash = (37 * hash) + BACKUPID_FIELD_NUMBER;
      hash = (53 * hash) + getBackupId().hashCode();
    }
    if (hasBackupState()) {
      hash = (37 * hash) + BACKUPSTATE_FIELD_NUMBER;
      hash = (53 * hash) + backupState_;
    }
    if (hasBackupHost()) {
      hash = (37 * hash) + BACKUPHOST_FIELD_NUMBER;
      hash = (53 * hash) + getBackupHost().hashCode();
    }
    if (hasBackupUri()) {
      hash = (37 * hash) + BACKUPURI_FIELD_NUMBER;
      hash = (53 * hash) + getBackupUri().hashCode();
    }
    if (hasEntryCount()) {
      hash = (37 * hash) + ENTRYCOUNT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getEntryCount());
    }
    if (hasBackupError()) {
      hash = (37 * hash) + BACKUPERROR_FIELD_NUMBER;
      hash = (53 * hash) + getBackupError().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.BackupPStatus parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.BackupPStatus parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.BackupPStatus parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.BackupPStatus parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.BackupPStatus prototype) {
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
   * Protobuf type {@code alluxio.grpc.meta.BackupPStatus}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.meta.BackupPStatus)
      alluxio.grpc.BackupPStatusOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_meta_BackupPStatus_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_meta_BackupPStatus_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.BackupPStatus.class, alluxio.grpc.BackupPStatus.Builder.class);
    }

    // Construct using alluxio.grpc.BackupPStatus.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      backupId_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      backupState_ = 1;
      bitField0_ = (bitField0_ & ~0x00000002);
      backupHost_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      backupUri_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      entryCount_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      backupError_ = com.google.protobuf.ByteString.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_meta_BackupPStatus_descriptor;
    }

    public alluxio.grpc.BackupPStatus getDefaultInstanceForType() {
      return alluxio.grpc.BackupPStatus.getDefaultInstance();
    }

    public alluxio.grpc.BackupPStatus build() {
      alluxio.grpc.BackupPStatus result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.BackupPStatus buildPartial() {
      alluxio.grpc.BackupPStatus result = new alluxio.grpc.BackupPStatus(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.backupId_ = backupId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.backupState_ = backupState_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.backupHost_ = backupHost_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.backupUri_ = backupUri_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.entryCount_ = entryCount_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.backupError_ = backupError_;
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
      if (other instanceof alluxio.grpc.BackupPStatus) {
        return mergeFrom((alluxio.grpc.BackupPStatus)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.BackupPStatus other) {
      if (other == alluxio.grpc.BackupPStatus.getDefaultInstance()) return this;
      if (other.hasBackupId()) {
        bitField0_ |= 0x00000001;
        backupId_ = other.backupId_;
        onChanged();
      }
      if (other.hasBackupState()) {
        setBackupState(other.getBackupState());
      }
      if (other.hasBackupHost()) {
        bitField0_ |= 0x00000004;
        backupHost_ = other.backupHost_;
        onChanged();
      }
      if (other.hasBackupUri()) {
        bitField0_ |= 0x00000008;
        backupUri_ = other.backupUri_;
        onChanged();
      }
      if (other.hasEntryCount()) {
        setEntryCount(other.getEntryCount());
      }
      if (other.hasBackupError()) {
        setBackupError(other.getBackupError());
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
      alluxio.grpc.BackupPStatus parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.BackupPStatus) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object backupId_ = "";
    /**
     * <code>optional string backupId = 1;</code>
     */
    public boolean hasBackupId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string backupId = 1;</code>
     */
    public java.lang.String getBackupId() {
      java.lang.Object ref = backupId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          backupId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string backupId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getBackupIdBytes() {
      java.lang.Object ref = backupId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        backupId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string backupId = 1;</code>
     */
    public Builder setBackupId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      backupId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupId = 1;</code>
     */
    public Builder clearBackupId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      backupId_ = getDefaultInstance().getBackupId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupId = 1;</code>
     */
    public Builder setBackupIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      backupId_ = value;
      onChanged();
      return this;
    }

    private int backupState_ = 1;
    /**
     * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
     */
    public boolean hasBackupState() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
     */
    public alluxio.grpc.BackupState getBackupState() {
      alluxio.grpc.BackupState result = alluxio.grpc.BackupState.valueOf(backupState_);
      return result == null ? alluxio.grpc.BackupState.None : result;
    }
    /**
     * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
     */
    public Builder setBackupState(alluxio.grpc.BackupState value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
      backupState_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.meta.BackupState backupState = 2;</code>
     */
    public Builder clearBackupState() {
      bitField0_ = (bitField0_ & ~0x00000002);
      backupState_ = 1;
      onChanged();
      return this;
    }

    private java.lang.Object backupHost_ = "";
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public boolean hasBackupHost() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public java.lang.String getBackupHost() {
      java.lang.Object ref = backupHost_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          backupHost_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public com.google.protobuf.ByteString
        getBackupHostBytes() {
      java.lang.Object ref = backupHost_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        backupHost_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public Builder setBackupHost(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      backupHost_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public Builder clearBackupHost() {
      bitField0_ = (bitField0_ & ~0x00000004);
      backupHost_ = getDefaultInstance().getBackupHost();
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupHost = 3;</code>
     */
    public Builder setBackupHostBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      backupHost_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object backupUri_ = "";
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public boolean hasBackupUri() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public java.lang.String getBackupUri() {
      java.lang.Object ref = backupUri_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          backupUri_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public com.google.protobuf.ByteString
        getBackupUriBytes() {
      java.lang.Object ref = backupUri_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        backupUri_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public Builder setBackupUri(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      backupUri_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public Builder clearBackupUri() {
      bitField0_ = (bitField0_ & ~0x00000008);
      backupUri_ = getDefaultInstance().getBackupUri();
      onChanged();
      return this;
    }
    /**
     * <code>optional string backupUri = 4;</code>
     */
    public Builder setBackupUriBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      backupUri_ = value;
      onChanged();
      return this;
    }

    private long entryCount_ ;
    /**
     * <code>optional int64 entryCount = 5;</code>
     */
    public boolean hasEntryCount() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int64 entryCount = 5;</code>
     */
    public long getEntryCount() {
      return entryCount_;
    }
    /**
     * <code>optional int64 entryCount = 5;</code>
     */
    public Builder setEntryCount(long value) {
      bitField0_ |= 0x00000010;
      entryCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 entryCount = 5;</code>
     */
    public Builder clearEntryCount() {
      bitField0_ = (bitField0_ & ~0x00000010);
      entryCount_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString backupError_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes backupError = 6;</code>
     */
    public boolean hasBackupError() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional bytes backupError = 6;</code>
     */
    public com.google.protobuf.ByteString getBackupError() {
      return backupError_;
    }
    /**
     * <code>optional bytes backupError = 6;</code>
     */
    public Builder setBackupError(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      backupError_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bytes backupError = 6;</code>
     */
    public Builder clearBackupError() {
      bitField0_ = (bitField0_ & ~0x00000020);
      backupError_ = getDefaultInstance().getBackupError();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.meta.BackupPStatus)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.meta.BackupPStatus)
  private static final alluxio.grpc.BackupPStatus DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.BackupPStatus();
  }

  public static alluxio.grpc.BackupPStatus getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BackupPStatus>
      PARSER = new com.google.protobuf.AbstractParser<BackupPStatus>() {
    public BackupPStatus parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new BackupPStatus(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BackupPStatus> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BackupPStatus> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.BackupPStatus getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

