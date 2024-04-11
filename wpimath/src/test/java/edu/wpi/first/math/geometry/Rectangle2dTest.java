// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Rectangle2dTest {
  private static final double kEpsilon = 1E-9;
  
  @Test
  void testNewWithCorners() {
    var cornerA = new Translation2d(1.0, 2.0);
    var cornerB = new Translation2d(4.0, 6.0);

    var rect = new Rectangle2d(cornerA, cornerB);

    assertAll(
      () -> assertEquals(3.0, rect.getWidth()),
      () -> assertEquals(4.0, rect.getHeight()),
      () -> assertEquals(2.5, rect.getCenter().getX()),
      () -> assertEquals(4.0, rect.getCenter().getY()));
  }

  @Test
  void testIntersectsPoint() {
    var center = new Pose2d(4.0, 3.0, Rotation2d.fromDegrees(90.0));
    var rect = new Rectangle2d(center, 2.0, 3.0);

    assertAll(
      () -> assertTrue(rect.intersectsPoint(new Translation2d(5.5, 4.0))),
      () -> assertTrue(rect.containsPoint(new Translation2d(3.0, 2.0))),
      () -> assertFalse(rect.containsPoint(new Translation2d(4.0, 1.5))),
      () -> assertFalse(rect.intersectsPoint(new Translation2d(4.0, 3.5))));
  }

  @Test
  void testContainsPoint() {
    var center = new Pose2d(2.0, 3.0, Rotation2d.fromDegrees(45.0));
    var rect = new Rectangle2d(center, 3.0, 1.0);

    assertAll(
      () -> assertTrue(rect.containsPoint(new Translation2d(2.0, 3.0))),
      () -> assertTrue(rect.containsPoint(new Translation2d(3.0, 4.0))),
      () -> assertFalse(rect.containsPoint(new Translation2d(3.0, 3.0))));
  }

  @Test
  void testDistanceToPoint() {
    var center = new Pose2d(1.0, 2.0, Rotation2d.fromDegrees(270.0));
    var rect = new Rectangle2d(center, 1.0, 2.0);

    var point1 = new Translation2d(2.5, 2.0);
    var point2 = new Translation2d(1.0, 2.0);
    var point3 = new Translation2d(1.0, 1.0);
    var point4 = new Translation2d(-1.0, 2.5);

    assertAll(
      () -> assertEquals(0.5, rect.distanceToPoint(point1), kEpsilon),
      () -> assertEquals(0.0, rect.distanceToPoint(point2), kEpsilon),
      () -> assertEquals(0.5, rect.distanceToPoint(point3), kEpsilon),
      () -> assertEquals(1.0, rect.distanceToPoint(point4), kEpsilon));
  }

  @Test
  void testFindNearestPoint() {
    var center = new Pose2d(1.0, 1.0, Rotation2d.fromDegrees(90.0));
    var rect = new Rectangle2d(center, 3.0, 4.0);

    var point1 = new Translation2d(1.0, 3.0);
    var nearestPoint1 = rect.findNearestPoint(point1);

    var point2 = new Translation2d(0.0, 0.0);
    var nearestPoint2 = rect.findNearestPoint(point2);

    assertAll(
      () -> assertEquals(1.0, nearestPoint1.getX(), kEpsilon),
      () -> assertEquals(2.5, nearestPoint1.getY(), kEpsilon),
      () -> assertEquals(0.0, nearestPoint2.getX(), kEpsilon),
      () -> assertEquals(0.0, nearestPoint2.getY(), kEpsilon));
  }

  @Test
  void testEquals() {
    var center1 = new Pose2d(2.0, 3.0, new Rotation2d());
    var rect1 = new Rectangle2d(center1, 5.0, 3.0);

    var center2 = new Pose2d(2.0, 3.0, new Rotation2d());
    var rect2 = new Rectangle2d(center2, 5.0, 3.0);

    var center3 = new Pose2d(2.0, 3.0, new Rotation2d());
    var rect3 = new Rectangle2d(center3, 3.0, 3.0);

    assertAll(
      () -> assertTrue(rect1.equals(rect2)),
      () -> assertFalse(rect2.equals(rect3)));
  }
}
