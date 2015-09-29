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

package tachyon.client.block;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;

import com.google.common.base.Preconditions;
import com.google.common.io.Closer;

import tachyon.client.ClientContext;
import tachyon.util.io.BufferUtils;
import tachyon.util.network.NetworkAddressUtils;
import tachyon.worker.WorkerClient;

/**
 * This class provides a streaming API to read a block in Tachyon. The data will be directly read
 * from the local machine's storage. The instances of this class should only be used by one
 * thread and are not thread safe.
 */
public final class LocalBlockInStream extends BufferedBlockInStream {
  private final Closer mCloser;
  private final FileChannel mLocalFileChannel;
  private final WorkerClient mWorkerClient;

  /**
   * Creates a new local block input stream.
   *
   * @param blockId the block id
   * @throws IOException if I/O error occurs
   */
  public LocalBlockInStream(long blockId, long blockSize, InetSocketAddress location)
      throws IOException {
    super(blockId, blockSize, location);
    mCloser = Closer.create();
    mWorkerClient =
        mContext.acquireWorkerClient(NetworkAddressUtils.getLocalHostName(ClientContext.getConf()));

    FileChannel localFileChannel = null;
    try {
      String blockPath = mWorkerClient.lockBlock(blockId);
      if (blockPath == null) {
        throw new IOException("Block " + mBlockId + " is not available on local machine.");
      }
      RandomAccessFile localFile = mCloser.register(new RandomAccessFile(blockPath, "r"));
      localFileChannel = mCloser.register(localFile.getChannel());
    } catch (IOException e) {
      mContext.releaseWorkerClient(mWorkerClient);
      throw e;
    }

    mLocalFileChannel = localFileChannel;
  }

  @Override
  public void close() throws IOException {
    super.close();
    try {
      mWorkerClient.unlockBlock(mBlockId);
    } finally {
      mContext.releaseWorkerClient(mWorkerClient);
      mCloser.close();
    }
  }
}
