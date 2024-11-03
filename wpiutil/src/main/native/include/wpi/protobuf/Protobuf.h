// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <stdint.h>

#include <concepts>
#include <optional>
#include <span>
#include <string>
#include <string_view>
#include <utility>
#include <vector>

#include "pb.h"
#include "pb_decode.h"
#include "pb_encode.h"
#include "wpi/array.h"
#include "wpi/function_ref.h"

namespace wpi {

template <typename T>
class SmallVectorImpl;

/**
 * Protobuf serialization template. Unspecialized class has no members; only
 * specializations of this class are useful, and only if they meet the
 * ProtobufSerializable concept.
 *
 * @tparam T type to serialize/deserialize
 */
template <typename T>
struct Protobuf {};

namespace detail {
using SmallVectorType = wpi::SmallVectorImpl<uint8_t>;
using StdVectorType = std::vector<uint8_t>;
bool WriteFromSmallVector(pb_ostream_t* stream, const pb_byte_t* buf,
                          size_t count);

bool WriteFromStdVector(pb_ostream_t* stream, const pb_byte_t* buf,
                        size_t count);
}  // namespace detail

/**
 * Class for wrapping a nanopb istream.
 */
template <typename T>
class ProtoInputStream {
 public:
  /**
   * Constructs a nanopb istream from an existing istream object.
   * Generally used internally for decoding submessages
   *
   * @param[in] stream the nanopb istream
   */
  explicit ProtoInputStream(pb_istream_t* stream)
      : m_streamMsg{stream},
        m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {}

  /**
   * Constructs a nanopb istream from a buffer.
   *
   * @param[in] stream the stream buffer
   */
  explicit ProtoInputStream(std::span<const uint8_t> stream)
      : m_streamLocal{pb_istream_from_buffer(
            reinterpret_cast<const pb_byte_t*>(stream.data()), stream.size())},
        m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {}

  /**
   * Gets the backing nanopb stream object.
   *
   * @return nanopb stream
   */
  pb_istream_t* Stream() noexcept {
    return m_streamMsg ? m_streamMsg : &m_streamLocal;
  }

  /**
   * Gets the nanopb message descriptor
   *
   * @return the nanopb message descriptor
   */
  const pb_msgdesc_t* MsgDesc() const noexcept { return m_msgDesc; }

  /**
   * Decodes a protobuf. Flags are the same flags passed to pb_decode_ex
   *
   * std::is_same_v<Protobuf<std::remove_cvref_t<T>>::MessageStruct, T>
   *
   * @param[in] msg The message to send
   * @param[in] flags Flags to pass
   * @return true if decoding was successful, false otherwise
   */
  bool Decode(Protobuf<std::remove_cvref_t<T>>::MessageStruct& msg,
              unsigned int flags = 0) {
    return pb_decode_ex(Stream(), m_msgDesc, &msg, flags);
  }

 private:
  pb_istream_t m_streamLocal;
  pb_istream_t* m_streamMsg{nullptr};
  const pb_msgdesc_t* m_msgDesc;
};

template <typename T>
class ProtoOutputStream {
 public:
  explicit ProtoOutputStream(pb_ostream_t* stream)
      : m_streamMsg{stream},
        m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {}

  explicit ProtoOutputStream(detail::SmallVectorType& out)
      : m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {
    m_streamLocal.callback = detail::WriteFromSmallVector;
    m_streamLocal.state = &out;
    m_streamLocal.max_size = SIZE_MAX;
    m_streamLocal.bytes_written = 0;
    m_streamLocal.errmsg = nullptr;
  }

  explicit ProtoOutputStream(detail::StdVectorType& out)
      : m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {
    m_streamLocal.callback = detail::WriteFromStdVector;
    m_streamLocal.state = &out;
    m_streamLocal.max_size = SIZE_MAX;
    m_streamLocal.bytes_written = 0;
    m_streamLocal.errmsg = nullptr;
  }

  ProtoOutputStream()
      : m_msgDesc{Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor()} {}

  pb_ostream_t* Stream() noexcept {
    return m_streamMsg ? m_streamMsg : &m_streamLocal;
  }
  bool IsSubmessage() const noexcept { return m_streamMsg; }
  const pb_msgdesc_t* MsgDesc() const noexcept { return m_msgDesc; }

  bool Encode(const Protobuf<std::remove_cvref_t<T>>::MessageStruct& msg) {
    if (m_streamMsg) {
      return pb_encode_submessage(m_streamMsg, m_msgDesc, &msg);
    }
    return pb_encode(&m_streamLocal, m_msgDesc, &msg);
  }

