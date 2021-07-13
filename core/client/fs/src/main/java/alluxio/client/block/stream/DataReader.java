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

package alluxio.client.block.stream;

import alluxio.network.protocol.databuffer.DataBuffer;

import java.io.Closeable;
import java.io.IOException;

/**
 * The interface to read data chunks.
 */
public interface DataReader extends Closeable {

  /**
   * The data reader type represents different ways to read data.
   */
  enum DataReaderType {
    /**
     * Read from a worker in the same process.
     * This is used by fuse client in the worker process.
     */
    BLOCK_WORKER,
    /**
     * Reads from a worker through gRPC.
     */
    GRPC,
    /**
     * Reads from a local block directly.
     * Usually used when the client and worker are on the same node but in the different processes.
     */
    SHORT_CIRCUIT,
    /**
     * Shared gRPC data reader that cache blocks data for multi-thread accessing.
     */
    SHARED,
    TEST // Mock data reader for testing
  }

  /**
   * Reads a chunk. The caller needs to release the chunk.
   *
   * @return the data buffer or null if EOF is reached
   */
  DataBuffer readChunk() throws IOException;

  /**
   * @return the current stream position
   */
  long pos();

  /**
   * The factory interface to create {@link DataReader}s.
   */
  interface Factory extends Closeable {
    /**
     * Creates an instance of {@link DataReader}.
     *
     * @param offset the stream offset
     * @param len the length of the stream
     * @return the created object
     */
    DataReader create(long offset, long len) throws IOException;

    /**
     * @return the data reader type
     */
    DataReaderType getDataReaderType();
  }
}
