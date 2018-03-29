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
public class GetConfigInfoListTResponse implements org.apache.thrift.TBase<GetConfigInfoListTResponse, GetConfigInfoListTResponse._Fields>, java.io.Serializable, Cloneable, Comparable<GetConfigInfoListTResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetConfigInfoListTResponse");

  private static final org.apache.thrift.protocol.TField CONFIG_INFO_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("configInfoList", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetConfigInfoListTResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetConfigInfoListTResponseTupleSchemeFactory());
  }

  private List<ConfigInfo> configInfoList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CONFIG_INFO_LIST((short)1, "configInfoList");

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
        case 1: // CONFIG_INFO_LIST
          return CONFIG_INFO_LIST;
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
    tmpMap.put(_Fields.CONFIG_INFO_LIST, new org.apache.thrift.meta_data.FieldMetaData("configInfoList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ConfigInfo.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetConfigInfoListTResponse.class, metaDataMap);
  }

  public GetConfigInfoListTResponse() {
  }

  public GetConfigInfoListTResponse(
    List<ConfigInfo> configInfoList)
  {
    this();
    this.configInfoList = configInfoList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetConfigInfoListTResponse(GetConfigInfoListTResponse other) {
    if (other.isSetConfigInfoList()) {
      List<ConfigInfo> __this__configInfoList = new ArrayList<ConfigInfo>(other.configInfoList.size());
      for (ConfigInfo other_element : other.configInfoList) {
        __this__configInfoList.add(new ConfigInfo(other_element));
      }
      this.configInfoList = __this__configInfoList;
    }
  }

  public GetConfigInfoListTResponse deepCopy() {
    return new GetConfigInfoListTResponse(this);
  }

  @Override
  public void clear() {
    this.configInfoList = null;
  }

  public int getConfigInfoListSize() {
    return (this.configInfoList == null) ? 0 : this.configInfoList.size();
  }

  public java.util.Iterator<ConfigInfo> getConfigInfoListIterator() {
    return (this.configInfoList == null) ? null : this.configInfoList.iterator();
  }

  public void addToConfigInfoList(ConfigInfo elem) {
    if (this.configInfoList == null) {
      this.configInfoList = new ArrayList<ConfigInfo>();
    }
    this.configInfoList.add(elem);
  }

  public List<ConfigInfo> getConfigInfoList() {
    return this.configInfoList;
  }

  public GetConfigInfoListTResponse setConfigInfoList(List<ConfigInfo> configInfoList) {
    this.configInfoList = configInfoList;
    return this;
  }

  public void unsetConfigInfoList() {
    this.configInfoList = null;
  }

  /** Returns true if field configInfoList is set (has been assigned a value) and false otherwise */
  public boolean isSetConfigInfoList() {
    return this.configInfoList != null;
  }

  public void setConfigInfoListIsSet(boolean value) {
    if (!value) {
      this.configInfoList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CONFIG_INFO_LIST:
      if (value == null) {
        unsetConfigInfoList();
      } else {
        setConfigInfoList((List<ConfigInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CONFIG_INFO_LIST:
      return getConfigInfoList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CONFIG_INFO_LIST:
      return isSetConfigInfoList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetConfigInfoListTResponse)
      return this.equals((GetConfigInfoListTResponse)that);
    return false;
  }

  public boolean equals(GetConfigInfoListTResponse that) {
    if (that == null)
      return false;

    boolean this_present_configInfoList = true && this.isSetConfigInfoList();
    boolean that_present_configInfoList = true && that.isSetConfigInfoList();
    if (this_present_configInfoList || that_present_configInfoList) {
      if (!(this_present_configInfoList && that_present_configInfoList))
        return false;
      if (!this.configInfoList.equals(that.configInfoList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_configInfoList = true && (isSetConfigInfoList());
    list.add(present_configInfoList);
    if (present_configInfoList)
      list.add(configInfoList);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetConfigInfoListTResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetConfigInfoList()).compareTo(other.isSetConfigInfoList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetConfigInfoList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.configInfoList, other.configInfoList);
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
    StringBuilder sb = new StringBuilder("GetConfigInfoListTResponse(");
    boolean first = true;

    sb.append("configInfoList:");
    if (this.configInfoList == null) {
      sb.append("null");
    } else {
      sb.append(this.configInfoList);
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

  private static class GetConfigInfoListTResponseStandardSchemeFactory implements SchemeFactory {
    public GetConfigInfoListTResponseStandardScheme getScheme() {
      return new GetConfigInfoListTResponseStandardScheme();
    }
  }

  private static class GetConfigInfoListTResponseStandardScheme extends StandardScheme<GetConfigInfoListTResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetConfigInfoListTResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CONFIG_INFO_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.configInfoList = new ArrayList<ConfigInfo>(_list0.size);
                ConfigInfo _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new ConfigInfo();
                  _elem1.read(iprot);
                  struct.configInfoList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setConfigInfoListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetConfigInfoListTResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.configInfoList != null) {
        oprot.writeFieldBegin(CONFIG_INFO_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.configInfoList.size()));
          for (ConfigInfo _iter3 : struct.configInfoList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetConfigInfoListTResponseTupleSchemeFactory implements SchemeFactory {
    public GetConfigInfoListTResponseTupleScheme getScheme() {
      return new GetConfigInfoListTResponseTupleScheme();
    }
  }

  private static class GetConfigInfoListTResponseTupleScheme extends TupleScheme<GetConfigInfoListTResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetConfigInfoListTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetConfigInfoList()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetConfigInfoList()) {
        {
          oprot.writeI32(struct.configInfoList.size());
          for (ConfigInfo _iter4 : struct.configInfoList)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetConfigInfoListTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.configInfoList = new ArrayList<ConfigInfo>(_list5.size);
          ConfigInfo _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new ConfigInfo();
            _elem6.read(iprot);
            struct.configInfoList.add(_elem6);
          }
        }
        struct.setConfigInfoListIsSet(true);
      }
    }
  }

}

