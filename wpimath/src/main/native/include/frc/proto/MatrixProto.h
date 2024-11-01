// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <stdexcept>

#include <fmt/format.h>
#include <wpi/protobuf/Protobuf.h>
#include <wpi/protobuf/ProtobufCallbacks.h>

#include "frc/EigenCore.h"
#include "wpimath.npb.h"

template <int Rows, int Cols, int Options, int MaxRows, int MaxCols>
  requires(Cols != 1)
struct wpi::Protobuf<frc::Matrixd<Rows, Cols, Options, MaxRows, MaxCols>> {
  static const pb_msgdesc_t* Message() {
    return get_wpi_proto_ProtobufMatrix_msg();
  }

  static std::optional<frc::Matrixd<Rows, Cols, Options, MaxRows, MaxCols>>
  Unpack(wpi::ProtoInputStream& stream) {
    using UnpackType =
        std::conditional_t<Rows * Cols * sizeof(double) < 256,
                           wpi::UnpackCallback<double, Rows * Cols>,
                           wpi::StdVectorUnpackCallback<double, Rows * Cols>>;

    UnpackType data;
    data.Vec().reserve(Rows * Cols);
    data.SetLimits(wpi::DecodeLimits::Fail);
    wpi_proto_ProtobufMatrix msg;
    msg.data = data.Callback();
    if (!stream.DecodeNoInit(msg)) {
      return {};
    }

    if (msg.num_rows != Rows || msg.num_cols != Cols) {
      return {};
    }

    auto items = data.Items();

    if (items.size() != Rows * Cols) {
      return {};
    }

    frc::Matrixd<Rows, Cols, Options, MaxRows, MaxCols> mat;
    for (int i = 0; i < Rows * Cols; i++) {
      mat(i) = items[i];
    }

    return mat;
  }

  static bool Pack(
      wpi::ProtoOutputStream& stream,
      const frc::Matrixd<Rows, Cols, Options, MaxRows, MaxCols>& value) {
    std::span<const double> dataSpan{value.data(), static_cast<size_t>(Rows * Cols)};
    wpi::PackCallback<double> data{dataSpan};
    wpi_proto_ProtobufMatrix msg{
        .num_rows = Rows,
        .num_cols = Cols,
        .data = data.Callback(),
    };
    return stream.Encode(msg);
  }
};
