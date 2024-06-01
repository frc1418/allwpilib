// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include "frc/geometry/Ellipse2d.h"

TEST(Ellipse2dTest, FocalPoints) {
  constexpr frc::Pose2d center{1_m, 2_m, 0_deg};
  constexpr frc::Ellipse2d ellipse{center, 5_m, 4_m};

  const auto [a, b] = ellipse.FocalPoints();

  EXPECT_EQ(frc::Translation2d(-2_m, 2_m), a);
  EXPECT_EQ(frc::Translation2d(4_m, 2_m), b);
}

TEST(Ellipse2dTest, IntersectsPoint) {
  constexpr frc::Pose2d center{1_m, 2_m, 0_deg};
  constexpr frc::Ellipse2d ellipse{center, 2_m, 1_m};

  constexpr frc::Translation2d pointA{1_m, 3_m};
  constexpr frc::Translation2d pointB{0_m, 3_m};

  EXPECT_TRUE(ellipse.Intersects(pointA));
  EXPECT_FALSE(ellipse.Intersects(pointB));
}

TEST(Ellipse2dTest, ContainsPoint) {
  constexpr frc::Pose2d center{-1_m, -2_m, 45_deg};
  constexpr frc::Ellipse2d ellipse{center, 2_m, 1_m};

  constexpr frc::Translation2d pointA{0_m, -1_m};
  constexpr frc::Translation2d pointB{0.5_m, -2_m};

  EXPECT_TRUE(ellipse.Contains(pointA));
  EXPECT_FALSE(ellipse.Contains(pointB));
}

TEST(Ellipse2dTest, DistanceToPoint) {
  constexpr double kEpsilon = 1E-9;

  constexpr frc::Pose2d center{1_m, 2_m, 270_deg};
  constexpr frc::Ellipse2d ellipse{center, 1_m, 2_m};

  constexpr frc::Translation2d point1{2.5_m, 2_m};
  constexpr frc::Translation2d point2{1_m, 2_m};
  constexpr frc::Translation2d point3{1_m, 1_m};
  constexpr frc::Translation2d point4{-1_m, 2.5_m};

  EXPECT_NEAR(0.5, ellipse.Distance(point1).value(), kEpsilon);
  EXPECT_NEAR(0, ellipse.Distance(point2).value(), kEpsilon);
  EXPECT_NEAR(0.5, ellipse.Distance(point3).value(), kEpsilon);
  EXPECT_NEAR(1, ellipse.Distance(point4).value(), kEpsilon);
}

TEST(Ellipse2dTest, FindNearestPoint) {
  constexpr double kEpsilon = 1E-9;

  constexpr frc::Pose2d center{1_m, 1_m, 90_deg};
  constexpr frc::Ellipse2d ellipse{center, 3_m, 4_m};

  constexpr frc::Translation2d point1{1_m, 3_m};
  auto nearestPoint1 = ellipse.FindNearestPoint(point1);

  frc::Translation2d point2{0_m, 0_m};
  auto nearestPoint2 = ellipse.FindNearestPoint(point2);

  EXPECT_NEAR(1.0, nearestPoint1.X().value(), kEpsilon);
  EXPECT_NEAR(2.5, nearestPoint1.Y().value(), kEpsilon);
  EXPECT_NEAR(0.0, nearestPoint2.X().value(), kEpsilon);
  EXPECT_NEAR(0.0, nearestPoint2.Y().value(), kEpsilon);
}

TEST(Ellipse2dTest, Equals) {
  constexpr frc::Pose2d center1{1_m, 2_m, 90_deg};
  constexpr frc::Ellipse2d ellipse1{center1, 2_m, 3_m};

  constexpr frc::Pose2d center2{1_m, 2_m, 90_deg};
  constexpr frc::Ellipse2d ellipse2{center2, 2_m, 3_m};

  constexpr frc::Pose2d center3{1_m, 2_m, 90_deg};
  constexpr frc::Ellipse2d ellipse3{center3, 3_m, 2_m};

  EXPECT_EQ(ellipse1, ellipse2);
  EXPECT_NE(ellipse1, ellipse3);
  EXPECT_NE(ellipse3, ellipse2);
}
