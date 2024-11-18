// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.geometry.proto;

import org.wpilib.math.geometry.Quaternion;
import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.math.proto.Geometry3D.ProtobufRotation3d;
import org.wpilib.util.protobuf.Protobuf;
import us.hebi.quickbuf.Descriptors.Descriptor;

public class Rotation3dProto implements Protobuf<Rotation3d, ProtobufRotation3d> {
  @Override
  public Class<Rotation3d> getTypeClass() {
    return Rotation3d.class;
  }

  @Override
  public Descriptor getDescriptor() {
    return ProtobufRotation3d.getDescriptor();
  }

  @Override
  public ProtobufRotation3d createMessage() {
    return ProtobufRotation3d.newInstance();
  }

  @Override
  public Rotation3d unpack(ProtobufRotation3d msg) {
    return new Rotation3d(Quaternion.proto.unpack(msg.getQ()));
  }

  @Override
  public void pack(ProtobufRotation3d msg, Rotation3d value) {
    Quaternion.proto.pack(msg.getMutableQ(), value.getQuaternion());
  }

  @Override
  public boolean isImmutable() {
    return true;
  }
}
