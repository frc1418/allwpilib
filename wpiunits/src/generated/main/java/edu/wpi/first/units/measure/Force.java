// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Force extends Measure<ForceUnit> {
  static  Force ofRelativeUnits(double magnitude, ForceUnit unit) {
    return new ImmutableForce(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Force ofBaseUnits(double baseUnitMagnitude, ForceUnit unit) {
    return new ImmutableForce(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Force copy();

  @Override
  default MutForce mutableCopy() {
    return new MutForce(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  ForceUnit unit();

  @Override
  default ForceUnit baseUnit() { return (ForceUnit) unit().getBaseUnit(); }

  @Override
  default double in(ForceUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Force unaryMinus() {
    return (Force) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default Force plus(Measure<? extends ForceUnit> other) {
    return (Force) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Force minus(Measure<? extends ForceUnit> other) {
    return (Force) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Force times(double multiplier) {
    return (Force) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Force divide(double divisor) {
    return (Force) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<ForceUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<ForceUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<ForceUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<ForceUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<ForceUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, AngleUnit> divide(Angle divisor) {
    return (Per<ForceUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<ForceUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<ForceUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<ForceUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<ForceUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<ForceUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<ForceUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<ForceUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, CurrentUnit> divide(Current divisor) {
    return (Per<ForceUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Force divide(Dimensionless divisor) {
    return (Force) Newtons.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Force times(Dimensionless multiplier) {
    return (Force) Newtons.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Energy times(Distance multiplier) {
    return Joules.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<ForceUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<ForceUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<ForceUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<ForceUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, ForceUnit> times(Force multiplier) {
    return (Mult<ForceUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(Force divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<ForceUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<ForceUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<ForceUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<ForceUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Mass divide(LinearAcceleration divisor) {
    return Kilograms.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<ForceUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<ForceUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<ForceUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<ForceUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<ForceUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, MassUnit> times(Mass multiplier) {
    return (Mult<ForceUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default LinearAcceleration divide(Mass divisor) {
    return MetersPerSecondPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<ForceUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<ForceUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<ForceUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<ForceUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<ForceUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<ForceUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<ForceUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, PowerUnit> times(Power multiplier) {
    return (Mult<ForceUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, PowerUnit> divide(Power divisor) {
    return (Per<ForceUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<ForceUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<ForceUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, TimeUnit> times(Time multiplier) {
    return (Mult<ForceUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<ForceUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<ForceUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<ForceUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<ForceUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<ForceUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<ForceUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<ForceUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<ForceUnit, VoltageUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VoltagePerAnglePerTimeUnit> times(VoltagePerAnglePerTime multiplier) {
    return (Mult<ForceUnit, VoltagePerAnglePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VoltagePerAnglePerTimeUnit> divide(VoltagePerAnglePerTime divisor) {
    return (Per<ForceUnit, VoltagePerAnglePerTimeUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VoltagePerAnglePerTimeSquaredUnit> times(VoltagePerAnglePerTimeSquared multiplier) {
    return (Mult<ForceUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VoltagePerAnglePerTimeSquaredUnit> divide(VoltagePerAnglePerTimeSquared divisor) {
    return (Per<ForceUnit, VoltagePerAnglePerTimeSquaredUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VoltagePerDistancePerTimeUnit> times(VoltagePerDistancePerTime multiplier) {
    return (Mult<ForceUnit, VoltagePerDistancePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VoltagePerDistancePerTimeUnit> divide(VoltagePerDistancePerTime divisor) {
    return (Per<ForceUnit, VoltagePerDistancePerTimeUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<ForceUnit, VoltagePerDistancePerTimeSquaredUnit> times(VoltagePerDistancePerTimeSquared multiplier) {
    return (Mult<ForceUnit, VoltagePerDistancePerTimeSquaredUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<ForceUnit, VoltagePerDistancePerTimeSquaredUnit> divide(VoltagePerDistancePerTimeSquared divisor) {
    return (Per<ForceUnit, VoltagePerDistancePerTimeSquaredUnit>) Measure.super.divide(divisor);
  }

}
