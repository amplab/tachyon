package tachyon.client;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.apache.log4j.Logger;

import tachyon.Constants;
import tachyon.conf.UserConf;

/**
 * <code>BlockOutStream</code> implementation of TachyonFile. This class is not client facing.
 */
public class BlockOutStream extends OutStream {
  private final Logger LOG = Logger.getLogger(Constants.LOGGER_TYPE);
  private final UserConf USER_CONF = UserConf.get();

  private final TachyonFS TFS;
  private final WriteType WRITE_TYPE;
  private final int FILE_ID;
  private final long BLOCK_ID;
  private final long BLOCK_OFFSET;
  private final long BLOCK_CAPACITY_BYTE;
  private final boolean PIN;

  private long mInFileBytes = 0;
  private long mWrittenBytes = 0;

  private String mLocalFilePath = null;
  private RandomAccessFile mLocalFile = null;
  private FileChannel mLocalFileChannel = null;

  private ByteBuffer mBuffer = null;

  private boolean mCanWrite = false;
  private boolean mClosed = false;
  private boolean mCancel = false;

  BlockOutStream(TachyonFS tfs, int fid, WriteType opType, long blockId, long blockOffset,
      long blockCapacityByte, boolean pin) throws IOException {
    if (!opType.isCache()) {
      throw new IOException("BlockOutStream only support WriteType.CACHE");
    }

    TFS = tfs;
    WRITE_TYPE = opType;
    FILE_ID = fid;
    BLOCK_ID = blockId;
    BLOCK_OFFSET = blockOffset;
    BLOCK_CAPACITY_BYTE = blockCapacityByte;
    PIN = pin;

    mCanWrite = true;

    if (!TFS.hasLocalWorker()) {
      mCanWrite = false;
      String msg = "The machine does not have any local worker.";
      if (WRITE_TYPE.isMustCache()) {
        throw new IOException(msg);
      }
      LOG.warn("The machine does not have any local worker.");
      return;
    }

    File localFolder = TFS.createAndGetUserTempFolder();
    if (localFolder == null) {
      mCanWrite = false;
      String msg = "Failed to create temp user folder for tachyon client.";
      if (WRITE_TYPE.isMustCache()) {
        throw new IOException(msg);
      }
      LOG.warn(msg);
      return;
    }

    mLocalFilePath = localFolder.getPath() + "/" + BLOCK_ID;
    mLocalFile = new RandomAccessFile(mLocalFilePath, "rw");
    mLocalFileChannel = mLocalFile.getChannel();
    LOG.info(mLocalFilePath + " was created!");

    mBuffer = ByteBuffer.allocate(USER_CONF.FILE_BUFFER_BYTES + 4);
  }

  private synchronized void appendCurrentBuffer(byte[] buf, int offset, 
      int length) throws IOException {
    if (!TFS.requestSpace(length)) {
      mCanWrite = false;

      String msg = "Local tachyon worker does not have enough " +
          "space (" + length + ") or no worker for " + FILE_ID + " " + BLOCK_ID;
      if (PIN) {
        TFS.outOfMemoryForPinFile(FILE_ID);
        throw new IOException(msg);
      }

      if (WRITE_TYPE.isMustCache()) {
        throw new IOException(msg);
      }
    }

    MappedByteBuffer out = 
        mLocalFileChannel.map(MapMode.READ_WRITE, mInFileBytes, length);
    out.put(buf, 0, length);
    mInFileBytes += length;
  }

  @Override
  public void write(int b) throws IOException {
    if (!mCanWrite) {
      throw new IOException("Can not write cache.");
    }
    if (mWrittenBytes + 1 > BLOCK_CAPACITY_BYTE) {
      throw new IOException("Out of capacity.");
    }

    if (mBuffer.position() >= USER_CONF.FILE_BUFFER_BYTES) {
      appendCurrentBuffer(mBuffer.array(), 0, mBuffer.position());
      mBuffer.clear();
    }

    mBuffer.put((byte) (b & 0xFF));
    mWrittenBytes ++;
  }

  @Override
  public void write(byte[] b) throws IOException {
    write(b, 0, b.length);
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    if (b == null) {
      throw new NullPointerException();
    } else if ((off < 0) || (off > b.length) || (len < 0) ||
        ((off + len) > b.length) || ((off + len) < 0)) {
      throw new IndexOutOfBoundsException();
    }

    if (!mCanWrite) {
      throw new IOException("Can not write cache.");
    }
    if (mWrittenBytes + len > BLOCK_CAPACITY_BYTE) {
      throw new IOException("Out of capacity.");
    }

    if (mBuffer.position() + len >= USER_CONF.FILE_BUFFER_BYTES) {
      if (mBuffer.position() > 0) {
        appendCurrentBuffer(mBuffer.array(), 0, mBuffer.position());
        mBuffer.clear();
      }

      if (len > 0) {
        appendCurrentBuffer(b, off, len);
      }
    } else {
      mBuffer.put(b, off, len);
    }

    mWrittenBytes += len;
  }

  @Override
  public void flush() throws IOException {
    throw new IOException("Not supported yet.");
  }

  @Override
  public void close() throws IOException {
    if (!mClosed) {
      if (!mCancel && mBuffer.position() > 0) {
        appendCurrentBuffer(mBuffer.array(), 0, mBuffer.position());
      }

      if (mLocalFileChannel != null) {
        mLocalFileChannel.close();
        mLocalFile.close();
      }

      if (mCancel) {
        TFS.releaseSpace(mWrittenBytes - mBuffer.position());
      } else {
        try {
          TFS.cacheBlock(BLOCK_ID);
        } catch (IOException e) {
          if (WRITE_TYPE == WriteType.CACHE) {
            throw e;
          }
        }
      }
    }
    mClosed = true;
  }

  @Override
  public void cancel() throws IOException {
    mCancel = true;
    close();
  }

  public boolean canWrite() {
    return !mClosed && mCanWrite;
  }

  public long getRemainingSpaceByte() {
    return BLOCK_CAPACITY_BYTE - mWrittenBytes;
  }

  public long getBlockId() {
    return BLOCK_ID;
  }
}