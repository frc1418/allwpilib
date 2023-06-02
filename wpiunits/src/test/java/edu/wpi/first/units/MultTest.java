package edu.wpi.first.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class MultTest {
  @Test
  void testAutomaticNames() {
    var unitA = new ExampleUnit(1, "Ay", "a");
    var unitB = new ExampleUnit(1, "Bee", "b");
    var mult = Mult.combine(unitA, unitB);
    assertEquals("Ay-Bee", mult.name());
    assertEquals("a*b", mult.symbol());
  }

  @Test
  void testCombine() {
    var unitA = new ExampleUnit(100);
    var unitB = new ExampleUnit(0.912);
    var mult = Mult.combine(unitA, unitB);
    assertEquals(91.2, mult.toBaseUnits(1));
  }

  @Test
  void testCaches() {
    var unitA = new ExampleUnit(1);
    var unitB = new ExampleUnit(2);
    var mult = Mult.combine(unitA, unitB);
    assertSame(mult, Mult.combine(unitA, unitB));
  }
}
