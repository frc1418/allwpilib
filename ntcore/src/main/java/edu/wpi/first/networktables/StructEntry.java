// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.networktables;

/**
 * NetworkTables struct-encoded value entry.
 *
 * <p>Unlike NetworkTableEntry, the entry goes away when close() is called.
 *
 * @param <T> value class
 */
public interface StructEntry<T> extends StructSubscriber<T>, StructPublisher<T> {
  /** Stops publishing the entry if it's published. */
  void unpublish();
}
