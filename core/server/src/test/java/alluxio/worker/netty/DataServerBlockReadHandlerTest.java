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

package alluxio.worker.netty;

import alluxio.Configuration;
import alluxio.Constants;
import alluxio.PropertyKey;
import alluxio.network.protocol.RPCProtoMessage;
import alluxio.network.protocol.databuffer.DataBuffer;
import alluxio.network.protocol.databuffer.DataFileChannel;
import alluxio.network.protocol.databuffer.DataNettyBufferV2;
import alluxio.proto.dataserver.Protocol;
import alluxio.util.CommonUtils;
import alluxio.util.io.BufferUtils;
import alluxio.worker.block.BlockWorker;
import alluxio.worker.block.io.BlockReader;
import alluxio.worker.block.io.LocalFileBlockReader;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Random;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BlockWorker.class})
public final class DataServerBlockReadHandlerTest {
  private static final long PACKET_SIZE =
      Configuration.getBytes(PropertyKey.WORKER_NETWORK_NETTY_READER_PACKET_SIZE_BYTES);
  private final Random mRandom = new Random();
  private final long mBlockId = 1L;

  private BlockWorker mBlockWorker;
  private BlockReader mBlockReader;
  private String mFile;
  private long mChecksum;
  private EmbeddedChannel mChannel;

  @Rule
  public TemporaryFolder mTestFolder = new TemporaryFolder();

  @Before
  public void before() throws Exception {
    mBlockWorker = PowerMockito.mock(BlockWorker.class);
    PowerMockito.doNothing().when(mBlockWorker).accessBlock(Mockito.anyLong(), Mockito.anyLong());
    mChannel = new EmbeddedChannel(
        new DataServerBlockReadHandler(NettyExecutors.BLOCK_READER_EXECUTOR, mBlockWorker,
            FileTransferType.MAPPED));
  }

  @Test
  public void readFullFile() throws Exception {
    populateInputFile(PACKET_SIZE * 10, 0, PACKET_SIZE * 10 - 1);
    mChannel.writeInbound(buildReadRequest(0, PACKET_SIZE * 10));
    checkAllReadResponses();
  }

  @Test
  public void readPartialFile() throws Exception {
    long start = 3;
    long end = PACKET_SIZE * 10 - 99;
    populateInputFile(PACKET_SIZE * 10, start, end);
    mChannel.writeInbound(buildReadRequest(start, end + 1 - start));
    checkAllReadResponses();
  }

  @Test
  public void reuseChannel() throws Exception {
    long fileSize = PACKET_SIZE * 5;
    populateInputFile(fileSize, 0, fileSize - 1);
    mChannel.writeInbound(buildReadRequest(0, fileSize));
    checkAllReadResponses();

    fileSize = fileSize / 2 + 1;
    long start = 3;
    long end = fileSize - 1;
    populateInputFile(fileSize, start, end);
    mChannel.writeInbound(buildReadRequest(start, end - start + 1));
    checkAllReadResponses();
  }

