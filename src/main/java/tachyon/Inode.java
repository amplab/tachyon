package tachyon;

public abstract class Inode implements Comparable<Inode> {
  private final long CREATION_TIME_MS;
  private final boolean IS_FOLDER;

  private int mId;
  private String mName;
  private int mParentId;

  protected Inode(String name, int id, int parentId, boolean isFolder) {
    mName = name;
    mParentId = parentId;

    mId = id;
    IS_FOLDER = isFolder;
    CREATION_TIME_MS = System.currentTimeMillis();
  }

  @Override
  public synchronized int compareTo(Inode o) {
    return mId - o.mId;
  }

  @Override
  public synchronized boolean equals(Object o) {
    if (!(o instanceof Inode)) {
      return false;
    }
    return mId == ((Inode)o).mId;
  }

  @Override
  public synchronized int hashCode() {
    return mId;
  }

  public boolean isDirectory() {
    return IS_FOLDER;
  }

  public boolean isFile() {
    return !IS_FOLDER;
  }

  public long getCreationTimeMs() {
    return CREATION_TIME_MS;
  }

  public synchronized int getId() {
    return mId;
  }

  public synchronized void reverseId() {
    mId = -mId;
  }

  public synchronized String getName() {
    return mName;
  }

  public synchronized void setName(String name) {
    mName = name;
  }

  public synchronized int getParentId() {
    return mParentId;
  }

  public synchronized void setParentId(int parentId) {
    mParentId = parentId;
  }

  @Override
  public synchronized String toString() {
    StringBuilder sb = new StringBuilder("INode(");
    sb.append(mName).append(",").append(mId).append(",").append(mParentId).append(")");
    return sb.toString();
  }
}