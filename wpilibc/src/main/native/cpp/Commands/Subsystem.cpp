/*----------------------------------------------------------------------------*/
/* Copyright (c) 2011-2017 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Commands/Subsystem.h"

#include "Commands/Command.h"
#include "Commands/Scheduler.h"
#include "WPIErrors.h"

using namespace frc;

/**
 * Creates a subsystem with the given name.
 *
 * @param name the name of the subsystem
 */
Subsystem::Subsystem(const std::string& name) {
  m_name = name;
  Scheduler::GetInstance()->RegisterSubsystem(this);
}
/**
 * Initialize the default command for this subsystem.
 *
 * This is meant to be the place to call SetDefaultCommand in a subsystem and
 * will be called on all the subsystems by the CommandBase method before the
 * program starts running by using the list of all registered Subsystems inside
 * the Scheduler.
 *
 * This should be overridden by a Subsystem that has a default Command
 */
void Subsystem::InitDefaultCommand() {}

/**
 * Sets the default command.  If this is not called or is called with null,
 * then there will be no default command for the subsystem.
 *
 * <b>WARNING:</b> This should <b>NOT</b> be called in a constructor if the
 * subsystem is a singleton.
 *
 * @param command the default command (or null if there should be none)
 */
void Subsystem::SetDefaultCommand(Command* command) {
  if (command == nullptr) {
    m_defaultCommand = nullptr;
  } else {
    bool found = false;
    Command::SubsystemSet requirements = command->GetRequirements();
    for (auto iter = requirements.begin(); iter != requirements.end(); iter++) {
      if (*iter == this) {
        found = true;
        break;
      }
    }

    if (!found) {
      wpi_setWPIErrorWithContext(
          CommandIllegalUse, "A default command must require the subsystem");
      return;
    }

    m_defaultCommand = command;
  }
  if (m_hasDefaultEntry && m_defaultEntry) {
    if (m_defaultCommand != nullptr) {
      m_hasDefaultEntry.SetBoolean(true);
      m_defaultEntry.SetString(m_defaultCommand->GetName());
    } else {
      m_hasDefaultEntry.SetBoolean(false);
    }
  }
}

/**
 * Returns the default command (or null if there is none).
 *
 * @return the default command
 */
Command* Subsystem::GetDefaultCommand() {
  if (!m_initializedDefaultCommand) {
    m_initializedDefaultCommand = true;
    InitDefaultCommand();
  }
  return m_defaultCommand;
}

/**
 * Sets the current command.
 *
 * @param command the new current command
 */
void Subsystem::SetCurrentCommand(Command* command) {
  m_currentCommand = command;
  m_currentCommandChanged = true;
}

/**
 * Returns the command which currently claims this subsystem.
 *
 * @return the command which currently claims this subsystem
 */
Command* Subsystem::GetCurrentCommand() const { return m_currentCommand; }

/**
 * When the run method of the scheduler is called this method will be called.
 */
void Subsystem::Periodic() {}

/**
 * Call this to alert Subsystem that the current command is actually the
 * command.
 *
 * Sometimes, the Subsystem is told that it has no command while the Scheduler
 * is going through the loop, only to be soon after given a new one. This will
 * avoid that situation.
 */
void Subsystem::ConfirmCommand() {
  if (m_currentCommandChanged) {
    if (m_hasCommandEntry && m_commandEntry) {
      if (m_currentCommand != nullptr) {
        m_hasCommandEntry.SetBoolean(true);
        m_commandEntry.SetString(m_currentCommand->GetName());
      } else {
        m_hasCommandEntry.SetBoolean(false);
      }
    }
    m_currentCommandChanged = false;
  }
}

std::string Subsystem::GetName() const { return m_name; }

std::string Subsystem::GetSmartDashboardType() const { return "Subsystem"; }

void Subsystem::InitTable(std::shared_ptr<nt::NetworkTable> subtable) {
  if (subtable != nullptr) {
    m_hasDefaultEntry = subtable->GetEntry("hasDefault");
    m_defaultEntry = subtable->GetEntry("default");
    m_hasCommandEntry = subtable->GetEntry("hasCommand");
    m_commandEntry = subtable->GetEntry("command");

    if (m_defaultCommand != nullptr) {
      m_hasDefaultEntry.SetBoolean(true);
      m_defaultEntry.SetString(m_defaultCommand->GetName());
    } else {
      m_hasDefaultEntry.SetBoolean(false);
    }
    if (m_currentCommand != nullptr) {
      m_hasCommandEntry.SetBoolean(true);
      m_commandEntry.SetString(m_currentCommand->GetName());
    } else {
      m_hasCommandEntry.SetBoolean(false);
    }
  }
}
