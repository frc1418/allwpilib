/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#define REAL

#include "ADXL345_I2C.h"
#include "ADXL345_SPI.h"
#include "ADXL362.h"
#include "ADXRS450_Gyro.h"
#include "AnalogAccelerometer.h"
#include "AnalogGyro.h"
#include "AnalogInput.h"
#include "AnalogOutput.h"
#include "AnalogPotentiometer.h"
#include "AnalogTrigger.h"
#include "AnalogTriggerOutput.h"
#include "BuiltInAccelerometer.h"
#include "Buttons/InternalButton.h"
#include "Buttons/JoystickButton.h"
#include "Buttons/NetworkButton.h"
#include "CANJaguar.h"
#include "CameraServer.h"
#include "Commands/Command.h"
#include "Commands/CommandGroup.h"
#include "Commands/PIDCommand.h"
#include "Commands/PIDSubsystem.h"
#include "Commands/PrintCommand.h"
#include "Commands/Scheduler.h"
#include "Commands/StartCommand.h"
#include "Commands/Subsystem.h"
#include "Commands/WaitCommand.h"
#include "Commands/WaitForChildren.h"
#include "Commands/WaitUntilCommand.h"
#include "Compressor.h"
#include "ControllerPower.h"
#include "Counter.h"
#include "DigitalInput.h"
#include "DigitalOutput.h"
#include "DigitalSource.h"
#include "DoubleSolenoid.h"
#include "DriverStation.h"
#include "Encoder.h"
#include "ErrorBase.h"
#include "Filters/LinearDigitalFilter.h"
#include "GearTooth.h"
#include "GenericHID.h"
#include "I2C.h"
#include "InterruptableSensorBase.h"
#include "IterativeRobot.h"
#include "Jaguar.h"
#include "Joystick.h"
#include "Notifier.h"
#include "PIDController.h"
#include "PIDOutput.h"
#include "PIDSource.h"
#include "PWM.h"
#include "PWMSpeedController.h"
#include "PowerDistributionPanel.h"
#include "Preferences.h"
#include "Relay.h"
#include "RobotBase.h"
#include "RobotDrive.h"
#include "SD540.h"
#include "SPI.h"
#include "SampleRobot.h"
#include "SensorBase.h"
#include "SerialPort.h"
#include "Servo.h"
#include "SmartDashboard/SendableChooser.h"
#include "SmartDashboard/SmartDashboard.h"
#include "Solenoid.h"
#include "Spark.h"
#include "SpeedController.h"
#include "Talon.h"
#include "TalonSRX.h"
#include "Task.h"
#include "Timer.h"
#include "Ultrasonic.h"
#include "Utility.h"
#include "Victor.h"
#include "VictorSP.h"
#include "Vision/AxisCamera.h"
#include "Vision/BinaryImage.h"
#include "Vision/ColorImage.h"
#include "Vision/HSLImage.h"
#include "Vision/ImageBase.h"
#include "Vision/MonoImage.h"
#include "Vision/RGBImage.h"
#include "Vision/Threshold.h"
#include "WPIErrors.h"
#include "interfaces/Accelerometer.h"
#include "interfaces/Gyro.h"
#include "interfaces/Potentiometer.h"
