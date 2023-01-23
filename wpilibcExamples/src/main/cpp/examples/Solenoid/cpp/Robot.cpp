// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "Robot.h"

#include <frc/shuffleboard/Shuffleboard.h>
#include <units/pressure.h>

void Robot::RobotInit() {
  // Publish elements to shuffleboard.
  frc::ShuffleboardTab& tab = frc::Shuffleboard::GetTab("Pneumatics");
  tab.Add("Single Solenoid", &m_solenoid);
  tab.Add("Double Solenoid", &m_doubleSolenoid);
  tab.Add("Compressor", &m_compressor);

  // Also publish some raw data
  tab.AddDouble("PH Pressure [PSI]", [&] {
    // Get the pressure (in PSI) from the analog sensor connected to the PH.
    // This function is supported only on the PH!
    // On a PCM, this function will return 0.
    units::pounds_per_square_inch_t pressure = m_compressor.GetPressure();
    return pressure.value();
  });
  tab.AddDouble("External Pressure [PSI]", [&] {
    // Get the pressure (in PSI) from an analog pressure sensor connected to
    // the RIO.
    return m_pressureTransducer.Get();
  });
  tab.AddDouble("Compressor Current", [&] {
    // Get compressor current draw.
    units::ampere_t compressorCurrent = m_compressor.GetCurrent();
    return compressorCurrent.value();
  });
  tab.AddBoolean("Pressure Switch", [&] {
    // Get the digital pressure switch connected to the PCM/PH.
    // The switch is open when the pressure is over ~120 PSI.
    return m_compressor.GetPressureSwitchValue();
  });

  // Add the options and send the chooser to the dashboard.
  m_compressorModeChooser.SetDefaultOption("Digital",
                                           frc::CompressorConfigType::Digital);
  m_compressorModeChooser.AddOption("Analog",
                                    frc::CompressorConfigType::Analog);
  m_compressorModeChooser.AddOption("Hybrid",
                                    frc::CompressorConfigType::Hybrid);
  tab.Add("Chooser", &m_compressorModeChooser);
}

void Robot::TeleopPeriodic() {
  /*
   * The output of GetRawButton is true/false depending on whether
   * the button is pressed; Set takes a boolean for whether
   * to retract the solenoid (false) or extend it (true).
   */
  m_solenoid.Set(m_stick.GetRawButton(kSolenoidButton));

  /*
   * GetRawButtonPressed will only return true once per press.
   * If a button is pressed, set the solenoid to the respective channel.
   */
  if (m_stick.GetRawButtonPressed(kDoubleSolenoidForward)) {
    m_doubleSolenoid.Set(frc::DoubleSolenoid::kForward);
  } else if (m_stick.GetRawButtonPressed(kDoubleSolenoidReverse)) {
    m_doubleSolenoid.Set(frc::DoubleSolenoid::kReverse);
  }

  // On button press, toggle the compressor with the mode selected from the
  // dashboard.
  if (m_stick.GetRawButtonPressed(kCompressorButton)) {
    // Check whether the compressor is currently enabled.
    bool isCompressorEnabled = m_compressor.IsEnabled();
    if (isCompressorEnabled) {
      // Disable closed-loop mode on the compressor.
      m_compressor.Disable();
    } else {
      switch (m_compressorModeChooser.GetSelected()) {
        case frc::CompressorConfigType::Digital:
          // Enable closed-loop mode based on the digital pressure switch
          // connected to the PCM/PH.
          m_compressor.EnableDigital();
          break;
        case frc::CompressorConfigType::Analog:
          // Enable closed-loop mode based on the analog pressure sensor
          // connected to the PH. The compressor will run while the pressure
          // reported by the sensor is in the specified range ([70 PSI, 120
          // PSI] in this example). Analog mode exists only on the PH! On the
          // PCM, this enables digital control.
          m_compressor.EnableAnalog(70_psi, 110_psi);
          break;
        case frc::CompressorConfigType::Hybrid:
          // Enable closed-loop mode based on both the digital pressure switch
          // and the analog pressure sensor connected to the PH. The
          // compressor will run while the pressure reported by the analog
          // sensor is in the specified range ([70 PSI, 120 PSI] in this
          // example) AND the digital switch reports that the system is not
          // full. Hybrid mode exists only on the PH! On the PCM, this enables
          // digital control.
          m_compressor.EnableHybrid(70_psi, 110_psi);
          break;
        case frc::CompressorConfigType::Disabled:
        default:
          // This shouldn't happen!
          break;
      }
    }
  }
}

#ifndef RUNNING_FRC_TESTS
int main() {
  return frc::StartRobot<Robot>();
}
#endif
