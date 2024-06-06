// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  @SuppressWarnings("resource")
  public void robotInit() {
    // Solenoid sCTRE = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    Solenoid sREV = new Solenoid(PneumaticsModuleType.REVPH, 0);
    // new Compressor(PneumaticsModuleType.CTREPCM);/
    // new Compressor(PneumaticsModuleType.REVPH);
  }

  /*
   * Good
   * main.cpp
   * REVPHSimGui.h
   * Getting there, still need to split compressor data
   * REVPHSimGui.cpp
   * PCM.cpp
   * PCM.h
   * 
   */

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once each time the robot enters tele-operated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called periodically during all modes. */
  @Override
  public void robotPeriodic() {}
}
