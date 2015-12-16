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

package tachyon.worker.block;

import java.io.IOException;
import java.util.concurrent.Executors;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import tachyon.Constants;
import tachyon.thrift.KeyValueWorkerService;
import tachyon.worker.file.FileSystemMasterClient;
import tachyon.conf.TachyonConf;
import tachyon.exception.ConnectionFailedException;
import tachyon.metrics.MetricsSystem;
import tachyon.security.authentication.AuthenticationUtils;
import tachyon.thrift.NetAddress;
import tachyon.thrift.WorkerService;
import tachyon.util.CommonUtils;
import tachyon.util.ThreadFactoryUtils;
import tachyon.util.network.NetworkAddressUtils;
import tachyon.util.network.NetworkAddressUtils.ServiceType;
import tachyon.web.UIWebServer;
import tachyon.web.WorkerUIWebServer;
import tachyon.worker.DataServer;
import tachyon.worker.WorkerBase;
import tachyon.worker.WorkerContext;
import tachyon.worker.WorkerIdRegistry;
import tachyon.worker.WorkerSource;
import tachyon.worker.keyvalue.KeyValueServiceHandler;

/**
 * The class is responsible for managing all top level components of the Block Worker, including:
 *
 * Servers: BlockServiceHandler (RPC Server), BlockDataServer (Data Server)
 *
 * Periodic Threads: BlockMasterSync (Worker to Master continuous communication)
 *
 * Logic: BlockDataManager (Logic for all block related storage operations)
 */
public final class BlockWorker extends WorkerBase {
  private static final Logger LOG = LoggerFactory.getLogger(Constants.LOGGER_TYPE);

  /** Runnable responsible for heartbeating and registration with master. */
  private final BlockMasterSync mBlockMasterSync;
  /** Runnable responsible for fetching pinlist from master. */
  private final PinListSync mPinListSync;
  /** Runnable responsible for clean up potential zombie sessions. */
  private final SessionCleaner mSessionCleanerThread;
  /** Logic for handling block RPC requests. */
  private final BlockServiceHandler mBlockServiceHandler;
  /** Logic for handling key-value RPC requests. */
  private final KeyValueServiceHandler mKeyValueServiceHandler;
  /** Logic for managing block store and under file system store. */
  private final BlockDataManager mBlockDataManager;
  /** Server for data requests and responses. */
  private final DataServer mDataServer;
  /** Client for all block master communication */
  private final BlockMasterClient mBlockMasterClient;
  /** Client for all file system master communication */
  private final FileSystemMasterClient mFileSystemMasterClient;
  /** Net address of this worker */
  private final NetAddress mWorkerNetAddress;
  /** Configuration object */
  private final TachyonConf mTachyonConf;
  /** Server socket for thrift */
  private final TServerSocket mThriftServerSocket;
  /** RPC local port for thrift */
  private final int mPort;
  /** Thread pool for thrift */
  private final TThreadPoolServer mThriftServer;
  /** Worker start time in milliseconds */
  private final long mStartTimeMs;
  /** Worker Web UI server */
  private final UIWebServer mWebServer;
  /** Worker metrics system */
  private MetricsSystem mWorkerMetricsSystem;
  /** Space reserver for the block data manager */
  private SpaceReserver mSpaceReserver = null;

  /**
   * @return the block data manager for other worker services
   */
  public BlockDataManager getBlockDataManager() {
    return mBlockDataManager;
  }

  /**
   * TODO(binfan): refactor this test-only method outside main class
   * @return the worker service handler (test only)
   */
  public BlockServiceHandler getWorkerServiceHandler() {
    return mBlockServiceHandler;
  }

  /**
   * @return the worker RPC service bind host
   */
  public String getRPCBindHost() {
    return NetworkAddressUtils.getThriftSocket(mThriftServerSocket).getInetAddress()
        .getHostAddress();
  }

  /**
   * @return the worker RPC service port
   */
  public int getRPCLocalPort() {
    return mPort;
  }

  /**
   * @return the worker data service bind host
   */
  public String getDataBindHost() {
    return mDataServer.getBindHost();
  }

  /**
   * @return the worker data service port
   */
  public int getDataLocalPort() {
    return mDataServer.getPort();
  }

