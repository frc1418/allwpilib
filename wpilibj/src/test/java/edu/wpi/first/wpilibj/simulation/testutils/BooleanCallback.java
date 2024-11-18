// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.simulation.testutils;

import org.wpilib.hal.HALValue;

public class BooleanCallback extends CallbackHelperBase<Boolean> {
  @Override
  public void callback(String name, HALValue value) {
    if (value.getType() != HALValue.kBoolean) {
      throw new IllegalArgumentException("Wrong callback for type " + value.getType());
    }

    m_wasTriggered = true;
    m_setValue = value.getBoolean();
  }
}
