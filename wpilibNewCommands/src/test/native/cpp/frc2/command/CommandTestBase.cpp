/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "CommandTestBase.h"

using namespace frc2;

CommandTestBase::CommandTestBase() {
  auto& scheduler = CommandScheduler::GetInstance();
  scheduler.CancelAll();
  scheduler.Enable();
  scheduler.ClearButtons();
}

CommandScheduler CommandTestBase::GetScheduler() { return CommandScheduler(); }

void CommandTestBase::SetUp() {
  frc::sim::DriverStationSim::SetEnabled(true);
  while (!frc::sim::DriverStationSim::GetEnabled()) {
    std::this_thread::sleep_for(std::chrono::milliseconds(1));
  }
}

void CommandTestBase::TearDown() {
  CommandScheduler::GetInstance().ClearButtons();
}

void CommandTestBase::SetDSEnabled(bool enabled) {
  frc::sim::DriverStationSim::SetEnabled(enabled);
  while (frc::sim::DriverStationSim::GetEnabled() != enabled) {
    std::this_thread::sleep_for(std::chrono::milliseconds(1));
  }
}
