namespace java tachyon.thrift

include "common.thrift"
include "exception.thrift"

service KeyValueWorkerClientService extends common.TachyonService {
  /**
   * Accesses a block given the block id.
   */
  binary get( /** the id of the block being accessed */ 1: i64 blockId,
      /** binary of the key */ 2: binary key)
    throws (1: exception.TachyonTException e, 2: exception.ThriftIOException ioe)
}
