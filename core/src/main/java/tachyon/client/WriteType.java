package tachyon.client;

import java.io.IOException;

/**
 * Different write types for a TachyonFile.
 */
public enum WriteType {
  /**
   * Write the file and must cache it.
   */
  MUST_CACHE(1),
  /**
   * Write the file and try to cache it.
   */
  TRY_CACHE(2),
  /**
   * Write the file synchronously to the under fs, and also try to cache it,
   */
  CACHE_THROUGH(3),
  /**
   * Write the file synchronously to the under fs, no cache.
   */
  THROUGH(4),
  /**
   * Write the file asynchronously to the under fs (either must cache or must through).
   */
  ASYNC_THROUGH(5);

  /**
   * Parse the write type
   * 
   * @param op
   *          the String format of the write type
   * @return the write type
   * @throws IOException
   */
  public static WriteType getOpType(String op) throws IOException {
    if (op.equals("MUST_CACHE")) {
      return MUST_CACHE;
    } else if (op.equals("TRY_CACHE")) {
      return TRY_CACHE;
    } else if (op.equals("CACHE_THROUGH")) {
      return CACHE_THROUGH;
    } else if (op.equals("THROUGH")) {
      return THROUGH;
    } else if (op.equals("ASYNC_THROUGH")) {
      return ASYNC_THROUGH;
    }

    throw new IOException("Unknown WriteType : " + op);
  }

  private final int mValue;

  private WriteType(int value) {
    mValue = value;
  }

  /**
   * Return the value of the write type
   * 
   * @return the value of the write type
   */
  public int getValue() {
    return mValue;
  }

  /**
   * @return true if the write type is ASYNC_THROUGH, false otherwise
   */
  public boolean isAsync() {
    return mValue == ASYNC_THROUGH.mValue;
  }

  /**
   * @return true if the write type is one of MUST_CACHE, CACHE_THROUGH,
   *         TRY_CACHE, or ASYNC_THROUGH
   */
  public boolean isCache() {
    return (mValue == MUST_CACHE.mValue) || (mValue == CACHE_THROUGH.mValue)
        || (mValue == TRY_CACHE.mValue) || (mValue == ASYNC_THROUGH.mValue);
  }

  /**
   * @return true if the write type is MUST_CACHE or ASYNC_THROUGH
   */
  public boolean isMustCache() {
    return (mValue == MUST_CACHE.mValue) || (mValue == ASYNC_THROUGH.mValue);
  }

  /**
   * @return true if the write type is CACHE_THROUGH or THROUGH
   */
  public boolean isThrough() {
    return (mValue == CACHE_THROUGH.mValue) || (mValue == THROUGH.mValue);
  }
}
