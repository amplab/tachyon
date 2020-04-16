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

package alluxio.grpc;

import alluxio.conf.AlluxioConfiguration;
import alluxio.conf.PropertyKey;
import alluxio.util.network.NetworkAddressUtils;

import com.google.common.base.MoreObjects;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

/**
 * Used to identify a unique {@link GrpcChannel}.
 */
public class GrpcChannelKey {
  private static final Random RANDOM = new Random();

  @IdentityField
  NetworkGroup mNetworkGroup = NetworkGroup.RPC;
  @IdentityField
  private GrpcServerAddress mServerAddress;

  /** Unique channel identifier. */
  private final UUID mChannelId = UUID.randomUUID();
  /** Hostname to send to server for identification. */
  private final String mLocalHostName;

  /** Client that requires a channel. */
  private String mClientType;

  private GrpcChannelKey(AlluxioConfiguration conf) {
    // Try to get local host name.
    String localHostName;
    try {
      localHostName = NetworkAddressUtils
          .getLocalHostName((int) conf.getMs(PropertyKey.NETWORK_HOST_RESOLUTION_TIMEOUT_MS));
    } catch (Exception e) {
      localHostName = NetworkAddressUtils.UNKNOWN_HOSTNAME;
    }
    mLocalHostName = localHostName;
  }

  /**
   * Creates a {@link GrpcChannelKey}.
   *
   * @param conf the Alluxio configuration
   * @return the created instance
   */
  public static GrpcChannelKey create(AlluxioConfiguration conf) {
    return new GrpcChannelKey(conf);
  }

  /**
   * @return unique identifier for the channel
   */
  public UUID getChannelId() {
    return mChannelId;
  }

  /**
   * @return destination address of the channel
   */
  public GrpcServerAddress getServerAddress() {
    return mServerAddress;
  }

  /**
   * @param address destination address of the channel
   * @return the modified {@link GrpcChannelKey}
   */
  public GrpcChannelKey setServerAddress(GrpcServerAddress address) {
    mServerAddress = address;
    return this;
  }

  /**
   * @param group the networking group membership
   * @return the modified {@link GrpcChannelKey}
   */
  public GrpcChannelKey setNetworkGroup(NetworkGroup group) {
    mNetworkGroup = group;
    return this;
  }

  /**
   * @return the network group
   */
  public NetworkGroup getNetworkGroup() {
    return mNetworkGroup;
  }

  /**
   * @param clientType the client type
   * @return the modified {@link GrpcChannelKey}
   */
  public GrpcChannelKey setClientType(String clientType) {
    mClientType = clientType;
    return this;
  }

  /**
   * Used to define connection level multiplexing groups.
   */
  public enum NetworkGroup {
    /**
     * Networking group for RPC traffic.
     */
    RPC,
    /**
     * Networking group for Streaming traffic.
     */
    STREAMING,
    ;

    /**
     * @return the code used to refer to this group in property key templates
     */
    public String getPropertyCode() {
      switch (this) {
        case RPC:
          return "rpc";
        case STREAMING:
          return "streaming";
        default:
          throw new IllegalArgumentException(
              String.format("Unrecognized network group: %s", this.toString()));
      }
    }
  }

  @Override
  public int hashCode() {
    HashCodeBuilder hashCodebuilder = new HashCodeBuilder();
    for (Field field : this.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(IdentityField.class)) {
        try {
          hashCodebuilder.append(field.get(this));
        } catch (IllegalAccessException e) {
          throw new RuntimeException(
              String.format("Failed to calculate hashcode for channel-key: %s", this), e);
        }
      }
    }
    return hashCodebuilder.toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof GrpcChannelKey) {
      GrpcChannelKey otherKey = (GrpcChannelKey) other;
      boolean areEqual = true;
      for (Field field : this.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(IdentityField.class)) {
          try {
            areEqual &= field.get(this).equals(field.get(otherKey));
          } catch (IllegalAccessException e) {
            throw new RuntimeException(String.format(
                "Failed to calculate equality between channel-keys source: %s | destination: %s",
                this, otherKey), e);
          }
        }
      }
      return areEqual;
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("ClientType", mClientType)
        .add("ServerAddress", mServerAddress)
        .add("ChannelId", mChannelId)
        .omitNullValues()
        .toString();
  }

  /**
   * @return short representation of this channel key
   */
  public String toStringShort() {
    return MoreObjects.toStringHelper(this)
        .add("ClientType", mClientType)
        .add("ClientHostname", mLocalHostName)
        .add("ServerAddress", mServerAddress)
        .add("ChannelId", mChannelId)
        .omitNullValues()
        .toString();
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  /**
   * Used to mark fields in this class that are part of
   * the identity of a channel while pooling channels.
   *
   * Values of fields that are marked with this annotation will be used
   * during {@link #hashCode()} and {@link #equals(Object)}.
   */
  protected @interface IdentityField {
  }
}
