/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <functional>

#include "LowFiSim/AccelerometerSim.h"

namespace frc {
namespace sim {
namespace lowfi {

class SimpleAccelerometerSim : public AccelerometerSim {
 public:
  SimpleAccelerometerSim(const std::function<void(double)>& setterFunction,
                         const std::function<double(void)>& getterFunction)
      : m_setAccelerationFunction(setterFunction),
        m_getAccelerationFunction(getterFunction) {}
  double GetAcceleration() override { return m_getAccelerationFunction(); }

  void SetAcceleration(double acceleration) override {
    m_setAccelerationFunction(acceleration);
  }

 private:
  std::function<void(double)> m_setAccelerationFunction;
  std::function<double(void)> m_getAccelerationFunction;
};

}  // namespace lowfi
}  // namespace sim
}  // namespace frc
