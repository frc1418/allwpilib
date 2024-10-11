// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.Gearbox;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.measure.MomentOfInertia;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.RobotController;

/** Represents a simulated DC motor mechanism controlled by voltage input. */
public class DCMotorSim extends DCMotorSimBase {
  /**
   * Creates a simulated DC motor mechanism controlled by voltage input.
   *
   * <p>If using physical constants create the plant using either {@link
   * LinearSystemId#createDCMotorSystem(Gearbox, double)} or {@link
   * LinearSystemId#createDCMotorSystem(Gearbox, MomentOfInertia)}.
   *
   * <p>If using system characterization create the plant using either {@link
   * LinearSystemId#identifyPositionSystem(double, double)} or the units class overload. The
   * distance unit must be radians. The input unit must be volts.
   *
   * @param plant The linear system that represents the simulated DC motor mechanism.
   * @param gearbox The gearbox of the simulated DC motor mechanism.
   * @param measurementStdDevs The standard deviations of the measurements. Can be omitted if no
   *     noise is desired. If present must have 2 elements. The first element is for position. The
   *     second element is for velocity. The units should be radians for position and radians per
   *     second for velocity.
   */
  public DCMotorSim(LinearSystem<N2, N1, N2> plant, Gearbox gearbox, double... measurementStdDevs) {
    // By theorem 6.10.1 of
    // https://file.tavsys.net/control/controls-engineering-in-frc.pdf,
    // the DC motor mechanism state-space model with voltage as input is:
    //
    // dx/dt = -G²Kₜ/(KᵥRJ)x + (GKₜ)/(RJ)u
    // A = -G²Kₜ/(KᵥRJ)
    // B = GKₜ/(RJ)
    //
    // Solve for J.
    //
    // B = GKₜ/(RJ)
    // J = GKₜ/(RB)
    super(
        plant,
        gearbox,
        KilogramSquareMeters.of(
            gearbox.numMotors
                * gearbox.reduction
                * gearbox.motorType.KtNMPerAmp
                / gearbox.motorType.rOhms
                / plant.getB(1, 0)),
        measurementStdDevs);
  }

  /**
   * Sets the input voltage of the simulated DC motor mechanism.
   *
   * @param volts The input voltage in volts.
   */
  public void setInputVoltage(double volts) {
    setInput(volts);
    clampInput(RobotController.getBatteryVoltage());
    m_voltage.mut_replace(m_u.get(0, 0), Volts);
  }

  /**
   * Sets the input voltage of the simulated DC motor mechanism.
   *
   * @param voltage The input voltage.
   */
  public void setInputVoltage(Voltage voltage) {
    setInputVoltage(voltage.in(Volts));
  }

  @Override
  public void update(double dtSeconds) {
    super.update(dtSeconds);
    m_currentDraw.mut_replace(
        m_gearbox.currentAmps(m_x.get(1, 0), getInput(0)) * Math.signum(m_u.get(0, 0)), Amps);
    m_voltage.mut_replace(getInput(0), Volts);
    m_torque.mut_replace(m_gearbox.torque(m_currentDraw));
  }
}
