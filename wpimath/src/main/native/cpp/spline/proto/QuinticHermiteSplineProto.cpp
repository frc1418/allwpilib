// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/spline/proto/QuinticHermiteSplineProto.h"

#include <wpi/protobuf/ProtobufCallbacks.h>

#include "spline.npb.h"

const pb_msgdesc_t* wpi::Protobuf<frc::QuinticHermiteSpline>::Message() {
  return get_wpi_proto_ProtobufQuinticHermiteSpline_msg();
}

std::optional<frc::QuinticHermiteSpline>
wpi::Protobuf<frc::QuinticHermiteSpline>::Unpack(wpi::ProtoInputStream& stream) {
  wpi::WpiArrayUnpackCallback<double, 3> xInitial;
  wpi::WpiArrayUnpackCallback<double, 3> xFinal;
  wpi::WpiArrayUnpackCallback<double, 3> yInitial;
  wpi::WpiArrayUnpackCallback<double, 3> yFinal;
  wpi_proto_ProtobufQuinticHermiteSpline msg{
      .x_initial = xInitial.Callback(),
      .x_final = xFinal.Callback(),
      .y_initial = yInitial.Callback(),
      .y_final = yFinal.Callback(),
  };
  if (!stream.DecodeNoInit(msg)) {
    return {};
  }

  if (!xInitial.IsFull() || !yInitial.IsFull() || !xFinal.IsFull() ||
      !yFinal.IsFull()) {
    return {};
  }

  return frc::QuinticHermiteSpline{
      xInitial.Array(),
      xFinal.Array(),
      yInitial.Array(),
      yFinal.Array(),
  };
}

bool wpi::Protobuf<frc::QuinticHermiteSpline>::Pack(
    wpi::ProtoOutputStream& stream, const frc::QuinticHermiteSpline& value) {
  wpi::PackCallback<double> xInitial{value.GetInitialControlVector().x};
  wpi::PackCallback<double> xFinal{value.GetFinalControlVector().x};
  wpi::PackCallback<double> yInitial{value.GetInitialControlVector().y};
  wpi::PackCallback<double> yFinal{value.GetFinalControlVector().y};
  wpi_proto_ProtobufQuinticHermiteSpline msg{
      .x_initial = xInitial.Callback(),
      .x_final = xFinal.Callback(),
      .y_initial = yInitial.Callback(),
      .y_final = yFinal.Callback(),
  };
  return stream.Encode(msg);
}
