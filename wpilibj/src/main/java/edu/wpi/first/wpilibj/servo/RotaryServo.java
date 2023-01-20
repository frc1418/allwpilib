// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.servo;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.PWM;

/* Common base class for all rotary servos */
public abstract class RotaryServo extends PWM {
  protected double m_minServoAngle;
  protected double m_maxServoAngle;

  /**
   * Constructor.<br>
   *
   * @param channel The PWM channel to which the servo is attached. 0-9 are on-board, 10-19 are on
   *     the MXP port
   */
  protected RotaryServo(
      final String name,
      final int channel,
      final double minServoAngle,
      final double maxServoAngle) {
    super(channel);
    setPeriodMultiplier(PeriodMultiplier.k4X);

    m_minServoAngle = minServoAngle;
    m_maxServoAngle = maxServoAngle;

    HAL.report(tResourceType.kResourceType_Servo, getChannel() + 1);
    SendableRegistry.setName(this, name, getChannel());
  }

  /**
   * Set the servo position.
   *
   * <p>Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
   *
   * @param value Position from 0.0 to 1.0.
   */
  public void set(double value) {
    setPosition(value);
  }

  /**
   * Get the servo position.
   *
   * <p>Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
   * This returns the commanded position, not the position that the servo is actually at, as the
   * servo does not report its own position.
   *
   * @return Position from 0.0 to 1.0.
   */
  public double get() {
    return getPosition();
  }

  /**
   * Set the servo angle.
   *
   * <p>Servo angles that are out of the supported range of the servo simply "saturate" in that
   * direction In other words, if the servo has a range of (X degrees to Y degrees) than angles of
   * less than X result in an angle of X being set and angles of more than Y degrees result in an
   * angle of Y being set.
   *
   * @param degrees The angle in degrees to set the servo.
   */
  public void setAngle(double degrees) {
    if (degrees < m_minServoAngle) {
      degrees = m_minServoAngle;
    } else if (degrees > m_maxServoAngle) {
      degrees = m_maxServoAngle;
    }

    setPosition((degrees - m_minServoAngle) / getServoAngleRange());
  }

  /**
   * Get the servo angle.
   *
   * <p>This returns the commanded angle, not the angle that the servo is actually at, as the servo
   * does not report its own angle.
   *
   * @return The angle in degrees to which the servo is set.
   */
  public double getAngle() {
    return getPosition() * getServoAngleRange() + m_minServoAngle;
  }

  private double getServoAngleRange() {
    return m_maxServoAngle - m_minServoAngle;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Servo");
    builder.addDoubleProperty("Value", this::get, this::set);
  }
}
