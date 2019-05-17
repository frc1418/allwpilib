// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.drive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.wpilibj.MockSpeedController;
import org.junit.jupiter.api.Test;

class DifferentialDriveTest {
  @Test
  void testArcadeDrive() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.arcadeDrive(1.0, 0.0, false);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.arcadeDrive(0.5, -0.5, false);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward right turn
    drive.arcadeDrive(0.5, 0.5, false);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward
    drive.arcadeDrive(-1.0, 0.0, false);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.arcadeDrive(-0.5, -0.5, false);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward right turn
    drive.arcadeDrive(-0.5, 0.5, false);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);
  }

  @Test
  void testArcadeDriveSquared() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.arcadeDrive(1.0, 0.0, true);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.arcadeDrive(0.5, -0.5, true);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(0.5, right.get(), 1e-9);

    // Forward right turn
    drive.arcadeDrive(0.5, 0.5, true);
    assertEquals(0.5, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward
    drive.arcadeDrive(-1.0, 0.0, true);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.arcadeDrive(-0.5, -0.5, true);
    assertEquals(-0.5, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward right turn
    drive.arcadeDrive(-0.5, 0.5, true);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(-0.5, right.get(), 1e-9);
  }

  @Test
  void testCurvatureDrive() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.curvatureDrive(1.0, 0.0, false);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.curvatureDrive(0.5, -0.5, false);
    assertEquals(0.25, left.get(), 1e-9);
    assertEquals(0.75, right.get(), 1e-9);

    // Forward right turn
    drive.curvatureDrive(0.5, 0.5, false);
    assertEquals(0.75, left.get(), 1e-9);
    assertEquals(0.25, right.get(), 1e-9);

    // Backward
    drive.curvatureDrive(-1.0, 0.0, false);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.curvatureDrive(-0.5, -0.5, false);
    assertEquals(-0.75, left.get(), 1e-9);
    assertEquals(-0.25, right.get(), 1e-9);

    // Backward right turn
    drive.curvatureDrive(-0.5, 0.5, false);
    assertEquals(-0.25, left.get(), 1e-9);
    assertEquals(-0.75, right.get(), 1e-9);
  }

  @Test
  void testCurvatureDriveTurnInPlace() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.curvatureDrive(1.0, 0.0, true);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.curvatureDrive(0.5, -0.5, true);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward right turn
    drive.curvatureDrive(0.5, 0.5, true);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward
    drive.curvatureDrive(-1.0, 0.0, true);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.curvatureDrive(-0.5, -0.5, true);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(0.0, right.get(), 1e-9);

    // Backward right turn
    drive.curvatureDrive(-0.5, 0.5, true);
    assertEquals(0.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);
  }

  @Test
  void testTankDrive() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.tankDrive(1.0, 1.0, false);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.tankDrive(0.5, 1.0, false);
    assertEquals(0.5, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward right turn
    drive.tankDrive(1.0, 0.5, false);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(0.5, right.get(), 1e-9);

    // Backward
    drive.tankDrive(-1.0, -1.0, false);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.tankDrive(-0.5, -1.0, false);
    assertEquals(-0.5, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward right turn
    drive.tankDrive(-0.5, 1.0, false);
    assertEquals(-0.5, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);
  }

  @Test
  void testTankDriveSquared() {
    var left = new MockSpeedController();
    var right = new MockSpeedController();
    var drive = new DifferentialDrive(left, right);

    // Forward
    drive.tankDrive(1.0, 1.0, true);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward left turn
    drive.tankDrive(0.5, 1.0, true);
    assertEquals(0.25, left.get(), 1e-9);
    assertEquals(1.0, right.get(), 1e-9);

    // Forward right turn
    drive.tankDrive(1.0, 0.5, true);
    assertEquals(1.0, left.get(), 1e-9);
    assertEquals(0.25, right.get(), 1e-9);

    // Backward
    drive.tankDrive(-1.0, -1.0, true);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward left turn
    drive.tankDrive(-0.5, -1.0, true);
    assertEquals(-0.25, left.get(), 1e-9);
    assertEquals(-1.0, right.get(), 1e-9);

    // Backward right turn
    drive.tankDrive(-1.0, -0.5, true);
    assertEquals(-1.0, left.get(), 1e-9);
    assertEquals(-0.25, right.get(), 1e-9);
  }
}
