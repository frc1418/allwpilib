// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/controller/proto/DifferentialDriveFeedforwardProto.h"

#include "controller.npb.h"

const pb_msgdesc_t*
wpi::Protobuf<frc::DifferentialDriveFeedforward>::Message() {
  return get_wpi_proto_ProtobufDifferentialDriveFeedforward_msg();
}

std::optional<frc::DifferentialDriveFeedforward> wpi::Protobuf<
    frc::DifferentialDriveFeedforward>::Unpack(wpi::ProtoInputStream& stream) {
  wpi_proto_ProtobufDifferentialDriveFeedforward msg;
  if (!stream.Decode(msg)) {
    return {};
  }

  return frc::DifferentialDriveFeedforward{
      decltype(1_V / 1_mps){msg.kv_linear},
      decltype(1_V / 1_mps_sq){msg.ka_linear},
      decltype(1_V / 1_mps){msg.kv_angular},
      decltype(1_V / 1_mps_sq){msg.ka_angular},
  };
}

bool wpi::Protobuf<frc::DifferentialDriveFeedforward>::Pack(
    wpi::ProtoOutputStream& stream,
    const frc::DifferentialDriveFeedforward& value) {
  wpi_proto_ProtobufDifferentialDriveFeedforward msg{
      .kv_linear = value.m_kVLinear.value(),
      .ka_linear = value.m_kALinear.value(),
      .kv_angular = value.m_kVAngular.value(),
      .ka_angular = value.m_kAAngular.value(),
  };
  return stream.Encode(msg);
}
