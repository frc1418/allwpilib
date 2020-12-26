// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.trajectory;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.util.Units;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CentripetalAccelerationConstraintTest {
  @SuppressWarnings("LocalVariableName")
  @Test
  void testCentripetalAccelerationConstraint() {
    double maxCentripetalAcceleration = Units.feetToMeters(7.0); // 7 feet per second squared
    var constraint = new CentripetalAccelerationConstraint(maxCentripetalAcceleration);

    Trajectory trajectory = TrajectoryGeneratorTest.getTrajectory(
        Collections.singletonList(constraint));

    var duration = trajectory.getTotalTimeSeconds();
    var t = 0.0;
    var dt = 0.02;

    while (t < duration) {
      var point = trajectory.sample(t);
      var centripetalAcceleration
          = Math.pow(point.velocityMetersPerSecond, 2) * point.curvatureRadPerMeter;

      t += dt;
      assertTrue(centripetalAcceleration <= maxCentripetalAcceleration + 0.05);
    }
  }

}
