// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>

#include <wpi/FunctionExtras.h>
#include <fmt/core.h>

#include "frc2/command/Command.h"
#include "frc2/command/CommandHelper.h"
#include "wpi/deprecated.h"

namespace frc2 {
/**
 * Schedules the given command when this command is initialized, and ends when
 * it ends. Useful for including a command in a composition without adding its
 * requirements. If this command is interrupted, it will cancel the command.
 *
 * <p>This class is provided by the NewCommands VendorDep
 */
class ProxyCommand : public CommandHelper<Command, ProxyCommand> {
 public:
  /**
   * Creates a new ProxyCommand that schedules the supplied command when
   * initialized, and ends when it is no longer scheduled. Useful for lazily
   * creating proxied commands at runtime. Proxying should only be done if truly
   * necessary, if only runtime command construction is needed, use {@link
   * DeferredCommand} instead.
   *
   * @param supplier the command supplier
   */
   WPI_IGNORE_DEPRECATED
  [[deprecated("Proxy a DeferredCommand instead")]] explicit ProxyCommand(
      wpi::unique_function<Command*()> supplier);

  /**
   * Creates a new ProxyCommand that schedules the supplied command when
   * initialized, and ends when it is no longer scheduled. Use this for lazily
   * creating <strong>proxied</strong> commands at runtime. Proxying should only
   * be done to escape from composition requirement semantics, so if only
   * initialization time command construction is needed, use {@link
   * DeferredCommand} instead.
   *
   * @param supplier the command supplier
   */
  [[deprecated("Proxy a DeferredCommand instead")]] explicit ProxyCommand(
      wpi::unique_function<CommandPtr()> supplier);
WPI_UNIGNORE_DEPRECATED
  /**
   * Creates a new ProxyCommand that schedules the given command when
   * initialized, and ends when it is no longer scheduled.
   *
   * @param command the command to run by proxy
   */
  explicit ProxyCommand(Command* command);

  /**
   * Creates a new ProxyCommand that schedules the given command when
   * initialized, and ends when it is no longer scheduled.
   *
   * <p>Note that this constructor passes ownership of the given command to the
   * returned ProxyCommand.
   *
   * @param command the command to schedule
   */
  explicit ProxyCommand(std::unique_ptr<Command> command);

  ProxyCommand(ProxyCommand&& other) = default;

  void Initialize() override;

  void End(bool interrupted) override;

  bool IsFinished() override;

  void InitSendable(wpi::SendableBuilder& builder) override;

 private:
  wpi::unique_function<Command*()> m_supplier;
  Command* m_command = nullptr;
};
}  // namespace frc2
