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

package tachyon.worker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import tachyon.Constants;
import tachyon.LocalTachyonClusterResource;
import tachyon.TachyonURI;
import tachyon.client.FileSystemTestUtils;
import tachyon.client.WriteType;
import tachyon.client.block.BlockMasterClient;
import tachyon.client.file.FileOutStream;
import tachyon.client.file.FileSystem;
import tachyon.client.file.URIStatus;
import tachyon.client.file.options.CreateFileOptions;
import tachyon.conf.TachyonConf;
import tachyon.exception.InvalidPathException;
import tachyon.heartbeat.HeartbeatContext;
import tachyon.heartbeat.HeartbeatScheduler;
import tachyon.master.block.BlockId;
import tachyon.thrift.TachyonTException;
import tachyon.underfs.UnderFileSystem;
import tachyon.util.io.BufferUtils;
import tachyon.util.io.PathUtils;
import tachyon.worker.block.BlockWorkerClientServiceHandler;

/**
 * Integration tests for {@link BlockWorkerClientServiceHandler}
 */
public class BlockServiceHandlerIntegrationTest {
  private static final long WORKER_CAPACITY_BYTES = 10 * Constants.MB;
  private static final long SESSION_ID = 1L;
  private static final int USER_QUOTA_UNIT_BYTES = 100;

  @Rule
  public LocalTachyonClusterResource mLocalTachyonClusterResource =
      new LocalTachyonClusterResource(WORKER_CAPACITY_BYTES, USER_QUOTA_UNIT_BYTES, Constants.MB,
          Constants.USER_FILE_BUFFER_BYTES, String.valueOf(100));
  private BlockWorkerClientServiceHandler mBlockWorkerServiceHandler = null;
  private FileSystem mFileSystem = null;
  private TachyonConf mMasterTachyonConf;
  private TachyonConf mWorkerTachyonConf;
  private BlockMasterClient mBlockMasterClient;

  @BeforeClass
  public static void beforeClass() {
    HeartbeatContext.setTimerClass(HeartbeatContext.WORKER_BLOCK_SYNC,
        HeartbeatContext.SCHEDULED_TIMER_CLASS);
  }

  @AfterClass
  public static void afterClass() {
    HeartbeatContext.setTimerClass(HeartbeatContext.WORKER_BLOCK_SYNC,
        HeartbeatContext.SLEEPING_TIMER_CLASS);
  }

  @Before
  public final void before() throws Exception {
    mFileSystem = mLocalTachyonClusterResource.get().getClient();
    mMasterTachyonConf = mLocalTachyonClusterResource.get().getMasterTachyonConf();
    mWorkerTachyonConf = mLocalTachyonClusterResource.get().getWorkerTachyonConf();
    mBlockWorkerServiceHandler =
        mLocalTachyonClusterResource.get().getWorker().getBlockWorkerServiceHandler();

    mBlockMasterClient = new BlockMasterClient(
        new InetSocketAddress(mLocalTachyonClusterResource.get().getMasterHostname(),
            mLocalTachyonClusterResource.get().getMasterPort()),
        mWorkerTachyonConf);
  }

  @After
  public final void after() throws Exception {
    mBlockMasterClient.close();
  }

