// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.util.function.Supplier;

/** NetworkTables generic subscriber. */
@SuppressWarnings("PMD.MissingOverride")
public interface GenericSubscriber extends Subscriber, Supplier<NetworkTableValue> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  Topic getTopic();

  /**
   * Get the last published value.
   * If no value has been published, returns a value with type NetworkTableType.kUnassigned.
   *
   * @return value
   */
  NetworkTableValue get();

  /**
   * Gets the entry's value as a boolean. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  boolean getBoolean(boolean defaultValue);

  /**
   * Gets the entry's value as a long. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  long getInteger(long defaultValue);

  /**
   * Gets the entry's value as a float. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  float getFloat(float defaultValue);

  /**
   * Gets the entry's value as a double. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  double getDouble(double defaultValue);

  /**
   * Gets the entry's value as a String. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  String getString(String defaultValue);

  /**
   * Gets the entry's value as a byte[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  byte[] getRaw(byte[] defaultValue);

  /**
   * Gets the entry's value as a boolean[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  boolean[] getBooleanArray(boolean[] defaultValue);

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  Boolean[] getBooleanArray(Boolean[] defaultValue);

  /**
   * Gets the entry's value as a long[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  long[] getIntegerArray(long[] defaultValue);

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  Long[] getIntegerArray(Long[] defaultValue);

  /**
   * Gets the entry's value as a float[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  float[] getFloatArray(float[] defaultValue);

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  Float[] getFloatArray(Float[] defaultValue);

  /**
   * Gets the entry's value as a double[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  double[] getDoubleArray(double[] defaultValue);

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  Double[] getDoubleArray(Double[] defaultValue);

  /**
   * Gets the entry's value as a String[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  String[] getStringArray(String[] defaultValue);

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
  NetworkTableValue[] readQueue();
}