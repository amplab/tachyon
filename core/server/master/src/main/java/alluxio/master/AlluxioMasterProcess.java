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

import alluxio.Configuration;
import alluxio.Constants;
import alluxio.PropertyKey;
import alluxio.RuntimeConstants;
<<<<<<< HEAD
import alluxio.clock.SystemClock;
import alluxio.collections.IndexDefinition;
import alluxio.collections.IndexedSet;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.NoMasterException;
import alluxio.heartbeat.HeartbeatContext;
import alluxio.heartbeat.HeartbeatExecutor;
import alluxio.heartbeat.HeartbeatThread;
import alluxio.master.journal.JournalSystem;
import alluxio.master.journal.JournalSystem.Mode;
import alluxio.master.meta.MetaMasterClientServiceHandler;
import alluxio.master.meta.MetaMasterInfo;
import alluxio.master.meta.MetaMasterSync;
=======
import alluxio.master.block.BlockMaster;
import alluxio.master.journal.JournalSystem;
import alluxio.master.journal.JournalSystem.Mode;
import alluxio.master.meta.ServerConfigurationChecker;
>>>>>>> 6caae6e58cf1680d53867b5ab04e6f87241e8cbd
import alluxio.metrics.MetricsSystem;
import alluxio.metrics.sink.MetricsServlet;
import alluxio.metrics.sink.PrometheusMetricsServlet;
import alluxio.security.authentication.TransportProvider;
import alluxio.thrift.MetaMasterClientService;
import alluxio.thrift.RegisterMasterTOptions;
import alluxio.util.CommonUtils;
import alluxio.util.IdUtils;
import alluxio.util.JvmPauseMonitor;
import alluxio.util.WaitForOptions;
import alluxio.util.executor.ExecutorServiceFactories;
import alluxio.util.network.NetworkAddressUtils;
import alluxio.util.network.NetworkAddressUtils.ServiceType;
import alluxio.web.MasterWebServer;
import alluxio.web.WebServer;
import alluxio.wire.ConfigProperty;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * This class encapsulates the different master services that are configured to run.
 */
@NotThreadSafe
public class AlluxioMasterProcess implements MasterProcess {
  private static final Logger LOG = LoggerFactory.getLogger(AlluxioMasterProcess.class);

  // Master metadata management.
  private static final IndexDefinition<MetaMasterInfo> ID_INDEX =
      new IndexDefinition<MetaMasterInfo>(true) {
        @Override
        public Object getFieldValue(MetaMasterInfo o) {
          return o.getId();
        }
      };

  private static final IndexDefinition<MetaMasterInfo> HOSTNAME_INDEX =
      new IndexDefinition<MetaMasterInfo>(true) {
        @Override
        public Object getFieldValue(MetaMasterInfo o) {
          return o.getHostname();
        }
      };

  /** Maximum number of threads to serve the rpc server. */
  private final int mMaxWorkerThreads;

  /** Minimum number of threads to serve the rpc server. */
  private final int mMinWorkerThreads;

  /** The port for the RPC server. */
  private final int mPort;

  /** The socket for thrift rpc server. */
  private TServerSocket mTServerSocket;

  /** The transport provider to create thrift server transport. */
  private final TransportProvider mTransportProvider;

  /** The bind address for the rpc server. */
  private final InetSocketAddress mRpcBindAddress;

  /** The connect address for the rpc server. */
  private final InetSocketAddress mRpcConnectAddress;

  private final MetricsServlet mMetricsServlet = new MetricsServlet(MetricsSystem.METRIC_REGISTRY);
  private final PrometheusMetricsServlet mPMetricsServlet = new PrometheusMetricsServlet(
      MetricsSystem.METRIC_REGISTRY);

  /** The master registry. */
  private final MasterRegistry mRegistry;

  /** The web ui server. */
  private WebServer mWebServer;

  /** The RPC server. */
  private TServer mThriftServer;

  /** is true if the master is serving the RPC server. */
  private boolean mIsServing;

  /** The start time for when the master started serving the RPC server. */
  private long mStartTimeMs = -1;

  /** The journal system for writing journal entries and restoring master state. */
  protected final JournalSystem mJournalSystem;

  /** The JVMMonitor Progress. */
  private JvmPauseMonitor mJvmPauseMonitor;

  /** The manager of safe mode state. */
  protected final SafeModeManager mSafeModeManager;

