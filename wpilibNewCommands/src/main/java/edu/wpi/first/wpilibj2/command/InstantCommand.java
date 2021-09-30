// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * A Command that runs instantly; it will initialize, execute once, and end on the same iteration of
 * the scheduler. Users can either pass in a Runnable and a set of requirements, or else subclass
 * this command if desired. If you wish to execute a Runnable repeatedly, use {@link RunCommand}.
 */
public class InstantCommand extends CommandBase {
  private final Runnable m_toRun;

  /**
   * Creates a new InstantCommand that runs the given Runnable with the given requirements.
   *
   * @param toRun the Runnable to run
   * @param requirements the subsystems required by this command
   */
  public InstantCommand(Runnable toRun, Subsystem... requirements) {
    m_toRun = requireNonNullParam(toRun, "toRun", "InstantCommand");

    addRequirements(requirements);
  }

  /**
   * Creates a new InstantCommand with a Runnable that does nothing. Useful only as a no-arg
   * constructor to call implicitly from subclass constructors.
   */
  public InstantCommand() {
    m_toRun = () -> {};
  }

  @Override
  public final void initialize() {
    m_toRun.run();
  }

  @Override
  public final boolean isFinished() {
    return true;
  }

  @Override
  public PerpetualCommand perpetually() {
    DriverStation.reportWarning("Called InstantCommand.perpetually(). A perpetuated InstantCommand"
                                + " will do nothing. If you want to execute a Runnable repeatedly,"
                                + " use RunCommand.", false);
    return super.perpetually();
  }
}
