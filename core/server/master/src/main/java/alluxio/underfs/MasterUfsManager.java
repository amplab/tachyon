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

package alluxio.underfs;

import alluxio.exception.ExceptionMessage;
import alluxio.exception.InvalidPathException;
import alluxio.util.UnderFileSystemUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A class that manages the UFS for master servers.
 */
@ThreadSafe
public final class MasterUfsManager extends AbstractUfsManager {
  private static final Logger LOG = LoggerFactory.getLogger(MasterUfsManager.class);

  // The physical ufs state for all active mounts if not the default
  private ConcurrentHashMap<String, UnderFileSystem.UfsMode> physicalUfsState =
      new ConcurrentHashMap<>();

  /**
   * Constructs the instance of {@link MasterUfsManager}.
   */
  public MasterUfsManager() {}

  @Override
  public void removeMount(long mountId) {
    super.removeMount(mountId);
    // TODO(adit): Remove ufs mode state any key in physicalUfsState is not active anymore
  }

  /**
   * Set the operation mode the given physical ufs.
   *
   * @param ufsPath the physical ufs path (scheme and authority only)
   * @param ufsMode the ufs operation mode
   * @throws InvalidPathException if no managed ufs covers the given path
   */
  public void setUfsMode(String ufsPath, UnderFileSystem.UfsMode ufsMode)
      throws InvalidPathException {
    LOG.info("Set ufs mode for {} to {}", ufsPath, ufsMode);
    for (UnderFileSystem ufs : mUnderFileSystemMap.values()) {
      if(ufs.isPathCovered(ufsPath)) {
        physicalUfsState.put(UnderFileSystemUtils.getPhysicalUfsPath(ufsPath), ufsMode);
        return;
      }
    }
    // No managed ufs uses the given physical ufs path
    throw new InvalidPathException(ExceptionMessage.UFS_PATH_IS_NOT_MOUNTED.getMessage(ufsPath));
  }
}
