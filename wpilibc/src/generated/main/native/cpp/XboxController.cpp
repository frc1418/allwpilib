// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibc/generate_hids.py. DO NOT MODIFY

#include "frc/XboxController.h"

#include <hal/UsageReporting.h>
#include <wpi/sendable/SendableBuilder.h>

#include "frc/event/BooleanEvent.h"

using namespace frc;

XboxController::XboxController(int port) : GenericHID(port) {
  HAL_ReportUsage("XboxController", port, "");
}

double XboxController::GetLeftX() const {
  return GetRawAxis(Axis::kLeftX);
}

double XboxController::GetRightX() const {
  return GetRawAxis(Axis::kRightX);
}

double XboxController::GetLeftY() const {
  return GetRawAxis(Axis::kLeftY);
}

double XboxController::GetRightY() const {
  return GetRawAxis(Axis::kRightY);
}

double XboxController::GetLeftTriggerAxis() const {
  return GetRawAxis(Axis::kLeftTrigger);
}

BooleanEvent XboxController::LeftTrigger(double threshold, EventLoop* loop) const {
  return BooleanEvent(loop, [this, threshold] { return this->GetLeftTriggerAxis() > threshold; });
}

BooleanEvent XboxController::LeftTrigger(EventLoop* loop) const {
  return this->LeftTrigger(0.5, loop);
}

double XboxController::GetRightTriggerAxis() const {
  return GetRawAxis(Axis::kRightTrigger);
}

BooleanEvent XboxController::RightTrigger(double threshold, EventLoop* loop) const {
  return BooleanEvent(loop, [this, threshold] { return this->GetRightTriggerAxis() > threshold; });
}

BooleanEvent XboxController::RightTrigger(EventLoop* loop) const {
  return this->RightTrigger(0.5, loop);
}

bool XboxController::GetAButton() const {
  return GetRawButton(Button::kA);
}

bool XboxController::GetAButtonPressed() {
  return GetRawButtonPressed(Button::kA);
}

bool XboxController::GetAButtonReleased() {
  return GetRawButtonReleased(Button::kA);
}

BooleanEvent XboxController::A(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetAButton(); });
}

bool XboxController::GetBButton() const {
  return GetRawButton(Button::kB);
}

bool XboxController::GetBButtonPressed() {
  return GetRawButtonPressed(Button::kB);
}

bool XboxController::GetBButtonReleased() {
  return GetRawButtonReleased(Button::kB);
}

BooleanEvent XboxController::B(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetBButton(); });
}

bool XboxController::GetXButton() const {
  return GetRawButton(Button::kX);
}

bool XboxController::GetXButtonPressed() {
  return GetRawButtonPressed(Button::kX);
}

bool XboxController::GetXButtonReleased() {
  return GetRawButtonReleased(Button::kX);
}

BooleanEvent XboxController::X(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetXButton(); });
}

bool XboxController::GetYButton() const {
  return GetRawButton(Button::kY);
}

bool XboxController::GetYButtonPressed() {
  return GetRawButtonPressed(Button::kY);
}

bool XboxController::GetYButtonReleased() {
  return GetRawButtonReleased(Button::kY);
}

BooleanEvent XboxController::Y(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetYButton(); });
}

bool XboxController::GetLeftBumperButton() const {
  return GetRawButton(Button::kLeftBumper);
}

bool XboxController::GetLeftBumperButtonPressed() {
  return GetRawButtonPressed(Button::kLeftBumper);
}

bool XboxController::GetLeftBumperButtonReleased() {
  return GetRawButtonReleased(Button::kLeftBumper);
}

BooleanEvent XboxController::LeftBumper(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetLeftBumperButton(); });
}

bool XboxController::GetRightBumperButton() const {
  return GetRawButton(Button::kRightBumper);
}

bool XboxController::GetRightBumperButtonPressed() {
  return GetRawButtonPressed(Button::kRightBumper);
}

bool XboxController::GetRightBumperButtonReleased() {
  return GetRawButtonReleased(Button::kRightBumper);
}

BooleanEvent XboxController::RightBumper(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetRightBumperButton(); });
}

