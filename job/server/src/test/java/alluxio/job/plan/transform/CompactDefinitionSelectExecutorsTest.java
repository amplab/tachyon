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

package alluxio.job.plan.transform;

import alluxio.AlluxioURI;
import alluxio.client.file.URIStatus;
import alluxio.collections.Pair;
import alluxio.job.JobServerContext;
import alluxio.job.SelectExecutorsContext;
import alluxio.job.plan.SelectExecutorsTest;

import alluxio.wire.WorkerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CompactDefinitionSelectExecutorsTest extends SelectExecutorsTest {

  private static String INPUT_DIR = "/input";
  private static String OUTPUT_DIR = "/output";

  @Test
  public void testExecutorsParallel() throws Exception {
    CompactConfig config = new CompactConfig(null, INPUT_DIR, OUTPUT_DIR, "test", 100);

    List<URIStatus> inputFiles = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      inputFiles.add(newFile(Integer.toString(i)));
    }

    when(mMockFileSystem.listStatus(new AlluxioURI(INPUT_DIR))).thenReturn(inputFiles);

    Set<Pair<WorkerInfo, ArrayList<CompactTask>>> result = new CompactDefinition().selectExecutors(
        config, SelectExecutorsTest.JOB_WORKERS, new SelectExecutorsContext(1,
            new JobServerContext(mMockFileSystem, mMockFileSystemContext, mMockUfsManager)));
    assertEquals(40, result.size());

    int allCompactTasks = 0;
    for (Pair<WorkerInfo, ArrayList<CompactTask>> tasks : result) {
      allCompactTasks += tasks.getSecond().size();
    }
    assertEquals(50, allCompactTasks);
  }

  private URIStatus newFile(String name) {
    URIStatus mockFileStatus = Mockito.mock(URIStatus.class);
    when(mockFileStatus.isFolder()).thenReturn(false);
    when(mockFileStatus.getName()).thenReturn(name);
    return mockFileStatus;
  }

}
