// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: meta_master.proto

package alluxio.grpc;

/**
 * Protobuf type {@code alluxio.grpc.MasterInfo}
 */
public  final class MasterInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:alluxio.grpc.MasterInfo)
    MasterInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MasterInfo.newBuilder() to construct.
  private MasterInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MasterInfo() {
    masterAddress_ = "";
    rpcPort_ = 0;
    safeMode_ = false;
    startTimeMs_ = 0L;
    upTimeMs_ = 0L;
    version_ = "";
    webPort_ = 0;
    zookeeperAddresses_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MasterInfo(
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
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            masterAddress_ = bs;
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            rpcPort_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            safeMode_ = input.readBool();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            startTimeMs_ = input.readInt64();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            upTimeMs_ = input.readInt64();
            break;
          }
          case 50: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000020;
            version_ = bs;
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            webPort_ = input.readInt32();
            break;
          }
          case 66: {
            com.google.protobuf.ByteString bs = input.readBytes();
            if (!((mutable_bitField0_ & 0x00000080) == 0x00000080)) {
              zookeeperAddresses_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000080;
            }
            zookeeperAddresses_.add(bs);
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
      if (((mutable_bitField0_ & 0x00000080) == 0x00000080)) {
        zookeeperAddresses_ = zookeeperAddresses_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MasterInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MasterInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alluxio.grpc.MasterInfo.class, alluxio.grpc.MasterInfo.Builder.class);
  }

  private int bitField0_;
  public static final int MASTERADDRESS_FIELD_NUMBER = 1;
  private volatile java.lang.Object masterAddress_;
  /**
   * <code>optional string masterAddress = 1;</code>
   */
  public boolean hasMasterAddress() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional string masterAddress = 1;</code>
   */
  public java.lang.String getMasterAddress() {
    java.lang.Object ref = masterAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        masterAddress_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string masterAddress = 1;</code>
   */
  public com.google.protobuf.ByteString
      getMasterAddressBytes() {
    java.lang.Object ref = masterAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      masterAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RPCPORT_FIELD_NUMBER = 2;
  private int rpcPort_;
  /**
   * <code>optional int32 rpcPort = 2;</code>
   */
  public boolean hasRpcPort() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional int32 rpcPort = 2;</code>
   */
  public int getRpcPort() {
    return rpcPort_;
  }

  public static final int SAFEMODE_FIELD_NUMBER = 3;
  private boolean safeMode_;
  /**
   * <code>optional bool safeMode = 3;</code>
   */
  public boolean hasSafeMode() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional bool safeMode = 3;</code>
   */
  public boolean getSafeMode() {
    return safeMode_;
  }

  public static final int STARTTIMEMS_FIELD_NUMBER = 4;
  private long startTimeMs_;
  /**
   * <code>optional int64 startTimeMs = 4;</code>
   */
  public boolean hasStartTimeMs() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional int64 startTimeMs = 4;</code>
   */
  public long getStartTimeMs() {
    return startTimeMs_;
  }

  public static final int UPTIMEMS_FIELD_NUMBER = 5;
  private long upTimeMs_;
  /**
   * <code>optional int64 upTimeMs = 5;</code>
   */
  public boolean hasUpTimeMs() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional int64 upTimeMs = 5;</code>
   */
  public long getUpTimeMs() {
    return upTimeMs_;
  }

  public static final int VERSION_FIELD_NUMBER = 6;
  private volatile java.lang.Object version_;
  /**
   * <code>optional string version = 6;</code>
   */
  public boolean hasVersion() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>optional string version = 6;</code>
   */
  public java.lang.String getVersion() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        version_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string version = 6;</code>
   */
  public com.google.protobuf.ByteString
      getVersionBytes() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      version_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int WEBPORT_FIELD_NUMBER = 7;
  private int webPort_;
  /**
   * <code>optional int32 webPort = 7;</code>
   */
  public boolean hasWebPort() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>optional int32 webPort = 7;</code>
   */
  public int getWebPort() {
    return webPort_;
  }

  public static final int ZOOKEEPERADDRESSES_FIELD_NUMBER = 8;
  private com.google.protobuf.LazyStringList zookeeperAddresses_;
  /**
   * <pre>
   * Null means zookeeper is not enabled
   * </pre>
   *
   * <code>repeated string zookeeperAddresses = 8;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getZookeeperAddressesList() {
    return zookeeperAddresses_;
  }
  /**
   * <pre>
   * Null means zookeeper is not enabled
   * </pre>
   *
   * <code>repeated string zookeeperAddresses = 8;</code>
   */
  public int getZookeeperAddressesCount() {
    return zookeeperAddresses_.size();
  }
  /**
   * <pre>
   * Null means zookeeper is not enabled
   * </pre>
   *
   * <code>repeated string zookeeperAddresses = 8;</code>
   */
  public java.lang.String getZookeeperAddresses(int index) {
    return zookeeperAddresses_.get(index);
  }
  /**
   * <pre>
   * Null means zookeeper is not enabled
   * </pre>
   *
   * <code>repeated string zookeeperAddresses = 8;</code>
   */
  public com.google.protobuf.ByteString
      getZookeeperAddressesBytes(int index) {
    return zookeeperAddresses_.getByteString(index);
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, masterAddress_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, rpcPort_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeBool(3, safeMode_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(4, startTimeMs_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, upTimeMs_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, version_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, webPort_);
    }
    for (int i = 0; i < zookeeperAddresses_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 8, zookeeperAddresses_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, masterAddress_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, rpcPort_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, safeMode_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, startTimeMs_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, upTimeMs_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, version_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, webPort_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < zookeeperAddresses_.size(); i++) {
        dataSize += computeStringSizeNoTag(zookeeperAddresses_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getZookeeperAddressesList().size();
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
    if (!(obj instanceof alluxio.grpc.MasterInfo)) {
      return super.equals(obj);
    }
    alluxio.grpc.MasterInfo other = (alluxio.grpc.MasterInfo) obj;

    boolean result = true;
    result = result && (hasMasterAddress() == other.hasMasterAddress());
    if (hasMasterAddress()) {
      result = result && getMasterAddress()
          .equals(other.getMasterAddress());
    }
    result = result && (hasRpcPort() == other.hasRpcPort());
    if (hasRpcPort()) {
      result = result && (getRpcPort()
          == other.getRpcPort());
    }
    result = result && (hasSafeMode() == other.hasSafeMode());
    if (hasSafeMode()) {
      result = result && (getSafeMode()
          == other.getSafeMode());
    }
    result = result && (hasStartTimeMs() == other.hasStartTimeMs());
    if (hasStartTimeMs()) {
      result = result && (getStartTimeMs()
          == other.getStartTimeMs());
    }
    result = result && (hasUpTimeMs() == other.hasUpTimeMs());
    if (hasUpTimeMs()) {
      result = result && (getUpTimeMs()
          == other.getUpTimeMs());
    }
    result = result && (hasVersion() == other.hasVersion());
    if (hasVersion()) {
      result = result && getVersion()
          .equals(other.getVersion());
    }
    result = result && (hasWebPort() == other.hasWebPort());
    if (hasWebPort()) {
      result = result && (getWebPort()
          == other.getWebPort());
    }
    result = result && getZookeeperAddressesList()
        .equals(other.getZookeeperAddressesList());
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
    if (hasMasterAddress()) {
      hash = (37 * hash) + MASTERADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getMasterAddress().hashCode();
    }
    if (hasRpcPort()) {
      hash = (37 * hash) + RPCPORT_FIELD_NUMBER;
      hash = (53 * hash) + getRpcPort();
    }
    if (hasSafeMode()) {
      hash = (37 * hash) + SAFEMODE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getSafeMode());
    }
    if (hasStartTimeMs()) {
      hash = (37 * hash) + STARTTIMEMS_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getStartTimeMs());
    }
    if (hasUpTimeMs()) {
      hash = (37 * hash) + UPTIMEMS_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getUpTimeMs());
    }
    if (hasVersion()) {
      hash = (37 * hash) + VERSION_FIELD_NUMBER;
      hash = (53 * hash) + getVersion().hashCode();
    }
    if (hasWebPort()) {
      hash = (37 * hash) + WEBPORT_FIELD_NUMBER;
      hash = (53 * hash) + getWebPort();
    }
    if (getZookeeperAddressesCount() > 0) {
      hash = (37 * hash) + ZOOKEEPERADDRESSES_FIELD_NUMBER;
      hash = (53 * hash) + getZookeeperAddressesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alluxio.grpc.MasterInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MasterInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alluxio.grpc.MasterInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.MasterInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MasterInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alluxio.grpc.MasterInfo parseFrom(
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
  public static Builder newBuilder(alluxio.grpc.MasterInfo prototype) {
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
   * Protobuf type {@code alluxio.grpc.MasterInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:alluxio.grpc.MasterInfo)
      alluxio.grpc.MasterInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MasterInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MasterInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alluxio.grpc.MasterInfo.class, alluxio.grpc.MasterInfo.Builder.class);
    }

    // Construct using alluxio.grpc.MasterInfo.newBuilder()
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
      masterAddress_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      rpcPort_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      safeMode_ = false;
      bitField0_ = (bitField0_ & ~0x00000004);
      startTimeMs_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      upTimeMs_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      version_ = "";
      bitField0_ = (bitField0_ & ~0x00000020);
      webPort_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      zookeeperAddresses_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alluxio.grpc.MetaMasterProto.internal_static_alluxio_grpc_MasterInfo_descriptor;
    }

    public alluxio.grpc.MasterInfo getDefaultInstanceForType() {
      return alluxio.grpc.MasterInfo.getDefaultInstance();
    }

    public alluxio.grpc.MasterInfo build() {
      alluxio.grpc.MasterInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public alluxio.grpc.MasterInfo buildPartial() {
      alluxio.grpc.MasterInfo result = new alluxio.grpc.MasterInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.masterAddress_ = masterAddress_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.rpcPort_ = rpcPort_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.safeMode_ = safeMode_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.startTimeMs_ = startTimeMs_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.upTimeMs_ = upTimeMs_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.version_ = version_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.webPort_ = webPort_;
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        zookeeperAddresses_ = zookeeperAddresses_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000080);
      }
      result.zookeeperAddresses_ = zookeeperAddresses_;
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
      if (other instanceof alluxio.grpc.MasterInfo) {
        return mergeFrom((alluxio.grpc.MasterInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alluxio.grpc.MasterInfo other) {
      if (other == alluxio.grpc.MasterInfo.getDefaultInstance()) return this;
      if (other.hasMasterAddress()) {
        bitField0_ |= 0x00000001;
        masterAddress_ = other.masterAddress_;
        onChanged();
      }
      if (other.hasRpcPort()) {
        setRpcPort(other.getRpcPort());
      }
      if (other.hasSafeMode()) {
        setSafeMode(other.getSafeMode());
      }
      if (other.hasStartTimeMs()) {
        setStartTimeMs(other.getStartTimeMs());
      }
      if (other.hasUpTimeMs()) {
        setUpTimeMs(other.getUpTimeMs());
      }
      if (other.hasVersion()) {
        bitField0_ |= 0x00000020;
        version_ = other.version_;
        onChanged();
      }
      if (other.hasWebPort()) {
        setWebPort(other.getWebPort());
      }
      if (!other.zookeeperAddresses_.isEmpty()) {
        if (zookeeperAddresses_.isEmpty()) {
          zookeeperAddresses_ = other.zookeeperAddresses_;
          bitField0_ = (bitField0_ & ~0x00000080);
        } else {
          ensureZookeeperAddressesIsMutable();
          zookeeperAddresses_.addAll(other.zookeeperAddresses_);
        }
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
      alluxio.grpc.MasterInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alluxio.grpc.MasterInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object masterAddress_ = "";
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public boolean hasMasterAddress() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public java.lang.String getMasterAddress() {
      java.lang.Object ref = masterAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          masterAddress_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMasterAddressBytes() {
      java.lang.Object ref = masterAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        masterAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public Builder setMasterAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      masterAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public Builder clearMasterAddress() {
      bitField0_ = (bitField0_ & ~0x00000001);
      masterAddress_ = getDefaultInstance().getMasterAddress();
      onChanged();
      return this;
    }
    /**
     * <code>optional string masterAddress = 1;</code>
     */
    public Builder setMasterAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      masterAddress_ = value;
      onChanged();
      return this;
    }

    private int rpcPort_ ;
    /**
     * <code>optional int32 rpcPort = 2;</code>
     */
    public boolean hasRpcPort() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 rpcPort = 2;</code>
     */
    public int getRpcPort() {
      return rpcPort_;
    }
    /**
     * <code>optional int32 rpcPort = 2;</code>
     */
    public Builder setRpcPort(int value) {
      bitField0_ |= 0x00000002;
      rpcPort_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 rpcPort = 2;</code>
     */
    public Builder clearRpcPort() {
      bitField0_ = (bitField0_ & ~0x00000002);
      rpcPort_ = 0;
      onChanged();
      return this;
    }

    private boolean safeMode_ ;
    /**
     * <code>optional bool safeMode = 3;</code>
     */
    public boolean hasSafeMode() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional bool safeMode = 3;</code>
     */
    public boolean getSafeMode() {
      return safeMode_;
    }
    /**
     * <code>optional bool safeMode = 3;</code>
     */
    public Builder setSafeMode(boolean value) {
      bitField0_ |= 0x00000004;
      safeMode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool safeMode = 3;</code>
     */
    public Builder clearSafeMode() {
      bitField0_ = (bitField0_ & ~0x00000004);
      safeMode_ = false;
      onChanged();
      return this;
    }

    private long startTimeMs_ ;
    /**
     * <code>optional int64 startTimeMs = 4;</code>
     */
    public boolean hasStartTimeMs() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int64 startTimeMs = 4;</code>
     */
    public long getStartTimeMs() {
      return startTimeMs_;
    }
    /**
     * <code>optional int64 startTimeMs = 4;</code>
     */
    public Builder setStartTimeMs(long value) {
      bitField0_ |= 0x00000008;
      startTimeMs_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 startTimeMs = 4;</code>
     */
    public Builder clearStartTimeMs() {
      bitField0_ = (bitField0_ & ~0x00000008);
      startTimeMs_ = 0L;
      onChanged();
      return this;
    }

    private long upTimeMs_ ;
    /**
     * <code>optional int64 upTimeMs = 5;</code>
     */
    public boolean hasUpTimeMs() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int64 upTimeMs = 5;</code>
     */
    public long getUpTimeMs() {
      return upTimeMs_;
    }
    /**
     * <code>optional int64 upTimeMs = 5;</code>
     */
    public Builder setUpTimeMs(long value) {
      bitField0_ |= 0x00000010;
      upTimeMs_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 upTimeMs = 5;</code>
     */
    public Builder clearUpTimeMs() {
      bitField0_ = (bitField0_ & ~0x00000010);
      upTimeMs_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object version_ = "";
    /**
     * <code>optional string version = 6;</code>
     */
    public boolean hasVersion() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional string version = 6;</code>
     */
    public java.lang.String getVersion() {
      java.lang.Object ref = version_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          version_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string version = 6;</code>
     */
    public com.google.protobuf.ByteString
        getVersionBytes() {
      java.lang.Object ref = version_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        version_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string version = 6;</code>
     */
    public Builder setVersion(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string version = 6;</code>
     */
    public Builder clearVersion() {
      bitField0_ = (bitField0_ & ~0x00000020);
      version_ = getDefaultInstance().getVersion();
      onChanged();
      return this;
    }
    /**
     * <code>optional string version = 6;</code>
     */
    public Builder setVersionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      version_ = value;
      onChanged();
      return this;
    }

    private int webPort_ ;
    /**
     * <code>optional int32 webPort = 7;</code>
     */
    public boolean hasWebPort() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional int32 webPort = 7;</code>
     */
    public int getWebPort() {
      return webPort_;
    }
    /**
     * <code>optional int32 webPort = 7;</code>
     */
    public Builder setWebPort(int value) {
      bitField0_ |= 0x00000040;
      webPort_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 webPort = 7;</code>
     */
    public Builder clearWebPort() {
      bitField0_ = (bitField0_ & ~0x00000040);
      webPort_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList zookeeperAddresses_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureZookeeperAddressesIsMutable() {
      if (!((bitField0_ & 0x00000080) == 0x00000080)) {
        zookeeperAddresses_ = new com.google.protobuf.LazyStringArrayList(zookeeperAddresses_);
        bitField0_ |= 0x00000080;
       }
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getZookeeperAddressesList() {
      return zookeeperAddresses_.getUnmodifiableView();
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public int getZookeeperAddressesCount() {
      return zookeeperAddresses_.size();
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public java.lang.String getZookeeperAddresses(int index) {
      return zookeeperAddresses_.get(index);
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public com.google.protobuf.ByteString
        getZookeeperAddressesBytes(int index) {
      return zookeeperAddresses_.getByteString(index);
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public Builder setZookeeperAddresses(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureZookeeperAddressesIsMutable();
      zookeeperAddresses_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public Builder addZookeeperAddresses(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureZookeeperAddressesIsMutable();
      zookeeperAddresses_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public Builder addAllZookeeperAddresses(
        java.lang.Iterable<java.lang.String> values) {
      ensureZookeeperAddressesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, zookeeperAddresses_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public Builder clearZookeeperAddresses() {
      zookeeperAddresses_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000080);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Null means zookeeper is not enabled
     * </pre>
     *
     * <code>repeated string zookeeperAddresses = 8;</code>
     */
    public Builder addZookeeperAddressesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureZookeeperAddressesIsMutable();
      zookeeperAddresses_.add(value);
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


    // @@protoc_insertion_point(builder_scope:alluxio.grpc.MasterInfo)
  }

  // @@protoc_insertion_point(class_scope:alluxio.grpc.MasterInfo)
  private static final alluxio.grpc.MasterInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alluxio.grpc.MasterInfo();
  }

  public static alluxio.grpc.MasterInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MasterInfo>
      PARSER = new com.google.protobuf.AbstractParser<MasterInfo>() {
    public MasterInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MasterInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MasterInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MasterInfo> getParserForType() {
    return PARSER;
  }

  public alluxio.grpc.MasterInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

