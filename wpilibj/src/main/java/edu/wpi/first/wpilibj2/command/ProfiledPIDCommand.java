/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj2.command;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;

import static edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.State;
import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

/**
 * A command that controls an output with a {@link ProfiledPIDController}.  Runs forever by
 * default - to add
 * exit conditions and/or other behavior, subclass this class.  The controller calculation and
 * output are performed synchronously in the command's execute() method.
 */
public class ProfiledPIDCommand extends CommandBase {
  protected final ProfiledPIDController m_controller;
  protected DoubleSupplier m_measurement;
  protected Supplier<State> m_goal;
  protected BiConsumer<Double, State> m_useOutput;

  /**
   * Creates a new PIDCommand, which controls the given output with a ProfiledPIDController.
   * Goal velocity is specified.
   *
   * @param controller        the controller that controls the output.
   * @param measurementSource the measurement of the process variable
   * @param goalSource        the controller's goal
   * @param useOutput         the controller's output
   * @param requirements      the subsystems required by this command
   */
  public ProfiledPIDCommand(ProfiledPIDController controller, DoubleSupplier measurementSource,
                            Supplier<State> goalSource, BiConsumer<Double, State> useOutput,
                            Subsystem... requirements) {
    requireNonNullParam(controller, "controller", "SynchronousPIDCommand");
    requireNonNullParam(measurementSource, "measurementSource", "SynchronousPIDCommand");
    requireNonNullParam(goalSource, "goalSource", "SynchronousPIDCommand");
    requireNonNullParam(useOutput, "useOutput", "SynchronousPIDCommand");

    m_controller = controller;
    m_useOutput = useOutput;
    m_measurement = measurementSource;
    m_goal = goalSource;
    m_requirements.addAll(Set.of(requirements));
  }

  /**
   * Creates a new PIDCommand, which controls the given output with a ProfiledPIDController.
   * Goal velocity is implicitly zero.
   *
   * @param controller        the controller that controls the output.
   * @param measurementSource the measurement of the process variable
   * @param goalSource        the controller's goal
   * @param useOutput         the controller's output
   * @param requirements      the subsystems required by this command
   */
  public ProfiledPIDCommand(ProfiledPIDController controller, DoubleSupplier measurementSource,
                            DoubleSupplier goalSource, BiConsumer<Double, State> useOutput,
                            Subsystem... requirements) {
    requireNonNullParam(controller, "controller", "SynchronousPIDCommand");
    requireNonNullParam(measurementSource, "measurementSource", "SynchronousPIDCommand");
    requireNonNullParam(goalSource, "goalSource", "SynchronousPIDCommand");
    requireNonNullParam(useOutput, "useOutput", "SynchronousPIDCommand");

    m_controller = controller;
    m_useOutput = useOutput;
    m_measurement = measurementSource;
    m_goal = () -> new State(goalSource.getAsDouble(), 0);
    m_requirements.addAll(Set.of(requirements));
  }

  /**
   * Creates a new PIDCommand, which controls the given output with a ProfiledPIDController. Goal
   * velocity is specified.
   *
   * @param controller        the controller that controls the output.
   * @param measurementSource the measurement of the process variable
   * @param goal              the controller's goal
   * @param useOutput         the controller's output
   * @param requirements      the subsystems required by this command
   */
  public ProfiledPIDCommand(ProfiledPIDController controller, DoubleSupplier measurementSource,
                            State goal, BiConsumer<Double, State> useOutput,
                            Subsystem... requirements) {
    this(controller, measurementSource, () -> goal, useOutput, requirements);
  }

  /**
   * Creates a new PIDCommand, which controls the given output with a ProfiledPIDController. Goal
   * velocity is implicitly zero.
   *
   * @param controller        the controller that controls the output.
   * @param measurementSource the measurement of the process variable
   * @param goal              the controller's goal
   * @param useOutput         the controller's output
   * @param requirements      the subsystems required by this command
   */
  public ProfiledPIDCommand(ProfiledPIDController controller, DoubleSupplier measurementSource,
                            double goal, BiConsumer<Double, State> useOutput,
                            Subsystem... requirements) {
    this(controller, measurementSource, () -> goal, useOutput, requirements);
  }

  @Override
  public void initialize() {
    m_controller.reset();
  }

  @Override
  public void execute() {
    useOutput(m_controller.calculate(getMeasurement(), getGoal()), m_controller.getSetpoint());
  }

  @Override
  public void end(boolean interrupted) {
    useOutput(0, new State());
  }

  /**
   * Returns the ProfiledPIDController used by the command.
   *
   * @return The ProfiledPIDController
   */
  public ProfiledPIDController getController() {
    return m_controller;
  }

  /**
   * Wraps the goal supplier field so that changes to it are visible to the controller.
   *
   * @return The goal for the controller
   */
  private State getGoal() {
    return m_goal.get();
  }

  /**
   * Wraps the measurement supplier field so that changes to it are visible to the controller.
   *
   * @return The measurement of the process variable
   */
  private double getMeasurement() {
    return m_measurement.getAsDouble();
  }

  /**
   * Wraps the output consumer field so that changes to it are visible to the controller.
   *
   * @param output The output from the controller
   */
  private void useOutput(double output, State state) {
    m_useOutput.accept(output, state);
  }
}
