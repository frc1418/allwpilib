// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/simulation/DutyCycleEncoderSim.h"  // NOLINT(build/include_order)

#include <hal/HAL.h>

#include "callback_helpers/TestCallbackHelpers.h"
#include "frc/DutyCycleEncoder.h"
#include "gtest/gtest.h"

namespace frc::sim {

TEST(DutyCycleEncoderSimTests, TestSet) {
  HAL_Initialize(500, 0);

  DutyCycleEncoder enc{0};
  DutyCycleEncoderSim sim(enc);

  constexpr units::turn_t TEST_VALUE{5.67};
  sim.Set(TEST_VALUE);
  EXPECT_EQ(TEST_VALUE, enc.Get());
}

TEST(DutyCycleEncoderSimTests, TestSetDistance) {
  HAL_Initialize(500, 0);

  DutyCycleEncoder enc{0};
  DutyCycleEncoderSim sim(enc);
  sim.SetDistance(19.1);
  EXPECT_EQ(19.1, enc.GetDistance());
}

}  // namespace frc::sim
