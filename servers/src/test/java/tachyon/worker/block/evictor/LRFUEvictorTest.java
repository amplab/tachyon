/*
 * Licensed to the University of California, Berkeley under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package tachyon.worker.block.evictor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import tachyon.Constants;
import tachyon.conf.TachyonConf;
import tachyon.worker.block.BlockMetadataManager;
import tachyon.worker.block.BlockMetadataManagerView;
import tachyon.worker.block.BlockStoreEventListener;
import tachyon.worker.block.BlockStoreLocation;
import tachyon.worker.block.meta.StorageDir;

/**
 * Sanity check on specific behavior of LRFUEvictor such as evicting/moving least blocks with
 * minimum CRF value and cascading LRFU eviction.
 */
public class LRFUEvictorTest {
  private static final long USER_ID = 2;
  private static final long BLOCK_ID = 10;

  private BlockMetadataManager mMetaManager;
  private BlockMetadataManagerView mManagerView;
  private Evictor mEvictor;

  private double mStepFactor;
  private double mAttenuationFactor;

  @Rule
  public TemporaryFolder mTestFolder = new TemporaryFolder();

  @Before
  public final void before() throws Exception {
    File tempFolder = mTestFolder.newFolder();
    mMetaManager = EvictorTestUtils.defaultMetadataManager(tempFolder.getAbsolutePath());
    mManagerView =
        new BlockMetadataManagerView(mMetaManager, Collections.<Integer>emptySet(),
            Collections.<Long>emptySet());
    TachyonConf conf = new TachyonConf();
    conf.set(Constants.WORKER_EVICT_STRATEGY_CLASS, LRFUEvictor.class.getName());
    mStepFactor = conf.getDouble(Constants.WORKER_EVICT_STRATEGY_LRFU_STEP_FACTOR, 0.25);
    mAttenuationFactor =
        conf.getDouble(Constants.WORKER_EVICT_STRATEGY_LRFU_ATTENUATION_FACTOR, 2.0);
    mEvictor = Evictor.Factory.createEvictor(conf, mManagerView);
  }

  private void cache(long userId, long blockId, long bytes, int tierLevel, int dirIdx)
      throws Exception {
    StorageDir dir = mMetaManager.getTiers().get(tierLevel).getDir(dirIdx);
    EvictorTestUtils.cache(userId, blockId, bytes, dir, mMetaManager, mEvictor);
  }

  // access the block to update evictor
  private void access(long blockId) {
    ((BlockStoreEventListener) mEvictor).onAccessBlock(USER_ID, blockId);
  }

  /**
   * Sort all blocks in ascending order of CRF
   * 
   * @return the sorted CRF of all blocks
   */
  private List<Map.Entry<Long, Double>> getSortedCRF(Map<Long, Double> crfMap) {
    List<Map.Entry<Long, Double>> sortedCRF =
        new ArrayList<Map.Entry<Long, Double>>(crfMap.entrySet());
    Collections.sort(sortedCRF, new Comparator<Map.Entry<Long, Double>>() {
      public int compare(Entry<Long, Double> o1, Entry<Long, Double> o2) {
        double res = o1.getValue() - o2.getValue();
        if (res < 0) {
          return -1;
        } else if (res > 0) {
          return 1;
        } else {
          return 0;
        }
      }
    });
    return sortedCRF;
  }

