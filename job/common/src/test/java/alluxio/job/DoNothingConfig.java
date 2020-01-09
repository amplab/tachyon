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

package alluxio.job;

import alluxio.job.plan.PlanConfig;

import com.google.common.base.MoreObjects;

/**
 * Config for a plan that does nothing.
 */
public class DoNothingConfig implements PlanConfig {

  public static final String NAME = "DoNothing";

  public DoNothingConfig() {}

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    return obj instanceof DoNothingConfig;
  }

  @Override
  public int hashCode() {
    return 42;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).toString();
  }

  @Override
  public String getName() {
    return NAME;
  }
}
