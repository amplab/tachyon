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

package alluxio.client.metrics;

import alluxio.Constants;
import alluxio.heartbeat.HeartbeatExecutor;
import alluxio.metrics.Metric;
import alluxio.metrics.MetricsSystem;
import alluxio.util.logging.SamplingLogger;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Task that carries the client metrics information to master through heartheat. This class manages
 * its own {@link MetricsMasterClient}.
 *
 * If the task fails to heartbeat to the master, it will destroy its old master client and recreate
 * it before retrying.
 */
@ThreadSafe
public final class ClientMasterSync implements HeartbeatExecutor {
  private static final Logger LOG =
      new SamplingLogger(LoggerFactory.getLogger(ClientMasterSync.class), 30 * Constants.SECOND_MS);

  /** Client for communicating to metrics master. */
  private final MetricsMasterClient mMasterClient;
  private final String mAppId;

  /**
   * Constructs a new {@link ClientMasterSync}.
   *
   * @param masterClient the master client
   * @param appId the app ID for this client
   */
  public ClientMasterSync(MetricsMasterClient masterClient, String appId) {
    mMasterClient = Preconditions.checkNotNull(masterClient, "masterClient");
    mAppId = Preconditions.checkNotNull(appId, "appId");
  }

  @Override
  public synchronized void heartbeat() {
    List<alluxio.grpc.Metric> metrics = new ArrayList<>();
    for (Metric metric : MetricsSystem.allClientMetrics()) {
      metric.setInstanceId(mAppId);
      metrics.add(metric.toProto());
    }
    try {
      mMasterClient.heartbeat(metrics);
    } catch (IOException e) {
      // WARN instead of ERROR as metrics are not critical to the application function
      LOG.warn("Failed to send metrics to master: ", e);
      mMasterClient.disconnect();
    }
  }

  @Override
  public void close() {
  }
}
