// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.kinematics;

import org.wpilib.math.kinematics.proto.MecanumDriveMotorVoltagesProto;
import org.wpilib.math.kinematics.struct.MecanumDriveMotorVoltagesStruct;
import org.wpilib.util.protobuf.ProtobufSerializable;
import org.wpilib.util.struct.StructSerializable;

/** Represents the motor voltages for a mecanum drive drivetrain. */
public class MecanumDriveMotorVoltages implements ProtobufSerializable, StructSerializable {
  /** Voltage of the front left motor. */
  public double frontLeftVoltage;

  /** Voltage of the front right motor. */
  public double frontRightVoltage;

  /** Voltage of the rear left motor. */
  public double rearLeftVoltage;

  /** Voltage of the rear right motor. */
  public double rearRightVoltage;

  /** Constructs a MecanumDriveMotorVoltages with zeros for all member fields. */
  public MecanumDriveMotorVoltages() {}

  /**
   * Constructs a MecanumDriveMotorVoltages.
   *
   * @param frontLeftVoltage Voltage of the front left motor.
   * @param frontRightVoltage Voltage of the front right motor.
   * @param rearLeftVoltage Voltage of the rear left motor.
   * @param rearRightVoltage Voltage of the rear right motor.
   */
  public MecanumDriveMotorVoltages(
      double frontLeftVoltage,
      double frontRightVoltage,
      double rearLeftVoltage,
      double rearRightVoltage) {
    this.frontLeftVoltage = frontLeftVoltage;
    this.frontRightVoltage = frontRightVoltage;
    this.rearLeftVoltage = rearLeftVoltage;
    this.rearRightVoltage = rearRightVoltage;
  }

  @Override
  public String toString() {
    return String.format(
        "MecanumDriveMotorVoltages(Front Left: %.2f V, Front Right: %.2f V, "
            + "Rear Left: %.2f V, Rear Right: %.2f V)",
        frontLeftVoltage, frontRightVoltage, rearLeftVoltage, rearRightVoltage);
  }

  /** MecanumDriveMotorVoltages struct for serialization. */
  public static final MecanumDriveMotorVoltagesStruct struct =
      new MecanumDriveMotorVoltagesStruct();

  /** MecanumDriveMotorVoltages protobuf for serialization. */
  public static final MecanumDriveMotorVoltagesProto proto = new MecanumDriveMotorVoltagesProto();
}
