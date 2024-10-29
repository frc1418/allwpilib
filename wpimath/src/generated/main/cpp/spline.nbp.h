// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/* Automatically generated nanopb header */
/* Generated by nanopb-0.4.9 */

#ifndef PB_WPI_PROTO_SPLINE_NBP_H_INCLUDED
#define PB_WPI_PROTO_SPLINE_NBP_H_INCLUDED
#include <pb.h>

#if PB_PROTO_HEADER_VERSION != 40
#error Regenerate this file with the current version of nanopb generator.
#endif

/* Struct definitions */
typedef struct _wpi_proto_ProtobufCubicHermiteSpline {
    pb_callback_t x_initial;
    pb_callback_t x_final;
    pb_callback_t y_initial;
    pb_callback_t y_final;
} wpi_proto_ProtobufCubicHermiteSpline;

typedef struct _wpi_proto_ProtobufQuinticHermiteSpline {
    pb_callback_t x_initial;
    pb_callback_t x_final;
    pb_callback_t y_initial;
    pb_callback_t y_final;
} wpi_proto_ProtobufQuinticHermiteSpline;


/* Initializer values for message structs */
#define wpi_proto_ProtobufCubicHermiteSpline_init_default {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufQuinticHermiteSpline_init_default {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufCubicHermiteSpline_init_zero {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}
#define wpi_proto_ProtobufQuinticHermiteSpline_init_zero {{{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}, {{NULL}, NULL}}

/* Field tags (for use in manual encoding/decoding) */
#define wpi_proto_ProtobufCubicHermiteSpline_x_initial_tag 1
#define wpi_proto_ProtobufCubicHermiteSpline_x_final_tag 2
#define wpi_proto_ProtobufCubicHermiteSpline_y_initial_tag 3
#define wpi_proto_ProtobufCubicHermiteSpline_y_final_tag 4
#define wpi_proto_ProtobufQuinticHermiteSpline_x_initial_tag 1
#define wpi_proto_ProtobufQuinticHermiteSpline_x_final_tag 2
#define wpi_proto_ProtobufQuinticHermiteSpline_y_initial_tag 3
#define wpi_proto_ProtobufQuinticHermiteSpline_y_final_tag 4

/* Struct field encoding specification for nanopb */
#define wpi_proto_ProtobufCubicHermiteSpline_FIELDLIST(X, a) \
X(a, CALLBACK, REPEATED, DOUBLE,   x_initial,         1) \
X(a, CALLBACK, REPEATED, DOUBLE,   x_final,           2) \
X(a, CALLBACK, REPEATED, DOUBLE,   y_initial,         3) \
X(a, CALLBACK, REPEATED, DOUBLE,   y_final,           4)
#define wpi_proto_ProtobufCubicHermiteSpline_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufCubicHermiteSpline_DEFAULT NULL

#define wpi_proto_ProtobufQuinticHermiteSpline_FIELDLIST(X, a) \
X(a, CALLBACK, REPEATED, DOUBLE,   x_initial,         1) \
X(a, CALLBACK, REPEATED, DOUBLE,   x_final,           2) \
X(a, CALLBACK, REPEATED, DOUBLE,   y_initial,         3) \
X(a, CALLBACK, REPEATED, DOUBLE,   y_final,           4)
#define wpi_proto_ProtobufQuinticHermiteSpline_CALLBACK pb_default_field_callback
#define wpi_proto_ProtobufQuinticHermiteSpline_DEFAULT NULL

const pb_msgdesc_t *get_wpi_proto_ProtobufCubicHermiteSpline_msg(void);
const pb_msgdesc_t *get_wpi_proto_ProtobufQuinticHermiteSpline_msg(void);

/* Maximum encoded size of messages (where known) */
/* wpi_proto_ProtobufCubicHermiteSpline_size depends on runtime parameters */
/* wpi_proto_ProtobufQuinticHermiteSpline_size depends on runtime parameters */


#endif
