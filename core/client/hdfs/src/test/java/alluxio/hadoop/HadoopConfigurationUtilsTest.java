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

package alluxio.hadoop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import alluxio.ConfigurationTestUtils;
import alluxio.conf.InstancedConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.conf.Source;

import org.junit.After;
import org.junit.Test;

/**
 * Tests for the {@link HadoopConfigurationUtils} class.
 */
public final class HadoopConfigurationUtilsTest {
  private static final String TEST_UNDERFS_S3_SIGNER_ALGORITHM = "12345";
  private static final String TEST_ALLUXIO_PROPERTY = "alluxio.unsupported.parameter";
  private static final String TEST_ALLUXIO_VALUE = "alluxio.unsupported.value";
  private InstancedConfiguration mConf = ConfigurationTestUtils.defaults();

  @After
  public void after() {
    mConf = ConfigurationTestUtils.defaults();
  }

  /**
   * Test for the {@link HadoopConfigurationUtils#mergeHadoopConfiguration} method for an empty
   * configuration.
   */
  @Test
  public void mergeEmptyHadoopConfiguration() {
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    long beforeSize = mConf.toMap().size();
    mConf = HadoopConfigurationUtils.mergeHadoopConfiguration(hadoopConfig, mConf.copyProperties());
    long afterSize = mConf.toMap().size();
    assertEquals(beforeSize, afterSize);
    assertFalse(mConf.getBoolean(PropertyKey.ZOOKEEPER_ENABLED));
  }

  /**
   * Test for the {@link HadoopConfigurationUtils#mergeHadoopConfiguration} method.
   */
  @Test
  public void mergeHadoopConfiguration() {
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(PropertyKey.UNDERFS_S3_SIGNER_ALGORITHM.toString(),
            TEST_UNDERFS_S3_SIGNER_ALGORITHM);
    hadoopConfig.set(TEST_ALLUXIO_PROPERTY, TEST_ALLUXIO_VALUE);
    hadoopConfig.setBoolean(PropertyKey.ZOOKEEPER_ENABLED.getName(), true);
    hadoopConfig.set(PropertyKey.ZOOKEEPER_ADDRESS.getName(),
        "host1:port1,host2:port2;host3:port3");

    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");
    mConf = HadoopConfigurationUtils.mergeHadoopConfiguration(hadoopConfig,
            mConf.copyProperties());
    assertEquals(TEST_UNDERFS_S3_SIGNER_ALGORITHM,
            mConf.get(PropertyKey.UNDERFS_S3_SIGNER_ALGORITHM));
    assertEquals(Source.RUNTIME, mConf.getSource(PropertyKey.UNDERFS_S3_SIGNER_ALGORITHM));
    assertTrue(mConf.getBoolean(PropertyKey.ZOOKEEPER_ENABLED));
    assertEquals("host1:port1,host2:port2;host3:port3",
        mConf.get(PropertyKey.ZOOKEEPER_ADDRESS));
  }
}
