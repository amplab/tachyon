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

package tachyon.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.io.Closer;

import tachyon.Constants;
import tachyon.TachyonURI;
import tachyon.client.block.BSContext;
import tachyon.client.file.FSContext;
import tachyon.client.table.RawTable;
import tachyon.conf.TachyonConf;
import tachyon.thrift.DependencyInfo;
import tachyon.thrift.FileBlockInfo;
import tachyon.thrift.FileInfo;
import tachyon.thrift.WorkerInfo;
import tachyon.underfs.UnderFileSystem;
import tachyon.util.ThreadFactoryUtils;
import tachyon.util.io.FileUtils;
import tachyon.util.network.NetworkAddressUtils;
import tachyon.util.network.NetworkAddressUtils.ServiceType;
import tachyon.worker.ClientMetrics;
import tachyon.worker.WorkerClient;

/**
 * Client API to use Tachyon as a file system. This API is not compatible with HDFS file system API;
 * while tachyon.hadoop.AbstractTFS provides another API that exposes Tachyon as HDFS file system.
 * Under the hood, this class maintains a MasterClient to talk to the master server and
 * WorkerClients to interact with different Tachyon workers.
 */
public class TachyonFS extends AbstractTachyonFS {

  /**
   * Creates a <code>TachyonFS</code> handler for the given path.
   *
   * @param tachyonPath a Tachyon path contains master address. e.g., tachyon://localhost:19998,
   *        tachyon://localhost:19998/ab/c.txt
   * @return the corresponding TachyonFS handler
   * @see #get(tachyon.TachyonURI, tachyon.conf.TachyonConf)
   */
  @Deprecated
  public static synchronized TachyonFS get(String tachyonPath) {
    return get(new TachyonURI(tachyonPath), new TachyonConf());
  }

  /**
   * Creates a <code>TachyonFS</code> handler for the given Tachyon URI.
   *
   * @param tachyonURI a Tachyon URI to indicate master address. e.g., tachyon://localhost:19998,
   *        tachyon://localhost:19998/ab/c.txt
   * @return the corresponding TachyonFS handler
   * @see #get(tachyon.TachyonURI, tachyon.conf.TachyonConf)
   */
  @Deprecated
  public static synchronized TachyonFS get(final TachyonURI tachyonURI) {
    return get(tachyonURI, new TachyonConf());
  }

  /**
   * Creates a <code>TachyonFS</code> handler for the given Tachyon URI and configuration.
   *
   * @param tachyonURI a Tachyon URI to indicate master address. e.g., tachyon://localhost:19998,
   *        tachyon://localhost:19998/ab/c.txt
   * @param tachyonConf The TachyonConf instance.
   * @return the corresponding TachyonFS handler
   */
  public static synchronized TachyonFS get(final TachyonURI tachyonURI, TachyonConf tachyonConf) {
    Preconditions.checkArgument(tachyonConf != null, "TachyonConf cannot be null.");
    Preconditions.checkArgument(tachyonURI != null, "Tachyon URI cannot be null. Use "
        + Constants.HEADER + "host:port/ ," + Constants.HEADER_FT + "host:port/.");
    String scheme = tachyonURI.getScheme();
    Preconditions.checkNotNull(scheme, "Tachyon scheme cannot be null. Use " + Constants.SCHEME
        + " or " + Constants.SCHEME_FT + ".");
    Preconditions.checkNotNull(tachyonURI.getHost(), "Tachyon hostname cannot be null.");
    Preconditions.checkState(tachyonURI.getPort() != -1, "Tachyon URI must have a port number.");
    Preconditions.checkState(
        (Constants.SCHEME.equals(scheme) || Constants.SCHEME_FT.equals(scheme)),
        "Tachyon scheme must be either " + Constants.SCHEME + " or " + Constants.SCHEME_FT + ".");

    boolean useZookeeper = scheme.equals(Constants.SCHEME_FT);
    tachyonConf.set(Constants.USE_ZOOKEEPER, Boolean.toString(useZookeeper));
    tachyonConf.set(Constants.MASTER_HOSTNAME, tachyonURI.getHost());
    tachyonConf.set(Constants.MASTER_PORT, Integer.toString(tachyonURI.getPort()));

    return get(tachyonConf);
  }

