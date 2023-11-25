// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables FloatArray implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class FloatArrayEntryImpl extends EntryBase implements FloatArrayEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  FloatArrayEntryImpl(FloatArrayTopic topic, int handle, float[] defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public FloatArrayTopic getTopic() {
    return m_topic;
  }

  @Override
  public float[] get() {
    return NetworkTablesJNI.getFloatArray(m_handle, m_defaultValue);
  }

  @Override
  public float[] get(float[] defaultValue) {
    return NetworkTablesJNI.getFloatArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedFloatArray getAtomic() {
    return NetworkTablesJNI.getAtomicFloatArray(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedFloatArray getAtomic(float[] defaultValue) {
    return NetworkTablesJNI.getAtomicFloatArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedFloatArray[] readQueue() {
    return NetworkTablesJNI.readQueueFloatArray(m_handle);
  }

  @Override
  public float[][] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesFloatArray(m_handle);
  }

  @Override
  public void set(float[] value, long time) {
    NetworkTablesJNI.setFloatArray(m_handle, time, value);
  }

  @Override
  public void setDefault(float[] value) {
    NetworkTablesJNI.setDefaultFloatArray(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final FloatArrayTopic m_topic;
  private final float[] m_defaultValue;
}
