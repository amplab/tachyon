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

package tachyon.client.keyvalue;

import java.io.Closeable;
import java.io.IOException;

import com.google.common.base.Preconditions;

import tachyon.TachyonURI;
import tachyon.client.file.FileOutStream;
import tachyon.client.file.TachyonFileSystem;
import tachyon.exception.TachyonException;

/**
 * Interface of the writer to create a Tachyon key-value file.
 */
public interface KeyValueFileWriter extends Closeable {

  class Factory {
    /**
     * Factory method to create a {@link KeyValueFileWriter} instance that writes to a new the
     * key-value data in a new file in Tachyon.
     *
     * @param uri URI of the key-value file to write to
     * @return an instance of a {@link KeyValueFileWriter}
     * @throws TachyonException if error occurs
     * @throws IOException if error occurs
     */
    public static KeyValueFileWriter create(TachyonURI uri) throws TachyonException, IOException {
      Preconditions.checkNotNull(uri);
      TachyonFileSystem tfs = TachyonFileSystem.TachyonFileSystemFactory.get();
      FileOutStream fileOutStream = tfs.getOutStream(uri);
      return new OutStreamKeyValueFileWriter(fileOutStream);
    }
  }

  /**
   * Adds a key and the associated value to this writer.
   * TODO(binfan): throw already exists exception if key is already inserted.
   *
   * @param key key to put, cannot be null
   * @param value value to put, cannot be null
   * @throws IOException
   */
  void put(byte[] key, byte[] value) throws IOException;

  /**
   * Closes the writer.
   *
   * @throws IOException
   */
  void close() throws IOException;

  /**
   * @return number of keys
   */
  long keyCount();

  /**
   * @return number of bytes estimated
   */
  long byteCount();
}
