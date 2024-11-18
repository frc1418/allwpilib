// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.examples.elevatorsimulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.wpilib.hal.HAL;
import org.wpilib.wpilibj.simulation.DriverStationSim;
import org.wpilib.wpilibj.simulation.EncoderSim;
import org.wpilib.wpilibj.simulation.JoystickSim;
import org.wpilib.wpilibj.simulation.PWMSim;
import org.wpilib.wpilibj.simulation.RoboRioSim;
import org.wpilib.wpilibj.simulation.SimHooks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

@ResourceLock("timing")
class ElevatorSimulationTest {
  private Robot m_robot;
  private Thread m_thread;

  private PWMSim m_motorSim;
  private EncoderSim m_encoderSim;
  private JoystickSim m_joystickSim;

  @BeforeEach
  void startThread() {
    HAL.initialize(500, 0);
    SimHooks.pauseTiming();
    DriverStationSim.resetData();
    m_robot = new Robot();
    m_thread = new Thread(m_robot::startCompetition);
    m_encoderSim = EncoderSim.createForChannel(Constants.kEncoderAChannel);
    m_motorSim = new PWMSim(Constants.kMotorPort);
    m_joystickSim = new JoystickSim(Constants.kJoystickPort);

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
    m_encoderSim.resetData();
    m_motorSim.resetData();
    RoboRioSim.resetData();
    DriverStationSim.resetData();
    DriverStationSim.notifyNewData();
  }

  @Test
  void teleopTest() {
    // teleop init
    {
      DriverStationSim.setAutonomous(false);
      DriverStationSim.setEnabled(true);
      DriverStationSim.notifyNewData();

      assertTrue(m_motorSim.getInitialized());
      assertTrue(m_encoderSim.getInitialized());
    }

    {
      // advance 50 timesteps
      SimHooks.stepTiming(1);

      // Ensure elevator is still at 0.
      assertEquals(0.0, m_encoderSim.getDistance(), 0.05);
    }

    {
      // Press button to reach setpoint
      m_joystickSim.setTrigger(true);
      m_joystickSim.notifyNewData();

      // advance 75 timesteps
      SimHooks.stepTiming(1.5);

      assertEquals(Constants.kSetpointMeters, m_encoderSim.getDistance(), 0.05);

      // advance 25 timesteps to see setpoint is held.
      SimHooks.stepTiming(0.5);

      assertEquals(Constants.kSetpointMeters, m_encoderSim.getDistance(), 0.05);
    }

    {
      // Unpress the button to go back down
      m_joystickSim.setTrigger(false);
      m_joystickSim.notifyNewData();

      // advance 75 timesteps
      SimHooks.stepTiming(1.5);

      assertEquals(0.0, m_encoderSim.getDistance(), 0.05);
    }

    {
      // Press button to go back up
      m_joystickSim.setTrigger(true);
      m_joystickSim.notifyNewData();

      // advance 75 timesteps
      SimHooks.stepTiming(1.5);

      assertEquals(Constants.kSetpointMeters, m_encoderSim.getDistance(), 0.05);

      // advance 25 timesteps to see setpoint is held.
      SimHooks.stepTiming(0.5);

      assertEquals(Constants.kSetpointMeters, m_encoderSim.getDistance(), 0.05);
    }

    {
      // Disable
      DriverStationSim.setAutonomous(false);
      DriverStationSim.setEnabled(false);
      DriverStationSim.notifyNewData();

      // advance 75 timesteps
      SimHooks.stepTiming(1.5);

      assertEquals(0.0, m_motorSim.getSpeed(), 0.05);
      assertEquals(0.0, m_encoderSim.getDistance(), 0.05);
    }
  }
}
