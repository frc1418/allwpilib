// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <google/protobuf/arena.h>
#include <gtest/gtest.h>

#include "frc/geometry/Rectangle2d.h"

using namespace frc;

namespace {

using ProtoType = wpi::Protobuf<frc::Rectangle2d>;

const Rectangle2d kExpectedData{
    Pose2d{Translation2d{0.191_m, 2.2_m}, Rotation2d{22.9_rad}}, 1.2_m, 2.3_m};
}  // namespace

TEST(Rectangle2dProtoTest, Roundtrip) {
  wpi::ProtobufMessage<Rectangle2d> message;
  wpi::SmallVector<uint8_t, 64> buf;

  ASSERT_TRUE(message.Pack(buf, kExpectedData));
  std::optional<Rectangle2d> unpacked_data = message.Unpack(buf);
  ASSERT_TRUE(unpacked_data.has_value());

  EXPECT_EQ(kExpectedData.Center(), unpacked_data.Center());
  EXPECT_EQ(kExpectedData.XWidth(), unpacked_data.XWidth());
  EXPECT_EQ(kExpectedData.YWidth(), unpacked_data.YWidth());
}
