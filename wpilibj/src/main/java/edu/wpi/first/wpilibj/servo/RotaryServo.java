// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.servo;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.PWM;

/* Common base class for all rotary servos */
public abstract class RotaryServo implements AutoCloseable, Sendable {
  protected PWM m_pwm;

  protected double m_minServoAngleRadians;
  protected double m_maxServoAngleRadians;

  /**
   * Constructor.
   *
   * @param name Name to use for SendableRegistry.
   * @param channel The PWM channel to which the servo is attached. 0-9 are on-board, 10-19 are on
   *     the MXP port.
   * @param minServoAngleRadians Minimum servo angle in radians which can be commanded.
   * @param maxServoAngleRadians Maximum servo angle in radians which can be commanded.
   */
  protected RotaryServo(
      final String name,
      final int channel,
      final double minServoAngleRadians,
      final double maxServoAngleRadians) {
    m_pwm = new PWM(channel, false);
    m_pwm.setPeriodMultiplier(PWM.PeriodMultiplier.k4X);

    m_minServoAngleRadians = minServoAngleRadians;
    m_maxServoAngleRadians = maxServoAngleRadians;

    HAL.report(tResourceType.kResourceType_Servo, channel + 1);
    SendableRegistry.addLW(this, name, channel);
  }

  /**
   * Set the servo position.
   *
   * <p>Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
   *
   * @param value Position from 0.0 to 1.0.
   */
  public void set(double value) {
    m_pwm.setPosition(value);
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
    return m_pwm.getPosition();
  }

  /**
   * Set the servo angle.
   *
   * <p>Servo angles that are out of the supported range of the servo simply "saturate" in that
   * direction In other words, if the servo has a range of (X rads to Y rads) than angles of less
   * than X result in an angle of X being set and angles of more than Y rads result in an angle of Y
   * being set.
   *
   * @param angle The angle in radians to set the servo.
   */
  public void setAngleRadians(double angle) {
    if (angle < m_minServoAngleRadians) {
      angle = m_minServoAngleRadians;
    } else if (angle > m_maxServoAngleRadians) {
      angle = m_maxServoAngleRadians;
    }

    m_pwm.setPosition((angle - m_minServoAngleRadians) / getServoAngleRange());
  }

  /**
   * Get the servo angle.
   *
   * <p>This returns the commanded angle, not the angle that the servo is actually at, as the servo
   * does not report its own angle.
   *
   * @return The angle in radians to which the servo is set.
   */
  public double getAngleRadians() {
    return m_pwm.getPosition() * getServoAngleRange() + m_minServoAngleRadians;
  }

  private double getServoAngleRange() {
    return m_maxServoAngleRadians - m_minServoAngleRadians;
  }

  /** Free the resource associated with the PWM channel and set the value to 0. */
  @Override
  public void close() {
    SendableRegistry.remove(this);
    m_pwm.close();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Servo");
    builder.setActuator(true);
    builder.addDoubleProperty("Value", this::get, this::set);
  }
}
