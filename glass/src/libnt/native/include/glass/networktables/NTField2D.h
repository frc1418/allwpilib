// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>
#include <string>
#include <string_view>
#include <utility>
#include <vector>

#include <ntcore_cpp.h>

#include "glass/networktables/NetworkTablesHelper.h"
#include "glass/other/Field2D.h"

namespace glass {

class NTField2DModel : public Field2DModel {
 public:
  static constexpr const char* kType = "Field2d";

  // path is to the table containing ".type", excluding the trailing /
  explicit NTField2DModel(std::string_view path);
  NTField2DModel(NT_Inst inst, std::string_view path);
  ~NTField2DModel() override;

  const char* GetPath() const { return m_path.c_str(); }
  const char* GetName() const { return m_nameValue.c_str(); }

  void Update() override;
  bool Exists() override;
  bool IsReadOnly() override;

  FieldObjectModel* AddFieldObject(std::string_view name) override;
  void RemoveFieldObject(std::string_view name) override;
  void ForEachFieldObject(
      wpi::function_ref<void(FieldObjectModel& model, std::string_view name)>
          func) override;

 private:
  NetworkTablesHelper m_nt;
  std::string m_path;
  NT_Entry m_name;
  std::string m_nameValue;

  class ObjectModel;
  using Objects = std::vector<std::unique_ptr<ObjectModel>>;
  Objects m_objects;

  std::pair<Objects::iterator, bool> Find(std::string_view fullName);
};

}  // namespace glass
