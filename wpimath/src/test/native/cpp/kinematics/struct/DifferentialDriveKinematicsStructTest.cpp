// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "frc/kinematics/DifferentialDriveKinematics.h"

using namespace frc;

namespace {

using StructType = wpi::Struct<frc::DifferentialDriveKinematics>;
const DifferentialDriveKinematics kExpectedData{
    DifferentialDriveKinematics{1.74}};
}  // namespace

TEST(DifferentialDriveKinematicsStructTest, Roundtrip) {
  uint8_t buffer[StructType::kSize];
  std::memset(buffer, 0, StructType::kSize);
  StructType::Pack(buffer, kExpectedData);

  DifferentialDriveKinematics unpacked_data = StructType::Unpack(buffer);

  EXPECT_EQ(kExpectedData.trackWidth.value(), unpacked_data.trackWidth.value());
}
