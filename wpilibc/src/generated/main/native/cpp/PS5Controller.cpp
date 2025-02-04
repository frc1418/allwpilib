// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibc/generate_hids.py. DO NOT MODIFY

#include "frc/PS5Controller.h"

#include <hal/UsageReporting.h>
#include <wpi/sendable/SendableBuilder.h>

#include "frc/event/BooleanEvent.h"

using namespace frc;

PS5Controller::PS5Controller(int port) : GenericHID(port) {
  HAL_ReportUsage("PS5Controller", port + 1, "");
}

double PS5Controller::GetLeftX() const {
  return GetRawAxis(Axis::kLeftX);
}

double PS5Controller::GetLeftY() const {
  return GetRawAxis(Axis::kLeftY);
}

double PS5Controller::GetRightX() const {
  return GetRawAxis(Axis::kRightX);
}

double PS5Controller::GetRightY() const {
  return GetRawAxis(Axis::kRightY);
}

double PS5Controller::GetL2Axis() const {
  return GetRawAxis(Axis::kL2);
}

double PS5Controller::GetR2Axis() const {
  return GetRawAxis(Axis::kR2);
}

bool PS5Controller::GetSquareButton() const {
  return GetRawButton(Button::kSquare);
}

bool PS5Controller::GetSquareButtonPressed() {
  return GetRawButtonPressed(Button::kSquare);
}

bool PS5Controller::GetSquareButtonReleased() {
  return GetRawButtonReleased(Button::kSquare);
}

BooleanEvent PS5Controller::Square(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetSquareButton(); });
}

bool PS5Controller::GetCrossButton() const {
  return GetRawButton(Button::kCross);
}

bool PS5Controller::GetCrossButtonPressed() {
  return GetRawButtonPressed(Button::kCross);
}

bool PS5Controller::GetCrossButtonReleased() {
  return GetRawButtonReleased(Button::kCross);
}

BooleanEvent PS5Controller::Cross(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetCrossButton(); });
}

bool PS5Controller::GetCircleButton() const {
  return GetRawButton(Button::kCircle);
}

bool PS5Controller::GetCircleButtonPressed() {
  return GetRawButtonPressed(Button::kCircle);
}

bool PS5Controller::GetCircleButtonReleased() {
  return GetRawButtonReleased(Button::kCircle);
}

BooleanEvent PS5Controller::Circle(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetCircleButton(); });
}

bool PS5Controller::GetTriangleButton() const {
  return GetRawButton(Button::kTriangle);
}

bool PS5Controller::GetTriangleButtonPressed() {
  return GetRawButtonPressed(Button::kTriangle);
}

bool PS5Controller::GetTriangleButtonReleased() {
  return GetRawButtonReleased(Button::kTriangle);
}

BooleanEvent PS5Controller::Triangle(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetTriangleButton(); });
}

bool PS5Controller::GetL1Button() const {
  return GetRawButton(Button::kL1);
}

bool PS5Controller::GetL1ButtonPressed() {
  return GetRawButtonPressed(Button::kL1);
}

bool PS5Controller::GetL1ButtonReleased() {
  return GetRawButtonReleased(Button::kL1);
}

BooleanEvent PS5Controller::L1(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetL1Button(); });
}

bool PS5Controller::GetR1Button() const {
  return GetRawButton(Button::kR1);
}

bool PS5Controller::GetR1ButtonPressed() {
  return GetRawButtonPressed(Button::kR1);
}

bool PS5Controller::GetR1ButtonReleased() {
  return GetRawButtonReleased(Button::kR1);
}

BooleanEvent PS5Controller::R1(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetR1Button(); });
}

bool PS5Controller::GetL2Button() const {
  return GetRawButton(Button::kL2);
}

bool PS5Controller::GetL2ButtonPressed() {
  return GetRawButtonPressed(Button::kL2);
}

bool PS5Controller::GetL2ButtonReleased() {
  return GetRawButtonReleased(Button::kL2);
}

BooleanEvent PS5Controller::L2(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetL2Button(); });
}

bool PS5Controller::GetR2Button() const {
  return GetRawButton(Button::kR2);
}

bool PS5Controller::GetR2ButtonPressed() {
  return GetRawButtonPressed(Button::kR2);
}

bool PS5Controller::GetR2ButtonReleased() {
  return GetRawButtonReleased(Button::kR2);
}

BooleanEvent PS5Controller::R2(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetR2Button(); });
}

bool PS5Controller::GetCreateButton() const {
  return GetRawButton(Button::kCreate);
}

bool PS5Controller::GetCreateButtonPressed() {
  return GetRawButtonPressed(Button::kCreate);
}

bool PS5Controller::GetCreateButtonReleased() {
  return GetRawButtonReleased(Button::kCreate);
}

