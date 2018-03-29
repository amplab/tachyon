namespace java alluxio.thrift

include "common.thrift"
include "exception.thrift"

struct ConfigInfo {
  1: string name
  2: string value
  3: string source
}

struct GetConfigInfoListTOptions{}
struct GetConfigInfoListTResponse{
  1: list<ConfigInfo> configInfoList
}

enum MasterInfoField {
  MASTER_ADDRESS
  RPC_PORT
  SAFE_MODE
  START_TIME_MS
  UP_TIME_MS
  VERSION
  WEB_PORT
}

struct MasterInfo {
 1: string masterAddress
 2: i32 rpcPort
 3: bool safeMode
 4: i64 startTimeMs
 5: i64 upTimeMs
 6: string version
 7: i32 webPort
}

struct GetMasterInfoTOptions {
  1: set<MasterInfoField> filter
}

struct GetMasterInfoTResponse {
  1: MasterInfo masterInfo
}

/**
  * This interface contains meta master service endpoints for Alluxio clients.
  */
service MetaMasterClientService extends common.AlluxioService {
  /**
   * Returns information about the master.
   */
  GetMasterInfoTResponse getMasterInfo(
    /** the method options */ 1: GetMasterInfoTOptions options,
    )
    throws (1: exception.AlluxioTException e)

  /**
   * Returns a list of Alluxio runtime configuration information.
   */
  GetConfigInfoListTResponse getConfigInfoList(
    /** the method options */ 1: GetConfigInfoListTOptions options,
  ) throws (1: exception.AlluxioTException e)
}
