// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>

#include <networktables/NTSendable.h>

namespace frc {

class ButtonScheduler;
class Command;
class Subsystem;

class Scheduler : public nt::NTSendable {
 public:
  /**
   * Returns the Scheduler, creating it if one does not exist.
   *
   * @return the Scheduler
   */
  static Scheduler* GetInstance();

  /**
   * Add a command to be scheduled later.
   *
   * In any pass through the scheduler, all commands are added to the additions
   * list, then at the end of the pass, they are all scheduled.
   *
   * @param command The command to be scheduled
   */
  void AddCommand(Command* command);

  void AddButton(ButtonScheduler* button);

  /**
   * Registers a Subsystem to this Scheduler, so that the Scheduler might know
   * if a default Command needs to be run.
   *
   * All Subsystems should call this.
   *
   * @param system the system
   */
  void RegisterSubsystem(Subsystem* subsystem);

  /**
   * Runs a single iteration of the loop.
   *
   * This method should be called often in order to have a functioning
   * Command system. The loop has five stages:
   *
   * <ol>
   *   <li>Poll the Buttons</li>
   *   <li>Execute/Remove the Commands</li>
   *   <li>Send values to SmartDashboard</li>
   *   <li>Add Commands</li>
   *   <li>Add Defaults</li>
   * </ol>
   */
  void Run();

  /**
   * Removes the Command from the Scheduler.
   *
   * @param command the command to remove
   */
  void Remove(Command* command);

  void RemoveAll();

  /**
   * Completely resets the scheduler. Undefined behavior if running.
   */
  void ResetAll();

  void SetEnabled(bool enabled);

  void InitSendable(nt::NTSendableBuilder& builder) override;

 private:
  Scheduler();
  ~Scheduler() override;

  Scheduler(Scheduler&&) = default;
  Scheduler& operator=(Scheduler&&) = default;

  struct Impl;
  std::unique_ptr<Impl> m_impl;
};

}  // namespace frc
