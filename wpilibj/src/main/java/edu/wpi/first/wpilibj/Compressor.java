// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.util.AllocationException;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;

/**
 * Class for operating a compressor connected to a pneumatics module. The module will automatically
 * run in closed loop mode by default whenever a {@link Solenoid} object is created. For most cases,
 * a Compressor object does not need to be instantiated or used in a robot program. This class is
 * only required in cases where the robot program needs a more detailed status of the compressor or
 * to enable/disable closed loop control.
 *
 * <p>Note: you cannot operate the compressor directly from this class as doing so would circumvent
 * the safety provided by using the pressure switch and closed loop control. You can only turn off
 * closed loop control, thereby stopping the compressor from operating.
 */
public class Compressor implements Sendable, AutoCloseable {
  private PneumaticsBase m_module;

  /**
   * Constructs a compressor for a specified module and type.
   *
   * @param module The module ID to use.
   * @param moduleType The module type to use.
   */
  public Compressor(int module, PneumaticsModuleType moduleType) {
    m_module = PneumaticsBase.getForType(module, moduleType);
    boolean allocatedCompressor = false;
    boolean successfulCompletion = false;

    try {
      if (!m_module.reserveCompressor()) {
        throw new AllocationException("Compressor already allocated");
      }

      allocatedCompressor = true;

      m_module.enableCompressorDigital();

      HAL.report(tResourceType.kResourceType_Compressor, module + 1);
      SendableRegistry.addLW(this, "Compressor", module);
      successfulCompletion = true;

    } finally {
      if (!successfulCompletion) {
        if (allocatedCompressor) {
          m_module.unreserveCompressor();
        }
        m_module.close();
      }
    }
  }

  /**
   * Constructs a compressor for a default module and specified type.
   *
   * @param moduleType The module type to use.
   */
  public Compressor(PneumaticsModuleType moduleType) {
    this(PneumaticsBase.getDefaultForType(moduleType), moduleType);
  }

  @Override
  public void close() {
    SendableRegistry.remove(this);
    m_module.unreserveCompressor();
    m_module.close();
    m_module = null;
  }

  /**
   * Get the status of the compressor.
   *
   * @return true if the compressor is on
   */
  public boolean enabled() {
    return m_module.getCompressor();
  }

  /**
   * Get the pressure switch value.
   *
   * @return true if the pressure is low
   */
  public boolean getPressureSwitchValue() {
    return m_module.getPressureSwitch();
  }

  /**
   * Get the current being used by the compressor.
   *
   * @return current consumed by the compressor in amps
   */
  public double getCurrent() {
    return m_module.getCompressorCurrent();
  }

  public void disable() {
    m_module.disableCompressor();
  }

  public void enableDigital() {
    m_module.enableCompressorDigital();
  }

  public void enableAnalog(double minAnalogVoltage, double maxAnalogVoltage) {
    m_module.enableCompressorAnalog(minAnalogVoltage, maxAnalogVoltage);
  }

  public void enableHybrid(double minAnalogVoltage, double maxAnalogVoltage) {
    m_module.enableCompressorHybrid(minAnalogVoltage, maxAnalogVoltage);
  }

  /**
   * Gets the current operating mode of the Compressor.
   *
   * @return true if compressor is operating on closed-loop mode
   */
  public CompressorControlType getControlType() {
    return m_module.getCompressorControlType();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Compressor");
    builder.addBooleanProperty("Enabled", this::enabled, null);
    builder.addBooleanProperty("Pressure switch", this::getPressureSwitchValue, null);
  }
}
