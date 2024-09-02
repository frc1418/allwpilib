// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.controller;

import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.controller.proto.SimpleMotorFeedforwardProto;
import edu.wpi.first.math.controller.struct.SimpleMotorFeedforwardStruct;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.util.protobuf.ProtobufSerializable;
import edu.wpi.first.util.struct.StructSerializable;

/** A helper class that computes feedforward outputs for a simple permanent-magnet DC motor. */
public class SimpleMotorFeedforward implements ProtobufSerializable, StructSerializable {
  /** The static gain, in volts. */
  private final double ks;

  /** The velocity gain, in V/(units/s). */
  private final double kv;

  /** The acceleration gain, in V/(units/s²). */
  private final double ka;

  /** The period, in seconds. */
  private final double m_dt;

  // ** The calculated output voltage measure */
  private final MutableMeasure<Voltage> output = mutable(Volts.of(0.0));

  /**
   * Creates a new SimpleMotorFeedforward with the specified gains and period.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param ks The static gain in volts.
   * @param kv The velocity gain in V/(units/s).
   * @param ka The acceleration gain in V/(units/s²).
   * @param dtSeconds The period in seconds.
   * @throws IllegalArgumentException for kv &lt; zero.
   * @throws IllegalArgumentException for ka &lt; zero.
   * @throws IllegalArgumentException for period &le; zero.
   */
  public SimpleMotorFeedforward(double ks, double kv, double ka, double dtSeconds) {
    this.ks = ks;
    this.kv = kv;
    this.ka = ka;
    if (kv < 0.0) {
      throw new IllegalArgumentException("kv must be a non-negative number, got " + kv + "!");
    }
    if (ka < 0.0) {
      throw new IllegalArgumentException("ka must be a non-negative number, got " + ka + "!");
    }
    if (dtSeconds <= 0.0) {
      throw new IllegalArgumentException(
          "period must be a positive number, got " + dtSeconds + "!");
    }
    m_dt = dtSeconds;
  }

  /**
   * Creates a new SimpleMotorFeedforward with the specified gains and period. The period is
   * defaulted to 20 ms.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param ks The static gain in volts.
   * @param kv The velocity gain in V/(units/s).
   * @param ka The acceleration gain in V/(units/s²).
   * @throws IllegalArgumentException for kv &lt; zero.
   * @throws IllegalArgumentException for ka &lt; zero.
   */
  public SimpleMotorFeedforward(double ks, double kv, double ka) {
    this(ks, kv, ka, 0.020);
  }

  /**
   * Creates a new SimpleMotorFeedforward with the specified gains. Acceleration gain is defaulted
   * to zero. The period is defaulted to 20 ms.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param ks The static gain in volts.
   * @param kv The velocity gain in V/(units/s).
   * @throws IllegalArgumentException for kv &lt; zero.
   */
  public SimpleMotorFeedforward(double ks, double kv) {
    this(ks, kv, 0);
  }

  /**
   * Returns the static gain in volts.
   *
   * @return The static gain in volts.
   */
  public double getKs() {
    return ks;
  }

  /**
   * Returns the velocity gain in V/(units/s).
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @return The velocity gain in V/(units/s).
   */
  public double getKv() {
    return kv;
  }

  /**
   * Returns the acceleration gain in V/(units/s²).
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @return The acceleration gain in V/(units/s²).
   */
  public double getKa() {
    return ka;
  }

  /**
   * Returns the period in seconds.
   *
   * @return The period in seconds.
   */
  public double getDt() {
    return m_dt;
  }

  /**
   * Calculates the feedforward from the gains and setpoints.
   *
   * @param velocity The velocity setpoint.
   * @param acceleration The acceleration setpoint.
   * @return The computed feedforward.
   * @deprecated Use the current/next velocity overload instead.
   */
  @SuppressWarnings("removal")
  @Deprecated(forRemoval = true, since = "2025")
  public double calculate(double velocity, double acceleration) {
    return ks * Math.signum(velocity) + kv * velocity + ka * acceleration;
  }

  /**
   * Calculates the feedforward from the gains and velocity setpoint (acceleration is assumed to be
   * zero).
   *
   * @param velocity The velocity setpoint.
   * @return The computed feedforward.
   * @deprecated Use the current/next velocity overload instead.
   */
  @SuppressWarnings("removal")
  @Deprecated(forRemoval = true, since = "2025")
  public double calculate(double velocity) {
    return calculate(velocity, 0);
  }

  /**
   * Calculates the feedforward from the gains and setpoints assuming discrete control when the
   * setpoint does not change.
   *
   * @param <U> The velocity parameter either as distance or angle.
   * @param setpoint The velocity setpoint.
   * @return The computed feedforward.
   */
  public <U extends Unit<U>> Measure<Voltage> calculate(Measure<Velocity<U>> setpoint) {
    return calculate(setpoint, setpoint);
  }

