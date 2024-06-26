// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

import edu.wpi.first.units.immutable.ImmutableVelocity;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.units.mutable.MutVelocity;

/**
 * Unit of velocity dimension that is a combination of a distance unit (numerator) and a time unit
 * (denominator).
 *
 * <p>This is the base type for units of velocity dimension. It is also used in combination with a
 * distance dimension to specify the dimension for {@link Measure}. For example: <code>
 * Measure&lt;VelocityUnit&lt;DistanceUnit&gt;&gt;</code>.
 *
 * <p>Actual units (such as {@link Units#MetersPerSecond} and {@link Units#RPM}) can be found in the
 * {@link Units} class.
 *
 * @param <D> the distance unit, such as {@link AngleUnit} or {@link DistanceUnit}
 */
public class VelocityUnit<D extends Unit> extends PerUnit<D, TimeUnit> {
  @SuppressWarnings("rawtypes")
  private static final CombinatoryUnitCache<Unit, TimeUnit, VelocityUnit> cache =
      new CombinatoryUnitCache<>(VelocityUnit::new);

  VelocityUnit(D unit, TimeUnit period) {
    super(unit, period);
  }

  VelocityUnit(
      VelocityUnit<D> baseUnit,
      UnaryFunction toBaseConverter,
      UnaryFunction fromBaseConverter,
      String name,
      String symbol) {
    super(baseUnit, toBaseConverter, fromBaseConverter, name, symbol);
  }

  @SuppressWarnings("unchecked")
  public static <D extends Unit> VelocityUnit<D> combine(D unit, TimeUnit period) {
    return cache.combine(unit, period);
  }

  /**
   * Gets the major unit being measured (eg Meters for Meters per Second).
   *
   * @return the major unit
   */
  public D getUnit() {
    return numerator();
  }

  /**
   * Gets the period unit of the velocity, eg Seconds or Milliseconds.
   *
   * @return the period unit
   */
  public TimeUnit getPeriod() {
    return denominator();
  }

  public AccelerationUnit<D> per(TimeUnit period) {
    return AccelerationUnit.combine(this, period);
  }

  public Velocity<D> of(double magnitude) {
    return new ImmutableVelocity<>(magnitude, toBaseUnits(magnitude), this);
  }

  @Override
  public Velocity<D> ofBaseUnits(double baseUnitMagnitude) {
    return new ImmutableVelocity<>(fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, this);
  }

  public MutableMeasure<VelocityUnit<D>, ?, ?> mutable(double initialMagnitude) {
    return new MutVelocity<>(initialMagnitude, toBaseUnits(initialMagnitude), this);
  }

  public double convertFrom(double magnitude, VelocityUnit<? extends D> otherUnit) {
    return fromBaseUnits(otherUnit.toBaseUnits(magnitude));
  }
}
