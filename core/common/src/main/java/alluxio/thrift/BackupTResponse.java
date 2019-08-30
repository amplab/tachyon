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
public class BackupTResponse implements org.apache.thrift.TBase<BackupTResponse, BackupTResponse._Fields>, java.io.Serializable, Cloneable, Comparable<BackupTResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BackupTResponse");

  private static final org.apache.thrift.protocol.TField BACKUP_URI_FIELD_DESC = new org.apache.thrift.protocol.TField("backupUri", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField HOSTNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("hostname", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ENTRY_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("entryCount", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BackupTResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BackupTResponseTupleSchemeFactory());
  }

  private String backupUri; // required
  private String hostname; // required
  private long entryCount; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BACKUP_URI((short)1, "backupUri"),
    HOSTNAME((short)2, "hostname"),
    ENTRY_COUNT((short)3, "entryCount");

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
        case 1: // BACKUP_URI
          return BACKUP_URI;
        case 2: // HOSTNAME
          return HOSTNAME;
        case 3: // ENTRY_COUNT
          return ENTRY_COUNT;
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
  private static final int __ENTRYCOUNT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BACKUP_URI, new org.apache.thrift.meta_data.FieldMetaData("backupUri", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOSTNAME, new org.apache.thrift.meta_data.FieldMetaData("hostname", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENTRY_COUNT, new org.apache.thrift.meta_data.FieldMetaData("entryCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BackupTResponse.class, metaDataMap);
  }

  public BackupTResponse() {
  }

  public BackupTResponse(
    String backupUri,
    String hostname,
    long entryCount)
  {
    this();
    this.backupUri = backupUri;
    this.hostname = hostname;
    this.entryCount = entryCount;
    setEntryCountIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BackupTResponse(BackupTResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetBackupUri()) {
      this.backupUri = other.backupUri;
    }
    if (other.isSetHostname()) {
      this.hostname = other.hostname;
    }
    this.entryCount = other.entryCount;
  }

  public BackupTResponse deepCopy() {
    return new BackupTResponse(this);
  }

  @Override
  public void clear() {
    this.backupUri = null;
    this.hostname = null;
    setEntryCountIsSet(false);
    this.entryCount = 0;
  }

  public String getBackupUri() {
    return this.backupUri;
  }

  public BackupTResponse setBackupUri(String backupUri) {
    this.backupUri = backupUri;
    return this;
  }

  public void unsetBackupUri() {
    this.backupUri = null;
  }

  /** Returns true if field backupUri is set (has been assigned a value) and false otherwise */
  public boolean isSetBackupUri() {
    return this.backupUri != null;
  }

  public void setBackupUriIsSet(boolean value) {
    if (!value) {
      this.backupUri = null;
    }
  }

  public String getHostname() {
    return this.hostname;
  }

  public BackupTResponse setHostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

  public void unsetHostname() {
    this.hostname = null;
  }

  /** Returns true if field hostname is set (has been assigned a value) and false otherwise */
  public boolean isSetHostname() {
    return this.hostname != null;
  }

  public void setHostnameIsSet(boolean value) {
    if (!value) {
      this.hostname = null;
    }
  }

  public long getEntryCount() {
    return this.entryCount;
  }

  public BackupTResponse setEntryCount(long entryCount) {
    this.entryCount = entryCount;
    setEntryCountIsSet(true);
    return this;
  }

  public void unsetEntryCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ENTRYCOUNT_ISSET_ID);
  }

  /** Returns true if field entryCount is set (has been assigned a value) and false otherwise */
  public boolean isSetEntryCount() {
    return EncodingUtils.testBit(__isset_bitfield, __ENTRYCOUNT_ISSET_ID);
  }

  public void setEntryCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ENTRYCOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BACKUP_URI:
      if (value == null) {
        unsetBackupUri();
      } else {
        setBackupUri((String)value);
      }
      break;

    case HOSTNAME:
      if (value == null) {
        unsetHostname();
      } else {
        setHostname((String)value);
      }
      break;

    case ENTRY_COUNT:
      if (value == null) {
        unsetEntryCount();
      } else {
        setEntryCount((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BACKUP_URI:
      return getBackupUri();

    case HOSTNAME:
      return getHostname();

    case ENTRY_COUNT:
      return getEntryCount();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BACKUP_URI:
      return isSetBackupUri();
    case HOSTNAME:
      return isSetHostname();
    case ENTRY_COUNT:
      return isSetEntryCount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BackupTResponse)
      return this.equals((BackupTResponse)that);
    return false;
  }

  public boolean equals(BackupTResponse that) {
    if (that == null)
      return false;

    boolean this_present_backupUri = true && this.isSetBackupUri();
    boolean that_present_backupUri = true && that.isSetBackupUri();
    if (this_present_backupUri || that_present_backupUri) {
      if (!(this_present_backupUri && that_present_backupUri))
        return false;
      if (!this.backupUri.equals(that.backupUri))
        return false;
    }

    boolean this_present_hostname = true && this.isSetHostname();
    boolean that_present_hostname = true && that.isSetHostname();
    if (this_present_hostname || that_present_hostname) {
      if (!(this_present_hostname && that_present_hostname))
        return false;
      if (!this.hostname.equals(that.hostname))
        return false;
    }

    boolean this_present_entryCount = true;
    boolean that_present_entryCount = true;
    if (this_present_entryCount || that_present_entryCount) {
      if (!(this_present_entryCount && that_present_entryCount))
        return false;
      if (this.entryCount != that.entryCount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_backupUri = true && (isSetBackupUri());
    list.add(present_backupUri);
    if (present_backupUri)
      list.add(backupUri);

    boolean present_hostname = true && (isSetHostname());
    list.add(present_hostname);
    if (present_hostname)
      list.add(hostname);

    boolean present_entryCount = true;
    list.add(present_entryCount);
    if (present_entryCount)
      list.add(entryCount);

    return list.hashCode();
  }

  @Override
  public int compareTo(BackupTResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetBackupUri()).compareTo(other.isSetBackupUri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBackupUri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.backupUri, other.backupUri);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHostname()).compareTo(other.isSetHostname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostname, other.hostname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEntryCount()).compareTo(other.isSetEntryCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEntryCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.entryCount, other.entryCount);
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
    StringBuilder sb = new StringBuilder("BackupTResponse(");
    boolean first = true;

    sb.append("backupUri:");
    if (this.backupUri == null) {
      sb.append("null");
    } else {
      sb.append(this.backupUri);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("hostname:");
    if (this.hostname == null) {
      sb.append("null");
    } else {
      sb.append(this.hostname);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("entryCount:");
    sb.append(this.entryCount);
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

  private static class BackupTResponseStandardSchemeFactory implements SchemeFactory {
    public BackupTResponseStandardScheme getScheme() {
      return new BackupTResponseStandardScheme();
    }
  }

  private static class BackupTResponseStandardScheme extends StandardScheme<BackupTResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BackupTResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BACKUP_URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.backupUri = iprot.readString();
              struct.setBackupUriIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HOSTNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.hostname = iprot.readString();
              struct.setHostnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ENTRY_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.entryCount = iprot.readI64();
              struct.setEntryCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BackupTResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.backupUri != null) {
        oprot.writeFieldBegin(BACKUP_URI_FIELD_DESC);
        oprot.writeString(struct.backupUri);
        oprot.writeFieldEnd();
      }
      if (struct.hostname != null) {
        oprot.writeFieldBegin(HOSTNAME_FIELD_DESC);
        oprot.writeString(struct.hostname);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ENTRY_COUNT_FIELD_DESC);
      oprot.writeI64(struct.entryCount);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BackupTResponseTupleSchemeFactory implements SchemeFactory {
    public BackupTResponseTupleScheme getScheme() {
      return new BackupTResponseTupleScheme();
    }
  }

  private static class BackupTResponseTupleScheme extends TupleScheme<BackupTResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BackupTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetBackupUri()) {
        optionals.set(0);
      }
      if (struct.isSetHostname()) {
        optionals.set(1);
      }
      if (struct.isSetEntryCount()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetBackupUri()) {
        oprot.writeString(struct.backupUri);
      }
      if (struct.isSetHostname()) {
        oprot.writeString(struct.hostname);
      }
      if (struct.isSetEntryCount()) {
        oprot.writeI64(struct.entryCount);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BackupTResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.backupUri = iprot.readString();
        struct.setBackupUriIsSet(true);
      }
      if (incoming.get(1)) {
        struct.hostname = iprot.readString();
        struct.setHostnameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.entryCount = iprot.readI64();
        struct.setEntryCountIsSet(true);
      }
    }
  }

}

