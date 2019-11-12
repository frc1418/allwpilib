/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <units/units.h>
#include <wpi/MathExtras.h>

namespace frc {
/**
 * A helper class that computes feedforward outputs for a simple arm (modeled as
 * a motor acting against the force of gravity on a beam suspended at an angle).
 */
class ArmFeedforward {
  using Angle = units::radians;
  using Velocity = units::radians_per_second;
  using Acceleration = units::compound_unit<units::radians_per_second,
                                            units::inverse<units::second>>;
  using kv_unit =
      units::compound_unit<units::volts,
                           units::inverse<units::radians_per_second>>;
  using ka_unit =
      units::compound_unit<units::volts, units::inverse<Acceleration>>;

 public:
  constexpr ArmFeedforward() = default;

  /**
   * Creates a new ArmFeedforward with the specified gains.
   *
   * @param kS   The static gain, in volts.
   * @param kCos The gravity gain, in volts.
   * @param kV   The velocity gain, in volt seconds per radian.
   * @param kA   The acceleration gain, in volt seconds^2 per radian.
   */
  constexpr ArmFeedforward(
      units::volt_t kS, units::volt_t kCos, units::unit_t<kv_unit> kV,
      units::unit_t<ka_unit> kA = units::unit_t<ka_unit>(0))
      : m_kS(kS), m_kCos(kCos), m_kV(kV), m_kA(kA) {}

  /**
   * Calculates the feedforward from the gains and setpoints.
   *
   * @param angle        The angle setpoint, in radians.
   * @param velocity     The velocity setpoint, in radians per second.
   * @param acceleration The acceleration setpoint, in radians per second^2.
   * @return The computed feedforward, in volts.
   */
  units::volt_t Calculate(units::unit_t<Angle> angle,
                          units::unit_t<Velocity> velocity,
                          units::unit_t<Acceleration> acceleration =
                              units::unit_t<Acceleration>(0)) const {
    return m_kS * wpi::sgn(velocity) + m_kCos * units::math::cos(angle) +
           m_kV * velocity + m_kA * acceleration;
  }

  units::volt_t kS{0};
  units::volt_t kCos{0};
  units::unit_t<kv_unit> kV{0};
  units::unit_t<ka_unit> kA{0};
};
}  // namespace frc
