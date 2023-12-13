// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.motorcontrol;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.PWM;

/**
 * Cross the Road Electronics (CTRE) Victor SPX Motor Controller with PWM control.
 *
 * <p>Note that the Victor SPX uses the following bounds for PWM values. These values should work
 * reasonably well for most controllers, but if users experience issues such as asymmetric behavior
 * around the deadband or inability to saturate the controller in either direction, calibration is
 * recommended. The calibration procedure can be found in the Victor SPX User Manual available from
 * CTRE.
 *
 * <ul>
 *   <li>2.004ms = full "forward"
 *   <li>1.520ms = the "high end" of the deadband range
 *   <li>1.500ms = center of the deadband range (off)
 *   <li>1.480ms = the "low end" of the deadband range
 *   <li>0.997ms = full "reverse"
 * </ul>
 */
public class PWMVictorSPX extends PWMMotorController {
  /**
   * Constructor for a Victor SPX connected via PWM.
   *
   * @param channel The PWM channel that the PWMVictorSPX is attached to. 0-9 are on-board, 10-19
   *     are on the MXP port
   */
  @SuppressWarnings("this-escape")
  public PWMVictorSPX(final int channel) {
    super("PWMVictorSPX", channel);

    m_pwm.setBoundsMicroseconds(2004, 1520, 1500, 1480, 997);
    m_pwm.setPeriodMultiplier(PWM.PeriodMultiplier.k1X);
    m_pwm.setSpeed(0.0);
    m_pwm.setZeroLatch();

    HAL.report(tResourceType.kResourceType_PWMVictorSPX, getChannel() + 1);
  }
}