  /**
   * Creates a <code>TachyonFS</code> handler for the given hostname, port, and Zookeeper mode.
   *
   * @param masterHost master host details
   * @param masterPort port master listens on
   * @param zkMode use zookeeper
   * @return the corresponding TachyonFS handler
   */
  public static synchronized TachyonFS get(String masterHost, int masterPort, boolean zkMode) {
    TachyonConf tachyonConf = new TachyonConf();
    tachyonConf.set(Constants.MASTER_HOSTNAME, masterHost);
    tachyonConf.set(Constants.MASTER_PORT, Integer.toString(masterPort));
    tachyonConf.set(Constants.USE_ZOOKEEPER, Boolean.toString(zkMode));
    return get(tachyonConf);
  }

  /**
   * Creates a <code>TachyonFS</code> handler for the given Tachyon configuration.
   *
   * @param tachyonConf The TachyonConf instance.
   * @return the corresponding TachyonFS handler
   */
  public static synchronized TachyonFS get(TachyonConf tachyonConf) {
    Preconditions.checkArgument(tachyonConf != null, "TachyonConf cannot be null.");
    return new TachyonFS(tachyonConf);
  }

  private static final Logger LOG = LoggerFactory.getLogger(Constants.LOGGER_TYPE);
  private final int mUserFailedSpaceRequestLimits;
  private final ExecutorService mExecutorService;

  /** The RPC client talks to the file system master. */
  private final FileSystemMasterClient mFSMasterClient;
  /** The RPC client talks to the block store master. */
  private final BlockMasterClient mBlockMasterClient;
  /** The Master address. */
  private final InetSocketAddress mMasterAddress;
  /** The RPC client talks to the local worker if there is one. */
  private final WorkerClient mWorkerClient;
  private final Closer mCloser = Closer.create();
  /** Whether to use ZooKeeper or not */
  private final boolean mZookeeperMode;
  // Cached FileInfo
  private final Map<String, FileInfo> mPathToClientFileInfo =
      new HashMap<String, FileInfo>();
  private final Map<Long, FileInfo> mIdToClientFileInfo =
      new HashMap<Long, FileInfo>();

  private UnderFileSystem mUnderFileSystem;

  /** All Blocks that have been locked. */
  private final Map<Long, Set<Integer>> mLockedBlockIds = new HashMap<Long, Set<Integer>>();
  /** Mapping from block id to path of the block locked */
  private final Map<Long, String> mLockedBlockIdToPath = new HashMap<Long, String>();
  /** Each user facing block has a unique block lock id. */
  private final AtomicInteger mBlockLockId = new AtomicInteger(0);

  private TachyonURI mRootUri = null;
  private ClientMetrics mClientMetrics = new ClientMetrics();

  private TachyonFS(TachyonConf tachyonConf) {
    super(tachyonConf);

    mMasterAddress = NetworkAddressUtils.getConnectAddress(ServiceType.MASTER_RPC, tachyonConf);
    mZookeeperMode = mTachyonConf.getBoolean(Constants.USE_ZOOKEEPER);
    mExecutorService =
        Executors.newFixedThreadPool(2, ThreadFactoryUtils.build("client-heartbeat-%d", true));
    mFSMasterClient = mCloser.register(FSContext.INSTANCE.acquireMasterClient());
    mBlockMasterClient = mCloser.register(BSContext.INSTANCE.acquireMasterClient());
    mWorkerClient = mCloser.register(BSContext.INSTANCE.acquireWorkerClient());
    mUserFailedSpaceRequestLimits =
        mTachyonConf.getInt(Constants.USER_FAILED_SPACE_REQUEST_LIMITS);
    String scheme = mZookeeperMode ? Constants.SCHEME_FT : Constants.SCHEME;
    String authority = mMasterAddress.getHostName() + ":" + mMasterAddress.getPort();
    mRootUri = new TachyonURI(scheme, authority, TachyonURI.SEPARATOR);
  }

  /**
   * Updates the latest block access time on the worker.
   *
   * @param blockId the local block's id
   * @throws IOException when it cannot access the block
   */
  synchronized void accessLocalBlock(long blockId) throws IOException {
    if (mWorkerClient.isLocal()) {
      mWorkerClient.accessBlock(blockId);
    }
  }

  /**
   * Notifies the worker that the checkpoint file of the indicated file id has been added.
   *
   * @param fid the file id
   * @throws IOException when the underlying worker RPC fails
   */
  synchronized void addCheckpoint(long fid) throws IOException {
    mWorkerClient.addCheckpoint(fid);
  }

