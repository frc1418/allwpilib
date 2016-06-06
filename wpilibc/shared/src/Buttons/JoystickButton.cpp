/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2011-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Buttons/JoystickButton.h"

JoystickButton::JoystickButton(GenericHID* joystick, uint8_t buttonNumber)
    : m_joystick(joystick), m_buttonNumber(buttonNumber) {}

bool JoystickButton::Get() { return m_joystick->GetRawButton(m_buttonNumber); }
