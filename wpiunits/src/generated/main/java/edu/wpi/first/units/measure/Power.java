// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package org.wpilib.units.measure;

import static org.wpilib.units.Units.*;
import org.wpilib.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Power extends Measure<PowerUnit> {
  static  Power ofRelativeUnits(double magnitude, PowerUnit unit) {
    return new ImmutablePower(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Power ofBaseUnits(double baseUnitMagnitude, PowerUnit unit) {
    return new ImmutablePower(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Power copy();

  @Override
  default MutPower mutableCopy() {
    return new MutPower(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  PowerUnit unit();

  @Override
  default PowerUnit baseUnit() { return (PowerUnit) unit().getBaseUnit(); }

  @Override
  default double in(PowerUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Power unaryMinus() {
    return (Power) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Power negate() {
    return (Power) unaryMinus();
  }

  @Override
  default Power plus(Measure<? extends PowerUnit> other) {
    return (Power) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Power minus(Measure<? extends PowerUnit> other) {
    return (Power) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Power times(double multiplier) {
    return (Power) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Power div(double divisor) {
    return (Power) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<PowerUnit> per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<PowerUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<PowerUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<PowerUnit, AccelerationUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<PowerUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngleUnit> div(Angle divisor) {
    return (Per<PowerUnit, AngleUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<PowerUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<PowerUnit, AngularAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<PowerUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<PowerUnit, AngularMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<PowerUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<PowerUnit, AngularVelocityUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<PowerUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Voltage div(Current divisor) {
    return Volts.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Power div(Dimensionless divisor) {
    return (Power) Watts.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Power times(Dimensionless multiplier) {
    return (Power) Watts.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<PowerUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, DistanceUnit> div(Distance divisor) {
    return (Per<PowerUnit, DistanceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<PowerUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Frequency div(Energy divisor) {
    return Hertz.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, ForceUnit> times(Force multiplier) {
    return (Mult<PowerUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, ForceUnit> div(Force divisor) {
    return (Per<PowerUnit, ForceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<PowerUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, FrequencyUnit> div(Frequency divisor) {
    return (Per<PowerUnit, FrequencyUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<PowerUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<PowerUnit, LinearAccelerationUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<PowerUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<PowerUnit, LinearMomentumUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<PowerUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearVelocityUnit> div(LinearVelocity divisor) {
    return (Per<PowerUnit, LinearVelocityUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, MassUnit> times(Mass multiplier) {
    return (Mult<PowerUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MassUnit> div(Mass divisor) {
    return (Per<PowerUnit, MassUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<PowerUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<PowerUnit, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<PowerUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<PowerUnit, MultUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<PowerUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<PowerUnit, PerUnit<?, ?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, PowerUnit> times(Power multiplier) {
    return (Mult<PowerUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless div(Power divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<PowerUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, ResistanceUnit> div(Resistance divisor) {
    return (Per<PowerUnit, ResistanceUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<PowerUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, TemperatureUnit> div(Temperature divisor) {
    return (Per<PowerUnit, TemperatureUnit>) Measure.super.div(divisor);
  }


  @Override
  default Energy times(Time multiplier) {
    return Joules.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Velocity<PowerUnit> div(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<PowerUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, TorqueUnit> div(Torque divisor) {
    return (Per<PowerUnit, TorqueUnit>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<PowerUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<PowerUnit, VelocityUnit<?>>) Measure.super.div(divisor);
  }


  @Override
  default Mult<PowerUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<PowerUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Current div(Voltage divisor) {
    return Amps.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

}
