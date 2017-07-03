/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "SpeedControllerGroup.h"

using namespace frc;

double SpeedControllerGroup::Get() const {
  if (!m_speedControllers.empty()) {
    return m_speedControllers.front().get().Get();
  }
  return 0.0;
}

void SpeedControllerGroup::Set(double speed) {
  for (auto speedController : m_speedControllers) {
    speedController.get().Set(m_isInverted ? -speed : speed);
  }
}

void SpeedControllerGroup::SetInverted(bool isInverted) {
  m_isInverted = isInverted;
}

bool SpeedControllerGroup::GetInverted() const { return m_isInverted; }

void SpeedControllerGroup::Disable() {
  for (auto speedController : m_speedControllers) {
    speedController.get().Disable();
  }
}

void SpeedControllerGroup::StopMotor() {
  for (auto speedController : m_speedControllers) {
    speedController.get().StopMotor();
  }
}

void SpeedControllerGroup::PIDWrite(double output) {
  for (auto speedController : m_speedControllers) {
    speedController.get().PIDWrite(output);
  }
}
