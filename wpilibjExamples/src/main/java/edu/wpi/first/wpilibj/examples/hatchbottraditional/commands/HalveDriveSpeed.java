// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.examples.hatchbottraditional.commands;

import org.wpilib.wpilibj.examples.hatchbottraditional.subsystems.DriveSubsystem;
import org.wpilib.wpilibj2.command.Command;

public class HalveDriveSpeed extends Command {
  private final DriveSubsystem m_drive;

  public HalveDriveSpeed(DriveSubsystem drive) {
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.setMaxOutput(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.setMaxOutput(1);
  }
}
