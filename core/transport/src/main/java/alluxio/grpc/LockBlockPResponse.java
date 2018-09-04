// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: block_worker.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.LockBlockPResponse}
 */
public  final class LockBlockPResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.LockBlockPResponse)
    LockBlockPResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LockBlockPResponse.newBuilder() to construct.
  private LockBlockPResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LockBlockPResponse() {
    lockId_ = 0L;
    blockPath_ = "";
    lockBlockStatus_ = 1;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LockBlockPResponse(
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
            lockId_ = input.readInt64();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            blockPath_ = bs;
            break;
          }
          case 24: {
            int rawValue = input.readEnum();
            alluxio.grpc.LockBlockStatus value = alluxio.grpc.LockBlockStatus.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(3, rawValue);
            } else {
              bitField0_ |= 0x00000004;
              lockBlockStatus_ = rawValue;
            }
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
    return alluxio.grpc.BlockWorkerProto.internal_static_alluxio_grpc_LockBlockPResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.BlockWorkerProto.internal_static_alluxio_grpc_LockBlockPResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.LockBlockPResponse.class, alluxio.grpc.LockBlockPResponse.Builder.class);
  }

  private int bitField0_;
  public static final int LOCKID_FIELD_NUMBER = 1;
  private long lockId_;
  /**
   * <code>optional int64 lockId = 1;</code>
   */
  public boolean hasLockId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional int64 lockId = 1;</code>
   */
  public long getLockId() {
    return lockId_;
  }

  public static final int BLOCKPATH_FIELD_NUMBER = 2;
  private volatile java.lang.Object blockPath_;
  /**
   * <code>optional string blockPath = 2;</code>
   */
  public boolean hasBlockPath() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional string blockPath = 2;</code>
   */
  public java.lang.String getBlockPath() {
    java.lang.Object ref = blockPath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        blockPath_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string blockPath = 2;</code>
   */
  public com.google.protobuf.ByteString
      getBlockPathBytes() {
    java.lang.Object ref = blockPath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      blockPath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LOCKBLOCKSTATUS_FIELD_NUMBER = 3;
  private int lockBlockStatus_;
  /**
   * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
   */
  public boolean hasLockBlockStatus() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
   */
  public alluxio.grpc.LockBlockStatus getLockBlockStatus() {
    alluxio.grpc.LockBlockStatus result = alluxio.grpc.LockBlockStatus.valueOf(lockBlockStatus_);
    return result == null ? alluxio.grpc.LockBlockStatus.ALLUXIO_BLOCK_LOCKED : result;
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
      output.writeInt64(1, lockId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, blockPath_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeEnum(3, lockBlockStatus_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, lockId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, blockPath_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, lockBlockStatus_);
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
    if (!(obj instanceof alluxio.grpc.LockBlockPResponse)) {
      return super.equals(obj);
    }
    alluxio.grpc.LockBlockPResponse other = (alluxio.grpc.LockBlockPResponse) obj;

    boolean result = true;
    result = result && (hasLockId() == other.hasLockId());
    if (hasLockId()) {
      result = result && (getLockId()
          == other.getLockId());
    }
    result = result && (hasBlockPath() == other.hasBlockPath());
    if (hasBlockPath()) {
      result = result && getBlockPath()
          .equals(other.getBlockPath());
    }
    result = result && (hasLockBlockStatus() == other.hasLockBlockStatus());
    if (hasLockBlockStatus()) {
      result = result && lockBlockStatus_ == other.lockBlockStatus_;
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
    if (hasLockId()) {
      hash = (37 * hash) + LOCKID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLockId());
    }
    if (hasBlockPath()) {
      hash = (37 * hash) + BLOCKPATH_FIELD_NUMBER;
      hash = (53 * hash) + getBlockPath().hashCode();
    }
    if (hasLockBlockStatus()) {
      hash = (37 * hash) + LOCKBLOCKSTATUS_FIELD_NUMBER;
      hash = (53 * hash) + lockBlockStatus_;
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.LockBlockPResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.LockBlockPResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.LockBlockPResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.LockBlockPResponse parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.LockBlockPResponse prototype) {
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
   * Protobuf type {@code alluxio.grpc.LockBlockPResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.LockBlockPResponse)
      alluxio.grpc.LockBlockPResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.BlockWorkerProto.internal_static_alluxio_grpc_LockBlockPResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.BlockWorkerProto.internal_static_alluxio_grpc_LockBlockPResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.LockBlockPResponse.class, alluxio.grpc.LockBlockPResponse.Builder.class);
    }

    // Construct using alluxio.grpc.LockBlockPResponse.newBuilder()
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
      lockId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      blockPath_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      lockBlockStatus_ = 1;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.BlockWorkerProto.internal_static_alluxio_grpc_LockBlockPResponse_descriptor;
    }

    public alluxio.grpc.LockBlockPResponse getDefaultInstanceForType() {
      return alluxio.grpc.LockBlockPResponse.getDefaultInstance();
    }

    public alluxio.grpc.LockBlockPResponse build() {
      alluxio.grpc.LockBlockPResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.LockBlockPResponse buildPartial() {
      alluxio.grpc.LockBlockPResponse result = new alluxio.grpc.LockBlockPResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.lockId_ = lockId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.blockPath_ = blockPath_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.lockBlockStatus_ = lockBlockStatus_;
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
      if (other instanceof alluxio.grpc.LockBlockPResponse) {
        return mergeFrom((alluxio.grpc.LockBlockPResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.LockBlockPResponse other) {
      if (other == alluxio.grpc.LockBlockPResponse.getDefaultInstance()) return this;
      if (other.hasLockId()) {
        setLockId(other.getLockId());
      }
      if (other.hasBlockPath()) {
        bitField0_ |= 0x00000002;
        blockPath_ = other.blockPath_;
        onChanged();
      }
      if (other.hasLockBlockStatus()) {
        setLockBlockStatus(other.getLockBlockStatus());
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
      alluxio.grpc.LockBlockPResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.LockBlockPResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long lockId_ ;
    /**
     * <code>optional int64 lockId = 1;</code>
     */
    public boolean hasLockId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 lockId = 1;</code>
     */
    public long getLockId() {
      return lockId_;
    }
    /**
     * <code>optional int64 lockId = 1;</code>
     */
    public Builder setLockId(long value) {
      bitField0_ |= 0x00000001;
      lockId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 lockId = 1;</code>
     */
    public Builder clearLockId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      lockId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object blockPath_ = "";
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public boolean hasBlockPath() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public java.lang.String getBlockPath() {
      java.lang.Object ref = blockPath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          blockPath_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public com.google.protobuf.ByteString
        getBlockPathBytes() {
      java.lang.Object ref = blockPath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        blockPath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public Builder setBlockPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      blockPath_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public Builder clearBlockPath() {
      bitField0_ = (bitField0_ & ~0x00000002);
      blockPath_ = getDefaultInstance().getBlockPath();
      onChanged();
      return this;
    }
    /**
     * <code>optional string blockPath = 2;</code>
     */
    public Builder setBlockPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      blockPath_ = value;
      onChanged();
      return this;
    }

    private int lockBlockStatus_ = 1;
    /**
     * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
     */
    public boolean hasLockBlockStatus() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
     */
    public alluxio.grpc.LockBlockStatus getLockBlockStatus() {
      alluxio.grpc.LockBlockStatus result = alluxio.grpc.LockBlockStatus.valueOf(lockBlockStatus_);
      return result == null ? alluxio.grpc.LockBlockStatus.ALLUXIO_BLOCK_LOCKED : result;
    }
    /**
     * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
     */
    public Builder setLockBlockStatus(alluxio.grpc.LockBlockStatus value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000004;
      lockBlockStatus_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.LockBlockStatus lockBlockStatus = 3;</code>
     */
    public Builder clearLockBlockStatus() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lockBlockStatus_ = 1;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.LockBlockPResponse)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.LockBlockPResponse)
  private static final alluxio.grpc.LockBlockPResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.LockBlockPResponse();
  }

  public static alluxio.grpc.LockBlockPResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<LockBlockPResponse>
      PARSER = new com.google.protobuf.AbstractParser<LockBlockPResponse>() {
    public LockBlockPResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LockBlockPResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LockBlockPResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LockBlockPResponse> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.LockBlockPResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

