// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: spline.proto

#include "spline.pb.h"

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
PROTOBUF_CONSTEXPR ProtobufCubicHermiteSpline::ProtobufCubicHermiteSpline(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.x_initial_)*/{}
  , /*decltype(_impl_.x_final_)*/{}
  , /*decltype(_impl_.y_initial_)*/{}
  , /*decltype(_impl_.y_final_)*/{}
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct ProtobufCubicHermiteSplineDefaultTypeInternal {
  PROTOBUF_CONSTEXPR ProtobufCubicHermiteSplineDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~ProtobufCubicHermiteSplineDefaultTypeInternal() {}
  union {
    ProtobufCubicHermiteSpline _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 ProtobufCubicHermiteSplineDefaultTypeInternal _ProtobufCubicHermiteSpline_default_instance_;
PROTOBUF_CONSTEXPR ProtobufQuinticHermiteSpline::ProtobufQuinticHermiteSpline(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.x_initial_)*/{}
  , /*decltype(_impl_.x_final_)*/{}
  , /*decltype(_impl_.y_initial_)*/{}
  , /*decltype(_impl_.y_final_)*/{}
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct ProtobufQuinticHermiteSplineDefaultTypeInternal {
  PROTOBUF_CONSTEXPR ProtobufQuinticHermiteSplineDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~ProtobufQuinticHermiteSplineDefaultTypeInternal() {}
  union {
    ProtobufQuinticHermiteSpline _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 ProtobufQuinticHermiteSplineDefaultTypeInternal _ProtobufQuinticHermiteSpline_default_instance_;
}  // namespace proto
}  // namespace wpi
static ::_pb::Metadata file_level_metadata_spline_2eproto[2];
static constexpr ::_pb::EnumDescriptor const** file_level_enum_descriptors_spline_2eproto = nullptr;
static constexpr ::_pb::ServiceDescriptor const** file_level_service_descriptors_spline_2eproto = nullptr;

const uint32_t TableStruct_spline_2eproto::offsets[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufCubicHermiteSpline, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufCubicHermiteSpline, _impl_.x_initial_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufCubicHermiteSpline, _impl_.x_final_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufCubicHermiteSpline, _impl_.y_initial_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufCubicHermiteSpline, _impl_.y_final_),
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufQuinticHermiteSpline, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufQuinticHermiteSpline, _impl_.x_initial_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufQuinticHermiteSpline, _impl_.x_final_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufQuinticHermiteSpline, _impl_.y_initial_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufQuinticHermiteSpline, _impl_.y_final_),
};
static const ::_pbi::MigrationSchema schemas[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  { 0, -1, -1, sizeof(::wpi::proto::ProtobufCubicHermiteSpline)},
  { 10, -1, -1, sizeof(::wpi::proto::ProtobufQuinticHermiteSpline)},
};

static const ::_pb::Message* const file_default_instances[] = {
  &::wpi::proto::_ProtobufCubicHermiteSpline_default_instance_._instance,
  &::wpi::proto::_ProtobufQuinticHermiteSpline_default_instance_._instance,
};

const char descriptor_table_protodef_spline_2eproto[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) =
  "\n\014spline.proto\022\twpi.proto\"d\n\032ProtobufCub"
  "icHermiteSpline\022\021\n\tx_initial\030\001 \003(\001\022\017\n\007x_"
  "final\030\002 \003(\001\022\021\n\ty_initial\030\003 \003(\001\022\017\n\007y_fina"
  "l\030\004 \003(\001\"f\n\034ProtobufQuinticHermiteSpline\022"
  "\021\n\tx_initial\030\001 \003(\001\022\017\n\007x_final\030\002 \003(\001\022\021\n\ty"
  "_initial\030\003 \003(\001\022\017\n\007y_final\030\004 \003(\001B\032\n\030edu.w"
  "pi.first.math.protob\006proto3"
  ;
