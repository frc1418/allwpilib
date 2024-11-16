// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Distance extends Measure<DistanceUnit> {
  static  Distance ofRelativeUnits(double magnitude, DistanceUnit unit) {
    return new ImmutableDistance(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Distance ofBaseUnits(double baseUnitMagnitude, DistanceUnit unit) {
    return new ImmutableDistance(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Distance copy();

  @Override
  default MutDistance mutableCopy() {
    return new MutDistance(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  DistanceUnit unit();

  @Override
  default DistanceUnit baseUnit() { return (DistanceUnit) unit().getBaseUnit(); }

  @Override
  default double in(DistanceUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Distance unaryMinus() {
    return (Distance) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Distance negate() {
    return (Distance) unaryMinus();
  }

  @Override
  default Distance plus(Measure<? extends DistanceUnit> other) {
    return (Distance) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Distance minus(Measure<? extends DistanceUnit> other) {
    return (Distance) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Distance times(double multiplier) {
    return (Distance) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Distance div(double divisor) {
    return (Distance) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default LinearVelocity per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<DistanceUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<DistanceUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<DistanceUnit, AccelerationUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<DistanceUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, AngleUnit> div(Angle divisor) {
    return (Per<DistanceUnit, AngleUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<DistanceUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<DistanceUnit, AngularAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<DistanceUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<DistanceUnit, AngularMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<DistanceUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<DistanceUnit, AngularVelocityUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<DistanceUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, CurrentUnit> div(Current divisor) {
    return (Per<DistanceUnit, CurrentUnit>) Measure.super.div(divisor);
  }

  @Override
  default Distance div(Dimensionless divisor) {
    return (Distance) Meters.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Distance times(Dimensionless multiplier) {
    return (Distance) Meters.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<DistanceUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<DistanceUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless div(Distance divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<DistanceUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<DistanceUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, EnergyUnit> div(Energy divisor) {
    return (Per<DistanceUnit, EnergyUnit>) Measure.super.div(divisor);
  }


  @Override
  default Torque times(Force multiplier) {
    return NewtonMeters.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<DistanceUnit, ForceUnit> div(Force divisor) {
    return (Per<DistanceUnit, ForceUnit>) Measure.super.div(divisor);
  }


  @Override
  default LinearVelocity times(Frequency multiplier) {
    return MetersPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<DistanceUnit, FrequencyUnit> div(Frequency divisor) {
    return (Per<DistanceUnit, FrequencyUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<DistanceUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<DistanceUnit, LinearAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<DistanceUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<DistanceUnit, LinearMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<DistanceUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Time div(LinearVelocity divisor) {
    return Seconds.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<DistanceUnit, MassUnit> times(Mass multiplier) {
    return (Mult<DistanceUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, MassUnit> div(Mass divisor) {
    return (Per<DistanceUnit, MassUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<DistanceUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<DistanceUnit, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<DistanceUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<DistanceUnit, MultUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<DistanceUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<DistanceUnit, PerUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, PowerUnit> times(Power multiplier) {
    return (Mult<DistanceUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, PowerUnit> div(Power divisor) {
    return (Per<DistanceUnit, PowerUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<DistanceUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, ResistanceUnit> div(Resistance divisor) {
    return (Per<DistanceUnit, ResistanceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<DistanceUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, TemperatureUnit> div(Temperature divisor) {
    return (Per<DistanceUnit, TemperatureUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, TimeUnit> times(Time multiplier) {
    return (Mult<DistanceUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default LinearVelocity div(Time divisor) {
    return MetersPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<DistanceUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<DistanceUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, TorqueUnit> div(Torque divisor) {
    return (Per<DistanceUnit, TorqueUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<DistanceUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<DistanceUnit, VelocityUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<DistanceUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<DistanceUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<DistanceUnit, VoltageUnit> div(Voltage divisor) {
    return (Per<DistanceUnit, VoltageUnit>) Measure.super.div(divisor);
  }

}
