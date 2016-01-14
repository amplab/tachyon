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

package tachyon.master.file;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Preconditions;

import tachyon.Constants;
import tachyon.TachyonURI;
import tachyon.client.file.options.SetAttributeOptions;
import tachyon.exception.TachyonException;
import tachyon.master.file.options.CompleteFileOptions;
import tachyon.master.file.options.CreateFileOptions;
import tachyon.master.file.options.CreateDirectoryOptions;
import tachyon.thrift.CompleteFileTOptions;
import tachyon.thrift.CreateDirectoryTOptions;
import tachyon.thrift.CreateFileTOptions;
import tachyon.thrift.FileBlockInfo;
import tachyon.thrift.FileInfo;
import tachyon.thrift.FileSystemMasterClientService;
import tachyon.thrift.SetAttributeTOptions;
import tachyon.thrift.TachyonTException;
import tachyon.thrift.ThriftIOException;

/**
 * This class is a Thrift handler for file system master RPCs invoked by a Tachyon client.
 */
public final class FileSystemMasterClientServiceHandler implements
    FileSystemMasterClientService.Iface {
  private final FileSystemMaster mFileSystemMaster;

  /**
   * Creates a new instance of {@link FileSystemMasterClientServiceHandler}.
   *
   * @param fileSystemMaster the {@link FileSystemMaster} the handler uses internally
   */
  public FileSystemMasterClientServiceHandler(FileSystemMaster fileSystemMaster) {
    Preconditions.checkNotNull(fileSystemMaster);
    mFileSystemMaster = fileSystemMaster;
  }

  @Override
  public long getServiceVersion() {
    return Constants.FILE_SYSTEM_MASTER_CLIENT_SERVICE_VERSION;
  }

  @Override
  public void completeFile(String path, CompleteFileTOptions options) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      mFileSystemMaster.completeFile(fileId, new CompleteFileOptions(options));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public void createDirectory(String path, CreateDirectoryTOptions options)
      throws TachyonTException, ThriftIOException {
    try {
      mFileSystemMaster.mkdir(new TachyonURI(path), new CreateDirectoryOptions(options));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }

  @Override
  public void createFile(String path, CreateFileTOptions options) throws TachyonTException,
      ThriftIOException {
    try {
      mFileSystemMaster.create(new TachyonURI(path), new CreateFileOptions(options));
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public void free(String path, boolean recursive) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      mFileSystemMaster.free(fileId, recursive);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public List<FileBlockInfo> getFileBlockInfoList(String path) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      return mFileSystemMaster.getFileBlockInfoList(fileId);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public long getNewBlockIdForFile(String path) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      return mFileSystemMaster.getNewBlockIdForFile(fileId);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public FileInfo getStatus(String path) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      return mFileSystemMaster.getFileInfo(fileId);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public String getUfsAddress() {
    return mFileSystemMaster.getUfsAddress();
  }

  @Override
  public List<FileInfo> listStatus(String path) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      return mFileSystemMaster.getFileInfoList(fileId);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public long loadMetadata(String tachyonPath, boolean recursive)
      throws TachyonTException, ThriftIOException {
    try {
      return mFileSystemMaster.loadMetadata(new TachyonURI(tachyonPath), recursive);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }

  @Override
  public void mount(String tachyonPath, String ufsPath)
      throws TachyonTException, ThriftIOException {
    try {
      mFileSystemMaster.mount(new TachyonURI(tachyonPath), new TachyonURI(ufsPath));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }

  @Override
  public void remove(String path, boolean recursive)
      throws TachyonTException, ThriftIOException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      mFileSystemMaster.deleteFile(fileId, recursive);
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }

  @Override
  public void rename(String srcPath, String dstPath)
      throws TachyonTException, ThriftIOException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(srcPath));
      mFileSystemMaster.rename(fileId, new TachyonURI(dstPath));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }

  // TODO(calvin): Do not rely on client side options
  @Override
  public void setAttribute(String path, SetAttributeTOptions options) throws TachyonTException {
    try {
      long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
      mFileSystemMaster.setState(fileId, SetAttributeOptions.fromThriftOptions(options));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    }
  }

  @Override
  public void unmount(String tachyonPath) throws TachyonTException, ThriftIOException {
    try {
      mFileSystemMaster.unmount(new TachyonURI(tachyonPath));
    } catch (TachyonException e) {
      throw e.toTachyonTException();
    } catch (IOException e) {
      throw new ThriftIOException(e.getMessage());
    }
  }
}
