// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class WaitUntilCommandTest extends CommandTestBase {
  @Test
  void waitUntilTest() {
    AtomicBoolean condition = new AtomicBoolean();

    Command command = new WaitUntilCommand(condition::get);

    command.schedule();
    CommandScheduler.getInstance().run();
    assertTrue(command.isScheduled());
    condition.set(true);
    CommandScheduler.getInstance().run();
    assertFalse(command.isScheduled());
  }
}
