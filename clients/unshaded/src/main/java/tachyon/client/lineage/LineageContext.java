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

package tachyon.client.lineage;

import tachyon.client.ClientContext;

/**
 * A shared context in each client JVM for common lineage master client functionality such as a pool
 * of lineage master clients. Any remote clients will be created and destroyed on a per use basis.
 * This class is thread safe.
 */
public enum LineageContext {
  INSTANCE;

  private LineageMasterClientPool mLineageMasterClientPool;

  /**
   * Creates a new lineage context.
   */
  LineageContext() {
    mLineageMasterClientPool = new LineageMasterClientPool(ClientContext.getMasterAddress());
  }

  /**
   * Acquires a lineage master client from the lineage master client pool.
   *
   * @return the acquired lineage master client
   */
  public LineageMasterClient acquireMasterClient() {
    return mLineageMasterClientPool.acquire();
  }

  /**
   * Releases a lineage master client into the lineage master client pool.
   *
   * @param masterClient a lineage master client to release
   */
  public void releaseMasterClient(LineageMasterClient masterClient) {
    mLineageMasterClientPool.release(masterClient);
  }
}
