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

package tachyon.yarn;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.AMRMClient.ContainerRequest;
import org.apache.hadoop.yarn.client.api.NMClient;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.Records;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import tachyon.Constants;
import tachyon.conf.TachyonConf;
import tachyon.util.FormatUtils;
import tachyon.util.io.PathUtils;

/**
 * Actual owner of Tachyon running on Yarn. The YARN ResourceManager will launch this
 * ApplicationMaster on an allocated container. The ApplicationMaster communicates with YARN
 * cluster, and handles application execution. It performs operations in an asynchronous fashion.
 */
public final class ApplicationMaster implements AMRMClientAsync.CallbackHandler {
  private static final Logger LOG = LoggerFactory.getLogger(Constants.LOGGER_TYPE);

  // Parameters sent from Client
  private final int mMasterCpu;
  private final int mWorkerCpu;
  private final int mMasterMemInMB;
  private final int mWorkerMemInMB;
  private final int mRamdiskMemInMB;
  private final int mNumWorkers;
  private final String mMasterJavaOpts;
  private final String mWorkerJavaOpts;
  private final String mTachyonHome;
  private final String mMasterAddress;
  private final String mResourcePath;

  private final YarnConfiguration mYarnConf = new YarnConfiguration();
  private final TachyonConf mTachyonConf = new TachyonConf();

  /** Client to talk to Resource Manager */
  private AMRMClientAsync<ContainerRequest> mRMClient;
  /** Client to talk to Node Manager */
  private NMClient mNMClient;
  /** Whether a container for Tachyon master is allocated */
  private volatile boolean mMasterContainerAllocated;
  /** Num of allocated worker containers */
  private volatile int mNumAllocatedWorkerContainers;
  /** Network address of the container allocated for Tachyon master */
  private String mMasterContainerNetAddress;

  private volatile boolean mApplicationDone;

  public ApplicationMaster(int numWorkers, String tachyonHome, String masterAddress,
      String resourcePath, String masterJavaOpts, String workerJavaOpts) {

    mMasterCpu = mTachyonConf.getInt(Constants.INTEGRATION_MASTER_RESOURCE_CPU);
    mMasterMemInMB =
        (int) mTachyonConf.getBytes(Constants.INTEGRATION_MASTER_RESOURCE_MEM) / Constants.MB;
    mWorkerCpu = mTachyonConf.getInt(Constants.INTEGRATION_WORKER_RESOURCE_CPU);
    // TODO(binfan): request worker container and ramdisk container separately
    // memory for running worker
    mWorkerMemInMB =
        (int) mTachyonConf.getBytes(Constants.INTEGRATION_WORKER_RESOURCE_MEM) / Constants.MB;
    // memory for running ramdisk
    mRamdiskMemInMB = (int) mTachyonConf.getBytes(Constants.WORKER_MEMORY_SIZE) / Constants.MB;
    mNumWorkers = numWorkers;
    mTachyonHome = tachyonHome;
    mMasterAddress = masterAddress;
    mResourcePath = resourcePath;
    mMasterJavaOpts = masterJavaOpts;
    mWorkerJavaOpts = workerJavaOpts;
    mMasterContainerAllocated = false;
    mNumAllocatedWorkerContainers = 0;
    mApplicationDone = false;
  }

  /**
   * @param args Command line arguments to launch application master
   */
  public static void main(String[] args) {
    Options options = new Options();
    options.addOption("num_workers", true, "Number of Tachyon workers to launch. Default 1");
    options.addOption("tachyon_home", true,
        "Path of the home dir of Tachyon deployment on YARN slave machines");
    options.addOption("master_address", true, "(Required) Address to run Tachyon master");
    options.addOption("master_java_opts", true, "Java opts for Tachyon master");
    options.addOption("worker_java_opts", true, "Java opts for Tachyon worker");
    options.addOption("resource_path", true,
        "(Required) HDFS path containing the Application Master");

    try {
      LOG.info("Starting Application Master with args " + Arrays.toString(args));

      CommandLine cliParser = new GnuParser().parse(options, args);
      int numWorkers = Integer.parseInt(cliParser.getOptionValue("num_workers", "1"));
      String tachyonHome;
      if (cliParser.hasOption("tachyon_home")) {
        tachyonHome = cliParser.getOptionValue("tachyon_home");
      } else {
        tachyonHome = ApplicationConstants.Environment.PWD.$();
      }
      String masterAddress = cliParser.getOptionValue("master_address");
      String resourcePath = cliParser.getOptionValue("resource_path");
      String masterJavaOpts = cliParser.getOptionValue("master_java_opts");
      String workerJavaOpts = cliParser.getOptionValue("worker_java_opts");

      LOG.info("master java opts : " + masterJavaOpts);
      LOG.info("worker java opts : " + workerJavaOpts);

      ApplicationMaster applicationMaster =
          new ApplicationMaster(numWorkers, tachyonHome, masterAddress, resourcePath,
              masterJavaOpts, workerJavaOpts);
      applicationMaster.start();
      applicationMaster.requestContainers();
      applicationMaster.stop();
    } catch (Exception ex) {
      LOG.error("Error running Application Master " + ex);
      System.exit(1);
    }
  }