bool XboxController::GetBackButton() const {
  return GetRawButton(Button::kBack);
}

bool XboxController::GetBackButtonPressed() {
  return GetRawButtonPressed(Button::kBack);
}

bool XboxController::GetBackButtonReleased() {
  return GetRawButtonReleased(Button::kBack);
}

BooleanEvent XboxController::Back(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetBackButton(); });
}

bool XboxController::GetStartButton() const {
  return GetRawButton(Button::kStart);
}

bool XboxController::GetStartButtonPressed() {
  return GetRawButtonPressed(Button::kStart);
}

bool XboxController::GetStartButtonReleased() {
  return GetRawButtonReleased(Button::kStart);
}

BooleanEvent XboxController::Start(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetStartButton(); });
}

bool XboxController::GetLeftStickButton() const {
  return GetRawButton(Button::kLeftStick);
}

bool XboxController::GetLeftStickButtonPressed() {
  return GetRawButtonPressed(Button::kLeftStick);
}

bool XboxController::GetLeftStickButtonReleased() {
  return GetRawButtonReleased(Button::kLeftStick);
}

BooleanEvent XboxController::LeftStick(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetLeftStickButton(); });
}

bool XboxController::GetRightStickButton() const {
  return GetRawButton(Button::kRightStick);
}

bool XboxController::GetRightStickButtonPressed() {
  return GetRawButtonPressed(Button::kRightStick);
}

bool XboxController::GetRightStickButtonReleased() {
  return GetRawButtonReleased(Button::kRightStick);
}

BooleanEvent XboxController::RightStick(EventLoop* loop) const {
  return BooleanEvent(loop, [this]() { return this->GetRightStickButton(); });
}

bool XboxController::GetLeftBumper() const {
  return GetRawButton(Button::kLeftBumper);
}

bool XboxController::GetRightBumper() const {
  return GetRawButton(Button::kRightBumper);
}

bool XboxController::GetLeftBumperPressed() {
  return GetRawButtonPressed(Button::kLeftBumper);
}

bool XboxController::GetRightBumperPressed() {
  return GetRawButtonPressed(Button::kRightBumper);
}

bool XboxController::GetLeftBumperReleased() {
  return GetRawButtonReleased(Button::kLeftBumper);
}

bool XboxController::GetRightBumperReleased() {
  return GetRawButtonReleased(Button::kRightBumper);
}

void XboxController::InitSendable(wpi::SendableBuilder& builder) {
  builder.SetSmartDashboardType("HID");
  builder.PublishConstString("ControllerType", "Xbox");
  builder.AddDoubleProperty("LeftTrigger", [this] { return GetLeftTriggerAxis(); }, nullptr);
  builder.AddDoubleProperty("RightTrigger", [this] { return GetRightTriggerAxis(); }, nullptr);
  builder.AddDoubleProperty("LeftX", [this] { return GetLeftX(); }, nullptr);
  builder.AddDoubleProperty("RightX", [this] { return GetRightX(); }, nullptr);
  builder.AddDoubleProperty("LeftY", [this] { return GetLeftY(); }, nullptr);
  builder.AddDoubleProperty("RightY", [this] { return GetRightY(); }, nullptr);
  builder.AddBooleanProperty("A", [this] { return GetAButton(); }, nullptr);
  builder.AddBooleanProperty("B", [this] { return GetBButton(); }, nullptr);
  builder.AddBooleanProperty("X", [this] { return GetXButton(); }, nullptr);
  builder.AddBooleanProperty("Y", [this] { return GetYButton(); }, nullptr);
  builder.AddBooleanProperty("LeftBumper", [this] { return GetLeftBumperButton(); }, nullptr);
  builder.AddBooleanProperty("RightBumper", [this] { return GetRightBumperButton(); }, nullptr);
  builder.AddBooleanProperty("Back", [this] { return GetBackButton(); }, nullptr);
  builder.AddBooleanProperty("Start", [this] { return GetStartButton(); }, nullptr);
  builder.AddBooleanProperty("LeftStick", [this] { return GetLeftStickButton(); }, nullptr);
  builder.AddBooleanProperty("RightStick", [this] { return GetRightStickButton(); }, nullptr);
}