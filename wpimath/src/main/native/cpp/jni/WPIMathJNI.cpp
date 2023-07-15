// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <jni.h>

#include <exception>

#include <wpi/jni_util.h>

#include "Eigen/Cholesky"
#include "Eigen/Core"
#include "Eigen/Eigenvalues"
#include "Eigen/QR"
#include "edu_wpi_first_math_WPIMathJNI.h"
#include "frc/DARE.h"
#include "frc/geometry/Pose3d.h"
#include "frc/trajectory/TrajectoryUtil.h"
#include "unsupported/Eigen/MatrixFunctions"

using namespace wpi::java;

namespace {

/**
 * Returns true if (A, B) is a stabilizable pair.
 *
 * (A, B) is stabilizable if and only if the uncontrollable eigenvalues of A, if
 * any, have absolute values less than one, where an eigenvalue is
 * uncontrollable if rank([λI - A, B]) < n where n is the number of states.
 *
 * @param A System matrix.
 * @param B Input matrix.
 */
bool IsStabilizable(const Eigen::Ref<const Eigen::MatrixXd>& A,
                    const Eigen::Ref<const Eigen::MatrixXd>& B) {
  Eigen::EigenSolver<Eigen::MatrixXd> es{A, false};

  for (int i = 0; i < A.rows(); ++i) {
    if (es.eigenvalues()[i].real() * es.eigenvalues()[i].real() +
            es.eigenvalues()[i].imag() * es.eigenvalues()[i].imag() <
        1) {
      continue;
    }

    Eigen::MatrixXcd E{A.rows(), A.rows() + B.cols()};
    E << es.eigenvalues()[i] * Eigen::MatrixXcd::Identity(A.rows(), A.rows()) -
             A,
        B;

    Eigen::ColPivHouseholderQR<Eigen::MatrixXcd> qr{E};
    if (qr.rank() < A.rows()) {
      return false;
    }
  }
  return true;
}

}  // namespace

static JClass pose3dCls;
static JClass quaternionCls;
static JClass rotation3dCls;
static JClass translation3dCls;
static JClass twist3dCls;

static const JClassInit classes[] = {
    {"edu/wpi/first/math/geometry/Pose3d", &pose3dCls},
    {"edu/wpi/first/math/geometry/Quaternion", &quaternionCls},
    {"edu/wpi/first/math/geometry/Rotation3d", &rotation3dCls},
    {"edu/wpi/first/math/geometry/Translation3d", &translation3dCls},
    {"edu/wpi/first/math/geometry/Twist3d", &twist3dCls}};

extern "C" {

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
  JNIEnv* env;
  if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
    return JNI_ERR;
  }

  for (auto& c : classes) {
    *c.cls = JClass(env, c.name);
    if (!*c.cls) {
      return JNI_ERR;
    }
  }

  return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* reserved) {
  JNIEnv* env;
  if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
    return;
  }

  for (auto& c : classes) {
    c.cls->free(env);
  }
}

}  // extern "C"

// frc::Pose3d Pose3dFromJDoubleArray(JNIEnv* env, jdoubleArray arr) {
//   jdouble* nativeArray = env->GetDoubleArrayElements(arr, nullptr);
//   frc::Translation3d translation{units::meter_t{nativeArray[0]},
//                                  units::meter_t{nativeArray[1]},
//                                  units::meter_t{nativeArray[2]}};
//   frc::Quaternion quaternion{nativeArray[3], nativeArray[4], nativeArray[5],
//                              nativeArray[6]};
//   env->ReleaseDoubleArrayElements(arr, nativeArray, 0);
//   return {translation, frc::Rotation3d{quaternion}};
// }
//
// frc::Twist3d Twist3dFromJDoubleArray(JNIEnv* env, jdoubleArray arr) {
//   jdouble* nativeArray = env->GetDoubleArrayElements(arr, nullptr);
//   frc::Twist3d twist = {
//       units::meter_t{nativeArray[0]},  units::meter_t{nativeArray[1]},
//       units::meter_t{nativeArray[2]},  units::radian_t{nativeArray[3]},
//       units::radian_t{nativeArray[4]}, units::radian_t{nativeArray[5]}};
//   env->ReleaseDoubleArrayElements(arr, nativeArray, 0);
//   return twist;
// }

