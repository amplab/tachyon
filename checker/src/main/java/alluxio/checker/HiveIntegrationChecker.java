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

package alluxio.checker;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Some Hive queries to test the integration of Hive with Alluxio.
 * Supports two options: use Alluxio as an option to store Hive tables
 * and use Alluxio as the default filesystem.
 *
 * This checker requires a running Hadoop cluster, but does not require a running Alluxio cluster.
 * It will check whether Alluxio classes and Alluxio filesystem can be recognized
 * in Hadoop task nodes.
 */
public class HiveIntegrationChecker {
  private static final Logger LOG = LoggerFactory.getLogger(HiveIntegrationChecker.class);
  private static final String FAIL_TO_FIND_CLASS_MESSAGE = "Please distribute "
      + "the Alluxio client jar on the classpath of the application across different nodes.\n\n"
      + "Please set HIVE_AUX_JARS_PATH either in shell or conf/hive-env.sh."
      + "For details, please refer to: "
      + "https://www.alluxio.org/docs/master/en/Running-Hive-with-Alluxio.html\n";
  private static final String FAIL_TO_FIND_FS_MESSAGE = "Please check the fs.alluxio.impl property "
      + "in core-site.xml file of your Hadoop installation.\n\n"
      + "For details, please refer to: "
      + "https://www.alluxio.org/docs/master/en/Debugging-Guide.html\n";
  private static final String TEST_FAILED_MESSAGE = "***** Integration test failed. *****\n";
  private static final String TEST_PASSED_MESSAGE = "***** Integration test passed. *****\n";

  @Parameter(names = {"-alluxiourl"}, description = "the alluxio cluster url of form "
      + "alluxio://master_hostname:port")
  private String mAlluxioURL = "";

  @Parameter(names = {"-mode"}, description = "1 means use Alluxio as one option "
      + "to store Hive tables, 2 means use Alluxio as the Hive default filesystem")
  private int mUserMode = 1;

  @Parameter(names = {"-hiveurl"}, description = "a database url "
      + "of the form jdbc:subprotocol:subname")
  private String mHiveURL = "";

  @Parameter(names = {"-user"}, description = "the database user on whose behalf "
      + "the connection is being made")
  private String mHiveUserName = System.getProperty("user.name");

  @Parameter(names = {"-password"}, description = "the user's password")
  private String mHiveUserPassword = "";

  /**
   * Implements Hive with Alluxio integration checker.
   *
   * @param reportWriter save user-facing messages to a generated file
   * @return 0 means success, 2 means input not valid, 1 means have other errors
   */
  private int run(PrintWriter reportWriter) throws Exception {
    // Supports Alluxio HA mode
    if (!CheckerUtils.supportAlluxioHA(reportWriter)) {
      reportWriter.println(TEST_FAILED_MESSAGE);
      return 1;
    }

    // Try to connect to Hive through JDBC
    Connection con;
    try {
      con = DriverManager.getConnection(mHiveURL, mHiveUserName, mHiveUserPassword);
    } catch (Exception e) {
      reportWriter.println("Unable to connect to Hive, please check "
          + "your Hive URL, username and password.\n");
      reportWriter.println(TEST_FAILED_MESSAGE);
      return 2;
    }

    // Hive statements to check the integration
    try {
      String tableName = "hiveTestTable";

      final String DROPTABLE = "drop table if exists ?";
      try (PreparedStatement dropTablePS = con.prepareStatement(DROPTABLE)) {
        dropTablePS.setString(1, tableName);
        dropTablePS.execute();
      }

      // Creates test table based on different integration ways
      if (mUserMode == 1) {
        useAsSource(con, tableName);
      } else {
        useAsUnderFS(con, tableName);
      }

      final String DESCRIBETABLE = "describe ?";
      try (PreparedStatement describeTablePS = con.prepareStatement(DESCRIBETABLE)) {
        describeTablePS.setString(1, tableName);
        describeTablePS.execute();
      }

      final String SHOWTABLE = "select * from ?";
      try (PreparedStatement showTablePS = con.prepareStatement(SHOWTABLE)) {
        showTablePS.setString(1, tableName);
        showTablePS.execute();
      }
    } catch (Exception e) {
      printExceptionReport(e, reportWriter);
      return 1;
    } finally {
      con.close();
    }

    reportWriter.println("Congratulations, you have configured Hive with Alluxio correctly!\n");
    reportWriter.println(TEST_PASSED_MESSAGE);
    return 0;
  }

