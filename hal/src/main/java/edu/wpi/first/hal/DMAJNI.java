/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                            */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.hal;

import java.nio.IntBuffer;

public class DMAJNI extends JNIWrapper {
  public static native int initialize();
  public static native void free(int handle);

  public static native void setPause(int handle, boolean pause);
  public static native void setRate(int handle, int cycles);

  public static native void addEncoder(int handle, int encoderHandle);
  public static native void addEncoderRate(int handle, int encoderHandle);

  public static native void addCounter(int handle, int counterHandle);
  public static native void addCounterRate(int handle, int counterHandle);

  public static native void addDigitalSource(int handle, int digitalSourceHandle);
  public static native void addAnalogInput(int handle, int analogInputHandle);
  public static native void addAveragedAnalogInput(int handle, int analogInputHandle);
  public static native void addAnalogAccumulator(int handle, int analogInputHandle);

  public static native void setExternalTrigger(int handle, int digitalSourceHandle, int analogTriggerType,
                          boolean rising, boolean falling);

  public static native void startDMA(int handle, int queueDepth);
  public static native void stopDMA(int handle);

  // 0-63: readBuffer
  // 64-83 channelOffsets
  // 84: capture size
  // 85: triggerChannels (bitflags)
  public static native long readDMA(int handle, int[] sampleStore);
}
