// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.wpilib.math.geometry.Pose3d;
import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.math.geometry.Transform3d;
import org.wpilib.math.geometry.Translation3d;
import org.wpilib.math.util.Units;
import org.wpilib.wpilibj.UtilityClassTest;
import org.junit.jupiter.api.Test;

class ComputerVisionUtilTest extends UtilityClassTest<ComputerVisionUtil> {
  ComputerVisionUtilTest() {
    super(ComputerVisionUtil.class);
  }

  @Test
  void testObjectToRobotPose() {
    var robot = new Pose3d(1.0, 2.0, 0.0, new Rotation3d(0.0, 0.0, Units.degreesToRadians(30.0)));
    var cameraToObject =
        new Transform3d(
            new Translation3d(1.0, 1.0, 1.0),
            new Rotation3d(0.0, Units.degreesToRadians(-20.0), Units.degreesToRadians(45.0)));
    var robotToCamera =
        new Transform3d(
            new Translation3d(1.0, 0.0, 2.0),
            new Rotation3d(0.0, 0.0, Units.degreesToRadians(25.0)));
    Pose3d object = robot.plus(robotToCamera).plus(cameraToObject);

    assertEquals(
        robot, ComputerVisionUtil.objectToRobotPose(object, cameraToObject, robotToCamera));
  }
}
