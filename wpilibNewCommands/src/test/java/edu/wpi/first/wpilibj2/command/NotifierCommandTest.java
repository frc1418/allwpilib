// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.simulation.SimHooks;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

class NotifierCommandTest extends CommandTestBase {
  @BeforeEach
  void setup() {
    HAL.initialize(500, 0);
    SimHooks.pauseTiming();
  }

  @AfterEach
  void cleanup() {
    SimHooks.resumeTiming();
  }

  @Test
  @ResourceLock("timing")
  void notifierCommandScheduleTest() {
    AtomicInteger counter = new AtomicInteger(0);

    NotifierCommand command = new NotifierCommand(counter::incrementAndGet, 0.01);

    command.schedule();
    for (int i = 0; i < 5; ++i) {
      SimHooks.stepTiming(0.005);
    }
    command.cancel();

    assertEquals(2, counter.get());
  }
}
