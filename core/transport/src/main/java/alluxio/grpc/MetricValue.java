// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: meta_master.proto

package alluxio.grpc;

/**
 * <pre>
 * This type is used as a union, only one of doubleValue or longValue should be set
 * </pre>
 *
 * Protobuf type {@code alluxio.grpc.MetricValue}
 */
public  final class MetricValue extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.MetricValue)
    MetricValueOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MetricValue.newBuilder() to construct.
  private MetricValue(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MetricValue() {
    doubleValue_ = 0D;
    longValue_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MetricValue(
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
          case 9: {
            bitField0_ |= 0x00000001;
            doubleValue_ = input.readDouble();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            longValue_ = input.readInt64();
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
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MetricValue_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MetricValue_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.MetricValue.class, alluxio.grpc.MetricValue.Builder.class);
  }

  private int bitField0_;
  public static final int DOUBLEVALUE_FIELD_NUMBER = 1;
  private double doubleValue_;
  /**
   * <code>optional double doubleValue = 1;</code>
   */
  public boolean hasDoubleValue() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional double doubleValue = 1;</code>
   */
  public double getDoubleValue() {
    return doubleValue_;
  }

  public static final int LONGVALUE_FIELD_NUMBER = 2;
  private long longValue_;
  /**
   * <code>optional int64 longValue = 2;</code>
   */
  public boolean hasLongValue() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional int64 longValue = 2;</code>
   */
  public long getLongValue() {
    return longValue_;
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
      output.writeDouble(1, doubleValue_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, longValue_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, doubleValue_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, longValue_);
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
    if (!(obj instanceof alluxio.grpc.MetricValue)) {
      return super.equals(obj);
    }
    alluxio.grpc.MetricValue other = (alluxio.grpc.MetricValue) obj;

    boolean result = true;
    result = result && (hasDoubleValue() == other.hasDoubleValue());
    if (hasDoubleValue()) {
      result = result && (
          java.lang.Double.doubleToLongBits(getDoubleValue())
          == java.lang.Double.doubleToLongBits(
              other.getDoubleValue()));
    }
    result = result && (hasLongValue() == other.hasLongValue());
    if (hasLongValue()) {
      result = result && (getLongValue()
          == other.getLongValue());
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
    if (hasDoubleValue()) {
      hash = (37 * hash) + DOUBLEVALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getDoubleValue()));
    }
    if (hasLongValue()) {
      hash = (37 * hash) + LONGVALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLongValue());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.MetricValue parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MetricValue parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MetricValue parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.MetricValue parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MetricValue parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.MetricValue parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MetricValue parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.MetricValue prototype) {
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
   * <pre>
   * This type is used as a union, only one of doubleValue or longValue should be set
   * </pre>
   *
   * Protobuf type {@code alluxio.grpc.MetricValue}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.MetricValue)
      alluxio.grpc.MetricValueOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MetricValue_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MetricValue_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.MetricValue.class, alluxio.grpc.MetricValue.Builder.class);
    }

    // Construct using alluxio.grpc.MetricValue.newBuilder()
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
      doubleValue_ = 0D;
      bitField0_ = (bitField0_ & ~0x00000001);
      longValue_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MetricValue_descriptor;
    }

    public alluxio.grpc.MetricValue getDefaultInstanceForType() {
      return alluxio.grpc.MetricValue.getDefaultInstance();
    }

    public alluxio.grpc.MetricValue build() {
      alluxio.grpc.MetricValue result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.MetricValue buildPartial() {
      alluxio.grpc.MetricValue result = new alluxio.grpc.MetricValue(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.doubleValue_ = doubleValue_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.longValue_ = longValue_;
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
      if (other instanceof alluxio.grpc.MetricValue) {
        return mergeFrom((alluxio.grpc.MetricValue)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.MetricValue other) {
      if (other == alluxio.grpc.MetricValue.getDefaultInstance()) return this;
      if (other.hasDoubleValue()) {
        setDoubleValue(other.getDoubleValue());
      }
      if (other.hasLongValue()) {
        setLongValue(other.getLongValue());
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
      alluxio.grpc.MetricValue parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.MetricValue) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private double doubleValue_ ;
    /**
     * <code>optional double doubleValue = 1;</code>
     */
    public boolean hasDoubleValue() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional double doubleValue = 1;</code>
     */
    public double getDoubleValue() {
      return doubleValue_;
    }
    /**
     * <code>optional double doubleValue = 1;</code>
     */
    public Builder setDoubleValue(double value) {
      bitField0_ |= 0x00000001;
      doubleValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional double doubleValue = 1;</code>
     */
    public Builder clearDoubleValue() {
      bitField0_ = (bitField0_ & ~0x00000001);
      doubleValue_ = 0D;
      onChanged();
      return this;
    }

    private long longValue_ ;
    /**
     * <code>optional int64 longValue = 2;</code>
     */
    public boolean hasLongValue() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 longValue = 2;</code>
     */
    public long getLongValue() {
      return longValue_;
    }
    /**
     * <code>optional int64 longValue = 2;</code>
     */
    public Builder setLongValue(long value) {
      bitField0_ |= 0x00000002;
      longValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 longValue = 2;</code>
     */
    public Builder clearLongValue() {
      bitField0_ = (bitField0_ & ~0x00000002);
      longValue_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.MetricValue)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.MetricValue)
  private static final alluxio.grpc.MetricValue DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.MetricValue();
  }

  public static alluxio.grpc.MetricValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MetricValue>
      PARSER = new com.google.protobuf.AbstractParser<MetricValue>() {
    public MetricValue parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MetricValue(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MetricValue> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MetricValue> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.MetricValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