static ::_pbi::once_flag descriptor_table_spline_2eproto_once;
const ::_pbi::DescriptorTable descriptor_table_spline_2eproto = {
    false, false, 267, descriptor_table_protodef_spline_2eproto,
    "spline.proto",
    &descriptor_table_spline_2eproto_once, nullptr, 0, 2,
    schemas, file_default_instances, TableStruct_spline_2eproto::offsets,
    file_level_metadata_spline_2eproto, file_level_enum_descriptors_spline_2eproto,
    file_level_service_descriptors_spline_2eproto,
};
PROTOBUF_ATTRIBUTE_WEAK const ::_pbi::DescriptorTable* descriptor_table_spline_2eproto_getter() {
  return &descriptor_table_spline_2eproto;
}

// Force running AddDescriptors() at dynamic initialization time.
PROTOBUF_ATTRIBUTE_INIT_PRIORITY2 static ::_pbi::AddDescriptorsRunner dynamic_init_dummy_spline_2eproto(&descriptor_table_spline_2eproto);
namespace wpi {
namespace proto {

// ===================================================================

class ProtobufCubicHermiteSpline::_Internal {
 public:
};

ProtobufCubicHermiteSpline::ProtobufCubicHermiteSpline(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:wpi.proto.ProtobufCubicHermiteSpline)
}
ProtobufCubicHermiteSpline::ProtobufCubicHermiteSpline(const ProtobufCubicHermiteSpline& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  ProtobufCubicHermiteSpline* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.x_initial_){from._impl_.x_initial_}
    , decltype(_impl_.x_final_){from._impl_.x_final_}
    , decltype(_impl_.y_initial_){from._impl_.y_initial_}
    , decltype(_impl_.y_final_){from._impl_.y_final_}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  // @@protoc_insertion_point(copy_constructor:wpi.proto.ProtobufCubicHermiteSpline)
}

inline void ProtobufCubicHermiteSpline::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.x_initial_){arena}
    , decltype(_impl_.x_final_){arena}
    , decltype(_impl_.y_initial_){arena}
    , decltype(_impl_.y_final_){arena}
    , /*decltype(_impl_._cached_size_)*/{}
  };
}

ProtobufCubicHermiteSpline::~ProtobufCubicHermiteSpline() {
  // @@protoc_insertion_point(destructor:wpi.proto.ProtobufCubicHermiteSpline)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void ProtobufCubicHermiteSpline::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.x_initial_.~RepeatedField();
  _impl_.x_final_.~RepeatedField();
  _impl_.y_initial_.~RepeatedField();
  _impl_.y_final_.~RepeatedField();
}

