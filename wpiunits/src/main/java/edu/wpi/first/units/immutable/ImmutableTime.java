// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units.immutable;

import edu.wpi.first.units.TimeUnit;
import edu.wpi.first.units.measure.Time;

public record ImmutableTime(double magnitude, double baseUnitMagnitude, TimeUnit unit)
    implements Time {
  @Override
  public Time copy() {
    return this;
  }

  @Override
  public String toString() {
    return toShortString();
  }
}
