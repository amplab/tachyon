package tachyon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tachyon.thrift.ClientBlockInfo;
import tachyon.thrift.NetAddress;

/**
 * Block info on the master side.
 */
public class BlockInfo {
  private final InodeFile INODE_FILE;

  public final int BLOCK_INDEX;
  public final long BLOCK_ID;
  public final long OFFSET;
  public final long LENGTH;

  private Map<Long, NetAddress> mLocations = new HashMap<Long, NetAddress>(5);

  /**
   * @param inodeFile
   * @param blockIndex
   * @param length Can not be no bigger than 2^31 - 1
   */
  BlockInfo(InodeFile inodeFile, int blockIndex, long length) {
    INODE_FILE = inodeFile;
    BLOCK_INDEX = blockIndex;
    BLOCK_ID = computeBlockId(INODE_FILE.getId(), BLOCK_INDEX);
    OFFSET = (long) inodeFile.getBlockSizeByte() * blockIndex;
    LENGTH = length;
  }

  public synchronized void addLocation(long workerId, NetAddress workerAddress) {
    mLocations.put(workerId, workerAddress);
  }

  public synchronized InodeFile getInodeFile() {
    return INODE_FILE;
  }

  public synchronized List<NetAddress> getLocations() {
    List<NetAddress> ret = new ArrayList<NetAddress>(mLocations.size());
    ret.addAll(mLocations.values());
    if (ret.isEmpty() && INODE_FILE.hasCheckpointed()) {
      UnderFileSystem ufs = UnderFileSystem.get(INODE_FILE.getCheckpointPath());
      List<String> locs = null;
      try {
        locs = ufs.getFileLocations(INODE_FILE.getCheckpointPath(), OFFSET);
      } catch (IOException e) {
        return ret;
      }
      if (locs != null) {
        for (String loc: locs) {
          ret.add(new NetAddress(loc, -1));
        }
      }
    }
    return ret;
  }

  public synchronized ClientBlockInfo generateClientBlockInfo() {
    ClientBlockInfo ret = new ClientBlockInfo();

    ret.blockId = BLOCK_ID;
    ret.offset = OFFSET;
    ret.length = LENGTH;
    ret.locations = getLocations();

    return ret;
  }

  public synchronized List<Pair<Long, Long>> getBlockIdWorkerIdPairs() {
    List<Pair<Long, Long>> ret = new ArrayList<Pair<Long, Long>>(mLocations.size());
    for (long workerId: mLocations.keySet()) {
      ret.add(new Pair<Long, Long>(BLOCK_ID, workerId));
    }
    return ret;
  }

  public synchronized boolean isInMemory() {
    return mLocations.size() > 0;
  }

  public synchronized void removeLocation(long workerId) {
    mLocations.remove(workerId);
  }

  public synchronized String toString() {
    StringBuilder sb = new StringBuilder("BlockInfo(BLOCK_INDEX: ");
    sb.append(BLOCK_INDEX);
    sb.append(", BLOCK_ID: ").append(BLOCK_ID);
    sb.append(", OFFSET: ").append(OFFSET);
    sb.append(", LENGTH: ").append(LENGTH);
    sb.append(", mLocations: ").append(mLocations).append(")");
    return sb.toString();
  }

  public static long computeBlockId(int inodeId, int blockIndex) {
    return ((long) inodeId << 30) + blockIndex;
  }

  public static int computeBlockIndex(long blockId) {
    return (int) (blockId & 0x3fffffff);
  }

  public static int computeInodeId(long blockId) {
    return (int) (blockId >> 30);
  }
}
