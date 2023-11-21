// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables IntegerArray implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class IntegerArrayEntryImpl extends EntryBase implements IntegerArrayEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  IntegerArrayEntryImpl(IntegerArrayTopic topic, int handle, long[] defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public IntegerArrayTopic getTopic() {
    return m_topic;
  }

  @Override
  public long[] get() {
    return NetworkTablesJNI.getIntegerArray(m_handle, m_defaultValue);
  }

  @Override
  public long[] get(long[] defaultValue) {
    return NetworkTablesJNI.getIntegerArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedIntegerArray getAtomic() {
    return NetworkTablesJNI.getAtomicIntegerArray(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedIntegerArray getAtomic(long[] defaultValue) {
    return NetworkTablesJNI.getAtomicIntegerArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedIntegerArray[] readQueue() {
    return NetworkTablesJNI.readQueueIntegerArray(m_handle);
  }

  @Override
  public long[][] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesIntegerArray(m_handle);
  }

  @Override
  public void set(long[] value, long time) {
    NetworkTablesJNI.setIntegerArray(m_handle, time, value);
  }

  @Override
  public void setDefault(long[] value) {
    NetworkTablesJNI.setDefaultIntegerArray(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final IntegerArrayTopic m_topic;
  private final long[] m_defaultValue;
}