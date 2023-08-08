// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.examples.differentialdrivebot;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.examples.differentialdrivebot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.examples.differentialdrivebot.Constants.OIConstants;
import edu.wpi.first.wpilibj.examples.differentialdrivebot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
  private final XboxController m_controller = new XboxController(OIConstants.kDriverControllerPort);
  private final Drivetrain m_drive = new Drivetrain();

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(DriveConstants.kVelSlewRate);
  private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(DriveConstants.kRotSlewRate);

  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
    m_drive.updateOdometry();
  }

  @Override
  public void teleopPeriodic() {
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    final var xSpeed = -m_speedLimiter.calculate(m_controller.getLeftY()) * DriveConstants.kMaxSpeed;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot = -m_rotLimiter.calculate(m_controller.getRightX()) * DriveConstants.kMaxAngularSpeed;

    m_drive.drive(xSpeed, rot);
  }
}
