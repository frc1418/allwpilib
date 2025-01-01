// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <cameracalibration.h>
#include <fieldcalibration.h>

#include <string>

#include <gtest/gtest.h>
#include <wpi/json.h>

const std::string projectRootPath = PROJECT_ROOT_PATH;
const std::string calSavePath =
    projectRootPath.substr(0,
                           projectRootPath.find("/src/main/native/resources")) +
    "/build";
cameracalibration::CameraModel cameraModel = {
    .intrinsic_matrix = Eigen::Matrix<double, 3, 3>::Identity(),
    .distortion_coefficients = Eigen::Matrix<double, 8, 1>::Zero(),
    .avg_reprojection_error = 0.0};
#ifdef __linux__
const std::string fileSuffix = ".avi";
const std::string videoLocation = "/altfieldvideo";
#else
const std::string fileSuffix = ".mp4";
const std::string videoLocation = "/fieldvideo";
#endif
// TEST(Camera_CalibrationTest, OpenCV_Typical) {
//   int ret = cameracalibration::calibrate(
//       projectRootPath + "/testcalibration" + fileSuffix, 0.709f, 0.551f, 12,
//       8, false);
//   EXPECT_EQ(ret, 0);
// }

// TEST(Camera_CalibrationTest, OpenCV_Atypical) {
//   int ret = cameracalibration::calibrate(
//       projectRootPath + videoLocation + "/long" + fileSuffix, 0.709f, 0.551f,
//       12, 8, false);
//   EXPECT_EQ(ret, 1);
// }

// TEST(Camera_CalibrationTest, MRcal_Typical) {
//   int ret = cameracalibration::calibrate(
//       projectRootPath + "/testcalibration" + fileSuffix, 0.709f, 12, 8, 1080,
//       1920, false);
//   EXPECT_EQ(ret, 0);
// }

// TEST(Camera_CalibrationTest, MRcal_Atypical) {
//   int ret = cameracalibration::calibrate(
//       projectRootPath + videoLocation + "/short" + fileSuffix, 0.709f, 12, 8,
//       1080, 1920, false);
//   EXPECT_EQ(ret, 1);
// }

TEST(Field_CalibrationTest, Typical) {
  int ret = fieldcalibration::calibrate(
      "/home/matt/Videos/wpical-testing",
      projectRootPath + "/2024-output-typical.json",
      "/home/matt/Downloads/"
      "photon_calibration_Arducam_OV2311_USB_Camera_1600x1200.json",
      projectRootPath + "/2024-crescendo.json", 3, false);
  EXPECT_EQ(ret, 0);
}

// TEST(Field_CalibrationTest, Atypical_Bad_Camera_Model_Directory) {
//   int ret = fieldcalibration::calibrate(
//       projectRootPath + videoLocation, projectRootPath + "",
//       projectRootPath + videoLocation + "/long" + fileSuffix,
//       projectRootPath + "/2024-crescendo.json", 3, false);
//   EXPECT_EQ(ret, 1);
// }

// TEST(Field_CalibrationTest, Atypical_Bad_Ideal_JSON) {
//   int ret = fieldcalibration::calibrate(
//       projectRootPath + videoLocation, projectRootPath + "",
//       projectRootPath + "/camera_calibration.json",
//       projectRootPath + "/camera_calibration.json", 3, false);
//   EXPECT_EQ(ret, 1);
// }

// TEST(Field_CalibrationTest, Atypical_Bad_Input_Directory) {
//   int ret = fieldcalibration::calibrate(
//       projectRootPath + "", projectRootPath + "",
//       projectRootPath + "/camera_calibration.json",
//       projectRootPath + "/2024-crescendo.json", 3, false);
//   EXPECT_EQ(ret, 1);
// }
