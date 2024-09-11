// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

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
  default Power divide(double divisor) {
    return (Power) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  @Override
  default Velocity<PowerUnit> per(TimeUnit period) {
    return divide(period.of(1));
  }


  @Override
  default Mult<PowerUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<PowerUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return (Per<PowerUnit, AccelerationUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<PowerUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngleUnit> divide(Angle divisor) {
    return (Per<PowerUnit, AngleUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<PowerUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return (Per<PowerUnit, AngularAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<PowerUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return (Per<PowerUnit, AngularMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<PowerUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return (Per<PowerUnit, AngularVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<PowerUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Voltage divide(Current divisor) {
    return Volts.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  @Override
  default Power divide(Dimensionless divisor) {
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
  default Per<PowerUnit, DistanceUnit> divide(Distance divisor) {
    return (Per<PowerUnit, DistanceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<PowerUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Frequency divide(Energy divisor) {
    return Hertz.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, ForceUnit> times(Force multiplier) {
    return (Mult<PowerUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, ForceUnit> divide(Force divisor) {
    return (Per<PowerUnit, ForceUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<PowerUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, FrequencyUnit> divide(Frequency divisor) {
    return (Per<PowerUnit, FrequencyUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<PowerUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return (Per<PowerUnit, LinearAccelerationUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<PowerUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return (Per<PowerUnit, LinearMomentumUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<PowerUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return (Per<PowerUnit, LinearVelocityUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, MassUnit> times(Mass multiplier) {
    return (Mult<PowerUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MassUnit> divide(Mass divisor) {
    return (Per<PowerUnit, MassUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<PowerUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return (Per<PowerUnit, MomentOfInertiaUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<PowerUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return (Per<PowerUnit, MultUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<PowerUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return (Per<PowerUnit, PerUnit<?, ?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, PowerUnit> times(Power multiplier) {
    return (Mult<PowerUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless divide(Power divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<PowerUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, TemperatureUnit> divide(Temperature divisor) {
    return (Per<PowerUnit, TemperatureUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Energy times(Time multiplier) {
    return Joules.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Velocity<PowerUnit> divide(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<PowerUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, TorqueUnit> divide(Torque divisor) {
    return (Per<PowerUnit, TorqueUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<PowerUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return (Per<PowerUnit, VelocityUnit<?>>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<PowerUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Current divide(Voltage divisor) {
    return Amps.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }


  @Override
  default Mult<PowerUnit, VoltagePerAnglePerTimeUnit> times(VoltagePerAnglePerTime multiplier) {
    return (Mult<PowerUnit, VoltagePerAnglePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, VoltagePerAnglePerTimeUnit> divide(VoltagePerAnglePerTime divisor) {
    return (Per<PowerUnit, VoltagePerAnglePerTimeUnit>) Measure.super.divide(divisor);
  }


  @Override
  default Mult<PowerUnit, VoltagePerDistancePerTimeUnit> times(VoltagePerDistancePerTime multiplier) {
    return (Mult<PowerUnit, VoltagePerDistancePerTimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<PowerUnit, VoltagePerDistancePerTimeUnit> divide(VoltagePerDistancePerTime divisor) {
    return (Per<PowerUnit, VoltagePerDistancePerTimeUnit>) Measure.super.divide(divisor);
  }

}
