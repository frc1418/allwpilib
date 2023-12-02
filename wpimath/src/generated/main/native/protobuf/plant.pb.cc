// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: plant.proto

#include "plant.pb.h"

#include <algorithm>

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/wire_format_lite.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)
#include <google/protobuf/port_def.inc>

PROTOBUF_PRAGMA_INIT_SEG

namespace _pb = ::PROTOBUF_NAMESPACE_ID;
namespace _pbi = _pb::internal;

namespace wpi {
namespace proto {
PROTOBUF_CONSTEXPR ProtobufDCMotor::ProtobufDCMotor(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.nominal_voltage_)*/0
  , /*decltype(_impl_.stall_torque_)*/0
  , /*decltype(_impl_.stall_current_)*/0
  , /*decltype(_impl_.free_current_)*/0
  , /*decltype(_impl_.free_speed_)*/0
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct ProtobufDCMotorDefaultTypeInternal {
  PROTOBUF_CONSTEXPR ProtobufDCMotorDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~ProtobufDCMotorDefaultTypeInternal() {}
  union {
    ProtobufDCMotor _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 ProtobufDCMotorDefaultTypeInternal _ProtobufDCMotor_default_instance_;
}  // namespace proto
}  // namespace wpi
static ::_pb::Metadata file_level_metadata_plant_2eproto[1];
static constexpr ::_pb::EnumDescriptor const** file_level_enum_descriptors_plant_2eproto = nullptr;
static constexpr ::_pb::ServiceDescriptor const** file_level_service_descriptors_plant_2eproto = nullptr;

const uint32_t TableStruct_plant_2eproto::offsets[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _impl_.nominal_voltage_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _impl_.stall_torque_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _impl_.stall_current_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _impl_.free_current_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufDCMotor, _impl_.free_speed_),
};
static const ::_pbi::MigrationSchema schemas[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  { 0, -1, -1, sizeof(::wpi::proto::ProtobufDCMotor)},
};

static const ::_pb::Message* const file_default_instances[] = {
  &::wpi::proto::_ProtobufDCMotor_default_instance_._instance,
};

const char descriptor_table_protodef_plant_2eproto[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) =
  "\n\013plant.proto\022\twpi.proto\"\201\001\n\017ProtobufDCM"
  "otor\022\027\n\017nominal_voltage\030\001 \001(\001\022\024\n\014stall_t"
  "orque\030\002 \001(\001\022\025\n\rstall_current\030\003 \001(\001\022\024\n\014fr"
  "ee_current\030\004 \001(\001\022\022\n\nfree_speed\030\005 \001(\001B\032\n\030"
  "edu.wpi.first.math.protob\006proto3"
  ;
static ::_pbi::once_flag descriptor_table_plant_2eproto_once;
const ::_pbi::DescriptorTable descriptor_table_plant_2eproto = {
    false, false, 192, descriptor_table_protodef_plant_2eproto,
    "plant.proto",
    &descriptor_table_plant_2eproto_once, nullptr, 0, 1,
    schemas, file_default_instances, TableStruct_plant_2eproto::offsets,
    file_level_metadata_plant_2eproto, file_level_enum_descriptors_plant_2eproto,
    file_level_service_descriptors_plant_2eproto,
};
PROTOBUF_ATTRIBUTE_WEAK const ::_pbi::DescriptorTable* descriptor_table_plant_2eproto_getter() {
  return &descriptor_table_plant_2eproto;
}

// Force running AddDescriptors() at dynamic initialization time.
PROTOBUF_ATTRIBUTE_INIT_PRIORITY2 static ::_pbi::AddDescriptorsRunner dynamic_init_dummy_plant_2eproto(&descriptor_table_plant_2eproto);
namespace wpi {
namespace proto {

// ===================================================================

class ProtobufDCMotor::_Internal {
 public:
};

ProtobufDCMotor::ProtobufDCMotor(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:wpi.proto.ProtobufDCMotor)
}
ProtobufDCMotor::ProtobufDCMotor(const ProtobufDCMotor& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  ProtobufDCMotor* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.nominal_voltage_){}
    , decltype(_impl_.stall_torque_){}
    , decltype(_impl_.stall_current_){}
    , decltype(_impl_.free_current_){}
    , decltype(_impl_.free_speed_){}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  ::memcpy(&_impl_.nominal_voltage_, &from._impl_.nominal_voltage_,
    static_cast<size_t>(reinterpret_cast<char*>(&_impl_.free_speed_) -
    reinterpret_cast<char*>(&_impl_.nominal_voltage_)) + sizeof(_impl_.free_speed_));
  // @@protoc_insertion_point(copy_constructor:wpi.proto.ProtobufDCMotor)
}

inline void ProtobufDCMotor::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.nominal_voltage_){0}
    , decltype(_impl_.stall_torque_){0}
    , decltype(_impl_.stall_current_){0}
    , decltype(_impl_.free_current_){0}
    , decltype(_impl_.free_speed_){0}
    , /*decltype(_impl_._cached_size_)*/{}
  };
}

