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

package alluxio.cli.fs.command;

import alluxio.AlluxioURI;
import alluxio.Constants;
import alluxio.cli.CommandUtils;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;
import alluxio.client.file.options.OpenFileOptions;
import alluxio.exception.AlluxioException;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.status.InvalidArgumentException;
import alluxio.util.FormatUtils;

import com.google.common.base.Preconditions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Prints the file's first n bytes (by default, 1KB) to the console.
 */
@ThreadSafe
public final class HeadCommand extends WithWildCardPathCommand {

  /**
   * @param fs the filesystem of Alluxio
   */
  public HeadCommand(FileSystem fs) {
    super(fs);
  }

  @Override
  public String getCommandName() {
    return "head";
  }

  @Override
  public void checkArgs(CommandLine cl) throws InvalidArgumentException {
    CommandUtils.checkNumOfArgsEquals(this, cl, 1);
  }

  @Override
  protected void runCommand(AlluxioURI path, CommandLine cl) throws AlluxioException, IOException {
    URIStatus status = mFileSystem.getStatus(path);
    int numOfBytes = Constants.KB;
    if (cl.hasOption('c')) {
      numOfBytes = (int) FormatUtils.parseSpaceSize(cl.getOptionValue('c'));
      Preconditions.checkArgument(numOfBytes > 0, "specified bytes must be > 0");
    }

    if (status.isFolder()) {
      throw new IOException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(path));
    }
    OpenFileOptions options = OpenFileOptions.defaults();
    try (FileInStream is = mFileSystem.openFile(path, options)) {
      long bytesToRead;
      if (status.getLength() > numOfBytes) {
        bytesToRead = numOfBytes;
      } else {
        bytesToRead = status.getLength();
      }

      byte[] buf = new byte[(int) bytesToRead];
      int read = is.read(buf);
      if (read != -1) {
        System.out.write(buf, 0, read);
      }
    }
  }

  @Override
  public String getUsage() {
    return "head -c <number of bytes> <path>";
  }

  @Override
  public String getDescription() {
    return "Prints the file's first n bytes (by default, 1KB) to the console.";
  }

  @Override
  public Options getOptions() {
    Option bytesOption =
        Option.builder("c").required(false).numberOfArgs(1).desc("user specified option").build();
    return new Options().addOption(bytesOption);
  }
}
