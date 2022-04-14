// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.util.datalog;

/** Log double values. */
public class DoubleLogEntry extends DataLogEntry {
  public static final String kDataType = "double";

  public DoubleLogEntry(DataLog log, String name, String metadata, long timestamp) {
    super(log, name, kDataType, metadata, timestamp);
  }

  public DoubleLogEntry(DataLog log, String name, String metadata) {
    this(log, name, metadata, 0);
  }

  public DoubleLogEntry(DataLog log, String name, long timestamp) {
    this(log, name, "", timestamp);
  }

  public DoubleLogEntry(DataLog log, String name) {
    this(log, name, 0);
  }

  /**
   * Appends a record to the log.
   *
   * @param value Value to record
   * @param timestamp Time stamp (may be 0 to indicate now)
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
    append(value, 0);
  }
}
