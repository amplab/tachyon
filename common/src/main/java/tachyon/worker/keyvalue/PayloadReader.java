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

package tachyon.worker.keyvalue;

/**
 * Interface to access key and value from payload storage.
 */
public interface PayloadReader {

  /**
   * Gets the key given the position of payload storage.
   *
   * @param pos position in the payload storage in bytes
   * @return bytes of the key
   */
  byte[] getKey(int pos);

  /**
   * Gets the value given the position of payload storage.
   *
   * @param pos position in the payload storage in bytes
   * @return bytes of the key
   */
  byte[] getValue(int pos);
}
