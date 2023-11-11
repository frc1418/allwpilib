// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

/** NetworkTables generic publisher. */
public interface GenericPublisher extends Publisher, Consumer<NetworkTableValue> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  Topic getTopic();

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  boolean set(NetworkTableValue value);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   * @throws IllegalArgumentException if the value is not a known type
   */
  default boolean setValue(Object value) {
    return setValue(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   * @throws IllegalArgumentException if the value is not a known type
   */
  boolean setValue(Object value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setBoolean(boolean value) {
    return setBoolean(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setBoolean(boolean value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setInteger(long value) {
    return setInteger(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setInteger(long value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setFloat(float value) {
    return setFloat(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setFloat(float value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setDouble(double value) {
    return setDouble(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setDouble(double value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setString(String value) {
    return setString(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setString(String value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(byte[] value) {
    return setRaw(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(ByteBuffer value) {
    return setRaw(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(byte[] value, long time) {
    return setRaw(value, 0, value.length, time);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish; will send from value.position() to value.limit()
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(ByteBuffer value, long time) {
    int pos = value.position();
    return setRaw(value, pos, value.limit() - pos, time);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(byte[] value, int start, int len) {
    return setRaw(value, start, len, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setRaw(byte[] value, int start, int len, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @return False if the topic already exists with a different type
   */
  default boolean setRaw(ByteBuffer value, int start, int len) {
    return setRaw(value, start, len, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setRaw(ByteBuffer value, int start, int len, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setBooleanArray(boolean[] value) {
    return setBooleanArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setBooleanArray(boolean[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setBooleanArray(Boolean[] value) {
    return setBooleanArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setBooleanArray(Boolean[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setIntegerArray(long[] value) {
    return setIntegerArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setIntegerArray(long[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setIntegerArray(Long[] value) {
    return setIntegerArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setIntegerArray(Long[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setFloatArray(float[] value) {
    return setFloatArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setFloatArray(float[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setFloatArray(Float[] value) {
    return setFloatArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setFloatArray(Float[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setDoubleArray(double[] value) {
    return setDoubleArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setDoubleArray(double[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setDoubleArray(Double[] value) {
    return setDoubleArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setDoubleArray(Double[] value, long time);

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @return False if the topic already exists with a different type
   */
  default boolean setStringArray(String[] value) {
    return setStringArray(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   * @return False if the topic already exists with a different type
   */
  boolean setStringArray(String[] value, long time);

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefault(NetworkTableValue defaultValue);

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   * @throws IllegalArgumentException if the value is not a known type
   */
  boolean setDefaultValue(Object defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultBoolean(boolean defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultInteger(long defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultFloat(float defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultDouble(double defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultString(String defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  default boolean setDefaultRaw(byte[] defaultValue) {
    return setDefaultRaw(defaultValue, 0, defaultValue.length);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set; will send from defaultValue.position() to
   *                     defaultValue.limit()
   * @return False if the entry exists with a different type
   */
  default boolean setDefaultRaw(ByteBuffer defaultValue) {
    int pos = defaultValue.position();
    return setDefaultRaw(defaultValue, pos, defaultValue.limit() - pos);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @return False if the entry exists with a different type
   */
  boolean setDefaultRaw(byte[] defaultValue, int start, int len);

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @return False if the entry exists with a different type
   */
  boolean setDefaultRaw(ByteBuffer defaultValue, int start, int len);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultBooleanArray(boolean[] defaultValue);

  boolean setDefaultBooleanArray(Boolean[] defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultIntegerArray(long[] defaultValue);

  boolean setDefaultIntegerArray(Long[] defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultFloatArray(float[] defaultValue);

  boolean setDefaultFloatArray(Float[] defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultDoubleArray(double[] defaultValue);

  boolean setDefaultDoubleArray(Double[] defaultValue);


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  boolean setDefaultStringArray(String[] defaultValue);

  @Override
  default void accept(NetworkTableValue value) {
    set(value);
  }
}
