// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <google/protobuf/arena.h>
#include <gtest/gtest.h>

#include "frc/kinematics/DifferentialDriveKinematics.h"

using namespace frc;

namespace {

using ProtoType = wpi::Protobuf<frc::DifferentialDriveKinematics>;

const DifferentialDriveKinematics kExpectedData =
    DifferentialDriveKinematics{1.74_m};
}  // namespace

TEST(DifferentialDriveKinematicsProtoTest, Roundtrip) {
  wpi::ProtobufMessage<DifferentialDriveKinematics> message;
  wpi::SmallVector<uint8_t, 64> buf;

  ASSERT_TRUE(message.Pack(buf, kExpectedData));
  std::optional<DifferentialDriveKinematics> unpacked_data =
      message.Unpack(buf);
  ASSERT_TRUE(unpacked_data.has_value());

  EXPECT_EQ(kExpectedData.trackWidth.value(), unpacked_data.trackWidth.value());
}
