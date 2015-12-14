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

package tachyon.security;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.reflect.Whitebox;

import tachyon.Constants;
import tachyon.LocalTachyonClusterResource;
import tachyon.client.worker.WorkerClient;
import tachyon.security.MasterClientAuthenticationIntegrationTest.NameMatchAuthenticationProvider;
import tachyon.worker.ClientMetrics;

/**
 * Test RPC authentication between worker and its client, in four modes: NOSASL, SIMPLE, CUSTOM,
 * KERBEROS.
 */
// TODO(bin): improve the way to set and isolate MasterContext/WorkerContext across test cases
public class WorkerClientAuthenticationIntegrationTest {
  @Rule
  public LocalTachyonClusterResource mLocalTachyonClusterResource =
      new LocalTachyonClusterResource(1000, 1000, Constants.GB);
  private ExecutorService mExecutorService;

  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  @Before
  public void before() throws Exception {
    mExecutorService = Executors.newFixedThreadPool(2);
    clearLoginUser();
  }

  @After
  public void after() throws Exception {
    System.clearProperty(Constants.SECURITY_LOGIN_USERNAME);
  }

  @Test
  @LocalTachyonClusterResource.Config(
      tachyonConfParams = {Constants.SECURITY_AUTHENTICATION_TYPE, "NOSASL"})
  public void noAuthenticationOpenCloseTest() throws Exception {
    authenticationOperationTest();
  }

  @Test
  @LocalTachyonClusterResource.Config(
      tachyonConfParams = {Constants.SECURITY_AUTHENTICATION_TYPE, "SIMPLE"})
  public void simpleAuthenticationOpenCloseTest() throws Exception {
    authenticationOperationTest();
  }

  @Test
  @LocalTachyonClusterResource.Config(
      tachyonConfParams = {Constants.SECURITY_AUTHENTICATION_TYPE, "CUSTOM",
          Constants.SECURITY_AUTHENTICATION_CUSTOM_PROVIDER,
          NameMatchAuthenticationProvider.FULL_CLASS_NAME},
      startCluster = false)
  public void customAuthenticationOpenCloseTest() throws Exception {
    /**
     * Using tachyon as loginUser for unit testing, only tachyon user is allowed to connect to
     * Tachyon Worker.
     */
    System.setProperty(Constants.SECURITY_LOGIN_USERNAME, "tachyon");
    mLocalTachyonClusterResource.start();
    authenticationOperationTest();
  }

  @Test(timeout = 10000)
  @LocalTachyonClusterResource.Config(tachyonConfParams = {Constants.SECURITY_AUTHENTICATION_TYPE,
      "CUSTOM", Constants.SECURITY_AUTHENTICATION_CUSTOM_PROVIDER,
      NameMatchAuthenticationProvider.FULL_CLASS_NAME}, startCluster = false)
  public void customAuthenticationDenyConnectTest() throws Exception {
    /**
     * Using tachyon as loginUser for unit testing, only tachyon user is allowed to connect to
     * Tachyon Master during starting cluster.
     */
    System.setProperty(Constants.SECURITY_LOGIN_USERNAME, "tachyon");
    mLocalTachyonClusterResource.start();

    // Using no-tachyon as loginUser to connect to Worker, the IOException will be thrown
    System.setProperty(Constants.SECURITY_LOGIN_USERNAME, "no-tachyon");
    // Clear the login user so that it will be reloaded and pick up our no-tachyon change
    clearLoginUser();
    mThrown.expect(IOException.class);
    mThrown.expectMessage("Failed to connect to the worker");

    WorkerClient workerClient =
        new WorkerClient(mLocalTachyonClusterResource.get().getWorkerAddress(), mExecutorService,
            mLocalTachyonClusterResource.get().getWorkerTachyonConf(), 1 /* fake session id */,
            true, new ClientMetrics());
    try {
      Assert.assertFalse(workerClient.isConnected());
      workerClient.connect();
    } finally {
      workerClient.close();
    }
  }

  /**
   * Test Tachyon Worker client connects or disconnects to the Worker.
   *
   * @throws Exception
   */
  private void authenticationOperationTest() throws Exception {
    WorkerClient workerClient =
        new WorkerClient(mLocalTachyonClusterResource.get().getWorkerAddress(), mExecutorService,
            mLocalTachyonClusterResource.get().getWorkerTachyonConf(), 1 /* fake session id */,
            true, new ClientMetrics());

    Assert.assertFalse(workerClient.isConnected());
    workerClient.connect();
    Assert.assertTrue(workerClient.isConnected());

    workerClient.close();
  }

  private void clearLoginUser() throws Exception {
    Whitebox.setInternalState(LoginUser.class, "sLoginUser", (User) null);
  }
}
