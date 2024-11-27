// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Mult<A extends Unit, B extends Unit> extends Measure<MultUnit<A, B>> {
  static <A extends Unit, B extends Unit> Mult<A, B> ofRelativeUnits(double magnitude, MultUnit<A, B> unit) {
    return new ImmutableMult<A, B>(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static <A extends Unit, B extends Unit> Mult<A, B> ofBaseUnits(double baseUnitMagnitude, MultUnit<A, B> unit) {
    return new ImmutableMult<A, B>(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Mult<A, B> copy();

  @Override
  default MutMult<A, B> mutableCopy() {
    return new MutMult<A, B>(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  MultUnit<A, B> unit();

  @Override
  default MultUnit<A, B> baseUnit() { return (MultUnit<A, B>) unit().getBaseUnit(); }

  @Override
  default double in(MultUnit<A, B> unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Mult<A, B> unaryMinus() {
    return (Mult<A, B>) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mult<A, B> negate() {
    return (Mult<A, B>) unaryMinus();
  }

  @Override
  default Mult<A, B> plus(Measure<? extends MultUnit<A, B>> other) {
    return (Mult<A, B>) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Mult<A, B> minus(Measure<? extends MultUnit<A, B>> other) {
    return (Mult<A, B>) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Mult<A, B> times(double multiplier) {
    return (Mult<A, B>) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Mult<A, B> div(double divisor) {
    return (Mult<A, B>) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mult<A, B> divide(double divisor) {
    return (Mult<A, B>) div(divisor);
  }

  @Override
  default Velocity<MultUnit<A, B>> per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<MultUnit<A, B>, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<MultUnit<A, B>, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<MultUnit<A, B>, AccelerationUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, AngleUnit> times(Angle multiplier) {
    return (Mult<MultUnit<A, B>, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, AngleUnit> div(Angle divisor) {
    return (Per<MultUnit<A, B>, AngleUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, AngleUnit> divide(Angle divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<MultUnit<A, B>, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<MultUnit<A, B>, AngularAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<MultUnit<A, B>, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<MultUnit<A, B>, AngularMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<MultUnit<A, B>, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<MultUnit<A, B>, AngularVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, CurrentUnit> times(Current multiplier) {
    return (Mult<MultUnit<A, B>, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, CurrentUnit> div(Current divisor) {
    return (Per<MultUnit<A, B>, CurrentUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, CurrentUnit> divide(Current divisor) {
    return div(divisor);
  }

  @Override
  default Mult<A, B> div(Dimensionless divisor) {
    return (Mult<A, B>) unit().of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mult<A, B> divide(Dimensionless divisor) {
    return (Mult<A, B>) div(divisor);
  }

  @Override
  default Mult<A, B> times(Dimensionless multiplier) {
    return (Mult<A, B>) unit().of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<MultUnit<A, B>, DistanceUnit> times(Distance multiplier) {
    return (Mult<MultUnit<A, B>, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, DistanceUnit> div(Distance divisor) {
    return (Per<MultUnit<A, B>, DistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, DistanceUnit> divide(Distance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, EnergyUnit> times(Energy multiplier) {
    return (Mult<MultUnit<A, B>, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, EnergyUnit> div(Energy divisor) {
    return (Per<MultUnit<A, B>, EnergyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, EnergyUnit> divide(Energy divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, ForceUnit> times(Force multiplier) {
    return (Mult<MultUnit<A, B>, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, ForceUnit> div(Force divisor) {
    return (Per<MultUnit<A, B>, ForceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, ForceUnit> divide(Force divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<MultUnit<A, B>, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, FrequencyUnit> div(Frequency divisor) {
    return (Per<MultUnit<A, B>, FrequencyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, FrequencyUnit> divide(Frequency divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, LinearAccelerationUnit> times(LinearAcceleration multiplier) {
    return (Mult<MultUnit<A, B>, LinearAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<MultUnit<A, B>, LinearAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<MultUnit<A, B>, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<MultUnit<A, B>, LinearMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<MultUnit<A, B>, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, LinearVelocityUnit> div(LinearVelocity divisor) {
    return (Per<MultUnit<A, B>, LinearVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, MassUnit> times(Mass multiplier) {
    return (Mult<MultUnit<A, B>, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, MassUnit> div(Mass divisor) {
    return (Per<MultUnit<A, B>, MassUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, MassUnit> divide(Mass divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<MultUnit<A, B>, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<MultUnit<A, B>, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<MultUnit<A, B>, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<MultUnit<A, B>, MultUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<MultUnit<A, B>, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<MultUnit<A, B>, PerUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, PowerUnit> times(Power multiplier) {
    return (Mult<MultUnit<A, B>, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, PowerUnit> div(Power divisor) {
    return (Per<MultUnit<A, B>, PowerUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, PowerUnit> divide(Power divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<MultUnit<A, B>, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, ResistanceUnit> div(Resistance divisor) {
    return (Per<MultUnit<A, B>, ResistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, ResistanceUnit> divide(Resistance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<MultUnit<A, B>, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, TemperatureUnit> div(Temperature divisor) {
    return (Per<MultUnit<A, B>, TemperatureUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, TemperatureUnit> divide(Temperature divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, TimeUnit> times(Time multiplier) {
    return (Mult<MultUnit<A, B>, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<MultUnit<A, B>> div(Time divisor) {
    return VelocityUnit.combine(unit(), divisor.unit()).ofBaseUnits(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Velocity<MultUnit<A, B>> divide(Time divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, TorqueUnit> times(Torque multiplier) {
    return (Mult<MultUnit<A, B>, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, TorqueUnit> div(Torque divisor) {
    return (Per<MultUnit<A, B>, TorqueUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, TorqueUnit> divide(Torque divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<MultUnit<A, B>, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<MultUnit<A, B>, VelocityUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MultUnit<A, B>, VoltageUnit> times(Voltage multiplier) {
    return (Mult<MultUnit<A, B>, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MultUnit<A, B>, VoltageUnit> div(Voltage divisor) {
    return (Per<MultUnit<A, B>, VoltageUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MultUnit<A, B>, VoltageUnit> divide(Voltage divisor) {
    return div(divisor);
  }

}
