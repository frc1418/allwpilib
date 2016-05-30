/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include "PWMSpeedController.h"

/**
 * REV Robotics Speed Controller
 */
class Spark : public PWMSpeedController {
 public:
  explicit Spark(int channel);
  virtual ~Spark() = default;
};
