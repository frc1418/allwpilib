// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <fmt/format.h>
#include <wpi/ct_string.h>
#include <wpi/struct/Struct.h>

#include "frc/struct/MatrixStruct.h"
#include "frc/system/LinearSystem.h"

template <int States, int Inputs, int Outputs>
struct wpi::Struct<frc::LinearSystem<States, Inputs, Outputs>> {
  static constexpr ct_string kTypeString = wpi::Concat(
      "struct:LinearSystem__"_ct_string, wpi::NumToCtString<States>(),
      "_"_ct_string, wpi::NumToCtString<Inputs>(), "_"_ct_string,
      wpi::NumToCtString<Outputs>());
  static constexpr std::string_view GetTypeString() { return kTypeString; }
  static constexpr size_t GetSize() {
    return wpi::Struct<frc::Matrixd<States, States>>::GetSize() +
           wpi::Struct<frc::Matrixd<States, Inputs>>::GetSize() +
           wpi::Struct<frc::Matrixd<Outputs, States>>::GetSize() +
           wpi::Struct<frc::Matrixd<Outputs, Inputs>>::GetSize();
  }
  static constexpr ct_string kSchema = wpi::Concat(
      wpi::Struct<frc::Matrixd<States, States>>::kTypeName, " a;"_ct_string,
      wpi::Struct<frc::Matrixd<States, Inputs>>::kTypeName, " b;"_ct_string,
      wpi::Struct<frc::Matrixd<Outputs, States>>::kTypeName, " c;"_ct_string,
      wpi::Struct<frc::Matrixd<Outputs, Inputs>>::kTypeName, " d"_ct_string);
  static constexpr std::string_view GetSchema() { return kSchema; }

  static frc::LinearSystem<States, Inputs, Outputs> Unpack(
      std::span<const uint8_t> data);
  static void Pack(std::span<uint8_t> data,
                   const frc::LinearSystem<States, Inputs, Outputs>& value);
};

static_assert(wpi::StructSerializable<frc::LinearSystem<4, 3, 2>>);
static_assert(wpi::StructSerializable<frc::LinearSystem<2, 3, 4>>);

#include "frc/system/struct/LinearSystemStruct.inc"
