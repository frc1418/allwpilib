/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.examples.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

//import java.util.Map; // for Map.of(), introduced in Java 9

@SuppressWarnings({"MemberName", "LineLength", "RegexpSinglelinejava"})
public class Robot extends IterativeRobot {
  private final DifferentialDrive tankDrive = new DifferentialDrive(new Spark(0), new Spark(1));
  private final Encoder leftEncoder = new Encoder(0, 1);
  private final Encoder rightEncoder = new Encoder(2, 3);

  private final Spark elevatorMotor = new Spark(2);
  private final AnalogPotentiometer elevatorPot = new AnalogPotentiometer(0);
  private NetworkTableEntry maxSpeed;

  @Override
  public void robotInit() {
    // Add a 'max speed' widget to a tab named 'Configuration', using a number slider
    // The widget will be placed in the second column and row and will be two columns wide
    maxSpeed = Shuffleboard.getTab("Configuration")
                           .add("Max Speed", 1)
                           .withWidget("Number Slider")
                           //.withProperties(Map.of("min", 0, "max", 1)) // Map.of introduced in Java 9
                           .withPosition(1, 1)
                           .withSize(2, 1)
                           .getEntry();

    // Add the tank drive and encoders to a 'Drivebase' tab
    ShuffleboardTab driveBaseTab = Shuffleboard.getTab("Drivebase");
    driveBaseTab.add("Tank Drive", tankDrive);
    // Put both encoders in a list layout
    ShuffleboardLayout encoders = driveBaseTab.getLayout("List", "Encoders")
                                              .withPosition(0, 0)
                                              .withSize(2, 2);
    encoders.add("Left Encoder", leftEncoder);
    encoders.add("Right Encoder", rightEncoder);

    // Add the elevator motor and potentiometer to an 'Elevator' tab
    ShuffleboardTab elevatorTab = Shuffleboard.getTab("Elevator");
    elevatorTab.add("Motor", elevatorMotor);
    elevatorTab.add("Potentiometer", elevatorPot);
  }

  @Override
  public void autonomousInit() {
    // Read the value of the 'max speed' widget from the dashboard
    tankDrive.setMaxOutput(maxSpeed.getDouble(1.0));
  }

}
