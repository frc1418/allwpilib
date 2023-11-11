// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

/** NetworkTables String implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class StringEntryImpl extends EntryBase implements StringEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  StringEntryImpl(StringTopic topic, int handle, String defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public StringTopic getTopic() {
    return m_topic;
  }

  @Override
  public String get() {
    return NetworkTablesJNI.getString(m_handle, m_defaultValue);
  }

  @Override
  public String get(String defaultValue) {
    return NetworkTablesJNI.getString(m_handle, defaultValue);
  }

  @Override
  public TimestampedString getAtomic() {
    return NetworkTablesJNI.getAtomicString(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedString getAtomic(String defaultValue) {
    return NetworkTablesJNI.getAtomicString(m_handle, defaultValue);
  }

  @Override
  public TimestampedString[] readQueue() {
    return NetworkTablesJNI.readQueueString(m_handle);
  }

  @Override
  public String[] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesString(m_handle);
  }

  @Override
  public void set(String value, long time) {
    NetworkTablesJNI.setString(m_handle, time, value);
  }

  @Override
  public void setDefault(String value) {
    NetworkTablesJNI.setDefaultString(m_handle, 0, value);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final StringTopic m_topic;
  private final String m_defaultValue;
}
