/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <functional>
#include <limits>

#include <units/units.h>

#include "frc/smartdashboard/SendableBase.h"

namespace frc2 {

/**
 * Implements a PID control loop.
 */
template <typename Unit>
class PIDController : public frc::SendableBase {
 public:
  using UnitPerSec = typename units::unit_t<units::compound_unit<
      typename Unit::unit_type, units::inverse<units::seconds>>>;

  /**
   * Allocates a PIDController with the given constants for Kp, Ki, and Kd.
   *
   * @param Kp     The proportional coefficient.
   * @param Ki     The integral coefficient.
   * @param Kd     The derivative coefficient.
   * @param period The period between controller updates in seconds. The
   *               default is 20 milliseconds.
   */
  PIDController(double Kp, double Ki, double Kd,
                units::second_t period = 20_ms);

  ~PIDController() override = default;

  PIDController(const PIDController&) = default;
  PIDController& operator=(const PIDController&) = default;
  PIDController(PIDController&&) = default;
  PIDController& operator=(PIDController&&) = default;

  /**
   * Sets the PID Controller gain parameters.
   *
   * Sets the proportional, integral, and differential coefficients.
   *
   * @param Kp Proportional coefficient
   * @param Ki Integral coefficient
   * @param Kd Differential coefficient
   */
  void SetPID(double Kp, double Ki, double Kd);

  /**
   * Sets the proportional coefficient of the PID controller gain.
   *
   * @param Kp proportional coefficient
   */
  void SetP(double Kp);

  /**
   * Sets the integral coefficient of the PID controller gain.
   *
   * @param Ki integral coefficient
   */
  void SetI(double Ki);

  /**
   * Sets the differential coefficient of the PID controller gain.
   *
   * @param Kd differential coefficient
   */
  void SetD(double Kd);

  /**
   * Gets the proportional coefficient.
   *
   * @return proportional coefficient
   */
  double GetP() const;

  /**
   * Gets the integral coefficient.
   *
   * @return integral coefficient
   */
  double GetI() const;

  /**
   * Gets the differential coefficient.
   *
   * @return differential coefficient
   */
  double GetD() const;

  /**
   * Gets the period of this controller.
   *
   * @return The period of the controller.
   */
  units::second_t GetPeriod() const;

  /**
   * Sets the setpoint for the PIDController.
   *
   * @param setpoint The desired setpoint.
   */
  void SetSetpoint(Unit setpoint);

  /**
   * Returns the current setpoint of the PIDController.
   *
   * @return The current setpoint.
   */
  Unit GetSetpoint() const;

  /**
   * Returns true if the error is within the tolerance of the error.
   *
   * This will return false until at least one input value has been computed.
   */
  bool AtSetpoint() const;

  /**
   * Sets the minimum and maximum values expected from the input.
   *
   * @param minimumInput The minimum value expected from the input.
   * @param maximumInput The maximum value expected from the input.
   */
  void SetInputRange(Unit minimumInput, Unit maximumInput);

  /**
   * Enables continuous input.
   *
   * Rather then using the max and min input range as constraints, it considers
   * them to be the same point and automatically calculates the shortest route
   * to the setpoint.
   *
   * @param minimumInput The minimum value expected from the input.
   * @param maximumInput The maximum value expected from the input.
   */
  void EnableContinuousInput(Unit minimumInput, Unit maximumInput);

  /**
   * Disables continuous input.
   */
  void DisableContinuousInput();

  /**
   * Sets the minimum and maximum values to write.
   *
   * @param minimumOutput the minimum value to write to the output
   * @param maximumOutput the maximum value to write to the output
   */
  void SetOutputRange(double minimumOutput, double maximumOutput);

  /**
   * Sets the error which is considered tolerable for use with AtSetpoint().
   *
   * @param positionTolerance Position error which is tolerable.
   * @param velociytTolerance Velocity error which is tolerable.
   */
  void SetTolerance(Unit positionTolerance,
                    UnitPerSec velocityTolerance = UnitPerSec{
                        std::numeric_limits<double>::infinity()});

  /**
   * Returns the difference between the setpoint and the measurement.
   */
  Unit GetPositionError() const;

  /**
   * Returns the velocity error.
   */
  UnitPerSec GetVelocityError() const;

  /**
   * Returns the next output of the PID controller.
   *
   * @param measurement The current measurement of the process variable.
   */
  double Calculate(Unit measurement);

  /**
   * Returns the next output of the PID controller.
   *
   * @param measurement The current measurement of the process variable.
   * @param setpoint The new setpoint of the controller.
   */
  double Calculate(Unit measurement, Unit setpoint);

  /**
   * Reset the previous error, the integral term, and disable the controller.
   */
  void Reset();

  void InitSendable(frc::SendableBuilder& builder) override;

 protected:
  /**
   * Wraps error around for continuous inputs. The original error is returned if
   * continuous mode is disabled.
   *
   * @param error The current error of the PID controller.
   * @return Error for continuous inputs.
   */
  Unit GetContinuousPositionError(Unit error) const;

 private:
  // Factor for "proportional" control
  double m_Kp;

  // Factor for "integral" control
  double m_Ki;

  // Factor for "derivative" control
  double m_Kd;

  // The period (in seconds) of the control loop running this controller
  units::second_t m_period;

  // |maximum output|
  double m_maximumOutput = 1.0;

  // |minimum output|
  double m_minimumOutput = -1.0;

  // Maximum input - limit setpoint to this
  Unit m_maximumInput{0.0};

  // Minimum input - limit setpoint to this
  Unit m_minimumInput{0.0};

  // Input range - difference between maximum and minimum
  Unit m_inputRange{0.0};

  // Do the endpoints wrap around? eg. Absolute encoder
  bool m_continuous = false;

  // The error at the time of the most recent call to Calculate()
  Unit m_positionError{0.0};
  UnitPerSec m_velocityError{0.0};

  // The error at the time of the second-most-recent call to Calculate() (used
  // to compute velocity)
  Unit m_prevError{0.0};

  // The sum of the errors for use in the integral calc
  double m_totalError = 0.0;

  // The error that is considered at setpoint.
  Unit m_positionTolerance{0.05};
  UnitPerSec m_velocityTolerance{std::numeric_limits<double>::infinity()};

  Unit m_setpoint{0.0};
};

}  // namespace frc2

#include "frc/controller/PIDController.inc"
