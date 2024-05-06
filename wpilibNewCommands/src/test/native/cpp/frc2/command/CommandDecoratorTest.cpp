// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <string>
#include <utility>

#include <frc/simulation/SimHooks.h>

#include "CommandTestBase.h"
#include "frc2/command/FunctionalCommand.h"
#include "frc2/command/InstantCommand.h"
#include "frc2/command/RunCommand.h"
#include "frc2/command/WaitUntilCommand.h"

using namespace frc2;
class CommandDecoratorTest : public CommandTestBase {};

TEST_F(CommandDecoratorTest, WithTimeout) {
  CommandScheduler scheduler = GetScheduler();

  frc::sim::PauseTiming();

  auto command = RunCommand([] {}, {}).WithTimeout(100_ms);

  scheduler.Schedule(command);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(command));

  frc::sim::StepTiming(150_ms);

  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(command));

  frc::sim::ResumeTiming();
}

TEST_F(CommandDecoratorTest, Until) {
  CommandScheduler scheduler = GetScheduler();

  bool finish = false;

  auto command = RunCommand([] {}, {}).Until([&finish] { return finish; });

  scheduler.Schedule(command);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(command));

  finish = true;
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, UntilOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool firstHasRun = false;
  bool firstWasPolled = false;

  auto first = FunctionalCommand([] {}, [&firstHasRun] { firstHasRun = true; },
                                 [](bool interrupted) {},
                                 [&firstWasPolled] {
                                   firstWasPolled = true;
                                   return true;
                                 });
  auto command = std::move(first).Until([&firstHasRun, &firstWasPolled] {
    EXPECT_TRUE(firstHasRun);
    EXPECT_TRUE(firstWasPolled);
    return true;
  });

  scheduler.Schedule(command);
  scheduler.Run();

  EXPECT_TRUE(firstHasRun);
  EXPECT_TRUE(firstWasPolled);
}

TEST_F(CommandDecoratorTest, OnlyWhile) {
  CommandScheduler scheduler = GetScheduler();

  bool run = true;

  auto command = RunCommand([] {}, {}).OnlyWhile([&run] { return run; });

  scheduler.Schedule(command);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(command));

  run = false;
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, OnlyWhileOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool firstHasRun = false;
  bool firstWasPolled = false;

  auto first = FunctionalCommand([] {}, [&firstHasRun] { firstHasRun = true; },
                                 [](bool interrupted) {},
                                 [&firstWasPolled] {
                                   firstWasPolled = true;
                                   return true;
                                 });
  auto command = std::move(first).Until([&firstHasRun, &firstWasPolled] {
    EXPECT_TRUE(firstHasRun);
    EXPECT_TRUE(firstWasPolled);
    return false;
  });

  scheduler.Schedule(command);
  scheduler.Run();

  EXPECT_TRUE(firstHasRun);
  EXPECT_TRUE(firstWasPolled);
}

TEST_F(CommandDecoratorTest, IgnoringDisable) {
  CommandScheduler scheduler = GetScheduler();

  auto command = RunCommand([] {}, {}).IgnoringDisable(true);

  SetDSEnabled(false);

  scheduler.Schedule(command);

  scheduler.Run();
  EXPECT_TRUE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, BeforeStarting) {
  CommandScheduler scheduler = GetScheduler();

  bool finished = false;

  auto command = InstantCommand([] {}, {}).BeforeStarting(
      [&finished] { finished = true; });

  scheduler.Schedule(command);

  EXPECT_TRUE(finished);

  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(command));

  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, AndThenLambda) {
  CommandScheduler scheduler = GetScheduler();

  bool finished = false;

  auto command =
      InstantCommand([] {}, {}).AndThen([&finished] { finished = true; });

  scheduler.Schedule(command);

  EXPECT_FALSE(finished);

  scheduler.Run();

  EXPECT_TRUE(finished);

  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, AndThen) {
  CommandScheduler scheduler = GetScheduler();

  bool finished = false;

  auto command1 = InstantCommand();
  auto command2 = InstantCommand([&finished] { finished = true; });
  auto group = std::move(command1).AndThen(std::move(command2).ToPtr());

  scheduler.Schedule(group);

  EXPECT_FALSE(finished);

  scheduler.Run();

  EXPECT_TRUE(finished);

  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(group));
}

