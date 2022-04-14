// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <gtest/gtest.h>

#include <cmath>

#include "Eigen/Core"
#include "frc/controller/LinearQuadraticRegulator.h"
#include "frc/system/LinearSystem.h"
#include "frc/system/plant/DCMotor.h"
#include "frc/system/plant/LinearSystemId.h"
#include "units/time.h"

namespace frc {

TEST(LinearQuadraticRegulatorTest, ElevatorGains) {
  LinearSystem<2, 1, 1> plant = [] {
    auto motors = DCMotor::Vex775Pro(2);

    // Carriage mass
    constexpr auto m = 5_kg;

    // Radius of pulley
    constexpr auto r = 0.0181864_m;

    // Gear ratio
    constexpr double G = 40.0 / 40.0;

    return frc::LinearSystemId::ElevatorSystem(motors, m, r, G);
  }();
  Eigen::Matrix<double, 1, 2> K =
      LinearQuadraticRegulator<2, 1>{plant, {0.02, 0.4}, {12.0}, 5.05_ms}.K();

  EXPECT_NEAR(522.15314269, K(0, 0), 1e-6);
  EXPECT_NEAR(38.20138596, K(0, 1), 1e-6);
}

TEST(LinearQuadraticRegulatorTest, ArmGains) {
  LinearSystem<2, 1, 1> plant = [] {
    auto motors = DCMotor::Vex775Pro(2);

    // Carriage mass
    constexpr auto m = 4_kg;

    // Radius of pulley
    constexpr auto r = 0.4_m;

    // Gear ratio
    constexpr double G = 100.0;

    return frc::LinearSystemId::SingleJointedArmSystem(
        motors, 1.0 / 3.0 * m * r * r, G);
  }();

  Eigen::Matrix<double, 1, 2> K =
      LinearQuadraticRegulator<2, 1>{plant, {0.01745, 0.08726}, {12.0}, 5.05_ms}
          .K();

  EXPECT_NEAR(19.16, K(0, 0), 1e-1);
  EXPECT_NEAR(3.32, K(0, 1), 1e-1);
}

TEST(LinearQuadraticRegulatorTest, FourMotorElevator) {
  LinearSystem<2, 1, 1> plant = [] {
    auto motors = DCMotor::Vex775Pro(4);

    // Carriage mass
    constexpr auto m = 8_kg;

    // Radius of pulley
    constexpr auto r = 0.75_in;

    // Gear ratio
    constexpr double G = 14.67;

    return frc::LinearSystemId::ElevatorSystem(motors, m, r, G);
  }();
  Eigen::Matrix<double, 1, 2> K =
      LinearQuadraticRegulator<2, 1>{plant, {0.1, 0.2}, {12.0}, 20_ms}.K();

  EXPECT_NEAR(10.38, K(0, 0), 1e-1);
  EXPECT_NEAR(0.69, K(0, 1), 1e-1);
}

/**
 * Returns feedback control gain for implicit model following.
 *
 * This is used to test the QRN overload of LQR.
 *
 * @tparam States Number of states.
 * @tparam Inputs Number of inputs.
 * @param A State matrix.
 * @param B Input matrix.
 * @param Q State cost matrix.
 * @param R Input cost matrix.
 * @param Aref Desired state matrix.
 * @param dt Discretization timestep.
 */
template <int States, int Inputs>
Eigen::Matrix<double, Inputs, States> GetImplicitModelFollowingK(
    const Eigen::Matrix<double, States, States>& A,
    const Eigen::Matrix<double, States, Inputs>& B,
    const Eigen::Matrix<double, States, States>& Q,
    const Eigen::Matrix<double, Inputs, Inputs>& R,
    const Eigen::Matrix<double, States, States>& Aref, units::second_t dt) {
  // Discretize real dynamics
  Eigen::Matrix<double, States, States> discA;
  Eigen::Matrix<double, States, Inputs> discB;
  DiscretizeAB<States, Inputs>(A, B, dt, &discA, &discB);

  // Discretize desired dynamics
  Eigen::Matrix<double, States, States> discAref;
  DiscretizeA<States>(Aref, dt, &discAref);

  Eigen::Matrix<double, States, States> Qimf =
      (discA - discAref).transpose() * Q * (discA - discAref);
  Eigen::Matrix<double, Inputs, Inputs> Rimf =
      discB.transpose() * Q * discB + R;
  Eigen::Matrix<double, States, Inputs> Nimf =
      (discA - discAref).transpose() * Q * discB;

  return LinearQuadraticRegulator<States, Inputs>{A, B, Qimf, Rimf, Nimf, dt}
      .K();
}

TEST(LinearQuadraticRegulatorTest, MatrixOverloadsWithSingleIntegrator) {
  Eigen::Matrix<double, 2, 2> A{Eigen::Matrix<double, 2, 2>::Zero()};
  Eigen::Matrix<double, 2, 2> B{Eigen::Matrix<double, 2, 2>::Identity()};
  Eigen::Matrix<double, 2, 2> Q{Eigen::Matrix<double, 2, 2>::Identity()};
  Eigen::Matrix<double, 2, 2> R{Eigen::Matrix<double, 2, 2>::Identity()};

  // QR overload
  Eigen::Matrix<double, 2, 2> K =
      LinearQuadraticRegulator<2, 2>{A, B, Q, R, 5_ms}.K();
  EXPECT_NEAR(0.99750312499512261, K(0, 0), 1e-10);
  EXPECT_NEAR(0.0, K(0, 1), 1e-10);
  EXPECT_NEAR(0.0, K(1, 0), 1e-10);
  EXPECT_NEAR(0.99750312499512261, K(1, 1), 1e-10);

  // QRN overload
  Eigen::Matrix<double, 2, 2> N{Eigen::Matrix<double, 2, 2>::Identity()};
  Eigen::Matrix<double, 2, 2> Kimf =
      LinearQuadraticRegulator<2, 2>{A, B, Q, R, N, 5_ms}.K();
  EXPECT_NEAR(1.0, Kimf(0, 0), 1e-10);
  EXPECT_NEAR(0.0, Kimf(0, 1), 1e-10);
  EXPECT_NEAR(0.0, Kimf(1, 0), 1e-10);
  EXPECT_NEAR(1.0, Kimf(1, 1), 1e-10);
}

TEST(LinearQuadraticRegulatorTest, MatrixOverloadsWithDoubleIntegrator) {
  double Kv = 3.02;
  double Ka = 0.642;

  Eigen::Matrix<double, 2, 2> A{{0, 1}, {0, -Kv / Ka}};
  Eigen::Matrix<double, 2, 1> B{{0}, {1.0 / Ka}};
  Eigen::Matrix<double, 2, 2> Q{{1, 0}, {0, 0.2}};
  Eigen::Matrix<double, 1, 1> R{0.25};

  // QR overload
  Eigen::Matrix<double, 1, 2> K =
      LinearQuadraticRegulator<2, 1>{A, B, Q, R, 5_ms}.K();
  EXPECT_NEAR(1.9960017786537287, K(0, 0), 1e-10);
  EXPECT_NEAR(0.51182128351092726, K(0, 1), 1e-10);

  // QRN overload
  Eigen::Matrix<double, 2, 2> Aref{{0, 1}, {0, -Kv / (Ka * 2.0)}};
  Eigen::Matrix<double, 1, 2> Kimf =
      GetImplicitModelFollowingK<2, 1>(A, B, Q, R, Aref, 5_ms);
  EXPECT_NEAR(0.0, Kimf(0, 0), 1e-10);
  EXPECT_NEAR(-5.367540084534802e-05, Kimf(0, 1), 1e-10);
}

TEST(LinearQuadraticRegulatorTest, LatencyCompensate) {
  LinearSystem<2, 1, 1> plant = [] {
    auto motors = DCMotor::Vex775Pro(4);

    // Carriage mass
    constexpr auto m = 8_kg;

    // Radius of pulley
    constexpr auto r = 0.75_in;

    // Gear ratio
    constexpr double G = 14.67;

    return frc::LinearSystemId::ElevatorSystem(motors, m, r, G);
  }();
  LinearQuadraticRegulator<2, 1> controller{plant, {0.1, 0.2}, {12.0}, 0.02_s};

  controller.LatencyCompensate(plant, 0.02_s, 0.01_s);

  EXPECT_NEAR(8.97115941, controller.K(0, 0), 1e-3);
  EXPECT_NEAR(0.07904881, controller.K(0, 1), 1e-3);
}

}  // namespace frc
