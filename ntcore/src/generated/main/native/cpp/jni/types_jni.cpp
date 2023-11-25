// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <jni.h>

#include <wpi/jni_util.h>

#include "edu_wpi_first_networktables_NetworkTablesJNI.h"
#include "ntcore.h"

using namespace wpi::java;

//
// Globals and load/unload
//

static JClass timestampedBooleanCls;
static JClass timestampedIntegerCls;
static JClass timestampedFloatCls;
static JClass timestampedDoubleCls;
static JClass timestampedStringCls;
static JClass timestampedRawCls;
static JClass timestampedBooleanArrayCls;
static JClass timestampedIntegerArrayCls;
static JClass timestampedFloatArrayCls;
static JClass timestampedDoubleArrayCls;
static JClass timestampedStringArrayCls;
static JClass jbyteArrayCls;
static JClass jbooleanArrayCls;
static JClass jlongArrayCls;
static JClass jfloatArrayCls;
static JClass jdoubleArrayCls;
static JClass jobjectArrayCls;
static JException illegalArgEx;
static JException indexOobEx;
static JException nullPointerEx;

static const JClassInit classes[] = {
    {"edu/wpi/first/networktables/TimestampedBoolean", &timestampedBooleanCls},
    {"edu/wpi/first/networktables/TimestampedInteger", &timestampedIntegerCls},
    {"edu/wpi/first/networktables/TimestampedFloat", &timestampedFloatCls},
    {"edu/wpi/first/networktables/TimestampedDouble", &timestampedDoubleCls},
    {"edu/wpi/first/networktables/TimestampedString", &timestampedStringCls},
    {"edu/wpi/first/networktables/TimestampedRaw", &timestampedRawCls},
    {"edu/wpi/first/networktables/TimestampedBooleanArray", &timestampedBooleanArrayCls},
    {"edu/wpi/first/networktables/TimestampedIntegerArray", &timestampedIntegerArrayCls},
    {"edu/wpi/first/networktables/TimestampedFloatArray", &timestampedFloatArrayCls},
    {"edu/wpi/first/networktables/TimestampedDoubleArray", &timestampedDoubleArrayCls},
    {"edu/wpi/first/networktables/TimestampedStringArray", &timestampedStringArrayCls},
    {"[B", &jbyteArrayCls},
    {"[Z", &jbooleanArrayCls},
    {"[J", &jlongArrayCls},
    {"[F", &jfloatArrayCls},
    {"[D", &jdoubleArrayCls},
    {"[Ljava/lang/Object;", &jobjectArrayCls},
};

static const JExceptionInit exceptions[] = {
    {"java/lang/IllegalArgumentException", &illegalArgEx},
    {"java/lang/IndexOutOfBoundsException", &indexOobEx},
    {"java/lang/NullPointerException", &nullPointerEx},
};

namespace nt {

bool JNI_LoadTypes(JNIEnv* env) {
  // Cache references to classes
  for (auto& c : classes) {
    *c.cls = JClass(env, c.name);
    if (!*c.cls) {
      return false;
    }
  }

  for (auto& c : exceptions) {
    *c.cls = JException(env, c.name);
    if (!*c.cls) {
      return false;
    }
  }

  return true;
}

void JNI_UnloadTypes(JNIEnv* env) {
  // Delete global references
  for (auto& c : classes) {
    c.cls->free(env);
  }
  for (auto& c : exceptions) {
    c.cls->free(env);
  }
}

}  // namespace nt

static std::vector<int> FromJavaBooleanArray(JNIEnv* env, jbooleanArray jarr) {
  CriticalJSpan<const jboolean> ref{env, jarr};
  if (!ref) {
    return {};
  }
  std::span<const jboolean> elements{ref};
  size_t len = elements.size();
  std::vector<int> arr;
  arr.reserve(len);
  for (size_t i = 0; i < len; ++i) {
    arr.push_back(elements[i]);
  }
  return arr;
}

static std::vector<std::string> FromJavaStringArray(JNIEnv* env, jobjectArray jarr) {
  size_t len = env->GetArrayLength(jarr);
  std::vector<std::string> arr;
  arr.reserve(len);
  for (size_t i = 0; i < len; ++i) {
    JLocal<jstring> elem{
        env, static_cast<jstring>(env->GetObjectArrayElement(jarr, i))};
    if (!elem) {
      return {};
    }
    arr.emplace_back(JStringRef{env, elem}.str());
  }
  return arr;
}