  /**
   * Notifies the worker to checkpoint the file of the indicated file id asynchronously
   *
   * @param fid the file id
   * @return true if succeed, false otherwise
   * @throws IOException when the underlying worker RPC fails
   */
  synchronized boolean asyncCheckpoint(long fid) throws IOException {
    return mWorkerClient.asyncCheckpoint(fid);
  }

  /**
   * Notifies the worker that the block is cached.
   *
   * @param blockId the block id
   * @throws IOException when the underlying worker RPC fails
   */
  public synchronized void cacheBlock(long blockId) throws IOException {
    mWorkerClient.cacheBlock(blockId);
  }

  /**
   * Notifies the worker the block is canceled.
   *
   * @param blockId the block id
   * @throws IOException when the underlying worker RPC fails
   */
  public synchronized void cancelBlock(long blockId) throws IOException {
    mWorkerClient.cancelBlock(blockId);
  }

  /**
   * Closes the client connections to both the master and worker.
   *
   * @throws IOException when it fails to close the connections
   */
  @Override
  public synchronized void close() throws IOException {
    try {
      mCloser.close();
    } finally {
      mExecutorService.shutdown();
    }
  }

  /**
   * Marks the file as complete.
   *
   * @param fid the file id
   * @throws IOException when the underlying master RPC fails
   */
  synchronized void completeFile(long fid) throws IOException {
    mFSMasterClient.completeFile(fid);
  }

  /**
   * Creates a user UnderFileSystem temporary folder and returns it.
   *
   * @param ufsConf the configuration of UnderFileSystem
   * @return the UnderFileSystem temporary folder
   * @throws IOException when the underlying worker RPC or under file system interaction fails
   */
  synchronized String createAndGetUserUfsTempFolder(Object ufsConf) throws IOException {
    String tmpFolder = mWorkerClient.getUserUfsTempFolder();
    if (tmpFolder == null) {
      return null;
    }

    if (mUnderFileSystem == null) {
      mUnderFileSystem = UnderFileSystem.get(tmpFolder, ufsConf, mTachyonConf);
    }

    mUnderFileSystem.mkdirs(tmpFolder, true);

    return tmpFolder;
  }

  /**
   * Creates a dependency.
   *
   * @param parents the dependency's input files
   * @param children the dependency's output files
   * @param commandPrefix identifies a command prefix
   * @param data stores dependency data
   * @param comment records a dependency comment
   * @param framework identifies the framework
   * @param frameworkVersion identifies the framework version
   * @param dependencyType the dependency's type, Wide or Narrow
   * @param childrenBlockSizeByte the block size of the dependency's output files
   * @return the dependency's id
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized int createDependency(List<String> parents, List<String> children,
      String commandPrefix, List<ByteBuffer> data, String comment, String framework,
      String frameworkVersion, int dependencyType, long childrenBlockSizeByte) throws IOException {
    return mFSMasterClient.user_createDependency(parents, children, commandPrefix, data, comment,
        framework, frameworkVersion, dependencyType, childrenBlockSizeByte);
  }

  /**
   * Creates a new file in the file system.
   *
   * @param path The path of the file
   * @param ufsPath The path of the file in the under file system. If this is empty, the file does
   *        not exist in the under file system yet.
   * @param blockSizeByte The size of the block in bytes. It is -1 iff ufsPath is non-empty.
   * @param recursive Creates necessary parent folders if true, not otherwise.
   * @return The file id, which is globally unique.
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized int createFile(TachyonURI path, TachyonURI ufsPath, long blockSizeByte,
      boolean recursive) throws IOException {
    validateUri(path);
    // TODO: This is not safe
    if (blockSizeByte > 0) {
      return (int) mFSMasterClient.createFile(path.getPath(), blockSizeByte, recursive);
    } else {
      return (int) mFSMasterClient.loadFileFromUfs(path.getPath(), ufsPath.getPath(), blockSizeByte,
          recursive);
    }
  }

  /**
   * Creates a <code>RawTable</code> and returns its id.
   *
   * @param path the RawTable's path
   * @param columns number of columns it has
   * @return the id if succeed, -1 otherwise
   * @throws IOException when number of columns is invalid or the underlying master RPC fails
   */
  public synchronized int createRawTable(TachyonURI path, int columns) throws IOException {
    return createRawTable(path, columns, ByteBuffer.allocate(0));
  }

