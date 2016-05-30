/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#ifndef HALUTIL_H
#define HALUTIL_H

#include <stdint.h>

#include <jni.h>

extern JavaVM *jvm;

void ReportError(JNIEnv *env, int32_t status, int32_t minRange, int32_t maxRange, 
                 int32_t requestedValue, bool do_throw = true);

inline bool CheckStatus(JNIEnv *env, int32_t status, bool do_throw = true) {
  if (status != 0) ReportError(env, status, 0, 0, 0, do_throw);
  return status == 0;
}

inline bool CheckStatusRange(JNIEnv *env, int32_t status, int32_t minRange, 
                             int32_t maxRange, int32_t requestedValue, 
                             bool do_throw = true) {
  if (status != 0) ReportError(env, status, minRange, maxRange, requestedValue, 
                               do_throw);
  return status == 0;
}

void ReportCANError(JNIEnv *env, int32_t status, int32_t message_id);

inline bool CheckCANStatus(JNIEnv *env, int32_t status, int32_t message_id) {
  if (status != 0) ReportCANError(env, status, message_id);
  return status == 0;
}

void ThrowIllegalArgumentException(JNIEnv *env, const char *msg);
void ThrowBoundaryException(JNIEnv *env, double value, double lower,
                            double upper);
                            
jobject CreatePWMConfigDataResult(JNIEnv *env, int32_t maxPwm,
                  int32_t deadbandMaxPwm, int32_t centerPwm,
                  int32_t deadbandMinPwm, int32_t minPwm);

#endif  // HALUTIL_H
