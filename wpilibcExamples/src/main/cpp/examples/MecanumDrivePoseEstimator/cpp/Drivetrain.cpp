// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "Drivetrain.h"

#include <frc/Timer.h>

#include "ExampleGlobalMeasurementSensor.h"

frc::MecanumDriveWheelSpeeds Drivetrain::GetCurrentState() const {
  return {units::meters_per_second_t(m_frontLeftEncoder.GetRate()),
          units::meters_per_second_t(m_frontRightEncoder.GetRate()),
          units::meters_per_second_t(m_backLeftEncoder.GetRate()),
          units::meters_per_second_t(m_backRightEncoder.GetRate())};
}

void Drivetrain::SetSpeeds(const frc::MecanumDriveWheelSpeeds& wheelSpeeds) {
  std::function<void(units::meters_per_second_t, const frc::Encoder&,
                     frc2::PIDController&, frc::PWMSparkMax&)>
      calcAndSetSpeeds = [&m_feedforward = m_feedforward](
                             units::meters_per_second_t speed,
                             const auto& encoder, auto& controller,
                             auto& motor) {
        auto feedforward = m_feedforward.Calculate(speed);
        double output = controller.Calculate(encoder.GetRate(), speed.value());
        motor.SetVoltage(units::volt_t{output} + feedforward);
      };

  calcAndSetSpeeds(wheelSpeeds.frontLeft, m_frontLeftEncoder,
                   m_frontLeftPIDController, m_frontLeftMotor);
  calcAndSetSpeeds(wheelSpeeds.frontRight, m_frontRightEncoder,
                   m_frontRightPIDController, m_frontRightMotor);
  calcAndSetSpeeds(wheelSpeeds.rearLeft, m_backLeftEncoder,
                   m_backLeftPIDController, m_backLeftMotor);
  calcAndSetSpeeds(wheelSpeeds.rearRight, m_backRightEncoder,
                   m_backRightPIDController, m_backRightMotor);
}

void Drivetrain::Drive(units::meters_per_second_t xSpeed,
                       units::meters_per_second_t ySpeed,
                       units::radians_per_second_t rot, bool fieldRelative) {
  auto wheelSpeeds = m_kinematics.ToWheelSpeeds(
      fieldRelative ? frc::ChassisSpeeds::FromFieldRelativeSpeeds(
                          xSpeed, ySpeed, rot, m_gyro.GetRotation2d())
                    : frc::ChassisSpeeds{xSpeed, ySpeed, rot});
  wheelSpeeds.Normalize(kMaxSpeed);
  SetSpeeds(wheelSpeeds);
}

void Drivetrain::UpdateOdometry() {
  m_poseEstimator.Update(m_gyro.GetRotation2d(), GetCurrentState());

  // Also apply vision measurements. We use 0.3 seconds in the past as an
  // example -- on a real robot, this must be calculated based either on latency
  // or timestamps.
  m_poseEstimator.AddVisionMeasurement(
      ExampleGlobalMeasurementSensor::GetEstimatedGlobalPose(
          m_poseEstimator.GetEstimatedPosition()),
      frc::Timer::GetFPGATimestamp() - 0.3_s);
}
