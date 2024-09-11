// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface AngularVelocity extends Measure<AngularVelocityUnit> {
  static  AngularVelocity ofRelativeUnits(double magnitude, AngularVelocityUnit unit) {
    return new ImmutableAngularVelocity(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  AngularVelocity ofBaseUnits(double baseUnitMagnitude, AngularVelocityUnit unit) {
    return new ImmutableAngularVelocity(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  AngularVelocity copy();

  @Override
  default MutAngularVelocity mutableCopy() {
    return new MutAngularVelocity(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  AngularVelocityUnit unit();

  @Override
  default AngularVelocityUnit baseUnit() { return (AngularVelocityUnit) unit().getBaseUnit(); }

  @Override
  default double in(AngularVelocityUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default AngularVelocity unaryMinus() {
    return (AngularVelocity) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default AngularVelocity plus(Measure<? extends AngularVelocityUnit> other) {
    return (AngularVelocity) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default AngularVelocity minus(Measure<? extends AngularVelocityUnit> other) {
    return (AngularVelocity) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default AngularVelocity times(double multiplier) {
    return (AngularVelocity) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default AngularVelocity divide(double divisor) {
    return (AngularVelocity) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default AngularAcceleration per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<AngularVelocityUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<AngularVelocityUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<AngularVelocityUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<AngularVelocityUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, AngleUnit> divide(Angle divisor) {
    return (Per<AngularVelocityUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<AngularVelocityUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<AngularVelocityUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<AngularVelocityUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<AngularVelocityUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<AngularVelocityUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(AngularVelocity divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularVelocityUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<AngularVelocityUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, CurrentUnit> divide(Current divisor) {
    return (Per<AngularVelocityUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default AngularVelocity divide(Dimensionless divisor) {
    return (AngularVelocity) RadiansPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default AngularVelocity times(Dimensionless multiplier) {
    return (AngularVelocity) RadiansPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularVelocityUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<AngularVelocityUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<AngularVelocityUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<AngularVelocityUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<AngularVelocityUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, ForceUnit> times(Force multiplier) {
    return (Mult<AngularVelocityUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, ForceUnit> divide(Force divisor) {
    return (Per<AngularVelocityUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default AngularAcceleration times(Frequency multiplier) {
    return RadiansPerSecondPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<AngularVelocityUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<AngularVelocityUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<AngularVelocityUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<AngularVelocityUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<AngularVelocityUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<AngularVelocityUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<AngularVelocityUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<AngularVelocityUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, MassUnit> times(Mass multiplier) {
    return (Mult<AngularVelocityUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, MassUnit> divide(Mass divisor) {
    return (Per<AngularVelocityUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<AngularVelocityUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<AngularVelocityUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<AngularVelocityUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<AngularVelocityUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<AngularVelocityUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<AngularVelocityUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, PowerUnit> times(Power multiplier) {
    return (Mult<AngularVelocityUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, PowerUnit> divide(Power divisor) {
    return (Per<AngularVelocityUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<AngularVelocityUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<AngularVelocityUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Angle times(Time multiplier) {
    return Radians.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default AngularAcceleration divide(Time divisor) {
    return RadiansPerSecondPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularVelocityUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<AngularVelocityUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<AngularVelocityUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<AngularVelocityUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<AngularVelocityUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<AngularVelocityUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<AngularVelocityUnit, VoltageUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Voltage times(VoltagePerAnglePerTime multiplier) {
    return Volts.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<AngularVelocityUnit, VoltagePerAnglePerTimeUnit> divide(VoltagePerAnglePerTime divisor) {
    return (Per<AngularVelocityUnit, VoltagePerAnglePerTimeUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, VoltagePerAnglePerTimeSquaredUnit> times(VoltagePerAnglePerTimeSquared multiplier) {
    return (Mult<AngularVelocityUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, VoltagePerAnglePerTimeSquaredUnit> divide(VoltagePerAnglePerTimeSquared divisor) {
    return (Per<AngularVelocityUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularVelocityUnit, VoltagePerDistancePerTimeUnit> times(VoltagePerDistancePerTime multiplier) {
    return (Mult<AngularVelocityUnit, VoltagePerDistancePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularVelocityUnit, VoltagePerDistancePerTimeUnit> divide(VoltagePerDistancePerTime divisor) {
    return (Per<AngularVelocityUnit, VoltagePerDistancePerTimeUnit>) Measure.super.divide(divisor);
  }
default Frequency asFrequency() { return Hertz.of(baseUnitMagnitude()); }
}
