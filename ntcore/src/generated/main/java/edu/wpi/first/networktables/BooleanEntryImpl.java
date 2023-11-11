// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables Boolean implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class BooleanEntryImpl extends EntryBase implements BooleanEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  BooleanEntryImpl(BooleanTopic topic, int handle, boolean defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public BooleanTopic getTopic() {
    return m_topic;
  }

  @Override
  public boolean get() {
    return NetworkTablesJNI.getBoolean(m_handle, m_defaultValue);
  }

  @Override
  public boolean get(boolean defaultValue) {
    return NetworkTablesJNI.getBoolean(m_handle, defaultValue);
  }

  @Override
  public TimestampedBoolean getAtomic() {
    return NetworkTablesJNI.getAtomicBoolean(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedBoolean getAtomic(boolean defaultValue) {
    return NetworkTablesJNI.getAtomicBoolean(m_handle, defaultValue);
  }

  @Override
  public TimestampedBoolean[] readQueue() {
    return NetworkTablesJNI.readQueueBoolean(m_handle);
  }

  @Override
  public boolean[] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesBoolean(m_handle);
  }

  @Override
  public void set(boolean value, long time) {
    NetworkTablesJNI.setBoolean(m_handle, time, value);
  }

  @Override
  public void setDefault(boolean value) {
    NetworkTablesJNI.setDefaultBoolean(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final BooleanTopic m_topic;
  private final boolean m_defaultValue;
}
