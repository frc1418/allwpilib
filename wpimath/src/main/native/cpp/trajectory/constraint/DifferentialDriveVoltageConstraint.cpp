// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/trajectory/constraint/DifferentialDriveVoltageConstraint.h"

#include <algorithm>
#include <limits>
#include <utility>

#include <wpi/MathExtras.h>

#include "units/math.h"

using namespace frc;

DifferentialDriveVoltageConstraint::DifferentialDriveVoltageConstraint(
    const SimpleMotorFeedforward<units::meter>& feedforward,
    DifferentialDriveKinematics kinematics, units::volt_t maxVoltage)
    : m_feedforward(feedforward),
      m_kinematics(std::move(kinematics)),
      m_maxVoltage(maxVoltage) {}

units::meters_per_second_t DifferentialDriveVoltageConstraint::MaxVelocity(
    const Pose2d& pose, units::curvature_t curvature,
    units::meters_per_second_t velocity) const {
  return units::meters_per_second_t{std::numeric_limits<double>::max()};
}

TrajectoryConstraint::MinMax
DifferentialDriveVoltageConstraint::MinMaxAcceleration(
    const Pose2d& pose, units::curvature_t curvature,
    units::meters_per_second_t speed) const {
  auto wheelSpeeds =
      m_kinematics.ToWheelSpeeds({speed, 0_mps, speed * curvature});

  auto maxWheelSpeed = (std::max)(wheelSpeeds.left, wheelSpeeds.right);
  auto minWheelSpeed = (std::min)(wheelSpeeds.left, wheelSpeeds.right);

  // Calculate maximum/minimum possible accelerations from motor dynamics
  // and max/min wheel speeds
  auto maxWheelAcceleration =
      m_feedforward.MaxAchievableAcceleration(m_maxVoltage, maxWheelSpeed);
  auto minWheelAcceleration =
      m_feedforward.MinAchievableAcceleration(m_maxVoltage, minWheelSpeed);

  // Robot chassis turning on radius = 1/|curvature|.  Outer wheel has radius
  // increased by half of the trackwidth T.  Inner wheel has radius decreased
  // by half of the trackwidth.  Achassis / radius = Aouter / (radius + T/2), so
  // Achassis = Aouter * radius / (radius + T/2) = Aouter / (1 +
  // |curvature|T/2). Inner wheel is similar.

  // sgn(speed) term added to correctly account for which wheel is on
  // outside of turn:
  // If moving forward, max acceleration constraint corresponds to wheel on
  // outside of turn If moving backward, max acceleration constraint corresponds
  // to wheel on inside of turn

  // When velocity is zero, then wheel velocities are uniformly zero (robot
  // cannot be turning on its center) - we have to treat this as a special case,
  // as it breaks the signum function.  Both max and min acceleration are
  // *reduced in magnitude* in this case.

  units::meters_per_second_squared_t maxChassisAcceleration;
  units::meters_per_second_squared_t minChassisAcceleration;

  if (speed == 0_mps) {
    maxChassisAcceleration =
        maxWheelAcceleration /
        (1 + m_kinematics.trackWidth * units::math::abs(curvature) / (2_rad));
    minChassisAcceleration =
        minWheelAcceleration /
        (1 + m_kinematics.trackWidth * units::math::abs(curvature) / (2_rad));
  } else {
    maxChassisAcceleration =
        maxWheelAcceleration /
        (1 + m_kinematics.trackWidth * units::math::abs(curvature) *
                 wpi::sgn(speed) / (2_rad));
    minChassisAcceleration =
        minWheelAcceleration /
        (1 - m_kinematics.trackWidth * units::math::abs(curvature) *
                 wpi::sgn(speed) / (2_rad));
  }

  // When turning about a point inside of the wheelbase (i.e. radius less than
  // half the trackwidth), the inner wheel's direction changes, but the
  // magnitude remains the same.  The formula above changes sign for the inner
  // wheel when this happens. We can accurately account for this by simply
  // negating the inner wheel.

  if ((m_kinematics.trackWidth / 2) > 1_rad / units::math::abs(curvature)) {
    if (speed > 0_mps) {
      minChassisAcceleration = -minChassisAcceleration;
    } else if (speed < 0_mps) {
      maxChassisAcceleration = -maxChassisAcceleration;
    }
  }

  return {minChassisAcceleration, maxChassisAcceleration};
}
