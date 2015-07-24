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

package tachyon.security.authentication;

import javax.security.sasl.AuthenticationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tachyon.Constants;

public class CustomAuthenticationProviderImplTest {
  @Rule
  public ExpectedException mThrown = ExpectedException.none();

  @Test
  public void classNotFoundTest() throws Exception {
    String notExistClass = "tachyon.test.custom.provider";
    System.setProperty(Constants.TACHYON_AUTHENTICATION_PROVIDER_CUSTOM_CLASS,
        notExistClass);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(notExistClass + " not found");
    new CustomAuthenticationProviderImpl();
  }

  @Test
  public void classNotProviderInterfaceTest() throws Exception {
    System.setProperty(Constants.TACHYON_AUTHENTICATION_PROVIDER_CUSTOM_CLASS,
        CustomAuthenticationProviderImplTest.class.getName());
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(CustomAuthenticationProviderImplTest.class.getName()
        + " isn't implement AuthenticationProvider");
    new CustomAuthenticationProviderImpl();
  }

  @Test
  public void createCustomeProviderTest() throws Exception {
    System.setProperty(Constants.TACHYON_AUTHENTICATION_PROVIDER_CUSTOM_CLASS,
        NoopAuthenticationProvider.class.getName());
    new CustomAuthenticationProviderImpl();
  }

  public static class NoopAuthenticationProvider implements AuthenticationProvider {
    @Override
    public void authenticate(String user, String password) throws AuthenticationException {
      // noop
    }
  }
}
