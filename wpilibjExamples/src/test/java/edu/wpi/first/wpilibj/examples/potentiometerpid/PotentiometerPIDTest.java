// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.examples.potentiometerpid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.wpilib.hal.HAL;
import org.wpilib.hal.HAL.SimPeriodicBeforeCallback;
import org.wpilib.math.system.plant.DCMotor;
import org.wpilib.math.util.Units;
import org.wpilib.wpilibj.RobotController;
import org.wpilib.wpilibj.simulation.AnalogInputSim;
import org.wpilib.wpilibj.simulation.DriverStationSim;
import org.wpilib.wpilibj.simulation.ElevatorSim;
import org.wpilib.wpilibj.simulation.JoystickSim;
import org.wpilib.wpilibj.simulation.PWMSim;
import org.wpilib.wpilibj.simulation.SimHooks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

@ResourceLock("timing")
class PotentiometerPIDTest {
  private final DCMotor m_elevatorGearbox = DCMotor.getVex775Pro(4);
  private static final double kElevatorGearing = 10.0;
  private static final double kElevatorDrumRadius = Units.inchesToMeters(2.0);
  private static final double kCarriageMassKg = 4.0; // kg

  private Robot m_robot;
  private Thread m_thread;

  private ElevatorSim m_elevatorSim;
  private PWMSim m_motorSim;
  private AnalogInputSim m_analogSim;
  private SimPeriodicBeforeCallback m_callback;
  private JoystickSim m_joystickSim;

  @BeforeEach
  void startThread() {
    HAL.initialize(500, 0);
    SimHooks.pauseTiming();
    DriverStationSim.resetData();
    m_robot = new Robot();
    m_thread = new Thread(m_robot::startCompetition);
    m_elevatorSim =
        new ElevatorSim(
            m_elevatorGearbox,
            kElevatorGearing,
            kCarriageMassKg,
            kElevatorDrumRadius,
            0.0,
            Robot.kFullHeightMeters,
            true,
            0);
    m_analogSim = new AnalogInputSim(Robot.kPotChannel);
    m_motorSim = new PWMSim(Robot.kMotorChannel);
    m_joystickSim = new JoystickSim(Robot.kJoystickChannel);

    m_callback =
        HAL.registerSimPeriodicBeforeCallback(
            () -> {
              m_elevatorSim.setInputVoltage(
                  m_motorSim.getSpeed() * RobotController.getBatteryVoltage());
              m_elevatorSim.update(0.02);

              /*
              meters = (v / 5v) * range
              meters / range = v / 5v
              5v * (meters / range) = v
               */
              m_analogSim.setVoltage(
                  RobotController.getVoltage5V()
                      * (m_elevatorSim.getPositionMeters() / Robot.kFullHeightMeters));
            });

    m_thread.start();
    SimHooks.stepTiming(0.0); // Wait for Notifiers
  }

  @AfterEach
  void stopThread() {
    m_robot.endCompetition();
    try {
      m_thread.interrupt();
      m_thread.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    m_robot.close();
    m_callback.close();
    m_analogSim.resetData();
    m_motorSim.resetData();
  }

  @Test
  void teleopTest() {
    // teleop init
    {
      DriverStationSim.setAutonomous(false);
      DriverStationSim.setEnabled(true);
      DriverStationSim.notifyNewData();

      assertTrue(m_motorSim.getInitialized());
      assertTrue(m_analogSim.getInitialized());
    }

    // first setpoint
    {
      // advance 50 timesteps
      SimHooks.stepTiming(1);

      assertEquals(Robot.kSetpointsMeters[0], m_elevatorSim.getPositionMeters(), 0.1);
    }

    // second setpoint
    {
      // press button to advance setpoint
      m_joystickSim.setTrigger(true);
      m_joystickSim.notifyNewData();

      // advance 50 timesteps
      SimHooks.stepTiming(1);

      assertEquals(Robot.kSetpointsMeters[1], m_elevatorSim.getPositionMeters(), 0.1);
    }

    // we need to unpress the button
    {
      m_joystickSim.setTrigger(false);
      m_joystickSim.notifyNewData();

      // advance 10 timesteps
      SimHooks.stepTiming(0.2);
    }

    // third setpoint
    {
      // press button to advance setpoint
      m_joystickSim.setTrigger(true);
      m_joystickSim.notifyNewData();

      // advance 50 timesteps
      SimHooks.stepTiming(1);

      assertEquals(Robot.kSetpointsMeters[2], m_elevatorSim.getPositionMeters(), 0.1);
    }

    // we need to unpress the button
    {
      m_joystickSim.setTrigger(false);
      m_joystickSim.notifyNewData();

      // advance 10 timesteps
      SimHooks.stepTiming(0.2);
    }

    // rollover: first setpoint
    {
      // press button to advance setpoint
      m_joystickSim.setTrigger(true);
      m_joystickSim.notifyNewData();

      // advance 60 timesteps
      SimHooks.stepTiming(1.2);

      assertEquals(Robot.kSetpointsMeters[0], m_elevatorSim.getPositionMeters(), 0.1);
    }
  }
}
