// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import java.util.function.DoubleSupplier;

/**
 * A command that does nothing but takes a specified amount of time to finish.
 *
 * <p>This class is provided by the NewCommands VendorDep
 */
public class WaitCommand extends Command {
  /** The timer used for waiting. */
  protected Timer m_timer = new Timer();

  private double m_duration;
  private final DoubleSupplier m_durationSupplier;

  /**
   * Creates a new WaitCommand. This command will do nothing, and end after the specified duration.
   *
   * @param seconds the time to wait, in seconds
   */
  public WaitCommand(double seconds) {
    this(() -> seconds);
    setName(getName() + ": " + seconds + " seconds");
  }

  /**
   * Creates a new WaitCommand. This command will do nothing, and end after the duration returned by
   * the provided supplier. The supplier will be called once each time the command is initialized.
   *
   * @param durationSupplier Function that provides the time to wait, in seconds.
   */
  public WaitCommand(DoubleSupplier durationSupplier) {
    m_durationSupplier = durationSupplier;
  }

  @Override
  public void initialize() {
    m_duration = m_durationSupplier.getAsDouble();
    m_timer.restart();
  }

  @Override
  public void end(boolean interrupted) {
    m_timer.stop();
    m_duration = 0;
  }

  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(m_duration);
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    builder.addDoubleProperty("duration", () -> m_duration, null);
  }
}
