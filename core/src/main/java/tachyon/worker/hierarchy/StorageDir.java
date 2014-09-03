package tachyon.worker.hierarchy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.io.Closer;

import tachyon.Constants;
import tachyon.UnderFileSystem;
import tachyon.client.BlockHandler;
import tachyon.util.CommonUtils;
import tachyon.worker.WorkerSpaceCounter;

/**
 * Used to store and manage block files in storage's directory on different under file systems.
 */
public final class StorageDir {
  private final Logger LOG = Logger.getLogger(Constants.LOGGER_TYPE);
  private final Map<Long, Long> mBlockSizes = new ConcurrentHashMap<Long, Long>();
  private final Map<Long, Long> mLastBlockAccessTimeMS = new ConcurrentHashMap<Long, Long>();
  private final BlockingQueue<Long> mRemovedBlockList = new ArrayBlockingQueue<Long>(
      Constants.WORKER_BLOCKS_QUEUE_SIZE);
  private final WorkerSpaceCounter mSpaceCounter;
  private final long mStorageId;
  private final String mDataPath;
  private final String mDirPath;
  private final String mUserTempPath;
  private final UnderFileSystem mUfs;
  private final Object mUfsConf;
  // Synchronized by itself
  private final Map<Long, Long> mUserAllocatedSpace = new HashMap<Long, Long>();
  // Synchronized by itself
  private final HashMultimap<Long, Long> mLockedBlocksPerUser = HashMultimap.create();
  // Synchronized by itself
  private final HashMultimap<Long, Long> mUserPerLockedBlock = HashMultimap.create();

  StorageDir(long storageId, String dirPath, long capacity, String dataFolder,
      String userTempFolder, Object conf) {
    mDirPath = dirPath;
    mUfsConf = conf;
    mUfs = UnderFileSystem.get(mDirPath, conf);
    mSpaceCounter = new WorkerSpaceCounter(capacity);
    mStorageId = storageId;
    mDataPath = CommonUtils.concat(mDirPath, dataFolder);
    mUserTempPath = CommonUtils.concat(mDirPath, userTempFolder);
  }

  /**
   * Update the access time of the block
   * 
   * @param blockId
   *          id of the block
   */
  public void accessBlock(long blockId) {
    mLastBlockAccessTimeMS.put(blockId, System.currentTimeMillis());
  }

  /**
   * Add a block id in current StorageDir
   * 
   * @param blockId
   *          id of the block
   * @param size
   *          size of the block file
   */
  private void addBlockId(long blockId, long size) {
    mLastBlockAccessTimeMS.put(blockId, System.currentTimeMillis());
    mBlockSizes.put(blockId, size);
  }

  /**
   * Move the cached file from user temp directory to data directory
   * 
   * @param userId
   *          id of the user
   * @param blockId
   *          id of the block
   * @return true if success, false otherwise
   * @throws IOException
   */
  public boolean cacheBlock(long userId, long blockId) throws IOException {
    String srcPath = getUserTempFilePath(userId, blockId);
    String destPath = getBlockFilePath(blockId);
    boolean result = mUfs.rename(srcPath, destPath);
    if (result) {
      addBlockId(blockId, getFileSize(blockId));
    }
    return result;
  }

  /**
   * Check status of the users, removedUsers can't be modified any more after being passed down from
   * the caller
   * 
   * @param removedUsers
   *          id list of the removed users
   */
  public void checkStatus(List<Long> removedUsers) {
    for (long userId : removedUsers) {
      synchronized (mUserPerLockedBlock) {
        Set<Long> blockIds = mLockedBlocksPerUser.get(userId);
        mLockedBlocksPerUser.removeAll(userId);
        if (blockIds != null) {
          for (long blockId : blockIds) {
            unlockBlock(blockId, userId);
          }
        }
      }
      synchronized (mUserAllocatedSpace) {
        mUserAllocatedSpace.remove(userId);
      }
    }
  }

  /**
   * Check whether current StorageDir contains certain block
   * 
   * @param blockId
   *          id of the block
   * @return true if StorageDir contains the block, false otherwise
   */
  public boolean containsBlock(long blockId) {
    return mBlockSizes.containsKey(blockId);
  }

  /**
   * Copy block from current StorageDir to another
   * 
   * @param blockId
   *          id of the block
   * @param dstDir
   *          destination StorageDir
   * @return true if success, false otherwise
   * @throws IOException
   */
  public boolean copyBlock(long blockId, StorageDir dstDir) throws IOException {
    int len = (int) getBlockSize(blockId);
    boolean copySuccess = false;
    Closer closer = Closer.create();
    try {
      BlockHandler bhSrc = closer.register(getBlockHandler(blockId));
      BlockHandler bhDst = closer.register(dstDir.getBlockHandler(blockId));
      ByteBuffer srcBuf = bhSrc.read(0, len);
      copySuccess = bhDst.append(0, srcBuf) > 0;
    } finally {
      closer.close();
    }
    if (copySuccess) {
      dstDir.addBlockId(blockId, len);
    }
    return copySuccess;
  }

