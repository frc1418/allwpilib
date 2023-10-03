// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.trajectory;

import java.util.Objects;

/**
 * A trapezoid-shaped velocity profile.
 *
 * <p>While this class can be used for a profiled movement from start to finish, the intended usage
 * is to filter a reference's dynamics based on trapezoidal velocity constraints. To compute the
 * reference obeying this constraint, do the following.
 *
 * <p>Initialization:
 *
 * <pre><code>
 * TrapezoidProfile.Constraints constraints =
 *   new TrapezoidProfile.Constraints(kMaxV, kMaxA);
 * TrapezoidProfile.State previousProfiledReference =
 *   new TrapezoidProfile.State(initialReference, 0.0);
 * TrapezoidProfile profile = new TrapezoidProfile(constraints);
 * </code></pre>
 *
 * <p>Run on update:
 *
 * <pre><code>
 * previousProfiledReference =
 * profile.calculate(timeSincePreviousUpdate, unprofiledReference, previousProfiledReference);
 * </code></pre>
 *
 * <p>where `unprofiledReference` is free to change between calls. Note that when the unprofiled
 * reference is within the constraints, `calculate()` returns the unprofiled reference unchanged.
 *
 * <p>Otherwise, a timer can be started to provide monotonic values for `calculate()` and to
 * determine when the profile has completed via `isFinished()`.
 */
public class ExponentialProfile {
  // The direction of the profile, either 1 for forwards or -1 for inverted
  private int m_direction;

  private final Constraints m_constraints;
  private State m_current;
  private State m_goal;

  private State m_inflectionPoint;

  public static class ProfileTiming {
    public final double inflectionTime;
    public final double totalTime;

    protected ProfileTiming(double inflectionTime, double totalTime) {
      this.inflectionTime = inflectionTime;
      this.totalTime = totalTime;
    }

    public boolean isFinished(double t) {
      return t > inflectionTime;
    }
  }

  public static class Constraints {
    public final double maxInput;

    public final double A;
    public final double B;

    /**
     * Construct constraints for a TrapezoidProfile.
     *
     * @param maxVelocity maximum velocity
     * @param maxAcceleration maximum acceleration
     */
    private Constraints(double maxInput, double A, double B) {
      this.maxInput = maxInput;
      this.A = A;
      this.B = B;
    }

    public double maxVelocity() {
      return -maxInput * B / A;
    }

    public static Constraints fromCharacteristics(double maxInput, double kV, double kA) {
      return new Constraints(maxInput, -kV / kA, 1.0 / kA);
    }

    public static Constraints fromStateSpace(double maxInput, double A, double B) {
      return new Constraints(maxInput, A, B);
    }
  }

  public static class State {
    public double position;

    public double velocity;

    public State() {}

    public State(double position, double velocity) {
      this.position = position;
      this.velocity = velocity;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof State) {
        State rhs = (State) other;
        return this.position == rhs.position && this.velocity == rhs.velocity;
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(position, velocity);
    }
  }

  /**
   * Construct a TrapezoidProfile.
   *
   * @param constraints The constraints on the profile, like maximum velocity.
   */
  public ExponentialProfile(Constraints constraints) {
    m_constraints = constraints;
  }

  /**
   * Calculate the correct position and velocity for the profile at a time t where the beginning of
   * the profile was at time t = 0.
   *
   * @param t The time since the beginning of the profile.
   * @param goal The desired state when the profile is complete.
   * @param current The current state.
   * @return The position and velocity of the profile at time t.
   */
  public State calculate(double t, State goal, State current) {
    var direction = shouldFlipAcceleration(current, goal) ? -1 : 1;
    var U = direction * m_constraints.maxInput;

    var inflectionPoint = calculateInflectionPoint(current, goal, U);
    var timing = calculateProfileTiming(current, inflectionPoint, goal, U);

    if (t < 0) {
      return current;
    } else if (t < timing.inflectionTime) {
      return new State(DistanceAtTime(t, U, current), VelocityAtTime(t, U, current));
    } else if (t < timing.totalTime) {
      return new State(DistanceAtTime(t, -U, goal), VelocityAtTime(t, -U, goal));
    } else {
      return goal;
    }
  }

