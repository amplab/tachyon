// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/table/table_master.proto

package alluxio.grpc.table;

/**
 * Protobuf type {@code alluxio.grpc.table.LongColumnStatsData}
 */
public  final class LongColumnStatsData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.table.LongColumnStatsData)
    LongColumnStatsDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LongColumnStatsData.newBuilder() to construct.
  private LongColumnStatsData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LongColumnStatsData() {
    bitVectors_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LongColumnStatsData();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LongColumnStatsData(
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
          case 8: {
            bitField0_ |= 0x00000001;
            lowValue_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            highValue_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            numNulls_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            numDistincts_ = input.readInt64();
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            bitVectors_ = bs;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
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
    return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_LongColumnStatsData_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_LongColumnStatsData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.table.LongColumnStatsData.class, alluxio.grpc.table.LongColumnStatsData.Builder.class);
  }

  private int bitField0_;
  public static final int LOW_VALUE_FIELD_NUMBER = 1;
  private long lowValue_;
  /**
   * <code>optional int64 low_value = 1;</code>
   * @return Whether the lowValue field is set.
   */
  public boolean hasLowValue() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional int64 low_value = 1;</code>
   * @return The lowValue.
   */
  public long getLowValue() {
    return lowValue_;
  }

  public static final int HIGH_VALUE_FIELD_NUMBER = 2;
  private long highValue_;
  /**
   * <code>optional int64 high_value = 2;</code>
   * @return Whether the highValue field is set.
   */
  public boolean hasHighValue() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional int64 high_value = 2;</code>
   * @return The highValue.
   */
  public long getHighValue() {
    return highValue_;
  }

  public static final int NUM_NULLS_FIELD_NUMBER = 3;
  private long numNulls_;
  /**
   * <code>optional int64 num_nulls = 3;</code>
   * @return Whether the numNulls field is set.
   */
  public boolean hasNumNulls() {
    return ((bitField0_ & 0x00000004) != 0);
  }
  /**
   * <code>optional int64 num_nulls = 3;</code>
   * @return The numNulls.
   */
  public long getNumNulls() {
    return numNulls_;
  }

  public static final int NUM_DISTINCTS_FIELD_NUMBER = 4;
  private long numDistincts_;
  /**
   * <code>optional int64 num_distincts = 4;</code>
   * @return Whether the numDistincts field is set.
   */
  public boolean hasNumDistincts() {
    return ((bitField0_ & 0x00000008) != 0);
  }
  /**
   * <code>optional int64 num_distincts = 4;</code>
   * @return The numDistincts.
   */
  public long getNumDistincts() {
    return numDistincts_;
  }

  public static final int BIT_VECTORS_FIELD_NUMBER = 5;
  private volatile java.lang.Object bitVectors_;
  /**
   * <code>optional string bit_vectors = 5;</code>
   * @return Whether the bitVectors field is set.
   */
  public boolean hasBitVectors() {
    return ((bitField0_ & 0x00000010) != 0);
  }
  /**
   * <code>optional string bit_vectors = 5;</code>
   * @return The bitVectors.
   */
  public java.lang.String getBitVectors() {
    java.lang.Object ref = bitVectors_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        bitVectors_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string bit_vectors = 5;</code>
   * @return The bytes for bitVectors.
   */
  public com.google.protobuf.ByteString
      getBitVectorsBytes() {
    java.lang.Object ref = bitVectors_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      bitVectors_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeInt64(1, lowValue_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeInt64(2, highValue_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      output.writeInt64(3, numNulls_);
    }
    if (((bitField0_ & 0x00000008) != 0)) {
      output.writeInt64(4, numDistincts_);
    }
    if (((bitField0_ & 0x00000010) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, bitVectors_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, lowValue_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, highValue_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, numNulls_);
    }
    if (((bitField0_ & 0x00000008) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, numDistincts_);
    }
    if (((bitField0_ & 0x00000010) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, bitVectors_);
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
    if (!(obj instanceof alluxio.grpc.table.LongColumnStatsData)) {
      return super.equals(obj);
    }
    alluxio.grpc.table.LongColumnStatsData other = (alluxio.grpc.table.LongColumnStatsData) obj;

    if (hasLowValue() != other.hasLowValue()) return false;
    if (hasLowValue()) {
      if (getLowValue()
          != other.getLowValue()) return false;
    }
    if (hasHighValue() != other.hasHighValue()) return false;
    if (hasHighValue()) {
      if (getHighValue()
          != other.getHighValue()) return false;
    }
    if (hasNumNulls() != other.hasNumNulls()) return false;
    if (hasNumNulls()) {
      if (getNumNulls()
          != other.getNumNulls()) return false;
    }
    if (hasNumDistincts() != other.hasNumDistincts()) return false;
    if (hasNumDistincts()) {
      if (getNumDistincts()
          != other.getNumDistincts()) return false;
    }
    if (hasBitVectors() != other.hasBitVectors()) return false;
    if (hasBitVectors()) {
      if (!getBitVectors()
          .equals(other.getBitVectors())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasLowValue()) {
      hash = (37 * hash) + LOW_VALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLowValue());
    }
    if (hasHighValue()) {
      hash = (37 * hash) + HIGH_VALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHighValue());
    }
    if (hasNumNulls()) {
      hash = (37 * hash) + NUM_NULLS_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getNumNulls());
    }
    if (hasNumDistincts()) {
      hash = (37 * hash) + NUM_DISTINCTS_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getNumDistincts());
    }
    if (hasBitVectors()) {
      hash = (37 * hash) + BIT_VECTORS_FIELD_NUMBER;
      hash = (53 * hash) + getBitVectors().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.LongColumnStatsData parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(alluxio.grpc.table.LongColumnStatsData prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code alluxio.grpc.table.LongColumnStatsData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.table.LongColumnStatsData)
      alluxio.grpc.table.LongColumnStatsDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_LongColumnStatsData_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_LongColumnStatsData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.table.LongColumnStatsData.class, alluxio.grpc.table.LongColumnStatsData.Builder.class);
    }

    // Construct using alluxio.grpc.table.LongColumnStatsData.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      lowValue_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      highValue_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      numNulls_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      numDistincts_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      bitVectors_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_LongColumnStatsData_descriptor;
    }

    @java.lang.Override
    public alluxio.grpc.table.LongColumnStatsData getDefaultInstanceForType() {
      return alluxio.grpc.table.LongColumnStatsData.getDefaultInstance();
    }

    @java.lang.Override
    public alluxio.grpc.table.LongColumnStatsData build() {
      alluxio.grpc.table.LongColumnStatsData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public alluxio.grpc.table.LongColumnStatsData buildPartial() {
      alluxio.grpc.table.LongColumnStatsData result = new alluxio.grpc.table.LongColumnStatsData(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.lowValue_ = lowValue_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.highValue_ = highValue_;
        to_bitField0_ |= 0x00000002;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.numNulls_ = numNulls_;
        to_bitField0_ |= 0x00000004;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.numDistincts_ = numDistincts_;
        to_bitField0_ |= 0x00000008;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        to_bitField0_ |= 0x00000010;
      }
      result.bitVectors_ = bitVectors_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof alluxio.grpc.table.LongColumnStatsData) {
        return mergeFrom((alluxio.grpc.table.LongColumnStatsData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.table.LongColumnStatsData other) {
      if (other == alluxio.grpc.table.LongColumnStatsData.getDefaultInstance()) return this;
      if (other.hasLowValue()) {
        setLowValue(other.getLowValue());
      }
      if (other.hasHighValue()) {
        setHighValue(other.getHighValue());
      }
      if (other.hasNumNulls()) {
        setNumNulls(other.getNumNulls());
      }
      if (other.hasNumDistincts()) {
        setNumDistincts(other.getNumDistincts());
      }
      if (other.hasBitVectors()) {
        bitField0_ |= 0x00000010;
        bitVectors_ = other.bitVectors_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      alluxio.grpc.table.LongColumnStatsData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.table.LongColumnStatsData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long lowValue_ ;
    /**
     * <code>optional int64 low_value = 1;</code>
     * @return Whether the lowValue field is set.
     */
    public boolean hasLowValue() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional int64 low_value = 1;</code>
     * @return The lowValue.
     */
    public long getLowValue() {
      return lowValue_;
    }
    /**
     * <code>optional int64 low_value = 1;</code>
     * @param value The lowValue to set.
     * @return This builder for chaining.
     */
    public Builder setLowValue(long value) {
      bitField0_ |= 0x00000001;
      lowValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 low_value = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearLowValue() {
      bitField0_ = (bitField0_ & ~0x00000001);
      lowValue_ = 0L;
      onChanged();
      return this;
    }

    private long highValue_ ;
    /**
     * <code>optional int64 high_value = 2;</code>
     * @return Whether the highValue field is set.
     */
    public boolean hasHighValue() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional int64 high_value = 2;</code>
     * @return The highValue.
     */
    public long getHighValue() {
      return highValue_;
    }
    /**
     * <code>optional int64 high_value = 2;</code>
     * @param value The highValue to set.
     * @return This builder for chaining.
     */
    public Builder setHighValue(long value) {
      bitField0_ |= 0x00000002;
      highValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 high_value = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearHighValue() {
      bitField0_ = (bitField0_ & ~0x00000002);
      highValue_ = 0L;
      onChanged();
      return this;
    }

    private long numNulls_ ;
    /**
     * <code>optional int64 num_nulls = 3;</code>
     * @return Whether the numNulls field is set.
     */
    public boolean hasNumNulls() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>optional int64 num_nulls = 3;</code>
     * @return The numNulls.
     */
    public long getNumNulls() {
      return numNulls_;
    }
    /**
     * <code>optional int64 num_nulls = 3;</code>
     * @param value The numNulls to set.
     * @return This builder for chaining.
     */
    public Builder setNumNulls(long value) {
      bitField0_ |= 0x00000004;
      numNulls_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 num_nulls = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumNulls() {
      bitField0_ = (bitField0_ & ~0x00000004);
      numNulls_ = 0L;
      onChanged();
      return this;
    }

    private long numDistincts_ ;
    /**
     * <code>optional int64 num_distincts = 4;</code>
     * @return Whether the numDistincts field is set.
     */
    public boolean hasNumDistincts() {
      return ((bitField0_ & 0x00000008) != 0);
    }
    /**
     * <code>optional int64 num_distincts = 4;</code>
     * @return The numDistincts.
     */
    public long getNumDistincts() {
      return numDistincts_;
    }
    /**
     * <code>optional int64 num_distincts = 4;</code>
     * @param value The numDistincts to set.
     * @return This builder for chaining.
     */
    public Builder setNumDistincts(long value) {
      bitField0_ |= 0x00000008;
      numDistincts_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 num_distincts = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumDistincts() {
      bitField0_ = (bitField0_ & ~0x00000008);
      numDistincts_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object bitVectors_ = "";
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @return Whether the bitVectors field is set.
     */
    public boolean hasBitVectors() {
      return ((bitField0_ & 0x00000010) != 0);
    }
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @return The bitVectors.
     */
    public java.lang.String getBitVectors() {
      java.lang.Object ref = bitVectors_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          bitVectors_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @return The bytes for bitVectors.
     */
    public com.google.protobuf.ByteString
        getBitVectorsBytes() {
      java.lang.Object ref = bitVectors_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        bitVectors_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @param value The bitVectors to set.
     * @return This builder for chaining.
     */
    public Builder setBitVectors(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      bitVectors_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearBitVectors() {
      bitField0_ = (bitField0_ & ~0x00000010);
      bitVectors_ = getDefaultInstance().getBitVectors();
      onChanged();
      return this;
    }
    /**
     * <code>optional string bit_vectors = 5;</code>
     * @param value The bytes for bitVectors to set.
     * @return This builder for chaining.
     */
    public Builder setBitVectorsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      bitVectors_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.table.LongColumnStatsData)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.table.LongColumnStatsData)
  private static final alluxio.grpc.table.LongColumnStatsData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.table.LongColumnStatsData();
  }

  public static alluxio.grpc.table.LongColumnStatsData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<LongColumnStatsData>
      PARSER = new com.google.protobuf.AbstractParser<LongColumnStatsData>() {
    @java.lang.Override
    public LongColumnStatsData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LongColumnStatsData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LongColumnStatsData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LongColumnStatsData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public alluxio.grpc.table.LongColumnStatsData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

