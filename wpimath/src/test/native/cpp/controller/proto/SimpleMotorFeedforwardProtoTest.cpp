// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "../../ProtoTestBase.h"
#include "frc/controller/SimpleMotorFeedforward.h"

using namespace frc;

struct SimpleMotorFeedforwardProtoTestData {
  using Type = SimpleMotorFeedforward<units::meters>;

  inline static const Type kTestData = {units::volt_t{0.4},
                                        units::volt_t{4.0} / 1_mps,
                                        units::volt_t{0.7} / 1_mps_sq};

  static void CheckEq(const Type& testData, const Type& data) {
    EXPECT_EQ(testData.kS.value(), data.kS.value());
    EXPECT_EQ(testData.kV.value(), data.kV.value());
    EXPECT_EQ(testData.kA.value(), data.kA.value());
  }
};

INSTANTIATE_TYPED_TEST_SUITE_P(SimpleMotorFeedforwardMeters, ProtoTest,
                               SimpleMotorFeedforwardProtoTestData);
