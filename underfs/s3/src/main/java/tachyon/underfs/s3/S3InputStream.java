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

package tachyon.underfs.s3;

import org.jets3t.service.ServiceException;
import org.jets3t.service.model.S3Object;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class S3InputStream extends InputStream {
  private S3Object mObject;
  private BufferedInputStream mInputStream;

  S3InputStream(S3Object object) throws ServiceException {
    mObject = object;
    mInputStream = new BufferedInputStream(mObject.getDataInputStream());
  }

  public int read() throws IOException {
    return mInputStream.read();
  }

  @Override
  public long skip(long n) throws IOException {
    if (mInputStream.available() >= n) {
      return mInputStream.skip(n);
    }
    mInputStream.close();
    try {
      mInputStream = new BufferedInputStream(mObject.getDataInputStream());
    } catch (ServiceException se) {
      return 0;
    }
    return n;
  }

}
