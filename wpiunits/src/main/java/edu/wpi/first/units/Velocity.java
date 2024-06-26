// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

public interface Velocity<D extends Unit> extends Measure<VelocityUnit<D>> {
  @Override
  Velocity<D> copy();

  default double in(VelocityUnit<? extends D> otherUnit) {
    return otherUnit.fromBaseUnits(baseUnitMagnitude());
  }
}
