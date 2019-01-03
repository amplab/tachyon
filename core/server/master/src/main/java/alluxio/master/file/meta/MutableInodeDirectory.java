/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.master.file.meta;

import alluxio.Constants;
import alluxio.master.ProtobufUtils;
import alluxio.master.file.options.CreateDirectoryOptions;
import alluxio.proto.journal.File.InodeDirectoryEntry;
import alluxio.proto.journal.File.UpdateInodeDirectoryEntry;
import alluxio.proto.journal.Journal.JournalEntry;
import alluxio.proto.meta.InodeMeta;
import alluxio.proto.meta.InodeMeta.InodeOrBuilder;
import alluxio.security.authorization.AccessControlList;
import alluxio.security.authorization.DefaultAccessControlList;
import alluxio.wire.FileInfo;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Alluxio file system's directory representation in the file system master.
 */
@NotThreadSafe
public final class MutableInodeDirectory extends MutableInode<MutableInodeDirectory>
    implements InodeDirectoryView {
  private boolean mMountPoint;
  private boolean mDirectChildrenLoaded;
  private long mChildCount;
  private DefaultAccessControlList mDefaultAcl;

  /**
   * Creates a new instance of {@link MutableInodeDirectory}.
   *
   * @param id the id to use
   */
  private MutableInodeDirectory(long id) {
    super(id, true);
    mMountPoint = false;
    mDirectChildrenLoaded = false;
    mChildCount = 0;
    mDefaultAcl = new DefaultAccessControlList(mAcl);
  }

  @Override
  protected MutableInodeDirectory getThis() {
    return this;
  }

  @Override
  public boolean isMountPoint() {
    return mMountPoint;
  }

  @Override
  public synchronized boolean isDirectChildrenLoaded() {
    return mDirectChildrenLoaded;
  }

  @Override
  public long getChildCount() {
    return mChildCount;
  }

  @Override
  public DefaultAccessControlList getDefaultACL() {
    return mDefaultAcl;
  }

  /**
   * @param mountPoint the mount point flag value to use
   * @return the updated object
   */
  public MutableInodeDirectory setMountPoint(boolean mountPoint) {
    mMountPoint = mountPoint;
    return getThis();
  }

  /**
   * @param directChildrenLoaded whether to load the direct children if they were not loaded before
   * @return the updated object
   */
  public synchronized MutableInodeDirectory setDirectChildrenLoaded(boolean directChildrenLoaded) {
    mDirectChildrenLoaded = directChildrenLoaded;
    return getThis();
  }

  /**
   * @param childCount the child count to set
   * @return the updated object
   */
  public MutableInodeDirectory setChildCount(long childCount) {
    mChildCount = childCount;
    return getThis();
  }

  @Override
  public MutableInodeDirectory setDefaultACL(DefaultAccessControlList acl) {
    mDefaultAcl = acl;
    return getThis();
  }

  /**
   * Generates client file info for a folder.
   *
   * @param path the path of the folder in the filesystem
   * @return the generated {@link FileInfo}
   */
  @Override
  public FileInfo generateClientFileInfo(String path) {
    FileInfo ret = new FileInfo();
    ret.setFileId(getId());
    ret.setName(getName());
    ret.setPath(path);
    ret.setBlockSizeBytes(0);
    ret.setCreationTimeMs(getCreationTimeMs());
    ret.setCompleted(true);
    ret.setFolder(isDirectory());
    ret.setPinned(isPinned());
    ret.setCacheable(false);
    ret.setPersisted(isPersisted());
    ret.setLastModificationTimeMs(getLastModificationTimeMs());
    ret.setTtl(mTtl);
    ret.setTtlAction(mTtlAction);
    ret.setOwner(getOwner());
    ret.setGroup(getGroup());
    ret.setMode(getMode());
    ret.setPersistenceState(getPersistenceState().toString());
    ret.setMountPoint(isMountPoint());
    ret.setUfsFingerprint(Constants.INVALID_UFS_FINGERPRINT);
    ret.setAcl(mAcl);
    ret.setDefaultAcl(mDefaultAcl);
    return ret;
  }

  /**
   * Updates this inode directory's state from the given entry.
   *
   * @param entry the entry
   */
  public void updateFromEntry(UpdateInodeDirectoryEntry entry) {
    if (entry.hasDefaultAcl()) {
      setDefaultACL(
          (DefaultAccessControlList) DefaultAccessControlList.fromProtoBuf(entry.getDefaultAcl()));
    }
    if (entry.hasDirectChildrenLoaded()) {
      setDirectChildrenLoaded(entry.getDirectChildrenLoaded());
    }
    if (entry.hasMountPoint()) {
      setMountPoint(entry.getMountPoint());
    }
  }

  @Override
  public String toString() {
    return toStringHelper().add("mountPoint", mMountPoint)
        .add("directChildrenLoaded", mDirectChildrenLoaded).toString();
  }

  /**
   * Converts the entry to an {@link MutableInodeDirectory}.
   *
   * @param entry the entry to convert
   * @return the {@link MutableInodeDirectory} representation
   */
  public static MutableInodeDirectory fromJournalEntry(InodeDirectoryEntry entry) {
    // If journal entry has no mode set, set default mode for backwards-compatibility.
    MutableInodeDirectory ret = new MutableInodeDirectory(entry.getId())
        .setCreationTimeMs(entry.getCreationTimeMs())
        .setName(entry.getName())
        .setParentId(entry.getParentId())
        .setPersistenceState(PersistenceState.valueOf(entry.getPersistenceState()))
        .setPinned(entry.getPinned())
        .setLastModificationTimeMs(entry.getLastModificationTimeMs(), true)
        .setMountPoint(entry.getMountPoint())
        .setTtl(entry.getTtl())
        .setTtlAction(ProtobufUtils.fromProtobuf(entry.getTtlAction()))
        .setDirectChildrenLoaded(entry.getDirectChildrenLoaded());
    if (entry.hasAcl()) {
      ret.mAcl = AccessControlList.fromProtoBuf(entry.getAcl());
    } else {
      // Backward compatibility.
      AccessControlList acl = new AccessControlList();
      acl.setOwningUser(entry.getOwner());
      acl.setOwningGroup(entry.getGroup());
      short mode = entry.hasMode() ? (short) entry.getMode() : Constants.DEFAULT_FILE_SYSTEM_MODE;
      acl.setMode(mode);
      ret.mAcl = acl;
    }
    if (entry.hasDefaultAcl()) {
      ret.mDefaultAcl = (DefaultAccessControlList) AccessControlList
          .fromProtoBuf(entry.getDefaultAcl());
    } else {
      ret.mDefaultAcl = new DefaultAccessControlList();
    }
    return ret;
  }

  /**
   * Creates an {@link MutableInodeDirectory}.
   *
   * @param id id of this inode
   * @param parentId id of the parent of this inode
   * @param name name of this inode
   * @param options options to create this directory
   * @return the {@link MutableInodeDirectory} representation
   */
  public static MutableInodeDirectory create(long id, long parentId, String name,
      CreateDirectoryOptions options) {
    return new MutableInodeDirectory(id)
        .setParentId(parentId)
        .setName(name)
        .setTtl(options.getTtl())
        .setTtlAction(options.getTtlAction())
        .setOwner(options.getOwner())
        .setGroup(options.getGroup())
        .setMode(options.getMode().toShort())
        .setAcl(options.getAcl())
        // SetAcl call is also setting default AclEntries
        .setAcl(options.getDefaultAcl())
        .setMountPoint(options.isMountPoint());
  }

  @Override
  public JournalEntry toJournalEntry() {
    InodeDirectoryEntry inodeDirectory = InodeDirectoryEntry.newBuilder()
        .setCreationTimeMs(getCreationTimeMs())
        .setId(getId())
        .setName(getName())
        .setParentId(getParentId())
        .setPersistenceState(getPersistenceState().name())
        .setPinned(isPinned())
        .setLastModificationTimeMs(getLastModificationTimeMs())
        .setMountPoint(isMountPoint())
        .setTtl(getTtl())
        .setTtlAction(ProtobufUtils.toProtobuf(getTtlAction()))
        .setDirectChildrenLoaded(isDirectChildrenLoaded())
        .setAcl(AccessControlList.toProtoBuf(mAcl))
        .setDefaultAcl(AccessControlList.toProtoBuf(mDefaultAcl))
        .build();
    return JournalEntry.newBuilder().setInodeDirectory(inodeDirectory).build();
  }

  @Override
  public InodeMeta.Inode toProto() {
    return super.toProtoBuilder()
        .setIsMountPoint(isMountPoint())
        .setHasDirectChildrenLoaded(isDirectChildrenLoaded())
        .setChildCount(getChildCount())
        .setDefaultAcl(AccessControlList.toProtoBuf(getDefaultACL()))
        .build();
  }

  /**
   * @param inode a protocol buffer inode
   * @return the {@link MutableInodeDirectory} representation for the inode
   */
  public static MutableInodeDirectory fromProto(InodeOrBuilder inode) {
    return new MutableInodeDirectory(inode.getId())
        .setCreationTimeMs(inode.getCreationTimeMs())
        .setLastModificationTimeMs(inode.getLastModifiedMs(), true)
        .setTtl(inode.getTtl())
        .setTtlAction(ProtobufUtils.fromProtobuf(inode.getTtlAction()))
        .setName(inode.getName())
        .setParentId(inode.getParentId())
        .setPersistenceState(PersistenceState.valueOf(inode.getPersistenceState()))
        .setPinned(inode.getIsPinned())
        .setInternalAcl(AccessControlList.fromProtoBuf(inode.getAccessAcl()))
        .setUfsFingerprint(inode.getUfsFingerprint())
        .setMountPoint(inode.getIsMountPoint())
        .setDirectChildrenLoaded(inode.getHasDirectChildrenLoaded())
        .setChildCount(inode.getChildCount())
        .setDefaultACL((DefaultAccessControlList)
            DefaultAccessControlList.fromProtoBuf(inode.getDefaultAcl()));
  }
}
