// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/**
 * NetworkTables BooleanArray entry.
 *
 * <p>Unlike NetworkTableEntry, the entry goes away when close() is called.
 */
public interface BooleanArrayEntry extends BooleanArraySubscriber, BooleanArrayPublisher {
  /** Stops publishing the entry if it's published. */
  void unpublish();
}