  /**
   * Remove a block from current StorageDir
   * 
   * @param blockId
   *          The block to be removed.
   * @return true if succeed, false otherwise
   * @throws IOException
   */
  public boolean deleteBlock(long blockId) throws IOException {
    if (mBlockSizes.containsKey(blockId)) {
      String blockfile = getBlockFilePath(blockId);
      boolean result = mUfs.delete(blockfile, true);
      if (result) {
        deleteBlockId(blockId);
        LOG.debug("Removed block file:" + blockfile);
      } else {
        LOG.error("Error during delete block! blockfile:" + blockfile);
      }
      return result;
    } else {
      LOG.error("Block " + blockId + " does not exist in current StorageDir.");
      return false;
    }
  }

  /**
   * Delete block from current StorageDir
   * 
   * @param blockId
   *          id of the block
   */
  private void deleteBlockId(long blockId) {
    mLastBlockAccessTimeMS.remove(blockId);
    returnSpace(mBlockSizes.remove(blockId));
    mRemovedBlockList.add(blockId);
  }

  /**
   * Get available space in current StorageDir
   * 
   * @return available space in current StorageDir
   */
  public long getAvailable() {
    return mSpaceCounter.getAvailableBytes();
  }

  /**
   * Get data of the block file
   * 
   * @param blockId
   *          id of the block
   * @param offset
   *          offset of the file
   * @param length
   *          length of data to read
   * @return ByteBuffer contains data of the block
   * @throws IOException
   */
  public ByteBuffer getBlockData(long blockId, long offset, int length) throws IOException {
    BlockHandler bh = getBlockHandler(blockId);
    try {
      return bh.read(offset, length);
    } finally {
      bh.close();
    }
  }

  /**
   * Get file path of the block
   * 
   * @param blockId
   *          id of the block
   * @return file path of the block
   */
  public String getBlockFilePath(long blockId) {
    return CommonUtils.concat(mDataPath, blockId);
  }

  /**
   * Get block handler of the block
   * 
   * @param blockId
   *          id of the block
   * @return block handler of the block file
   * @throws IOException
   */
  public BlockHandler getBlockHandler(long blockId) throws IOException {
    String filePath = getBlockFilePath(blockId);
    try {
      return BlockHandler.get(filePath);
    } catch (IllegalArgumentException e) {
      throw new IOException(e.getMessage());
    }
  }

  /**
   * Get ids of the blocks on current StorageDir
   * 
   * @return ids of the blocks
   */
  public Set<Long> getBlockIds() {
    return mBlockSizes.keySet();
  }

  /**
   * Get size of the block
   * 
   * @param blockId
   *          id of the block
   * @return size of the block, -1 if block doesn't exist
   */
  public long getBlockSize(long blockId) {
    if (mBlockSizes.containsKey(blockId)) {
      return mBlockSizes.get(blockId);
    } else {
      return -1;
    }
  }

  /**
   * Get sizes of the blocks on current StorageDir
   * 
   * @return sizes of the blocks
   * @throws IOException
   */
  public Map<Long, Long> getBlockSizes() {
    return mBlockSizes;
  }

  /**
   * Get capacity of current StorageDir
   * 
   * @return capacity of current StorageDir
   */
  public long getCapacity() {
    return mSpaceCounter.getCapacityBytes();
  }

  /**
   * Get data path of current StorageDir
   * 
   * @return data path on current StorageDir
   */
  public String getDirDataPath() {
    return mDataPath;
  }

  /**
   * Get path of current StorageDir
   * 
   * @return path of StorageDir
   */
  public String getDirPath() {
    return mDirPath;
  }

  /**
   * Get size of the block file on current StorageDir, used to initialize StorageDir
   * 
   * @param blockId
   *          id of the block
   * @return file size of the block
   * @throws IOException
   */
  private long getFileSize(long blockId) throws IOException {
    String blockfile = getBlockFilePath(blockId);
    return mUfs.getFileSize(blockfile);
  }

  /**
   * Get access time of blocks
   * 
   * @return access time of blocks
   */
  public Map<Long, Long> getLastBlockAccessTime() {
    return mLastBlockAccessTimeMS;
  }

  /**
   * Get list of removed block ids
   * 
   * @return list of removed block ids
   */
  public List<Long> getRemovedBlockList() {
    List<Long> removedBlockList = new ArrayList<Long>();
    mRemovedBlockList.drainTo(removedBlockList);
    return removedBlockList;
  }

  /**
   * Get storage id of current StorageDir
   * 
   * @return storage id of current StorageDir
   */
  public long getStorageId() {
    return mStorageId;
  }

  /**
   * Get current StorageDir's under file system
   * 
   * @return StorageDir's under file system
   */
  public UnderFileSystem getUfs() {
    return mUfs;
  }

  /**
   * Get configuration of current StorageDir's under file system
   * 
   * @return configuration of the under file system
   */
  public Object getUfsConf() {
    return mUfsConf;
  }

  /**
   * Get used space on current StorageDir
   * 
   * @return used space on current StorageDir
   */
  public long getUsed() {
    return mSpaceCounter.getUsedBytes();
  }

