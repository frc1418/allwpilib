// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "ntcore_cpp_types.h"

#include "Handle.h"
#include "InstanceImpl.h"

namespace {
template <nt::ValidType T>
struct ValuesType {
  using Vector =
      std::vector<typename nt::TypeInfo<std::remove_cvref_t<T>>::Value>;
};

template <>
struct ValuesType<bool> {
  using Vector = std::vector<int>;
};
}  // namespace

namespace nt {

template <ValidType T>
static inline bool Set(NT_Handle pubentry, typename TypeInfo<T>::View value,
                       int64_t time) {
  if (auto ii = InstanceImpl::Get(Handle{pubentry}.GetInst())) {
    return ii->localStorage.SetEntryValue(
        pubentry, MakeValue<T>(value, time == 0 ? Now() : time));
  } else {
    return {};
  }
}

template <ValidType T>
static inline bool SetDefault(NT_Handle pubentry,
                              typename TypeInfo<T>::View defaultValue) {
  if (auto ii = InstanceImpl::Get(Handle{pubentry}.GetInst())) {
    return ii->localStorage.SetDefaultEntryValue(pubentry,
                                                 MakeValue<T>(defaultValue, 1));
  } else {
    return {};
  }
}

template <ValidType T>
static inline Timestamped<typename TypeInfo<T>::Value> GetAtomic(
    NT_Handle subentry, typename TypeInfo<T>::View defaultValue) {
  if (auto ii = InstanceImpl::Get(Handle{subentry}.GetInst())) {
    return ii->localStorage.GetAtomic<T>(subentry, defaultValue);
  } else {
    return {};
  }
}

template <ValidType T>
inline Timestamped<typename TypeInfo<T>::SmallRet> GetAtomic(
    NT_Handle subentry,
    wpi::SmallVectorImpl<typename TypeInfo<T>::SmallElem>& buf,
    typename TypeInfo<T>::View defaultValue) {
  if (auto ii = InstanceImpl::Get(Handle{subentry}.GetInst())) {
    return ii->localStorage.GetAtomic<T>(subentry, buf, defaultValue);
  } else {
    return {};
  }
}

template <typename T>
static inline std::vector<Timestamped<typename TypeInfo<T>::Value>> ReadQueue(
    NT_Handle subentry) {
  if (auto ii = InstanceImpl::Get(Handle{subentry}.GetInst())) {
    return ii->localStorage.ReadQueue<T>(subentry);
  } else {
    return {};
  }
}

template <typename T>
static inline typename ValuesType<T>::Vector ReadQueueValues(
    NT_Handle subentry) {
  typename ValuesType<T>::Vector rv;
  auto arr = ReadQueue<T>(subentry);
  rv.reserve(arr.size());
  for (auto&& elem : arr) {
    rv.emplace_back(std::move(elem.value));
  }
  return rv;
}

bool SetBoolean(NT_Handle pubentry, bool value, int64_t time) {
  return Set<bool>(pubentry, value, time);
}

bool SetDefaultBoolean(NT_Handle pubentry, bool defaultValue) {
  return SetDefault<bool>(pubentry, defaultValue);
}

bool GetBoolean(NT_Handle subentry, bool defaultValue) {
  return GetAtomic<bool>(subentry, defaultValue).value;
}

TimestampedBoolean GetAtomicBoolean(
    NT_Handle subentry, bool defaultValue) {
  return GetAtomic<bool>(subentry, defaultValue);
}

std::vector<TimestampedBoolean> ReadQueueBoolean(NT_Handle subentry) {
  return ReadQueue<bool>(subentry);
}

std::vector<int> ReadQueueValuesBoolean(NT_Handle subentry) {
  return ReadQueueValues<bool>(subentry);
}


bool SetInteger(NT_Handle pubentry, int64_t value, int64_t time) {
  return Set<int64_t>(pubentry, value, time);
}

bool SetDefaultInteger(NT_Handle pubentry, int64_t defaultValue) {
  return SetDefault<int64_t>(pubentry, defaultValue);
}

int64_t GetInteger(NT_Handle subentry, int64_t defaultValue) {
  return GetAtomic<int64_t>(subentry, defaultValue).value;
}

TimestampedInteger GetAtomicInteger(
    NT_Handle subentry, int64_t defaultValue) {
  return GetAtomic<int64_t>(subentry, defaultValue);
}

std::vector<TimestampedInteger> ReadQueueInteger(NT_Handle subentry) {
  return ReadQueue<int64_t>(subentry);
}

std::vector<int64_t> ReadQueueValuesInteger(NT_Handle subentry) {
  return ReadQueueValues<int64_t>(subentry);
}


bool SetFloat(NT_Handle pubentry, float value, int64_t time) {
  return Set<float>(pubentry, value, time);
}

bool SetDefaultFloat(NT_Handle pubentry, float defaultValue) {
  return SetDefault<float>(pubentry, defaultValue);
}

float GetFloat(NT_Handle subentry, float defaultValue) {
  return GetAtomic<float>(subentry, defaultValue).value;
}

TimestampedFloat GetAtomicFloat(
    NT_Handle subentry, float defaultValue) {
  return GetAtomic<float>(subentry, defaultValue);
}

std::vector<TimestampedFloat> ReadQueueFloat(NT_Handle subentry) {
  return ReadQueue<float>(subentry);
}

std::vector<float> ReadQueueValuesFloat(NT_Handle subentry) {
  return ReadQueueValues<float>(subentry);
}


bool SetDouble(NT_Handle pubentry, double value, int64_t time) {
  return Set<double>(pubentry, value, time);
}

bool SetDefaultDouble(NT_Handle pubentry, double defaultValue) {
  return SetDefault<double>(pubentry, defaultValue);
}

double GetDouble(NT_Handle subentry, double defaultValue) {
  return GetAtomic<double>(subentry, defaultValue).value;
}

TimestampedDouble GetAtomicDouble(
    NT_Handle subentry, double defaultValue) {
  return GetAtomic<double>(subentry, defaultValue);
}

std::vector<TimestampedDouble> ReadQueueDouble(NT_Handle subentry) {
  return ReadQueue<double>(subentry);
}

std::vector<double> ReadQueueValuesDouble(NT_Handle subentry) {
  return ReadQueueValues<double>(subentry);
}


bool SetString(NT_Handle pubentry, std::string_view value, int64_t time) {
  return Set<std::string>(pubentry, value, time);
}

bool SetDefaultString(NT_Handle pubentry, std::string_view defaultValue) {
  return SetDefault<std::string>(pubentry, defaultValue);
}

std::string GetString(NT_Handle subentry, std::string_view defaultValue) {
  return GetAtomic<std::string>(subentry, defaultValue).value;
}

TimestampedString GetAtomicString(
    NT_Handle subentry, std::string_view defaultValue) {
  return GetAtomic<std::string>(subentry, defaultValue);
}

std::vector<TimestampedString> ReadQueueString(NT_Handle subentry) {
  return ReadQueue<std::string>(subentry);
}

std::vector<std::string> ReadQueueValuesString(NT_Handle subentry) {
  return ReadQueueValues<std::string>(subentry);
}

std::string_view GetString(
    NT_Handle subentry,
    wpi::SmallVectorImpl<char>& buf,
    std::string_view defaultValue) {
  return GetAtomic<std::string>(subentry, buf, defaultValue).value;
}

TimestampedStringView GetAtomicString(
    NT_Handle subentry,
    wpi::SmallVectorImpl<char>& buf,
    std::string_view defaultValue) {
  return GetAtomic<std::string>(subentry, buf, defaultValue);
}


bool SetRaw(NT_Handle pubentry, std::span<const uint8_t> value, int64_t time) {
  return Set<uint8_t[]>(pubentry, value, time);
}

bool SetDefaultRaw(NT_Handle pubentry, std::span<const uint8_t> defaultValue) {
  return SetDefault<uint8_t[]>(pubentry, defaultValue);
}

std::vector<uint8_t> GetRaw(NT_Handle subentry, std::span<const uint8_t> defaultValue) {
  return GetAtomic<uint8_t[]>(subentry, defaultValue).value;
}

TimestampedRaw GetAtomicRaw(
    NT_Handle subentry, std::span<const uint8_t> defaultValue) {
  return GetAtomic<uint8_t[]>(subentry, defaultValue);
}

std::vector<TimestampedRaw> ReadQueueRaw(NT_Handle subentry) {
  return ReadQueue<uint8_t[]>(subentry);
}

std::vector<std::vector<uint8_t>> ReadQueueValuesRaw(NT_Handle subentry) {
  return ReadQueueValues<uint8_t[]>(subentry);
}

std::span<uint8_t> GetRaw(
    NT_Handle subentry,
    wpi::SmallVectorImpl<uint8_t>& buf,
    std::span<const uint8_t> defaultValue) {
  return GetAtomic<uint8_t[]>(subentry, buf, defaultValue).value;
}

TimestampedRawView GetAtomicRaw(
    NT_Handle subentry,
    wpi::SmallVectorImpl<uint8_t>& buf,
    std::span<const uint8_t> defaultValue) {
  return GetAtomic<uint8_t[]>(subentry, buf, defaultValue);
}


bool SetBooleanArray(NT_Handle pubentry, std::span<const int> value, int64_t time) {
  return Set<bool[]>(pubentry, value, time);
}

bool SetDefaultBooleanArray(NT_Handle pubentry, std::span<const int> defaultValue) {
  return SetDefault<bool[]>(pubentry, defaultValue);
}

std::vector<int> GetBooleanArray(NT_Handle subentry, std::span<const int> defaultValue) {
  return GetAtomic<bool[]>(subentry, defaultValue).value;
}

TimestampedBooleanArray GetAtomicBooleanArray(
    NT_Handle subentry, std::span<const int> defaultValue) {
  return GetAtomic<bool[]>(subentry, defaultValue);
}

std::vector<TimestampedBooleanArray> ReadQueueBooleanArray(NT_Handle subentry) {
  return ReadQueue<bool[]>(subentry);
}

std::vector<std::vector<int>> ReadQueueValuesBooleanArray(NT_Handle subentry) {
  return ReadQueueValues<bool[]>(subentry);
}

std::span<int> GetBooleanArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<int>& buf,
    std::span<const int> defaultValue) {
  return GetAtomic<bool[]>(subentry, buf, defaultValue).value;
}

