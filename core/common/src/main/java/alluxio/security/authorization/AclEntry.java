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

package alluxio.security.authorization;

import com.google.common.base.Objects;

/**
 * An entry in {@link AccessControlList}.
 */
public final class AclEntry {
  /**
   * Type of this entry.
   */
  private AclEntryType mType;
  /**
   * Name of owning user, owning group, named user, named group.
   * If the entry is of type MASK or OTHER, this is an empty string.
   */
  private String mSubject;
  /**
   * Permitted actions.
   */
  private AclActions mActions;

  private AclEntry(AclEntryType type, String subject, AclActions actions) {
    mType = type;
    mSubject = subject;
    mActions = actions;
  }

  /**
   * @return the type
   */
  public AclEntryType getType() {
    return mType;
  }

  /**
   * @return the subject
   */
  public String getSubject() {
    return mSubject;
  }

  /**
   * @return a copy of actions
   */
  public AclActions getActions() {
    return new AclActions(mActions);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AclEntry)) {
      return false;
    }
    AclEntry that = (AclEntry) o;
    return Objects.equal(mType, that.mType)
        && Objects.equal(mSubject, that.mSubject)
        && Objects.equal(mActions, that.mActions);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mType, mSubject, mActions);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("type", mType)
        .add("subject", mSubject)
        .add("actions", mActions)
        .toString();
  }

  /**
   * Builder for {@link AclEntry}.
   */
  public static final class Builder {
    private AclEntryType mType;
    private String mSubject;
    private AclActions mActions;

    /**
     * Creates a new builder where type is null, subject is an empty string, and no action is
     * permitted.
     */
    public Builder() {
      mSubject = "";
      mActions = new AclActions();
    }

    /**
     * Sets the type of the entry.
     *
     * @param type the type of the entry
     * @return the builder
     */
    public Builder setType(AclEntryType type) {
      mType = type;
      return this;
    }

    /**
     * Sets subject of this entry.
     * If the entry is of type OWNING_USER or NAMED_USER, then the subject is the username.
     * If the entry is of type OWNING_GROUP or NAMED_GROUP, then the subject is the group name.
     * For other types, the subject should be an empty string.
     *
     * @param subject the subject
     * @return the builder
     */
    public Builder setSubject(String subject) {
      mSubject = subject;
      return this;
    }

    /**
     * Sets a copy of actions for this entry.
     *
     * @param actions the actions to be copied from
     * @return the builder
     */
    public Builder setActions(AclActions actions) {
      for (AclAction action : actions.getActions()) {
        mActions.add(action);
      }
      return this;
    }

    /**
     * Adds a permitted action.
     *
     * @param action the permitted action
     * @return the builder
     */
    public Builder addAction(AclAction action) {
      mActions.add(action);
      return this;
    }

    /**
     * @return a new {@link AclEntry}
     * @throws IllegalStateException if type if null, or if type is either NAMED_USER or NAMED_GROUP
     *    while subject is empty
     */
    public AclEntry build() {
      if (mType == null) {
        throw new IllegalStateException("Type cannot be null");
      }
      boolean shouldHaveSubject = mType.equals(AclEntryType.NAMED_USER)
          || mType.equals(AclEntryType.NAMED_GROUP);
      if (shouldHaveSubject && mSubject.isEmpty()) {
        throw new IllegalStateException("Subject for type " + mType + " cannot be empty");
      }
      return new AclEntry(mType, mSubject, mActions);
    }
  }
}
