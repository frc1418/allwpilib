// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "controller.pb.h"
#include "frc/controller/DifferentialDriveWheelVoltages.h"

using namespace frc;

namespace {

using ProtoType = wpi::Protobuf<frc::DifferentialDriveWheelVoltages>;

const DifferentialDriveWheelVoltages kExpectedData =
    DifferentialDriveWheelVoltages{0.174, 0.191};
}  // namespace

TEST(DifferentialDriveWheelVoltagesProtoTest, Roundtrip) {
  wpi::proto::ProtobufDifferentialDriveWheelVoltages proto;
  ProtoType::Pack(&proto, kExpectedData);

  DifferentialDriveWheelVoltages unpacked_data = ProtoType::Unpack(proto);
  EXPECT_EQ(kExpectedData.left.value(), unpacked_data.left.value());
  EXPECT_EQ(kExpectedData.right.value(), unpacked_data.right.value());
}
