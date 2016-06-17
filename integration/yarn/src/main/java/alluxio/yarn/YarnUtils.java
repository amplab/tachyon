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

package alluxio.yarn;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.NodeState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.apache.hadoop.yarn.util.Records;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Utility methods for working with Yarn.
 */
@ThreadSafe
public final class YarnUtils {
  public static final String ALLUXIO_SETUP_SCRIPT = "alluxio-yarn-setup.sh";

  private static final NodeState[] USABLE_NODE_STATES;

  static {
    List<NodeState> usableStates = Lists.newArrayList();
    for (NodeState nodeState : NodeState.values()) {
      if (!nodeState.isUnusable()) {
        usableStates.add(nodeState);
      }
    }
    USABLE_NODE_STATES = usableStates.toArray(new NodeState[usableStates.size()]);
  }

  /**
   * Returns the set of host names for all nodes yarnClient's yarn cluster.
   *
   * @param yarnClient the client to use to look up node information
   * @return the set of host names
   * @throws YarnException if an error occurs within Yarn
   * @throws IOException if an error occurs in Yarn's underlying IO
   */
  public static Set<String> getNodeHosts(YarnClient yarnClient) throws YarnException, IOException {
    ImmutableSet.Builder<String> nodeHosts = ImmutableSet.builder();
    for (NodeReport runningNode : yarnClient.getNodeReports(USABLE_NODE_STATES)) {
      nodeHosts.add(runningNode.getNodeId().getHost());
    }
    return nodeHosts.build();
  }

  /**
   * Creates a local resource for a file on HDFS.
   *
   * @param yarnConf YARN configuration
   * @param resourcePath known path of resource file on HDFS
   * @throws IOException if the file can not be found on HDFS
   * @return the created local resource
   */
  public static LocalResource createLocalResourceOfFile(YarnConfiguration yarnConf,
      String resourcePath) throws IOException {
    LocalResource localResource = Records.newRecord(LocalResource.class);

    Path jarHdfsPath = new Path(resourcePath);
    FileStatus jarStat = FileSystem.get(yarnConf).getFileStatus(jarHdfsPath);
    localResource.setResource(ConverterUtils.getYarnUrlFromPath(jarHdfsPath));
    localResource.setSize(jarStat.getLen());
    localResource.setTimestamp(jarStat.getModificationTime());
    localResource.setType(LocalResourceType.FILE);
    localResource.setVisibility(LocalResourceVisibility.PUBLIC);
    return localResource;
  }

  /**
   * Enum representing types of containers run by the yarn setup script. The strings here correspond
   * with the strings in integration/bin/alluxio-yarn-setup.sh.
   */
  public enum YarnContainerType {
    APPLICATION_MASTER("application-master"),
    ALLUXIO_MASTER("alluxio-master"),
    ALLUXIO_WORKER("alluxio-worker");

    private final String mName;

    YarnContainerType(String name) {
      mName = name;
    }

    /**
     * @return the name of the container type
     */
    public String getName() {
      return mName;
    }
  }

  /**
   * Convenience method for calling {@link #buildCommand(YarnContainerType, Map)} with no arguments.
   *
   * @param containerType the type of container to build the command for
   * @return the built command string
   */
  public static String buildCommand(YarnContainerType containerType) {
    return buildCommand(containerType, new HashMap<String, String>());
  }

  /**
   * Creates a command string for running the Alluxio yarn setup script for the given type of yarn
   * container.
   *
   * @param containerType the type of container to build the command for
   * @param args arguments to pass to to the setup script
   * @return the built command string
   */
  public static String buildCommand(YarnContainerType containerType, Map<String, String> args) {
    CommandBuilder commandBuilder =
        new CommandBuilder("./" + ALLUXIO_SETUP_SCRIPT).addArg(containerType.getName());
    for (Entry<String, String> argsEntry : args.entrySet()) {
      commandBuilder.addArg(argsEntry.getKey(), argsEntry.getValue());
    }
    // Redirect stdout and stderr to yarn log files
    commandBuilder.addArg("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout");
    commandBuilder.addArg("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr");
    return commandBuilder.toString();
  }

  /**
   * Get yarn resource manager ip address.
   *
   * @param config yarn configuration
   * @return the ip address of yarn resource manager
   */
  public static InetSocketAddress getRMAddress(Configuration config) {
    return config.getSocketAddr(YarnConfiguration.RM_ADDRESS,
        YarnConfiguration.DEFAULT_RM_ADDRESS, YarnConfiguration.DEFAULT_RM_PORT);
  }

  private YarnUtils() {} // this utils class should not be instantiated
}
