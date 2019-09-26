// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.file.ListStatusPOptions}
 */
public  final class ListStatusPOptions extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.file.ListStatusPOptions)
    ListStatusPOptionsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ListStatusPOptions.newBuilder() to construct.
  private ListStatusPOptions(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ListStatusPOptions() {
    loadDirectChildren_ = false;
    loadMetadataType_ = 0;
    recursive_ = false;
    includeUfsInfo_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ListStatusPOptions(
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
            loadDirectChildren_ = input.readBool();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();
            alluxio.grpc.LoadMetadataPType value = alluxio.grpc.LoadMetadataPType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(2, rawValue);
            } else {
              bitField0_ |= 0x00000002;
              loadMetadataType_ = rawValue;
            }
            break;
          }
          case 26: {
            alluxio.grpc.FileSystemMasterCommonPOptions.Builder subBuilder = null;
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
              subBuilder = commonOptions_.toBuilder();
            }
            commonOptions_ = input.readMessage(alluxio.grpc.FileSystemMasterCommonPOptions.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(commonOptions_);
              commonOptions_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000004;
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            recursive_ = input.readBool();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            includeUfsInfo_ = input.readBool();
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
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_ListStatusPOptions_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_ListStatusPOptions_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.ListStatusPOptions.class, alluxio.grpc.ListStatusPOptions.Builder.class);
  }

  private int bitField0_;
  public static final int LOADDIRECTCHILDREN_FIELD_NUMBER = 1;
  private boolean loadDirectChildren_;
  /**
   * <pre>
   * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
   * </pre>
   *
   * <code>optional bool loadDirectChildren = 1;</code>
   */
  public boolean hasLoadDirectChildren() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
   * </pre>
   *
   * <code>optional bool loadDirectChildren = 1;</code>
   */
  public boolean getLoadDirectChildren() {
    return loadDirectChildren_;
  }

  public static final int LOADMETADATATYPE_FIELD_NUMBER = 2;
  private int loadMetadataType_;
  /**
   * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
   */
  public boolean hasLoadMetadataType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
   */
  public alluxio.grpc.LoadMetadataPType getLoadMetadataType() {
    alluxio.grpc.LoadMetadataPType result = alluxio.grpc.LoadMetadataPType.valueOf(loadMetadataType_);
    return result == null ? alluxio.grpc.LoadMetadataPType.NEVER : result;
  }

  public static final int COMMONOPTIONS_FIELD_NUMBER = 3;
  private alluxio.grpc.FileSystemMasterCommonPOptions commonOptions_;
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   */
  public boolean hasCommonOptions() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   */
  public alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions() {
    return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
  }
  /**
   * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
   */
  public alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder() {
    return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
  }

  public static final int RECURSIVE_FIELD_NUMBER = 4;
  private boolean recursive_;
  /**
   * <code>optional bool recursive = 4;</code>
   */
  public boolean hasRecursive() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional bool recursive = 4;</code>
   */
  public boolean getRecursive() {
    return recursive_;
  }

  public static final int INCLUDEUFSINFO_FIELD_NUMBER = 5;
  private boolean includeUfsInfo_;
  /**
   * <code>optional bool includeUfsInfo = 5;</code>
   */
  public boolean hasIncludeUfsInfo() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional bool includeUfsInfo = 5;</code>
   */
  public boolean getIncludeUfsInfo() {
    return includeUfsInfo_;
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
      output.writeBool(1, loadDirectChildren_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeEnum(2, loadMetadataType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeMessage(3, getCommonOptions());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeBool(4, recursive_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeBool(5, includeUfsInfo_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, loadDirectChildren_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, loadMetadataType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getCommonOptions());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, recursive_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, includeUfsInfo_);
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
    if (!(obj instanceof alluxio.grpc.ListStatusPOptions)) {
      return super.equals(obj);
    }
    alluxio.grpc.ListStatusPOptions other = (alluxio.grpc.ListStatusPOptions) obj;

    boolean result = true;
    result = result && (hasLoadDirectChildren() == other.hasLoadDirectChildren());
    if (hasLoadDirectChildren()) {
      result = result && (getLoadDirectChildren()
          == other.getLoadDirectChildren());
    }
    result = result && (hasLoadMetadataType() == other.hasLoadMetadataType());
    if (hasLoadMetadataType()) {
      result = result && loadMetadataType_ == other.loadMetadataType_;
    }
    result = result && (hasCommonOptions() == other.hasCommonOptions());
    if (hasCommonOptions()) {
      result = result && getCommonOptions()
          .equals(other.getCommonOptions());
    }
    result = result && (hasRecursive() == other.hasRecursive());
    if (hasRecursive()) {
      result = result && (getRecursive()
          == other.getRecursive());
    }
    result = result && (hasIncludeUfsInfo() == other.hasIncludeUfsInfo());
    if (hasIncludeUfsInfo()) {
      result = result && (getIncludeUfsInfo()
          == other.getIncludeUfsInfo());
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
    if (hasLoadDirectChildren()) {
      hash = (37 * hash) + LOADDIRECTCHILDREN_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getLoadDirectChildren());
    }
    if (hasLoadMetadataType()) {
      hash = (37 * hash) + LOADMETADATATYPE_FIELD_NUMBER;
      hash = (53 * hash) + loadMetadataType_;
    }
    if (hasCommonOptions()) {
      hash = (37 * hash) + COMMONOPTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getCommonOptions().hashCode();
    }
    if (hasRecursive()) {
      hash = (37 * hash) + RECURSIVE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getRecursive());
    }
    if (hasIncludeUfsInfo()) {
      hash = (37 * hash) + INCLUDEUFSINFO_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getIncludeUfsInfo());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.ListStatusPOptions parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.ListStatusPOptions parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.ListStatusPOptions parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.ListStatusPOptions parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.ListStatusPOptions prototype) {
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
   * Protobuf type {@code alluxio.grpc.file.ListStatusPOptions}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.file.ListStatusPOptions)
      alluxio.grpc.ListStatusPOptionsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_ListStatusPOptions_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_ListStatusPOptions_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.ListStatusPOptions.class, alluxio.grpc.ListStatusPOptions.Builder.class);
    }

    // Construct using alluxio.grpc.ListStatusPOptions.newBuilder()
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
        getCommonOptionsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      loadDirectChildren_ = false;
      bitField0_ = (bitField0_ & ~0x00000001);
      loadMetadataType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = null;
      } else {
        commonOptionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      recursive_ = false;
      bitField0_ = (bitField0_ & ~0x00000008);
      includeUfsInfo_ = false;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_ListStatusPOptions_descriptor;
    }

    public alluxio.grpc.ListStatusPOptions getDefaultInstanceForType() {
      return alluxio.grpc.ListStatusPOptions.getDefaultInstance();
    }

    public alluxio.grpc.ListStatusPOptions build() {
      alluxio.grpc.ListStatusPOptions result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.ListStatusPOptions buildPartial() {
      alluxio.grpc.ListStatusPOptions result = new alluxio.grpc.ListStatusPOptions(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.loadDirectChildren_ = loadDirectChildren_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.loadMetadataType_ = loadMetadataType_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      if (commonOptionsBuilder_ == null) {
        result.commonOptions_ = commonOptions_;
      } else {
        result.commonOptions_ = commonOptionsBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.recursive_ = recursive_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.includeUfsInfo_ = includeUfsInfo_;
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
      if (other instanceof alluxio.grpc.ListStatusPOptions) {
        return mergeFrom((alluxio.grpc.ListStatusPOptions)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.ListStatusPOptions other) {
      if (other == alluxio.grpc.ListStatusPOptions.getDefaultInstance()) return this;
      if (other.hasLoadDirectChildren()) {
        setLoadDirectChildren(other.getLoadDirectChildren());
      }
      if (other.hasLoadMetadataType()) {
        setLoadMetadataType(other.getLoadMetadataType());
      }
      if (other.hasCommonOptions()) {
        mergeCommonOptions(other.getCommonOptions());
      }
      if (other.hasRecursive()) {
        setRecursive(other.getRecursive());
      }
      if (other.hasIncludeUfsInfo()) {
        setIncludeUfsInfo(other.getIncludeUfsInfo());
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
      alluxio.grpc.ListStatusPOptions parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.ListStatusPOptions) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private boolean loadDirectChildren_ ;
    /**
     * <pre>
     * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
     * </pre>
     *
     * <code>optional bool loadDirectChildren = 1;</code>
     */
    public boolean hasLoadDirectChildren() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
     * </pre>
     *
     * <code>optional bool loadDirectChildren = 1;</code>
     */
    public boolean getLoadDirectChildren() {
      return loadDirectChildren_;
    }
    /**
     * <pre>
     * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
     * </pre>
     *
     * <code>optional bool loadDirectChildren = 1;</code>
     */
    public Builder setLoadDirectChildren(boolean value) {
      bitField0_ |= 0x00000001;
      loadDirectChildren_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * This is deprecated since 1.1.1 and will be removed in 2.0. Use loadMetadataType.
     * </pre>
     *
     * <code>optional bool loadDirectChildren = 1;</code>
     */
    public Builder clearLoadDirectChildren() {
      bitField0_ = (bitField0_ & ~0x00000001);
      loadDirectChildren_ = false;
      onChanged();
      return this;
    }

    private int loadMetadataType_ = 0;
    /**
     * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
     */
    public boolean hasLoadMetadataType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
     */
    public alluxio.grpc.LoadMetadataPType getLoadMetadataType() {
      alluxio.grpc.LoadMetadataPType result = alluxio.grpc.LoadMetadataPType.valueOf(loadMetadataType_);
      return result == null ? alluxio.grpc.LoadMetadataPType.NEVER : result;
    }
    /**
     * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
     */
    public Builder setLoadMetadataType(alluxio.grpc.LoadMetadataPType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
      loadMetadataType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.LoadMetadataPType loadMetadataType = 2;</code>
     */
    public Builder clearLoadMetadataType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      loadMetadataType_ = 0;
      onChanged();
      return this;
    }

    private alluxio.grpc.FileSystemMasterCommonPOptions commonOptions_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder> commonOptionsBuilder_;
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public boolean hasCommonOptions() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptions getCommonOptions() {
      if (commonOptionsBuilder_ == null) {
        return commonOptions_ == null ? alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
      } else {
        return commonOptionsBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public Builder setCommonOptions(alluxio.grpc.FileSystemMasterCommonPOptions value) {
      if (commonOptionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        commonOptions_ = value;
        onChanged();
      } else {
        commonOptionsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public Builder setCommonOptions(
        alluxio.grpc.FileSystemMasterCommonPOptions.Builder builderForValue) {
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = builderForValue.build();
        onChanged();
      } else {
        commonOptionsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public Builder mergeCommonOptions(alluxio.grpc.FileSystemMasterCommonPOptions value) {
      if (commonOptionsBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004) &&
            commonOptions_ != null &&
            commonOptions_ != alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance()) {
          commonOptions_ =
            alluxio.grpc.FileSystemMasterCommonPOptions.newBuilder(commonOptions_).mergeFrom(value).buildPartial();
        } else {
          commonOptions_ = value;
        }
        onChanged();
      } else {
        commonOptionsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public Builder clearCommonOptions() {
      if (commonOptionsBuilder_ == null) {
        commonOptions_ = null;
        onChanged();
      } else {
        commonOptionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptions.Builder getCommonOptionsBuilder() {
      bitField0_ |= 0x00000004;
      onChanged();
      return getCommonOptionsFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    public alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder getCommonOptionsOrBuilder() {
      if (commonOptionsBuilder_ != null) {
        return commonOptionsBuilder_.getMessageOrBuilder();
      } else {
        return commonOptions_ == null ?
            alluxio.grpc.FileSystemMasterCommonPOptions.getDefaultInstance() : commonOptions_;
      }
    }
    /**
     * <code>optional .alluxio.grpc.file.FileSystemMasterCommonPOptions commonOptions = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder> 
        getCommonOptionsFieldBuilder() {
      if (commonOptionsBuilder_ == null) {
        commonOptionsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            alluxio.grpc.FileSystemMasterCommonPOptions, alluxio.grpc.FileSystemMasterCommonPOptions.Builder, alluxio.grpc.FileSystemMasterCommonPOptionsOrBuilder>(
                getCommonOptions(),
                getParentForChildren(),
                isClean());
        commonOptions_ = null;
      }
      return commonOptionsBuilder_;
    }

    private boolean recursive_ ;
    /**
     * <code>optional bool recursive = 4;</code>
     */
    public boolean hasRecursive() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bool recursive = 4;</code>
     */
    public boolean getRecursive() {
      return recursive_;
    }
    /**
     * <code>optional bool recursive = 4;</code>
     */
    public Builder setRecursive(boolean value) {
      bitField0_ |= 0x00000008;
      recursive_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool recursive = 4;</code>
     */
    public Builder clearRecursive() {
      bitField0_ = (bitField0_ & ~0x00000008);
      recursive_ = false;
      onChanged();
      return this;
    }

    private boolean includeUfsInfo_ ;
    /**
     * <code>optional bool includeUfsInfo = 5;</code>
     */
    public boolean hasIncludeUfsInfo() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional bool includeUfsInfo = 5;</code>
     */
    public boolean getIncludeUfsInfo() {
      return includeUfsInfo_;
    }
    /**
     * <code>optional bool includeUfsInfo = 5;</code>
     */
    public Builder setIncludeUfsInfo(boolean value) {
      bitField0_ |= 0x00000010;
      includeUfsInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool includeUfsInfo = 5;</code>
     */
    public Builder clearIncludeUfsInfo() {
      bitField0_ = (bitField0_ & ~0x00000010);
      includeUfsInfo_ = false;
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.file.ListStatusPOptions)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.file.ListStatusPOptions)
  private static final alluxio.grpc.ListStatusPOptions DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.ListStatusPOptions();
  }

  public static alluxio.grpc.ListStatusPOptions getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ListStatusPOptions>
      PARSER = new com.google.protobuf.AbstractParser<ListStatusPOptions>() {
    public ListStatusPOptions parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ListStatusPOptions(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ListStatusPOptions> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListStatusPOptions> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.ListStatusPOptions getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

