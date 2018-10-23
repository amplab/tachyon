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
public class ConfigCheckReport implements org.apache.thrift.TBase<ConfigCheckReport, ConfigCheckReport._Fields>, java.io.Serializable, Cloneable, Comparable<ConfigCheckReport> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ConfigCheckReport");

  private static final org.apache.thrift.protocol.TField ERRORS_FIELD_DESC = new org.apache.thrift.protocol.TField("errors", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField WARNS_FIELD_DESC = new org.apache.thrift.protocol.TField("warns", org.apache.thrift.protocol.TType.MAP, (short)2);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ConfigCheckReportStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ConfigCheckReportTupleSchemeFactory());
  }

  private Map<Scope,List<InconsistentProperty>> errors; // required
  private Map<Scope,List<InconsistentProperty>> warns; // required
  private ConfigStatus status; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERRORS((short)1, "errors"),
    WARNS((short)2, "warns"),
    /**
     * 
     * @see ConfigStatus
     */
    STATUS((short)3, "status");

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
        case 1: // ERRORS
          return ERRORS;
        case 2: // WARNS
          return WARNS;
        case 3: // STATUS
          return STATUS;
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
    tmpMap.put(_Fields.ERRORS, new org.apache.thrift.meta_data.FieldMetaData("errors", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, Scope.class), 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, InconsistentProperty.class)))));
    tmpMap.put(_Fields.WARNS, new org.apache.thrift.meta_data.FieldMetaData("warns", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, Scope.class), 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, InconsistentProperty.class)))));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, ConfigStatus.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ConfigCheckReport.class, metaDataMap);
  }

  public ConfigCheckReport() {
  }

  public ConfigCheckReport(
    Map<Scope,List<InconsistentProperty>> errors,
    Map<Scope,List<InconsistentProperty>> warns,
    ConfigStatus status)
  {
    this();
    this.errors = errors;
    this.warns = warns;
    this.status = status;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ConfigCheckReport(ConfigCheckReport other) {
    if (other.isSetErrors()) {
      Map<Scope,List<InconsistentProperty>> __this__errors = new HashMap<Scope,List<InconsistentProperty>>(other.errors.size());
      for (Map.Entry<Scope, List<InconsistentProperty>> other_element : other.errors.entrySet()) {

        Scope other_element_key = other_element.getKey();
        List<InconsistentProperty> other_element_value = other_element.getValue();

        Scope __this__errors_copy_key = other_element_key;

        List<InconsistentProperty> __this__errors_copy_value = new ArrayList<InconsistentProperty>(other_element_value.size());
        for (InconsistentProperty other_element_value_element : other_element_value) {
          __this__errors_copy_value.add(new InconsistentProperty(other_element_value_element));
        }

        __this__errors.put(__this__errors_copy_key, __this__errors_copy_value);
      }
      this.errors = __this__errors;
    }
    if (other.isSetWarns()) {
      Map<Scope,List<InconsistentProperty>> __this__warns = new HashMap<Scope,List<InconsistentProperty>>(other.warns.size());
      for (Map.Entry<Scope, List<InconsistentProperty>> other_element : other.warns.entrySet()) {

        Scope other_element_key = other_element.getKey();
        List<InconsistentProperty> other_element_value = other_element.getValue();

        Scope __this__warns_copy_key = other_element_key;

        List<InconsistentProperty> __this__warns_copy_value = new ArrayList<InconsistentProperty>(other_element_value.size());
        for (InconsistentProperty other_element_value_element : other_element_value) {
          __this__warns_copy_value.add(new InconsistentProperty(other_element_value_element));
        }

        __this__warns.put(__this__warns_copy_key, __this__warns_copy_value);
      }
      this.warns = __this__warns;
    }
    if (other.isSetStatus()) {
      this.status = other.status;
    }
  }

  public ConfigCheckReport deepCopy() {
    return new ConfigCheckReport(this);
  }

  @Override
  public void clear() {
    this.errors = null;
    this.warns = null;
    this.status = null;
  }

  public int getErrorsSize() {
    return (this.errors == null) ? 0 : this.errors.size();
  }

  public void putToErrors(Scope key, List<InconsistentProperty> val) {
    if (this.errors == null) {
      this.errors = new HashMap<Scope,List<InconsistentProperty>>();
    }
    this.errors.put(key, val);
  }

  public Map<Scope,List<InconsistentProperty>> getErrors() {
    return this.errors;
  }

  public ConfigCheckReport setErrors(Map<Scope,List<InconsistentProperty>> errors) {
    this.errors = errors;
    return this;
  }

  public void unsetErrors() {
    this.errors = null;
  }

  /** Returns true if field errors is set (has been assigned a value) and false otherwise */
  public boolean isSetErrors() {
    return this.errors != null;
  }

  public void setErrorsIsSet(boolean value) {
    if (!value) {
      this.errors = null;
    }
  }

  public int getWarnsSize() {
    return (this.warns == null) ? 0 : this.warns.size();
  }

  public void putToWarns(Scope key, List<InconsistentProperty> val) {
    if (this.warns == null) {
      this.warns = new HashMap<Scope,List<InconsistentProperty>>();
    }
    this.warns.put(key, val);
  }

  public Map<Scope,List<InconsistentProperty>> getWarns() {
    return this.warns;
  }

  public ConfigCheckReport setWarns(Map<Scope,List<InconsistentProperty>> warns) {
    this.warns = warns;
    return this;
  }

  public void unsetWarns() {
    this.warns = null;
  }

  /** Returns true if field warns is set (has been assigned a value) and false otherwise */
  public boolean isSetWarns() {
    return this.warns != null;
  }

  public void setWarnsIsSet(boolean value) {
    if (!value) {
      this.warns = null;
    }
  }

  /**
   * 
   * @see ConfigStatus
   */
  public ConfigStatus getStatus() {
    return this.status;
  }

  /**
   * 
   * @see ConfigStatus
   */
  public ConfigCheckReport setStatus(ConfigStatus status) {
    this.status = status;
    return this;
  }

  public void unsetStatus() {
    this.status = null;
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.status = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERRORS:
      if (value == null) {
        unsetErrors();
      } else {
        setErrors((Map<Scope,List<InconsistentProperty>>)value);
      }
      break;

    case WARNS:
      if (value == null) {
        unsetWarns();
      } else {
        setWarns((Map<Scope,List<InconsistentProperty>>)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((ConfigStatus)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERRORS:
      return getErrors();

    case WARNS:
      return getWarns();

    case STATUS:
      return getStatus();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERRORS:
      return isSetErrors();
    case WARNS:
      return isSetWarns();
    case STATUS:
      return isSetStatus();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ConfigCheckReport)
      return this.equals((ConfigCheckReport)that);
    return false;
  }

  public boolean equals(ConfigCheckReport that) {
    if (that == null)
      return false;

    boolean this_present_errors = true && this.isSetErrors();
    boolean that_present_errors = true && that.isSetErrors();
    if (this_present_errors || that_present_errors) {
      if (!(this_present_errors && that_present_errors))
        return false;
      if (!this.errors.equals(that.errors))
        return false;
    }

    boolean this_present_warns = true && this.isSetWarns();
    boolean that_present_warns = true && that.isSetWarns();
    if (this_present_warns || that_present_warns) {
      if (!(this_present_warns && that_present_warns))
        return false;
      if (!this.warns.equals(that.warns))
        return false;
    }

    boolean this_present_status = true && this.isSetStatus();
    boolean that_present_status = true && that.isSetStatus();
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (!this.status.equals(that.status))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_errors = true && (isSetErrors());
    list.add(present_errors);
    if (present_errors)
      list.add(errors);

    boolean present_warns = true && (isSetWarns());
    list.add(present_warns);
    if (present_warns)
      list.add(warns);

    boolean present_status = true && (isSetStatus());
    list.add(present_status);
    if (present_status)
      list.add(status.getValue());

    return list.hashCode();
  }

  @Override
  public int compareTo(ConfigCheckReport other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetErrors()).compareTo(other.isSetErrors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errors, other.errors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWarns()).compareTo(other.isSetWarns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWarns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.warns, other.warns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
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
    StringBuilder sb = new StringBuilder("ConfigCheckReport(");
    boolean first = true;

    sb.append("errors:");
    if (this.errors == null) {
      sb.append("null");
    } else {
      sb.append(this.errors);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("warns:");
    if (this.warns == null) {
      sb.append("null");
    } else {
      sb.append(this.warns);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("status:");
    if (this.status == null) {
      sb.append("null");
    } else {
      sb.append(this.status);
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

  private static class ConfigCheckReportStandardSchemeFactory implements SchemeFactory {
    public ConfigCheckReportStandardScheme getScheme() {
      return new ConfigCheckReportStandardScheme();
    }
  }

  private static class ConfigCheckReportStandardScheme extends StandardScheme<ConfigCheckReport> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ConfigCheckReport struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERRORS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map76 = iprot.readMapBegin();
                struct.errors = new HashMap<Scope,List<InconsistentProperty>>(2*_map76.size);
                Scope _key77;
                List<InconsistentProperty> _val78;
                for (int _i79 = 0; _i79 < _map76.size; ++_i79)
                {
                  _key77 = alluxio.thrift.Scope.findByValue(iprot.readI32());
                  {
                    org.apache.thrift.protocol.TList _list80 = iprot.readListBegin();
                    _val78 = new ArrayList<InconsistentProperty>(_list80.size);
                    InconsistentProperty _elem81;
                    for (int _i82 = 0; _i82 < _list80.size; ++_i82)
                    {
                      _elem81 = new InconsistentProperty();
                      _elem81.read(iprot);
                      _val78.add(_elem81);
                    }
                    iprot.readListEnd();
                  }
                  struct.errors.put(_key77, _val78);
                }
                iprot.readMapEnd();
              }
              struct.setErrorsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WARNS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map83 = iprot.readMapBegin();
                struct.warns = new HashMap<Scope,List<InconsistentProperty>>(2*_map83.size);
                Scope _key84;
                List<InconsistentProperty> _val85;
                for (int _i86 = 0; _i86 < _map83.size; ++_i86)
                {
                  _key84 = alluxio.thrift.Scope.findByValue(iprot.readI32());
                  {
                    org.apache.thrift.protocol.TList _list87 = iprot.readListBegin();
                    _val85 = new ArrayList<InconsistentProperty>(_list87.size);
                    InconsistentProperty _elem88;
                    for (int _i89 = 0; _i89 < _list87.size; ++_i89)
                    {
                      _elem88 = new InconsistentProperty();
                      _elem88.read(iprot);
                      _val85.add(_elem88);
                    }
                    iprot.readListEnd();
                  }
                  struct.warns.put(_key84, _val85);
                }
                iprot.readMapEnd();
              }
              struct.setWarnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.status = alluxio.thrift.ConfigStatus.findByValue(iprot.readI32());
              struct.setStatusIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ConfigCheckReport struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.errors != null) {
        oprot.writeFieldBegin(ERRORS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.LIST, struct.errors.size()));
          for (Map.Entry<Scope, List<InconsistentProperty>> _iter90 : struct.errors.entrySet())
          {
            oprot.writeI32(_iter90.getKey().getValue());
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter90.getValue().size()));
              for (InconsistentProperty _iter91 : _iter90.getValue())
              {
                _iter91.write(oprot);
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.warns != null) {
        oprot.writeFieldBegin(WARNS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.LIST, struct.warns.size()));
          for (Map.Entry<Scope, List<InconsistentProperty>> _iter92 : struct.warns.entrySet())
          {
            oprot.writeI32(_iter92.getKey().getValue());
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter92.getValue().size()));
              for (InconsistentProperty _iter93 : _iter92.getValue())
              {
                _iter93.write(oprot);
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.status != null) {
        oprot.writeFieldBegin(STATUS_FIELD_DESC);
        oprot.writeI32(struct.status.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ConfigCheckReportTupleSchemeFactory implements SchemeFactory {
    public ConfigCheckReportTupleScheme getScheme() {
      return new ConfigCheckReportTupleScheme();
    }
  }

  private static class ConfigCheckReportTupleScheme extends TupleScheme<ConfigCheckReport> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ConfigCheckReport struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetErrors()) {
        optionals.set(0);
      }
      if (struct.isSetWarns()) {
        optionals.set(1);
      }
      if (struct.isSetStatus()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetErrors()) {
        {
          oprot.writeI32(struct.errors.size());
          for (Map.Entry<Scope, List<InconsistentProperty>> _iter94 : struct.errors.entrySet())
          {
            oprot.writeI32(_iter94.getKey().getValue());
            {
              oprot.writeI32(_iter94.getValue().size());
              for (InconsistentProperty _iter95 : _iter94.getValue())
              {
                _iter95.write(oprot);
              }
            }
          }
        }
      }
      if (struct.isSetWarns()) {
        {
          oprot.writeI32(struct.warns.size());
          for (Map.Entry<Scope, List<InconsistentProperty>> _iter96 : struct.warns.entrySet())
          {
            oprot.writeI32(_iter96.getKey().getValue());
            {
              oprot.writeI32(_iter96.getValue().size());
              for (InconsistentProperty _iter97 : _iter96.getValue())
              {
                _iter97.write(oprot);
              }
            }
          }
        }
      }
      if (struct.isSetStatus()) {
        oprot.writeI32(struct.status.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ConfigCheckReport struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map98 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
          struct.errors = new HashMap<Scope,List<InconsistentProperty>>(2*_map98.size);
          Scope _key99;
          List<InconsistentProperty> _val100;
          for (int _i101 = 0; _i101 < _map98.size; ++_i101)
          {
            _key99 = alluxio.thrift.Scope.findByValue(iprot.readI32());
            {
              org.apache.thrift.protocol.TList _list102 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
              _val100 = new ArrayList<InconsistentProperty>(_list102.size);
              InconsistentProperty _elem103;
              for (int _i104 = 0; _i104 < _list102.size; ++_i104)
              {
                _elem103 = new InconsistentProperty();
                _elem103.read(iprot);
                _val100.add(_elem103);
              }
            }
            struct.errors.put(_key99, _val100);
          }
        }
        struct.setErrorsIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map105 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
          struct.warns = new HashMap<Scope,List<InconsistentProperty>>(2*_map105.size);
          Scope _key106;
          List<InconsistentProperty> _val107;
          for (int _i108 = 0; _i108 < _map105.size; ++_i108)
          {
            _key106 = alluxio.thrift.Scope.findByValue(iprot.readI32());
            {
              org.apache.thrift.protocol.TList _list109 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
              _val107 = new ArrayList<InconsistentProperty>(_list109.size);
              InconsistentProperty _elem110;
              for (int _i111 = 0; _i111 < _list109.size; ++_i111)
              {
                _elem110 = new InconsistentProperty();
                _elem110.read(iprot);
                _val107.add(_elem110);
              }
            }
            struct.warns.put(_key106, _val107);
          }
        }
        struct.setWarnsIsSet(true);
      }
      if (incoming.get(2)) {
        struct.status = alluxio.thrift.ConfigStatus.findByValue(iprot.readI32());
        struct.setStatusIsSet(true);
      }
    }
  }

}