TimestampedBooleanArrayView GetAtomicBooleanArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<int>& buf,
    std::span<const int> defaultValue) {
  return GetAtomic<bool[]>(subentry, buf, defaultValue);
}


bool SetIntegerArray(NT_Handle pubentry, std::span<const int64_t> value, int64_t time) {
  return Set<int64_t[]>(pubentry, value, time);
}

bool SetDefaultIntegerArray(NT_Handle pubentry, std::span<const int64_t> defaultValue) {
  return SetDefault<int64_t[]>(pubentry, defaultValue);
}

std::vector<int64_t> GetIntegerArray(NT_Handle subentry, std::span<const int64_t> defaultValue) {
  return GetAtomic<int64_t[]>(subentry, defaultValue).value;
}

TimestampedIntegerArray GetAtomicIntegerArray(
    NT_Handle subentry, std::span<const int64_t> defaultValue) {
  return GetAtomic<int64_t[]>(subentry, defaultValue);
}

std::vector<TimestampedIntegerArray> ReadQueueIntegerArray(NT_Handle subentry) {
  return ReadQueue<int64_t[]>(subentry);
}

std::vector<std::vector<int64_t>> ReadQueueValuesIntegerArray(NT_Handle subentry) {
  return ReadQueueValues<int64_t[]>(subentry);
}

