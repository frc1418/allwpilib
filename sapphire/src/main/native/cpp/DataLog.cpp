//
// Created by bagatelle on 6/2/22.
//

#include "DataLog.h"

#include <map>
#include <string>
#include <memory>
#include <system_error>
#include <utility>
#include <vector>
#include <filesystem>
#include "wpi/fmt/raw_ostream.h"
#include "fmt/format.h"

#include <wpi/MemoryBuffer.h>
#include <wpi/DataLogReader.h>
#include <imgui.h>

using namespace sapphire;

static float maxTimestamp = 100;
static bool needsUpdate = false;
static float timestamp = 0;
static const int TIMESTAMP_FUDGE_FACTOR = 10000;

wpi::log::DataLogRecord EntryData::GetRecordAt(int timestamp){
  wpi::log::DataLogRecord record;
  for(auto& entry : datapoints){
    if(entry.first <= timestamp){
      record = entry.second;
    }
  }
  return record;
}
wpi::log::DataLogRecord EntryData::GetNextRecord(int timestamp){
  wpi::log::DataLogRecord record;
  for(auto& entry : datapoints){
    if(entry.first > timestamp && (entry.first < record.GetTimestamp() || record.GetTimestamp() <= timestamp)){
      record = entry.second;
    }
  }
  return record;
}

bool DataLogModel::LoadWPILog(std::string filename) {
  std::error_code ec;
  auto buf = wpi::MemoryBuffer::GetFile(filename.c_str(), ec);
  if (ec) {
    return false;
  }

  reader = std::make_shared<wpi::log::DataLogReader>(std::move(buf));
  if (!reader->IsValid()) {
    return false;
  }
  m_tree.clear();
  this->m_entries.clear();
  for (const auto& record : *reader) {
    int entryId;
    if(record.IsStart()) {
      // If we find a new start record, create a new entry
      wpi::log::StartRecordData start;
      
      if(!record.GetStartData(&start)) { continue; }
      if(m_entries.find(start.entry) != m_entries.end()){ continue; } // This should probably be an error

      m_entries[start.entry] = std::make_unique<EntryData>(start);
      AddEntryNode(m_entries[start.entry].get(), m_entries[start.entry]->name);
    } else if(record.GetFinishEntry(&entryId)) {
      // If we find a finish entry,
      auto entryPair= m_entries.find(entryId);
      if(entryPair == m_entries.end()) { continue; }
    
      entryPair->second->finishTimestamp = record.GetTimestamp();
    } else if(!record.IsControl()) {
      auto entryPair = m_entries.find(record.GetEntry());
      if(entryPair == m_entries.end()) {
        continue;
      }
      EntryData &entry = *entryPair->second;
      
      int timestamp = record.GetTimestamp();
      if(timestamp > entry.finishTimestamp) {
        fmt::print("Entry Finished, Invalid Data Point\n");
        continue;
      }
      
      if(timestamp > m_maxTimestamp) {
        m_maxTimestamp = timestamp;
      }

      entry.datapoints[record.GetTimestamp()] = std::move(record);
    }
  }
  
  this->filename = filename;
  m_hasLog = true;
  flags.IsLogActive = true;
  return true;
}


EntryNode* find(std::vector<EntryNode>& list, std::string name){
  for(auto& child : list){
    if(child.name == name){
      return &child;
    }
  }
  return nullptr;
}


void DataLogModel::AddEntryNode(EntryData* data, std::string path){
  std::vector<std::string> pathVec;
  int last = 0;
  for(int i = 0; i < path.length(); i++){
    if(path[i] == '/'){
      pathVec.emplace_back(path.substr(last, i-last));
      last = i;
    }
  }
  pathVec.emplace_back(path.substr(last, path.length()-last));
  if(pathVec.size() > 0){
    auto nextNode = find(m_tree, pathVec[0]);
    if(nextNode == nullptr){
      nextNode = &m_tree.emplace_back(EntryNode{pathVec[0]});
    }
    nextNode->AddEntry(data, std::vector<std::string>(pathVec.begin()+1, pathVec.end()));
  }
}

void EntryNode::AddEntry(EntryData *entry,std::vector<std::string> path){
  if(path.size() > 0){
    auto nextNode = find(children, path[0]);
    if(nextNode == nullptr){
      nextNode = &children.emplace_back(EntryNode{path[0]});
    }
    nextNode->AddEntry(entry, std::vector<std::string>(path.begin()+1, path.end()));
  } else {
    // We are the final destination for the data
    this->entry = entry;
  }

}


std::string EntryNode::TreeToString(int depth){
  std::string ret = fmt::format("{} \n", this->name);
  for(auto &child : children){
    for(int i = 0; i < depth; i++){
      ret += fmt::format("-");
    }
    ret += child.TreeToString(depth+1) + "\n";
  }
  return ret;
}
std::string sapphire::GetFormattedEntryValue(EntryData& data, int timestamp, wpi::log::DataLogRecord record){
    if (data.type == "double") {
        double val;
        if (record.GetDouble(&val)) {
            return fmt::format("  {}\n", val);
        }
    } else if (data.type == "int64") {
        int64_t val;
        if (record.GetInteger(&val)) {
            return fmt::format("  {}\n", val);
        }
    } else if (data.type == "string" ||
            data.type == "json") {
        std::string_view val;
        record.GetString(&val);
        return fmt::format("  '{}'\n", val);
    } else if (data.type == "boolean") {
        bool val;
        if (record.GetBoolean(&val)) {
            return fmt::format("  {}\n", val);
        }
    } else if (data.type == "boolean[]") {
        std::vector<int> val;
        if (record.GetBooleanArray(&val)) {
            return fmt::format("  {}\n", fmt::join(val, ", "));
        }
    } else if (data.type == "double[]") {
        std::vector<double> val;
        if (record.GetDoubleArray(&val)) {
            return fmt::format("  {}\n", fmt::join(val, ", "));
        }
    } else if (data.type == "float[]") {
        std::vector<float> val;
        if (record.GetFloatArray(&val)) {
            return fmt::format("  {}\n", fmt::join(val, ", "));
        }
    } else if (data.type == "int64[]") {
        std::vector<int64_t> val;
        if (record.GetIntegerArray(&val)) {
            return fmt::format("  {}\n", fmt::join(val, ", "));
        }
    } else if (data.type == "string[]") {
        std::vector<std::string_view> val;
        if (record.GetStringArray(&val)) {
            return fmt::format("  {}\n", fmt::join(val, ", "));
        }
    }
    return "  invalid";
}

