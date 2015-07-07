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

package tachyon.security;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tachyon.Constants;

/**
 * This class wraps a JAAS Subject and provides methods to determine the user's name. It supports
 * Windows, Unix, and Kerberos login modules.
 */
public class UserInformation {
  private static final Logger LOG = LoggerFactory.getLogger(Constants.LOGGER_TYPE);

  private static final String OS_LOGIN_MODULE_NAME;
  private static final Class<? extends Principal> OS_PRINCIPAL_CLASS;
  private static final boolean WINDOWS = System.getProperty("os.name").startsWith("Windows");
  private static final boolean IS_64_BIT = System.getProperty("os.arch").contains("64");
  private static final boolean AIX = System.getProperty("os.name").equals("AIX");
  public static final String JAVA_VENDOR_NAME = System.getProperty("java.vendor");
  public static final boolean IBM_JAVA = JAVA_VENDOR_NAME.contains("IBM");

  static {
    OS_LOGIN_MODULE_NAME = getOSLoginModuleName();
    OS_PRINCIPAL_CLASS = getOsPrincipalClass();
  }

  // Return the OS login module class name.
  private static String getOSLoginModuleName() {
    if (IBM_JAVA) {
      if (WINDOWS) {
        return IS_64_BIT ? "com.ibm.security.auth.module.Win64LoginModule"
            : "com.ibm.security.auth.module.NTLoginModule";
      } else if (AIX) {
        return IS_64_BIT ? "com.ibm.security.auth.module.AIX64LoginModule"
            : "com.ibm.security.auth.module.AIXLoginModule";
      } else {
        return "com.ibm.security.auth.module.LinuxLoginModule";
      }
    } else {
      return WINDOWS ? "com.sun.security.auth.module.NTLoginModule"
          : "com.sun.security.auth.module.UnixLoginModule";
    }
  }

  // Return the OS principal class
  static Class<? extends Principal> getOsPrincipalClass() {
    ClassLoader cl = ClassLoader.getSystemClassLoader();
    try {
      String principalClass = null;
      if (IBM_JAVA) {
        if (IS_64_BIT) {
          principalClass = "com.ibm.security.auth.UsernamePrincipal";
        } else {
          if (WINDOWS) {
            principalClass = "com.ibm.security.auth.NTUserPrincipal";
          } else if (AIX) {
            principalClass = "com.ibm.security.auth.AIXPrincipal";
          } else {
            principalClass = "com.ibm.security.auth.LinuxPrincipal";
          }
        }
      } else {
        principalClass = WINDOWS ? "com.sun.security.auth.NTUserPrincipal"
            : "com.sun.security.auth.UnixPrincipal";
      }
      return (Class<? extends Principal>) cl.loadClass(principalClass);
    } catch (ClassNotFoundException e) {
      LOG.error("Unable to find JAAS classes:" + e.getMessage());
    }
    return null;
  }

  /**
   * A JAAS configuration that defines the login modules, by which we use to login.
   */
  static class TachyonJaasConfiguration extends Configuration {
    private static final Map<String, String> BASIC_JAAS_OPTIONS = new HashMap<String,String>();

    private static final AppConfigurationEntry OS_SPECIFIC_LOGIN =
        new AppConfigurationEntry(OS_LOGIN_MODULE_NAME,
            AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, BASIC_JAAS_OPTIONS);

    // TODO: define AppConfigurationEntry TACHYON_LOGIN

    private static final AppConfigurationEntry[] SIMPLE = new
        AppConfigurationEntry[]{OS_SPECIFIC_LOGIN};

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String appName) {
      // TODO: switch branch based on appName. (Simple, Kerberos, etc)
      return SIMPLE;
    }
  }
}
