// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables BooleanArray implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class BooleanArrayEntryImpl extends EntryBase implements BooleanArrayEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  BooleanArrayEntryImpl(BooleanArrayTopic topic, int handle, boolean[] defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public BooleanArrayTopic getTopic() {
    return m_topic;
  }

  @Override
  public boolean[] get() {
    return NetworkTablesJNI.getBooleanArray(m_handle, m_defaultValue);
  }

  @Override
  public boolean[] get(boolean[] defaultValue) {
    return NetworkTablesJNI.getBooleanArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedBooleanArray getAtomic() {
    return NetworkTablesJNI.getAtomicBooleanArray(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedBooleanArray getAtomic(boolean[] defaultValue) {
    return NetworkTablesJNI.getAtomicBooleanArray(m_handle, defaultValue);
  }

  @Override
  public TimestampedBooleanArray[] readQueue() {
    return NetworkTablesJNI.readQueueBooleanArray(m_handle);
  }

  @Override
  public boolean[][] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesBooleanArray(m_handle);
  }

  @Override
  public void set(boolean[] value, long time) {
    NetworkTablesJNI.setBooleanArray(m_handle, time, value);
  }

  @Override
  public void setDefault(boolean[] value) {
    NetworkTablesJNI.setDefaultBooleanArray(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final BooleanArrayTopic m_topic;
  private final boolean[] m_defaultValue;
}