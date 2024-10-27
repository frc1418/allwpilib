// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface AngularMomentum extends Measure<AngularMomentumUnit> {
  static  AngularMomentum ofRelativeUnits(double magnitude, AngularMomentumUnit unit) {
    return new ImmutableAngularMomentum(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  AngularMomentum ofBaseUnits(double baseUnitMagnitude, AngularMomentumUnit unit) {
    return new ImmutableAngularMomentum(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  AngularMomentum copy();

  @Override
  default MutAngularMomentum mutableCopy() {
    return new MutAngularMomentum(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  AngularMomentumUnit unit();

  @Override
  default AngularMomentumUnit baseUnit() { return (AngularMomentumUnit) unit().getBaseUnit(); }

  @Override
  default double in(AngularMomentumUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default AngularMomentum unaryMinus() {
    return (AngularMomentum) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  default AngularMomentum plus(Measure<? extends AngularMomentumUnit> other) {
    return (AngularMomentum) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default AngularMomentum minus(Measure<? extends AngularMomentumUnit> other) {
    return (AngularMomentum) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default AngularMomentum times(double multiplier) {
    return (AngularMomentum) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default AngularMomentum divide(double divisor) {
    return (AngularMomentum) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<AngularMomentumUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<AngularMomentumUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<AngularMomentumUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<AngularMomentumUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<AngularMomentumUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, AngleUnit> divide(Angle divisor) {
    return (Per<AngularMomentumUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<AngularMomentumUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<AngularMomentumUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<AngularMomentumUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(AngularMomentum divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularMomentumUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<AngularMomentumUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default MomentOfInertia divide(AngularVelocity divisor) {
    return KilogramSquareMeters.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularMomentumUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<AngularMomentumUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, CurrentUnit> divide(Current divisor) {
    return (Per<AngularMomentumUnit, CurrentUnit>) Measure.super.divide(divisor);
  }

  @Override
  default AngularMomentum divide(Dimensionless divisor) {
    return (AngularMomentum) KilogramMetersSquaredPerSecond.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default AngularMomentum times(Dimensionless multiplier) {
    return (AngularMomentum) KilogramMetersSquaredPerSecond.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularMomentumUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<AngularMomentumUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<AngularMomentumUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<AngularMomentumUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, EnergyUnit> divide(Energy divisor) {
    return (Per<AngularMomentumUnit, EnergyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, ForceUnit> times(Force multiplier) {
    return (Mult<AngularMomentumUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, ForceUnit> divide(Force divisor) {
    return (Per<AngularMomentumUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<AngularMomentumUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<AngularMomentumUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<AngularMomentumUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<AngularMomentumUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<AngularMomentumUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<AngularMomentumUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<AngularMomentumUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<AngularMomentumUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, MassUnit> times(Mass multiplier) {
    return (Mult<AngularMomentumUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, MassUnit> divide(Mass divisor) {
    return (Per<AngularMomentumUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<AngularMomentumUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<AngularMomentumUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<AngularMomentumUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<AngularMomentumUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<AngularMomentumUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<AngularMomentumUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, PowerUnit> times(Power multiplier) {
    return (Mult<AngularMomentumUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, PowerUnit> divide(Power divisor) {
    return (Per<AngularMomentumUnit, PowerUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<AngularMomentumUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, ResistanceUnit> divide(Resistance divisor) {
    return (Per<AngularMomentumUnit, ResistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<AngularMomentumUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<AngularMomentumUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, TimeUnit> times(Time multiplier) {
    return (Mult<AngularMomentumUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<AngularMomentumUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<AngularMomentumUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<AngularMomentumUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<AngularMomentumUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<AngularMomentumUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<AngularMomentumUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<AngularMomentumUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<AngularMomentumUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<AngularMomentumUnit, VoltageUnit> divide(Voltage divisor) {
    return (Per<AngularMomentumUnit, VoltageUnit>) Measure.super.divide(divisor);
  }

}
