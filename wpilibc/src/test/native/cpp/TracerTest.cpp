// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <string>
#include <string_view>
#include <thread>

#include <gtest/gtest.h>
#include <networktables/DoubleTopic.h>
#include <networktables/NetworkTableInstance.h>

#include "frc/Tracer.h"
#include "frc/simulation/SimHooks.h"

TEST(TracerTest, TraceFunc) {
  frc::Tracer::ResetForTest();
  frc::Tracer::SetThreadName("TracerTest");

  frc::sim::PauseTiming();

  frc::Tracer::StartTrace("Test1");
  frc::Tracer::TraceFunc("Test2", []() { frc::sim::StepTiming(400_ms); });
  frc::sim::StepTiming(100_ms);
  frc::Tracer::EndTrace();

  auto test1Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/TracerTest/Test1");
  auto test2Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/TracerTest/Test1/Test2");

  EXPECT_NEAR(test1Entry.GetDouble(0.0), 500.0, 1.0);
  EXPECT_NEAR(test2Entry.GetDouble(0.0), 400.0, 1.0);

  frc::sim::ResumeTiming();
  frc::Tracer::ResetForTest();
}

TEST(TracerTest, ScopedTrace) {
  frc::Tracer::ResetForTest();
  frc::Tracer::SetThreadName("ScopedTracerTest");

  frc::sim::PauseTiming();

  frc::Tracer::StartTrace("Test3");
  {
    auto st = frc::Tracer::StartScopedTrace("Test4");
    frc::sim::StepTiming(400_ms);
  }
  frc::sim::StepTiming(100_ms);
  frc::Tracer::EndTrace();

  auto test3Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/ScopedTracerTest/Test3");
  auto test4Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/ScopedTracerTest/Test3/Test4");

  EXPECT_TRUE(test3Entry.Exists());
  EXPECT_TRUE(test4Entry.Exists());
  EXPECT_NEAR(test3Entry.GetDouble(0.0), 500.0, 1.0);
  EXPECT_NEAR(test4Entry.GetDouble(0.0), 400.0, 1.0);

  frc::sim::ResumeTiming();
  frc::Tracer::ResetForTest();
}

TEST(TracerTest, Threaded) {
  frc::Tracer::ResetForTest();
  frc::Tracer::SetThreadName("TracerTest");

  frc::sim::PauseTiming();

  // run a trace in the main thread, assert that the tracer ran
  {
    frc::Tracer::StartTrace("ThreadTest1");
    frc::sim::StepTiming(100_ms);
    frc::Tracer::EndTrace();

    auto test1Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
        "/Tracer/TracerTest/ThreadTest1");

    EXPECT_TRUE(test1Entry.Exists());
    EXPECT_NEAR(test1Entry.GetDouble(0.0), 100.0, 1.0);
  }

  // run a trace in a new thread, assert that the tracer ran
  // and that the output position and value are correct
  {
    std::thread thread([]() {
      frc::Tracer::SetThreadName("TestThread");
      frc::Tracer::StartTrace("ThreadTest1");
      frc::sim::StepTiming(400_ms);
      frc::Tracer::EndTrace();

      auto test1Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
        "/Tracer/TracerTest/ThreadTest1");

      EXPECT_TRUE(test1Entry.Exists());
      EXPECT_NEAR(test1Entry.GetDouble(0.0), 100.0, 1.0);

      auto test2Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
        "/Tracer/TestThread/ThreadTest1");

      EXPECT_TRUE(test2Entry.Exists());
      EXPECT_NEAR(test2Entry.GetDouble(0.0), 400.0, 1.0);
    });
    thread.join();

    auto test2Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/TestThread/ThreadTest1");

    EXPECT_TRUE(test2Entry.Exists());
    EXPECT_NEAR(test2Entry.GetDouble(0.0), 400.0, 1.0);
  }

  frc::sim::ResumeTiming();
  frc::Tracer::ResetForTest();
}

TEST(TracerTest, SingleThreaded) {
  frc::Tracer::ResetForTest();
  frc::Tracer::SetThreadName("TracerTest");

  frc::sim::PauseTiming();

  // start a trace in the main thread, assert that the tracer ran
  // and that the thread name is not in the trace path
  {
    frc::Tracer::EnableSingleThreadedMode();

    frc::Tracer::StartTrace("SingleThreadTest1");
    frc::sim::StepTiming(100_ms);
    frc::Tracer::EndTrace();

    auto test1Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
        "/Tracer/SingleThreadTest1");

    EXPECT_TRUE(test1Entry.Exists());
    EXPECT_NEAR(test1Entry.GetDouble(0.0), 100.0, 1.0);
  }

  // start a trace in a new thread after enabling single threaded mode,
  // this should disable the tracer on the new thread, assert that the tracer
  // did not run
  {
    std::thread thread([]() {
      std::string newThreadName = "TestThread";
      frc::Tracer::SetThreadName(newThreadName);
      frc::Tracer::StartTrace("SingleThreadTest1");
      frc::sim::StepTiming(400_ms);
      frc::Tracer::EndTrace();

      auto test2Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
        "/Tracer/" + newThreadName + "/SingleThreadTest1");

      EXPECT_FALSE(test2Entry.Exists());
    });
    thread.join();
  }

  frc::sim::ResumeTiming();
  frc::Tracer::ResetForTest();
}

TEST(TracerTest, Substitutor) {
  frc::Tracer::ResetForTest();
  frc::Tracer::SetThreadName("TracerTest");

  frc::sim::PauseTiming();

  {
    frc::Tracer::StartTrace("SubTest1");
    frc::Tracer::TraceFunc("SubTest2", []() { frc::sim::StepTiming(400_ms); });
    frc::sim::StepTiming(100_ms);
    frc::Tracer::EndTrace();
  }

  {
    frc::Tracer::SubstitutiveTracer sub("Sub");
    sub.SubIn();

    frc::Tracer::StartTrace("SubTest1");
    frc::Tracer::TraceFunc("SubTest2", []() { frc::sim::StepTiming(400_ms); });
    frc::sim::StepTiming(100_ms);
    frc::Tracer::EndTrace();

    sub.SubOut();
  }

  auto test1Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/TracerTest/SubTest1");
  auto test2Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/TracerTest/SubTest1/SubTest2");
  auto test3Entry =
      nt::NetworkTableInstance::GetDefault().GetEntry("/Tracer/Sub/SubTest1");
  auto test4Entry = nt::NetworkTableInstance::GetDefault().GetEntry(
      "/Tracer/Sub/SubTest1/SubTest2");

  EXPECT_TRUE(test1Entry.Exists());
  EXPECT_TRUE(test2Entry.Exists());
  EXPECT_TRUE(test3Entry.Exists());
  EXPECT_TRUE(test4Entry.Exists());
  EXPECT_NEAR(test1Entry.GetDouble(0.0), 500.0, 1.0);
  EXPECT_NEAR(test2Entry.GetDouble(0.0), 400.0, 1.0);
  EXPECT_NEAR(test3Entry.GetDouble(0.0), 500.0, 1.0);
  EXPECT_NEAR(test4Entry.GetDouble(0.0), 400.0, 1.0);

  frc::sim::ResumeTiming();
  frc::Tracer::ResetForTest();
}
