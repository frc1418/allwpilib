// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <wpi/MulticastServiceAnnouncer.h>
#include <wpi/MulticastServiceResolver.h>
#include <wpi/timestamp.h>

#include "gtest/gtest.h"

TEST(MulticastServiceAnnouncerTest, EmptyText) {
  const std::string_view serviceName = "TestServiceNoText";
  const std::string_view serviceType = "_wpinotxt";
  const int port = rand();
  wpi::MulticastServiceAnnouncer announcer(serviceName, serviceType, port);
  wpi::MulticastServiceResolver resolver(serviceType);

  if (announcer.HasImplementation() && resolver.HasImplementation()) {
    announcer.Start();
    resolver.Start();

    std::this_thread::sleep_for(std::chrono::seconds(1));
  }
}

TEST(MulticastServiceAnnouncerTest, SingleText) {
  const std::string_view serviceName = "TestServiceSingle";
  const std::string_view serviceType = "_wpitxt";
  const int port = rand();
  std::array<std::pair<std::string, std::string>, 1> txt = {
      std::make_pair("hello", "world")};
  wpi::MulticastServiceAnnouncer announcer(serviceName, serviceType, port, txt);
  wpi::MulticastServiceResolver resolver(serviceType);

  if (announcer.HasImplementation() && resolver.HasImplementation()) {
    announcer.Start();
    resolver.Start();

    std::this_thread::sleep_for(std::chrono::seconds(1));
  }
}
