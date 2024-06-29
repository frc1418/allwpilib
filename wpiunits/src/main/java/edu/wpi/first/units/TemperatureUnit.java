// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

import edu.wpi.first.units.measure.ImmutableTemperature;
import edu.wpi.first.units.measure.MutTemperature;
import edu.wpi.first.units.measure.Temperature;

/**
 * Unit of temperature dimension.
 *
 * <p>This is the base type for units of temperature dimension. It is also used to specify the
 * dimension for {@link Measure}: <code>Measure&lt;TemperatureUnit&gt;</code>.
 *
 * <p>Actual units (such as {@link Units#Celsius} and {@link Units#Fahrenheit}) can be found in the
 * {@link Units} class.
 */
public final class TemperatureUnit extends Unit {
  TemperatureUnit(
      TemperatureUnit baseUnit,
      UnaryFunction toBaseConverter,
      UnaryFunction fromBaseConverter,
      String name,
      String symbol) {
    super(baseUnit, toBaseConverter, fromBaseConverter, name, symbol);
  }

  @Override
  public Temperature of(double magnitude) {
    return new ImmutableTemperature(magnitude, toBaseUnits(magnitude), this);
  }

  @Override
  public Temperature ofBaseUnits(double baseUnitMagnitude) {
    return new ImmutableTemperature(fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, this);
  }

  @Override
  public MutTemperature mutable(double initialMagnitude) {
    return new MutTemperature(initialMagnitude, toBaseUnits(initialMagnitude), this);
  }

  @Override
  public VelocityUnit<TemperatureUnit> per(TimeUnit period) {
    return VelocityUnit.combine(this, period);
  }

  /**
   * Converts a measurement value in terms of another temperature unit to this unit.
   *
   * @param magnitude the magnitude of the measurement in terms of the other temperature unit
   * @param otherUnit the other temperature unit
   * @return the value of the measurement in terms of this unit
   */
  public double convertFrom(double magnitude, TemperatureUnit otherUnit) {
    return fromBaseUnits(otherUnit.toBaseUnits(magnitude));
  }
}
