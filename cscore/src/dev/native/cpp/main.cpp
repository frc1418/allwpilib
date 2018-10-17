/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <iostream>

#include "cscore.h"

int main() {
  std::cout << cs::GetHostname() << std::endl;

  int32_t status = 0;
  auto parameter = cs::EnumerateUsbCameras(&status);
  for (auto&& cam : parameter) {
    std::cout << cam.name << "\n";
  }
}