  /**
   * Creates a <code>RawTable</code> and returns its id.
   *
   * @param path the RawTable's path
   * @param columns number of columns it has
   * @param metadata the meta data of the RawTable
   * @return the id if succeed, -1 otherwise
   * @throws IOException when number of columns is invalid or the underlying master RPC fails
   */
  public synchronized int createRawTable(TachyonURI path, int columns, ByteBuffer metadata)
      throws IOException {
    validateUri(path);
    int maxColumns = mTachyonConf.getInt(Constants.MAX_COLUMNS);
    if (columns < 1 || columns > maxColumns) {
      throw new IOException("Column count " + columns + " is smaller than 1 or " + "bigger than "
          + maxColumns);
    }
    throw new UnsupportedOperationException("Raw table is currently unsupported");
    // return mMasterClient.user_createRawTable(path.getPath(), columns, metadata);
  }

  /**
   * Deletes a file or folder.
   *
   * @param fileId The id of the file / folder. If it is not -1, path parameter is ignored.
   *        Otherwise, the method uses the path parameter.
   * @param path The path of the file / folder. It could be empty iff id is not -1.
   * @param recursive If fileId or path represents a non-empty folder, delete the folder recursively
   *        or not.
   * @return true if deletes successfully, false otherwise
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized boolean delete(long fileId, TachyonURI path, boolean recursive)
      throws IOException {
    validateUri(path);
    if (fileId == -1) {
      fileId = mFSMasterClient.getFileId(path.getPath());
    }
    return mFSMasterClient.deleteFile(fileId, recursive);
  }

  /**
   * Returns whether the file exists or not.
   *
   * @param path the file's path in Tachyon file system
   * @return true if it exists, false otherwise
   * @throws IOException when the underlying master RPC fails
   */
  // TODO: Consider making an exists function
  public synchronized boolean exist(TachyonURI path) throws IOException {
    try {
      FileInfo info = getFileStatus(-1, path, false);
      return null != info;
    } catch (IOException ioe) {
      return false;
    }
  }

  /**
   * Get the block id by the file id and block index. it will check whether the file and the block
   * exist.
   *
   * @param fileId the file id
   * @param blockIndex The index of the block in the file.
   * @return the block id if exists
   * @throws IOException if the file does not exist, or connection issue.
   */
  public synchronized long getBlockId(long fileId, int blockIndex) throws IOException {
    FileInfo info = getFileStatus(fileId, true);

    if (info == null) {
      throw new IOException("File " + fileId + " does not exist.");
    }

    if (info.blockIds.size() > blockIndex) {
      return info.blockIds.get(blockIndex);
    }

    return mFSMasterClient.getFileBlockInfo(fileId, blockIndex).blockInfo.getBlockId();
  }

  /**
   * @return a new block lock id
   */
  synchronized int getBlockLockId() {
    return mBlockLockId.getAndIncrement();
  }

  /**
   * Gets a ClientBlockInfo by the block id.
   *
   * @param blockId the id of the block
   * @return the ClientBlockInfo of the specified block
   * @throws IOException when the underlying master RPC fails.
   */
  synchronized FileBlockInfo getClientBlockInfo(long blockId) throws IOException {
    throw new UnsupportedOperationException("FileBlockInfo is no longer supported, use FileInfo "
        + "and/or BlockInfo");
  }

  /**
   *
   * @param depId the dependency id
   * @return the DependencyInfo of the specified dependency
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized DependencyInfo getClientDependencyInfo(int depId) throws IOException {
    return mFSMasterClient.getDependencyInfo(depId);
  }

  /**
   * Gets the user's <code>ClientMetrics</code>.
   *
   * @return the <code>ClientMetrics</code> object.
   */
  ClientMetrics getClientMetrics() {
    return mClientMetrics;
  }

