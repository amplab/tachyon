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

package alluxio.table.under.hive;

import alluxio.table.common.UdbContext;
import alluxio.table.common.UnderDatabase;
import alluxio.table.common.UnderDatabaseFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Factory to create database implementation.
 */
public class HiveDatabaseFactory implements UnderDatabaseFactory {
  public static final String TYPE = "hive";

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public UnderDatabase create(UdbContext udbContext, Map<String, String> configuration)
      throws IOException {
    return HiveDatabase.create(udbContext, configuration);
  }
}
