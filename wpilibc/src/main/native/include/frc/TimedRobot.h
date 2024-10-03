// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <chrono>
#include <functional>
#include <utility>
#include <vector>

#include <hal/Notifier.h>
#include <hal/Types.h>
#include <units/math.h>
#include <units/time.h>
#include <wpi/priority_queue.h>

#include "frc/IterativeRobotBase.h"
#include "frc/RobotController.h"
#include "frc/Tracer.h"

namespace frc {

/**
 * TimedRobot implements the IterativeRobotBase robot program framework.
 *
 * The TimedRobot class is intended to be subclassed by a user creating a
 * robot program.
 *
 * Periodic() functions from the base class are called on an interval by a
 * Notifier instance.
 */
class TimedRobot : public IterativeRobotBase {
 public:
  /// Default loop period.
  static constexpr auto kDefaultPeriod = 20_ms;

  /**
   * Provide an alternate "main loop" via StartCompetition().
   */
  void StartCompetition() override;

  /**
   * Ends the main loop in StartCompetition().
   */
  void EndCompetition() override;

  /**
   * Constructor for TimedRobot.
   *
   * @param period Period.
   */
  explicit TimedRobot(units::second_t period = kDefaultPeriod);

  TimedRobot(TimedRobot&&) = default;
  TimedRobot& operator=(TimedRobot&&) = default;

  ~TimedRobot() override;

  /**
   * Add a callback to run at a specific period with a starting time offset.
   *
   * This is scheduled on TimedRobot's Notifier, so TimedRobot and the callback
   * run synchronously. Interactions between them are thread-safe.
   *
   * @param callback The callback to run.
   * @param period   The period at which to run the callback.
   * @param offset   The offset from the common starting time. This is useful
   *                 for scheduling a callback in a different timeslot relative
   *                 to TimedRobot.
   * 
   * @deprecated Use AddPeriodic(std::function<void()>, std::string_view, units::second_t, units::second_t) instead.
   */
  [[deprecated("Use AddPeriodic(std::function<void()>, std::string_view, units::second_t, units::second_t) instead.")]]
  void AddPeriodic(std::function<void()> callback, units::second_t period,
                   units::second_t offset = 0_s);

  /**
   * Add a callback to run at a specific period with a starting time offset.
   * 
   * This is scheduled on TimedRobot's Notifier, so TimedRobot and the callback
   * run synchronously. Interactions between them are thread-safe.
   * 
   * @param callback The callback to run.
   * @param name     The name of the callback.
   * @param period   The period at which to run the callback.
   * @param offset   The offset from the common starting time. This is useful
   *                 for scheduling a callback in a different timeslot relative
   *                 to TimedRobot.
   */
  void AddPeriodic(std::function<void()> callback, std::string name,
                   units::second_t period, units::second_t offset = 0_s);

 private:
  class Callback {
   public:
    std::function<void()> func;
    frc::Tracer::SubstitutiveTracer tracer;
    std::chrono::microseconds period;
    std::chrono::microseconds expirationTime;

    /**
     * Construct a callback container.
     *
     * @param func      The callback to run.
     * @param startTime The common starting point for all callback scheduling.
     * @param period    The period at which to run the callback.
     * @param offset    The offset from the common starting time.
     */
    Callback(std::function<void()> func, std::string name, std::chrono::microseconds startTime,
             std::chrono::microseconds period, std::chrono::microseconds offset)
        : func{std::move(func)},
          tracer{name},
          period{period},
          expirationTime(
              startTime + offset + period +
              (std::chrono::microseconds{frc::RobotController::GetFPGATime()} -
               startTime) /
                  period * period) {}

    bool operator>(const Callback& rhs) const {
      return expirationTime > rhs.expirationTime;
    }
  };

  hal::Handle<HAL_NotifierHandle, HAL_CleanNotifier> m_notifier;
  std::chrono::microseconds m_startTime;

  wpi::priority_queue<Callback, std::vector<Callback>, std::greater<Callback>>
      m_callbacks;
};

}  // namespace frc
