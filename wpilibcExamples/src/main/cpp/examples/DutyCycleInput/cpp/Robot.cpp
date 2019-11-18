/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <frc/DigitalInput.h>
#include <frc/DutyCycle.h>
#include <frc/TimedRobot.h>

class Robot : public frc::TimedRobot {
  frc::DigitalInput m_input{0};         // Input channel
  frc::DutyCycle m_dutyCycle{m_input};  // Duty cycle input

 public:
  void RobotInit() override {}

  void RobotPeriodic() override {
    // Duty Cycle Frequency in Hz
    auto frequency = m_dutyCycle.GetFrequency();

    // Output of duty cycle, ranging from 0 to 1
    // 1 is fully on, 0 is fully off
    auto output = m_dutyCycle.GetOutput();
  }
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
