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

package alluxio.worker.block.meta;

import alluxio.util.io.PathUtils;

import com.google.common.base.Preconditions;

import java.io.File;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents the metadata of a block in Alluxio managed storage.
 */
@ThreadSafe
public class BlockMeta {
  private final long mBlockId;
  private final StorageDir mDir;
  private final long mBlockSize;

  /**
   * Committed block is stored in BlockStore under its {@link StorageDir} as a block file named
   * after its blockId. e.g. Block 100 of StorageDir "/mnt/mem/0" has path:
   * <p>
   * /mnt/mem/0/100
   *
   * @param blockId the block id
   * @param dir the parent directory
   * @return committed file path
   */
  public static String commitPath(StorageDir dir, long blockId) {
    return PathUtils.concatPath(dir.getDirPath(), blockId);
  }

  /**
   * Creates a new instance of {@link BlockMeta}.
   *
   * @param blockId the block id
   * @param blockSize the block size
   * @param dir the parent directory
   */
  public BlockMeta(long blockId, long blockSize, StorageDir dir) {
    mBlockId = blockId;
    mDir = Preconditions.checkNotNull(dir, "dir");
    mBlockSize = blockSize;
  }

  /**
   * Creates a new instance of {@link BlockMeta} from {@link TempBlockMeta}.
   *
   * @param tempBlock uncommitted block metadata
   */
  public BlockMeta(TempBlockMeta tempBlock) {
    this(tempBlock.getBlockId(),
        // NOTE: TempBlockMeta must be committed after the actual data block file is moved.
        new File(tempBlock.getCommitPath()).length(),
        tempBlock.getParentDir());
  }

  /**
   * @return the block size
   */
  public long getBlockSize() {
    return mBlockSize;
  }

  /**
   * @return the block path
   */
  public String getPath() {
    return commitPath(mDir, mBlockId);
  }
}