void EmitEntry(EntryData *data, std::string name, float *offset, DataLogFlags flags){
  ImGui::PushID(name.c_str());
  ImGui::Text("%s", name.c_str());
  
  int expandedts = static_cast<int>((timestamp+*offset)*1000000);
  auto record = data->GetRecordAt(expandedts);

  if (ImGui::BeginPopupContextItem(name.c_str())) {
    if(ImGui::MenuItem("Next Event")){
      auto temp = data->GetNextRecord(record.GetTimestamp());
      if(temp.GetEntry() != -1){
        *offset = (temp.GetTimestamp()+TIMESTAMP_FUDGE_FACTOR)/1000000.0;
        record = temp;
      }
    }
    ImGui::EndPopup();
  }
  
  ImGui::NextColumn();
  std::string value = (record.GetEntry() == -1) ? "" : GetFormattedEntryValue(*data, expandedts, record);
  ImGui::Text(value.c_str());
  ImGui::NextColumn();
  
  if(flags.ShowLastUpdate){
    ImGui::Text("%fs (%fs ago)", record.GetTimestamp()/1000000.0, (timestamp+*offset)-(record.GetTimestamp()/1000000.0));
    ImGui::NextColumn();
  }
  if(flags.ShowNextUpdate){
    auto temp = data->GetNextRecord(record.GetTimestamp());
    if(temp.GetEntry() != -1){
      float next = temp.GetTimestamp()/1000000.0;
      ImGui::Text("%fs (%fs later)", next, (next) - timestamp+*offset);
    } else {
      ImGui::Text("No Next Record");
    }
    ImGui::NextColumn();
  }
  ImGui::Separator();
  ImGui::PopID();
}

void EmitTree(const std::vector<EntryNode>& tree, float *timestamp, DataLogFlags flags) {
  for(auto &&node : tree){
    if(node.entry){
      EmitEntry(node.entry, node.name, timestamp, flags);
    }
    if(!node.children.empty()){
      ImGui::PushID(node.name.c_str());
      bool open = ImGui::TreeNodeEx(node.name.c_str(), ImGuiTreeNodeFlags_SpanFullWidth);
      ImGui::NextColumn();
      ImGui::NextColumn();
      if(flags.ShowLastUpdate){
        ImGui::NextColumn();
      }
      if(flags.ShowNextUpdate){
        ImGui::NextColumn();
      }
      ImGui::Separator();
      if(open){
        EmitTree(node.children, timestamp, flags);
        ImGui::TreePop();
      }
      ImGui::PopID();
    }
  }
}

void DataLogView::DisplayDataLog(DataLogModel* log){
  ImGui::Text("Manage Entry Offset:");
  ImGui::SameLine();
  ImGui::SliderFloat("TsSlider", &log->offset ,0, log->GetMaxTimestamp()/1000000.0);
  ImGui::SameLine();
  ImGui::InputFloat("TsInput", &log->offset);

  ImGui::Columns(2 + log->flags.ShowLastUpdate + log->flags.ShowNextUpdate, "values");
  ImGui::Text("Name");
  ImGui::NextColumn();
  
  ImGui::Text("Value");
  ImGui::NextColumn();
  if(log->flags.ShowLastUpdate){
    ImGui::Text("Last Update");
    ImGui::NextColumn();
  }
  if(log->flags.ShowNextUpdate){
    ImGui::Text("Next Update");
    ImGui::NextColumn();
  }
  EmitTree(log->GetTreeRoot(), &log->offset, log->flags);
  
  ImGui::Columns();
}

int DataLogView::GetMaxTimestamp(){
  int max = 0;
  for(auto &log : logs){
    if(log->GetMaxTimestamp() > max){
      max = log->GetMaxTimestamp();
    }
  }
  return max;
}

void DataLogView::Display() {
  ImGui::Text("Manage Overall Time:");
  ImGui::SameLine();
  ImGui::SliderFloat("TsSlider", &timestamp ,0, GetMaxTimestamp()/1000000.0);
  ImGui::SameLine();
  ImGui::InputFloat("TsInput", &timestamp);
  for(auto& log : logs){
    if(log->Exists()){
      ImGui::PushID(log->filename.c_str());
      
      if(ImGui::CollapsingHeader(log->filename.c_str())){
        if (ImGui::BeginPopupContextItem(log->filename.c_str())) {
          if(ImGui::MenuItem("Show Last Update Timestamp", "", log->flags.ShowLastUpdate)){
            log->flags.ShowLastUpdate = !log->flags.ShowLastUpdate;
          }
          if(ImGui::MenuItem("Show Next Update Timestamp", "", log->flags.ShowNextUpdate)){
            log->flags.ShowNextUpdate = !log->flags.ShowNextUpdate;
          }
          if(ImGui::MenuItem("Close Log")){
            log->flags.IsLogActive = false;
          }
          ImGui::EndPopup();
        }
        DisplayDataLog(log.get());
      }
      ImGui::PopID();
    }
  }
}



