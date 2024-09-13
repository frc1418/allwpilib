// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <gcem.hpp>
#include <wpi/MathExtras.h>

#include "units/angle.h"
#include "units/length.h"
#include "units/time.h"
#include "units/voltage.h"
#include "wpimath/MathShared.h"

namespace frc {

/**
 * A helper class that computes feedforward voltages for a simple
 * permanent-magnet DC motor.
 */
template <class Distance>
  requires std::same_as<units::meter, Distance> ||
           std::same_as<units::radian, Distance>
class SimpleMotorFeedforward {
 public:
  using Velocity =
      units::compound_unit<Distance, units::inverse<units::seconds>>;
  using Acceleration =
      units::compound_unit<Velocity, units::inverse<units::seconds>>;
  using kv_unit = units::compound_unit<units::volts, units::inverse<Velocity>>;
  using ka_unit =
      units::compound_unit<units::volts, units::inverse<Acceleration>>;

  /**
   * Creates a new SimpleMotorFeedforward with the specified gains.
   *
   * @param kS The static gain, in volts.
   * @param kV The velocity gain, in volt seconds per distance.
   * @param kA The acceleration gain, in volt seconds² per distance.
   * @param dt The period in seconds.
   * @throws IllegalArgumentException for kv &lt; zero.
   * @throws IllegalArgumentException for ka &lt; zero.
   * @throws IllegalArgumentException for period &le; zero.
   */
  constexpr SimpleMotorFeedforward(
      units::volt_t kS, units::unit_t<kv_unit> kV,
      units::unit_t<ka_unit> kA = units::unit_t<ka_unit>(0),
      units::second_t dt = 20_ms)
      : kS(kS), kV(kV), kA(kA), m_dt(dt) {
    if (kV.value() < 0) {
      wpi::math::MathSharedStore::ReportError(
          "kV must be a non-negative number, got {}!", kV.value());
      this->kV = units::unit_t<kv_unit>{0};
      wpi::math::MathSharedStore::ReportWarning("kV defaulted to 0.");
    }
    if (kA.value() < 0) {
      wpi::math::MathSharedStore::ReportError(
          "kA must be a non-negative number, got {}!", kA.value());
      this->kA = units::unit_t<ka_unit>{0};
      wpi::math::MathSharedStore::ReportWarning("kA defaulted to 0.");
    }
    if (dt <= 0_ms) {
      wpi::math::MathSharedStore::ReportError(
          "period must be a positive number, got {}!", dt.value());
      this->m_dt = 20_ms;
      wpi::math::MathSharedStore::ReportWarning("period defaulted to 20 ms.");
    }
  }

  /**
   * Calculates the feedforward from the gains and setpoints.
   *
   * @param velocity     The velocity setpoint.
   * @param acceleration The acceleration setpoint.
   * @return The computed feedforward, in volts.
   * @deprecated Use the current/next velocity overload instead.
   */
  [[deprecated("Use the current/next velocity overload instead.")]]
  constexpr units::volt_t Calculate(
      units::unit_t<Velocity> velocity,
      units::unit_t<Acceleration> acceleration) const {
    return kS * wpi::sgn(velocity) + kV * velocity + kA * acceleration;
  }

  /**
   * Calculates the feedforward from the gains and setpoint assuming discrete
   * control. Use this method when the setpoint does not change.
   *
   * @param setpoint The velocity setpoint.
   * @return The computed feedforward, in volts.
   */
  constexpr units::volt_t Calculate(units::unit_t<Velocity> setpoint) const {
    return Calculate(setpoint, setpoint);
  }

  /**
   * Calculates the feedforward from the gains and setpoints assuming discrete
   * control.
   *
   * <p>Note this method is inaccurate when the velocity crosses 0.
   *
   * @param currentVelocity The current velocity setpoint.
   * @param nextVelocity    The next velocity setpoint.
   * @return The computed feedforward, in volts.
   */
  constexpr units::volt_t Calculate(
      units::unit_t<Velocity> currentVelocity,
      units::unit_t<Velocity> nextVelocity) const {
    // For a simple DC motor with the model
    //
    //  dx/dt = −kᵥ/kₐ x + 1/kₐ u - kₛ/kₐ sgn(x),
    //
    // where
    //
    //  A = −kᵥ/kₐ
    //  B = 1/kₐ
    //  c = -kₛ/kₐ sgn(x))
    //  A_d = eᴬᵀ
    //  B_d = A⁻¹(eᴬᵀ - I)B
    //  dx/dt = Ax + Bu + c
    //
    // Discretize the affine model.
    //
    //  dx/dt = Ax + Bu + c
    //  dx/dt = Ax + B(u + B⁺c)
    //  xₖ₊₁ = eᴬᵀxₖ + A⁻¹(eᴬᵀ - I)B(uₖ + B⁺cₖ)
    //  xₖ₊₁ = A_d xₖ + B_d (uₖ + B⁺cₖ)
    //  xₖ₊₁ = A_d xₖ + B_d uₖ + B_d B⁺cₖ
    //
    // Solve for uₖ.
    //
    //  B_d uₖ = xₖ₊₁ − A_d xₖ − B_d B⁺cₖ
    //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ − B_d B⁺cₖ)
    //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) − B⁺cₖ
    //
    // Substitute in B assuming sgn(x) is a constant for the duration of the step.
    //
    //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) − kₐ(-(kₛ/kₐ sgn(x)))
    //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₐ(kₛ/kₐ sgn(x))
    //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₛ sgn(x)
    if (kA == decltype(kA)(0)) {
      // Simplify the model when kₐ = 0.
      //
      // Simplify A.
      //
      //  A = −kᵥ/kₐ
      //
      // As kₐ approaches zero, A approaches -∞.
      //
      //  A = −∞
      //
      // Simplify A_d.
      //
      //  A_d = eᴬᵀ
      //  A_d = exp(−∞)
      //  A_d = 0
      //
      // Simplify B_d.
      //
      //  B_d = A⁻¹(eᴬᵀ - I)B
      //  B_d = A⁻¹((0) - I)B
      //  B_d = A⁻¹(-I)B
      //  B_d = -A⁻¹B
      //  B_d = -(−kᵥ/kₐ)⁻¹(1/kₐ)
      //  B_d = (kᵥ/kₐ)⁻¹(1/kₐ)
      //  B_d = kₐ/kᵥ(1/kₐ)
      //  B_d = 1/kᵥ
      //
      // Substitute these into the feedforward equation.
      //
      //  uₖ = B_d⁺(xₖ₊₁ − A_d xₖ) + kₛ sgn(x)
      //  uₖ = (1/kᵥ)⁺(xₖ₊₁ − (0) xₖ) + kₛ sgn(x)
      //  uₖ = kᵥxₖ₊₁  + kₛ sgn(x)
      return kS * wpi::sgn(nextVelocity) + kV * nextVelocity;
    } else {
      //  A = −kᵥ/kₐ
      //  B = 1/kₐ
      //  A_d = eᴬᵀ
      //  B_d = A⁻¹(eᴬᵀ - I)B
      double A = -kV.value() / kA.value();
      double B = 1.0 / kA.value();
      double A_d = gcem::exp(A * m_dt.value());
      double B_d = 1.0 / A * (A_d - 1.0) * B;
      return kS * wpi::sgn(currentVelocity) +
             units::volt_t{
                 1.0 / B_d *
                 (nextVelocity.value() - A_d * currentVelocity.value())};
    }
  }

