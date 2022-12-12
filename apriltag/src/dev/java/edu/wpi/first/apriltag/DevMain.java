// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.apriltag;

import edu.wpi.first.apriltag.jni.AprilTagJNI;

public final class DevMain {
  /** Main entry point. */
  public static void main(String[] args) {
    System.out.println("Hello World!");
    var detector=AprilTagJNI.aprilTagCreate("tag16h5", 2.0, 0.0, 1, false, false);
    AprilTagJNI.aprilTagDestroy(detector);
  }

  private DevMain() {}
}
