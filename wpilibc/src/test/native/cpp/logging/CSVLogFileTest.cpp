/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "frc/logging/CSVLogFile.h"  // NOLINT(build/include_order)

#include <fstream>
#include <string>

#include "gtest/gtest.h"

TEST(CSVLogFileTest, WriteLog) {
  std::string filename;

  {
    frc::CSVLogFile logFile("csvlogfile", "First column", 2);

    logFile.Log(1, "Second column");

    filename = logFile.GetFileName();
  }

  std::ifstream testFile(filename);

  std::string line;
  std::getline(testFile, line);
  EXPECT_EQ("\"Timestamp (ms)\",\"First column\",2", line);

  std::getline(testFile, line);
  std::size_t pos =
      line.find_first_of(',');  // find location of timestamp's end
  line.erase(0, pos);           // delete everything prior to location found
  EXPECT_EQ(",1,\"Second column\"", line);

  std::getline(testFile, line);
  EXPECT_EQ("", line);
}