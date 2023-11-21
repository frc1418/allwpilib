// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 15.
*/
public final class N15 extends Num implements Nat<N15> {
  private N15() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 15.
  */
  @Override
  public int getNum() {
    return 15;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N15 instance = new N15();
}
