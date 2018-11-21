// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: block_master_options.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.RegisterWorkerPOptions}
 */
public  final class RegisterWorkerPOptions extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.RegisterWorkerPOptions)
    RegisterWorkerPOptionsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RegisterWorkerPOptions.newBuilder() to construct.
  private RegisterWorkerPOptions(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RegisterWorkerPOptions() {
    config_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RegisterWorkerPOptions(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              config_ = new java.util.ArrayList<alluxio.grpc.ConfigProperty>();
              mutable_bitField0_ |= 0x00000001;
            }
            config_.add(
                input.readMessage(alluxio.grpc.ConfigProperty.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        config_ = java.util.Collections.unmodifiableList(config_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return alluxio.grpc.BlockMasterProtoOptions.internal_static_alluxio_grpc_RegisterWorkerPOptions_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.BlockMasterProtoOptions.internal_static_alluxio_grpc_RegisterWorkerPOptions_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.RegisterWorkerPOptions.class, alluxio.grpc.RegisterWorkerPOptions.Builder.class);
  }

  public static final int CONFIG_FIELD_NUMBER = 1;
  private java.util.List<alluxio.grpc.ConfigProperty> config_;
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
   */
  public java.util.List<alluxio.grpc.ConfigProperty> getConfigList() {
    return config_;
  }
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
   */
  public java.util.List<? extends alluxio.grpc.ConfigPropertyOrBuilder> 
      getConfigOrBuilderList() {
    return config_;
  }
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
   */
  public int getConfigCount() {
    return config_.size();
  }
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
   */
  public alluxio.grpc.ConfigProperty getConfig(int index) {
    return config_.get(index);
  }
  /**
   * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
   */
  public alluxio.grpc.ConfigPropertyOrBuilder getConfigOrBuilder(
      int index) {
    return config_.get(index);
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
    for (int i = 0; i < config_.size(); i++) {
      output.writeMessage(1, config_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < config_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, config_.get(i));
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
    if (!(obj instanceof alluxio.grpc.RegisterWorkerPOptions)) {
      return super.equals(obj);
    }
    alluxio.grpc.RegisterWorkerPOptions other = (alluxio.grpc.RegisterWorkerPOptions) obj;

    boolean result = true;
    result = result && getConfigList()
        .equals(other.getConfigList());
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
    if (getConfigCount() > 0) {
      hash = (37 * hash) + CONFIG_FIELD_NUMBER;
      hash = (53 * hash) + getConfigList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.RegisterWorkerPOptions parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.RegisterWorkerPOptions prototype) {
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
   * Protobuf type {@code alluxio.grpc.RegisterWorkerPOptions}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.RegisterWorkerPOptions)
      alluxio.grpc.RegisterWorkerPOptionsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.BlockMasterProtoOptions.internal_static_alluxio_grpc_RegisterWorkerPOptions_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.BlockMasterProtoOptions.internal_static_alluxio_grpc_RegisterWorkerPOptions_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.RegisterWorkerPOptions.class, alluxio.grpc.RegisterWorkerPOptions.Builder.class);
    }

    // Construct using alluxio.grpc.RegisterWorkerPOptions.newBuilder()
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
        getConfigFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (configBuilder_ == null) {
        config_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        configBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.BlockMasterProtoOptions.internal_static_alluxio_grpc_RegisterWorkerPOptions_descriptor;
    }

    public alluxio.grpc.RegisterWorkerPOptions getDefaultInstanceForType() {
      return alluxio.grpc.RegisterWorkerPOptions.getDefaultInstance();
    }

    public alluxio.grpc.RegisterWorkerPOptions build() {
      alluxio.grpc.RegisterWorkerPOptions result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.RegisterWorkerPOptions buildPartial() {
      alluxio.grpc.RegisterWorkerPOptions result = new alluxio.grpc.RegisterWorkerPOptions(this);
      int from_bitField0_ = bitField0_;
      if (configBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          config_ = java.util.Collections.unmodifiableList(config_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.config_ = config_;
      } else {
        result.config_ = configBuilder_.build();
      }
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
      if (other instanceof alluxio.grpc.RegisterWorkerPOptions) {
        return mergeFrom((alluxio.grpc.RegisterWorkerPOptions)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.RegisterWorkerPOptions other) {
      if (other == alluxio.grpc.RegisterWorkerPOptions.getDefaultInstance()) return this;
      if (configBuilder_ == null) {
        if (!other.config_.isEmpty()) {
          if (config_.isEmpty()) {
            config_ = other.config_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureConfigIsMutable();
            config_.addAll(other.config_);
          }
          onChanged();
        }
      } else {
        if (!other.config_.isEmpty()) {
          if (configBuilder_.isEmpty()) {
            configBuilder_.dispose();
            configBuilder_ = null;
            config_ = other.config_;
            bitField0_ = (bitField0_ & ~0x00000001);
            configBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getConfigFieldBuilder() : null;
          } else {
            configBuilder_.addAllMessages(other.config_);
          }
        }
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
      alluxio.grpc.RegisterWorkerPOptions parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.RegisterWorkerPOptions) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<alluxio.grpc.ConfigProperty> config_ =
      java.util.Collections.emptyList();
    private void ensureConfigIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        config_ = new java.util.ArrayList<alluxio.grpc.ConfigProperty>(config_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        alluxio.grpc.ConfigProperty, alluxio.grpc.ConfigProperty.Builder, alluxio.grpc.ConfigPropertyOrBuilder> configBuilder_;

    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public java.util.List<alluxio.grpc.ConfigProperty> getConfigList() {
      if (configBuilder_ == null) {
        return java.util.Collections.unmodifiableList(config_);
      } else {
        return configBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public int getConfigCount() {
      if (configBuilder_ == null) {
        return config_.size();
      } else {
        return configBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public alluxio.grpc.ConfigProperty getConfig(int index) {
      if (configBuilder_ == null) {
        return config_.get(index);
      } else {
        return configBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder setConfig(
        int index, alluxio.grpc.ConfigProperty value) {
      if (configBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConfigIsMutable();
        config_.set(index, value);
        onChanged();
      } else {
        configBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder setConfig(
        int index, alluxio.grpc.ConfigProperty.Builder builderForValue) {
      if (configBuilder_ == null) {
        ensureConfigIsMutable();
        config_.set(index, builderForValue.build());
        onChanged();
      } else {
        configBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder addConfig(alluxio.grpc.ConfigProperty value) {
      if (configBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConfigIsMutable();
        config_.add(value);
        onChanged();
      } else {
        configBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder addConfig(
        int index, alluxio.grpc.ConfigProperty value) {
      if (configBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConfigIsMutable();
        config_.add(index, value);
        onChanged();
      } else {
        configBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder addConfig(
        alluxio.grpc.ConfigProperty.Builder builderForValue) {
      if (configBuilder_ == null) {
        ensureConfigIsMutable();
        config_.add(builderForValue.build());
        onChanged();
      } else {
        configBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder addConfig(
        int index, alluxio.grpc.ConfigProperty.Builder builderForValue) {
      if (configBuilder_ == null) {
        ensureConfigIsMutable();
        config_.add(index, builderForValue.build());
        onChanged();
      } else {
        configBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder addAllConfig(
        java.lang.Iterable<? extends alluxio.grpc.ConfigProperty> values) {
      if (configBuilder_ == null) {
        ensureConfigIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, config_);
        onChanged();
      } else {
        configBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder clearConfig() {
      if (configBuilder_ == null) {
        config_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        configBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public Builder removeConfig(int index) {
      if (configBuilder_ == null) {
        ensureConfigIsMutable();
        config_.remove(index);
        onChanged();
      } else {
        configBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public alluxio.grpc.ConfigProperty.Builder getConfigBuilder(
        int index) {
      return getConfigFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public alluxio.grpc.ConfigPropertyOrBuilder getConfigOrBuilder(
        int index) {
      if (configBuilder_ == null) {
        return config_.get(index);  } else {
        return configBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public java.util.List<? extends alluxio.grpc.ConfigPropertyOrBuilder> 
         getConfigOrBuilderList() {
      if (configBuilder_ != null) {
        return configBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(config_);
      }
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public alluxio.grpc.ConfigProperty.Builder addConfigBuilder() {
      return getConfigFieldBuilder().addBuilder(
          alluxio.grpc.ConfigProperty.getDefaultInstance());
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public alluxio.grpc.ConfigProperty.Builder addConfigBuilder(
        int index) {
      return getConfigFieldBuilder().addBuilder(
          index, alluxio.grpc.ConfigProperty.getDefaultInstance());
    }
    /**
     * <code>repeated .alluxio.grpc.ConfigProperty config = 1;</code>
     */
    public java.util.List<alluxio.grpc.ConfigProperty.Builder> 
         getConfigBuilderList() {
      return getConfigFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        alluxio.grpc.ConfigProperty, alluxio.grpc.ConfigProperty.Builder, alluxio.grpc.ConfigPropertyOrBuilder> 
        getConfigFieldBuilder() {
      if (configBuilder_ == null) {
        configBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            alluxio.grpc.ConfigProperty, alluxio.grpc.ConfigProperty.Builder, alluxio.grpc.ConfigPropertyOrBuilder>(
                config_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        config_ = null;
      }
      return configBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.RegisterWorkerPOptions)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.RegisterWorkerPOptions)
  private static final alluxio.grpc.RegisterWorkerPOptions DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.RegisterWorkerPOptions();
  }

  public static alluxio.grpc.RegisterWorkerPOptions getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RegisterWorkerPOptions>
      PARSER = new com.google.protobuf.AbstractParser<RegisterWorkerPOptions>() {
    public RegisterWorkerPOptions parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RegisterWorkerPOptions(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RegisterWorkerPOptions> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RegisterWorkerPOptions> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.RegisterWorkerPOptions getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

