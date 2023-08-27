// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>
#include <span>

#include <wpi/FunctionExtras.h>

#include "frc2/command/Command.h"
#include "frc2/command/CommandHelper.h"
#include "frc2/command/PrintCommand.h"

namespace frc2 {
/**
 * Runs the command returned by the supplier when this command is initialized,
 * and ends when it ends. Useful for performing runtime tasks before creating a
 * new command. If this command is interrupted, it will cancel the command.
 *
 * Note that the supplier <i>must</i> create a new Command each call. For
 * selecting one of a preallocated set of commands, use SelectCommand.
 *
 * <p>This class is provided by the NewCommands VendorDep
 */
class DeferredCommand : public CommandHelper<Command, DeferredCommand> {
 public:
  /**
   * Creates a new DeferredCommand that runs the supplied command when
   * initialized, and ends when it ends. Useful for lazily
   * creating commands at runtime. The supplier will be called each time this
   * command is initialized. The supplier <i>must</i> create a new Command each
   * call.
   *
   * @param supplier The command supplier
   * @param requirements The command requirements.
   *
   */
  explicit DeferredCommand(wpi::unique_function<Command*()> supplier,
                           std::span<Subsystem* const> requirements);

  /**
   * Creates a new DeferredCommand that runs the supplied command when
   * initialized, and ends when it ends. Useful for lazily
   * creating commands at runtime. The supplier will be called each time this
   * command is initialized. The supplier <i>must</i> create a new Command each
   * call.
   *
   * @param supplier The command supplier
   * @param requirements The command requirements.
   *
   */
  explicit DeferredCommand(wpi::unique_function<Command*()> supplier,
                           std::initializer_list<Subsystem*> requirements);

  /**
   * Creates a new DeferredCommand that runs the supplied command when
   * initialized, and ends when it ends. Useful for lazily
   * creating commands at runtime. The supplier will be called each time this
   * command is initialized. The supplier <i>must</i> create a new Command each
   * call.
   *
   * @param supplier The command supplier
   * @param requirements The command requirements.
   *
   */
  explicit DeferredCommand(wpi::unique_function<CommandPtr()> supplier,
                           std::span<Subsystem* const> requirements);

  /**
   * Creates a new DeferredCommand that runs the supplied command when
   * initialized, and ends when it ends. Useful for lazily
   * creating commands at runtime. The supplier will be called each time this
   * command is initialized. The supplier <i>must</i> create a new Command each
   * call.
   *
   * @param supplier The command supplier
   * @param requirements The command requirements.
   *
   */
  explicit DeferredCommand(wpi::unique_function<CommandPtr()> supplier,
                           std::initializer_list<Subsystem*> requirements);

  DeferredCommand(DeferredCommand&& other) = default;

  void Initialize() override;

  void Execute() override;

  void End(bool interrupted) override;

  bool IsFinished() override;

  void InitSendable(wpi::SendableBuilder& builder) override;

 private:
  PrintCommand m_nullCommand{"[DeferredCommand] Supplied command was null!"};
  wpi::unique_function<Command*()> m_supplier;
  Command* m_command{&m_nullCommand};
};
}  // namespace frc2
