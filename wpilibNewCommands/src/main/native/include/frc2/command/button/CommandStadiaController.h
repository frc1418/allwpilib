// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once
#include <frc/StadiaController.h>

#include "Trigger.h"
#include "frc2/command/CommandScheduler.h"
#include "frc2/command/button/CommandGenericHID.h"

namespace frc2 {
/**
 * A version of {@link frc::StadiaController} with {@link Trigger} factories for
 * command-based.
 *
 * @see frc::StadiaController
 */
class CommandStadiaController : public CommandGenericHID {
 public:
  /**
   * Construct an instance of a controller.
   *
   * @param port The port index on the Driver Station that the controller is
   * plugged into.
   */
  explicit CommandStadiaController(int port);

  /**
   * Get the underlying GenericHID object.
   *
   * @return the wrapped GenericHID object
   */
  frc::StadiaController& GetHID();

  /**
   * Constructs an event instance around the left bumper's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the left bumper's digital signal
   * attached to the given loop.
   */
  Trigger LeftBumper(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                                .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the right bumper's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the right bumper's digital signal
   * attached to the given loop.
   */
  Trigger RightBumper(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                                 .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the left stick's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the left stick's digital signal
   * attached to the given loop.
   */
  Trigger LeftStick(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                               .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the right stick's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the right stick's digital signal
   * attached to the given loop.
   */
  Trigger RightStick(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                                .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the A button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the A button's digital signal
   * attached to the given loop.
   */
  Trigger A(frc::EventLoop* loop =
                CommandScheduler::GetInstance().GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the B button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the B button's digital signal
   * attached to the given loop.
   */
  Trigger B(frc::EventLoop* loop =
                CommandScheduler::GetInstance().GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the X button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the X button's digital signal
   * attached to the given loop.
   */
  Trigger X(frc::EventLoop* loop =
                CommandScheduler::GetInstance().GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the Y button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the Y button's digital signal
   * attached to the given loop.
   */
  Trigger Y(frc::EventLoop* loop =
                CommandScheduler::GetInstance().GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the ellipses button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the ellipses button's digital signal
   * attached to the given loop.
   */
  Trigger Ellipses(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                              .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the hamburger button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the hamburger button's digital
   * signal attached to the given loop.
   */
  Trigger Hamburger(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                               .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the stadia button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the stadia button's digital signal
   * attached to the given loop.
   */
  Trigger Stadia(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                            .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the google button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the google button's digital signal
   * attached to the given loop.
   */
  Trigger Google(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                            .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the frame button's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the frame button's digital signal
   * attached to the given loop.
   */
  Trigger Frame(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                           .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the left trigger's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the left trigger's digital signal
   * attached to the given loop.
   */
  Trigger LeftTrigger(frc::EventLoop* loop = CommandScheduler::GetInstance()
                                                 .GetDefaultButtonLoop()) const;

  /**
   * Constructs an event instance around the right trigger's digital signal.
   *
   * @param loop the event loop instance to attach the event to. Defaults to the
   * CommandScheduler's default loop.
   * @return an event instance representing the right trigger's digital signal
   * attached to the given loop.
   */
  Trigger RightTrigger(
      frc::EventLoop* loop =
          CommandScheduler::GetInstance().GetDefaultButtonLoop()) const;

 private:
  frc::StadiaController m_hid;
};
}  // namespace frc2
