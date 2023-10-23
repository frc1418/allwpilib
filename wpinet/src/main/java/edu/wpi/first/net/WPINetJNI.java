// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.net;

import edu.wpi.first.util.RuntimeLoader;
import java.io.IOException;

public class WPINetJNI {
  static boolean libraryLoaded = false;
  static RuntimeLoader<WPINetJNI> loader = null;

  /**
   * Load the library.
   *
   * @throws IOException if the library failed to load
   */
  public static synchronized void load() throws IOException {
    if (libraryLoaded) {
      return;
    }
    loader =
        new RuntimeLoader<>("wpinetjni", RuntimeLoader.getDefaultExtractionRoot(), WPINetJNI.class);
    loader.loadLibrary();
    libraryLoaded = true;
  }

  public static native void addPortForwarder(int port, String remoteHost, int remotePort);

  public static native void removePortForwarder(int port);

  public static native int createMulticastServiceAnnouncer(
      String serviceName, String serviceType, int port, String[] keys, String[] values);

  public static native void freeMulticastServiceAnnouncer(int handle);

  public static native void startMulticastServiceAnnouncer(int handle);

  public static native void stopMulticastServiceAnnouncer(int handle);

  public static native boolean getMulticastServiceAnnouncerHasImplementation(int handle);

  public static native int createMulticastServiceResolver(String serviceType);

  public static native void freeMulticastServiceResolver(int handle);

  public static native void startMulticastServiceResolver(int handle);

  public static native void stopMulticastServiceResolver(int handle);

  public static native boolean getMulticastServiceResolverHasImplementation(int handle);

  public static native int getMulticastServiceResolverEventHandle(int handle);

  public static native ServiceData[] getMulticastServiceResolverData(int handle);
}