  /**
   * @return the worker web service bind host
   */
  public String getWebBindHost() {
    return mWebServer.getBindHost();
  }

  /**
   * @return the worker web service port
   */
  public int getWebLocalPort() {
    return mWebServer.getLocalPort();
  }

  /**
   * Creates a Tachyon Block Worker.
   *
   * @throws IOException for other exceptions
   * @throws ConnectionFailedException if network connection failed
   */
  public BlockWorker() throws IOException, ConnectionFailedException {
    super(Executors.newFixedThreadPool(4,
        ThreadFactoryUtils.build("block-worker-heartbeat-%d", true)));
    mTachyonConf = WorkerContext.getConf();
    mStartTimeMs = System.currentTimeMillis();

    // Setup MasterClientBase
    mBlockMasterClient = new BlockMasterClient(
        NetworkAddressUtils.getConnectAddress(ServiceType.MASTER_RPC, mTachyonConf), mTachyonConf);

    mFileSystemMasterClient = new FileSystemMasterClient(
        NetworkAddressUtils.getConnectAddress(ServiceType.MASTER_RPC, mTachyonConf), mTachyonConf);

    // Set up BlockDataManager
    WorkerSource workerSource = new WorkerSource();
    mBlockDataManager =
        new BlockDataManager(workerSource, mBlockMasterClient, mFileSystemMasterClient,
            new TieredBlockStore());

    // Setup metrics collection
    mWorkerMetricsSystem = new MetricsSystem("worker", mTachyonConf);
    workerSource.registerGauges(mBlockDataManager);
    mWorkerMetricsSystem.registerSource(workerSource);

    // Set up DataServer
    mDataServer =
        DataServer.Factory.createDataServer(
            NetworkAddressUtils.getBindAddress(ServiceType.WORKER_DATA, mTachyonConf),
            mBlockDataManager, mTachyonConf);
    // reset data server port
    mTachyonConf.set(Constants.WORKER_DATA_PORT, Integer.toString(mDataServer.getPort()));

    // Setup RPC Server
    // TODO(binfan): move RPC setup from BlockWorker to TachyonWorker, following the same
    // pattern as in TachyonMaster
    mBlockServiceHandler = new BlockServiceHandler(mBlockDataManager);
    mKeyValueServiceHandler = new KeyValueServiceHandler(mBlockDataManager);
    mThriftServerSocket = createThriftServerSocket();
    mPort = NetworkAddressUtils.getThriftPort(mThriftServerSocket);
    // reset worker RPC port
    mTachyonConf.set(Constants.WORKER_RPC_PORT, Integer.toString(mPort));
    mThriftServer = createThriftServer();
    mWorkerNetAddress =
        new NetAddress(NetworkAddressUtils.getConnectHost(ServiceType.WORKER_RPC, mTachyonConf),
            mPort, mDataServer.getPort());
    // Get the worker id
    WorkerIdRegistry.registerWithBlockMaster(mBlockMasterClient, mWorkerNetAddress);

    // Set up web server
    mWebServer =
        new WorkerUIWebServer(ServiceType.WORKER_WEB, NetworkAddressUtils.getBindAddress(
            ServiceType.WORKER_WEB, mTachyonConf), mBlockDataManager,
            NetworkAddressUtils.getConnectAddress(ServiceType.WORKER_RPC, mTachyonConf),
            mStartTimeMs, mTachyonConf);

    mBlockMasterSync = new BlockMasterSync(mBlockDataManager, mWorkerNetAddress,
        mBlockMasterClient);

    // Setup PinListSyncer
    mPinListSync = new PinListSync(mBlockDataManager, mFileSystemMasterClient);

    // Setup session cleaner
    mSessionCleanerThread = new SessionCleaner(mBlockDataManager);

    // Setup space reserver
    if (mTachyonConf.getBoolean(Constants.WORKER_TIERED_STORE_RESERVER_ENABLED)) {
      mSpaceReserver = new SpaceReserver(mBlockDataManager);
    }
  }

  /**
   * Gets this worker's {@link tachyon.thrift.NetAddress}, which is the worker's hostname, rpc
   * server port, and data server port
   *
   * @return the worker's net address
   */
  public NetAddress getWorkerNetAddress() {
    return mWorkerNetAddress;
  }

