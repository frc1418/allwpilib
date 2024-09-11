// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface VoltagePerAnglePerTime extends Measure<VoltagePerAnglePerTimeUnit> {
  static  VoltagePerAnglePerTime ofRelativeUnits(double magnitude, VoltagePerAnglePerTimeUnit unit) {
    return new ImmutableVoltagePerAnglePerTime(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  VoltagePerAnglePerTime ofBaseUnits(double baseUnitMagnitude, VoltagePerAnglePerTimeUnit unit) {
    return new ImmutableVoltagePerAnglePerTime(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  VoltagePerAnglePerTime copy();

  @Override
  default MutVoltagePerAnglePerTime mutableCopy() {
    return new MutVoltagePerAnglePerTime(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  VoltagePerAnglePerTimeUnit unit();

  @Override
  default VoltagePerAnglePerTimeUnit baseUnit() { return (VoltagePerAnglePerTimeUnit) unit().getBaseUnit(); }

  @Override
  default double in(VoltagePerAnglePerTimeUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default VoltagePerAnglePerTime unaryMinus() {
    return (VoltagePerAnglePerTime) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default VoltagePerAnglePerTime plus(Measure<? extends VoltagePerAnglePerTimeUnit> other) {
    return (VoltagePerAnglePerTime) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default VoltagePerAnglePerTime minus(Measure<? extends VoltagePerAnglePerTimeUnit> other) {
    return (VoltagePerAnglePerTime) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default VoltagePerAnglePerTime times(double multiplier) {
    return (VoltagePerAnglePerTime) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default VoltagePerAnglePerTime divide(double divisor) {
    return (VoltagePerAnglePerTime) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<VoltagePerAnglePerTimeUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, AngleUnit> divide(Angle divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Voltage times(AngularVelocity multiplier) {
    return Volts.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, CurrentUnit> divide(Current divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default VoltagePerAnglePerTime divide(Dimensionless divisor) {
    return (VoltagePerAnglePerTime) VoltsPerRadianPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default VoltagePerAnglePerTime times(Dimensionless multiplier) {
    return (VoltagePerAnglePerTime) VoltsPerRadianPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, ForceUnit> times(Force multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, ForceUnit> divide(Force divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, MassUnit> times(Mass multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, MassUnit> divide(Mass divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, PowerUnit> times(Power multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, PowerUnit> divide(Power divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, TimeUnit> times(Time multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<VoltagePerAnglePerTimeUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, VoltageUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, VoltagePerAnglePerTimeUnit> times(VoltagePerAnglePerTime multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, VoltagePerAnglePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(VoltagePerAnglePerTime divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<VoltagePerAnglePerTimeUnit, VoltagePerDistancePerTimeUnit> times(VoltagePerDistancePerTime multiplier) {
    return (Mult<VoltagePerAnglePerTimeUnit, VoltagePerDistancePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltagePerAnglePerTimeUnit, VoltagePerDistancePerTimeUnit> divide(VoltagePerDistancePerTime divisor) {
    return (Per<VoltagePerAnglePerTimeUnit, VoltagePerDistancePerTimeUnit>) Measure.super.divide(divisor);
  }

}
