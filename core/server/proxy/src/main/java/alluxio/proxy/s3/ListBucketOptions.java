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

package alluxio.proxy.s3;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * The options for list bucket operation.
 */
public final class ListBucketOptions {
  private String mMarker = null;
  private String mMaxKeys = null;
  private String mPrefix = null;
  private String mDelimiter = null;

  /**
   * Creates a default {@link ListBucketOptions}.
   *
   * @return the created {@link ListBucketOptions}
   */
  public static ListBucketOptions defaults() {
    return new ListBucketOptions();
  }

  /**
   * Constructs a new {@link ListBucketOptions}.
   */
  private ListBucketOptions() {}

  /**
   * @return the continuation token
   */
  public String getMarker() {
    return mMarker;
  }

  /**
   * @return the max keys
   */
  public String getMaxKeys() {
    return mMaxKeys;
  }

  /**
   * @return the prefix
   */
  public String getPrefix() {
    return mPrefix;
  }

  /**
   * @return the delimiter
   */
  public String getDelimiter() {
    return mDelimiter;
  }

  /**
   * @param marker the marker to set
   * @return the updated object
   */
  public ListBucketOptions setMarker(String marker) {
    mMarker = marker;
    return this;
  }

  /**
   * @param maxKeys the max keys to set
   * @return the updated object
   */
  public ListBucketOptions setMaxKeys(String maxKeys) {
    mMaxKeys = maxKeys;
    return this;
  }

  /**
   * @param prefix the prefix to set
   * @return the updated object
   */
  public ListBucketOptions setPrefix(String prefix) {
    mPrefix = prefix;
    return this;
  }

  /**
   * @param delimiter the delimiter
   * @return the updated object
   */
  public ListBucketOptions setDelimiter(String delimiter) {
    mDelimiter = delimiter;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ListBucketOptions)) {
      return false;
    }
    ListBucketOptions that = (ListBucketOptions) o;
    return Objects.equal(mMarker, that.mMarker)
        && Objects.equal(mMaxKeys, that.mMaxKeys)
        && Objects.equal(mPrefix, that.mPrefix);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mMarker, mMaxKeys, mPrefix);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("continuationToken", mMarker)
        .add("maxKeys", mMaxKeys)
        .add("prefix", mPrefix)
        .toString();
  }
}