  /**
   * Runs the block worker. The thread calling this will be blocked until the thrift server shuts
   * down.
   */
  public void process() {
    mWorkerMetricsSystem.start();

    // Add the metrics servlet to the web server, this must be done after the metrics system starts
    mWebServer.addHandler(mWorkerMetricsSystem.getServletHandler());

    getExecutorService().submit(mBlockMasterSync);

    // Start the pinlist syncer to perform the periodical fetching
    getExecutorService().submit(mPinListSync);

    // Start the session cleanup checker to perform the periodical checking
    getExecutorService().submit(mSessionCleanerThread);

    // Start the space reserver
    if (mSpaceReserver != null) {
      getExecutorService().submit(mSpaceReserver);
    }

    mWebServer.startWebServer();
    mThriftServer.serve();
  }

  /**
   * Stops the block worker. This method should only be called to terminate the worker.
   *
   * @throws IOException if the data server fails to close
   */
  public void stop() throws IOException {
    mDataServer.close();
    mThriftServer.stop();
    mThriftServerSocket.close();
    mBlockMasterSync.stop();
    mPinListSync.stop();
    mSessionCleanerThread.stop();
    mBlockMasterClient.close();
    if (mSpaceReserver != null) {
      mSpaceReserver.stop();
    }
    mFileSystemMasterClient.close();
    getExecutorService().shutdown();
    mWorkerMetricsSystem.stop();
    try {
      mWebServer.shutdownWebServer();
    } catch (Exception e) {
      LOG.error("Failed to stop web server", e);
    }
    mBlockDataManager.stop();
    while (!mDataServer.isClosed() || mThriftServer.isServing()) {
      // The reason to stop and close again is due to some issues in Thrift.
      mDataServer.close();
      mThriftServer.stop();
      mThriftServerSocket.close();
      CommonUtils.sleepMs(100);
    }
  }

  /**
   * Helper method to create a {@link org.apache.thrift.server.TThreadPoolServer} for handling
   * incoming RPC requests.
   *
   * @return a thrift server
   */
  private TThreadPoolServer createThriftServer() {
    int minWorkerThreads = mTachyonConf.getInt(Constants.WORKER_WORKER_BLOCK_THREADS_MIN);
    int maxWorkerThreads = mTachyonConf.getInt(Constants.WORKER_WORKER_BLOCK_THREADS_MAX);
    // set up multiplexed thrift processors
    // TODO(binfan): move this logic outside BlockWorker but to TachyonWorker
    TMultiplexedProcessor processor = new TMultiplexedProcessor();
    // TODO(binfan): make names returned from corresponding worker
    processor.registerProcessor("BlockWorker", new WorkerService.Processor<BlockServiceHandler>
        (mBlockServiceHandler));
    processor.registerProcessor("KeyValueWorker", new KeyValueWorkerService
        .Processor<KeyValueServiceHandler>
        (mKeyValueServiceHandler));

    // Return a TTransportFactory based on the authentication type
    TTransportFactory tTransportFactory;
    try {
      tTransportFactory = AuthenticationUtils.getServerTransportFactory(mTachyonConf);
    } catch (IOException ioe) {
      throw Throwables.propagate(ioe);
    }
    Args args = new TThreadPoolServer.Args(mThriftServerSocket).minWorkerThreads(minWorkerThreads)
        .maxWorkerThreads(maxWorkerThreads).processor(processor).transportFactory(tTransportFactory)
        .protocolFactory(new TBinaryProtocol.Factory(true, true));
    args.stopTimeoutVal = WorkerContext.getConf().getInt(Constants.THRIFT_STOP_TIMEOUT_SECONDS);
    return new TThreadPoolServer(args);
  }

  /**
   * Helper method to create a {@link org.apache.thrift.transport.TServerSocket} for the RPC server
   *
   * @return a thrift server socket
   */
  private TServerSocket createThriftServerSocket() {
    try {
      return new TServerSocket(NetworkAddressUtils.getBindAddress(ServiceType.WORKER_RPC,
          mTachyonConf));
    } catch (TTransportException tte) {
      LOG.error(tte.getMessage(), tte);
      throw Throwables.propagate(tte);
    }
  }
}
