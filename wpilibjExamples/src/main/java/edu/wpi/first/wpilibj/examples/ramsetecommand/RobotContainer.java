/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.examples.ramsetecommand;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.examples.ramsetecommand.subsystems.DriveSubsystem;

import static edu.wpi.first.wpilibj.XboxController.Button;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.AutoConstants.kMaxSpeedMetersPerSecond;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.AutoConstants.kRamseteB;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.AutoConstants.kRamseteZeta;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants.kDriveKinematics;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants.kPDriveVel;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants.kaVoltSecondsSquaredPerMeter;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants.ksVolts;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.DriveConstants.kvVoltSecondsPerMeter;
import static edu.wpi.first.wpilibj.examples.ramsetecommand.Constants.OIConstants.kDriverControllerPort;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(kDriverControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(() -> m_robotDrive
            .arcadeDrive(m_driverController.getY(GenericHID.Hand.kLeft),
                         m_driverController.getX(GenericHID.Hand.kRight)), m_robotDrive));

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    // Drive at half speed when the right bumper is held
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(.5))
        .whenReleased(() -> m_robotDrive.setMaxOutput(1));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, - 1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass the drive kinematics to ensure constraints are obeyed
        kDriveKinematics,
        // Start stationary
        0,
        // End stationary
        0,
        // Apply max speed constraint
        kMaxSpeedMetersPerSecond,
        // Apply max acceleration constraint
        kMaxAccelerationMetersPerSecondSquared,
        false
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_robotDrive::getPose,
        new RamseteController(kRamseteB, kRamseteZeta),
        ksVolts,
        kvVoltSecondsPerMeter,
        kaVoltSecondsSquaredPerMeter,
        kDriveKinematics,
        m_robotDrive.getLeftEncoder()::getRate,
        m_robotDrive.getRightEncoder()::getRate,
        new PIDController(kPDriveVel, 0, 0),
        new PIDController(kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback, so we have to rescale here
        (left, right) -> m_robotDrive.tankDrive(left / 12., right / 12.),
        m_robotDrive
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDrive(0, 0));
  }
}
