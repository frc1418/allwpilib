// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.examples.flywheelbangbangcontroller;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a sample program to demonstrate the use of a BangBangController with a flywheel to
 * control RPM.
 */
public class Robot extends TimedRobot {
  private static final int kMotorPort = 0;
  private static final int kEncoderAChannel = 0;
  private static final int kEncoderBChannel = 1;

  // Max setpoint for joystick control in RPM
  private static final double kMaxSetpointValue = 6000.0;

  // Joystick to control setpoint
  private final Joystick m_joystick = new Joystick(0);

  private final PWMSparkMax m_flywheelMotor = new PWMSparkMax(kMotorPort);
  private final Encoder m_encoder = new Encoder(kEncoderAChannel, kEncoderBChannel);

  private final BangBangController m_bangBangControler = new BangBangController();

  // Gains are for example purposes only - must be determined for your own robot!
  public static final double kFlywheelKs = 0.0001; // V
  public static final double kFlywheelKv = 0.000195; // V/RPM
  public static final double kFlywheelKa = 0.0003; // V/(RPM/s)
  private final SimpleMotorFeedforward m_feedforward =
      new SimpleMotorFeedforward(kFlywheelKs, kFlywheelKv, kFlywheelKa);

  // Simulation classes help us simulate our robot

  // Reduction between motors and encoder, as output over input. If the flywheel
  // spins slower than the motors, this number should be greater than one.
  private static final double kFlywheelGearing = 1.0;

  // 1/2 MR²
  private static final double kFlywheelMomentOfInertia =
      0.5 * Units.lbsToKilograms(1.5) * Math.pow(Units.inchesToMeters(4), 2);

  private final FlywheelSim m_flywheelSim =
      new FlywheelSim(DCMotor.getNEO(1), kFlywheelGearing, kFlywheelMomentOfInertia);
  private final EncoderSim m_encoderSim = new EncoderSim(m_encoder);

  private final MutableMeasure<Velocity<Angle>> m_prevSpeedSetpoint =
      MutableMeasure.zero(RadiansPerSecond);
  private final MutableMeasure<Velocity<Angle>> m_speedSetpoint =
      MutableMeasure.zero(RadiansPerSecond);

  @Override
  public void robotInit() {
    // Add bang-bang controler to SmartDashboard and networktables.
    SmartDashboard.putData(m_bangBangControler);
  }

  /** Controls flywheel to a set speed (RPM) controlled by a joystick. */
  @Override
  public void teleopPeriodic() {
    // Scale setpoint value between 0 and maxSetpointValue
    double setpoint =
        Math.max(
            0.0,
            m_joystick.getRawAxis(0)
                * Units.rotationsPerMinuteToRadiansPerSecond(kMaxSetpointValue));

    // Set setpoint and measurement of the bang-bang controller
    double bangOutput = m_bangBangControler.calculate(m_encoder.getRate(), setpoint) * 12.0;

    m_prevSpeedSetpoint.mut_setMagnitude(m_encoder.getRate());
    m_speedSetpoint.mut_setMagnitude(setpoint);

    // Controls a motor with the output of the BangBang controller and a
    // feedforward. The feedforward is reduced slightly to avoid overspeeding
    // the shooter.
    m_flywheelMotor.setVoltage(
        bangOutput + 0.9 * m_feedforward.calculate(m_prevSpeedSetpoint, m_speedSetpoint));
  }

  /** Update our simulation. This should be run every robot loop in simulation. */
  @Override
  public void simulationPeriodic() {
    // To update our simulation, we set motor voltage inputs, update the
    // simulation, and write the simulated velocities to our simulated encoder
    m_flywheelSim.setInputVoltage(m_flywheelMotor.get() * RobotController.getInputVoltage());
    m_flywheelSim.update(0.02);
    m_encoderSim.setRate(m_flywheelSim.getAngularVelocityRadPerSec());
  }
}
