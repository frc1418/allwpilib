// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/DigitalInput.h"

#include <iostream>
#include <limits>

#include <hal/DIO.h>
#include <hal/FRCUsageReporting.h>
#include <hal/HALBase.h>
#include <hal/Ports.h>
#include <wpi/StackTrace.h>

#include "frc/Errors.h"
#include "frc/SensorUtil.h"
#include "frc/smartdashboard/SendableBuilder.h"
#include "frc/smartdashboard/SendableRegistry.h"

using namespace frc;

DigitalInput::DigitalInput(int channel) {
  if (!SensorUtil::CheckDigitalChannel(channel)) {
    throw FRC_MakeError(err::ChannelIndexOutOfRange,
                        "Digital Channel " + wpi::Twine{channel});
  }
  m_channel = channel;

  int32_t status = 0;
  std::string stackTrace = wpi::GetStackTrace(1);
  m_handle = HAL_InitializeDIOPort(HAL_GetPort(channel), true,
                                   stackTrace.c_str(), &status);
  FRC_CheckErrorStatus(status, "Digital Channel " + wpi::Twine{channel});

  HAL_Report(HALUsageReporting::kResourceType_DigitalInput, channel + 1);
  SendableRegistry::AddLW(this, "DigitalInput", channel);
}

DigitalInput::~DigitalInput() {
  HAL_FreeDIOPort(m_handle);
}

bool DigitalInput::Get() const {
  int32_t status = 0;
  bool value = HAL_GetDIO(m_handle, &status);
  FRC_CheckErrorStatus(status, "Get");
  return value;
}

HAL_Handle DigitalInput::GetPortHandleForRouting() const {
  return m_handle;
}

AnalogTriggerType DigitalInput::GetAnalogTriggerTypeForRouting() const {
  return static_cast<AnalogTriggerType>(0);
}

bool DigitalInput::IsAnalogTrigger() const {
  return false;
}

void DigitalInput::SetSimDevice(HAL_SimDeviceHandle device) {
  HAL_SetDIOSimDevice(m_handle, device);
}

int DigitalInput::GetChannel() const {
  return m_channel;
}

void DigitalInput::InitSendable(SendableBuilder& builder) {
  builder.SetSmartDashboardType("Digital Input");
  builder.AddBooleanProperty(
      "Value", [=]() { return Get(); }, nullptr);
}
