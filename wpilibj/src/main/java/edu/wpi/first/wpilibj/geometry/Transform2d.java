/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.geometry;

/**
 * Represents a transformation for a Pose2d.
 */
public class Transform2d {
  private Translation2d m_translation;
  private Rotation2d m_rotation;

  /**
   * Constructs the transform that maps the initial pose to the final pose.
   */
  public Transform2d(Pose2d initial, Pose2d last) {
    m_translation = (last.getTranslation().minus(initial.getTranslation())
            .RotateBy(initial.getRotation().unaryMinus()));

    m_rotation = last.getRotation().minus(initial.getRotation());
  }

  /**
   * Constructs a transform with the given translation and rotation components.
   */
  public Transform2d(Translation2d translation, Rotation2d rotation) {
    m_translation = translation;
    m_rotation = rotation;
  }

  /**
   * Constructs the identity transform -- maps an initial pose to itself.
   */
  public Transform2d() {
    m_translation = new Translation2d();
    m_rotation = new Rotation2d();
  }

  /**
   * Returns the translation component of the transformation.
   */
  public Translation2d getTranslation() {
    return m_translation;
  }

  /**
   * Returns the rotational component of the transformation.
   */
  public Rotation2d getRotation() {
    return m_rotation;
  }
}