  /**
   * Gets <code>TachyonFile</code> based on the file id.
   *
   * NOTE: This *will* use cached file metadata, and so will not see changes to dynamic properties,
   * such as the pinned flag. This is also different from the behavior of getFile(path), which by
   * default will not use cached metadata.
   *
   * @param fid file id.
   * @return <code>TachyonFile</code> of the file id, or null if the file does not exist
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized TachyonFile getFile(long fid) throws IOException {
    return getFile(fid, true);
  }

  /**
   * Gets <code>TachyonFile</code> based on the file id. If useCachedMetadata, this will not see
   * changes to the file's pin setting, or other dynamic properties.
   *
   * @return TachyonFile of the file id, or null if the file does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized TachyonFile getFile(long fid, boolean useCachedMetadata) throws IOException {
    FileInfo fileInfo = getFileStatus(fid, TachyonURI.EMPTY_URI, useCachedMetadata);
    if (fileInfo == null) {
      return null;
    }
    return new TachyonFile(this, fid, mTachyonConf);
  }

  /**
   * Gets <code>TachyonFile</code> based on the path. Does not utilize the file metadata cache.
   *
   * @param path file path.
   * @return TachyonFile of the path, or null if the file does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized TachyonFile getFile(TachyonURI path) throws IOException {
    validateUri(path);
    return getFile(path, false);
  }

  /**
   * Gets <code>TachyonFile</code> based on the path. If useCachedMetadata is true, this will not
   * see changes to the file's pin setting, or other dynamic properties.
   *
   * @param path file path.
   * @param useCachedMetadata whether to use the file metadata cache
   * @return TachyonFile of the path, or null if the file does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized TachyonFile getFile(TachyonURI path, boolean useCachedMetadata)
      throws IOException {
    validateUri(path);
    FileInfo fileInfo = getFileStatus(-1, path, useCachedMetadata);
    if (fileInfo == null) {
      return null;
    }
    return new TachyonFile(this, fileInfo.getFileId(), mTachyonConf);
  }

  /**
   * Gets all the blocks' info of the file.
   *
   * @param fid the file id
   * @return the list of the blocks' info
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized List<FileBlockInfo> getFileBlocks(long fid) throws IOException {
    // TODO Should read from mClientFileInfos if possible. Should add timeout to improve this.
    return mFSMasterClient.getFileBlockInfoList(fid);
  }

  /**
   * Get file id by the path. It will check if the path exists.
   *
   * @param path the path in Tachyon file system
   * @return the file id if exists, -1 otherwise
   */
  public synchronized long getFileId(TachyonURI path) {
    try {
      FileInfo fileInfo = getFileStatus(-1, path, false);
      return fileInfo == null ? -1 : fileInfo.getFileId();
    } catch (IOException e) {
      return -1;
    }
  }

  /**
   * Gets a file status.
   *
   * @param cache FileInfo cache.
   * @param key the key in the cache.
   * @param fileId the id of the queried file. If it is -1, uses path.
   * @param path the path of the queried file. If fielId is not -1, this parameter is ignored.
   * @param useCachedMetaData whether to use the cached data or not.
   * @return the clientFileInfo.
   * @throws IOException when the underlying master RPC fails
   */
  private synchronized <K> FileInfo getFileStatus(Map<K, FileInfo> cache, K key,
      long fileId, String path, boolean useCachedMetaData) throws IOException {
    FileInfo info = null;
    if (useCachedMetaData) {
      info = cache.get(key);
      if (info != null) {
        return info;
      }
    }

    if (fileId == -1) {
      fileId = mFSMasterClient.getFileId(path);
    }
    if (fileId == -1) {
      cache.remove(key);
      return null;
    }
    info = mFSMasterClient.getFileInfo(fileId);
    path = info.getPath();

    // TODO(hy): LRU
    mIdToClientFileInfo.put(fileId, info);
    mPathToClientFileInfo.put(path, info);

    return info;
  }

