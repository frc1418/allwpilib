// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast"})
public interface Voltage extends Measure<VoltageUnit> {
  static  Voltage ofRelativeUnits(double magnitude, VoltageUnit unit) {
    return new ImmutableVoltage(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Voltage ofBaseUnits(double baseUnitMagnitude, VoltageUnit unit) {
    return new ImmutableVoltage(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Voltage copy();

  @Override
  default MutVoltage mutableCopy() {
    return new MutVoltage(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  VoltageUnit unit();

  @Override
  default VoltageUnit baseUnit() { return (VoltageUnit) unit().getBaseUnit(); }

  @Override
  default double in(VoltageUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Voltage unaryMinus() {
    return (Voltage) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default Voltage plus(Measure<? extends VoltageUnit> other) {
    return (Voltage) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Voltage minus(Measure<? extends VoltageUnit> other) {
    return (Voltage) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Voltage times(double multiplier) {
    return (Voltage) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Voltage divide(double divisor) {
    return (Voltage) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<VoltageUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<VoltageUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<VoltageUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<VoltageUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<VoltageUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, AngleUnit> divide(Angle divisor) {
    return (Per<VoltageUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<VoltageUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<VoltageUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<VoltageUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<VoltageUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<VoltageUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<VoltageUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Power times(Current multiplier) {
    return Watts.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<VoltageUnit, CurrentUnit> divide(Current divisor) {
    return (Per<VoltageUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default Voltage divide(Dimensionless divisor) {
    return (Voltage) Volts.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Voltage times(Dimensionless multiplier) {
    return (Voltage) Volts.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<VoltageUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<VoltageUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<VoltageUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<VoltageUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<VoltageUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, ForceUnit> times(Force multiplier) {
    return (Mult<VoltageUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, ForceUnit> divide(Force divisor) {
    return (Per<VoltageUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<VoltageUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<VoltageUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<VoltageUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<VoltageUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<VoltageUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<VoltageUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<VoltageUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<VoltageUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, MassUnit> times(Mass multiplier) {
    return (Mult<VoltageUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, MassUnit> divide(Mass divisor) {
    return (Per<VoltageUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<VoltageUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<VoltageUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<VoltageUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<VoltageUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<VoltageUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<VoltageUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, PowerUnit> times(Power multiplier) {
    return (Mult<VoltageUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, PowerUnit> divide(Power divisor) {
    return (Per<VoltageUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<VoltageUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<VoltageUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, TimeUnit> times(Time multiplier) {
    return (Mult<VoltageUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<VoltageUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<VoltageUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<VoltageUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<VoltageUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<VoltageUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<VoltageUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<VoltageUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<VoltageUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<VoltageUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(Voltage divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

}
