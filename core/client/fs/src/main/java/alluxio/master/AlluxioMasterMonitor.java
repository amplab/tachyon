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

package alluxio.master;

import alluxio.AlluxioURI;
import alluxio.client.file.FileSystem;
import alluxio.util.ShellUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Alluxio master monitor for inquiring RPC service availability.
 */
public final class AlluxioMasterMonitor implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(AlluxioMasterMonitor.class);

  private Thread mMasterServingThread;
  private Thread mMastersHealthCheckThread;
  private ScheduledExecutorService mExecutorService = Executors.newScheduledThreadPool(2);

  /**
   * Creates a new Alluxio master monitor.
   */
  public AlluxioMasterMonitor() {
    mMasterServingThread = new Thread(() -> {
      try {
        LOG.debug("Checking master is serving requests");
        FileSystem fs = FileSystem.Factory.get();
        fs.exists(new AlluxioURI("/"));
        LOG.debug("Master is serving requests");
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }
    }, "MasterServingThread");

    mMastersHealthCheckThread = new Thread(() -> {
      MasterInquireClient client = MasterInquireClient.Factory.create();
      try {
        List<InetSocketAddress> addresses = client.getMasterRpcAddresses();
        for (InetSocketAddress address : addresses) {
          String host = address.getHostName();
          LOG.debug("Master health check on node {}", host);
          String cmd = String.format("ssh %s %s %s", ShellUtils.COMMON_SSH_OPTS, host,
              "ps -ef | "
              + "grep -v \"alluxio.master.AlluxioMasterMonitor\" | "
              + "grep \"alluxio.master.AlluxioMaster\" | "
              + "grep \"java\" | "
              + "awk '{ print $2; }'");
          LOG.debug("Executing: {}", cmd);
          String output = ShellUtils.execCommand("bash", "-c", cmd);
          if (output.isEmpty()) {
            throw new IllegalStateException(
                    String.format("Master process is not running on the host %s", host));
          }
          LOG.debug("Master running on node {} with pid={}", host, output);
        }
      } catch (Throwable e) {
        if (!mExecutorService.isShutdown()) {
          mExecutorService.shutdownNow();
        }
        throw new RuntimeException(e);
      }
    }, "MastersHealthCheckThread");
  }

  @Override
  public void run() {
    try {
      mExecutorService.scheduleAtFixedRate(mMastersHealthCheckThread, 0, 5,
              TimeUnit.SECONDS);
      Future<?> future = mExecutorService.submit(mMasterServingThread);
      future.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (!mExecutorService.isShutdown()) {
        mExecutorService.shutdown();
      }
    }
  }

  /**
   * Starts the Alluxio master monitor.
   *
   * @param args command line arguments, should be empty
   */
  public static void main(String[] args) {
    AlluxioMasterMonitor monitor = new AlluxioMasterMonitor();
    try {
      monitor.run();
    } catch (Exception e) {
      LOG.error("Exception thrown in master monitor {}", e);
      System.exit(1);
    }
    System.exit(0);
  }
}
