// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "frc/trajectory/Trajectory.h"

using namespace frc;

namespace {

using ProtoType = wpi::Protobuf<frc::Trajectory::State>;

const Trajectory::State kExpectedData = Trajectory::State{
    1.91_s, 4.4_mps, 17.4_mps_sq,
    Pose2d{Translation2d{1.74_m, 19.1_m}, Rotation2d{22.9_rad}},
    units::curvature_t{0.174}};
}  // namespace

TEST(TrajectoryStateProtoTest, Roundtrip) {
  google::protobuf::Arena arena;
  google::protobuf::Message* proto = ProtoType::New(&arena);
  ProtoType::Pack(proto, kExpectedData);

  Trajectory::State unpacked_data = ProtoType::Unpack(*proto);
  EXPECT_EQ(kExpectedData.t.value(), unpacked_data.t.value());
  EXPECT_EQ(kExpectedData.velocity.value(), unpacked_data.velocity.value());
  EXPECT_EQ(kExpectedData.acceleration.value(),
            unpacked_data.acceleration.value());
  EXPECT_EQ(kExpectedData.pose, unpacked_data.pose);
  EXPECT_EQ(kExpectedData.curvature.value(), unpacked_data.curvature.value());
}
