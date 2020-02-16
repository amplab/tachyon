// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/table/table_master.proto

package alluxio.grpc.table;

/**
 * Protobuf type {@code alluxio.grpc.table.RangeSet}
 */
public  final class RangeSet extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.table.RangeSet)
    RangeSetOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RangeSet.newBuilder() to construct.
  private RangeSet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RangeSet() {
    ranges_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RangeSet();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RangeSet(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              ranges_ = new java.util.ArrayList<alluxio.grpc.table.Range>();
              mutable_bitField0_ |= 0x00000001;
            }
            ranges_.add(
                input.readMessage(alluxio.grpc.table.Range.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        ranges_ = java.util.Collections.unmodifiableList(ranges_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_RangeSet_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_RangeSet_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.table.RangeSet.class, alluxio.grpc.table.RangeSet.Builder.class);
  }

  public static final int RANGES_FIELD_NUMBER = 1;
  private java.util.List<alluxio.grpc.table.Range> ranges_;
  /**
   * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
   */
  public java.util.List<alluxio.grpc.table.Range> getRangesList() {
    return ranges_;
  }
  /**
   * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
   */
  public java.util.List<? extends alluxio.grpc.table.RangeOrBuilder> 
      getRangesOrBuilderList() {
    return ranges_;
  }
  /**
   * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
   */
  public int getRangesCount() {
    return ranges_.size();
  }
  /**
   * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
   */
  public alluxio.grpc.table.Range getRanges(int index) {
    return ranges_.get(index);
  }
  /**
   * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
   */
  public alluxio.grpc.table.RangeOrBuilder getRangesOrBuilder(
      int index) {
    return ranges_.get(index);
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
    for (int i = 0; i < ranges_.size(); i++) {
      output.writeMessage(1, ranges_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < ranges_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, ranges_.get(i));
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
    if (!(obj instanceof alluxio.grpc.table.RangeSet)) {
      return super.equals(obj);
    }
    alluxio.grpc.table.RangeSet other = (alluxio.grpc.table.RangeSet) obj;

    if (!getRangesList()
        .equals(other.getRangesList())) return false;
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
    if (getRangesCount() > 0) {
      hash = (37 * hash) + RANGES_FIELD_NUMBER;
      hash = (53 * hash) + getRangesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.table.RangeSet parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.table.RangeSet parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.RangeSet parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.table.RangeSet parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.table.RangeSet prototype) {
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
   * Protobuf type {@code alluxio.grpc.table.RangeSet}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.table.RangeSet)
      alluxio.grpc.table.RangeSetOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_RangeSet_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_RangeSet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.table.RangeSet.class, alluxio.grpc.table.RangeSet.Builder.class);
    }

    // Construct using alluxio.grpc.table.RangeSet.newBuilder()
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
        getRangesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (rangesBuilder_ == null) {
        ranges_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        rangesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.table.TableMasterProto.internal_static_alluxio_grpc_table_RangeSet_descriptor;
    }

    @java.lang.Override
    public alluxio.grpc.table.RangeSet getDefaultInstanceForType() {
      return alluxio.grpc.table.RangeSet.getDefaultInstance();
    }

    @java.lang.Override
    public alluxio.grpc.table.RangeSet build() {
      alluxio.grpc.table.RangeSet result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public alluxio.grpc.table.RangeSet buildPartial() {
      alluxio.grpc.table.RangeSet result = new alluxio.grpc.table.RangeSet(this);
      int from_bitField0_ = bitField0_;
      if (rangesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          ranges_ = java.util.Collections.unmodifiableList(ranges_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.ranges_ = ranges_;
      } else {
        result.ranges_ = rangesBuilder_.build();
      }
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
      if (other instanceof alluxio.grpc.table.RangeSet) {
        return mergeFrom((alluxio.grpc.table.RangeSet)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.table.RangeSet other) {
      if (other == alluxio.grpc.table.RangeSet.getDefaultInstance()) return this;
      if (rangesBuilder_ == null) {
        if (!other.ranges_.isEmpty()) {
          if (ranges_.isEmpty()) {
            ranges_ = other.ranges_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureRangesIsMutable();
            ranges_.addAll(other.ranges_);
          }
          onChanged();
        }
      } else {
        if (!other.ranges_.isEmpty()) {
          if (rangesBuilder_.isEmpty()) {
            rangesBuilder_.dispose();
            rangesBuilder_ = null;
            ranges_ = other.ranges_;
            bitField0_ = (bitField0_ & ~0x00000001);
            rangesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getRangesFieldBuilder() : null;
          } else {
            rangesBuilder_.addAllMessages(other.ranges_);
          }
        }
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
      alluxio.grpc.table.RangeSet parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.table.RangeSet) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<alluxio.grpc.table.Range> ranges_ =
      java.util.Collections.emptyList();
    private void ensureRangesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        ranges_ = new java.util.ArrayList<alluxio.grpc.table.Range>(ranges_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        alluxio.grpc.table.Range, alluxio.grpc.table.Range.Builder, alluxio.grpc.table.RangeOrBuilder> rangesBuilder_;

    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public java.util.List<alluxio.grpc.table.Range> getRangesList() {
      if (rangesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(ranges_);
      } else {
        return rangesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public int getRangesCount() {
      if (rangesBuilder_ == null) {
        return ranges_.size();
      } else {
        return rangesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public alluxio.grpc.table.Range getRanges(int index) {
      if (rangesBuilder_ == null) {
        return ranges_.get(index);
      } else {
        return rangesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder setRanges(
        int index, alluxio.grpc.table.Range value) {
      if (rangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRangesIsMutable();
        ranges_.set(index, value);
        onChanged();
      } else {
        rangesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder setRanges(
        int index, alluxio.grpc.table.Range.Builder builderForValue) {
      if (rangesBuilder_ == null) {
        ensureRangesIsMutable();
        ranges_.set(index, builderForValue.build());
        onChanged();
      } else {
        rangesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder addRanges(alluxio.grpc.table.Range value) {
      if (rangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRangesIsMutable();
        ranges_.add(value);
        onChanged();
      } else {
        rangesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder addRanges(
        int index, alluxio.grpc.table.Range value) {
      if (rangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRangesIsMutable();
        ranges_.add(index, value);
        onChanged();
      } else {
        rangesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder addRanges(
        alluxio.grpc.table.Range.Builder builderForValue) {
      if (rangesBuilder_ == null) {
        ensureRangesIsMutable();
        ranges_.add(builderForValue.build());
        onChanged();
      } else {
        rangesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder addRanges(
        int index, alluxio.grpc.table.Range.Builder builderForValue) {
      if (rangesBuilder_ == null) {
        ensureRangesIsMutable();
        ranges_.add(index, builderForValue.build());
        onChanged();
      } else {
        rangesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder addAllRanges(
        java.lang.Iterable<? extends alluxio.grpc.table.Range> values) {
      if (rangesBuilder_ == null) {
        ensureRangesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, ranges_);
        onChanged();
      } else {
        rangesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder clearRanges() {
      if (rangesBuilder_ == null) {
        ranges_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        rangesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public Builder removeRanges(int index) {
      if (rangesBuilder_ == null) {
        ensureRangesIsMutable();
        ranges_.remove(index);
        onChanged();
      } else {
        rangesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public alluxio.grpc.table.Range.Builder getRangesBuilder(
        int index) {
      return getRangesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public alluxio.grpc.table.RangeOrBuilder getRangesOrBuilder(
        int index) {
      if (rangesBuilder_ == null) {
        return ranges_.get(index);  } else {
        return rangesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public java.util.List<? extends alluxio.grpc.table.RangeOrBuilder> 
         getRangesOrBuilderList() {
      if (rangesBuilder_ != null) {
        return rangesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(ranges_);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public alluxio.grpc.table.Range.Builder addRangesBuilder() {
      return getRangesFieldBuilder().addBuilder(
          alluxio.grpc.table.Range.getDefaultInstance());
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public alluxio.grpc.table.Range.Builder addRangesBuilder(
        int index) {
      return getRangesFieldBuilder().addBuilder(
          index, alluxio.grpc.table.Range.getDefaultInstance());
    }
    /**
     * <code>repeated .alluxio.grpc.table.Range ranges = 1;</code>
     */
    public java.util.List<alluxio.grpc.table.Range.Builder> 
         getRangesBuilderList() {
      return getRangesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        alluxio.grpc.table.Range, alluxio.grpc.table.Range.Builder, alluxio.grpc.table.RangeOrBuilder> 
        getRangesFieldBuilder() {
      if (rangesBuilder_ == null) {
        rangesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            alluxio.grpc.table.Range, alluxio.grpc.table.Range.Builder, alluxio.grpc.table.RangeOrBuilder>(
                ranges_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        ranges_ = null;
      }
      return rangesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.table.RangeSet)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.table.RangeSet)
  private static final alluxio.grpc.table.RangeSet DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.table.RangeSet();
  }

  public static alluxio.grpc.table.RangeSet getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RangeSet>
      PARSER = new com.google.protobuf.AbstractParser<RangeSet>() {
    @java.lang.Override
    public RangeSet parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RangeSet(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RangeSet> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RangeSet> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public alluxio.grpc.table.RangeSet getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

