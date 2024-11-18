// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.math.controller.proto;

import org.wpilib.math.controller.ElevatorFeedforward;
import org.wpilib.math.proto.Controller.ProtobufElevatorFeedforward;
import org.wpilib.util.protobuf.Protobuf;
import us.hebi.quickbuf.Descriptors.Descriptor;

public class ElevatorFeedforwardProto
    implements Protobuf<ElevatorFeedforward, ProtobufElevatorFeedforward> {
  @Override
  public Class<ElevatorFeedforward> getTypeClass() {
    return ElevatorFeedforward.class;
  }

  @Override
  public Descriptor getDescriptor() {
    return ProtobufElevatorFeedforward.getDescriptor();
  }

  @Override
  public ProtobufElevatorFeedforward createMessage() {
    return ProtobufElevatorFeedforward.newInstance();
  }

  @Override
  public ElevatorFeedforward unpack(ProtobufElevatorFeedforward msg) {
    return new ElevatorFeedforward(msg.getKs(), msg.getKg(), msg.getKv(), msg.getKa(), msg.getDt());
  }

  @Override
  public void pack(ProtobufElevatorFeedforward msg, ElevatorFeedforward value) {
    msg.setKs(value.getKs());
    msg.setKg(value.getKg());
    msg.setKv(value.getKv());
    msg.setKa(value.getKa());
    msg.setDt(value.getDt());
  }
}
