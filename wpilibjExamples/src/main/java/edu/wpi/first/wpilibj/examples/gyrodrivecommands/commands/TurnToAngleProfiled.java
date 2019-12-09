/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.examples.gyrodrivecommands.commands;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;

import edu.wpi.first.wpilibj.examples.gyrodrivecommands.subsystems.DriveSubsystem;

import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kMaxTurnAccelerationDegPerSSquared;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kMaxTurnRateDegPerS;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kTurnD;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kTurnI;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kTurnP;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kTurnRateToleranceDegPerS;
import static edu.wpi.first.wpilibj.examples.gyrodrivecommands.Constants.DriveConstants.kTurnToleranceDeg;

/**
 * A command that will turn the robot to the specified angle using a motion profile.
 */
public class TurnToAngleProfiled extends ProfiledPIDCommand {
  /**
   * Turns to robot to the specified angle using a motion profile.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   */
  public TurnToAngleProfiled(double targetAngleDegrees, DriveSubsystem drive) {
    super(
        new ProfiledPIDController(kTurnP, kTurnI, kTurnD,
                                  new TrapezoidProfile.Constraints(
                                      kMaxTurnRateDegPerS,
                                      kMaxTurnAccelerationDegPerSSquared
                                  )),
        // Close loop on heading
        drive::getHeading,
        // Set reference to target
        targetAngleDegrees,
        // Pipe output to turn robot
        (output, setpoint) -> drive.arcadeDrive(0, output),
        // Require the drive
        drive);

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(kTurnToleranceDeg, kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atGoal();
  }
}
