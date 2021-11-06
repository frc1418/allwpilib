// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.counter;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.hal.CounterJNI;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;

public class UpDownCounter implements Sendable, AutoCloseable {
  private DigitalSource m_upSource;
  private DigitalSource m_downSource;

  private final int m_handle;

  public UpDownCounter(DigitalSource upSource, DigitalSource downSource) {
    ByteBuffer index = ByteBuffer.allocateDirect(4);
    // set the byte order
    index.order(ByteOrder.LITTLE_ENDIAN);
    m_handle = CounterJNI.initializeCounter(0, index.asIntBuffer());

    if (upSource != null) {
      m_upSource = upSource;
      CounterJNI.setCounterUpSource(m_handle, upSource.getPortHandleForRouting(),
          upSource.getAnalogTriggerTypeForRouting());
      CounterJNI.setCounterUpSourceEdge(m_handle, true, false);
    }

    if (downSource != null) {
      m_downSource = downSource;
      CounterJNI.setCounterDownSource(m_handle, downSource.getPortHandleForRouting(),
      downSource.getAnalogTriggerTypeForRouting());
      CounterJNI.setCounterDownSourceEdge(m_handle, true, false);
    }

    reset();

    int intIndex = index.getInt();
    HAL.report(tResourceType.kResourceType_Counter, intIndex + 1);
    SendableRegistry.addLW(this, "UpDown Counter", intIndex);
  }

  @Override
  public void close() throws Exception {
    SendableRegistry.remove(this);
    CounterJNI.freeCounter(m_handle);
  }

  public void setUpEdgeConfiguration(EdgeConfiguration configuration) {
    CounterJNI.setCounterUpSourceEdge(m_handle, configuration.rising, configuration.falling);
  }

  public void setDownEdgeConfiguration(EdgeConfiguration configuration) {
    CounterJNI.setCounterDownSourceEdge(m_handle, configuration.rising, configuration.falling);
  }

  public void reset() {
    CounterJNI.resetCounter(m_handle);
  }

  public void setReverseDirection(boolean reverseDirection) {
    CounterJNI.setCounterReverseDirection(m_handle, reverseDirection);
  }

  public int getCount() {
    return CounterJNI.getCounter(m_handle);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("UpDown Counter");
    builder.addDoubleProperty("Count", this::getCount, null);
  }
}
