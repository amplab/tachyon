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

package alluxio.client.file.policy;

import alluxio.client.block.BlockWorkerInfo;
import alluxio.client.file.policy.options.BlockLocationPolicyGetWorkerOptions;
import alluxio.wire.WorkerNetAddress;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This policy maps blockId to several deterministic Alluxio workers. The number of workers a block
 * can be mapped to can be passed through the constructor. The default is 1. It skips the workers
 * that do not have enough capacity to hold the block.
 */
@NotThreadSafe
public final class DeterministicHashPolicy implements BlockLocationPolicy {
  /** The default number of shards to serve a block. */
  private static final int DEFAULT_NUM_SHARDS = 1;
  private final int mShards;
  private final Random mRandom = new Random();
  private List<BlockWorkerInfo> mWorkerInfoList;
  private boolean mInitialized = false;

  /**
   * Constructs a new {@link DeterministicHashPolicy}.
   */
  public DeterministicHashPolicy() {
    this(DEFAULT_NUM_SHARDS);
  }

  /**
   * Constructs a new {@link DeterministicHashPolicy}.
   *
   * @param numShards the number of shards a block's traffic can be sharded to
   */
  public DeterministicHashPolicy(Integer numShards) {
    Preconditions.checkArgument(numShards >= 1);
    mShards = numShards;
  }

  @Override
  public WorkerNetAddress getWorkerForBlock(BlockLocationPolicyGetWorkerOptions options) {
    if (!mInitialized) {
      mWorkerInfoList = Lists.newArrayList(options.getBlockWorkerInfos());
      Collections.sort(mWorkerInfoList, new Comparator<BlockWorkerInfo>() {
        @Override
        public int compare(BlockWorkerInfo o1, BlockWorkerInfo o2) {
          return o1.getNetAddress().toString().compareToIgnoreCase(o2.getNetAddress().toString());
        }
      });
      mInitialized = true;
    }

    // The worker information (e.g. capacity) in the blockWorkerInfoMap can be more up-to-date
    // than those in mWorkerInfoList. We assume that the worker information is more likely to
    // change than the worker network address.
    HashMap<WorkerNetAddress, BlockWorkerInfo> blockWorkerInfoMap = new HashMap<>();
    for (BlockWorkerInfo workerInfo : options.getBlockWorkerInfos()) {
      blockWorkerInfoMap.put(workerInfo.getNetAddress(), workerInfo);
    }

    List<WorkerNetAddress> workers = new ArrayList<>();
    // Try the next one if the worker mapped from the blockId doesn't work until all the workers
    // are examined.
    int index = (int) (options.getBlockId() % (long) mWorkerInfoList.size());
    for (BlockWorkerInfo blockWorkerInfoUnused : mWorkerInfoList) {
      WorkerNetAddress candidate = mWorkerInfoList.get(index).getNetAddress();
      BlockWorkerInfo workerInfo = blockWorkerInfoMap.get(candidate);
      if (workerInfo != null && workerInfo.getCapacityBytes() >= options.getBlockSize()) {
        workers.add(candidate);
        if (workers.size() >= mShards) {
          break;
        }
      }
      index = (index + 1) % mWorkerInfoList.size();
    }
    return workers.isEmpty() ? null : workers.get(mRandom.nextInt(workers.size()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeterministicHashPolicy)) {
      return false;
    }
    DeterministicHashPolicy that = (DeterministicHashPolicy) o;
    return Objects.equal(mWorkerInfoList, that.mWorkerInfoList)
        && Objects.equal(mInitialized, that.mInitialized)
        && Objects.equal(mShards, that.mShards);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mWorkerInfoList, mInitialized, mShards);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("workerInfoList", mWorkerInfoList)
        .add("initialized", mInitialized)
        .add("shards", mShards)
        .toString();
  }
}
