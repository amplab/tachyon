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

package tachyon.master.next;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tachyon.Constants;
import tachyon.TachyonURI;
import tachyon.client.TachyonFS;
import tachyon.conf.TachyonConf;
import tachyon.master.Journal;
import tachyon.master.LocalTachyonCluster;
import tachyon.master.LocalTachyonClusterMultiMaster;
import tachyon.master.MasterInfo;
import tachyon.thrift.FileDoesNotExistException;
import tachyon.thrift.InvalidPathException;
import tachyon.thrift.TableDoesNotExistException;
import tachyon.util.CommonUtils;

/**
 * Test master journal for cluster terminating. Assert that test can replay the editlog and
 * reproduce the correct state. Test both the single master(tachyon) and multi masters(tachyon-ft).
 */
public class JournalShutdownIntegrationTest {

  /**
   * Hold a client and keep creating files/tables.
   */
  class ClientThread implements Runnable {
    /** The number of successfully created files/tables. */
    private int mSuccessNum = 0;

    private final int mOpType;  //0:create file; 1:create raw table.
    private final TachyonFS mTfs;

    public ClientThread(int opType, TachyonFS tfs) {
      mOpType = opType;
      mTfs = tfs;
    }

    public int getSuccessNum() {
      return mSuccessNum;
    }

    /**
     * Keep creating files/tables until something crashes or fail to create. Record how many files/
     * tables are created successfully.
     */
    @Override
    public void run() {
      try {
        // This infinity loop will be broken if something crashes or fail to create. This is
        // expected since the master will shutdown at a certain time.
        while (true) {
          if (mOpType == 0) {
            if (mTfs.createFile(new TachyonURI(TEST_FILE_DIR + mSuccessNum)) == -1) {
              break;
            }
          } else if (mOpType == 1) {
            if (mTfs.createRawTable(new TachyonURI(TEST_TABLE_DIR + mSuccessNum), 1) == -1) {
              break;
            }
          }
          mSuccessNum ++;
          CommonUtils.sleepMs(null, 100);
        }
      } catch (Exception e) {
        // Something crashed. Stop the thread.
      }
    }
  }

  private static final int TEST_BLOCK_SIZE = 128;
  private static final String TEST_FILE_DIR = "/files/";
  private static final int TEST_NUM_MASTERS = 3;
  private static final String TEST_TABLE_DIR = "/tables/";
  private static final long TEST_TIME_MS = Constants.SECOND_MS;

  private ClientThread mCreateFileThread = null;
  private ClientThread mCreateTableThread = null;
  /** Executor for running client threads */
  private final ExecutorService mExecutorsForClient = Executors.newFixedThreadPool(2);
  /** Executor for constructing MasterInfo */
  private final ExecutorService mExecutorsForMasterInfo = Executors.newFixedThreadPool(2);
  private TachyonConf mMasterTachyonConf =  null;

  @After
  public final void after() throws Exception {
    mExecutorsForClient.shutdown();
    mExecutorsForMasterInfo.shutdown();
    System.clearProperty("fs.hdfs.impl.disable.cache");
  }

  @Before
  public final void before() throws Exception {
    System.setProperty("fs.hdfs.impl.disable.cache", "true");
  }

  /**
   * Reproduce the journal and check if the state is correct.
   */
  private void reproduceAndCheckState(int successFiles, int successTables) throws IOException,
      InvalidPathException, FileDoesNotExistException, TableDoesNotExistException {
    String masterJournal = mMasterTachyonConf.get(Constants.MASTER_JOURNAL_FOLDER);
    Journal journal = new Journal(masterJournal, "image.data", "log.data", mMasterTachyonConf);
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal, mExecutorsForMasterInfo,
        mMasterTachyonConf);
    info.init();

    Assert.assertEquals(successFiles, info.listFiles(new TachyonURI(TEST_FILE_DIR), false).size());
    for (int f = 0; f < successFiles; f ++) {
      Assert.assertTrue(info.getFileId(new TachyonURI(TEST_FILE_DIR + f)) != -1);
    }
    Assert.assertEquals(successTables,
        info.listFiles(new TachyonURI(TEST_TABLE_DIR), false).size());
    for (int t = 0; t < successTables; t ++) {
      Assert.assertTrue(info.getRawTableId(new TachyonURI(TEST_TABLE_DIR + t)) != -1);
    }
    info.stop();
  }

  private LocalTachyonClusterMultiMaster setupMultiMasterCluster() throws IOException {
    // Setup and start the tachyon-ft cluster.
    LocalTachyonClusterMultiMaster cluster = new LocalTachyonClusterMultiMaster(100,
        TEST_NUM_MASTERS, TEST_BLOCK_SIZE);
    cluster.start();
    mMasterTachyonConf = cluster.getMasterTachyonConf();
    mCreateFileThread = new ClientThread(0, cluster.getClient());
    mCreateTableThread = new ClientThread(1, cluster.getClient());
    mExecutorsForClient.submit(mCreateFileThread);
    mExecutorsForClient.submit(mCreateTableThread);
    return cluster;
  }

  private tachyon.master.LocalTachyonCluster setupSingleMasterCluster() throws IOException {
    // Setup and start the local tachyon cluster.
    tachyon.master.LocalTachyonCluster cluster = new tachyon.master.LocalTachyonCluster(100, 100, TEST_BLOCK_SIZE);
    cluster.start();
    mMasterTachyonConf = cluster.getMasterTachyonConf();
    mCreateFileThread = new ClientThread(0, cluster.getClient());
    mCreateTableThread = new ClientThread(1, cluster.getClient());
    mExecutorsForClient.submit(mCreateFileThread);
    mExecutorsForClient.submit(mCreateTableThread);
    return cluster;
  }

  @Test
  public void singleMasterJournalCrashIntegrationTest() throws Exception {
    LocalTachyonCluster cluster = setupSingleMasterCluster();
    CommonUtils.sleepMs(null, TEST_TIME_MS);
    // Shutdown the cluster
    cluster.stopTFS();
    CommonUtils.sleepMs(null, TEST_TIME_MS);
    // Ensure the client threads are stopped.
    mExecutorsForClient.shutdown();
    CommonUtils.sleepMs(null, TEST_TIME_MS);
    reproduceAndCheckState(mCreateFileThread.getSuccessNum(), mCreateTableThread.getSuccessNum());
    // clean up
    cluster.stopUFS();
  }

  @Test
  public void multiMasterJournalCrashIntegrationTest() throws Exception {
    LocalTachyonClusterMultiMaster cluster = setupMultiMasterCluster();
    // Kill the leader one by one.
    for (int kills = 0; kills < TEST_NUM_MASTERS; kills ++) {
      CommonUtils.sleepMs(null, TEST_TIME_MS);
      Assert.assertTrue(cluster.killLeader());
    }
    CommonUtils.sleepMs(null, TEST_TIME_MS);
    // Ensure the client threads are stopped.
    mExecutorsForClient.shutdown();
    CommonUtils.sleepMs(null, TEST_TIME_MS);
    reproduceAndCheckState(mCreateFileThread.getSuccessNum(), mCreateTableThread.getSuccessNum());
    // clean up
    cluster.stopUFS();
  }
}
