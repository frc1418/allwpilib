// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/motorcontrol/Talon.h"

#include <hal/FRCUsageReporting.h>

using namespace frc;

Talon::Talon(int channel) : PWMMotorController("Talon", channel) {
  m_pwm.SetBounds(2.037_ms, 1.539_ms, 1.513_ms, 1.487_ms, 0.989_ms);
  m_pwm.SetPeriodMultiplier(PWM::kPeriodMultiplier_1X);
  m_pwm.SetSpeed(0.0);
  m_pwm.SetZeroLatch();

  HAL_Report(HALUsageReporting::kResourceType_Talon, GetChannel() + 1);
}