 private:
  pb_ostream_t m_streamLocal;
  pb_ostream_t* m_streamMsg{nullptr};
  const pb_msgdesc_t* m_msgDesc;
};

/**
 * Specifies that a type is capable of protobuf serialization and
 * deserialization.
 *
 * This is designed for serializing complex flexible data structures using
 * code generated from a .proto file. Serialization consists of writing
 * values into a mutable protobuf Message and deserialization consists of
 * reading values from an immutable protobuf Message.
 *
 * Implementations must define a template specialization for wpi::Protobuf with
 * T being the type that is being serialized/deserialized, with the following
 * static members (as enforced by this concept):
 * - google::protobuf::Message* New(google::protobuf::Arena*): create a protobuf
 *   message
 * - T Unpack(const google::protobuf::Message&): function for deserialization
 * - void Pack(google::protobuf::Message*, T&& value): function for
 *   serialization
 *
 * To avoid pulling in the protobuf headers, these functions use
 * google::protobuf::Message instead of a more specific type; implementations
 * will need to static_cast to the correct type as created by New().
 *
 * Additionally: In a static block, call StructRegistry.registerClass() to
 * register the class
 */
template <typename T>
concept ProtobufSerializable = requires(
    wpi::ProtoOutputStream<std::remove_cvref_t<T>>& ostream,
    wpi::ProtoInputStream<std::remove_cvref_t<T>>& istream, const T& value) {
  typename Protobuf<typename std::remove_cvref_t<T>>;
  typename Protobuf<typename std::remove_cvref_t<T>>::MessageStruct;
  {
    Protobuf<typename std::remove_cvref_t<T>>::Unpack(istream)
  } -> std::same_as<std::optional<typename std::remove_cvref_t<T>>>;
  {
    Protobuf<typename std::remove_cvref_t<T>>::Pack(ostream, value)
  } -> std::same_as<bool>;
};

/**
 * Specifies that a type is capable of in-place protobuf deserialization.
 *
 * In addition to meeting ProtobufSerializable, implementations must define a
 * wpi::Protobuf<T> static member
 * `void UnpackInto(T*, const google::protobuf::Message&)` to update the
 * pointed-to T with the contents of the message.
 */
template <typename T>
concept MutableProtobufSerializable =
    ProtobufSerializable<T> &&
    requires(T* out, wpi::ProtoInputStream<T>& istream) {
      Protobuf<typename std::remove_cvref_t<T>>::UnpackInto(out, istream);
    };

namespace detail {
std::string GetTypeString(const pb_msgdesc_t* msg);
void ForEachProtobufDescriptor(
    const pb_msgdesc_t* msg,
    function_ref<bool(std::string_view filename)> wants,
    function_ref<void(std::string_view filename,
                      std::span<const uint8_t> descriptor)>
        fn);
}  // namespace detail

/**
 * Owning wrapper (ala std::unique_ptr) for google::protobuf::Message* that does
 * not require the protobuf headers be included. Note this object is not thread
 * safe; users of this object are required to provide any necessary thread
 * safety.
 *
 * @tparam T serialized object type
 */
template <ProtobufSerializable T>
class ProtobufMessage {
 public:
  /**
   * Unpacks from a byte array.
   *
   * @param data byte array
   * @return Optional; empty if parsing failed
   */
  std::optional<std::remove_cvref_t<T>> Unpack(std::span<const uint8_t> data) {
    ProtoInputStream<std::remove_cvref_t<T>> stream{data};
    return Protobuf<std::remove_cvref_t<T>>::Unpack(stream);
  }

  /**
   * Unpacks from a byte array into an existing object.
   *
   * @param[out] out output object
   * @param[in] data byte array
   * @return true if successful
   */
  bool UnpackInto(T* out, std::span<const uint8_t> data) {
    if constexpr (MutableProtobufSerializable<T>) {
      ProtoInputStream<std::remove_cvref_t<T>> stream{data};
      return Protobuf<std::remove_cvref_t<T>>::UnpackInto(out, stream);
    } else {
      auto unpacked = Unpack(data);
      if (!unpacked) {
        return false;
      }
      *out = std::move(unpacked.value());
      return true;
    }
  }

  /**
   * Packs object into a SmallVector.
   *
   * @param[out] out output bytes
   * @param[in] value value
   * @return true if successful
   */
  bool Pack(wpi::SmallVectorImpl<uint8_t>& out, const T& value) {
    ProtoOutputStream<std::remove_cvref_t<T>> stream{out};
    return Protobuf<std::remove_cvref_t<T>>::Pack(stream, value);
  }

  /**
   * Packs object into a std::vector.
   *
   * @param[out] out output bytes
   * @param[in] value value
   * @return true if successful
   */
  bool Pack(std::vector<uint8_t>& out, const T& value) {
    ProtoOutputStream<std::remove_cvref_t<T>> stream{out};
    return Protobuf<std::remove_cvref_t<T>>::Pack(stream, value);
  }

  /**
   * Gets the type string for the message.
   *
   * @return type string
   */
  std::string GetTypeString() const {
    return detail::GetTypeString(
        Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor());
  }

  /**
   * Loops over all protobuf descriptors including nested/referenced
   * descriptors.
   *
   * @param exists function that returns false if fn should be called for the
   *               given type string
   * @param fn function to call for each descriptor
   */
  void ForEachProtobufDescriptor(
      function_ref<bool(std::string_view filename)> exists,
      function_ref<void(std::string_view filename,
                        std::span<const uint8_t> descriptor)>
          fn) {
    detail::ForEachProtobufDescriptor(
        Protobuf<std::remove_cvref_t<T>>::MessageStruct::msg_descriptor(), exists, fn);
  }
};

}  // namespace wpi
