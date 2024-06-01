// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry.struct;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.util.struct.Struct;
import java.nio.ByteBuffer;

public class Transform2dStruct implements Struct<Transform2d> {
  @Override
  public Class<Transform2d> getTypeClass() {
    return Transform2d.class;
  }

  @Override
  public String getTypeString() {
    return "struct:Transform2d";
  }

  @Override
  public int getSize() {
    return Translation2d.struct.getSize() + Rotation2d.struct.getSize();
  }

  @Override
  public String getSchema() {
    return "Translation2d translation;Rotation2d rotation";
  }

  @Override
  public Struct<?>[] getNested() {
    return new Struct<?>[] {Translation2d.struct, Rotation2d.struct};
  }

  @Override
  public Transform2d unpack(ByteBuffer bb) {
    Translation2d translation = Translation2d.struct.unpack(bb);
    Rotation2d rotation = Rotation2d.struct.unpack(bb);
    return new Transform2d(translation, rotation);
  }

  @Override
  public void pack(ByteBuffer bb, Transform2d value) {
    Translation2d.struct.pack(bb, value.getTranslation());
    Rotation2d.struct.pack(bb, value.getRotation());
  }

  @Override
  public boolean isCloneable() {
    return true;
  }

  @Override
  public Transform2d clone(Transform2d obj) {
    return obj.clone();
  }
}
