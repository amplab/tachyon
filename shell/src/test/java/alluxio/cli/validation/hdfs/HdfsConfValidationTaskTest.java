package alluxio.cli.validation.hdfs;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import alluxio.cli.ValidationUtils;
import alluxio.cli.validation.ValidationTestUtils;
import alluxio.cli.validation.hdfs.HdfsConfValidationTask;
import alluxio.conf.InstancedConfiguration;
import alluxio.conf.PropertyKey;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

public class HdfsConfValidationTaskTest {
  private static InstancedConfiguration sConf;
  private static File sTestDir;

  @BeforeClass
  public static void initConf() throws IOException {
    sTestDir = ValidationTestUtils.prepareConfDir();
    sConf = InstancedConfiguration.defaults();
    sConf.set(PropertyKey.CONF_DIR, sTestDir.getAbsolutePath());
  }

  @Test
  public void loadedConf() {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key2", "value2"));

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.OK);
  }

  @Test
  public void missingCoreSiteXML() {
    // Only prepare hdfs-site.xml
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, hdfsSite);
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.FAILED);
    assertThat(result.getResult(), containsString("core-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("core-site.xml"));
  }

  @Test
  public void missingHdfsSiteXML() {
    // Only prepare core-site.xml
    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, coreSite);
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.FAILED);
    assertThat(result.getResult(), containsString("hdfs-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("hdfs-site.xml"));
  }

  @Test
  public void missingBoth() {
    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, "/conf/");
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.FAILED);
    assertThat(result.getResult(), containsString("hdfs-site.xml is not configured"));
    assertThat(result.getResult(), containsString("core-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("hdfs-site.xml"));
    assertThat(result.getAdvice(), containsString("core-site.xml"));
  }

  @Test
  public void cannotParseCoreSiteXml() throws IOException {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key2", "value2"));
    RandomAccessFile hdfsFile = new RandomAccessFile(hdfsSite, "rw");
    hdfsFile.setLength(hdfsFile.length() - 10);

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));
    RandomAccessFile coreFile = new RandomAccessFile(coreSite, "rw");
    coreFile.setLength(coreFile.length() - 10);

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.loadHdfsConfig();
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString(String.format("Failed to parse %s", hdfsSite)));
    assertThat(result.getResult(), containsString(String.format("Failed to parse %s", coreSite)));
    assertThat(result.getAdvice(), containsString(String.format("Please check your %s", hdfsSite)));
    assertThat(result.getAdvice(), containsString(String.format("Please check your %s", coreSite)));
  }

  @Test
  public void inconsistentConf() {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value2"));

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.validate(ImmutableMap.of());
    System.out.println(result);

    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString("key1"));
    assertThat(result.getResult(), containsString("value1 in core-site.xml"));
    assertThat(result.getResult(), containsString("value2 in hdfs-site.xml"));
    assertThat(result.getAdvice(), containsString("fix the inconsistency"));
  }

  @Test
  public void validConf() {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value1", "key3", "value3"));

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1", "key4", "value4"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationUtils.TaskResult result = task.validate(ImmutableMap.of());
    System.out.println(result);

    assertEquals(ValidationUtils.State.OK, result.getState());
  }

  @After
  public void resetConf() {
    sConf.unset(PropertyKey.UNDERFS_HDFS_CONFIGURATION);
  }

  @After
  public void removeConfFiles() {
    File hdfsSite = new File(sTestDir, "hdfs-site.xml");
    if (hdfsSite.exists()) {
      hdfsSite.delete();
    }
    File coreSite = new File(sTestDir, "core-site.xml");
    if (coreSite.exists()) {
      coreSite.delete();
    }
  }
}
