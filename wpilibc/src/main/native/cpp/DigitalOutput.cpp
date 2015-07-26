/*----------------------------------------------------------------------------*/
/* Copyright (c) 2008-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "DigitalOutput.h"

#include <limits>

#include <HAL/DIO.h>
#include <HAL/HAL.h>
#include <HAL/Ports.h>

#include "SensorUtil.h"
#include "SmartDashboard/SendableBuilder.h"
#include "WPIErrors.h"

using namespace frc;

/**
 * Create an instance of a digital output.
 *
 * Create a digital output given a channel.
 *
 * @param channel The digital channel 0-9 are on-board, 10-25 are on the MXP
 *                port
 */
DigitalOutput::DigitalOutput(int channel) {
  m_pwmGenerator = HAL_kInvalidHandle;
  if (!SensorUtil::CheckDigitalChannel(channel)) {
    wpi_setWPIErrorWithContext(ChannelIndexOutOfRange,
                               "Digital Channel " + wpi::Twine(channel));
    m_channel = std::numeric_limits<int>::max();
    return;
  }
  m_channel = channel;

  int32_t status = 0;
  m_handle = HAL_InitializeDIOPort(HAL_GetPort(channel), false, &status);
  if (status != 0) {
    wpi_setErrorWithContextRange(status, 0, HAL_GetNumDigitalChannels(),
                                 channel, HAL_GetErrorMessage(status));
    m_channel = std::numeric_limits<int>::max();
    m_handle = HAL_kInvalidHandle;
    return;
  }

  HAL_Report(HALUsageReporting::kResourceType_DigitalOutput, channel);
  SetName("DigitalOutput", channel);
}

/**
 * Free the resources associated with a digital output.
 */
DigitalOutput::~DigitalOutput() {
  if (StatusIsFatal()) return;
  // Disable the PWM in case it was running.
  DisablePWM();

  HAL_FreeDIOPort(m_handle);
}

/**
 * Set the value of a digital output.
 *
 * Set the value of a digital output to either one (true) or zero (false).
 *
 * @param value 1 (true) for high, 0 (false) for disabled
 */
void DigitalOutput::Set(bool value) {
  if (StatusIsFatal()) return;

  int32_t status = 0;
  HAL_SetDIO(m_handle, value, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

/**
 * Gets the value being output from the Digital Output.
 *
 * @return the state of the digital output.
 */
bool DigitalOutput::Get() const {
  if (StatusIsFatal()) return false;

  int32_t status = 0;
  bool val = HAL_GetDIO(m_handle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
  return val;
}

/**
 * @return The GPIO channel number that this object represents.
 */
int DigitalOutput::GetChannel() const { return m_channel; }

/**
 * Output a single pulse on the digital output line.
 *
 * Send a single pulse on the digital output line where the pulse duration is
 * specified in seconds. Maximum pulse length is 0.0016 seconds.
 *
 * @param length The pulse length in seconds
 */
void DigitalOutput::Pulse(double length) {
  if (StatusIsFatal()) return;

  int32_t status = 0;
  HAL_Pulse(m_handle, length, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

/**
 * Determine if the pulse is still going.
 *
 * Determine if a previously started pulse is still going.
 */
bool DigitalOutput::IsPulsing() const {
  if (StatusIsFatal()) return false;

  int32_t status = 0;
  bool value = HAL_IsPulsing(m_handle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
  return value;
}

/**
 * Change the PWM frequency of the PWM output on a Digital Output line.
 *
 * The valid range is from 0.6 Hz to 19 kHz.  The frequency resolution is
 * logarithmic.
 *
 * There is only one PWM frequency for all digital channels.
 *
 * @param rate The frequency to output all digital output PWM signals.
 */
void DigitalOutput::SetPWMRate(double rate) {
  if (StatusIsFatal()) return;

  int32_t status = 0;
  HAL_SetDigitalPWMRate(rate, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

/**
 * Enable a PWM Output on this line.
 *
 * Allocate one of the 6 DO PWM generator resources from this module.
 *
 * Supply the initial duty-cycle to output so as to avoid a glitch when first
 * starting.
 *
 * The resolution of the duty cycle is 8-bit for low frequencies (1kHz or less)
 * but is reduced the higher the frequency of the PWM signal is.
 *
 * @param initialDutyCycle The duty-cycle to start generating. [0..1]
 */
void DigitalOutput::EnablePWM(double initialDutyCycle) {
  if (m_pwmGenerator != HAL_kInvalidHandle) return;

  int32_t status = 0;

  if (StatusIsFatal()) return;
  m_pwmGenerator = HAL_AllocateDigitalPWM(&status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));

  if (StatusIsFatal()) return;
  HAL_SetDigitalPWMDutyCycle(m_pwmGenerator, initialDutyCycle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));

  if (StatusIsFatal()) return;
  HAL_SetDigitalPWMOutputChannel(m_pwmGenerator, m_channel, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

/**
 * Change this line from a PWM output back to a static Digital Output line.
 *
 * Free up one of the 6 DO PWM generator resources that were in use.
 */
void DigitalOutput::DisablePWM() {
  if (StatusIsFatal()) return;
  if (m_pwmGenerator == HAL_kInvalidHandle) return;

  int32_t status = 0;

  // Disable the output by routing to a dead bit.
  HAL_SetDigitalPWMOutputChannel(m_pwmGenerator, SensorUtil::kDigitalChannels,
                                 &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));

  HAL_FreeDigitalPWM(m_pwmGenerator, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));

  m_pwmGenerator = HAL_kInvalidHandle;
}

/**
 * Change the duty-cycle that is being generated on the line.
 *
 * The resolution of the duty cycle is 8-bit for low frequencies (1kHz or less)
 * but is reduced the higher the frequency of the PWM signal is.
 *
 * @param dutyCycle The duty-cycle to change to. [0..1]
 */
void DigitalOutput::UpdateDutyCycle(double dutyCycle) {
  if (StatusIsFatal()) return;

  int32_t status = 0;
  HAL_SetDigitalPWMDutyCycle(m_pwmGenerator, dutyCycle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

void DigitalOutput::InitSendable(SendableBuilder& builder) {
  builder.SetSmartDashboardType("Digital Output");
  builder.AddBooleanProperty("Value", [=]() { return Get(); },
                             [=](bool value) { Set(value); });
}
