#!/bin/bash
#
# Usage:
#  tachyon-yarn.sh <deployTachyonHome> <numWorkers> <pathHdfs>

function printUsage {
  echo "Usage: tachyon-yarn.sh <deployTachyonHome> <numWorkers> <pathHdfs>"
  echo -e "  deployTachyonHome \tHome directory of Tachyon deployment on YARN slave machines"
  echo -e "  numWorkers        \tNumber of Tachyon workers to launch"
  echo -e "  pathHdfs          \tpath on HDFS to put tachyon jar and distribute it to YARN"
  echo
  echo "Example: ./tachyon-yarn.sh /path/to/tachyon/deployment 10 hdfs://NAMENODE:9000/tmp/"
}

if [ "$#" != 3 ]; then
  printUsage
  exit 1
fi

if [ -z "$HADOOP_HOME" ]; then
  echo "\$HADOOP_HOME is unset, please set this variable to connect to HDFS and YARN";
  exit 1
else
  echo "Using \$HADOOP_HOME set to '$HADOOP_HOME'";
fi

SCRIPT_DIR="$(cd "$(dirname "$0")"; pwd)"
source "${SCRIPT_DIR}/common.sh"
TACHYON_MASTER_JAVA_OPTS="${TACHYON_MASTER_JAVA_OPTS:-${TACHYON_JAVA_OPTS}}"
TACHYON_WORKER_JAVA_OPTS="${TACHYON_WORKER_JAVA_OPTS:-${TACHYON_JAVA_OPTS}}"

DEPLOY_TACHYON_HOME=$1
NUM_WORKERS=$2
HDFS_PATH=$3

JAR_LOCAL=${DEPLOY_TACHYON_HOME}/assembly/target/tachyon-assemblies-${VERSION}-jar-with-dependencies.jar

${HADOOP_HOME}/bin/hadoop fs -put -f ${JAR_LOCAL} ${HDFS_PATH}/tachyon.jar
${HADOOP_HOME}/bin/hadoop fs -put -f ./tachyon-master-yarn.sh ${HDFS_PATH}/tachyon-master-yarn.sh
${HADOOP_HOME}/bin/hadoop fs -put -f ./tachyon-worker-yarn.sh ${HDFS_PATH}/tachyon-worker-yarn.sh

${HADOOP_HOME}/bin/yarn jar ${JAR_LOCAL} tachyon.yarn.Client \
    -num_workers $NUM_WORKERS \
    -master_address localhost \
    -resource_path ${HDFS_PATH} \
    -master_java_opts "$TACHYON_MASTER_JAVA_OPTS" \
    -worker_java_opts "$TACHYON_WORKER_JAVA_OPTS"