jobject MakeJObject(JNIEnv* env, const frc::Pose3d& pose) {
  static jmethodID quaternionCtor = nullptr;
  static jmethodID rotation3dCtor = nullptr;
  static jmethodID pose3dCtor = nullptr;
  static jmethodID translation3dCtor = nullptr;
  static bool loaded = false;
  if (!loaded) {
    quaternionCtor = env->GetMethodID(quaternionCls, "<init>", "(DDDD)V");
    rotation3dCtor = env->GetMethodID(
        rotation3dCls, "<init>", "(Ledu/wpi/first/math/geometry/Quaternion;)V");
    pose3dCtor =
        env->GetMethodID(pose3dCls, "<init>",
                         "(Ledu/wpi/first/math/geometry/Translation3d;Ledu/wpi/"
                         "first/math/geometry/Rotation3d;)V");
    translation3dCtor = env->GetMethodID(translation3dCls, "<init>", "(DDD)V");
    loaded = true;
  }

  const frc::Quaternion& quaternion = pose.Rotation().GetQuaternion();

  jobject jTranslation =
      env->NewObject(translation3dCls, translation3dCtor, pose.X().value(),
                     pose.Y().value(), pose.Z().value());
  jobject jQuaternion =
      env->NewObject(quaternionCls, quaternionCtor, quaternion.W(),
                     quaternion.X(), quaternion.Y(), quaternion.Z());
  jobject jRotation =
      env->NewObject(rotation3dCls, rotation3dCtor, jQuaternion);
  jobject jPose =
      env->NewObject(pose3dCls, pose3dCtor, jTranslation, jRotation);

  return jPose;
}

jobject MakeJObject(JNIEnv* env, const frc::Twist3d& twist) {
  static jmethodID twist3dCtor = nullptr;
  if (!twist3dCtor) {
    twist3dCtor = env->GetMethodID(twist3dCls, "<init>", "(DDDDDD)V");
  }

  jobject jTwist = env->NewObject(
      twist3dCls, twist3dCtor, twist.dx.value(), twist.dy.value(),
      twist.dz.value(), twist.rx.value(), twist.ry.value(), twist.rz.value());

  return jTwist;
}

// jdoubleArray MakeJDoubleArray(JNIEnv* env, const frc::Pose3d& pose) {
//   const frc::Quaternion& quaternion = pose.Rotation().GetQuaternion();
//   jdoubleArray results = env->NewDoubleArray(7);
//   jdouble* buffer = env->GetDoubleArrayElements(results, nullptr);
//   buffer[0] = pose.X().value();
//   buffer[1] = pose.Y().value();
//   buffer[2] = pose.Z().value();
//   buffer[3] = quaternion.W();
//   buffer[4] = quaternion.X();
//   buffer[5] = quaternion.Y();
//   buffer[6] = quaternion.Z();
//   env->ReleaseDoubleArrayElements(results, buffer, 0);
//   return results;
// }
//
// jdoubleArray MakeJDoubleArray(JNIEnv* env, const frc::Twist3d& twist) {
//   jdoubleArray results = env->NewDoubleArray(6);
//   jdouble* buffer = env->GetDoubleArrayElements(results, nullptr);
//   buffer[0] = twist.dx.value();
//   buffer[1] = twist.dy.value();
//   buffer[2] = twist.dz.value();
//   buffer[3] = twist.rx.value();
//   buffer[4] = twist.ry.value();
//   buffer[5] = twist.rz.value();
//   env->ReleaseDoubleArrayElements(results, buffer, 0);
//   return results;
// }

