// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package org.wpilib.units.measure;

import static org.wpilib.units.Units.*;
import org.wpilib.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public record ImmutablePower(double magnitude, double baseUnitMagnitude, PowerUnit unit) implements Power {
  @Override
  public Power copy() {
    return this;
  }

  @Override
  public String toString() {
    return toShortString();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Measure<?> m && isEquivalent(m);
  }
}
