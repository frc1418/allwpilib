// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "frc/simulation/PS5ControllerSim.h"

#include "frc/PS5Controller.h"

using namespace frc;
using namespace frc::sim;

PS5ControllerSim::PS5ControllerSim(const PS5Controller& joystick)
    : GenericHIDSim{joystick} {
  SetAxisCount(6);
  SetButtonCount(14);
  SetPOVCount(1);
}

PS5ControllerSim::PS5ControllerSim(int port) : GenericHIDSim{port} {
  SetAxisCount(6);
  SetButtonCount(14);
  SetPOVCount(1);
}

void PS5ControllerSim::SetLeftX(double value) {
  SetRawAxis(PS5Controller::Axis::kLeftX, value);
}

void PS5ControllerSim::SetRightX(double value) {
  SetRawAxis(PS5Controller::Axis::kRightX, value);
}

void PS5ControllerSim::SetLeftY(double value) {
  SetRawAxis(PS5Controller::Axis::kLeftY, value);
}

void PS5ControllerSim::SetRightY(double value) {
  SetRawAxis(PS5Controller::Axis::kRightY, value);
}

void PS5ControllerSim::SetL2Axis(double value) {
  SetRawAxis(PS5Controller::Axis::kL2, value);
}

void PS5ControllerSim::SetR2Axis(double value) {
  SetRawAxis(PS5Controller::Axis::kR2, value);
}

void PS5ControllerSim::SetSquareButton(bool value) {
  SetRawButton(PS5Controller::Button::kSquare, value);
}

void PS5ControllerSim::SetCrossButton(bool value) {
  SetRawButton(PS5Controller::Button::kCross, value);
}

void PS5ControllerSim::SetCircleButton(bool value) {
  SetRawButton(PS5Controller::Button::kCircle, value);
}

void PS5ControllerSim::SetTriangleButton(bool value) {
  SetRawButton(PS5Controller::Button::kTriangle, value);
}

void PS5ControllerSim::SetL1Button(bool value) {
  SetRawButton(PS5Controller::Button::kL1, value);
}

void PS5ControllerSim::SetR1Button(bool value) {
  SetRawButton(PS5Controller::Button::kR1, value);
}

void PS5ControllerSim::SetL2Button(bool value) {
  SetRawButton(PS5Controller::Button::kL2, value);
}

void PS5ControllerSim::SetR2Button(bool value) {
  SetRawButton(PS5Controller::Button::kR2, value);
}

void PS5ControllerSim::SetCreateButton(bool value) {
  SetRawButton(PS5Controller::Button::kCreate, value);
}

void PS5ControllerSim::SetOptionsButton(bool value) {
  SetRawButton(PS5Controller::Button::kOptions, value);
}

void PS5ControllerSim::SetL3Button(bool value) {
  SetRawButton(PS5Controller::Button::kL3, value);
}

void PS5ControllerSim::SetR3Button(bool value) {
  SetRawButton(PS5Controller::Button::kR3, value);
}

void PS5ControllerSim::SetPSButton(bool value) {
  SetRawButton(PS5Controller::Button::kPS, value);
}

void PS5ControllerSim::SetTouchpad(bool value) {
  SetRawButton(PS5Controller::Button::kTouchpad, value);
}
