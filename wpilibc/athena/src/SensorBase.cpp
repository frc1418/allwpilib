/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "SensorBase.h"

#include "FRC_NetworkCommunication/LoadOut.h"
#include "HAL/AnalogInput.h"
#include "HAL/AnalogOutput.h"
#include "HAL/DIO.h"
#include "HAL/HAL.h"
#include "HAL/PDP.h"
#include "HAL/PWM.h"
#include "HAL/Ports.h"
#include "HAL/Relay.h"
#include "HAL/Solenoid.h"
#include "WPIErrors.h"

const int frc::SensorBase::kDigitalChannels = HAL_GetNumDigitalChannels();
const int frc::SensorBase::kAnalogInputs = HAL_GetNumAnalogInputs();
const int frc::SensorBase::kSolenoidChannels = HAL_GetNumSolenoidChannels();
const int frc::SensorBase::kSolenoidModules = HAL_GetNumPCMModules();
const int frc::SensorBase::kPwmChannels = HAL_GetNumPWMChannels();
const int frc::SensorBase::kRelayChannels = HAL_GetNumRelayHeaders();
const int frc::SensorBase::kPDPChannels = HAL_GetNumPDPChannels();

/**
 * Check that the solenoid module number is valid.
 *
 * @return Solenoid module is valid and present
 */
bool frc::SensorBase::CheckSolenoidModule(int moduleNumber) {
  return HAL_CheckSolenoidModule(moduleNumber);
}

/**
 * Check that the digital channel number is valid.
 *
 * Verify that the channel number is one of the legal channel numbers. Channel
 * numbers are 1-based.
 *
 * @return Digital channel is valid
 */
bool frc::SensorBase::CheckDigitalChannel(int channel) {
  return HAL_CheckDIOChannel(channel);
}

/**
 * Check that the relay channel number is valid.
 *
 * Verify that the channel number is one of the legal channel numbers. Channel
 * numbers are 0-based.
 *
 * @return Relay channel is valid
 */
bool frc::SensorBase::CheckRelayChannel(int channel) {
  return HAL_CheckRelayChannel(channel);
}

/**
 * Check that the digital channel number is valid.
 *
 * Verify that the channel number is one of the legal channel numbers. Channel
 * numbers are 1-based.
 *
 * @return PWM channel is valid
 */
bool frc::SensorBase::CheckPWMChannel(int channel) {
  return HAL_CheckPWMChannel(channel);
}

/**
 * Check that the analog input number is value.
 *
 * Verify that the analog input number is one of the legal channel numbers.
 * Channel numbers are 0-based.
 *
 * @return Analog channel is valid
 */
bool frc::SensorBase::CheckAnalogInputChannel(int channel) {
  return HAL_CheckAnalogInputChannel(channel);
}

/**
 * Check that the analog output number is valid.
 *
 * Verify that the analog output number is one of the legal channel numbers.
 * Channel numbers are 0-based.
 *
 * @return Analog channel is valid
 */
bool frc::SensorBase::CheckAnalogOutputChannel(int channel) {
  return HAL_CheckAnalogOutputChannel(channel);
}

/**
 * Verify that the solenoid channel number is within limits.
 *
 * @return Solenoid channel is valid
 */
bool frc::SensorBase::CheckSolenoidChannel(int channel) {
  return HAL_CheckSolenoidChannel(channel);
}

/**
 * Verify that the power distribution channel number is within limits.
 *
 * @return PDP channel is valid
 */
bool frc::SensorBase::CheckPDPChannel(int channel) {
  return HAL_CheckPDPModule(channel);
}