TEST_F(CommandDecoratorTest, DeadlineFor) {
  CommandScheduler scheduler = GetScheduler();

  bool finish = false;

  auto dictator = WaitUntilCommand([&finish] { return finish; });
  auto endsAfter = WaitUntilCommand([] { return false; });

  auto group = std::move(dictator).DeadlineFor(std::move(endsAfter).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(group));

  finish = true;
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(group));
}

TEST_F(CommandDecoratorTest, WithDeadline) {
  CommandScheduler scheduler = GetScheduler();

  bool finish = false;

  auto dictator = WaitUntilCommand([&finish] { return finish; });
  auto endsAfter = WaitUntilCommand([] { return false; });

  auto group = std::move(endsAfter).WithDeadline(std::move(dictator).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(group));

  finish = true;
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(group));
}

TEST_F(CommandDecoratorTest, AlongWith) {
  CommandScheduler scheduler = GetScheduler();

  bool finish = false;

  auto command1 = WaitUntilCommand([&finish] { return finish; });
  auto command2 = InstantCommand();

  auto group = std::move(command1).AlongWith(std::move(command2).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(scheduler.IsScheduled(group));

  finish = true;
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(group));
}

TEST_F(CommandDecoratorTest, RaceWith) {
  CommandScheduler scheduler = GetScheduler();

  auto command1 = WaitUntilCommand([] { return false; });
  auto command2 = InstantCommand();

  auto group = std::move(command1).RaceWith(std::move(command2).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_FALSE(scheduler.IsScheduled(group));
}

TEST_F(CommandDecoratorTest, DeadlineForOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool dictatorHasRun = false;
  bool dictatorWasPolled = false;

  auto dictator =
      FunctionalCommand([] {}, [&dictatorHasRun] { dictatorHasRun = true; },
                        [](bool interrupted) {},
                        [&dictatorWasPolled] {
                          dictatorWasPolled = true;
                          return true;
                        });
  auto other = RunCommand([&dictatorHasRun, &dictatorWasPolled] {
    EXPECT_TRUE(dictatorHasRun);
    EXPECT_TRUE(dictatorWasPolled);
  });

  auto group = std::move(dictator).DeadlineFor(std::move(other).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(dictatorHasRun);
  EXPECT_TRUE(dictatorWasPolled);
}

TEST_F(CommandDecoratorTest, WithDeadlineOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool dictatorHasRun = false;
  bool dictatorWasPolled = false;

  auto dictator =
      FunctionalCommand([] {}, [&dictatorHasRun] { dictatorHasRun = true; },
                        [](bool interrupted) {},
                        [&dictatorWasPolled] {
                          dictatorWasPolled = true;
                          return true;
                        });
  auto other = RunCommand([&dictatorHasRun, &dictatorWasPolled] {
    EXPECT_TRUE(dictatorHasRun);
    EXPECT_TRUE(dictatorWasPolled);
  });

  auto group = std::move(other).WithDeadline(std::move(dictator).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(dictatorHasRun);
  EXPECT_TRUE(dictatorWasPolled);
}

TEST_F(CommandDecoratorTest, AlongWithOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool firstHasRun = false;
  bool firstWasPolled = false;

  auto command1 = FunctionalCommand(
      [] {}, [&firstHasRun] { firstHasRun = true; }, [](bool interrupted) {},
      [&firstWasPolled] {
        firstWasPolled = true;
        return true;
      });
  auto command2 = RunCommand([&firstHasRun, &firstWasPolled] {
    EXPECT_TRUE(firstHasRun);
    EXPECT_TRUE(firstWasPolled);
  });

  auto group = std::move(command1).AlongWith(std::move(command2).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(firstHasRun);
  EXPECT_TRUE(firstWasPolled);
}

TEST_F(CommandDecoratorTest, RaceWithOrder) {
  CommandScheduler scheduler = GetScheduler();

  bool firstHasRun = false;
  bool firstWasPolled = false;

  auto command1 = FunctionalCommand(
      [] {}, [&firstHasRun] { firstHasRun = true; }, [](bool interrupted) {},
      [&firstWasPolled] {
        firstWasPolled = true;
        return true;
      });
  auto command2 = RunCommand([&firstHasRun, &firstWasPolled] {
    EXPECT_TRUE(firstHasRun);
    EXPECT_TRUE(firstWasPolled);
  });

  auto group = std::move(command1).RaceWith(std::move(command2).ToPtr());

  scheduler.Schedule(group);
  scheduler.Run();

  EXPECT_TRUE(firstHasRun);
  EXPECT_TRUE(firstWasPolled);
}

TEST_F(CommandDecoratorTest, Repeatedly) {
  CommandScheduler scheduler = GetScheduler();

  int counter = 0;

  auto command = InstantCommand([&counter] { counter++; }, {}).Repeatedly();

  scheduler.Schedule(command);

  for (int i = 1; i <= 50; i++) {
    scheduler.Run();
    EXPECT_EQ(i, counter);
  }

  EXPECT_TRUE(scheduler.IsScheduled(command));
}

TEST_F(CommandDecoratorTest, RepeatFor) {
  CommandScheduler scheduler = GetScheduler();

  int counter = 0;

  auto command = InstantCommand([&counter] { counter++; }, {}).Repeatedly(3);

  scheduler.Schedule(command);
  for (int i = 0; scheduler.IsScheduled(command); i++) {
    scheduler.Run();
    EXPECT_EQ(i + 1, counter);
  }

  EXPECT_EQ(3, counter);
}

TEST_F(CommandDecoratorTest, Unless) {
  CommandScheduler scheduler = GetScheduler();

  bool hasRun = false;
  bool unlessCondition = true;

  auto command = InstantCommand([&hasRun] { hasRun = true; }, {})
                     .Unless([&unlessCondition] { return unlessCondition; });

  scheduler.Schedule(command);
  scheduler.Run();
  EXPECT_FALSE(hasRun);

  unlessCondition = false;
  scheduler.Schedule(command);
  scheduler.Run();
  EXPECT_TRUE(hasRun);
}

TEST_F(CommandDecoratorTest, OnlyIf) {
  CommandScheduler scheduler = GetScheduler();

  bool hasRun = false;
  bool onlyIfCondition = false;

  auto command = InstantCommand([&hasRun] { hasRun = true; }, {})
                     .OnlyIf([&onlyIfCondition] { return onlyIfCondition; });

  scheduler.Schedule(command);
  scheduler.Run();
  EXPECT_FALSE(hasRun);

  onlyIfCondition = true;
  scheduler.Schedule(command);
  scheduler.Run();
  EXPECT_TRUE(hasRun);
}

TEST_F(CommandDecoratorTest, FinallyDo) {
  CommandScheduler scheduler = GetScheduler();
  int first = 0;
  int second = 0;
  CommandPtr command = FunctionalCommand([] {}, [] {},
                                         [&first](bool interrupted) {
                                           if (!interrupted) {
                                             first++;
                                           }
                                         },
                                         [] { return true; })
                           .FinallyDo([&first, &second](bool interrupted) {
                             if (!interrupted) {
                               // to differentiate between "didn't run" and "ran
                               // before command's `end()`
                               second += 1 + first;
                             }
                           });

  scheduler.Schedule(command);
  EXPECT_EQ(0, first);
  EXPECT_EQ(0, second);
  scheduler.Run();
  EXPECT_EQ(1, first);
  // if `second == 0`, neither of the lambdas ran.
  // if `second == 1`, the second lambda ran before the first one
  EXPECT_EQ(2, second);
}

// handleInterruptTest() implicitly tests the interrupt=true branch of
// finallyDo()
TEST_F(CommandDecoratorTest, HandleInterrupt) {
  CommandScheduler scheduler = GetScheduler();
  int first = 0;
  int second = 0;
  CommandPtr command = FunctionalCommand([] {}, [] {},
                                         [&first](bool interrupted) {
                                           if (interrupted) {
                                             first++;
                                           }
                                         },
                                         [] { return false; })
                           .HandleInterrupt([&first, &second] {
                             // to differentiate between "didn't run" and "ran
                             // before command's `end()`
                             second += 1 + first;
                           });

  scheduler.Schedule(command);
  scheduler.Run();
  EXPECT_EQ(0, first);
  EXPECT_EQ(0, second);

  scheduler.Cancel(command);
  // if `second == 0`, neither of the lambdas ran.
  // if `second == 1`, the second lambda ran before the first one
  EXPECT_EQ(2, second);
}

TEST_F(CommandDecoratorTest, WithName) {
  InstantCommand command;
  std::string name{"Named"};
  CommandPtr named = std::move(command).WithName(name);
  EXPECT_EQ(name, named.get()->GetName());
}
