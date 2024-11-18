// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.examples.elevatorprofiledpid;

import static org.wpilib.units.Units.MetersPerSecond;
import static org.wpilib.units.Units.Volts;

import org.wpilib.math.controller.ElevatorFeedforward;
import org.wpilib.math.controller.ProfiledPIDController;
import org.wpilib.math.trajectory.TrapezoidProfile;
import org.wpilib.wpilibj.Encoder;
import org.wpilib.wpilibj.Joystick;
import org.wpilib.wpilibj.TimedRobot;
import org.wpilib.wpilibj.motorcontrol.PWMSparkMax;

@SuppressWarnings("PMD.RedundantFieldInitializer")
public class Robot extends TimedRobot {
  private static double kDt = 0.02;
  private static double kMaxVelocity = 1.75;
  private static double kMaxAcceleration = 0.75;
  private static double kP = 1.3;
  private static double kI = 0.0;
  private static double kD = 0.7;
  private static double kS = 1.1;
  private static double kG = 1.2;
  private static double kV = 1.3;

  private final Joystick m_joystick = new Joystick(1);
  private final Encoder m_encoder = new Encoder(1, 2);
  private final PWMSparkMax m_motor = new PWMSparkMax(1);

  // Create a PID controller whose setpoint's change is subject to maximum
  // velocity and acceleration constraints.
  private final TrapezoidProfile.Constraints m_constraints =
      new TrapezoidProfile.Constraints(kMaxVelocity, kMaxAcceleration);
  private final ProfiledPIDController m_controller =
      new ProfiledPIDController(kP, kI, kD, m_constraints, kDt);
  private final ElevatorFeedforward m_feedforward = new ElevatorFeedforward(kS, kG, kV);

  public Robot() {
    m_encoder.setDistancePerPulse(1.0 / 360.0 * 2.0 * Math.PI * 1.5);
  }

  @Override
  public void teleopPeriodic() {
    if (m_joystick.getRawButtonPressed(2)) {
      m_controller.setGoal(5);
    } else if (m_joystick.getRawButtonPressed(3)) {
      m_controller.setGoal(0);
    }

    // Run controller and update motor output
    m_motor.setVoltage(
        m_controller.calculate(m_encoder.getDistance())
            + m_feedforward
                .calculate(MetersPerSecond.of(m_controller.getSetpoint().velocity))
                .in(Volts));
  }
}
