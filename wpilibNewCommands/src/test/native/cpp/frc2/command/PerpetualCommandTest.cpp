// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "CommandTestBase.h"
#include "frc2/command/InstantCommand.h"
#include "frc2/command/PerpetualCommand.h"

using namespace frc2;
class PerpetualCommandTest : public CommandTestBase {};

#ifdef __GNUC__
#pragma GCC diagnostic ignored "-Wdeprecated-declarations"
#elif defined(_WIN32)
#pragma warning(disable : 4996)
#endif

TEST_F(PerpetualCommandTest, PerpetualCommandSchedule) {
  CommandScheduler scheduler = GetScheduler();

  bool check = false;

  PerpetualCommand command{InstantCommand([&check] { check = true; }, {})};

  scheduler.Schedule(&command);
  scheduler.Run();
  EXPECT_TRUE(scheduler.IsScheduled(&command));
  EXPECT_TRUE(check);
}
