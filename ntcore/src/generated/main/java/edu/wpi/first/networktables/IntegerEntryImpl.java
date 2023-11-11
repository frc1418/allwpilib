// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables Integer implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class IntegerEntryImpl extends EntryBase implements IntegerEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  IntegerEntryImpl(IntegerTopic topic, int handle, long defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public IntegerTopic getTopic() {
    return m_topic;
  }

  @Override
  public long get() {
    return NetworkTablesJNI.getInteger(m_handle, m_defaultValue);
  }

  @Override
  public long get(long defaultValue) {
    return NetworkTablesJNI.getInteger(m_handle, defaultValue);
  }

  @Override
  public TimestampedInteger getAtomic() {
    return NetworkTablesJNI.getAtomicInteger(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedInteger getAtomic(long defaultValue) {
    return NetworkTablesJNI.getAtomicInteger(m_handle, defaultValue);
  }

  @Override
  public TimestampedInteger[] readQueue() {
    return NetworkTablesJNI.readQueueInteger(m_handle);
  }

  @Override
  public long[] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesInteger(m_handle);
  }

  @Override
  public void set(long value, long time) {
    NetworkTablesJNI.setInteger(m_handle, time, value);
  }

  @Override
  public void setDefault(long value) {
    NetworkTablesJNI.setDefaultInteger(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final IntegerTopic m_topic;
  private final long m_defaultValue;
}
