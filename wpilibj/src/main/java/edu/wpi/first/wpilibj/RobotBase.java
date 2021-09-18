// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj;

import edu.wpi.first.cameraserver.CameraServerShared;
import edu.wpi.first.cameraserver.CameraServerSharedStore;
import edu.wpi.first.cscore.CameraServerJNI;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.HALUtil;
import edu.wpi.first.math.MathShared;
import edu.wpi.first.math.MathSharedStore;
import edu.wpi.first.math.MathUsageId;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Implement a Robot Program framework. The RobotBase class is intended to be subclassed by a user
 * creating a robot program. Overridden autonomous() and operatorControl() methods are called at the
 * appropriate time as the match proceeds. In the current implementation, the Autonomous code will
 * run to completion before the OperatorControl code could start. In the future the Autonomous code
 * might be spawned as a task, then killed at the end of the Autonomous period.
 */
public abstract class RobotBase implements AutoCloseable {
  /** The ID of the main Java thread. */
  // This is usually 1, but it is best to make sure
  private static long m_threadId = -1;

  private static void setupCameraServerShared() {
    CameraServerShared shared =
        new CameraServerShared() {
          @Override
          public void reportVideoServer(int id) {
            HAL.report(tResourceType.kResourceType_PCVideoServer, id + 1);
          }

          @Override
          public void reportUsbCamera(int id) {
            HAL.report(tResourceType.kResourceType_UsbCamera, id + 1);
          }

          @Override
          public void reportDriverStationError(String error) {
            DriverStation.reportError(error, true);
          }

          @Override
          public void reportAxisCamera(int id) {
            HAL.report(tResourceType.kResourceType_AxisCamera, id + 1);
          }

          @Override
          public Long getRobotMainThreadId() {
            return RobotBase.getMainThreadId();
          }

          @Override
          public boolean isRoboRIO() {
            return RobotBase.isReal();
          }
        };

    CameraServerSharedStore.setCameraServerShared(shared);
  }

  private static void setupMathShared() {
    MathSharedStore.setMathShared(
        new MathShared() {
          @Override
          public void reportError(String error, StackTraceElement[] stackTrace) {
            DriverStation.reportError(error, stackTrace);
          }

          @Override
          public void reportUsage(MathUsageId id, int count) {
            switch (id) {
              case kKinematics_DifferentialDrive:
                HAL.report(
                    tResourceType.kResourceType_Kinematics,
                    tInstances.kKinematics_DifferentialDrive);
                break;
              case kKinematics_MecanumDrive:
                HAL.report(
                    tResourceType.kResourceType_Kinematics, tInstances.kKinematics_MecanumDrive);
                break;
              case kKinematics_SwerveDrive:
                HAL.report(
                    tResourceType.kResourceType_Kinematics, tInstances.kKinematics_SwerveDrive);
                break;
              case kTrajectory_TrapezoidProfile:
                HAL.report(tResourceType.kResourceType_TrapezoidProfile, count);
                break;
              case kFilter_Linear:
                HAL.report(tResourceType.kResourceType_LinearFilter, count);
                break;
              case kOdometry_DifferentialDrive:
                HAL.report(
                    tResourceType.kResourceType_Odometry, tInstances.kOdometry_DifferentialDrive);
                break;
              case kOdometry_SwerveDrive:
                HAL.report(tResourceType.kResourceType_Odometry, tInstances.kOdometry_SwerveDrive);
                break;
              case kOdometry_MecanumDrive:
                HAL.report(tResourceType.kResourceType_Odometry, tInstances.kOdometry_MecanumDrive);
                break;
              case kController_PIDController2:
                HAL.report(tResourceType.kResourceType_PIDController2, count);
                break;
              case kController_ProfiledPIDController:
                HAL.report(tResourceType.kResourceType_ProfiledPIDController, count);
                break;
              default:
                break;
            }
          }
        });
  }

  /**
   * Constructor for a generic robot program. User code should be placed in the constructor that
   * runs before the Autonomous or Operator Control period starts. The constructor will run to
   * completion before Autonomous is entered.
   *
   * <p>This must be used to ensure that the communications code starts. In the future it would be
   * nice to put this code into it's own task that loads on boot so ensure that it runs.
   */
  protected RobotBase() {
    final NetworkTableInstance inst = NetworkTableInstance.getDefault();
    m_threadId = Thread.currentThread().getId();
    setupCameraServerShared();
    setupMathShared();
    inst.setNetworkIdentity("Robot");
    if (isReal()) {
      inst.startServer("/home/lvuser/networktables.ini");
    } else {
      inst.startServer();
    }
    inst.getTable("LiveWindow").getSubTable(".status").getEntry("LW Enabled").setBoolean(false);

    LiveWindow.setEnabled(false);
    Shuffleboard.disableActuatorWidgets();
  }

