/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Translation2dTest {
  private static double kEpsilon = 1E-9;

  @Test
  void testSumAndDifference() {
    Translation2d one = new Translation2d(1.0, 3.0);
    Translation2d two = new Translation2d(2.0, 5.0);

    var sum = one.plus(two);
    var difference = one.minus(two);

    assertAll(
        () -> assertEquals(sum.getX(), 3.0, kEpsilon),
        () -> assertEquals(sum.getY(), 8.0, kEpsilon),
        () -> assertEquals(difference.getX(), -1.0, kEpsilon),
        () -> assertEquals(difference.getY(), -2.0, kEpsilon)
    );
  }

  @Test
  void testRotateBy() {
    Translation2d another = new Translation2d(3.0, 0.0);
    var rotated = another.rotateBy(Rotation2d.fromDegrees(90.0));

    assertAll(
        () -> assertEquals(rotated.getX(), 0.0, kEpsilon),
        () -> assertEquals(rotated.getY(), 3.0, kEpsilon)
    );
  }

  @Test
  void testScaling() {
    Translation2d original = new Translation2d(3.0, 5.0);
    var mult = original.times(3);
    var div = original.div(2);

    assertAll(
        () -> assertEquals(mult.getX(), 9.0, kEpsilon),
        () -> assertEquals(mult.getY(), 15.0, kEpsilon),
        () -> assertEquals(div.getX(), 1.5, kEpsilon),
        () -> assertEquals(div.getY(), 2.5, kEpsilon)
    );
  }

  @Test
  void testNorm() {
    Translation2d one = new Translation2d(3.0, 5.0);
    assertEquals(one.getNorm(), Math.hypot(3.0, 5.0), kEpsilon);
  }

  @Test
  void testDistance() {
    Translation2d one = new Translation2d(1, 1);
    Translation2d two = new Translation2d(6, 6);
    assertEquals(one.getDistance(two), 5 * Math.sqrt(2), kEpsilon);
  }
}
