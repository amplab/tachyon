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

import tachyon.conf.TachyonConf;
import tachyon.master.MasterClient;
import tachyon.util.ThreadFactoryUtils;

import java.net.InetSocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockMasterClientPool {
  private final BlockingQueue<MasterClient> mClients;
  private final ExecutorService mExecutorService;

  public BlockMasterClientPool(InetSocketAddress masterAddress, TachyonConf conf) {
    // TODO: Get capacity from conf
    mClients = new LinkedBlockingQueue<MasterClient>(10);
    mExecutorService = Executors.newFixedThreadPool(10, ThreadFactoryUtils.build
        ("block-master-heartbeat-%d", true));

    // Initialize Clients
    for(int i = 0; i < mClients.size(); i++) {
      mClients.add(new MasterClient(masterAddress, mExecutorService, conf));
    }
  }

  public MasterClient acquire() throws InterruptedException {
    return mClients.take();
  }

  public void close() {
    // TODO: Consider collecting all the clients and shutting them down
    mExecutorService.shutdown();
  }

  public void release(MasterClient masterClient) {
    mClients.add(masterClient);
  }


}
