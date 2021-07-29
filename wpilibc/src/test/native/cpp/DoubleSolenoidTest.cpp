// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <hal/HAL.h>

#include "frc/DoubleSolenoid.h"
#include "frc/PneumaticsControlModule.h"
#include "frc/Solenoid.h"
#include "gtest/gtest.h"

namespace frc {

TEST(DoubleSolenoidTests, testValidInitialization) {
  PneumaticsControlModule pcm(3);
  DoubleSolenoid solenoid(pcm, 2, 3);
  solenoid.Set(DoubleSolenoid::kReverse);
  EXPECT_EQ(DoubleSolenoid::kReverse, solenoid.Get());

  solenoid.Set(DoubleSolenoid::kForward);
  EXPECT_EQ(DoubleSolenoid::kForward, solenoid.Get());

  solenoid.Set(DoubleSolenoid::kOff);
  EXPECT_EQ(DoubleSolenoid::kOff, solenoid.Get());
}

TEST(DoubleSolenoidTests, testThrowForwardPortAlreadyInitialized) {
  PneumaticsControlModule pcm(5);
  // Single solenoid that is reused for forward port
  Solenoid solenoid(pcm, 2);
  EXPECT_THROW(DoubleSolenoid(pcm, 2, 3), std::runtime_error);
}

TEST(DoubleSolenoidTests, testThrowReversePortAlreadyInitialized) {
  PneumaticsControlModule pcm(6);
  // Single solenoid that is reused for forward port
  Solenoid solenoid(pcm, 3);
  EXPECT_THROW(DoubleSolenoid(pcm, 2, 3), std::runtime_error);
}

TEST(DoubleSolenoidTests, testThrowBothPortsAlreadyInitialized) {
  PneumaticsControlModule pcm(6);
  // Single solenoid that is reused for forward port
  Solenoid solenoid0(pcm, 2);
  Solenoid solenoid1(pcm, 3);
  EXPECT_THROW(DoubleSolenoid(pcm, 2, 3), std::runtime_error);
}

TEST(DoubleSolenoidTests, testToggle) {
  PneumaticsControlModule pcm(4);
  DoubleSolenoid solenoid(&pcm, 2, 3);
  // Bootstrap it into reverse
  solenoid.Set(DoubleSolenoid::kReverse);

  solenoid.Toggle();
  EXPECT_EQ(DoubleSolenoid::kForward, solenoid.Get());

  solenoid.Toggle();
  EXPECT_EQ(DoubleSolenoid::kReverse, solenoid.Get());

  // Of shouldn't do anything on toggle
  solenoid.Set(DoubleSolenoid::kOff);
  solenoid.Toggle();
  EXPECT_EQ(DoubleSolenoid::kOff, solenoid.Get());
}

TEST(DoubleSolenoidTests, testInvalidForwardPort) {
  PneumaticsControlModule pcm(0);
  EXPECT_THROW(DoubleSolenoid(pcm, 100, 1), std::runtime_error);
}

TEST(DoubleSolenoidTests, testInvalidReversePort) {
  PneumaticsControlModule pcm(0);
  EXPECT_THROW(DoubleSolenoid(pcm, 0, 100), std::runtime_error);
}
}  // namespace frc