void ProtobufCubicHermiteSpline::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void ProtobufCubicHermiteSpline::Clear() {
// @@protoc_insertion_point(message_clear_start:wpi.proto.ProtobufCubicHermiteSpline)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.x_initial_.Clear();
  _impl_.x_final_.Clear();
  _impl_.y_initial_.Clear();
  _impl_.y_final_.Clear();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* ProtobufCubicHermiteSpline::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // repeated double x_initial = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 10)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_x_initial(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 9) {
          _internal_add_x_initial(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double x_final = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 18)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_x_final(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 17) {
          _internal_add_x_final(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double y_initial = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 26)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_y_initial(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 25) {
          _internal_add_y_initial(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double y_final = 4;
      case 4:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 34)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_y_final(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 33) {
          _internal_add_y_final(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
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

uint8_t* ProtobufCubicHermiteSpline::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:wpi.proto.ProtobufCubicHermiteSpline)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // repeated double x_initial = 1;
  if (this->_internal_x_initial_size() > 0) {
    target = stream->WriteFixedPacked(1, _internal_x_initial(), target);
  }

  // repeated double x_final = 2;
  if (this->_internal_x_final_size() > 0) {
    target = stream->WriteFixedPacked(2, _internal_x_final(), target);
  }

  // repeated double y_initial = 3;
  if (this->_internal_y_initial_size() > 0) {
    target = stream->WriteFixedPacked(3, _internal_y_initial(), target);
  }

  // repeated double y_final = 4;
  if (this->_internal_y_final_size() > 0) {
    target = stream->WriteFixedPacked(4, _internal_y_final(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:wpi.proto.ProtobufCubicHermiteSpline)
  return target;
}

size_t ProtobufCubicHermiteSpline::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:wpi.proto.ProtobufCubicHermiteSpline)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated double x_initial = 1;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_x_initial_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double x_final = 2;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_x_final_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double y_initial = 3;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_y_initial_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double y_final = 4;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_y_final_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData ProtobufCubicHermiteSpline::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    ProtobufCubicHermiteSpline::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*ProtobufCubicHermiteSpline::GetClassData() const { return &_class_data_; }


void ProtobufCubicHermiteSpline::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<ProtobufCubicHermiteSpline*>(&to_msg);
  auto& from = static_cast<const ProtobufCubicHermiteSpline&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:wpi.proto.ProtobufCubicHermiteSpline)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_impl_.x_initial_.MergeFrom(from._impl_.x_initial_);
  _this->_impl_.x_final_.MergeFrom(from._impl_.x_final_);
  _this->_impl_.y_initial_.MergeFrom(from._impl_.y_initial_);
  _this->_impl_.y_final_.MergeFrom(from._impl_.y_final_);
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void ProtobufCubicHermiteSpline::CopyFrom(const ProtobufCubicHermiteSpline& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:wpi.proto.ProtobufCubicHermiteSpline)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ProtobufCubicHermiteSpline::IsInitialized() const {
  return true;
}

void ProtobufCubicHermiteSpline::InternalSwap(ProtobufCubicHermiteSpline* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  _impl_.x_initial_.InternalSwap(&other->_impl_.x_initial_);
  _impl_.x_final_.InternalSwap(&other->_impl_.x_final_);
  _impl_.y_initial_.InternalSwap(&other->_impl_.y_initial_);
  _impl_.y_final_.InternalSwap(&other->_impl_.y_final_);
}

::PROTOBUF_NAMESPACE_ID::Metadata ProtobufCubicHermiteSpline::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_spline_2eproto_getter, &descriptor_table_spline_2eproto_once,
      file_level_metadata_spline_2eproto[0]);
}

// ===================================================================

class ProtobufQuinticHermiteSpline::_Internal {
 public:
};

ProtobufQuinticHermiteSpline::ProtobufQuinticHermiteSpline(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:wpi.proto.ProtobufQuinticHermiteSpline)
}
ProtobufQuinticHermiteSpline::ProtobufQuinticHermiteSpline(const ProtobufQuinticHermiteSpline& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  ProtobufQuinticHermiteSpline* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.x_initial_){from._impl_.x_initial_}
    , decltype(_impl_.x_final_){from._impl_.x_final_}
    , decltype(_impl_.y_initial_){from._impl_.y_initial_}
    , decltype(_impl_.y_final_){from._impl_.y_final_}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  // @@protoc_insertion_point(copy_constructor:wpi.proto.ProtobufQuinticHermiteSpline)
}

inline void ProtobufQuinticHermiteSpline::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.x_initial_){arena}
    , decltype(_impl_.x_final_){arena}
    , decltype(_impl_.y_initial_){arena}
    , decltype(_impl_.y_final_){arena}
    , /*decltype(_impl_._cached_size_)*/{}
  };
}

ProtobufQuinticHermiteSpline::~ProtobufQuinticHermiteSpline() {
  // @@protoc_insertion_point(destructor:wpi.proto.ProtobufQuinticHermiteSpline)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void ProtobufQuinticHermiteSpline::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.x_initial_.~RepeatedField();
  _impl_.x_final_.~RepeatedField();
  _impl_.y_initial_.~RepeatedField();
  _impl_.y_final_.~RepeatedField();
}

