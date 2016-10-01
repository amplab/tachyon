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

package alluxio.master.file.options;

import alluxio.CommonTestUtils;
import alluxio.wire.TtlAction;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Unit tests for {@link SetAttributeOptions}.
 */
public class SetAttributeOptionsTest {
  /**
   * Tests the {@link SetAttributeOptions#defaults()} method.
   */
  @Test
  public void defaults() {
    SetAttributeOptions options = SetAttributeOptions.defaults();

    Assert.assertNull(options.getPinned());
    Assert.assertNull(options.getTtl());
    Assert.assertEquals(TtlAction.DELETE, options.getTtlAction());
    Assert.assertNull(options.getPersisted());
  }

  /**
   * Tests getting and setting fields.
   */
  @Test
  public void fields() {
    Random random = new Random();
    Boolean pinned = random.nextBoolean();
    Long ttl = random.nextLong();
    Boolean persisted = random.nextBoolean();

    SetAttributeOptions options = SetAttributeOptions.defaults().setPinned(pinned).setTtl(ttl)
        .setTtlAction(TtlAction.FREE).setPersisted(persisted);

    Assert.assertEquals(pinned, options.getPinned());
    Assert.assertEquals(ttl, options.getTtl());
    Assert.assertEquals(TtlAction.FREE, options.getTtlAction());
    Assert.assertEquals(persisted, options.getPersisted());
  }

  @Test
  public void equalsTest() throws Exception {
    CommonTestUtils.testEquals(SetAttributeOptions.class, "mOperationTimeMs");
  }
}
