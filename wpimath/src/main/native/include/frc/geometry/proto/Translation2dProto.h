// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/SymbolExports.h>
#include <wpi/protobuf/Protobuf.h>

#include "frc/geometry/Translation2d.h"
#include "pb.h"

template <>
struct WPILIB_DLLEXPORT wpi::Protobuf<frc::Translation2d> {
  static google::protobuf::Message* New(google::protobuf::Arena* arena);
  static frc::Translation2d Unpack(const google::protobuf::Message& msg);
  static void Pack(google::protobuf::Message* msg,
                   const frc::Translation2d& value);

  static std::optional<frc::Translation2d> Unpack(pb_istream_t& stream);
  static bool Pack(pb_ostream_t& stream, const frc::Translation2d& value, bool is_subobject = false);
};
