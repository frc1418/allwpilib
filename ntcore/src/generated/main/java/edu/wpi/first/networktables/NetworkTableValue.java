// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.util.Objects;

/** A network table entry value. */
@SuppressWarnings({"UnnecessaryParentheses", "PMD.MethodReturnsInternalArray"})
public final class NetworkTableValue {
  NetworkTableValue(NetworkTableType type, Object value, long time, long serverTime) {
    m_type = type;
    m_value = value;
    m_time = time;
    m_serverTime = serverTime;
  }

  NetworkTableValue(NetworkTableType type, Object value, long time) {
    this(type, value, time, time == 0 ? 0 : 1);
  }

  NetworkTableValue(NetworkTableType type, Object value) {
    this(type, value, NetworkTablesJNI.now(), 1);
  }

  NetworkTableValue(int type, Object value, long time, long serverTime) {
    this(NetworkTableType.getFromInt(type), value, time, serverTime);
  }

  /**
   * Get the data type.
   *
   * @return The type.
   */
  public NetworkTableType getType() {
    return m_type;
  }

  /**
   * Get the data value stored.
   *
   * @return The type.
   */
  public Object getValue() {
    return m_value;
  }

  /**
   * Get the creation time of the value in local time.
   *
   * @return The time, in the units returned by NetworkTablesJNI.now().
   */
  public long getTime() {
    return m_time;
  }

  /**
   * Get the creation time of the value in server time.
   *
   * @return The server time.
   */
  public long getServerTime() {
    return m_serverTime;
  }

  /*
   * Type Checkers
   */

  /**
   * Determine if entry value contains a value or is unassigned.
   *
   * @return True if the entry value contains a value.
   */
  public boolean isValid() {
    return m_type != NetworkTableType.kUnassigned;
  }

  /**
   * Determine if entry value contains a boolean.
   *
   * @return True if the entry value is of boolean type.
   */
  public boolean isBoolean() {
    return m_type == NetworkTableType.kBoolean;
  }

  /**
   * Determine if entry value contains a long.
   *
   * @return True if the entry value is of long type.
   */
  public boolean isInteger() {
    return m_type == NetworkTableType.kInteger;
  }

  /**
   * Determine if entry value contains a float.
   *
   * @return True if the entry value is of float type.
   */
  public boolean isFloat() {
    return m_type == NetworkTableType.kFloat;
  }

  /**
   * Determine if entry value contains a double.
   *
   * @return True if the entry value is of double type.
   */
  public boolean isDouble() {
    return m_type == NetworkTableType.kDouble;
  }

  /**
   * Determine if entry value contains a String.
   *
   * @return True if the entry value is of String type.
   */
  public boolean isString() {
    return m_type == NetworkTableType.kString;
  }

  /**
   * Determine if entry value contains a byte[].
   *
   * @return True if the entry value is of byte[] type.
   */
  public boolean isRaw() {
    return m_type == NetworkTableType.kRaw;
  }

  /**
   * Determine if entry value contains a boolean[].
   *
   * @return True if the entry value is of boolean[] type.
   */
  public boolean isBooleanArray() {
    return m_type == NetworkTableType.kBooleanArray;
  }

  /**
   * Determine if entry value contains a long[].
   *
   * @return True if the entry value is of long[] type.
   */
  public boolean isIntegerArray() {
    return m_type == NetworkTableType.kIntegerArray;
  }

  /**
   * Determine if entry value contains a float[].
   *
   * @return True if the entry value is of float[] type.
   */
  public boolean isFloatArray() {
    return m_type == NetworkTableType.kFloatArray;
  }

  /**
   * Determine if entry value contains a double[].
   *
   * @return True if the entry value is of double[] type.
   */
  public boolean isDoubleArray() {
    return m_type == NetworkTableType.kDoubleArray;
  }

  /**
   * Determine if entry value contains a String[].
   *
   * @return True if the entry value is of String[] type.
   */
  public boolean isStringArray() {
    return m_type == NetworkTableType.kStringArray;
  }

  /*
   * Type-Safe Getters
   */

  /**
   * Get the boolean value.
   *
   * @return The boolean value.
   * @throws ClassCastException if the entry value is not of boolean type.
   */
  public boolean getBoolean() {
    if (m_type != NetworkTableType.kBoolean) {
      throw new ClassCastException("cannot convert " + m_type + " to boolean");
    }
    return (Boolean) m_value;
  }

  /**
   * Get the long value.
   *
   * @return The long value.
   * @throws ClassCastException if the entry value is not of long type.
   */
  public long getInteger() {
    if (m_type != NetworkTableType.kInteger) {
      throw new ClassCastException("cannot convert " + m_type + " to long");
    }
    return ((Number) m_value).longValue();
  }