BooleanEvent PS5Controller::Create(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetCreateButton(); });
}

bool PS5Controller::GetOptionsButton() const {
  return GetRawButton(Button::kOptions);
}

bool PS5Controller::GetOptionsButtonPressed() {
  return GetRawButtonPressed(Button::kOptions);
}

bool PS5Controller::GetOptionsButtonReleased() {
  return GetRawButtonReleased(Button::kOptions);
}

BooleanEvent PS5Controller::Options(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetOptionsButton(); });
}

bool PS5Controller::GetL3Button() const {
  return GetRawButton(Button::kL3);
}

bool PS5Controller::GetL3ButtonPressed() {
  return GetRawButtonPressed(Button::kL3);
}

bool PS5Controller::GetL3ButtonReleased() {
  return GetRawButtonReleased(Button::kL3);
}

BooleanEvent PS5Controller::L3(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetL3Button(); });
}

bool PS5Controller::GetR3Button() const {
  return GetRawButton(Button::kR3);
}

bool PS5Controller::GetR3ButtonPressed() {
  return GetRawButtonPressed(Button::kR3);
}

bool PS5Controller::GetR3ButtonReleased() {
  return GetRawButtonReleased(Button::kR3);
}

BooleanEvent PS5Controller::R3(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetR3Button(); });
}

bool PS5Controller::GetPSButton() const {
  return GetRawButton(Button::kPS);
}

bool PS5Controller::GetPSButtonPressed() {
  return GetRawButtonPressed(Button::kPS);
}

bool PS5Controller::GetPSButtonReleased() {
  return GetRawButtonReleased(Button::kPS);
}

BooleanEvent PS5Controller::PS(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetPSButton(); });
}

bool PS5Controller::GetTouchpadButton() const {
  return GetRawButton(Button::kTouchpad);
}

bool PS5Controller::GetTouchpadButtonPressed() {
  return GetRawButtonPressed(Button::kTouchpad);
}

bool PS5Controller::GetTouchpadButtonReleased() {
  return GetRawButtonReleased(Button::kTouchpad);
}

BooleanEvent PS5Controller::Touchpad(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetTouchpadButton(); });
}

bool PS5Controller::GetTouchpad() const {
  return GetRawButton(Button::kTouchpad);
}

bool PS5Controller::GetTouchpadPressed() {
  return GetRawButtonPressed(Button::kTouchpad);
}

bool PS5Controller::GetTouchpadReleased() {
  return GetRawButtonReleased(Button::kTouchpad);
}

void PS5Controller::InitSendable(wpi::SendableBuilder& builder) {
  builder.SetSmartDashboardType("HID");
  builder.PublishConstString("ControllerType", "PS5");
  builder.AddDoubleProperty("L2", [this] { return GetL2Axis(); }, nullptr);
  builder.AddDoubleProperty("R2", [this] { return GetR2Axis(); }, nullptr);
  builder.AddDoubleProperty("LeftX", [this] { return GetLeftX(); }, nullptr);
  builder.AddDoubleProperty("LeftY", [this] { return GetLeftY(); }, nullptr);
  builder.AddDoubleProperty("RightX", [this] { return GetRightX(); }, nullptr);
  builder.AddDoubleProperty("RightY", [this] { return GetRightY(); }, nullptr);
  builder.AddBooleanProperty("Square", [this] { return GetSquareButton(); }, nullptr);
  builder.AddBooleanProperty("Cross", [this] { return GetCrossButton(); }, nullptr);
  builder.AddBooleanProperty("Circle", [this] { return GetCircleButton(); }, nullptr);
  builder.AddBooleanProperty("Triangle", [this] { return GetTriangleButton(); }, nullptr);
  builder.AddBooleanProperty("L1", [this] { return GetL1Button(); }, nullptr);
  builder.AddBooleanProperty("R1", [this] { return GetR1Button(); }, nullptr);
  builder.AddBooleanProperty("L2", [this] { return GetL2Button(); }, nullptr);
  builder.AddBooleanProperty("R2", [this] { return GetR2Button(); }, nullptr);
  builder.AddBooleanProperty("Create", [this] { return GetCreateButton(); }, nullptr);
  builder.AddBooleanProperty("Options", [this] { return GetOptionsButton(); }, nullptr);
  builder.AddBooleanProperty("L3", [this] { return GetL3Button(); }, nullptr);
  builder.AddBooleanProperty("R3", [this] { return GetR3Button(); }, nullptr);
  builder.AddBooleanProperty("PS", [this] { return GetPSButton(); }, nullptr);
  builder.AddBooleanProperty("Touchpad", [this] { return GetTouchpadButton(); }, nullptr);
}