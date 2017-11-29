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
public class UfsFileTStatus implements org.apache.thrift.TBase<UfsFileTStatus, UfsFileTStatus._Fields>, java.io.Serializable, Cloneable, Comparable<UfsFileTStatus> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UfsFileTStatus");

  private static final org.apache.thrift.protocol.TField COMMON_STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("commonStatus", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField LENGTH_FIELD_DESC = new org.apache.thrift.protocol.TField("length", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField LAST_MODIFICATION_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("lastModificationTimeMs", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new UfsFileTStatusStandardSchemeFactory());
    schemes.put(TupleScheme.class, new UfsFileTStatusTupleSchemeFactory());
  }

  private UfsTStatus commonStatus; // optional
  private long length; // optional
  private long lastModificationTimeMs; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMMON_STATUS((short)1, "commonStatus"),
    LENGTH((short)2, "length"),
    LAST_MODIFICATION_TIME_MS((short)3, "lastModificationTimeMs");

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
        case 1: // COMMON_STATUS
          return COMMON_STATUS;
        case 2: // LENGTH
          return LENGTH;
        case 3: // LAST_MODIFICATION_TIME_MS
          return LAST_MODIFICATION_TIME_MS;
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
  private static final int __LENGTH_ISSET_ID = 0;
  private static final int __LASTMODIFICATIONTIMEMS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.COMMON_STATUS,_Fields.LENGTH,_Fields.LAST_MODIFICATION_TIME_MS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMMON_STATUS, new org.apache.thrift.meta_data.FieldMetaData("commonStatus", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, UfsTStatus.class)));
    tmpMap.put(_Fields.LENGTH, new org.apache.thrift.meta_data.FieldMetaData("length", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LAST_MODIFICATION_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("lastModificationTimeMs", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UfsFileTStatus.class, metaDataMap);
  }

  public UfsFileTStatus() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UfsFileTStatus(UfsFileTStatus other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCommonStatus()) {
      this.commonStatus = new UfsTStatus(other.commonStatus);
    }
    this.length = other.length;
    this.lastModificationTimeMs = other.lastModificationTimeMs;
  }

  public UfsFileTStatus deepCopy() {
    return new UfsFileTStatus(this);
  }

  @Override
  public void clear() {
    this.commonStatus = null;
    setLengthIsSet(false);
    this.length = 0;
    setLastModificationTimeMsIsSet(false);
    this.lastModificationTimeMs = 0;
  }

  public UfsTStatus getCommonStatus() {
    return this.commonStatus;
  }

  public UfsFileTStatus setCommonStatus(UfsTStatus commonStatus) {
    this.commonStatus = commonStatus;
    return this;
  }

  public void unsetCommonStatus() {
    this.commonStatus = null;
  }

  /** Returns true if field commonStatus is set (has been assigned a value) and false otherwise */
  public boolean isSetCommonStatus() {
    return this.commonStatus != null;
  }

  public void setCommonStatusIsSet(boolean value) {
    if (!value) {
      this.commonStatus = null;
    }
  }

  public long getLength() {
    return this.length;
  }

  public UfsFileTStatus setLength(long length) {
    this.length = length;
    setLengthIsSet(true);
    return this;
  }

  public void unsetLength() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LENGTH_ISSET_ID);
  }

  /** Returns true if field length is set (has been assigned a value) and false otherwise */
  public boolean isSetLength() {
    return EncodingUtils.testBit(__isset_bitfield, __LENGTH_ISSET_ID);
  }

  public void setLengthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LENGTH_ISSET_ID, value);
  }

  public long getLastModificationTimeMs() {
    return this.lastModificationTimeMs;
  }

  public UfsFileTStatus setLastModificationTimeMs(long lastModificationTimeMs) {
    this.lastModificationTimeMs = lastModificationTimeMs;
    setLastModificationTimeMsIsSet(true);
    return this;
  }

  public void unsetLastModificationTimeMs() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTMODIFICATIONTIMEMS_ISSET_ID);
  }

  /** Returns true if field lastModificationTimeMs is set (has been assigned a value) and false otherwise */
  public boolean isSetLastModificationTimeMs() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTMODIFICATIONTIMEMS_ISSET_ID);
  }

  public void setLastModificationTimeMsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LASTMODIFICATIONTIMEMS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMMON_STATUS:
      if (value == null) {
        unsetCommonStatus();
      } else {
        setCommonStatus((UfsTStatus)value);
      }
      break;

    case LENGTH:
      if (value == null) {
        unsetLength();
      } else {
        setLength((Long)value);
      }
      break;

    case LAST_MODIFICATION_TIME_MS:
      if (value == null) {
        unsetLastModificationTimeMs();
      } else {
        setLastModificationTimeMs((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMMON_STATUS:
      return getCommonStatus();

    case LENGTH:
      return getLength();

    case LAST_MODIFICATION_TIME_MS:
      return getLastModificationTimeMs();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMMON_STATUS:
      return isSetCommonStatus();
    case LENGTH:
      return isSetLength();
    case LAST_MODIFICATION_TIME_MS:
      return isSetLastModificationTimeMs();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof UfsFileTStatus)
      return this.equals((UfsFileTStatus)that);
    return false;
  }

  public boolean equals(UfsFileTStatus that) {
    if (that == null)
      return false;

    boolean this_present_commonStatus = true && this.isSetCommonStatus();
    boolean that_present_commonStatus = true && that.isSetCommonStatus();
    if (this_present_commonStatus || that_present_commonStatus) {
      if (!(this_present_commonStatus && that_present_commonStatus))
        return false;
      if (!this.commonStatus.equals(that.commonStatus))
        return false;
    }

    boolean this_present_length = true && this.isSetLength();
    boolean that_present_length = true && that.isSetLength();
    if (this_present_length || that_present_length) {
      if (!(this_present_length && that_present_length))
        return false;
      if (this.length != that.length)
        return false;
    }

    boolean this_present_lastModificationTimeMs = true && this.isSetLastModificationTimeMs();
    boolean that_present_lastModificationTimeMs = true && that.isSetLastModificationTimeMs();
    if (this_present_lastModificationTimeMs || that_present_lastModificationTimeMs) {
      if (!(this_present_lastModificationTimeMs && that_present_lastModificationTimeMs))
        return false;
      if (this.lastModificationTimeMs != that.lastModificationTimeMs)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_commonStatus = true && (isSetCommonStatus());
    list.add(present_commonStatus);
    if (present_commonStatus)
      list.add(commonStatus);

    boolean present_length = true && (isSetLength());
    list.add(present_length);
    if (present_length)
      list.add(length);

    boolean present_lastModificationTimeMs = true && (isSetLastModificationTimeMs());
    list.add(present_lastModificationTimeMs);
    if (present_lastModificationTimeMs)
      list.add(lastModificationTimeMs);

    return list.hashCode();
  }

  @Override
  public int compareTo(UfsFileTStatus other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCommonStatus()).compareTo(other.isSetCommonStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommonStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.commonStatus, other.commonStatus);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLength()).compareTo(other.isSetLength());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLength()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.length, other.length);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastModificationTimeMs()).compareTo(other.isSetLastModificationTimeMs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastModificationTimeMs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastModificationTimeMs, other.lastModificationTimeMs);
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
    StringBuilder sb = new StringBuilder("UfsFileTStatus(");
    boolean first = true;

    if (isSetCommonStatus()) {
      sb.append("commonStatus:");
      if (this.commonStatus == null) {
        sb.append("null");
      } else {
        sb.append(this.commonStatus);
      }
      first = false;
    }
    if (isSetLength()) {
      if (!first) sb.append(", ");
      sb.append("length:");
      sb.append(this.length);
      first = false;
    }
    if (isSetLastModificationTimeMs()) {
      if (!first) sb.append(", ");
      sb.append("lastModificationTimeMs:");
      sb.append(this.lastModificationTimeMs);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (commonStatus != null) {
      commonStatus.validate();
    }
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

  private static class UfsFileTStatusStandardSchemeFactory implements SchemeFactory {
    public UfsFileTStatusStandardScheme getScheme() {
      return new UfsFileTStatusStandardScheme();
    }
  }

  private static class UfsFileTStatusStandardScheme extends StandardScheme<UfsFileTStatus> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UfsFileTStatus struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMMON_STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.commonStatus = new UfsTStatus();
              struct.commonStatus.read(iprot);
              struct.setCommonStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LENGTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.length = iprot.readI64();
              struct.setLengthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LAST_MODIFICATION_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastModificationTimeMs = iprot.readI64();
              struct.setLastModificationTimeMsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, UfsFileTStatus struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.commonStatus != null) {
        if (struct.isSetCommonStatus()) {
          oprot.writeFieldBegin(COMMON_STATUS_FIELD_DESC);
          struct.commonStatus.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetLength()) {
        oprot.writeFieldBegin(LENGTH_FIELD_DESC);
        oprot.writeI64(struct.length);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLastModificationTimeMs()) {
        oprot.writeFieldBegin(LAST_MODIFICATION_TIME_MS_FIELD_DESC);
        oprot.writeI64(struct.lastModificationTimeMs);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UfsFileTStatusTupleSchemeFactory implements SchemeFactory {
    public UfsFileTStatusTupleScheme getScheme() {
      return new UfsFileTStatusTupleScheme();
    }
  }

  private static class UfsFileTStatusTupleScheme extends TupleScheme<UfsFileTStatus> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UfsFileTStatus struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCommonStatus()) {
        optionals.set(0);
      }
      if (struct.isSetLength()) {
        optionals.set(1);
      }
      if (struct.isSetLastModificationTimeMs()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetCommonStatus()) {
        struct.commonStatus.write(oprot);
      }
      if (struct.isSetLength()) {
        oprot.writeI64(struct.length);
      }
      if (struct.isSetLastModificationTimeMs()) {
        oprot.writeI64(struct.lastModificationTimeMs);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UfsFileTStatus struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.commonStatus = new UfsTStatus();
        struct.commonStatus.read(iprot);
        struct.setCommonStatusIsSet(true);
      }
      if (incoming.get(1)) {
        struct.length = iprot.readI64();
        struct.setLengthIsSet(true);
      }
      if (incoming.get(2)) {
        struct.lastModificationTimeMs = iprot.readI64();
        struct.setLastModificationTimeMsIsSet(true);
      }
    }
  }

}

