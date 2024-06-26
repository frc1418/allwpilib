// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units.immutable;

import edu.wpi.first.units.Energy;
import edu.wpi.first.units.EnergyUnit;

public record ImmutableEnergy(double magnitude, double baseUnitMagnitude, EnergyUnit unit)
    implements Energy {
  @Override
  public Energy copy() {
    return this;
  }
}