std::vector<double> GetElementsFromTrajectory(
    const frc::Trajectory& trajectory) {
  std::vector<double> elements;
  elements.reserve(trajectory.States().size() * 7);

  for (auto&& state : trajectory.States()) {
    elements.push_back(state.t.value());
    elements.push_back(state.velocity.value());
    elements.push_back(state.acceleration.value());
    elements.push_back(state.pose.X().value());
    elements.push_back(state.pose.Y().value());
    elements.push_back(state.pose.Rotation().Radians().value());
    elements.push_back(state.curvature.value());
  }

  return elements;
}

frc::Trajectory CreateTrajectoryFromElements(std::span<const double> elements) {
  // Make sure that the elements have the correct length.
  assert(elements.size() % 7 == 0);

  // Create a vector of states from the elements.
  std::vector<frc::Trajectory::State> states;
  states.reserve(elements.size() / 7);

  for (size_t i = 0; i < elements.size(); i += 7) {
    states.emplace_back(frc::Trajectory::State{
        units::second_t{elements[i]},
        units::meters_per_second_t{elements[i + 1]},
        units::meters_per_second_squared_t{elements[i + 2]},
        frc::Pose2d{units::meter_t{elements[i + 3]},
                    units::meter_t{elements[i + 4]},
                    units::radian_t{elements[i + 5]}},
        units::curvature_t{elements[i + 6]}});
  }

  return frc::Trajectory(states);
}

