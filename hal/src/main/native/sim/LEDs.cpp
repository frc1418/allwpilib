// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "hal/LEDs.h"

#include "hal/simulation/RoboRioData.h"

namespace hal::init {
void InitializeLEDs() {}
}  // namespace hal::init

void HAL_SetRadioLEDState(HAL_RadioLEDState state, int32_t* status) {
  HALSIM_SetRoboRioRadioLEDState(state);
}
