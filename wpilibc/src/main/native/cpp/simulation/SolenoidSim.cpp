// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/simulation/SolenoidSim.h"

#include "frc/PneumaticsBase.h"

using namespace frc;
using namespace frc::sim;

SolenoidSim::SolenoidSim(std::shared_ptr<PneumaticsBaseSim> moduleSim,
                         int channel)
    : m_module{std::move(moduleSim)}, m_channel{channel} {}

SolenoidSim::SolenoidSim(int module, PneumaticsModuleType type, int channel)
    : m_module{PneumaticsBaseSim::GetForType(module, type)},
      m_channel{channel} {}

SolenoidSim::SolenoidSim(PneumaticsModuleType type, int channel)
    : m_module{PneumaticsBaseSim::GetForType(
          PneumaticsBase::GetDefaultForType(type), type)},
      m_channel{channel} {}

bool SolenoidSim::Get() const {
  return m_module->GetSolenoidOutput(m_channel);
}

void SolenoidSim::Set(bool output) {
  m_module->SetSolenoidOutput(m_channel, output);
}

bool SolenoidSim::IsOn() {
  return Get();
}

bool SolenoidSim::IsOff() {
  return !Get();
}

void SolenoidSim::SetOn() {
  Set(true);
}

void SolenoidSim::SetOff() {
  Set(false);
}

std::unique_ptr<CallbackStore> SolenoidSim::RegisterOutputCallback(
    NotifyCallback callback, bool initialNotify) {
  return m_module->RegisterSolenoidOutputCallback(m_channel, callback,
                                                  initialNotify);
}

std::shared_ptr<PneumaticsBaseSim> SolenoidSim::GetModuleSim() const {
  return m_module;
}
