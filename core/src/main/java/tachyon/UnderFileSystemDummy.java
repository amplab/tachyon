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

package tachyon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tachyon.conf.TachyonConf;

/**
 * dummy UnderFilesystem implementation.
 * 
 * This is used when we use Tachyon as pure cache without any backing store
 */
public class UnderFileSystemDummy extends UnderFileSystemSingleLocal {

  protected UnderFileSystemDummy(TachyonConf tachyonConf) {
    super(tachyonConf);
  }

  public static UnderFileSystem getClient(TachyonConf tachyonConf) {
    return new UnderFileSystemDummy(tachyonConf);
  }

  @Override
  public OutputStream create(String path) throws IOException {
    return new DummyFileOutputStream();
  }

  @Override
  public OutputStream create(String path, int blockSizeByte) throws IOException {
    return new DummyFileOutputStream();
  }

  @Override
  public OutputStream create(String path, short replication, int blockSizeByte) throws IOException {
    return new DummyFileOutputStream();
  }

  @Override
  public boolean rename(String src, String dst) throws IOException {
    return true;
  }
}
