// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <arm_neon.h>

#include <cstddef>
#include <cstring>

// SimdLib.h
#define SIMD_INLINE inline __attribute__((always_inline))

// SimdMemory.h
namespace Simd {
SIMD_INLINE size_t DivHi(size_t value, size_t divider) {
  return (value + divider - 1) / divider;
}

SIMD_INLINE size_t Pow2Hi(size_t value) {
  size_t pow2 = 1;
  for (; pow2 < value; pow2 *= 2)
    ;
  return pow2;
}

SIMD_INLINE size_t AlignHiAny(size_t size, size_t align) {
  return (size + align - 1) / align * align;
}

SIMD_INLINE size_t AlignLoAny(size_t size, size_t align) {
  return size / align * align;
}

SIMD_INLINE size_t AlignHi(size_t size, size_t align) {
  return (size + align - 1) & ~(align - 1);
}

SIMD_INLINE void* AlignHi(const void* ptr, size_t align) {
  return (void*)((((size_t)ptr) + align - 1) & ~(align - 1));
}

SIMD_INLINE size_t AlignLo(size_t size, size_t align) {
  return size & ~(align - 1);
}

SIMD_INLINE void* AlignLo(const void* ptr, size_t align) {
  return (void*)(((size_t)ptr) & ~(align - 1));
}

SIMD_INLINE bool Aligned(size_t size, size_t align) {
  return size == AlignLo(size, align);
}

SIMD_INLINE bool Aligned(const void* ptr, size_t align) {
  return ptr == AlignLo(ptr, align);
}
}  // namespace Simd
namespace Simd::Neon {
SIMD_INLINE bool Aligned(size_t size, size_t align = sizeof(uint8x16_t)) {
  return Simd::Aligned(size, align);
}

SIMD_INLINE bool Aligned(const void* ptr, size_t align = sizeof(uint8x16_t)) {
  return Simd::Aligned(ptr, align);
}
}  // namespace Simd::Neon
// SimdInit.h
#define SIMD_VEC_SET1_EPI8(a) \
  { a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a }
#define SIMD_VEC_SET1_EPI16(a) \
  { a, a, a, a, a, a, a, a }
#define SIMD_VEC_SET1_EPI32(a) \
  { a, a, a, a }
#define SIMD_VEC_SETR_EPI32(a0, a1, a2, a3) \
  { a0, a1, a2, a3 }
#define SIMD_VEC_SET1_EPI64(a) \
  { a, a }
// SimdConst.h
namespace Simd::Neon {
const size_t A = sizeof(uint8x16_t);
const size_t DA = 2 * A;
const size_t QA = 4 * A;
const size_t OA = 8 * A;
const size_t HA = A / 2;

const size_t F = sizeof(float32x4_t) / sizeof(float);
const size_t DF = 2 * F;
const size_t QF = 4 * F;
const size_t HF = F / 2;
}  // namespace Simd::Neon

// SimdDefs.h
#define SIMD_ALIGN 16

// SimdLoad.h
namespace Simd::Neon {
template <bool align>
SIMD_INLINE uint8x8x4_t LoadHalf4(const uint8_t* p);

template <>
SIMD_INLINE uint8x8x4_t LoadHalf4<false>(const uint8_t* p) {
#if defined(__GNUC__) && SIMD_NEON_PREFECH_SIZE
  __builtin_prefetch(p + SIMD_NEON_PREFECH_SIZE);
#endif
  return vld4_u8(p);
}

template <>
SIMD_INLINE uint8x8x4_t LoadHalf4<true>(const uint8_t* p) {
#if defined(__GNUC__)
#if SIMD_NEON_PREFECH_SIZE
  __builtin_prefetch(p + SIMD_NEON_PREFECH_SIZE);
#endif
  uint8_t* _p = (uint8_t*)__builtin_assume_aligned(p, 8);
  return vld4_u8(_p);
#elif defined(_MSC_VER)
  return vld4_u8_ex(p, 64);
#else
  return vld4_u8(p);
#endif
}

template <bool align>
SIMD_INLINE uint8x16x4_t Load4(const uint8_t* p);

template <>
SIMD_INLINE uint8x16x4_t Load4<false>(const uint8_t* p) {
#if defined(__GNUC__) && SIMD_NEON_PREFECH_SIZE
  __builtin_prefetch(p + SIMD_NEON_PREFECH_SIZE);
#endif
  return vld4q_u8(p);
}

template <>
SIMD_INLINE uint8x16x4_t Load4<true>(const uint8_t* p) {
#if defined(__GNUC__)
#if SIMD_NEON_PREFECH_SIZE
  __builtin_prefetch(p + SIMD_NEON_PREFECH_SIZE);
#endif
  uint8_t* _p = (uint8_t*)__builtin_assume_aligned(p, 16);
  return vld4q_u8(_p);
#elif defined(_MSC_VER)
  return vld4q_u8_ex(p, 128);
#else
  return vld4q_u8(p);
#endif
}

// SimdStore.h
template <bool align>
SIMD_INLINE void Store4(uint8_t* p, uint8x16x4_t a);

template <>
SIMD_INLINE void Store4<false>(uint8_t* p, uint8x16x4_t a) {
  vst4q_u8(p, a);
}

template <>
SIMD_INLINE void Store4<true>(uint8_t* p, uint8x16x4_t a) {
#if defined(__GNUC__)
  uint8_t* _p = (uint8_t*)__builtin_assume_aligned(p, 16);
  vst4q_u8(_p, a);
#elif defined(_MSC_VER)
  vst4q_u8_ex(p, a, 128);
#else
  vst4q_u8(p, a);
#endif
}

template <bool align>
SIMD_INLINE void Store4(uint8_t* p, uint8x8x4_t a);

template <>
SIMD_INLINE void Store4<false>(uint8_t* p, uint8x8x4_t a) {
  vst4_u8(p, a);
}

template <>
SIMD_INLINE void Store4<true>(uint8_t* p, uint8x8x4_t a) {
#if defined(__GNUC__)
  uint8_t* _p = (uint8_t*)__builtin_assume_aligned(p, 8);
  vst4_u8(_p, a);
#elif defined(_MSC_VER)
  vst4_u8_ex(p, a, 64);
#else
  vst4_u8(p, a);
#endif
}

}  // namespace Simd::Neon

using namespace Simd::Neon;
void RGBToBGR_16(uint8_t* rgb, uint8_t* bgr) {
  uint8x16x4_t _rgb = Load4<false>(rgb);
  uint8x16_t tmp = _rgb.val[0];  // tmp = r
  _rgb.val[0] = _rgb.val[2];     // r = r;
  _rgb.val[2] = tmp;
  Store4<false>(bgr, _rgb);
}

using namespace Simd::Neon;
void RGBToBGR_8(uint8_t* rgb, uint8_t* bgr) {
  uint8x8x4_t _rgb = LoadHalf4<false>(rgb);
  uint8x8_t tmp = _rgb.val[0];  // tmp = r
  _rgb.val[0] = _rgb.val[2];    // r = r;
  _rgb.val[2] = tmp;
  Store4<false>(bgr, _rgb);
}