static jobject MakeJObject(JNIEnv* env, nt::TimestampedBoolean value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedBooleanCls, "<init>", "(JJZ)V");
  return env->NewObject(timestampedBooleanCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime),
                        static_cast<jboolean>(value.value));
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedInteger value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedIntegerCls, "<init>", "(JJJ)V");
  return env->NewObject(timestampedIntegerCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime),
                        static_cast<jlong>(value.value));
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedFloat value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedFloatCls, "<init>", "(JJF)V");
  return env->NewObject(timestampedFloatCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime),
                        static_cast<jfloat>(value.value));
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedDouble value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedDoubleCls, "<init>", "(JJD)V");
  return env->NewObject(timestampedDoubleCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime),
                        static_cast<jdouble>(value.value));
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedString value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedStringCls, "<init>", "(JJLjava/lang/String;)V");
  JLocal<jstring> val{env, MakeJString(env, value.value)};
  return env->NewObject(timestampedStringCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedRaw value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedRawCls, "<init>", "(JJ[B)V");
  JLocal<jbyteArray> val{env, MakeJByteArray(env, value.value)};
  return env->NewObject(timestampedRawCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedBooleanArray value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedBooleanArrayCls, "<init>", "(JJ[Z)V");
  JLocal<jbooleanArray> val{env, MakeJBooleanArray(env, value.value)};
  return env->NewObject(timestampedBooleanArrayCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedIntegerArray value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedIntegerArrayCls, "<init>", "(JJ[J)V");
  JLocal<jlongArray> val{env, MakeJLongArray(env, value.value)};
  return env->NewObject(timestampedIntegerArrayCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedFloatArray value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedFloatArrayCls, "<init>", "(JJ[F)V");
  JLocal<jfloatArray> val{env, MakeJFloatArray(env, value.value)};
  return env->NewObject(timestampedFloatArrayCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedDoubleArray value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedDoubleArrayCls, "<init>", "(JJ[D)V");
  JLocal<jdoubleArray> val{env, MakeJDoubleArray(env, value.value)};
  return env->NewObject(timestampedDoubleArrayCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobject MakeJObject(JNIEnv* env, nt::TimestampedStringArray value) {
  static jmethodID constructor = env->GetMethodID(
      timestampedStringArrayCls, "<init>", "(JJ[Ljava/lang/Object;)V");
  JLocal<jobjectArray> val{env, MakeJStringArray(env, value.value)};
  return env->NewObject(timestampedStringArrayCls, constructor,
                        static_cast<jlong>(value.time),
                        static_cast<jlong>(value.serverTime), val.obj());
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedBoolean> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedBooleanCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedInteger> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedIntegerCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedFloat> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedFloatCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedDouble> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedDoubleCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedString> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedStringCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedRaw> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedRawCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedBooleanArray> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedBooleanArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedIntegerArray> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedIntegerArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedFloatArray> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedFloatArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedDoubleArray> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedDoubleArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObject(JNIEnv* env,
                                std::span<const nt::TimestampedStringArray> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), timestampedStringArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJObject(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<uint8_t>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jbyteArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJByteArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<int>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jbooleanArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJBooleanArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<int64_t>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jlongArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJLongArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<float>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jfloatArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJFloatArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<double>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jdoubleArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJDoubleArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}

static jobjectArray MakeJObjectArray(JNIEnv* env, std::span<const std::vector<std::string>> arr) {
  jobjectArray jarr =
      env->NewObjectArray(arr.size(), jobjectArrayCls, nullptr);
  if (!jarr) {
    return nullptr;
  }
  for (size_t i = 0; i < arr.size(); ++i) {
    JLocal<jobject> elem{env, MakeJStringArray(env, arr[i])};
    env->SetObjectArrayElement(jarr, i, elem.obj());
  }
  return jarr;
}


extern "C" {

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicBoolean
 * Signature: (IZ)Ledu/wpi/first/networktables/TimestampedBoolean;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicBoolean
  (JNIEnv* env, jclass, jint subentry, jboolean defaultValue)
{
  return MakeJObject(env, nt::GetAtomicBoolean(subentry, defaultValue != JNI_FALSE));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueBoolean
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedBoolean;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueBoolean
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueBoolean(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesBoolean
 * Signature: (I)[Z
 */
JNIEXPORT jbooleanArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesBoolean
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJBooleanArray(env, nt::ReadQueueValuesBoolean(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setBoolean
 * Signature: (IJZ)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setBoolean
  (JNIEnv*, jclass, jint entry, jlong time, jboolean value)
{
  return nt::SetBoolean(entry, value != JNI_FALSE, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getBoolean
 * Signature: (IZ)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getBoolean
  (JNIEnv*, jclass, jint entry, jboolean defaultValue)
{
  return nt::GetBoolean(entry, defaultValue);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultBoolean
 * Signature: (IJZ)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultBoolean
  (JNIEnv*, jclass, jint entry, jlong, jboolean defaultValue)
{
  return nt::SetDefaultBoolean(entry, defaultValue != JNI_FALSE);
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicInteger
 * Signature: (IJ)Ledu/wpi/first/networktables/TimestampedInteger;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicInteger
  (JNIEnv* env, jclass, jint subentry, jlong defaultValue)
{
  return MakeJObject(env, nt::GetAtomicInteger(subentry, defaultValue));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueInteger
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedInteger;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueInteger
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueInteger(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesInteger
 * Signature: (I)[J
 */
JNIEXPORT jlongArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesInteger
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJLongArray(env, nt::ReadQueueValuesInteger(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setInteger
 * Signature: (IJJ)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setInteger
  (JNIEnv*, jclass, jint entry, jlong time, jlong value)
{
  return nt::SetInteger(entry, value, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getInteger
 * Signature: (IJ)J
 */
JNIEXPORT jlong JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getInteger
  (JNIEnv*, jclass, jint entry, jlong defaultValue)
{
  return nt::GetInteger(entry, defaultValue);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultInteger
 * Signature: (IJJ)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultInteger
  (JNIEnv*, jclass, jint entry, jlong, jlong defaultValue)
{
  return nt::SetDefaultInteger(entry, defaultValue);
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicFloat
 * Signature: (IF)Ledu/wpi/first/networktables/TimestampedFloat;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicFloat
  (JNIEnv* env, jclass, jint subentry, jfloat defaultValue)
{
  return MakeJObject(env, nt::GetAtomicFloat(subentry, defaultValue));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueFloat
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedFloat;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueFloat
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueFloat(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesFloat
 * Signature: (I)[F
 */
JNIEXPORT jfloatArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesFloat
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJFloatArray(env, nt::ReadQueueValuesFloat(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setFloat
 * Signature: (IJF)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setFloat
  (JNIEnv*, jclass, jint entry, jlong time, jfloat value)
{
  return nt::SetFloat(entry, value, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getFloat
 * Signature: (IF)F
 */
JNIEXPORT jfloat JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getFloat
  (JNIEnv*, jclass, jint entry, jfloat defaultValue)
{
  return nt::GetFloat(entry, defaultValue);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultFloat
 * Signature: (IJF)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultFloat
  (JNIEnv*, jclass, jint entry, jlong, jfloat defaultValue)
{
  return nt::SetDefaultFloat(entry, defaultValue);
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicDouble
 * Signature: (ID)Ledu/wpi/first/networktables/TimestampedDouble;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicDouble
  (JNIEnv* env, jclass, jint subentry, jdouble defaultValue)
{
  return MakeJObject(env, nt::GetAtomicDouble(subentry, defaultValue));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueDouble
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedDouble;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueDouble
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueDouble(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesDouble
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesDouble
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJDoubleArray(env, nt::ReadQueueValuesDouble(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDouble
 * Signature: (IJD)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDouble
  (JNIEnv*, jclass, jint entry, jlong time, jdouble value)
{
  return nt::SetDouble(entry, value, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getDouble
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getDouble
  (JNIEnv*, jclass, jint entry, jdouble defaultValue)
{
  return nt::GetDouble(entry, defaultValue);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultDouble
 * Signature: (IJD)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultDouble
  (JNIEnv*, jclass, jint entry, jlong, jdouble defaultValue)
{
  return nt::SetDefaultDouble(entry, defaultValue);
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicString
 * Signature: (ILjava/lang/String;)Ledu/wpi/first/networktables/TimestampedString;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicString
  (JNIEnv* env, jclass, jint subentry, jstring defaultValue)
{
  return MakeJObject(env, nt::GetAtomicString(subentry, JStringRef{env, defaultValue}));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueString
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedString;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueString
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueString(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesString
 * Signature: (I)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesString
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJStringArray(env, nt::ReadQueueValuesString(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setString
 * Signature: (IJLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setString
  (JNIEnv* env, jclass, jint entry, jlong time, jstring value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetString(entry, JStringRef{env, value}, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getString
 * Signature: (ILjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getString
  (JNIEnv* env, jclass, jint entry, jstring defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsString()) {
    return defaultValue;
  }
  return MakeJString(env, val.GetString());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultString
 * Signature: (IJLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultString
  (JNIEnv* env, jclass, jint entry, jlong, jstring defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultString(entry, JStringRef{env, defaultValue});
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicRaw
 * Signature: (I[B)Ledu/wpi/first/networktables/TimestampedRaw;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicRaw
  (JNIEnv* env, jclass, jint subentry, jbyteArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicRaw(subentry, CriticalJSpan<const jbyte>{env, defaultValue}.uarray()));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueRaw
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedRaw;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueRaw
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueRaw(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesRaw
 * Signature: (I)[[B
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesRaw
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesRaw(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setRaw
 * Signature: (IJ[BII)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setRaw
  (JNIEnv* env, jclass, jint entry, jlong time, jbyteArray value, jint start, jint len)
{
  if (!value) {
    nullPointerEx.Throw(env, "value is null");
    return false;
  }
  if (start < 0) {
    indexOobEx.Throw(env, "start must be >= 0");
    return false;
  }
  if (len < 0) {
    indexOobEx.Throw(env, "len must be >= 0");
    return false;
  }
  CriticalJSpan<const jbyte> cvalue{env, value};
  if (static_cast<unsigned int>(start + len) > cvalue.size()) {
    indexOobEx.Throw(env, "start + len must be smaller than array length");
    return false;
  }
  return nt::SetRaw(entry, cvalue.uarray().subspan(start, len), time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setRawBuffer
 * Signature: (IJLjava/nio/ByteBuffer;II)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setRawBuffer
  (JNIEnv* env, jclass, jint entry, jlong time, jobject value, jint start, jint len)
{
  if (!value) {
    nullPointerEx.Throw(env, "value is null");
    return false;
  }
  if (start < 0) {
    indexOobEx.Throw(env, "start must be >= 0");
    return false;
  }
  if (len < 0) {
    indexOobEx.Throw(env, "len must be >= 0");
    return false;
  }
  JSpan<const jbyte> cvalue{env, value, static_cast<size_t>(start + len)};
  if (!cvalue) {
    illegalArgEx.Throw(env, "value must be a native ByteBuffer");
    return false;
  }
  return nt::SetRaw(entry, cvalue.uarray().subspan(start, len), time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getRaw
 * Signature: (I[B)[B
 */
JNIEXPORT jbyteArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getRaw
  (JNIEnv* env, jclass, jint entry, jbyteArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsRaw()) {
    return defaultValue;
  }
  return MakeJByteArray(env, val.GetRaw());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultRaw
 * Signature: (IJ[BII)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultRaw
  (JNIEnv* env, jclass, jint entry, jlong, jbyteArray defaultValue, jint start, jint len)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "value is null");
    return false;
  }
  if (start < 0) {
    indexOobEx.Throw(env, "start must be >= 0");
    return false;
  }
  if (len < 0) {
    indexOobEx.Throw(env, "len must be >= 0");
    return false;
  }
  CriticalJSpan<const jbyte> cvalue{env, defaultValue};
  if (static_cast<unsigned int>(start + len) > cvalue.size()) {
    indexOobEx.Throw(env, "start + len must be smaller than array length");
    return false;
  }
  return nt::SetDefaultRaw(entry, cvalue.uarray().subspan(start, len));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultRawBuffer
 * Signature: (IJLjava/nio/ByteBuffer;II)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultRawBuffer
  (JNIEnv* env, jclass, jint entry, jlong, jobject defaultValue, jint start, jint len)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "value is null");
    return false;
  }
  if (start < 0) {
    indexOobEx.Throw(env, "start must be >= 0");
    return false;
  }
  if (len < 0) {
    indexOobEx.Throw(env, "len must be >= 0");
    return false;
  }
  JSpan<const jbyte> cvalue{env, defaultValue, static_cast<size_t>(start + len)};
  if (!cvalue) {
    illegalArgEx.Throw(env, "value must be a native ByteBuffer");
    return false;
  }
  return nt::SetDefaultRaw(entry, cvalue.uarray().subspan(start, len));
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicBooleanArray
 * Signature: (I[Z)Ledu/wpi/first/networktables/TimestampedBooleanArray;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicBooleanArray
  (JNIEnv* env, jclass, jint subentry, jbooleanArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicBooleanArray(subentry, FromJavaBooleanArray(env, defaultValue)));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueBooleanArray
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedBooleanArray;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueBooleanArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueBooleanArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesBooleanArray
 * Signature: (I)[[Z
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesBooleanArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesBooleanArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setBooleanArray
 * Signature: (IJ[Z)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setBooleanArray
  (JNIEnv* env, jclass, jint entry, jlong time, jbooleanArray value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetBooleanArray(entry, FromJavaBooleanArray(env, value), time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getBooleanArray
 * Signature: (I[Z)[Z
 */
JNIEXPORT jbooleanArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getBooleanArray
  (JNIEnv* env, jclass, jint entry, jbooleanArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsBooleanArray()) {
    return defaultValue;
  }
  return MakeJBooleanArray(env, val.GetBooleanArray());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultBooleanArray
 * Signature: (IJ[Z)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultBooleanArray
  (JNIEnv* env, jclass, jint entry, jlong, jbooleanArray defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultBooleanArray(entry, FromJavaBooleanArray(env, defaultValue));
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicIntegerArray
 * Signature: (I[J)Ledu/wpi/first/networktables/TimestampedIntegerArray;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicIntegerArray
  (JNIEnv* env, jclass, jint subentry, jlongArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicIntegerArray(subentry, CriticalJSpan<const jlong>{env, defaultValue}));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueIntegerArray
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedIntegerArray;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueIntegerArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueIntegerArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesIntegerArray
 * Signature: (I)[[J
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesIntegerArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesIntegerArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setIntegerArray
 * Signature: (IJ[J)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setIntegerArray
  (JNIEnv* env, jclass, jint entry, jlong time, jlongArray value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetIntegerArray(entry, CriticalJSpan<const jlong>{env, value}, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getIntegerArray
 * Signature: (I[J)[J
 */
JNIEXPORT jlongArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getIntegerArray
  (JNIEnv* env, jclass, jint entry, jlongArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsIntegerArray()) {
    return defaultValue;
  }
  return MakeJLongArray(env, val.GetIntegerArray());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultIntegerArray
 * Signature: (IJ[J)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultIntegerArray
  (JNIEnv* env, jclass, jint entry, jlong, jlongArray defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultIntegerArray(entry, CriticalJSpan<const jlong>{env, defaultValue});
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicFloatArray
 * Signature: (I[F)Ledu/wpi/first/networktables/TimestampedFloatArray;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicFloatArray
  (JNIEnv* env, jclass, jint subentry, jfloatArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicFloatArray(subentry, CriticalJSpan<const jfloat>{env, defaultValue}));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueFloatArray
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedFloatArray;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueFloatArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueFloatArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesFloatArray
 * Signature: (I)[[F
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesFloatArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesFloatArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setFloatArray
 * Signature: (IJ[F)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setFloatArray
  (JNIEnv* env, jclass, jint entry, jlong time, jfloatArray value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetFloatArray(entry, CriticalJSpan<const jfloat>{env, value}, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getFloatArray
 * Signature: (I[F)[F
 */
JNIEXPORT jfloatArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getFloatArray
  (JNIEnv* env, jclass, jint entry, jfloatArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsFloatArray()) {
    return defaultValue;
  }
  return MakeJFloatArray(env, val.GetFloatArray());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultFloatArray
 * Signature: (IJ[F)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultFloatArray
  (JNIEnv* env, jclass, jint entry, jlong, jfloatArray defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultFloatArray(entry, CriticalJSpan<const jfloat>{env, defaultValue});
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicDoubleArray
 * Signature: (I[D)Ledu/wpi/first/networktables/TimestampedDoubleArray;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicDoubleArray
  (JNIEnv* env, jclass, jint subentry, jdoubleArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicDoubleArray(subentry, CriticalJSpan<const jdouble>{env, defaultValue}));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueDoubleArray
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedDoubleArray;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueDoubleArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueDoubleArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesDoubleArray
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesDoubleArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesDoubleArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDoubleArray
 * Signature: (IJ[D)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDoubleArray
  (JNIEnv* env, jclass, jint entry, jlong time, jdoubleArray value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetDoubleArray(entry, CriticalJSpan<const jdouble>{env, value}, time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getDoubleArray
 * Signature: (I[D)[D
 */
JNIEXPORT jdoubleArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getDoubleArray
  (JNIEnv* env, jclass, jint entry, jdoubleArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsDoubleArray()) {
    return defaultValue;
  }
  return MakeJDoubleArray(env, val.GetDoubleArray());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultDoubleArray
 * Signature: (IJ[D)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultDoubleArray
  (JNIEnv* env, jclass, jint entry, jlong, jdoubleArray defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultDoubleArray(entry, CriticalJSpan<const jdouble>{env, defaultValue});
}


/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getAtomicStringArray
 * Signature: (I[Ljava/lang/Object;)Ledu/wpi/first/networktables/TimestampedStringArray;
 */
JNIEXPORT jobject JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getAtomicStringArray
  (JNIEnv* env, jclass, jint subentry, jobjectArray defaultValue)
{
  return MakeJObject(env, nt::GetAtomicStringArray(subentry, FromJavaStringArray(env, defaultValue)));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueStringArray
 * Signature: (I)[Ledu/wpi/first/networktables/TimestampedStringArray;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueStringArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObject(env, nt::ReadQueueStringArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    readQueueValuesStringArray
 * Signature: (I)[[Ljava/lang/Object;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_readQueueValuesStringArray
  (JNIEnv* env, jclass, jint subentry)
{
  return MakeJObjectArray(env, nt::ReadQueueValuesStringArray(subentry));
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setStringArray
 * Signature: (IJ[Ljava/lang/Object;)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setStringArray
  (JNIEnv* env, jclass, jint entry, jlong time, jobjectArray value)
{
  if (!value) {
    nullPointerEx.Throw(env, "value cannot be null");
    return false;
  }
  return nt::SetStringArray(entry, FromJavaStringArray(env, value), time);
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    getStringArray
 * Signature: (I[Ljava/lang/Object;)[Ljava/lang/Object;
 */
JNIEXPORT jobjectArray JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_getStringArray
  (JNIEnv* env, jclass, jint entry, jobjectArray defaultValue)
{
  auto val = nt::GetEntryValue(entry);
  if (!val || !val.IsStringArray()) {
    return defaultValue;
  }
  return MakeJStringArray(env, val.GetStringArray());
}

/*
 * Class:     edu_wpi_first_networktables_NetworkTablesJNI
 * Method:    setDefaultStringArray
 * Signature: (IJ[Ljava/lang/Object;)Z
 */
JNIEXPORT jboolean JNICALL
Java_edu_wpi_first_networktables_NetworkTablesJNI_setDefaultStringArray
  (JNIEnv* env, jclass, jint entry, jlong, jobjectArray defaultValue)
{
  if (!defaultValue) {
    nullPointerEx.Throw(env, "defaultValue cannot be null");
    return false;
  }
  return nt::SetDefaultStringArray(entry, FromJavaStringArray(env, defaultValue));
}


}  // extern "C"