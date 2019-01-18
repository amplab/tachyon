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

import alluxio.resource.LockResource;
import alluxio.resource.RefCountLockResource;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

/**
 * A cache specifically designed to contain locks and will NOT evict any entries
 * that are in use.
 *
 * @param <K> key to the cache
 */
public class LockCache<K> {
  /**
   * Node containing value and other information to be stored in the cache.
   */
  public class ValNode {
    private ReentrantReadWriteLock mValue;
    private boolean mIsAccessed;
    private AtomicInteger mRefCount;

    private ValNode(ReentrantReadWriteLock value) {
      mValue = value;
      mIsAccessed = false;
      mRefCount = new AtomicInteger(1);
    }

    /**
     * Get the ref counter associated with this value node.
     *
     * @return a ref counter
     */
    public AtomicInteger getRefCounter() {
      return mRefCount;
    }

    /**
     * Get the value contained in the value node.
     *
     * @return the lock contained in the value node
     */
    public ReentrantReadWriteLock get() {
      return mValue;
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(LockCache.class);
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;
  static final float SOFT_LIMIT_RATIO = 0.9f;

  private final Map<K, ValNode> mCache;

  /**
   * SoftLimit = hardLimit * softLimitRatio
   * once the size reaches softlimit, eviction starts to happen,
   * once the size reaches hardlimit, blocking starts to happen.
   */
  private final int mHardLimit;
  private final int mSoftLimit;
  private final Function<? super K, ? extends ReentrantReadWriteLock> mDefaultLoader;
  private Iterator<Map.Entry<K, ValNode>> mIterator;
  private final Lock mEvictLock;

  private long mLastLogTime;

  /**
   * Constructor for a lock cache.
   *
   * @param defaultLoader specify a function to generate a value based on a key
   * @param initialSize initial size of the cache
   * @param maxSize maximum size of the cache
   * @param concurrencyLevel concurrency level of the cache
   */
  public LockCache(@Nullable Function<? super K, ? extends ReentrantReadWriteLock> defaultLoader,
      int initialSize, int maxSize, int concurrencyLevel) {
    mDefaultLoader = defaultLoader;

    mHardLimit = maxSize;
    mSoftLimit = (int) Math.round(SOFT_LIMIT_RATIO * maxSize);
    mCache = new ConcurrentHashMap<>(initialSize, DEFAULT_LOAD_FACTOR, concurrencyLevel);
    mIterator = mCache.entrySet().iterator();
    mEvictLock = new ReentrantLock();
    mLastLogTime = System.currentTimeMillis();
  }

  private void evictIfOverLimit() {
    long numToEvict = mCache.size() - mSoftLimit;

    if (numToEvict <= 0) {
      return;
    }

    if (mEvictLock.tryLock()) {
      try {
        // This thread won the race as the evictor
        while (numToEvict > 0) {

          if (!mIterator.hasNext()) {
            mIterator = mCache.entrySet().iterator();
          }
          Map.Entry<K, ValNode> candidateMapEntry = mIterator.next();
          ValNode candidate = candidateMapEntry.getValue();
          if (candidate.mIsAccessed) {
            candidate.mIsAccessed = false;
          } else {
            if (candidate.mRefCount.compareAndSet(0, Integer.MIN_VALUE)) {
              // the value object can be evicted, at the same time we make refCount minValue
              mIterator.remove();
              numToEvict--;
            }
          }
        }
      } finally {
        mEvictLock.unlock();
      }
    }
  }

  /**
   * Get the value from the cache.
   *
   * @param key the key to look up the cache
   * @param mode lockMode to acquire
   * @return the value contained in the cache, if it is already in cache,
   *         otherwise generate an entry based on the loader
   */

  public LockResource get(final K key, LockResource.LockMode mode) {
    ValNode valNode = getValNode(key);
    ReentrantReadWriteLock lock = valNode.mValue;
    switch (mode) {
      case READ:
        return new RefCountLockResource(lock.readLock(), valNode.mRefCount);
      case WRITE:
        return new RefCountLockResource(lock.writeLock(), valNode.mRefCount);
      default:
        throw new IllegalStateException("Unknown lock mode: " + mode);
    }
  }

  /**
   * Get the raw readwrite lock from the cache.
   *
   * @param key key to look up the value
   * @return the lock associated with the key
   */

  @VisibleForTesting
  public ReentrantReadWriteLock getRawReadWriteLock(final K key) {
    return mCache.getOrDefault(key, new ValNode(new ReentrantReadWriteLock())).mValue;
  }

  private ValNode getValNode(final K key) {
    Preconditions.checkNotNull(key, "key can not be null");
    // oldCacheEntry keeps track of the last cache entry that was in the process of being removed.
    // we do not need to do anything if we did not get a new CacheEntry
    ValNode oldCacheEntry = null;
    ValNode cacheEntry;
    while (true) {
      // repeat until we get a cacheEntry that is not in the process of being removed.
      cacheEntry = mCache.compute(key, (k, v) -> {
        if (v != null) {
          v.getRefCounter().incrementAndGet();
          v.mIsAccessed = true;
          return v;
        }
        // new cache entry
        if (mCache.size() >= mHardLimit) {
          return null;
        } else {
          return new ValNode(mDefaultLoader.apply(k));
        }
      });

      if (cacheEntry == null) {
        // cache is at hard limit
        try {
          if (System.currentTimeMillis() - mLastLogTime > 60000) {
            LOG.warn("Cache at hard limit, cache ize = " + mCache.size()
                + " softLimit = " + mSoftLimit + " hardLimit = " + mHardLimit);
            mLastLogTime = System.currentTimeMillis();
          }
          Thread.sleep(100);
          evictIfOverLimit();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        continue;
      }

      if ((oldCacheEntry != cacheEntry)
          && cacheEntry.mRefCount.get() > 0) {
        evictIfOverLimit();
        return cacheEntry;
      } else {
        // refCount went negative, we are evicting this entry
        oldCacheEntry = cacheEntry;
        evictIfOverLimit();
        //TODO(yuzhu): sleep here to prevent overloading the cache
      }
    }
  }

  /**
   * Returns whether the cache contains a particular key.
   *
   * @param key the key to look up in the cache
   * @return true if the key is contained in the cache
   */
  public boolean contains(final K key) {
    Preconditions.checkNotNull(key, "key can not be null");
    return mCache.containsKey(key);
  }

  /**
   * Returns the size of the cache.
   *
   * @return the number of entries cached
   */
  public int size() {
    return mCache.size();
  }
}
