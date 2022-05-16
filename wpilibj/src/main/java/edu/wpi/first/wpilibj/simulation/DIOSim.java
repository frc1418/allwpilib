// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.simulation;

import edu.wpi.first.hal.simulation.DIODataJNI;
import edu.wpi.first.hal.simulation.NotifyCallback;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Class to control a simulated digital input or output.
 *
 * @deprecated Use {@link edu.wpi.first.wpilibj.simulation.DigitalSim}. instead.
 */
@Deprecated(since = "2022", forRemoval = true)
public class DIOSim extends DigitalSim {
  /**
   * Constructs from a DigitalInput object.
   *
   * @param input DigitalInput to simulate
   */
  public DIOSim(DigitalInput input) {
    super(input);
  }

  /**
   * Constructs from a DigitalOutput object.
   *
   * @param output DigitalOutput to simulate
   */
  public DIOSim(DigitalOutput output) {
    super(output);
  }

  /**
   * Constructs from an digital I/O channel number.
   *
   * @param channel Channel number
   */
  public DIOSim(int channel) {
    super(channel);
  }

  /**
   * Register a callback to be run when this DIO is initialized.
   *
   * @param callback the callback
   * @param initialNotify whether to run the callback with the initial state
   * @return the {@link CallbackStore} object associated with this callback. Save a reference to
   *     this object so GC doesn't cancel the callback.
   */
  @Override
  public CallbackStore registerInitializedCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = DIODataJNI.registerInitializedCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, DIODataJNI::cancelInitializedCallback);
  }

  /**
   * Check whether this DIO has been initialized.
   *
   * @return true if initialized
   */
  @Override
  public boolean getInitialized() {
    return DIODataJNI.getInitialized(m_index);
  }

  /**
   * Define whether this DIO has been initialized.
   *
   * @param initialized whether this object is initialized
   */
  @Override
  public void setInitialized(boolean initialized) {
    DIODataJNI.setInitialized(m_index, initialized);
  }

  /**
   * Register a callback to be run whenever the DIO value changes.
   *
   * @param callback the callback
   * @param initialNotify whether the callback should be called with the initial value
   * @return the {@link CallbackStore} object associated with this callback. Save a reference to
   *     this object so GC doesn't cancel the callback.
   */
  @Override
  public CallbackStore registerValueCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = DIODataJNI.registerValueCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, DIODataJNI::cancelValueCallback);
  }

  /**
   * Read the value of the DIO port.
   *
   * @return the DIO value
   */
  @Override
  public boolean getValue() {
    return DIODataJNI.getValue(m_index);
  }

  /**
   * Change the DIO value.
   *
   * @param value the new value
   */
  @Override
  public void setValue(boolean value) {
    DIODataJNI.setValue(m_index, value);
  }

  /**
   * Register a callback to be run whenever the pulse length changes.
   *
   * @param callback the callback
   * @param initialNotify whether to call the callback with the initial state
   * @return the {@link CallbackStore} object associated with this callback. Save a reference to
   *     this object so GC doesn't cancel the callback.
   */
  @Override
  public CallbackStore registerPulseLengthCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = DIODataJNI.registerPulseLengthCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, DIODataJNI::cancelPulseLengthCallback);
  }

  /**
   * Read the pulse length.
   *
   * @return the pulse length of this DIO port
   */
  @Override
  public double getPulseLength() {
    return DIODataJNI.getPulseLength(m_index);
  }

  /**
   * Change the pulse length of this DIO port.
   *
   * @param pulseLength the new pulse length
   */
  @Override
  public void setPulseLength(double pulseLength) {
    DIODataJNI.setPulseLength(m_index, pulseLength);
  }

  /**
   * Register a callback to be run whenever this DIO changes to be an input.
   *
   * @param callback the callback
   * @param initialNotify whether the callback should be called with the initial state
   * @return the {@link CallbackStore} object associated with this callback. Save a reference to
   *     this object so GC doesn't cancel the callback.
   */
  @Override
  public CallbackStore registerIsInputCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = DIODataJNI.registerIsInputCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, DIODataJNI::cancelIsInputCallback);
  }

  /**
   * Check whether this DIO port is currently an Input.
   *
   * @return true if Input
   */
  @Override
  public boolean getIsInput() {
    return DIODataJNI.getIsInput(m_index);
  }

  /**
   * Define whether this DIO port is an Input.
   *
   * @param isInput whether this DIO should be an Input
   */
  @Override
  public void setIsInput(boolean isInput) {
    DIODataJNI.setIsInput(m_index, isInput);
  }

  /**
   * Register a callback to be run whenever the filter index changes.
   *
   * @param callback the callback
   * @param initialNotify whether the callback should be called with the initial value
   * @return the {@link CallbackStore} object associated with this callback. Save a reference to
   *     this object so GC doesn't cancel the callback.
   */
  @Override
  public CallbackStore registerFilterIndexCallback(NotifyCallback callback, boolean initialNotify) {
    int uid = DIODataJNI.registerFilterIndexCallback(m_index, callback, initialNotify);
    return new CallbackStore(m_index, uid, DIODataJNI::cancelFilterIndexCallback);
  }

  /**
   * Read the filter index.
   *
   * @return the filter index of this DIO port
   */
  @Override
  public int getFilterIndex() {
    return DIODataJNI.getFilterIndex(m_index);
  }

  /**
   * Change the filter index of this DIO port.
   *
   * @param filterIndex the new filter index
   */
  @Override
  public void setFilterIndex(int filterIndex) {
    DIODataJNI.setFilterIndex(m_index, filterIndex);
  }

  /** Reset all simulation data of this object. */
  @Override
  public void resetData() {
    DIODataJNI.resetData(m_index);
  }
}