  private State calculateInflectionPoint(State current, State goal, double input) {
    var U = m_direction * m_constraints.maxInput;

    if (current.equals(goal)) {
      m_inflectionPoint = current;

      return m_inflectionPoint;
    }

    var inflectionV = SolveForInflectionVelocity(U, current, goal);
    var inflectionP = computeDistanceInPhaseSpace(inflectionV, -U, m_goal);

    m_inflectionPoint = new State(inflectionP, inflectionV);

    return m_inflectionPoint;
  }

  private ProfileTiming calculateProfileTiming(State current, State inflectionPoint, State goal, double input) {
    var U = input;

    double inflectionT_forward;

    if (m_constraints.maxVelocity() - inflectionPoint.velocity < 1e-9) {
      var solvableV = m_constraints.maxVelocity() - 1e-9 * m_direction;
      var t_to_solvable_v = computeTimeFromVelocity(solvableV, U, current.velocity);
      var x_at_solvable_v = computeDistanceInPhaseSpace(solvableV, U, current);

      inflectionT_forward = t_to_solvable_v + (inflectionPoint.position - x_at_solvable_v) / m_constraints.maxVelocity();
    } else {
      inflectionT_forward = computeTimeFromVelocity(inflectionPoint.velocity, U, current.velocity);
    }

    var inflectionT_backward = computeTimeFromVelocity(inflectionPoint.velocity, -U, goal.velocity);

    return new ProfileTiming(inflectionT_forward, inflectionT_forward - inflectionT_backward);
  }

  private void calculateProfile(State current, State goal) {
    m_direction = shouldFlipAcceleration(current, goal) ? -1 : 1;
    m_current = current;
    m_goal = goal;

    var U = m_direction * m_constraints.maxInput;

    var inflectionPoint = calculateInflectionPoint(current, goal, U);

    calculateProfileTiming(current, inflectionPoint, goal, U);
  }

  public double timeLeftUntil(State current, State goal) {
    calculateProfile(current, goal);

    return totalTime();
  }

  private double DistanceAtTime(double t, double input, State initial) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = input;

  
    return initial.position + (-B * U * t + (initial.velocity + B * U / A) * (Math.exp(A * t) - 1)) / A;
  }

  private double VelocityAtTime(double t, double input, State initial) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = input;
  
    return (initial.velocity + B * U / A) * Math.exp(A * t) - B * U / A;
  }

  private double computeTimeFromVelocity(double velocity, double input, double initial) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = input;
  
    return Math.log((A * velocity + B * U)/(A * initial + B * U)) / A;
  }

  private double SolveForInflectionVelocity(double input, State current, State goal) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = input;

    var U_dir = Math.signum(U);

    var position_delta = goal.position - current.position;
    var velocity_delta = goal.velocity - current.velocity;

    var scalar = (A * current.velocity + B * U) * (A * goal.velocity - B * U);
    var power = -A / B / U * (A * position_delta - velocity_delta);

    var a = -A * A;
    var c = B*B*U*U + scalar * Math.exp(power);

    return U_dir * Math.sqrt(-c/a);
  }

  /**
   * Returns true if the profile inverted.
   *
   * <p>The profile is inverted if goal position is less than the initial position.
   *
   * @param Constraints 
   * @param initial The initial state (usually the current state).
   * @param goal The desired state when the profile is complete.
   */
  private boolean shouldFlipAcceleration(State initial, State goal) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = m_constraints.maxInput;

    var x0 = initial.position;
    var xf = goal.position;
    var v0 = initial.velocity;
    var vf = goal.velocity;

    var x_forward = computeDistanceInPhaseSpace(vf, U, initial);
    var x_reverse = computeDistanceInPhaseSpace(vf, -U, initial);

    if (v0 >= -B / A * U) {
      return xf < x_reverse;
    } 
    
    if (v0 <= B / A * U) {
      return xf < x_forward;
    }
  
    var a = v0 >= 0;
    var b = vf >= 0;
    var c = xf >= x_forward;
    var d = xf >= x_reverse;
  
    return (a && !d) || (b && !c) || (!c && !d);
  }

  private double computeDistanceInPhaseSpace(double velocity, double input, State initial) {
    var A = m_constraints.A;
    var B = m_constraints.B;
    var U = input;
  
    return initial.position + (velocity - initial.velocity) / A - B * U / (A * A) * Math.log((A * velocity + B * U) / (A * initial.velocity + B * U));
  }
}
