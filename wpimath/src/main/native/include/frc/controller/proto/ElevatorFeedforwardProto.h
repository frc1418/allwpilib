// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <google/protobuf/arena.h>
#include <google/protobuf/message.h>
#include <wpi/SymbolExports.h>
#include <wpi/protobuf/Protobuf.h>

#include "frc/controller/ElevatorFeedforward.h"

template <>
struct WPILIB_DLLEXPORT wpi::Protobuf<frc::ElevatorFeedforward> {
  static google::protobuf::Message* New(google::protobuf::Arena* arena);
  static frc::ElevatorFeedforward Unpack(const google::protobuf::Message& msg);
  static void Pack(google::protobuf::Message* msg,
                   const frc::ElevatorFeedforward& value);
};
