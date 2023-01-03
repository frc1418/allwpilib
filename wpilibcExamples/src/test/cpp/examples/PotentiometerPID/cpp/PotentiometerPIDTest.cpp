// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include <string>
#include <thread>

#include <frc/RobotController.h>
#include <hal/simulation/MockHooks.h>
#include <frc/simulation/DriverStationSim.h>
#include <frc/system/plant/DCMotor.h>
#include <frc/simulation/SimHooks.h>
#include <frc/simulation/PWMSim.h>
#include <frc/simulation/ElevatorSim.h>
#include <frc/simulation/AnalogInputSim.h>
#include <frc/simulation/JoystickSim.h>
#include <units/time.h>
#include <units/length.h>
#include <units/mass.h>

#include "Robot.h"

void callback(void* param) {
  auto fixture = reinterpret_cast<PotentiometerPIDTest*>(param);
  fixture->SimPeriodicBefore();
}

class PotentiometerPIDTest : public testing::Test {
  frc::DCMotor m_elevatorGearbox = frc::DCMotor::Vex775Pro(4);
  static constexpr double kElevatorGearing = 10.0;
  static constexpr units::meter_t kElevatorDrumRadius = 2.0_in;
  static constexpr units::kilogram_t kCarriageMass = 4.0_kg;

  Robot m_robot;
  std::optional<std::thread> m_thread;

 protected:
  frc::sim::ElevatorSim m_elevatorSim{m_elevatorGearbox,
                                      kElevatorGearing,
                                      kCarriageMass,
                                      kElevatorDrumRadius,
                                      0.0_m,
                                      Robot::kFullHeight,
                                      true};
  frc::sim::PWMSim m_motorSim{Robot::kMotorChannel};
  frc::sim::AnalogInputSim m_analogSim{Robot::kPotChannel};
  frc::sim::JoystickSim m_joystickSim{Robot::kJoystickChannel};
  int32_t m_callback;
  int32_t m_port;

 public:
  void SimPeriodicBefore() {
    m_elevatorSim.SetInputVoltage(m_motorSim.GetSpeed() *
                                  frc::RobotController::GetBatteryVoltage());
    m_elevatorSim.Update(20_ms);

    /*
    meters = (v / 5v) * range
    meters / range = v / 5v
    5v * (meters / range) = v
     */
    m_analogSim.SetVoltage(
        (frc::RobotController::GetVoltage5V() *
         (m_elevatorSim.GetPosition().value() / Robot::kFullHeight))
            .value());
  }

  void SetUp() override {
    frc::sim::PauseTiming();
    frc::sim::DriverStationSim::ResetData();

    m_callback = HALSIM_RegisterSimPeriodicBeforeCallback(callback, this);

    m_thread = std::thread([&] { m_robot.StartCompetition(); });
    frc::sim::StepTiming(0.0_ms);  // Wait for Notifiers
  }

  void TearDown() override {
    m_robot.EndCompetition();
    m_thread->join();

    HALSIM_CancelSimPeriodicBeforeCallback(m_callback);
    m_analogSim.ResetData();
    m_motorSim.ResetData();
  }
};

TEST_F(PotentiometerPIDTest, Teleop) {
  // teleop init
  {
    frc::sim::DriverStationSim::SetAutonomous(false);
    frc::sim::DriverStationSim::SetEnabled(true);
    frc::sim::DriverStationSim::NotifyNewData();

    EXPECT_TRUE(m_motorSim.GetInitialized());
    EXPECT_TRUE(m_analogSim.GetInitialized());
  }

  // first setpoint
  {
    // advance 50 timesteps
    frc::sim::StepTiming(1_s);

    EXPECT_NEAR(Robot::kSetpoints[0].value(),
                m_elevatorSim.GetPosition().value(), 0.1);
  }

  // second setpoint
  {
    // press button to advance setpoint
    m_joystickSim.SetTrigger(true);
    m_joystickSim.NotifyNewData();

    // advance 50 timesteps
    frc::sim::StepTiming(1_s);

    EXPECT_NEAR(Robot::kSetpoints[1].value(),
                m_elevatorSim.GetPosition().value(), 0.1);
  }

  // we need to unpress the button
  {
    m_joystickSim.SetTrigger(false);
    m_joystickSim.NotifyNewData();

    // advance 10 timesteps
    frc::sim::StepTiming(0.2_s);
  }

  // third setpoint
  {
    // press button to advance setpoint
    m_joystickSim.SetTrigger(true);
    m_joystickSim.NotifyNewData();

    // advance 50 timesteps
    frc::sim::StepTiming(1_s);

    EXPECT_NEAR(Robot::kSetpoints[2].value(),
                m_elevatorSim.GetPosition().value(), 0.1);
  }

  // we need to unpress the button
  {
    m_joystickSim.SetTrigger(false);
    m_joystickSim.NotifyNewData();

    // advance 10 timesteps
    frc::sim::StepTiming(0.2_s);
  }

  // rollover: first setpoint
  {
    // press button to advance setpoint
    m_joystickSim.SetTrigger(true);
    m_joystickSim.NotifyNewData();

    // advance 60 timesteps
    frc::sim::StepTiming(1.2_s);
    EXPECT_NEAR(Robot::kSetpoints[0].value(),
                m_elevatorSim.GetPosition().value(), 0.1);
  }
}