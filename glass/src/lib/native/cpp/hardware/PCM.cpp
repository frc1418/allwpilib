// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "glass/hardware/PCM.h"

#include <cstdio>
#include <cstring>

#include <imgui.h>
#include <wpi/SmallVector.h>

#include "glass/Context.h"
#include "glass/DataSource.h"
#include "glass/Storage.h"
#include "glass/other/DeviceTree.h"
#include "glass/support/ExtraGuiWidgets.h"
#include "glass/support/NameSetting.h"

using namespace glass;

bool glass::DisplayPCMSolenoids(PCMModel* model, int index,
                                bool outputsEnabled) {
  wpi::SmallVector<int, 16> channels;
  model->ForEachSolenoid([&](SolenoidModel& solenoid, int j) {
    if (auto data = solenoid.GetOutputData()) {
      if (j >= static_cast<int>(channels.size())) {
        channels.resize(j + 1);
      }
      channels[j] = (outputsEnabled && data->GetValue()) ? 1 : -1;
    }
  });

  if (channels.empty()) {
    return false;
  }

  // show nonexistent channels as empty
  for (auto&& ch : channels) {
    if (ch == 0) {
      ch = -2;
    }
  }

  // build header label
  std::string& name = GetStorage().GetString("name");
  char label[128];
  if (!name.empty()) {
    std::snprintf(label, sizeof(label), "%s [%d]###header", name.c_str(),
                  index);
  } else {
    std::snprintf(label, sizeof(label), "PCM[%d]###header", index);
  }

  // header
  bool open = CollapsingHeader(label);

  PopupEditName("header", &name);

  ImGui::SetItemAllowOverlap();
  ImGui::SameLine();

  // show channels as LED indicators
  static const ImU32 colors[] = {IM_COL32(255, 255, 102, 255),
                                 IM_COL32(128, 128, 128, 255)};
  DrawLEDs(channels.data(), channels.size(), channels.size(), colors);

  if (open) {
    ImGui::PushItemWidth(ImGui::GetFontSize() * 4);
    model->ForEachSolenoid([&](SolenoidModel& solenoid, int j) {
      if (auto data = solenoid.GetOutputData()) {
        PushID(j);
        char label[64];
        NameSetting name{data->GetName()};
        name.GetLabel(label, sizeof(label), "Solenoid", j);
        data->LabelText(label, "%s", channels[j] == 1 ? "On" : "Off");
        name.PopupEditName(j);
        PopID();
      }
    });
    ImGui::PopItemWidth();
  }

  return true;
}

void glass::DisplayPCMsSolenoids(PCMsModel* model, bool outputsEnabled,
                                 std::string_view noneMsg) {
  bool hasAny = false;
  model->ForEachPCM([&](PCMModel& pcm, int i) {
    PushID(i);
    if (DisplayPCMSolenoids(&pcm, i, outputsEnabled)) {
      hasAny = true;
    }
    PopID();
  });
  if (!hasAny && !noneMsg.empty()) {
    ImGui::TextUnformatted(noneMsg.data(), noneMsg.data() + noneMsg.size());
  }
}

void glass::DisplayCompressorDevice(PCMModel* model, int index,
                                    bool outputsEnabled) {
  auto compressor = model->GetCompressor();
  if (!compressor || !compressor->Exists()) {
    return;
  }
  DisplayCompressorDevice(compressor, index, outputsEnabled);
}

void glass::DisplayCompressorDevice(CompressorModel* model, int index,
                                    bool outputsEnabled) {
  char name[32];
  std::snprintf(name, sizeof(name), "Compressor[%d]", index);
  if (BeginDevice(name)) {
    // output enabled
    if (auto runningData = model->GetRunningData()) {
      bool value = outputsEnabled && runningData->GetValue();
      if (DeviceBoolean("Running", false, &value, runningData)) {
        model->SetRunning(value);
      }
    }

    // closed loop enabled
    if (auto enabledData = model->GetEnabledData()) {
      int value = enabledData->GetValue() ? 1 : 0;
      static const char* enabledOptions[] = {"disabled", "enabled"};
      if (DeviceEnum("Closed Loop", true, &value, enabledOptions, 2,
                     enabledData)) {
        model->SetEnabled(value != 0);
      }
    }

    // pressure switch
    if (auto pressureSwitchData = model->GetPressureSwitchData()) {
      int value = pressureSwitchData->GetValue() ? 1 : 0;
      static const char* switchOptions[] = {"full", "low"};
      if (DeviceEnum("Pressure", false, &value, switchOptions, 2,
                     pressureSwitchData)) {
        model->SetPressureSwitch(value != 0);
      }
    }

    // compressor current
    if (auto currentData = model->GetCurrentData()) {
      double value = currentData->GetValue();
      if (DeviceDouble("Current (A)", false, &value, currentData)) {
        model->SetCurrent(value);
      }
    }

    EndDevice();
  }
}

void glass::DisplayCompressorsDevice(PCMsModel* model, bool outputsEnabled) {
  model->ForEachPCM([&](PCMModel& pcm, int i) {
    DisplayCompressorDevice(&pcm, i, outputsEnabled);
  });
}
