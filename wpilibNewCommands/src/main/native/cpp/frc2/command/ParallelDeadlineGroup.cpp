// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc2/command/ParallelDeadlineGroup.h"

using namespace frc2;

ParallelDeadlineGroup::ParallelDeadlineGroup(
    std::unique_ptr<Command>&& deadline,
    std::vector<std::unique_ptr<Command>>&& commands) {
  SetDeadline(std::move(deadline));
  AddCommands(std::move(commands));
}

void ParallelDeadlineGroup::Initialize() {
  for (auto& commandRunning : m_commands) {
    commandRunning.first->Initialize();
    commandRunning.second = true;
  }
  m_finished = false;
}

void ParallelDeadlineGroup::Execute() {
  for (auto& commandRunning : m_commands) {
    if (!commandRunning.second) {
      continue;
    }
    commandRunning.first->Execute();
    if (commandRunning.first->IsFinished()) {
      commandRunning.first->End(false);
      commandRunning.second = false;
      if (commandRunning.first.get() == m_deadline) {
        m_finished = true;
      }
    }
  }
}

void ParallelDeadlineGroup::End(bool interrupted) {
  for (auto& commandRunning : m_commands) {
    if (commandRunning.second) {
      commandRunning.first->End(true);
    }
  }
}

bool ParallelDeadlineGroup::IsFinished() {
  return m_finished;
}

bool ParallelDeadlineGroup::RunsWhenDisabled() const {
  return m_runWhenDisabled;
}

void ParallelDeadlineGroup::AddCommands(
    std::vector<std::unique_ptr<Command>>&& commands) {
  if (!RequireUngrouped(commands)) {
    return;
  }

  if (!m_finished) {
    throw FRC_MakeError(frc::err::CommandIllegalUse, "{}",
                        "Commands cannot be added to a CommandGroup "
                        "while the group is running");
  }

  for (auto&& command : commands) {
    if (RequirementsDisjoint(this, command.get())) {
      command->SetGrouped(true);
      AddRequirements(command->GetRequirements());
      m_runWhenDisabled &= command->RunsWhenDisabled();
      m_commands.emplace_back(std::move(command), false);
    } else {
      throw FRC_MakeError(frc::err::CommandIllegalUse, "{}",
                          "Multiple commands in a parallel group cannot "
                          "require the same subsystems");
    }
  }
}

void ParallelDeadlineGroup::SetDeadline(std::unique_ptr<Command>&& deadline) {
  m_deadline = deadline.get();
  m_deadline->SetGrouped(true);
  m_commands.emplace_back(std::move(deadline), false);
  AddRequirements(m_deadline->GetRequirements());
  m_runWhenDisabled &= m_deadline->RunsWhenDisabled();
}
