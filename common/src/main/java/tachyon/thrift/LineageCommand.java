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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-9-22")
public class LineageCommand implements org.apache.thrift.TBase<LineageCommand, LineageCommand._Fields>, java.io.Serializable, Cloneable, Comparable<LineageCommand> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LineageCommand");

  private static final org.apache.thrift.protocol.TField M_COMMAND_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("mCommandType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField M_CHECKPOINT_FILES_FIELD_DESC = new org.apache.thrift.protocol.TField("mCheckpointFiles", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LineageCommandStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LineageCommandTupleSchemeFactory());
  }

  /**
   * 
   * @see CommandType
   */
  public CommandType mCommandType; // required
  public List<CheckpointFile> mCheckpointFiles; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see CommandType
     */
    M_COMMAND_TYPE((short)1, "mCommandType"),
    M_CHECKPOINT_FILES((short)2, "mCheckpointFiles");

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
        case 1: // M_COMMAND_TYPE
          return M_COMMAND_TYPE;
        case 2: // M_CHECKPOINT_FILES
          return M_CHECKPOINT_FILES;
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
    tmpMap.put(_Fields.M_COMMAND_TYPE, new org.apache.thrift.meta_data.FieldMetaData("mCommandType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, CommandType.class)));
    tmpMap.put(_Fields.M_CHECKPOINT_FILES, new org.apache.thrift.meta_data.FieldMetaData("mCheckpointFiles", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "CheckpointFile"))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LineageCommand.class, metaDataMap);
  }

  public LineageCommand() {
  }

  public LineageCommand(
    CommandType mCommandType,
    List<CheckpointFile> mCheckpointFiles)
  {
    this();
    this.mCommandType = mCommandType;
    this.mCheckpointFiles = mCheckpointFiles;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LineageCommand(LineageCommand other) {
    if (other.isSetMCommandType()) {
      this.mCommandType = other.mCommandType;
    }
    if (other.isSetMCheckpointFiles()) {
      List<CheckpointFile> __this__mCheckpointFiles = new ArrayList<CheckpointFile>(other.mCheckpointFiles.size());
      for (CheckpointFile other_element : other.mCheckpointFiles) {
        __this__mCheckpointFiles.add(other_element);
      }
      this.mCheckpointFiles = __this__mCheckpointFiles;
    }
  }

  public LineageCommand deepCopy() {
    return new LineageCommand(this);
  }

  @Override
  public void clear() {
    this.mCommandType = null;
    this.mCheckpointFiles = null;
  }

  /**
   * 
   * @see CommandType
   */
  public CommandType getMCommandType() {
    return this.mCommandType;
  }

  /**
   * 
   * @see CommandType
   */
  public LineageCommand setMCommandType(CommandType mCommandType) {
    this.mCommandType = mCommandType;
    return this;
  }

  public void unsetMCommandType() {
    this.mCommandType = null;
  }

  /** Returns true if field mCommandType is set (has been assigned a value) and false otherwise */
  public boolean isSetMCommandType() {
    return this.mCommandType != null;
  }

  public void setMCommandTypeIsSet(boolean value) {
    if (!value) {
      this.mCommandType = null;
    }
  }

  public int getMCheckpointFilesSize() {
    return (this.mCheckpointFiles == null) ? 0 : this.mCheckpointFiles.size();
  }

  public java.util.Iterator<CheckpointFile> getMCheckpointFilesIterator() {
    return (this.mCheckpointFiles == null) ? null : this.mCheckpointFiles.iterator();
  }

  public void addToMCheckpointFiles(CheckpointFile elem) {
    if (this.mCheckpointFiles == null) {
      this.mCheckpointFiles = new ArrayList<CheckpointFile>();
    }
    this.mCheckpointFiles.add(elem);
  }

  public List<CheckpointFile> getMCheckpointFiles() {
    return this.mCheckpointFiles;
  }

  public LineageCommand setMCheckpointFiles(List<CheckpointFile> mCheckpointFiles) {
    this.mCheckpointFiles = mCheckpointFiles;
    return this;
  }

  public void unsetMCheckpointFiles() {
    this.mCheckpointFiles = null;
  }

  /** Returns true if field mCheckpointFiles is set (has been assigned a value) and false otherwise */
  public boolean isSetMCheckpointFiles() {
    return this.mCheckpointFiles != null;
  }

  public void setMCheckpointFilesIsSet(boolean value) {
    if (!value) {
      this.mCheckpointFiles = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case M_COMMAND_TYPE:
      if (value == null) {
        unsetMCommandType();
      } else {
        setMCommandType((CommandType)value);
      }
      break;

    case M_CHECKPOINT_FILES:
      if (value == null) {
        unsetMCheckpointFiles();
      } else {
        setMCheckpointFiles((List<CheckpointFile>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case M_COMMAND_TYPE:
      return getMCommandType();

    case M_CHECKPOINT_FILES:
      return getMCheckpointFiles();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case M_COMMAND_TYPE:
      return isSetMCommandType();
    case M_CHECKPOINT_FILES:
      return isSetMCheckpointFiles();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LineageCommand)
      return this.equals((LineageCommand)that);
    return false;
  }

  public boolean equals(LineageCommand that) {
    if (that == null)
      return false;

    boolean this_present_mCommandType = true && this.isSetMCommandType();
    boolean that_present_mCommandType = true && that.isSetMCommandType();
    if (this_present_mCommandType || that_present_mCommandType) {
      if (!(this_present_mCommandType && that_present_mCommandType))
        return false;
      if (!this.mCommandType.equals(that.mCommandType))
        return false;
    }

    boolean this_present_mCheckpointFiles = true && this.isSetMCheckpointFiles();
    boolean that_present_mCheckpointFiles = true && that.isSetMCheckpointFiles();
    if (this_present_mCheckpointFiles || that_present_mCheckpointFiles) {
      if (!(this_present_mCheckpointFiles && that_present_mCheckpointFiles))
        return false;
      if (!this.mCheckpointFiles.equals(that.mCheckpointFiles))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_mCommandType = true && (isSetMCommandType());
    list.add(present_mCommandType);
    if (present_mCommandType)
      list.add(mCommandType.getValue());

    boolean present_mCheckpointFiles = true && (isSetMCheckpointFiles());
    list.add(present_mCheckpointFiles);
    if (present_mCheckpointFiles)
      list.add(mCheckpointFiles);

    return list.hashCode();
  }

  @Override
  public int compareTo(LineageCommand other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMCommandType()).compareTo(other.isSetMCommandType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMCommandType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mCommandType, other.mCommandType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMCheckpointFiles()).compareTo(other.isSetMCheckpointFiles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMCheckpointFiles()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mCheckpointFiles, other.mCheckpointFiles);
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
    StringBuilder sb = new StringBuilder("LineageCommand(");
    boolean first = true;

    sb.append("mCommandType:");
    if (this.mCommandType == null) {
      sb.append("null");
    } else {
      sb.append(this.mCommandType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("mCheckpointFiles:");
    if (this.mCheckpointFiles == null) {
      sb.append("null");
    } else {
      sb.append(this.mCheckpointFiles);
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

  private static class LineageCommandStandardSchemeFactory implements SchemeFactory {
    public LineageCommandStandardScheme getScheme() {
      return new LineageCommandStandardScheme();
    }
  }

  private static class LineageCommandStandardScheme extends StandardScheme<LineageCommand> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LineageCommand struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // M_COMMAND_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.mCommandType = tachyon.thrift.CommandType.findByValue(iprot.readI32());
              struct.setMCommandTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // M_CHECKPOINT_FILES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list56 = iprot.readListBegin();
                struct.mCheckpointFiles = new ArrayList<CheckpointFile>(_list56.size);
                CheckpointFile _elem57;
                for (int _i58 = 0; _i58 < _list56.size; ++_i58)
                {
                  _elem57 = new CheckpointFile();
                  _elem57.read(iprot);
                  struct.mCheckpointFiles.add(_elem57);
                }
                iprot.readListEnd();
              }
              struct.setMCheckpointFilesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, LineageCommand struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.mCommandType != null) {
        oprot.writeFieldBegin(M_COMMAND_TYPE_FIELD_DESC);
        oprot.writeI32(struct.mCommandType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.mCheckpointFiles != null) {
        oprot.writeFieldBegin(M_CHECKPOINT_FILES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.mCheckpointFiles.size()));
          for (CheckpointFile _iter59 : struct.mCheckpointFiles)
          {
            _iter59.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LineageCommandTupleSchemeFactory implements SchemeFactory {
    public LineageCommandTupleScheme getScheme() {
      return new LineageCommandTupleScheme();
    }
  }

  private static class LineageCommandTupleScheme extends TupleScheme<LineageCommand> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LineageCommand struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetMCommandType()) {
        optionals.set(0);
      }
      if (struct.isSetMCheckpointFiles()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetMCommandType()) {
        oprot.writeI32(struct.mCommandType.getValue());
      }
      if (struct.isSetMCheckpointFiles()) {
        {
          oprot.writeI32(struct.mCheckpointFiles.size());
          for (CheckpointFile _iter60 : struct.mCheckpointFiles)
          {
            _iter60.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LineageCommand struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.mCommandType = tachyon.thrift.CommandType.findByValue(iprot.readI32());
        struct.setMCommandTypeIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list61 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.mCheckpointFiles = new ArrayList<CheckpointFile>(_list61.size);
          CheckpointFile _elem62;
          for (int _i63 = 0; _i63 < _list61.size; ++_i63)
          {
            _elem62 = new CheckpointFile();
            _elem62.read(iprot);
            struct.mCheckpointFiles.add(_elem62);
          }
        }
        struct.setMCheckpointFilesIsSet(true);
      }
    }
  }

}

