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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-05-12")
public class PersistFile implements org.apache.thrift.TBase<PersistFile, PersistFile._Fields>, java.io.Serializable, Cloneable, Comparable<PersistFile> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PersistFile");

  private static final org.apache.thrift.protocol.TField FILE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("fileId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField BLOCK_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("blockIds", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PersistFileStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PersistFileTupleSchemeFactory());
  }

  private long fileId; // required
  private List<Long> blockIds; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILE_ID((short)1, "fileId"),
    BLOCK_IDS((short)2, "blockIds");

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
        case 1: // FILE_ID
          return FILE_ID;
        case 2: // BLOCK_IDS
          return BLOCK_IDS;
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
  private static final int __FILEID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILE_ID, new org.apache.thrift.meta_data.FieldMetaData("fileId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.BLOCK_IDS, new org.apache.thrift.meta_data.FieldMetaData("blockIds", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PersistFile.class, metaDataMap);
  }

  public PersistFile() {
  }

  public PersistFile(
    long fileId,
    List<Long> blockIds)
  {
    this();
    this.fileId = fileId;
    setFileIdIsSet(true);
    this.blockIds = blockIds;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PersistFile(PersistFile other) {
    __isset_bitfield = other.__isset_bitfield;
    this.fileId = other.fileId;
    if (other.isSetBlockIds()) {
      List<Long> __this__blockIds = new ArrayList<Long>(other.blockIds);
      this.blockIds = __this__blockIds;
    }
  }

  public PersistFile deepCopy() {
    return new PersistFile(this);
  }

  @Override
  public void clear() {
    setFileIdIsSet(false);
    this.fileId = 0;
    this.blockIds = null;
  }

  public long getFileId() {
    return this.fileId;
  }

  public PersistFile setFileId(long fileId) {
    this.fileId = fileId;
    setFileIdIsSet(true);
    return this;
  }

  public void unsetFileId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FILEID_ISSET_ID);
  }

  /** Returns true if field fileId is set (has been assigned a value) and false otherwise */
  public boolean isSetFileId() {
    return EncodingUtils.testBit(__isset_bitfield, __FILEID_ISSET_ID);
  }

  public void setFileIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FILEID_ISSET_ID, value);
  }

  public int getBlockIdsSize() {
    return (this.blockIds == null) ? 0 : this.blockIds.size();
  }

  public java.util.Iterator<Long> getBlockIdsIterator() {
    return (this.blockIds == null) ? null : this.blockIds.iterator();
  }

  public void addToBlockIds(long elem) {
    if (this.blockIds == null) {
      this.blockIds = new ArrayList<Long>();
    }
    this.blockIds.add(elem);
  }

  public List<Long> getBlockIds() {
    return this.blockIds;
  }

  public PersistFile setBlockIds(List<Long> blockIds) {
    this.blockIds = blockIds;
    return this;
  }

  public void unsetBlockIds() {
    this.blockIds = null;
  }

  /** Returns true if field blockIds is set (has been assigned a value) and false otherwise */
  public boolean isSetBlockIds() {
    return this.blockIds != null;
  }

  public void setBlockIdsIsSet(boolean value) {
    if (!value) {
      this.blockIds = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FILE_ID:
      if (value == null) {
        unsetFileId();
      } else {
        setFileId((Long)value);
      }
      break;

    case BLOCK_IDS:
      if (value == null) {
        unsetBlockIds();
      } else {
        setBlockIds((List<Long>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FILE_ID:
      return getFileId();

    case BLOCK_IDS:
      return getBlockIds();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FILE_ID:
      return isSetFileId();
    case BLOCK_IDS:
      return isSetBlockIds();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PersistFile)
      return this.equals((PersistFile)that);
    return false;
  }

  public boolean equals(PersistFile that) {
    if (that == null)
      return false;

    boolean this_present_fileId = true;
    boolean that_present_fileId = true;
    if (this_present_fileId || that_present_fileId) {
      if (!(this_present_fileId && that_present_fileId))
        return false;
      if (this.fileId != that.fileId)
        return false;
    }

    boolean this_present_blockIds = true && this.isSetBlockIds();
    boolean that_present_blockIds = true && that.isSetBlockIds();
    if (this_present_blockIds || that_present_blockIds) {
      if (!(this_present_blockIds && that_present_blockIds))
        return false;
      if (!this.blockIds.equals(that.blockIds))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_fileId = true;
    list.add(present_fileId);
    if (present_fileId)
      list.add(fileId);

    boolean present_blockIds = true && (isSetBlockIds());
    list.add(present_blockIds);
    if (present_blockIds)
      list.add(blockIds);

    return list.hashCode();
  }

  @Override
  public int compareTo(PersistFile other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFileId()).compareTo(other.isSetFileId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileId, other.fileId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBlockIds()).compareTo(other.isSetBlockIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlockIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.blockIds, other.blockIds);
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
    StringBuilder sb = new StringBuilder("PersistFile(");
    boolean first = true;

    sb.append("fileId:");
    sb.append(this.fileId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("blockIds:");
    if (this.blockIds == null) {
      sb.append("null");
    } else {
      sb.append(this.blockIds);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PersistFileStandardSchemeFactory implements SchemeFactory {
    public PersistFileStandardScheme getScheme() {
      return new PersistFileStandardScheme();
    }
  }

  private static class PersistFileStandardScheme extends StandardScheme<PersistFile> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PersistFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.fileId = iprot.readI64();
              struct.setFileIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BLOCK_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list42 = iprot.readListBegin();
                struct.blockIds = new ArrayList<Long>(_list42.size);
                long _elem43;
                for (int _i44 = 0; _i44 < _list42.size; ++_i44)
                {
                  _elem43 = iprot.readI64();
                  struct.blockIds.add(_elem43);
                }
                iprot.readListEnd();
              }
              struct.setBlockIdsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PersistFile struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(FILE_ID_FIELD_DESC);
      oprot.writeI64(struct.fileId);
      oprot.writeFieldEnd();
      if (struct.blockIds != null) {
        oprot.writeFieldBegin(BLOCK_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.blockIds.size()));
          for (long _iter45 : struct.blockIds)
          {
            oprot.writeI64(_iter45);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PersistFileTupleSchemeFactory implements SchemeFactory {
    public PersistFileTupleScheme getScheme() {
      return new PersistFileTupleScheme();
    }
  }

  private static class PersistFileTupleScheme extends TupleScheme<PersistFile> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PersistFile struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFileId()) {
        optionals.set(0);
      }
      if (struct.isSetBlockIds()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetFileId()) {
        oprot.writeI64(struct.fileId);
      }
      if (struct.isSetBlockIds()) {
        {
          oprot.writeI32(struct.blockIds.size());
          for (long _iter46 : struct.blockIds)
          {
            oprot.writeI64(_iter46);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PersistFile struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.fileId = iprot.readI64();
        struct.setFileIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list47 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.blockIds = new ArrayList<Long>(_list47.size);
          long _elem48;
          for (int _i49 = 0; _i49 < _list47.size; ++_i49)
          {
            _elem48 = iprot.readI64();
            struct.blockIds.add(_elem48);
          }
        }
        struct.setBlockIdsIsSet(true);
      }
    }
  }

}

