// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.file.GetFileInfoPRequest}
 */
public  final class GetFileInfoPRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.file.GetFileInfoPRequest)
    GetFileInfoPRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetFileInfoPRequest.newBuilder() to construct.
  private GetFileInfoPRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetFileInfoPRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetFileInfoPRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetFileInfoPRequest(
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
            fileId_ = input.readInt64();
            break;
          }
          case 18: {
            alluxio.grpc.GetFileInfoPOptions.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) != 0)) {
              subBuilder = options_.toBuilder();
            }
            options_ = input.readMessage(alluxio.grpc.GetFileInfoPOptions.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(options_);
              options_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetFileInfoPRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetFileInfoPRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.GetFileInfoPRequest.class, alluxio.grpc.GetFileInfoPRequest.Builder.class);
  }

  private int bitField0_;
  public static final int FILEID_FIELD_NUMBER = 1;
  private long fileId_;
  /**
   * <pre>
   ** the id of the file 
   * </pre>
   *
   * <code>optional int64 fileId = 1;</code>
   * @return Whether the fileId field is set.
   */
  public boolean hasFileId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <pre>
   ** the id of the file 
   * </pre>
   *
   * <code>optional int64 fileId = 1;</code>
   * @return The fileId.
   */
  public long getFileId() {
    return fileId_;
  }

  public static final int OPTIONS_FIELD_NUMBER = 2;
  private alluxio.grpc.GetFileInfoPOptions options_;
  /**
   * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
   * @return Whether the options field is set.
   */
  public boolean hasOptions() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
   * @return The options.
   */
  public alluxio.grpc.GetFileInfoPOptions getOptions() {
    return options_ == null ? alluxio.grpc.GetFileInfoPOptions.getDefaultInstance() : options_;
  }
  /**
   * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
   */
  public alluxio.grpc.GetFileInfoPOptionsOrBuilder getOptionsOrBuilder() {
    return options_ == null ? alluxio.grpc.GetFileInfoPOptions.getDefaultInstance() : options_;
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
      output.writeInt64(1, fileId_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(2, getOptions());
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
        .computeInt64Size(1, fileId_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getOptions());
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
    if (!(obj instanceof alluxio.grpc.GetFileInfoPRequest)) {
      return super.equals(obj);
    }
    alluxio.grpc.GetFileInfoPRequest other = (alluxio.grpc.GetFileInfoPRequest) obj;

    if (hasFileId() != other.hasFileId()) return false;
    if (hasFileId()) {
      if (getFileId()
          != other.getFileId()) return false;
    }
    if (hasOptions() != other.hasOptions()) return false;
    if (hasOptions()) {
      if (!getOptions()
          .equals(other.getOptions())) return false;
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
    if (hasFileId()) {
      hash = (37 * hash) + FILEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getFileId());
    }
    if (hasOptions()) {
      hash = (37 * hash) + OPTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getOptions().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetFileInfoPRequest parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.GetFileInfoPRequest prototype) {
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
   * Protobuf type {@code alluxio.grpc.file.GetFileInfoPRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.file.GetFileInfoPRequest)
      alluxio.grpc.GetFileInfoPRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetFileInfoPRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetFileInfoPRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.GetFileInfoPRequest.class, alluxio.grpc.GetFileInfoPRequest.Builder.class);
    }

    // Construct using alluxio.grpc.GetFileInfoPRequest.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      fileId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (optionsBuilder_ == null) {
        options_ = null;
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetFileInfoPRequest_descriptor;
    }

    @java.lang.Override
    public alluxio.grpc.GetFileInfoPRequest getDefaultInstanceForType() {
      return alluxio.grpc.GetFileInfoPRequest.getDefaultInstance();
    }

    @java.lang.Override
    public alluxio.grpc.GetFileInfoPRequest build() {
      alluxio.grpc.GetFileInfoPRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public alluxio.grpc.GetFileInfoPRequest buildPartial() {
      alluxio.grpc.GetFileInfoPRequest result = new alluxio.grpc.GetFileInfoPRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.fileId_ = fileId_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        if (optionsBuilder_ == null) {
          result.options_ = options_;
        } else {
          result.options_ = optionsBuilder_.build();
        }
        to_bitField0_ |= 0x00000002;
      }
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
      if (other instanceof alluxio.grpc.GetFileInfoPRequest) {
        return mergeFrom((alluxio.grpc.GetFileInfoPRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.GetFileInfoPRequest other) {
      if (other == alluxio.grpc.GetFileInfoPRequest.getDefaultInstance()) return this;
      if (other.hasFileId()) {
        setFileId(other.getFileId());
      }
      if (other.hasOptions()) {
        mergeOptions(other.getOptions());
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
      alluxio.grpc.GetFileInfoPRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.GetFileInfoPRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long fileId_ ;
    /**
     * <pre>
     ** the id of the file 
     * </pre>
     *
     * <code>optional int64 fileId = 1;</code>
     * @return Whether the fileId field is set.
     */
    public boolean hasFileId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     ** the id of the file 
     * </pre>
     *
     * <code>optional int64 fileId = 1;</code>
     * @return The fileId.
     */
    public long getFileId() {
      return fileId_;
    }
    /**
     * <pre>
     ** the id of the file 
     * </pre>
     *
     * <code>optional int64 fileId = 1;</code>
     * @param value The fileId to set.
     * @return This builder for chaining.
     */
    public Builder setFileId(long value) {
      bitField0_ |= 0x00000001;
      fileId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the id of the file 
     * </pre>
     *
     * <code>optional int64 fileId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFileId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      fileId_ = 0L;
      onChanged();
      return this;
    }

    private alluxio.grpc.GetFileInfoPOptions options_;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.GetFileInfoPOptions, alluxio.grpc.GetFileInfoPOptions.Builder, alluxio.grpc.GetFileInfoPOptionsOrBuilder> optionsBuilder_;
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     * @return Whether the options field is set.
     */
    public boolean hasOptions() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     * @return The options.
     */
    public alluxio.grpc.GetFileInfoPOptions getOptions() {
      if (optionsBuilder_ == null) {
        return options_ == null ? alluxio.grpc.GetFileInfoPOptions.getDefaultInstance() : options_;
      } else {
        return optionsBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public Builder setOptions(alluxio.grpc.GetFileInfoPOptions value) {
      if (optionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        options_ = value;
        onChanged();
      } else {
        optionsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public Builder setOptions(
        alluxio.grpc.GetFileInfoPOptions.Builder builderForValue) {
      if (optionsBuilder_ == null) {
        options_ = builderForValue.build();
        onChanged();
      } else {
        optionsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public Builder mergeOptions(alluxio.grpc.GetFileInfoPOptions value) {
      if (optionsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
            options_ != null &&
            options_ != alluxio.grpc.GetFileInfoPOptions.getDefaultInstance()) {
          options_ =
            alluxio.grpc.GetFileInfoPOptions.newBuilder(options_).mergeFrom(value).buildPartial();
        } else {
          options_ = value;
        }
        onChanged();
      } else {
        optionsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public Builder clearOptions() {
      if (optionsBuilder_ == null) {
        options_ = null;
        onChanged();
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public alluxio.grpc.GetFileInfoPOptions.Builder getOptionsBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getOptionsFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    public alluxio.grpc.GetFileInfoPOptionsOrBuilder getOptionsOrBuilder() {
      if (optionsBuilder_ != null) {
        return optionsBuilder_.getMessageOrBuilder();
      } else {
        return options_ == null ?
            alluxio.grpc.GetFileInfoPOptions.getDefaultInstance() : options_;
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.GetFileInfoPOptions options = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.GetFileInfoPOptions, alluxio.grpc.GetFileInfoPOptions.Builder, alluxio.grpc.GetFileInfoPOptionsOrBuilder> 
        getOptionsFieldBuilder() {
      if (optionsBuilder_ == null) {
        optionsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.GetFileInfoPOptions, alluxio.grpc.GetFileInfoPOptions.Builder, alluxio.grpc.GetFileInfoPOptionsOrBuilder>(
                getOptions(),
                getParentForChildren(),
                isClean());
        options_ = null;
      }
      return optionsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.file.GetFileInfoPRequest)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.file.GetFileInfoPRequest)
  private static final alluxio.grpc.GetFileInfoPRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.GetFileInfoPRequest();
  }

  public static alluxio.grpc.GetFileInfoPRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetFileInfoPRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetFileInfoPRequest>() {
    @java.lang.Override
    public GetFileInfoPRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetFileInfoPRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetFileInfoPRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetFileInfoPRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public alluxio.grpc.GetFileInfoPRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

