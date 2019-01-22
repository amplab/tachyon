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

package alluxio.master.file.contexts;

import alluxio.grpc.MountPOptions;
import alluxio.master.file.FileSystemMasterOptions;

import com.google.common.base.MoreObjects;

/**
 * Used to merge and wrap {@link MountPOptions}.
 */
public class MountContext extends OperationContext<MountPOptions.Builder> {

  /**
   * Creates context with given option data.
   *
   * @param optionsBuilder options builder
   */
  private MountContext(MountPOptions.Builder optionsBuilder) {
    super(optionsBuilder);
  }

  /**
   * Merges and embeds the given {@link MountPOptions} with the corresponding master options.
   *
   * @param optionsBuilder Builder for proto {@link MountPOptions} to embed
   * @return the instance of {@link MountContext} with default values for master
   */
  public static MountContext defaults(MountPOptions.Builder optionsBuilder) {
    MountPOptions masterOptions = FileSystemMasterOptions.mountDefaults();
    MountPOptions.Builder mergedOptionsBuilder =
        masterOptions.toBuilder().mergeFrom(optionsBuilder.build());
    return new MountContext(mergedOptionsBuilder);
  }

  /**
   * @return the instance of {@link MountContext} with default values for master
   */
  public static MountContext defaults() {
    MountPOptions masterOptions = FileSystemMasterOptions.mountDefaults();
    return new MountContext(masterOptions.toBuilder());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("ProtoOptions", getOptions().build())
        .toString();
  }
}
