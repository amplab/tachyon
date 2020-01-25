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

package alluxio.master.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import alluxio.conf.ServerConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.master.file.DefaultFileSystemMaster.Metrics;
import alluxio.metrics.MetricKey;
import alluxio.metrics.MetricsSystem;
import alluxio.resource.CloseableResource;
import alluxio.underfs.UfsManager;
import alluxio.underfs.UnderFileSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link DefaultFileSystemMaster.Metrics}.
 */
public class FileSystemMasterMetricsTest {
  private FileSystemMaster mFileSystemMaster;
  private UfsManager mUfsManager;

  @Before
  public void before() throws Exception {
    MetricsSystem.clearAllMetrics();
    mFileSystemMaster = Mockito.mock(FileSystemMaster.class);
    mUfsManager = Mockito.mock(UfsManager.class);
    Metrics.registerGauges(mFileSystemMaster, mUfsManager);
  }

  @Test
  public void testMetricsFilesPinned() {
    when(mFileSystemMaster.getNumberOfPinnedFiles()).thenReturn(100);
    assertEquals(100, getGauge(MetricKey.MASTER_FILES_PINNED.getName()));
  }

  @Test
  public void testMetricsPathsTotal() {
    when(mFileSystemMaster.getInodeCount()).thenReturn(90L);
    assertEquals(90L, getGauge(MetricKey.MASTER_TOTAL_PATHS.getName()));
  }

  @Test
  public void testMetricsUfsCapacity() throws Exception {
    UfsManager.UfsClient client = Mockito.mock(UfsManager.UfsClient.class);
    UnderFileSystem ufs = Mockito.mock(UnderFileSystem.class);
    String ufsDataFolder = ServerConfiguration.get(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS);
    when(ufs.getSpace(ufsDataFolder, UnderFileSystem.SpaceType.SPACE_TOTAL)).thenReturn(1000L);
    when(ufs.getSpace(ufsDataFolder, UnderFileSystem.SpaceType.SPACE_USED)).thenReturn(200L);
    when(ufs.getSpace(ufsDataFolder, UnderFileSystem.SpaceType.SPACE_FREE)).thenReturn(800L);
    when(client.acquireUfsResource()).thenReturn(new CloseableResource<UnderFileSystem>(ufs) {
      @Override
      public void close() {}
    });
    when(mUfsManager.getRoot()).thenReturn(client);
    assertEquals(1000L, getGauge(MetricKey.CLUSTER_UFS_CAPACITY_TOTAL.getName()));
    assertEquals(200L, getGauge(MetricKey.CLUSTER_UFS_CAPACITY_USED.getName()));
    assertEquals(800L, getGauge(MetricKey.CLUSTER_UFS_CAPACITY_FREE.getName()));
  }

  private Object getGauge(String name) {
    return MetricsSystem.METRIC_REGISTRY.getGauges()
        .get(MetricsSystem.attachHostToMetricsName(name)).getValue();
  }
}
