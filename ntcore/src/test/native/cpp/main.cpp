// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <climits>

#include "gmock/gmock.h"
#include "ntcore.h"

int main(int argc, char** argv) {
  nt::AddLogger(
      nt::GetDefaultInstance(),
      [](const nt::LogMessage& msg) {
        std::fputs(msg.message.c_str(), stderr);
        std::fputc('\n', stderr);
      },
      0, UINT_MAX);
  ::testing::InitGoogleMock(&argc, argv);
  int ret = RUN_ALL_TESTS();
  return ret;
}

extern "C" {
void __ubsan_on_report(void) {
  FAIL() << "Encountered an undefined behavior sanitizer error";
}
void __asan_on_error(void) {
  FAIL() << "Encountered an address sanitizer error";
}
void __tsan_on_report(void) {
  FAIL() << "Encountered a thread sanitizer error";
}
}  // extern "C"
