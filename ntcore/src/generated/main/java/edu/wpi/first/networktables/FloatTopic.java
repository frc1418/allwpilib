// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./ntcore/generate_topics.py. DO NOT MODIFY

package org.wpilib.networktables;

/** NetworkTables Float topic. */
public final class FloatTopic extends Topic {
  /** The default type string for this topic type. */
  public static final String kTypeString = "float";

  /**
   * Construct from a generic topic.
   *
   * @param topic Topic
   */
  public FloatTopic(Topic topic) {
    super(topic.m_inst, topic.m_handle);
  }

  /**
   * Constructor; use NetworkTableInstance.getFloatTopic() instead.
   *
   * @param inst Instance
   * @param handle Native handle
   */
  public FloatTopic(NetworkTableInstance inst, int handle) {
    super(inst, handle);
  }

  /**
   * Create a new subscriber to the topic.
   *
   * <p>The subscriber is only active as long as the returned object
   * is not closed.
   *
   * <p>Subscribers that do not match the published data type do not return
   * any values. To determine if the data type matches, use the appropriate
   * Topic functions.
   *
   * @param defaultValue default value used when a default is not provided to a
   *        getter function
   * @param options subscribe options
   * @return subscriber
   */
  public FloatSubscriber subscribe(
      float defaultValue,
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.subscribe(
            m_handle, NetworkTableType.kFloat.getValue(),
            "float", options),
        defaultValue);
  }

  /**
   * Create a new subscriber to the topic, with specified type string.
   *
   * <p>The subscriber is only active as long as the returned object
   * is not closed.
   *
   * <p>Subscribers that do not match the published data type do not return
   * any values. To determine if the data type matches, use the appropriate
   * Topic functions.
   *
   * @param typeString type string
   * @param defaultValue default value used when a default is not provided to a
   *        getter function
   * @param options subscribe options
   * @return subscriber
   */
  public FloatSubscriber subscribeEx(
      String typeString,
      float defaultValue,
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.subscribe(
            m_handle, NetworkTableType.kFloat.getValue(),
            typeString, options),
        defaultValue);
  }

  /**
   * Create a new publisher to the topic.
   *
   * <p>The publisher is only active as long as the returned object
   * is not closed.
   *
   * <p>It is not possible to publish two different data types to the same
   * topic. Conflicts between publishers are typically resolved by the server on
   * a first-come, first-served basis. Any published values that do not match
   * the topic's data type are dropped (ignored). To determine if the data type
   * matches, use the appropriate Topic functions.
   *
   * @param options publish options
   * @return publisher
   */
  public FloatPublisher publish(
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.publish(
            m_handle, NetworkTableType.kFloat.getValue(),
            "float", options),
        0);
  }

  /**
   * Create a new publisher to the topic, with type string and initial properties.
   *
   * <p>The publisher is only active as long as the returned object
   * is not closed.
   *
   * <p>It is not possible to publish two different data types to the same
   * topic. Conflicts between publishers are typically resolved by the server on
   * a first-come, first-served basis. Any published values that do not match
   * the topic's data type are dropped (ignored). To determine if the data type
   * matches, use the appropriate Topic functions.
   *
   * @param typeString type string
   * @param properties JSON properties
   * @param options publish options
   * @return publisher
   * @throws IllegalArgumentException if properties is not a JSON object
   */
  public FloatPublisher publishEx(
      String typeString,
      String properties,
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.publishEx(
            m_handle, NetworkTableType.kFloat.getValue(),
            typeString, properties, options),
        0);
  }

  /**
   * Create a new entry for the topic.
   *
   * <p>Entries act as a combination of a subscriber and a weak publisher. The
   * subscriber is active as long as the entry is not closed. The publisher is
   * created when the entry is first written to, and remains active until either
   * unpublish() is called or the entry is closed.
   *
   * <p>It is not possible to use two different data types with the same
   * topic. Conflicts between publishers are typically resolved by the server on
   * a first-come, first-served basis. Any published values that do not match
   * the topic's data type are dropped (ignored), and the entry will show no new
   * values if the data type does not match. To determine if the data type
   * matches, use the appropriate Topic functions.
   *
   * @param defaultValue default value used when a default is not provided to a
   *        getter function
   * @param options publish and/or subscribe options
   * @return entry
   */
  public FloatEntry getEntry(
      float defaultValue,
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.getEntry(
            m_handle, NetworkTableType.kFloat.getValue(),
            "float", options),
        defaultValue);
  }

  /**
   * Create a new entry for the topic, with specified type string.
   *
   * <p>Entries act as a combination of a subscriber and a weak publisher. The
   * subscriber is active as long as the entry is not closed. The publisher is
   * created when the entry is first written to, and remains active until either
   * unpublish() is called or the entry is closed.
   *
   * <p>It is not possible to use two different data types with the same
   * topic. Conflicts between publishers are typically resolved by the server on
   * a first-come, first-served basis. Any published values that do not match
   * the topic's data type are dropped (ignored), and the entry will show no new
   * values if the data type does not match. To determine if the data type
   * matches, use the appropriate Topic functions.
   *
   * @param typeString type string
   * @param defaultValue default value used when a default is not provided to a
   *        getter function
   * @param options publish and/or subscribe options
   * @return entry
   */
  public FloatEntry getEntryEx(
      String typeString,
      float defaultValue,
      PubSubOption... options) {
    return new FloatEntryImpl(
        this,
        NetworkTablesJNI.getEntry(
            m_handle, NetworkTableType.kFloat.getValue(),
            typeString, options),
        defaultValue);
  }

}
