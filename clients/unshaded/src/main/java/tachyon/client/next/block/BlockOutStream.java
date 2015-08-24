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

package tachyon.client.next.block;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Provides a stream API to write a block to Tachyon. An instance of this class can be obtained by
 * calling {@link TachyonBS#getOutStream}. Only one BlockOutStream should be opened for a
 * block. This class is not thread safe and should only be used by one thread.
 *
 * The type of BlockOutStream returned will depend on the user configuration and cluster setup. A
 * {@link LocalBlockOutStream} is returned if the client is co-located with a Tachyon worker and
 * the user has enabled this optimization. Otherwise, a {@link RemoteBlockOutStream} will be
 * returned which will write the data through a Tachyon worker.
 */
public abstract class BlockOutStream extends OutputStream {
  /**
   * Cancels the write to Tachyon storage. This will delete all the temporary data and metadata
   * that has been written to the worker. This method should be called when a write is aborted or
   * if the write is not a complete write of the block.
   *
   * @throws IOException if there is a failure when the worker invalidates the cache attempt
   */
  public abstract void cancel() throws IOException;
}
