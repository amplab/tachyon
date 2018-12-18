// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sasl_server.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.SaslMessage}
 */
public  final class SaslMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.SaslMessage)
    SaslMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SaslMessage.newBuilder() to construct.
  private SaslMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SaslMessage() {
    messageType_ = 0;
    message_ = com.google.protobuf.ByteString.EMPTY;
    clientId_ = "";
    authenticationName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SaslMessage(
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
            int rawValue = input.readEnum();
            alluxio.grpc.SaslMessageType value = alluxio.grpc.SaslMessageType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(1, rawValue);
            } else {
              bitField0_ |= 0x00000001;
              messageType_ = rawValue;
            }
            break;
          }
          case 18: {
            bitField0_ |= 0x00000002;
            message_ = input.readBytes();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            clientId_ = bs;
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            authenticationName_ = bs;
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
    return alluxio.grpc.AuthenticationServerProto.internal_static_alluxio_grpc_SaslMessage_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.AuthenticationServerProto.internal_static_alluxio_grpc_SaslMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.SaslMessage.class, alluxio.grpc.SaslMessage.Builder.class);
  }

  private int bitField0_;
  public static final int MESSAGETYPE_FIELD_NUMBER = 1;
  private int messageType_;
  /**
   * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
   */
  public boolean hasMessageType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
   */
  public alluxio.grpc.SaslMessageType getMessageType() {
    alluxio.grpc.SaslMessageType result = alluxio.grpc.SaslMessageType.valueOf(messageType_);
    return result == null ? alluxio.grpc.SaslMessageType.CHALLENGE : result;
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString message_;
  /**
   * <code>optional bytes message = 2;</code>
   */
  public boolean hasMessage() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional bytes message = 2;</code>
   */
  public com.google.protobuf.ByteString getMessage() {
    return message_;
  }

  public static final int CLIENTID_FIELD_NUMBER = 3;
  private volatile java.lang.Object clientId_;
  /**
   * <code>optional string clientId = 3;</code>
   */
  public boolean hasClientId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string clientId = 3;</code>
   */
  public java.lang.String getClientId() {
    java.lang.Object ref = clientId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        clientId_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string clientId = 3;</code>
   */
  public com.google.protobuf.ByteString
      getClientIdBytes() {
    java.lang.Object ref = clientId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      clientId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AUTHENTICATIONNAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object authenticationName_;
  /**
   * <code>optional string authenticationName = 4;</code>
   */
  public boolean hasAuthenticationName() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional string authenticationName = 4;</code>
   */
  public java.lang.String getAuthenticationName() {
    java.lang.Object ref = authenticationName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        authenticationName_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string authenticationName = 4;</code>
   */
  public com.google.protobuf.ByteString
      getAuthenticationNameBytes() {
    java.lang.Object ref = authenticationName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      authenticationName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
      output.writeEnum(1, messageType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, message_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, clientId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, authenticationName_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, messageType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, message_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, clientId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, authenticationName_);
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
    if (!(obj instanceof alluxio.grpc.SaslMessage)) {
      return super.equals(obj);
    }
    alluxio.grpc.SaslMessage other = (alluxio.grpc.SaslMessage) obj;

    boolean result = true;
    result = result && (hasMessageType() == other.hasMessageType());
    if (hasMessageType()) {
      result = result && messageType_ == other.messageType_;
    }
    result = result && (hasMessage() == other.hasMessage());
    if (hasMessage()) {
      result = result && getMessage()
          .equals(other.getMessage());
    }
    result = result && (hasClientId() == other.hasClientId());
    if (hasClientId()) {
      result = result && getClientId()
          .equals(other.getClientId());
    }
    result = result && (hasAuthenticationName() == other.hasAuthenticationName());
    if (hasAuthenticationName()) {
      result = result && getAuthenticationName()
          .equals(other.getAuthenticationName());
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
    if (hasMessageType()) {
      hash = (37 * hash) + MESSAGETYPE_FIELD_NUMBER;
      hash = (53 * hash) + messageType_;
    }
    if (hasMessage()) {
      hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getMessage().hashCode();
    }
    if (hasClientId()) {
      hash = (37 * hash) + CLIENTID_FIELD_NUMBER;
      hash = (53 * hash) + getClientId().hashCode();
    }
    if (hasAuthenticationName()) {
      hash = (37 * hash) + AUTHENTICATIONNAME_FIELD_NUMBER;
      hash = (53 * hash) + getAuthenticationName().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.SaslMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.SaslMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.SaslMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.SaslMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.SaslMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.SaslMessage parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.SaslMessage prototype) {
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
   * Protobuf type {@code alluxio.grpc.SaslMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.SaslMessage)
      alluxio.grpc.SaslMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.AuthenticationServerProto.internal_static_alluxio_grpc_SaslMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.AuthenticationServerProto.internal_static_alluxio_grpc_SaslMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.SaslMessage.class, alluxio.grpc.SaslMessage.Builder.class);
    }

    // Construct using alluxio.grpc.SaslMessage.newBuilder()
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
      messageType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      message_ = com.google.protobuf.ByteString.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      clientId_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      authenticationName_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.AuthenticationServerProto.internal_static_alluxio_grpc_SaslMessage_descriptor;
    }

    public alluxio.grpc.SaslMessage getDefaultInstanceForType() {
      return alluxio.grpc.SaslMessage.getDefaultInstance();
    }

    public alluxio.grpc.SaslMessage build() {
      alluxio.grpc.SaslMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.SaslMessage buildPartial() {
      alluxio.grpc.SaslMessage result = new alluxio.grpc.SaslMessage(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.messageType_ = messageType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.message_ = message_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.clientId_ = clientId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.authenticationName_ = authenticationName_;
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
      if (other instanceof alluxio.grpc.SaslMessage) {
        return mergeFrom((alluxio.grpc.SaslMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.SaslMessage other) {
      if (other == alluxio.grpc.SaslMessage.getDefaultInstance()) return this;
      if (other.hasMessageType()) {
        setMessageType(other.getMessageType());
      }
      if (other.hasMessage()) {
        setMessage(other.getMessage());
      }
      if (other.hasClientId()) {
        bitField0_ |= 0x00000004;
        clientId_ = other.clientId_;
        onChanged();
      }
      if (other.hasAuthenticationName()) {
        bitField0_ |= 0x00000008;
        authenticationName_ = other.authenticationName_;
        onChanged();
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
      alluxio.grpc.SaslMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.SaslMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int messageType_ = 0;
    /**
     * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
     */
    public boolean hasMessageType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
     */
    public alluxio.grpc.SaslMessageType getMessageType() {
      alluxio.grpc.SaslMessageType result = alluxio.grpc.SaslMessageType.valueOf(messageType_);
      return result == null ? alluxio.grpc.SaslMessageType.CHALLENGE : result;
    }
    /**
     * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
     */
    public Builder setMessageType(alluxio.grpc.SaslMessageType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
      messageType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.SaslMessageType messageType = 1;</code>
     */
    public Builder clearMessageType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      messageType_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString message_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes message = 2;</code>
     */
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bytes message = 2;</code>
     */
    public com.google.protobuf.ByteString getMessage() {
      return message_;
    }
    /**
     * <code>optional bytes message = 2;</code>
     */
    public Builder setMessage(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bytes message = 2;</code>
     */
    public Builder clearMessage() {
      bitField0_ = (bitField0_ & ~0x00000002);
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }

    private java.lang.Object clientId_ = "";
    /**
     * <code>optional string clientId = 3;</code>
     */
    public boolean hasClientId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string clientId = 3;</code>
     */
    public java.lang.String getClientId() {
      java.lang.Object ref = clientId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          clientId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string clientId = 3;</code>
     */
    public com.google.protobuf.ByteString
        getClientIdBytes() {
      java.lang.Object ref = clientId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        clientId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string clientId = 3;</code>
     */
    public Builder setClientId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      clientId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string clientId = 3;</code>
     */
    public Builder clearClientId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      clientId_ = getDefaultInstance().getClientId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string clientId = 3;</code>
     */
    public Builder setClientIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      clientId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object authenticationName_ = "";
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public boolean hasAuthenticationName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public java.lang.String getAuthenticationName() {
      java.lang.Object ref = authenticationName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          authenticationName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAuthenticationNameBytes() {
      java.lang.Object ref = authenticationName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        authenticationName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public Builder setAuthenticationName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      authenticationName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public Builder clearAuthenticationName() {
      bitField0_ = (bitField0_ & ~0x00000008);
      authenticationName_ = getDefaultInstance().getAuthenticationName();
      onChanged();
      return this;
    }
    /**
     * <code>optional string authenticationName = 4;</code>
     */
    public Builder setAuthenticationNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      authenticationName_ = value;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.SaslMessage)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.SaslMessage)
  private static final alluxio.grpc.SaslMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.SaslMessage();
  }

  public static alluxio.grpc.SaslMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SaslMessage>
      PARSER = new com.google.protobuf.AbstractParser<SaslMessage>() {
    public SaslMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SaslMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SaslMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SaslMessage> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.SaslMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

