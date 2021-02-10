// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <frc/AnalogInput.h>
#include <frc/AnalogOutput.h>
#include <frc/DigitalOutput.h>
#include <frc/Jaguar.h>

#include "TestBench.h"
#include "frc/DMA.h"
#include "frc/DMASample.h"
#include "gtest/gtest.h"

using namespace frc;

static const double kDelayTime = 0.01;

class DMATest : public testing::Test {
 protected:
  DMA* m_dma;
  AnalogInput* m_analogInput;
  AnalogOutput* m_analogOutput;
  DigitalOutput* m_manualTrigger;
  PWMSpeedController* m_pwm;

  void SetUp() override {
    m_analogOutput = new AnalogOutput(TestBench::kAnalogOutputChannel);
    m_analogInput = new AnalogInput(TestBench::kFakeAnalogOutputChannel);
    m_manualTrigger = new DigitalOutput(TestBench::kLoop1InputChannel);
    m_pwm = new Jaguar(TestBench::kFakePwmOutput);
    m_dma = new DMA();

    m_dma->AddAnalogInput(m_analogInput);
    m_dma->SetExternalTrigger(m_manualTrigger, false, true);
    m_manualTrigger->Set(true);
  }

  void TearDown() override {
    delete m_pwm;
    delete m_manualTrigger;
    delete m_analogOutput;
    delete m_analogInput;
    delete m_dma;
  }
};

TEST_F(DMATest, PausingWorks) {
  m_dma->Start(1024);
  m_dma->SetPause(true);
  m_manualTrigger->Set(false);

  frc::DMASample sample;
  int32_t remaining = 0;
  int32_t status = 0;

  auto timedOut = sample.Update(m_dma, 5_ms, &remaining, &status);

  ASSERT_EQ(DMASample::DMAReadStatus::kTimeout, timedOut);
}

TEST_F(DMATest, RemovingTriggersWorks) {
  m_dma->ClearExternalTriggers();
  m_dma->Start(1024);
  m_manualTrigger->Set(false);

  frc::DMASample sample;
  int32_t remaining = 0;
  int32_t status = 0;

  auto timedOut = sample.Update(m_dma, 5_ms, &remaining, &status);

  ASSERT_EQ(DMASample::DMAReadStatus::kTimeout, timedOut);
}

TEST_F(DMATest, ManualTriggerOnlyHappensOnce) {
  m_dma->Start(1024);
  m_manualTrigger->Set(false);

  frc::DMASample sample;
  int32_t remaining = 0;
  int32_t status = 0;

  auto timedOut = sample.Update(m_dma, 5_ms, &remaining, &status);

  ASSERT_EQ(DMASample::DMAReadStatus::kOk, timedOut);
  ASSERT_EQ(0, remaining);
  timedOut = sample.Update(m_dma, 5_ms, &remaining, &status);
  ASSERT_EQ(DMASample::DMAReadStatus::kTimeout, timedOut);
}

TEST_F(DMATest, AnalogIndividualTriggers) {
  m_dma->Start(1024);
  for (double i = 0; i < 5; i += 0.5) {
    frc::DMASample sample;
    int32_t remaining = 0;
    int32_t status = 0;

    m_analogOutput->SetVoltage(i);
    Wait(kDelayTime);
    m_manualTrigger->Set(false);
    auto timedOut = sample.Update(m_dma, 1_ms, &remaining, &status);
    m_manualTrigger->Set(true);
    ASSERT_EQ(DMASample::DMAReadStatus::kOk, timedOut);
    ASSERT_EQ(0, status);
    ASSERT_EQ(0, remaining);
    ASSERT_DOUBLE_EQ(m_analogInput->GetVoltage(),
                     sample.GetAnalogInputVoltage(m_analogInput, &status));
  }
}

TEST_F(DMATest, AnalogMultipleTriggers) {
  m_dma->Start(1024);
  std::vector<double> values;
  for (double i = 0; i < 5; i += 0.5) {
    values.push_back(i);
    m_analogOutput->SetVoltage(i);
    Wait(kDelayTime);
    m_manualTrigger->Set(false);
    Wait(kDelayTime);
    m_manualTrigger->Set(true);
  }

  for (double i = 0; i < 5; i += 0.5) {
    frc::DMASample sample;
    int32_t remaining = 0;
    int32_t status = 0;
    auto timedOut = sample.Update(m_dma, 1_ms, &remaining, &status);
    ASSERT_EQ(DMASample::DMAReadStatus::kOk, timedOut);
    ASSERT_EQ(0, status);
    ASSERT_EQ(values.size() - i - 1, remaining);
    ASSERT_DOUBLE_EQ(m_analogInput->GetVoltage(),
                     sample.GetAnalogInputVoltage(m_analogInput, &status));
  }
}

TEST_F(DMATest, TimedTriggers) {
  m_dma->SetTimedTrigger(10_ms);
  m_dma->Start(1024);
  Wait(kDelayTime);
  m_dma->SetPause(true);

  frc::DMASample sample;
  int32_t remaining = 0;
  int32_t status = 0;

  auto timedOut = sample.Update(m_dma, 1_ms, &remaining, &status);
  ASSERT_EQ(DMASample::DMAReadStatus::kOk, timedOut);
  ASSERT_EQ(0, status);
  ASSERT_GT(remaining, 5);
  ASSERT_EQ(DMASample::DMAReadStatus::kTimeout, timedOut);
}

TEST_F(DMATest, PWMTimedTriggers) {
  m_dma->ClearExternalTriggers();
  m_dma->SetPwmEdgeTrigger(m_pwm, true, false);
  m_dma->Start(1024);
  Wait(kDelayTime);
  m_dma->SetPause(true);

  frc::DMASample sample;
  int32_t remaining = 0;
  int32_t status = 0;

  auto timedOut = sample.Update(m_dma, 1_ms, &remaining, &status);
  ASSERT_EQ(DMASample::DMAReadStatus::kOk, timedOut);
  ASSERT_EQ(0, status);
  ASSERT_GT(remaining, 5);
  ASSERT_EQ(DMASample::DMAReadStatus::kTimeout, timedOut);
}
