// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.wpilib.math.MatBuilder;
import org.wpilib.math.Matrix;
import org.wpilib.math.Nat;
import org.wpilib.math.VecBuilder;
import org.wpilib.math.numbers.N1;
import org.wpilib.math.numbers.N2;
import org.junit.jupiter.api.Test;

class LinearPlantInversionFeedforwardTest {
  @Test
  void testCalculate() {
    Matrix<N2, N2> A = MatBuilder.fill(Nat.N2(), Nat.N2(), 1, 0, 0, 1);
    Matrix<N2, N1> B = VecBuilder.fill(0, 1);

    LinearPlantInversionFeedforward<N2, N1, N1> feedforward =
        new LinearPlantInversionFeedforward<>(A, B, 0.02);

    assertEquals(
        47.502599,
        feedforward.calculate(VecBuilder.fill(2, 2), VecBuilder.fill(3, 3)).get(0, 0),
        0.002);
  }
}
