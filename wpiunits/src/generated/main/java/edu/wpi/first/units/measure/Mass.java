// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpiunits/generate_units.py. DO NOT MODIFY

package edu.wpi.first.units.measure;

import static edu.wpi.first.units.Units.*;
import edu.wpi.first.units.*;

@SuppressWarnings({"unchecked", "cast", "checkstyle", "PMD"})
public interface Mass extends Measure<MassUnit> {
  static  Mass ofRelativeUnits(double magnitude, MassUnit unit) {
    return new ImmutableMass(magnitude, unit.toBaseUnits(magnitude), unit);
  }

  static  Mass ofBaseUnits(double baseUnitMagnitude, MassUnit unit) {
    return new ImmutableMass(unit.fromBaseUnits(baseUnitMagnitude), baseUnitMagnitude, unit);
  }

  @Override
  Mass copy();

  @Override
  default MutMass mutableCopy() {
    return new MutMass(magnitude(), baseUnitMagnitude(), unit());
  }

  @Override
  MassUnit unit();

  @Override
  default MassUnit baseUnit() { return (MassUnit) unit().getBaseUnit(); }

  @Override
  default double in(MassUnit unit) {
    return unit.fromBaseUnits(baseUnitMagnitude());
  }

  @Override
  default Mass unaryMinus() {
    return (Mass) unit().ofBaseUnits(0 - baseUnitMagnitude());
  }

  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mass negate() {
    return (Mass) unaryMinus();
  }

  @Override
  default Mass plus(Measure<? extends MassUnit> other) {
    return (Mass) unit().ofBaseUnits(baseUnitMagnitude() + other.baseUnitMagnitude());
  }

  @Override
  default Mass minus(Measure<? extends MassUnit> other) {
    return (Mass) unit().ofBaseUnits(baseUnitMagnitude() - other.baseUnitMagnitude());
  }

  @Override
  default Mass times(double multiplier) {
    return (Mass) unit().ofBaseUnits(baseUnitMagnitude() * multiplier);
  }

