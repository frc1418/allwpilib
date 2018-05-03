/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#ifdef _MSC_VER
#pragma message \
    "warning: llvm/WindowsError.h is deprecated; include wpi/WindowsError.h instead"
#else
#warning "llvm/WindowsError.h is deprecated; include wpi/WindowsError.h instead"
#endif

#include "wpi/WindowsError.h"

namespace llvm = wpi;
