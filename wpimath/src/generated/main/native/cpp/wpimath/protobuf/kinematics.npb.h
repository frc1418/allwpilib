// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/* Automatically generated nanopb header */
/* Generated by nanopb-0.4.9 */

#ifndef PB_WPI_PROTO_KINEMATICS_NPB_H_INCLUDED
#define PB_WPI_PROTO_KINEMATICS_NPB_H_INCLUDED
#include <pb.h>
#include <span>
#include <string_view>
#include "geometry2d.npb.h"

#if PB_PROTO_HEADER_VERSION != 40
#error Regenerate this file with the current version of nanopb generator.
#endif

/* Struct definitions */
typedef struct _wpi_proto_ProtobufChassisSpeeds {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double vx;
    double vy;
    double omega;
} wpi_proto_ProtobufChassisSpeeds;

typedef struct _wpi_proto_ProtobufDifferentialDriveKinematics {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double track_width;
} wpi_proto_ProtobufDifferentialDriveKinematics;

typedef struct _wpi_proto_ProtobufDifferentialDriveWheelSpeeds {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double left;
    double right;
} wpi_proto_ProtobufDifferentialDriveWheelSpeeds;

typedef struct _wpi_proto_ProtobufDifferentialDriveWheelPositions {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double left;
    double right;
} wpi_proto_ProtobufDifferentialDriveWheelPositions;

typedef struct _wpi_proto_ProtobufMecanumDriveKinematics {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    pb_callback_t front_left;
    pb_callback_t front_right;
    pb_callback_t rear_left;
    pb_callback_t rear_right;
} wpi_proto_ProtobufMecanumDriveKinematics;

typedef struct _wpi_proto_ProtobufMecanumDriveWheelPositions {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double front_left;
    double front_right;
    double rear_left;
    double rear_right;
} wpi_proto_ProtobufMecanumDriveWheelPositions;

typedef struct _wpi_proto_ProtobufMecanumDriveWheelSpeeds {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double front_left;
    double front_right;
    double rear_left;
    double rear_right;
} wpi_proto_ProtobufMecanumDriveWheelSpeeds;

typedef struct _wpi_proto_ProtobufSwerveDriveKinematics {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    pb_callback_t modules;
} wpi_proto_ProtobufSwerveDriveKinematics;

typedef struct _wpi_proto_ProtobufSwerveModulePosition {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double distance;
    pb_callback_t angle;
} wpi_proto_ProtobufSwerveModulePosition;

typedef struct _wpi_proto_ProtobufSwerveModuleState {
    static const pb_msgdesc_t* msg_descriptor(void) noexcept;
    static std::string_view msg_name(void) noexcept;
    static pb_filedesc_t file_descriptor(void) noexcept;

    double speed;
    pb_callback_t angle;
} wpi_proto_ProtobufSwerveModuleState;


/* Initializer values for message structs */
#define wpi_proto_ProtobufChassisSpeeds_init_default {0, 0, 0}
#define wpi_proto_ProtobufDifferentialDriveKinematics_init_default {0}
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_init_default {0, 0}
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_init_default {0, 0}
#define wpi_proto_ProtobufMecanumDriveKinematics_init_default {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufMecanumDriveWheelPositions_init_default {0, 0, 0, 0}
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_init_default {0, 0, 0, 0}
#define wpi_proto_ProtobufSwerveDriveKinematics_init_default {{{NULL}, NULL}}
#define wpi_proto_ProtobufSwerveModulePosition_init_default {0, {{NULL}, NULL}}
#define wpi_proto_ProtobufSwerveModuleState_init_default {0, {{NULL}, NULL}}
#define wpi_proto_ProtobufChassisSpeeds_init_zero {0, 0, 0}
#define wpi_proto_ProtobufDifferentialDriveKinematics_init_zero {0}
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_init_zero {0, 0}
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_init_zero {0, 0}
#define wpi_proto_ProtobufMecanumDriveKinematics_init_zero {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufMecanumDriveWheelPositions_init_zero {0, 0, 0, 0}
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_init_zero {0, 0, 0, 0}
#define wpi_proto_ProtobufSwerveDriveKinematics_init_zero {{{NULL}, NULL}}
#define wpi_proto_ProtobufSwerveModulePosition_init_zero {0, {{NULL}, NULL}}
#define wpi_proto_ProtobufSwerveModuleState_init_zero {0, {{NULL}, NULL}}

/* Field tags (for use in manual encoding/decoding) */
#define wpi_proto_ProtobufChassisSpeeds_vx_tag   1
#define wpi_proto_ProtobufChassisSpeeds_vy_tag   2
#define wpi_proto_ProtobufChassisSpeeds_omega_tag 3
#define wpi_proto_ProtobufDifferentialDriveKinematics_track_width_tag 1
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_left_tag 1
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_right_tag 2
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_left_tag 1
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_right_tag 2
#define wpi_proto_ProtobufMecanumDriveKinematics_front_left_tag 1
#define wpi_proto_ProtobufMecanumDriveKinematics_front_right_tag 2
#define wpi_proto_ProtobufMecanumDriveKinematics_rear_left_tag 3
#define wpi_proto_ProtobufMecanumDriveKinematics_rear_right_tag 4
#define wpi_proto_ProtobufMecanumDriveWheelPositions_front_left_tag 1
#define wpi_proto_ProtobufMecanumDriveWheelPositions_front_right_tag 2
#define wpi_proto_ProtobufMecanumDriveWheelPositions_rear_left_tag 3
#define wpi_proto_ProtobufMecanumDriveWheelPositions_rear_right_tag 4
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_front_left_tag 1
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_front_right_tag 2
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_rear_left_tag 3
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_rear_right_tag 4
#define wpi_proto_ProtobufSwerveDriveKinematics_modules_tag 1
#define wpi_proto_ProtobufSwerveModulePosition_distance_tag 1
#define wpi_proto_ProtobufSwerveModulePosition_angle_tag 2
#define wpi_proto_ProtobufSwerveModuleState_speed_tag 1
#define wpi_proto_ProtobufSwerveModuleState_angle_tag 2