ProtobufDCMotor::~ProtobufDCMotor() {
  // @@protoc_insertion_point(destructor:wpi.proto.ProtobufDCMotor)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void ProtobufDCMotor::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
}

void ProtobufDCMotor::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void ProtobufDCMotor::Clear() {
// @@protoc_insertion_point(message_clear_start:wpi.proto.ProtobufDCMotor)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  ::memset(&_impl_.nominal_voltage_, 0, static_cast<size_t>(
      reinterpret_cast<char*>(&_impl_.free_speed_) -
      reinterpret_cast<char*>(&_impl_.nominal_voltage_)) + sizeof(_impl_.free_speed_));
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* ProtobufDCMotor::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // double nominal_voltage = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 9)) {
          _impl_.nominal_voltage_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr);
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // double stall_torque = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 17)) {
          _impl_.stall_torque_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr);
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // double stall_current = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 25)) {
          _impl_.stall_current_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr);
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // double free_current = 4;
      case 4:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 33)) {
          _impl_.free_current_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr);
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // double free_speed = 5;
      case 5:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 41)) {
          _impl_.free_speed_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr);
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      default:
        goto handle_unusual;
    }  // switch
  handle_unusual:
    if ((tag == 0) || ((tag & 7) == 4)) {
      CHK_(ptr);
      ctx->SetLastTag(tag);
      goto message_done;
    }
    ptr = UnknownFieldParse(
        tag,
        _internal_metadata_.mutable_unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(),
        ptr, ctx);
    CHK_(ptr != nullptr);
  }  // while
message_done:
  return ptr;
failure:
  ptr = nullptr;
  goto message_done;
#undef CHK_
}

