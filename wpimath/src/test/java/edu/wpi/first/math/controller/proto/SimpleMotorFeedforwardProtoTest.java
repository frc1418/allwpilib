// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.controller.proto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.proto.Controller.ProtobufSimpleMotorFeedforward;
import edu.wpi.first.wpilibj.ProtoTestBase;

@SuppressWarnings("PMD.TestClassWithoutTestCases")
class SimpleMotorFeedforwardProtoTest
    extends ProtoTestBase<SimpleMotorFeedforward, ProtobufSimpleMotorFeedforward> {
  SimpleMotorFeedforwardProtoTest() {
    super(new SimpleMotorFeedforward(0.4, 4.0, 0.7), SimpleMotorFeedforward.proto);
  }

  @Override
  public void checkEquals(SimpleMotorFeedforward testData, SimpleMotorFeedforward data) {
    assertEquals(testData.ks, data.ks);
    assertEquals(testData.kv, data.kv);
    assertEquals(testData.ka, data.ka);
  }
}