  /**
   * Advanced API.
   *
   * Gets the FileInfo object that represents the fileId, or the path if fileId is -1.
   *
   * @param fileId the file id of the file or folder.
   * @param path the path of the file or folder. valid iff fileId is -1.
   * @param useCachedMetadata if true use the local cached meta data
   * @return the FileInfo of the file. null if the file does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized FileInfo getFileStatus(long fileId, TachyonURI path,
      boolean useCachedMetadata) throws IOException {
    if (fileId == -1) {
      fileId = mFSMasterClient.getFileId(path.getPath());
    }
    return getFileStatus(mIdToClientFileInfo, fileId, fileId, TachyonURI.EMPTY_URI.getPath(),
        useCachedMetadata);
  }

  @Override
  public FileInfo getFileStatus(long fileId, TachyonURI path) throws IOException {
    return getFileStatus(fileId, path, false);
  }

  /**
   * Gets <code>ClientFileInfo</code> object based on fileId.
   *
   * @param fileId the file id of the file or folder.
   * @param useCachedMetadata if true use the local cached meta data
   * @return the FileInfo of the file. null if the file does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized FileInfo getFileStatus(long fileId, boolean useCachedMetadata)
      throws IOException {
    return getFileStatus(fileId, TachyonURI.EMPTY_URI, useCachedMetadata);
  }

  /**
   * Gets block's temporary path from worker with initial space allocated.
   *
   * @param blockId the id of the block
   * @param initialBytes the initial bytes allocated for the block file
   * @return the temporary path of the block file
   * @throws IOException when the underlying worker RPC fails
   */
  public synchronized String getLocalBlockTemporaryPath(long blockId, long initialBytes)
      throws IOException {
    String blockPath = mWorkerClient.requestBlockLocation(blockId, initialBytes);
    // TODO(hy): Handle this in the worker?
    FileUtils.createBlockPath(blockPath);
    return blockPath;
  }

  /**
   * Gets <code>RawTable</code> by id.
   *
   * @param id the id of the raw table
   * @return the RawTable
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized RawTable getRawTable(long id) throws IOException {
    throw new UnsupportedOperationException("Raw table is currently unsupported");
    //RawTableInfo rawTableInfo = mMasterClient.user_getClientRawTableInfo(id, "");
    //return new RawTable(this, rawTableInfo);
  }

  /**
   * Get the <code>RawTable</code> by path.
   *
   * @param path the path of the raw table
   * @return the RawTable
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized RawTable getRawTable(TachyonURI path) throws IOException {
    throw new UnsupportedOperationException("Raw table is currently unsupported");
    // validateUri(path);
    // RawTableInfo rawTableInfo =
    //    mMasterClient.user_getClientRawTableInfo(-1, path.getPath());
    // return new RawTable(this, rawTableInfo);
  }

  /**
   * @return the address of the UnderFileSystem
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized String getUfsAddress() throws IOException {
    return mFSMasterClient.getUfsAddress();
  }

  /**
   * @return URI of the root of the filesystem
   */
  @Override
  public synchronized TachyonURI getUri() {
    return mRootUri;
  }

  /**
   * Returns the userId of the worker client. This is only used for testing.
   *
   * @return the userId of the worker client
   * @throws IOException when the underlying master RPC fails
   */
  long getUserId() throws IOException {
    return mWorkerClient.getUserId();
  }

  /**
   * @return get the total number of bytes used in Tachyon cluster
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized long getUsedBytes() throws IOException {
    throw new UnsupportedOperationException("Currently unsupported.");
  }

  /**
   * @return get the capacity of Tachyon cluster
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized long getCapacityBytes() throws IOException {
    throw new UnsupportedOperationException("Currently unsupported.");
  }

  /**
   * @return The address of the data server on the worker.
   */
  public synchronized InetSocketAddress getWorkerDataServerAddress() {
    return mWorkerClient.getDataServerAddress();
  }

  /**
   * @return all the works' info
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized List<WorkerInfo> getWorkersInfo() throws IOException {
    return mBlockMasterClient.getWorkerInfoList();
  }

  /**
   * @return true if there is a local worker, false otherwise
   */
  public synchronized boolean hasLocalWorker() {
    return mWorkerClient.isLocal();
  }

  /**
   * Checks if this client is connected to master.
   *
   * @return true if this client is connected to master, false otherwise
   */
  public synchronized boolean isConnected() {
    return mFSMasterClient.isConnected();
  }

  /**
   * Checks if the indicated file id is a directory.
   *
   * @param fid the file id
   * @return true if the file is a directory, false otherwise
   */
  synchronized boolean isDirectory(int fid) {
    return mIdToClientFileInfo.get(fid).isFolder;
  }

