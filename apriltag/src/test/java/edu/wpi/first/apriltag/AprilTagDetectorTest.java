// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.apriltag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.util.RuntimeLoader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

@SuppressWarnings("PMD.MutableStaticState")
class AprilTagDetectorTest {
  @SuppressWarnings("MemberName")
  AprilTagDetector detector;

  static RuntimeLoader<Core> loader;

  @BeforeAll
  static void beforeAll() {
    try {
      loader =
          new RuntimeLoader<>(
              Core.NATIVE_LIBRARY_NAME, RuntimeLoader.getDefaultExtractionRoot(), Core.class);
      loader.loadLibrary();
    } catch (IOException ex) {
      fail(ex);
    }
  }

  @BeforeEach
  void beforeEach() {
    detector = new AprilTagDetector();
  }

  @AfterEach
  void afterEach() {
    detector.close();
  }

  @Test
  void testNumThreads() {
    assertEquals(1, detector.getNumThreads());
    detector.setNumThreads(2);
    assertEquals(2, detector.getNumThreads());
  }

  @Test
  void testAdd16h5() {
    assertDoesNotThrow(() -> detector.addFamily("tag16h5"));
    // duplicate addition is also okay
    assertDoesNotThrow(() -> detector.addFamily("tag16h5"));
  }

  @Test
  void testAdd25h9() {
    assertDoesNotThrow(() -> detector.addFamily("tag25h9"));
  }

  @Test
  void testAdd36h11() {
    assertDoesNotThrow(() -> detector.addFamily("tag36h11"));
  }

  @Test
  void testAddMultiple() {
    assertDoesNotThrow(() -> detector.addFamily("tag16h5"));
    assertDoesNotThrow(() -> detector.addFamily("tag36h11"));
  }

  @Test
  void testRemoveFamily() {
    // okay to remove non-existent family
    detector.removeFamily("tag16h5");

    // add and remove
    detector.addFamily("tag16h5");
    detector.removeFamily("tag16h5");
  }

  @SuppressWarnings("PMD.AssignmentInOperand")
  public Mat loadImage(String resource) throws IOException {
    Mat encoded;
    try (InputStream is = getClass().getResource(resource).openStream()) {
      try (ByteArrayOutputStream os = new ByteArrayOutputStream(is.available())) {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
          os.write(buffer, 0, bytesRead);
        }
        encoded = new Mat(1, os.size(), CvType.CV_8U);
        encoded.put(0, 0, os.toByteArray());
      }
    }
    Mat image = Imgcodecs.imdecode(encoded, Imgcodecs.IMREAD_COLOR);
    encoded.release();
    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
    return image;
  }

  @Test
  void testDecodeAndPose() {
    detector.addFamily("tag16h5");
    detector.addFamily("tag36h11");

    Mat image;
    try {
      image = loadImage("tag1_640_480.jpg");
    } catch (IOException ex) {
      fail(ex);
      return;
    }
    try {
      AprilTagDetection[] results = detector.detect(image);
      assertEquals(1, results.length);
      assertEquals("tag36h11", results[0].getFamily());
      assertEquals(1, results[0].getId());
      assertEquals(0, results[0].getHamming());

      AprilTagPoseEstimator.Config cfg = new AprilTagPoseEstimator.Config(0.2, 500, 500, 320, 240);
      AprilTagPoseEstimate est =
          AprilTagPoseEstimator.estimateOrthogonalIteration(results[0], cfg, 50);
      assertEquals(new Transform3d(), est.pose2);
      Transform3d pose = AprilTagPoseEstimator.estimate(results[0], cfg);
      assertEquals(est.pose1, pose);
    } finally {
      image.release();
    }
  }
}
