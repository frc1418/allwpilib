// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.drive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.wpilibj.MockSpeedController;
import org.junit.jupiter.api.Test;

class MecanumDriveTest {
  @Test
  void testCartesian() {
    var fl = new MockSpeedController();
    var fr = new MockSpeedController();
    var rl = new MockSpeedController();
    var rr = new MockSpeedController();
    var drive = new MecanumDrive(fl, fr, rl, rr);

    // Forward
    drive.driveCartesian(1.0, 0.0, 0.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Left
    drive.driveCartesian(0.0, -1.0, 0.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);

    // Right
    drive.driveCartesian(0.0, 1.0, 0.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CCW
    drive.driveCartesian(0.0, 0.0, -1.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CW
    drive.driveCartesian(0.0, 0.0, 1.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);
  }

  @Test
  void testCartesiangyro90CW() {
    var fl = new MockSpeedController();
    var fr = new MockSpeedController();
    var rl = new MockSpeedController();
    var rr = new MockSpeedController();
    var drive = new MecanumDrive(fl, fr, rl, rr);

    // Forward in global frame; left in robot frame
    drive.driveCartesian(1.0, 0.0, 0.0, 90.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);

    // Left in global frame; backward in robot frame
    drive.driveCartesian(0.0, -1.0, 0.0, 90.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);

    // Right in global frame; forward in robot frame
    drive.driveCartesian(0.0, 1.0, 0.0, 90.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CCW
    drive.driveCartesian(0.0, 0.0, -1.0, 90.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CW
    drive.driveCartesian(0.0, 0.0, 1.0, 90.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);
  }

  @Test
  void testPolar() {
    var fl = new MockSpeedController();
    var fr = new MockSpeedController();
    var rl = new MockSpeedController();
    var rr = new MockSpeedController();
    var drive = new MecanumDrive(fl, fr, rl, rr);

    // Forward
    drive.drivePolar(1.0, 0.0, 0.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Left
    drive.drivePolar(1.0, -90.0, 0.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);

    // Right
    drive.drivePolar(1.0, 90.0, 0.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CCW
    drive.drivePolar(0.0, 0.0, -1.0);
    assertEquals(-1.0, fl.get(), 1e-9);
    assertEquals(-1.0, fr.get(), 1e-9);
    assertEquals(1.0, rl.get(), 1e-9);
    assertEquals(1.0, rr.get(), 1e-9);

    // Rotate CW
    drive.drivePolar(0.0, 0.0, 1.0);
    assertEquals(1.0, fl.get(), 1e-9);
    assertEquals(1.0, fr.get(), 1e-9);
    assertEquals(-1.0, rl.get(), 1e-9);
    assertEquals(-1.0, rr.get(), 1e-9);
  }
}