  /**
   * If the <code>path</code> is a directory, returns all the direct entries in it. If the
   * <code>path</code> is a file, returns its ClientFileInfo.
   *
   * @param path the target directory/file path
   * @return A list of FileInfo, null if the file or folder does not exist.
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized List<FileInfo> listStatus(TachyonURI path) throws IOException {
    validateUri(path);
    return mFSMasterClient.getFileInfoList(getFileStatus(-1, path).getFileId());
  }

  /**
   * Locks a block in the current TachyonFS.
   *
   * @param blockId The id of the block to lock. <code>blockId</code> must be positive.
   * @param blockLockId The block lock id of the block of lock. <code>blockLockId</code> must be
   *        non-negative.
   * @return the path of the block file locked
   * @throws IOException when the underlying worker RPC fails
   */
  synchronized String lockBlock(long blockId, int blockLockId) throws IOException {
    if (blockId <= 0 || blockLockId < 0) {
      return null;
    }

    if (mLockedBlockIds.containsKey(blockId)) {
      mLockedBlockIds.get(blockId).add(blockLockId);
      return mLockedBlockIdToPath.get(blockId);
    }

    if (!mWorkerClient.isLocal()) {
      return null;
    }
    String blockPath = mWorkerClient.lockBlock(blockId);

    if (blockPath != null) {
      Set<Integer> lockIds = new HashSet<Integer>(4);
      lockIds.add(blockLockId);
      mLockedBlockIds.put(blockId, lockIds);
      mLockedBlockIdToPath.put(blockId, blockPath);
      return blockPath;
    }
    return null;
  }

  /**
   * Creates a folder.
   *
   * @param path the path of the folder to be created
   * @param recursive Creates necessary parent folders if true, not otherwise.
   * @return true if the folder is created successfully or already existing. false otherwise.
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized boolean mkdirs(TachyonURI path, boolean recursive) throws IOException {
    validateUri(path);
    return mFSMasterClient.createDirectory(path.getPath(), recursive);
  }

  /**
   * An alias for setPinned(fid, true).
   *
   * @throws IOException when the underlying worker RPC fails
   * @see #setPinned(long, boolean)
   */
  public synchronized void pinFile(long fid) throws IOException {
    setPinned(fid, true);
  }

  /**
   * Frees an in-memory file or folder.
   *
   * @param fileId The id of the file / folder. If it is not -1, path parameter is ignored.
   *        Otherwise, the method uses the path parameter.
   * @param path The path of the file / folder. It could be empty iff id is not -1.
   * @param recursive If fileId or path represents a non-empty folder, free the folder recursively
   *        or not
   * @return true if in-memory free successfully, false otherwise.
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized boolean freepath(long fileId, TachyonURI path, boolean recursive)
      throws IOException {
    validateUri(path);
    if (fileId == -1) {
      fileId = mFSMasterClient.getFileId(path.getPath());
    }
    return mFSMasterClient.free(fileId, recursive);
  }

  /**
   * Promotes a block to the top StorageTier, after the block file is accessed.
   *
   * @param blockId the id of the block
   * @return true if success, false otherwise
   * @throws IOException when the underlying worker RPC fails
   */
  public synchronized boolean promoteBlock(long blockId) throws IOException {
    if (mWorkerClient.isLocal()) {
      return mWorkerClient.promoteBlock(blockId);
    }
    return false;
  }

  /**
   * Renames a file or folder to the indicated new path.
   *
   * @param fileId The id of the source file / folder. If it is not -1, path parameter is ignored.
   *        Otherwise, the method uses the srcPath parameter.
   * @param srcPath The path of the source file / folder. It could be empty iff id is not -1.
   * @param dstPath The path of the destination file / folder. It could be empty iff id is not -1.
   * @return true if renames successfully, false otherwise.
   * @throws IOException when the underlying master RPC fails
   */
  @Override
  public synchronized boolean rename(long fileId, TachyonURI srcPath, TachyonURI dstPath)
      throws IOException {
    validateUri(srcPath);
    validateUri(dstPath);
    if (fileId == -1) {
      fileId = mFSMasterClient.getFileId(srcPath.getPath());
    }
    return mFSMasterClient.renameFile(fileId, dstPath.getPath());
  }

