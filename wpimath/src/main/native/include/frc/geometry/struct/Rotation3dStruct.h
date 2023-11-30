// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/SymbolExports.h>
#include <wpi/struct/Struct.h>

#include "frc/geometry/Rotation3d.h"

template <>
struct WPILIB_DLLEXPORT wpi::Struct<frc::Rotation3d> {
  static constexpr std::string_view kTypeString = "struct:Rotation3d";
  static constexpr size_t kSize = wpi::Struct<frc::Quaternion>::kSize;
  static constexpr std::string_view kSchema = "Quaternion q";

  static frc::Rotation3d Unpack(std::span<const uint8_t, kSize> data);
  static void Pack(std::span<uint8_t, kSize> data,
                   const frc::Rotation3d& value);
  static void ForEachNested(
      std::invocable<std::string_view, std::string_view> auto fn);
};

static_assert(wpi::HasNestedStruct<frc::Rotation3d>);
