// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Temperature extends Measure<TemperatureUnit> {
  static  Temperature ofRelativeUnits(double magnitude, TemperatureUnit unit) {
    return new ImmutableTemperature(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Temperature ofBaseUnits(double baseUnitMagnitude, TemperatureUnit unit) {
    return new ImmutableTemperature(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Temperature copy();

  @Override
  default MutTemperature mutableCopy() {
    return new MutTemperature(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  TemperatureUnit unit();

  @Override
  default TemperatureUnit baseUnit() { return (TemperatureUnit) unit().getBaseUnit(); }

  @Override
  default double in(TemperatureUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Temperature unaryMinus() {
    return (Temperature) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Temperature negate() {
    return (Temperature) unaryMinus();
  }

  @Override
  default Temperature plus(Measure<? extends TemperatureUnit> other) {
    return (Temperature) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Temperature minus(Measure<? extends TemperatureUnit> other) {
    return (Temperature) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Temperature times(double multiplier) {
    return (Temperature) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Temperature div(double divisor) {
    return (Temperature) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Temperature divide(double divisor) {
    return (Temperature) div(divisor);
  }

  @Override
  default Velocity<TemperatureUnit> per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<TemperatureUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<TemperatureUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<TemperatureUnit, AccelerationUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<TemperatureUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, AngleUnit> div(Angle divisor) {
    return (Per<TemperatureUnit, AngleUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, AngleUnit> divide(Angle divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<TemperatureUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<TemperatureUnit, AngularAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<TemperatureUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<TemperatureUnit, AngularMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<TemperatureUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<TemperatureUnit, AngularVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<TemperatureUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, CurrentUnit> div(Current divisor) {
    return (Per<TemperatureUnit, CurrentUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, CurrentUnit> divide(Current divisor) {
    return div(divisor);
  }

  @Override
  default Temperature div(Dimensionless divisor) {
    return (Temperature) Kelvin.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Temperature divide(Dimensionless divisor) {
    return (Temperature) div(divisor);
  }

  @Override
  default Temperature times(Dimensionless multiplier) {
    return (Temperature) Kelvin.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<TemperatureUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<TemperatureUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, DistanceUnit> div(Distance divisor) {
    return (Per<TemperatureUnit, DistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, DistanceUnit> divide(Distance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<TemperatureUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, EnergyUnit> div(Energy divisor) {
    return (Per<TemperatureUnit, EnergyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, EnergyUnit> divide(Energy divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, ForceUnit> times(Force multiplier) {
    return (Mult<TemperatureUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, ForceUnit> div(Force divisor) {
    return (Per<TemperatureUnit, ForceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, ForceUnit> divide(Force divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<TemperatureUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, FrequencyUnit> div(Frequency divisor) {
    return (Per<TemperatureUnit, FrequencyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, FrequencyUnit> divide(Frequency divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<TemperatureUnit, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<TemperatureUnit, LinearAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<TemperatureUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<TemperatureUnit, LinearMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<TemperatureUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, LinearVelocityUnit> div(LinearVelocity divisor) {
    return (Per<TemperatureUnit, LinearVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, MassUnit> times(Mass multiplier) {
    return (Mult<TemperatureUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, MassUnit> div(Mass divisor) {
    return (Per<TemperatureUnit, MassUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, MassUnit> divide(Mass divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<TemperatureUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<TemperatureUnit, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<TemperatureUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<TemperatureUnit, MultUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<TemperatureUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<TemperatureUnit, PerUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, PowerUnit> times(Power multiplier) {
    return (Mult<TemperatureUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, PowerUnit> div(Power divisor) {
    return (Per<TemperatureUnit, PowerUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, PowerUnit> divide(Power divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<TemperatureUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, ResistanceUnit> div(Resistance divisor) {
    return (Per<TemperatureUnit, ResistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, ResistanceUnit> divide(Resistance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<TemperatureUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless div(Temperature divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Dimensionless divide(Temperature divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, TimeUnit> times(Time multiplier) {
    return (Mult<TemperatureUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<TemperatureUnit> div(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Velocity<TemperatureUnit> divide(Time divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<TemperatureUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, TorqueUnit> div(Torque divisor) {
    return (Per<TemperatureUnit, TorqueUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, TorqueUnit> divide(Torque divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<TemperatureUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<TemperatureUnit, VelocityUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<TemperatureUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<TemperatureUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<TemperatureUnit, VoltageUnit> div(Voltage divisor) {
    return (Per<TemperatureUnit, VoltageUnit>) Measure.super.div(divisor);
  }

  /**
  * {@inheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistency with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<TemperatureUnit, VoltageUnit> divide(Voltage divisor) {
    return div(divisor);
  }

}