  /**
   * Reports the lost file to master.
   *
   * @param fileId the lost file id
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized void reportLostFile(long fileId) throws IOException {
    mFSMasterClient.reportLostFile(fileId);
  }

  /**
   * Requests the dependency's needed files.
   *
   * @param depId the dependency id
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized void requestFilesInDependency(int depId) throws IOException {
    mFSMasterClient.requestFilesInDependency(depId);
  }

  /**
   * Tries to request space for certain block. Only works when a local worker exists.
   *
   * @param blockId the id of the block that space will be allocated for
   * @param requestSpaceBytes size to request in bytes
   * @return the size bytes that allocated to the block, -1 if no local worker exists
   * @throws IOException when the underlying file does not exist
   */
  public synchronized long requestSpace(long blockId, long requestSpaceBytes) throws IOException {
    if (!hasLocalWorker()) {
      return -1;
    }

    long userQuotaUnitBytes = mTachyonConf.getBytes(Constants.USER_QUOTA_UNIT_BYTES);

    long toRequestSpaceBytes = Math.max(requestSpaceBytes, userQuotaUnitBytes);
    for (int attempt = 0; attempt < mUserFailedSpaceRequestLimits; attempt ++) {
      if (mWorkerClient.requestSpace(blockId, toRequestSpaceBytes)) {
        return toRequestSpaceBytes;
      }
    }
    return 0;
  }

  /**
   * Sets the "pinned" flag for the given file. Pinned files are never evicted by Tachyon until they
   * are unpinned.
   *
   * Calling setPinned() on a folder will recursively set the "pinned" flag on all of that folder's
   * children. This may be an expensive operation for folders with many files/subfolders.
   *
   * @param fid the file id
   * @param pinned the target "pinned" flag value
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized void setPinned(long fid, boolean pinned) throws IOException {
    mFSMasterClient.setPinned(fid, pinned);
  }

  /**
   * Prints out the string representation of this Tachyon server address.
   *
   * @return the string representation like tachyon://host:port or tachyon-ft://host:port
   */
  @Override
  public String toString() {
    return (mZookeeperMode ? Constants.HEADER_FT : Constants.HEADER) + mMasterAddress.toString();
  }

  /**
   * Unlocks a block in the current TachyonFS.
   *
   * @param blockId The id of the block to unlock. <code>blockId</code> must be positive.
   * @param blockLockId The block lock id of the block of unlock. <code>blockLockId</code> must be
   *        non-negative.
   * @throws IOException when the underlying workerRPC fails
   */
  synchronized boolean unlockBlock(long blockId, int blockLockId) throws IOException {
    if (blockId <= 0 || blockLockId < 0) {
      return false;
    }

    if (!mLockedBlockIds.containsKey(blockId)) {
      return true;
    }
    Set<Integer> lockIds = mLockedBlockIds.get(blockId);
    lockIds.remove(blockLockId);
    if (!lockIds.isEmpty()) {
      return true;
    }

    if (!mWorkerClient.isLocal()) {
      return false;
    }

    mLockedBlockIdToPath.remove(blockId);
    mLockedBlockIds.remove(blockId);
    return mWorkerClient.unlockBlock(blockId);
  }

  /**
   * An alias for setPinned(fid, false).
   *
   * @throws IOException when the underlying worker RPC fails
   * @see #setPinned(long, boolean)
   */
  public synchronized void unpinFile(long fid) throws IOException {
    setPinned(fid, false);
  }

  /**
   * Updates the RawTable's meta data
   *
   * @param id the raw table's id
   * @param metadata the new meta data
   * @throws IOException when the underlying master RPC fails
   */
  public synchronized void updateRawTableMetadata(long id, ByteBuffer metadata) throws IOException {
    throw new UnsupportedOperationException("Raw table is currently unsupported");
    //mMasterClient.user_updateRawTableMetadata(id, metadata);
  }

  /**
   * Validates the given uri, throws an appropriate Exception if the uri is invalid.
   *
   * @param uri The uri to validate
   */
  private void validateUri(TachyonURI uri) {
    Preconditions.checkNotNull(uri, "URI cannot be null.");
    Preconditions.checkArgument(uri.isPathAbsolute() || TachyonURI.EMPTY_URI.equals(uri),
        "URI must be absolute, unless it's empty.");
    Preconditions.checkArgument(
        !uri.hasScheme() || mRootUri.getScheme().equals(uri.getScheme()),
        "URI's scheme: " + uri.getScheme() + " must match the file system's scheme: "
            + mRootUri.getScheme() + ", unless it doesn't have a scheme.");
    Preconditions.checkArgument(
        !uri.hasAuthority() || mRootUri.getAuthority().equals(uri.getAuthority()),
        "URI's authority: " + uri.getAuthority() + " must match the file system's authority: "
            + mRootUri.getAuthority() + ", unless it doesn't have an authority.");
  }
}
