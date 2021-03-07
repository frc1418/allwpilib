// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <string>

#include <ntcore_cpp.h>
#include <wpi/SafeThread.h>

namespace wpi {
template <typename T>
class SmallVectorImpl;
}  // namespace wpi

namespace glass {

class NetworkTablesSettings {
 public:
  explicit NetworkTablesSettings(
      NT_Inst inst = nt::GetDefaultInstance(),
      const char* storageName = "NetworkTables Settings");

  void Update();
  bool Display();

 private:
  bool m_restart = true;
  int* m_pMode;
  std::string* m_pIniName;
  std::string* m_pServerTeam;
  std::string* m_pListenAddress;

  class Thread : public wpi::SafeThread {
   public:
    explicit Thread(NT_Inst inst) : m_inst{inst} {}

    void Main() override;

    NT_Inst m_inst;
    bool m_restart = false;
    int m_mode;
    std::string m_iniName;
    std::string m_serverTeam;
    std::string m_listenAddress;
  };
  wpi::SafeThreadOwner<Thread> m_thread;
};

}  // namespace glass
