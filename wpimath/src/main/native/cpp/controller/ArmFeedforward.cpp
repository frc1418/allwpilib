// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/controller/ArmFeedforward.h"

#include <limits>

#include <sleipnir/autodiff/Gradient.hpp>
#include <sleipnir/autodiff/Hessian.hpp>

using namespace frc;

units::volt_t ArmFeedforward::Calculate(units::unit_t<Angle> currentAngle,
                                        units::unit_t<Velocity> currentVelocity,
                                        units::unit_t<Velocity> nextVelocity,
                                        units::second_t dt) const {
  using VarMat = sleipnir::VariableMatrix;

  // Arm dynamics
  Matrixd<2, 2> A{{0.0, 1.0}, {0.0, -kV.value() / kA.value()}};
  Matrixd<2, 1> B{{0.0}, {1.0 / kA.value()}};
  const auto& f = [&](const VarMat& x, const VarMat& u) -> VarMat {
    VarMat c{{0.0},
             {-(kS / kA).value() * sleipnir::sign(x(1)) -
              (kG / kA).value() * sleipnir::cos(x(0))}};
    return A * x + B * u + c;
  };

  Vectord<2> r_k{currentAngle.value(), currentVelocity.value()};

  sleipnir::Variable u_k;

  // Initial guess
  auto acceleration = (nextVelocity - currentVelocity) / dt;
  u_k.SetValue((kS * wpi::sgn(currentVelocity.value()) + kV * currentVelocity +
                kA * acceleration + kG * units::math::cos(currentAngle))
                   .value());
  fmt::print("u₀ = {}\n", u_k.Value());

  auto r_k1 = RK4<decltype(f), VarMat, VarMat>(f, r_k, u_k, dt);

  // Minimize difference between desired and actual next velocity
  auto cost =
      (nextVelocity.value() - r_k1(1)) * (nextVelocity.value() - r_k1(1));

  // Refine solution via Newton's method
  auto solveStartTime = std::chrono::system_clock::now();
  {
    auto xAD = u_k;
    double x = xAD.Value();

    sleipnir::Gradient gradientF{cost, xAD};
    Eigen::SparseVector<double> g = gradientF.Value();

    sleipnir::Hessian hessianF{cost, xAD};
    Eigen::SparseMatrix<double> H = hessianF.Value();

    double error = std::numeric_limits<double>::infinity();
    while (error > 1e-8) {
      // Iterate via Newton's method.
      //
      //   xₖ₊₁ = xₖ − H⁻¹g
      //
      // The Hessian is regularized to at least 1e-4.
      double p_x = -g.coeff(0) / std::max(H.coeff(0, 0), 1e-4);

      // Shrink step until cost goes down
      {
        double oldCost = cost.Value();

        double α = 1.0;
        double trial_x = x + α * p_x;

        xAD.SetValue(trial_x);
        cost.Update();

        while (cost.Value() > oldCost) {
          α *= 0.5;
          trial_x = x + α * p_x;

          xAD.SetValue(trial_x);
          cost.Update();
        }

        x = trial_x;
      }

      xAD.SetValue(x);

      g = gradientF.Value();
      H = hessianF.Value();

      error = std::abs(g.coeff(0));
    }
  }
  auto solveEndTime = std::chrono::system_clock::now();

  sleipnir::println("Solve time: {:.3f} ms",
                    std::chrono::duration_cast<std::chrono::microseconds>(
                        solveEndTime - solveStartTime)
                            .count() /
                        1e3);

  fmt::print("uₖ = {}\n", u_k.Value());
  return units::volt_t{u_k.Value()};
}
