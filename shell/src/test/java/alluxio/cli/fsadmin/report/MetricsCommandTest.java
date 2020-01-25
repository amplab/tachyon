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

package alluxio.cli.fsadmin.report;

import alluxio.client.meta.MetaMasterClient;
import alluxio.grpc.MetricValue;
import alluxio.metrics.MetricKey;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricsCommandTest {
  private MetaMasterClient mMetaMasterClient;
  private ByteArrayOutputStream mOutputStream;
  private PrintStream mPrintStream;

  @Before
  public void before() throws IOException {
    Map<String, MetricValue> metricsMap = generateMetricsMap();

    // Prepare mock meta master client
    mMetaMasterClient = Mockito.mock(MetaMasterClient.class);
    Mockito.when(mMetaMasterClient.getMetrics()).thenReturn(metricsMap);

    // Prepare print stream
    mOutputStream = new ByteArrayOutputStream();
    mPrintStream = new PrintStream(mOutputStream, true, "utf-8");
  }

  @After
  public void after() {
    mPrintStream.close();
  }

  @Test
  public void metrics() throws IOException {
    MetricsCommand metricsCommand = new MetricsCommand(mMetaMasterClient, mPrintStream);
    metricsCommand.run();
    checkIfOutputValid();
  }

  /**
   * @return a generated metrics map
   */
  private Map<String, MetricValue> generateMetricsMap() {
    Map<String, MetricValue> map = new HashMap<>();
    map.put(MetricKey.CLIENT_BYTES_READ_LOCAL.getName(),
        MetricValue.newBuilder().setLongValue(12312312312L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_ALLUXIO.getName(),
        MetricValue.newBuilder().setLongValue(421312312L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_DOMAIN.getName(),
        MetricValue.newBuilder().setLongValue(4245232L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_UFS_ALL.getName(),
        MetricValue.newBuilder().setLongValue(534214123L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_ALLUXIO.getName(),
        MetricValue.newBuilder().setLongValue(23532L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_DOMAIN.getName(),
        MetricValue.newBuilder().setLongValue(65463532L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_UFS_ALL.getName(),
        MetricValue.newBuilder().setLongValue(325324L).build());

    map.put(MetricKey.CLUSTER_BYTES_READ_LOCAL_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(123125324L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_ALLUXIO_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(543534623L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_DOMAIN_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(31423412L).build());
    map.put(MetricKey.CLUSTER_BYTES_READ_UFS_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(745632L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_ALLUXIO_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(8423432L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_DOMAIN_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(1231231L).build());
    map.put(MetricKey.CLUSTER_BYTES_WRITTEN_UFS_THROUGHPUT.getName(),
        MetricValue.newBuilder().setLongValue(34264L).build());

    map.put(MetricKey.MASTER_DIRECTORIES_CREATED.getName(),
        MetricValue.newBuilder().setLongValue(121L).build());
    map.put(MetricKey.MASTER_FILE_BLOCK_INFOS_GOT.getName(),
        MetricValue.newBuilder().setLongValue(31243412L).build());
    map.put(MetricKey.MASTER_FILE_INFOS_GOT.getName(),
        MetricValue.newBuilder().setLongValue(12312321434276L).build());
    map.put(MetricKey.MASTER_FILES_COMPLETED.getName(),
        MetricValue.newBuilder().setLongValue(0L).build());
    map.put(MetricKey.MASTER_FILES_CREATED.getName(),
        MetricValue.newBuilder().setLongValue(534L).build());
    map.put(MetricKey.MASTER_FILES_FREED.getName(),
        MetricValue.newBuilder().setLongValue(2141L).build());
    map.put(MetricKey.MASTER_FILES_PERSISTED.getName(),
        MetricValue.newBuilder().setLongValue(4171L).build());
    map.put(MetricKey.MASTER_NEW_BLOCKS_GOT.getName(),
        MetricValue.newBuilder().setLongValue(4L).build());
    map.put(MetricKey.MASTER_PATHS_DELETED.getName(),
        MetricValue.newBuilder().setLongValue(583L).build());
    map.put(MetricKey.MASTER_PATHS_MOUNTED.getName(),
        MetricValue.newBuilder().setLongValue(3635L).build());
    map.put(MetricKey.MASTER_PATHS_RENAMED.getName(),
        MetricValue.newBuilder().setLongValue(382L).build());
    map.put(MetricKey.MASTER_PATHS_UNMOUNTED.getName(),
        MetricValue.newBuilder().setLongValue(975L).build());

    map.put(MetricKey.MASTER_COMPLETE_FILE_OPS.getName(),
        MetricValue.newBuilder().setLongValue(813L).build());
    map.put(MetricKey.MASTER_CREATE_DIRECTORIES_OPS.getName(),
        MetricValue.newBuilder().setLongValue(325728397L).build());
    map.put(MetricKey.MASTER_CREATE_FILES_OPS.getName(),
        MetricValue.newBuilder().setLongValue(89L).build());
    map.put(MetricKey.MASTER_DELETE_PATHS_OPS.getName(),
        MetricValue.newBuilder().setLongValue(21L).build());
    map.put(MetricKey.MASTER_FREE_FILE_OPS.getName(),
        MetricValue.newBuilder().setLongValue(5213L).build());
    map.put(MetricKey.MASTER_GET_FILE_BLOCK_INFO_OPS.getName(),
        MetricValue.newBuilder().setLongValue(798L).build());
    map.put(MetricKey.MASTER_GET_FILE_INFO_OPS.getName(),
        MetricValue.newBuilder().setLongValue(32L).build());
    map.put(MetricKey.MASTER_GET_NEW_BLOCK_OPS.getName(),
        MetricValue.newBuilder().setLongValue(912572136653L).build());
    map.put(MetricKey.MASTER_MOUNT_OPS.getName(),
        MetricValue.newBuilder().setLongValue(953795L).build());
    map.put(MetricKey.MASTER_RENAME_PATH_OPS.getName(),
        MetricValue.newBuilder().setLongValue(29L).build());
    map.put(MetricKey.MASTER_SET_ACL_OPS.getName(),
        MetricValue.newBuilder().setLongValue(316L).build());
    map.put(MetricKey.MASTER_SET_ATTRIBUTE_OPS.getName(),
        MetricValue.newBuilder().setLongValue(0L).build());
    map.put(MetricKey.MASTER_UNMOUNT_OPS.getName(),
        MetricValue.newBuilder().setLongValue(1L).build());

    map.put("UfsSessionCount-Ufs:_alluxio_underFSStorage",
        MetricValue.newBuilder().setLongValue(8535L).build());
    map.put("UfsSessionCount-Ufs:file:___Users_alluxio_alluxioMountedFolder",
        MetricValue.newBuilder().setLongValue(1231L).build());
    map.put("master.CapacityTotal",
        MetricValue.newBuilder().setLongValue(1154531246129122L).build());
    map.put("heap.used", MetricValue.newBuilder().setDoubleValue(0.0028321312).build());
    map.put("pools.Metaspace.usage", MetricValue.newBuilder().setDoubleValue(0.95728).build());
    map.put("pools.Metaspace.max", MetricValue.newBuilder().setLongValue(-1).build());
    return map;
  }

  /**
   * Checks if the output is expected.
   */
  private void checkIfOutputValid() {
    String output = new String(mOutputStream.toByteArray(), StandardCharsets.UTF_8);
    // CHECKSTYLE.OFF: LineLengthExceed - Much more readable
    List<String> expectedOutput = Arrays.asList(
        "Total IO: ",
        "    Short-circuit Read                                    11.47GB",
        "    Short-circuit Read (Domain Socket)                  4145.73KB",
        "    From Remote Instances                                401.79MB",
        "    Under Filesystem Read                                509.47MB",
        "    Alluxio Write                                         22.98KB",
        "    Alluxio Write (Domain Socket)                         62.43MB",
        "    Under Filesystem Write                               317.70KB",
        "",
        "Total IO Throughput (Last Minute): ",
        "    Short-circuit Read                                   117.42MB",
        "    Short-circuit Read (Domain Socket)                    29.97MB",
        "    From Remote Instances                                518.36MB",
        "    Under Filesystem Read                                728.16KB",
        "    Alluxio Write                                          8.03MB",
        "    Alluxio Write (Domain Socket)                       1202.37KB",
        "    Under Filesystem Write                                33.46KB",
        "",
        "Cache Hit Rate (Percentage): ",
        "    Alluxio Local                                           92.80",
        "    Alluxio Remote                                           3.18",
        "    Miss                                                     4.03",
        "",
        "Logical Operations: ",
        "    Directories Created                                       121",
        "    File Block Infos Got                               31,243,412",
        "    File Infos Got                             12,312,321,434,276",
        "    Files Completed                                             0",
        "    Files Created                                             534",
        "    Files Freed                                             2,141",
        "    Files Persisted                                         4,171",
        "    New Blocks Got                                              4",
        "    Paths Deleted                                             583",
        "    Paths Mounted                                           3,635",
        "    Paths Renamed                                             382",
        "    Paths Unmounted                                           975",
        "",
        "RPC Invocations: ",
        "    Complete File Operations                                  813",
        "    Create Directory Operations                       325,728,397",
        "    Create File Operations                                     89",
        "    Delete Path Operations                                     21",
        "    Free File Operations                                    5,213",
        "    Get File Block Info Operations                            798",
        "    Get File Info Operations                                   32",
        "    Get New Block Operations                      912,572,136,653",
        "    Mount Operations                                      953,795",
        "    Rename Path Operations                                     29",
        "    Set ACL Operations                                        316",
        "    Set Attribute Operations                                    0",
        "    Unmount Operations                                          1",
        "",
        "Other Metrics: ",
        "    UfsSessionCount-Ufs:_alluxio_underFSStorage  (8,535)",
        "    UfsSessionCount-Ufs:file:___Users_alluxio_alluxioMountedFolder  (1,231)",
        "    heap.used  (0.00283)",
        "    master.CapacityTotal  (1,154,531,246,129,122)",
        "    pools.Metaspace.max  (-1)",
        "    pools.Metaspace.usage  (0.95728)");
    // CHECKSTYLE.ON: LineLengthExceed
    List<String> testOutput = Arrays.asList(output.split("\n"));
    Assert.assertThat(testOutput,
        IsIterableContainingInOrder.contains(expectedOutput.toArray()));
  }
}
