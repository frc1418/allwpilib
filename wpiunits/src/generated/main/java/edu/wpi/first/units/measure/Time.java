// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Time extends Measure<TimeUnit> {
  static  Time ofRelativeUnits(double magnitude, TimeUnit unit) {
    return new ImmutableTime(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Time ofBaseUnits(double baseUnitMagnitude, TimeUnit unit) {
    return new ImmutableTime(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Time copy();

  @Override
  default MutTime mutableCopy() {
    return new MutTime(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  TimeUnit unit();

  @Override
  default TimeUnit baseUnit() { return (TimeUnit) unit().getBaseUnit(); }

  @Override
  default double in(TimeUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Time unaryMinus() {
    return (Time) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use unaryMinus() instead. This was renamed for consistancy with other WPILib classes like Rotation2d
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Time negate() {
    return (Time) unaryMinus();
  }

  @Override
  default Time plus(Measure<? extends TimeUnit> other) {
    return (Time) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Time minus(Measure<? extends TimeUnit> other) {
    return (Time) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Time times(double multiplier) {
    return (Time) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Time div(double divisor) {
    return (Time) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Dimensionless per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<TimeUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<TimeUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<TimeUnit, AccelerationUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<TimeUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AngleUnit> div(Angle divisor) {
    return (Per<TimeUnit, AngleUnit>) Measure.super.div(divisor);
  }


  @Override
  default AngularVelocity times(AngularAcceleration multiplier) {
    return RadiansPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<TimeUnit, AngularAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<TimeUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<TimeUnit, AngularMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Angle times(AngularVelocity multiplier) {
    return Radians.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<TimeUnit, AngularVelocityUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<TimeUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, CurrentUnit> div(Current divisor) {
    return (Per<TimeUnit, CurrentUnit>) Measure.super.div(divisor);
  }

  @Override
  default Time div(Dimensionless divisor) {
    return (Time) Seconds.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Time times(Dimensionless multiplier) {
    return (Time) Seconds.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<TimeUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<TimeUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, DistanceUnit> div(Distance divisor) {
    return (Per<TimeUnit, DistanceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<TimeUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, EnergyUnit> div(Energy divisor) {
    return (Per<TimeUnit, EnergyUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, ForceUnit> times(Force multiplier) {
    return (Mult<TimeUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, ForceUnit> div(Force divisor) {
    return (Per<TimeUnit, ForceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Dimensionless times(Frequency multiplier) {
    return Value.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, FrequencyUnit> div(Frequency divisor) {
    return (Per<TimeUnit, FrequencyUnit>) Measure.super.div(divisor);
  }


  @Override
  default LinearVelocity times(LinearAcceleration multiplier) {
    return MetersPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<TimeUnit, LinearAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<TimeUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<TimeUnit, LinearMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Distance times(LinearVelocity multiplier) {
    return Meters.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, LinearVelocityUnit> div(LinearVelocity divisor) {
    return (Per<TimeUnit, LinearVelocityUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, MassUnit> times(Mass multiplier) {
    return (Mult<TimeUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MassUnit> div(Mass divisor) {
    return (Per<TimeUnit, MassUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<TimeUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<TimeUnit, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<TimeUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<TimeUnit, MultUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<TimeUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<TimeUnit, PerUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, PowerUnit> times(Power multiplier) {
    return (Mult<TimeUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, PowerUnit> div(Power divisor) {
    return (Per<TimeUnit, PowerUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<TimeUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, ResistanceUnit> div(Resistance divisor) {
    return (Per<TimeUnit, ResistanceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<TimeUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, TemperatureUnit> div(Temperature divisor) {
    return (Per<TimeUnit, TemperatureUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, TimeUnit> times(Time multiplier) {
    return (Mult<TimeUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless div(Time divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<TimeUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<TimeUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, TorqueUnit> div(Torque divisor) {
    return (Per<TimeUnit, TorqueUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<TimeUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<TimeUnit, VelocityUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<TimeUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<TimeUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VoltageUnit> div(Voltage divisor) {
    return (Per<TimeUnit, VoltageUnit>) Measure.super.div(divisor);
  }
default Frequency asFrequency() { return Hertz.of(1 / baseUnitMagnitude()); }
}
