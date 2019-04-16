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

package alluxio.server.auth;

import static org.junit.Assert.assertEquals;

import alluxio.AlluxioURI;
import alluxio.conf.PropertyKey;
import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.status.PermissionDeniedException;
import alluxio.master.MasterRegistry;
import alluxio.master.file.FileSystemMaster;
import alluxio.master.file.contexts.GetStatusContext;
import alluxio.security.LoginUserTestUtils;
import alluxio.security.authentication.AuthType;
import alluxio.security.authentication.AuthenticatedClientUser;
import alluxio.testutils.BaseIntegrationTest;
import alluxio.testutils.LocalAlluxioClusterResource;
import alluxio.testutils.master.MasterTestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for starting a cluster when security is enabled.
 */
public final class ClusterInitializationIntegrationTest extends BaseIntegrationTest {
  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  private static final String SUPER_USER = "alluxio";
  private static final String USER = "jack";

  private static final AlluxioURI ROOT = new AlluxioURI("/");

  @Rule
  public LocalAlluxioClusterResource mLocalAlluxioClusterResource =
      new LocalAlluxioClusterResource.Builder().build()
      .setProperty(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.name())
      .setProperty(PropertyKey.SECURITY_AUTHORIZATION_PERMISSION_ENABLED, "true");

  /**
   * When a user starts a new cluster, an empty root dir is created and owned by the user.
   */
  @Test
  @LocalAlluxioClusterResource.Config(
      confParams = {PropertyKey.Name.SECURITY_LOGIN_USERNAME, SUPER_USER})
  public void startCluster() throws Exception {
    FileSystem fs = mLocalAlluxioClusterResource.get().getClient();
    URIStatus status = fs.getStatus(ROOT);
    assertEquals(SUPER_USER, status.getOwner());
    assertEquals(0755, status.getMode());

    assertEquals(0, fs.listStatus(new AlluxioURI("/")).size());
  }

  /**
   * When a user starts a cluster with journal logs, which are generated by previous running
   * cluster owned by the same user, it should succeed.
   */
  @Test
  @LocalAlluxioClusterResource.Config(
      confParams = {PropertyKey.Name.SECURITY_LOGIN_USERNAME, SUPER_USER})
  public void recoverClusterSuccess() throws Exception {
    FileSystem fs = mLocalAlluxioClusterResource.get().getClient();
    fs.createFile(new AlluxioURI("/testFile")).close();
    mLocalAlluxioClusterResource.get().stopFS();

    LoginUserTestUtils.resetLoginUser(SUPER_USER);

    // user alluxio can recover master from journal
    MasterRegistry registry = MasterTestUtils.createLeaderFileSystemMasterFromJournal();
    FileSystemMaster fileSystemMaster = registry.get(FileSystemMaster.class);

    AuthenticatedClientUser.set(SUPER_USER);
    assertEquals(SUPER_USER, fileSystemMaster
        .getFileInfo(new AlluxioURI("/testFile"), GetStatusContext.defaults()).getOwner());
    registry.stop();
  }

  /**
   * When a user starts a cluster with journal logs, which are generated by previous running
   * cluster owned by a different user, it should fail and throw an exception.
   */
  @Test
  @LocalAlluxioClusterResource.Config(
      confParams = {PropertyKey.Name.SECURITY_LOGIN_USERNAME, SUPER_USER})
  public void recoverClusterFail() throws Exception {
    FileSystem fs = mLocalAlluxioClusterResource.get().getClient();
    fs.createFile(new AlluxioURI("/testFile")).close();
    mLocalAlluxioClusterResource.get().stopFS();

    LoginUserTestUtils.resetLoginUser(USER);

    mThrown.expect(PermissionDeniedException.class);
    mThrown.expectMessage(ExceptionMessage.PERMISSION_DENIED
        .getMessage("Unauthorized user on root"));
    // user jack cannot recover master from journal, in which the root is owned by alluxio.
    MasterTestUtils.createLeaderFileSystemMasterFromJournal();
  }
}
