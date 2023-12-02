// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wpimath.proto

#include "wpimath.pb.h"

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
PROTOBUF_CONSTEXPR ProtobufMatrix::ProtobufMatrix(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.data_)*/{}
  , /*decltype(_impl_.num_rows_)*/0u
  , /*decltype(_impl_.num_cols_)*/0u
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct ProtobufMatrixDefaultTypeInternal {
  PROTOBUF_CONSTEXPR ProtobufMatrixDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~ProtobufMatrixDefaultTypeInternal() {}
  union {
    ProtobufMatrix _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 ProtobufMatrixDefaultTypeInternal _ProtobufMatrix_default_instance_;
PROTOBUF_CONSTEXPR ProtobufVector::ProtobufVector(
    ::_pbi::ConstantInitialized): _impl_{
    /*decltype(_impl_.rows_)*/{}
  , /*decltype(_impl_._cached_size_)*/{}} {}
struct ProtobufVectorDefaultTypeInternal {
  PROTOBUF_CONSTEXPR ProtobufVectorDefaultTypeInternal()
      : _instance(::_pbi::ConstantInitialized{}) {}
  ~ProtobufVectorDefaultTypeInternal() {}
  union {
    ProtobufVector _instance;
  };
};
PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 ProtobufVectorDefaultTypeInternal _ProtobufVector_default_instance_;
}  // namespace proto
}  // namespace wpi
static ::_pb::Metadata file_level_metadata_wpimath_2eproto[2];
static constexpr ::_pb::EnumDescriptor const** file_level_enum_descriptors_wpimath_2eproto = nullptr;
static constexpr ::_pb::ServiceDescriptor const** file_level_service_descriptors_wpimath_2eproto = nullptr;

const uint32_t TableStruct_wpimath_2eproto::offsets[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufMatrix, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufMatrix, _impl_.num_rows_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufMatrix, _impl_.num_cols_),
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufMatrix, _impl_.data_),
  ~0u,  // no _has_bits_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufVector, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  ~0u,  // no _inlined_string_donated_
  PROTOBUF_FIELD_OFFSET(::wpi::proto::ProtobufVector, _impl_.rows_),
};
static const ::_pbi::MigrationSchema schemas[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  { 0, -1, -1, sizeof(::wpi::proto::ProtobufMatrix)},
  { 9, -1, -1, sizeof(::wpi::proto::ProtobufVector)},
};

static const ::_pb::Message* const file_default_instances[] = {
  &::wpi::proto::_ProtobufMatrix_default_instance_._instance,
  &::wpi::proto::_ProtobufVector_default_instance_._instance,
};

const char descriptor_table_protodef_wpimath_2eproto[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) =
  "\n\rwpimath.proto\022\twpi.proto\"B\n\016ProtobufMa"
  "trix\022\020\n\010num_rows\030\001 \001(\r\022\020\n\010num_cols\030\002 \001(\r"
  "\022\014\n\004data\030\003 \003(\001\"\036\n\016ProtobufVector\022\014\n\004rows"
  "\030\001 \003(\001B\032\n\030edu.wpi.first.math.protob\006prot"
  "o3"
  ;
static ::_pbi::once_flag descriptor_table_wpimath_2eproto_once;
const ::_pbi::DescriptorTable descriptor_table_wpimath_2eproto = {
    false, false, 162, descriptor_table_protodef_wpimath_2eproto,
    "wpimath.proto",
    &descriptor_table_wpimath_2eproto_once, nullptr, 0, 2,
    schemas, file_default_instances, TableStruct_wpimath_2eproto::offsets,
    file_level_metadata_wpimath_2eproto, file_level_enum_descriptors_wpimath_2eproto,
    file_level_service_descriptors_wpimath_2eproto,
};
PROTOBUF_ATTRIBUTE_WEAK const ::_pbi::DescriptorTable* descriptor_table_wpimath_2eproto_getter() {
  return &descriptor_table_wpimath_2eproto;
}

// Force running AddDescriptors() at dynamic initialization time.
PROTOBUF_ATTRIBUTE_INIT_PRIORITY2 static ::_pbi::AddDescriptorsRunner dynamic_init_dummy_wpimath_2eproto(&descriptor_table_wpimath_2eproto);
namespace wpi {
namespace proto {

// ===================================================================

class ProtobufMatrix::_Internal {
 public:
};

ProtobufMatrix::ProtobufMatrix(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:wpi.proto.ProtobufMatrix)
}
ProtobufMatrix::ProtobufMatrix(const ProtobufMatrix& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  ProtobufMatrix* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.data_){from._impl_.data_}
    , decltype(_impl_.num_rows_){}
    , decltype(_impl_.num_cols_){}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  ::memcpy(&_impl_.num_rows_, &from._impl_.num_rows_,
    static_cast<size_t>(reinterpret_cast<char*>(&_impl_.num_cols_) -
    reinterpret_cast<char*>(&_impl_.num_rows_)) + sizeof(_impl_.num_cols_));
  // @@protoc_insertion_point(copy_constructor:wpi.proto.ProtobufMatrix)
}

