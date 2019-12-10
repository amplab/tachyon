package alluxio.cli.bundler.command;

import alluxio.cli.Command;
import alluxio.client.file.FileSystemContext;
import alluxio.conf.InstancedConfiguration;
import alluxio.util.ConfigurationUtils;
import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class AbstractInfoCollectorCommand implements Command {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractInfoCollectorCommand.class);

  FileSystemContext mFsContext;

  public AbstractInfoCollectorCommand(@Nullable FileSystemContext fsContext) {
    if (fsContext == null) {
      fsContext =
              FileSystemContext.create(new InstancedConfiguration(ConfigurationUtils.defaults()));
    }
    mFsContext = fsContext;
  }

  public String getDestDir(CommandLine cl) {
    String[] args = cl.getArgs();
    LOG.info(String.format("Args for %s: %s", getCommandName(), Arrays.toString(args)));
    String targetDir = getWorkingDirectory(args[0]);
    return targetDir;
  }

  public String getWorkingDirectory(String baseDirPath) {
    String workingDirPath =  Paths.get(baseDirPath, this.getCommandName()).toString();
    createWorkingDirIfNotExisting(workingDirPath);
    return workingDirPath;
  }

  public void createWorkingDirIfNotExisting(String path) {
    // mkdirs checks existence of the path
    File workingDir = new File(path);
    workingDir.mkdirs();
  }

  public boolean foundPreviousWork(String baseDirPath) {
    String workingDirPath = getWorkingDirectory(baseDirPath);
    File workingDir = new File(workingDirPath);

    // TODO(jiacheng): better idea?
    // If the working directory is not empty, assume previous work can be reused.
    if (workingDir.list().length == 0) {
      return false;
    }
    LOG.info(String.format("Working dir %s is not empty. Assume previous work has completed.",
            workingDirPath));
    return true;
  }

  public File getOutputFile(String baseDirPath, String fileName) throws IOException {
    String outputFilePath = Paths.get(getWorkingDirectory(baseDirPath), fileName).toString();
    File outputFile = new File(outputFilePath);
    if (!outputFile.exists()) {
      outputFile.createNewFile();
    }
    return outputFile;
  }

  // TODO(jiacheng): Use context instead of mConf
}
