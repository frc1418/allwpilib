/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <stdint.h>

#include <HAL/CANAPI.h>
#include <wpi/ArrayRef.h>

#include "ErrorBase.h"

namespace frc {
struct CANData {
  uint8_t data[8];
  int32_t length;
  uint64_t timestamp;
};

/**
 * High level class for interfacing with CAN devices conforming to
 * the standard CAN spec.
 *
 * No packets that can be sent gets blocked by the RoboRIO, so all methods
 * work identically in all robot modes.
 *
 * All methods are thread save, however the buffer objects passed in
 * by the user need to not be modified for the duration of their calls.
 */
class CAN : public ErrorBase {
 public:
  /**
   * Create a new CAN communication interface with the specific device ID.
   * The device ID is 6 bits (0-63)
   */
  explicit CAN(int deviceId);

  /**
   * Closes the CAN communication.
   */
  ~CAN() override;

  /**
   * Write a packet to the CAN device with a specific ID. This ID is 10 bits.
   *
   * @param data The data to write (8 bytes max)
   * @param length The data length to write
   * @param apiId The API ID to write.
   */
  void WritePacket(const uint8_t* data, int length, int apiId);

  /**
   * Write a repeating packet to the CAN device with a specific ID. This ID is
   * 10 bits. The RoboRIO will automatically repeat the packet at the specified
   * interval
   *
   * @param data The data to write (8 bytes max)
   * @param length The data length to write
   * @param apiId The API ID to write.
   * @param repeatMs The period to repeat the packet at.
   */
  void WritePacketRepeating(const uint8_t* data, int length, int apiId,
                            int repeatMs);

  /**
   * Stop a repeating packet with a specific ID. This ID is 10 bits.
   *
   * @param apiId The API ID to stop repeating
   */
  void StopPacketRepeating(int apiId);

  /**
   * Read a new CAN packet. This will only return properly once per packet
   * received. Multiple calls without receiving another packet will return
   * false.
   *
   * @param apiId The API ID to read.
   * @param data Storage for the received data.
   * @return True if the data is valid, otherwise false.
   */
  bool ReadPacketNew(int apiId, CANData* data);

  /**
   * Read a CAN packet. The will continuously return the last packet received,
   * without accounting for packet age.
   *
   * @param apiId The API ID to read.
   * @param data Storage for the received data.
   * @return True if the data is valid, otherwise false.
   */
  bool ReadPacketLatest(int apiId, CANData* data);

  /**
   * Read a CAN packet. The will return the last packet received until the
   * packet is older then the requested timeout. Then it will return false.
   *
   * @param apiId The API ID to read.
   * @param timeoutMs The timeout time for the packet
   * @param data Storage for the received data.
   * @return True if the data is valid, otherwise false.
   */
  bool ReadPacketTimeout(int apiId, int timeoutMs, CANData* data);

  /**
   * Read a CAN packet. The will return the last packet received until the
   * packet is older then the requested timeout. Then it will return false. The
   * period parameter is used when you know the packet is sent at specific
   * intervals, so calls will not attempt to read a new packet from the network
   * until that period has passed. We do not recommend users use this API unless
   * they know the implications.
   *
   * @param apiId The API ID to read.
   * @param timeoutMs The timeout time for the packet
   * @param periodMs The usual period for the packet
   * @param data Storage for the received data.
   * @return True if the data is valid, otherwise false.
   */
  bool ReadPeriodicPacket(int apiId, int timeoutMs, int periodMs,
                          CANData* data);

  static constexpr HAL_CANManufacturer kTeamManufacturer = HAL_CAN_Man_kTeamUse;
  static constexpr HAL_CANDeviceType kTeamDeviceType =
      HAL_CAN_Dev_kMiscellaneous;

 private:
  HAL_CANHandle m_handle{HAL_kInvalidHandle};
};
}  // namespace frc
