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

import alluxio.worker.block.BlockStoreLocation;

public interface TempBlockMeta {
  /**
   * @return the block size
   */
  long getBlockSize();

  /**
   * @return the block path
   */
  String getPath();

  /**
   * @return the block id
   */
  long getBlockId();

  /**
   * @return location of the block
   */
  BlockStoreLocation getBlockLocation();

  /**
   * @return the parent directory
   */
  StorageDir getParentDir();

  /**
   * @return the commit path
   */
  String getCommitPath();

  /**
   * @return the session id
   */
  long getSessionId();

  /**
   * @param newSize block size to use
   */
  void setBlockSize(long newSize);
}
