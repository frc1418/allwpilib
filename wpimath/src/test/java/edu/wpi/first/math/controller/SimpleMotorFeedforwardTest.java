// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.controller;

import static edu.wpi.first.units.Units.RadiansPerSecond;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.units.MutableMeasure;
import org.junit.jupiter.api.Test;

class SimpleMotorFeedforwardTest {
  @Test
  void testCalculate() {
    double Ks = 0.5;
    double Kv = 3.0;
    double Ka = 0.6;
    double dt = 0.02;

    var A = MatBuilder.fill(Nat.N1(), Nat.N1(), -Kv / Ka);
    var B = MatBuilder.fill(Nat.N1(), Nat.N1(), 1.0 / Ka);

    var plantInversion = new LinearPlantInversionFeedforward<N1, N1, N1>(A, B, dt);
    var simpleMotor = new SimpleMotorFeedforward(Ks, Kv, Ka);

    var r = VecBuilder.fill(2.0);
    var nextR = VecBuilder.fill(3.0);
    var currentVelocity = MutableMeasure.ofBaseUnits(2.0, RadiansPerSecond);
    var nextVelocity = MutableMeasure.ofBaseUnits(3.0, RadiansPerSecond);

    assertEquals(
        37.52499583432516 + 0.5, simpleMotor.calculate(currentVelocity, nextVelocity), 0.002);
    assertEquals(
        plantInversion.calculate(r, nextR).get(0, 0) + Ks,
        simpleMotor.calculate(currentVelocity, nextVelocity),
        0.002);

    // These won't match exactly. It's just an approximation to make sure they're
    // in the same ballpark.
    assertEquals(
        plantInversion.calculate(r, nextR).get(0, 0) + Ks,
        simpleMotor.calculate(currentVelocity, nextVelocity),
        2.0);
  }
}
