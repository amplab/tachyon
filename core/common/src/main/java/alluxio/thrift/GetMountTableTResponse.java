/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package alluxio.thrift;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class GetMountTableTResponse implements org.apache.thrift.TBase<GetMountTableTResponse, GetMountTableTResponse._Fields>, java.io.Serializable, Cloneable, Comparable<GetMountTableTResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetMountTableTResponse");

  private static final org.apache.thrift.protocol.TField MOUNT_TABLE_FIELD_DESC = new org.apache.thrift.protocol.TField("mountTable", org.apache.thrift.protocol.TType.MAP, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetMountTableTResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetMountTableTResponseTupleSchemeFactory());
  }

  private Map<String,MountPointInfo> mountTable; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MOUNT_TABLE((short)1, "mountTable");

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
        case 1: // MOUNT_TABLE
          return MOUNT_TABLE;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MOUNT_TABLE, new org.apache.thrift.meta_data.FieldMetaData("mountTable", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "MountPointInfo"))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetMountTableTResponse.class, metaDataMap);
  }

  public GetMountTableTResponse() {
  }

  public GetMountTableTResponse(
    Map<String,MountPointInfo> mountTable)
  {
    this();
    this.mountTable = mountTable;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetMountTableTResponse(GetMountTableTResponse other) {
    if (other.isSetMountTable()) {
      Map<String,MountPointInfo> __this__mountTable = new HashMap<String,MountPointInfo>(other.mountTable.size());
      for (Map.Entry<String, MountPointInfo> other_element : other.mountTable.entrySet()) {

        String other_element_key = other_element.getKey();
        MountPointInfo other_element_value = other_element.getValue();

        String __this__mountTable_copy_key = other_element_key;

        MountPointInfo __this__mountTable_copy_value = other_element_value;

        __this__mountTable.put(__this__mountTable_copy_key, __this__mountTable_copy_value);
      }
      this.mountTable = __this__mountTable;
    }
  }

  public GetMountTableTResponse deepCopy() {
    return new GetMountTableTResponse(this);
  }

  @Override
  public void clear() {
    this.mountTable = null;
  }

  public int getMountTableSize() {
    return (this.mountTable == null) ? 0 : this.mountTable.size();
  }

  public void putToMountTable(String key, MountPointInfo val) {
    if (this.mountTable == null) {
      this.mountTable = new HashMap<String,MountPointInfo>();
    }
    this.mountTable.put(key, val);
  }

  public Map<String,MountPointInfo> getMountTable() {
    return this.mountTable;
  }

  public GetMountTableTResponse setMountTable(Map<String,MountPointInfo> mountTable) {
    this.mountTable = mountTable;
    return this;
  }

  public void unsetMountTable() {
    this.mountTable = null;
  }

  /** Returns true if field mountTable is set (has been assigned a value) and false otherwise */
  public boolean isSetMountTable() {
    return this.mountTable != null;
  }

  public void setMountTableIsSet(boolean value) {
    if (!value) {
      this.mountTable = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MOUNT_TABLE:
      if (value == null) {
        unsetMountTable();
      } else {
        setMountTable((Map<String,MountPointInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MOUNT_TABLE:
      return getMountTable();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MOUNT_TABLE:
      return isSetMountTable();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetMountTableTResponse)
      return this.equals((GetMountTableTResponse)that);
    return false;
  }

  public boolean equals(GetMountTableTResponse that) {
    if (that == null)
      return false;

    boolean this_present_mountTable = true && this.isSetMountTable();
    boolean that_present_mountTable = true && that.isSetMountTable();
    if (this_present_mountTable || that_present_mountTable) {
      if (!(this_present_mountTable && that_present_mountTable))
        return false;
      if (!this.mountTable.equals(that.mountTable))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_mountTable = true && (isSetMountTable());
    list.add(present_mountTable);
    if (present_mountTable)
      list.add(mountTable);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetMountTableTResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMountTable()).compareTo(other.isSetMountTable());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMountTable()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mountTable, other.mountTable);
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
    StringBuilder sb = new StringBuilder("GetMountTableTResponse(");
    boolean first = true;

    sb.append("mountTable:");
    if (this.mountTable == null) {
      sb.append("null");
    } else {
      sb.append(this.mountTable);
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GetMountTableTResponseStandardSchemeFactory implements SchemeFactory {
    public GetMountTableTResponseStandardScheme getScheme() {
      return new GetMountTableTResponseStandardScheme();
    }
  }

  private static class GetMountTableTResponseStandardScheme extends StandardScheme<GetMountTableTResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetMountTableTResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MOUNT_TABLE
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map68 = iprot.readMapBegin();
                struct.mountTable = new HashMap<String,MountPointInfo>(2*_map68.size);
                String _key69;
                MountPointInfo _val70;
                for (int _i71 = 0; _i71 < _map68.size; ++_i71)
                {
                  _key69 = iprot.readString();
                  _val70 = new MountPointInfo();
                  _val70.read(iprot);
                  struct.mountTable.put(_key69, _val70);
                }
                iprot.readMapEnd();
              }
              struct.setMountTableIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetMountTableTResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.mountTable != null) {
        oprot.writeFieldBegin(MOUNT_TABLE_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, struct.mountTable.size()));
          for (Map.Entry<String, MountPointInfo> _iter72 : struct.mountTable.entrySet())
          {
            oprot.writeString(_iter72.getKey());
            _iter72.getValue().write(oprot);
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetMountTableTResponseTupleSchemeFactory implements SchemeFactory {
    public GetMountTableTResponseTupleScheme getScheme() {
      return new GetMountTableTResponseTupleScheme();
    }
  }

  private static class GetMountTableTResponseTupleScheme extends TupleScheme<GetMountTableTResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetMountTableTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetMountTable()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetMountTable()) {
        {
          oprot.writeI32(struct.mountTable.size());
          for (Map.Entry<String, MountPointInfo> _iter73 : struct.mountTable.entrySet())
          {
            oprot.writeString(_iter73.getKey());
            _iter73.getValue().write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetMountTableTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map74 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.mountTable = new HashMap<String,MountPointInfo>(2*_map74.size);
          String _key75;
          MountPointInfo _val76;
          for (int _i77 = 0; _i77 < _map74.size; ++_i77)
          {
            _key75 = iprot.readString();
            _val76 = new MountPointInfo();
            _val76.read(iprot);
            struct.mountTable.put(_key75, _val76);
          }
        }
        struct.setMountTableIsSet(true);
      }
    }
  }

}

