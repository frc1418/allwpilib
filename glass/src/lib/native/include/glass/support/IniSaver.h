// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <imgui.h>
#include <wpi/DenseMap.h>
#include <wpi/Twine.h>

#include "glass/support/IniSaverBase.h"

namespace glass {

template <typename Info>
class IniSaver : public IniSaverBase {
 public:
  explicit IniSaver(const wpi::Twine& typeName,
                    IniSaverBackend* backend = nullptr)
      : IniSaverBase(typeName, backend) {}

  // pass through useful functions to map
  Info& operator[](int index) { return m_map[index]; }

  auto begin() { return m_map.begin(); }
  auto end() { return m_map.end(); }
  auto find(int index) { return m_map.find(index); }

  auto begin() const { return m_map.begin(); }
  auto end() const { return m_map.end(); }
  auto find(int index) const { return m_map.find(index); }

 private:
  void* IniReadOpen(const char* name) override;
  void IniReadLine(void* entry, const char* lineStr) override;
  void IniWriteAll(ImGuiTextBuffer* out_buf) override;

  wpi::DenseMap<int, Info> m_map;
};

}  // namespace glass

#include "IniSaver.inc"
