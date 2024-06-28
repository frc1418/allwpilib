// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public record ImmutableFrequency(double magnitude, double baseUnitMagnitude, FrequencyUnit unit) implements Frequency {
  @Override
  public Frequency copy() {
    return this;
  }

  @Override
  public String toString() {
    return toShortString();
  }
}
