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

package alluxio.underfs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import alluxio.AlluxioURI;
import alluxio.Constants;
import alluxio.conf.ServerConfiguration;
import alluxio.exception.InvalidPathException;
import alluxio.grpc.MountPOptions;
import alluxio.master.file.meta.MountTable;
import alluxio.master.file.meta.options.MountInfo;
import alluxio.underfs.local.LocalUnderFileSystem;
import alluxio.util.IdUtils;
import alluxio.util.io.PathUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class UfsStatusCacheTest {

  @Rule
  public TemporaryFolder mTempDir = new TemporaryFolder();

  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  private String mUfsUri;
  private LocalUnderFileSystem mUfs;
  private UfsStatusCache mCache;
  private ExecutorService mService;
  private MountTable mMountTable;

  @Before
  public void before() throws Exception {
    mUfsUri = mTempDir.newFolder().getAbsolutePath();
    mUfs = new LocalUnderFileSystem(new AlluxioURI(mUfsUri),
        UnderFileSystemConfiguration.defaults(ServerConfiguration.global()));
    mService = Executors.newSingleThreadExecutor();
    mCache = new UfsStatusCache(mService);
    MountInfo rootMountInfo = new MountInfo(
        new AlluxioURI(MountTable.ROOT),
        new AlluxioURI(mUfsUri),
        IdUtils.ROOT_MOUNT_ID,
        MountPOptions.newBuilder()
            .setReadOnly(false)
            .setShared(false)
            .build());
    MasterUfsManager manager = new MasterUfsManager();
    manager.getRoot(); // add root mount
    mMountTable = new MountTable(manager, rootMountInfo);
  }

  @After
  public void after() throws Exception {
    mService.shutdown();
    if (!mService.awaitTermination(50, TimeUnit.MILLISECONDS)) {
      mService.shutdownNow();
    }
  }

  @Test
  public void testMismatchingChildComponent() {
    UfsStatus s = Mockito.mock(UfsStatus.class);
    when(s.getName()).thenReturn("name");
    mThrown.expect(IllegalArgumentException.class);
    mCache.addStatus(new AlluxioURI("/notName"), s);
  }

  @Test
  public void testDoubleRemove() {
    UfsStatus s = Mockito.mock(UfsStatus.class);
    when(s.getName()).thenReturn("name");
    AlluxioURI u = new AlluxioURI("/name");
    mCache.addStatus(u, s);
    assertEquals(s, mCache.remove(u));
    assertNull(mCache.remove(u));
  }

  @Test
  public void testAddRemove() {
    AlluxioURI path = new AlluxioURI("/abc/123");
    UfsStatus stat = Mockito.mock(UfsStatus.class);
    when(stat.getName()).thenReturn("123");
    mCache.addStatus(path, stat);
    assertEquals(stat, mCache.getStatus(path));
    mCache.remove(path);
    assertNull(mCache.getStatus(path));
  }

  @Test
  public void testCancelPrefetch() throws Exception {
    for (int i = 65; i < 90; i++) {
      createUfsDirs(Character.getName(i));
    }
    for (int i = 65; i < 90; i++) {
      mCache.prefetchChildren(new AlluxioURI("/" + Character.getName(i)), mMountTable);
    }
    mCache.cancelAllPrefetch();
    assertNull(mCache.fetchChildrenIfAbsent(new AlluxioURI("/" + Character.getName(89)),
        mMountTable, false));
  }

  @Test
  public void testUseFallback() throws Exception {
    createUfsDirs("a");
    createUfsFile("a/b");
    Collection<UfsStatus> children = mCache
        .fetchChildrenIfAbsent(new AlluxioURI("/a"), mMountTable);
    assertEquals(1, children.size());
    children.forEach(stat -> assertEquals("b", stat.getName()));
  }

  @Test
  public void testFetchInterruptedException() throws Exception {
    mUfs = Mockito.spy(mUfs);
    MountInfo rootMountInfo = new MountInfo(
        new AlluxioURI(MountTable.ROOT),
        new AlluxioURI(mUfsUri),
        IdUtils.ROOT_MOUNT_ID,
        MountPOptions.newBuilder()
            .setReadOnly(false)
            .setShared(false)
            .build());
    MasterUfsManager manager = new MasterUfsManager();
    manager.getRoot(); // add root mount
    manager.mUnderFileSystemMap.put(new AbstractUfsManager.Key(new AlluxioURI("/"), null), mUfs);
    doAnswer((Answer<UfsStatus[]>) invocation -> {
      Thread.sleep(30 * Constants.HOUR_MS);
      return new UfsStatus[] {Mockito.mock(UfsStatus.class)};
    }).when(mUfs).listStatus(any(String.class));
    mMountTable = new MountTable(manager, rootMountInfo);
    mCache.prefetchChildren(new AlluxioURI("/"), mMountTable);
    AtomicReference<Error> ref = new AtomicReference<>(null);
    Thread t = new Thread(() -> {
      try {
        try {
          mCache.fetchChildrenIfAbsent(new AlluxioURI("/"), mMountTable);
          fail("Should not have been able to fetch children");
        } catch (InterruptedException | InvalidPathException e) {
          // Assert interrupted flag was set properly.
          assertTrue(Thread.currentThread().isInterrupted());
          assertTrue(e instanceof InterruptedException);
        }
      } catch (AssertionError err) {
        ref.set(err);
      }
    });
    t.start();
    t.interrupt();
    t.join();
    assertNull(ref.get());
  }

  @Test
  public void testFetchExecutionException() throws Exception {
    mUfs = Mockito.spy(mUfs);
    MountInfo rootMountInfo = new MountInfo(
        new AlluxioURI(MountTable.ROOT),
        new AlluxioURI(mUfsUri),
        IdUtils.ROOT_MOUNT_ID,
        MountPOptions.newBuilder()
            .setReadOnly(false)
            .setShared(false)
            .build());
    MasterUfsManager manager = new MasterUfsManager();
    manager.getRoot(); // add root mount
    manager.mUnderFileSystemMap.put(new AbstractUfsManager.Key(new AlluxioURI("/"), null), mUfs);
    mMountTable = new MountTable(manager, rootMountInfo);
    // Now test execution exception
    AtomicReference<Error> ref = new AtomicReference<>(null);

    doThrow(new RuntimeException("Purposefully thrown exception."))
        .when(mUfs).listStatus(any(String.class));
    mCache.prefetchChildren(new AlluxioURI("/"), mMountTable);
    Thread t = new Thread(() -> {
      try {
        try {
          assertNull("Should return null when fetching children",
              mCache.fetchChildrenIfAbsent(new AlluxioURI("/"), mMountTable, false));
        } catch (InterruptedException | InvalidPathException e) {
          // Thread should not be interrupted if interrupt not called
          assertFalse(Thread.currentThread().isInterrupted());
          assertTrue(e instanceof InterruptedException);
        }
      } catch (AssertionError err) {
        ref.set(err);
      }
    });
    t.start();
    t.join();
    assertNull(ref.get());
  }

  @Test
  public void testAddRemoveChildren() throws Exception {
    AlluxioURI path = new AlluxioURI("/abc");
    AlluxioURI pathChild = new AlluxioURI("/abc/123");

    UfsStatus stat = Mockito.mock(UfsStatus.class);
    when(stat.getName()).thenReturn("abc");

    UfsStatus statChild = Mockito.mock(UfsStatus.class);
    when(statChild.getName()).thenReturn("123");

    mCache.addStatus(path, stat);
    mCache.addChildren(path, Collections.singleton(statChild));
    assertEquals(stat, mCache.getStatus(path));
    assertEquals(statChild, mCache.getStatus(pathChild));
    assertEquals(Collections.singleton(statChild), mCache.getChildren(path));
    assertEquals(Collections.singleton(statChild), mCache.fetchChildrenIfAbsent(path, mMountTable));

    mCache.remove(path);
    assertNull(mCache.getStatus(path));
    assertNull(mCache.getChildren(path));
    assertNotNull(mCache.getStatus(pathChild));
    assertEquals(statChild, mCache.getStatus(pathChild));
  }

  @Test
  public void conflictingParentStatus() throws Exception {
    AlluxioURI path = new AlluxioURI("/mnt/dir1/dir0/dir2");
    AlluxioURI path2 = new AlluxioURI("/mnt/dir2/dir0/dir3");
    createUfsDirs(path.getPath());
    createUfsDirs(path2.getPath());
    // Both parent dirs have the same name - previously caused issues with the way child references
    // were stored
    mCache.addStatus(new AlluxioURI("/mnt/dir1/dir0"),
        mUfs.getStatus(PathUtils.concatPath(mUfsUri, "/mnt/dir1/dir0")).setName("dir0"));
    mCache.addStatus(new AlluxioURI("/mnt/dir2/dir0"),
        mUfs.getStatus(PathUtils.concatPath(mUfsUri, "/mnt/dir2/dir0")).setName("dir0"));
    mCache.prefetchChildren(new AlluxioURI("/mnt/dir1/dir0"), mMountTable);
    mCache.prefetchChildren(new AlluxioURI("/mnt/dir2/dir0"), mMountTable);
    Collection<UfsStatus> children;
    children = mCache.fetchChildrenIfAbsent(new AlluxioURI("/mnt/dir1/dir0"), mMountTable, false);
    assertNotNull(children);
    assertEquals(1, children.size());
    children.forEach(s -> assertEquals("dir2", s.getName()));
    children = mCache.fetchChildrenIfAbsent(new AlluxioURI("/mnt/dir2/dir0"), mMountTable, false);
    assertNotNull(children);
    assertEquals(1, children.size());
    children.forEach(s -> assertEquals("dir3", s.getName()));
    children = mCache.fetchChildrenIfAbsent(new AlluxioURI("/mnt/dir1/dir0"), mMountTable, false);
    assertNotNull(children);
    assertEquals(1, children.size());
    children.forEach(s -> assertEquals("dir2", s.getName()));
  }

  @Test
  public void testPrefetch() throws Exception {
    createUfsDirs("dir0/dir0");
    createUfsFile("dir0/dir0/file");
    mCache.prefetchChildren(new AlluxioURI("/dir0/dir0"), mMountTable);
    mCache.prefetchChildren(new AlluxioURI("/dir0"), mMountTable);
    Collection<UfsStatus> statuses =
        mCache.fetchChildrenIfAbsent(new AlluxioURI("/dir0/dir0"), mMountTable, false);
    assertEquals(1, statuses.size());
    statuses.forEach(s -> assertEquals("file", s.getName()));
    statuses = mCache.fetchChildrenIfAbsent(new AlluxioURI("/dir0"), mMountTable, false);
    assertEquals(1, statuses.size());
    statuses.forEach(s -> assertEquals("dir0", s.getName()));
  }

  public void createUfsFile(String relPath) throws Exception {
    mUfs.create(PathUtils.concatPath(mUfsUri, relPath)).close();
  }

  public void createUfsDirs(String relPath) throws Exception {
    mUfs.mkdirs(PathUtils.concatPath(mUfsUri, relPath));
  }
}
