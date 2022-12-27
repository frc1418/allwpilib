// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class ParallelRaceGroupTest extends CommandTestBase
    implements MultiCompositionTestBase<ParallelRaceGroup> {
  @Test
  void parallelRaceScheduleTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();

    Command group = new ParallelRaceGroup(command1, command2);

    group.schedule();

    verify(command1).initialize();
    verify(command2).initialize();

    command1Holder.setFinished(true);
    CommandScheduler.getInstance().run();
    command2Holder.setFinished(true);
    CommandScheduler.getInstance().run();

    verify(command1).execute();
    verify(command1).end(false);
    verify(command2).execute();
    verify(command2).end(true);
    verify(command2, never()).end(false);

    assertFalse(group.isScheduled());
  }

  @Test
  void parallelRaceInterruptTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();

    Command group = new ParallelRaceGroup(command1, command2);

    group.schedule();

    CommandScheduler.getInstance().run();
    CommandScheduler.getInstance().run();
    group.cancel();

    verify(command1, times(2)).execute();
    verify(command1, never()).end(false);
    verify(command1).end(true);

    verify(command2, times(2)).execute();
    verify(command2, never()).end(false);
    verify(command2).end(true);

    assertFalse(group.isScheduled());
  }

  @Test
  void notScheduledCancelTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();

    Command group = new ParallelRaceGroup(command1, command2);

    assertDoesNotThrow(group::cancel);
  }

  @Test
  void parallelRaceRequirementTest() {
    Subsystem system1 = new SubsystemBase() {};
    Subsystem system2 = new SubsystemBase() {};
    Subsystem system3 = new SubsystemBase() {};
    Subsystem system4 = new SubsystemBase() {};

    MockCommandHolder command1Holder = new MockCommandHolder(true, system1, system2);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true, system3);
    Command command2 = command2Holder.getMock();
    MockCommandHolder command3Holder = new MockCommandHolder(true, system3, system4);
    Command command3 = command3Holder.getMock();

    Command group = new ParallelRaceGroup(command1, command2);

    group.schedule();
    command3.schedule();

    assertFalse(group.isScheduled());
    assertTrue(command3.isScheduled());
  }

  @Test
  void parallelRaceRequirementErrorTest() {
    Subsystem system1 = new SubsystemBase() {};
    Subsystem system2 = new SubsystemBase() {};
    Subsystem system3 = new SubsystemBase() {};

    MockCommandHolder command1Holder = new MockCommandHolder(true, system1, system2);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true, system2, system3);
    Command command2 = command2Holder.getMock();

    assertThrows(IllegalArgumentException.class, () -> new ParallelRaceGroup(command1, command2));
  }

  @Test
  void parallelRaceOnlyCallsEndOnceTest() {
    Subsystem system1 = new SubsystemBase() {};
    Subsystem system2 = new SubsystemBase() {};

    MockCommandHolder command1Holder = new MockCommandHolder(true, system1);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true, system2);
    Command command2 = command2Holder.getMock();
    MockCommandHolder command3Holder = new MockCommandHolder(true);
    Command command3 = command3Holder.getMock();

    Command group1 = new SequentialCommandGroup(command1, command2);
    assertNotNull(group1);
    assertNotNull(command3);
    Command group2 = new ParallelRaceGroup(group1, command3);

    group2.schedule();
    CommandScheduler.getInstance().run();
    command1Holder.setFinished(true);
    CommandScheduler.getInstance().run();
    command2Holder.setFinished(true);
    // at this point the sequential group should be done
    assertDoesNotThrow(() -> CommandScheduler.getInstance().run());
    assertFalse(group2.isScheduled());
  }

  @Test
  void parallelRaceScheduleTwiceTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();

    Command group = new ParallelRaceGroup(command1, command2);

    group.schedule();

    verify(command1).initialize();
    verify(command2).initialize();

    command1Holder.setFinished(true);
    CommandScheduler.getInstance().run();
    command2Holder.setFinished(true);
    CommandScheduler.getInstance().run();

    verify(command1).execute();
    verify(command1).end(false);
    verify(command2).execute();
    verify(command2).end(true);
    verify(command2, never()).end(false);

    assertFalse(group.isScheduled());

    reset(command1);
    reset(command2);

    group.schedule();

    verify(command1).initialize();
    verify(command2).initialize();

    CommandScheduler.getInstance().run();
    CommandScheduler.getInstance().run();
    assertTrue(group.isScheduled());
    command2Holder.setFinished(true);
    CommandScheduler.getInstance().run();

    assertFalse(group.isScheduled());
  }

  @Override
  public ParallelRaceGroup compose(Command... members) {
    return new ParallelRaceGroup(members);
  }
}
