// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 5.
*/
public final class N5 extends Num implements Nat<N5> {
  private N5() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 5.
  */
  @Override
  public int getNum() {
    return 5;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N5 instance = new N5();
}