std::span<int64_t> GetIntegerArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<int64_t>& buf,
    std::span<const int64_t> defaultValue) {
  return GetAtomic<int64_t[]>(subentry, buf, defaultValue).value;
}

TimestampedIntegerArrayView GetAtomicIntegerArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<int64_t>& buf,
    std::span<const int64_t> defaultValue) {
  return GetAtomic<int64_t[]>(subentry, buf, defaultValue);
}


bool SetFloatArray(NT_Handle pubentry, std::span<const float> value, int64_t time) {
  return Set<float[]>(pubentry, value, time);
}

bool SetDefaultFloatArray(NT_Handle pubentry, std::span<const float> defaultValue) {
  return SetDefault<float[]>(pubentry, defaultValue);
}

std::vector<float> GetFloatArray(NT_Handle subentry, std::span<const float> defaultValue) {
  return GetAtomic<float[]>(subentry, defaultValue).value;
}

TimestampedFloatArray GetAtomicFloatArray(
    NT_Handle subentry, std::span<const float> defaultValue) {
  return GetAtomic<float[]>(subentry, defaultValue);
}

std::vector<TimestampedFloatArray> ReadQueueFloatArray(NT_Handle subentry) {
  return ReadQueue<float[]>(subentry);
}

std::vector<std::vector<float>> ReadQueueValuesFloatArray(NT_Handle subentry) {
  return ReadQueueValues<float[]>(subentry);
}

