// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibc/generate_pwm_motor_controllers.py. DO NOT MODIFY

#include "frc/motorcontrol/PWMSparkFlex.h"

#include <hal/UsageReporting.h>

using namespace frc;

PWMSparkFlex::PWMSparkFlex(int channel) : PWMMotorController("PWMSparkFlex", channel) {
  m_pwm.SetBounds(2.003_ms, 1.55_ms, 1.5_ms, 1.46_ms, 0.999_ms);
  m_pwm.SetPeriodMultiplier(PWM::kPeriodMultiplier_1X);
  m_pwm.SetSpeed(0.0);
  m_pwm.SetZeroLatch();

  HAL_ReportUsage("DIO", GetChannel(), "RevSparkFlexPWM");
}