  @Override
  default Mass div(double divisor) {
    return (Mass) unit().ofBaseUnits(baseUnitMagnitude() / divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mass divide(double divisor) {
    return (Mass) div(divisor);
  }

  @Override
  default Velocity<MassUnit> per(TimeUnit period) {
    return div(period.of(1));
  }


  @Override
  default Mult<MassUnit, AccelerationUnit<?>> times(Acceleration<?> multiplier) {
    return (Mult<MassUnit, AccelerationUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, AccelerationUnit<?>> div(Acceleration<?> divisor) {
    return (Per<MassUnit, AccelerationUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, AccelerationUnit<?>> divide(Acceleration<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, AngleUnit> times(Angle multiplier) {
    return (Mult<MassUnit, AngleUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, AngleUnit> div(Angle divisor) {
    return (Per<MassUnit, AngleUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, AngleUnit> divide(Angle divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, AngularAccelerationUnit> times(AngularAcceleration multiplier) {
    return (Mult<MassUnit, AngularAccelerationUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, AngularAccelerationUnit> div(AngularAcceleration divisor) {
    return (Per<MassUnit, AngularAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, AngularAccelerationUnit> divide(AngularAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, AngularMomentumUnit> times(AngularMomentum multiplier) {
    return (Mult<MassUnit, AngularMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, AngularMomentumUnit> div(AngularMomentum divisor) {
    return (Per<MassUnit, AngularMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, AngularMomentumUnit> divide(AngularMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, AngularVelocityUnit> times(AngularVelocity multiplier) {
    return (Mult<MassUnit, AngularVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, AngularVelocityUnit> div(AngularVelocity divisor) {
    return (Per<MassUnit, AngularVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, AngularVelocityUnit> divide(AngularVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, CurrentUnit> times(Current multiplier) {
    return (Mult<MassUnit, CurrentUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, CurrentUnit> div(Current divisor) {
    return (Per<MassUnit, CurrentUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, CurrentUnit> divide(Current divisor) {
    return div(divisor);
  }

  @Override
  default Mass div(Dimensionless divisor) {
    return (Mass) Kilograms.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Override
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  default Mass divide(Dimensionless divisor) {
    return (Mass) div(divisor);
  }

  @Override
  default Mass times(Dimensionless multiplier) {
    return (Mass) Kilograms.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }


  @Override
  default Mult<MassUnit, DistanceUnit> times(Distance multiplier) {
    return (Mult<MassUnit, DistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, DistanceUnit> div(Distance divisor) {
    return (Per<MassUnit, DistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, DistanceUnit> divide(Distance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, EnergyUnit> times(Energy multiplier) {
    return (Mult<MassUnit, EnergyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, EnergyUnit> div(Energy divisor) {
    return (Per<MassUnit, EnergyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, EnergyUnit> divide(Energy divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, ForceUnit> times(Force multiplier) {
    return (Mult<MassUnit, ForceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, ForceUnit> div(Force divisor) {
    return (Per<MassUnit, ForceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, ForceUnit> divide(Force divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, FrequencyUnit> times(Frequency multiplier) {
    return (Mult<MassUnit, FrequencyUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, FrequencyUnit> div(Frequency divisor) {
    return (Per<MassUnit, FrequencyUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, FrequencyUnit> divide(Frequency divisor) {
    return div(divisor);
  }


  @Override
  default Force times(LinearAcceleration multiplier) {
    return Newtons.of(baseUnitMagnitude() * multiplier.baseUnitMagnitude());
  }

  @Override
  default Per<MassUnit, LinearAccelerationUnit> div(LinearAcceleration divisor) {
    return (Per<MassUnit, LinearAccelerationUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, LinearAccelerationUnit> divide(LinearAcceleration divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, LinearMomentumUnit> times(LinearMomentum multiplier) {
    return (Mult<MassUnit, LinearMomentumUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, LinearMomentumUnit> div(LinearMomentum divisor) {
    return (Per<MassUnit, LinearMomentumUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, LinearMomentumUnit> divide(LinearMomentum divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, LinearVelocityUnit> times(LinearVelocity multiplier) {
    return (Mult<MassUnit, LinearVelocityUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, LinearVelocityUnit> div(LinearVelocity divisor) {
    return (Per<MassUnit, LinearVelocityUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, LinearVelocityUnit> divide(LinearVelocity divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, MassUnit> times(Mass multiplier) {
    return (Mult<MassUnit, MassUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Dimensionless div(Mass divisor) {
    return Value.of(baseUnitMagnitude() / divisor.baseUnitMagnitude());
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Dimensionless divide(Mass divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, MomentOfInertiaUnit> times(MomentOfInertia multiplier) {
    return (Mult<MassUnit, MomentOfInertiaUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, MomentOfInertiaUnit> div(MomentOfInertia divisor) {
    return (Per<MassUnit, MomentOfInertiaUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, MomentOfInertiaUnit> divide(MomentOfInertia divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, MultUnit<?, ?>> times(Mult<?, ?> multiplier) {
    return (Mult<MassUnit, MultUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, MultUnit<?, ?>> div(Mult<?, ?> divisor) {
    return (Per<MassUnit, MultUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, MultUnit<?, ?>> divide(Mult<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, PerUnit<?, ?>> times(Per<?, ?> multiplier) {
    return (Mult<MassUnit, PerUnit<?, ?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, PerUnit<?, ?>> div(Per<?, ?> divisor) {
    return (Per<MassUnit, PerUnit<?, ?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, PerUnit<?, ?>> divide(Per<?, ?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, PowerUnit> times(Power multiplier) {
    return (Mult<MassUnit, PowerUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, PowerUnit> div(Power divisor) {
    return (Per<MassUnit, PowerUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, PowerUnit> divide(Power divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, ResistanceUnit> times(Resistance multiplier) {
    return (Mult<MassUnit, ResistanceUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, ResistanceUnit> div(Resistance divisor) {
    return (Per<MassUnit, ResistanceUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, ResistanceUnit> divide(Resistance divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, TemperatureUnit> times(Temperature multiplier) {
    return (Mult<MassUnit, TemperatureUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, TemperatureUnit> div(Temperature divisor) {
    return (Per<MassUnit, TemperatureUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, TemperatureUnit> divide(Temperature divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, TimeUnit> times(Time multiplier) {
    return (Mult<MassUnit, TimeUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Velocity<MassUnit> div(Time divisor) {
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
  default Velocity<MassUnit> divide(Time divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, TorqueUnit> times(Torque multiplier) {
    return (Mult<MassUnit, TorqueUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, TorqueUnit> div(Torque divisor) {
    return (Per<MassUnit, TorqueUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, TorqueUnit> divide(Torque divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, VelocityUnit<?>> times(Velocity<?> multiplier) {
    return (Mult<MassUnit, VelocityUnit<?>>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, VelocityUnit<?>> div(Velocity<?> divisor) {
    return (Per<MassUnit, VelocityUnit<?>>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, VelocityUnit<?>> divide(Velocity<?> divisor) {
    return div(divisor);
  }


  @Override
  default Mult<MassUnit, VoltageUnit> times(Voltage multiplier) {
    return (Mult<MassUnit, VoltageUnit>) Measure.super.times(multiplier);
  }

  @Override
  default Per<MassUnit, VoltageUnit> div(Voltage divisor) {
    return (Per<MassUnit, VoltageUnit>) Measure.super.div(divisor);
  }

  /**
  * {@InheritDoc}
  *
  * @deprecated use div instead. This was renamed for consistancy with other languages like Kotlin
  */
  @Deprecated(since = "2025", forRemoval = true)
  @SuppressWarnings({"deprecation", "removal"})
  @Override
  default Per<MassUnit, VoltageUnit> divide(Voltage divisor) {
    return div(divisor);
  }

}
