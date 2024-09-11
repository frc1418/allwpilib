// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Frequency extends Measure<FrequencyUnit> {
  static  Frequency ofRelativeUnits(double magnitude, FrequencyUnit unit) {
    return new ImmutableFrequency(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Frequency ofBaseUnits(double baseUnitMagnitude, FrequencyUnit unit) {
    return new ImmutableFrequency(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Frequency copy();

  @Override
  default MutFrequency mutableCopy() {
    return new MutFrequency(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  FrequencyUnit unit();

  @Override
  default FrequencyUnit baseUnit() { return (FrequencyUnit) unit().getBaseUnit(); }

  @Override
  default double in(FrequencyUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Frequency unaryMinus() {
    return (Frequency) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default Frequency plus(Measure<? extends FrequencyUnit> other) {
    return (Frequency) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Frequency minus(Measure<? extends FrequencyUnit> other) {
    return (Frequency) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Frequency times(double multiplier) {
    return (Frequency) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Frequency divide(double divisor) {
    return (Frequency) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<FrequencyUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<FrequencyUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<FrequencyUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<FrequencyUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default AngularVelocity times(Angle multiplier) {
    return RadiansPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<FrequencyUnit, AngleUnit> divide(Angle divisor) {
    return (Per<FrequencyUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<FrequencyUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<FrequencyUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<FrequencyUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<FrequencyUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default AngularAcceleration times(AngularVelocity multiplier) {
    return RadiansPerSecondPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<FrequencyUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<FrequencyUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<FrequencyUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, CurrentUnit> divide(Current divisor) {
    return (Per<FrequencyUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Frequency divide(Dimensionless divisor) {
    return (Frequency) Hertz.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Frequency times(Dimensionless multiplier) {
    return (Frequency) Hertz.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default LinearVelocity times(Distance multiplier) {
    return MetersPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<FrequencyUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<FrequencyUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<FrequencyUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<FrequencyUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, ForceUnit> times(Force multiplier) {
    return (Mult<FrequencyUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, ForceUnit> divide(Force divisor) {
    return (Per<FrequencyUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<FrequencyUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(Frequency divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<FrequencyUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<FrequencyUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<FrequencyUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<FrequencyUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<FrequencyUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default LinearAcceleration times(LinearVelocity multiplier) {
    return MetersPerSecondPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<FrequencyUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<FrequencyUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, MassUnit> times(Mass multiplier) {
    return (Mult<FrequencyUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, MassUnit> divide(Mass divisor) {
    return (Per<FrequencyUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<FrequencyUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<FrequencyUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<FrequencyUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<FrequencyUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<FrequencyUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<FrequencyUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, PowerUnit> times(Power multiplier) {
    return (Mult<FrequencyUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, PowerUnit> divide(Power divisor) {
    return (Per<FrequencyUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<FrequencyUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<FrequencyUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Dimensionless times(Time multiplier) {
    return Value.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Velocity<FrequencyUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<FrequencyUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<FrequencyUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<FrequencyUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<FrequencyUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<FrequencyUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<FrequencyUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<FrequencyUnit, VoltageUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<FrequencyUnit, VoltagePerAnglePerTimeUnit> times(VoltagePerAnglePerTime multiplier) {
    return (Mult<FrequencyUnit, VoltagePerAnglePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<FrequencyUnit, VoltagePerAnglePerTimeUnit> divide(VoltagePerAnglePerTime divisor) {
    return (Per<FrequencyUnit, VoltagePerAnglePerTimeUnit>) Measure.super.divide(divisor);
  }
/** Converts this frequency to the time period between cycles. */
default Time asPeriod() { return Seconds.of(1 / baseUnitMagnitude()); }
}
