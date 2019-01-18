// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/block_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.block.CommitBlockPRequest}
 */
public  final class CommitBlockPRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.block.CommitBlockPRequest)
    CommitBlockPRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CommitBlockPRequest.newBuilder() to construct.
  private CommitBlockPRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CommitBlockPRequest() {
    workerId_ = 0L;
    usedBytesOnTier_ = 0L;
    tierAlias_ = "";
    blockId_ = 0L;
    length_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CommitBlockPRequest(
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
            workerId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            usedBytesOnTier_ = input.readInt64();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            tierAlias_ = bs;
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            blockId_ = input.readInt64();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            length_ = input.readInt64();
            break;
          }
          case 50: {
            alluxio.grpc.CommitBlockPOptions.Builder subBuilder = null;
            if (((bitField0_ & 0x00000020) == 0x00000020)) {
              subBuilder = options_.toBuilder();
            }
            options_ = input.readMessage(alluxio.grpc.CommitBlockPOptions.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(options_);
              options_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000020;
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
    return alluxio.grpc.BlockMasterProto.internal_static_alluxio_grpc_block_CommitBlockPRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.BlockMasterProto.internal_static_alluxio_grpc_block_CommitBlockPRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.CommitBlockPRequest.class, alluxio.grpc.CommitBlockPRequest.Builder.class);
  }

  private int bitField0_;
  public static final int WORKERID_FIELD_NUMBER = 1;
  private long workerId_;
  /**
   * <pre>
   ** the id of the worker 
   * </pre>
   *
   * <code>optional int64 workerId = 1;</code>
   */
  public boolean hasWorkerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   ** the id of the worker 
   * </pre>
   *
   * <code>optional int64 workerId = 1;</code>
   */
  public long getWorkerId() {
    return workerId_;
  }

  public static final int USEDBYTESONTIER_FIELD_NUMBER = 2;
  private long usedBytesOnTier_;
  /**
   * <pre>
   ** the space used in bytes on the target tier 
   * </pre>
   *
   * <code>optional int64 usedBytesOnTier = 2;</code>
   */
  public boolean hasUsedBytesOnTier() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   ** the space used in bytes on the target tier 
   * </pre>
   *
   * <code>optional int64 usedBytesOnTier = 2;</code>
   */
  public long getUsedBytesOnTier() {
    return usedBytesOnTier_;
  }

  public static final int TIERALIAS_FIELD_NUMBER = 3;
  private volatile java.lang.Object tierAlias_;
  /**
   * <pre>
   ** the alias of the target tier 
   * </pre>
   *
   * <code>optional string tierAlias = 3;</code>
   */
  public boolean hasTierAlias() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   ** the alias of the target tier 
   * </pre>
   *
   * <code>optional string tierAlias = 3;</code>
   */
  public java.lang.String getTierAlias() {
    java.lang.Object ref = tierAlias_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        tierAlias_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   ** the alias of the target tier 
   * </pre>
   *
   * <code>optional string tierAlias = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTierAliasBytes() {
    java.lang.Object ref = tierAlias_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tierAlias_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BLOCKID_FIELD_NUMBER = 4;
  private long blockId_;
  /**
   * <pre>
   ** the id of the block being committed 
   * </pre>
   *
   * <code>optional int64 blockId = 4;</code>
   */
  public boolean hasBlockId() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   ** the id of the block being committed 
   * </pre>
   *
   * <code>optional int64 blockId = 4;</code>
   */
  public long getBlockId() {
    return blockId_;
  }

  public static final int LENGTH_FIELD_NUMBER = 5;
  private long length_;
  /**
   * <pre>
   ** the length of the block being committed 
   * </pre>
   *
   * <code>optional int64 length = 5;</code>
   */
  public boolean hasLength() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   ** the length of the block being committed 
   * </pre>
   *
   * <code>optional int64 length = 5;</code>
   */
  public long getLength() {
    return length_;
  }

  public static final int OPTIONS_FIELD_NUMBER = 6;
  private alluxio.grpc.CommitBlockPOptions options_;
  /**
   * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
   */
  public boolean hasOptions() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
   */
  public alluxio.grpc.CommitBlockPOptions getOptions() {
    return options_ == null ? alluxio.grpc.CommitBlockPOptions.getDefaultInstance() : options_;
  }
  /**
   * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
   */
  public alluxio.grpc.CommitBlockPOptionsOrBuilder getOptionsOrBuilder() {
    return options_ == null ? alluxio.grpc.CommitBlockPOptions.getDefaultInstance() : options_;
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
      output.writeInt64(1, workerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, usedBytesOnTier_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, tierAlias_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(4, blockId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, length_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeMessage(6, getOptions());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, workerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, usedBytesOnTier_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, tierAlias_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, blockId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, length_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(6, getOptions());
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
    if (!(obj instanceof alluxio.grpc.CommitBlockPRequest)) {
      return super.equals(obj);
    }
    alluxio.grpc.CommitBlockPRequest other = (alluxio.grpc.CommitBlockPRequest) obj;

    boolean result = true;
    result = result && (hasWorkerId() == other.hasWorkerId());
    if (hasWorkerId()) {
      result = result && (getWorkerId()
          == other.getWorkerId());
    }
    result = result && (hasUsedBytesOnTier() == other.hasUsedBytesOnTier());
    if (hasUsedBytesOnTier()) {
      result = result && (getUsedBytesOnTier()
          == other.getUsedBytesOnTier());
    }
    result = result && (hasTierAlias() == other.hasTierAlias());
    if (hasTierAlias()) {
      result = result && getTierAlias()
          .equals(other.getTierAlias());
    }
    result = result && (hasBlockId() == other.hasBlockId());
    if (hasBlockId()) {
      result = result && (getBlockId()
          == other.getBlockId());
    }
    result = result && (hasLength() == other.hasLength());
    if (hasLength()) {
      result = result && (getLength()
          == other.getLength());
    }
    result = result && (hasOptions() == other.hasOptions());
    if (hasOptions()) {
      result = result && getOptions()
          .equals(other.getOptions());
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
    if (hasWorkerId()) {
      hash = (37 * hash) + WORKERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getWorkerId());
    }
    if (hasUsedBytesOnTier()) {
      hash = (37 * hash) + USEDBYTESONTIER_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getUsedBytesOnTier());
    }
    if (hasTierAlias()) {
      hash = (37 * hash) + TIERALIAS_FIELD_NUMBER;
      hash = (53 * hash) + getTierAlias().hashCode();
    }
    if (hasBlockId()) {
      hash = (37 * hash) + BLOCKID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBlockId());
    }
    if (hasLength()) {
      hash = (37 * hash) + LENGTH_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLength());
    }
    if (hasOptions()) {
      hash = (37 * hash) + OPTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getOptions().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CommitBlockPRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CommitBlockPRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CommitBlockPRequest parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.CommitBlockPRequest prototype) {
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
   * Protobuf type {@code alluxio.grpc.block.CommitBlockPRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.block.CommitBlockPRequest)
      alluxio.grpc.CommitBlockPRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.BlockMasterProto.internal_static_alluxio_grpc_block_CommitBlockPRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.BlockMasterProto.internal_static_alluxio_grpc_block_CommitBlockPRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.CommitBlockPRequest.class, alluxio.grpc.CommitBlockPRequest.Builder.class);
    }

    // Construct using alluxio.grpc.CommitBlockPRequest.newBuilder()
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
        getOptionsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      workerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      usedBytesOnTier_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      tierAlias_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      blockId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      length_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      if (optionsBuilder_ == null) {
        options_ = null;
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.BlockMasterProto.internal_static_alluxio_grpc_block_CommitBlockPRequest_descriptor;
    }

    public alluxio.grpc.CommitBlockPRequest getDefaultInstanceForType() {
      return alluxio.grpc.CommitBlockPRequest.getDefaultInstance();
    }

    public alluxio.grpc.CommitBlockPRequest build() {
      alluxio.grpc.CommitBlockPRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.CommitBlockPRequest buildPartial() {
      alluxio.grpc.CommitBlockPRequest result = new alluxio.grpc.CommitBlockPRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.workerId_ = workerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.usedBytesOnTier_ = usedBytesOnTier_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.tierAlias_ = tierAlias_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.blockId_ = blockId_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.length_ = length_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      if (optionsBuilder_ == null) {
        result.options_ = options_;
      } else {
        result.options_ = optionsBuilder_.build();
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
      if (other instanceof alluxio.grpc.CommitBlockPRequest) {
        return mergeFrom((alluxio.grpc.CommitBlockPRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.CommitBlockPRequest other) {
      if (other == alluxio.grpc.CommitBlockPRequest.getDefaultInstance()) return this;
      if (other.hasWorkerId()) {
        setWorkerId(other.getWorkerId());
      }
      if (other.hasUsedBytesOnTier()) {
        setUsedBytesOnTier(other.getUsedBytesOnTier());
      }
      if (other.hasTierAlias()) {
        bitField0_ |= 0x00000004;
        tierAlias_ = other.tierAlias_;
        onChanged();
      }
      if (other.hasBlockId()) {
        setBlockId(other.getBlockId());
      }
      if (other.hasLength()) {
        setLength(other.getLength());
      }
      if (other.hasOptions()) {
        mergeOptions(other.getOptions());
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
      alluxio.grpc.CommitBlockPRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.CommitBlockPRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long workerId_ ;
    /**
     * <pre>
     ** the id of the worker 
     * </pre>
     *
     * <code>optional int64 workerId = 1;</code>
     */
    public boolean hasWorkerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     ** the id of the worker 
     * </pre>
     *
     * <code>optional int64 workerId = 1;</code>
     */
    public long getWorkerId() {
      return workerId_;
    }
    /**
     * <pre>
     ** the id of the worker 
     * </pre>
     *
     * <code>optional int64 workerId = 1;</code>
     */
    public Builder setWorkerId(long value) {
      bitField0_ |= 0x00000001;
      workerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the id of the worker 
     * </pre>
     *
     * <code>optional int64 workerId = 1;</code>
     */
    public Builder clearWorkerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      workerId_ = 0L;
      onChanged();
      return this;
    }

    private long usedBytesOnTier_ ;
    /**
     * <pre>
     ** the space used in bytes on the target tier 
     * </pre>
     *
     * <code>optional int64 usedBytesOnTier = 2;</code>
     */
    public boolean hasUsedBytesOnTier() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     ** the space used in bytes on the target tier 
     * </pre>
     *
     * <code>optional int64 usedBytesOnTier = 2;</code>
     */
    public long getUsedBytesOnTier() {
      return usedBytesOnTier_;
    }
    /**
     * <pre>
     ** the space used in bytes on the target tier 
     * </pre>
     *
     * <code>optional int64 usedBytesOnTier = 2;</code>
     */
    public Builder setUsedBytesOnTier(long value) {
      bitField0_ |= 0x00000002;
      usedBytesOnTier_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the space used in bytes on the target tier 
     * </pre>
     *
     * <code>optional int64 usedBytesOnTier = 2;</code>
     */
    public Builder clearUsedBytesOnTier() {
      bitField0_ = (bitField0_ & ~0x00000002);
      usedBytesOnTier_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object tierAlias_ = "";
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public boolean hasTierAlias() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public java.lang.String getTierAlias() {
      java.lang.Object ref = tierAlias_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          tierAlias_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTierAliasBytes() {
      java.lang.Object ref = tierAlias_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tierAlias_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public Builder setTierAlias(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      tierAlias_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public Builder clearTierAlias() {
      bitField0_ = (bitField0_ & ~0x00000004);
      tierAlias_ = getDefaultInstance().getTierAlias();
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the alias of the target tier 
     * </pre>
     *
     * <code>optional string tierAlias = 3;</code>
     */
    public Builder setTierAliasBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      tierAlias_ = value;
      onChanged();
      return this;
    }

    private long blockId_ ;
    /**
     * <pre>
     ** the id of the block being committed 
     * </pre>
     *
     * <code>optional int64 blockId = 4;</code>
     */
    public boolean hasBlockId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     ** the id of the block being committed 
     * </pre>
     *
     * <code>optional int64 blockId = 4;</code>
     */
    public long getBlockId() {
      return blockId_;
    }
    /**
     * <pre>
     ** the id of the block being committed 
     * </pre>
     *
     * <code>optional int64 blockId = 4;</code>
     */
    public Builder setBlockId(long value) {
      bitField0_ |= 0x00000008;
      blockId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the id of the block being committed 
     * </pre>
     *
     * <code>optional int64 blockId = 4;</code>
     */
    public Builder clearBlockId() {
      bitField0_ = (bitField0_ & ~0x00000008);
      blockId_ = 0L;
      onChanged();
      return this;
    }

    private long length_ ;
    /**
     * <pre>
     ** the length of the block being committed 
     * </pre>
     *
     * <code>optional int64 length = 5;</code>
     */
    public boolean hasLength() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     ** the length of the block being committed 
     * </pre>
     *
     * <code>optional int64 length = 5;</code>
     */
    public long getLength() {
      return length_;
    }
    /**
     * <pre>
     ** the length of the block being committed 
     * </pre>
     *
     * <code>optional int64 length = 5;</code>
     */
    public Builder setLength(long value) {
      bitField0_ |= 0x00000010;
      length_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the length of the block being committed 
     * </pre>
     *
     * <code>optional int64 length = 5;</code>
     */
    public Builder clearLength() {
      bitField0_ = (bitField0_ & ~0x00000010);
      length_ = 0L;
      onChanged();
      return this;
    }

    private alluxio.grpc.CommitBlockPOptions options_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.CommitBlockPOptions, alluxio.grpc.CommitBlockPOptions.Builder, alluxio.grpc.CommitBlockPOptionsOrBuilder> optionsBuilder_;
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public boolean hasOptions() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public alluxio.grpc.CommitBlockPOptions getOptions() {
      if (optionsBuilder_ == null) {
        return options_ == null ? alluxio.grpc.CommitBlockPOptions.getDefaultInstance() : options_;
      } else {
        return optionsBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public Builder setOptions(alluxio.grpc.CommitBlockPOptions value) {
      if (optionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        options_ = value;
        onChanged();
      } else {
        optionsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000020;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public Builder setOptions(
        alluxio.grpc.CommitBlockPOptions.Builder builderForValue) {
      if (optionsBuilder_ == null) {
        options_ = builderForValue.build();
        onChanged();
      } else {
        optionsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000020;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public Builder mergeOptions(alluxio.grpc.CommitBlockPOptions value) {
      if (optionsBuilder_ == null) {
        if (((bitField0_ & 0x00000020) == 0x00000020) &&
            options_ != null &&
            options_ != alluxio.grpc.CommitBlockPOptions.getDefaultInstance()) {
          options_ =
            alluxio.grpc.CommitBlockPOptions.newBuilder(options_).mergeFrom(value).buildPartial();
        } else {
          options_ = value;
        }
        onChanged();
      } else {
        optionsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000020;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public Builder clearOptions() {
      if (optionsBuilder_ == null) {
        options_ = null;
        onChanged();
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public alluxio.grpc.CommitBlockPOptions.Builder getOptionsBuilder() {
      bitField0_ |= 0x00000020;
      onChanged();
      return getOptionsFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    public alluxio.grpc.CommitBlockPOptionsOrBuilder getOptionsOrBuilder() {
      if (optionsBuilder_ != null) {
        return optionsBuilder_.getMessageOrBuilder();
      } else {
        return options_ == null ?
            alluxio.grpc.CommitBlockPOptions.getDefaultInstance() : options_;
      }
    }
    /**
     * <code>optional .alluxio.grpc.block.CommitBlockPOptions options = 6;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.CommitBlockPOptions, alluxio.grpc.CommitBlockPOptions.Builder, alluxio.grpc.CommitBlockPOptionsOrBuilder> 
        getOptionsFieldBuilder() {
      if (optionsBuilder_ == null) {
        optionsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.CommitBlockPOptions, alluxio.grpc.CommitBlockPOptions.Builder, alluxio.grpc.CommitBlockPOptionsOrBuilder>(
                getOptions(),
                getParentForChildren(),
                isClean());
        options_ = null;
      }
      return optionsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.block.CommitBlockPRequest)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.block.CommitBlockPRequest)
  private static final alluxio.grpc.CommitBlockPRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.CommitBlockPRequest();
  }

  public static alluxio.grpc.CommitBlockPRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CommitBlockPRequest>
      PARSER = new com.google.protobuf.AbstractParser<CommitBlockPRequest>() {
    public CommitBlockPRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CommitBlockPRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CommitBlockPRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommitBlockPRequest> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.CommitBlockPRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

