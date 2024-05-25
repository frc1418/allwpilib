// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "subsystems/Shooter.h"

#include <frc2/command/Commands.h>
#include <frc2/command/button/Trigger.h>

Shooter::Shooter() {
  m_shooterFeedback.SetTolerance(ShooterConstants::kShooterTolerance.value());
  m_shooterEncoder.SetDistancePerPulse(
      ShooterConstants::kEncoderDistancePerPulse);

  SetDefaultCommand(RunOnce([this] {
                      m_shooterMotor.Set(0.0);
                      m_feederMotor.Set(0.0);
                    })
                        .AndThen(Run([] {}))
                        .IgnoringDisable(true)
                        .WithName("Idle"));
}

units::turns_per_second_t Shooter::GetShooterVelocity() {
  return units::turns_per_second_t(m_shooterEncoder.GetRate());
}

frc2::CommandPtr Shooter::ShootCommand(units::turns_per_second_t setpoint) {
  return frc2::cmd::Parallel(
             // Run the shooter flywheel at the desired setpoint using
             // feedforward and feedback
             Run([this, setpoint] {
               m_shooterMotor.SetVoltage(
                   m_shooterFeedforward.Calculate(setpoint) +
                   units::volt_t(m_shooterFeedback.Calculate(
                       GetShooterVelocity().value(), setpoint.value())));
             }),
             // Wait until the shooter has reached the setpoint, and then
             // run the feeder
             frc2::cmd::WaitUntil([this] {
               return m_shooterFeedback.AtSetpoint();
             }).AndThen([this] {
               m_feederMotor.Set(ShooterConstants::kFeederSpeed);
             }))
      .WithName("Shoot");
}
