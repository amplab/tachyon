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

package alluxio.table.common.transform.action;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The action factory interface.
 */
public interface TransformActionFactory {

  default int getOrder() {
    return 100;
  }

  /**
   * @param definition the raw definition of the action
   * @return a new instance of an action
   */
  @Nullable
  TransformAction create(Properties definition);
}
