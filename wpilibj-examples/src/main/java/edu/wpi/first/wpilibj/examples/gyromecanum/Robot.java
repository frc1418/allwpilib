/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2017. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.examples.gyromecanum;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 * This is a sample program that uses mecanum drive with a gyro sensor to
 * maintian rotation vectorsin relation to the starting orientation of the robot
 * (field-oriented controls).
 */
public class Robot extends IterativeRobot {
	// gyro calibration constant, may need to be adjusted;
	// gyro value of 360 is set to correspond to one full revolution
	private static final double kVoltsPerDegreePerSecond = 0.0128;

	private static final int kFrontLeftMotorPort = 0;
	private static final int kFrontRightMotorPort = 1;
	private static final int kRearLeftMotorPort = 2;
	private static final int kRearRightMotorPort = 3;
	private static final int kGyroPort = 0;
	private static final int kJoystickPort = 0;

	private RobotDrive m_myRobot;
	private AnalogGyro m_gyro = new AnalogGyro(kGyroPort);
	private Joystick m_joystick = new Joystick(kJoystickPort);

	@Override
	public void robotInit() {
		m_myRobot = new RobotDrive(kFrontLeftMotorPort, kFrontRightMotorPort, kRearLeftMotorPort,
				kRearRightMotorPort);

		// invert the left side motors
		// you may need to change or remove this to match your robot
		m_myRobot.setInvertedMotor(MotorType.kFrontLeft, true);
		m_myRobot.setInvertedMotor(MotorType.kRearLeft, true);

		m_gyro.setSensitivity(kVoltsPerDegreePerSecond);
	}

	/**
	 * Mecanum drive is used with the gyro angle as an input.
	 */
	@Override
	public void teleopPeriodic() {
		m_myRobot.mecanumDrive_Cartesian(m_joystick.getX(), m_joystick.getY(), m_joystick.getZ(),
				m_gyro.getAngle());
	}
}