inline void ProtobufMatrix::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.data_){arena}
    , decltype(_impl_.num_rows_){0u}
    , decltype(_impl_.num_cols_){0u}
    , /*decltype(_impl_._cached_size_)*/{}
  };
}

ProtobufMatrix::~ProtobufMatrix() {
  // @@protoc_insertion_point(destructor:wpi.proto.ProtobufMatrix)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void ProtobufMatrix::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.data_.~RepeatedField();
}

void ProtobufMatrix::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void ProtobufMatrix::Clear() {
// @@protoc_insertion_point(message_clear_start:wpi.proto.ProtobufMatrix)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.data_.Clear();
  ::memset(&_impl_.num_rows_, 0, static_cast<size_t>(
      reinterpret_cast<char*>(&_impl_.num_cols_) -
      reinterpret_cast<char*>(&_impl_.num_rows_)) + sizeof(_impl_.num_cols_));
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* ProtobufMatrix::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // uint32 num_rows = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 8)) {
          _impl_.num_rows_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else
          goto handle_unusual;
        continue;
      // uint32 num_cols = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 16)) {
          _impl_.num_cols_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else
          goto handle_unusual;
        continue;
      // repeated double data = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 26)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_data(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 25) {
          _internal_add_data(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
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

uint8_t* ProtobufMatrix::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:wpi.proto.ProtobufMatrix)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // uint32 num_rows = 1;
  if (this->_internal_num_rows() != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteUInt32ToArray(1, this->_internal_num_rows(), target);
  }

  // uint32 num_cols = 2;
  if (this->_internal_num_cols() != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteUInt32ToArray(2, this->_internal_num_cols(), target);
  }

  // repeated double data = 3;
  if (this->_internal_data_size() > 0) {
    target = stream->WriteFixedPacked(3, _internal_data(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:wpi.proto.ProtobufMatrix)
  return target;
}

size_t ProtobufMatrix::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:wpi.proto.ProtobufMatrix)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated double data = 3;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_data_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  // uint32 num_rows = 1;
  if (this->_internal_num_rows() != 0) {
    total_size += ::_pbi::WireFormatLite::UInt32SizePlusOne(this->_internal_num_rows());
  }

  // uint32 num_cols = 2;
  if (this->_internal_num_cols() != 0) {
    total_size += ::_pbi::WireFormatLite::UInt32SizePlusOne(this->_internal_num_cols());
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData ProtobufMatrix::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    ProtobufMatrix::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*ProtobufMatrix::GetClassData() const { return &_class_data_; }


void ProtobufMatrix::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<ProtobufMatrix*>(&to_msg);
  auto& from = static_cast<const ProtobufMatrix&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:wpi.proto.ProtobufMatrix)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_impl_.data_.MergeFrom(from._impl_.data_);
  if (from._internal_num_rows() != 0) {
    _this->_internal_set_num_rows(from._internal_num_rows());
  }
  if (from._internal_num_cols() != 0) {
    _this->_internal_set_num_cols(from._internal_num_cols());
  }
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void ProtobufMatrix::CopyFrom(const ProtobufMatrix& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:wpi.proto.ProtobufMatrix)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ProtobufMatrix::IsInitialized() const {
  return true;
}

void ProtobufMatrix::InternalSwap(ProtobufMatrix* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  _impl_.data_.InternalSwap(&other->_impl_.data_);
  ::PROTOBUF_NAMESPACE_ID::internal::memswap<
      PROTOBUF_FIELD_OFFSET(ProtobufMatrix, _impl_.num_cols_)
      + sizeof(ProtobufMatrix::_impl_.num_cols_)
      - PROTOBUF_FIELD_OFFSET(ProtobufMatrix, _impl_.num_rows_)>(
          reinterpret_cast<char*>(&_impl_.num_rows_),
          reinterpret_cast<char*>(&other->_impl_.num_rows_));
}

::PROTOBUF_NAMESPACE_ID::Metadata ProtobufMatrix::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_wpimath_2eproto_getter, &descriptor_table_wpimath_2eproto_once,
      file_level_metadata_wpimath_2eproto[0]);
}

// ===================================================================

class ProtobufVector::_Internal {
 public:
};

