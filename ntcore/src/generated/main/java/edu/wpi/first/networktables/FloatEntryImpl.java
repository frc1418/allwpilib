// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables Float implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class FloatEntryImpl extends EntryBase implements FloatEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  FloatEntryImpl(FloatTopic topic, int handle, float defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public FloatTopic getTopic() {
    return m_topic;
  }

  @Override
  public float get() {
    return NetworkTablesJNI.getFloat(m_handle, m_defaultValue);
  }

  @Override
  public float get(float defaultValue) {
    return NetworkTablesJNI.getFloat(m_handle, defaultValue);
  }

  @Override
  public TimestampedFloat getAtomic() {
    return NetworkTablesJNI.getAtomicFloat(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedFloat getAtomic(float defaultValue) {
    return NetworkTablesJNI.getAtomicFloat(m_handle, defaultValue);
  }

  @Override
  public TimestampedFloat[] readQueue() {
    return NetworkTablesJNI.readQueueFloat(m_handle);
  }

  @Override
  public float[] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesFloat(m_handle);
  }

  @Override
  public void set(float value, long time) {
    NetworkTablesJNI.setFloat(m_handle, time, value);
  }

  @Override
  public void setDefault(float value) {
    NetworkTablesJNI.setDefaultFloat(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final FloatTopic m_topic;
  private final float m_defaultValue;
}