  /**
   * Supports the first integration way: use Alluxio as one option to store Hive tables.
   *
   * @param con the Hive connection
   * @param tableName the name of the test table
   */
  private void useAsSource(Connection con, String tableName) throws Exception {
    final String CREATETABLE = "CREATE TABLE ? (ROW1 STRING, ROW2 STRING) "
        + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'"
        + "LOCATION '?/alluxioTestFolder/'";
    try (PreparedStatement createTablePS = con.prepareStatement(CREATETABLE)) {
      createTablePS.setString(1, tableName);
      createTablePS.setString(2, mAlluxioURL);
      createTablePS.execute();
    }
  }

  /**
   * Supports the second integration way: use Alluxio as the default filesystem.
   *
   * @param con the Hive connection
   * @param tableName the name of the test table
   */
  private void useAsUnderFS(Connection con, String tableName) throws Exception {
    final String CREATETABLE = "CREATE TABLE ? (ROW1 STRING, ROW2 STRING) "
        + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'"
        + "STORED AS TEXTFILE";
    try (PreparedStatement createTablePS = con.prepareStatement(CREATETABLE)) {
      createTablePS.setString(1, tableName);
      createTablePS.execute();
    }
    final String LOADDATA = "LOAD DATA LOCAL INPATH './hiveTestTable' "
        + "OVERWRITE INTO TABLE ?";
    try (PreparedStatement loadTablePS = con.prepareStatement(LOADDATA)) {
      loadTablePS.setString(1, tableName);
      loadTablePS.execute();
    }
  }

  /**
   * Translates the exception message to user-facing message.
   *
   * @param exception the thrown exception when running Hive queries
   * @param reportWriter save user-facing messages to a generated file
   */
  private void printExceptionReport(Exception exception, PrintWriter reportWriter) {
    String exceptionStr = exception.toString();
    if (exceptionStr.contains("Class alluxio.hadoop.FileSystem not found")) {
      reportWriter.println(FAIL_TO_FIND_CLASS_MESSAGE);
      reportWriter.println(TEST_FAILED_MESSAGE);
    } else if (exceptionStr.contains("No FileSystem for scheme \"alluxio\"")) {
      reportWriter.println(FAIL_TO_FIND_FS_MESSAGE);
      reportWriter.println(TEST_FAILED_MESSAGE);
    } else {
      reportWriter.println("Other errors occur, please fix it and "
          + "rerun the Hive Integration Checker. \n");
      exception.printStackTrace(reportWriter);
      reportWriter.println(TEST_FAILED_MESSAGE);
    }
  }

  /**
   * Checks if the input arguments are valid.
   *
   * @return true if input is valid, false otherwise
   */
  private boolean checkIfInputValid() {
    if (mUserMode != 1 && mUserMode != 2) {
      System.out.println("Please set the correct user mode.");
      return false;
    }
    if (!mHiveURL.startsWith("jdbc")) {
      System.out.println("Please set the correct Hive URL.");
      return false;
    }
    return true;
  }

  /**
   * Main function will be triggered by hive-checker.sh.
   *
   * @param args the arguments to connect to Hive and define the test mode
   */
  public static void main(String[] args) throws Exception {
    HiveIntegrationChecker checker = new HiveIntegrationChecker();
    JCommander jCommander = new JCommander(checker, args);
    jCommander.setProgramName("HiveIntegrationChecker");

    if (!checker.checkIfInputValid()) {
      System.exit(2);
    }

    PrintWriter reportWriter = CheckerUtils.initReportFile();

    int result = checker.run(reportWriter);

    reportWriter.close();
    System.exit(result);
  }
}