extern "C" {

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    dare
 * Signature: ([D[D[D[DII[D)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_dare
  (JNIEnv* env, jclass, jdoubleArray A, jdoubleArray B, jdoubleArray Q,
   jdoubleArray R, jint states, jint inputs, jdoubleArray S)
{
  jdouble* nativeA = env->GetDoubleArrayElements(A, nullptr);
  jdouble* nativeB = env->GetDoubleArrayElements(B, nullptr);
  jdouble* nativeQ = env->GetDoubleArrayElements(Q, nullptr);
  jdouble* nativeR = env->GetDoubleArrayElements(R, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Amat{nativeA, states, states};
  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Bmat{nativeB, states, inputs};
  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Qmat{nativeQ, states, states};
  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Rmat{nativeR, inputs, inputs};

  try {
    Eigen::MatrixXd result = frc::DARE(Amat, Bmat, Qmat, Rmat);

    env->ReleaseDoubleArrayElements(A, nativeA, 0);
    env->ReleaseDoubleArrayElements(B, nativeB, 0);
    env->ReleaseDoubleArrayElements(Q, nativeQ, 0);
    env->ReleaseDoubleArrayElements(R, nativeR, 0);

    env->SetDoubleArrayRegion(S, 0, states * states, result.data());
  } catch (const std::runtime_error& e) {
    jclass cls = env->FindClass("java/lang/RuntimeException");
    if (cls) {
      env->ThrowNew(cls, e.what());
    }
  }
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    exp
 * Signature: ([DI[D)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_exp
  (JNIEnv* env, jclass, jdoubleArray src, jint rows, jdoubleArray dst)
{
  jdouble* arrayBody = env->GetDoubleArrayElements(src, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Amat{arrayBody, rows, rows};
  Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor> Aexp =
      Amat.exp();

  env->ReleaseDoubleArrayElements(src, arrayBody, 0);
  env->SetDoubleArrayRegion(dst, 0, rows * rows, Aexp.data());
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    pow
 * Signature: ([DID[D)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_pow
  (JNIEnv* env, jclass, jdoubleArray src, jint rows, jdouble exponent,
   jdoubleArray dst)
{
  jdouble* arrayBody = env->GetDoubleArrayElements(src, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Amat{arrayBody, rows, rows};  // NOLINT
  Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor> Apow =
      Amat.pow(exponent);

  env->ReleaseDoubleArrayElements(src, arrayBody, 0);
  env->SetDoubleArrayRegion(dst, 0, rows * rows, Apow.data());
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    expPose3d
 * Signature: (DDDDDDDDDDDDD)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_math_WPIMathJNI_expPose3d
  (JNIEnv* env, jclass, jdouble poseX, jdouble poseY, jdouble poseZ,
   jdouble poseQw, jdouble poseQx, jdouble poseQy, jdouble poseQz,
   jdouble twistDx, jdouble twistDy, jdouble twistDz, jdouble twistRx,
   jdouble twistRy, jdouble twistRz)
{
  frc::Pose3d poseCpp = {
      units::meter_t{poseX}, units::meter_t{poseY}, units::meter_t{poseZ},
      frc::Rotation3d{frc::Quaternion{poseQw, poseQx, poseQy, poseQz}}};
  frc::Twist3d twistCpp = {units::meter_t{twistDx},  units::meter_t{twistDy},
                           units::meter_t{twistDz},  units::radian_t{twistRx},
                           units::radian_t{twistRy}, units::radian_t{twistRz}};

  frc::Pose3d result = poseCpp.Exp(twistCpp);

  return MakeJObject(env, result);
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    logPose3d
 * Signature: (DDDDDDDDDDDDDD)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_math_WPIMathJNI_logPose3d
  (JNIEnv* env, jclass, jdouble startX, jdouble startY, jdouble startZ,
   jdouble startQw, jdouble startQx, jdouble startQy, jdouble startQz,
   jdouble endX, jdouble endY, jdouble endZ, jdouble endQw, jdouble endQx,
   jdouble endQy, jdouble endQz)
{
  frc::Pose3d startCpp = {
      units::meter_t{startX}, units::meter_t{startY}, units::meter_t{startZ},
      frc::Rotation3d{frc::Quaternion{startQw, startQx, startQy, startQz}}};
  frc::Pose3d endCpp = {
      units::meter_t{endX}, units::meter_t{endY}, units::meter_t{endZ},
      frc::Rotation3d{frc::Quaternion{endQw, endQx, endQy, endQz}}};

  frc::Twist3d result = startCpp.Log(endCpp);

  return MakeJObject(env, result);
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    isStabilizable
 * Signature: (II[D[D)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_math_WPIMathJNI_isStabilizable
  (JNIEnv* env, jclass, jint states, jint inputs, jdoubleArray aSrc,
   jdoubleArray bSrc)
{
  jdouble* nativeA = env->GetDoubleArrayElements(aSrc, nullptr);
  jdouble* nativeB = env->GetDoubleArrayElements(bSrc, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      A{nativeA, states, states};

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      B{nativeB, states, inputs};

  bool isStabilizable = IsStabilizable(A, B);

  env->ReleaseDoubleArrayElements(aSrc, nativeA, 0);
  env->ReleaseDoubleArrayElements(bSrc, nativeB, 0);

  return isStabilizable;
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    fromPathweaverJson
 * Signature: (Ljava/lang/String;)[D
 */
JNIEXPORT jdoubleArray JNICALL
Java_edu_wpi_first_math_WPIMathJNI_fromPathweaverJson
  (JNIEnv* env, jclass, jstring path)
{
  try {
    auto trajectory =
        frc::TrajectoryUtil::FromPathweaverJson(JStringRef{env, path}.c_str());
    std::vector<double> elements = GetElementsFromTrajectory(trajectory);
    return MakeJDoubleArray(env, elements);
  } catch (std::exception& e) {
    jclass cls = env->FindClass("java/io/IOException");
    if (cls) {
      env->ThrowNew(cls, e.what());
    }
    return nullptr;
  }
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    toPathweaverJson
 * Signature: ([DLjava/lang/String;)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_toPathweaverJson
  (JNIEnv* env, jclass, jdoubleArray elements, jstring path)
{
  try {
    auto trajectory =
        CreateTrajectoryFromElements(JDoubleArrayRef{env, elements});
    frc::TrajectoryUtil::ToPathweaverJson(trajectory,
                                          JStringRef{env, path}.c_str());
  } catch (std::exception& e) {
    jclass cls = env->FindClass("java/io/IOException");
    if (cls) {
      env->ThrowNew(cls, e.what());
    }
  }
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    deserializeTrajectory
 * Signature: (Ljava/lang/String;)[D
 */
JNIEXPORT jdoubleArray JNICALL
Java_edu_wpi_first_math_WPIMathJNI_deserializeTrajectory
  (JNIEnv* env, jclass, jstring json)
{
  try {
    auto trajectory = frc::TrajectoryUtil::DeserializeTrajectory(
        JStringRef{env, json}.c_str());
    std::vector<double> elements = GetElementsFromTrajectory(trajectory);
    return MakeJDoubleArray(env, elements);
  } catch (std::exception& e) {
    jclass cls = env->FindClass(
        "edu/wpi/first/math/trajectory/TrajectoryUtil$"
        "TrajectorySerializationException");
    if (cls) {
      env->ThrowNew(cls, e.what());
    }
    return nullptr;
  }
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    serializeTrajectory
 * Signature: ([D)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_edu_wpi_first_math_WPIMathJNI_serializeTrajectory
  (JNIEnv* env, jclass, jdoubleArray elements)
{
  try {
    auto trajectory =
        CreateTrajectoryFromElements(JDoubleArrayRef{env, elements});
    return MakeJString(env,
                       frc::TrajectoryUtil::SerializeTrajectory(trajectory));
  } catch (std::exception& e) {
    jclass cls = env->FindClass(
        "edu/wpi/first/math/trajectory/TrajectoryUtil$"
        "TrajectorySerializationException");
    if (cls) {
      env->ThrowNew(cls, e.what());
    }
    return nullptr;
  }
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    rankUpdate
 * Signature: ([DI[DDZ)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_rankUpdate
  (JNIEnv* env, jclass, jdoubleArray mat, jint rows, jdoubleArray vec,
   jdouble sigma, jboolean lowerTriangular)
{
  jdouble* matBody = env->GetDoubleArrayElements(mat, nullptr);
  jdouble* vecBody = env->GetDoubleArrayElements(vec, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      L{matBody, rows, rows};
  Eigen::Map<Eigen::Vector<double, Eigen::Dynamic>> v{vecBody, rows};

  if (lowerTriangular == JNI_TRUE) {
    Eigen::internal::llt_inplace<double, Eigen::Lower>::rankUpdate(L, v, sigma);
  } else {
    Eigen::internal::llt_inplace<double, Eigen::Upper>::rankUpdate(L, v, sigma);
  }

  env->ReleaseDoubleArrayElements(mat, matBody, 0);
  env->ReleaseDoubleArrayElements(vec, vecBody, 0);
}

/*
 * Class:     edu_wpi_first_math_WPIMathJNI
 * Method:    solveFullPivHouseholderQr
 * Signature: ([DII[DII[D)V
 */
JNIEXPORT void JNICALL
Java_edu_wpi_first_math_WPIMathJNI_solveFullPivHouseholderQr
  (JNIEnv* env, jclass, jdoubleArray A, jint Arows, jint Acols, jdoubleArray B,
   jint Brows, jint Bcols, jdoubleArray dst)
{
  jdouble* nativeA = env->GetDoubleArrayElements(A, nullptr);
  jdouble* nativeB = env->GetDoubleArrayElements(B, nullptr);

  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Amat{nativeA, Arows, Acols};
  Eigen::Map<
      Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor>>
      Bmat{nativeB, Brows, Bcols};

  Eigen::Matrix<double, Eigen::Dynamic, Eigen::Dynamic, Eigen::RowMajor> Xmat =
      Amat.fullPivHouseholderQr().solve(Bmat);

  env->ReleaseDoubleArrayElements(A, nativeA, 0);
  env->ReleaseDoubleArrayElements(B, nativeB, 0);
  env->SetDoubleArrayRegion(dst, 0, Brows * Bcols, Xmat.data());
}

}  // extern "C"
