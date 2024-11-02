// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;
import edu.wpi.first.units.mutable.MutableMeasureBase;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public final class MutResistance
  extends MutableMeasureBase<ResistanceUnit, Resistance, MutResistance>
  implements Resistance {
  public MutResistance(double magnitude, double baseUnitMagnitude, ResistanceUnit unit) {
    super(magnitude, baseUnitMagnitude, unit);
  }

  @Override
  public Resistance copy() {
    return new ImmutableResistance(magnitude(), baseUnitMagnitude(), unit());
  }
}
