// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WPINetJNITest {

    @Test
    void jniLinkTest() throws IOException  {
        WPINetJNI.forceLoad();
    }
}
