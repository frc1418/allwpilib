// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj;

/** Buffer storage for Addressable LEDs. */
public class AddressableLEDBuffer implements LEDReader, LEDWriter {
  byte[] m_buffer;
  final AddressableLEDColorOrder m_colorOrder;

  /**
   * Constructs a new LED buffer with the specified length.
   *
   * @param length The length of the buffer in pixels
   */
  public AddressableLEDBuffer(int length) {
    m_buffer = new byte[length * 4];
  }

  /**
   * Constructs a new LED buffer with the specified length and color order.
   *
   * @param length The length of the buffer in pixels
   * @param colorOrder The order the pixels display colors in
   */
  public AddressableLEDBuffer(int length, AddressableLEDColorOrder colorOrder) {
      m_buffer = new byte[length * 4];
      m_colorOrder = colorOrder;
  }

  /**
   * Sets a specific led in the buffer.
   *
   * @param index the index to write
   * @param r the r value [0-255]
   * @param g the g value [0-255]
   * @param b the b value [0-255]
   */
  @Override
  public void setRGB(int index, int r, int g, int b) {
    switch (m_colorOrder) {
      case kRGB:
        m_buffer[index * 4] = (byte) b;
        m_buffer[(index * 4) + 1] = (byte) g;
        m_buffer[(index * 4) + 2] = (byte) r;
        break;
      case kRBG:
        m_buffer[index * 4] = (byte) g;
        m_buffer[(index * 4) + 1] = (byte) b;
        m_buffer[(index * 4) + 2] = (byte) r;
        break;
      case kGBR:
        m_buffer[index * 4] = (byte) r;
        m_buffer[(index * 4) + 1] = (byte) b;
        m_buffer[(index * 4) + 2] = (byte) g;
        break;
      case kGRB:
        m_buffer[index * 4] = (byte) b;
        m_buffer[(index * 4) + 1] = (byte) r;
        m_buffer[(index * 4) + 2] = (byte) g;
        break;
      case kBRG:
        m_buffer[index * 4] = (byte) g;
        m_buffer[(index * 4) + 1] = (byte) r;
        m_buffer[(index * 4) + 2] = (byte) b;
        break;
      case kBGR:
        m_buffer[index * 4] = (byte) r;
        m_buffer[(index * 4) + 1] = (byte) g;
        m_buffer[(index * 4) + 2] = (byte) b;
        break;
    }
    m_buffer[(index * 4) + 3] = 0;
  }

  /**
   * Gets the buffer length.
   *
   * @return the buffer length
   */
  @Override
  public int getLength() {
    return m_buffer.length / 4;
  }

  /**
   * Gets the red channel of the color at the specified index.
   *
   * @param index the index of the LED to read
   * @return the value of the red channel, from [0, 255]
   */
  @Override
  public int getRed(int index) {
    return m_buffer[index * 4 + 2] & 0xFF;
  }

  /**
   * Gets the green channel of the color at the specified index.
   *
   * @param index the index of the LED to read
   * @return the value of the green channel, from [0, 255]
   */
  @Override
  public int getGreen(int index) {
    return m_buffer[index * 4 + 1] & 0xFF;
  }

  /**
   * Gets the blue channel of the color at the specified index.
   *
   * @param index the index of the LED to read
   * @return the value of the blue channel, from [0, 255]
   */
  @Override
  public int getBlue(int index) {
    return m_buffer[index * 4] & 0xFF;
  }

  /**
   * Creates a view of a subsection of this data buffer, starting from (and including) {@code
   * startingIndex} and ending on (and including) {@code endingIndex}. Views cannot be written
   * directly to an {@link AddressableLED}, but are useful tools for logically separating different
   * sections of an LED strip for independent control.
   *
   * @param startingIndex the first index in this buffer that the view should encompass (inclusive)
   * @param endingIndex the last index in this buffer that the view should encompass (inclusive)
   * @return the view object
   */
  public AddressableLEDBufferView createView(int startingIndex, int endingIndex) {
    return new AddressableLEDBufferView(this, startingIndex, endingIndex);
  }
}
