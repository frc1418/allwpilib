// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "ntcore_c_types.h"

#include "Value_internal.h"
#include "ntcore_cpp.h"

using namespace nt;

template <typename T>
static inline std::span<const T> ConvertFromC(const T* arr, size_t size) {
  return {arr, size};
}

static inline std::string_view ConvertFromC(const char* arr, size_t size) {
  return {arr, size};
}

static std::vector<std::string> ConvertFromC(const NT_String* arr, size_t size) {
  std::vector<std::string> v;
  v.reserve(size);
  for (size_t i = 0; i < size; ++i) {
    v.emplace_back(ConvertFromC(arr[i]));
  }
  return v;
}


static void ConvertToC(const nt::TimestampedBoolean& in, NT_TimestampedBoolean* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = in.value;
}

static void ConvertToC(const nt::TimestampedInteger& in, NT_TimestampedInteger* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = in.value;
}

static void ConvertToC(const nt::TimestampedFloat& in, NT_TimestampedFloat* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = in.value;
}

static void ConvertToC(const nt::TimestampedDouble& in, NT_TimestampedDouble* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = in.value;
}

static void ConvertToC(const nt::TimestampedString& in, NT_TimestampedString* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<char>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedRaw& in, NT_TimestampedRaw* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<uint8_t>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedBooleanArray& in, NT_TimestampedBooleanArray* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<NT_Bool>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedIntegerArray& in, NT_TimestampedIntegerArray* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<int64_t>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedFloatArray& in, NT_TimestampedFloatArray* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<float>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedDoubleArray& in, NT_TimestampedDoubleArray* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<double>(in.value, &out->len);
}

static void ConvertToC(const nt::TimestampedStringArray& in, NT_TimestampedStringArray* out) {
  out->time = in.time;
  out->serverTime = in.serverTime;
  out->value = ConvertToC<struct NT_String>(in.value, &out->len);
}