  /**
   * Get the float value.
   *
   * @return The float value.
   * @throws ClassCastException if the entry value is not of float type.
   */
  public float getFloat() {
    if (m_type != NetworkTableType.kFloat) {
      throw new ClassCastException("cannot convert " + m_type + " to float");
    }
    return ((Number) m_value).floatValue();
  }

  /**
   * Get the double value.
   *
   * @return The double value.
   * @throws ClassCastException if the entry value is not of double type.
   */
  public double getDouble() {
    if (m_type != NetworkTableType.kDouble) {
      throw new ClassCastException("cannot convert " + m_type + " to double");
    }
    return ((Number) m_value).doubleValue();
  }

  /**
   * Get the String value.
   *
   * @return The String value.
   * @throws ClassCastException if the entry value is not of String type.
   */
  public String getString() {
    if (m_type != NetworkTableType.kString) {
      throw new ClassCastException("cannot convert " + m_type + " to String");
    }
    return (String) m_value;
  }

  /**
   * Get the byte[] value.
   *
   * @return The byte[] value.
   * @throws ClassCastException if the entry value is not of byte[] type.
   */
  public byte[] getRaw() {
    if (m_type != NetworkTableType.kRaw) {
      throw new ClassCastException("cannot convert " + m_type + " to byte[]");
    }
    return (byte[]) m_value;
  }

  /**
   * Get the boolean[] value.
   *
   * @return The boolean[] value.
   * @throws ClassCastException if the entry value is not of boolean[] type.
   */
  public boolean[] getBooleanArray() {
    if (m_type != NetworkTableType.kBooleanArray) {
      throw new ClassCastException("cannot convert " + m_type + " to boolean[]");
    }
    return (boolean[]) m_value;
  }

  /**
   * Get the long[] value.
   *
   * @return The long[] value.
   * @throws ClassCastException if the entry value is not of long[] type.
   */
  public long[] getIntegerArray() {
    if (m_type != NetworkTableType.kIntegerArray) {
      throw new ClassCastException("cannot convert " + m_type + " to long[]");
    }
    return (long[]) m_value;
  }

  /**
   * Get the float[] value.
   *
   * @return The float[] value.
   * @throws ClassCastException if the entry value is not of float[] type.
   */
  public float[] getFloatArray() {
    if (m_type != NetworkTableType.kFloatArray) {
      throw new ClassCastException("cannot convert " + m_type + " to float[]");
    }
    return (float[]) m_value;
  }

  /**
   * Get the double[] value.
   *
   * @return The double[] value.
   * @throws ClassCastException if the entry value is not of double[] type.
   */
  public double[] getDoubleArray() {
    if (m_type != NetworkTableType.kDoubleArray) {
      throw new ClassCastException("cannot convert " + m_type + " to double[]");
    }
    return (double[]) m_value;
  }

  /**
   * Get the String[] value.
   *
   * @return The String[] value.
   * @throws ClassCastException if the entry value is not of String[] type.
   */
  public String[] getStringArray() {
    if (m_type != NetworkTableType.kStringArray) {
      throw new ClassCastException("cannot convert " + m_type + " to String[]");
    }
    return (String[]) m_value;
  }

  /*
   * Factory functions.
   */

