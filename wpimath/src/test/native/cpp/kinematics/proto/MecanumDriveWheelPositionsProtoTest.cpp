// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "frc/kinematics/MecanumDriveWheelPositions.h"
#include "kinematics.pb.h"

using namespace frc;

namespace {

using ProtoType = wpi::Protobuf<frc::MecanumDriveWheelPositions>;

const MecanumDriveWheelPositions kExpectedData =
    MecanumDriveWheelPositions{17.4, 2.29, 22.9, 1.74};
}  // namespace

TEST(MecanumDriveWheelPositionsProtoTest, Roundtrip) {
  wpi::proto::ProtobufMecanumDriveWheelPositions proto;
  ProtoType::Pack(&proto, kExpectedData);

  MecanumDriveWheelPositions unpacked_data = ProtoType::Unpack(proto);
  EXPECT_EQ(kExpectedData.frontLeft.value(), unpacked_data.frontLeft.value());
  EXPECT_EQ(kExpectedData.frontRight.value(), unpacked_data.frontRight.value());
  EXPECT_EQ(kExpectedData.rearLeft.value(), unpacked_data.rearLeft.value());
  EXPECT_EQ(kExpectedData.rearRight.value(), unpacked_data.rearRight.value());
}
