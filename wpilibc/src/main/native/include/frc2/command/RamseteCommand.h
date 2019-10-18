/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <functional>
#include <memory>

#include <units/units.h>

#include "CommandBase.h"
#include "CommandHelper.h"
#include "frc/Timer.h"
#include "frc/controller/PIDController.h"
#include "frc/controller/RamseteController.h"
#include "frc/geometry/Pose2d.h"
#include "frc/kinematics/DifferentialDriveKinematics.h"
#include "frc/trajectory/Trajectory.h"

#pragma once

namespace frc2 {
/**
 * A command that uses a RAMSETE controller  to follow a trajectory
 * with a differential drive.
 *
 * <p>The command handles trajectory-following, PID calculations, and
 * feedforwards internally.  This is intended to be a more-or-less "complete
 * solution" that can be used by teams without a great deal of controls
 * expertise.
 *
 * <p>Advanced teams seeking more flexibility (for example, those who wish to
 * use the onboard PID functionality of a "smart" motor controller) may use the
 * secondary constructor that omits the PID and feedforward functionality,
 * returning only the raw wheel speeds from the RAMSETE controller.
 *
 * @see RamseteController
 * @see Trajectory
 */
class RamseteCommand : public CommandHelper<CommandBase, RamseteCommand> {
  using voltsecondspermeter =
      units::compound_unit<units::voltage::volt, units::second,
                           units::inverse<units::meter>>;
  using voltsecondssquaredpermeter =
      units::compound_unit<units::voltage::volt, units::squared<units::second>,
                           units::inverse<units::meter>>;

 public:
  /**
   * Constructs a new RamseteCommand that, when executed, will follow the
   * provided trajectory. PID control and feedforward are handled internally,
   * and outputs are scaled -1 to 1 for easy consumption by speed controllers.
   *
   * <p>Note: The controller will *not* set the outputVolts to zero upon
   * completion of the path - this is left to the user, since it is not
   * appropriate for paths with nonstationary endstates.
   *
   * @param trajectory                     The trajectory to follow.
   * @param pose                           A function that supplies the robot
   * pose - use one of the odometry classes to provide this.
   * @param follower                       The RAMSETE follower used to follow
   * the trajectory.
   * @param ks                             Constant feedforward term for the
   * robot drive.
   * @param kv                             Velocity-proportional feedforward
   * term for the robot drive.
   * @param ka                             Acceleration-proportional feedforward
   * term for the robot drive.
   * @param kinematics                     The kinematics for the robot
   * drivetrain.
   * @param leftSpeed                      A function that supplies the speed of
   * the left side of the robot drive.
   * @param rightSpeed                     A function that supplies the speed of
   * the right side of the robot drive.
   * @param leftController                 The PIDController for the left side
   * of the robot drive.
   * @param rightController                The PIDController for the right side
   * of the robot drive.
   * @param output                         A function that consumes the computed
   * left and right outputs (in volts) for the robot drive.
   */
  RamseteCommand(
      frc::Trajectory trajectory, std::function<frc::Pose2d()> pose,
      frc::RamseteController follower, units::voltage::volt_t ks,
      units::unit_t<voltsecondspermeter> kv,
      units::unit_t<voltsecondssquaredpermeter> ka,
      frc::DifferentialDriveKinematics kinematics,
      std::function<units::meters_per_second_t()> leftSpeed,
      std::function<units::meters_per_second_t()> rightSpeed,
      frc2::PIDController leftController, frc2::PIDController rightController,
      std::function<void(units::voltage::volt_t, units::voltage::volt_t)>
          output);

  /**
   * Constructs a new RamseteCommand that, when executed, will follow the
   * provided trajectory. Performs no PID control and calculates no
   * feedforwards; outputs are the raw wheel speeds from the RAMSETE controller,
   * and will need to be converted into a usable form by the user.
   *
   * @param trajectory            The trajectory to follow.
   * @param pose                  A function that supplies the robot pose - use
   * one of the odometry classes to provide this.
   * @param follower              The RAMSETE follower used to follow the
   * trajectory.
   * @param kinematics            The kinematics for the robot drivetrain.
   * @param output                A function that consumes the computed left and
   * right wheel speeds.
   */
  RamseteCommand(frc::Trajectory trajectory, std::function<frc::Pose2d()> pose,
                 frc::RamseteController follower,
                 frc::DifferentialDriveKinematics kinematics,
                 std::function<void(units::meters_per_second_t,
                                    units::meters_per_second_t)>
                     output);

  void Initialize() override;

  void Execute() override;

  void End(bool interrupted) override;

  bool IsFinished() override;

 private:
  frc::Trajectory m_trajectory;
  std::function<frc::Pose2d()> m_pose;
  frc::RamseteController m_follower;
  const units::voltage::volt_t m_ks;
  const units::unit_t<voltsecondspermeter> m_kv;
  const units::unit_t<voltsecondssquaredpermeter> m_ka;
  frc::DifferentialDriveKinematics m_kinematics;
  std::function<units::meters_per_second_t()> m_leftSpeed;
  std::function<units::meters_per_second_t()> m_rightSpeed;
  std::unique_ptr<frc2::PIDController> m_leftController;
  std::unique_ptr<frc2::PIDController> m_rightController;
  std::function<void(units::voltage::volt_t, units::voltage::volt_t)>
      m_outputVolts;
  std::function<void(units::meters_per_second_t, units::meters_per_second_t)>
      m_outputVel;

  frc::Timer m_timer;
  units::second_t m_prevTime;
  frc::DifferentialDriveWheelSpeeds m_prevSpeeds;
};
}  // namespace frc2
