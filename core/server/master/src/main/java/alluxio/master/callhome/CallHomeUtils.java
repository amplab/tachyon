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

package alluxio.master.callhome;

import alluxio.Configuration;
import alluxio.PropertyKey;
import alluxio.RuntimeConstants;
import alluxio.exception.ExceptionMessage;
import alluxio.master.MasterProcess;
import alluxio.master.block.BlockMaster;
import alluxio.master.file.FileSystemMaster;
import alluxio.underfs.UnderFileSystem;
import alluxio.util.CommonUtils;
import alluxio.wire.WorkerInfo;

import java.io.IOException;
import java.net.NetworkInterface;
import java.security.GeneralSecurityException;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Nullable;

/**
 * This class encapsulates utilities for collecting diagnostic info.
 */
public final class CallHomeUtils {
  private CallHomeUtils() {}  // prevent instantiation

  /**
   * @param masterProcess the Alluxio master process
   * @param blockMaster the block master
   * @param fsMaster the file system master
   * @return the collected call home information, null if license hasn't been loaded
   * @throws IOException when failed to collect call home information
   */
  @Nullable
  public static CallHomeInfo collectDiagnostics(MasterProcess masterProcess,
      BlockMaster blockMaster, FileSystemMaster fsMaster)
      throws IOException, GeneralSecurityException {
    CallHomeInfo info = new CallHomeInfo();
    info.setFaultTolerant(Configuration.getBoolean(PropertyKey.ZOOKEEPER_ENABLED));
    info.setWorkerCount(blockMaster.getWorkerCount());
    List<WorkerInfo> workerInfos = blockMaster.getWorkerInfoList();
    info.setWorkerInfos(workerInfos.toArray(new WorkerInfo[workerInfos.size()]));
    info.setLostWorkerCount(blockMaster.getLostWorkerCount());
    List<WorkerInfo> lostWorkerInfos = blockMaster.getWorkerInfoList();
    info.setWorkerInfos(lostWorkerInfos.toArray(new WorkerInfo[lostWorkerInfos.size()]));
    info.setStartTime(masterProcess.getStartTimeMs());
    info.setUptime(masterProcess.getUptimeMs());
    info.setClusterVersion(RuntimeConstants.VERSION);
    // Set ufs information.
    String ufsRoot = Configuration.get(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS);
    UnderFileSystem ufs = UnderFileSystem.Factory.createForRoot();
    info.setUfsType(ufs.getUnderFSType());
    info.setUfsSize(ufs.getSpace(ufsRoot, UnderFileSystem.SpaceType.SPACE_TOTAL));
    // Set storage tiers.
    List<String> aliases = blockMaster.getGlobalStorageTierAssoc().getOrderedStorageAliases();
    java.util.Map<String, Long> tierSizes = blockMaster.getTotalBytesOnTiers();
    java.util.Map<String, Long> usedTierSizes = blockMaster.getUsedBytesOnTiers();
    List<CallHomeInfo.StorageTier> tiers = com.google.common.collect.Lists.newArrayList();
    for (String alias : aliases) {
      CallHomeInfo.StorageTier tier = new CallHomeInfo.StorageTier();
      if (tierSizes.containsKey(alias)) {
        tier.setAlias(alias);
        tier.setSize(tierSizes.get(alias));
        tier.setUsedSizeInBytes(usedTierSizes.get(alias));
        tiers.add(tier);
      }
    }
    info.setStorageTiers(tiers.toArray(new CallHomeInfo.StorageTier[tiers.size()]));
    // Set file system master info
    info.setMasterAddress(masterProcess.getRpcAddress().toString());
    info.setNumberOfPaths(fsMaster.getNumberOfPaths());
    info.setMachineId(getMACAddress(masterProcess));

    return info;
  }

  /**
   * @return the MAC address of the network interface being used by the master
   * @throws IOException when no MAC address is found
   */
  private static String getMACAddress(MasterProcess masterProcess) throws IOException {
    // Try to get the MAC address of the network interface of the master's RPC address.
    NetworkInterface nic =
            NetworkInterface.getByInetAddress(masterProcess.getRpcAddress().getAddress());
    byte[] mac = nic.getHardwareAddress();
    if (mac != null) {
      return CommonUtils.convertToHexString(mac);
    }

    // Try to get the MAC address of the common "en0" interface.
    nic = NetworkInterface.getByName("en0");
    mac = nic.getHardwareAddress();
    if (mac != null) {
      return CommonUtils.convertToHexString(mac);
    }

    // Try to get the first non-empty MAC address in the enumeration of all network interfaces.
    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
    while (ifaces.hasMoreElements()) {
      nic = ifaces.nextElement();
      if (nic == null) {
        continue;
      }
      mac = nic.getHardwareAddress();
      if (mac != null) {
        return CommonUtils.convertToHexString(mac);
      }
    }

    throw new IOException(ExceptionMessage.MAC_ADDRESS_NOT_FOUND.toString());
  }
}
