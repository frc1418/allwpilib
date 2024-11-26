// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.system.plant.struct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.system.plant.DCMotor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;

class DCMotorStructTest {
  private static final DCMotor DATA = new DCMotor(1.91, 19.1, 1.74, 1.74, 22.9);

  @Test
  void testRoundtrip() {
    ByteBuffer buffer = ByteBuffer.allocate(DCMotor.struct.getSize());
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    DCMotor.struct.pack(buffer, DATA);
    buffer.rewind();

    DCMotor data = DCMotor.struct.unpack(buffer);
    assertEquals(DATA.nominalVoltage.baseUnitMagnitude(), data.nominalVoltage.baseUnitMagnitude());
    assertEquals(DATA.stallTorque.baseUnitMagnitude(), data.stallTorque.baseUnitMagnitude());
    assertEquals(DATA.stallCurrent.baseUnitMagnitude(), data.stallCurrent.baseUnitMagnitude());
    assertEquals(DATA.freeCurrent.baseUnitMagnitude(), data.freeCurrent.baseUnitMagnitude());
    assertEquals(DATA.freeSpeed.baseUnitMagnitude(), data.freeSpeed.baseUnitMagnitude());
    assertEquals(
        DATA.internalResistance.baseUnitMagnitude(), data.internalResistance.baseUnitMagnitude());
    assertEquals(DATA.kv.baseUnitMagnitude(), data.kv.baseUnitMagnitude());
    assertEquals(DATA.kt.baseUnitMagnitude(), data.kt.baseUnitMagnitude());
  }
}