  @Override
  public void onContainersAllocated(List<Container> containers) {
    if (!mMasterContainerAllocated) {
      launchTachyonMasterContainers(containers);
    } else {
      launchTachyonWorkerContainers(containers);
    }
  }

  @Override
  public void onContainersCompleted(List<ContainerStatus> statuses) {
    for (ContainerStatus status : statuses) {
      LOG.error("Completed container " + status.getContainerId());
    }
  }

  @Override
  public void onNodesUpdated(List<NodeReport> updated) {}

  @Override
  public void onShutdownRequest() {
    mApplicationDone = true;
  }

  @Override
  public void onError(Throwable t) {}

  @Override
  public float getProgress() {
    return 0;
  }

  public void start() throws IOException, YarnException {
    // create a client to talk to NodeManager
    mNMClient = NMClient.createNMClient();
    mNMClient.init(mYarnConf);
    mNMClient.start();

    // Create a client to talk to the ResourceManager
    mRMClient = AMRMClientAsync.createAMRMClientAsync(100, this);
    mRMClient.init(mYarnConf);
    mRMClient.start();

    // Register with ResourceManager
    mRMClient.registerApplicationMaster("" /* hostname */, 0 /* port */, "" /* tracking url */);
    LOG.info("ApplicationMaster registered");
  }

  public void requestContainers() throws Exception {
    // Priority for Tachyon master and worker containers - priorities are intra-application
    Priority priority = Records.newRecord(Priority.class);
    priority.setPriority(0);

    // Resource requirements for master containers
    Resource masterResource = Records.newRecord(Resource.class);
    masterResource.setMemory(mMasterMemInMB);
    masterResource.setVirtualCores(mMasterCpu);

    String[] nodes = {mMasterAddress};

    // Make container request for Tachyon master to ResourceManager
    boolean relaxLocality = true;
    if (!mMasterAddress.equals("localhost")) {
      relaxLocality = false;
    }
    ContainerRequest masterContainerAsk =
        new ContainerRequest(masterResource, nodes, null /* any racks */, priority, relaxLocality);
    LOG.info("Making resource request for Tachyon master: cpu {} memory {} MB on node {}",
        masterResource.getVirtualCores(), masterResource.getMemory(), mMasterAddress);

    mRMClient.addContainerRequest(masterContainerAsk);

    // Wait until Tachyon master container has been allocated
    while (!mMasterContainerAllocated) {
      Thread.sleep(1000);
    }

    // Resource requirements for master containers
    Resource workerResource = Records.newRecord(Resource.class);
    workerResource.setMemory(mWorkerMemInMB + mRamdiskMemInMB);
    workerResource.setVirtualCores(mWorkerCpu);

    // Make container requests for workers to ResourceManager
    for (int i = 0; i < mNumWorkers; i ++) {
      ContainerRequest containerAsk = new ContainerRequest(workerResource, null /* any hosts */,
          null /* any racks */, priority);
      LOG.info("Making resource request for Tachyon worker {}: cpu {} memory {} MB on any nodes", i,
          workerResource.getVirtualCores(), workerResource.getMemory());
      mRMClient.addContainerRequest(containerAsk);
    }

    // Wait until all Tachyon worker containers have been allocated
    while (mNumAllocatedWorkerContainers < mNumWorkers) {
      Thread.sleep(1000);
    }

    LOG.info("Master and workers are launched");
    // Wait for 5 more seconds to avoid application unregistered before some container fully
    // launched.
    while (!mApplicationDone) {
      Thread.sleep(5000);
    }
  }

  public void stop() {
    try {
      mRMClient.unregisterApplicationMaster(FinalApplicationStatus.SUCCEEDED, "", "");
    } catch (YarnException yex) {
      LOG.error("Failed to unregister application " + yex);
    } catch (IOException ioe) {
      LOG.error("Failed to unregister application " + ioe);
    }
    mRMClient.stop();
  }

