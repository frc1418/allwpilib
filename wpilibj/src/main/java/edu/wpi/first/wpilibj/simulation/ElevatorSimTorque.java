// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;
import static edu.wpi.first.units.Units.NewtonMeters;
import static edu.wpi.first.units.Units.Newtons;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.system.plant.Wheel;
import edu.wpi.first.units.LinearAccelerationUnit;
import edu.wpi.first.units.LinearVelocityUnit;
import edu.wpi.first.units.TorqueUnit;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearAcceleration;
import edu.wpi.first.units.measure.Per;
import edu.wpi.first.units.measure.Torque;

/** Represents a simulated elevator mechanism controlled by torque input. */
public class ElevatorSimTorque extends ElevatorSimBase {
  /**
   * Creates a simulated elevator mechanism.
   *
   * @param plant The linear system that represents the elevator. This system can be created with
   *     {@link edu.wpi.first.math.system.plant.LinearSystemId#createElevatorSystem(Wheel, double)}
   *     or {@link edu.wpi.first.math.system.plant.LinearSystemId#identifyPositionSystem(double,
   *     double)}. If {@link
   *     edu.wpi.first.math.system.plant.LinearSystemId#identifyPositionSystem(double, double)} is
   *     used, the distance unit must be meters.
   * @param drum The elevator's drum.
   * @param minHeightMeters The min allowable height of the elevator in meters.
   * @param maxHeightMeters The max allowable height of the elevator in meters.
   * @param gMetersPerSecondSquared The acceleration due to gravity in meters per second squared.
   * @param startingHeightMeters The starting height of the elevator in meters.
   * @param measurementStdDevs The standard deviations of the measurements. Can be omitted if no
   *     noise is desired. If present must have 2 elements. The first element is for position. The
   *     second element is for velocity.
   */
  public ElevatorSimTorque(
      LinearSystem<N2, N1, N2> plant,
      Wheel drum,
      double minHeightMeters,
      double maxHeightMeters,
      double gMetersPerSecondSquared,
      double startingHeightMeters,
      double... measurementStdDevs) {
    this(
        plant,
        drum,
        Meters.of(minHeightMeters),
        Meters.of(maxHeightMeters),
        MetersPerSecondPerSecond.of(gMetersPerSecondSquared),
        Meters.of(startingHeightMeters),
        measurementStdDevs);
  }

  /**
   * Creates a simulated elevator mechanism.
   *
   * @param plant The linear system that represents the elevator. This system can be created with
   *     {@link edu.wpi.first.math.system.plant.LinearSystemId#createElevatorSystem(Wheel, double)}
   *     or {@link edu.wpi.first.math.system.plant.LinearSystemId#identifyPositionSystem(double,
   *     double)}. If {@link
   *     edu.wpi.first.math.system.plant.LinearSystemId#identifyPositionSystem(double, double)} is
   *     used, the distance unit must be meters.
   * @param drum The elevator's drum.
   * @param minHeight The min allowable height of the elevator.
   * @param maxHeight The max allowable height of the elevator.
   * @param g The acceleration due to gravity.
   * @param startingHeight The starting height of the elevator.
   * @param measurementStdDevs The standard deviations of the measurements. Can be omitted if no
   *     noise is desired. If present must have 2 elements. The first element is for position. The
   *     second element is for velocity.
   */
  public ElevatorSimTorque(
      LinearSystem<N2, N1, N2> plant,
      Wheel drum,
      Distance minHeight,
      Distance maxHeight,
      LinearAcceleration g,
      Distance startingHeight,
      double... measurementStdDevs) {
    // τ = rF, F = ma, τ = rma.
    // The acceleration a = τ / mr
    // the elevator state-space model with torque as input is:
    //
    // dx/dt = 0 x + (1/mr) u
    // A = 0
    // B = (1/mr)
    //
    // Solve for m.
    //
    // B = (1/mr)
    // m = (1/Br)
    super(
        plant,
        drum,
        Kilograms.of(1.0 / drum.radiusMeters / plant.getB(1, 0)),
        minHeight,
        maxHeight,
        g,
        startingHeight,
        measurementStdDevs);
  }

  /**
   * Creates a simulated elevator mechanism.
   *
   * @param kv The velocity gain of the elevator.
   * @param ka The acceleration gain of the elevator.
   * @param kg The gravity gain of the elevator.
   * @param drum The elevator's drum.
   * @param minHeight The min allowable height of the elevator.
   * @param maxHeight The max allowable height of the elevator.
   * @param startingHeight The starting height of the elevator.
   * @param measurementStdDevs The standard deviations of the measurements. Can be omitted if no
   *     noise is desired. If present must have 2 elements. The first element is for position. The
   *     second element is for velocity.
   */
  public ElevatorSimTorque(
      Per<TorqueUnit, LinearVelocityUnit> kv,
      Per<TorqueUnit, LinearAccelerationUnit> ka,
      Torque kg,
      Wheel drum,
      Distance minHeight,
      Distance maxHeight,
      Distance startingHeight,
      double... measurementStdDevs) {
    this(
        LinearSystemId.identifyPositionSystem(kv.baseUnitMagnitude(), ka.baseUnitMagnitude()),
        drum,
        minHeight,
        maxHeight,
        MetersPerSecondPerSecond.of(-kg.in(NewtonMeters) / ka.baseUnitMagnitude()),
        startingHeight,
        measurementStdDevs);
  }

  /**
   * Creates a simulated elevator mechanism.
   *
   * @param kv The velocity gain of the elevator in Newton-Meters / meters per second.
   * @param ka The acceleration gain of the elevator in Newton-Meters / meters per second squared.
   * @param kg The gravity gain of the elevator in Newton-Meters.
   * @param drum The elevator's drum.
   * @param minHeightMeters The min allowable height of the elevator in meters.
   * @param maxHeightMeters The max allowable height of the elevator in meters.
   * @param startingHeightMeters The starting height of the elevator in meters.
   * @param measurementStdDevs The standard deviations of the measurements. Can be omitted if no
   *     noise is desired. If present must have 2 elements. The first element is for position. The
   *     second element is for velocity.
   */
  public ElevatorSimTorque(
      double kv,
      double ka,
      double kg,
      Wheel drum,
      double minHeightMeters,
      double maxHeightMeters,
      double startingHeightMeters,
      double... measurementStdDevs) {
    this(
        LinearSystemId.identifyPositionSystem(kv, ka),
        drum,
        minHeightMeters,
        maxHeightMeters,
        -kg / ka,
        startingHeightMeters,
        measurementStdDevs);
  }

  /**
   * Sets the input torque for the elevator.
   *
   * @param torqueNM The input torque.
   */
  public void setInputTorque(double torqueNM) {
    setInput(torqueNM);
    // TODO: Need some guidance on clamping.
    m_torque.mut_replace(m_u.get(0, 0), NewtonMeters);
  }

  /**
   * Sets the input torque for the elevator.
   *
   * @param torque The input torque.
   */
  public void setInputTorque(Torque torque) {
    setInputTorque(torque.in(NewtonMeters));
  }

  @Override
  public void update(double dtSeconds) {
    super.update(dtSeconds);
    m_currentDraw.mut_replace(m_drum.currentAmps(getInput(0)) * Math.signum(m_u.get(0, 0)), Amps);
    m_voltage.mut_replace(m_drum.voltage(getInput(0) / m_drum.radiusMeters, m_x.get(1, 0)), Volts);
    m_force.mut_replace(getInput(0) / m_drum.radiusMeters, Newtons);
    m_torque.mut_replace(getInput(0), NewtonMeters);
  }
}
