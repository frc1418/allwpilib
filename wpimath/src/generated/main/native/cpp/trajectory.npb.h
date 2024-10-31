// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/* Automatically generated nanopb header */
/* Generated by nanopb-0.4.9 */

#ifndef PB_WPI_PROTO_TRAJECTORY_NPB_H_INCLUDED
#define PB_WPI_PROTO_TRAJECTORY_NPB_H_INCLUDED
#include <pb.h>
#include <span>
#include <string_view>
#include "geometry2d.npb.h"

#if PB_PROTO_HEADER_VERSION != 40
#error Regenerate this file with the current version of nanopb generator.
#endif

/* Struct definitions */
typedef struct _wpi_proto_ProtobufTrajectoryState {
    double time;
    double velocity;
    double acceleration;
    pb_callback_t pose;
    double curvature;
} wpi_proto_ProtobufTrajectoryState;

typedef struct _wpi_proto_ProtobufTrajectory {
    pb_callback_t states;
} wpi_proto_ProtobufTrajectory;


/* Initializer values for message structs */
#define wpi_proto_ProtobufTrajectoryState_init_default {0, 0, 0, {{NULL}, NULL}, 0}
#define wpi_proto_ProtobufTrajectory_init_default {{{NULL}, NULL}}
#define wpi_proto_ProtobufTrajectoryState_init_zero {0, 0, 0, {{NULL}, NULL}, 0}
#define wpi_proto_ProtobufTrajectory_init_zero   {{{NULL}, NULL}}

/* Field tags (for use in manual encoding/decoding) */
#define wpi_proto_ProtobufTrajectoryState_time_tag 1
#define wpi_proto_ProtobufTrajectoryState_velocity_tag 2
#define wpi_proto_ProtobufTrajectoryState_acceleration_tag 3
#define wpi_proto_ProtobufTrajectoryState_pose_tag 4
#define wpi_proto_ProtobufTrajectoryState_curvature_tag 5
#define wpi_proto_ProtobufTrajectory_states_tag  2

/* Struct field encoding specification for nanopb */
#define wpi_proto_ProtobufTrajectoryState_FIELDLIST(X, a) \
X(a, STATIC,   SINGULAR, DOUBLE,   time,              1) \
X(a, STATIC,   SINGULAR, DOUBLE,   velocity,          2) \
X(a, STATIC,   SINGULAR, DOUBLE,   acceleration,      3) \
X(a, CALLBACK, OPTIONAL, MESSAGE,  pose,              4) \
X(a, STATIC,   SINGULAR, DOUBLE,   curvature,         5)
#define wpi_proto_ProtobufTrajectoryState_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufTrajectoryState_DEFAULT NULL
#define wpi_proto_ProtobufTrajectoryState_pose_MSGTYPE wpi_proto_ProtobufPose2d

#define wpi_proto_ProtobufTrajectory_FIELDLIST(X, a) \
X(a, CALLBACK, REPEATED, MESSAGE,  states,            2)
#define wpi_proto_ProtobufTrajectory_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufTrajectory_DEFAULT NULL
#define wpi_proto_ProtobufTrajectory_states_MSGTYPE wpi_proto_ProtobufTrajectoryState

const pb_msgdesc_t *get_wpi_proto_ProtobufTrajectoryState_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufTrajectoryState_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufTrajectoryState_name(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufTrajectory_msg(void);
std::span<const uint8_t> get_wpi_proto_ProtobufTrajectory_file_descriptor(void);
std::string_view get_wpi_proto_ProtobufTrajectory_name(void);

/* Maximum encoded size of messages (where known) */
/* wpi_proto_ProtobufTrajectoryState_size depends on runtime parameters */
/* wpi_proto_ProtobufTrajectory_size depends on runtime parameters */


#endif
