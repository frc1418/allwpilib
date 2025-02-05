// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibc/generate_pwm_motor_controllers.py. DO NOT MODIFY

#include "frc/motorcontrol/Jaguar.h"

#include <hal/UsageReporting.h>

using namespace frc;

Jaguar::Jaguar(int channel) : PWMMotorController("Jaguar", channel) {
  m_pwm.SetBounds(2.31_ms, 1.55_ms, 1.507_ms, 1.454_ms, 0.697_ms);
  m_pwm.SetPeriodMultiplier(PWM::kPeriodMultiplier_1X);
  m_pwm.SetSpeed(0.0);
  m_pwm.SetZeroLatch();

  HAL_ReportUsage("Jaguar", GetChannel(), "");
}
