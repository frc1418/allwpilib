// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <hal/SimDevice.h>
#include <networktables/NTSendableHelper.h>

#include "frc/SPI.h"
#include "frc/interfaces/Accelerometer.h"

namespace frc {

/**
 * ADXL362 SPI Accelerometer.
 *
 * This class allows access to an Analog Devices ADXL362 3-axis accelerometer.
 */
class ADXL362 : public Accelerometer, public nt::NTSendableHelper<ADXL362> {
 public:
  enum Axes { kAxis_X = 0x00, kAxis_Y = 0x02, kAxis_Z = 0x04 };
  struct AllAxes {
    double XAxis;
    double YAxis;
    double ZAxis;
  };

 public:
  /**
   * Constructor.  Uses the onboard CS1.
   *
   * @param range The range (+ or -) that the accelerometer will measure.
   */
  explicit ADXL362(Range range = kRange_2G);

  /**
   * Constructor.
   *
   * @param port  The SPI port the accelerometer is attached to
   * @param range The range (+ or -) that the accelerometer will measure.
   */
  explicit ADXL362(SPI::Port port, Range range = kRange_2G);

  ~ADXL362() override = default;

  ADXL362(ADXL362&&) = default;
  ADXL362& operator=(ADXL362&&) = default;

  SPI::Port GetSpiPort() const;

  // Accelerometer interface
  void SetRange(Range range) final;
  double GetX() override;
  double GetY() override;
  double GetZ() override;

  /**
   * Get the acceleration of one axis in Gs.
   *
   * @param axis The axis to read from.
   * @return Acceleration of the ADXL362 in Gs.
   */
  virtual double GetAcceleration(Axes axis);

  /**
   * Get the acceleration of all axes in Gs.
   *
   * @return An object containing the acceleration measured on each axis of the
   *         ADXL362 in Gs.
   */
  virtual AllAxes GetAccelerations();

  void InitSendable(nt::NTSendableBuilder& builder) override;

 private:
  SPI m_spi;
  hal::SimDevice m_simDevice;
  hal::SimEnum m_simRange;
  hal::SimDouble m_simX;
  hal::SimDouble m_simY;
  hal::SimDouble m_simZ;
  double m_gsPerLSB = 0.001;
};

}  // namespace frc
