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

package tachyon.client.file.options;

import org.junit.Test;

/**
 * Tests for the {@link OpenOptions} class.
 */
public class OpenOptionsTest {

  /**
   * Tests that building an {@link OpenOptions} works.
   */
  @Test
  public void builderTest() {
    new OpenOptions.Builder().build();
  }

  /**
   * Tests that building an {@link OpenOptions} with the defaults works.
   */
  @Test
  public void defaultsTest() {
    OpenOptions.defaults();
  }
}