  // Tests that caching a block successfully persists the block if the block exists
  @Test
  public void cacheBlockTest() throws Exception {
    mFileSystem.createFile(new TachyonURI("/testFile"));
    URIStatus file = mFileSystem.getStatus(new TachyonURI("/testFile"));

    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;
    // Construct the block ids for the file.
    final long blockId0 = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);
    final long blockId1 = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 1);

    String filename =
        mBlockWorkerServiceHandler.requestBlockLocation(SESSION_ID, blockId0, blockSize);
    createBlockFile(filename, blockSize);
    mBlockWorkerServiceHandler.cacheBlock(SESSION_ID, blockId0);

    // The master should be immediately updated with the persisted block
    Assert.assertEquals(blockSize, mBlockMasterClient.getUsedBytes());

    // Attempting to cache a non existent block should throw an exception
    Exception exception = null;
    try {
      mBlockWorkerServiceHandler.cacheBlock(SESSION_ID, blockId1);
    } catch (TException e) {
      exception = e;
    }
    Assert.assertNotNull(exception);
  }

  // Tests that cancelling a block will remove the temporary file
  @Test
  public void cancelBlockTest() throws Exception {
    mFileSystem.createFile(new TachyonURI("/testFile"));
    URIStatus file = mFileSystem.getStatus(new TachyonURI("/testFile"));

    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;
    final long blockId = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);

    String filename =
        mBlockWorkerServiceHandler.requestBlockLocation(SESSION_ID, blockId, blockSize);
    createBlockFile(filename, blockSize);
    mBlockWorkerServiceHandler.cancelBlock(SESSION_ID, blockId);

    // The block should not exist after being cancelled
    Assert.assertFalse(new File(filename).exists());

    // The master should not have recorded any used space after the block is cancelled
    waitForHeartbeat();
    Assert.assertEquals(0, mBlockMasterClient.getUsedBytes());
  }

  // Tests that lock block returns the correct path
  @Test
  public void lockBlockTest() throws Exception {
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;

    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(blockSize)
            .setWriteType(WriteType.MUST_CACHE);
    FileOutStream out = mFileSystem.createFile(new TachyonURI("/testFile"), options);
    URIStatus file = mFileSystem.getStatus(new TachyonURI("/testFile"));

    final long blockId = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);

    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();

    String localPath = mBlockWorkerServiceHandler.lockBlock(blockId, SESSION_ID).getBlockPath();

    // The local path should exist
    Assert.assertNotNull(localPath);

    UnderFileSystem ufs = UnderFileSystem.get(localPath, mMasterTachyonConf);
    byte[] data = new byte[blockSize];
    int bytesRead = ufs.open(localPath).read(data);

    // The data in the local file should equal the data we wrote earlier
    Assert.assertEquals(blockSize, bytesRead);
    Assert.assertTrue(BufferUtils.equalIncreasingByteArray(bytesRead, data));

    mBlockWorkerServiceHandler.unlockBlock(blockId, SESSION_ID);
  }

  // Tests that lock block returns error on failure
  @Test
  public void lockBlockFailureTest() throws Exception {
    mFileSystem.createFile(new TachyonURI("/testFile"));
    URIStatus file = mFileSystem.getStatus(new TachyonURI("/testFile"));
    final long blockId = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);

    Exception exception = null;
    try {
      mBlockWorkerServiceHandler.lockBlock(blockId, SESSION_ID);
    } catch (TachyonTException e) {
      exception = e;
    }

    // A file does not exist exception should have been thrown
    Assert.assertNotNull(exception);
  }

  // Tests that files are evicted when there is not enough space in the worker.
  @Test
  public void evictionTest() throws Exception {
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;
    TachyonURI file1 = new TachyonURI("/file1");
    FileSystemTestUtils.createByteFile(mFileSystem, file1, WriteType.MUST_CACHE, blockSize);

    // File should be in memory after it is written with MUST_CACHE
    URIStatus fileInfo1 = mFileSystem.getStatus(file1);
    Assert.assertEquals(100, fileInfo1.getInMemoryPercentage());

    TachyonURI file2 = new TachyonURI("/file2");
    FileSystemTestUtils.createByteFile(mFileSystem, file2, WriteType.MUST_CACHE, blockSize);

    // Both file 1 and 2 should be in memory since the combined size is not larger than worker space
    fileInfo1 = mFileSystem.getStatus(file1);
    URIStatus fileInfo2 = mFileSystem.getStatus(file2);
    Assert.assertEquals(100, fileInfo1.getInMemoryPercentage());
    Assert.assertEquals(100, fileInfo2.getInMemoryPercentage());

    TachyonURI file3 = new TachyonURI("/file3");
    FileSystemTestUtils.createByteFile(mFileSystem, file3, WriteType.MUST_CACHE, blockSize);

    waitForHeartbeat();

    fileInfo1 = mFileSystem.getStatus(file1);
    fileInfo2 = mFileSystem.getStatus(file2);
    URIStatus fileInfo3 = mFileSystem.getStatus(file3);

    // File 3 should be in memory and one of file 1 or 2 should be in memory
    Assert.assertEquals(100, fileInfo3.getInMemoryPercentage());
    Assert.assertTrue("Exactly one of file1 and file2 should be 100% in memory",
        fileInfo1.getInMemoryPercentage() == 100 ^ fileInfo2.getInMemoryPercentage() == 100);
  }

  // Tests that space will be allocated when possible
  @Test
  public void requestSpaceTest() throws Exception {
    final long blockId1 = 12345L;
    final long blockId2 = 12346L;
    final int chunkSize = (int) WORKER_CAPACITY_BYTES / 10;

    mBlockWorkerServiceHandler.requestBlockLocation(SESSION_ID, blockId1, chunkSize);
    boolean result = mBlockWorkerServiceHandler.requestSpace(SESSION_ID, blockId1, chunkSize);

    // Initial request and first additional request should succeed
    Assert.assertTrue(result);

    result = mBlockWorkerServiceHandler.requestSpace(SESSION_ID, blockId1, WORKER_CAPACITY_BYTES);

    // Impossible request should fail
    Assert.assertFalse(result);

    // Request for space on a nonexistent block should fail
    Assert.assertFalse(mBlockWorkerServiceHandler.requestSpace(SESSION_ID, blockId2, chunkSize));

    // Request for impossible initial space should fail
    Exception exception = null;
    try {
      mBlockWorkerServiceHandler.requestBlockLocation(SESSION_ID, blockId2,
          WORKER_CAPACITY_BYTES + 1);
    } catch (TachyonTException e) {
      exception = e;
    }
    Assert.assertNotNull(exception);
  }

  // Tests that multiple users cannot request a combined space greater than worker space
  @Test
  public void totalOverCapacityRequestSpaceTest() throws Exception {
    final int chunkSize = (int) WORKER_CAPACITY_BYTES / 2;
    final long userId1 = SESSION_ID;
    final long userId2 = SESSION_ID + 1;
    final long blockId1 = 12345L;
    final long blockId2 = 23456L;

    String filePath1 =
        mBlockWorkerServiceHandler.requestBlockLocation(userId1, blockId1, chunkSize);
    String filePath2 =
        mBlockWorkerServiceHandler.requestBlockLocation(userId2, blockId2, chunkSize);

    // Initial requests should succeed
    Assert.assertTrue(filePath1 != null);
    Assert.assertTrue(filePath2 != null);

    // Additional requests for space should fail
    Assert.assertFalse(mBlockWorkerServiceHandler.requestSpace(userId1, blockId1, chunkSize));
    Assert.assertFalse(mBlockWorkerServiceHandler.requestSpace(userId2, blockId2, chunkSize));
  }

  // Creates a block file and write an increasing byte array into it
  private void createBlockFile(String filename, int len) throws IOException, InvalidPathException {
    UnderFileSystem ufs = UnderFileSystem.get(filename, mMasterTachyonConf);
    ufs.mkdirs(PathUtils.getParent(filename), true);
    OutputStream out = ufs.create(filename);
    out.write(BufferUtils.getIncreasingByteArray(len), 0, len);
    out.close();
  }

  // Waits for a worker heartbeat to master to be processed
  private void waitForHeartbeat() throws InterruptedException {
    Assert.assertTrue(HeartbeatScheduler.await(HeartbeatContext.WORKER_BLOCK_SYNC, 5,
        TimeUnit.SECONDS));
    HeartbeatScheduler.schedule(HeartbeatContext.WORKER_BLOCK_SYNC);
    // Wait for the next heartbeat to be ready to guarantee that the previous heartbeat has finished
    Assert.assertTrue(HeartbeatScheduler.await(HeartbeatContext.WORKER_BLOCK_SYNC, 5,
        TimeUnit.SECONDS));
  }
}
