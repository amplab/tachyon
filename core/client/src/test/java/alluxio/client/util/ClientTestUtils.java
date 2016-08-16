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

package alluxio.client.util;

import alluxio.Configuration;
import alluxio.PropertyKey;
import alluxio.client.ClientContext;
import alluxio.client.block.BlockStoreContext;
import alluxio.client.file.FileSystemContext;
import alluxio.client.lineage.LineageContext;
import alluxio.hadoop.HadoopClientTestUtils;

import com.google.common.base.Throwables;

/**
 * Utility methods for the client tests.
 */
public final class ClientTestUtils {

  /**
   * Sets small buffer sizes so that Alluxio does not run out of heap space.
   */
  public static void setSmallBufferSizes() {
    Configuration.set(PropertyKey.USER_BLOCK_REMOTE_READ_BUFFER_SIZE_BYTES, "4KB");
    Configuration.set(PropertyKey.USER_FILE_BUFFER_BYTES, "4KB");
  }

  /**
   * Resets the client to its initial state, re-initializing Alluxio and Hadoop contexts.
   *
   * This method should only be used as a cleanup mechanism between tests. It should not be used
   * while any object may be using the {@link ClientContext}.
   */
  public static void resetClient() {
    try {
      HadoopClientTestUtils.resetHadoopClientContext();
      ClientContext.init();
      resetContexts();
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }

  private static void resetContexts() {
    BlockStoreContext.INSTANCE.reset();
    FileSystemContext.INSTANCE.reset();
    LineageContext.INSTANCE.reset();
  }
}