uint8_t* ProtobufDCMotor::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:wpi.proto.ProtobufDCMotor)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // double nominal_voltage = 1;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_nominal_voltage = this->_internal_nominal_voltage();
  uint64_t raw_nominal_voltage;
  memcpy(&raw_nominal_voltage, &tmp_nominal_voltage, sizeof(tmp_nominal_voltage));
  if (raw_nominal_voltage != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteDoubleToArray(1, this->_internal_nominal_voltage(), target);
  }

  // double stall_torque = 2;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_torque = this->_internal_stall_torque();
  uint64_t raw_stall_torque;
  memcpy(&raw_stall_torque, &tmp_stall_torque, sizeof(tmp_stall_torque));
  if (raw_stall_torque != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteDoubleToArray(2, this->_internal_stall_torque(), target);
  }

  // double stall_current = 3;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_current = this->_internal_stall_current();
  uint64_t raw_stall_current;
  memcpy(&raw_stall_current, &tmp_stall_current, sizeof(tmp_stall_current));
  if (raw_stall_current != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteDoubleToArray(3, this->_internal_stall_current(), target);
  }

  // double free_current = 4;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_current = this->_internal_free_current();
  uint64_t raw_free_current;
  memcpy(&raw_free_current, &tmp_free_current, sizeof(tmp_free_current));
  if (raw_free_current != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteDoubleToArray(4, this->_internal_free_current(), target);
  }

  // double free_speed = 5;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_speed = this->_internal_free_speed();
  uint64_t raw_free_speed;
  memcpy(&raw_free_speed, &tmp_free_speed, sizeof(tmp_free_speed));
  if (raw_free_speed != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteDoubleToArray(5, this->_internal_free_speed(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:wpi.proto.ProtobufDCMotor)
  return target;
}

size_t ProtobufDCMotor::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:wpi.proto.ProtobufDCMotor)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // double nominal_voltage = 1;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_nominal_voltage = this->_internal_nominal_voltage();
  uint64_t raw_nominal_voltage;
  memcpy(&raw_nominal_voltage, &tmp_nominal_voltage, sizeof(tmp_nominal_voltage));
  if (raw_nominal_voltage != 0) {
    total_size += 1 + 8;
  }

  // double stall_torque = 2;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_torque = this->_internal_stall_torque();
  uint64_t raw_stall_torque;
  memcpy(&raw_stall_torque, &tmp_stall_torque, sizeof(tmp_stall_torque));
  if (raw_stall_torque != 0) {
    total_size += 1 + 8;
  }

  // double stall_current = 3;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_current = this->_internal_stall_current();
  uint64_t raw_stall_current;
  memcpy(&raw_stall_current, &tmp_stall_current, sizeof(tmp_stall_current));
  if (raw_stall_current != 0) {
    total_size += 1 + 8;
  }

  // double free_current = 4;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_current = this->_internal_free_current();
  uint64_t raw_free_current;
  memcpy(&raw_free_current, &tmp_free_current, sizeof(tmp_free_current));
  if (raw_free_current != 0) {
    total_size += 1 + 8;
  }

  // double free_speed = 5;
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_speed = this->_internal_free_speed();
  uint64_t raw_free_speed;
  memcpy(&raw_free_speed, &tmp_free_speed, sizeof(tmp_free_speed));
  if (raw_free_speed != 0) {
    total_size += 1 + 8;
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData ProtobufDCMotor::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    ProtobufDCMotor::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*ProtobufDCMotor::GetClassData() const { return &_class_data_; }


void ProtobufDCMotor::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<ProtobufDCMotor*>(&to_msg);
  auto& from = static_cast<const ProtobufDCMotor&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:wpi.proto.ProtobufDCMotor)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_nominal_voltage = from._internal_nominal_voltage();
  uint64_t raw_nominal_voltage;
  memcpy(&raw_nominal_voltage, &tmp_nominal_voltage, sizeof(tmp_nominal_voltage));
  if (raw_nominal_voltage != 0) {
    _this->_internal_set_nominal_voltage(from._internal_nominal_voltage());
  }
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_torque = from._internal_stall_torque();
  uint64_t raw_stall_torque;
  memcpy(&raw_stall_torque, &tmp_stall_torque, sizeof(tmp_stall_torque));
  if (raw_stall_torque != 0) {
    _this->_internal_set_stall_torque(from._internal_stall_torque());
  }
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_stall_current = from._internal_stall_current();
  uint64_t raw_stall_current;
  memcpy(&raw_stall_current, &tmp_stall_current, sizeof(tmp_stall_current));
  if (raw_stall_current != 0) {
    _this->_internal_set_stall_current(from._internal_stall_current());
  }
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_current = from._internal_free_current();
  uint64_t raw_free_current;
  memcpy(&raw_free_current, &tmp_free_current, sizeof(tmp_free_current));
  if (raw_free_current != 0) {
    _this->_internal_set_free_current(from._internal_free_current());
  }
  static_assert(sizeof(uint64_t) == sizeof(double), "Code assumes uint64_t and double are the same size.");
  double tmp_free_speed = from._internal_free_speed();
  uint64_t raw_free_speed;
  memcpy(&raw_free_speed, &tmp_free_speed, sizeof(tmp_free_speed));
  if (raw_free_speed != 0) {
    _this->_internal_set_free_speed(from._internal_free_speed());
  }
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void ProtobufDCMotor::CopyFrom(const ProtobufDCMotor& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:wpi.proto.ProtobufDCMotor)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ProtobufDCMotor::IsInitialized() const {
  return true;
}

void ProtobufDCMotor::InternalSwap(ProtobufDCMotor* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::internal::memswap<
      PROTOBUF_FIELD_OFFSET(ProtobufDCMotor, _impl_.free_speed_)
      + sizeof(ProtobufDCMotor::_impl_.free_speed_)
      - PROTOBUF_FIELD_OFFSET(ProtobufDCMotor, _impl_.nominal_voltage_)>(
          reinterpret_cast<char*>(&_impl_.nominal_voltage_),
          reinterpret_cast<char*>(&other->_impl_.nominal_voltage_));
}

::PROTOBUF_NAMESPACE_ID::Metadata ProtobufDCMotor::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_plant_2eproto_getter, &descriptor_table_plant_2eproto_once,
      file_level_metadata_plant_2eproto[0]);
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace wpi
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::wpi::proto::ProtobufDCMotor*
Arena::CreateMaybeMessage< ::wpi::proto::ProtobufDCMotor >(Arena* arena) {
  return Arena::CreateMessageInternal< ::wpi::proto::ProtobufDCMotor >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>
