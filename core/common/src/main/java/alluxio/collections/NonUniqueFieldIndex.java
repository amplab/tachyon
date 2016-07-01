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

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class representing a non-unique index. A non-unique index is
 * an index where an index value can map to one or more objects.
 *
 * @param <T> type of objects in this {@link IndexedSet}
 */
class NonUniqueFieldIndex<T> implements FieldIndex<T> {
  private final IndexDefinition<T> mIndexDefinition;
  private final ConcurrentHashMap<Object, ConcurrentHashSet<T>> mIndexMap;

  /**
   * Constructs a new {@link NonUniqueFieldIndex} instance.
   */
  public NonUniqueFieldIndex(IndexDefinition<T> indexDefinition) {
    mIndexMap = new ConcurrentHashMap<>(8, 0.95f, 8);
    mIndexDefinition = indexDefinition;
  }

  @Override
  public void add(T object) {
    Object fieldValue = mIndexDefinition.getFieldValue(object);

    ConcurrentHashSet<T> objSet;

    while (true) {
      objSet = mIndexMap.get(fieldValue);
      // If there is no object set for the current value, creates a new one.
      while (objSet == null) {
        mIndexMap.putIfAbsent(fieldValue, new ConcurrentHashSet<T>());
        objSet = mIndexMap.get(fieldValue);
      }

      synchronized (objSet) {
        if (objSet != mIndexMap.get(fieldValue)) {
          continue;
        }
        // Adds the value to the object set.
        objSet.add(object);
        break;
      }
    }
  }

  @Override
  public boolean remove(T object) {
    boolean res = false;
    Object fieldValue = mIndexDefinition.getFieldValue(object);
    ConcurrentHashSet<T> objSet = mIndexMap.get(fieldValue);
    if (objSet != null) {
      synchronized (objSet) {
        if (objSet != mIndexMap.get(fieldValue)) {
          return false;
        }
        res = objSet.remove(object);
        if (objSet.isEmpty()) {
          mIndexMap.remove(fieldValue, objSet);
        }
      }
    }
    return res;
  }

  @Override
  public boolean contains(Object value) {
    return mIndexMap.containsKey(value);
  }

  @Override
  public boolean containsObject(Object object) {
    for (ConcurrentHashSet<T> innerSet : mIndexMap.values()) {
      if (innerSet.contains(object)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Set<T> getByField(Object value) {
    Set<T> set = mIndexMap.get(value);
    return set == null ? Collections.<T>emptySet() : set;
  }

  @Override
  public T getFirst(Object value) {
    Set<T> all = mIndexMap.get(value);
    try {
      return all == null || !all.iterator().hasNext() ? null : all.iterator().next();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new NonUniqueFieldIndexIterator();
  }

  @Override
  public int size() {
    int totalSize = 0;
    for (ConcurrentHashSet<T> innerSet : mIndexMap.values()) {
      totalSize += innerSet.size();
    }
    return totalSize;
  }

  /**
   * Specialized iterator for {@link NonUniqueFieldIndex}.
   *
   * This is needed to support consistent removal from the set and the indices.
   */
  private class NonUniqueFieldIndexIterator implements Iterator<T> {
    private final Iterator<ConcurrentHashSet<T>> mSetIterator;
    private Iterator<T> mIndexIterator;
    private T mObject;

    public NonUniqueFieldIndexIterator() {
      mSetIterator = mIndexMap.values().iterator();
      mIndexIterator = null;
      mObject = null;
    }

    @Override
    public boolean hasNext() {
      if (mIndexIterator != null && mIndexIterator.hasNext()) {
        return true;
      }
      while (mSetIterator.hasNext()) {
        mIndexIterator = mSetIterator.next().iterator();
        if (mIndexIterator.hasNext()) {
          return true;
        }
      }
      return false;
    }

    @Override
    public T next() {
      while (mIndexIterator == null || !mIndexIterator.hasNext()) {
        mIndexIterator = mSetIterator.next().iterator();
      }

      final T next = mIndexIterator.next();
      mObject = next;
      return next;
    }

    @Override
    public void remove() {
      if (mObject != null) {
        NonUniqueFieldIndex.this.remove(mObject);
        mObject = null;
      } else {
        throw new IllegalStateException("next() was not called before remove()");
      }
    }

  }
}
