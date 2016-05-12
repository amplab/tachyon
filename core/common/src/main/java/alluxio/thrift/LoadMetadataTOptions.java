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
public class LoadMetadataTOptions implements org.apache.thrift.TBase<LoadMetadataTOptions, LoadMetadataTOptions._Fields>, java.io.Serializable, Cloneable, Comparable<LoadMetadataTOptions> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LoadMetadataTOptions");

  private static final org.apache.thrift.protocol.TField RECURSIVE_FIELD_DESC = new org.apache.thrift.protocol.TField("recursive", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField LOAD_DIRECT_CHILDREN_FIELD_DESC = new org.apache.thrift.protocol.TField("loadDirectChildren", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LoadMetadataTOptionsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LoadMetadataTOptionsTupleSchemeFactory());
  }

  private boolean recursive; // optional
  private boolean loadDirectChildren; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RECURSIVE((short)1, "recursive"),
    LOAD_DIRECT_CHILDREN((short)2, "loadDirectChildren");

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
        case 1: // RECURSIVE
          return RECURSIVE;
        case 2: // LOAD_DIRECT_CHILDREN
          return LOAD_DIRECT_CHILDREN;
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
  private static final int __RECURSIVE_ISSET_ID = 0;
  private static final int __LOADDIRECTCHILDREN_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.RECURSIVE,_Fields.LOAD_DIRECT_CHILDREN};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RECURSIVE, new org.apache.thrift.meta_data.FieldMetaData("recursive", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.LOAD_DIRECT_CHILDREN, new org.apache.thrift.meta_data.FieldMetaData("loadDirectChildren", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LoadMetadataTOptions.class, metaDataMap);
  }

  public LoadMetadataTOptions() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LoadMetadataTOptions(LoadMetadataTOptions other) {
    __isset_bitfield = other.__isset_bitfield;
    this.recursive = other.recursive;
    this.loadDirectChildren = other.loadDirectChildren;
  }

  public LoadMetadataTOptions deepCopy() {
    return new LoadMetadataTOptions(this);
  }

  @Override
  public void clear() {
    setRecursiveIsSet(false);
    this.recursive = false;
    setLoadDirectChildrenIsSet(false);
    this.loadDirectChildren = false;
  }

  public boolean isRecursive() {
    return this.recursive;
  }

  public LoadMetadataTOptions setRecursive(boolean recursive) {
    this.recursive = recursive;
    setRecursiveIsSet(true);
    return this;
  }

  public void unsetRecursive() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RECURSIVE_ISSET_ID);
  }

  /** Returns true if field recursive is set (has been assigned a value) and false otherwise */
  public boolean isSetRecursive() {
    return EncodingUtils.testBit(__isset_bitfield, __RECURSIVE_ISSET_ID);
  }

  public void setRecursiveIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RECURSIVE_ISSET_ID, value);
  }

  public boolean isLoadDirectChildren() {
    return this.loadDirectChildren;
  }

  public LoadMetadataTOptions setLoadDirectChildren(boolean loadDirectChildren) {
    this.loadDirectChildren = loadDirectChildren;
    setLoadDirectChildrenIsSet(true);
    return this;
  }

  public void unsetLoadDirectChildren() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LOADDIRECTCHILDREN_ISSET_ID);
  }

  /** Returns true if field loadDirectChildren is set (has been assigned a value) and false otherwise */
  public boolean isSetLoadDirectChildren() {
    return EncodingUtils.testBit(__isset_bitfield, __LOADDIRECTCHILDREN_ISSET_ID);
  }

  public void setLoadDirectChildrenIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LOADDIRECTCHILDREN_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RECURSIVE:
      if (value == null) {
        unsetRecursive();
      } else {
        setRecursive((Boolean)value);
      }
      break;

    case LOAD_DIRECT_CHILDREN:
      if (value == null) {
        unsetLoadDirectChildren();
      } else {
        setLoadDirectChildren((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RECURSIVE:
      return isRecursive();

    case LOAD_DIRECT_CHILDREN:
      return isLoadDirectChildren();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RECURSIVE:
      return isSetRecursive();
    case LOAD_DIRECT_CHILDREN:
      return isSetLoadDirectChildren();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LoadMetadataTOptions)
      return this.equals((LoadMetadataTOptions)that);
    return false;
  }

  public boolean equals(LoadMetadataTOptions that) {
    if (that == null)
      return false;

    boolean this_present_recursive = true && this.isSetRecursive();
    boolean that_present_recursive = true && that.isSetRecursive();
    if (this_present_recursive || that_present_recursive) {
      if (!(this_present_recursive && that_present_recursive))
        return false;
      if (this.recursive != that.recursive)
        return false;
    }

    boolean this_present_loadDirectChildren = true && this.isSetLoadDirectChildren();
    boolean that_present_loadDirectChildren = true && that.isSetLoadDirectChildren();
    if (this_present_loadDirectChildren || that_present_loadDirectChildren) {
      if (!(this_present_loadDirectChildren && that_present_loadDirectChildren))
        return false;
      if (this.loadDirectChildren != that.loadDirectChildren)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_recursive = true && (isSetRecursive());
    list.add(present_recursive);
    if (present_recursive)
      list.add(recursive);

    boolean present_loadDirectChildren = true && (isSetLoadDirectChildren());
    list.add(present_loadDirectChildren);
    if (present_loadDirectChildren)
      list.add(loadDirectChildren);

    return list.hashCode();
  }

  @Override
  public int compareTo(LoadMetadataTOptions other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRecursive()).compareTo(other.isSetRecursive());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRecursive()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.recursive, other.recursive);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLoadDirectChildren()).compareTo(other.isSetLoadDirectChildren());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoadDirectChildren()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loadDirectChildren, other.loadDirectChildren);
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
    StringBuilder sb = new StringBuilder("LoadMetadataTOptions(");
    boolean first = true;

    if (isSetRecursive()) {
      sb.append("recursive:");
      sb.append(this.recursive);
      first = false;
    }
    if (isSetLoadDirectChildren()) {
      if (!first) sb.append(", ");
      sb.append("loadDirectChildren:");
      sb.append(this.loadDirectChildren);
      first = false;
    }
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

  private static class LoadMetadataTOptionsStandardSchemeFactory implements SchemeFactory {
    public LoadMetadataTOptionsStandardScheme getScheme() {
      return new LoadMetadataTOptionsStandardScheme();
    }
  }

  private static class LoadMetadataTOptionsStandardScheme extends StandardScheme<LoadMetadataTOptions> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LoadMetadataTOptions struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RECURSIVE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.recursive = iprot.readBool();
              struct.setRecursiveIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LOAD_DIRECT_CHILDREN
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.loadDirectChildren = iprot.readBool();
              struct.setLoadDirectChildrenIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, LoadMetadataTOptions struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetRecursive()) {
        oprot.writeFieldBegin(RECURSIVE_FIELD_DESC);
        oprot.writeBool(struct.recursive);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLoadDirectChildren()) {
        oprot.writeFieldBegin(LOAD_DIRECT_CHILDREN_FIELD_DESC);
        oprot.writeBool(struct.loadDirectChildren);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LoadMetadataTOptionsTupleSchemeFactory implements SchemeFactory {
    public LoadMetadataTOptionsTupleScheme getScheme() {
      return new LoadMetadataTOptionsTupleScheme();
    }
  }

  private static class LoadMetadataTOptionsTupleScheme extends TupleScheme<LoadMetadataTOptions> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LoadMetadataTOptions struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRecursive()) {
        optionals.set(0);
      }
      if (struct.isSetLoadDirectChildren()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetRecursive()) {
        oprot.writeBool(struct.recursive);
      }
      if (struct.isSetLoadDirectChildren()) {
        oprot.writeBool(struct.loadDirectChildren);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LoadMetadataTOptions struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.recursive = iprot.readBool();
        struct.setRecursiveIsSet(true);
      }
      if (incoming.get(1)) {
        struct.loadDirectChildren = iprot.readBool();
        struct.setLoadDirectChildrenIsSet(true);
      }
    }
  }

}