std::span<float> GetFloatArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<float>& buf,
    std::span<const float> defaultValue) {
  return GetAtomic<float[]>(subentry, buf, defaultValue).value;
}

TimestampedFloatArrayView GetAtomicFloatArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<float>& buf,
    std::span<const float> defaultValue) {
  return GetAtomic<float[]>(subentry, buf, defaultValue);
}


bool SetDoubleArray(NT_Handle pubentry, std::span<const double> value, int64_t time) {
  return Set<double[]>(pubentry, value, time);
}

bool SetDefaultDoubleArray(NT_Handle pubentry, std::span<const double> defaultValue) {
  return SetDefault<double[]>(pubentry, defaultValue);
}

std::vector<double> GetDoubleArray(NT_Handle subentry, std::span<const double> defaultValue) {
  return GetAtomic<double[]>(subentry, defaultValue).value;
}

TimestampedDoubleArray GetAtomicDoubleArray(
    NT_Handle subentry, std::span<const double> defaultValue) {
  return GetAtomic<double[]>(subentry, defaultValue);
}

std::vector<TimestampedDoubleArray> ReadQueueDoubleArray(NT_Handle subentry) {
  return ReadQueue<double[]>(subentry);
}

std::vector<std::vector<double>> ReadQueueValuesDoubleArray(NT_Handle subentry) {
  return ReadQueueValues<double[]>(subentry);
}

std::span<double> GetDoubleArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<double>& buf,
    std::span<const double> defaultValue) {
  return GetAtomic<double[]>(subentry, buf, defaultValue).value;
}

TimestampedDoubleArrayView GetAtomicDoubleArray(
    NT_Handle subentry,
    wpi::SmallVectorImpl<double>& buf,
    std::span<const double> defaultValue) {
  return GetAtomic<double[]>(subentry, buf, defaultValue);
}


bool SetStringArray(NT_Handle pubentry, std::span<const std::string> value, int64_t time) {
  return Set<std::string[]>(pubentry, value, time);
}

bool SetDefaultStringArray(NT_Handle pubentry, std::span<const std::string> defaultValue) {
  return SetDefault<std::string[]>(pubentry, defaultValue);
}

std::vector<std::string> GetStringArray(NT_Handle subentry, std::span<const std::string> defaultValue) {
  return GetAtomic<std::string[]>(subentry, defaultValue).value;
}

TimestampedStringArray GetAtomicStringArray(
    NT_Handle subentry, std::span<const std::string> defaultValue) {
  return GetAtomic<std::string[]>(subentry, defaultValue);
}

std::vector<TimestampedStringArray> ReadQueueStringArray(NT_Handle subentry) {
  return ReadQueue<std::string[]>(subentry);
}

std::vector<std::vector<std::string>> ReadQueueValuesStringArray(NT_Handle subentry) {
  return ReadQueueValues<std::string[]>(subentry);
}


}  // namespace nt
