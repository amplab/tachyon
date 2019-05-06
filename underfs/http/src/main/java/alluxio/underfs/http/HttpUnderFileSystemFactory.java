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

package alluxio.underfs.http;

import alluxio.AlluxioURI;
import alluxio.conf.AlluxioConfiguration;
import alluxio.conf.InstancedConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.underfs.UnderFileSystem;
import alluxio.underfs.UnderFileSystemConfiguration;
import alluxio.underfs.UnderFileSystemFactory;
import alluxio.util.ConfigurationUtils;

import com.google.common.base.Preconditions;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Factory for creating {@link HttpUnderFileSystem}.
 */
@ThreadSafe
public class HttpUnderFileSystemFactory implements UnderFileSystemFactory {

  /**
   * Constructs a new {@link HttpUnderFileSystemFactory}.
   */
  public HttpUnderFileSystemFactory() {}

  @Override
  public UnderFileSystem create(String path, UnderFileSystemConfiguration conf,
      AlluxioConfiguration alluxioConf) {
    Preconditions.checkArgument(path != null, "path may not be null");
    return new HttpUnderFileSystem(new AlluxioURI(path), conf, alluxioConf);
  }

  @Override
  public boolean supportsPath(String path) {
    if (path != null) {
      // This loads the configuration from the JVM's system properties and the site properties file
      // on disk. Because of this, setting the property UNDERFS_HTTP_PREFIXES programatically *not*
      // work.
      AlluxioConfiguration alluxioConf = new InstancedConfiguration(ConfigurationUtils.defaults());

      for (final String prefix : alluxioConf.getList(PropertyKey.UNDERFS_HTTP_PREFIXES, ",")) {
        if (path.startsWith(prefix)) {
          return true;
        }
      }
    }
    return false;
  }
}
