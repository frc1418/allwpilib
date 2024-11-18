// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package org.wpilib.units.measure;

import static org.wpilib.units.Units.*;
import org.wpilib.units.*;
import org.wpilib.units.mutable.MutableMeasureBase;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public final class MutLinearVelocity
  extends MutableMeasureBase<LinearVelocityUnit, LinearVelocity, MutLinearVelocity>
  implements LinearVelocity {
  public MutLinearVelocity(double magnitude, double baseUnitMagnitude, LinearVelocityUnit unit) {
    super(magnitude, baseUnitMagnitude, unit);
  }

  @Override
  public LinearVelocity copy() {
    return new ImmutableLinearVelocity(magnitude(), baseUnitMagnitude(), unit());
  }
}
