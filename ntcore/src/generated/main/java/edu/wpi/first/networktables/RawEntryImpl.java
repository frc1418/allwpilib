// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import java.nio.ByteBuffer;

/** NetworkTables Raw implementation. */
@SuppressWarnings("PMD.ArrayIsStoredDirectly")
final class RawEntryImpl extends EntryBase implements RawEntry {
  /**
   * Constructor.
   *
   * @param topic Topic
   * @param handle Native handle
   * @param defaultValue Default value for get()
   */
  RawEntryImpl(RawTopic topic, int handle, byte[] defaultValue) {
    super(handle);
    m_topic = topic;
    m_defaultValue = defaultValue;
  }

  @Override
  public RawTopic getTopic() {
    return m_topic;
  }

  @Override
  public byte[] get() {
    return NetworkTablesJNI.getRaw(m_handle, m_defaultValue);
  }

  @Override
  public byte[] get(byte[] defaultValue) {
    return NetworkTablesJNI.getRaw(m_handle, defaultValue);
  }

  @Override
  public TimestampedRaw getAtomic() {
    return NetworkTablesJNI.getAtomicRaw(m_handle, m_defaultValue);
  }

  @Override
  public TimestampedRaw getAtomic(byte[] defaultValue) {
    return NetworkTablesJNI.getAtomicRaw(m_handle, defaultValue);
  }

  @Override
  public TimestampedRaw[] readQueue() {
    return NetworkTablesJNI.readQueueRaw(m_handle);
  }

  @Override
  public byte[][] readQueueValues() {
    return NetworkTablesJNI.readQueueValuesRaw(m_handle);
  }

  @Override
  public void set(byte[] value, int start, int len, long time) {
    NetworkTablesJNI.setRaw(m_handle, time, value, start, len);
  }

  @Override
  public void set(ByteBuffer value, int start, int len, long time) {
    NetworkTablesJNI.setRaw(m_handle, time, value, start, len);
  }

  @Override
  public void setDefault(byte[] value, int start, int len) {
    NetworkTablesJNI.setDefaultRaw(m_handle, 0, value, start, len);
  }

  @Override
  public void setDefault(ByteBuffer value, int start, int len) {
    NetworkTablesJNI.setDefaultRaw(m_handle, 0, value, start, len);
  }

  @Override
  public void unpublish() {
    NetworkTablesJNI.unpublish(m_handle);
  }

  private final RawTopic m_topic;
  private final byte[] m_defaultValue;
}
