package tachyon;

public abstract class Inode implements Comparable<Inode> {
  private final long CREATION_TIME_MS;
  protected final InodeType TYPE;

  private int mId;
  private String mName;
  private int mParentId;
  private int mDependencyId;

  protected Inode(String name, int id, int parentId, InodeType type) {
    TYPE = type;

    mId = id;
    mName = name;
    mParentId = parentId;
    mDependencyId = -1;

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
    return TYPE != InodeType.File;
  }

  public boolean isFile() {
    return TYPE == InodeType.File;
  }

  public InodeType getInodeType() {
    return TYPE;
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

  public synchronized void setDependencyId(int dependencyId) {
    mDependencyId = dependencyId;
  }

  public synchronized int getDependencyId() {
    return mDependencyId;
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