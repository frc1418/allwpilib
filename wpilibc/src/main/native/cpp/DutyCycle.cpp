/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "frc/DutyCycle.h"

#include <hal/DutyCycle.h>
#include <hal/HALBase.h>

#include "frc/DigitalSource.h"
#include "frc/WPIErrors.h"
#include "frc/smartdashboard/SendableBuilder.h"

using namespace frc;

DutyCycle::DutyCycle(DigitalSource* source)
    : m_source{source, NullDeleter<DigitalSource>()} {
  if (m_source == nullptr) {
    wpi_setWPIError(NullParameter);
  } else {
    InitDutyCycle();
  }
}

DutyCycle::DutyCycle(DigitalSource& source)
    : m_source{&source, NullDeleter<DigitalSource>()} {
  InitDutyCycle();
}

DutyCycle::DutyCycle(std::shared_ptr<DigitalSource> source)
    : m_source{std::move(source)} {
  if (m_source == nullptr) {
    wpi_setWPIError(NullParameter);
  } else {
    InitDutyCycle();
  }
}

DutyCycle::~DutyCycle() { HAL_FreeDutyCycle(m_handle); }

int DutyCycle::GetFrequency() const {
  int32_t status = 0;
  auto retVal = HAL_GetDutyCycleFrequency(m_handle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
  return retVal;
}

unsigned int DutyCycle::GetOutputRaw() const {
  int32_t status = 0;
  auto retVal = HAL_GetDutyCycleOutput(m_handle, &status);
  wpi_setErrorWithContext(status, HAL_GetErrorMessage(status));
  return retVal;
}

void DutyCycle::InitSendable(SendableBuilder& builder) {
  builder.SetSmartDashboardType("Duty Cycle");
}
