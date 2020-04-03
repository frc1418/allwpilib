/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "frc/trajectory/constraint/EllipticalRegionConstraint.h"

#include <limits>

using namespace frc;

EllipticalRegionConstraint::EllipticalRegionConstraint(
    const Translation2d& origin, units::meter_t xWidth, units::meter_t yWidth,
    const Rotation2d& rotation, TrajectoryConstraint* constraint)
    : m_origin(origin),
      m_radii(xWidth / 2.0, yWidth / 2.0),
      m_constraint(constraint) {
  m_radii = m_radii.RotateBy(rotation);
}

units::meters_per_second_t EllipticalRegionConstraint::MaxVelocity(
    const Pose2d& pose, units::curvature_t curvature,
    units::meters_per_second_t velocity) {
  if (IsPoseInRegion(pose)) {
    return m_constraint->MaxVelocity(pose, curvature, velocity);
  } else {
    return units::meters_per_second_t(std::numeric_limits<double>::infinity());
  }
}

TrajectoryConstraint::MinMax EllipticalRegionConstraint::MinMaxAcceleration(
    const Pose2d& pose, units::curvature_t curvature,
    units::meters_per_second_t speed) {
  if (IsPoseInRegion(pose)) {
    return m_constraint->MinMaxAcceleration(pose, curvature, speed);
  } else {
    return {};
  }
}

bool frc::EllipticalRegionConstraint::IsPoseInRegion(const Pose2d& pose) {
  // The region (disk) bounded by the ellipse is given by the equation:
  // ((x-h)^2)/Rx^2) + ((y-k)^2)/Ry^2) <= 1
  // If the inequality is satisfied, then it is inside the ellipse; otherwise
  // it is outside the ellipse.
  // Both sides have been multiplied by Rx^2 * Ry^2 for efficiency reasons.
  return units::math::pow<2>(pose.Translation().X() - m_origin.X()) *
                 units::math::pow<2>(m_radii.Y()) +
             units::math::pow<2>(pose.Translation().Y() - m_origin.Y()) *
                 units::math::pow<2>(m_radii.X()) <=
         units::math::pow<2>(m_radii.X()) * units::math::pow<2>(m_radii.Y());
}
