// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.kinematics.proto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.wpilib.math.kinematics.ChassisSpeeds;
import org.wpilib.math.proto.Kinematics.ProtobufChassisSpeeds;
import org.junit.jupiter.api.Test;

class ChassisSpeedsProtoTest {
  private static final ChassisSpeeds DATA = new ChassisSpeeds(2.29, 2.2, 0.3504);

  @Test
  void testRoundtrip() {
    ProtobufChassisSpeeds proto = ChassisSpeeds.proto.createMessage();
    ChassisSpeeds.proto.pack(proto, DATA);

    ChassisSpeeds data = ChassisSpeeds.proto.unpack(proto);
    assertEquals(DATA.vxMetersPerSecond, data.vxMetersPerSecond);
    assertEquals(DATA.vyMetersPerSecond, data.vyMetersPerSecond);
    assertEquals(DATA.omegaRadiansPerSecond, data.omegaRadiansPerSecond);
  }
}
