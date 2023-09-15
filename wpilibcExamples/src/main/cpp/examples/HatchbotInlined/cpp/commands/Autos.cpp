// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "commands/Autos.h"

#include <frc/command2/Commands.h>
#include <frc/command2/FunctionalCommand.h>

#include "Constants.h"

using namespace AutoConstants;

frc::CommandPtr autos::SimpleAuto(DriveSubsystem* drive) {
  return frc::FunctionalCommand(
             // Reset encoders on command start
             [drive] { drive->ResetEncoders(); },
             // Drive forward while the command is executing
             [drive] { drive->ArcadeDrive(AutoConstants::kAutoDriveSpeed, 0); },
             // Stop driving at the end of the command
             [drive](bool interrupted) { drive->ArcadeDrive(0, 0); },
             // End the command when the robot's driven distance exceeds the
             // desired value
             [drive] {
               return drive->GetAverageEncoderDistance() >=
                      AutoConstants::kAutoDriveDistanceInches;
             },
             // Requires the drive subsystem
             {drive})
      .ToPtr();
}

frc::CommandPtr autos::ComplexAuto(DriveSubsystem* drive,
                                   HatchSubsystem* hatch) {
  return frc::cmd::Sequence(
      // Drive forward the specified distance
      frc::FunctionalCommand(
          // Reset encoders on command start
          [drive] { drive->ResetEncoders(); },
          // Drive forward while the command is executing
          [drive] { drive->ArcadeDrive(kAutoDriveSpeed, 0); },
          // Stop driving at the end of the command
          [drive](bool interrupted) { drive->ArcadeDrive(0, 0); },
          // End the command when the robot's driven distance exceeds the
          // desired value
          [drive] {
            return drive->GetAverageEncoderDistance() >=
                   kAutoDriveDistanceInches;
          },
          // Requires the drive subsystem
          {drive})
          .ToPtr(),
      // Release the hatch
      hatch->ReleaseHatchCommand(),
      // Drive backward the specified distance
      // Drive forward the specified distance
      frc::FunctionalCommand(
          // Reset encoders on command start
          [drive] { drive->ResetEncoders(); },
          // Drive backward while the command is executing
          [drive] { drive->ArcadeDrive(-kAutoDriveSpeed, 0); },
          // Stop driving at the end of the command
          [drive](bool interrupted) { drive->ArcadeDrive(0, 0); },
          // End the command when the robot's driven distance exceeds the
          // desired value
          [drive] {
            return drive->GetAverageEncoderDistance() <=
                   kAutoBackupDistanceInches;
          },
          // Requires the drive subsystem
          {drive})
          .ToPtr());
}
