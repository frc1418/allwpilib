// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "hal/DriverStation.h"

#ifdef __APPLE__
#include <pthread.h>
#endif

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>

#include <fmt/format.h>
#include <wpi/condition_variable.h>
#include <wpi/mutex.h>

#include "HALInitializer.h"
#include "hal/cpp/fpga_clock.h"
#include "hal/simulation/MockHooks.h"
#include "mockdata/DriverStationDataInternal.h"

static wpi::mutex msgMutex;
//static std::atomic_bool isFinalized{false};
static std::atomic<HALSIM_SendErrorHandler> sendErrorHandler{nullptr};
static std::atomic<HALSIM_SendConsoleLineHandler> sendConsoleLineHandler{
    nullptr};

namespace hal::init {
void InitializeDriverStation() {
}
}  // namespace hal::init

using namespace hal;

extern "C" {

void HALSIM_SetSendError(HALSIM_SendErrorHandler handler) {
  sendErrorHandler.store(handler);
}

void HALSIM_SetSendConsoleLine(HALSIM_SendConsoleLineHandler handler) {
  sendConsoleLineHandler.store(handler);
}

int32_t HAL_SendError(HAL_Bool isError, int32_t errorCode, HAL_Bool isLVCode,
                      const char* details, const char* location,
                      const char* callStack, HAL_Bool printMsg) {
  auto errorHandler = sendErrorHandler.load();
  if (errorHandler) {
    return errorHandler(isError, errorCode, isLVCode, details, location,
                        callStack, printMsg);
  }
  // Avoid flooding console by keeping track of previous 5 error
  // messages and only printing again if they're longer than 1 second old.
  static constexpr int KEEP_MSGS = 5;
  std::scoped_lock lock(msgMutex);
  static std::string prevMsg[KEEP_MSGS];
  static fpga_clock::time_point prevMsgTime[KEEP_MSGS];
  static bool initialized = false;
  if (!initialized) {
    for (int i = 0; i < KEEP_MSGS; i++) {
      prevMsgTime[i] = fpga_clock::now() - std::chrono::seconds(2);
    }
    initialized = true;
  }

  auto curTime = fpga_clock::now();
  int i;
  for (i = 0; i < KEEP_MSGS; ++i) {
    if (prevMsg[i] == details) {
      break;
    }
  }
  int retval = 0;
  if (i == KEEP_MSGS || (curTime - prevMsgTime[i]) >= std::chrono::seconds(1)) {
    printMsg = true;
    if (printMsg) {
      fmt::memory_buffer buf;
      if (location && location[0] != '\0') {
        fmt::format_to(fmt::appender{buf},
                       "{} at {}: ", isError ? "Error" : "Warning", location);
      }
      fmt::format_to(fmt::appender{buf}, "{}\n", details);
      if (callStack && callStack[0] != '\0') {
        fmt::format_to(fmt::appender{buf}, "{}\n", callStack);
      }
      std::fwrite(buf.data(), buf.size(), 1, stderr);
    }
    if (i == KEEP_MSGS) {
      // replace the oldest one
      i = 0;
      auto first = prevMsgTime[0];
      for (int j = 1; j < KEEP_MSGS; ++j) {
        if (prevMsgTime[j] < first) {
          first = prevMsgTime[j];
          i = j;
        }
      }
      prevMsg[i] = details;
    }
    prevMsgTime[i] = curTime;
  }
  return retval;
}

int32_t HAL_SendConsoleLine(const char* line) {
  auto handler = sendConsoleLineHandler.load();
  if (handler) {
    return handler(line);
  }
  std::puts(line);
  std::fflush(stdout);
  return 0;
}

int32_t HAL_GetControlWord(HAL_ControlWord* controlWord) {
  std::memset(controlWord, 0, sizeof(HAL_ControlWord));
  controlWord->enabled = SimDriverStationData->enabled;
  controlWord->autonomous = SimDriverStationData->autonomous;
  controlWord->test = SimDriverStationData->test;
  controlWord->eStop = SimDriverStationData->eStop;
  controlWord->fmsAttached = SimDriverStationData->fmsAttached;
  controlWord->dsAttached = SimDriverStationData->dsAttached;
  return 0;
}

HAL_AllianceStationID HAL_GetAllianceStation(int32_t* status) {
  *status = 0;
  return SimDriverStationData->allianceStationId;
}

int32_t HAL_GetJoystickAxes(int32_t joystickNum, HAL_JoystickAxes* axes) {
  SimDriverStationData->GetJoystickAxes(joystickNum, axes);
  return 0;
}

int32_t HAL_GetJoystickPOVs(int32_t joystickNum, HAL_JoystickPOVs* povs) {
  SimDriverStationData->GetJoystickPOVs(joystickNum, povs);
  return 0;
}

int32_t HAL_GetJoystickButtons(int32_t joystickNum,
                               HAL_JoystickButtons* buttons) {
  SimDriverStationData->GetJoystickButtons(joystickNum, buttons);
  return 0;
}

int32_t HAL_GetJoystickDescriptor(int32_t joystickNum,
                                  HAL_JoystickDescriptor* desc) {
  SimDriverStationData->GetJoystickDescriptor(joystickNum, desc);
  return 0;
}

HAL_Bool HAL_GetJoystickIsXbox(int32_t joystickNum) {
  HAL_JoystickDescriptor desc;
  SimDriverStationData->GetJoystickDescriptor(joystickNum, &desc);
  return desc.isXbox;
}

int32_t HAL_GetJoystickType(int32_t joystickNum) {
  HAL_JoystickDescriptor desc;
  SimDriverStationData->GetJoystickDescriptor(joystickNum, &desc);
  return desc.type;
}

char* HAL_GetJoystickName(int32_t joystickNum) {
  HAL_JoystickDescriptor desc;
  SimDriverStationData->GetJoystickDescriptor(joystickNum, &desc);
  size_t len = std::strlen(desc.name);
  char* name = static_cast<char*>(std::malloc(len + 1));
  std::memcpy(name, desc.name, len + 1);
  return name;
}

void HAL_FreeJoystickName(char* name) {
  std::free(name);
}

int32_t HAL_GetJoystickAxisType(int32_t joystickNum, int32_t axis) {
  return 0;
}

int32_t HAL_SetJoystickOutputs(int32_t joystickNum, int64_t outputs,
                               int32_t leftRumble, int32_t rightRumble) {
  SimDriverStationData->SetJoystickOutputs(joystickNum, outputs, leftRumble,
                                           rightRumble);
  return 0;
}

double HAL_GetMatchTime(int32_t* status) {
  return SimDriverStationData->matchTime;
}

int32_t HAL_GetMatchInfo(HAL_MatchInfo* info) {
  SimDriverStationData->GetMatchInfo(info);
  return 0;
}

void HAL_ObserveUserProgramStarting(void) {
  HALSIM_SetProgramStarted();
}

void HAL_ObserveUserProgramDisabled(void) {
  // TODO
}

void HAL_ObserveUserProgramAutonomous(void) {
  // TODO
}

void HAL_ObserveUserProgramTeleop(void) {
  // TODO
}

void HAL_ObserveUserProgramTest(void) {
  // TODO
}

void HAL_UpdateDSData(void) {}
void HAL_ProvideNewDataEventHandle(WPI_EventHandle handle) {}
void HAL_RemoveNewDataEventHandle(WPI_EventHandle handle) {}

HAL_Bool HAL_GetOutputsEnabled(void) {
  return false;
}

}  // extern "C"

namespace hal {
void NewDriverStationData() {
  SimDriverStationData->CallNewDataCallbacks();
}

void InitializeDriverStation() {
  SimDriverStationData->ResetData();
}
}  // namespace hal
