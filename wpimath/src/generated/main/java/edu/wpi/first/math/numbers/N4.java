// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpimath/generate_numbers.py. DO NOT MODIFY

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/** A class representing the number 4. */
public final class N4 extends Num implements Nat<N4> {
  private N4() {}

  /**
   * The integer this class represents.
   *
   * @return The literal number 4.
   */
  @Override
  public int getNum() {
    return 4;
  }

  /** The singleton instance of this class. */
  public static final N4 instance = new N4();
}