  @Test
  public void evictInBottomTierTest() throws Exception {
    int bottomTierLevel = EvictorTestUtils.TIER_LEVEL[EvictorTestUtils.TIER_LEVEL.length - 1];
    Map<Long, Double> blockIdToCRF = new HashMap<Long, Double>();
    // capacity increases with index
    long[] bottomTierDirCapacity = EvictorTestUtils.TIER_CAPACITY[bottomTierLevel];
    int nDir = bottomTierDirCapacity.length;
    // fill in dirs from larger to smaller capacity with blockId equal to BLOCK_ID plus dir index
    for (int i = 0; i < nDir; i ++) {
      cache(USER_ID, BLOCK_ID + i, bottomTierDirCapacity[i], bottomTierLevel, i);
      // update CRF of blocks when blocks are committed
      blockIdToCRF.put(BLOCK_ID + i, Math
          .pow(1.0 / mAttenuationFactor, mStepFactor * (nDir - 1 - i)));
    }
    // access blocks in the order: 10, 10, 11, 10, 11, 12. Update CRF of all blocks
    // during each access
    for (int i = 0; i < nDir; i ++) {
      for (int j = 0; j <= i; j ++) {
        access(BLOCK_ID + j);
        for (int k = 0; k < nDir; k ++) {
          if (k == j) {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor)
                    + 1.0);
          } else {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor));
          }
        }
      }
    }
    // sort blocks in ascending order of CRF
    List<Map.Entry<Long, Double>> blockCRF = getSortedCRF(blockIdToCRF);
    BlockStoreLocation anyDirInBottomTier = BlockStoreLocation.anyDirInTier(bottomTierLevel + 1);
    // request smallest capacity and update access time on the evicted block for nDir times, the dir
    // to evict blocks from should be in the same order as sorted blockCRF
    for (int i = 0; i < nDir; i ++) {
      EvictionPlan plan =
          mEvictor.freeSpaceWithView(bottomTierDirCapacity[0], anyDirInBottomTier, mManagerView);
      Assert.assertNotNull(plan);
      Assert.assertTrue(plan.toMove().isEmpty());
      Assert.assertEquals(1, plan.toEvict().size());
      long toEvictBlockId = plan.toEvict().get(0);
      long objectBlockId = blockCRF.get(i).getKey();
      Assert.assertEquals(objectBlockId + " " + toEvictBlockId, objectBlockId, toEvictBlockId);
      // update CRF of the chosen block in case that it is chosen again
      for (int j = 0; j < nDir; j ++) {
        access(toEvictBlockId);
      }
    }
  }

  @Test
  public void cascadingEvictionTest1() throws Exception {
    // Two tiers, each dir in the second tier has more space than any dir in the first tier. Fill in
    // the first tier, leave the second tier empty. Request space from the first tier, blocks should
    // be moved from the first to the second tier without eviction.
    int firstTierLevel = EvictorTestUtils.TIER_LEVEL[0];
    long[] firstTierDirCapacity = EvictorTestUtils.TIER_CAPACITY[0];
    int nDir = firstTierDirCapacity.length;
    Map<Long, Double> blockIdToCRF = new HashMap<Long, Double>();
    for (int i = 0; i < nDir; i ++) {
      cache(USER_ID, BLOCK_ID + i, firstTierDirCapacity[i], firstTierLevel, i);
      // update CRF of blocks when blocks are committed
      blockIdToCRF.put(BLOCK_ID + i, Math
          .pow(1.0 / mAttenuationFactor, mStepFactor * (nDir - 1 - i)));
    }
    // access blocks in the order: 10, 10, 11. Update CRF of all blocks
    // during each access
    for (int i = 0; i < nDir; i ++) {
      for (int j = 0; j <= i; j ++) {
        access(BLOCK_ID + j);
        for (int k = 0; k < nDir; k ++) {
          if (k == j) {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor)
                    + 1.0);
          } else {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor));
          }
        }
      }
    }
    List<Map.Entry<Long, Double>> blockCRF = getSortedCRF(blockIdToCRF);
    BlockStoreLocation anyDirInFirstTier = BlockStoreLocation.anyDirInTier(firstTierLevel + 1);
    long smallestCapacity = firstTierDirCapacity[0];
    // request smallest capacity and update access time on the moved block for nDir times, the dir
    // to move blocks from should be in the same order as sorted blockCRF
    for (int i = 0; i < nDir; i ++) {
      EvictionPlan plan =
          mEvictor.freeSpaceWithView(smallestCapacity, anyDirInFirstTier, mManagerView);
      Assert.assertTrue(EvictorTestUtils.validCascadingPlan(smallestCapacity, plan, mMetaManager));
      Assert.assertEquals(0, plan.toEvict().size());
      Assert.assertEquals(1, plan.toMove().size());
      long blockId = plan.toMove().get(0).getFirst();
      long objectBlockId = blockCRF.get(i).getKey();
      Assert.assertEquals(objectBlockId, blockId);
      // update CRF of the chosen block in case that it is chosen again
      for (int j = 0; j < nDir; j ++) {
        access(objectBlockId);
      }
    }
  }

  @Test
  public void cascadingEvictionTest2() throws Exception {
    // Two tiers, the second tier has more dirs than the first tier and each dir in the second tier
    // has more space than any dir in the first tier. Fill in all dirs and request space from the
    // first tier, blocks should be moved from the first to the second tier, and some blocks in the
    // second tier should be evicted to hold blocks moved from the first tier.
    long blockId = BLOCK_ID;
    long totalBlocks = 0;
    for (int tierLevel : EvictorTestUtils.TIER_LEVEL) {
      totalBlocks += EvictorTestUtils.TIER_CAPACITY[tierLevel].length;
    }
    Map<Long, Double> blockIdToCRF = new HashMap<Long, Double>();
    for (int tierLevel : EvictorTestUtils.TIER_LEVEL) {
      long[] tierCapacity = EvictorTestUtils.TIER_CAPACITY[tierLevel];
      for (int dirIdx = 0; dirIdx < tierCapacity.length; dirIdx ++) {
        cache(USER_ID, blockId, tierCapacity[dirIdx], tierLevel, dirIdx);
        // update CRF of blocks when blocks are committed
        blockIdToCRF.put(blockId,
            Math.pow(1.0 / mAttenuationFactor, mStepFactor 
                * (totalBlocks - 1 - (blockId - BLOCK_ID))));
        blockId ++;
      }
    }

    // access blocks in the order: 10, 10, 11, 10, 11, 12, 10, 11, 12, 13, 10, 11, 12, 13, 14
    // Update CRF of all blocks during each access
    for (int i = 0; i < totalBlocks; i ++) {
      for (int j = 0; j <= i; j ++) {
        access(BLOCK_ID + j);
        for (int k = 0; k < totalBlocks; k ++) {
          if (k == j) {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor)
                    + 1.0);
          } else {
            blockIdToCRF.put(BLOCK_ID + k,
                blockIdToCRF.get(BLOCK_ID + k) * Math.pow(1.0 / mAttenuationFactor, mStepFactor));
          }
        }
      }
    }

    List<Map.Entry<Long, Double>> blockCRF = getSortedCRF(blockIdToCRF);
    // sorted blocks in the first tier
    List<Long> blocksInFirstTier = new ArrayList<Long>();
    // sorted blocks in the second tier
    List<Long> blocksInSecondTier = new ArrayList<Long>();
    for (int i = 0; i < blockCRF.size(); i ++) {
      long block = blockCRF.get(i).getKey();
      if (block - BLOCK_ID < EvictorTestUtils.TIER_CAPACITY[0].length) {
        blocksInFirstTier.add(block);
      } else if (block - BLOCK_ID < EvictorTestUtils.TIER_CAPACITY[0].length
          + EvictorTestUtils.TIER_CAPACITY[1].length) {
        blocksInSecondTier.add(block);
      }
    }
    BlockStoreLocation anyDirInFirstTier =
        BlockStoreLocation.anyDirInTier(EvictorTestUtils.TIER_LEVEL[0] + 1);
    int nDirInFirstTier = EvictorTestUtils.TIER_CAPACITY[0].length;
    long smallestCapacity = EvictorTestUtils.TIER_CAPACITY[0][0];
    for (int i = 0; i < nDirInFirstTier; i ++) {
      EvictionPlan plan =
          mEvictor.freeSpaceWithView(smallestCapacity, anyDirInFirstTier, mManagerView);
      Assert.assertTrue(EvictorTestUtils.validCascadingPlan(smallestCapacity, plan, mMetaManager));
      // block with minimum CRF in the first tier needs to be moved to the second tier
      Assert.assertEquals(1, plan.toMove().size());
      long blockIdMovedInFirstTier = plan.toMove().get(0).getFirst();
      long objectBlockIdInFirstTier = blocksInFirstTier.get(i);
      Assert.assertEquals(objectBlockIdInFirstTier, blockIdMovedInFirstTier);
      // cached block with minimum CRF in the second tier will be evicted to hold blocks moved
      // from first tier
      Assert.assertEquals(1, plan.toEvict().size());
      long blockIdEvictedInSecondTier = plan.toEvict().get(0);
      long objectBlockIdInSecondTier = blocksInSecondTier.get(i);
      Assert.assertEquals(objectBlockIdInSecondTier, blockIdEvictedInSecondTier);
      // update CRF of the chosen blocks in case that they are chosen again
      for (int j = 0; j < totalBlocks; j ++) {
        access(blockIdMovedInFirstTier);
      }
      for (int j = 0; j < totalBlocks; j ++) {
        access(blockIdEvictedInSecondTier); 
      }
    }
  }
}
