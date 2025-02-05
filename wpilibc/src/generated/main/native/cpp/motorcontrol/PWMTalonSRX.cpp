// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibc/generate_pwm_motor_controllers.py. DO NOT MODIFY

#include "frc/motorcontrol/PWMTalonSRX.h"

#include <hal/UsageReporting.h>

using namespace frc;

PWMTalonSRX::PWMTalonSRX(int channel) : PWMMotorController("PWMTalonSRX", channel) {
  m_pwm.SetBounds(2.004_ms, 1.52_ms, 1.5_ms, 1.48_ms, 0.997_ms);
  m_pwm.SetPeriodMultiplier(PWM::kPeriodMultiplier_1X);
  m_pwm.SetSpeed(0.0);
  m_pwm.SetZeroLatch();

  HAL_ReportUsage("PWMTalonSRX", GetChannel(), "");
}