  private void launchTachyonMasterContainers(List<Container> containers) {
    final String command = new CommandBuilder(PathUtils.concatPath("./tachyon-master-yarn.sh"))
            .addArg("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout")
            .addArg("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr").toString();

    List<String> commands = Lists.newArrayList(command);

    for (Container container : containers) {
      try {
        // Launch container by create ContainerLaunchContext
        ContainerLaunchContext ctx = Records.newRecord(ContainerLaunchContext.class);

        // Setup commands
        ctx.setCommands(commands);

        // Setup local resources
        Map<String, LocalResource> localResources = new HashMap<String, LocalResource>();
        localResources.put("tachyon.jar", Utils.createLocalResourceOfFile(mYarnConf,
            mResourcePath + "/tachyon.jar"));
        localResources.put("tachyon-master-yarn.sh", Utils.createLocalResourceOfFile(mYarnConf,
            mResourcePath + "/tachyon-master-yarn.sh"));
        ctx.setLocalResources(localResources);

        // Setup the environment needed for the launch context.
        // Because our jars are available as local resources in the working directory from which
        // the command will be run, we need to append "." to the path.
        Map<String, String> env = new HashMap<String, String>();
        String classPath =
            new StringBuilder(ApplicationConstants.Environment.CLASSPATH.$())
                .append(File.pathSeparatorChar).append("./*").toString();
        env.put("CLASSPATH", classPath);
        env.put("JAVA", PathUtils.concatPath(ApplicationConstants.Environment.JAVA_HOME.$$(),
                "bin", "java"));
        env.put("TACHYON_MASTER_JAVA_OPTS", mMasterJavaOpts);
        env.put("TACHYON_HOME", mTachyonHome);
        env.put("TACHYON_LOGS_DIR", PathUtils.concatPath(ApplicationConstants.Environment.PWD.$(),
            "logs"));
        ctx.setEnvironment(env);

        LOG.info("Launching container {} for Tachyon master on {} with master command: {}",
            container.getId(), container.getNodeHttpAddress(), commands);
        mNMClient.startContainer(container, ctx);
        String containerUri = container.getNodeHttpAddress(); // in the form of 1.2.3.4:8042
        mMasterContainerNetAddress = containerUri.split(":")[0];
        LOG.info("Master address: " + mMasterContainerNetAddress);
        mMasterContainerAllocated = true;
        return;
      } catch (Exception ex) {
        LOG.error("Error launching container " + container.getId() + " " + ex);
      }
    }
  }

  private void launchTachyonWorkerContainers(List<Container> containers) {
    final String command = new CommandBuilder(PathUtils.concatPath("./tachyon-worker-yarn.sh"))
            .addArg("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout")
            .addArg("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr").toString();

    List<String> commands = Lists.newArrayList(command);

    // Setup the environment needed for the launch context.
    // Because our jars are available as local resources in the working directory from which
    // the command will be run, we need to append "." to the path.
    Map<String, String> env = new HashMap<String, String>();
    String classPath =
        new StringBuilder(ApplicationConstants.Environment.CLASSPATH.$())
            .append(File.pathSeparatorChar).append("./*").toString();
    env.put("CLASSPATH", classPath);
    env.put("JAVA", PathUtils.concatPath(ApplicationConstants.Environment.JAVA_HOME.$$(),
        "bin", "java"));
    env.put("TACHYON_WORKER_JAVA_OPTS", mWorkerJavaOpts);
    env.put("TACHYON_HOME", mTachyonHome);
    env.put("TACHYON_LOGS_DIR", PathUtils.concatPath(ApplicationConstants.Environment.PWD.$(),
        "logs"));
    env.put("TACHYON_MASTER_ADDRESS", mMasterContainerNetAddress);
    env.put("TACHYON_WORKER_MEMORY_SIZE",
        FormatUtils.getSizeFromBytes((long) mRamdiskMemInMB * Constants.MB));

    // Setup local resources
    Map<String, LocalResource> localResources = new HashMap<String, LocalResource>();
    try {
      localResources.put("tachyon.jar", Utils.createLocalResourceOfFile(mYarnConf,
          mResourcePath + "/tachyon.jar"));
      localResources.put("tachyon-worker-yarn.sh", Utils.createLocalResourceOfFile(mYarnConf,
          mResourcePath + "/tachyon-worker-yarn.sh"));
    } catch (IOException e) {
      throw new RuntimeException("Cannot find resource", e);
    }

    for (Container container : containers) {
      if (mNumAllocatedWorkerContainers >= mNumWorkers) {
        break;
      }
      try {
        // Launch container by create ContainerLaunchContext
        ContainerLaunchContext ctx = Records.newRecord(ContainerLaunchContext.class);
        ctx.setCommands(commands);
        ctx.setLocalResources(localResources);
        ctx.setEnvironment(env);
        LOG.info("Launching container {} for Tachyon worker {} on {} with worker command: {}",
            container.getId(), mNumAllocatedWorkerContainers, container.getNodeHttpAddress(),
            command);
        mNMClient.startContainer(container, ctx);
        mNumAllocatedWorkerContainers ++;
      } catch (Exception ex) {
        LOG.error("Error launching container " + container.getId() + " " + ex);
      }
    }
  }
}
