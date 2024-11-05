// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Velocity<D extends Unit> extends Measure<VelocityUnit<D>> {
  static <D extends Unit> Velocity<D> ofRelativeUnits(double magnitude, VelocityUnit<D> unit) {
    return new ImmutableVelocity<D>(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static <D extends Unit> Velocity<D> ofBaseUnits(double baseUnitMagnitude, VelocityUnit<D> unit) {
    return new ImmutableVelocity<D>(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Velocity<D> copy();

  @Override
  default MutVelocity<D> mutableCopy() {
    return new MutVelocity<D>(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  VelocityUnit<D> unit();

  @Override
  default VelocityUnit<D> baseUnit() { return (VelocityUnit<D>) unit().getBaseUnit(); }

  @Override
  default double in(VelocityUnit<D> unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Velocity<D> unaryMinus() {
    return (Velocity<D>) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  default Velocity<D> negate() {
    return (Velocity<D>) unaryMinus();
  }

  @Override
  default Velocity<D> plus(Measure<? extends VelocityUnit<D>> other) {
    return (Velocity<D>) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Velocity<D> minus(Measure<? extends VelocityUnit<D>> other) {
    return (Velocity<D>) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Velocity<D> times(double multiplier) {
    return (Velocity<D>) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Velocity<D> divide(double divisor) {
    return (Velocity<D>) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<VelocityUnit<D>> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<VelocityUnit<D>, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<VelocityUnit<D>, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<VelocityUnit<D>, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, AngleUnit> times(Angle multiplier) {
    return (Mult<VelocityUnit<D>, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, AngleUnit> divide(Angle divisor) {
    return (Per<VelocityUnit<D>, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<VelocityUnit<D>, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<VelocityUnit<D>, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<VelocityUnit<D>, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<VelocityUnit<D>, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<VelocityUnit<D>, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<VelocityUnit<D>, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, CurrentUnit> times(Current multiplier) {
    return (Mult<VelocityUnit<D>, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, CurrentUnit> divide(Current divisor) {
    return (Per<VelocityUnit<D>, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Velocity<D> divide(Dimensionless divisor) {
    return (Velocity<D>) unit().of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Velocity<D> times(Dimensionless multiplier) {
    return (Velocity<D>) unit().of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<VelocityUnit<D>, DistanceUnit> times(Distance multiplier) {
    return (Mult<VelocityUnit<D>, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, DistanceUnit> divide(Distance divisor) {
    return (Per<VelocityUnit<D>, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, EnergyUnit> times(Energy multiplier) {
    return (Mult<VelocityUnit<D>, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, EnergyUnit> divide(Energy divisor) {
    return (Per<VelocityUnit<D>, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, ForceUnit> times(Force multiplier) {
    return (Mult<VelocityUnit<D>, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, ForceUnit> divide(Force divisor) {
    return (Per<VelocityUnit<D>, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<VelocityUnit<D>, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, FrequencyUnit> divide(Frequency divisor) {
    return (Per<VelocityUnit<D>, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<VelocityUnit<D>, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<VelocityUnit<D>, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<VelocityUnit<D>, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<VelocityUnit<D>, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<VelocityUnit<D>, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<VelocityUnit<D>, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, MassUnit> times(Mass multiplier) {
    return (Mult<VelocityUnit<D>, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, MassUnit> divide(Mass divisor) {
    return (Per<VelocityUnit<D>, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<VelocityUnit<D>, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<VelocityUnit<D>, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<VelocityUnit<D>, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<VelocityUnit<D>, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<VelocityUnit<D>, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<VelocityUnit<D>, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, PowerUnit> times(Power multiplier) {
    return (Mult<VelocityUnit<D>, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, PowerUnit> divide(Power divisor) {
    return (Per<VelocityUnit<D>, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<VelocityUnit<D>, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, ResistanceUnit> divide(Resistance divisor) {
    return (Per<VelocityUnit<D>, ResistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<VelocityUnit<D>, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, TemperatureUnit> divide(Temperature divisor) {
    return (Per<VelocityUnit<D>, TemperatureUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Measure<D> times(Time multiplier) {
    return (Measure<D>) unit().numerator().ofBaseUnits(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Velocity<VelocityUnit<D>> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<VelocityUnit<D>, TorqueUnit> times(Torque multiplier) {
    return (Mult<VelocityUnit<D>, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, TorqueUnit> divide(Torque divisor) {
    return (Per<VelocityUnit<D>, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<VelocityUnit<D>, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<VelocityUnit<D>, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VelocityUnit<D>, VoltageUnit> times(Voltage multiplier) {
    return (Mult<VelocityUnit<D>, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VelocityUnit<D>, VoltageUnit> divide(Voltage divisor) {
    return (Per<VelocityUnit<D>, VoltageUnit>) Measure.super.divide(divisor);
  }

}
