/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package alluxio.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum MasterInfoField implements org.apache.thrift.TEnum {
  MASTER_ADDRESS(0),
  RPC_PORT(1),
  SAFE_MODE(2),
  START_TIME_MS(3),
  UP_TIME_MS(4),
  VERSION(5),
  WEB_PORT(6),
  ZOOKEEPER_ADDRESS(7),
  ZOOKEEPER_ENABLED(8);

  private final int value;

  private MasterInfoField(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static MasterInfoField findByValue(int value) { 
    switch (value) {
      case 0:
        return MASTER_ADDRESS;
      case 1:
        return RPC_PORT;
      case 2:
        return SAFE_MODE;
      case 3:
        return START_TIME_MS;
      case 4:
        return UP_TIME_MS;
      case 5:
        return VERSION;
      case 6:
        return WEB_PORT;
      case 7:
        return ZOOKEEPER_ADDRESS;
      case 8:
        return ZOOKEEPER_ENABLED;
      default:
        return null;
    }
  }
}
