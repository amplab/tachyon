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

package alluxio.cli.fsadmin.command;

import alluxio.cli.Command;
import alluxio.cli.CommandDocumentation;
import alluxio.cli.CommandUtils;
import alluxio.client.block.BlockMasterClient;
import alluxio.client.file.FileSystemMasterClient;
import alluxio.client.journal.JournalMasterClient;
import alluxio.client.meta.MetaMasterClient;
import alluxio.client.meta.MetaMasterConfigClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jline.internal.Preconditions;

import java.io.PrintStream;
import java.net.URL;

/**
 * Base class for fsadmin commands. It provides access to clients for talking to fs master, block
 * master, and meta master.
 */
public abstract class AbstractFsAdminCommand implements Command {
  protected final FileSystemMasterClient mFsClient;
  protected final BlockMasterClient mBlockClient;
  protected final MetaMasterClient mMetaClient;
  protected final MetaMasterConfigClient mMetaConfigClient;
  protected final PrintStream mPrintStream;
  protected final JournalMasterClient mMasterJournalMasterClient;
  protected final JournalMasterClient mJobMasterJournalMasterClient;

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

  protected AbstractFsAdminCommand(Context context) {
    mFsClient = context.getFsClient();
    mBlockClient = context.getBlockClient();
    mMetaClient = context.getMetaClient();
    mMetaConfigClient = context.getMetaConfigClient();
    mMasterJournalMasterClient = context.getJournalMasterClientForMaster();
    mJobMasterJournalMasterClient = context.getJournalMasterClientForJobMaster();
    mPrintStream = context.getPrintStream();
  }

  @Override
  public String getCommandName() {
    return getDocumentation().getName();
  }

  @Override
  public String getUsage() {
    return getDocumentation().getUsage();
  }

  @Override
  public String getDescription() {
    return getDocumentation().getDescription();
  }

  @Override
  public String getExample() {
    return getDocumentation().getExamples();
  }

  @Override
  public CommandDocumentation getDocumentation() {
    CommandDocumentation d = CommandUtils.readDocumentation(this.getClass());
    d.setOptions(CommandUtils.addOptions(this));
    return d;
  }
}
