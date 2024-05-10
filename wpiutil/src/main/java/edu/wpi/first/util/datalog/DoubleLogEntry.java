// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.util.datalog;

/** Log double values. */
public class DoubleLogEntry extends DataLogEntry {
  /** The data type for double values. */
  public static final String kDataType = "double";

  /**
   * Constructs a double log entry.
   *
   * @param log datalog
   * @param name name of the entry
   * @param metadata metadata
   * @param timestamp entry creation timestamp (0=now)
   */
  public DoubleLogEntry(DataLog log, String name, String metadata, long timestamp) {
    super(log, name, kDataType, metadata, timestamp);
  }

  /**
   * Constructs a double log entry.
   *
   * @param log datalog
   * @param name name of the entry
   * @param metadata metadata
   */
  public DoubleLogEntry(DataLog log, String name, String metadata) {
    this(log, name, metadata, 0);
  }

  /**
   * Constructs a double log entry.
   *
   * @param log datalog
   * @param name name of the entry
   * @param timestamp entry creation timestamp (0=now)
   */
  public DoubleLogEntry(DataLog log, String name, long timestamp) {
    this(log, name, "", timestamp);
  }

  /**
   * Constructs a double log entry.
   *
   * @param log datalog
   * @param name name of the entry
   */
  public DoubleLogEntry(DataLog log, String name) {
    this(log, name, 0);
  }

  /**
   * Appends a record to the log.
   *
   * @param value Value to record
   * @param timestamp Time stamp (0 to indicate now)
   */
  public void append(double value, long timestamp) {
    m_log.appendDouble(m_entry, value, timestamp);
  }

  /**
   * Appends a record to the log.
   *
   * @param value Value to record
   */
  public void append(double value) {
    m_log.appendDouble(m_entry, value, 0);
  }

  /**
   * Updates the last value and appends a record to the log if it has changed.
   *
   * @param value Value to record
   * @param timestamp Time stamp (0 to indicate now)
   */
  public synchronized void update(double value, long timestamp) {
    if (!m_hasLastValue || m_lastValue != value) {
      m_lastValue = value;
      m_hasLastValue = true;
      append(value, timestamp);
    }
  }

  /**
   * Updates the last value and appends a record to the log if it has changed.
   *
   * @param value Value to record
   */
  public void update(double value) {
    update(value, 0);
  }

  /**
   * Gets the last value.
   *
   * @return Last value, or 0 if none.
   */
  public synchronized double getLastValue() {
    return m_lastValue;
  }

  boolean m_hasLastValue;
  double m_lastValue;
}
