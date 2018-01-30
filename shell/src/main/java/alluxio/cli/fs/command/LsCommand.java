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
import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;
import alluxio.client.file.options.ListStatusOptions;
import alluxio.exception.AlluxioException;
import alluxio.exception.ExceptionMessage;
import alluxio.exception.status.InvalidArgumentException;
import alluxio.util.CommonUtils;
import alluxio.util.FormatUtils;
import alluxio.util.SecurityUtils;
import alluxio.wire.LoadMetadataType;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Displays information for the path specified in args. Depends on different options, this command
 * can also display the information for all directly children under the path, or recursively.
 */
@ThreadSafe
public final class LsCommand extends WithWildCardPathCommand {
  public static final String IN_ALLUXIO_STATE_DIR = "DIR";
  public static final String IN_ALLUXIO_STATE_FILE_FORMAT = "%d%%";
  public static final String LS_FORMAT_PERMISSION = "%-11s";
  public static final String LS_FORMAT_FILE_SIZE = "%15s";
  public static final String LS_FORMAT_CREATE_TIME = "%24s";
  public static final String LS_FORMAT_ALLUXIO_STATE = "%5s";
  public static final String LS_FORMAT_PERSISTENCE_STATE = "%16s";
  public static final String LS_FORMAT_USER_NAME = "%-15s";
  public static final String LS_FORMAT_GROUP_NAME = "%-15s";
  public static final String LS_FORMAT_FILE_PATH = "%-5s";
  public static final String LS_FORMAT_NO_ACL = LS_FORMAT_FILE_SIZE + LS_FORMAT_PERSISTENCE_STATE
      + LS_FORMAT_CREATE_TIME + LS_FORMAT_ALLUXIO_STATE + " " + LS_FORMAT_FILE_PATH + "%n";
  public static final String LS_FORMAT = LS_FORMAT_PERMISSION + LS_FORMAT_USER_NAME
      + LS_FORMAT_GROUP_NAME + LS_FORMAT_FILE_SIZE + LS_FORMAT_PERSISTENCE_STATE
      + LS_FORMAT_CREATE_TIME + LS_FORMAT_ALLUXIO_STATE + " " + LS_FORMAT_FILE_PATH + "%n";

  private static final Option FORCE_OPTION =
      Option.builder("f")
          .required(false)
          .hasArg(false)
          .desc("force to load metadata for immediate children in a directory")
          .build();

  private static final Option LIST_DIR_AS_FILE_OPTION =
      Option.builder("d")
          .required(false)
          .hasArg(false)
          .desc("list directories as plain files")
          .build();

  private static final Option LIST_HUMAN_READABLE_OPTION =
      Option.builder("h")
          .required(false)
          .hasArg(false)
          .desc("print human-readable format sizes")
          .build();

  private static final Option LIST_PINNED_FILES_OPTION =
      Option.builder("p")
          .required(false)
          .hasArg(false)
          .desc("list all pinned files")
          .build();

  private static final Option RECURSIVE_OPTION =
      Option.builder("R")
          .required(false)
          .hasArg(false)
          .desc("list subdirectories recursively")
          .build();

  private static final Option SORT_OPTION =
      Option.builder()
              .required(false)
              .longOpt("sort")
              .hasArg(true)
              .desc("sort statuses by the given field "
                      + "{size|creationTime|inMemoryPercentage|lastModificationTime|path}")
              .build();

  private static final Map<String, Comparator<URIStatus>> SORT_FIELD_COMPARATORS = new HashMap<>();

  static {
    SORT_FIELD_COMPARATORS.put("size", Comparator.comparingLong(URIStatus::getBlockSizeBytes));
    SORT_FIELD_COMPARATORS.put("creationTime",
            Comparator.comparingLong(URIStatus::getCreationTimeMs));
    SORT_FIELD_COMPARATORS.put("inMemoryPercentage",
            Comparator.comparingLong(URIStatus::getInMemoryPercentage));
    SORT_FIELD_COMPARATORS.put("lastModificationTime",
            Comparator.comparingLong(URIStatus::getLastModificationTimeMs));
    SORT_FIELD_COMPARATORS.put("path", Comparator.comparing(URIStatus::getPath));
  }

