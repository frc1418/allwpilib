// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/controller/proto/ElevatorFeedforwardProto.h"

#include "controller.pb.h"

google::protobuf::Message* wpi::Protobuf<frc::ElevatorFeedforward>::New(
    google::protobuf::Arena* arena) {
  return google::protobuf::Arena::CreateMessage<
      wpi::proto::ProtobufElevatorFeedforward>(arena);
}

frc::ElevatorFeedforward wpi::Protobuf<frc::ElevatorFeedforward>::Unpack(
    const google::protobuf::Message& msg) {
  auto m = static_cast<const wpi::proto::ProtobufElevatorFeedforward*>(&msg);
  return frc::ElevatorFeedforward{
      m->ks(),
      m->kg(),
      m->kv(),
      m->ka(),
  };
}

void wpi::Protobuf<frc::ElevatorFeedforward>::Pack(
    google::protobuf::Message* msg, const frc::ElevatorFeedforward& value) {
  auto m = static_cast<wpi::proto::ProtobufElevatorFeedforward*>(msg);
  m->set_ks(value.ks.value());
  m->set_kg(value.kg.value());
  m->set_kv(value.kv.value());
  m->set_ka(value.ka.value());
}
