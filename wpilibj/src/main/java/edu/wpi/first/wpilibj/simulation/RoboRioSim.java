// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import edu.wpi.first.hal.simulation.NotifyCallback;
import edu.wpi.first.hal.simulation.RoboRioDataJNI;

/** Class to control a simulated RoboRIO. */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.UseUtilityClass"})
public class RoboRioSim {
  /**
   * Register a callback to be run when the FPGA button state changes.
   * @param callback the callback
   * @param initialNotify whether to run the callback with the initial state
   * @return the {@link CallbackStore} object associated with this callback
   */
  @SuppressWarnings("AbbreviationAsWordInName")
  public static CallbackStore registerFPGAButtonCallback(NotifyCallback callback,
                                                         boolean initialNotify) {
    int uid = RoboRioDataJNI.registerFPGAButtonCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelFPGAButtonCallback);
  }

  /**
   * Query the state of the FPGA button.
   * @return the FPGA button state
   */
  @SuppressWarnings("AbbreviationAsWordInName")
  public static boolean getFPGAButton() {
    return RoboRioDataJNI.getFPGAButton();
  }

  /**
   * Define the state of the FPGA button.
   * @param fpgaButton the new state
   */
  @SuppressWarnings("AbbreviationAsWordInName")
  public static void setFPGAButton(boolean fpgaButton) {
    RoboRioDataJNI.setFPGAButton(fpgaButton);
  }

  /**
   * Register a callback to be run whenever the Vin voltage changes.
   * @param callback the callback
   * @param initialNotify whether to call the callback with the initial state
   * @return the {@link CallbackStore} object associated with this callback
   */
  public static CallbackStore registerVInVoltageCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerVInVoltageCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelVInVoltageCallback);
  }

  /**
   * Measure the Vin voltage.
   * @return the Vin voltage
   */
  public static double getVInVoltage() {
    return RoboRioDataJNI.getVInVoltage();
  }

  /**
   * Define teh Vin voltage.
   * @param vInVoltage the new voltage
   */
  @SuppressWarnings("ParameterName")
  public static void setVInVoltage(double vInVoltage) {
    RoboRioDataJNI.setVInVoltage(vInVoltage);
  }

  /**
   * Register a callback to be run whenever the Vin current changes.
   * @param callback the callback
   * @param initialNotify whether the callback should be called with the initial value
   * @return the {@link CallbackStore} object associated with this callback
   */
  public static CallbackStore registerVInCurrentCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerVInCurrentCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelVInCurrentCallback);
  }

  public static double getVInCurrent() {
    return RoboRioDataJNI.getVInCurrent();
  }

  @SuppressWarnings("ParameterName")
  public static void setVInCurrent(double vInCurrent) {
    RoboRioDataJNI.setVInCurrent(vInCurrent);
  }

  public static CallbackStore registerUserVoltage6VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserVoltage6VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserVoltage6VCallback);
  }

  public static double getUserVoltage6V() {
    return RoboRioDataJNI.getUserVoltage6V();
  }

  public static void setUserVoltage6V(double userVoltage6V) {
    RoboRioDataJNI.setUserVoltage6V(userVoltage6V);
  }

  public static CallbackStore registerUserCurrent6VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserCurrent6VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserCurrent6VCallback);
  }

  public static double getUserCurrent6V() {
    return RoboRioDataJNI.getUserCurrent6V();
  }

  public static void setUserCurrent6V(double userCurrent6V) {
    RoboRioDataJNI.setUserCurrent6V(userCurrent6V);
  }

  public static CallbackStore registerUserActive6VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserActive6VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserActive6VCallback);
  }

  public static boolean getUserActive6V() {
    return RoboRioDataJNI.getUserActive6V();
  }

  public static void setUserActive6V(boolean userActive6V) {
    RoboRioDataJNI.setUserActive6V(userActive6V);
  }

  public static CallbackStore registerUserVoltage5VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserVoltage5VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserVoltage5VCallback);
  }

  public static double getUserVoltage5V() {
    return RoboRioDataJNI.getUserVoltage5V();
  }

  public static void setUserVoltage5V(double userVoltage5V) {
    RoboRioDataJNI.setUserVoltage5V(userVoltage5V);
  }

  public static CallbackStore registerUserCurrent5VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserCurrent5VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserCurrent5VCallback);
  }

  public static double getUserCurrent5V() {
    return RoboRioDataJNI.getUserCurrent5V();
  }

  public static void setUserCurrent5V(double userCurrent5V) {
    RoboRioDataJNI.setUserCurrent5V(userCurrent5V);
  }

  public static CallbackStore registerUserActive5VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserActive5VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserActive5VCallback);
  }

  public static boolean getUserActive5V() {
    return RoboRioDataJNI.getUserActive5V();
  }

  public static void setUserActive5V(boolean userActive5V) {
    RoboRioDataJNI.setUserActive5V(userActive5V);
  }

  public static CallbackStore registerUserVoltage3V3Callback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserVoltage3V3Callback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserVoltage3V3Callback);
  }

  public static double getUserVoltage3V3() {
    return RoboRioDataJNI.getUserVoltage3V3();
  }

  public static void setUserVoltage3V3(double userVoltage3V3) {
    RoboRioDataJNI.setUserVoltage3V3(userVoltage3V3);
  }

  public static CallbackStore registerUserCurrent3V3Callback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserCurrent3V3Callback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserCurrent3V3Callback);
  }

  public static double getUserCurrent3V3() {
    return RoboRioDataJNI.getUserCurrent3V3();
  }

  public static void setUserCurrent3V3(double userCurrent3V3) {
    RoboRioDataJNI.setUserCurrent3V3(userCurrent3V3);
  }

  public static CallbackStore registerUserActive3V3Callback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserActive3V3Callback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserActive3V3Callback);
  }

  public static boolean getUserActive3V3() {
    return RoboRioDataJNI.getUserActive3V3();
  }

  public static void setUserActive3V3(boolean userActive3V3) {
    RoboRioDataJNI.setUserActive3V3(userActive3V3);
  }

  public static CallbackStore registerUserFaults6VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserFaults6VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserFaults6VCallback);
  }

  public static int getUserFaults6V() {
    return RoboRioDataJNI.getUserFaults6V();
  }

  public static void setUserFaults6V(int userFaults6V) {
    RoboRioDataJNI.setUserFaults6V(userFaults6V);
  }

  public static CallbackStore registerUserFaults5VCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserFaults5VCallback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserFaults5VCallback);
  }

  public static int getUserFaults5V() {
    return RoboRioDataJNI.getUserFaults5V();
  }

  public static void setUserFaults5V(int userFaults5V) {
    RoboRioDataJNI.setUserFaults5V(userFaults5V);
  }

  public static CallbackStore registerUserFaults3V3Callback(
      NotifyCallback callback, boolean initialNotify) {
    int uid = RoboRioDataJNI.registerUserFaults3V3Callback(callback, initialNotify);
    return new CallbackStore(uid, RoboRioDataJNI::cancelUserFaults3V3Callback);
  }

  public static int getUserFaults3V3() {
    return RoboRioDataJNI.getUserFaults3V3();
  }

  public static void setUserFaults3V3(int userFaults3V3) {
    RoboRioDataJNI.setUserFaults3V3(userFaults3V3);
  }

  /**
   * Reset all simulation data.
   */
  public static void resetData() {
    RoboRioDataJNI.resetData();
  }
}