  /**
   * Get temp file path of block file written by some user
   * 
   * @param userId
   *          id of the user
   * @param blockId
   *          id of the block
   * @return path of the temp file
   */
  public String getUserTempFilePath(long userId, long blockId) {
    return CommonUtils.concat(mUserTempPath, userId, blockId);
  }

  /**
   * Get temp folder path of some user
   * 
   * @param userId
   *          id of the user
   * @return path of the temp folder
   */
  public String getUserTempPath(long userId) {
    return CommonUtils.concat(mUserTempPath, userId);
  }

  /**
   * Initialize current StorageDir
   * 
   * @throws IOException
   */
  public void initailize() throws IOException {
    if (!mUfs.exists(mDataPath)) {
      LOG.info("Local folder " + mDataPath + " does not exist. Creating a new one.");
      mUfs.mkdirs(mDataPath, true);
      mUfs.mkdirs(mUserTempPath, true);
      mUfs.setPermission(mDataPath, "775");
      mUfs.setPermission(mUserTempPath, "775");
      return;
    }

    if (mUfs.isFile(mDataPath)) {
      String msg = "Data folder " + mDataPath + " is not a folder!";
      throw new IllegalArgumentException(msg);
    }

    int cnt = 0;
    for (String name : mUfs.list(mDataPath)) {
      String path = CommonUtils.concat(mDataPath, name);
      if (mUfs.isFile(path)) {
        cnt ++;
        long fileSize = mUfs.getFileSize(path);
        LOG.info("File " + cnt + ": " + path + " with size " + fileSize + " Bs.");
        long blockId = CommonUtils.getBlockIdFromFileName(name);
        boolean success = requestSpace(fileSize);
        if (success) {
          addBlockId(blockId, fileSize);
        } else {
          mUfs.delete(path, true);
          throw new RuntimeException("Pre-existing files exceed the local storage capacity.");
        }
      }
    }
    return;
  }

  /**
   * Check whether block is locked by some user
   * 
   * @param blockId
   *          id of the block
   * @return true if block is locked, false otherwise
   */
  public boolean isBlockLocked(long blockId) {
    synchronized (mUserPerLockedBlock) {
      if (mUserPerLockedBlock.containsKey(blockId)) {
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Lock block by some user
   * 
   * @param blockId
   *          id of the block
   * @param userId
   *          id of the user
   */
  public void lockBlock(long blockId, long userId) {
    if (!containsBlock(blockId)) {
      return;
    }
    synchronized (mUserPerLockedBlock) {
      mUserPerLockedBlock.put(blockId, userId);
    }
    synchronized (mLockedBlocksPerUser) {
      mLockedBlocksPerUser.put(userId, blockId);
    }
  }

  /**
   * Move block from current StorageDir to another StorageDir
   * 
   * @param blockId
   *          id of the block
   * @param dstDir
   *          destination StorageDir
   * @return true if success, false otherwise
   * @throws IOException
   */
  public boolean moveBlock(long blockId, StorageDir dstDir) throws IOException {
    boolean copySuccess = copyBlock(blockId, dstDir);
    if (copySuccess) {
      return deleteBlock(blockId);
    } else {
      return false;
    }
  }

  /**
   * Allocate space from current StorageDir
   * 
   * @param size
   *          request size
   * @return true if success, false otherwise
   */
  public boolean requestSpace(long size) {
    return mSpaceCounter.requestSpaceBytes(size);
  }

  /**
   * Allocate space from current StorageDir
   * 
   * @param userId
   *          id of the user
   * @param size
   *          request size
   * @return true if success, false otherwise
   */
  public boolean requestSpace(long userId, long size) {
    boolean result = requestSpace(size);
    if (result) {
      synchronized (mUserAllocatedSpace) {
        if (mUserAllocatedSpace.containsKey(userId)) {
          long current = mUserAllocatedSpace.get(userId);
          mUserAllocatedSpace.put(userId, current + size);
        } else {
          mUserAllocatedSpace.put(userId, size);
        }
      }
    }
    return result;
  }

  /**
   * Return space to current StorageDir
   * 
   * @param size
   *          size to return
   */
  public void returnSpace(long size) {
    mSpaceCounter.returnUsedBytes(size);
  }

  /**
   * Return space to current StorageDir by some user
   * 
   * @param userId
   *          id of the user
   * @param size
   *          size to return
   */
  public void returnSpace(long userId, long size) {
    returnSpace(size);
    synchronized (mUserAllocatedSpace) {
      if (mUserAllocatedSpace.containsKey(userId)) {
        long current = mUserAllocatedSpace.get(userId);
        mUserAllocatedSpace.put(userId, current - size);
      } else {
        LOG.warn("Error during returning space: unknown user ID.");
      }
    }
  }

  /**
   * Unlock block by some user
   * 
   * @param blockId
   *          id of the block
   * @param userId
   *          id of the user
   */
  public void unlockBlock(long blockId, long userId) {
    if (!containsBlock(blockId)) {
      return;
    }
    synchronized (mUserPerLockedBlock) {
      mUserPerLockedBlock.removeAll(blockId);
    }
    synchronized (mLockedBlocksPerUser) {
      mLockedBlocksPerUser.remove(userId, blockId);
    }
  }
}
