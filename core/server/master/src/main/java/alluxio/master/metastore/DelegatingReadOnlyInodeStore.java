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

package alluxio.master.metastore;

import alluxio.master.file.meta.Inode;
import alluxio.master.file.meta.InodeDirectoryView;

import java.util.Optional;

/**
 * Wrapper for providing read-only access to an inode store.
 */
public class DelegatingReadOnlyInodeStore implements ReadOnlyInodeStore {
  private final InodeStore mDelegate;

  /**
   * @param delegate the delegate inode store
   */
  public DelegatingReadOnlyInodeStore(InodeStore delegate) {
    mDelegate = delegate;
  }

  @Override
  public long estimateSize() {
    return mDelegate.estimateSize();
  }

  @Override
  public Optional<Inode> get(long id) {
    return mDelegate.get(id);
  }

  @Override
  public Iterable<Long> getChildIds(InodeDirectoryView inode) {
    return mDelegate.getChildIds(inode);
  }

  @Override
  public Iterable<? extends Inode> getChildren(InodeDirectoryView inode) {
    return mDelegate.getChildren(inode);
  }

  @Override
  public Optional<Long> getChildId(InodeDirectoryView inode, String name) {
    return mDelegate.getChildId(inode, name);
  }

  @Override
  public Optional<Inode> getChild(InodeDirectoryView inode, String name) {
    return mDelegate.getChild(inode, name);
  }

  @Override
  public boolean hasChildren(InodeDirectoryView inode) {
    return mDelegate.hasChildren(inode);
  }
}
