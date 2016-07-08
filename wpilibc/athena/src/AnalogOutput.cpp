/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2014-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "AnalogOutput.h"
#include "HAL/HAL.h"
#include "LiveWindow/LiveWindow.h"
#include "WPIErrors.h"

#include <limits>
#include <sstream>

/**
 * Construct an analog output on the given channel.
 *
 * All analog outputs are located on the MXP port.
 *
 * @param channel The channel number on the roboRIO to represent.
 */
AnalogOutput::AnalogOutput(uint32_t channel) {
  std::stringstream buf;
  buf << "analog input " << channel;

  if (!HAL_CheckAnalogOutputChannel(channel)) {
    wpi_setWPIErrorWithContext(ChannelIndexOutOfRange, buf.str());
    m_channel = std::numeric_limits<uint32_t>::max();
    m_port = HAL_kInvalidHandle;
    return;
  }

  m_channel = channel;

  HAL_PortHandle port = HAL_GetPort(m_channel);
  int32_t status = 0;
  m_port = HAL_InitializeAnalogOutputPort(port, &status);
  if (status != 0) {
    wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
    m_channel = std::numeric_limits<uint32_t>::max();
    m_port = HAL_kInvalidHandle;
    return;
  }

  LiveWindow::GetInstance()->AddActuator("AnalogOutput", m_channel, this);
  HAL_Report(HALUsageReporting::kResourceType_AnalogOutput, m_channel);
}

/**
 * Destructor.
 *
 * Frees analog output resource.
 */
AnalogOutput::~AnalogOutput() { HAL_FreeAnalogOutputPort(m_port); }

/**
 * Set the value of the analog output.
 *
 * @param voltage The output value in Volts, from 0.0 to +5.0
 */
void AnalogOutput::SetVoltage(float voltage) {
  int32_t status = 0;
  HAL_SetAnalogOutput(m_port, voltage, &status);

  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
}

/**
 * Get the voltage of the analog output
 *
 * @return The value in Volts, from 0.0 to +5.0
 */
float AnalogOutput::GetVoltage() const {
  int32_t status = 0;
  float voltage = HAL_GetAnalogOutput(m_port, &status);

  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));

  return voltage;
}

void AnalogOutput::UpdateTable() {
  if (m_table != nullptr) {
    m_table->PutNumber("Value", GetVoltage());
  }
}

void AnalogOutput::StartLiveWindowMode() {}

void AnalogOutput::StopLiveWindowMode() {}

std::string AnalogOutput::GetSmartDashboardType() const {
  return "Analog Output";
}

void AnalogOutput::InitTable(std::shared_ptr<ITable> subTable) {
  m_table = subTable;
  UpdateTable();
}

std::shared_ptr<ITable> AnalogOutput::GetTable() const { return m_table; }
