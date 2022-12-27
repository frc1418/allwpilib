// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SelectCommandTest extends CommandTestBase implements MultiCompositionTestBase<SelectCommand> {
  @Test
  void selectCommandTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    command1Holder.setFinished(true);
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();
    MockCommandHolder command3Holder = new MockCommandHolder(true);
    Command command3 = command3Holder.getMock();

    SelectCommand selectCommand =
        new SelectCommand(
            Map.ofEntries(
                Map.entry("one", command1),
                Map.entry("two", command2),
                Map.entry("three", command3)),
            () -> "one");

    selectCommand.schedule();
    CommandScheduler.getInstance().run();

    verify(command1).initialize();
    verify(command1).execute();
    verify(command1).end(false);

    verify(command2, never()).initialize();
    verify(command2, never()).execute();
    verify(command2, never()).end(false);

    verify(command3, never()).initialize();
    verify(command3, never()).execute();
    verify(command3, never()).end(false);
  }

  @Test
  void selectCommandInvalidKeyTest() {
    MockCommandHolder command1Holder = new MockCommandHolder(true);
    Command command1 = command1Holder.getMock();
    command1Holder.setFinished(true);
    MockCommandHolder command2Holder = new MockCommandHolder(true);
    Command command2 = command2Holder.getMock();
    MockCommandHolder command3Holder = new MockCommandHolder(true);
    Command command3 = command3Holder.getMock();

    SelectCommand selectCommand =
        new SelectCommand(
            Map.ofEntries(
                Map.entry("one", command1),
                Map.entry("two", command2),
                Map.entry("three", command3)),
            () -> "four");

    assertDoesNotThrow(selectCommand::schedule);
  }

  @Test
  void selectCommandRequirementTest() {
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

    SelectCommand selectCommand =
        new SelectCommand(
            Map.ofEntries(
                Map.entry("one", command1),
                Map.entry("two", command2),
                Map.entry("three", command3)),
            () -> "one");

    selectCommand.schedule();
    new InstantCommand(() -> {}, system3).schedule();

    assertFalse(selectCommand.isScheduled());

    verify(command1).end(true);
    verify(command2, never()).end(true);
    verify(command3, never()).end(true);
  }

  @Override
  public SelectCommand compose(Command... members) {
    var map = new HashMap<Object, Command>();
    for (int i = 0; i < members.length; i++) {
      map.put(i, members[i]);
    }
    return new SelectCommand(map, () -> 0);
  }
}