/* Struct field encoding specification for nanopb */
#define wpi_proto_ProtobufChassisSpeeds_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   vx,                1) \
X(a, STATIC,   SINGULAR, DOUBLE,   vy,                2) \
X(a, STATIC,   SINGULAR, DOUBLE,   omega,             3)
#define wpi_proto_ProtobufChassisSpeeds_CALLBACK NULL
#define wpi_proto_ProtobufChassisSpeeds_DEFAULT NULL

#define wpi_proto_ProtobufDifferentialDriveKinematics_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   track_width,       1)
#define wpi_proto_ProtobufDifferentialDriveKinematics_CALLBACK NULL
#define wpi_proto_ProtobufDifferentialDriveKinematics_DEFAULT NULL

#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   left,              1) \
X(a, STATIC,   SINGULAR, DOUBLE,   right,             2)
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_CALLBACK NULL
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_DEFAULT NULL

#define wpi_proto_ProtobufDifferentialDriveWheelPositions_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   left,              1) \
X(a, STATIC,   SINGULAR, DOUBLE,   right,             2)
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_CALLBACK NULL
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_DEFAULT NULL

#define wpi_proto_ProtobufMecanumDriveKinematics_FIELDLIST(X, a) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  front_left,        1) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  front_right,       2) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  rear_left,         3) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  rear_right,        4)
#define wpi_proto_ProtobufMecanumDriveKinematics_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufMecanumDriveKinematics_DEFAULT NULL
#define wpi_proto_ProtobufMecanumDriveKinematics_front_left_MSGTYPE wpi_proto_ProtobufTranslation2d
#define wpi_proto_ProtobufMecanumDriveKinematics_front_right_MSGTYPE wpi_proto_ProtobufTranslation2d
#define wpi_proto_ProtobufMecanumDriveKinematics_rear_left_MSGTYPE wpi_proto_ProtobufTranslation2d
#define wpi_proto_ProtobufMecanumDriveKinematics_rear_right_MSGTYPE wpi_proto_ProtobufTranslation2d

#define wpi_proto_ProtobufMecanumDriveWheelPositions_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   front_left,        1) \
X(a, STATIC,   SINGULAR, DOUBLE,   front_right,       2) \
X(a, STATIC,   SINGULAR, DOUBLE,   rear_left,         3) \
X(a, STATIC,   SINGULAR, DOUBLE,   rear_right,        4)
#define wpi_proto_ProtobufMecanumDriveWheelPositions_CALLBACK NULL
#define wpi_proto_ProtobufMecanumDriveWheelPositions_DEFAULT NULL

#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   front_left,        1) \
X(a, STATIC,   SINGULAR, DOUBLE,   front_right,       2) \
X(a, STATIC,   SINGULAR, DOUBLE,   rear_left,         3) \
X(a, STATIC,   SINGULAR, DOUBLE,   rear_right,        4)
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_CALLBACK NULL
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_DEFAULT NULL

#define wpi_proto_ProtobufSwerveDriveKinematics_FIELDLIST(X, a) \
X(a, CALLBACK, REPEATED, MESSAGE,  modules,           1)
#define wpi_proto_ProtobufSwerveDriveKinematics_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufSwerveDriveKinematics_DEFAULT NULL
#define wpi_proto_ProtobufSwerveDriveKinematics_modules_MSGTYPE wpi_proto_ProtobufTranslation2d

#define wpi_proto_ProtobufSwerveModulePosition_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   distance,          1) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  angle,             2)
#define wpi_proto_ProtobufSwerveModulePosition_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufSwerveModulePosition_DEFAULT NULL
#define wpi_proto_ProtobufSwerveModulePosition_angle_MSGTYPE wpi_proto_ProtobufRotation2d

#define wpi_proto_ProtobufSwerveModuleState_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   speed,             1) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  angle,             2)
#define wpi_proto_ProtobufSwerveModuleState_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufSwerveModuleState_DEFAULT NULL
#define wpi_proto_ProtobufSwerveModuleState_angle_MSGTYPE wpi_proto_ProtobufRotation2d

/* Maximum encoded size of messages (where known) */
/* wpi_proto_ProtobufMecanumDriveKinematics_size depends on runtime parameters */
/* wpi_proto_ProtobufSwerveDriveKinematics_size depends on runtime parameters */
/* wpi_proto_ProtobufSwerveModulePosition_size depends on runtime parameters */
/* wpi_proto_ProtobufSwerveModuleState_size depends on runtime parameters */
#define WPI_PROTO_KINEMATICS_NPB_H_MAX_SIZE      wpi_proto_ProtobufMecanumDriveWheelPositions_size
#define wpi_proto_ProtobufChassisSpeeds_size     27
#define wpi_proto_ProtobufDifferentialDriveKinematics_size 9
#define wpi_proto_ProtobufDifferentialDriveWheelPositions_size 18
#define wpi_proto_ProtobufDifferentialDriveWheelSpeeds_size 18
#define wpi_proto_ProtobufMecanumDriveWheelPositions_size 36
#define wpi_proto_ProtobufMecanumDriveWheelSpeeds_size 36


#endif
