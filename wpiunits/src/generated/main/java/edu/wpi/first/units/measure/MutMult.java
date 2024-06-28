// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;
import edu.wpi.first.units.mutable.MutableMeasureBase;

@SuppressWarnings({"unchecked", "cast"})
public final class MutMult<A extends Unit, B extends Unit>
  extends MutableMeasureBase<MultUnit<A, B>, Mult<A, B>, MutMult<A, B>>
  implements Mult<A, B> {

  public MutMult(double magnitude, double baseUnitMagnitude, MultUnit<A, B> unit) {
    super(magnitude, baseUnitMagnitude, unit);
  }

  @Override
  public Mult<A, B> copy() {
    return new ImmutableMult<A, B>(magnitude, baseUnitMagnitude, unit);
  }
}
