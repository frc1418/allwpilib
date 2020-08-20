/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <units/angle.h>

#include "frc/geometry/Rotation2d.h"
#include "hal/SimDevice.h"

namespace frc {

class AnalogEncoder;

namespace sim {

/**
 * Class to control a simulated analog encoder.
 */
class AnalogEncoderSim {
 public:
  /**
   * Constructs from an AnalogEncoder object.
   *
   * @param analogInput AnalogEncoder to simulate
   */
  AnalogEncoderSim(const AnalogEncoder& analogEncoder);

  /**
   * Set the position using an {@link Rotation2d}.
   */
  void SetPosition(Rotation2d angle);

  /**
   * Set the position of the encoder.
   *
   * @param turns The position.
   */
  void SetTurns(units::turn_t turns);

  /**
   * Get the simulated position.
   */
  units::turn_t GetTurns();

  /**
   * Get the position as a {@link Rotation2d}.
   */
  Rotation2d GetPosition();

 private:
  hal::SimDouble m_positionSim;
};
}  // namespace sim
}  // namespace frc
