/*----------------------------------------------------------------------------*/
/* Copyright (c) 2008-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "frc/PWMVictorSPX.h"

#include <hal/FRCUsageReporting.h>

#include "frc/smartdashboard/SendableRegistry.h"

using namespace frc;

PWMVictorSPX::PWMVictorSPX(int channel) : PWMSpeedController(channel) {
  SetBounds(2.004, 1.52, 1.50, 1.48, 0.997);
  SetPeriodMultiplier(kPeriodMultiplier_1X);
  SetSpeed(0.0);
  SetZeroLatch();

  HAL_Report(HALUsageReporting::kResourceType_PWMVictorSPX, GetChannel() + 1);
  SendableRegistry::GetInstance().SetName(this, "PWMVictorSPX", GetChannel());
}
