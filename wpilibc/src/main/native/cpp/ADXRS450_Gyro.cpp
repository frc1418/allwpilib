/*----------------------------------------------------------------------------*/
/* Copyright (c) 2015-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "frc/ADXRS450_Gyro.h"

#include <hal/HAL.h>

#include "frc/DriverStation.h"
#include "frc/Timer.h"

using namespace frc;

static constexpr double kSamplePeriod = 0.0005;
static constexpr double kCalibrationSampleTime = 5.0;
static constexpr double kDegreePerSecondPerLSB = 0.0125;

static constexpr int kRateRegister = 0x00;
static constexpr int kTemRegister = 0x02;
static constexpr int kLoCSTRegister = 0x04;
static constexpr int kHiCSTRegister = 0x06;
static constexpr int kQuadRegister = 0x08;
static constexpr int kFaultRegister = 0x0A;
static constexpr int kPIDRegister = 0x0C;
static constexpr int kSNHighRegister = 0x0E;
static constexpr int kSNLowRegister = 0x10;

ADXRS450_Gyro::ADXRS450_Gyro() : ADXRS450_Gyro(SPI::kOnboardCS0) {}

ADXRS450_Gyro::ADXRS450_Gyro(SPI::Port port) : m_spi(port) {
  m_spi.SetClockRate(3000000);
  m_spi.SetMSBFirst();
  m_spi.SetSampleDataOnLeadingEdge();
  m_spi.SetClockActiveHigh();
  m_spi.SetChipSelectActiveLow();

  // Validate the part ID
  if ((ReadRegister(kPIDRegister) & 0xff00) != 0x5200) {
    DriverStation::ReportError("could not find ADXRS450 gyro");
    return;
  }

  m_spi.InitAccumulator(kSamplePeriod, 0x20000000u, 4, 0x0c00000eu, 0x04000000u,
                        10u, 16u, true, true);

  Calibrate();

  HAL_Report(HALUsageReporting::kResourceType_ADXRS450, port);
  SetName("ADXRS450_Gyro", port);
}

static bool CalcParity(int v) {
  bool parity = false;
  while (v != 0) {
    parity = !parity;
    v = v & (v - 1);
  }
  return parity;
}

static inline int BytesToIntBE(uint8_t* buf) {
  int result = static_cast<int>(buf[0]) << 24;
  result |= static_cast<int>(buf[1]) << 16;
  result |= static_cast<int>(buf[2]) << 8;
  result |= static_cast<int>(buf[3]);
  return result;
}

uint16_t ADXRS450_Gyro::ReadRegister(int reg) {
  int cmd = 0x80000000 | static_cast<int>(reg) << 17;
  if (!CalcParity(cmd)) cmd |= 1u;

  // big endian
  uint8_t buf[4] = {static_cast<uint8_t>((cmd >> 24) & 0xff),
                    static_cast<uint8_t>((cmd >> 16) & 0xff),
                    static_cast<uint8_t>((cmd >> 8) & 0xff),
                    static_cast<uint8_t>(cmd & 0xff)};

  m_spi.Write(buf, 4);
  m_spi.Read(false, buf, 4);
  if ((buf[0] & 0xe0) == 0) return 0;  // error, return 0
  return static_cast<uint16_t>((BytesToIntBE(buf) >> 5) & 0xffff);
}

double ADXRS450_Gyro::GetAngle() const {
  return m_spi.GetAccumulatorValue() * kDegreePerSecondPerLSB * kSamplePeriod;
}

double ADXRS450_Gyro::GetRate() const {
  return static_cast<double>(m_spi.GetAccumulatorLastValue()) *
         kDegreePerSecondPerLSB;
}

void ADXRS450_Gyro::Reset() { m_spi.ResetAccumulator(); }

void ADXRS450_Gyro::Calibrate() {
  Wait(0.1);

  m_spi.SetAccumulatorCenter(0);
  m_spi.ResetAccumulator();

  Wait(kCalibrationSampleTime);

  m_spi.SetAccumulatorCenter(static_cast<int>(m_spi.GetAccumulatorAverage()));
  m_spi.ResetAccumulator();
}
