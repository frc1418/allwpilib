/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <stdint.h>

#include <memory>
#include <vector>

#include "HAL/Handles.h"
#include "HAL/cpp/priority_mutex.h"
#include "handles/HandlesInternal.h"

namespace hal {

/**
 * The UnlimitedHandleResource class is a way to track handles. This version
 * allows an unlimted number of handles that are allocated sequentially. When
 * possible, indices are reused to save memory usage and keep the array length
 * down.
 * However, automatic array management has not been implemented, but might be in
 * the future.
 * Because we have to loop through the allocator, we must use a global mutex.

 * @tparam THandle The Handle Type (Must be typedefed from HalHandle)
 * @tparam TStruct The struct type held by this resource
 * @tparam enumValue The type value stored in the handle
 *
 */
template <typename THandle, typename TStruct, HalHandleEnum enumValue>
class UnlimitedHandleResource {
  friend class UnlimitedHandleResourceTest;

 public:
  UnlimitedHandleResource(const UnlimitedHandleResource&) = delete;
  UnlimitedHandleResource operator=(const UnlimitedHandleResource&) = delete;
  UnlimitedHandleResource() = default;
  THandle Allocate(std::shared_ptr<TStruct> structure);
  std::shared_ptr<TStruct> Get(THandle handle);
  void Free(THandle handle);

 private:
  std::vector<std::shared_ptr<TStruct>> m_structures;
  priority_mutex m_handleMutex;
};

template <typename THandle, typename TStruct, HalHandleEnum enumValue>
THandle UnlimitedHandleResource<THandle, TStruct, enumValue>::Allocate(
    std::shared_ptr<TStruct> structure) {
  std::lock_guard<priority_mutex> sync(m_handleMutex);
  size_t i;
  for (i = 0; i < m_structures.size(); i++) {
    if (m_structures[i] == nullptr) {
      m_structures[i] = structure;
      return (THandle)createHandle(i, enumValue);
    }
  }
  if (i >= INT16_MAX) return HAL_HANDLE_OUT_OF_HANDLES;

  m_structures.push_back(structure);
  return (THandle)createHandle(static_cast<int16_t>(i), enumValue);
}

template <typename THandle, typename TStruct, HalHandleEnum enumValue>
std::shared_ptr<TStruct>
UnlimitedHandleResource<THandle, TStruct, enumValue>::Get(THandle handle) {
  int16_t index = getHandleTypedIndex(handle, enumValue);
  std::lock_guard<priority_mutex> sync(m_handleMutex);
  if (index < 0 || index >= static_cast<int16_t>(m_structures.size()))
    return nullptr;
  return m_structures[index];
}

template <typename THandle, typename TStruct, HalHandleEnum enumValue>
void UnlimitedHandleResource<THandle, TStruct, enumValue>::Free(
    THandle handle) {
  int16_t index = getHandleTypedIndex(handle, enumValue);
  std::lock_guard<priority_mutex> sync(m_handleMutex);
  if (index < 0 || index >= static_cast<int16_t>(m_structures.size())) return;
  m_structures[index].reset();
}
}
