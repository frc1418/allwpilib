/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2014-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include "ColorImage.h"

namespace frc {

/**
 * A color image represented in HSL color space at 3 bytes per pixel.
 */
class HSLImage : public ColorImage {
 public:
  HSLImage();
  explicit HSLImage(const char* fileName);
  virtual ~HSLImage() = default;
};

}  // namespace frc