  /**
   * Calculates the feedforward from the gains and setpoints assuming discrete control.
   *
   * <p>Note this method is inaccurate when the velocity crosses 0.
   *
   * @param <U> The velocity parameter either as distance or angle.
   * @param currentVelocity The current velocity setpoint.
   * @param nextVelocity The next velocity setpoint.
   * @return The computed feedforward.
   */
  public <U extends Unit<U>> Measure<Voltage> calculate(
      Measure<Velocity<U>> currentVelocity, Measure<Velocity<U>> nextVelocity) {
    // For a simple DC motor with the model
    //   dx/dt = −kᵥ/kₐ x + 1/kₐ u - kₛ/kₐ sgn(x),
    //
    // where
    //   A = −kᵥ/kₐ
    //   B = 1/kₐ
    //   c = -kₛ/kₐ sgn(x))
    //   A_d = eᴬᵀ
    //   B_d = A⁻¹(eᴬᵀ - I)B
    //   dx/dt = Ax + Bu + c
    //
    // Discretize the affine model.
    //   dx/dt = Ax + Bu + c
    //   dx/dt = Ax + B(u + B⁺c)
    //   xₖ₊₁ = eᴬᵀxₖ + A⁻¹(eᴬᵀ - I)B(uₖ + B⁺cₖ)
    //   xₖ₊₁ = A_d xₖ + B_d (uₖ + B⁺cₖ)
    //   xₖ₊₁ = A_d xₖ + B_d uₖ + B_d B⁺cₖ
    //
    // Solve for uₖ.
    //   B_d uₖ = xₖ₊₁ − A_d xₖ − B_d B⁺cₖ
    //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ − B_d B⁺cₖ)
    //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) − B⁺cₖ
    //
    // Substitute in B assuming sgn(x) is a constant for the duration of the step.
    //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) − kₐ(-(kₛ/kₐ sgn(x)))
    //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₐ(kₛ/kₐ sgn(x))
    //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₛ sgn(x)
    if (ka == 0.0) {
      // Simplify the model when kₐ = 0.
      //
      // Simplify A.
      //   A = −kᵥ/kₐ
      //   As kₐ approaches zero, A approaches -∞.
      //   A = −∞
      //
      // Simplify A_d.
      //   A_d = eᴬᵀ
      //   A_d = exp(−∞)
      //   A_d = 0
      //
      // Simplify B_d.
      //   B_d = A⁻¹(eᴬᵀ - I)B
      //   B_d = A⁻¹((0) - I)B
      //   B_d = A⁻¹(-I)B
      //   B_d = -A⁻¹B
      //   B_d = -(−kᵥ/kₐ)⁻¹(1/kₐ)
      //   B_d = (kᵥ/kₐ)⁻¹(1/kₐ)
      //   B_d = kₐ/kᵥ(1/kₐ)
      //   B_d = 1/kᵥ
      //
      // Substitute these into the feedforward equation.
      //   uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₛ sgn(x)
      //   uₖ = (1/kᵥ)⁺(xₖ₊₁ − (0) xₖ) + kₛ sgn(x)
      //   uₖ = kᵥxₖ₊₁  + kₛ sgn(x)
      output.mut_replace(
          ks * Math.signum(nextVelocity.magnitude()) + kv * nextVelocity.magnitude(), Volts);
      return output;
    } else {
      //   A = −kᵥ/kₐ
      //   B = 1/kₐ
      //   A_d = eᴬᵀ
      //   B_d = A⁻¹(eᴬᵀ - I)B
      double A = -kv / ka;
      double B = 1.0 / ka;
      double A_d = Math.exp(A * m_dt);
      double B_d = 1.0 / A * (A_d - 1.0) * B;
      output.mut_replace(
          ks * Math.signum(currentVelocity.magnitude())
              + 1.0 / B_d * (nextVelocity.magnitude() - A_d * currentVelocity.magnitude()),
          Volts);
      return output;
    }
  }

  /**
   * Calculates the maximum achievable velocity given a maximum voltage supply and an acceleration.
   * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
   * simultaneously achievable - enter the acceleration constraint, and this will give you a
   * simultaneously-achievable velocity constraint.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
   * @param acceleration The acceleration of the motor, in (units/s²).
   * @return The maximum possible velocity in (units/s) at the given acceleration.
   */
  public double maxAchievableVelocity(double maxVoltage, double acceleration) {
    // Assume max velocity is positive
    return (maxVoltage - ks - acceleration * ka) / kv;
  }

  /**
   * Calculates the minimum achievable velocity given a maximum voltage supply and an acceleration.
   * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
   * simultaneously achievable - enter the acceleration constraint, and this will give you a
   * simultaneously-achievable velocity constraint.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
   * @param acceleration The acceleration of the motor, in (units/s²).
   * @return The maximum possible velocity in (units/s) at the given acceleration.
   */
  public double minAchievableVelocity(double maxVoltage, double acceleration) {
    // Assume min velocity is negative, ks flips sign
    return (-maxVoltage + ks - acceleration * ka) / kv;
  }

  /**
   * Calculates the maximum achievable acceleration given a maximum voltage supply and a velocity.
   * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
   * simultaneously achievable - enter the velocity constraint, and this will give you a
   * simultaneously-achievable acceleration constraint.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
   * @param velocity The velocity of the motor, in (units/s).
   * @return The maximum possible acceleration in (units/s²) at the given velocity.
   */
  public double maxAchievableAcceleration(double maxVoltage, double velocity) {
    return (maxVoltage - ks * Math.signum(velocity) - velocity * kv) / ka;
  }

  /**
   * Calculates the minimum achievable acceleration given a maximum voltage supply and a velocity.
   * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
   * simultaneously achievable - enter the velocity constraint, and this will give you a
   * simultaneously-achievable acceleration constraint.
   *
   * <p>The units should be radians for angular systems and meters for linear systems.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
   * @param velocity The velocity of the motor, in (units/s).
   * @return The maximum possible acceleration in (units/s²) at the given velocity.
   */
  public double minAchievableAcceleration(double maxVoltage, double velocity) {
    return maxAchievableAcceleration(-maxVoltage, velocity);
  }

  /** SimpleMotorFeedforward struct for serialization. */
  public static final SimpleMotorFeedforwardStruct struct = new SimpleMotorFeedforwardStruct();

  /** SimpleMotorFeedforward protobuf for serialization. */
  public static final SimpleMotorFeedforwardProto proto = new SimpleMotorFeedforwardProto();
}