  @Test
  public void transferType() throws Exception {
    mChannel = new EmbeddedChannel(
        new DataServerBlockReadHandler(NettyExecutors.BLOCK_READER_EXECUTOR, mBlockWorker,
            FileTransferType.TRANSFER));

    long fileSize = PACKET_SIZE * 2;
    populateInputFile(fileSize, 0, fileSize - 1);

    BlockReader blockReader = PowerMockito.spy(mBlockReader);
    // Do not call close so that we can check result. It will be closed explicitly.
    PowerMockito.doNothing().when(blockReader).close();
    PowerMockito
        .when(mBlockWorker.readBlockRemote(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
        .thenReturn(blockReader);
    mChannel.writeInbound(buildReadRequest(0, fileSize));
    checkAllReadResponses();
    mBlockReader.close();
  }

  @Test
  public void readEmptyFile() throws Exception {
    populateInputFile(0, 0, 0);
    mChannel.writeInbound(buildReadRequest(0, 0));
    checkAllReadResponses();
  }


  /*
  @Test
  public void writeInvalidOffset() throws Exception {
    final EmbeddedChannel channel = new EmbeddedChannel(
        new DataServerBlockWriteHandler(NettyExecutors.BLOCK_WRITER_EXECUTOR, mBlockWorker));
    channel.writeInbound(buildWriteRequest(0, 1024));
    channel.writeInbound(buildWriteRequest(1025, 1024));
    Object writeResponse = waitForResponse(channel);
    Assert.assertTrue(writeResponse instanceof RPCProtoMessage);
    checkWriteResponse(writeResponse, Protocol.Status.Code.INVALID_ARGUMENT);
    waitForChannelClose(channel);
    Assert.assertTrue(!channel.isOpen());
  }

  @Test
  public void writeFailure() throws Exception {
    final EmbeddedChannel channel = new EmbeddedChannel(
        new DataServerBlockWriteHandler(NettyExecutors.BLOCK_WRITER_EXECUTOR, mBlockWorker));
    channel.writeInbound(buildWriteRequest(0, 1024));
    mBlockWriter.close();
    channel.writeInbound(buildWriteRequest(1024, 1024));
    Object writeResponse = waitForResponse(channel);
    Assert.assertTrue(writeResponse instanceof RPCProtoMessage);
    checkWriteResponse(writeResponse, Protocol.Status.Code.INTERNAL);
    waitForChannelClose(channel);
    Assert.assertTrue(!channel.isOpen());
  }
  */

  private void populateInputFile(long length, long start, long end) throws Exception {
    mChecksum = 0;
    File file = mTestFolder.newFile();
    long pos = 0;
    if (length > 0) {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      while (length > 0) {
        byte[] buffer = new byte[(int) Math.min(length, Constants.MB)];
        mRandom.nextBytes(buffer);
        for (int i = 0; i < buffer.length; i++) {
          if (pos >= start && pos <= end) {
            mChecksum += BufferUtils.byteToInt(buffer[i]);
          }
          pos++;
        }
        fileOutputStream.write(buffer);
        length -= buffer.length;
      }
      fileOutputStream.close();
    }

    mFile = file.getPath();
    mBlockReader = new LocalFileBlockReader(mFile);
    PowerMockito
        .when(mBlockWorker.readBlockRemote(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
        .thenReturn(mBlockReader);
  }

  private RPCProtoMessage buildReadRequest(long offset, long len) {
    Protocol.ReadRequest readRequest =
        Protocol.ReadRequest.newBuilder().setId(mBlockId).setOffset(offset).setSessionId(1L)
            .setLength(len).setLockId(1L).setType(Protocol.RequestType.ALLUXIO_BLOCK).build();
    return new RPCProtoMessage(readRequest, null);
  }

  private void checkAllReadResponses() {
    int timeRemaining = Constants.MINUTE_MS;
    boolean EOF = false;
    long checksumActual = 0;
    while (!EOF && timeRemaining > 0) {
      Object readResponse = null;
      while (readResponse == null && timeRemaining > 0) {
        readResponse = mChannel.readOutbound();
        CommonUtils.sleepMs(10);
        timeRemaining -= 10;
      }
      DataBuffer buffer = checkReadResponse(readResponse, Protocol.Status.Code.OK);
      EOF = buffer == null;
      if (buffer != null) {
        if (buffer instanceof DataNettyBufferV2) {
          ByteBuf buf = (ByteBuf) buffer.getNettyOutput();
          while (buf.readableBytes() > 0) {
            checksumActual += BufferUtils.byteToInt(buf.readByte());
          }
          buf.release();
        } else {
          Assert.assertTrue(buffer instanceof DataFileChannel);
          ByteBuffer buf = buffer.getReadOnlyByteBuffer();
          byte[] array = new byte[buf.remaining()];
          buf.get(array);
          for (int i = 0; i < array.length; i++) {
            checksumActual += BufferUtils.byteToInt(array[i]);
          }
        }
      }
    }
    Assert.assertEquals(mChecksum, checksumActual);
    Assert.assertTrue(EOF);
  }

  private DataBuffer checkReadResponse(Object readResponse, Protocol.Status.Code codeExpected) {
    Assert.assertTrue(readResponse instanceof RPCProtoMessage);

    Object response = ((RPCProtoMessage) readResponse).getMessage();
    Assert.assertTrue(response instanceof Protocol.Response);
    Assert.assertEquals(codeExpected, ((Protocol.Response) response).getStatus().getCode());
    return ((RPCProtoMessage) readResponse).getPayloadDataBuffer();
  }

  private void waitForChannelClose(EmbeddedChannel channel) {
    int timeRemaining = Constants.MINUTE_MS;
    while (timeRemaining > 0 && channel.isOpen()) {}
  }
}
