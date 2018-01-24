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

package alluxio.underfs.oss;

import alluxio.underfs.MultiRangeObjectInputStream;

import com.obs.services.ObsClient;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.S3Object;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * A stream for reading a file from OBS. This input stream returns 0 when calling read with an empty
 * buffer.
 */
@NotThreadSafe
public class OBSInputStream extends MultiRangeObjectInputStream {

  /** Bucket name of the OBS bucket. */
  private final String mBucketName;

  /** Key of the file in OBS. */
  private final String mKey;

  /** The OBS client. */
  private final ObsClient mObsClient;

  /** The size of the object in bytes. */
  private final long mContentLength;

  /**
   * Creates a new instance of {@link OBSInputStream}.
   *
   * @param bucketName the name of the bucket
   * @param key the key of the file
   * @param client the client for OSS
   */
  OBSInputStream(String bucketName, String key, ObsClient client) throws IOException {
    this(bucketName, key, client, 0L);
  }

  /**
   * Creates a new instance of {@link OBSInputStream}.
   *
   * @param bucketName the name of the bucket
   * @param key the key of the file
   * @param client the OBS client
   * @param position the position to begin reading from
   */
  OBSInputStream(String bucketName, String key, ObsClient client, long position)
      throws IOException {
    mBucketName = bucketName;
    mKey = key;
    mObsClient = client;
    mPos = position;
    ObjectMetadata meta = mObsClient.getObjectMetadata(mBucketName, key);
    mContentLength = meta == null ? 0 : meta.getContentLength();
  }

  @Override
  protected InputStream createStream(long startPos, long endPos) throws IOException {
    GetObjectRequest req = new GetObjectRequest(mBucketName, mKey);
    req.setRangeStart(startPos);
    req.setRangeEnd(endPos < mContentLength ? endPos - 1 : mContentLength - 1);
    S3Object obj = mObsClient.getObject(req);
    return new BufferedInputStream(obj.getObjectContent());
  }
}
