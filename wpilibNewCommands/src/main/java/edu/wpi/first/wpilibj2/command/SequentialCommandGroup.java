// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj2.command;

import java.util.ArrayList;
import java.util.List;

/**
 * A CommandGroups that runs a list of commands in sequence.
 *
 * <p>As a rule, CommandGroups require the union of the requirements of their component commands.
 */
public class SequentialCommandGroup extends CommandGroupBase {
  private final List<Command> m_commands = new ArrayList<>();
  private int m_currentCommandIndex = -1;
  private boolean m_runWhenDisabled = true;

  /**
   * Creates a new SequentialCommandGroup. The given commands will be run sequentially, with the
   * CommandGroup finishing when the last command finishes.
   *
   * @param commands the commands to include in this group.
   */
  public SequentialCommandGroup(Command... commands) {
    addCommands(commands);
  }

  @Override
  public final void addCommands(Command... commands) {
    requireUngrouped(commands);

    if (m_currentCommandIndex != -1) {
      throw new IllegalStateException(
          "Commands cannot be added to a CommandGroup while the group is running");
    }

    registerGroupedCommands(commands);

    for (Command command : commands) {
      m_commands.add(command);
      m_requirements.addAll(command.getRequirements());
      m_runWhenDisabled &= command.runsWhenDisabled();
    }
  }

  @Override
  public final void initialize() {
    m_currentCommandIndex = 0;

    if (!m_commands.isEmpty()) {
      m_commands.get(0).initialize();
    }
  }

  @Override
  public final void execute() {
    if (m_commands.isEmpty()) {
      return;
    }

    Command currentCommand = m_commands.get(m_currentCommandIndex);

    currentCommand.execute();
    if (currentCommand.isFinished()) {
      currentCommand.end(false);
      m_currentCommandIndex++;
      if (m_currentCommandIndex < m_commands.size()) {
        m_commands.get(m_currentCommandIndex).initialize();
      }
    }
  }

  @Override
  public final void end(boolean interrupted) {
    if (interrupted
        && !m_commands.isEmpty()
        && m_currentCommandIndex > -1
        && m_currentCommandIndex < m_commands.size()) {
      m_commands.get(m_currentCommandIndex).end(true);
    }
    m_currentCommandIndex = -1;
  }

  @Override
  public final boolean isFinished() {
    return m_currentCommandIndex == m_commands.size();
  }

  @Override
  public final boolean runsWhenDisabled() {
    return m_runWhenDisabled;
  }

  @Override
  public final SequentialCommandGroup beforeStarting(Command before) {
    // store all the commands
    var commands = new ArrayList<Command>();
    commands.add(before);
    commands.addAll(m_commands);

    // reset current state
    commands.forEach(CommandGroupBase::clearGroupedCommand);
    m_commands.clear();
    m_requirements.clear();
    m_runWhenDisabled = true;

    // add them back
    addCommands(commands.toArray(Command[]::new));
    return this;
  }

  @Override
  public final SequentialCommandGroup andThen(Command... next) {
    addCommands(next);
    return this;
  }
}
