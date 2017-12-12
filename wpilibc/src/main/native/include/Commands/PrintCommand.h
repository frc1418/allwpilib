/*----------------------------------------------------------------------------*/
/* Copyright (c) 2011-2017 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <string>

#include <llvm/Twine.h>

#include "Commands/InstantCommand.h"

namespace frc {

class PrintCommand : public InstantCommand {
 public:
  explicit PrintCommand(const llvm::Twine& message);
  virtual ~PrintCommand() = default;

 protected:
  virtual void Initialize();

 private:
  std::string m_message;
};

}  // namespace frc
