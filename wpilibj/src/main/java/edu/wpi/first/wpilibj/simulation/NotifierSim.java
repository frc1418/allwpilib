// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.simulation;

import org.wpilib.hal.simulation.NotifierDataJNI;

/** Class to control simulated notifiers. */
public final class NotifierSim {
  private NotifierSim() {}

  /**
   * Gets the timeout of the next notifier.
   *
   * @return Timestamp
   */
  public static long getNextTimeout() {
    return NotifierDataJNI.getNextTimeout();
  }

  /**
   * Gets the total number of notifiers.
   *
   * @return Count
   */
  public static int getNumNotifiers() {
    return NotifierDataJNI.getNumNotifiers();
  }
}
