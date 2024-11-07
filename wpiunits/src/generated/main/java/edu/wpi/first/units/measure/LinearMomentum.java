// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface LinearMomentum extends Measure<LinearMomentumUnit> {
  static  LinearMomentum ofRelativeUnits(double magnitude, LinearMomentumUnit unit) {
    return new ImmutableLinearMomentum(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  LinearMomentum ofBaseUnits(double baseUnitMagnitude, LinearMomentumUnit unit) {
    return new ImmutableLinearMomentum(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  LinearMomentum copy();

  @Override
  default MutLinearMomentum mutableCopy() {
    return new MutLinearMomentum(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  LinearMomentumUnit unit();

  @Override
  default LinearMomentumUnit baseUnit() { return (LinearMomentumUnit) unit().getBaseUnit(); }

  @Override
  default double in(LinearMomentumUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default LinearMomentum unaryMinus() {
    return (LinearMomentum) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default LinearMomentum negate() {
    return (LinearMomentum) unaryMinus();
  }

  @Override
  default LinearMomentum plus(Measure<? extends LinearMomentumUnit> other) {
    return (LinearMomentum) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default LinearMomentum minus(Measure<? extends LinearMomentumUnit> other) {
    return (LinearMomentum) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default LinearMomentum times(double multiplier) {
    return (LinearMomentum) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default LinearMomentum divide(double divisor) {
    return (LinearMomentum) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Force per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<LinearMomentumUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<LinearMomentumUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<LinearMomentumUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<LinearMomentumUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, AngleUnit> divide(Angle divisor) {
    return (Per<LinearMomentumUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<LinearMomentumUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<LinearMomentumUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<LinearMomentumUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<LinearMomentumUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<LinearMomentumUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<LinearMomentumUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<LinearMomentumUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, CurrentUnit> divide(Current divisor) {
    return (Per<LinearMomentumUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default LinearMomentum divide(Dimensionless divisor) {
    return (LinearMomentum) KilogramMetersPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default LinearMomentum times(Dimensionless multiplier) {
    return (LinearMomentum) KilogramMetersPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<LinearMomentumUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<LinearMomentumUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<LinearMomentumUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<LinearMomentumUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<LinearMomentumUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, ForceUnit> times(Force multiplier) {
    return (Mult<LinearMomentumUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, ForceUnit> divide(Force divisor) {
    return (Per<LinearMomentumUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Force times(Frequency multiplier) {
    return Newtons.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<LinearMomentumUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<LinearMomentumUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<LinearMomentumUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<LinearMomentumUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<LinearMomentumUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(LinearMomentum divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<LinearMomentumUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<LinearMomentumUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Mass divide(LinearVelocity divisor) {
    return Kilograms.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<LinearMomentumUnit, MassUnit> times(Mass multiplier) {
    return (Mult<LinearMomentumUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default LinearVelocity divide(Mass divisor) {
    return MetersPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<LinearMomentumUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<LinearMomentumUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<LinearMomentumUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<LinearMomentumUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<LinearMomentumUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<LinearMomentumUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<LinearMomentumUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, PowerUnit> times(Power multiplier) {
    return (Mult<LinearMomentumUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, PowerUnit> divide(Power divisor) {
    return (Per<LinearMomentumUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<LinearMomentumUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, ResistanceUnit> divide(Resistance divisor) {
    return (Per<LinearMomentumUnit, ResistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<LinearMomentumUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<LinearMomentumUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, TimeUnit> times(Time multiplier) {
    return (Mult<LinearMomentumUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Force divide(Time divisor) {
    return Newtons.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<LinearMomentumUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<LinearMomentumUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<LinearMomentumUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<LinearMomentumUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<LinearMomentumUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<LinearMomentumUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<LinearMomentumUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<LinearMomentumUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<LinearMomentumUnit, VoltageUnit>) Measure.super.divide(divisor);
  }

}
