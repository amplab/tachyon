// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc/file_system_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.file.GetMountTablePResponse}
 */
public  final class GetMountTablePResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.file.GetMountTablePResponse)
    GetMountTablePResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetMountTablePResponse.newBuilder() to construct.
  private GetMountTablePResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetMountTablePResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetMountTablePResponse(
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
              mountPoints_ = com.google.protobuf.MapField.newMapField(
                  MountPointsDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.String, alluxio.grpc.MountPointInfo>
            mountPoints__ = input.readMessage(
                MountPointsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            mountPoints_.getMutableMap().put(
                mountPoints__.getKey(), mountPoints__.getValue());
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
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetMountPoints();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.GetMountTablePResponse.class, alluxio.grpc.GetMountTablePResponse.Builder.class);
  }

  public static final int MOUNTPOINTS_FIELD_NUMBER = 1;
  private static final class MountPointsDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, alluxio.grpc.MountPointInfo> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, alluxio.grpc.MountPointInfo>newDefaultInstance(
                alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_MountPointsEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                alluxio.grpc.MountPointInfo.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, alluxio.grpc.MountPointInfo> mountPoints_;
  private com.google.protobuf.MapField<java.lang.String, alluxio.grpc.MountPointInfo>
  internalGetMountPoints() {
    if (mountPoints_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          MountPointsDefaultEntryHolder.defaultEntry);
    }
    return mountPoints_;
  }

  public int getMountPointsCount() {
    return internalGetMountPoints().getMap().size();
  }
  /**
   * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
   */

  public boolean containsMountPoints(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetMountPoints().getMap().containsKey(key);
  }
  /**
   * Use {@link #getMountPointsMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> getMountPoints() {
    return getMountPointsMap();
  }
  /**
   * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
   */

  public java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> getMountPointsMap() {
    return internalGetMountPoints().getMap();
  }
  /**
   * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
   */

  public alluxio.grpc.MountPointInfo getMountPointsOrDefault(
      java.lang.String key,
      alluxio.grpc.MountPointInfo defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> map =
        internalGetMountPoints().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
   */

  public alluxio.grpc.MountPointInfo getMountPointsOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> map =
        internalGetMountPoints().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
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
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetMountPoints(),
        MountPointsDefaultEntryHolder.defaultEntry,
        1);
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<java.lang.String, alluxio.grpc.MountPointInfo> entry
         : internalGetMountPoints().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, alluxio.grpc.MountPointInfo>
      mountPoints__ = MountPointsDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, mountPoints__);
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
    if (!(obj instanceof alluxio.grpc.GetMountTablePResponse)) {
      return super.equals(obj);
    }
    alluxio.grpc.GetMountTablePResponse other = (alluxio.grpc.GetMountTablePResponse) obj;

    boolean result = true;
    result = result && internalGetMountPoints().equals(
        other.internalGetMountPoints());
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
    if (!internalGetMountPoints().getMap().isEmpty()) {
      hash = (37 * hash) + MOUNTPOINTS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetMountPoints().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetMountTablePResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetMountTablePResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.GetMountTablePResponse parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.GetMountTablePResponse prototype) {
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
   * Protobuf type {@code alluxio.grpc.file.GetMountTablePResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.file.GetMountTablePResponse)
      alluxio.grpc.GetMountTablePResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMountPoints();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableMountPoints();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.GetMountTablePResponse.class, alluxio.grpc.GetMountTablePResponse.Builder.class);
    }

    // Construct using alluxio.grpc.GetMountTablePResponse.newBuilder()
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
      internalGetMutableMountPoints().clear();
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.FileSystemMasterProto.internal_static_alluxio_grpc_file_GetMountTablePResponse_descriptor;
    }

    public alluxio.grpc.GetMountTablePResponse getDefaultInstanceForType() {
      return alluxio.grpc.GetMountTablePResponse.getDefaultInstance();
    }

    public alluxio.grpc.GetMountTablePResponse build() {
      alluxio.grpc.GetMountTablePResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.GetMountTablePResponse buildPartial() {
      alluxio.grpc.GetMountTablePResponse result = new alluxio.grpc.GetMountTablePResponse(this);
      int from_bitField0_ = bitField0_;
      result.mountPoints_ = internalGetMountPoints();
      result.mountPoints_.makeImmutable();
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
      if (other instanceof alluxio.grpc.GetMountTablePResponse) {
        return mergeFrom((alluxio.grpc.GetMountTablePResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.GetMountTablePResponse other) {
      if (other == alluxio.grpc.GetMountTablePResponse.getDefaultInstance()) return this;
      internalGetMutableMountPoints().mergeFrom(
          other.internalGetMountPoints());
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
      alluxio.grpc.GetMountTablePResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.GetMountTablePResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.MapField<
        java.lang.String, alluxio.grpc.MountPointInfo> mountPoints_;
    private com.google.protobuf.MapField<java.lang.String, alluxio.grpc.MountPointInfo>
    internalGetMountPoints() {
      if (mountPoints_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            MountPointsDefaultEntryHolder.defaultEntry);
      }
      return mountPoints_;
    }
    private com.google.protobuf.MapField<java.lang.String, alluxio.grpc.MountPointInfo>
    internalGetMutableMountPoints() {
      onChanged();;
      if (mountPoints_ == null) {
        mountPoints_ = com.google.protobuf.MapField.newMapField(
            MountPointsDefaultEntryHolder.defaultEntry);
      }
      if (!mountPoints_.isMutable()) {
        mountPoints_ = mountPoints_.copy();
      }
      return mountPoints_;
    }

    public int getMountPointsCount() {
      return internalGetMountPoints().getMap().size();
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public boolean containsMountPoints(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetMountPoints().getMap().containsKey(key);
    }
    /**
     * Use {@link #getMountPointsMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> getMountPoints() {
      return getMountPointsMap();
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> getMountPointsMap() {
      return internalGetMountPoints().getMap();
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public alluxio.grpc.MountPointInfo getMountPointsOrDefault(
        java.lang.String key,
        alluxio.grpc.MountPointInfo defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> map =
          internalGetMountPoints().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public alluxio.grpc.MountPointInfo getMountPointsOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> map =
          internalGetMountPoints().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearMountPoints() {
      internalGetMutableMountPoints().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public Builder removeMountPoints(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableMountPoints().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo>
    getMutableMountPoints() {
      return internalGetMutableMountPoints().getMutableMap();
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */
    public Builder putMountPoints(
        java.lang.String key,
        alluxio.grpc.MountPointInfo value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableMountPoints().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, .alluxio.grpc.file.MountPointInfo&gt; mountPoints = 1;</code>
     */

    public Builder putAllMountPoints(
        java.util.Map<java.lang.String, alluxio.grpc.MountPointInfo> values) {
      internalGetMutableMountPoints().getMutableMap()
          .putAll(values);
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.file.GetMountTablePResponse)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.file.GetMountTablePResponse)
  private static final alluxio.grpc.GetMountTablePResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.GetMountTablePResponse();
  }

  public static alluxio.grpc.GetMountTablePResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetMountTablePResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetMountTablePResponse>() {
    public GetMountTablePResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetMountTablePResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetMountTablePResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetMountTablePResponse> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.GetMountTablePResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

