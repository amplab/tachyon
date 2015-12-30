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

package tachyon.client.file.policy;

import java.util.List;

import com.google.common.base.Preconditions;

import tachyon.client.block.BlockWorkerInfo;
import tachyon.worker.WorkerNetAddress;

/**
 * Always returns a worker with the specified hostname. Returns null if no active worker on that
 * hostname found.
 */
public final class SpecificHostPolicy implements FileWriteLocationPolicy {
  private final String mHostname;

  /**
   * Constructs the policy with the hostname.
   */
  public SpecificHostPolicy(String hostname) {
    mHostname = Preconditions.checkNotNull(hostname);
  }

  @Override
  public WorkerNetAddress getWorkerForNextBlock(List<BlockWorkerInfo> workerInfoList,
      long blockSizeBytes) {
    // find the first worker matching the host name
    for (BlockWorkerInfo info : workerInfoList) {
      if (info.getNetAddress().getHost().equals(mHostname)) {
        return info.getNetAddress();
      }
    }
    return null;
  }
}
