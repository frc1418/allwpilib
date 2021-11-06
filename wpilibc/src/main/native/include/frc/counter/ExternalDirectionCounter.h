// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <memory>

#include <hal/Types.h>
#include <wpi/sendable/Sendable.h>
#include <wpi/sendable/SendableHelper.h>

#include "EdgeConfiguration.h"

namespace frc {
class DigitalSource;

class ExternalDirectionCounter
    : public wpi::Sendable,
      public wpi::SendableHelper<ExternalDirectionCounter> {
 public:
  ExternalDirectionCounter(DigitalSource& countSource,
                           DigitalSource& directionSource);
  ExternalDirectionCounter(std::shared_ptr<DigitalSource> countSource,
                           std::shared_ptr<DigitalSource> directionSource);

  ~ExternalDirectionCounter() override;

  ExternalDirectionCounter(ExternalDirectionCounter&&) = default;
  ExternalDirectionCounter& operator=(ExternalDirectionCounter&&) = default;

  int GetCount() const;

  void SetReverseDirection(bool reverseDirection);
  void Reset();

  void SetEdgeConfiguration(EdgeConfiguration configuration);

 protected:
  void InitSendable(wpi::SendableBuilder& builder) override;

 private:
  std::shared_ptr<DigitalSource> m_countSource;
  std::shared_ptr<DigitalSource> m_directionSource;
  hal::Handle<HAL_CounterHandle> m_handle;
  int32_t m_index = 0;
};
}  // namespace frc
