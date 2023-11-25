// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.nio.ByteBuffer;

/** NetworkTables generic implementation. */
final class GenericEntryImpl extends EntryBase implements GenericEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   */
  GenericEntryImpl(Topic topic, int handle) {
    super(handle);
    m_topic = topic;
  }

  @Override
  public Topic getTopic() {
    return m_topic;
  }

  @Override
  public NetworkTableValue get() {
    return NetworkTablesJNI.getValue(m_handle);
  }

  /**
   * Gets the entry's value as a boolean. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public boolean getBoolean(boolean defaultValue) {
    return NetworkTablesJNI.getBoolean(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a long. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public long getInteger(long defaultValue) {
    return NetworkTablesJNI.getInteger(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a float. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public float getFloat(float defaultValue) {
    return NetworkTablesJNI.getFloat(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a double. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public double getDouble(double defaultValue) {
    return NetworkTablesJNI.getDouble(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a String. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public String getString(String defaultValue) {
    return NetworkTablesJNI.getString(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a byte[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public byte[] getRaw(byte[] defaultValue) {
    return NetworkTablesJNI.getRaw(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a boolean[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public boolean[] getBooleanArray(boolean[] defaultValue) {
    return NetworkTablesJNI.getBooleanArray(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public Boolean[] getBooleanArray(Boolean[] defaultValue) {
    return NetworkTableValue.fromNativeBooleanArray(
        getBooleanArray(NetworkTableValue.toNativeBooleanArray(defaultValue)));
  }

  /**
   * Gets the entry's value as a long[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public long[] getIntegerArray(long[] defaultValue) {
    return NetworkTablesJNI.getIntegerArray(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public Long[] getIntegerArray(Long[] defaultValue) {
    return NetworkTableValue.fromNativeIntegerArray(
        getIntegerArray(NetworkTableValue.toNativeIntegerArray(defaultValue)));
  }

  /**
   * Gets the entry's value as a float[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public float[] getFloatArray(float[] defaultValue) {
    return NetworkTablesJNI.getFloatArray(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public Float[] getFloatArray(Float[] defaultValue) {
    return NetworkTableValue.fromNativeFloatArray(
        getFloatArray(NetworkTableValue.toNativeFloatArray(defaultValue)));
  }

  /**
   * Gets the entry's value as a double[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public double[] getDoubleArray(double[] defaultValue) {
    return NetworkTablesJNI.getDoubleArray(m_handle, defaultValue);
  }

  /**
   * Gets the entry's value as a boolean array. If the entry does not exist or is of different type,
   * it will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public Double[] getDoubleArray(Double[] defaultValue) {
    return NetworkTableValue.fromNativeDoubleArray(
        getDoubleArray(NetworkTableValue.toNativeDoubleArray(defaultValue)));
  }

  /**
   * Gets the entry's value as a String[]. If the entry does not exist or is of different type, it
   * will return the default value.
   *
   * @param defaultValue the value to be returned if no value is found
   * @return the entry's value or the given default value
   */
  @Override
  public String[] getStringArray(String[] defaultValue) {
    return NetworkTablesJNI.getStringArray(m_handle, defaultValue);
  }

  @Override
  public NetworkTableValue[] readQueue() {
    return NetworkTablesJNI.readQueueValue(m_handle);
  }

  @Override
  public boolean set(NetworkTableValue value) {
    long time = value.getTime();
    Object otherValue = value.getValue();
    switch (value.getType()) {
      case kBoolean:
        return NetworkTablesJNI.setBoolean(m_handle, time, (Boolean) otherValue);
      case kInteger:
        return NetworkTablesJNI.setInteger(
            m_handle, time, ((Number) otherValue).longValue());
      case kFloat:
        return NetworkTablesJNI.setFloat(
            m_handle, time, ((Number) otherValue).floatValue());
      case kDouble:
        return NetworkTablesJNI.setDouble(
            m_handle, time, ((Number) otherValue).doubleValue());
      case kString:
        return NetworkTablesJNI.setString(m_handle, time, (String) otherValue);
      case kRaw:
        return NetworkTablesJNI.setRaw(m_handle, time, (byte[]) otherValue);
      case kBooleanArray:
        return NetworkTablesJNI.setBooleanArray(m_handle, time, (boolean[]) otherValue);
      case kIntegerArray:
        return NetworkTablesJNI.setIntegerArray(m_handle, time, (long[]) otherValue);
      case kFloatArray:
        return NetworkTablesJNI.setFloatArray(m_handle, time, (float[]) otherValue);
      case kDoubleArray:
        return NetworkTablesJNI.setDoubleArray(m_handle, time, (double[]) otherValue);
      case kStringArray:
        return NetworkTablesJNI.setStringArray(m_handle, time, (String[]) otherValue);
      default:
        return true;
    }
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value that will be assigned
   * @return False if the table key already exists with a different type
   * @throws IllegalArgumentException if the value is not a known type
   */
  @Override
  public boolean setValue(Object value, long time) {
    if (value instanceof NetworkTableValue) {
      return set((NetworkTableValue) value);
    } else if (value instanceof Boolean) {
      return setBoolean((Boolean) value, time);
    } else if (value instanceof Long) {
      return setInteger((Long) value, time);
    } else if (value instanceof Float) {
      return setFloat((Float) value, time);
    } else if (value instanceof Number) {
      return setNumber((Number) value, time);
    } else if (value instanceof String) {
      return setString((String) value, time);
    } else if (value instanceof byte[]) {
      return setRaw((byte[]) value, time);
    } else if (value instanceof boolean[]) {
      return setBooleanArray((boolean[]) value, time);
    } else if (value instanceof long[]) {
      return setIntegerArray((long[]) value, time);
    } else if (value instanceof float[]) {
      return setFloatArray((float[]) value, time);
    } else if (value instanceof double[]) {
      return setDoubleArray((double[]) value, time);
    } else if (value instanceof Boolean[]) {
      return setBooleanArray((Boolean[]) value, time);
    } else if (value instanceof Long[]) {
      return setIntegerArray((Long[]) value, time);
    } else if (value instanceof Float[]) {
      return setFloatArray((Float[]) value, time);
    } else if (value instanceof Number[]) {
      return setNumberArray((Number[]) value, time);
    } else if (value instanceof String[]) {
      return setStringArray((String[]) value, time);
    } else {
      throw new IllegalArgumentException(
          "Value of type " + value.getClass().getName() + " cannot be put into a table");
    }
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setBoolean(boolean value, long time) {
    return NetworkTablesJNI.setBoolean(m_handle, time, value);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setInteger(long value, long time) {
    return NetworkTablesJNI.setInteger(m_handle, time, value);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setFloat(float value, long time) {
    return NetworkTablesJNI.setFloat(m_handle, time, value);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDouble(double value, long time) {
    return NetworkTablesJNI.setDouble(m_handle, time, value);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setString(String value, long time) {
    return NetworkTablesJNI.setString(m_handle, time, value);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setRaw(byte[] value, int start, int len, long time) {
    return NetworkTablesJNI.setRaw(m_handle, time, value, start, len);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setRaw(ByteBuffer value, int start, int len, long time) {
    return NetworkTablesJNI.setRaw(m_handle, time, value, start, len);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setBooleanArray(boolean[] value, long time) {
    return NetworkTablesJNI.setBooleanArray(m_handle, time, value);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setBooleanArray(Boolean[] value, long time) {
    return setBooleanArray(NetworkTableValue.toNativeBooleanArray(value), time);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setIntegerArray(long[] value, long time) {
    return NetworkTablesJNI.setIntegerArray(m_handle, time, value);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setIntegerArray(Long[] value, long time) {
    return setIntegerArray(NetworkTableValue.toNativeIntegerArray(value), time);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setFloatArray(float[] value, long time) {
    return NetworkTablesJNI.setFloatArray(m_handle, time, value);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setFloatArray(Float[] value, long time) {
    return setFloatArray(NetworkTableValue.toNativeFloatArray(value), time);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDoubleArray(double[] value, long time) {
    return NetworkTablesJNI.setDoubleArray(m_handle, time, value);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDoubleArray(Double[] value, long time) {
    return setDoubleArray(NetworkTableValue.toNativeDoubleArray(value), time);
  }


  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setStringArray(String[] value, long time) {
    return NetworkTablesJNI.setStringArray(m_handle, time, value);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  public boolean setNumber(Number value, long time) {
    return setDouble(value.doubleValue(), time);
  }

  /**
   * Sets the entry's value.
   *
   * @param value the value to set
   * @return False if the entry exists with a different type
   */
  public boolean setNumberArray(Number[] value, long time) {
    return setDoubleArray(NetworkTableValue.toNativeDoubleArray(value), time);
  }

  @Override
  public boolean setDefault(NetworkTableValue defaultValue) {
    long time = defaultValue.getTime();
    Object otherValue = defaultValue.getValue();
    switch (defaultValue.getType()) {
      case kBoolean:
        return NetworkTablesJNI.setDefaultBoolean(m_handle, time, (Boolean) otherValue);
      case kInteger:
        return NetworkTablesJNI.setDefaultInteger(
            m_handle, time, ((Number) otherValue).longValue());
      case kFloat:
        return NetworkTablesJNI.setDefaultFloat(
            m_handle, time, ((Number) otherValue).floatValue());
      case kDouble:
        return NetworkTablesJNI.setDefaultDouble(
            m_handle, time, ((Number) otherValue).doubleValue());
      case kString:
        return NetworkTablesJNI.setDefaultString(m_handle, time, (String) otherValue);
      case kRaw:
        return NetworkTablesJNI.setDefaultRaw(m_handle, time, (byte[]) otherValue);
      case kBooleanArray:
        return NetworkTablesJNI.setDefaultBooleanArray(m_handle, time, (boolean[]) otherValue);
      case kIntegerArray:
        return NetworkTablesJNI.setDefaultIntegerArray(m_handle, time, (long[]) otherValue);
      case kFloatArray:
        return NetworkTablesJNI.setDefaultFloatArray(m_handle, time, (float[]) otherValue);
      case kDoubleArray:
        return NetworkTablesJNI.setDefaultDoubleArray(m_handle, time, (double[]) otherValue);
      case kStringArray:
        return NetworkTablesJNI.setDefaultStringArray(m_handle, time, (String[]) otherValue);
      default:
        return true;
    }
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   * @throws IllegalArgumentException if the value is not a known type
   */
  @Override
  public boolean setDefaultValue(Object defaultValue) {
    if (defaultValue instanceof NetworkTableValue) {
      return setDefault((NetworkTableValue) defaultValue);
    } else if (defaultValue instanceof Boolean) {
      return setDefaultBoolean((Boolean) defaultValue);
    } else if (defaultValue instanceof Integer) {
      return setDefaultInteger((Integer) defaultValue);
    } else if (defaultValue instanceof Float) {
      return setDefaultFloat((Float) defaultValue);
    } else if (defaultValue instanceof Number) {
      return setDefaultNumber((Number) defaultValue);
    } else if (defaultValue instanceof String) {
      return setDefaultString((String) defaultValue);
    } else if (defaultValue instanceof byte[]) {
      return setDefaultRaw((byte[]) defaultValue);
    } else if (defaultValue instanceof boolean[]) {
      return setDefaultBooleanArray((boolean[]) defaultValue);
    } else if (defaultValue instanceof long[]) {
      return setDefaultIntegerArray((long[]) defaultValue);
    } else if (defaultValue instanceof float[]) {
      return setDefaultFloatArray((float[]) defaultValue);
    } else if (defaultValue instanceof double[]) {
      return setDefaultDoubleArray((double[]) defaultValue);
    } else if (defaultValue instanceof Boolean[]) {
      return setDefaultBooleanArray((Boolean[]) defaultValue);
    } else if (defaultValue instanceof Long[]) {
      return setDefaultIntegerArray((Long[]) defaultValue);
    } else if (defaultValue instanceof Float[]) {
      return setDefaultFloatArray((Float[]) defaultValue);
    } else if (defaultValue instanceof Number[]) {
      return setDefaultNumberArray((Number[]) defaultValue);
    } else if (defaultValue instanceof String[]) {
      return setDefaultStringArray((String[]) defaultValue);
    } else {
      throw new IllegalArgumentException(
          "Value of type " + defaultValue.getClass().getName() + " cannot be put into a table");
    }
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultBoolean(boolean defaultValue) {
    return NetworkTablesJNI.setDefaultBoolean(m_handle, 0, defaultValue);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultInteger(long defaultValue) {
    return NetworkTablesJNI.setDefaultInteger(m_handle, 0, defaultValue);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultFloat(float defaultValue) {
    return NetworkTablesJNI.setDefaultFloat(m_handle, 0, defaultValue);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultDouble(double defaultValue) {
    return NetworkTablesJNI.setDefaultDouble(m_handle, 0, defaultValue);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultString(String defaultValue) {
    return NetworkTablesJNI.setDefaultString(m_handle, 0, defaultValue);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultRaw(byte[] defaultValue, int start, int len) {
    return NetworkTablesJNI.setDefaultRaw(m_handle, 0, defaultValue, start, len);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultRaw(ByteBuffer defaultValue, int start, int len) {
    return NetworkTablesJNI.setDefaultRaw(m_handle, 0, defaultValue, start, len);
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultBooleanArray(boolean[] defaultValue) {
    return NetworkTablesJNI.setDefaultBooleanArray(m_handle, 0, defaultValue);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultBooleanArray(Boolean[] defaultValue) {
    return setDefaultBooleanArray(NetworkTableValue.toNativeBooleanArray(defaultValue));
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultIntegerArray(long[] defaultValue) {
    return NetworkTablesJNI.setDefaultIntegerArray(m_handle, 0, defaultValue);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultIntegerArray(Long[] defaultValue) {
    return setDefaultIntegerArray(NetworkTableValue.toNativeIntegerArray(defaultValue));
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultFloatArray(float[] defaultValue) {
    return NetworkTablesJNI.setDefaultFloatArray(m_handle, 0, defaultValue);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultFloatArray(Float[] defaultValue) {
    return setDefaultFloatArray(NetworkTableValue.toNativeFloatArray(defaultValue));
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultDoubleArray(double[] defaultValue) {
    return NetworkTablesJNI.setDefaultDoubleArray(m_handle, 0, defaultValue);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultDoubleArray(Double[] defaultValue) {
    return setDefaultDoubleArray(NetworkTableValue.toNativeDoubleArray(defaultValue));
  }


  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  @Override
  public boolean setDefaultStringArray(String[] defaultValue) {
    return NetworkTablesJNI.setDefaultStringArray(m_handle, 0, defaultValue);
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  public boolean setDefaultNumber(Number defaultValue) {
    return setDefaultDouble(defaultValue.doubleValue());
  }

  /**
   * Sets the entry's value if it does not exist.
   *
   * @param defaultValue the default value to set
   * @return False if the entry exists with a different type
   */
  public boolean setDefaultNumberArray(Number[] defaultValue) {
    return setDefaultDoubleArray(NetworkTableValue.toNativeDoubleArray(defaultValue));
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final Topic m_topic;
}
