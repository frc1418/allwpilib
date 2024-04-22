package edu.wpi.first.wpilibj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.simulation.GenericHIDSim;
import org.junit.jupiter.api.Test;

class GenericHIDTest {
  private static final double kEpsilon = 0.0001;

  @Test
  void testRumbleRange() {
    GenericHID hid = new GenericHID(0);
    GenericHIDSim sim = new GenericHIDSim(0);

    for (int i = 0; i <= 100; i++) {
      double rumbleValue = i / 100.0;
      hid.setRumble(RumbleType.kBothRumble, (i / 100.0));
      assertEquals(sim.getRumble(RumbleType.kLeftRumble), rumbleValue, kEpsilon);
      assertEquals(sim.getRumble(RumbleType.kRightRumble), rumbleValue, kEpsilon);
    }
  }

  @Test
  void testRumbleTypes() {
    GenericHID hid = new GenericHID(0);
    GenericHIDSim sim = new GenericHIDSim(0);

    // Make sure both are off
    hid.setRumble(RumbleType.kBothRumble, 0);
    assertEquals(sim.getRumble(RumbleType.kBothRumble), 0, kEpsilon);

    // test both
    hid.setRumble(RumbleType.kBothRumble, 1);
    assertEquals(sim.getRumble(RumbleType.kLeftRumble), 1, kEpsilon);
    assertEquals(sim.getRumble(RumbleType.kRightRumble), 1, kEpsilon);
    hid.setRumble(RumbleType.kBothRumble, 0);

    // test left only
    hid.setRumble(RumbleType.kLeftRumble, 1);
    assertEquals(sim.getRumble(RumbleType.kLeftRumble), 1, kEpsilon);
    assertEquals(sim.getRumble(RumbleType.kRightRumble), 0, kEpsilon);
    hid.setRumble(RumbleType.kLeftRumble, 0);

    // test right only
    hid.setRumble(RumbleType.kRightRumble, 1);
    assertEquals(sim.getRumble(RumbleType.kLeftRumble), 0, kEpsilon);
    assertEquals(sim.getRumble(RumbleType.kRightRumble), 1, kEpsilon);
    hid.setRumble(RumbleType.kRightRumble, 0);
  }
}
