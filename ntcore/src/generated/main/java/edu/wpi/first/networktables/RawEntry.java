// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./ntcore/generate_topics.py. DO NOT MODIFY

package edu.wpi.first.networktables;

/**
 * NetworkTables Raw entry.
 *
 * <p>Unlike NetworkTableEntry, the entry goes away when close() is called.
 */
public interface RawEntry extends RawSubscriber, RawPublisher {
  /** Stops publishing the entry if it's published. */
  void unpublish();
}
