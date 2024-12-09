// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.examples.coroutines;

import edu.wpi.first.wpilibj.commandsv3.Command;
import edu.wpi.first.wpilibj.commandsv3.button.CommandXboxController;
import edu.wpi.first.wpilibj.commandsv3.button.Trigger;
import edu.wpi.first.wpilibj.examples.coroutines.Constants.AutoConstants;
import edu.wpi.first.wpilibj.examples.coroutines.Constants.OIConstants;
import edu.wpi.first.wpilibj.examples.coroutines.Constants.ShooterConstants;
import edu.wpi.first.wpilibj.examples.coroutines.subsystems.Drive;
import edu.wpi.first.wpilibj.examples.coroutines.subsystems.Intake;
import edu.wpi.first.wpilibj.examples.coroutines.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.examples.coroutines.subsystems.Shooter;
import edu.wpi.first.wpilibj.examples.coroutines.subsystems.Storage;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class AsyncCommandBot {
  // The robot's subsystems
  private final Drive m_drive = new Drive();
  private final Intake m_intake = new Intake();
  private final Storage m_storage = new Storage();
  private final Shooter m_shooter = new Shooter();
  private final Pneumatics m_pneumatics = new Pneumatics();

  // The driver's controller
  private final CommandXboxController m_driverController =
      new CommandXboxController(OIConstants.kDriverControllerPort);

  /**
   * Use this method to define bindings between conditions and commands. These are useful for
   * automating robot behaviors based on button and sensor input.
   *
   * <p>Should be called during {@link Robot#robotInit()}.
   *
   * <p>Event binding methods are available on the {@link Trigger} class.
   */
  public void configureBindings() {
    // Automatically run the storage motor whenever the ball storage is not full,
    // and turn it off whenever it fills. Uses subsystem-hosted trigger to
    // improve readability and make inter-subsystem communication easier.
    m_storage.hasCargo.whileFalse(m_storage.runCommand());

    // Automatically disable and retract the intake whenever the ball storage is full.
    m_storage.hasCargo.onTrue(m_intake.retractCommand());

    // Control the drive with split-stick arcade controls
    m_drive.setDefaultCommand(
        m_drive.arcadeDriveCommand(
            () -> -m_driverController.getLeftY(), () -> -m_driverController.getRightX()));

    // Deploy the intake with the X button
    m_driverController.x().onTrue(m_intake.intakeCommand());
    // Retract the intake with the Y button
    m_driverController.y().onTrue(m_intake.retractCommand());

    // Fire the shooter with the A button
    m_driverController.a().onTrue(loadAndShoot());

    // Toggle compressor with the Start button
    m_driverController.start()
        .onTrue(m_pneumatics.disableCompressor())
        .onFalse(m_pneumatics.enableCompressor());
  }

  private Command loadAndShoot() {
    return Command
               .requiring(m_shooter, m_storage)
               .executing((coroutine) -> {
                 while (coroutine.yield()) {
                   m_storage.run();
                   m_shooter.ramp(ShooterConstants.kShooterTarget);

                   if (m_shooter.atSetpoint()) {
                     m_shooter.feed();
                   } else {
                     m_shooter.stop();
                   }
                 }
               })
               .named("Shoot");
  }

  /**
   * Use this to define the command that runs during autonomous.
   *
   * <p>Scheduled during {@link Robot#autonomousInit()}.
   */
  public Command getAutonomousCommand() {
    // Drive forward for 2 meters at half speed with a 3 second timeout
    return m_drive
               .driveDistanceCommand(AutoConstants.kDriveDistance, AutoConstants.kDriveSpeed)
               .withTimeout(AutoConstants.kTimeout);
  }
}
