// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./ntcore/generate_topics.py. DO NOT MODIFY

package org.wpilib.networktables;

import java.util.function.Consumer;

/** NetworkTables StringArray publisher. */
public interface StringArrayPublisher extends Publisher, Consumer<String[]> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  StringArrayTopic getTopic();

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish
   */
  default void set(String[] value) {
    set(value, 0);
  }


  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   */
  void set(String[] value, long time);

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value
   */
  void setDefault(String[] value);

  @Override
  default void accept(String[] value) {
    set(value);
  }
}
