// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "../../ProtoTestBase.h"
#include "frc/spline/QuinticHermiteSpline.h"

using namespace frc;

struct QuinticHermiteSplineProtoTestData {
  using Type = QuinticHermiteSpline;

  inline static const Type kTestData{wpi::array<double, 3>{{0.01, 0.02, 0.03}},
                                     wpi::array<double, 3>{{0.04, 0.05, 0.06}},
                                     wpi::array<double, 3>{{0.07, 0.08, 0.09}},
                                     wpi::array<double, 3>{{0.10, 0.11, 0.11}}};

  static void CheckEq(const Type& testData, const Type& data) {
    EXPECT_EQ(testData.xInitialControlVector, data.xInitialControlVector);
    EXPECT_EQ(testData.xFinalControlVector, data.xFinalControlVector);
    EXPECT_EQ(testData.yInitialControlVector, data.yInitialControlVector);
    EXPECT_EQ(testData.yFinalControlVector, data.yFinalControlVector);
  }
};

INSTANTIATE_TYPED_TEST_SUITE_P(QuinticHermiteSpline, ProtoTest,
                               QuinticHermiteSplineProtoTestData);
