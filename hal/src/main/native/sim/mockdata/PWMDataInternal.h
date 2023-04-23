// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include "hal/simulation/PWMData.h"
#include "hal/simulation/SimDataValue.h"

namespace hal {
class PWMData {
  HAL_SIMDATAVALUE_DEFINE_NAME(Initialized)
  HAL_SIMDATAVALUE_DEFINE_NAME(PulseMillisecondValue)
  HAL_SIMDATAVALUE_DEFINE_NAME(Speed)
  HAL_SIMDATAVALUE_DEFINE_NAME(Position)
  HAL_SIMDATAVALUE_DEFINE_NAME(PeriodScale)
  HAL_SIMDATAVALUE_DEFINE_NAME(ZeroLatch)

 public:
  SimDataValue<HAL_Bool, HAL_MakeBoolean, GetInitializedName> initialized{
      false};
  SimDataValue<double, HAL_MakeDouble, GetPulseMillisecondValueName> pulseMillisecondValue{0};
  SimDataValue<double, HAL_MakeDouble, GetSpeedName> speed{0};
  SimDataValue<double, HAL_MakeDouble, GetPositionName> position{0};
  SimDataValue<int32_t, HAL_MakeInt, GetPeriodScaleName> periodScale{0};
  SimDataValue<HAL_Bool, HAL_MakeBoolean, GetZeroLatchName> zeroLatch{false};

  virtual void ResetData();
};
extern PWMData* SimPWMData;
}  // namespace hal
