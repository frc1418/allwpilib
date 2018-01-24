/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import org.junit.Test;

public class PDPDataTest {
  @Test
  public void pdpoDataDoesNotThrow() {
    HAL.initialize(500, 0);
    PDPData data = new PDPData();
    PDPJNI.getAllPDPData(0, data);
    // Nothing we can assert, so just make sure it didn't throw.
  }
}
