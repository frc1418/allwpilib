// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class FunctionalCommandTest extends CommandTestBase {
  @Test
  void functionalCommandScheduleTest() {
    AtomicBoolean cond1 = new AtomicBoolean();
    AtomicBoolean cond2 = new AtomicBoolean();
    AtomicBoolean cond3 = new AtomicBoolean();
    AtomicBoolean cond4 = new AtomicBoolean();

    FunctionalCommand command =
        new FunctionalCommand(
            () -> cond1.set(true),
            () -> cond2.set(true),
            interrupted -> cond3.set(true),
            cond4::get);

    command.schedule();
    CommandScheduler.getInstance().run();

    assertTrue(command.isScheduled());

    cond4.set(true);

    CommandScheduler.getInstance().run();

    assertFalse(command.isScheduled());
    assertTrue(cond1.get());
    assertTrue(cond2.get());
    assertTrue(cond3.get());
  }
}
