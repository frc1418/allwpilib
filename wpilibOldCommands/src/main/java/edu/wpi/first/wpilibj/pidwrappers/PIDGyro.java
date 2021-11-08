// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.pidwrappers;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDGyro extends AnalogGyro implements PIDSource {
  private PIDSourceType m_pidSource = PIDSourceType.kDisplacement;

  public PIDGyro(int channel) {
    super(channel);
  }

  public PIDGyro(AnalogInput channel) {
    super(channel);
  }

  public PIDGyro(int channel, int center, double offset) {
    super(channel, center, offset);
  }

  public PIDGyro(AnalogInput channel, int center, double offset) {
    super(channel, center, offset);
  }

  /**
   * Set which parameter of the gyro you are using as a process control variable. The Gyro class
   * supports the rate and displacement parameters
   *
   * @param pidSource An enum to select the parameter.
   */
  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    m_pidSource = pidSource;
  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return m_pidSource;
  }

  /**
   * Get the output of the gyro for use with PIDControllers. May be the angle or rate depending on
   * the set PIDSourceType
   *
   * @return the output according to the gyro
   */
  @Override
  public double pidGet() {
    switch (m_pidSource) {
      case kRate:
        return getRate();
      case kDisplacement:
        return getAngle();
      default:
        return 0.0;
    }
  }
}
