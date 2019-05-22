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

package alluxio.concurrent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link CountLatch}.
 */
public class CountLatchTest {
  private static final long SLEEP_MILLIS = 1000;
  private static final long STILL_BLOCKED = -1;

  private CountLatch mLatch;

  private static class BlockingThread extends Thread {
    private volatile long mBlockedTimeMillis;
    private final Runnable mRunnable;

    public BlockingThread(Runnable runnable) {
      mBlockedTimeMillis = STILL_BLOCKED;
      mRunnable = runnable;
    }

    public void run() {
      long start = System.currentTimeMillis();
      mRunnable.run();
      mBlockedTimeMillis = System.currentTimeMillis() - start;
    }

    public long getBlockedTimeMillis() {
      return mBlockedTimeMillis;
    }
  }

  @Rule
  public ExpectedException mExpectedException = ExpectedException.none();

  @Before
  public void before() {
    mLatch = new CountLatch();
  }

  /**
   * Tests that inc and dec can proceed without being blocked when there is no awaitAndBlockInc.
   */
  @Test
  public void noAwait() {
    final int N = 10;
    for (int i = 0; i < N; i++) {
      Assert.assertEquals(0, mLatch.getState());
      mLatch.inc();
      Assert.assertEquals(1, mLatch.getState());
      mLatch.dec();
      Assert.assertEquals(0, mLatch.getState());
    }
    for (int i = 0; i < N; i++) {
      Assert.assertEquals(i, mLatch.getState());
      mLatch.inc();
    }
    for (int i = 0; i < N; i++) {
      mLatch.dec();
      Assert.assertEquals(N - 1 - i, mLatch.getState());
    }
  }

  /**
   * Tests that inc is blocked when awaitAndBlockInc returns,
   * and inc is unblocked when unblockInc returns.
   */
  @Test
  public void blockAndUnblockInc() throws Exception {
    Assert.assertEquals(0, mLatch.getState());
    mLatch.awaitAndBlockInc();
    Assert.assertEquals(-1, mLatch.getState());

    BlockingThread inc = new BlockingThread(mLatch::inc);
    inc.start();

    Thread.sleep(SLEEP_MILLIS);
    mLatch.unblockInc();

    inc.join();
    Assert.assertTrue(inc.getBlockedTimeMillis() >= SLEEP_MILLIS);
    Assert.assertEquals(1, mLatch.getState());
  }

  /**
   * Tests that multiple blocked inc can be unblocked when unblockInc returns.
   */
  @Test
  public void unblockMultipleInc() throws Exception {
    Assert.assertEquals(0, mLatch.getState());
    mLatch.awaitAndBlockInc();
    Assert.assertEquals(-1, mLatch.getState());

    List<BlockingThread> incThreads = new ArrayList<>();
    final int numThreads = 10;
    for (int i = 0; i < numThreads; i++) {
      incThreads.add(new BlockingThread(mLatch::inc));
    }
    for (BlockingThread t : incThreads) {
      t.start();
    }

    Thread.sleep(SLEEP_MILLIS);
    mLatch.unblockInc();

    for (BlockingThread t : incThreads) {
      t.join();
      Assert.assertTrue(t.getBlockedTimeMillis() >= SLEEP_MILLIS);
    }
    Assert.assertEquals(numThreads, mLatch.getState());
  }

  /**
   * Tests that awaitAndBlockInc is blocked when state is not 0.
   */
  @Test
  public void await() throws Exception {
    Assert.assertEquals(0, mLatch.getState());
    mLatch.inc();
    Assert.assertEquals(1, mLatch.getState());
    mLatch.inc();
    Assert.assertEquals(2, mLatch.getState());

    BlockingThread await = new BlockingThread(mLatch::awaitAndBlockInc);
    await.start();

    Assert.assertEquals(STILL_BLOCKED, await.getBlockedTimeMillis());

    Thread.sleep(SLEEP_MILLIS);
    mLatch.dec();
    Assert.assertEquals(1, mLatch.getState());
    Assert.assertEquals(STILL_BLOCKED, await.getBlockedTimeMillis());

    Thread.sleep(SLEEP_MILLIS);
    mLatch.dec();
    Assert.assertEquals(0, mLatch.getState());

    await.join();
    Assert.assertTrue(await.getBlockedTimeMillis() >= 2 * SLEEP_MILLIS);
    Assert.assertEquals(-1, mLatch.getState());
  }

  /**
   * Tests that calling dec without a paired inc will throw exception.
   */
  @Test
  public void decWithoutInc() {
    mLatch.inc();
    mLatch.dec();
    mExpectedException.expect(Error.class);
    mLatch.dec();
  }

  /**
   * Tests that unblock without a paired awaitAndBlockInc will throw exception.
   */
  @Test
  public void unblockWithoutAwait() {
    mLatch.awaitAndBlockInc();
    mLatch.unblockInc();
    mExpectedException.expect(Error.class);
    mLatch.unblockInc();
  }
}
