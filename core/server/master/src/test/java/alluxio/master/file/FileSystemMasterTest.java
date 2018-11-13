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

package alluxio.master.file;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import alluxio.AlluxioTestDirectory;
import alluxio.AlluxioURI;
import alluxio.AuthenticatedClientUserResource;
import alluxio.AuthenticatedUserRule;
import alluxio.Configuration;
import alluxio.ConfigurationRule;
import alluxio.Constants;
import alluxio.LoginUserRule;
import alluxio.PropertyKey;
import alluxio.exception.AccessControlException;
import alluxio.exception.BlockInfoException;
import alluxio.exception.DirectoryNotEmptyException;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.FileAlreadyExistsException;
import alluxio.exception.FileDoesNotExistException;
import alluxio.exception.InvalidPathException;
import alluxio.exception.UnexpectedAlluxioException;
import alluxio.grpc.*;
import alluxio.heartbeat.HeartbeatContext;
import alluxio.heartbeat.HeartbeatScheduler;
import alluxio.heartbeat.ManuallyScheduleHeartbeat;
import alluxio.master.MasterContext;
import alluxio.master.MasterRegistry;
import alluxio.master.MasterTestUtils;
import alluxio.master.SafeModeManager;
import alluxio.master.block.BlockMaster;
import alluxio.master.block.BlockMasterFactory;
import alluxio.master.file.meta.PersistenceState;
import alluxio.master.file.meta.TtlIntervalRule;
import alluxio.master.file.options.CompleteFileOptions;
import alluxio.master.file.options.CreateDirectoryOptions;
import alluxio.master.file.options.CreateFileOptions;
import alluxio.master.file.options.SetAttributeOptions;
import alluxio.master.file.options.WorkerHeartbeatOptions;
import alluxio.master.journal.JournalSystem;
import alluxio.master.journal.JournalTestUtils;
import alluxio.master.metrics.MetricsMaster;
import alluxio.master.metrics.MetricsMasterFactory;
import alluxio.metrics.Metric;
import alluxio.security.GroupMappingServiceTestUtils;
import alluxio.security.authorization.AclEntry;
import alluxio.thrift.Command;
import alluxio.thrift.CommandType;
import alluxio.thrift.RegisterWorkerTOptions;
import alluxio.util.IdUtils;
import alluxio.util.ThreadFactoryUtils;
import alluxio.util.executor.ExecutorServiceFactories;
import alluxio.util.io.FileUtils;
import alluxio.wire.FileBlockInfo;
import alluxio.wire.FileInfo;
import alluxio.wire.FileSystemCommand;
import alluxio.wire.SetAclAction;
import alluxio.wire.TtlAction;
import alluxio.wire.UfsInfo;
import alluxio.wire.WorkerNetAddress;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Unit tests for {@link FileSystemMaster}.
 */
public final class FileSystemMasterTest {
  private static final Logger LOG = LoggerFactory.getLogger(FileSystemMasterTest.class);

  private static final AlluxioURI NESTED_URI = new AlluxioURI("/nested/test");
  private static final AlluxioURI NESTED_FILE_URI = new AlluxioURI("/nested/test/file");
  private static final AlluxioURI NESTED_FILE2_URI = new AlluxioURI("/nested/test/file2");
  private static final AlluxioURI NESTED_DIR_URI = new AlluxioURI("/nested/test/dir");
  private static final AlluxioURI ROOT_URI = new AlluxioURI("/");
  private static final AlluxioURI ROOT_FILE_URI = new AlluxioURI("/file");
  private static final AlluxioURI TEST_URI = new AlluxioURI("/test");
  private static final String TEST_USER = "test";
  private static final FileSystemMasterOptions MASTER_OPTIONS = new DefaultFileSystemMasterOptions();
  private static final GetStatusPOptions GET_STATUS_OPTIONS = MASTER_OPTIONS.getGetStatusOptions();

  // Constants for tests on persisted directories.
  private static final String DIR_PREFIX = "dir";
  private static final String DIR_TOP_LEVEL = "top";
  private static final String FILE_PREFIX = "file";
  private static final String MOUNT_PARENT_URI = "/mnt";
  private static final String MOUNT_URI = "/mnt/local";
  private static final int DIR_WIDTH = 2;

  private CreateFileOptions mNestedFileOptions;
  private MasterRegistry mRegistry;
  private JournalSystem mJournalSystem;
  private BlockMaster mBlockMaster;
  private ExecutorService mExecutorService;
  private FileSystemMaster mFileSystemMaster;
  private SafeModeManager mSafeModeManager;
  private long mStartTimeMs;
  private int mPort;
  private MetricsMaster mMetricsMaster;
  private List<Metric> mMetrics;
  private long mWorkerId1;
  private long mWorkerId2;

  private String mJournalFolder;
  private String mUnderFS;

  @Rule
  public TemporaryFolder mTestFolder = new TemporaryFolder();

  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  @Rule
  public AuthenticatedUserRule mAuthenticatedUser = new AuthenticatedUserRule(TEST_USER);

  @Rule
  public LoginUserRule mLoginUser = new LoginUserRule(TEST_USER);

  @Rule
  public ConfigurationRule mConfigurationRule = new ConfigurationRule(new HashMap() {
    {
      put(PropertyKey.SECURITY_AUTHORIZATION_PERMISSION_UMASK, "000");
      put(PropertyKey.MASTER_JOURNAL_TAILER_SLEEP_TIME_MS, "20");
      put(PropertyKey.MASTER_JOURNAL_TAILER_SHUTDOWN_QUIET_WAIT_TIME_MS, "0");
      put(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS, AlluxioTestDirectory
          .createTemporaryDirectory("FileSystemMasterTest").getAbsolutePath());
    }
  });

  @ClassRule
  public static ManuallyScheduleHeartbeat sManuallySchedule = new ManuallyScheduleHeartbeat(
      HeartbeatContext.MASTER_TTL_CHECK, HeartbeatContext.MASTER_LOST_FILES_DETECTION);

  // Set ttl interval to 0 so that there is no delay in detecting expired files.
  @ClassRule
  public static TtlIntervalRule sTtlIntervalRule = new TtlIntervalRule(0);

