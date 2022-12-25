// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.net;

import edu.wpi.first.util.WpiCleaner;
import java.lang.ref.Cleaner.Cleanable;
import java.util.Map;

/** Class to announce over mDNS that a service is available. */
public class MulticastServiceAnnouncer implements AutoCloseable {
  private final int m_handle;
  private final Cleanable m_cleanable;

  /**
   * Creates a MulticastServiceAnnouncer.
   *
   * @param serviceName service name
   * @param serviceType service type
   * @param port port
   * @param txt txt
   */
  public MulticastServiceAnnouncer(
      String serviceName, String serviceType, int port, Map<String, String> txt) {
    String[] keys = txt.keySet().toArray(String[]::new);
    String[] values = txt.values().toArray(String[]::new);
    m_handle =
        WPINetJNI.createMulticastServiceAnnouncer(serviceName, serviceType, port, keys, values);
    int localHandle = m_handle;
    m_cleanable =
        WpiCleaner.getInstance()
            .register(this, () -> WPINetJNI.freeMulticastServiceAnnouncer(localHandle));
  }

  /**
   * Creates a MulticastServiceAnnouncer.
   *
   * @param serviceName service name
   * @param serviceType service type
   * @param port port
   */
  public MulticastServiceAnnouncer(String serviceName, String serviceType, int port) {
    m_handle =
        WPINetJNI.createMulticastServiceAnnouncer(serviceName, serviceType, port, null, null);
    int localHandle = m_handle;
    m_cleanable =
        WpiCleaner.getInstance()
            .register(this, () -> WPINetJNI.freeMulticastServiceAnnouncer(localHandle));
  }

  @Override
  public void close() {
    m_cleanable.clean();
  }

  public void start() {
    WPINetJNI.startMulticastServiceAnnouncer(m_handle);
  }

  public void stop() {
    WPINetJNI.stopMulticastServiceAnnouncer(m_handle);
  }

  public boolean hasImplementation() {
    return WPINetJNI.getMulticastServiceAnnouncerHasImplementation(m_handle);
  }
}
