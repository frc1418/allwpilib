// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.trajectory.proto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Translation2d;
import org.wpilib.math.proto.Trajectory.ProtobufTrajectoryState;
import org.wpilib.math.trajectory.Trajectory;
import org.junit.jupiter.api.Test;

class TrajectoryStateProtoTest {
  private static final Trajectory.State DATA =
      new Trajectory.State(
          1.91, 4.4, 17.4, new Pose2d(new Translation2d(1.74, 19.1), new Rotation2d(22.9)), 0.174);

  @Test
  void testRoundtrip() {
    ProtobufTrajectoryState proto = Trajectory.State.proto.createMessage();
    Trajectory.State.proto.pack(proto, DATA);

    Trajectory.State data = Trajectory.State.proto.unpack(proto);
    assertEquals(DATA.timeSeconds, data.timeSeconds);
    assertEquals(DATA.velocityMetersPerSecond, data.velocityMetersPerSecond);
    assertEquals(DATA.accelerationMetersPerSecondSq, data.accelerationMetersPerSecondSq);
    assertEquals(DATA.poseMeters, data.poseMeters);
    assertEquals(DATA.curvatureRadPerMeter, data.curvatureRadPerMeter);
  }
}
