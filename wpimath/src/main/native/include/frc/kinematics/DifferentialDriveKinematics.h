// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/SymbolExports.h>

#include "frc/geometry/Twist2d.h"
#include "frc/kinematics/ChassisSpeeds.h"
#include "frc/kinematics/DifferentialDriveWheelSpeeds.h"
#include "units/angle.h"
#include "units/length.h"
#include "wpimath/MathShared.h"

namespace frc {
/**
 * Helper class that converts a chassis velocity (dx and dtheta components) to
 * left and right wheel velocities for a differential drive.
 *
 * Inverse kinematics converts a desired chassis speed into left and right
 * velocity components whereas forward kinematics converts left and right
 * component velocities into a linear and angular chassis speed.
 * @see <a
 * href="https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/differential-drive-kinematics.html">DifferentialDriveKinematics
 * on frc-docs</a>
 */
class WPILIB_DLLEXPORT DifferentialDriveKinematics {
 public:
  /**
   * Constructs a differential drive kinematics object.
   *
   * @param trackWidth The track width of the drivetrain. Theoretically, this is
   * the distance between the left wheels and right wheels. However, the
   * empirical value may be larger than the physical measured value due to
   * scrubbing effects.
   */
  explicit DifferentialDriveKinematics(units::meter_t trackWidth)
      : trackWidth(trackWidth) {
    wpi::math::MathSharedStore::ReportUsage(
        wpi::math::MathUsageId::kKinematics_DifferentialDrive, 1);
  }

  /**
   * Returns a chassis speed from left and right component velocities using
   * forward kinematics.
   *
   * @param wheelSpeeds The left and right velocities.
   * @return The chassis speed.
   */
  constexpr ChassisSpeeds ToChassisSpeeds(
      const DifferentialDriveWheelSpeeds& wheelSpeeds) const {
    return {(wheelSpeeds.left + wheelSpeeds.right) / 2.0, 0_mps,
            (wheelSpeeds.right - wheelSpeeds.left) / trackWidth * 1_rad};
  }

  /**
   * Returns left and right component velocities from a chassis speed using
   * inverse kinematics.
   *
   * @param chassisSpeeds The linear and angular (dx and dtheta) components that
   * represent the chassis' speed.
   * @return The left and right velocities.
   */
  constexpr DifferentialDriveWheelSpeeds ToWheelSpeeds(
      const ChassisSpeeds& chassisSpeeds) const {
    return {chassisSpeeds.vx - trackWidth / 2 * chassisSpeeds.omega / 1_rad,
            chassisSpeeds.vx + trackWidth / 2 * chassisSpeeds.omega / 1_rad};
  }

  /**
   * Returns a twist from left and right distance deltas using
   * forward kinematics.
   *
   * @param leftDistance The distance measured by the left encoder.
   * @param rightDistance The distance measured by the right encoder.
   * @return The resulting Twist2d.
   */
  constexpr Twist2d ToTwist2d(const units::meter_t leftDistance,
                              const units::meter_t rightDistance) const {
    return {(leftDistance + rightDistance) / 2, 0_m,
            (rightDistance - leftDistance) / trackWidth * 1_rad};
  }

  units::meter_t trackWidth;
};
}  // namespace frc
