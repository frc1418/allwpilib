/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2017 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * Classes in the {@code edu.wpi.first.wpilibj.vision} package are designed to
 * simplify using OpenCV vision processing code from a robot program.
 *
 * An example usecase for grabbing a yellow tote from 2015 in autonomous:
 * <br>
 * <pre><code>
 * public class Robot extends IterativeRobot
 *     implements VisionRunner.Listener&lt;MyFindTotePipeline&gt; {
 *
 *      // A USB camera connected to the roboRIO.
 *      private {@link edu.wpi.cscore.VideoSource VideoSource} usbCamera;
 *
 *      // A vision pipeline. This could be handwritten or generated by GRIP.
 *      // This has to implement {@link edu.wpi.first.wpilibj.vision.VisionPipeline}.
 *      // For this example, assume that it's perfect and will always see the tote.
 *      private MyFindTotePipeline findTotePipeline;
 *      private {@link edu.wpi.first.wpilibj.vision.VisionThread} findToteThread;
 *
 *      // The object to synchronize on to make sure the vision thread doesn't
 *      // write to variables the main thread is using.
 *      private final Object visionLock = new Object();
 *
 *      // The pipeline outputs we want
 *      private boolean pipelineRan = false; // lets us know when the pipeline has actually run
 *      private double angleToTote = 0;
 *      private double distanceToTote = 0;
 *
 *     {@literal @}Override
 *      public void {@link edu.wpi.first.wpilibj.vision.VisionRunner.Listener#copyPipelineOutputs
 *          copyPipelineOutputs(MyFindTotePipeline pipeline)} {
 *          synchronized (visionLock) {
 *              // Take a snapshot of the pipeline's output because
 *              // it may have changed the next time this method is called!
 *              this.pipelineRan = true;
 *              this.angleToTote = pipeline.getAngleToTote();
 *              this.distanceToTote = pipeline.getDistanceToTote();
 *          }
 *      }
 *
 *     {@literal @}Override
 *      public void robotInit() {
 *          usbCamera = CameraServer.getInstance().startAutomaticCapture(0);
 *          findTotePipeline = new MyFindTotePipeline();
 *          findToteThread = new VisionThread(usbCamera, findTotePipeline, this);
 *      }
 *
 *     {@literal @}Override
 *      public void autonomousInit() {
 *          findToteThread.start();
 *      }
 *
 *     {@literal @}Override
 *      public void autonomousPeriodic() {
 *          double angle;
 *          double distance;
 *          synchronized (visionLock) {
 *              if (!pipelineRan) {
 *                  // Wait until the pipeline has run
 *                  return;
 *              }
 *              // Copy the outputs to make sure they're all from the same run
 *              angle = this.angleToTote;
 *              distance = this.distanceToTote;
 *          }
 *          if (!aimedAtTote()) {
 *              turnToAngle(angle);
 *          } else if (!droveToTote()) {
 *              driveDistance(distance);
 *          } else if (!grabbedTote()) {
 *              grabTote();
 *          } else {
 *              // Tote was grabbed and we're done!
 *              return;
 *          }
 *      }
 *
 * }
 * </code></pre>
 */
package edu.wpi.first.wpilibj.vision;
