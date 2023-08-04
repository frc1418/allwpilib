// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <fmt/core.h>

#include "hal/HAL.h"

int main() {
  fmt::print("Hello World\n");
  fmt::print("{}\n", static_cast<int32_t>(HAL_GetRuntimeType()));
}
