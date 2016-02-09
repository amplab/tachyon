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

package alluxio.shell.command;

import alluxio.AlluxioURI;
import alluxio.client.FileSystemTestUtils;
import alluxio.client.WriteType;
import alluxio.exception.AlluxioException;
import alluxio.shell.AbstractAlluxioShellTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests for chmodr command.
 */
public class ChmodrCommandTest extends AbstractAlluxioShellTest {

  @Test
  public void chmodrTest() throws IOException, AlluxioException {
    clearLoginUser();
    mFsShell.run("mkdir", "/testFolder1");
    FileSystemTestUtils.createByteFile(mFileSystem, "/testFolder1/testFile", WriteType.MUST_CACHE,
        10);
    mFsShell.run("chmodr", "777", "/testFolder1");
    int permission = mFileSystem.getStatus(new AlluxioURI("/testFolder1")).getPermission();
    Assert.assertEquals((short) 0777, permission);
    mFsShell.run("chmodr", "755", "/testFolder1");
    permission = mFileSystem.getStatus(new AlluxioURI("/testFolder1")).getPermission();
    Assert.assertEquals((short) 0755, permission);
  }

}
