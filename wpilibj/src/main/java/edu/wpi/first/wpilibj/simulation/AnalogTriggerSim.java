// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import edu.wpi.first.hal.simulation.AnalogTriggerDataJNI;
import edu.wpi.first.hal.simulation.NotifyCallback;
import edu.wpi.first.wpilibj.AnalogTrigger;
import java.util.NoSuchElementException;

/** Class to control a simulated analog trigger. */
public class AnalogTriggerSim {
  private final int m_index;

  /**
   * Constructs from an AnalogTrigger object.
   *
   * @param analogTrigger AnalogTrigger to simulate
   */
  public AnalogTriggerSim(AnalogTrigger analogTrigger) {
    m_index = analogTrigger.getIndex();
  }

  private AnalogTriggerSim(int index) {
    m_index = index;
  }

  /**
   * Creates an AnalogTriggerSim for an analog input channel.
   *
   * @param channel analog input channel
   * @return Simulated object
   * @throws NoSuchElementException if no AnalogTrigger is configured for that channel
   */
  public static AnalogTriggerSim createForChannel(int channel) {
    int index = AnalogTriggerDataJNI.findForChannel(channel);
    if (index < 0) {
      throw new NoSuchElementException("no analog trigger found for channel " + channel);
    }
    return new AnalogTriggerSim(index);
  }

  /**
   * Creates an AnalogTriggerSim for a simulated index. The index is incremented for each simulated
   * AnalogTrigger.
   *
   * @param index simulator index
   * @return Simulated object
   */
  public static AnalogTriggerSim createForIndex(int index) {
    return new AnalogTriggerSim(index);
  }

  public CallbackStore registerInitializedCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = AnalogTriggerDataJNI.registerInitializedCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, AnalogTriggerDataJNI::cancelInitializedCallback);
  }

  public boolean getInitialized() {
    return AnalogTriggerDataJNI.getInitialized(m_index);
  }

  public void setInitialized(boolean initialized) {
    AnalogTriggerDataJNI.setInitialized(m_index, initialized);
  }

  public CallbackStore registerTriggerLowerBoundCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid =
        AnalogTriggerDataJNI.registerTriggerLowerBoundCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, AnalogTriggerDataJNI::cancelTriggerLowerBoundCallback);
  }

  public double getTriggerLowerBound() {
    return AnalogTriggerDataJNI.getTriggerLowerBound(m_index);
  }

  public void setTriggerLowerBound(double triggerLowerBound) {
    AnalogTriggerDataJNI.setTriggerLowerBound(m_index, triggerLowerBound);
  }

  public CallbackStore registerTriggerUpperBoundCallback(
      NotifyCallback callback, boolean initialNotify) {
    int uid =
        AnalogTriggerDataJNI.registerTriggerUpperBoundCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, AnalogTriggerDataJNI::cancelTriggerUpperBoundCallback);
  }

  public double getTriggerUpperBound() {
    return AnalogTriggerDataJNI.getTriggerUpperBound(m_index);
  }

  public void setTriggerUpperBound(double triggerUpperBound) {
    AnalogTriggerDataJNI.setTriggerUpperBound(m_index, triggerUpperBound);
  }

  public void resetData() {
    AnalogTriggerDataJNI.resetData(m_index);
  }
}
