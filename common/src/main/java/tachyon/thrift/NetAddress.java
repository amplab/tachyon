/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package tachyon.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-9-17")
public class NetAddress implements org.apache.thrift.TBase<NetAddress, NetAddress._Fields>, java.io.Serializable, Cloneable, Comparable<NetAddress> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NetAddress");

  private static final org.apache.thrift.protocol.TField HOST_FIELD_DESC = new org.apache.thrift.protocol.TField("host", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField RPC_PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("rpcPort", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("dataPort", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new NetAddressStandardSchemeFactory());
    schemes.put(TupleScheme.class, new NetAddressTupleSchemeFactory());
  }

  public String host; // required
  public int rpcPort; // required
  public int dataPort; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    HOST((short)1, "host"),
    RPC_PORT((short)2, "rpcPort"),
    DATA_PORT((short)3, "dataPort");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // HOST
          return HOST;
        case 2: // RPC_PORT
          return RPC_PORT;
        case 3: // DATA_PORT
          return DATA_PORT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RPCPORT_ISSET_ID = 0;
  private static final int __DATAPORT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.HOST, new org.apache.thrift.meta_data.FieldMetaData("host", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RPC_PORT, new org.apache.thrift.meta_data.FieldMetaData("rpcPort", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DATA_PORT, new org.apache.thrift.meta_data.FieldMetaData("dataPort", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NetAddress.class, metaDataMap);
  }

  public NetAddress() {
  }

  public NetAddress(
    String host,
    int rpcPort,
    int dataPort)
  {
    this();
    this.host = host;
    this.rpcPort = rpcPort;
    setRpcPortIsSet(true);
    this.dataPort = dataPort;
    setDataPortIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NetAddress(NetAddress other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetHost()) {
      this.host = other.host;
    }
    this.rpcPort = other.rpcPort;
    this.dataPort = other.dataPort;
  }

  public NetAddress deepCopy() {
    return new NetAddress(this);
  }

  @Override
  public void clear() {
    this.host = null;
    setRpcPortIsSet(false);
    this.rpcPort = 0;
    setDataPortIsSet(false);
    this.dataPort = 0;
  }

  public String getHost() {
    return this.host;
  }

  public NetAddress setHost(String host) {
    this.host = host;
    return this;
  }

  public void unsetHost() {
    this.host = null;
  }

  /** Returns true if field host is set (has been assigned a value) and false otherwise */
  public boolean isSetHost() {
    return this.host != null;
  }

  public void setHostIsSet(boolean value) {
    if (!value) {
      this.host = null;
    }
  }

  public int getRpcPort() {
    return this.rpcPort;
  }

  public NetAddress setRpcPort(int rpcPort) {
    this.rpcPort = rpcPort;
    setRpcPortIsSet(true);
    return this;
  }

  public void unsetRpcPort() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RPCPORT_ISSET_ID);
  }

  /** Returns true if field rpcPort is set (has been assigned a value) and false otherwise */
  public boolean isSetRpcPort() {
    return EncodingUtils.testBit(__isset_bitfield, __RPCPORT_ISSET_ID);
  }

  public void setRpcPortIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RPCPORT_ISSET_ID, value);
  }

  public int getDataPort() {
    return this.dataPort;
  }

  public NetAddress setDataPort(int dataPort) {
    this.dataPort = dataPort;
    setDataPortIsSet(true);
    return this;
  }

  public void unsetDataPort() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DATAPORT_ISSET_ID);
  }

  /** Returns true if field dataPort is set (has been assigned a value) and false otherwise */
  public boolean isSetDataPort() {
    return EncodingUtils.testBit(__isset_bitfield, __DATAPORT_ISSET_ID);
  }

  public void setDataPortIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DATAPORT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case HOST:
      if (value == null) {
        unsetHost();
      } else {
        setHost((String)value);
      }
      break;

    case RPC_PORT:
      if (value == null) {
        unsetRpcPort();
      } else {
        setRpcPort((Integer)value);
      }
      break;

    case DATA_PORT:
      if (value == null) {
        unsetDataPort();
      } else {
        setDataPort((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case HOST:
      return getHost();

    case RPC_PORT:
      return Integer.valueOf(getRpcPort());

    case DATA_PORT:
      return Integer.valueOf(getDataPort());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case HOST:
      return isSetHost();
    case RPC_PORT:
      return isSetRpcPort();
    case DATA_PORT:
      return isSetDataPort();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof NetAddress)
      return this.equals((NetAddress)that);
    return false;
  }

  public boolean equals(NetAddress that) {
    if (that == null)
      return false;

    boolean this_present_host = true && this.isSetHost();
    boolean that_present_host = true && that.isSetHost();
    if (this_present_host || that_present_host) {
      if (!(this_present_host && that_present_host))
        return false;
      if (!this.host.equals(that.host))
        return false;
    }

    boolean this_present_rpcPort = true;
    boolean that_present_rpcPort = true;
    if (this_present_rpcPort || that_present_rpcPort) {
      if (!(this_present_rpcPort && that_present_rpcPort))
        return false;
      if (this.rpcPort != that.rpcPort)
        return false;
    }

    boolean this_present_dataPort = true;
    boolean that_present_dataPort = true;
    if (this_present_dataPort || that_present_dataPort) {
      if (!(this_present_dataPort && that_present_dataPort))
        return false;
      if (this.dataPort != that.dataPort)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_host = true && (isSetHost());
    list.add(present_host);
    if (present_host)
      list.add(host);

    boolean present_rpcPort = true;
    list.add(present_rpcPort);
    if (present_rpcPort)
      list.add(rpcPort);

    boolean present_dataPort = true;
    list.add(present_dataPort);
    if (present_dataPort)
      list.add(dataPort);

    return list.hashCode();
  }

  @Override
  public int compareTo(NetAddress other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetHost()).compareTo(other.isSetHost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.host, other.host);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRpcPort()).compareTo(other.isSetRpcPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRpcPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rpcPort, other.rpcPort);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDataPort()).compareTo(other.isSetDataPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataPort, other.dataPort);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("NetAddress(");
    boolean first = true;

    sb.append("host:");
    if (this.host == null) {
      sb.append("null");
    } else {
      sb.append(this.host);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("rpcPort:");
    sb.append(this.rpcPort);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dataPort:");
    sb.append(this.dataPort);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NetAddressStandardSchemeFactory implements SchemeFactory {
    public NetAddressStandardScheme getScheme() {
      return new NetAddressStandardScheme();
    }
  }

  private static class NetAddressStandardScheme extends StandardScheme<NetAddress> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NetAddress struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // HOST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.host = iprot.readString();
              struct.setHostIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RPC_PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.rpcPort = iprot.readI32();
              struct.setRpcPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA_PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.dataPort = iprot.readI32();
              struct.setDataPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NetAddress struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.host != null) {
        oprot.writeFieldBegin(HOST_FIELD_DESC);
        oprot.writeString(struct.host);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RPC_PORT_FIELD_DESC);
      oprot.writeI32(struct.rpcPort);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(DATA_PORT_FIELD_DESC);
      oprot.writeI32(struct.dataPort);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NetAddressTupleSchemeFactory implements SchemeFactory {
    public NetAddressTupleScheme getScheme() {
      return new NetAddressTupleScheme();
    }
  }

  private static class NetAddressTupleScheme extends TupleScheme<NetAddress> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NetAddress struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetHost()) {
        optionals.set(0);
      }
      if (struct.isSetRpcPort()) {
        optionals.set(1);
      }
      if (struct.isSetDataPort()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetHost()) {
        oprot.writeString(struct.host);
      }
      if (struct.isSetRpcPort()) {
        oprot.writeI32(struct.rpcPort);
      }
      if (struct.isSetDataPort()) {
        oprot.writeI32(struct.dataPort);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NetAddress struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.host = iprot.readString();
        struct.setHostIsSet(true);
      }
      if (incoming.get(1)) {
        struct.rpcPort = iprot.readI32();
        struct.setRpcPortIsSet(true);
      }
      if (incoming.get(2)) {
        struct.dataPort = iprot.readI32();
        struct.setDataPortIsSet(true);
      }
    }
  }

}

