// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units.mutable;

import edu.wpi.first.units.Temperature;
import edu.wpi.first.units.TemperatureUnit;
import edu.wpi.first.units.immutable.ImmutableTemperature;

public final class MutTemperature
    extends MutableMeasureBase<TemperatureUnit, Temperature, MutTemperature>
    implements Temperature {
  public MutTemperature(double magnitude, double baseUnitMagnitude, TemperatureUnit unit) {
    super(magnitude, baseUnitMagnitude, unit);
  }

  @Override
  public Temperature copy() {
    return new ImmutableTemperature(magnitude, baseUnitMagnitude, unit);
  }
}
