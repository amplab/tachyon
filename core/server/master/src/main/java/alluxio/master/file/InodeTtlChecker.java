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

package alluxio.master.file;

import alluxio.Constants;
import alluxio.grpc.DeletePOptions;
import alluxio.grpc.FreePOptions;
import alluxio.heartbeat.HeartbeatExecutor;
import alluxio.master.ProtobufUtils;
import alluxio.master.file.meta.InodeTree;
import alluxio.master.file.meta.InodeTree.LockPattern;
import alluxio.master.file.meta.InodeView;
import alluxio.master.file.meta.LockedInodePath;
import alluxio.master.file.meta.TtlBucket;
import alluxio.master.file.meta.TtlBucketList;
import alluxio.master.file.contexts.DeleteContext;
import alluxio.master.file.contexts.FreeContext;
import alluxio.master.journal.JournalContext;
import alluxio.proto.journal.File.UpdateInodeEntry;
import alluxio.grpc.TtlAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This class represents the executor for periodic inode ttl check.
 */
@NotThreadSafe
final class InodeTtlChecker implements HeartbeatExecutor {
  private static final Logger LOG = LoggerFactory.getLogger(InodeTtlChecker.class);

  private final FileSystemMaster mFileSystemMaster;
  private final InodeTree mInodeTree;
  private final TtlBucketList mTtlBuckets;

  /**
   * Constructs a new {@link InodeTtlChecker}.
   */
  public InodeTtlChecker(FileSystemMaster fileSystemMaster, InodeTree inodeTree) {
    mFileSystemMaster = fileSystemMaster;
    mInodeTree = inodeTree;
    mTtlBuckets = inodeTree.getTtlBuckets();
  }

  @Override
  public void heartbeat() {
    Set<TtlBucket> expiredBuckets = mTtlBuckets.getExpiredBuckets(System.currentTimeMillis());
    for (TtlBucket bucket : expiredBuckets) {
      for (InodeView inode : bucket.getInodes()) {
        try {
          TtlAction ttlAction = inode.getTtlAction();
          switch (ttlAction) {
            case FREE:
              // public free method will lock the path, and check WRITE permission required at
              // parent of file
              if (inode.isDirectory()) {
                mFileSystemMaster.free(inode, FreeContext
                    .defaults(FreePOptions.newBuilder().setForced(true).setRecursive(true)));
              } else {
                mFileSystemMaster.free(inode,
                    FreeContext.defaults(FreePOptions.newBuilder().setForced(true)));
              }
              try (JournalContext journalContext = mFileSystemMaster.createJournalContext();
                   LockedInodePath inodePath =
                       mInodeTree.lockFullInodePath(inode.getId(), LockPattern.WRITE_INODE)) {
                // Reset state
                mInodeTree.updateInode(journalContext, UpdateInodeEntry.newBuilder()
                    .setId(inode.getId())
                    .setTtl(Constants.NO_TTL)
                    .setTtlAction(ProtobufUtils.toProtobuf(TtlAction.DELETE))
                    .build());
              }
              mTtlBuckets.remove(inode);
              break;
            case DELETE:// Default if not set is DELETE
              // public delete method will lock the path, and check WRITE permission required at
              // parent of file
              if (inode.isDirectory()) {
                mFileSystemMaster.delete(inode,
                    DeleteContext.defaults(DeletePOptions.newBuilder().setRecursive(true)));
              } else {
                mFileSystemMaster.delete(inode, DeleteContext.defaults());
              }
              break;
            default:
              LOG.error("Unknown ttl action {}", ttlAction);
          }
        } catch (FileNotFoundException e) {
          // The inode has already been deleted, nothing needs to be done.
        } catch (Exception e) {
          LOG.error("Exception trying to clean up {} for ttl check", inode.toString(), e);
        }
      }
    }
    mTtlBuckets.removeBuckets(expiredBuckets);
  }

  @Override
  public void close() {
    // Nothing to clean up
  }
}
