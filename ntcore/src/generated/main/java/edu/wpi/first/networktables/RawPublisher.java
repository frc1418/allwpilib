// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;


import java.nio.ByteBuffer;
import java.util.function.Consumer;

/** NetworkTables Raw publisher. */
public interface RawPublisher extends Publisher, Consumer<byte[]> {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  RawTopic getTopic();

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish
   */
  default void set(byte[] value) {
    set(value, 0);
  }


  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param time timestamp; 0 indicates current NT time should be used
   */
  default void set(byte[] value, long time) {
    set(value, 0, value.length, time);
  }

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   */
  default void set(byte[] value, int start, int len) {
    set(value, start, len, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   * @param time timestamp; 0 indicates current NT time should be used
   */
  void set(byte[] value, int start, int len, long time);

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish; will send from value.position() to value.limit()
   */
  default void set(ByteBuffer value) {
    set(value, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish; will send from value.position() to value.limit()
   * @param time timestamp; 0 indicates current NT time should be used
   */
  default void set(ByteBuffer value, long time) {
    int pos = value.position();
    set(value, pos, value.limit() - pos, time);
  }

  /**
   * Publish a new value using current NT time.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   */
  default void set(ByteBuffer value, int start, int len) {
    set(value, start, len, 0);
  }

  /**
   * Publish a new value.
   *
   * @param value value to publish
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   * @param time timestamp; 0 indicates current NT time should be used
   */
  void set(ByteBuffer value, int start, int len, long time);

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value
   */
  default void setDefault(byte[] value) {
    setDefault(value, 0, value.length);
  }

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.length - start)
   */
  void setDefault(byte[] value, int start, int len);

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value; will send from value.position() to value.limit()
   */
  default void setDefault(ByteBuffer value) {
    int pos = value.position();
    setDefault(value, pos, value.limit() - pos);
  }

  /**
   * Publish a default value.
   * On reconnect, a default value will never be used in preference to a
   * published value.
   *
   * @param value value
   * @param start Start position of data (in buffer)
   * @param len Length of data (must be less than or equal to value.capacity() - start)
   */
  void setDefault(ByteBuffer value, int start, int len);

  @Override
  default void accept(byte[] value) {
    set(value);
  }
}
