// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include "DataLogExport.h"

#include <memory>
#include <string>
#include <string_view>
#include <vector>

#include <wpi/DataLogReader.h>
#include <wpi/Signal.h>

namespace glass {
class Storage;
}  // namespace glass

namespace wpi {
class DataLogReaderEntry;
class DataLogReaderThread;
class Logger;
}  // namespace wpi

namespace sawmill {
/**
 * Helps with loading datalog files.
 */
class LogLoader {
 public:
  /**
   * Creates a log loader
   */
  explicit LogLoader();

  ~LogLoader();

  /**
   * Signal called when the current file is unloaded (invalidates any
   * LogEntry*).
   */
  wpi::sig::Signal<> unload;

  void Load(fs::path logPath);

  std::vector<sawmill::DataLogRecord> GetAllRecords();

 private:
  std::string m_filename;
  std::unique_ptr<wpi::DataLogReaderThread> m_reader;

  std::string m_error;

  std::string m_filter;

  wpi::log::StartRecordData* entryData;

  std::vector<sawmill::DataLogRecord> records;
};
}  // namespace sawmill
