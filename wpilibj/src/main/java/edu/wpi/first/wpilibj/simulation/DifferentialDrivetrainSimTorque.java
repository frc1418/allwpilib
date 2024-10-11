// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import static edu.wpi.first.units.Units.NewtonMeters;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.Wheel;
import edu.wpi.first.units.measure.Torque;

/**
 * This class simulates the state of the drivetrain controlled by voltage. In simulationPeriodic,
 * users should first set inputs from motors with {@link #setInputTorques(double, double)}, call
 * {@link #update(double)} to update the simulation, and set estimated encoder and gyro positions,
 * as well as estimated odometry pose. Teams can use {@link
 * edu.wpi.first.wpilibj.smartdashboard.Field2d} to visualize their robot on the Sim GUI's field.
 *
 * <p>Our state-space system is:
 *
 * <p>x = [[x, y, theta, vel_l, vel_r, dist_l, dist_r]]ᵀ in the field coordinate system (dist_* are
 * wheel distances.)
 *
 * <p>u = [[voltage_l, voltage_r]]ᵀ This is typically the control input of the last timestep from a
 * LTVDiffDriveController.
 *
 * <p>y = x
 */
public class DifferentialDrivetrainSimTorque extends DifferentialDrivetrainSimBase {
  /**
   * Creates a simulated differential drivetrain.
   *
   * @param plant The {@link LinearSystem} representing the robot's drivetrain. This system can be
   *     created with {@link
   *     edu.wpi.first.math.system.plant.LinearSystemId#createDrivetrainVelocitySystem(Wheel,
   *     double, double, double)} or {@link
   *     edu.wpi.first.math.system.plant.LinearSystemId#identifyDrivetrainSystem(double, double,
   *     double, double)}.
   * @param driveWheel A {@link Wheel} representing one of the drivetrain's wheels.
   * @param trackWidthMeters The distance between the two sides of the drivetrain. Can be found with
   *     SysId.
   * @param measurementStdDevs Standard deviations for measurements, in the form [x, y, heading,
   *     left velocity, right velocity, left distance, right distance]ᵀ. Can be null if no noise is
   *     desired. Gyro standard deviations of 0.0001 radians, velocity standard deviations of 0.05
   *     m/s, and position measurement standard deviations of 0.005 meters are a reasonable starting
   *     point.
   */
  public DifferentialDrivetrainSimTorque(
      LinearSystem<N2, N2, N2> plant,
      Wheel driveWheel,
      double trackWidthMeters,
      double... measurementStdDevs) {
    super(plant, driveWheel, trackWidthMeters, measurementStdDevs);
  }

  /**
   * Sets the applied torque to the drivetrain. Note that positive torque must make that side of the
   * drivetrain travel forward (+X).
   *
   * @param leftTorqueNewtonMeters The left torque in Newton-Meters.
   * @param rightTorqueNewtonMeters The right torque in Newton-Meters.
   */
  public void setInputTorques(double leftTorqueNewtonMeters, double rightTorqueNewtonMeters) {
    m_u.set(0, 0, leftTorqueNewtonMeters);
    m_u.set(1, 0, rightTorqueNewtonMeters);
    // TODO: not sure how to clamp these.
  }

  /**
   * Sets the applied torque to the drivetrain. Note that positive torque must make that side of the
   * drivetrain travel forward (+X).
   *
   * @param leftTorque The left torque.
   * @param rightTorque The right torque.
   */
  public void setInputTorques(Torque leftTorque, Torque rightTorque) {
    setInputTorques(leftTorque.in(NewtonMeters), rightTorque.in(NewtonMeters));
  }

  @Override
  public void update(double dtSeconds) {
    super.update(dtSeconds);
    m_leftVoltage.mut_replace(m_u.get(0, 0), Volts); // TODO: do something here.
    m_rightVoltage.mut_replace(m_u.get(1, 0), Volts); // TODO: do something here.
    m_leftTorque.mut_replace(m_u.get(0, 0), NewtonMeters);
    m_rightTorque.mut_replace(m_u.get(1, 0), NewtonMeters);
  }
}
