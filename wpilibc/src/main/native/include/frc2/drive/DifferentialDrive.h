// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <wpi/raw_ostream.h>

#include "frc/smartdashboard/Sendable.h"
#include "frc/smartdashboard/SendableHelper.h"
#include "frc2/drive/RobotDriveBase.h"

namespace frc {
class SpeedController;
}  // namespace frc

namespace frc2 {

/**
 * A class for driving differential drive/skid-steer drive platforms such as
 * the Kit of Parts drive base, "tank drive", or West Coast Drive.
 *
 * These drive bases typically have drop-center / skid-steer with two or more
 * wheels per side (e.g., 6WD or 8WD). This class takes a SpeedController per
 * side. For four and six motor drivetrains, construct and pass in
 * SpeedControllerGroup instances as follows.
 *
 * Four motor drivetrain:
 * @code{.cpp}
 * class Robot {
 *  public:
 *   frc::Spark m_frontLeft{1};
 *   frc::Spark m_rearLeft{2};
 *   frc::SpeedControllerGroup m_left{m_frontLeft, m_rearLeft};
 *
 *   frc::Spark m_frontRight{3};
 *   frc::Spark m_rearRight{4};
 *   frc::SpeedControllerGroup m_right{m_frontRight, m_rearRight};
 *
 *   frc::DifferentialDrive m_drive{m_left, m_right};
 * };
 * @endcode
 *
 * Six motor drivetrain:
 * @code{.cpp}
 * class Robot {
 *  public:
 *   frc::Spark m_frontLeft{1};
 *   frc::Spark m_midLeft{2};
 *   frc::Spark m_rearLeft{3};
 *   frc::SpeedControllerGroup m_left{m_frontLeft, m_midLeft, m_rearLeft};
 *
 *   frc::Spark m_frontRight{4};
 *   frc::Spark m_midRight{5};
 *   frc::Spark m_rearRight{6};
 *   frc::SpeedControllerGroup m_right{m_frontRight, m_midRight, m_rearRight};
 *
 *   frc::DifferentialDrive m_drive{m_left, m_right};
 * };
 * @endcode
 *
 * A differential drive robot has left and right wheels separated by an
 * arbitrary width.
 *
 * Drive base diagram:
 * <pre>
 * |_______|
 * | |   | |
 *   |   |
 * |_|___|_|
 * |       |
 * </pre>
 *
 * Each Drive() function provides different inverse kinematic relations for a
 * differential drive robot. Note that motor direction inversion by the user is
 * usually unnecessary.
 *
 * This library uses the NED axes convention (North-East-Down as external
 * reference in the world frame):
 * http://www.nuclearprojects.com/ins/images/axis_big.png.
 *
 * The positive X axis points ahead, the positive Y axis points to the right,
 * and the positive Z axis points down. Rotations follow the right-hand rule, so
 * clockwise rotation around the Z axis is positive.
 */
class DifferentialDrive : public RobotDriveBase,
                          public frc::Sendable,
                          public frc::SendableHelper<DifferentialDrive> {
 public:
  /**
   * Construct a DifferentialDrive.
   *
   * To pass multiple motors per side, use a SpeedControllerGroup. If a motor
   * needs to be inverted, do so before passing it in.
   *
   * @param leftMotor Left motor.
   * @param rightMotor Right motor.
   */
  DifferentialDrive(frc::SpeedController& leftMotor,
                    frc::SpeedController& rightMotor);

  ~DifferentialDrive() override = default;

  DifferentialDrive(DifferentialDrive&&) = default;
  DifferentialDrive& operator=(DifferentialDrive&&) = default;

  /**
   * Arcade drive method for differential drive platform.
   *
   * Note: Some drivers may prefer inverted rotation controls. This can be done
   * by negating the value passed for rotation.
   *
   * @param xSpeed       The speed at which the robot should drive along the X
   *                     axis [-1.0..1.0]. Forward is positive.
   * @param zRotation    The rotation rate of the robot around the Z axis
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   *                     [-1.0..1.0]. Clockwise is positive.
   */
  void ArcadeDrive(double xSpeed, double zRotation, bool squareInputs = true);

  /**
   * Curvature drive method for differential drive platform.
   *
   * The rotation argument controls the curvature of the robot's path rather
   * than its rate of heading change. This makes the robot more controllable at
   * high speeds. Constant-curvature turning can be overridden for turn-in-place
   * maneuvers.
   *
   * @param xSpeed           The robot's speed along the X axis [-1.0..1.0].
   *                         Forward is positive.
   * @param zRotation        The robot's rotation rate around the Z axis
   *                         [-1.0..1.0]. Clockwise is positive.
   * @param allowTurnInPlace If set, overrides constant-curvature turning for
   *                         turn-in-place maneuvers.
   */
  void CurvatureDrive(double xSpeed, double zRotation, bool allowTurnInPlace);

  /**
   * Tank drive method for differential drive platform.
   *
   * @param leftSpeed     The robot left side's speed along the X axis
   *                      [-1.0..1.0]. Forward is positive.
   * @param rightSpeed    The robot right side's speed along the X axis
   *                      [-1.0..1.0]. Forward is positive.
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   *                     [-1.0..1.0]. Clockwise is positive.
   */
  void TankDrive(double leftSpeed, double rightSpeed, bool squareInputs = true);

  void InitSendable(frc::SendableBuilder& builder) override;

 private:
  frc::SpeedController* m_leftMotor;
  frc::SpeedController* m_rightMotor;
};

}  // namespace frc2
