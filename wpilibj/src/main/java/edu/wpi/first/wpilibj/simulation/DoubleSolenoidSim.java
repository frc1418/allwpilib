package edu.wpi.first.wpilibj.simulation;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/** Class to control a simulated {@link edu.wpi.first.wpilibj.DoubleSolenoid}. */
public class DoubleSolenoidSim {
  private final PneumaticsBaseSim m_module;
  private final int m_fwd;
  private final int m_rev;

  /**
   * Constructs for a solenoid on the given pneumatics module.
   *
   * @param moduleSim the PCM the solenoid is connected to.
   * @param fwd the forward solenoid channel.
   * @param rev the reverse solenoid channel.
   */
  public DoubleSolenoidSim(PneumaticsBaseSim moduleSim, int fwd, int rev) {
    m_module = moduleSim;
    m_fwd = fwd;
    m_rev = rev;
  }

  /**
   * Constructs for a solenoid on a pneumatics module of the given type and ID.
   *
   * @param module the CAN ID of the pneumatics module the solenoid is connected to.
   * @param moduleType the module type (PH or PCM)
   * @param fwd the forward solenoid channel.
   * @param rev the reverse solenoid channel.
   */
  public DoubleSolenoidSim(int module, PneumaticsModuleType moduleType, int fwd, int rev) {
    this(PneumaticsBaseSim.getForType(module, moduleType), fwd, rev);
  }

  /**
   * Constructs for a solenoid on a pneumatics module of the given type and default ID.
   *
   * @param moduleType the module type (PH or PCM)
   * @param fwd the forward solenoid channel.
   * @param rev the reverse solenoid channel.
   */
  public DoubleSolenoidSim(PneumaticsModuleType moduleType, int fwd, int rev) {
    this(PneumaticsBase.getDefaultForType(moduleType), moduleType, fwd, rev);
  }

  /**
   * Check the value of the double solenoid output.
   *
   * @return the output value of the double solenoid.
   */
  public DoubleSolenoid.Value get() {
    boolean fwdState = m_module.getSolenoidOutput(m_fwd);
    boolean revState = m_module.getSolenoidOutput(m_rev);
    if (!fwdState && !revState) {
      return DoubleSolenoid.Value.kOff;
    } else if (fwdState && !revState) {
      return DoubleSolenoid.Value.kForward;
    } else if (!fwdState && revState) {
      return DoubleSolenoid.Value.kReverse;
    } else {
      throw new AssertionError(
          "In a double solenoid, both fwd and rev can't be on at the same time.");
    }
  }

  /**
   * Set the value of the double solenoid output.
   *
   * @param value The value to set (Off, Forward, Reverse)
   */
  public void set(final DoubleSolenoid.Value value) {
    boolean forward;
    boolean reverse;

    switch (value) {
      case kOff:
        forward = false;
        reverse = false;
        break;
      case kForward:
        forward = true;
        reverse = false;
        break;
      case kReverse:
        forward = false;
        reverse = true;
        break;
      default:
        throw new AssertionError("Illegal value: " + value);
    }

    m_module.setSolenoidOutput(m_fwd, forward);
    m_module.setSolenoidOutput(m_rev, reverse);
  }

  /**
   * Get the wrapped {@link PneumaticsBaseSim} object.
   *
   * @return the wrapped {@link PneumaticsBaseSim} object.
   */
  public PneumaticsBaseSim getPCMSim() {
    return m_module;
  }
}