  public static long getMainThreadId() {
    return m_threadId;
  }

  @Override
  public void close() {}

  /**
   * Get the current runtime type.
   *
   * @return Current runtime type.
   */
  public static RuntimeType getRuntimeType() {
    return RuntimeType.getValue(HALUtil.getHALRuntimeType());
  }

  /**
   * Get if the robot is a simulation.
   *
   * @return If the robot is running in simulation.
   */
  public static boolean isSimulation() {
    return !isReal();
  }

  /**
   * Get if the robot is real.
   *
   * @return If the robot is running in the real world.
   */
  public static boolean isReal() {
    RuntimeType runtimeType = getRuntimeType();
    return runtimeType == RuntimeType.kRoboRIO || runtimeType == RuntimeType.kRoboRIO2;
  }

  /**
   * Determine if the Robot is currently disabled.
   *
   * @return True if the Robot is currently disabled by the field controls.
   */
  public boolean isDisabled() {
    return DriverStation.isDisabled();
  }

  /**
   * Determine if the Robot is currently enabled.
   *
   * @return True if the Robot is currently enabled by the field controls.
   */
  public boolean isEnabled() {
    return DriverStation.isEnabled();
  }

  /**
   * Determine if the robot is currently in Autonomous mode as determined by the field controls.
   *
   * @return True if the robot is currently operating Autonomously.
   */
  public boolean isAutonomous() {
    return DriverStation.isAutonomous();
  }

  /**
   * Determine if the robot is current in Autonomous mode and enabled as determined by the field
   * controls.
   *
   * @return True if the robot is currently operating autonomously while enabled.
   */
  public boolean isAutonomousEnabled() {
    return DriverStation.isAutonomousEnabled();
  }

  /**
   * Determine if the robot is currently in Test mode as determined by the driver station.
   *
   * @return True if the robot is currently operating in Test mode.
   */
  public boolean isTest() {
    return DriverStation.isTest();
  }

  /**
   * Determine if the robot is currently in Operator Control mode as determined by the field
   * controls.
   *
   * @return True if the robot is currently operating in Tele-Op mode.
   * @deprecated Use isTeleop() instead.
   */
  @Deprecated(since = "2022", forRemoval = true)
  public boolean isOperatorControl() {
    return DriverStation.isTeleop();
  }

  /**
   * Determine if the robot is currently in Operator Control mode as determined by the field
   * controls.
   *
   * @return True if the robot is currently operating in Tele-Op mode.
   */
  public boolean isTeleop() {
    return DriverStation.isTeleop();
  }

  /**
   * Determine if the robot is current in Operator Control mode and enabled as determined by the
   * field controls.
   *
   * @return True if the robot is currently operating in Tele-Op mode while enabled.
   * @deprecated Use isTeleopEnabled() instead.
   */
  @Deprecated(since = "2022", forRemoval = true)
  public boolean isOperatorControlEnabled() {
    return DriverStation.isTeleopEnabled();
  }

  /**
   * Determine if the robot is current in Operator Control mode and enabled as determined by the
   * field controls.
   *
   * @return True if the robot is currently operating in Tele-Op mode while enabled.
   */
  public boolean isTeleopEnabled() {
    return DriverStation.isTeleopEnabled();
  }

  /**
   * Indicates if new data is available from the driver station.
   *
   * @return Has new data arrived over the network since the last time this function was called?
   */
  public boolean isNewDataAvailable() {
    return DriverStation.isNewControlData();
  }

  /** Provide an alternate "main loop" via startCompetition(). */
  public abstract void startCompetition();

  /** Ends the main loop in startCompetition(). */
  public abstract void endCompetition();

  @SuppressWarnings("MissingJavadocMethod")
  public static boolean getBooleanProperty(String name, boolean defaultValue) {
    String propVal = System.getProperty(name);
    if (propVal == null) {
      return defaultValue;
    }
    if ("false".equalsIgnoreCase(propVal)) {
      return false;
    } else if ("true".equalsIgnoreCase(propVal)) {
      return true;
    } else {
      throw new IllegalStateException(propVal);
    }
  }

  private static final ReentrantLock m_runMutex = new ReentrantLock();
  private static RobotBase m_robotCopy;
  private static boolean m_suppressExitWarning;

