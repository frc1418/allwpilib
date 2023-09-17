// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.util.Units;
import org.junit.jupiter.api.Test;

class QuaternionTest {
  @Test
  void testInit() {
    // Identity
    var q1 = new Quaternion();
    assertEquals(1.0, q1.getW());
    assertEquals(0.0, q1.getX());
    assertEquals(0.0, q1.getY());
    assertEquals(0.0, q1.getZ());

    // Normalized
    var q2 = new Quaternion(0.5, 0.5, 0.5, 0.5);
    assertEquals(0.5, q2.getW());
    assertEquals(0.5, q2.getX());
    assertEquals(0.5, q2.getY());
    assertEquals(0.5, q2.getZ());

    // Unnormalized
    var q3 = new Quaternion(0.75, 0.3, 0.4, 0.5);
    assertEquals(0.75, q3.getW());
    assertEquals(0.3, q3.getX());
    assertEquals(0.4, q3.getY());
    assertEquals(0.5, q3.getZ());

    q3 = q3.normalize();
    double norm = Math.sqrt(0.75 * 0.75 + 0.3 * 0.3 + 0.4 * 0.4 + 0.5 * 0.5);
    assertEquals(0.75 / norm, q3.getW());
    assertEquals(0.3 / norm, q3.getX());
    assertEquals(0.4 / norm, q3.getY());
    assertEquals(0.5 / norm, q3.getZ());
    assertEquals(
        1.0,
        q3.getW() * q3.getW()
            + q3.getX() * q3.getX()
            + q3.getY() * q3.getY()
            + q3.getZ() * q3.getZ());
  }

  @Test
  void testAddition() {
    var q = new Quaternion(0.1, 0.2, 0.3, 0.4);
    var p = new Quaternion(0.5, 0.6, 0.7, 0.8);

    var sum = q.plus(p);

    assertEquals(q.getW() + p.getW(), sum.getW());
    assertEquals(q.getX() + p.getX(), sum.getX());
    assertEquals(q.getY() + p.getY(), sum.getY());
    assertEquals(q.getZ() + p.getZ(), sum.getZ());
  }

  @Test
  void testScalarMultiplication() {
    var q = new Quaternion(0.1, 0.2, 0.3, 0.4);
    var scalar = 2;

    var product = q.times(scalar);

    assertEquals(q.getW() * scalar, product.getW());
    assertEquals(q.getX() * scalar, product.getX());
    assertEquals(q.getY() * scalar, product.getY());
    assertEquals(q.getZ() * scalar, product.getZ());
  }

  @Test
  void testTimes() {
    // 90° CCW rotations around each axis
    double c = Math.cos(Units.degreesToRadians(90.0) / 2.0);
    double s = Math.sin(Units.degreesToRadians(90.0) / 2.0);
    var xRot = new Quaternion(c, s, 0.0, 0.0);
    var yRot = new Quaternion(c, 0.0, s, 0.0);
    var zRot = new Quaternion(c, 0.0, 0.0, s);

    // 90° CCW X rotation, 90° CCW Y rotation, and 90° CCW Z rotation should
    // produce a 90° CCW Y rotation
    var expected = yRot;
    var actual = zRot.times(yRot).times(xRot);
    assertEquals(expected.getW(), actual.getW(), 1e-9);
    assertEquals(expected.getX(), actual.getX(), 1e-9);
    assertEquals(expected.getY(), actual.getY(), 1e-9);
    assertEquals(expected.getZ(), actual.getZ(), 1e-9);

    // Identity
    var q =
        new Quaternion(
            0.72760687510899891, 0.29104275004359953, 0.38805700005813276, 0.48507125007266594);
    actual = q.times(q.inverse());
    assertEquals(1.0, actual.getW());
    assertEquals(0.0, actual.getX());
    assertEquals(0.0, actual.getY());
    assertEquals(0.0, actual.getZ());
  }

  @Test
  void testConjugate() {
    var q = new Quaternion(0.75, 0.3, 0.4, 0.5);
    var inv = q.conjugate();

    assertEquals(q.getW(), inv.getW());
    assertEquals(-q.getX(), inv.getX());
    assertEquals(-q.getY(), inv.getY());
    assertEquals(-q.getZ(), inv.getZ());
  }

  @Test
  void testInverse() {
    var q = new Quaternion(0.75, 0.3, 0.4, 0.5);
    var inv = q.inverse();
    var norm = q.norm();

    assertEquals(q.getW() / (norm * norm), inv.getW(), 1e-10);
    assertEquals(-q.getX() / (norm * norm), inv.getX(), 1e-10);
    assertEquals(-q.getY() / (norm * norm), inv.getY(), 1e-10);
    assertEquals(-q.getZ() / (norm * norm), inv.getZ(), 1e-10);
  }

  @Test
  void testNorm() {
    var q = new Quaternion(3, 4, 12, 84);

    // pythagorean triples (3, 4, 5), (5, 12, 13), (13, 84, 85)
    assertEquals(q.norm(), 85, 1e-10);
  }

  @Test
  void testExponential() {
    var q = new Quaternion(1.1, 2.2, 3.3, 4.4);
    var q_exp =
        new Quaternion(
            2.81211398529184, -0.392521193481878, -0.588781790222817, -0.785042386963756);

    QuaternionEquals(q_exp, q.exp());
  }

  @Test
  void testLogarithm() {
    var q = new Quaternion(1.1, 2.2, 3.3, 4.4);
    var q_log =
        new Quaternion(1.7959088706354, 0.515190292664085, 0.772785438996128, 1.03038058532817);

    QuaternionEquals(q_log, q.log());

    var zero = new Quaternion(0, 0, 0, 0);
    var one = new Quaternion();
    var i = new Quaternion(0, 1, 0, 0);
    var j = new Quaternion(0, 0, 1, 0);
    var k = new Quaternion(0, 0, 0, 1);

    QuaternionEquals(zero, one.log());
    QuaternionEquals(i.times(Math.PI / 2), i.log());
    QuaternionEquals(j.times(Math.PI / 2), j.log());
    QuaternionEquals(k.times(Math.PI / 2), k.log());
    QuaternionEquals(i.times(Math.PI), one.times(-1).log());
  }

  @Test
  void testLogarithmIsInverseOfExponential() {
    var q = new Quaternion(1.1, 2.2, 3.3, 4.4);

    // These operations are order-dependent: ln(exp(SO(3))) is not congruent to exp(ln(SO(3))).
    // The correct order of operations is the latter, exp(ln(SO(3)))

    var q_log_exp = q.log().exp();

    QuaternionEquals(q, q_log_exp);

    var start = new Quaternion(1, 2, 3, 4);
    var expect = new Quaternion(5, 6, 7, 8);

    var twist = start.log(expect);
    var actual = start.exp(twist);

    QuaternionEquals(expect, actual);
  }

  private void QuaternionEquals(Quaternion expect, Quaternion test) {
    QuaternionEquals(expect, test, 1e-10);
  }

  private void QuaternionEquals(Quaternion expect, Quaternion test, double threshold) {
    assertEquals(expect.getW(), test.getW(), threshold);
    assertEquals(expect.getX(), test.getX(), threshold);
    assertEquals(expect.getY(), test.getY(), threshold);
    assertEquals(expect.getZ(), test.getZ(), threshold);
  }
}
