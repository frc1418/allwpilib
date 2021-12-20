// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <algorithm>

#include <wpi/sendable/Sendable.h>
#include <wpi/sendable/SendableBuilder.h>
#include <wpi/sendable/SendableHelper.h>
#include <wpi/timestamp.h>

#include "units/time.h"

namespace frc {
/**
 * A class that limits the rate of change of an input value.  Useful for
 * implementing voltage, setpoint, and/or output ramps.  A slew-rate limit
 * is most appropriate when the quantity being controlled is a velocity or
 * a voltage; when controlling a position, consider using a TrapezoidProfile
 * instead.
 *
 * @see TrapezoidProfile
 */
template <class Unit>
class SlewRateLimiter : public wpi::Sendable,
                        public wpi::SendableHelper<SlewRateLimiter<Unit>> {
 public:
  using Unit_t = units::unit_t<Unit>;
  using Rate = units::compound_unit<Unit, units::inverse<units::seconds>>;
  using Rate_t = units::unit_t<Rate>;

  /**
   * Creates a new SlewRateLimiter with the given rate limit and initial value.
   *
   * @param rateLimit The rate-of-change limit.
   * @param initialValue The initial value of the input.
   */
  explicit SlewRateLimiter(Rate_t rateLimit, Unit_t initialValue = Unit_t{0})
      : m_rateLimit{rateLimit},
        m_prevVal{initialValue},
        m_prevTime{units::microsecond_t(wpi::Now())} {}

  /**
   * Filters the input to limit its slew rate.
   *
   * @param input The input value whose slew rate is to be limited.
   * @return The filtered value, which will not change faster than the slew
   * rate.
   */
  Unit_t Calculate(Unit_t input) {
    m_input = input;
    units::second_t currentTime = units::microsecond_t(wpi::Now());
    units::second_t elapsedTime = currentTime - m_prevTime;
    m_prevVal += std::clamp(input - m_prevVal, -m_rateLimit * elapsedTime,
                            m_rateLimit * elapsedTime);
    m_prevTime = currentTime;
    return m_prevVal;
  }

  /**
   * Resets the slew rate limiter to the specified value; ignores the rate limit
   * when doing so.
   *
   * @param value The value to reset to.
   */
  void Reset(Unit_t value) {
    m_prevVal = value;
    m_prevTime = units::microsecond_t(wpi::Now());
  }

  void InitSendable(wpi::SendableBuilder& builder) override {
    builder.SetSmartDashboardType("SlewRateLimiter");
    builder.AddDoubleProperty(
        "rateLimit", [&] { return m_rateLimit.value(); }, nullptr);
    builder.AddDoubleProperty(
        "input", [&] { return m_input.value(); }, nullptr);
    builder.AddDoubleProperty(
        "output", [&] { return m_prevVal.value(); }, nullptr);
  }

 private:
  Rate_t m_rateLimit;
  Unit_t m_prevVal;
  Unit_t m_input;
  units::second_t m_prevTime;
};
}  // namespace frc
