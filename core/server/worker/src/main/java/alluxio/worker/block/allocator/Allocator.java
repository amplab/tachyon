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

package alluxio.worker.block.allocator;

import alluxio.Configuration;
import alluxio.PropertyKey;
import alluxio.annotation.PublicApi;
import alluxio.util.CommonUtils;
import alluxio.worker.block.BlockMetadataManagerView;
import alluxio.worker.block.BlockStoreLocation;
import alluxio.worker.block.meta.StorageDirView;

import com.google.common.base.Preconditions;

/**
 * Interface for the allocation policy of Alluxio managed data.
 */
@PublicApi
public interface Allocator {

  /**
   * Factory for {@link Allocator}.
   */
  class Factory {

    private Factory() {} // prevent instantiation

    /**
     * Factory for {@link Allocator}.
     *
     * @param view {@link BlockMetadataManagerView} to pass to {@link Allocator}
     * @return the generated {@link Allocator}, it will be a {@link MaxFreeAllocator} by default
     */
    public static Allocator create(BlockMetadataManagerView view) {
      BlockMetadataManagerView managerView = Preconditions.checkNotNull(view, "view");
      try {
        return CommonUtils.createNewClassInstance(
            Configuration.<Allocator>getClass(PropertyKey.WORKER_ALLOCATOR_CLASS),
            new Class[] {BlockMetadataManagerView.class}, new Object[] {managerView});
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Allocates a block from the given block store location under a given view. The location can be a
   * specific location, or {@link BlockStoreLocation#anyTier()} or
   * {@link BlockStoreLocation#anyDirInTier(String)}. The view is generated by a
   * {@link alluxio.worker.block.BlockStore}.
   *
   * @param sessionId the id of session to apply for the block allocation
   * @param blockSize the size of block in bytes
   * @param location the location in block store
   * @param view of the metadata manager
   * @return a {@link StorageDirView} in which to create the temp block meta if success, null
   *         otherwise
   */
  StorageDirView allocateBlockWithView(long sessionId, long blockSize, BlockStoreLocation location,
      BlockMetadataManagerView view);
}
