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

package alluxio.client.catalog;

import alluxio.AlluxioURI;
import alluxio.ClientContext;
import alluxio.client.file.FileSystem;
import alluxio.conf.PropertyKey;
import alluxio.conf.ServerConfiguration;
import alluxio.experimental.ProtoUtils;
import alluxio.grpc.SetAttributePOptions;
import alluxio.grpc.TableInfo;
import alluxio.master.MasterClientContext;
import alluxio.master.file.meta.options.MountInfo;
import alluxio.testutils.BaseIntegrationTest;
import alluxio.testutils.LocalAlluxioClusterResource;
import alluxio.util.io.PathUtils;
import alluxio.wire.MountPointInfo;
import alluxio.worker.file.FileSystemMasterClient;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DataFiles;
import org.apache.iceberg.FileFormat;
import org.apache.iceberg.Schema;
import org.apache.iceberg.Table;
import org.apache.iceberg.avro.Avro;
import org.apache.iceberg.avro.AvroSchemaUtil;
import org.apache.iceberg.io.FileAppender;
import org.apache.iceberg.parquet.Parquet;
import org.apache.iceberg.types.Types;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.apache.iceberg.Files.localOutput;

/**
 * Integration tests for the Catalog Master Client.
 */
public final class CatalogMasterClientTest extends BaseIntegrationTest {
  @Rule
  public LocalAlluxioClusterResource mLocalAlluxioClusterResource =
      new LocalAlluxioClusterResource.Builder()
          .setProperty(PropertyKey.USER_FILE_WRITE_TYPE_DEFAULT, "CACHE_THROUGH").build();
  private FileSystem mFileSystem = null;
  private FileSystemMasterClient mFSMasterClient;
  private CatalogMasterClient mCatalogMasterClient;

  @Before
  public final void before() throws Exception {
    mFileSystem = mLocalAlluxioClusterResource.get().getClient();
    MasterClientContext context = MasterClientContext
        .newBuilder(ClientContext.create(ServerConfiguration.global())).build();
    mCatalogMasterClient = new RetryHandlingCatalogMasterClient(context);
    mFSMasterClient = new FileSystemMasterClient(context);

  }

  @After
  public final void after() throws Exception {
    mFSMasterClient.close();
    mCatalogMasterClient.close();
  }

  /**
   * Tests catalog service table metadata operation
   */
  @Test
  public void tableOps() throws Exception {
    Map<String, MountPointInfo> test = mFileSystem.getMountTable();
    List<String> dbs = mCatalogMasterClient.getAllDatabases();
    Assert.assertEquals( 0, dbs.size());
    mCatalogMasterClient.createDatabase("test1");
    dbs = mCatalogMasterClient.getAllDatabases();
    Assert.assertEquals( 1, dbs.size());
    Assert.assertEquals(dbs.get(0), "test1");
    Schema schema = new Schema(Types.NestedField.optional(0, "id", Types.IntegerType.get()));

    TableInfo table = mCatalogMasterClient.createTable("test1", "table1", schema);
    Assert.assertEquals(test.get("/").getUfsUri() +"/catalog-metadata/test1.db/table1", table.getBaseLocation());
    Assert.assertEquals(1, table.getSchema().getColsCount());
    Assert.assertEquals(Types.IntegerType.get(), ProtoUtils.fromProto(table.getSchema()).findField(0).type());
  }

}
