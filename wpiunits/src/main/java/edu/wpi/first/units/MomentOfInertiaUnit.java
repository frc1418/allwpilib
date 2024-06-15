// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

public class MomentOfInertiaUnit extends Per<AngularMomentumUnit, AngularVelocityUnit> {
  private static final CombinatoryUnitCache<
          AngularMomentumUnit, AngularVelocityUnit, MomentOfInertiaUnit>
      cache = new CombinatoryUnitCache<>(MomentOfInertiaUnit::new);

  protected MomentOfInertiaUnit(AngularMomentumUnit numerator, AngularVelocityUnit denominator) {
    super(numerator, denominator);
  }

  MomentOfInertiaUnit(
      Per<AngularMomentumUnit, AngularVelocityUnit> baseUnit,
      UnaryFunction toBaseConverter,
      UnaryFunction fromBaseConverter,
      String name,
      String symbol) {
    super(baseUnit, toBaseConverter, fromBaseConverter, name, symbol);
  }

  public static MomentOfInertiaUnit combine(
      AngularMomentumUnit momentumUnit, AngularVelocityUnit velocityUnit) {
    return cache.combine(momentumUnit, velocityUnit);
  }
}