  // Rearranging the main equation from the calculate() method yields the
  // formulas for the methods below:

  /**
   * Calculates the maximum achievable velocity given a maximum voltage supply
   * and an acceleration.  Useful for ensuring that velocity and
   * acceleration constraints for a trapezoidal profile are simultaneously
   * achievable - enter the acceleration constraint, and this will give you
   * a simultaneously-achievable velocity constraint.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor.
   * @param acceleration The acceleration of the motor.
   * @return The maximum possible velocity at the given acceleration.
   */
  constexpr units::unit_t<Velocity> MaxAchievableVelocity(
      units::volt_t maxVoltage,
      units::unit_t<Acceleration> acceleration) const {
    // Assume max velocity is positive
    return (maxVoltage - kS - kA * acceleration) / kV;
  }

  /**
   * Calculates the minimum achievable velocity given a maximum voltage supply
   * and an acceleration.  Useful for ensuring that velocity and
   * acceleration constraints for a trapezoidal profile are simultaneously
   * achievable - enter the acceleration constraint, and this will give you
   * a simultaneously-achievable velocity constraint.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor.
   * @param acceleration The acceleration of the motor.
   * @return The minimum possible velocity at the given acceleration.
   */
  constexpr units::unit_t<Velocity> MinAchievableVelocity(
      units::volt_t maxVoltage,
      units::unit_t<Acceleration> acceleration) const {
    // Assume min velocity is positive, ks flips sign
    return (-maxVoltage + kS - kA * acceleration) / kV;
  }

  /**
   * Calculates the maximum achievable acceleration given a maximum voltage
   * supply and a velocity. Useful for ensuring that velocity and
   * acceleration constraints for a trapezoidal profile are simultaneously
   * achievable - enter the velocity constraint, and this will give you
   * a simultaneously-achievable acceleration constraint.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor.
   * @param velocity The velocity of the motor.
   * @return The maximum possible acceleration at the given velocity.
   */
  constexpr units::unit_t<Acceleration> MaxAchievableAcceleration(
      units::volt_t maxVoltage, units::unit_t<Velocity> velocity) const {
    return (maxVoltage - kS * wpi::sgn(velocity) - kV * velocity) / kA;
  }

  /**
   * Calculates the minimum achievable acceleration given a maximum voltage
   * supply and a velocity. Useful for ensuring that velocity and
   * acceleration constraints for a trapezoidal profile are simultaneously
   * achievable - enter the velocity constraint, and this will give you
   * a simultaneously-achievable acceleration constraint.
   *
   * @param maxVoltage The maximum voltage that can be supplied to the motor.
   * @param velocity The velocity of the motor.
   * @return The minimum possible acceleration at the given velocity.
   */
  constexpr units::unit_t<Acceleration> MinAchievableAcceleration(
      units::volt_t maxVoltage, units::unit_t<Velocity> velocity) const {
    return MaxAchievableAcceleration(-maxVoltage, velocity);
  }

  /**
   * Returns the static gain.
   *
   * @return The static gain.
   */
  units::volt_t GetKs() const { return kS; }

  /**
   * Returns the velocity gain.
   *
   * @return The velocity gain.
   */
  units::unit_t<kv_unit> GetKv() const { return kV; }

  /**
   * Returns the acceleration gain.
   *
   * @return The acceleration gain.
   */
  units::unit_t<ka_unit> GetKa() const { return kA; }

  /**
   * Returns the period.
   *
   * @return The period.
   */
  units::second_t GetDt() const { return m_dt; }

 private:
  /** The static gain. */
  units::volt_t kS;

  /** The velocity gain. */
  units::unit_t<kv_unit> kV;

  /** The acceleration gain. */
  units::unit_t<ka_unit> kA;

  /** The period. */
  units::second_t m_dt;
};

}  // namespace frc
