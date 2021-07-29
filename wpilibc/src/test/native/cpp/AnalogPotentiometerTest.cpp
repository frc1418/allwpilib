// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <hal/HAL.h>

#include "frc/AnalogPotentiometer.h"
#include "frc/simulation/AnalogInputSim.h"
#include "frc/simulation/RoboRioSim.h"
#include "gtest/gtest.h"

namespace frc {
using namespace frc::sim;
TEST(AnalogPotentiometerTests, testInitializeWithAnalogInput) {
  HAL_Initialize(500, 0);

  AnalogInput ai(0);
  AnalogPotentiometer pot(&ai);
  AnalogInputSim sim(ai);

  RoboRioSim::ResetData();
  sim.SetVoltage(4.0);
  EXPECT_EQ(.8, pot.Get());
}

TEST(AnalogPotentiometerTests, testInitializeWithAnalogInputAndScale) {
  HAL_Initialize(500, 0);

  AnalogInput ai(0);
  AnalogPotentiometer pot(&ai, 270.0);
  RoboRioSim::ResetData();
  AnalogInputSim sim(ai);

  sim.SetVoltage(5.0);
  EXPECT_EQ(270.0, pot.Get());

  sim.SetVoltage(2.5);
  EXPECT_EQ(135, pot.Get());

  sim.SetVoltage(0.0);
  EXPECT_EQ(0.0, pot.Get());
}

TEST(AnalogPotentiometerTests, testInitializeWithChannel) {
  HAL_Initialize(500, 0);

  AnalogPotentiometer pot(1);
  RoboRioSim::ResetData();
  AnalogInputSim sim(1);

  sim.SetVoltage(5.0);
  EXPECT_EQ(1.0, pot.Get());
}

TEST(AnalogPotentiometerTests, testInitializeWithChannelAndScale) {
  HAL_Initialize(500, 0);

  AnalogPotentiometer pot(1, 180.0);
  RoboRioSim::ResetData();
  AnalogInputSim sim(1);

  sim.SetVoltage(5.0);
  EXPECT_EQ(180.0, pot.Get());

  sim.SetVoltage(0.0);
  EXPECT_EQ(0.0, pot.Get());
}

TEST(AnalogPotentiometerTests, testWithModifiedBatteryVoltage) {
  AnalogPotentiometer pot(1, 180.0, 90.0);
  RoboRioSim::ResetData();
  AnalogInputSim sim(1);

  // Test at 5v
  sim.SetVoltage(5.0);
  EXPECT_EQ(270, pot.Get());

  sim.SetVoltage(0.0);
  EXPECT_EQ(90, pot.Get());

  // Simulate a lower battery voltage
  RoboRioSim::SetUserVoltage5V(units::volt_t{2.5});

  sim.SetVoltage(2.5);
  EXPECT_EQ(270, pot.Get());

  sim.SetVoltage(2.0);
  EXPECT_EQ(234, pot.Get());

  sim.SetVoltage(0.0);
  EXPECT_EQ(90, pot.Get());
}
}  // namespace frc
