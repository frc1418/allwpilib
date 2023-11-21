// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.util.function.Consumer;

/** NetworkTables BooleanArray publisher. */
public interface BooleanArrayPublisher extends Publisher, Consumer<boolean[]> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  BooleanArrayTopic getTopic();

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish
   */
  default void set(boolean[] value) {
    set(value, 0);
  }


  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   */
  void set(boolean[] value, long time);

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value
   */
  void setDefault(boolean[] value);

  @Override
  default void accept(boolean[] value) {
    set(value);
  }
}