void ProtobufQuinticHermiteSpline::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void ProtobufQuinticHermiteSpline::Clear() {
// @@protoc_insertion_point(message_clear_start:wpi.proto.ProtobufQuinticHermiteSpline)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.x_initial_.Clear();
  _impl_.x_final_.Clear();
  _impl_.y_initial_.Clear();
  _impl_.y_final_.Clear();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* ProtobufQuinticHermiteSpline::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // repeated double x_initial = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 10)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_x_initial(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 9) {
          _internal_add_x_initial(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double x_final = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 18)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_x_final(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 17) {
          _internal_add_x_final(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double y_initial = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 26)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_y_initial(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 25) {
          _internal_add_y_initial(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
          ptr += sizeof(double);
        } else
          goto handle_unusual;
        continue;
      // repeated double y_final = 4;
      case 4:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 34)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_y_final(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 33) {
          _internal_add_y_final(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
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

uint8_t* ProtobufQuinticHermiteSpline::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:wpi.proto.ProtobufQuinticHermiteSpline)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // repeated double x_initial = 1;
  if (this->_internal_x_initial_size() > 0) {
    target = stream->WriteFixedPacked(1, _internal_x_initial(), target);
  }

  // repeated double x_final = 2;
  if (this->_internal_x_final_size() > 0) {
    target = stream->WriteFixedPacked(2, _internal_x_final(), target);
  }

  // repeated double y_initial = 3;
  if (this->_internal_y_initial_size() > 0) {
    target = stream->WriteFixedPacked(3, _internal_y_initial(), target);
  }

  // repeated double y_final = 4;
  if (this->_internal_y_final_size() > 0) {
    target = stream->WriteFixedPacked(4, _internal_y_final(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:wpi.proto.ProtobufQuinticHermiteSpline)
  return target;
}

size_t ProtobufQuinticHermiteSpline::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:wpi.proto.ProtobufQuinticHermiteSpline)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated double x_initial = 1;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_x_initial_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double x_final = 2;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_x_final_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double y_initial = 3;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_y_initial_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // repeated double y_final = 4;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_y_final_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData ProtobufQuinticHermiteSpline::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    ProtobufQuinticHermiteSpline::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*ProtobufQuinticHermiteSpline::GetClassData() const { return &_class_data_; }


void ProtobufQuinticHermiteSpline::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<ProtobufQuinticHermiteSpline*>(&to_msg);
  auto& from = static_cast<const ProtobufQuinticHermiteSpline&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:wpi.proto.ProtobufQuinticHermiteSpline)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_impl_.x_initial_.MergeFrom(from._impl_.x_initial_);
  _this->_impl_.x_final_.MergeFrom(from._impl_.x_final_);
  _this->_impl_.y_initial_.MergeFrom(from._impl_.y_initial_);
  _this->_impl_.y_final_.MergeFrom(from._impl_.y_final_);
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void ProtobufQuinticHermiteSpline::CopyFrom(const ProtobufQuinticHermiteSpline& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:wpi.proto.ProtobufQuinticHermiteSpline)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ProtobufQuinticHermiteSpline::IsInitialized() const {
  return true;
}

void ProtobufQuinticHermiteSpline::InternalSwap(ProtobufQuinticHermiteSpline* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  _impl_.x_initial_.InternalSwap(&other->_impl_.x_initial_);
  _impl_.x_final_.InternalSwap(&other->_impl_.x_final_);
  _impl_.y_initial_.InternalSwap(&other->_impl_.y_initial_);
  _impl_.y_final_.InternalSwap(&other->_impl_.y_final_);
}

::PROTOBUF_NAMESPACE_ID::Metadata ProtobufQuinticHermiteSpline::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_spline_2eproto_getter, &descriptor_table_spline_2eproto_once,
      file_level_metadata_spline_2eproto[1]);
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace wpi
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::wpi::proto::ProtobufCubicHermiteSpline*
Arena::CreateMaybeMessage< ::wpi::proto::ProtobufCubicHermiteSpline >(Arena* arena) {
  return Arena::CreateMessageInternal< ::wpi::proto::ProtobufCubicHermiteSpline >(arena);
}
template<> PROTOBUF_NOINLINE ::wpi::proto::ProtobufQuinticHermiteSpline*
Arena::CreateMaybeMessage< ::wpi::proto::ProtobufQuinticHermiteSpline >(Arena* arena) {
  return Arena::CreateMessageInternal< ::wpi::proto::ProtobufQuinticHermiteSpline >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>
