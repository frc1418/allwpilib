// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;
import edu.wpi.first.units.mutable.MutableMeasureBase;

@SuppressWarnings({"unchecked", "cast"})
public final class MutPer<Dividend extends Unit, Divisor extends Unit>
  extends MutableMeasureBase<PerUnit<Dividend, Divisor>, Per<Dividend, Divisor>, MutPer<Dividend, Divisor>>
  implements Per<Dividend, Divisor> {

  public MutPer(double magnitude, double baseUnitMagnitude, PerUnit<Dividend, Divisor> unit) {
    super(magnitude, baseUnitMagnitude, unit);
  }

  @Override
  public Per<Dividend, Divisor> copy() {
    return new ImmutablePer<Dividend, Divisor>(magnitude, baseUnitMagnitude, unit);
  }
}