  /**
   * Sets up the dependencies before a test runs.
   */
  @Before
  public void before() throws Exception {
    GroupMappingServiceTestUtils.resetCache();
    // This makes sure that the mount point of the UFS corresponding to the Alluxio root ("/")
    // doesn't exist by default (helps loadRootTest).
    mUnderFS = Configuration.get(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS);
    mNestedFileOptions =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true);
    mJournalFolder = mTestFolder.newFolder().getAbsolutePath();
    startServices();
  }

  /**
   * Resets global state after each test run.
   */
  @After
  public void after() throws Exception {
    stopServices();
  }

  @Test
  public void createPathWithWhiteSpaces() throws Exception {
    String[] paths = new String[]{
        "/ ",
        "/  ",
        "/ path",
        "/path ",
        "/pa th",
        "/ pa th ",
        "/pa/ th",
        "/pa / th",
        "/ pa / th ",
    };
    for (String path : paths) {
      AlluxioURI uri = new AlluxioURI(path);
      long id = mFileSystemMaster.createFile(uri,
          CreateFileOptions.defaults().setRecursive(true));
      Assert.assertEquals(id, mFileSystemMaster.getFileId(uri));
      mFileSystemMaster.delete(uri, MASTER_OPTIONS.getDeleteOptions());
      id = mFileSystemMaster.createDirectory(uri,
          CreateDirectoryOptions.defaults().setRecursive(true));
      Assert.assertEquals(id, mFileSystemMaster.getFileId(uri));
    }
  }

  @Test
  public void createFileMustCacheThenCacheThrough() throws Exception {
    File file = mTestFolder.newFile();
    AlluxioURI path = new AlluxioURI("/test");
    mFileSystemMaster.createFile(path, CreateFileOptions.defaults().setPersisted(false));

    mThrown.expect(FileAlreadyExistsException.class);
    mFileSystemMaster.createFile(path, CreateFileOptions.defaults().setPersisted(true));
  }

  @Test
  public void createFileUsesOperationTime() throws Exception {
    AlluxioURI path = new AlluxioURI("/test");
    mFileSystemMaster.createFile(path, CreateFileOptions.defaults().setOperationTimeMs(100));
    assertEquals(100, mFileSystemMaster.getFileInfo(path,
        MASTER_OPTIONS.getGetStatusOptions())
        .getLastModificationTimeMs());
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method.
   */
  @Test
  public void deleteFile() throws Exception {
    // cannot delete root
    try {
      mFileSystemMaster.delete(ROOT_URI,
          MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).build());
      fail("Should not have been able to delete the root");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.DELETE_ROOT_DIRECTORY.getMessage(), e.getMessage());
    }

    // delete the file
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.delete(NESTED_FILE_URI, MASTER_OPTIONS.getDeleteOptions());

    try {
      mBlockMaster.getBlockInfo(blockId);
      fail("Expected blockInfo to fail");
    } catch (BlockInfoException e) {
      // expected
    }

    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat1 = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat1);
    assertFalse(mBlockMaster.getLostBlocks().contains(blockId));

    // verify the file is deleted
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));

    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());
    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file1").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    AlluxioURI uri = new AlluxioURI("/mnt/local/dir1");
    mFileSystemMaster.listStatus(uri,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).build());
    mFileSystemMaster.delete(new AlluxioURI("/mnt/local/dir1/file1"),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setAlluxioOnly(true).build());

    // ufs file still exists
    assertTrue(Files.exists(Paths.get(ufsMount.join("dir1").join("file1").getPath())));
    // verify the file is deleted
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local/dir1/file1"),
        MASTER_OPTIONS.getGetStatusOptions().toBuilder().
            setLoadMetadataType(LoadMetadataPType.NEVER).build());
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method with a
   * non-empty directory.
   */
  @Test
  public void deleteNonemptyDirectory() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    String dirName = mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getName();
    try {
      mFileSystemMaster.delete(NESTED_URI, MASTER_OPTIONS.getDeleteOptions());
      fail("Deleting a non-empty directory without setting recursive should fail");
    } catch (DirectoryNotEmptyException e) {
      String expectedMessage =
          ExceptionMessage.DELETE_NONEMPTY_DIRECTORY_NONRECURSIVE.getMessage(dirName);
      assertEquals(expectedMessage, e.getMessage());
    }

    // Now delete with recursive set to true.
    mFileSystemMaster.delete(NESTED_URI,
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).build());
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a directory.
   */
  @Test
  public void deleteDir() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    // delete the dir
    mFileSystemMaster.delete(NESTED_URI,
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).build());

    // verify the dir is deleted
    assertEquals(-1, mFileSystemMaster.getFileId(NESTED_URI));

    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());
    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());
    // load the dir1 to alluxio
    mFileSystemMaster.listStatus(new AlluxioURI("/mnt/local"),
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).build());
    mFileSystemMaster.delete(new AlluxioURI("/mnt/local/dir1"), MASTER_OPTIONS.getDeleteOptions()
        .toBuilder().setRecursive(true).setAlluxioOnly(true).build());
    // ufs directory still exists
    assertTrue(Files.exists(Paths.get(ufsMount.join("dir1").getPath())));
    // verify the directory is deleted
    Files.delete(Paths.get(ufsMount.join("dir1").getPath()));
    assertEquals(IdUtils.INVALID_FILE_ID,
        mFileSystemMaster.getFileId(new AlluxioURI("/mnt/local/dir1")));
  }

  @Test
  public void deleteDirRecursiveWithPermissions() throws Exception {
    // userA has permissions to delete directory and nested file
    createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeOptions.defaults().setMode((short) 0777));
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setMode((short) 0777));
    try (AuthenticatedClientUserResource userA = new AuthenticatedClientUserResource("userA")) {
      mFileSystemMaster.delete(NESTED_URI,
          MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).build());
    }
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_URI));
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));
  }

  @Test
  public void deleteDirRecursiveWithInsufficientPermissions() throws Exception {
    // userA has permissions to delete directory but not one of the nested files
    createFileWithSingleBlock(NESTED_FILE_URI);
    createFileWithSingleBlock(NESTED_FILE2_URI);
    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeOptions.defaults().setMode((short) 0777));
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setMode((short) 0700));
    mFileSystemMaster.setAttribute(NESTED_FILE2_URI,
        SetAttributeOptions.defaults().setMode((short) 0777));
    try (AuthenticatedClientUserResource userA = new AuthenticatedClientUserResource("userA")) {
      mFileSystemMaster.delete(NESTED_URI,
          MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).build());
      fail("Deleting a directory w/ insufficient permission on child should fail");
    } catch (AccessControlException e) {
      String expectedChildMessage = ExceptionMessage.PERMISSION_DENIED
          .getMessage("user=userA, access=-w-, path=" + NESTED_FILE_URI + ": failed at file");
      assertTrue(e.getMessage().startsWith(ExceptionMessage.DELETE_FAILED_DIR_CHILDREN
          .getMessage(NESTED_URI, expectedChildMessage)));
    }
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_URI));
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE2_URI));
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a directory with persistent entries with a sync check.
   */
  @Test
  public void deleteSyncedPersistedDirectoryWithCheck() throws Exception {
    deleteSyncedPersistedDirectory(1, false);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a directory with persistent entries without a sync check.
   */
  @Test
  public void deleteSyncedPersistedDirectoryWithoutCheck() throws Exception {
    deleteSyncedPersistedDirectory(1, true);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a multi-level directory with persistent entries with a sync check.
   */
  @Test
  public void deleteSyncedPersistedMultilevelDirectoryWithCheck() throws Exception {
    deleteSyncedPersistedDirectory(3, false);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a multi-level directory with persistent entries without a sync check.
   */
  @Test
  public void deleteSyncedPersistedMultilevelDirectoryWithoutCheck() throws Exception {
    deleteSyncedPersistedDirectory(3, true);
  }

  // Helper method for deleteSynctedPersisted* tests.
  private void deleteSyncedPersistedDirectory(int levels, boolean unchecked) throws Exception {
    AlluxioURI ufsMount = createPersistedDirectories(levels);
    mountPersistedDirectories(ufsMount);
    loadPersistedDirectories(levels);
    // delete top-level directory
    mFileSystemMaster.delete(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).setAlluxioOnly(false)
            .setUnchecked(unchecked).build());
    checkPersistedDirectoriesDeleted(levels, ufsMount, Collections.EMPTY_LIST);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a directory with un-synced persistent entries with a sync check.
   */
  @Test
  public void deleteUnsyncedPersistedDirectoryWithCheck() throws Exception {
    AlluxioURI ufsMount = createPersistedDirectories(1);
    mountPersistedDirectories(ufsMount);
    loadPersistedDirectories(1);
    // Add a file to the UFS.
    Files.createFile(
        Paths.get(ufsMount.join(DIR_TOP_LEVEL).join(FILE_PREFIX + (DIR_WIDTH)).getPath()));
    mThrown.expect(IOException.class);
    // delete top-level directory
    mFileSystemMaster.delete(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).setAlluxioOnly(false)
            .setUnchecked(false).build());
    // Check all that could be deleted.
    List<AlluxioURI> except = new ArrayList<>();
    except.add(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL));
    checkPersistedDirectoriesDeleted(1, ufsMount, except);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a directory with un-synced persistent entries without a sync check.
   */
  @Test
  public void deleteUnsyncedPersistedDirectoryWithoutCheck() throws Exception {
    AlluxioURI ufsMount = createPersistedDirectories(1);
    mountPersistedDirectories(ufsMount);
    loadPersistedDirectories(1);
    // Add a file to the UFS.
    Files.createFile(
        Paths.get(ufsMount.join(DIR_TOP_LEVEL).join(FILE_PREFIX + (DIR_WIDTH)).getPath()));
    // delete top-level directory
    mFileSystemMaster.delete(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).setAlluxioOnly(false)
            .setUnchecked(true).build());
    checkPersistedDirectoriesDeleted(1, ufsMount, Collections.EMPTY_LIST);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a multi-level directory with un-synced persistent entries with a sync check.
   */
  @Test
  public void deleteUnsyncedPersistedMultilevelDirectoryWithCheck() throws Exception {
    AlluxioURI ufsMount = createPersistedDirectories(3);
    mountPersistedDirectories(ufsMount);
    loadPersistedDirectories(3);
    // Add a file to the UFS down the tree.
    Files.createFile(Paths.get(ufsMount.join(DIR_TOP_LEVEL).join(DIR_PREFIX + 0)
        .join(FILE_PREFIX + (DIR_WIDTH)).getPath()));
    mThrown.expect(IOException.class);
    // delete top-level directory
    mFileSystemMaster.delete(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).setAlluxioOnly(false)
            .setUnchecked(false).build());
    // Check all that could be deleted.
    List<AlluxioURI> except = new ArrayList<>();
    except.add(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL));
    except.add(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL).join(DIR_PREFIX + 0));
    checkPersistedDirectoriesDeleted(3, ufsMount, except);
  }

  /**
   * Tests the {@link FileSystemMaster#delete(AlluxioURI, DeletePOptions)} method for
   * a multi-level directory with un-synced persistent entries without a sync check.
   */
  @Test
  public void deleteUnsyncedPersistedMultilevelDirectoryWithoutCheck() throws Exception {
    AlluxioURI ufsMount = createPersistedDirectories(3);
    mountPersistedDirectories(ufsMount);
    loadPersistedDirectories(3);
    // Add a file to the UFS down the tree.
    Files.createFile(Paths.get(ufsMount.join(DIR_TOP_LEVEL).join(DIR_PREFIX + 0)
        .join(FILE_PREFIX + (DIR_WIDTH)).getPath()));
    // delete top-level directory
    mFileSystemMaster.delete(new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        MASTER_OPTIONS.getDeleteOptions().toBuilder().setRecursive(true).setAlluxioOnly(false)
            .setUnchecked(true).build());
    checkPersistedDirectoriesDeleted(3, ufsMount, Collections.EMPTY_LIST);
  }

  // Helper method to check if expected entries were deleted.
  private void checkPersistedDirectoriesDeleted(int levels, AlluxioURI ufsMount,
      List<AlluxioURI> except) throws Exception {
    checkPersistedDirectoryDeletedLevel(levels, new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL),
        ufsMount.join(DIR_TOP_LEVEL), except);
  }

  private void checkPersistedDirectoryDeletedLevel(int levels, AlluxioURI alluxioURI,
      AlluxioURI ufsURI, List<AlluxioURI> except) throws Exception {
    if (levels <= 0) {
      return;
    }
    if (except.contains(alluxioURI)) {
      assertTrue(Files.exists(Paths.get(ufsURI.getPath())));
      assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(alluxioURI));
    } else {
      assertFalse(Files.exists(Paths.get(ufsURI.getPath())));
      assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(alluxioURI));
    }
    for (int i = 0; i < DIR_WIDTH; ++i) {
      // Files can always be deleted.
      assertFalse(Files.exists(Paths.get(ufsURI.join(FILE_PREFIX + i).getPath())));
      assertEquals(IdUtils.INVALID_FILE_ID,
          mFileSystemMaster.getFileId(alluxioURI.join(FILE_PREFIX + i)));

      checkPersistedDirectoryDeletedLevel(levels - 1, alluxioURI.join(DIR_PREFIX + i),
          ufsURI.join(DIR_PREFIX + i), except);
    }
  }

  // Helper method to construct a directory tree in the UFS.
  private AlluxioURI createPersistedDirectories(int levels) throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    // Create top-level directory in UFS.
    Files.createDirectory(Paths.get(ufsMount.join(DIR_TOP_LEVEL).getPath()));
    createPersistedDirectoryLevel(levels, ufsMount.join(DIR_TOP_LEVEL));
    return ufsMount;
  }

  private void createPersistedDirectoryLevel(int levels, AlluxioURI ufsTop) throws Exception {
    if (levels <= 0) {
      return;
    }
    // Create files and nested directories in UFS.
    for (int i = 0; i < DIR_WIDTH; ++i) {
      Files.createFile(Paths.get(ufsTop.join(FILE_PREFIX + i).getPath()));
      Files.createDirectories(Paths.get(ufsTop.join(DIR_PREFIX + i).getPath()));
      createPersistedDirectoryLevel(levels - 1, ufsTop.join(DIR_PREFIX + i));
    }
  }

  // Helper method to load a tree from the UFS.
  private void loadPersistedDirectories(int levels) throws Exception {
    // load persisted ufs entries to alluxio
    mFileSystemMaster.listStatus(new AlluxioURI(MOUNT_URI),
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).build());
    loadPersistedDirectoryLevel(levels, new AlluxioURI(MOUNT_URI).join(DIR_TOP_LEVEL));
  }

  private void loadPersistedDirectoryLevel(int levels, AlluxioURI alluxioTop) throws Exception {
    if (levels <= 0) {
      return;
    }

    mFileSystemMaster.listStatus(alluxioTop,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).build());

    for (int i = 0; i < DIR_WIDTH; ++i) {
      loadPersistedDirectoryLevel(levels - 1, alluxioTop.join(DIR_PREFIX + i));
    }
  }

  // Helper method to mount UFS tree.
  private void mountPersistedDirectories(AlluxioURI ufsMount) throws Exception {
    mFileSystemMaster.createDirectory(new AlluxioURI(MOUNT_PARENT_URI),
        CreateDirectoryOptions.defaults());
    mFileSystemMaster.mount(new AlluxioURI(MOUNT_URI), ufsMount, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests the {@link FileSystemMaster#getNewBlockIdForFile(AlluxioURI)} method.
   */
  @Test
  public void getNewBlockIdForFile() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);
    long blockId = mFileSystemMaster.getNewBlockIdForFile(NESTED_FILE_URI);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertEquals(Lists.newArrayList(blockId), fileInfo.getBlockIds());
  }

  @Test
  public void getPath() throws Exception {
    AlluxioURI rootUri = new AlluxioURI("/");
    long rootId = mFileSystemMaster.getFileId(rootUri);
    assertEquals(rootUri, mFileSystemMaster.getPath(rootId));

    // get non-existent id
    try {
      mFileSystemMaster.getPath(rootId + 1234);
      fail("getPath() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  /**
   * Tests the {@link FileSystemMaster#getPersistenceState(long)} method.
   */
  @Test
  public void getPersistenceState() throws Exception {
    AlluxioURI rootUri = new AlluxioURI("/");
    long rootId = mFileSystemMaster.getFileId(rootUri);
    assertEquals(PersistenceState.PERSISTED, mFileSystemMaster.getPersistenceState(rootId));

    // get non-existent id
    try {
      mFileSystemMaster.getPersistenceState(rootId + 1234);
      fail("getPath() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  /**
   * Tests the {@link FileSystemMaster#getFileId(AlluxioURI)} method.
   */
  @Test
  public void getFileId() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);

    // These URIs exist.
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(ROOT_URI));
    assertEquals(ROOT_URI, mFileSystemMaster.getPath(mFileSystemMaster.getFileId(ROOT_URI)));

    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_URI));
    assertEquals(NESTED_URI,
        mFileSystemMaster.getPath(mFileSystemMaster.getFileId(NESTED_URI)));

    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));
    assertEquals(NESTED_FILE_URI,
        mFileSystemMaster.getPath(mFileSystemMaster.getFileId(NESTED_FILE_URI)));

    // These URIs do not exist.
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(ROOT_FILE_URI));
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(TEST_URI));
    assertEquals(IdUtils.INVALID_FILE_ID,
        mFileSystemMaster.getFileId(NESTED_FILE_URI.join("DNE")));
  }

  /**
   * Tests the {@link FileSystemMaster#getFileInfo(AlluxioURI, GetStatusPOptions)} method.
   */
  @Test
  public void getFileInfo() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    long fileId;
    FileInfo info;

    fileId = mFileSystemMaster.getFileId(ROOT_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(ROOT_URI.getPath(), info.getPath());
    assertEquals(ROOT_URI.getPath(),
        mFileSystemMaster.getFileInfo(ROOT_URI, GET_STATUS_OPTIONS).getPath());

    fileId = mFileSystemMaster.getFileId(NESTED_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(NESTED_URI.getPath(), info.getPath());
    assertEquals(NESTED_URI.getPath(),
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getPath());

    fileId = mFileSystemMaster.getFileId(NESTED_FILE_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(NESTED_FILE_URI.getPath(), info.getPath());
    assertEquals(NESTED_FILE_URI.getPath(),
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getPath());

    // Test non-existent id.
    try {
      mFileSystemMaster.getFileInfo(fileId + 1234);
      fail("getFileInfo() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    // Test non-existent URIs.
    try {
      mFileSystemMaster.getFileInfo(ROOT_FILE_URI, GET_STATUS_OPTIONS);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
    try {
      mFileSystemMaster.getFileInfo(TEST_URI, GET_STATUS_OPTIONS);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
    try {
      mFileSystemMaster.getFileInfo(NESTED_URI.join("DNE"), GET_STATUS_OPTIONS);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  @Test
  public void getFileInfoWithLoadMetadata() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createFile(Paths.get(ufsMount.join("file").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // getFileInfo should load metadata automatically.
    AlluxioURI uri = new AlluxioURI("/mnt/local/file");
    assertEquals(uri.getPath(),
        mFileSystemMaster.getFileInfo(uri, GET_STATUS_OPTIONS).getPath());

    // getFileInfo should have loaded another file, so now 4 paths exist.
    assertEquals(4, mFileSystemMaster.getNumberOfPaths());
  }

  @Test
  public void getFileIdWithLoadMetadata() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createFile(Paths.get(ufsMount.join("file").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // getFileId should load metadata automatically.
    AlluxioURI uri = new AlluxioURI("/mnt/local/file");
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(uri));

    // getFileId should have loaded another file, so now 4 paths exist.
    assertEquals(4, mFileSystemMaster.getNumberOfPaths());
  }

  @Test
  public void listStatusWithLoadMetadataNever() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file2").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // getFileId should load metadata automatically.
    AlluxioURI uri = new AlluxioURI("/mnt/local/dir1");
    try {
      mFileSystemMaster.listStatus(uri, MASTER_OPTIONS.getListStatusOptions().toBuilder()
          .setLoadMetadataType(LoadMetadataPType.NEVER).build());
      fail("Exception expected");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    assertEquals(3, mFileSystemMaster.getNumberOfPaths());
  }

  @Test
  public void listStatusWithLoadMetadataOnce() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file2").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // getFileId should load metadata automatically.
    AlluxioURI uri = new AlluxioURI("/mnt/local/dir1");
    List<FileInfo> fileInfoList =
        mFileSystemMaster.listStatus(uri, MASTER_OPTIONS.getListStatusOptions());
    Set<String> paths = new HashSet<>();
    for (FileInfo fileInfo : fileInfoList) {
      paths.add(fileInfo.getPath());
    }
    assertEquals(2, paths.size());
    assertTrue(paths.contains("/mnt/local/dir1/file1"));
    assertTrue(paths.contains("/mnt/local/dir1/file2"));
    // listStatus should have loaded another 3 files (dir1, dir1/file1, dir1/file2), so now 6
    // paths exist.
    assertEquals(6, mFileSystemMaster.getNumberOfPaths());
  }

  @Test
  public void listStatusWithLoadMetadataAlways() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // getFileId should load metadata automatically.
    AlluxioURI uri = new AlluxioURI("/mnt/local/dir1");
    List<FileInfo> fileInfoList =
        mFileSystemMaster.listStatus(uri, MASTER_OPTIONS.getListStatusOptions());
    assertEquals(0, fileInfoList.size());
    // listStatus should have loaded another files (dir1), so now 4 paths exist.
    assertEquals(4, mFileSystemMaster.getNumberOfPaths());

    // Add two files.
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file2").getPath()));

    fileInfoList =
        mFileSystemMaster.listStatus(uri, MASTER_OPTIONS.getListStatusOptions());
    assertEquals(0, fileInfoList.size());
    // No file is loaded since dir1 has been loaded once.
    assertEquals(4, mFileSystemMaster.getNumberOfPaths());

    fileInfoList = mFileSystemMaster.listStatus(uri,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).build());
    Set<String> paths = new HashSet<>();
    for (FileInfo fileInfo : fileInfoList) {
      paths.add(fileInfo.getPath());
    }
    assertEquals(2, paths.size());
    assertTrue(paths.contains("/mnt/local/dir1/file1"));
    assertTrue(paths.contains("/mnt/local/dir1/file2"));
    // listStatus should have loaded another 2 files (dir1/file1, dir1/file2), so now 6
    // paths exist.
    assertEquals(6, mFileSystemMaster.getNumberOfPaths());
  }

  /**
   * Tests listing status on a non-persisted directory.
   */
  @Test
  public void listStatusWithLoadMetadataNonPersistedDir() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // 3 directories exist.
    assertEquals(3, mFileSystemMaster.getNumberOfPaths());

    // Create a drectory in alluxio which is not persisted.
    AlluxioURI folder = new AlluxioURI("/mnt/local/folder");
    mFileSystemMaster.createDirectory(folder, CreateDirectoryOptions.defaults());

    assertFalse(
        mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local/folder"), GET_STATUS_OPTIONS)
            .isPersisted());

    // Create files in ufs.
    Files.createDirectory(Paths.get(ufsMount.join("folder").getPath()));
    Files.createFile(Paths.get(ufsMount.join("folder").join("file1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("folder").join("file2").getPath()));

    // getStatus won't mark folder as persisted.
    assertFalse(
        mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local/folder"), GET_STATUS_OPTIONS)
            .isPersisted());

    List<FileInfo> fileInfoList =
        mFileSystemMaster.listStatus(folder, MASTER_OPTIONS.getListStatusOptions());
    assertEquals(2, fileInfoList.size());
    // listStatus should have loaded files (folder, folder/file1, folder/file2), so now 6 paths
    // exist.
    assertEquals(6, mFileSystemMaster.getNumberOfPaths());

    Set<String> paths = new HashSet<>();
    for (FileInfo f : fileInfoList) {
      paths.add(f.getPath());
    }
    assertEquals(2, paths.size());
    assertTrue(paths.contains("/mnt/local/folder/file1"));
    assertTrue(paths.contains("/mnt/local/folder/file2"));

    assertTrue(
        mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local/folder"), GET_STATUS_OPTIONS)
            .isPersisted());
  }

  @Test
  public void listStatus() throws Exception {
    final int files = 10;
    List<FileInfo> infos;
    List<String> filenames;

    // Test files in root directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(ROOT_URI.join("file" + String.format("%05d", i)));
    }
    infos = mFileSystemMaster.listStatus(ROOT_URI,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.NEVER).build());
    assertEquals(files, infos.size());
    // Copy out filenames to use List contains.
    filenames = new ArrayList<>();
    for (FileInfo info : infos) {
      filenames.add(info.getPath());
    }
    // Compare all filenames.
    for (int i = 0; i < files; i++) {
      assertTrue(
          filenames.contains(ROOT_URI.join("file" + String.format("%05d", i)).toString()));
    }

    // Test single file.
    createFileWithSingleBlock(ROOT_FILE_URI);
    infos = mFileSystemMaster.listStatus(ROOT_FILE_URI,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.NEVER).build());
    assertEquals(1, infos.size());
    assertEquals(ROOT_FILE_URI.getPath(), infos.get(0).getPath());

    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_URI.join("file" + String.format("%05d", i)));
    }
    infos = mFileSystemMaster.listStatus(NESTED_URI,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.NEVER).build());
    assertEquals(files, infos.size());
    // Copy out filenames to use List contains.
    filenames = new ArrayList<>();
    for (FileInfo info : infos) {
      filenames.add(info.getPath());
    }
    // Compare all filenames.
    for (int i = 0; i < files; i++) {
      assertTrue(
          filenames.contains(NESTED_URI.join("file" + String.format("%05d", i)).toString()));
    }

    // Test non-existent URIs.
    try {
      mFileSystemMaster.listStatus(NESTED_URI.join("DNE"),
          MASTER_OPTIONS.getListStatusOptions().toBuilder()
              .setLoadMetadataType(LoadMetadataPType.NEVER).build());
      fail("listStatus() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  @Test
  public void listStatusRecursive() throws Exception {
    final int files = 10;
    List<FileInfo> infos;
    List<String> filenames;

    // Test files in root directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(ROOT_URI.join("file" + String.format("%05d", i)));
    }
    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_URI.join("file" + String.format("%05d", i)));
    }

    // Test recursive listStatus
    infos = mFileSystemMaster.listStatus(ROOT_URI,
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS)
            .setRecursive(true).build());
    // 10 files in each directory, 2 levels of directories
    assertEquals(files + files + 2, infos.size());

    filenames = new ArrayList<>();
    for (FileInfo info : infos) {
      filenames.add(info.getPath());
    }
    for (int i = 0; i < files; i++) {
      assertTrue(
          filenames.contains(ROOT_URI.join("file" + String.format("%05d", i)).toString()));
    }
    for (int i = 0; i < files; i++) {
      assertTrue(
          filenames.contains(NESTED_URI.join("file" + String.format("%05d", i)).toString()));
    }
  }

  @Test
  public void listStatusRecursivePermissions() throws Exception {
    final int files = 10;
    List<FileInfo> infos;
    List<String> filenames;

    // Test files in root directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(ROOT_URI.join("file" + String.format("%05d", i)));
    }
    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_URI.join("file" + String.format("%05d", i)));
    }

    // Test with permissions
    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeOptions.defaults().setMode((short) 0400).setRecursive(true));
    try (Closeable r = new AuthenticatedUserRule("test_user1").toResource()) {
      // Test recursive listStatus
      infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
          .toBuilder().setLoadMetadataType(LoadMetadataPType.ALWAYS).setRecursive(true).build());

      // 10 files in each directory, 1 level of directories
      assertEquals(files + 1, infos.size());
    }
  }

  @Test
  public void listStatusRecursiveLoadMetadata() throws Exception {
    final int files = 10;
    List<FileInfo> infos;
    List<String> filenames;

    // Test files in root directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(ROOT_URI.join("file" + String.format("%05d", i)));
    }

    FileUtils.createFile(Paths.get(mUnderFS).resolve("ufsfile1").toString());
    // Test interaction between recursive and loadMetadata
    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ONCE).setRecursive(false).build());

    assertEquals(files + 1  , infos.size());

    FileUtils.createFile(Paths.get(mUnderFS).resolve("ufsfile2").toString());
    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ONCE).setRecursive(false).build());
    assertEquals(files + 1  , infos.size());
    long newFileId = 1;

    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ALWAYS).setRecursive(false).build());
    assertEquals(files + 2 , infos.size());

    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_URI.join("file" + String.format("%05d", i)));
    }

    FileUtils.createFile(Paths.get(mUnderFS).resolve("nested/test/ufsnestedfile1").toString());
    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ONCE).setRecursive(true).build());
    // 2 sets of files, 2 files inserted at root, 2 directories nested and test,
    // 1 file ufsnestedfile1
    assertEquals(files + files +  2 + 2 + 1 , infos.size());

    FileUtils.createFile(Paths.get(mUnderFS).resolve("nested/test/ufsnestedfile2").toString());
    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ONCE).setRecursive(true).build());
    assertEquals(files + files +  2 + 2 + 1 , infos.size());

    infos = mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
        .toBuilder().setLoadMetadataType(LoadMetadataPType.ALWAYS).setRecursive(true).build());
    assertEquals(files + files +  2 + 2 + 2 , infos.size());
  }

  @Test
  public void getFileBlockInfoList() throws Exception {
    createFileWithSingleBlock(ROOT_FILE_URI);
    createFileWithSingleBlock(NESTED_FILE_URI);

    List<FileBlockInfo> blockInfo;

    blockInfo = mFileSystemMaster.getFileBlockInfoList(ROOT_FILE_URI);
    assertEquals(1, blockInfo.size());

    blockInfo = mFileSystemMaster.getFileBlockInfoList(NESTED_FILE_URI);
    assertEquals(1, blockInfo.size());

    // Test directory URI.
    try {
      mFileSystemMaster.getFileBlockInfoList(NESTED_URI);
      fail("getFileBlockInfoList() for a directory URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    // Test non-existent URI.
    try {
      mFileSystemMaster.getFileBlockInfoList(TEST_URI);
      fail("getFileBlockInfoList() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  @Test
  public void mountUnmount() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Alluxio mount point should not exist before mounting.
    try {
      mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local"), GET_STATUS_OPTIONS);
      fail("getFileInfo() for a non-existent URI (before mounting) should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());
    // Alluxio mount point should exist after mounting.
    assertNotNull(
        mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local"), GET_STATUS_OPTIONS));

    mFileSystemMaster.unmount(new AlluxioURI("/mnt/local"));

    // Alluxio mount point should not exist after unmounting.
    try {
      mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local"), GET_STATUS_OPTIONS);
      fail("getFileInfo() for a non-existent URI (before mounting) should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  @Test
  public void loadMetadata() throws Exception {
    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryOptions.defaults());

    // Create ufs file.
    Files.createFile(Paths.get(ufsMount.join("file").getPath()));

    // Created nested file.
    Files.createDirectory(Paths.get(ufsMount.join("nested").getPath()));
    Files.createFile(Paths.get(ufsMount.join("nested").join("file").getPath()));

    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount, MASTER_OPTIONS.getMountOptions());

    // Test simple file.
    AlluxioURI uri = new AlluxioURI("/mnt/local/file");
    mFileSystemMaster.loadMetadata(uri,
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(false).build());
    assertNotNull(mFileSystemMaster.getFileInfo(uri, GET_STATUS_OPTIONS));

    // Test nested file.
    uri = new AlluxioURI("/mnt/local/nested/file");
    try {
      mFileSystemMaster.loadMetadata(uri,
          MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(false).build());
      fail("loadMetadata() without recursive, for a nested file should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    // Test the nested file with recursive flag.
    mFileSystemMaster.loadMetadata(uri,
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());
    assertNotNull(mFileSystemMaster.getFileInfo(uri, GET_STATUS_OPTIONS));
  }

  @Test
  public void setDefaultAcl() throws Exception {
    SetAclPOptions options = MASTER_OPTIONS.getSetAclOptions();
    createFileWithSingleBlock(NESTED_FILE_URI);
    Set<String> entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertEquals(0, entries.size());

    // replace
    Set<String> newEntries = Sets.newHashSet("default:user::rwx",
        "default:group::rwx", "default:other::r-x");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertEquals(newEntries, entries);

    // replace
    newEntries = Sets.newHashSet("default:user::rw-", "default:group::r--", "default:other::r--");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);
    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify existing
    newEntries = Sets.newHashSet("default:user::rwx", "default:group::rw-", "default:other::r-x");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify add
    Set<String> oldEntries = new HashSet<>(entries);
    newEntries = Sets.newHashSet("default:user:usera:---", "default:group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));
    assertTrue(entries.containsAll(newEntries));
    assertTrue(entries.contains("default:mask::rwx"));

    // modify existing and add
    newEntries = Sets.newHashSet("default:user:usera:---", "default:group:groupa:--x",
        "default:other::r-x");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertTrue(entries.containsAll(newEntries));

    // remove default
    mFileSystemMaster
        .setAcl(NESTED_URI, SetAclAction.REMOVE_DEFAULT, Collections.emptyList(), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertEquals(0, entries.size());

    // remove
    newEntries =
        Sets.newHashSet("default:user:usera:---", "default:user:userb:rwx",
            "default:group:groupa:--x", "default:group:groupb:-wx");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);
    oldEntries = new HashSet<>(entries);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));

    Set<String> deleteEntries = Sets.newHashSet("default:user:userb:rwx",
        "default:group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_URI, SetAclAction.REMOVE,
        deleteEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).convertDefaultAclToStringEntries());
    Set<String> remainingEntries = new HashSet<>(newEntries);
    assertTrue(remainingEntries.removeAll(deleteEntries));
    assertTrue(entries.containsAll(remainingEntries));

    final Set<String> finalEntries = entries;
    assertTrue(deleteEntries.stream().noneMatch(finalEntries::contains));
  }

  @Test
  public void setAcl() throws Exception {
    SetAclPOptions options = MASTER_OPTIONS.getSetAclOptions();
    createFileWithSingleBlock(NESTED_FILE_URI);

    Set<String> entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertEquals(3, entries.size());

    // replace
    Set<String> newEntries = Sets.newHashSet("user::rwx", "group::rwx", "other::rwx");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // replace
    newEntries = Sets.newHashSet("user::rw-", "group::r--", "other::r--");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify existing
    newEntries = Sets.newHashSet("user::rwx", "group::r--", "other::r-x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify add
    Set<String> oldEntries = new HashSet<>(entries);
    newEntries = Sets.newHashSet("user:usera:---", "group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));
    assertTrue(entries.containsAll(newEntries));
    // check if the mask got updated correctly
    assertTrue(entries.contains("mask::r-x"));

    // modify existing and add
    newEntries = Sets.newHashSet("user:usera:---", "group:groupa:--x", "other::r-x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertTrue(entries.containsAll(newEntries));

    // remove all
    mFileSystemMaster
        .setAcl(NESTED_FILE_URI, SetAclAction.REMOVE_ALL, Collections.emptyList(), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertEquals(3, entries.size());

    // remove
    newEntries =
        Sets.newHashSet("user:usera:---", "user:userb:rwx", "group:groupa:--x", "group:groupb:-wx");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);
    oldEntries = new HashSet<>(entries);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));

    Set<String> deleteEntries = Sets.newHashSet("user:userb:rwx", "group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REMOVE,
        deleteEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).convertAclToStringEntries());
    Set<String> remainingEntries = new HashSet<>(newEntries);
    assertTrue(remainingEntries.removeAll(deleteEntries));
    assertTrue(entries.containsAll(remainingEntries));

    final Set<String> finalEntries = entries;
    assertTrue(deleteEntries.stream().noneMatch(finalEntries::contains));
  }

  @Test
  public void setRecursiveAcl() throws Exception {
    final int files = 10;
    SetAclPOptions options =
        MASTER_OPTIONS.getSetAclOptions().toBuilder().setRecursive(true).build();

    // Test files in root directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(ROOT_URI.join("file" + String.format("%05d", i)));
    }
    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_URI.join("file" + String.format("%05d", i)));
    }

    // Test files in nested directory.
    for (int i = 0; i < files; i++) {
      createFileWithSingleBlock(NESTED_DIR_URI.join("file" + String.format("%05d", i)));
    }

    // replace
    Set<String> newEntries = Sets.newHashSet("user::rw-", "group::r-x", "other::-wx");
    mFileSystemMaster.setAcl(ROOT_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), options);

    List<FileInfo> infos =
        mFileSystemMaster.listStatus(ROOT_URI, MASTER_OPTIONS.getListStatusOptions()
            .toBuilder().setLoadMetadataType(LoadMetadataPType.ONCE).setRecursive(true).build());
    assertEquals(files * 3 + 3, infos.size());
    for (FileInfo info : infos) {
      assertEquals(newEntries, Sets.newHashSet(info.convertAclToStringEntries()));
    }
  }

  @Test
  public void removeExtendedAclMask() throws Exception {
    mFileSystemMaster.createDirectory(NESTED_URI,
        CreateDirectoryOptions.defaults().setRecursive(true));
    AclEntry newAcl = AclEntry.fromCliString("user:newuser:rwx");
    // Add an ACL
    addAcl(NESTED_URI, newAcl);
    assertThat(getInfo(NESTED_URI).getAcl().getEntries(), hasItem(newAcl));

    // Attempt to remove the ACL mask
    AclEntry maskEntry = AclEntry.fromCliString("mask::rwx");
    assertThat(getInfo(NESTED_URI).getAcl().getEntries(), hasItem(maskEntry));
    try {
      removeAcl(NESTED_URI, maskEntry);
      fail("Expected removing the mask from an extended ACL to fail");
    } catch (IOException e) {
      assertThat(e.getMessage(), containsString("mask"));
    }

    // Remove the extended ACL
    removeAcl(NESTED_URI, newAcl);
    // Now we can add and remove a mask
    addAcl(NESTED_URI, maskEntry);
    removeAcl(NESTED_URI, maskEntry);
  }

  @Test
  public void removeExtendedDefaultAclMask() throws Exception {
    mFileSystemMaster.createDirectory(NESTED_URI,
        CreateDirectoryOptions.defaults().setRecursive(true));
    AclEntry newAcl = AclEntry.fromCliString("default:user:newuser:rwx");
    // Add an ACL
    addAcl(NESTED_URI, newAcl);
    assertThat(getInfo(NESTED_URI).getDefaultAcl().getEntries(), hasItem(newAcl));

    // Attempt to remove the ACL mask
    AclEntry maskEntry = AclEntry.fromCliString("default:mask::rwx");
    assertThat(getInfo(NESTED_URI).getDefaultAcl().getEntries(), hasItem(maskEntry));
    try {
      removeAcl(NESTED_URI, maskEntry);
      fail("Expected removing the mask from an extended ACL to fail");
    } catch (IOException e) {
      assertThat(e.getMessage(), containsString("mask"));
    }

    // Remove the extended ACL
    removeAcl(NESTED_URI, newAcl);
    // Now we can add and remove a mask
    addAcl(NESTED_URI, maskEntry);
    removeAcl(NESTED_URI, maskEntry);
  }

  private void addAcl(AlluxioURI uri, AclEntry acl) throws Exception {
    mFileSystemMaster.setAcl(uri, SetAclAction.MODIFY, Arrays.asList(acl),
        MASTER_OPTIONS.getSetAclOptions());
  }

  private void removeAcl(AlluxioURI uri, AclEntry acl) throws Exception {
    mFileSystemMaster.setAcl(uri, SetAclAction.REMOVE, Arrays.asList(acl),
        MASTER_OPTIONS.getSetAclOptions());
  }

  private FileInfo getInfo(AlluxioURI uri) throws Exception {
    return mFileSystemMaster.getFileInfo(uri, MASTER_OPTIONS.getGetStatusOptions());
  }

  /**
   * Tests that an exception is in the
   * {@link FileSystemMaster#createFile(AlluxioURI, CreateFileOptions)} with a TTL set in the
   * {@link CreateFileOptions} after the TTL check was done once.
   */
  @Test
  public void ttlFileDelete() throws Exception {
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true).setTtl(0);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(fileInfo.getFileId(), fileId);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(fileId);
  }

  /**
   * Tests that TTL delete of a file is not forgotten across restarts.
   */
  @Test
  public void ttlFileDeleteReplay() throws Exception {
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true).setTtl(0);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);

    // Simulate restart.
    stopServices();
    startServices();

    FileInfo fileInfo = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(fileInfo.getFileId(), fileId);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(fileId);
  }

  /**
   * Tests that an exception is in the
   * {@link FileSystemMaster#createDirectory(AlluxioURI, CreateDirectoryOptions)} with a TTL
   * set in the {@link CreateDirectoryOptions} after the TTL check was done once.
   */
  @Test
  public void ttlDirectoryDelete() throws Exception {
    CreateDirectoryOptions directoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true).setTtl(0);
    long dirId = mFileSystemMaster.createDirectory(NESTED_DIR_URI, directoryOptions);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(dirId);
    assertEquals(fileInfo.getFileId(), dirId);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(dirId);
  }

  /**
   * Tests that TTL delete of a directory is not forgotten across restarts.
   */
  @Test
  public void ttlDirectoryDeleteReplay() throws Exception {
    CreateDirectoryOptions directoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true).setTtl(0);
    long dirId = mFileSystemMaster.createDirectory(NESTED_DIR_URI, directoryOptions);

    // Simulate restart.
    stopServices();
    startServices();

    FileInfo fileInfo = mFileSystemMaster.getFileInfo(dirId);
    assertEquals(fileInfo.getFileId(), dirId);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(dirId);
  }

  /**
   * Tests that file information is still present after it has been freed after the TTL has been set
   * to 0.
   */
  @Test
  public void ttlFileFree() throws Exception {
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());
    // Set ttl & operation.
    SetAttributeOptions options = SetAttributeOptions.defaults();
    options.setTtl(0);
    options.setTtlAction(TtlAction.FREE);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, options);
    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests that TTL free of a file is not forgotten across restarts.
   */
  @Test
  public void ttlFileFreeReplay() throws Exception {
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());
    // Set ttl & operation.
    SetAttributeOptions options = SetAttributeOptions.defaults();
    options.setTtl(0);
    options.setTtlAction(TtlAction.FREE);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, options);

    // Simulate restart.
    stopServices();
    startServices();

    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests that file information is still present after it has been freed after the parent
   * directory's TTL has been set to 0.
   */
  @Test
  public void ttlDirectoryFree() throws Exception {
    CreateDirectoryOptions createDirectoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true);
    mFileSystemMaster.createDirectory(NESTED_URI, createDirectoryOptions);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());
    // Set ttl & operation.
    SetAttributeOptions options = SetAttributeOptions.defaults();
    options.setTtl(0);
    options.setTtlAction(TtlAction.FREE);
    mFileSystemMaster.setAttribute(NESTED_URI, options);
    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests that TTL free of a directory is not forgotten across restarts.
   */
  @Test
  public void ttlDirectoryFreeReplay() throws Exception {
    CreateDirectoryOptions createDirectoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true);
    mFileSystemMaster.createDirectory(NESTED_URI, createDirectoryOptions);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());
    // Set ttl & operation.
    SetAttributeOptions options = SetAttributeOptions.defaults();
    options.setTtl(0);
    options.setTtlAction(TtlAction.FREE);
    mFileSystemMaster.setAttribute(NESTED_URI, options);

    // Simulate restart.
    stopServices();
    startServices();

    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests that an exception is thrown when trying to get information about a file after it has been
   * deleted because of a TTL of 0.
   */
  @Test
  public void setTtlForFileWithNoTtl() throws Exception {
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // Since no TTL is set, the file should not be deleted.
    assertEquals(fileId,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getFileId());

    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setTtl(0));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is set to 0, the file should have been deleted during last TTL check.
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(fileId);
  }

  /**
   * Tests that an exception is thrown when trying to get information about a Directory after
   * it has been deleted because of a TTL of 0.
   */
  @Test
  public void setTtlForDirectoryWithNoTtl() throws Exception {
    CreateDirectoryOptions createDirectoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true);
    mFileSystemMaster.createDirectory(NESTED_URI, createDirectoryOptions);
    mFileSystemMaster.createDirectory(NESTED_DIR_URI, createDirectoryOptions);
    CreateFileOptions createFileOptions =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, createFileOptions);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // Since no TTL is set, the file should not be deleted.
    assertEquals(fileId,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getFileId());
    // Set ttl.
    mFileSystemMaster.setAttribute(NESTED_URI, SetAttributeOptions.defaults().setTtl(0));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is set to 0, the file and directory should have been deleted during last TTL check.
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS);
    mFileSystemMaster.getFileInfo(NESTED_DIR_URI, GET_STATUS_OPTIONS);
    mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
  }

  /**
   * Tests that an exception is thrown when trying to get information about a file after it has been
   * deleted after the TTL has been set to 0.
   */
  @Test
  public void setSmallerTtlForFileWithTtl() throws Exception {
    CreateFileOptions options = CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB)
        .setRecursive(true).setTtl(Constants.HOUR_MS);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // Since TTL is 1 hour, the file won't be deleted during last TTL check.
    assertEquals(fileId,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getFileId());

    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setTtl(0));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is reset to 0, the file should have been deleted during last TTL check.
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(fileId);
  }

  /**
   * Tests that an exception is thrown when trying to get information about a Directory after
   * it has been deleted after the TTL has been set to 0.
   */
  @Test
  public void setSmallerTtlForDirectoryWithTtl() throws Exception {
    CreateDirectoryOptions createDirectoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true).setTtl(Constants.HOUR_MS);
    mFileSystemMaster.createDirectory(NESTED_URI, createDirectoryOptions);
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    assertTrue(
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getName() != null);
    mFileSystemMaster.setAttribute(NESTED_URI, SetAttributeOptions.defaults().setTtl(0));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is reset to 0, the file should have been deleted during last TTL check.
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS);
  }

  /**
   * Tests that a file has not been deleted after the TTL has been reset to a valid value.
   */
  @Test
  public void setLargerTtlForFileWithTtl() throws Exception {
    mFileSystemMaster.createDirectory(NESTED_URI,
        CreateDirectoryOptions.defaults().setRecursive(true));
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true).setTtl(0);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);
    assertEquals(fileId,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getFileId());

    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setTtl(Constants.HOUR_MS));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is reset to 1 hour, the file should not be deleted during last TTL check.
    assertEquals(fileId, mFileSystemMaster.getFileInfo(fileId).getFileId());
  }

  /**
   * Tests that a directory has not been deleted after the TTL has been reset to a valid value.
   */
  @Test
  public void setLargerTtlForDirectoryWithTtl() throws Exception {
    mFileSystemMaster.createDirectory(new AlluxioURI("/nested"),
        CreateDirectoryOptions.defaults().setRecursive(true));
    mFileSystemMaster.createDirectory(NESTED_URI,
        CreateDirectoryOptions.defaults().setRecursive(true).setTtl(0));
    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeOptions.defaults().setTtl(Constants.HOUR_MS));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    // TTL is reset to 1 hour, the directory should not be deleted during last TTL check.
    assertEquals(NESTED_URI.getName(),
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getName());
  }

  /**
   * Tests that the original TTL is removed after setting it to {@link Constants#NO_TTL} for a file.
   */
  @Test
  public void setNoTtlForFileWithTtl() throws Exception {
    mFileSystemMaster.createDirectory(NESTED_URI,
        CreateDirectoryOptions.defaults().setRecursive(true));
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true).setTtl(0);
    long fileId = mFileSystemMaster.createFile(NESTED_FILE_URI, options);
    // After setting TTL to NO_TTL, the original TTL will be removed, and the file will not be
    // deleted during next TTL check.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setTtl(Constants.NO_TTL));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    assertEquals(fileId, mFileSystemMaster.getFileInfo(fileId).getFileId());
  }

  /**
   * Tests that the original TTL is removed after setting it to {@link Constants#NO_TTL} for
   * a directory.
   */
  @Test
  public void setNoTtlForDirectoryWithTtl() throws Exception {
    mFileSystemMaster.createDirectory(new AlluxioURI("/nested"),
        CreateDirectoryOptions.defaults().setRecursive(true));
    CreateDirectoryOptions createDirectoryOptions =
        CreateDirectoryOptions.defaults().setRecursive(true).setTtl(0);
    mFileSystemMaster.createDirectory(NESTED_URI, createDirectoryOptions);
    // After setting TTL to NO_TTL, the original TTL will be removed, and the file will not be
    // deleted during next TTL check.
    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeOptions.defaults().setTtl(Constants.NO_TTL));
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_TTL_CHECK);
    assertEquals(NESTED_URI.getName(),
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getName());
  }

  /**
   * Tests the {@link FileSystemMaster#setAttribute(AlluxioURI, SetAttributeOptions)} method and
   * that an exception is thrown when trying to set a TTL for a directory.
   */
  @Test
  public void setAttribute() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // No State.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Just set pinned flag.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertTrue(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Both pinned flag and ttl value.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setPinned(false).setTtl(1));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(1, fileInfo.getTtl());

    mFileSystemMaster.setAttribute(NESTED_URI, SetAttributeOptions.defaults().setTtl(1));
  }

  /**
   * Tests the permission bits are 0777 for directories and 0666 for files with UMASK 000.
   */
  @Test
  public void permission() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);
    assertEquals(0777,
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_OPTIONS).getMode());
    assertEquals(0666,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS).getMode());
  }

  /**
   * Tests that a file is fully written to memory.
   */
  @Test
  public void isFullyInMemory() throws Exception {
    // add nested file
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);
    // add in-memory block
    long blockId = mFileSystemMaster.getNewBlockIdForFile(NESTED_FILE_URI);
    mBlockMaster.commitBlock(mWorkerId1, Constants.KB, "MEM", blockId, Constants.KB);
    // add SSD block
    blockId = mFileSystemMaster.getNewBlockIdForFile(NESTED_FILE_URI);
    mBlockMaster.commitBlock(mWorkerId1, Constants.KB, "SSD", blockId, Constants.KB);
    mFileSystemMaster.completeFile(NESTED_FILE_URI, CompleteFileOptions.defaults());

    // Create 2 files in memory.
    createFileWithSingleBlock(ROOT_FILE_URI);
    AlluxioURI nestedMemUri = NESTED_URI.join("mem_file");
    createFileWithSingleBlock(nestedMemUri);
    assertEquals(2, mFileSystemMaster.getInMemoryFiles().size());
    assertTrue(mFileSystemMaster.getInMemoryFiles().contains(ROOT_FILE_URI));
    assertTrue(mFileSystemMaster.getInMemoryFiles().contains(nestedMemUri));
  }

  /**
   * Tests the {@link FileSystemMaster#rename(AlluxioURI, AlluxioURI, RenamePOptions)} method.
   */
  @Test
  public void rename() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);

    // try to rename a file to root
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, ROOT_URI,
          mFileSystemMaster.getMasterOptions().getRenameOptions());
      fail("Renaming to root should fail.");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.RENAME_CANNOT_BE_TO_ROOT.getMessage(), e.getMessage());
    }

    // move root to another path
    try {
      mFileSystemMaster.rename(ROOT_URI, TEST_URI,
          mFileSystemMaster.getMasterOptions().getRenameOptions());
      fail("Should not be able to rename root");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.ROOT_CANNOT_BE_RENAMED.getMessage(), e.getMessage());
    }

    // move to existing path
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, NESTED_URI,
          mFileSystemMaster.getMasterOptions().getRenameOptions());
      fail("Should not be able to overwrite existing file.");
    } catch (FileAlreadyExistsException e) {
      assertEquals(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(NESTED_URI.getPath()),
          e.getMessage());
    }

    // move a nested file to a root file
    mFileSystemMaster.rename(NESTED_FILE_URI, TEST_URI,
        mFileSystemMaster.getMasterOptions().getRenameOptions());
    assertEquals(mFileSystemMaster.getFileInfo(TEST_URI, GET_STATUS_OPTIONS).getPath(),
        TEST_URI.getPath());

    // move a file where the dst is lexicographically earlier than the source
    AlluxioURI newDst = new AlluxioURI("/abc_test");
    mFileSystemMaster.rename(TEST_URI, newDst,
        mFileSystemMaster.getMasterOptions().getRenameOptions());
    assertEquals(mFileSystemMaster.getFileInfo(newDst, GET_STATUS_OPTIONS).getPath(),
        newDst.getPath());
  }

  /**
   * Tests that an exception is thrown when trying to create a file in a non-existing directory
   * without setting the {@code recursive} flag.
   */
  @Test
  public void renameUnderNonexistingDir() throws Exception {
    mThrown.expect(FileDoesNotExistException.class);
    mThrown.expectMessage(ExceptionMessage.PATH_DOES_NOT_EXIST.getMessage("/nested/test"));

    CreateFileOptions options = CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB);
    mFileSystemMaster.createFile(TEST_URI, options);

    // nested dir
    mFileSystemMaster.rename(TEST_URI, NESTED_FILE_URI,
        mFileSystemMaster.getMasterOptions().getRenameOptions());
  }

  @Test
  public void renameToNonExistentParent() throws Exception {
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true);
    mFileSystemMaster.createFile(NESTED_URI, options);

    try {
      mFileSystemMaster.rename(NESTED_URI, new AlluxioURI("/testDNE/b"),
          mFileSystemMaster.getMasterOptions().getRenameOptions());
      fail("Rename to a non-existent parent path should not succeed.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }

  /**
   * Tests that an exception is thrown when trying to rename a file to a prefix of the original
   * file.
   */
  @Test
  public void renameToSubpath() throws Exception {
    mThrown.expect(InvalidPathException.class);
    mThrown.expectMessage("Traversal failed. Component 2(test) is a file");

    mFileSystemMaster.createFile(NESTED_URI, mNestedFileOptions);
    mFileSystemMaster.rename(NESTED_URI, NESTED_FILE_URI,
        mFileSystemMaster.getMasterOptions().getRenameOptions());
  }

  /**
   * Tests {@link FileSystemMaster#free} on persisted file.
   */
  @Test
  public void free() throws Exception {
    mNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the file
    mFileSystemMaster.free(NESTED_FILE_URI,
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(false).setRecursive(false).build());
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat2 = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat2);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests {@link FileSystemMaster#free} on non-persisted file.
   */
  @Test
  public void freeNonPersistedFile() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    mThrown.expect(UnexpectedAlluxioException.class);
    mThrown.expectMessage(ExceptionMessage.CANNOT_FREE_NON_PERSISTED_FILE
        .getMessage(NESTED_FILE_URI.getPath()));
    // cannot free a non-persisted file
    mFileSystemMaster.free(NESTED_FILE_URI, MASTER_OPTIONS.getFreeOptions());
  }

  /**
   * Tests {@link FileSystemMaster#free} on pinned file when forced flag is false.
   */
  @Test
  public void freePinnedFileWithoutForce() throws Exception {
    mNestedFileOptions.setPersisted(true);
    createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));
    mThrown.expect(UnexpectedAlluxioException.class);
    mThrown.expectMessage(ExceptionMessage.CANNOT_FREE_PINNED_FILE
        .getMessage(NESTED_FILE_URI.getPath()));
    // cannot free a pinned file without "forced"
    mFileSystemMaster.free(NESTED_FILE_URI, MASTER_OPTIONS.getFreeOptions());
  }

  /**
   * Tests {@link FileSystemMaster#free} on pinned file when forced flag is true.
   */
  @Test
  public void freePinnedFileWithForce() throws Exception {
    mNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));

    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the file
    mFileSystemMaster.free(NESTED_FILE_URI,
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(true).build());
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests the {@link FileSystemMaster#free} method with a directory but recursive to false.
   */
  @Test
  public void freeDirNonRecursive() throws Exception {
    mNestedFileOptions.setPersisted(true);
    createFileWithSingleBlock(NESTED_FILE_URI);
    mThrown.expect(UnexpectedAlluxioException.class);
    mThrown.expectMessage(ExceptionMessage.CANNOT_FREE_NON_EMPTY_DIR.getMessage(NESTED_URI));
    // cannot free directory with recursive argument to false
    mFileSystemMaster.free(NESTED_FILE_URI.getParent(),
        MASTER_OPTIONS.getFreeOptions().toBuilder().setRecursive(false).build());
  }

  /**
   * Tests the {@link FileSystemMaster#free} method with a directory.
   */
  @Test
  public void freeDir() throws Exception {
    mNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the dir
    mFileSystemMaster.free(NESTED_FILE_URI.getParent(),
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(true).setRecursive(true).build());
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat3 = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat3);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests the {@link FileSystemMaster#free} method with a directory with a file non-persisted.
   */
  @Test
  public void freeDirWithNonPersistedFile() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    mThrown.expect(UnexpectedAlluxioException.class);
    mThrown.expectMessage(ExceptionMessage.CANNOT_FREE_NON_PERSISTED_FILE
        .getMessage(NESTED_FILE_URI.getPath()));
    // cannot free the parent dir of a non-persisted file
    mFileSystemMaster.free(NESTED_FILE_URI.getParent(),
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(false).setRecursive(true).build());
  }

  /**
   * Tests the {@link FileSystemMaster#free} method with a directory with a file pinned when
   * forced flag is false.
   */
  @Test
  public void freeDirWithPinnedFileAndNotForced() throws Exception {
    mNestedFileOptions.setPersisted(true);
    createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));
    mThrown.expect(UnexpectedAlluxioException.class);
    mThrown.expectMessage(ExceptionMessage.CANNOT_FREE_PINNED_FILE
        .getMessage(NESTED_FILE_URI.getPath()));
    // cannot free the parent dir of a pinned file without "forced"
    mFileSystemMaster.free(NESTED_FILE_URI.getParent(),
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(false).setRecursive(true).build());
  }

  /**
   * Tests the {@link FileSystemMaster#free} method with a directory with a file pinned when
   * forced flag is true.
   */
  @Test
  public void freeDirWithPinnedFileAndForced() throws Exception {
    mNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));
    // free the parent dir of a pinned file with "forced"
    mFileSystemMaster.free(NESTED_FILE_URI.getParent(),
        MASTER_OPTIONS.getFreeOptions().toBuilder().setForced(true).setRecursive(true).build());
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }

  /**
   * Tests the {@link FileSystemMaster#mount(AlluxioURI, AlluxioURI, MountPOptions)} method.
   */
  @Test
  public void mount() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests mounting an existing dir.
   */
  @Test
  public void mountExistingDir() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    mFileSystemMaster.createDirectory(alluxioURI, CreateDirectoryOptions.defaults());
    mThrown.expect(InvalidPathException.class);
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests mounting to an Alluxio path whose parent dir does not exist.
   */
  @Test
  public void mountNonExistingParentDir() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/non-existing/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests a readOnly mount for the create directory op.
   */
  @Test
  public void mountReadOnlyCreateDirectory() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI,
        MASTER_OPTIONS.getMountOptions().toBuilder().setReadOnly(true).build());

    mThrown.expect(AccessControlException.class);
    AlluxioURI path = new AlluxioURI("/hello/dir1");
    mFileSystemMaster.createDirectory(path, CreateDirectoryOptions.defaults());
  }

  /**
   * Tests a readOnly mount for the create file op.
   */
  @Test
  public void mountReadOnlyCreateFile() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI,
        MASTER_OPTIONS.getMountOptions().toBuilder().setReadOnly(true).build());

    mThrown.expect(AccessControlException.class);
    AlluxioURI path = new AlluxioURI("/hello/file1");
    mFileSystemMaster.createFile(path, CreateFileOptions.defaults());
  }

  /**
   * Tests a readOnly mount for the delete op.
   */
  @Test
  public void mountReadOnlyDeleteFile() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    createTempUfsFile("ufs/hello/file1");
    mFileSystemMaster.mount(alluxioURI, ufsURI,
        MASTER_OPTIONS.getMountOptions().toBuilder().setReadOnly(true).build());

    mThrown.expect(AccessControlException.class);
    AlluxioURI path = new AlluxioURI("/hello/file1");
    mFileSystemMaster.delete(path, MASTER_OPTIONS.getDeleteOptions());
  }

  /**
   * Tests a readOnly mount for the rename op.
   */
  @Test
  public void mountReadOnlyRenameFile() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    createTempUfsFile("ufs/hello/file1");
    mFileSystemMaster.mount(alluxioURI, ufsURI,
        MASTER_OPTIONS.getMountOptions().toBuilder().setReadOnly(true).build());

    mThrown.expect(AccessControlException.class);
    AlluxioURI src = new AlluxioURI("/hello/file1");
    AlluxioURI dst = new AlluxioURI("/hello/file2");
    mFileSystemMaster.rename(src, dst, mFileSystemMaster.getMasterOptions().getRenameOptions());
  }

  /**
   * Tests a readOnly mount for the set attribute op.
   */
  @Test
  public void mountReadOnlySetAttribute() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    createTempUfsFile("ufs/hello/file1");
    mFileSystemMaster.mount(alluxioURI, ufsURI,
        MASTER_OPTIONS.getMountOptions().toBuilder().setReadOnly(true).build());

    mThrown.expect(AccessControlException.class);
    AlluxioURI path = new AlluxioURI("/hello/file1");
    mFileSystemMaster.setAttribute(path, SetAttributeOptions.defaults().setOwner("owner"));
  }

  /**
   * Tests mounting a shadow Alluxio dir.
   */
  @Test
  public void mountShadowDir() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello/shadow");

    mFileSystemMaster.mount(alluxioURI, ufsURI.getParent(), MASTER_OPTIONS.getMountOptions());
    AlluxioURI shadowAlluxioURI = new AlluxioURI("/hello/shadow");
    AlluxioURI notShadowAlluxioURI = new AlluxioURI("/hello/notshadow");
    AlluxioURI shadowUfsURI = createTempUfsDir("ufs/hi");
    AlluxioURI notShadowUfsURI = createTempUfsDir("ufs/notshadowhi");
    mFileSystemMaster.mount(notShadowAlluxioURI, notShadowUfsURI, MASTER_OPTIONS.getMountOptions());
    mThrown.expect(IOException.class);
    mFileSystemMaster.mount(shadowAlluxioURI, shadowUfsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests mounting a prefix UFS dir.
   */
  @Test
  public void mountPrefixUfsDir() throws Exception {
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello/shadow");
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
    AlluxioURI preUfsURI = ufsURI.getParent();
    AlluxioURI anotherAlluxioURI = new AlluxioURI("/hi");
    mThrown.expect(InvalidPathException.class);
    mFileSystemMaster.mount(anotherAlluxioURI, preUfsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests mounting a suffix UFS dir.
   */
  @Test
  public void mountSuffixUfsDir() throws Exception {
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello/shadow");
    AlluxioURI preUfsURI = ufsURI.getParent();
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    mFileSystemMaster.mount(alluxioURI, preUfsURI, MASTER_OPTIONS.getMountOptions());
    AlluxioURI anotherAlluxioURI = new AlluxioURI("/hi");
    mThrown.expect(InvalidPathException.class);
    mFileSystemMaster.mount(anotherAlluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
  }

  /**
   * Tests unmount operation.
   */
  @Test
  public void unmount() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
    mFileSystemMaster.createDirectory(alluxioURI.join("dir"),
        CreateDirectoryOptions.defaults().setPersisted(true));
    mFileSystemMaster.unmount(alluxioURI);
    // after unmount, ufs path under previous mount point should still exist
    File file = new File(ufsURI.join("dir").toString());
    assertTrue(file.exists());
    // after unmount, alluxio path under previous mount point should not exist
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(alluxioURI.join("dir"), GET_STATUS_OPTIONS);
  }

  /**
   * Tests unmount operation failed when unmounting root.
   */
  @Test
  public void unmountRootWithException() throws Exception {
    mThrown.expect(InvalidPathException.class);
    mFileSystemMaster.unmount(new AlluxioURI("/"));
  }

  /**
   * Tests unmount operation failed when unmounting non-mount point.
   */
  @Test
  public void unmountNonMountPointWithException() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MASTER_OPTIONS.getMountOptions());
    AlluxioURI dirURI = alluxioURI.join("dir");
    mFileSystemMaster.createDirectory(dirURI, CreateDirectoryOptions.defaults().setPersisted(true));
    mThrown.expect(InvalidPathException.class);
    mFileSystemMaster.unmount(dirURI);
  }

  /**
   * Tests unmount operation failed when unmounting non-existing dir.
   */
  @Test
  public void unmountNonExistingPathWithException() throws Exception {
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.unmount(new AlluxioURI("/FileNotExists"));
  }

  /**
   * Creates a temporary UFS folder. The ufsPath must be a relative path since it's a temporary dir
   * created by mTestFolder.
   *
   * @param ufsPath the UFS path of the temp dir needed to created
   * @return the AlluxioURI of the temp dir
   */
  private AlluxioURI createTempUfsDir(String ufsPath) throws IOException {
    String path = mTestFolder.newFolder(ufsPath.split("/")).getPath();
    return new AlluxioURI(path);
  }
  /**
   * Creates a file in a temporary UFS folder.
   *
   * @param ufsPath the UFS path of the temp file to create
   * @return the AlluxioURI of the temp file
   */
  private AlluxioURI createTempUfsFile(String ufsPath) throws IOException {
    String path = mTestFolder.newFile(ufsPath).getPath();
    return new AlluxioURI(path);
  }

  /**
   * Tests the {@link DefaultFileSystemMaster#stop()} method.
   */
  @Test
  public void stop() throws Exception {
    mRegistry.stop();
    assertTrue(mExecutorService.isShutdown());
    assertTrue(mExecutorService.isTerminated());
  }

  /**
   * Tests the {@link FileSystemMaster#workerHeartbeat} method.
   */
  @Test
  public void workerHeartbeat() throws Exception {
    long blockId = createFileWithSingleBlock(ROOT_FILE_URI);

    long fileId = mFileSystemMaster.getFileId(ROOT_FILE_URI);
    mFileSystemMaster.scheduleAsyncPersistence(ROOT_FILE_URI);

    FileSystemCommand command = mFileSystemMaster
        .workerHeartbeat(mWorkerId1, Lists.newArrayList(fileId), WorkerHeartbeatOptions.defaults());
    assertEquals(CommandType.Persist, command.getCommandType());
    assertEquals(0, command.getCommandOptions().getPersistOptions().getFilesToPersist().size());
  }

  /**
   * Tests that lost files can successfully be detected.
   */
  @Test
  public void lostFilesDetection() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    long fileId = mFileSystemMaster.getFileId(NESTED_FILE_URI);

    FileInfo fileInfo = mFileSystemMaster.getFileInfo(fileId);
    mBlockMaster.reportLostBlocks(fileInfo.getBlockIds());

    assertEquals(PersistenceState.NOT_PERSISTED.name(), fileInfo.getPersistenceState());
    // Check with getPersistenceState.
    assertEquals(PersistenceState.NOT_PERSISTED,
        mFileSystemMaster.getPersistenceState(fileId));

    // run the detector
    HeartbeatScheduler.execute(HeartbeatContext.MASTER_LOST_FILES_DETECTION);

    fileInfo = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(PersistenceState.LOST.name(), fileInfo.getPersistenceState());
    // Check with getPersistenceState.
    assertEquals(PersistenceState.LOST, mFileSystemMaster.getPersistenceState(fileId));
  }

  /**
   * Tests load metadata logic.
   */
  @Test
  public void testLoadMetadata() throws Exception {
    FileUtils.createDir(Paths.get(mUnderFS).resolve("a").toString());
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());

    // TODO(peis): Avoid this hack by adding an option in getFileInfo to skip loading metadata.
    try {
      mFileSystemMaster.createDirectory(new AlluxioURI("alluxio:/a"),
          CreateDirectoryOptions.defaults());
      fail("createDirectory was expected to fail with FileAlreadyExistsException");
    } catch (FileAlreadyExistsException e) {
      assertEquals(
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(new AlluxioURI("alluxio:/a")),
          e.getMessage());
    }

    FileUtils.createFile(Paths.get(mUnderFS).resolve("a/f1").toString());
    FileUtils.createFile(Paths.get(mUnderFS).resolve("a/f2").toString());

    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a/f1"),
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());

    // This should not throw file exists exception those a/f1 is loaded.
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(false)
            .setLoadDescendantType(LoadDescendantPType.ONE).build());

    // TODO(peis): Avoid this hack by adding an option in getFileInfo to skip loading metadata.
    try {
      mFileSystemMaster.createFile(new AlluxioURI("alluxio:/a/f2"), CreateFileOptions.defaults());
      fail("createDirectory was expected to fail with FileAlreadyExistsException");
    } catch (FileAlreadyExistsException e) {
      assertEquals(
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(new AlluxioURI("alluxio:/a/f2")),
          e.getMessage());
    }

    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(false)
            .setLoadDescendantType(LoadDescendantPType.ONE).build());
  }

  /**
   * Tests ufs load with ACL.
   * Currently, it respects the ufs permissions instead of the default ACL for loadMetadata.
   * We may change that in the future, and change this test.
   * TODO(david): update this test when we respect default ACL for loadmetadata
   */
  @Test
  public void loadMetadataWithACL() throws Exception {
    FileUtils.createDir(Paths.get(mUnderFS).resolve("a").toString());
    AlluxioURI uri = new AlluxioURI("/a");
    mFileSystemMaster.loadMetadata(uri,
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());
    List<AclEntry> aclEntryList = new ArrayList<>();
    aclEntryList.add(AclEntry.fromCliString("default:user::r-x"));
    mFileSystemMaster.setAcl(uri, SetAclAction.MODIFY, aclEntryList,
        MASTER_OPTIONS.getSetAclOptions());

    FileInfo infoparent = mFileSystemMaster.getFileInfo(uri,
        MASTER_OPTIONS.getGetStatusOptions());

    FileUtils.createFile(Paths.get(mUnderFS).resolve("a/b/file1").toString());
    uri = new AlluxioURI("/a/b/file1");
    mFileSystemMaster.loadMetadata(uri,
        MASTER_OPTIONS.getLoadMetadataOptions().toBuilder().setCreateAncestors(true).build());
    FileInfo info = mFileSystemMaster.getFileInfo(uri,
        MASTER_OPTIONS.getGetStatusOptions());
    Assert.assertTrue(info.convertAclToStringEntries().contains("user::r-x"));
  }

  /**
   * Tests load root metadata. It should not fail.
   */
  @Test
  public void loadRoot() throws Exception {
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/"),
        MASTER_OPTIONS.getLoadMetadataOptions());
  }

  @Test
  public void getUfsInfo() throws Exception {
    FileInfo alluxioRootInfo =
        mFileSystemMaster.getFileInfo(new AlluxioURI("alluxio://"), GET_STATUS_OPTIONS);
    UfsInfo ufsRootInfo = mFileSystemMaster.getUfsInfo(alluxioRootInfo.getMountId());
    assertEquals(mUnderFS, ufsRootInfo.getUri());
    assertTrue(ufsRootInfo.getMountOptions().getProperties().isEmpty());
  }

  @Test
  public void getUfsInfoNotExist() throws Exception {
    UfsInfo noSuchUfsInfo = mFileSystemMaster.getUfsInfo(100L);
    assertNull(noSuchUfsInfo.getUri());
    assertNull(noSuchUfsInfo.getMountOptions());
  }

  /**
   * Tests that setting the ufs fingerprint persists across restarts.
   */
  @Test
  public void setUfsFingerprintReplay() throws Exception {
    String fingerprint = "FINGERPRINT";
    createFileWithSingleBlock(NESTED_FILE_URI);

    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setUfsFingerprint(fingerprint));

    // Simulate restart.
    stopServices();
    startServices();

    assertEquals(fingerprint,
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI,
            MASTER_OPTIONS.getGetStatusOptions()).getUfsFingerprint());
  }

  @Test
  public void ignoreInvalidFiles() throws Exception {
    FileUtils.createDir(Paths.get(mUnderFS, "test").toString());
    FileUtils.createFile(Paths.get(mUnderFS, "test", "a?b=C").toString());
    FileUtils.createFile(Paths.get(mUnderFS, "test", "valid").toString());
    List<FileInfo> listing = mFileSystemMaster.listStatus(new AlluxioURI("/test"),
        MASTER_OPTIONS.getListStatusOptions().toBuilder()
            .setLoadMetadataType(LoadMetadataPType.ALWAYS).setRecursive(true).build());
    assertEquals(1, listing.size());
    assertEquals("valid", listing.get(0).getName());
  }

  @Test
  public void propagatePersisted() throws Exception {
    AlluxioURI nestedFile = new AlluxioURI("/nested1/nested2/file");
    AlluxioURI parent1 = new AlluxioURI("/nested1/");
    AlluxioURI parent2 = new AlluxioURI("/nested1/nested2/");

    createFileWithSingleBlock(nestedFile);

    // Nothing is persisted yet.
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, MASTER_OPTIONS.getGetStatusOptions())
            .getPersistenceState());
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());

    // Persist the file.
    mFileSystemMaster.setAttribute(nestedFile, SetAttributeOptions.defaults().setPersisted(true));

    // Everything component should be persisted.
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, MASTER_OPTIONS.getGetStatusOptions())
            .getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());

    // Simulate restart.
    stopServices();
    startServices();

    // Everything component should be persisted.
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, MASTER_OPTIONS.getGetStatusOptions())
            .getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            MASTER_OPTIONS.getGetStatusOptions()).getPersistenceState());
  }

  private long createFileWithSingleBlock(AlluxioURI uri) throws Exception {
    mFileSystemMaster.createFile(uri, mNestedFileOptions);
    long blockId = mFileSystemMaster.getNewBlockIdForFile(uri);
    mBlockMaster.commitBlock(mWorkerId1, Constants.KB, "MEM", blockId, Constants.KB);
    CompleteFileOptions options = CompleteFileOptions.defaults().setUfsLength(Constants.KB);
    mFileSystemMaster.completeFile(uri, options);
    return blockId;
  }

  private void startServices() throws Exception {
    mRegistry = new MasterRegistry();
    mJournalSystem = JournalTestUtils.createJournalSystem(mJournalFolder);
    MasterContext masterContext = MasterTestUtils.testMasterContext(mJournalSystem);
    mMetricsMaster = new MetricsMasterFactory().create(mRegistry, masterContext);
    mRegistry.add(MetricsMaster.class, mMetricsMaster);
    mMetrics = Lists.newArrayList();
    mBlockMaster = new BlockMasterFactory().create(mRegistry, masterContext);
    mExecutorService = Executors
        .newFixedThreadPool(4, ThreadFactoryUtils.build("DefaultFileSystemMasterTest-%d", true));
    mFileSystemMaster = new DefaultFileSystemMaster(mBlockMaster, masterContext,
        ExecutorServiceFactories.constantExecutorServiceFactory(mExecutorService));
    mRegistry.add(FileSystemMaster.class, mFileSystemMaster);
    mJournalSystem.start();
    mJournalSystem.gainPrimacy();
    mRegistry.start(true);

    // set up workers
    mWorkerId1 = mBlockMaster.getWorkerId(
        new WorkerNetAddress().setHost("localhost").setRpcPort(80).setDataPort(81).setWebPort(82));
    mBlockMaster.workerRegister(mWorkerId1, Arrays.asList("MEM", "SSD"),
        ImmutableMap.of("MEM", (long) Constants.MB, "SSD", (long) Constants.MB),
        ImmutableMap.of("MEM", (long) Constants.KB, "SSD", (long) Constants.KB),
        new HashMap<String, List<Long>>(), new RegisterWorkerTOptions());
    mWorkerId2 = mBlockMaster.getWorkerId(
        new WorkerNetAddress().setHost("remote").setRpcPort(80).setDataPort(81).setWebPort(82));
    mBlockMaster.workerRegister(mWorkerId2, Arrays.asList("MEM", "SSD"),
        ImmutableMap.of("MEM", (long) Constants.MB, "SSD", (long) Constants.MB),
        ImmutableMap.of("MEM", (long) Constants.KB, "SSD", (long) Constants.KB),
        new HashMap<String, List<Long>>(), new RegisterWorkerTOptions());
  }

  private void stopServices() throws Exception {
    mRegistry.stop();
    mJournalSystem.stop();
  }
}