ProtobufVector::ProtobufVector(::PROTOBUF_NAMESPACE_ID::Arena* arena,
                         bool is_message_owned)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena, is_message_owned) {
  SharedCtor(arena, is_message_owned);
  // @@protoc_insertion_point(arena_constructor:wpi.proto.ProtobufVector)
}
ProtobufVector::ProtobufVector(const ProtobufVector& from)
  : ::PROTOBUF_NAMESPACE_ID::Message() {
  ProtobufVector* const _this = this; (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.rows_){from._impl_.rows_}
    , /*decltype(_impl_._cached_size_)*/{}};

  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  // @@protoc_insertion_point(copy_constructor:wpi.proto.ProtobufVector)
}

inline void ProtobufVector::SharedCtor(
    ::_pb::Arena* arena, bool is_message_owned) {
  (void)arena;
  (void)is_message_owned;
  new (&_impl_) Impl_{
      decltype(_impl_.rows_){arena}
    , /*decltype(_impl_._cached_size_)*/{}
  };
}

ProtobufVector::~ProtobufVector() {
  // @@protoc_insertion_point(destructor:wpi.proto.ProtobufVector)
  if (auto *arena = _internal_metadata_.DeleteReturnArena<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>()) {
  (void)arena;
    return;
  }
  SharedDtor();
}

inline void ProtobufVector::SharedDtor() {
  GOOGLE_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.rows_.~RepeatedField();
}

void ProtobufVector::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

void ProtobufVector::Clear() {
// @@protoc_insertion_point(message_clear_start:wpi.proto.ProtobufVector)
  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.rows_.Clear();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* ProtobufVector::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    uint32_t tag;
    ptr = ::_pbi::ReadTag(ptr, &tag);
    switch (tag >> 3) {
      // repeated double rows = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<uint8_t>(tag) == 10)) {
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::PackedDoubleParser(_internal_mutable_rows(), ptr, ctx);
          CHK_(ptr);
        } else if (static_cast<uint8_t>(tag) == 9) {
          _internal_add_rows(::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<double>(ptr));
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

uint8_t* ProtobufVector::_InternalSerialize(
    uint8_t* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:wpi.proto.ProtobufVector)
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  // repeated double rows = 1;
  if (this->_internal_rows_size() > 0) {
    target = stream->WriteFixedPacked(1, _internal_rows(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::_pbi::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:wpi.proto.ProtobufVector)
  return target;
}

size_t ProtobufVector::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:wpi.proto.ProtobufVector)
  size_t total_size = 0;

  uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated double rows = 1;
  {
    unsigned int count = static_cast<unsigned int>(this->_internal_rows_size());
    size_t data_size = 8UL * count;
    if (data_size > 0) {
      total_size += 1 +
        ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
    }
    total_size += data_size;
  }

  return MaybeComputeUnknownFieldsSize(total_size, &_impl_._cached_size_);
}

const ::PROTOBUF_NAMESPACE_ID::Message::ClassData ProtobufVector::_class_data_ = {
    ::PROTOBUF_NAMESPACE_ID::Message::CopyWithSourceCheck,
    ProtobufVector::MergeImpl
};
const ::PROTOBUF_NAMESPACE_ID::Message::ClassData*ProtobufVector::GetClassData() const { return &_class_data_; }


void ProtobufVector::MergeImpl(::PROTOBUF_NAMESPACE_ID::Message& to_msg, const ::PROTOBUF_NAMESPACE_ID::Message& from_msg) {
  auto* const _this = static_cast<ProtobufVector*>(&to_msg);
  auto& from = static_cast<const ProtobufVector&>(from_msg);
  // @@protoc_insertion_point(class_specific_merge_from_start:wpi.proto.ProtobufVector)
  GOOGLE_DCHECK_NE(&from, _this);
  uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_impl_.rows_.MergeFrom(from._impl_.rows_);
  _this->_internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
}

void ProtobufVector::CopyFrom(const ProtobufVector& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:wpi.proto.ProtobufVector)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ProtobufVector::IsInitialized() const {
  return true;
}

void ProtobufVector::InternalSwap(ProtobufVector* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  _impl_.rows_.InternalSwap(&other->_impl_.rows_);
}

::PROTOBUF_NAMESPACE_ID::Metadata ProtobufVector::GetMetadata() const {
  return ::_pbi::AssignDescriptors(
      &descriptor_table_wpimath_2eproto_getter, &descriptor_table_wpimath_2eproto_once,
      file_level_metadata_wpimath_2eproto[1]);
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace wpi
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::wpi::proto::ProtobufMatrix*
Arena::CreateMaybeMessage< ::wpi::proto::ProtobufMatrix >(Arena* arena) {
  return Arena::CreateMessageInternal< ::wpi::proto::ProtobufMatrix >(arena);
}
template<> PROTOBUF_NOINLINE ::wpi::proto::ProtobufVector*
Arena::CreateMaybeMessage< ::wpi::proto::ProtobufVector >(Arena* arena) {
  return Arena::CreateMessageInternal< ::wpi::proto::ProtobufVector >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>
