/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "NotifyCallbackHelpers.h"

using namespace hal;

template <typename VectorType, typename CallbackType>
std::shared_ptr<VectorType> RegisterCallbackImpl(
    std::shared_ptr<VectorType> currentVector, const char* name,
    CallbackType callback, void* param, int32_t* newUid) {
  std::shared_ptr<VectorType> newCallbacks;
  if (currentVector == nullptr) {
    newCallbacks = std::make_shared<VectorType>(
        param, callback, reinterpret_cast<unsigned int*>(newUid));
  } else {
    newCallbacks = currentVector->emplace_back(
        param, callback, reinterpret_cast<unsigned int*>(newUid));
  }
  return newCallbacks;
}

template <typename VectorType, typename CallbackType>
std::shared_ptr<VectorType> CancelCallbackImpl(
    std::shared_ptr<VectorType> currentVector, int32_t uid) {
  // Create a copy of the callbacks to erase from
  auto newCallbacks = currentVector->erase(uid);
  return newCallbacks;
}

std::shared_ptr<NotifyListenerVector> RegisterCallback(
    std::shared_ptr<NotifyListenerVector> currentVector, const char* name,
    HAL_NotifyCallback callback, void* param, int32_t* newUid) {
  return RegisterCallbackImpl<NotifyListenerVector, HAL_NotifyCallback>(
      currentVector, name, callback, param, newUid);
}

std::shared_ptr<NotifyListenerVector> CancelCallback(
    std::shared_ptr<NotifyListenerVector> currentVector, int32_t uid) {
  return CancelCallbackImpl<NotifyListenerVector, HAL_NotifyCallback>(
      currentVector, uid);
}

void InvokeCallback(std::shared_ptr<NotifyListenerVector> currentVector,
                    const char* name, const HAL_Value* value) {
  // Return if no callbacks are assigned
  if (currentVector == nullptr) return;
  // Get a copy of the shared_ptr, then iterate and callback listeners
  auto newCallbacks = currentVector;
  for (size_t i = 0; i < newCallbacks->size(); ++i) {
    if (!(*newCallbacks)[i]) continue;  // removed
    auto listener = (*newCallbacks)[i];
    listener.callback(name, listener.param, value);
  }
}

std::shared_ptr<BufferListenerVector> RegisterCallback(
    std::shared_ptr<BufferListenerVector> currentVector, const char* name,
    HAL_BufferCallback callback, void* param, int32_t* newUid) {
  return RegisterCallbackImpl<BufferListenerVector, HAL_BufferCallback>(
      currentVector, name, callback, param, newUid);
}

std::shared_ptr<BufferListenerVector> CancelCallback(
    std::shared_ptr<BufferListenerVector> currentVector, int32_t uid) {
  return CancelCallbackImpl<BufferListenerVector, HAL_BufferCallback>(
      currentVector, uid);
}

void InvokeCallback(std::shared_ptr<BufferListenerVector> currentVector,
                    const char* name, uint8_t* buffer, int32_t count) {
  // Return if no callbacks are assigned
  if (currentVector == nullptr) return;
  // Get a copy of the shared_ptr, then iterate and callback listeners
  auto newCallbacks = currentVector;
  for (size_t i = 0; i < newCallbacks->size(); ++i) {
    if (!(*newCallbacks)[i]) continue;  // removed
    auto listener = (*newCallbacks)[i];
    listener.callback(name, listener.param, buffer, count);
  }
}
