// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/* Automatically generated nanopb header */
/* Generated by nanopb-0.4.9 */

#ifndef PB_WPI_PROTO_GEOMETRY3D_NPB_H_INCLUDED
#define PB_WPI_PROTO_GEOMETRY3D_NPB_H_INCLUDED
#include <pb.h>
#include <span>
#include <string_view>

#if PB_PROTO_HEADER_VERSION != 40
#error Regenerate this file with the current version of nanopb generator.
#endif

/* Struct definitions */
typedef struct _wpi_proto_ProtobufTranslation3d {
    double x;
    double y;
    double z;
} wpi_proto_ProtobufTranslation3d;

typedef struct _wpi_proto_ProtobufQuaternion {
    double w;
    double x;
    double y;
    double z;
} wpi_proto_ProtobufQuaternion;

typedef struct _wpi_proto_ProtobufRotation3d {
    pb_callback_t q;
} wpi_proto_ProtobufRotation3d;

typedef struct _wpi_proto_ProtobufPose3d {
    pb_callback_t translation;
    pb_callback_t rotation;
} wpi_proto_ProtobufPose3d;

typedef struct _wpi_proto_ProtobufTransform3d {
    pb_callback_t translation;
    pb_callback_t rotation;
} wpi_proto_ProtobufTransform3d;

typedef struct _wpi_proto_ProtobufTwist3d {
    double dx;
    double dy;
    double dz;
    double rx;
    double ry;
    double rz;
} wpi_proto_ProtobufTwist3d;


/* Initializer values for message structs */
#define wpi_proto_ProtobufTranslation3d_init_default {0, 0, 0}
#define wpi_proto_ProtobufQuaternion_init_default {0, 0, 0, 0}
#define wpi_proto_ProtobufRotation3d_init_default {{{NULL}, NULL}}
#define wpi_proto_ProtobufPose3d_init_default    {{{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufTransform3d_init_default {{{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufTwist3d_init_default   {0, 0, 0, 0, 0, 0}
#define wpi_proto_ProtobufTranslation3d_init_zero {0, 0, 0}
#define wpi_proto_ProtobufQuaternion_init_zero   {0, 0, 0, 0}
#define wpi_proto_ProtobufRotation3d_init_zero   {{{NULL}, NULL}}
#define wpi_proto_ProtobufPose3d_init_zero       {{{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufTransform3d_init_zero  {{{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufTwist3d_init_zero      {0, 0, 0, 0, 0, 0}

/* Field tags (for use in manual encoding/decoding) */
#define wpi_proto_ProtobufTranslation3d_x_tag    1
#define wpi_proto_ProtobufTranslation3d_y_tag    2
#define wpi_proto_ProtobufTranslation3d_z_tag    3
#define wpi_proto_ProtobufQuaternion_w_tag       1
#define wpi_proto_ProtobufQuaternion_x_tag       2
#define wpi_proto_ProtobufQuaternion_y_tag       3
#define wpi_proto_ProtobufQuaternion_z_tag       4
#define wpi_proto_ProtobufRotation3d_q_tag       1
#define wpi_proto_ProtobufPose3d_translation_tag 1
#define wpi_proto_ProtobufPose3d_rotation_tag    2
#define wpi_proto_ProtobufTransform3d_translation_tag 1
#define wpi_proto_ProtobufTransform3d_rotation_tag 2
#define wpi_proto_ProtobufTwist3d_dx_tag         1
#define wpi_proto_ProtobufTwist3d_dy_tag         2
#define wpi_proto_ProtobufTwist3d_dz_tag         3
#define wpi_proto_ProtobufTwist3d_rx_tag         4
#define wpi_proto_ProtobufTwist3d_ry_tag         5
#define wpi_proto_ProtobufTwist3d_rz_tag         6

/* Struct field encoding specification for nanopb */
#define wpi_proto_ProtobufTranslation3d_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   x,                 1) \
X(a, STATIC,   SINGULAR, DOUBLE,   y,                 2) \
X(a, STATIC,   SINGULAR, DOUBLE,   z,                 3)
#define wpi_proto_ProtobufTranslation3d_CALLBACK NULL
#define wpi_proto_ProtobufTranslation3d_DEFAULT NULL

#define wpi_proto_ProtobufQuaternion_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   w,                 1) \
X(a, STATIC,   SINGULAR, DOUBLE,   x,                 2) \
X(a, STATIC,   SINGULAR, DOUBLE,   y,                 3) \
X(a, STATIC,   SINGULAR, DOUBLE,   z,                 4)
#define wpi_proto_ProtobufQuaternion_CALLBACK NULL
#define wpi_proto_ProtobufQuaternion_DEFAULT NULL

#define wpi_proto_ProtobufRotation3d_FIELDLIST(X, a) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  q,                 1)
#define wpi_proto_ProtobufRotation3d_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufRotation3d_DEFAULT NULL
#define wpi_proto_ProtobufRotation3d_q_MSGTYPE wpi_proto_ProtobufQuaternion

#define wpi_proto_ProtobufPose3d_FIELDLIST(X, a) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  translation,       1) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  rotation,          2)
#define wpi_proto_ProtobufPose3d_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufPose3d_DEFAULT NULL
#define wpi_proto_ProtobufPose3d_translation_MSGTYPE wpi_proto_ProtobufTranslation3d
#define wpi_proto_ProtobufPose3d_rotation_MSGTYPE wpi_proto_ProtobufRotation3d

#define wpi_proto_ProtobufTransform3d_FIELDLIST(X, a) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  translation,       1) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  rotation,          2)
#define wpi_proto_ProtobufTransform3d_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufTransform3d_DEFAULT NULL
#define wpi_proto_ProtobufTransform3d_translation_MSGTYPE wpi_proto_ProtobufTranslation3d
#define wpi_proto_ProtobufTransform3d_rotation_MSGTYPE wpi_proto_ProtobufRotation3d

#define wpi_proto_ProtobufTwist3d_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   dx,                1) \
X(a, STATIC,   SINGULAR, DOUBLE,   dy,                2) \
X(a, STATIC,   SINGULAR, DOUBLE,   dz,                3) \
X(a, STATIC,   SINGULAR, DOUBLE,   rx,                4) \
X(a, STATIC,   SINGULAR, DOUBLE,   ry,                5) \
X(a, STATIC,   SINGULAR, DOUBLE,   rz,                6)
#define wpi_proto_ProtobufTwist3d_CALLBACK NULL
#define wpi_proto_ProtobufTwist3d_DEFAULT NULL

const pb_msgdesc_t *get_wpi_proto_ProtobufTranslation3d_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufTranslation3d_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufTranslation3d_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufQuaternion_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufQuaternion_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufQuaternion_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufRotation3d_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufRotation3d_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufRotation3d_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufPose3d_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufPose3d_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufPose3d_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufTransform3d_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufTransform3d_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufTransform3d_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufTwist3d_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufTwist3d_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufTwist3d_name(void);

/* Maximum encoded size of messages (where known) */
/* wpi_proto_ProtobufRotation3d_size depends on runtime parameters */
/* wpi_proto_ProtobufPose3d_size depends on runtime parameters */
/* wpi_proto_ProtobufTransform3d_size depends on runtime parameters */
#define WPI_PROTO_GEOMETRY3D_NPB_H_MAX_SIZE      wpi_proto_ProtobufTwist3d_size
#define wpi_proto_ProtobufQuaternion_size        36
#define wpi_proto_ProtobufTranslation3d_size     27
#define wpi_proto_ProtobufTwist3d_size           54


#endif
