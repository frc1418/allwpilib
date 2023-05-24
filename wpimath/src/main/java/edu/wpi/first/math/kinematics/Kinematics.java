// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.kinematics;

import edu.wpi.first.math.geometry.Twist2d;

/** Helper class that converts a chassis velocity (dx and dtheta components) into wheel speeds. */
public interface Kinematics<T1, T2> {
  /**
   * Performs forward kinematics to return the resulting chassis speed from the wheel speeds. This
   * method is often used for odometry -- determining the robot's position on the field using data
   * from the real-world speed of each wheel on the robot.
   *
   * @param wheelSpeeds The speeds of the wheels.
   * @return The chassis speed.
   */
  ChassisSpeeds toChassisSpeeds(T1 wheelSpeeds);

  /**
   * Performs inverse kinematics to return the wheel speeds from a desired chassis velocity. This
   * method is often used to convert joystick values into wheel speeds.
   *
   * @param chassisSpeeds The desired chassis speed.
   * @return The wheel speeds.
   */
  T1 toWheelSpeeds(ChassisSpeeds chassisSpeeds);

  /**
   * Performs forward kinematics to return the resulting from the given wheel deltas. This method is
   * often used for odometry -- determining the robot's position on the field using changes in the
   * distance driven by each wheel on the robot.
   *
   * @param wheelDeltas The distances driven by each wheel.
   * @return The resulting Twist2d in the robot's movement.
   */
  Twist2d toTwist2d(T2 wheelDeltas);
}
