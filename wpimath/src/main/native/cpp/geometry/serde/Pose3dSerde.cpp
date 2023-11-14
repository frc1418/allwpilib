// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/geometry/serde/Pose3dSerde.h"

#include "geometry3d.pb.h"

namespace {
constexpr size_t kTranslationOff = 0;
constexpr size_t kRotationOff =
    kTranslationOff + wpi::Struct<frc::Translation3d>::kSize;
}  // namespace

using StructType = wpi::Struct<frc::Pose3d>;

frc::Pose3d StructType::Unpack(std::span<const uint8_t, kSize> data) {
  return frc::Pose3d{
      wpi::UnpackStruct<frc::Translation3d, kTranslationOff>(data),
      wpi::UnpackStruct<frc::Rotation3d, kRotationOff>(data),
  };
}

void StructType::Pack(std::span<uint8_t, kSize> data,
                      const frc::Pose3d& value) {
  wpi::PackStruct<kTranslationOff>(data, value.Translation());
  wpi::PackStruct<kRotationOff>(data, value.Rotation());
}

void StructType::ForEachNested(
    std::invocable<std::string_view, std::string_view> auto fn) {
  wpi::ForEachStructSchema<frc::Translation3d>(fn);
  wpi::ForEachStructSchema<frc::Rotation3d>(fn);
}

google::protobuf::Message* wpi::Protobuf<frc::Pose3d>::New(
    google::protobuf::Arena* arena) {
  return google::protobuf::Arena::CreateMessage<wpi::proto::ProtobufPose3d>(
      arena);
}

frc::Pose3d wpi::Protobuf<frc::Pose3d>::Unpack(
    const google::protobuf::Message& msg) {
  auto m = static_cast<const wpi::proto::ProtobufPose3d*>(&msg);
  return frc::Pose3d{
      wpi::UnpackProtobuf<frc::Translation3d>(m->translation()),
      wpi::UnpackProtobuf<frc::Rotation3d>(m->rotation()),
  };
}

void wpi::Protobuf<frc::Pose3d>::Pack(google::protobuf::Message* msg,
                                      const frc::Pose3d& value) {
  auto m = static_cast<wpi::proto::ProtobufPose3d*>(msg);
  wpi::PackProtobuf(m->mutable_translation(), value.Translation());
  wpi::PackProtobuf(m->mutable_rotation(), value.Rotation());
}
