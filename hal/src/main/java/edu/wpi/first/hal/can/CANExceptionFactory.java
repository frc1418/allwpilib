// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.hal.can;

import edu.wpi.first.hal.communication.NIRioStatus;
import edu.wpi.first.hal.util.UncleanStatusException;

public final class CANExceptionFactory {
  // FRC Error codes
  static final int ERR_CANSessionMux_InvalidBuffer = -44086;
  static final int ERR_CANSessionMux_MessageNotFound = -44087;
  static final int ERR_CANSessionMux_NotAllowed = -44088;
  static final int ERR_CANSessionMux_NotInitialized = -44089;

  @SuppressWarnings("MissingJavadocMethod")
  public static void checkStatus(int status, int messageID)
      throws CANInvalidBufferException, CANMessageNotAllowedException, CANNotInitializedException,
          UncleanStatusException {
    switch (status) {
      case NIRioStatus.kRioStatusSuccess:
        // Everything is ok... don't throw.
        return;
      case ERR_CANSessionMux_InvalidBuffer:
      case NIRioStatus.kRIOStatusBufferInvalidSize:
        throw new CANInvalidBufferException();
      case ERR_CANSessionMux_MessageNotFound:
      case NIRioStatus.kRIOStatusOperationTimedOut:
        throw new CANMessageNotFoundException();
      case ERR_CANSessionMux_NotAllowed:
      case NIRioStatus.kRIOStatusFeatureNotSupported:
        throw new CANMessageNotAllowedException("MessageID = " + messageID);
      case ERR_CANSessionMux_NotInitialized:
      case NIRioStatus.kRIOStatusResourceNotInitialized:
        throw new CANNotInitializedException();
      default:
        throw new UncleanStatusException("Fatal status code detected:  " + status);
    }
  }

  private CANExceptionFactory() {}
}
