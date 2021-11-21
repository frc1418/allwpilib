// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>

#include <hal/Types.h>
#include <wpi/sendable/Sendable.h>
#include <wpi/sendable/SendableHelper.h>

#include "frc/CompressorControlType.h"
#include "frc/PneumaticsBase.h"
#include "frc/PneumaticsModuleType.h"
#include "frc/SensorUtil.h"

namespace frc {

/**
 * Class for operating a compressor connected to a pneumatics module.
 *
 * The module will automatically run in closed loop mode by default whenever a
 * Solenoid object is created. For most cases, a Compressor object does not need
 * to be instantiated or used in a robot program. This class is only required in
 * cases where the robot program needs a more detailed status of the compressor
 * or to enable/disable closed loop control.
 *
 * Note: you cannot operate the compressor directly from this class as doing so
 * would circumvent the safety provided by using the pressure switch and closed
 * loop control. You can only turn off closed loop control, thereby stopping
 * the compressor from operating.
 */
class Compressor : public wpi::Sendable,
                   public wpi::SendableHelper<Compressor> {
 public:
  /**
   * Constructs a compressor for a specified module and type.
   *
   * @param module The module ID to use.
   * @param moduleType The module type to use.
   */
  Compressor(int module, PneumaticsModuleType moduleType);

  /**
   * Constructs a compressor for a default module and specified type.
   *
   * @param moduleType The module type to use.
   */
  explicit Compressor(PneumaticsModuleType moduleType);

  ~Compressor() override;

  Compressor(const Compressor&) = delete;
  Compressor& operator=(const Compressor&) = delete;

  Compressor(Compressor&&) = default;
  Compressor& operator=(Compressor&&) = default;

  /**
   * Check if compressor output is active.
   *
   * @return true if the compressor is on
   */
  bool Enabled() const;

  /**
   * Check if the pressure switch is triggered.
   *
   * @return true if pressure is low
   */
  bool GetPressureSwitchValue() const;

  /**
   * Query how much current the compressor is drawing.
   *
   * @return The current through the compressor, in amps
   */
  double GetCurrent() const;

  void Disable();

  void EnableDigital();

  void EnableAnalog(double minAnalogVoltage, double maxAnalogVoltage);

  void EnableHybrid(double minAnalogVoltage, double maxAnalogVoltage);

  CompressorControlType GetControlType() const;

  void InitSendable(wpi::SendableBuilder& builder) override;

 private:
  std::shared_ptr<PneumaticsBase> m_module;
};

}  // namespace frc
