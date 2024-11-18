// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.examples.differentialdriveposeestimator;

import org.wpilib.math.StateSpaceUtil;
import org.wpilib.math.VecBuilder;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.util.Units;

/** This dummy class represents a global measurement sensor, such as a computer vision solution. */
public final class ExampleGlobalMeasurementSensor {
  private ExampleGlobalMeasurementSensor() {
    // Utility class
  }

  /**
   * Get a "noisy" fake global pose reading.
   *
   * @param estimatedRobotPose The robot pose.
   */
  public static Pose2d getEstimatedGlobalPose(Pose2d estimatedRobotPose) {
    var rand =
        StateSpaceUtil.makeWhiteNoiseVector(VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30)));
    return new Pose2d(
        estimatedRobotPose.getX() + rand.get(0, 0),
        estimatedRobotPose.getY() + rand.get(1, 0),
        estimatedRobotPose.getRotation().plus(new Rotation2d(rand.get(2, 0))));
  }
}
