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

package alluxio.client.file.policy;

import alluxio.CommonTestUtils;
import alluxio.Constants;
import alluxio.client.block.BlockWorkerInfo;
import alluxio.wire.WorkerNetAddress;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests {@link RoundRobinPolicy}.
 */
public final class RoundRobinPolicyTest {
  private static final int PORT = 1;

  /**
   * Tests that the correct workers are chosen when round robin is used.
   */
  @Test
  public void getWorkerWithAvailbility() {
    List<BlockWorkerInfo> workerInfoList = new ArrayList<>();
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker1")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), 3 * (long) Constants.GB,
        2 * (long) Constants.GB));
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker2")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), 2 * (long) Constants.GB, 0));
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker3")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), 3 * (long) Constants.GB, 0));
    RoundRobinPolicy policy = new RoundRobinPolicy();

    Assert.assertNotEquals(
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost(),
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost());

    for (int i = 0; i < 100; i++) {
      // Make sure we don't get worker1 as it doesn't have enough capacity.
      Assert.assertNotEquals("worker1",
          policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost());
    }
  }

  /**
   * Tests that the correct worker is chosen when all workers are full.
   */
  @Test
  public void getWorkerWithoutAvailbility() {
    List<BlockWorkerInfo> workerInfoList = new ArrayList<>();
    workerInfoList.add(new BlockWorkerInfo(
        new WorkerNetAddress().setHost("worker1").setRpcPort(PORT).setDataPort(PORT)
            .setWebPort(PORT), 3 * (long) Constants.GB, 2 * (long) Constants.GB));
    workerInfoList.add(new BlockWorkerInfo(
        new WorkerNetAddress().setHost("worker2").setRpcPort(PORT).setDataPort(PORT)
            .setWebPort(PORT), 2 * (long) Constants.GB, 2 * (long) Constants.GB));
    workerInfoList.add(new BlockWorkerInfo(
        new WorkerNetAddress().setHost("worker3").setRpcPort(PORT).setDataPort(PORT)
            .setWebPort(PORT), 3 * (long) Constants.GB, 2 * (long) Constants.GB));
    RoundRobinPolicy policy = new RoundRobinPolicy();

    Assert.assertNotEquals(
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost(),
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost());

    boolean worker1Chosen = false;
    for (int i = 0; i < 100; i++) {
      worker1Chosen =
          policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost()
              .equals("worker1");
    }
    Assert.assertTrue(worker1Chosen);
  }

  @Test
  public void equalsTest() throws Exception {
    CommonTestUtils.testEquals(RoundRobinPolicy.class);
  }
}
