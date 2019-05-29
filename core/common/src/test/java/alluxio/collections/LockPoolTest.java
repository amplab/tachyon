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

package alluxio.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import alluxio.concurrent.LockMode;
import alluxio.resource.LockResource;
import alluxio.util.CommonUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Tests the {@link LockPool} class.
 */
public class LockPoolTest {
  private LockPool<Integer> mCache;
  private static final int LOW_WATERMARK = 8;
  private static final int HIGH_WATERMARK = 16;

  /**
   * Sets up the fields before running a test.
   */
  @Before
  public void before() {
    mCache = new LockPool<>(k -> new ReentrantReadWriteLock(), 2, LOW_WATERMARK, HIGH_WATERMARK, 4);
  }

  @Test(timeout = 10000)
  public void insertValueTest() throws Exception {
    for (int i = 0; i < HIGH_WATERMARK; i++) {
      assertEquals(i , mCache.size());
      try (LockResource resource = mCache.get(i, LockMode.READ)) {
        assertTrue(mCache.containsKey(i));
        assertEquals(i + 1, mCache.size());
      }
    }

    // it should be full now
    for (int i = HIGH_WATERMARK; i < 2 * HIGH_WATERMARK; i++) {
      try (LockResource resource = mCache.get(i, LockMode.READ)) {
        assertTrue(mCache.containsKey(i));
        CommonUtils.waitFor("Cache size to go below low watermark",
            () -> mCache.size() <= LOW_WATERMARK);
        assertEquals(LOW_WATERMARK, mCache.size());
      }
    }
  }

  private Thread getKeys(int low, int high, int totalThreadCount) {
    Thread t = new Thread(() -> {
      for (int i = low; i < high; i++) {
        try (LockResource resource = mCache.get(i, LockMode.READ)) {
          assertTrue(mCache.size() <= HIGH_WATERMARK + totalThreadCount);
          assertTrue(mCache.containsKey(i));
        }
      }
    });
    t.start();
    return t;
  }

  @Test(timeout = 1000)
  public void parallelInsertTest() throws InterruptedException {
    Thread t1 = getKeys(0, HIGH_WATERMARK * 2, 4);
    Thread t2 = getKeys(0, HIGH_WATERMARK * 2, 4);
    Thread t3 = getKeys(HIGH_WATERMARK * 2, HIGH_WATERMARK * 4, 4);
    Thread t4 = getKeys(HIGH_WATERMARK * 2, HIGH_WATERMARK * 4, 4);
    t1.join();
    t2.join();
    t3.join();
    t4.join();
  }

  @Test(timeout = 1000)
  public void referencedLockTest() throws InterruptedException {
    LockResource lock0 = mCache.get(0, LockMode.READ);
    LockResource lock1 = mCache.get(50, LockMode.READ);
    LockResource lock2 = mCache.get(100, LockMode.READ);

    for (int j = 0; j < 10; j++) {
      for (int i = 0; i < 100; i++) {
        mCache.get(i, LockMode.READ).close();
      }
    }
    assertTrue(lock0.hasSameLock(mCache.get(0, LockMode.READ)));
    assertTrue(lock1.hasSameLock(mCache.get(50, LockMode.READ)));
    assertTrue(lock2.hasSameLock(mCache.get(100, LockMode.READ)));
  }
}
