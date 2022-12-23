// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "gtest/gtest.h"
#include "networktables/NetworkTableInstance.h"

class TimeSyncTest : public ::testing::Test {
 public:
  TimeSyncTest() : m_inst(nt::NetworkTableInstance::Create()) {}

  ~TimeSyncTest() override { nt::NetworkTableInstance::Destroy(m_inst); }

 protected:
  nt::NetworkTableInstance m_inst;
};

TEST_F(TimeSyncTest, TestLocal) {
  auto offset = m_inst.GetServerTimeOffset();
  ASSERT_TRUE(offset);
  ASSERT_EQ(0, *offset);
}

TEST_F(TimeSyncTest, TestServer) {
  m_inst.StartServer("timesynctest.json", "127.0.0.1", 0, 10030);
  auto offset = m_inst.GetServerTimeOffset();
  ASSERT_TRUE(offset);
  ASSERT_EQ(0, *offset);

  m_inst.StopServer();
  offset = m_inst.GetServerTimeOffset();
  ASSERT_TRUE(offset);
  ASSERT_EQ(0, *offset);
}

TEST_F(TimeSyncTest, TestClient3) {
  m_inst.StartClient3("client");
  auto offset = m_inst.GetServerTimeOffset();
  ASSERT_FALSE(offset);

  m_inst.StopClient();
  offset = m_inst.GetServerTimeOffset();
  ASSERT_TRUE(offset);
  ASSERT_EQ(0, *offset);
}

TEST_F(TimeSyncTest, TestClient4) {
  m_inst.StartClient4("client");
  auto offset = m_inst.GetServerTimeOffset();
  ASSERT_FALSE(offset);

  m_inst.StopClient();
  offset = m_inst.GetServerTimeOffset();
  ASSERT_TRUE(offset);
  ASSERT_EQ(0, *offset);
}
