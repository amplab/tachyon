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

package alluxio.client.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import alluxio.SystemPropertyRule;
import alluxio.conf.InstancedConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.master.MasterInquireClient;
import alluxio.security.User;
import alluxio.uri.MultiMasterAuthority;
import alluxio.uri.ZookeeperAuthority;
import alluxio.util.ConfigurationUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.reflect.Whitebox;

import java.io.Closeable;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.Subject;

public class FileSystemTest {

  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  @Before
  public void before() {
    ConfigurationUtils.reloadProperties();
  }

  @Test
  public void singleMasterClientCacheTest() {
    clientCacheTest();
  }

  @Test
  public void multiMasterClientCacheTest() {
    try (Closeable p = new SystemPropertyRule(PropertyKey.MASTER_RPC_ADDRESSES.getName(),
        "192.168.0.1:1234,192.168.0.2:1445,192.168.0.3:9943").toResource()) {
      ConfigurationUtils.reloadProperties();
      InstancedConfiguration conf = new InstancedConfiguration(ConfigurationUtils.defaults());
      MasterInquireClient.ConnectDetails connectDetails = MasterInquireClient.Factory.getConnectDetails(conf);
      // Make sure we have a MultiMaster authority
      assertTrue(connectDetails.toAuthority() instanceof MultiMasterAuthority);
      clientCacheTest();
    } catch (IOException e) {
      fail("Unable to set system properties");
    }
  }

  @Test
  public void zkClientCacheTest() {
    Map<String, String> sysProps = new HashMap<>();
    sysProps.put(PropertyKey.ZOOKEEPER_ENABLED.getName(), Boolean.toString(true));
    sysProps.put(PropertyKey.ZOOKEEPER_ADDRESS.getName(), "zk@192.168.0.5");
    sysProps.put(PropertyKey.ZOOKEEPER_ELECTION_PATH.getName(), "/leader");

    try (Closeable p = new SystemPropertyRule(sysProps).toResource()) {
      ConfigurationUtils.reloadProperties();
      InstancedConfiguration conf = new InstancedConfiguration(ConfigurationUtils.defaults());
      MasterInquireClient.ConnectDetails connectDetails =
          MasterInquireClient.Factory.getConnectDetails(conf);
      // Make sure we have a Zookeeper authority
      assertTrue(connectDetails.toAuthority() instanceof ZookeeperAuthority);
      clientCacheTest();
    } catch (IOException e) {
      fail("Unable to set system properties");
    }
  }

  @Test
  public void nullSubjectTest() throws Exception {
    mThrown.expect(NullPointerException.class);
    FileSystem.Factory.get(null);
  }

  public void clientCacheTest() {
    resetClientCache();
    FileSystem fs1 = FileSystem.Factory.get();
    FileSystem fs2 = FileSystem.Factory.get();
    assertEquals("Second client should have been retrieved from cache.", fs1, fs2);
    fs2 = FileSystem.Factory.get(new Subject());
    assertEquals("Passing empty subject should have given the same cached client", fs1, fs2);
    fs2 = FileSystem.Factory.get(createTestSubject("alluxio-test"));
    assertNotEquals("Passing filled subject should have given a new client", fs1, fs2);
    fs1 = FileSystem.Factory.get(createTestSubject("alluxio-test"));
    assertEquals("Second subject with same credentials should return cached client", fs1, fs2);
    fs2 = FileSystem.Factory.get(createTestSubject("alluxio-test-2"));
    assertNotEquals("Passing filled subject should have given a new client", fs1, fs2);
  }

  private Subject createTestSubject(String username) {
    User user = new User(username);
    Set<Principal> principals = new HashSet<>();
    principals.add(user);
    return new Subject(false, principals, new HashSet<>(), new HashSet<>());
  }


  public void resetClientCache() {
    Whitebox.setInternalState(FileSystem.Factory.class, "CLIENT_CACHE", new ConcurrentHashMap<>());
  }
}