  /**
   * Formats the ls result string.
   *
   * @param hSize print human-readable format sizes
   * @param acl whether security is enabled
   * @param isFolder whether this path is a file or a folder
   * @param permission permission string
   * @param userName user name
   * @param groupName group name
   * @param size size of the file in bytes
   * @param createTimeMs the epoch time in ms when the path is created
   * @param inAlluxioPercentage whether the file is in Alluxio
   * @param persistenceState the persistence state of the file
   * @param path path of the file or folder
   * @return the formatted string according to acl and isFolder
   */
  public static String formatLsString(boolean hSize, boolean acl, boolean isFolder, String
      permission,
      String userName, String groupName, long size, long createTimeMs, int inAlluxioPercentage,
      String persistenceState, String path) {
    String inAlluxioState;
    String sizeStr;
    if (isFolder) {
      inAlluxioState = IN_ALLUXIO_STATE_DIR;
      sizeStr = String.valueOf(size);
    } else {
      inAlluxioState = String.format(IN_ALLUXIO_STATE_FILE_FORMAT, inAlluxioPercentage);
      sizeStr = hSize ? FormatUtils.getSizeFromBytes(size) : String.valueOf(size);
    }

    if (acl) {
      return String.format(LS_FORMAT, permission, userName, groupName,
          sizeStr, persistenceState, CommonUtils.convertMsToDate(createTimeMs),
          inAlluxioState, path);
    } else {
      return String.format(LS_FORMAT_NO_ACL, sizeStr,
          persistenceState, CommonUtils.convertMsToDate(createTimeMs), inAlluxioState, path);
    }
  }

  private void printLsString(URIStatus status, boolean hSize) {
    System.out.print(formatLsString(hSize, SecurityUtils.isSecurityEnabled(),
        status.isFolder(), FormatUtils.formatMode((short) status.getMode(), status.isFolder()),
        status.getOwner(), status.getGroup(), status.getLength(), status.getCreationTimeMs(),
        status.getInAlluxioPercentage(), status.getPersistenceState(), status.getPath()));
  }

  /**
   * Constructs a new instance to display information for all directories and files directly under
   * the path specified in args.
   *
   * @param fs the filesystem of Alluxio
   */
  public LsCommand(FileSystem fs) {
    super(fs);
  }

  @Override
  public String getCommandName() {
    return "ls";
  }

  @Override
  protected int getNumOfArgs() {
    return 1;
  }

  @Override
  public Options getOptions() {
    return new Options()
        .addOption(RECURSIVE_OPTION)
        .addOption(FORCE_OPTION)
        .addOption(LIST_DIR_AS_FILE_OPTION)
        .addOption(LIST_PINNED_FILES_OPTION)
        .addOption(LIST_HUMAN_READABLE_OPTION)
        .addOption(SORT_OPTION);
  }

  /**
   * Displays information for all directories and files directly under the path specified in args.
   *
   * @param path The {@link AlluxioURI} path as the input of the command
   * @param recursive Whether list the path recursively
   * @param dirAsFile list the directory status as a plain file
   * @param hSize print human-readable format sizes
   * @param sortField sort the result by this field
   */
  private void ls(AlluxioURI path, boolean recursive, boolean forceLoadMetadata, boolean dirAsFile,
                  boolean hSize, boolean pinnedOnly, String sortField)
      throws AlluxioException, IOException {
    if (dirAsFile) {
      URIStatus status = mFileSystem.getStatus(path);
      if (pinnedOnly && !status.isPinned()) {
        return;
      }
      printLsString(status, hSize);
      return;
    }

    ListStatusOptions options = ListStatusOptions.defaults();
    if (forceLoadMetadata) {
      options.setLoadMetadataType(LoadMetadataType.Always);
    }
    List<URIStatus> statuses = mFileSystem.listStatus(path, options);
    List<URIStatus> sorted = sortBySortField(statuses, sortField);
    for (URIStatus status : sorted) {
      if (!pinnedOnly || status.isPinned()) {
        printLsString(status, hSize);
      }
      if (recursive && status.isFolder()) {
        ls(new AlluxioURI(path.getScheme(), path.getAuthority(), status.getPath()), true,
            forceLoadMetadata, false, hSize, pinnedOnly, sortField);
      }
    }
  }

  private List<URIStatus> sortBySortField(List<URIStatus> statuses, String sortField) {
    return statuses.stream()
            .sorted(SORT_FIELD_COMPARATORS.getOrDefault(sortField,
                    SORT_FIELD_COMPARATORS.get("creationTime")))
            .collect(Collectors.toList());
  }

  @Override
  public void runCommand(AlluxioURI path, CommandLine cl) throws AlluxioException, IOException {
    ls(path, cl.hasOption("R"), cl.hasOption("f"), cl.hasOption("d"), cl.hasOption("h"),
        cl.hasOption("p"), cl.getOptionValue("sort", "creationTime"));
  }

  @Override
  public String getUsage() {
    return "ls [-d|-f|-p|-R|-h|--sort=WORD] <path>";
  }

  @Override
  public String getDescription() {
    return "Displays information for all files and directories directly under the specified path, "
        + "including permission, owner, group, size (bytes for files or the number of children "
        + "for directories, persistence state, creation time, the percentage of content already "
        + "in Alluxio and the path in order.";
  }

  @Override
  public void validateArgs(String... args) throws InvalidArgumentException {
    if (args.length < 1) {
      throw new InvalidArgumentException(ExceptionMessage.INVALID_ARGS_NUM_INSUFFICIENT
          .getMessage(getCommandName(), 1, args.length));
    }
  }
}
