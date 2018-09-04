// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: file_system_worker.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.CloseUfsFilePRequest}
 */
public  final class CloseUfsFilePRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.CloseUfsFilePRequest)
    CloseUfsFilePRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CloseUfsFilePRequest.newBuilder() to construct.
  private CloseUfsFilePRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CloseUfsFilePRequest() {
    sessionId_ = 0L;
    tempUfsFileId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CloseUfsFilePRequest(
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
            sessionId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            tempUfsFileId_ = input.readInt64();
            break;
          }
          case 26: {
            alluxio.grpc.CloseUfsFilePOptions.Builder subBuilder = null;
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
              subBuilder = options_.toBuilder();
            }
            options_ = input.readMessage(alluxio.grpc.CloseUfsFilePOptions.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(options_);
              options_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000004;
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
    return alluxio.grpc.FileSystemWorkerProto.internal_static_alluxio_grpc_CloseUfsFilePRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemWorkerProto.internal_static_alluxio_grpc_CloseUfsFilePRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.CloseUfsFilePRequest.class, alluxio.grpc.CloseUfsFilePRequest.Builder.class);
  }

  private int bitField0_;
  public static final int SESSIONID_FIELD_NUMBER = 1;
  private long sessionId_;
  /**
   * <pre>
   ** the id of the current session 
   * </pre>
   *
   * <code>optional int64 sessionId = 1;</code>
   */
  public boolean hasSessionId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   ** the id of the current session 
   * </pre>
   *
   * <code>optional int64 sessionId = 1;</code>
   */
  public long getSessionId() {
    return sessionId_;
  }

  public static final int TEMPUFSFILEID_FIELD_NUMBER = 2;
  private long tempUfsFileId_;
  /**
   * <pre>
   ** the worker specific file id of the ufs file 
   * </pre>
   *
   * <code>optional int64 tempUfsFileId = 2;</code>
   */
  public boolean hasTempUfsFileId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   ** the worker specific file id of the ufs file 
   * </pre>
   *
   * <code>optional int64 tempUfsFileId = 2;</code>
   */
  public long getTempUfsFileId() {
    return tempUfsFileId_;
  }

  public static final int OPTIONS_FIELD_NUMBER = 3;
  private alluxio.grpc.CloseUfsFilePOptions options_;
  /**
   * <pre>
   ** the options for closing the file 
   * </pre>
   *
   * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
   */
  public boolean hasOptions() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   ** the options for closing the file 
   * </pre>
   *
   * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
   */
  public alluxio.grpc.CloseUfsFilePOptions getOptions() {
    return options_ == null ? alluxio.grpc.CloseUfsFilePOptions.getDefaultInstance() : options_;
  }
  /**
   * <pre>
   ** the options for closing the file 
   * </pre>
   *
   * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
   */
  public alluxio.grpc.CloseUfsFilePOptionsOrBuilder getOptionsOrBuilder() {
    return options_ == null ? alluxio.grpc.CloseUfsFilePOptions.getDefaultInstance() : options_;
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
      output.writeInt64(1, sessionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, tempUfsFileId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeMessage(3, getOptions());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, sessionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, tempUfsFileId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getOptions());
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
    if (!(obj instanceof alluxio.grpc.CloseUfsFilePRequest)) {
      return super.equals(obj);
    }
    alluxio.grpc.CloseUfsFilePRequest other = (alluxio.grpc.CloseUfsFilePRequest) obj;

    boolean result = true;
    result = result && (hasSessionId() == other.hasSessionId());
    if (hasSessionId()) {
      result = result && (getSessionId()
          == other.getSessionId());
    }
    result = result && (hasTempUfsFileId() == other.hasTempUfsFileId());
    if (hasTempUfsFileId()) {
      result = result && (getTempUfsFileId()
          == other.getTempUfsFileId());
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
    if (hasSessionId()) {
      hash = (37 * hash) + SESSIONID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSessionId());
    }
    if (hasTempUfsFileId()) {
      hash = (37 * hash) + TEMPUFSFILEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTempUfsFileId());
    }
    if (hasOptions()) {
      hash = (37 * hash) + OPTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getOptions().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.CloseUfsFilePRequest parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.CloseUfsFilePRequest prototype) {
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
   * Protobuf type {@code alluxio.grpc.CloseUfsFilePRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.CloseUfsFilePRequest)
      alluxio.grpc.CloseUfsFilePRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemWorkerProto.internal_static_alluxio_grpc_CloseUfsFilePRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemWorkerProto.internal_static_alluxio_grpc_CloseUfsFilePRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.CloseUfsFilePRequest.class, alluxio.grpc.CloseUfsFilePRequest.Builder.class);
    }

    // Construct using alluxio.grpc.CloseUfsFilePRequest.newBuilder()
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
      sessionId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      tempUfsFileId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (optionsBuilder_ == null) {
        options_ = null;
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemWorkerProto.internal_static_alluxio_grpc_CloseUfsFilePRequest_descriptor;
    }

    public alluxio.grpc.CloseUfsFilePRequest getDefaultInstanceForType() {
      return alluxio.grpc.CloseUfsFilePRequest.getDefaultInstance();
    }

    public alluxio.grpc.CloseUfsFilePRequest build() {
      alluxio.grpc.CloseUfsFilePRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.CloseUfsFilePRequest buildPartial() {
      alluxio.grpc.CloseUfsFilePRequest result = new alluxio.grpc.CloseUfsFilePRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.sessionId_ = sessionId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.tempUfsFileId_ = tempUfsFileId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
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
      if (other instanceof alluxio.grpc.CloseUfsFilePRequest) {
        return mergeFrom((alluxio.grpc.CloseUfsFilePRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.CloseUfsFilePRequest other) {
      if (other == alluxio.grpc.CloseUfsFilePRequest.getDefaultInstance()) return this;
      if (other.hasSessionId()) {
        setSessionId(other.getSessionId());
      }
      if (other.hasTempUfsFileId()) {
        setTempUfsFileId(other.getTempUfsFileId());
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
      alluxio.grpc.CloseUfsFilePRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.CloseUfsFilePRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long sessionId_ ;
    /**
     * <pre>
     ** the id of the current session 
     * </pre>
     *
     * <code>optional int64 sessionId = 1;</code>
     */
    public boolean hasSessionId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     ** the id of the current session 
     * </pre>
     *
     * <code>optional int64 sessionId = 1;</code>
     */
    public long getSessionId() {
      return sessionId_;
    }
    /**
     * <pre>
     ** the id of the current session 
     * </pre>
     *
     * <code>optional int64 sessionId = 1;</code>
     */
    public Builder setSessionId(long value) {
      bitField0_ |= 0x00000001;
      sessionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the id of the current session 
     * </pre>
     *
     * <code>optional int64 sessionId = 1;</code>
     */
    public Builder clearSessionId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      sessionId_ = 0L;
      onChanged();
      return this;
    }

    private long tempUfsFileId_ ;
    /**
     * <pre>
     ** the worker specific file id of the ufs file 
     * </pre>
     *
     * <code>optional int64 tempUfsFileId = 2;</code>
     */
    public boolean hasTempUfsFileId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     ** the worker specific file id of the ufs file 
     * </pre>
     *
     * <code>optional int64 tempUfsFileId = 2;</code>
     */
    public long getTempUfsFileId() {
      return tempUfsFileId_;
    }
    /**
     * <pre>
     ** the worker specific file id of the ufs file 
     * </pre>
     *
     * <code>optional int64 tempUfsFileId = 2;</code>
     */
    public Builder setTempUfsFileId(long value) {
      bitField0_ |= 0x00000002;
      tempUfsFileId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     ** the worker specific file id of the ufs file 
     * </pre>
     *
     * <code>optional int64 tempUfsFileId = 2;</code>
     */
    public Builder clearTempUfsFileId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      tempUfsFileId_ = 0L;
      onChanged();
      return this;
    }

    private alluxio.grpc.CloseUfsFilePOptions options_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.CloseUfsFilePOptions, alluxio.grpc.CloseUfsFilePOptions.Builder, alluxio.grpc.CloseUfsFilePOptionsOrBuilder> optionsBuilder_;
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public boolean hasOptions() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public alluxio.grpc.CloseUfsFilePOptions getOptions() {
      if (optionsBuilder_ == null) {
        return options_ == null ? alluxio.grpc.CloseUfsFilePOptions.getDefaultInstance() : options_;
      } else {
        return optionsBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public Builder setOptions(alluxio.grpc.CloseUfsFilePOptions value) {
      if (optionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        options_ = value;
        onChanged();
      } else {
        optionsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public Builder setOptions(
        alluxio.grpc.CloseUfsFilePOptions.Builder builderForValue) {
      if (optionsBuilder_ == null) {
        options_ = builderForValue.build();
        onChanged();
      } else {
        optionsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public Builder mergeOptions(alluxio.grpc.CloseUfsFilePOptions value) {
      if (optionsBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004) &&
            options_ != null &&
            options_ != alluxio.grpc.CloseUfsFilePOptions.getDefaultInstance()) {
          options_ =
            alluxio.grpc.CloseUfsFilePOptions.newBuilder(options_).mergeFrom(value).buildPartial();
        } else {
          options_ = value;
        }
        onChanged();
      } else {
        optionsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public Builder clearOptions() {
      if (optionsBuilder_ == null) {
        options_ = null;
        onChanged();
      } else {
        optionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public alluxio.grpc.CloseUfsFilePOptions.Builder getOptionsBuilder() {
      bitField0_ |= 0x00000004;
      onChanged();
      return getOptionsFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    public alluxio.grpc.CloseUfsFilePOptionsOrBuilder getOptionsOrBuilder() {
      if (optionsBuilder_ != null) {
        return optionsBuilder_.getMessageOrBuilder();
      } else {
        return options_ == null ?
            alluxio.grpc.CloseUfsFilePOptions.getDefaultInstance() : options_;
      }
    }
    /**
     * <pre>
     ** the options for closing the file 
     * </pre>
     *
     * <code>optional .alluxio.grpc.CloseUfsFilePOptions options = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.CloseUfsFilePOptions, alluxio.grpc.CloseUfsFilePOptions.Builder, alluxio.grpc.CloseUfsFilePOptionsOrBuilder> 
        getOptionsFieldBuilder() {
      if (optionsBuilder_ == null) {
        optionsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.CloseUfsFilePOptions, alluxio.grpc.CloseUfsFilePOptions.Builder, alluxio.grpc.CloseUfsFilePOptionsOrBuilder>(
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.CloseUfsFilePRequest)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.CloseUfsFilePRequest)
  private static final alluxio.grpc.CloseUfsFilePRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.CloseUfsFilePRequest();
  }

  public static alluxio.grpc.CloseUfsFilePRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CloseUfsFilePRequest>
      PARSER = new com.google.protobuf.AbstractParser<CloseUfsFilePRequest>() {
    public CloseUfsFilePRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CloseUfsFilePRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CloseUfsFilePRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CloseUfsFilePRequest> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.CloseUfsFilePRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

