// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/SymbolExports.h>
#include <wpi/protobuf/Protobuf.h>

#include "frc/geometry/Rotation2d.h"
#include "pb.h"

template <>
struct WPILIB_DLLEXPORT wpi::Protobuf<frc::Rotation2d> {
  static google::protobuf::Message* New(google::protobuf::Arena* arena);
  static frc::Rotation2d Unpack(const google::protobuf::Message& msg);
  static void Pack(google::protobuf::Message* msg,
                   const frc::Rotation2d& value);

  static const pb_msgdesc_t* Message();
  static std::optional<frc::Rotation2d> Unpack(wpi::ProtoInputStream& stream);
  static bool Pack(wpi::ProtoOutputStream& stream, const frc::Rotation2d& value);
};
