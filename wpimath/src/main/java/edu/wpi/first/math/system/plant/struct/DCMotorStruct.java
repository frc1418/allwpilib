// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.system.plant.struct;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.util.struct.Struct;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.NewtonMeters;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Volts;

import java.nio.ByteBuffer;

public class DCMotorStruct implements Struct<DCMotor> {
  @Override
  public Class<DCMotor> getTypeClass() {
    return DCMotor.class;
  }

  @Override
  public String getTypeName() {
    return "DCMotor";
  }

  @Override
  public int getSize() {
    return kSizeDouble * 5;
  }

  @Override
  public String getSchema() {
    return "double nominal_voltage;double stall_torque;double stall_current;"
        + "double free_current;double free_speed";
  }

  @Override
  public DCMotor unpack(ByteBuffer bb) {
    double nominalVoltage = bb.getDouble();
    double stallTorque = bb.getDouble();
    double stallCurrent = bb.getDouble();
    double freeCurrent = bb.getDouble();
    double freeSpeed = bb.getDouble();
    return new DCMotor(nominalVoltage, stallTorque, stallCurrent, freeCurrent, freeSpeed);
  }

  @Override
  public void pack(ByteBuffer bb, DCMotor value) {
    bb.putDouble(value.nominalVoltage.in(Volts));
    bb.putDouble(value.stallTorque.in(NewtonMeters));
    bb.putDouble(value.stallCurrent.in(Amps));
    bb.putDouble(value.freeCurrent.in(Amps));
    bb.putDouble(value.freeSpeed.in(RadiansPerSecond));
  }
}
