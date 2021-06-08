// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <wpi/numbers>

#include "frc/kinematics/ChassisSpeeds.h"
#include "frc/kinematics/DifferentialDriveKinematics.h"
#include "gtest/gtest.h"
#include "units/angular_velocity.h"
#include "units/length.h"
#include "units/velocity.h"

using namespace frc;

static constexpr double kEpsilon = 1E-9;

TEST(DifferentialDriveKinematics, InverseKinematicsFromZero) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const ChassisSpeeds chassisSpeeds;
  const auto wheelSpeeds = kinematics.ToWheelSpeeds(chassisSpeeds);

  EXPECT_NEAR(wheelSpeeds.left.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(wheelSpeeds.right.to<double>(), 0, kEpsilon);
}

TEST(DifferentialDriveKinematics, ForwardKinematicsFromZero) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const DifferentialDriveWheelSpeeds wheelSpeeds;
  const auto chassisSpeeds = kinematics.ToChassisSpeeds(wheelSpeeds);

  EXPECT_NEAR(chassisSpeeds.vx.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.vy.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.omega.to<double>(), 0, kEpsilon);
}

TEST(DifferentialDriveKinematics, InverseKinematicsForStraightLine) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const ChassisSpeeds chassisSpeeds{3.0_mps, 0_mps, 0_rad_per_s};
  const auto wheelSpeeds = kinematics.ToWheelSpeeds(chassisSpeeds);

  EXPECT_NEAR(wheelSpeeds.left.to<double>(), 3, kEpsilon);
  EXPECT_NEAR(wheelSpeeds.right.to<double>(), 3, kEpsilon);
}

TEST(DifferentialDriveKinematics, ForwardKinematicsForStraightLine) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const DifferentialDriveWheelSpeeds wheelSpeeds{3.0_mps, 3.0_mps};
  const auto chassisSpeeds = kinematics.ToChassisSpeeds(wheelSpeeds);

  EXPECT_NEAR(chassisSpeeds.vx.to<double>(), 3, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.vy.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.omega.to<double>(), 0, kEpsilon);
}

TEST(DifferentialDriveKinematics, InverseKinematicsForRotateInPlace) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const ChassisSpeeds chassisSpeeds{
      0.0_mps, 0.0_mps, units::radians_per_second_t{wpi::numbers::pi}};
  const auto wheelSpeeds = kinematics.ToWheelSpeeds(chassisSpeeds);

  EXPECT_NEAR(wheelSpeeds.left.to<double>(), -0.381 * wpi::numbers::pi,
              kEpsilon);
  EXPECT_NEAR(wheelSpeeds.right.to<double>(), +0.381 * wpi::numbers::pi,
              kEpsilon);
}

TEST(DifferentialDriveKinematics, ForwardKinematicsForRotateInPlace) {
  const DifferentialDriveKinematics kinematics{0.381_m * 2};
  const DifferentialDriveWheelSpeeds wheelSpeeds{
      units::meters_per_second_t(+0.381 * wpi::numbers::pi),
      units::meters_per_second_t(-0.381 * wpi::numbers::pi)};
  const auto chassisSpeeds = kinematics.ToChassisSpeeds(wheelSpeeds);

  EXPECT_NEAR(chassisSpeeds.vx.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.vy.to<double>(), 0, kEpsilon);
  EXPECT_NEAR(chassisSpeeds.omega.to<double>(), -wpi::numbers::pi, kEpsilon);
}
