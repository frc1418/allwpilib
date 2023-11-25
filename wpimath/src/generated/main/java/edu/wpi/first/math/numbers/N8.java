// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 8.
*/
public final class N8 extends Num implements Nat<N8> {
  private N8() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 8.
  */
  @Override
  public int getNum() {
    return 8;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N8 instance = new N8();
}
