// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units.immutable;

import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.VelocityUnit;

public record ImmutableVelocity<D extends Unit>(
    double magnitude, double baseUnitMagnitude, VelocityUnit<D> unit) implements Velocity<D> {
  @Override
  public Velocity<D> copy() {
    return this;
  }
}
