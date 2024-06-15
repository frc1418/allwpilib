// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.units;

/**
 * Unit of electric current dimension.
 *
 * <p>This is the base type for units of current dimension. It is also used to specify the dimension
 * for {@link Measure}: <code>Measure&lt;CurrentUnit&gt;</code>.
 *
 * <p>Actual units (such as {@link Units#Amps} and {@link Units#Milliamps}) can be found in the
 * {@link Units} class.
 */
public class CurrentUnit extends Unit {
  CurrentUnit(CurrentUnit baseUnit, double baseUnitEquivalent, String name, String symbol) {
    super(baseUnit, baseUnitEquivalent, name, symbol);
  }

  CurrentUnit(
      CurrentUnit baseUnit,
      UnaryFunction toBaseConverter,
      UnaryFunction fromBaseConverter,
      String name,
      String symbol) {
    super(baseUnit, toBaseConverter, fromBaseConverter, name, symbol);
  }

  /**
   * Constructs a unit of power equivalent to this unit of electrical current multiplied by another
   * unit of voltage. For example, {@code Amps.times(Volts)} will return a unit of power equivalent
   * to one Watt; {@code Amps.times(Millivolts)} will return a unit of power equivalent to a
   * milliwatt, and so on.
   *
   * @param voltage the voltage unit to multiply by
   * @param name the name of the resulting unit of power
   * @param symbol the symbol used to represent the unit of power
   * @return the power unit
   */
  public PowerUnit times(VoltageUnit voltage, String name, String symbol) {
    return Units.derive(PowerUnit.combine(voltage, this)).named(name).symbol(symbol).make();
  }

  public Current of(double magnitude) {
    return new Current(magnitude, toBaseUnits(magnitude), this);
  }

  public Current ofBaseUnits(double baseUnitMagnitude) {
    return new Current(fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, this);
  }

  public double convertFrom(double magnitude, CurrentUnit otherUnit) {
    return fromBaseUnits(otherUnit.toBaseUnits(magnitude));
  }
}
