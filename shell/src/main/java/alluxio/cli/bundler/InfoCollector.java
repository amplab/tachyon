package alluxio.cli.bundler;

import alluxio.cli.AbstractShell;
import alluxio.cli.Command;
import alluxio.cli.CommandUtils;
import alluxio.cli.bundler.command.AbstractInfoCollectorCommand;
import alluxio.cli.bundler.command.TarUtils;
import alluxio.cli.fs.FileSystemShell;
import alluxio.client.file.FileSystemContext;
import alluxio.conf.InstancedConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.conf.Source;
import alluxio.util.ConfigurationUtils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InfoCollector extends AbstractShell {
  private static final Logger LOG = LoggerFactory.getLogger(InfoCollector.class);

  private static final Map<String, String[]> CMD_ALIAS = ImmutableMap.<String, String[]>builder()
          .build();

  // In order for a warning to be displayed for an unstable alias, it must also exist within the
  // CMD_ALIAS map.
  private static final Set<String> UNSTABLE_ALIAS = ImmutableSet.<String>builder()
          .build();

  /**
   * Main method, starts a new FileSystemShell.
   *
   * @param argv array of arguments given by the user's input from the terminal
   */
  public static void main(String[] argv) throws IOException {
    int ret = 0;

    InstancedConfiguration conf = new InstancedConfiguration(ConfigurationUtils.defaults());
    FileSystemContext fsContext = FileSystemContext.create(conf);

    // Execute the Collectors one by one
    // Reduce the RPC retry max duration to fall earlier for CLIs
    conf.set(PropertyKey.USER_RPC_RETRY_MAX_DURATION, "5s", Source.DEFAULT);
    InfoCollector shell = new InfoCollector(conf);

    // Determine the working dir path
    if (argv.length < 2) {
      throw new IOException(String.format("No target directory specified by args %s",
              Arrays.toString(argv)));
    }
    String subCommand = argv[0];
    String targetDirPath = argv[1];

    // Invoke all other commands to collect information
    // FORCE_OPTION will be propagated to child commands
    List<File> filesToCollect = new ArrayList<>();
    if (subCommand.equals("all")) {
      // Execute all commands if the option is "all"
      System.out.println("Execute all child commands");
      for (Command cmd : shell.getCommands()) {
        System.out.println(String.format("Executing %s", cmd.getCommandName()));

        // TODO(jiacheng): How to handle argv difference?

        AbstractInfoCollectorCommand infoCmd = (AbstractInfoCollectorCommand) cmd;

        // Find the argv for this command
        argv[0] = infoCmd.getCommandName();
        int childRet = shell.run(argv);

        // File to collect
        File infoCmdOutputFile = infoCmd.generateOutputFile(targetDirPath, infoCmd.getCommandName());
        filesToCollect.add(infoCmdOutputFile);

        System.out.println(String.format("Command %s exit with code %s", cmd.getCommandName(), childRet));
      }
    } else {
      AbstractInfoCollectorCommand cmd = shell.findCommand(subCommand);
      if (cmd == null) {
        // Unknown command (we didn't find the cmd in our dict)
        System.err.println(String.format("%s is an unknown command.", cmd));
        shell.printUsage();
        return;
      }

      int childRet = shell.run(argv);

      File infoCmdOutputFile = cmd.generateOutputFile(targetDirPath, cmd.getCommandName());
      filesToCollect.add(infoCmdOutputFile);

      System.out.println(String.format("Command %s exit with code %s", subCommand, childRet));
    }

    // Generate bundle
    System.out.println(String.format("Archiving dir %s", targetDirPath));
    System.out.println(String.format("Files to include: %s", filesToCollect));
    String tarballPath = Paths.get(targetDirPath, "collectAll.tar.gz").toAbsolutePath().toString();

    TarUtils.compress(tarballPath, filesToCollect.toArray(new File[0]));
    System.out.println("Archiving finished");

    System.exit(ret);
  }

  public AbstractInfoCollectorCommand findCommand(String cmdName) {
    for (Command c : this.getCommands()) {
      if (c.getCommandName().equals(cmdName)) {
        return (AbstractInfoCollectorCommand) c;
      }
    }
    return null;
  }

  /**
   * Creates a new instance of {@link FileSystemShell}.
   *
   * @param alluxioConf Alluxio configuration
   */
  public InfoCollector(InstancedConfiguration alluxioConf) {
    super(CMD_ALIAS, UNSTABLE_ALIAS, alluxioConf);
  }

  @Override
  protected String getShellName() {
    return "infoBundle";
  }

  @Override
  protected Map<String, Command> loadCommands() {
    // Give each command the configuration
    Map<String, Command> commands = CommandUtils.loadCommands(InfoCollector.class.getPackage().getName(),
              new Class[] {FileSystemContext.class}, new Object[] {FileSystemContext.create(mConfiguration)});
    System.out.println(String.format("Loaded commands %s", commands));
    return commands;
  }
}