  /**
   * Creates a boolean value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeBoolean(boolean value) {
    return new NetworkTableValue(NetworkTableType.kBoolean, Boolean.valueOf(value));
  }

  /**
   * Creates a boolean value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeBoolean(boolean value, long time) {
    return new NetworkTableValue(NetworkTableType.kBoolean, Boolean.valueOf(value), time);
  }

  /**
   * Creates a long value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeInteger(long value) {
    return new NetworkTableValue(NetworkTableType.kInteger, Long.valueOf(value));
  }

  /**
   * Creates a long value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeInteger(long value, long time) {
    return new NetworkTableValue(NetworkTableType.kInteger, Long.valueOf(value), time);
  }

  /**
   * Creates a float value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeFloat(float value) {
    return new NetworkTableValue(NetworkTableType.kFloat, Float.valueOf(value));
  }

  /**
   * Creates a float value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeFloat(float value, long time) {
    return new NetworkTableValue(NetworkTableType.kFloat, Float.valueOf(value), time);
  }

  /**
   * Creates a double value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeDouble(double value) {
    return new NetworkTableValue(NetworkTableType.kDouble, Double.valueOf(value));
  }

  /**
   * Creates a double value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeDouble(double value, long time) {
    return new NetworkTableValue(NetworkTableType.kDouble, Double.valueOf(value), time);
  }

  /**
   * Creates a String value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeString(String value) {
    return new NetworkTableValue(NetworkTableType.kString, (value));
  }

  /**
   * Creates a String value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeString(String value, long time) {
    return new NetworkTableValue(NetworkTableType.kString, (value), time);
  }

  /**
   * Creates a byte[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeRaw(byte[] value) {
    return new NetworkTableValue(NetworkTableType.kRaw, (value));
  }

  /**
   * Creates a byte[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeRaw(byte[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kRaw, (value), time);
  }

  /**
   * Creates a boolean[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeBooleanArray(boolean[] value) {
    return new NetworkTableValue(NetworkTableType.kBooleanArray, (value));
  }

  /**
   * Creates a boolean[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeBooleanArray(boolean[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kBooleanArray, (value), time);
  }

  /**
   * Creates a boolean[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeBooleanArray(Boolean[] value) {
    return new NetworkTableValue(NetworkTableType.kBooleanArray, toNativeBooleanArray(value));
  }

  /**
   * Creates a boolean[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeBooleanArray(Boolean[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kBooleanArray, toNativeBooleanArray(value), time);
  }

  /**
   * Creates a long[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeIntegerArray(long[] value) {
    return new NetworkTableValue(NetworkTableType.kIntegerArray, (value));
  }

  /**
   * Creates a long[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeIntegerArray(long[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kIntegerArray, (value), time);
  }

  /**
   * Creates a long[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeIntegerArray(Long[] value) {
    return new NetworkTableValue(NetworkTableType.kIntegerArray, toNativeIntegerArray(value));
  }

  /**
   * Creates a long[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeIntegerArray(Long[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kIntegerArray, toNativeIntegerArray(value), time);
  }

  /**
   * Creates a float[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeFloatArray(float[] value) {
    return new NetworkTableValue(NetworkTableType.kFloatArray, (value));
  }

  /**
   * Creates a float[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeFloatArray(float[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kFloatArray, (value), time);
  }

  /**
   * Creates a float[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeFloatArray(Float[] value) {
    return new NetworkTableValue(NetworkTableType.kFloatArray, toNativeFloatArray(value));
  }

  /**
   * Creates a float[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeFloatArray(Float[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kFloatArray, toNativeFloatArray(value), time);
  }

  /**
   * Creates a double[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeDoubleArray(double[] value) {
    return new NetworkTableValue(NetworkTableType.kDoubleArray, (value));
  }

  /**
   * Creates a double[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeDoubleArray(double[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kDoubleArray, (value), time);
  }

  /**
   * Creates a double[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeDoubleArray(Double[] value) {
    return new NetworkTableValue(NetworkTableType.kDoubleArray, toNativeDoubleArray(value));
  }

  /**
   * Creates a double[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeDoubleArray(Double[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kDoubleArray, toNativeDoubleArray(value), time);
  }

  /**
   * Creates a String[] value.
   *
   * @param value the value
   * @return The entry value
   */
  public static NetworkTableValue makeStringArray(String[] value) {
    return new NetworkTableValue(NetworkTableType.kStringArray, (value));
  }

  /**
   * Creates a String[] value.
   *
   * @param value the value
   * @param time the creation time to use (instead of the current time)
   * @return The entry value
   */
  public static NetworkTableValue makeStringArray(String[] value, long time) {
    return new NetworkTableValue(NetworkTableType.kStringArray, (value), time);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof NetworkTableValue)) {
      return false;
    }
    NetworkTableValue ntOther = (NetworkTableValue) other;
    return m_type == ntOther.m_type && m_value.equals(ntOther.m_value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(m_type, m_value);
  }

  // arraycopy() doesn't know how to unwrap boxed values; this is a false positive in PMD
  // (see https://sourceforge.net/p/pmd/bugs/804/)
  @SuppressWarnings("PMD.AvoidArrayLoops")
  static boolean[] toNativeBooleanArray(Boolean[] arr) {
    boolean[] out = new boolean[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i];
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static double[] toNativeDoubleArray(Number[] arr) {
    double[] out = new double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i].doubleValue();
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static long[] toNativeIntegerArray(Number[] arr) {
    long[] out = new long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i].longValue();
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static float[] toNativeFloatArray(Number[] arr) {
    float[] out = new float[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i].floatValue();
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static Boolean[] fromNativeBooleanArray(boolean[] arr) {
    Boolean[] out = new Boolean[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i];
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static Long[] fromNativeIntegerArray(long[] arr) {
    Long[] out = new Long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i];
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static Float[] fromNativeFloatArray(float[] arr) {
    Float[] out = new Float[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i];
    }
    return out;
  }

  @SuppressWarnings("PMD.AvoidArrayLoops")
  static Double[] fromNativeDoubleArray(double[] arr) {
    Double[] out = new Double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      out[i] = arr[i];
    }
    return out;
  }

  private NetworkTableType m_type;
  private Object m_value;
  private long m_time;
  private long m_serverTime;
}
