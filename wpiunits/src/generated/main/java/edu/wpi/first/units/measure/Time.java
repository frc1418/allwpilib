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
  default Time divide(double divisor) {
    return (Time) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Dimensionless per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<TimeUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<TimeUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<TimeUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<TimeUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AngleUnit> divide(Angle divisor) {
    return (Per<TimeUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default AngularVelocity times(AngularAcceleration multiplier) {
    return RadiansPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<TimeUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<TimeUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<TimeUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Angle times(AngularVelocity multiplier) {
    return Radians.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<TimeUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<TimeUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, CurrentUnit> divide(Current divisor) {
    return (Per<TimeUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Time divide(Dimensionless divisor) {
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
  default Per<TimeUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<TimeUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<TimeUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<TimeUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, ForceUnit> times(Force multiplier) {
    return (Mult<TimeUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, ForceUnit> divide(Force divisor) {
    return (Per<TimeUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Dimensionless times(Frequency multiplier) {
    return Value.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<TimeUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default LinearVelocity times(LinearAcceleration multiplier) {
    return MetersPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<TimeUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<TimeUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<TimeUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Distance times(LinearVelocity multiplier) {
    return Meters.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<TimeUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<TimeUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, MassUnit> times(Mass multiplier) {
    return (Mult<TimeUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MassUnit> divide(Mass divisor) {
    return (Per<TimeUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<TimeUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<TimeUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<TimeUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<TimeUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<TimeUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<TimeUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, PowerUnit> times(Power multiplier) {
    return (Mult<TimeUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, PowerUnit> divide(Power divisor) {
    return (Per<TimeUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<TimeUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<TimeUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, TimeUnit> times(Time multiplier) {
    return (Mult<TimeUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(Time divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<TimeUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<TimeUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<TimeUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<TimeUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<TimeUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<TimeUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<TimeUnit, VoltageUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, VoltagePerAnglePerTimeUnit> times(VoltagePerAnglePerTime multiplier) {
    return (Mult<TimeUnit, VoltagePerAnglePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VoltagePerAnglePerTimeUnit> divide(VoltagePerAnglePerTime divisor) {
    return (Per<TimeUnit, VoltagePerAnglePerTimeUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, VoltagePerAnglePerTimeSquaredUnit> times(VoltagePerAnglePerTimeSquared multiplier) {
    return (Mult<TimeUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VoltagePerAnglePerTimeSquaredUnit> divide(VoltagePerAnglePerTimeSquared divisor) {
    return (Per<TimeUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<TimeUnit, VoltagePerDistancePerTimeUnit> times(VoltagePerDistancePerTime multiplier) {
    return (Mult<TimeUnit, VoltagePerDistancePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TimeUnit, VoltagePerDistancePerTimeUnit> divide(VoltagePerDistancePerTime divisor) {
    return (Per<TimeUnit, VoltagePerDistancePerTimeUnit>) Measure.super.divide(divisor);
  }
default Frequency asFrequency() { return Hertz.of(1 / baseUnitMagnitude()); }
}
