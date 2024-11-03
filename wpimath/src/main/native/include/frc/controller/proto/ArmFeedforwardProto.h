// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/SymbolExports.h>
#include <wpi/protobuf/Protobuf.h>

#include "wpimath/protobuf/controller.npb.h"
#include "frc/controller/ArmFeedforward.h"
#include "pb.h"

template <>
struct WPILIB_DLLEXPORT wpi::Protobuf<frc::ArmFeedforward> {
  using MessageStruct = wpi_proto_ProtobufArmFeedforward;
  using InputStream = wpi::ProtoInputStream<frc::ArmFeedforward>;
  using OutputStream = wpi::ProtoOutputStream<frc::ArmFeedforward>;
  static std::optional<frc::ArmFeedforward> Unpack(InputStream& stream);
  static bool Pack(OutputStream& stream, const frc::ArmFeedforward& value);
};
