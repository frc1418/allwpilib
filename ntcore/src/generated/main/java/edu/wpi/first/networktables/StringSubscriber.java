// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.util.function.Supplier;

/** NetworkTables String subscriber. */
@SuppressWarnings("PMD.MissingOverride")
public interface StringSubscriber extends Subscriber, Supplier<String> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  StringTopic getTopic();

  /**
   * Get the last published value.
   * If no value has been published, returns the stored default value.
   *
   * @return value
   */
  String get();

  /**
   * Get the last published value.
   * If no value has been published, returns the passed defaultValue.
   *
   * @param defaultValue default value to return if no value has been published
   * @return value
   */
  String get(String defaultValue);

  /**
   * Get the last published value along with its timestamp
   * If no value has been published, returns the stored default value and a
   * timestamp of 0.
   *
   * @return timestamped value
   */
  TimestampedString getAtomic();

  /**
   * Get the last published value along with its timestamp
   * If no value has been published, returns the passed defaultValue and a
   * timestamp of 0.
   *
   * @param defaultValue default value to return if no value has been published
   * @return timestamped value
   */
  TimestampedString getAtomic(String defaultValue);

  /**
   * Get an array of all value changes since the last call to readQueue.
   * Also provides a timestamp for each value.
   *
   * <p>The "poll storage" subscribe option can be used to set the queue
   * depth.
   *
   * @return Array of timestamped values; empty array if no new changes have
   *     been published since the previous call.
   */
  TimestampedString[] readQueue();

  /**
   * Get an array of all value changes since the last call to readQueue.
   *
   * <p>The "poll storage" subscribe option can be used to set the queue
   * depth.
   *
   * @return Array of values; empty array if no new changes have been
   *     published since the previous call.
   */
  String[] readQueueValues();
}