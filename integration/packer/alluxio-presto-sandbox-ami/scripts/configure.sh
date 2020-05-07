
set -ex

# pick up environment settings defined by scripts in /etc/profile.d/
source /etc/profile

# alluxio

/opt/alluxio/bin/alluxio formatJournal

# hadoop
sed -i 's|hadoop-daemons.sh|hadoop-daemon.sh|g' /opt/hadoop/sbin/start-dfs.sh
sed -i 's|hadoop-daemons.sh|hadoop-daemon.sh|g' /opt/hadoop/sbin/stop-dfs.sh
/opt/hadoop/bin/hdfs namenode -format -force

# alluxio client jar to classpaths
echo "export HADOOP_CLASSPATH=/opt/alluxio/client/alluxio-${ALLUXIO_VERSION}-client.jar:/opt/hadoop/share/hadoop/tools/lib/*:\${HADOOP_CLASSPATH}" >> /opt/hadoop/etc/hadoop/hadoop-env.sh
echo "export HIVE_AUX_JARS_PATH=/opt/alluxio/client/alluxio-${ALLUXIO_VERSION}-client.jar" >> /opt/hive/conf/hive-env.sh
ln -s /opt/alluxio/client/alluxio-${ALLUXIO_VERSION}-client.jar /opt/presto/plugin/hive-hadoop2/alluxio-${ALLUXIO_VERSION}-client.jar

# link mysql connector in Hive: https://www.guru99.com/installation-configuration-hive-mysql.html#3
ln -s /usr/share/java/mysql-connector-java.jar /opt/hive/lib/mysql-connector-java.jar

# initialize db
sudo mysql_install_db --user=ec2-user # MariaDB specific command
sudo chown -R ec2-user:ec2-user /var/lib/mysql # allow respective user to run mysql daemon
sudo chmod -R 777 /var/lib/mysql/
