/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.examples.periodvariantflywheel.subsystems;

import edu.wpi.first.wpilibj.controller.PeriodVariantLoop;
import edu.wpi.first.wpiutil.math.Matrix;
import edu.wpi.first.wpiutil.math.MatrixUtils;
import edu.wpi.first.wpiutil.math.Nat;
import edu.wpi.first.wpiutil.math.numbers.N1;

@SuppressWarnings("MemberName")
public class FlywheelController {
  // Angular velocity tolerance in radians/sec.
  public static final double kTolerance = 10.0;

  // The current sensor measurement.
  private final Matrix<N1, N1> m_Y;
  // The control loop.
  private final PeriodVariantLoop<N1, N1, N1> m_loop = FlywheelCoeffs.makeFlywheelLoop();

  private boolean m_atReference;

  public FlywheelController() {
    m_Y = MatrixUtils.zeros(Nat.N1());
  }

  public void enable() {
    m_loop.enable();
  }

  public void disable() {
    m_loop.disable();
  }

  /**
   * Sets the velocity reference in radians/sec.
   */
  public void setVelocityReference(double angularVelocity) {
    Matrix<N1, N1> nextR = MatrixUtils.vec(Nat.N1()).fill(angularVelocity);
    m_loop.setNextR(nextR);
  }

  public boolean atReference() {
    return m_atReference;
  }

  /**
   * Sets the current encoder velocity in radians/sec.
   */
  public void setMeasuredVelocity(double angularVelocity) {
    m_Y.set(0, 0, angularVelocity);
  }

  public double getControllerVoltage() {
    return m_loop.getU(0);
  }

  public double getEstimatedVelocity() {
    return m_loop.getXhat(0);
  }

  public double getError() {
    return m_loop.getError().get(0, 0);
  }

  /**
   * Executes the control loop for a cycle.
   *
   * @param dt Measured time since the last controller update.
   */
  public void update(double dt) {
    if (Math.abs(m_loop.getNextR(0)) < 1.0) {
      m_loop.disable();
    }

    m_loop.correct(m_Y);

    m_atReference = Math.abs(getError()) < kTolerance && m_loop.getNextR(0) > 1.0;

    m_loop.predict(dt);
  }

  /**
   * Resets any internal state.
   */
  public void reset() {
    m_loop.reset();
  }
}
