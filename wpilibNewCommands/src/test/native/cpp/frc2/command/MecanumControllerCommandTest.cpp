/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <frc2/Timer.h>
#include <frc2/command/MecanumControllerCommand.h>
#include <frc2/command/Subsystem.h>

#include <iostream>

#include <frc/controller/PIDController.h>
#include <frc/controller/ProfiledPIDController.h>
#include <frc/geometry/Rotation2d.h>
#include <frc/geometry/Translation2d.h>
#include <frc/kinematics/MecanumDriveKinematics.h>
#include <frc/kinematics/MecanumDriveOdometry.h>
#include <frc/trajectory/TrajectoryGenerator.h>
#include <wpi/math>

#include "gtest/gtest.h"

#define EXPECT_NEAR_UNITS(val1, val2, eps) \
  EXPECT_LE(units::math::abs(val1 - val2), eps)

class MecanumControllerCommandTest : public ::testing::Test {
  using radians_per_second_squared_t =
      units::compound_unit<units::radians,
                           units::inverse<units::squared<units::second>>>;

 protected:
  frc2::Timer m_timer;
  frc::Rotation2d m_angle{0_rad};

  units::meters_per_second_t m_frontLeftSpeed = 0.0_mps;
  units::meters_per_second_t m_rearLeftSpeed = 0.0_mps;
  units::meters_per_second_t m_frontRightSpeed = 0.0_mps;
  units::meters_per_second_t m_rearRightSpeed = 0.0_mps;

  frc::ProfiledPIDController<units::radians> m_rotController{
      1, 0, 0,
      frc::TrapezoidProfile<units::radians>::Constraints{
          9_rad_per_s, units::unit_t<radians_per_second_squared_t>(3)}};

  static constexpr units::meter_t kxTolerance{1 / 12.0};
  static constexpr units::meter_t kyTolerance{1 / 12.0};
  static constexpr units::radian_t kAngularTolerance{1 / 12.0};

  static constexpr units::meter_t kWheelBase{0.5};
  static constexpr units::meter_t kTrackWidth{0.5};

  frc::MecanumDriveKinematics m_kinematics{
      frc::Translation2d{kWheelBase / 2, kTrackWidth / 2},
      frc::Translation2d{kWheelBase / 2, -kTrackWidth / 2},
      frc::Translation2d{-kWheelBase / 2, kTrackWidth / 2},
      frc::Translation2d{-kWheelBase / 2, -kTrackWidth / 2}};

  frc::MecanumDriveOdometry m_odometry{m_kinematics, 0_rad,
                                       frc::Pose2d{0_m, 0_m, 0_rad}};

  frc::MecanumDriveWheelSpeeds getCurrentWheelSpeeds() {
    return frc::MecanumDriveWheelSpeeds{m_frontLeftSpeed, m_frontRightSpeed,
                                        m_rearLeftSpeed, m_rearRightSpeed};
  }

  frc::Pose2d getRobotPose() {
    m_odometry.UpdateWithTime(m_timer.Get(), m_angle, getCurrentWheelSpeeds());
    return m_odometry.GetPose();
  }
};

TEST_F(MecanumControllerCommandTest, ReachesReference) {
  frc2::Subsystem subsystem{};

  auto waypoints =
      std::vector{frc::Pose2d{0_m, 0_m, 0_rad}, frc::Pose2d{1_m, 5_m, 3_rad}};
  auto trajectory = frc::TrajectoryGenerator::GenerateTrajectory(
      waypoints, {8.8_mps, 0.1_mps_sq});

  auto endState = trajectory.Sample(trajectory.TotalTime());

  auto command = frc2::MecanumControllerCommand(
      trajectory, [&]() { return getRobotPose(); }, m_kinematics,

      frc2::PIDController(0.6, 0, 0), frc2::PIDController(0.6, 0, 0),
      m_rotController, units::meters_per_second_t(8.8),
      [&](units::meters_per_second_t frontLeft,
          units::meters_per_second_t rearLeft,
          units::meters_per_second_t frontRight,
          units::meters_per_second_t rearRight) {
        m_frontLeftSpeed = frontLeft;
        m_rearLeftSpeed = rearLeft;
        m_frontRightSpeed = frontRight;
        m_rearRightSpeed = rearRight;
      },
      {&subsystem});

  m_timer.Reset();
  m_timer.Start();
  command.Initialize();
  while (!command.IsFinished()) {
    command.Execute();
    m_angle = trajectory.Sample(m_timer.Get()).pose.Rotation();
  }
  m_timer.Stop();
  command.End(false);

  EXPECT_NEAR_UNITS(endState.pose.X(), getRobotPose().X(), kxTolerance);
  EXPECT_NEAR_UNITS(endState.pose.Y(), getRobotPose().Y(), kyTolerance);
  EXPECT_NEAR_UNITS(endState.pose.Rotation().Radians(),
                    getRobotPose().Rotation().Radians(), kAngularTolerance);
}