  /** Run the robot main loop. */
  @SuppressWarnings("PMD.AvoidCatchingThrowable")
  private static <T extends RobotBase> void runRobot(Supplier<T> robotSupplier) {
    System.out.println("********** Robot program starting **********");

    T robot;
    try {
      robot = robotSupplier.get();
    } catch (Throwable throwable) {
      Throwable cause = throwable.getCause();
      if (cause != null) {
        throwable = cause;
      }
      String robotName = "Unknown";
      StackTraceElement[] elements = throwable.getStackTrace();
      if (elements.length > 0) {
        robotName = elements[0].getClassName();
      }
      DriverStation.reportError(
          "Unhandled exception instantiating robot " + robotName + " " + throwable.toString(),
          elements);
      DriverStation.reportError(
          "The robot program quit unexpectedly."
              + " This is usually due to a code error.\n"
              + "  The above stacktrace can help determine where the error occurred.\n"
              + "  See https://wpilib.org/stacktrace for more information.\n",
          false);
      DriverStation.reportError("Could not instantiate robot " + robotName + "!", false);
      return;
    }

    m_runMutex.lock();
    m_robotCopy = robot;
    m_runMutex.unlock();

    if (isReal()) {
      try {
        final File file = new File("/tmp/frc_versions/FRC_Lib_Version.ini");

        if (file.exists()) {
          file.delete();
        }

        file.createNewFile();

        try (OutputStream output = Files.newOutputStream(file.toPath())) {
          output.write("Java ".getBytes(StandardCharsets.UTF_8));
          output.write(WPILibVersion.Version.getBytes(StandardCharsets.UTF_8));
        }

      } catch (IOException ex) {
        DriverStation.reportError(
            "Could not write FRC_Lib_Version.ini: " + ex.toString(), ex.getStackTrace());
      }
    }

    boolean errorOnExit = false;
    try {
      robot.startCompetition();
    } catch (Throwable throwable) {
      Throwable cause = throwable.getCause();
      if (cause != null) {
        throwable = cause;
      }
      DriverStation.reportError(
          "Unhandled exception: " + throwable.toString(), throwable.getStackTrace());
      errorOnExit = true;
    } finally {
      m_runMutex.lock();
      boolean suppressExitWarning = m_suppressExitWarning;
      m_runMutex.unlock();
      if (!suppressExitWarning) {
        // startCompetition never returns unless exception occurs....
        DriverStation.reportWarning(
            "The robot program quit unexpectedly."
                + " This is usually due to a code error.\n"
                + "  The above stacktrace can help determine where the error occurred.\n"
                + "  See https://wpilib.org/stacktrace for more information.",
            false);
        if (errorOnExit) {
          DriverStation.reportError(
              "The startCompetition() method (or methods called by it) should have "
                  + "handled the exception above.",
              false);
        } else {
          DriverStation.reportError("Unexpected return from startCompetition() method.", false);
        }
      }
    }
  }

  /**
   * Suppress the "The robot program quit unexpectedly." message.
   *
   * @param value True if exit warning should be suppressed.
   */
  public static void suppressExitWarning(boolean value) {
    m_runMutex.lock();
    m_suppressExitWarning = value;
    m_runMutex.unlock();
  }

  /**
   * Starting point for the applications.
   *
   * @param <T> Robot subclass.
   * @param robotSupplier Function that returns an instance of the robot subclass.
   */
  public static <T extends RobotBase> void startRobot(Supplier<T> robotSupplier) {
    if (!HAL.initialize(500, 0)) {
      throw new IllegalStateException("Failed to initialize. Terminating");
    }

    // Call a CameraServer JNI function to force OpenCV native library loading
    // Needed because all the OpenCV JNI functions don't have built in loading
    CameraServerJNI.enumerateSinks();

    HAL.report(
        tResourceType.kResourceType_Language, tInstances.kLanguage_Java, 0, WPILibVersion.Version);

    if (!Notifier.setHALThreadPriority(true, 40)) {
      DriverStation.reportWarning("Setting HAL Notifier RT priority to 40 failed", false);
    }

    if (HAL.hasMain()) {
      Thread thread =
          new Thread(
              () -> {
                runRobot(robotSupplier);
                HAL.exitMain();
              },
              "robot main");
      thread.setDaemon(true);
      thread.start();
      HAL.runMain();
      suppressExitWarning(true);
      m_runMutex.lock();
      RobotBase robot = m_robotCopy;
      m_runMutex.unlock();
      if (robot != null) {
        robot.endCompetition();
      }
      try {
        thread.join(1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    } else {
      runRobot(robotSupplier);
    }

    HAL.shutdown();

    System.exit(0);
  }
}
