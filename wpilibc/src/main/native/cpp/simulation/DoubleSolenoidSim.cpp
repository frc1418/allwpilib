// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/simulation/DoubleSolenoidSim.h"

#include "frc/PneumaticsBase.h"

using namespace frc;
using namespace frc::sim;

DoubleSolenoidSim::DoubleSolenoidSim(
    std::shared_ptr<PneumaticsBaseSim> moduleSim, int fwd, int rev)
    : m_module{std::move(moduleSim)}, m_fwd{fwd}, m_rev{rev} {}

DoubleSolenoidSim::DoubleSolenoidSim(int module, PneumaticsModuleType type,
                                     int fwd, int rev)
    : m_module{PneumaticsBaseSim::GetForType(module, type)},
      m_fwd{fwd},
      m_rev{rev} {}

DoubleSolenoidSim::DoubleSolenoidSim(PneumaticsModuleType type, int fwd,
                                     int rev)
    : m_module{PneumaticsBaseSim::GetForType(
          PneumaticsBase::GetDefaultForType(type), type)},
      m_fwd{fwd},
      m_rev{rev} {}

DoubleSolenoid::Value DoubleSolenoidSim::Get() const {
  bool fwdState = m_module->GetSolenoidOutput(m_fwd);
  bool revState = m_module->GetSolenoidOutput(m_rev);
  if (fwdState && !revState) {
    return DoubleSolenoid::Value::kForward;
  } else if (!fwdState && revState) {
    return DoubleSolenoid::Value::kReverse;
  } else {
    return DoubleSolenoid::Value::kOff;
  }
}

void DoubleSolenoidSim::Set(DoubleSolenoid::Value output) {
  m_module->SetSolenoidOutput(m_fwd, output == DoubleSolenoid::Value::kForward);
  m_module->SetSolenoidOutput(m_rev, output == DoubleSolenoid::Value::kReverse);
}

bool DoubleSolenoid::IsForward() const {
  return Get() == kForward;
}

bool DoubleSolenoid::IsReverse() const {
  return Get() == kReverse;
}

bool DoubleSolenoid::IsOff() const {
  return Get() == kOff;
}

void DoubleSolenoid::Forward() {
  Set(kForward);
}

void DoubleSolenoid::Reverse() {
  Set(kReverse);
}

void DoubleSolenoid::Off() {
  Set(kOff);
}

std::shared_ptr<PneumaticsBaseSim> DoubleSolenoidSim::GetModuleSim() const {
  return m_module;
}
