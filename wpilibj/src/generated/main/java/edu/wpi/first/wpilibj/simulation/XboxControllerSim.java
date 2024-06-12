// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./wpilibj/generate_hids.py. DO NOT MODIFY

package edu.wpi.first.wpilibj.simulation;

import edu.wpi.first.wpilibj.XboxController;

/** Class to control a simulated Xbox controller. */
public class XboxControllerSim extends GenericHIDSim {
  /**
   * Constructs from a XboxController object.
   *
   * @param joystick controller to simulate
   */
  @SuppressWarnings("this-escape")
  public XboxControllerSim(XboxController joystick) {
    super(joystick);
    setAxisCount(6);
    setButtonCount(10);
    setPOVCount(1);
  }

  /**
   * Constructs from a joystick port number.
   *
   * @param port port number
   */
  @SuppressWarnings("this-escape")
  public XboxControllerSim(int port) {
    super(port);
    setAxisCount(6);
    setButtonCount(10);
    setPOVCount(1);
  }

  /**
   * Change the left X value of the controller's joystick.
   *
   * @param value the new value
   */
  public void setLeftX(double value) {
    setRawAxis(XboxController.Axis.kLeftX.value, value);
  }

  /**
   * Change the right X value of the controller's joystick.
   *
   * @param value the new value
   */
  public void setRightX(double value) {
    setRawAxis(XboxController.Axis.kRightX.value, value);
  }

  /**
   * Change the left Y value of the controller's joystick.
   *
   * @param value the new value
   */
  public void setLeftY(double value) {
    setRawAxis(XboxController.Axis.kLeftY.value, value);
  }

  /**
   * Change the right Y value of the controller's joystick.
   *
   * @param value the new value
   */
  public void setRightY(double value) {
    setRawAxis(XboxController.Axis.kRightY.value, value);
  }

  /**
   * Change the value of the left trigger axis on the controller.
   *
   * @param value the new value
   */
  public void setLeftTriggerAxis(double value) {
    setRawAxis(XboxController.Axis.kLeftTrigger.value, value);
  }

  /**
   * Change the value of the right trigger axis on the controller.
   *
   * @param value the new value
   */
  public void setRightTriggerAxis(double value) {
    setRawAxis(XboxController.Axis.kRightTrigger.value, value);
  }

  /**
   * Change the value of the A button on the controller.
   *
   * @param value the new value
   */
  public void setAButton(boolean value) {
    setRawButton(XboxController.Button.kA.value, value);
  }

  /**
   * Change the value of the B button on the controller.
   *
   * @param value the new value
   */
  public void setBButton(boolean value) {
    setRawButton(XboxController.Button.kB.value, value);
  }

  /**
   * Change the value of the X button on the controller.
   *
   * @param value the new value
   */
  public void setXButton(boolean value) {
    setRawButton(XboxController.Button.kX.value, value);
  }

  /**
   * Change the value of the Y button on the controller.
   *
   * @param value the new value
   */
  public void setYButton(boolean value) {
    setRawButton(XboxController.Button.kY.value, value);
  }

  /**
   * Change the value of the left bumper button on the controller.
   *
   * @param value the new value
   */
  public void setLeftBumperButton(boolean value) {
    setRawButton(XboxController.Button.kLeftBumper.value, value);
  }

  /**
   * Change the value of the right bumper button on the controller.
   *
   * @param value the new value
   */
  public void setRightBumperButton(boolean value) {
    setRawButton(XboxController.Button.kRightBumper.value, value);
  }

  /**
   * Change the value of the back button on the controller.
   *
   * @param value the new value
   */
  public void setBackButton(boolean value) {
    setRawButton(XboxController.Button.kBack.value, value);
  }

  /**
   * Change the value of the start button on the controller.
   *
   * @param value the new value
   */
  public void setStartButton(boolean value) {
    setRawButton(XboxController.Button.kStart.value, value);
  }

  /**
   * Change the value of the left stick button on the controller.
   *
   * @param value the new value
   */
  public void setLeftStickButton(boolean value) {
    setRawButton(XboxController.Button.kLeftStick.value, value);
  }

  /**
   * Change the value of the right stick button on the controller.
   *
   * @param value the new value
   */
  public void setRightStickButton(boolean value) {
    setRawButton(XboxController.Button.kRightStick.value, value);
  }

  /**
   * Change the value of the left bumper on the joystick.
   *
   * @param state the new value
   * @deprecated Use {@link setLeftBumperButton} instead
   */
  @Deprecated(since = "2025", forRemoval = true)
  public void setLeftBumper(boolean state) {
    setRawButton(XboxController.Button.kLeftBumper.value, state);
  }

  /**
   * Change the value of the right bumper on the joystick.
   *
   * @param state the new value
   * @deprecated Use {@link setRightBumperButton} instead
   */
  @Deprecated(since = "2025", forRemoval = true)
  public void setRightBumper(boolean state) {
    setRawButton(XboxController.Button.kRightBumper.value, state);
  }
}
