/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the “License”). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.exception;

import javax.annotation.concurrent.ThreadSafe;

/**
 * The exception thrown when a worker is out of space.
 */
@ThreadSafe
public class WorkerOutOfSpaceException extends AlluxioException {
  private static final long serialVersionUID = 8148784998226149669L;

  private static final AlluxioExceptionType EXCEPTION_TYPE =
      AlluxioExceptionType.WORKER_OUT_OF_SPACE;

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public WorkerOutOfSpaceException(String message) {
    super(EXCEPTION_TYPE, message);
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause
   */
  public WorkerOutOfSpaceException(String message, Throwable cause) {
    super(EXCEPTION_TYPE, message, cause);
  }

  /**
   * Constructs a new exception with the specified exception message and multiple parameters.
   *
   * @param message the exception message
   * @param params the parameters
   */
  public WorkerOutOfSpaceException(ExceptionMessage message, Object... params) {
    this(message.getMessage(params));
  }

  /**
   * Constructs a new exception with the specified exception message, the cause and multiple
   * parameters.
   *
   * @param message the exception message
   * @param cause the cause
   * @param params the parameters
   */
  public WorkerOutOfSpaceException(ExceptionMessage message, Throwable cause, Object... params) {
    this(message.getMessage(params), cause);
  }
}
