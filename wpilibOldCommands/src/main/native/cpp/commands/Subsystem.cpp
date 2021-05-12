// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/commands/Subsystem.h"

#include "frc/Errors.h"
#include "frc/commands/Command.h"
#include "frc/commands/Scheduler.h"
#include "frc/livewindow/LiveWindow.h"
#include "frc/smartdashboard/SendableBuilder.h"
#include "frc/smartdashboard/SendableRegistry.h"

using namespace frc;

Subsystem::Subsystem(const wpi::Twine& name) {
  SendableRegistry::AddLW(this, name, name);
  Scheduler::GetInstance()->RegisterSubsystem(this);
}

void Subsystem::SetDefaultCommand(Command* command) {
  if (command == nullptr) {
    m_defaultCommand = nullptr;
  } else {
    const auto& reqs = command->GetRequirements();
    if (std::find(reqs.begin(), reqs.end(), this) == reqs.end()) {
      throw FRC_MakeError(err::CommandIllegalUse,
                          "A default command must require the subsystem");
    }

    m_defaultCommand = command;
  }
}

Command* Subsystem::GetDefaultCommand() {
  if (!m_initializedDefaultCommand) {
    m_initializedDefaultCommand = true;
    InitDefaultCommand();
  }
  return m_defaultCommand;
}

wpi::StringRef Subsystem::GetDefaultCommandName() {
  Command* defaultCommand = GetDefaultCommand();
  if (defaultCommand) {
    return SendableRegistry::GetName(defaultCommand);
  } else {
    return wpi::StringRef();
  }
}

void Subsystem::SetCurrentCommand(Command* command) {
  m_currentCommand = command;
  m_currentCommandChanged = true;
}

Command* Subsystem::GetCurrentCommand() const {
  return m_currentCommand;
}

wpi::StringRef Subsystem::GetCurrentCommandName() const {
  Command* currentCommand = GetCurrentCommand();
  if (currentCommand) {
    return SendableRegistry::GetName(currentCommand);
  } else {
    return wpi::StringRef();
  }
}

void Subsystem::Periodic() {}

void Subsystem::InitDefaultCommand() {}

std::string Subsystem::GetName() const {
  return SendableRegistry::GetName(this);
}

void Subsystem::SetName(const wpi::Twine& name) {
  SendableRegistry::SetName(this, name);
}

std::string Subsystem::GetSubsystem() const {
  return SendableRegistry::GetSubsystem(this);
}

void Subsystem::SetSubsystem(const wpi::Twine& name) {
  SendableRegistry::SetSubsystem(this, name);
}

void Subsystem::AddChild(const wpi::Twine& name,
                         std::shared_ptr<Sendable> child) {
  AddChild(name, *child);
}

void Subsystem::AddChild(const wpi::Twine& name, Sendable* child) {
  AddChild(name, *child);
}

void Subsystem::AddChild(const wpi::Twine& name, Sendable& child) {
  SendableRegistry::AddLW(&child, SendableRegistry::GetSubsystem(this), name);
}

void Subsystem::AddChild(std::shared_ptr<Sendable> child) {
  AddChild(*child);
}

void Subsystem::AddChild(Sendable* child) {
  AddChild(*child);
}

void Subsystem::AddChild(Sendable& child) {
  SendableRegistry::SetSubsystem(&child, SendableRegistry::GetSubsystem(this));
  SendableRegistry::EnableLiveWindow(&child);
}

void Subsystem::ConfirmCommand() {
  if (m_currentCommandChanged) {
    m_currentCommandChanged = false;
  }
}

void Subsystem::InitSendable(SendableBuilder& builder) {
  builder.SetSmartDashboardType("Subsystem");

  builder.AddBooleanProperty(
      ".hasDefault", [=]() { return m_defaultCommand != nullptr; }, nullptr);
  builder.AddStringProperty(
      ".default", [=]() { return GetDefaultCommandName(); }, nullptr);

  builder.AddBooleanProperty(
      ".hasCommand", [=]() { return m_currentCommand != nullptr; }, nullptr);
  builder.AddStringProperty(
      ".command", [=]() { return GetCurrentCommandName(); }, nullptr);
}
