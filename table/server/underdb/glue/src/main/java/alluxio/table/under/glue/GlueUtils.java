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

package alluxio.table.under.glue;

import alluxio.grpc.table.Schema;
import alluxio.grpc.table.layout.hive.HiveBucketProperty;
import alluxio.grpc.table.layout.hive.SortingColumn;
import alluxio.grpc.table.layout.hive.Storage;
import alluxio.grpc.table.layout.hive.StorageFormat;
import alluxio.table.under.glue.util.PathTranslator;

import com.amazonaws.services.glue.model.Column;
import com.amazonaws.services.glue.model.Order;
import com.amazonaws.services.glue.model.Partition;
import com.amazonaws.services.glue.model.StorageDescriptor;
import com.amazonaws.services.glue.model.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Glue utils.
 */
public class GlueUtils {

  private GlueUtils() {}

  /**
   * Convert glue field schema to alluxio proto (Glue do not have filedschema api).
   *
   * @param glueColumns list of glue columns
   * @return alluxio proto of schema
   */
  public static Schema toProtoSchema(List<Column> glueColumns) {
    Schema.Builder schemaBuilder = Schema.newBuilder();
    schemaBuilder.addAllCols(toProto(glueColumns));
    return schemaBuilder.build();
  }

  /**
   * Convert the Glue FieldSchema to Alluxio FieldSchema.
   *
   * @param glueCloumns Glue FiledSchema
   * @return list of Alluxio FieldSchema
   */
  public static List<alluxio.grpc.table.FieldSchema> toProto(List<Column> glueCloumns) {
    List<alluxio.grpc.table.FieldSchema> list = new ArrayList<>();
    for (Column column:glueCloumns) {
      alluxio.grpc.table.FieldSchema.Builder builder = alluxio.grpc.table.FieldSchema.newBuilder()
          .setName(column.getName())
          .setType(column.getType());
      if (column.getComment() != null) {
        builder.setComment(column.getComment());
      }
      list.add(builder.build());
    }
    return list;
  }

  /**
   * Convert the Glue Storage Descriptor and Translator information to Storage.
   *
   * @param sd the glue storage descriptor
   * @param translator the glue translator
   * @return storage proto
   * @throws IOException
   */
  public static Storage toProto(StorageDescriptor sd, PathTranslator translator)
      throws IOException {
    if (sd == null) {
      return Storage.getDefaultInstance();
    }

    String serDe = sd.getSerdeInfo() == null ? null
        : sd.getSerdeInfo().getSerializationLibrary();
    Map<String, String> serdeLibMap = sd.getSerdeInfo() == null ? null
        : sd.getSerdeInfo().getParameters();
    StorageFormat.Builder formatBuilder = StorageFormat.newBuilder()
        .setInputFormat(sd.getInputFormat())
        .setOutputFormat(sd.getOutputFormat());

    if (serdeLibMap != null) {
      formatBuilder.putAllSerdelibParameters(serdeLibMap);
    }
    if (serDe != null) {
      formatBuilder.setSerde(serDe); // Check SerDe info
    }

    alluxio.grpc.table.layout.hive.Storage.Builder storageBuilder =
        alluxio.grpc.table.layout.hive.Storage.newBuilder();
    List<Order> orderList = sd.getSortColumns();
    List<SortingColumn> sortingColumns;
    if (orderList == null) {
      sortingColumns = Collections.emptyList();
    } else {
      sortingColumns = orderList.stream().map(
          order -> SortingColumn.newBuilder().setColumnName(order.getColumn())
              .setOrder(order.getSortOrder() == 1 ? SortingColumn.SortingOrder.ASCENDING
                  : SortingColumn.SortingOrder.DESCENDING).build())
          .collect(Collectors.toList());
    }
    return storageBuilder.setStorageFormat(formatBuilder.build())
        .setLocation(translator.toAlluxioPath(sd.getLocation()))
        .setBucketProperty(HiveBucketProperty.newBuilder().setBucketCount(sd.getNumberOfBuckets())
            .addAllBucketedBy(sd.getBucketColumns()).addAllSortedBy(sortingColumns).build())
        .setSkewed(sd.getSkewedInfo() != null && (sd.getSkewedInfo().getSkewedColumnNames()) != null
            && !sd.getSkewedInfo().getSkewedColumnNames().isEmpty())
        .putAllSerdeParameters(sd.getParameters()).build();
  }

  /**
   * Convert glue partion information to alluxio partition name.
   *
   * @param table glue table
   * @param partition glue partition
   * @return partition name
   * @throws IOException
   */
  public static String makePartitionName(Table table, Partition partition) throws IOException {
    // TODO(shouwei): make makePartitionName work both for hive and glue
    String partitionName;
    if (table.getName().isEmpty()) {
      throw new IOException("Table name cannot be null.");
    }
    if (partition.getValues().toString().isEmpty()) {
      throw new IOException("Error making partition name for table {"
          + table.getName()
          + "}, partition {" + partition.getValues().toString() + "}.");
    }
    partitionName = partition.getValues().toString();
    return partitionName;
  }
}
