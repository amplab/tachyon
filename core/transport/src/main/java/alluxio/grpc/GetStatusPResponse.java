// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.file.GetStatusPResponse}
 */
public  final class GetStatusPResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.file.GetStatusPResponse)
    GetStatusPResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetStatusPResponse.newBuilder() to construct.
  private GetStatusPResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetStatusPResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetStatusPResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetStatusPResponse(
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
            alluxio.grpc.FileInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) != 0)) {
              subBuilder = fileInfo_.toBuilder();
            }
            fileInfo_ = input.readMessage(alluxio.grpc.FileInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(fileInfo_);
              fileInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetStatusPResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetStatusPResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.GetStatusPResponse.class, alluxio.grpc.GetStatusPResponse.Builder.class);
  }

  private int bitField0_;
  public static final int FILEINFO_FIELD_NUMBER = 1;
  private alluxio.grpc.FileInfo fileInfo_;
  /**
   * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
   * @return Whether the fileInfo field is set.
   */
  public boolean hasFileInfo() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
   * @return The fileInfo.
   */
  public alluxio.grpc.FileInfo getFileInfo() {
    return fileInfo_ == null ? alluxio.grpc.FileInfo.getDefaultInstance() : fileInfo_;
  }
  /**
   * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
   */
  public alluxio.grpc.FileInfoOrBuilder getFileInfoOrBuilder() {
    return fileInfo_ == null ? alluxio.grpc.FileInfo.getDefaultInstance() : fileInfo_;
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
      output.writeMessage(1, getFileInfo());
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
        .computeMessageSize(1, getFileInfo());
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
    if (!(obj instanceof alluxio.grpc.GetStatusPResponse)) {
      return super.equals(obj);
    }
    alluxio.grpc.GetStatusPResponse other = (alluxio.grpc.GetStatusPResponse) obj;

    if (hasFileInfo() != other.hasFileInfo()) return false;
    if (hasFileInfo()) {
      if (!getFileInfo()
          .equals(other.getFileInfo())) return false;
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
    if (hasFileInfo()) {
      hash = (37 * hash) + FILEINFO_FIELD_NUMBER;
      hash = (53 * hash) + getFileInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.GetStatusPResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetStatusPResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetStatusPResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetStatusPResponse parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.GetStatusPResponse prototype) {
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
   * Protobuf type {@code alluxio.grpc.file.GetStatusPResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.file.GetStatusPResponse)
      alluxio.grpc.GetStatusPResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetStatusPResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetStatusPResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.GetStatusPResponse.class, alluxio.grpc.GetStatusPResponse.Builder.class);
    }

    // Construct using alluxio.grpc.GetStatusPResponse.newBuilder()
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
        getFileInfoFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (fileInfoBuilder_ == null) {
        fileInfo_ = null;
      } else {
        fileInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetStatusPResponse_descriptor;
    }

    @java.lang.Override
    public alluxio.grpc.GetStatusPResponse getDefaultInstanceForType() {
      return alluxio.grpc.GetStatusPResponse.getDefaultInstance();
    }

    @java.lang.Override
    public alluxio.grpc.GetStatusPResponse build() {
      alluxio.grpc.GetStatusPResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public alluxio.grpc.GetStatusPResponse buildPartial() {
      alluxio.grpc.GetStatusPResponse result = new alluxio.grpc.GetStatusPResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        if (fileInfoBuilder_ == null) {
          result.fileInfo_ = fileInfo_;
        } else {
          result.fileInfo_ = fileInfoBuilder_.build();
        }
        to_bitField0_ |= 0x00000001;
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
      if (other instanceof alluxio.grpc.GetStatusPResponse) {
        return mergeFrom((alluxio.grpc.GetStatusPResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.GetStatusPResponse other) {
      if (other == alluxio.grpc.GetStatusPResponse.getDefaultInstance()) return this;
      if (other.hasFileInfo()) {
        mergeFileInfo(other.getFileInfo());
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
      alluxio.grpc.GetStatusPResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.GetStatusPResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private alluxio.grpc.FileInfo fileInfo_;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileInfo, alluxio.grpc.FileInfo.Builder, alluxio.grpc.FileInfoOrBuilder> fileInfoBuilder_;
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     * @return Whether the fileInfo field is set.
     */
    public boolean hasFileInfo() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     * @return The fileInfo.
     */
    public alluxio.grpc.FileInfo getFileInfo() {
      if (fileInfoBuilder_ == null) {
        return fileInfo_ == null ? alluxio.grpc.FileInfo.getDefaultInstance() : fileInfo_;
      } else {
        return fileInfoBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public Builder setFileInfo(alluxio.grpc.FileInfo value) {
      if (fileInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        fileInfo_ = value;
        onChanged();
      } else {
        fileInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public Builder setFileInfo(
        alluxio.grpc.FileInfo.Builder builderForValue) {
      if (fileInfoBuilder_ == null) {
        fileInfo_ = builderForValue.build();
        onChanged();
      } else {
        fileInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public Builder mergeFileInfo(alluxio.grpc.FileInfo value) {
      if (fileInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            fileInfo_ != null &&
            fileInfo_ != alluxio.grpc.FileInfo.getDefaultInstance()) {
          fileInfo_ =
            alluxio.grpc.FileInfo.newBuilder(fileInfo_).mergeFrom(value).buildPartial();
        } else {
          fileInfo_ = value;
        }
        onChanged();
      } else {
        fileInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public Builder clearFileInfo() {
      if (fileInfoBuilder_ == null) {
        fileInfo_ = null;
        onChanged();
      } else {
        fileInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public alluxio.grpc.FileInfo.Builder getFileInfoBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getFileInfoFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    public alluxio.grpc.FileInfoOrBuilder getFileInfoOrBuilder() {
      if (fileInfoBuilder_ != null) {
        return fileInfoBuilder_.getMessageOrBuilder();
      } else {
        return fileInfo_ == null ?
            alluxio.grpc.FileInfo.getDefaultInstance() : fileInfo_;
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.FileInfo fileInfo = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileInfo, alluxio.grpc.FileInfo.Builder, alluxio.grpc.FileInfoOrBuilder> 
        getFileInfoFieldBuilder() {
      if (fileInfoBuilder_ == null) {
        fileInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.FileInfo, alluxio.grpc.FileInfo.Builder, alluxio.grpc.FileInfoOrBuilder>(
                getFileInfo(),
                getParentForChildren(),
                isClean());
        fileInfo_ = null;
      }
      return fileInfoBuilder_;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.file.GetStatusPResponse)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.file.GetStatusPResponse)
  private static final alluxio.grpc.GetStatusPResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.GetStatusPResponse();
  }

  public static alluxio.grpc.GetStatusPResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetStatusPResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetStatusPResponse>() {
    @java.lang.Override
    public GetStatusPResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetStatusPResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetStatusPResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetStatusPResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public alluxio.grpc.GetStatusPResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

