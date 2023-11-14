// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry.serde;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.proto.Geometry3D.ProtobufTransform3d;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Transform3dSerdeTest {
  private static final Transform3d DATA =
      new Transform3d(new Translation3d(1.91, 2.29, 1.74), new Rotation3d(1.1, 2.2, 3.3));
  private static final byte[] STRUCT_BUFFER = createStructBuffer();

  private static final byte[] createStructBuffer() {
    byte[] bytes = new byte[Transform3d.struct.getSize()];
    ByteBuffer buffer = ByteBuffer.wrap(bytes);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.putDouble(1.91);
    buffer.putDouble(2.29);
    buffer.putDouble(1.74);
    buffer.putDouble(DATA.getRotation().getQuaternion().getW());
    buffer.putDouble(DATA.getRotation().getQuaternion().getX());
    buffer.putDouble(DATA.getRotation().getQuaternion().getY());
    buffer.putDouble(DATA.getRotation().getQuaternion().getZ());
    return bytes;
  }

  @Test
  void testStructPack() {
    ByteBuffer buffer = ByteBuffer.allocate(Transform3d.struct.getSize());
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    Transform3d.struct.pack(buffer, DATA);

    byte[] actual = buffer.array();
    assertArrayEquals(actual, STRUCT_BUFFER);
  }

  @Test
  void testStructUnpack() {
    ByteBuffer buffer = ByteBuffer.wrap(STRUCT_BUFFER);
    buffer.order(ByteOrder.LITTLE_ENDIAN);

    Transform3d data = Transform3d.struct.unpack(buffer);
    assertEquals(DATA.getTranslation(), data.getTranslation());
    assertEquals(DATA.getRotation(), data.getRotation());
  }

  @Test
  void testProtoPack() {
    ProtobufTransform3d proto = Transform3d.proto.createMessage();
    Transform3d.proto.pack(proto, DATA);

    Assertions.assertEquals(
        DATA.getTranslation(), Translation3d.proto.unpack(proto.getTranslation()));
    Assertions.assertEquals(DATA.getRotation(), Rotation3d.proto.unpack(proto.getRotation()));
  }

  @Test
  void testProtoUnpack() {
    ProtobufTransform3d proto = Transform3d.proto.createMessage();
    Translation3d.proto.pack(proto.getMutableTranslation(), DATA.getTranslation());
    Rotation3d.proto.pack(proto.getMutableRotation(), DATA.getRotation());

    Transform3d data = Transform3d.proto.unpack(proto);
    assertEquals(DATA.getTranslation(), data.getTranslation());
    assertEquals(DATA.getRotation(), data.getRotation());
  }
}