  /** The clock to use for determining the time. */
  protected final Clock mClock;

  /** Keeps track of standby masters which are in communication with the leader master. */
  private final IndexedSet<MetaMasterInfo> mMasters =
      new IndexedSet<>(ID_INDEX, HOSTNAME_INDEX);
  /** Keeps track of standby masters which are no longer in communication with the leader master. */
  private final IndexedSet<MetaMasterInfo> mLostMasters =
      new IndexedSet<>(ID_INDEX, HOSTNAME_INDEX);

  /**
   * The service that detects lost master nodes.
   */
  @SuppressFBWarnings("URF_UNREAD_FIELD")
  private Future<?> mLostMasterDetectionService;

  /** The executor used for running maintenance threads for the meta master. */
  private ExecutorService mExecutorService;

  /** The worker configuration checker. */
  private final ServerConfigurationChecker mWorkerConfigChecker;

  /**
   * Creates a new {@link AlluxioMasterProcess}.
   */
  AlluxioMasterProcess(JournalSystem journalSystem) {
    mJournalSystem = Preconditions.checkNotNull(journalSystem, "journalSystem");
    mMinWorkerThreads = Configuration.getInt(PropertyKey.MASTER_WORKER_THREADS_MIN);
    mMaxWorkerThreads = Configuration.getInt(PropertyKey.MASTER_WORKER_THREADS_MAX);
    int connectionTimeout = (int) Configuration.getMs(PropertyKey.MASTER_CONNECTION_TIMEOUT_MS);

    Preconditions.checkArgument(mMaxWorkerThreads >= mMinWorkerThreads,
        PropertyKey.MASTER_WORKER_THREADS_MAX + " can not be less than "
            + PropertyKey.MASTER_WORKER_THREADS_MIN);

    if (connectionTimeout > 0) {
      LOG.debug("{} connection timeout[{}] is {}", this, PropertyKey.MASTER_CONNECTION_TIMEOUT_MS,
          connectionTimeout);
    }
    try {
      // Extract the port from the generated socket.
      // When running tests, it is fine to use port '0' so the system will figure out what port to
      // use (any random free port).
      // In a production or any real deployment setup, port '0' should not be used as it will make
      // deployment more complicated.
      if (!Configuration.getBoolean(PropertyKey.TEST_MODE)) {
        Preconditions.checkState(Configuration.getInt(PropertyKey.MASTER_RPC_PORT) > 0,
            this + " rpc port is only allowed to be zero in test mode.");
        Preconditions.checkState(Configuration.getInt(PropertyKey.MASTER_WEB_PORT) > 0,
            this + " web port is only allowed to be zero in test mode.");
      }

      mTransportProvider = TransportProvider.Factory.create();
      mTServerSocket = new TServerSocket(NetworkAddressUtils.getBindAddress(ServiceType.MASTER_RPC),
          (int) Configuration.getMs(PropertyKey.MASTER_CONNECTION_TIMEOUT_MS));
      mPort = NetworkAddressUtils.getThriftPort(mTServerSocket);
      // reset master rpc port
      Configuration.set(PropertyKey.MASTER_RPC_PORT, Integer.toString(mPort));
      mRpcBindAddress = NetworkAddressUtils.getBindAddress(ServiceType.MASTER_RPC);
      mRpcConnectAddress = NetworkAddressUtils.getConnectAddress(ServiceType.MASTER_RPC);

      if (!mJournalSystem.isFormatted()) {
        throw new RuntimeException(
            String.format("Journal %s has not been formatted!", mJournalSystem));
      }
      // Create masters.
      mRegistry = new MasterRegistry();
      mSafeModeManager = new DefaultSafeModeManager();
      MasterUtils.createMasters(mJournalSystem, mRegistry, mSafeModeManager);

      mClock = new SystemClock();
      mExecutorService = ExecutorServiceFactories
          .fixedThreadPoolExecutorServiceFactory(Constants.META_MASTER_NAME, 2).create();

      // Create config checker
      mWorkerConfigChecker = new ServerConfigurationChecker();

      // Register listeners for BlockMaster to interact with config checker
      BlockMaster blockMaster = mRegistry.get(BlockMaster.class);
      blockMaster.registerLostWorkerFoundListener(this::lostWorkerFoundHandler);
      blockMaster.registerWorkerLostListener(this::workerLostHandler);
      blockMaster.registerNewWorkerConfListener(this::registerNewWorkerConfHandler);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public <T extends Master> T getMaster(Class<T> clazz) {
    return mRegistry.get(clazz);
  }

  @Override
  public InetSocketAddress getRpcAddress() {
    return mRpcConnectAddress;
  }

  @Override
  public long getStartTimeMs() {
    return mStartTimeMs;
  }

  @Override
  public long getUptimeMs() {
    return System.currentTimeMillis() - mStartTimeMs;
  }

  @Override
  @Nullable
  public InetSocketAddress getWebAddress() {
    if (mWebServer != null) {
      return new InetSocketAddress(mWebServer.getBindHost(), mWebServer.getLocalPort());
    }
    return null;
  }

  @Override
  public boolean isInSafeMode() {
    return mSafeModeManager.isInSafeMode();
  }

  @Override
  public boolean isServing() {
    return mIsServing;
  }

  @Override
  public List<ConfigProperty> getConfiguration() {
    List<ConfigProperty> configInfoList = new ArrayList<>();
    String alluxioConfPrefix = "alluxio";
    for (Map.Entry<String, String> entry : Configuration.toMap().entrySet()) {
      String key = entry.getKey();
      if (key.startsWith(alluxioConfPrefix)) {
        PropertyKey propertyKey = PropertyKey.fromString(key);
        String source = Configuration.getFormattedSource(propertyKey);
        configInfoList.add(new ConfigProperty()
            .setName(key).setValue(entry.getValue()).setSource(source));
      }
    }
    return configInfoList;
  }

  @Override
  public long getMasterId(String hostname) {
    MetaMasterInfo existingMaster = mMasters.getFirstByField(HOSTNAME_INDEX, hostname);
    if (existingMaster != null) {
      // This master hostname is already mapped to a master id.
      long oldMasterId = existingMaster.getId();
      LOG.warn("The master {} already exists as id {}.", hostname, oldMasterId);
      return oldMasterId;
    }

    MetaMasterInfo lostMaster = mLostMasters.getFirstByField(HOSTNAME_INDEX, hostname);
    if (lostMaster != null) {
      // TODO(lu) call ServerConfigurationChecker.lostNodeFound()
      // This is one of the lost masters
      synchronized (lostMaster) {
        final long lostMasterId = lostMaster.getId();
        LOG.warn("A lost master {} has requested its old id {}.", hostname, lostMasterId);

        // Update the timestamp of the master before it is considered an active master.
        lostMaster.updateLastUpdatedTimeMs();
        mMasters.add(lostMaster);
        mLostMasters.remove(lostMaster);
        return lostMasterId;
      }
    }

    // Generate a new master id.
    long masterId = IdUtils.getRandomNonNegativeLong();
    while (!mMasters.add(new MetaMasterInfo(masterId, hostname))) {
      masterId = IdUtils.getRandomNonNegativeLong();
    }

    LOG.info("getMasterId(): Hostname: {} id: {}", hostname, masterId);
    return masterId;
  }

  @Override
  public void masterRegister(long masterId, RegisterMasterTOptions options)
      throws NoMasterException {
    MetaMasterInfo master = mMasters.getFirstByField(ID_INDEX, masterId);
    if (master == null) {
      throw new NoMasterException(ExceptionMessage.NO_MASTER_FOUND.getMessage(masterId));
    }
    // TODO(lu) master config checker registerNewConf
    synchronized (master) {
      master.updateLastUpdatedTimeMs();
    }

    LOG.info("registerMaster(): {}", master);
  }

  @Override
  public void masterHeartbeat(long masterId) {
    MetaMasterInfo master = mMasters.getFirstByField(ID_INDEX, masterId);
    if (master == null) {
      LOG.warn("Could not find master id: {} for heartbeat.", masterId);
      return;
    }

    synchronized (master) {
      master.updateLastUpdatedTimeMs();
    }
  }

  @Override
  public void waitForReady() {
    CommonUtils.waitFor(this + " to start", new Function<Void, Boolean>() {
      @Override
      public Boolean apply(Void input) {
        return mThriftServer != null && mThriftServer.isServing() && mWebServer != null
            && mWebServer.getServer().isRunning();
      }
    }, WaitForOptions.defaults().setTimeoutMs(10000));
  }

  @Override
  public void start() throws Exception {
    mJournalSystem.start();
    mJournalSystem.setMode(Mode.PRIMARY);
    startMasters(true);
    startServing();
  }

  @Override
  public void stop() throws Exception {
    if (mIsServing) {
      stopServing();
      stopMasters();
      mJournalSystem.stop();
      mIsServing = false;
    }
  }

  /**
   * Starts all masters, including block master, FileSystem master, lineage master and additional
   * masters.
   *
   * @param isLeader if the Master is leader
   */
  protected void startMasters(boolean isLeader) {
    try {
      mWorkerConfigChecker.reset();
      if (isLeader) {
        mSafeModeManager.notifyPrimaryMasterStarted();

        mLostMasterDetectionService = mExecutorService.submit(new HeartbeatThread(
            HeartbeatContext.MASTER_LOST_MASTER_DETECTION,
            new LostMasterDetectionHeartbeatExecutor(),
            (int) Configuration.getMs(PropertyKey.MASTER_HEARTBEAT_INTERVAL_MS)));
      } else {
        // Standby master should setup MetaMasterSync to communicate with the leader master
        mMetaMasterSync = new MetaMasterSync(this, mMasterId, mHostname, mMetaMasterMasterClient);
      }
      mRegistry.start(isLeader);
      LOG.info("All masters started");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Stops all masters, including lineage master, block master and fileSystem master and additional
   * masters.
   */
  protected void stopMasters() {
    try {
      mRegistry.stop();
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }

  private void startServing() {
    startServing("", "");
  }

  /**
   * Starts serving, letting {@link MetricsSystem} start sink and starting the web ui server and RPC
   * Server.
   *
   * @param startMessage empty string or the message that the master gains the leadership
   * @param stopMessage empty string or the message that the master loses the leadership
   */
  protected void startServing(String startMessage, String stopMessage) {
    MetricsSystem.startSinks();
    startServingWebServer();
    startJvmMonitorProcess();
    LOG.info("Alluxio master version {} started{}. "
            + "bindHost={}, connectHost={}, rpcPort={}, webPort={}",
        RuntimeConstants.VERSION,
        startMessage,
        NetworkAddressUtils.getBindAddress(ServiceType.MASTER_RPC),
        NetworkAddressUtils.getConnectAddress(ServiceType.MASTER_RPC),
        NetworkAddressUtils.getPort(ServiceType.MASTER_RPC),
        NetworkAddressUtils.getPort(ServiceType.MASTER_WEB));
    startServingRPCServer();
    LOG.info("Alluxio master ended{}", stopMessage);
  }

  /**
   * Starts serving web ui server, resetting master web port, adding the metrics servlet to the web
   * server and starting web ui.
   */
  protected void startServingWebServer() {
    mWebServer = new MasterWebServer(ServiceType.MASTER_WEB.getServiceName(),
        NetworkAddressUtils.getBindAddress(ServiceType.MASTER_WEB), this);
    // reset master web port
    Configuration.set(PropertyKey.MASTER_WEB_PORT, Integer.toString(mWebServer.getLocalPort()));
    // Add the metrics servlet to the web server.
    mWebServer.addHandler(mMetricsServlet.getHandler());
    // Add the prometheus metrics servlet to the web server.
    mWebServer.addHandler(mPMetricsServlet.getHandler());
    // start web ui
    mWebServer.start();
  }

  /**
   * Starts jvm monitor process, to monitor jvm.
   */
  protected void startJvmMonitorProcess() {
    if (Configuration.getBoolean(PropertyKey.MASTER_JVM_MONITOR_ENABLED)) {
      mJvmPauseMonitor = new JvmPauseMonitor();
      mJvmPauseMonitor.start();
    }
  }

  private void registerServices(TMultiplexedProcessor processor, Map<String, TProcessor> services) {
    for (Map.Entry<String, TProcessor> service : services.entrySet()) {
      processor.registerProcessor(service.getKey(), service.getValue());
    }
  }

  /**
   * Starts the Thrift RPC server. The AlluxioMaster registers the Services of registered
   * {@link Master}s and meta services to a multiplexed processor, then creates the master thrift
   * service with the multiplexed processor.
   */
  protected void startServingRPCServer() {
    // set up multiplexed thrift processors
    TMultiplexedProcessor processor = new TMultiplexedProcessor();
    // register master services
    for (Master master : mRegistry.getServers()) {
      registerServices(processor, master.getServices());
    }
    // register meta services
    processor.registerProcessor(Constants.META_MASTER_CLIENT_SERVICE_NAME,
        new MetaMasterClientService.Processor<>(new MetaMasterClientServiceHandler(this)));

    // Return a TTransportFactory based on the authentication type
    TTransportFactory transportFactory;
    try {
      String serverName = NetworkAddressUtils.getConnectHost(ServiceType.MASTER_RPC);
      transportFactory = mTransportProvider.getServerTransportFactory(serverName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      if (mTServerSocket != null) {
        mTServerSocket.close();
      }
      mTServerSocket = new TServerSocket(mRpcBindAddress,
          (int) Configuration.getMs(PropertyKey.MASTER_CONNECTION_TIMEOUT_MS));
    } catch (TTransportException e) {
      throw new RuntimeException(e);
    }
    // create master thrift service with the multiplexed processor.
    Args args = new TThreadPoolServer.Args(mTServerSocket).maxWorkerThreads(mMaxWorkerThreads)
        .minWorkerThreads(mMinWorkerThreads).processor(processor).transportFactory(transportFactory)
        .protocolFactory(new TBinaryProtocol.Factory(true, true));

    args.stopTimeoutVal = (int) Configuration.getMs(PropertyKey.MASTER_THRIFT_SHUTDOWN_TIMEOUT);
    mThriftServer = new TThreadPoolServer(args);

    // start thrift rpc server
    mIsServing = true;
    mStartTimeMs = System.currentTimeMillis();
    mSafeModeManager.notifyRpcServerStarted();
    mThriftServer.serve();
  }

  /**
   * Stops serving, trying stop RPC server and web ui server and letting {@link MetricsSystem} stop
   * all the sinks.
   */
  protected void stopServing() throws Exception {
    if (mThriftServer != null) {
      mThriftServer.stop();
      mThriftServer = null;
    }
    if (mTServerSocket != null) {
      mTServerSocket.close();
      mTServerSocket = null;
    }
    if (mJvmPauseMonitor != null) {
      mJvmPauseMonitor.stop();
    }
    if (mWebServer != null) {
      mWebServer.stop();
      mWebServer = null;
    }
    MetricsSystem.stopSinks();
    mIsServing = false;
  }

  @Override
  public String toString() {
    return "Alluxio master @" + mRpcConnectAddress;
  }

  /**
   * Lost master periodic check.
   */
  private final class LostMasterDetectionHeartbeatExecutor implements HeartbeatExecutor {

    /**
     * Constructs a new {@link LostMasterDetectionHeartbeatExecutor}.
     */
    public LostMasterDetectionHeartbeatExecutor() {
    }

    @Override
    public void heartbeat() {
      int masterTimeoutMs = (int) Configuration.getMs(PropertyKey.MASTER_HEARTBEAT_TIMEOUT_MS);
      for (MetaMasterInfo master : mMasters) {
        synchronized (master) {
          final long lastUpdate = mClock.millis() - master.getLastUpdatedTimeMs();
          if (lastUpdate > masterTimeoutMs) {
            LOG.error("The master {}({}) timed out after {}ms without a heartbeat!", master.getId(),
                master.getHostname(), lastUpdate);
            mLostMasters.add(master);
            mMasters.remove(master);
            // TODO(lu) call ServerConfigurationChecker.detectNodeLost()
          }
        }
      }
    }

    @Override
    public void close() {
      // Nothing to clean up
    }
  }

  /**
   * Updates the config checker when a lost worker becomes alive.
   *
   * @param id the id of the worker
   */
  private void lostWorkerFoundHandler(long id) {
    mWorkerConfigChecker.lostNodeFound(id);
  }

  /**
   * Updates the config checker when a live worker becomes lost.
   *
   * @param id the id of the worker
   */
  private void workerLostHandler(long id) {
    mWorkerConfigChecker.handleNodeLost(id);
  }

  /**
   * Updates the config checker when a worker registers with configuration.
   *
   * @param id the id of the worker
   * @param configList the configuration of this worker
   */
  private void registerNewWorkerConfHandler(long id, List<ConfigProperty> configList) {
    mWorkerConfigChecker.registerNewConf(id, configList);
  }
}
