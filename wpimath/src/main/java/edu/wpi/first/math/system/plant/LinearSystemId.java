// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.system.plant;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Dimensionless;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Mult;
import edu.wpi.first.units.Per;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;

public final class LinearSystemId {
  private LinearSystemId() {
    // Utility class
  }

  /**
   * Create a state-space model of an elevator system. The states of the system are [position,
   * velocity]ᵀ, inputs are [voltage], and outputs are [position].
   *
   * @param motor The motor (or gearbox) attached to the carriage.
   * @param mass The mass of the elevator carriage.
   * @param radius The radius of the elevator's driving drum.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if mass &lt;= 0, radius &lt;= 0, or G &lt;= 0.
   */
  public static LinearSystem<N2, N1, N1> createElevatorSystem(
      DCMotor motor, Measure<Mass> mass, Measure<Distance> radius, Measure<Dimensionless> G) {
    if (mass.in(Units.Kilograms) <= 0.0) {
      throw new IllegalArgumentException("massKg must be greater than zero.");
    }
    if (radius.in(Units.Meters) <= 0.0) {
      throw new IllegalArgumentException("radiusMeters must be greater than zero.");
    }
    if (G.in(Units.Value) <= 0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -Math.pow(G.in(Units.Value), 2)
                    * motor.KtNMPerAmp
                    / (motor.rOhms
                        * radius.in(Units.Meters)
                        * radius.in(Units.Meters)
                        * mass.in(Units.Kilograms)
                        * motor.KvRadPerSecPerVolt)),
        VecBuilder.fill(
            0,
            G.in(Units.Value)
                * motor.KtNMPerAmp
                / (motor.rOhms * radius.in(Units.Meters) * mass.in(Units.Kilograms))),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1, 0),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model of an elevator system. The states of the system are [position,
   * velocity]ᵀ, inputs are [voltage], and outputs are [position].
   *
   * @param motor The motor (or gearbox) attached to the carriage.
   * @param massKg The mass of the elevator carriage, in kilograms.
   * @param radiusMeters The radius of the elevator's driving drum, in meters.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if massKg &lt;= 0, radiusMeters &lt;= 0, or G &lt;= 0.
   */
  public static LinearSystem<N2, N1, N1> createElevatorSystem(
      DCMotor motor, double massKg, double radiusMeters, double G) {
    if (massKg <= 0.0) {
      throw new IllegalArgumentException("massKg must be greater than zero.");
    }
    if (radiusMeters <= 0.0) {
      throw new IllegalArgumentException("radiusMeters must be greater than zero.");
    }
    if (G <= 0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -Math.pow(G, 2)
                    * motor.KtNMPerAmp
                    / (motor.rOhms
                        * radiusMeters
                        * radiusMeters
                        * massKg
                        * motor.KvRadPerSecPerVolt)),
        VecBuilder.fill(0, G * motor.KtNMPerAmp / (motor.rOhms * radiusMeters * massKg)),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1, 0),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model of a flywheel system. The states of the system are [angular
   * velocity], inputs are [voltage], and outputs are [angular velocity].
   *
   * @param motor The motor (or gearbox) attached to the flywheel.
   * @param J The moment of inertia J of the flywheel.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if J &lt;= 0 or G &lt;= 0.
   */
  public static LinearSystem<N1, N1, N1> createFlywheelSystem(
      DCMotor motor, Measure<Mult<Mass, Mult<Distance, Distance>>> J, Measure<Dimensionless> G) {
    if (J.in(Units.KilogramsMetersSquared) <= 0.0) {
      throw new IllegalArgumentException("J must be greater than zero.");
    }
    if (G.in(Units.Value) <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        VecBuilder.fill(
            -Math.pow(G.in(Units.Value), 2)
                * motor.KtNMPerAmp
                / (motor.KvRadPerSecPerVolt * motor.rOhms * J.in(Units.KilogramsMetersSquared))),
        VecBuilder.fill(
            G.in(Units.Value)
                * motor.KtNMPerAmp
                / (motor.rOhms * J.in(Units.KilogramsMetersSquared))),
        Matrix.eye(Nat.N1()),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model of a flywheel system. The states of the system are [angular
   * velocity], inputs are [voltage], and outputs are [angular velocity].
   *
   * @param motor The motor (or gearbox) attached to the flywheel.
   * @param JKgMetersSquared The moment of inertia J of the flywheel.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if JKgMetersSquared &lt;= 0 or G &lt;= 0.
   */
  public static LinearSystem<N1, N1, N1> createFlywheelSystem(
      DCMotor motor, double JKgMetersSquared, double G) {
    if (JKgMetersSquared <= 0.0) {
      throw new IllegalArgumentException("J must be greater than zero.");
    }
    if (G <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        VecBuilder.fill(
            -G
                * G
                * motor.KtNMPerAmp
                / (motor.KvRadPerSecPerVolt * motor.rOhms * JKgMetersSquared)),
        VecBuilder.fill(G * motor.KtNMPerAmp / (motor.rOhms * JKgMetersSquared)),
        Matrix.eye(Nat.N1()),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model of a DC motor system. The states of the system are [angular
   * position, angular velocity], inputs are [voltage], and outputs are [angular position, angular
   * velocity].
   *
   * @param motor The motor (or gearbox) attached to system.
   * @param J The moment of inertia J of the DC motor.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if J &lt;= 0 or G &lt;= 0.
   */
  public static LinearSystem<N2, N1, N2> createDCMotorSystem(
      DCMotor motor, Measure<Mult<Mass, Mult<Distance, Distance>>> J, Measure<Dimensionless> G) {
    if (J.in(Units.KilogramsMetersSquared) <= 0.0) {
      throw new IllegalArgumentException("J must be greater than zero.");
    }
    if (G.in(Units.Value) <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -Math.pow(G.in(Units.Value), 2)
                    * motor.KtNMPerAmp
                    / (motor.KvRadPerSecPerVolt
                        * motor.rOhms
                        * J.in(Units.KilogramsMetersSquared))),
        VecBuilder.fill(
            0,
            G.in(Units.Value)
                * motor.KtNMPerAmp
                / (motor.rOhms * J.in(Units.KilogramsMetersSquared))),
        Matrix.eye(Nat.N2()),
        new Matrix<>(Nat.N2(), Nat.N1()));
  }

  /**
   * Create a state-space model of a DC motor system. The states of the system are [angular
   * position, angular velocity], inputs are [voltage], and outputs are [angular position, angular
   * velocity].
   *
   * @param motor The motor (or gearbox) attached to system.
   * @param JKgMetersSquared The moment of inertia J of the DC motor.
   * @param G The reduction between motor and drum, as a ratio of output to input.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if JKgMetersSquared &lt;= 0 or G &lt;= 0.
   */
  public static LinearSystem<N2, N1, N2> createDCMotorSystem(
      DCMotor motor, double JKgMetersSquared, double G) {
    if (JKgMetersSquared <= 0.0) {
      throw new IllegalArgumentException("J must be greater than zero.");
    }
    if (G <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -G
                    * G
                    * motor.KtNMPerAmp
                    / (motor.KvRadPerSecPerVolt * motor.rOhms * JKgMetersSquared)),
        VecBuilder.fill(0, G * motor.KtNMPerAmp / (motor.rOhms * JKgMetersSquared)),
        Matrix.eye(Nat.N2()),
        new Matrix<>(Nat.N2(), Nat.N1()));
  }

  /**
   * Create a state-space model of a DC motor system. The states of the system are [angular
   * position, angular velocity], inputs are [voltage], and outputs are [angular position, angular
   * velocity].
   *
   * <p>The distance unit you choose MUST be an SI unit (i.e. meters or radians). You can use the
   * {@link edu.wpi.first.math.util.Units} class for converting between unit types.
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain, in volts/angular velocity
   * @param kA The acceleration gain, in volts/angular acceleration
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N1, N2> createDCMotorSystem(
      Measure<Per<Voltage, Velocity<Angle>>> kV,
      Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kA) {
    if (kV.in(Units.VoltsPerRadianPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA.in(Units.VoltsPerRadianPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -kV.in(Units.VoltsPerRadianPerSecond)
                    / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        VecBuilder.fill(0, 1 / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        Matrix.eye(Nat.N2()),
        new Matrix<>(Nat.N2(), Nat.N1()));
  }

  /**
   * Create a state-space model of a DC motor system. The states of the system are [angular
   * position, angular velocity], inputs are [voltage], and outputs are [angular position, angular
   * velocity].
   *
   * <p>The distance unit you choose MUST be radians. You can use the {@link
   * edu.wpi.first.math.util.Units} class for converting between unit types.
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain, in volts/(rad/sec)
   * @param kA The acceleration gain, in volts/(rad/sec^2)
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N1, N2> createDCMotorSystem(double kV, double kA) {
    if (kV <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2()).fill(0, 1, 0, -kV / kA),
        VecBuilder.fill(0, 1 / kA),
        Matrix.eye(Nat.N2()),
        new Matrix<>(Nat.N2(), Nat.N1()));
  }

  /**
   * Create a state-space model of a differential drive drivetrain. In this model, the states are
   * [left velocity, right velocity]ᵀ, inputs are [left voltage, right voltage]ᵀ, and outputs are
   * [left velocity, right velocity]ᵀ.
   *
   * @param motor The motor (or gearbox) driving the drivetrain.
   * @param mass The mass of the robot.
   * @param r The radius of the wheels.
   * @param rb The radius of the base (half the track width).
   * @param J The moment of inertia of the robot.
   * @param G The gearing reduction as output over input.
   * @return A LinearSystem representing a differential drivetrain.
   * @throws IllegalArgumentException if m &lt;= 0, r &lt;= 0, rb &lt;= 0, J &lt;= 0, or G &lt;= 0.
   */
  public static LinearSystem<N2, N2, N2> createDrivetrainVelocitySystem(
      DCMotor motor,
      Measure<Mass> mass,
      Measure<Distance> r,
      Measure<Distance> rb,
      Measure<Mult<Mass, Mult<Distance, Distance>>> J,
      Measure<Dimensionless> G) {
    if (mass.in(Units.Kilograms) <= 0.0) {
      throw new IllegalArgumentException("massKg must be greater than zero.");
    }
    if (r.in(Units.Meters) <= 0.0) {
      throw new IllegalArgumentException("rMeters must be greater than zero.");
    }
    if (rb.in(Units.Meters) <= 0.0) {
      throw new IllegalArgumentException("rbMeters must be greater than zero.");
    }
    if (J.in(Units.KilogramsMetersSquared) <= 0.0) {
      throw new IllegalArgumentException("JKgMetersSquared must be greater than zero.");
    }
    if (G.in(Units.Value) <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    var C1 =
        -Math.pow(G.in(Units.Value), 2)
            * motor.KtNMPerAmp
            / (motor.KvRadPerSecPerVolt * motor.rOhms * r.in(Units.Meters) * r.in(Units.Meters));
    var C2 = G.in(Units.Value) * motor.KtNMPerAmp / (motor.rOhms * r.in(Units.Meters));

    final double C3 =
        1 / mass.in(Units.Kilograms)
            + rb.in(Units.Meters) * rb.in(Units.Meters) / J.in(Units.KilogramsMetersSquared);
    final double C4 =
        1 / mass.in(Units.Kilograms)
            - rb.in(Units.Meters) * rb.in(Units.Meters) / J.in(Units.KilogramsMetersSquared);
    var A = Matrix.mat(Nat.N2(), Nat.N2()).fill(C3 * C1, C4 * C1, C4 * C1, C3 * C1);
    var B = Matrix.mat(Nat.N2(), Nat.N2()).fill(C3 * C2, C4 * C2, C4 * C2, C3 * C2);
    var C = Matrix.mat(Nat.N2(), Nat.N2()).fill(1.0, 0.0, 0.0, 1.0);
    var D = Matrix.mat(Nat.N2(), Nat.N2()).fill(0.0, 0.0, 0.0, 0.0);

    return new LinearSystem<>(A, B, C, D);
  }

  /**
   * Create a state-space model of a differential drive drivetrain. In this model, the states are
   * [left velocity, right velocity]ᵀ, inputs are [left voltage, right voltage]ᵀ, and outputs are
   * [left velocity, right velocity]ᵀ.
   *
   * @param motor The motor (or gearbox) driving the drivetrain.
   * @param massKg The mass of the robot in kilograms.
   * @param rMeters The radius of the wheels in meters.
   * @param rbMeters The radius of the base (half the track width) in meters.
   * @param JKgMetersSquared The moment of inertia of the robot.
   * @param G The gearing reduction as output over input.
   * @return A LinearSystem representing a differential drivetrain.
   * @throws IllegalArgumentException if m &lt;= 0, r &lt;= 0, rb &lt;= 0, J &lt;= 0, or G &lt;= 0.
   */
  public static LinearSystem<N2, N2, N2> createDrivetrainVelocitySystem(
      DCMotor motor,
      double massKg,
      double rMeters,
      double rbMeters,
      double JKgMetersSquared,
      double G) {
    if (massKg <= 0.0) {
      throw new IllegalArgumentException("massKg must be greater than zero.");
    }
    if (rMeters <= 0.0) {
      throw new IllegalArgumentException("rMeters must be greater than zero.");
    }
    if (rbMeters <= 0.0) {
      throw new IllegalArgumentException("rbMeters must be greater than zero.");
    }
    if (JKgMetersSquared <= 0.0) {
      throw new IllegalArgumentException("JKgMetersSquared must be greater than zero.");
    }
    if (G <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    var C1 =
        -(G * G) * motor.KtNMPerAmp / (motor.KvRadPerSecPerVolt * motor.rOhms * rMeters * rMeters);
    var C2 = G * motor.KtNMPerAmp / (motor.rOhms * rMeters);

    final double C3 = 1 / massKg + rbMeters * rbMeters / JKgMetersSquared;
    final double C4 = 1 / massKg - rbMeters * rbMeters / JKgMetersSquared;
    var A = Matrix.mat(Nat.N2(), Nat.N2()).fill(C3 * C1, C4 * C1, C4 * C1, C3 * C1);
    var B = Matrix.mat(Nat.N2(), Nat.N2()).fill(C3 * C2, C4 * C2, C4 * C2, C3 * C2);
    var C = Matrix.mat(Nat.N2(), Nat.N2()).fill(1.0, 0.0, 0.0, 1.0);
    var D = Matrix.mat(Nat.N2(), Nat.N2()).fill(0.0, 0.0, 0.0, 0.0);

    return new LinearSystem<>(A, B, C, D);
  }

  /**
   * Create a state-space model of a single jointed arm system. The states of the system are [angle,
   * angular velocity], inputs are [voltage], and outputs are [angle].
   *
   * @param motor The motor (or gearbox) attached to the arm.
   * @param J The moment of inertia J of the arm.
   * @param G The gearing between the motor and arm, in output over input. Most of the time this
   *     will be greater than 1.
   * @return A LinearSystem representing the given characterized constants.
   */
  public static LinearSystem<N2, N1, N1> createSingleJointedArmSystem(
      DCMotor motor, Measure<Mult<Mass, Mult<Distance, Distance>>> J, Measure<Dimensionless> G) {
    if (J.in(Units.KilogramsMetersSquared) <= 0.0) {
      throw new IllegalArgumentException("JKgSquaredMeters must be greater than zero.");
    }
    if (G.in(Units.Value) <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -Math.pow(G.in(Units.Value), 2)
                    * motor.KtNMPerAmp
                    / (motor.KvRadPerSecPerVolt
                        * motor.rOhms
                        * J.in(Units.KilogramsMetersSquared))),
        VecBuilder.fill(
            0,
            G.in(Units.Value)
                * motor.KtNMPerAmp
                / (motor.rOhms * J.in(Units.KilogramsMetersSquared))),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1, 0),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model of a single jointed arm system. The states of the system are [angle,
   * angular velocity], inputs are [voltage], and outputs are [angle].
   *
   * @param motor The motor (or gearbox) attached to the arm.
   * @param JKgSquaredMeters The moment of inertia J of the arm.
   * @param G The gearing between the motor and arm, in output over input. Most of the time this
   *     will be greater than 1.
   * @return A LinearSystem representing the given characterized constants.
   */
  public static LinearSystem<N2, N1, N1> createSingleJointedArmSystem(
      DCMotor motor, double JKgSquaredMeters, double G) {
    if (JKgSquaredMeters <= 0.0) {
      throw new IllegalArgumentException("JKgSquaredMeters must be greater than zero.");
    }
    if (G <= 0.0) {
      throw new IllegalArgumentException("G must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0,
                1,
                0,
                -Math.pow(G, 2)
                    * motor.KtNMPerAmp
                    / (motor.KvRadPerSecPerVolt * motor.rOhms * JKgSquaredMeters)),
        VecBuilder.fill(0, G * motor.KtNMPerAmp / (motor.rOhms * JKgSquaredMeters)),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1, 0),
        new Matrix<>(Nat.N1(), Nat.N1()));
  }

  /**
   * Create a state-space model for a 1 DOF velocity system from its kV (volts/angular velocity) and
   * kA (volts/(angular acceleration). These constants cam be found using SysId. The states of the
   * system are [velocity], inputs are [voltage], and outputs are [velocity].
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain.
   * @param kA The acceleration gain.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N1, N1, N1> identifyAngularVelocitySystem(
      Measure<Per<Voltage, Velocity<Angle>>> kV,
      Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kA) {
    if (kV.in(Units.VoltsPerRadianPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA.in(Units.VoltsPerRadianPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        VecBuilder.fill(
            -kV.in(Units.VoltsPerRadianPerSecond) / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        VecBuilder.fill(1.0 / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        VecBuilder.fill(1.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Create a state-space model for a 1 DOF velocity system from its kV (volts/linear velocity) and
   * kA (volts/(linear acceleration). These constants cam be found using SysId. The states of the
   * system are [velocity], inputs are [voltage], and outputs are [velocity].
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain.
   * @param kA The acceleration gain.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N1, N1, N1> identifyLinearVelocitySystem(
      Measure<Per<Voltage, Velocity<Distance>>> kV,
      Measure<Per<Voltage, Velocity<Velocity<Distance>>>> kA) {
    if (kV.in(Units.VoltsPerMeterPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA.in(Units.VoltsPerMeterPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        VecBuilder.fill(
            -kV.in(Units.VoltsPerMeterPerSecond) / kA.in(Units.VoltsPerMeterPerSecondSquared)),
        VecBuilder.fill(1.0 / kA.in(Units.VoltsPerMeterPerSecondSquared)),
        VecBuilder.fill(1.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Create a state-space model for a 1 DOF velocity system from its kV (volts/(unit/sec)) and kA
   * (volts/(unit/sec²). These constants cam be found using SysId. The states of the system are
   * [velocity], inputs are [voltage], and outputs are [velocity].
   *
   * <p>The distance unit you choose MUST be an SI unit (i.e. meters or radians). You can use the
   * {@link edu.wpi.first.math.util.Units} class for converting between unit types.
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain, in volts/(unit/sec)
   * @param kA The acceleration gain, in volts/(unit/sec^2)
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N1, N1, N1> identifyVelocitySystem(double kV, double kA) {
    if (kV <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        VecBuilder.fill(-kV / kA),
        VecBuilder.fill(1.0 / kA),
        VecBuilder.fill(1.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Create a state-space model for a 1 DOF position system from its kV (volts/angular velocity) and
   * kA (volts/angular acceleration). These constants cam be found using SysId. The states of the
   * system are [position, velocity]ᵀ, inputs are [voltage], and outputs are [position].
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain.
   * @param kA The acceleration gain.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N1, N1> identifyAngularPositionSystem(
      Measure<Per<Voltage, Velocity<Angle>>> kV,
      Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kA) {
    if (kV.in(Units.VoltsPerRadianPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA.in(Units.VoltsPerRadianPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0.0,
                1.0,
                0.0,
                -kV.in(Units.VoltsPerRadianPerSecond)
                    / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        VecBuilder.fill(0.0, 1.0 / kA.in(Units.VoltsPerRadianPerSecondSquared)),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1.0, 0.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Create a state-space model for a 1 DOF position system from its kV (volts/linear velocity) and
   * kA (volts/linear acceleration). These constants cam be found using SysId. The states of the
   * system are [position, velocity]ᵀ, inputs are [voltage], and outputs are [position].
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain.
   * @param kA The acceleration gain.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N1, N1> identifyLinearPositionSystem(
      Measure<Per<Voltage, Velocity<Distance>>> kV,
      Measure<Per<Voltage, Velocity<Velocity<Distance>>>> kA) {
    if (kV.in(Units.VoltsPerMeterPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA.in(Units.VoltsPerMeterPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2())
            .fill(
                0.0,
                1.0,
                0.0,
                -kV.in(Units.VoltsPerMeterPerSecond) / kA.in(Units.VoltsPerMeterPerSecondSquared)),
        VecBuilder.fill(0.0, 1.0 / kA.in(Units.VoltsPerMeterPerSecondSquared)),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1.0, 0.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Create a state-space model for a 1 DOF position system from its kV (volts/(unit/sec)) and kA
   * (volts/(unit/sec²). These constants cam be found using SysId. The states of the system are
   * [position, velocity]ᵀ, inputs are [voltage], and outputs are [position].
   *
   * <p>The distance unit you choose MUST be an SI unit (i.e. meters or radians). You can use the
   * {@link edu.wpi.first.math.util.Units} class for converting between unit types.
   *
   * <p>The parameters provided by the user are from this feedforward model:
   *
   * <p>u = K_v v + K_a a
   *
   * @param kV The velocity gain, in volts/(unit/sec)
   * @param kA The acceleration gain, in volts/(unit/sec²)
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kV &lt;= 0 or kA &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N1, N1> identifyPositionSystem(double kV, double kA) {
    if (kV <= 0.0) {
      throw new IllegalArgumentException("Kv must be greater than zero.");
    }
    if (kA <= 0.0) {
      throw new IllegalArgumentException("Ka must be greater than zero.");
    }

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2()).fill(0.0, 1.0, 0.0, -kV / kA),
        VecBuilder.fill(0.0, 1.0 / kA),
        Matrix.mat(Nat.N1(), Nat.N2()).fill(1.0, 0.0),
        VecBuilder.fill(0.0));
  }

  /**
   * Identify a differential drive drivetrain given the drivetrain's kV and kA in both linear
   * {(volts/linear velocity), (volts/linear acceleration)} and angular {(volts/angular velocity),
   * (volts/angular acceleration)} cases. These constants can be found using SysId.
   *
   * <p>States: [[left velocity], [right velocity]]<br>
   * Inputs: [[left voltage], [right voltage]]<br>
   * Outputs: [[left velocity], [right velocity]]
   *
   * @param kVLinear The linear velocity gain.
   * @param kALinear The linear acceleration gain.
   * @param kVAngular The angular velocity gain.
   * @param kAAngular The angular acceleration gain.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kVLinear &lt;= 0, kALinear &lt;= 0, kVAngular &lt;= 0, or
   *     kAAngular &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N2, N2> identifyDrivetrainSystem(
      Measure<Per<Voltage, Velocity<Distance>>> kVLinear,
      Measure<Per<Voltage, Velocity<Velocity<Distance>>>> kALinear,
      Measure<Per<Voltage, Velocity<Angle>>> kVAngular,
      Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kAAngular) {
    if (kVLinear.in(Units.VoltsPerMeterPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv,linear must be greater than zero.");
    }
    if (kALinear.in(Units.VoltsPerMeterPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka,linear must be greater than zero.");
    }
    if (kVAngular.in(Units.VoltsPerRadianPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv,angular must be greater than zero.");
    }
    if (kAAngular.in(Units.VoltsPerRadianPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka,angular must be greater than zero.");
    }

    final double A1 =
        0.5
            * -(kVLinear.in(Units.VoltsPerMeterPerSecond)
                    / kALinear.in(Units.VoltsPerMeterPerSecondSquared)
                + kVAngular.in(Units.VoltsPerRadianPerSecond)
                    / kAAngular.in(Units.VoltsPerRadianPerSecondSquared));
    final double A2 =
        0.5
            * -(kVLinear.in(Units.VoltsPerMeterPerSecond)
                    / kALinear.in(Units.VoltsPerMeterPerSecondSquared)
                - kVAngular.in(Units.VoltsPerRadianPerSecond)
                    / kAAngular.in(Units.VoltsPerRadianPerSecondSquared));
    final double B1 =
        0.5
            * (1.0 / kALinear.in(Units.VoltsPerMeterPerSecondSquared)
                + 1.0 / kAAngular.in(Units.VoltsPerRadianPerSecondSquared));
    final double B2 =
        0.5
            * (1.0 / kALinear.in(Units.VoltsPerMeterPerSecondSquared)
                + 1.0 / kAAngular.in(Units.VoltsPerRadianPerSecondSquared));

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2()).fill(A1, A2, A2, A1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(B1, B2, B2, B1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(1, 0, 0, 1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(0, 0, 0, 0));
  }

  /**
   * Identify a differential drive drivetrain given the drivetrain's kV and kA in both linear
   * {(volts/linear velocity), (volts/linear acceleration)} and angular {(volts/angular velocity),
   * (volts/angular acceleration)} cases. This can be found using SysId.
   *
   * <p>States: [[left velocity], [right velocity]]<br>
   * Inputs: [[left voltage], [right voltage]]<br>
   * Outputs: [[left velocity], [right velocity]]
   *
   * @param kVLinear The linear velocity gain.
   * @param kALinear The linear acceleration gain.
   * @param kVAngular The angular velocity gain.
   * @param kAAngular The angular acceleration gain.
   * @param trackwidth The distance between the differential drive's left and right wheels.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kVLinear &lt;= 0, kALinear &lt;= 0, kVAngular &lt;= 0,
   *     kAAngular &lt;= 0, or trackwidth &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N2, N2> identifyDrivetrainSystem(
      Measure<Per<Voltage, Velocity<Distance>>> kVLinear,
      Measure<Per<Voltage, Velocity<Velocity<Distance>>>> kALinear,
      Measure<Per<Voltage, Velocity<Angle>>> kVAngular,
      Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kAAngular,
      Measure<Distance> trackwidth) {
    if (kVLinear.in(Units.VoltsPerMeterPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv,linear must be greater than zero.");
    }
    if (kALinear.in(Units.VoltsPerMeterPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka,linear must be greater than zero.");
    }
    if (kVAngular.in(Units.VoltsPerRadianPerSecond) <= 0.0) {
      throw new IllegalArgumentException("Kv,angular must be greater than zero.");
    }
    if (kAAngular.in(Units.VoltsPerRadianPerSecondSquared) <= 0.0) {
      throw new IllegalArgumentException("Ka,angular must be greater than zero.");
    }
    if (trackwidth.in(Units.Meters) <= 0.0) {
      throw new IllegalArgumentException("trackwidth must be greater than zero.");
    }

    // We want to find a factor to include in Kv,angular that will convert
    // `u = Kv,angular omega` to `u = Kv,angular v`.
    //
    // v = omega r
    // omega = v/r
    // omega = 1/r v
    // omega = 1/(trackwidth/2) v
    // omega = 2/trackwidth v
    //
    // So multiplying by 2/trackwidth converts the angular gains from V/(rad/s)
    // to V/(m/s).
    return identifyDrivetrainSystem(
        kVLinear,
        kALinear,
        kVAngular.times(2.0).divide(trackwidth.in(Units.Meters)),
        kAAngular.times(2.0).divide(trackwidth.in(Units.Meters)));
  }

  /**
   * Identify a differential drive drivetrain given the drivetrain's kV and kA in both linear
   * {(volts/(meter/sec), (volts/(meter/sec²))} and angular {(volts/(radian/sec)),
   * (volts/(radian/sec²))} cases. These constants can be found using SysId.
   *
   * <p>States: [[left velocity], [right velocity]]<br>
   * Inputs: [[left voltage], [right voltage]]<br>
   * Outputs: [[left velocity], [right velocity]]
   *
   * @param kVLinear The linear velocity gain in volts per (meters per second).
   * @param kALinear The linear acceleration gain in volts per (meters per second squared).
   * @param kVAngular The angular velocity gain in volts per (meters per second).
   * @param kAAngular The angular acceleration gain in volts per (meters per second squared).
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kVLinear &lt;= 0, kALinear &lt;= 0, kVAngular &lt;= 0, or
   *     kAAngular &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N2, N2> identifyDrivetrainSystem(
      double kVLinear, double kALinear, double kVAngular, double kAAngular) {
    if (kVLinear <= 0.0) {
      throw new IllegalArgumentException("Kv,linear must be greater than zero.");
    }
    if (kALinear <= 0.0) {
      throw new IllegalArgumentException("Ka,linear must be greater than zero.");
    }
    if (kVAngular <= 0.0) {
      throw new IllegalArgumentException("Kv,angular must be greater than zero.");
    }
    if (kAAngular <= 0.0) {
      throw new IllegalArgumentException("Ka,angular must be greater than zero.");
    }

    final double A1 = 0.5 * -(kVLinear / kALinear + kVAngular / kAAngular);
    final double A2 = 0.5 * -(kVLinear / kALinear - kVAngular / kAAngular);
    final double B1 = 0.5 * (1.0 / kALinear + 1.0 / kAAngular);
    final double B2 = 0.5 * (1.0 / kALinear - 1.0 / kAAngular);

    return new LinearSystem<>(
        Matrix.mat(Nat.N2(), Nat.N2()).fill(A1, A2, A2, A1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(B1, B2, B2, B1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(1, 0, 0, 1),
        Matrix.mat(Nat.N2(), Nat.N2()).fill(0, 0, 0, 0));
  }

  /**
   * Identify a differential drive drivetrain given the drivetrain's kV and kA in both linear
   * {(volts/(meter/sec)), (volts/(meter/sec²))} and angular {(volts/(radian/sec)),
   * (volts/(radian/sec²))} cases. This can be found using SysId.
   *
   * <p>States: [[left velocity], [right velocity]]<br>
   * Inputs: [[left voltage], [right voltage]]<br>
   * Outputs: [[left velocity], [right velocity]]
   *
   * @param kVLinear The linear velocity gain in volts per (meters per second).
   * @param kALinear The linear acceleration gain in volts per (meters per second squared).
   * @param kVAngular The angular velocity gain in volts per (radians per second).
   * @param kAAngular The angular acceleration gain in volts per (radians per second squared).
   * @param trackwidth The distance between the differential drive's left and right wheels, in
   *     meters.
   * @return A LinearSystem representing the given characterized constants.
   * @throws IllegalArgumentException if kVLinear &lt;= 0, kALinear &lt;= 0, kVAngular &lt;= 0,
   *     kAAngular &lt;= 0, or trackwidth &lt;= 0.
   * @see <a href= "https://github.com/wpilibsuite/sysid">https://github.com/wpilibsuite/sysid</a>
   */
  public static LinearSystem<N2, N2, N2> identifyDrivetrainSystem(
      double kVLinear, double kALinear, double kVAngular, double kAAngular, double trackwidth) {
    if (kVLinear <= 0.0) {
      throw new IllegalArgumentException("Kv,linear must be greater than zero.");
    }
    if (kALinear <= 0.0) {
      throw new IllegalArgumentException("Ka,linear must be greater than zero.");
    }
    if (kVAngular <= 0.0) {
      throw new IllegalArgumentException("Kv,angular must be greater than zero.");
    }
    if (kAAngular <= 0.0) {
      throw new IllegalArgumentException("Ka,angular must be greater than zero.");
    }
    if (trackwidth <= 0.0) {
      throw new IllegalArgumentException("trackwidth must be greater than zero.");
    }

    // We want to find a factor to include in Kv,angular that will convert
    // `u = Kv,angular omega` to `u = Kv,angular v`.
    //
    // v = omega r
    // omega = v/r
    // omega = 1/r v
    // omega = 1/(trackwidth/2) v
    // omega = 2/trackwidth v
    //
    // So multiplying by 2/trackwidth converts the angular gains from V/(rad/s)
    // to V/(m/s).
    return identifyDrivetrainSystem(
        kVLinear, kALinear, kVAngular * 2.0 / trackwidth, kAAngular * 2.0 / trackwidth);
  }
}
