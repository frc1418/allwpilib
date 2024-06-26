// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

import edu.wpi.first.units.immutable.ImmutableAngularVelocity;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.mutable.MutAngularVelocity;

public class AngularVelocityUnit extends PerUnit<AngleUnit, TimeUnit> {
  private static final CombinatoryUnitCache<AngleUnit, TimeUnit, AngularVelocityUnit> cache =
      new CombinatoryUnitCache<>(AngularVelocityUnit::new);

  protected AngularVelocityUnit(AngleUnit numerator, TimeUnit denominator) {
    super(numerator, denominator);
  }

  AngularVelocityUnit(
      AngularVelocityUnit baseUnit,
      UnaryFunction toBaseConverter,
      UnaryFunction fromBaseConverter,
      String name,
      String symbol) {
    super(baseUnit, toBaseConverter, fromBaseConverter, name, symbol);
  }

  public static AngularVelocityUnit combine(AngleUnit angle, TimeUnit time) {
    return cache.combine(angle, time);
  }

  @Override
  public AngularVelocity of(double magnitude) {
    return new ImmutableAngularVelocity(magnitude, toBaseUnits(magnitude), this);
  }

  @Override
  public AngularVelocity ofBaseUnits(double baseUnitMagnitude) {
    return new ImmutableAngularVelocity(fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, this);
  }

  @Override
  public MutAngularVelocity mutable(double initialMagnitude) {
    return new MutAngularVelocity(initialMagnitude, toBaseUnits(initialMagnitude), this);
  }

  public AngularAccelerationUnit per(TimeUnit period) {
    return AngularAccelerationUnit.combine(this, period);
  }
}