extern "C" {

NT_Bool NT_SetBoolean(NT_Handle pubentry, int64_t time, NT_Bool value) {
  return nt::SetBoolean(pubentry, value, time);
}

NT_Bool NT_SetDefaultBoolean(NT_Handle pubentry, NT_Bool defaultValue) {
  return nt::SetDefaultBoolean(pubentry, defaultValue);
}

NT_Bool NT_GetBoolean(NT_Handle subentry, NT_Bool defaultValue) {
  return nt::GetBoolean(subentry, defaultValue);
}

void NT_GetAtomicBoolean(NT_Handle subentry, NT_Bool defaultValue, struct NT_TimestampedBoolean* value) {
  auto cppValue = nt::GetAtomicBoolean(subentry, defaultValue);
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedBoolean(struct NT_TimestampedBoolean* value) {
}

struct NT_TimestampedBoolean* NT_ReadQueueBoolean(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueBoolean(subentry);
  return ConvertToC<NT_TimestampedBoolean>(arr, len);
}

void NT_FreeQueueBoolean(struct NT_TimestampedBoolean* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedBoolean(&arr[i]);
  }
  std::free(arr);
}
NT_Bool* NT_ReadQueueValuesBoolean(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueValuesBoolean(subentry);
  return ConvertToC<NT_Bool>(arr, len);
}


NT_Bool NT_SetInteger(NT_Handle pubentry, int64_t time, int64_t value) {
  return nt::SetInteger(pubentry, value, time);
}

NT_Bool NT_SetDefaultInteger(NT_Handle pubentry, int64_t defaultValue) {
  return nt::SetDefaultInteger(pubentry, defaultValue);
}

int64_t NT_GetInteger(NT_Handle subentry, int64_t defaultValue) {
  return nt::GetInteger(subentry, defaultValue);
}

void NT_GetAtomicInteger(NT_Handle subentry, int64_t defaultValue, struct NT_TimestampedInteger* value) {
  auto cppValue = nt::GetAtomicInteger(subentry, defaultValue);
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedInteger(struct NT_TimestampedInteger* value) {
}

struct NT_TimestampedInteger* NT_ReadQueueInteger(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueInteger(subentry);
  return ConvertToC<NT_TimestampedInteger>(arr, len);
}

void NT_FreeQueueInteger(struct NT_TimestampedInteger* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedInteger(&arr[i]);
  }
  std::free(arr);
}
int64_t* NT_ReadQueueValuesInteger(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueValuesInteger(subentry);
  return ConvertToC<int64_t>(arr, len);
}


NT_Bool NT_SetFloat(NT_Handle pubentry, int64_t time, float value) {
  return nt::SetFloat(pubentry, value, time);
}

NT_Bool NT_SetDefaultFloat(NT_Handle pubentry, float defaultValue) {
  return nt::SetDefaultFloat(pubentry, defaultValue);
}

float NT_GetFloat(NT_Handle subentry, float defaultValue) {
  return nt::GetFloat(subentry, defaultValue);
}

void NT_GetAtomicFloat(NT_Handle subentry, float defaultValue, struct NT_TimestampedFloat* value) {
  auto cppValue = nt::GetAtomicFloat(subentry, defaultValue);
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedFloat(struct NT_TimestampedFloat* value) {
}

struct NT_TimestampedFloat* NT_ReadQueueFloat(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueFloat(subentry);
  return ConvertToC<NT_TimestampedFloat>(arr, len);
}

void NT_FreeQueueFloat(struct NT_TimestampedFloat* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedFloat(&arr[i]);
  }
  std::free(arr);
}
float* NT_ReadQueueValuesFloat(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueValuesFloat(subentry);
  return ConvertToC<float>(arr, len);
}


NT_Bool NT_SetDouble(NT_Handle pubentry, int64_t time, double value) {
  return nt::SetDouble(pubentry, value, time);
}

NT_Bool NT_SetDefaultDouble(NT_Handle pubentry, double defaultValue) {
  return nt::SetDefaultDouble(pubentry, defaultValue);
}

double NT_GetDouble(NT_Handle subentry, double defaultValue) {
  return nt::GetDouble(subentry, defaultValue);
}

void NT_GetAtomicDouble(NT_Handle subentry, double defaultValue, struct NT_TimestampedDouble* value) {
  auto cppValue = nt::GetAtomicDouble(subentry, defaultValue);
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedDouble(struct NT_TimestampedDouble* value) {
}

struct NT_TimestampedDouble* NT_ReadQueueDouble(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueDouble(subentry);
  return ConvertToC<NT_TimestampedDouble>(arr, len);
}

void NT_FreeQueueDouble(struct NT_TimestampedDouble* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedDouble(&arr[i]);
  }
  std::free(arr);
}
double* NT_ReadQueueValuesDouble(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueValuesDouble(subentry);
  return ConvertToC<double>(arr, len);
}


NT_Bool NT_SetString(NT_Handle pubentry, int64_t time, const char* value, size_t len) {
  return nt::SetString(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultString(NT_Handle pubentry, const char* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultString(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

char* NT_GetString(NT_Handle subentry, const char* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetString(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<char>(cppValue, len);
}

void NT_GetAtomicString(NT_Handle subentry, const char* defaultValue, size_t defaultValueLen, struct NT_TimestampedString* value) {
  auto cppValue = nt::GetAtomicString(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedString(struct NT_TimestampedString* value) {
  std::free(value->value);
}

struct NT_TimestampedString* NT_ReadQueueString(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueString(subentry);
  return ConvertToC<NT_TimestampedString>(arr, len);
}

void NT_FreeQueueString(struct NT_TimestampedString* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedString(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetRaw(NT_Handle pubentry, int64_t time, const uint8_t* value, size_t len) {
  return nt::SetRaw(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultRaw(NT_Handle pubentry, const uint8_t* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultRaw(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

uint8_t* NT_GetRaw(NT_Handle subentry, const uint8_t* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetRaw(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<uint8_t>(cppValue, len);
}

void NT_GetAtomicRaw(NT_Handle subentry, const uint8_t* defaultValue, size_t defaultValueLen, struct NT_TimestampedRaw* value) {
  auto cppValue = nt::GetAtomicRaw(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedRaw(struct NT_TimestampedRaw* value) {
  std::free(value->value);
}

struct NT_TimestampedRaw* NT_ReadQueueRaw(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueRaw(subentry);
  return ConvertToC<NT_TimestampedRaw>(arr, len);
}

void NT_FreeQueueRaw(struct NT_TimestampedRaw* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedRaw(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetBooleanArray(NT_Handle pubentry, int64_t time, const NT_Bool* value, size_t len) {
  return nt::SetBooleanArray(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultBooleanArray(NT_Handle pubentry, const NT_Bool* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultBooleanArray(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

NT_Bool* NT_GetBooleanArray(NT_Handle subentry, const NT_Bool* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetBooleanArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<NT_Bool>(cppValue, len);
}

void NT_GetAtomicBooleanArray(NT_Handle subentry, const NT_Bool* defaultValue, size_t defaultValueLen, struct NT_TimestampedBooleanArray* value) {
  auto cppValue = nt::GetAtomicBooleanArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedBooleanArray(struct NT_TimestampedBooleanArray* value) {
  std::free(value->value);
}

struct NT_TimestampedBooleanArray* NT_ReadQueueBooleanArray(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueBooleanArray(subentry);
  return ConvertToC<NT_TimestampedBooleanArray>(arr, len);
}

void NT_FreeQueueBooleanArray(struct NT_TimestampedBooleanArray* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedBooleanArray(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetIntegerArray(NT_Handle pubentry, int64_t time, const int64_t* value, size_t len) {
  return nt::SetIntegerArray(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultIntegerArray(NT_Handle pubentry, const int64_t* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultIntegerArray(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

int64_t* NT_GetIntegerArray(NT_Handle subentry, const int64_t* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetIntegerArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<int64_t>(cppValue, len);
}

void NT_GetAtomicIntegerArray(NT_Handle subentry, const int64_t* defaultValue, size_t defaultValueLen, struct NT_TimestampedIntegerArray* value) {
  auto cppValue = nt::GetAtomicIntegerArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedIntegerArray(struct NT_TimestampedIntegerArray* value) {
  std::free(value->value);
}

struct NT_TimestampedIntegerArray* NT_ReadQueueIntegerArray(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueIntegerArray(subentry);
  return ConvertToC<NT_TimestampedIntegerArray>(arr, len);
}

void NT_FreeQueueIntegerArray(struct NT_TimestampedIntegerArray* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedIntegerArray(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetFloatArray(NT_Handle pubentry, int64_t time, const float* value, size_t len) {
  return nt::SetFloatArray(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultFloatArray(NT_Handle pubentry, const float* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultFloatArray(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

float* NT_GetFloatArray(NT_Handle subentry, const float* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetFloatArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<float>(cppValue, len);
}

void NT_GetAtomicFloatArray(NT_Handle subentry, const float* defaultValue, size_t defaultValueLen, struct NT_TimestampedFloatArray* value) {
  auto cppValue = nt::GetAtomicFloatArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedFloatArray(struct NT_TimestampedFloatArray* value) {
  std::free(value->value);
}

struct NT_TimestampedFloatArray* NT_ReadQueueFloatArray(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueFloatArray(subentry);
  return ConvertToC<NT_TimestampedFloatArray>(arr, len);
}

void NT_FreeQueueFloatArray(struct NT_TimestampedFloatArray* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedFloatArray(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetDoubleArray(NT_Handle pubentry, int64_t time, const double* value, size_t len) {
  return nt::SetDoubleArray(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultDoubleArray(NT_Handle pubentry, const double* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultDoubleArray(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

double* NT_GetDoubleArray(NT_Handle subentry, const double* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetDoubleArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<double>(cppValue, len);
}

void NT_GetAtomicDoubleArray(NT_Handle subentry, const double* defaultValue, size_t defaultValueLen, struct NT_TimestampedDoubleArray* value) {
  auto cppValue = nt::GetAtomicDoubleArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedDoubleArray(struct NT_TimestampedDoubleArray* value) {
  std::free(value->value);
}

struct NT_TimestampedDoubleArray* NT_ReadQueueDoubleArray(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueDoubleArray(subentry);
  return ConvertToC<NT_TimestampedDoubleArray>(arr, len);
}

void NT_FreeQueueDoubleArray(struct NT_TimestampedDoubleArray* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedDoubleArray(&arr[i]);
  }
  std::free(arr);
}


NT_Bool NT_SetStringArray(NT_Handle pubentry, int64_t time, const struct NT_String* value, size_t len) {
  return nt::SetStringArray(pubentry, ConvertFromC(value, len), time);
}

NT_Bool NT_SetDefaultStringArray(NT_Handle pubentry, const struct NT_String* defaultValue, size_t defaultValueLen) {
  return nt::SetDefaultStringArray(pubentry, ConvertFromC(defaultValue, defaultValueLen));
}

struct NT_String* NT_GetStringArray(NT_Handle subentry, const struct NT_String* defaultValue, size_t defaultValueLen, size_t* len) {
  auto cppValue = nt::GetStringArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  return ConvertToC<struct NT_String>(cppValue, len);
}

void NT_GetAtomicStringArray(NT_Handle subentry, const struct NT_String* defaultValue, size_t defaultValueLen, struct NT_TimestampedStringArray* value) {
  auto cppValue = nt::GetAtomicStringArray(subentry, ConvertFromC(defaultValue, defaultValueLen));
  ConvertToC(cppValue, value);
}

void NT_DisposeTimestampedStringArray(struct NT_TimestampedStringArray* value) {
  NT_FreeStringArray(value->value, value->len);
}

struct NT_TimestampedStringArray* NT_ReadQueueStringArray(NT_Handle subentry, size_t* len) {
  auto arr = nt::ReadQueueStringArray(subentry);
  return ConvertToC<NT_TimestampedStringArray>(arr, len);
}

void NT_FreeQueueStringArray(struct NT_TimestampedStringArray* arr, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    NT_DisposeTimestampedStringArray(&arr[i]);
  }
  std::free(arr);
}


}  // extern "C"
