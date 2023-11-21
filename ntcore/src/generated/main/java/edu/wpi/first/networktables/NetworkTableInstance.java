// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.networktables;

import edu.wpi.first.util.WPIUtilJNI;
import edu.wpi.first.util.concurrent.Event;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.protobuf.Protobuf;
import edu.wpi.first.util.struct.Struct;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import us.hebi.quickbuf.ProtoMessage;

/**
 * NetworkTables Instance.
 *
 * <p>Instances are completely independent from each other. Table operations on one instance will
 * not be visible to other instances unless the instances are connected via the network. The main
 * limitation on instances is that you cannot have two servers on the same network port. The main
 * utility of instances is for unit testing, but they can also enable one program to connect to two
 * different NetworkTables networks.
 *
 * <p>The global "default" instance (as returned by {@link #getDefault()}) is always available, and
 * is intended for the common case when there is only a single NetworkTables instance being used in
 * the program.
 *
 * <p>Additional instances can be created with the {@link #create()} function. A reference must be
 * kept to the NetworkTableInstance returned by this function to keep it from being garbage
 * collected.
 */
@SuppressWarnings("PMD.CouplingBetweenObjects")
public final class NetworkTableInstance implements AutoCloseable {
  /** Client/server mode flag values (as returned by {@link #getNetworkMode()}). */
  public enum NetworkMode {
    /** Running in server mode. */
    kServer(0x01),

    /** Running in NT3 client mode. */
    kClient3(0x02),

    /** Running in NT4 client mode. */
    kClient4(0x04),

    /** Currently starting up (either client or server). */
    kStarting(0x08),

    /** Running in local-only mode. */
    kLocal(0x10);

    private final int value;

    NetworkMode(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  /** The default port that network tables operates on for NT3. */
  public static final int kDefaultPort3 = 1735;

  /** The default port that network tables operates on for NT4. */
  public static final int kDefaultPort4 = 5810;

  /**
   * Construct from native handle.
   *
   * @param handle Native handle
   */
  private NetworkTableInstance(int handle) {
    m_owned = false;
    m_handle = handle;
  }

  /** Destroys the instance (if created by {@link #create()}). */
  @Override
  public synchronized void close() {
    if (m_owned && m_handle != 0) {
      m_listeners.close();
      m_schemas.forEach((k, v) -> v.close());
      NetworkTablesJNI.destroyInstance(m_handle);
      m_handle = 0;
    }
  }

  /**
   * Determines if the native handle is valid.
   *
   * @return True if the native handle is valid, false otherwise.
   */
  public boolean isValid() {
    return m_handle != 0;
  }

  /* The default instance. */
  private static NetworkTableInstance s_defaultInstance;

  /**
   * Get global default instance.
   *
   * @return Global default instance
   */
  public static synchronized NetworkTableInstance getDefault() {
    if (s_defaultInstance == null) {
      s_defaultInstance = new NetworkTableInstance(NetworkTablesJNI.getDefaultInstance());
    }
    return s_defaultInstance;
  }

  /**
   * Create an instance. Note: A reference to the returned instance must be retained to ensure the
   * instance is not garbage collected.
   *
   * @return Newly created instance
   */
  public static NetworkTableInstance create() {
    NetworkTableInstance inst = new NetworkTableInstance(NetworkTablesJNI.createInstance());
    inst.m_owned = true;
    return inst;
  }

  /**
   * Gets the native handle for the instance.
   *
   * @return Native handle
   */
  public int getHandle() {
    return m_handle;
  }

  /**
   * Get (generic) topic.
   *
   * @param name topic name
   * @return Topic
   */
  public Topic getTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic == null) {
      int handle = NetworkTablesJNI.getTopic(m_handle, name);
      topic = new Topic(this, handle);
      Topic oldTopic = m_topics.putIfAbsent(name, topic);
      if (oldTopic != null) {
        topic = oldTopic;
      }
      // also cache by handle
      m_topicsByHandle.putIfAbsent(handle, topic);
    }
    return topic;
  }

  /**
   * Get boolean topic.
   *
   * @param name topic name
   * @return BooleanTopic
   */
  public BooleanTopic getBooleanTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof BooleanTopic) {
      return (BooleanTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    BooleanTopic wrapTopic = new BooleanTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get long topic.
   *
   * @param name topic name
   * @return IntegerTopic
   */
  public IntegerTopic getIntegerTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof IntegerTopic) {
      return (IntegerTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    IntegerTopic wrapTopic = new IntegerTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get float topic.
   *
   * @param name topic name
   * @return FloatTopic
   */
  public FloatTopic getFloatTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof FloatTopic) {
      return (FloatTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    FloatTopic wrapTopic = new FloatTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get double topic.
   *
   * @param name topic name
   * @return DoubleTopic
   */
  public DoubleTopic getDoubleTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof DoubleTopic) {
      return (DoubleTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    DoubleTopic wrapTopic = new DoubleTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get String topic.
   *
   * @param name topic name
   * @return StringTopic
   */
  public StringTopic getStringTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof StringTopic) {
      return (StringTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    StringTopic wrapTopic = new StringTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get byte[] topic.
   *
   * @param name topic name
   * @return RawTopic
   */
  public RawTopic getRawTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof RawTopic) {
      return (RawTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    RawTopic wrapTopic = new RawTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get boolean[] topic.
   *
   * @param name topic name
   * @return BooleanArrayTopic
   */
  public BooleanArrayTopic getBooleanArrayTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof BooleanArrayTopic) {
      return (BooleanArrayTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    BooleanArrayTopic wrapTopic = new BooleanArrayTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get long[] topic.
   *
   * @param name topic name
   * @return IntegerArrayTopic
   */
  public IntegerArrayTopic getIntegerArrayTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof IntegerArrayTopic) {
      return (IntegerArrayTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    IntegerArrayTopic wrapTopic = new IntegerArrayTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get float[] topic.
   *
   * @param name topic name
   * @return FloatArrayTopic
   */
  public FloatArrayTopic getFloatArrayTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof FloatArrayTopic) {
      return (FloatArrayTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    FloatArrayTopic wrapTopic = new FloatArrayTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get double[] topic.
   *
   * @param name topic name
   * @return DoubleArrayTopic
   */
  public DoubleArrayTopic getDoubleArrayTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof DoubleArrayTopic) {
      return (DoubleArrayTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    DoubleArrayTopic wrapTopic = new DoubleArrayTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get String[] topic.
   *
   * @param name topic name
   * @return StringArrayTopic
   */
  public StringArrayTopic getStringArrayTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic instanceof StringArrayTopic) {
      return (StringArrayTopic) topic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    StringArrayTopic wrapTopic = new StringArrayTopic(this, handle);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }


  /**
   * Get protobuf-encoded value topic.
   *
   * @param <T> value class (inferred from proto)
   * @param <MessageType> protobuf message type (inferred from proto)
   * @param name topic name
   * @param proto protobuf serialization implementation
   * @return ProtobufTopic
   */
  public <T, MessageType extends ProtoMessage<?>>
      ProtobufTopic<T> getProtobufTopic(String name, Protobuf<T, MessageType> proto) {
    Topic topic = m_topics.get(name);
    if (topic instanceof ProtobufTopic<?>
        && ((ProtobufTopic<?>) topic).getProto().equals(proto)) {
      @SuppressWarnings("unchecked")
      ProtobufTopic<T> wrapTopic = (ProtobufTopic<T>) topic;
      return wrapTopic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    ProtobufTopic<T> wrapTopic = ProtobufTopic.wrap(this, handle, proto);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get struct-encoded value topic.
   *
   * @param <T> value class (inferred from struct)
   * @param name topic name
   * @param struct struct serialization implementation
   * @return StructTopic
   */
  public <T>
      StructTopic<T> getStructTopic(String name, Struct<T> struct) {
    Topic topic = m_topics.get(name);
    if (topic instanceof StructTopic<?>
        && ((StructTopic<?>) topic).getStruct().equals(struct)) {
      @SuppressWarnings("unchecked")
      StructTopic<T> wrapTopic = (StructTopic<T>) topic;
      return wrapTopic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    StructTopic<T> wrapTopic = StructTopic.wrap(this, handle, struct);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  /**
   * Get struct-encoded value array topic.
   *
   * @param <T> value class (inferred from struct)
   * @param name topic name
   * @param struct struct serialization implementation
   * @return StructArrayTopic
   */
  public <T>
      StructArrayTopic<T> getStructArrayTopic(String name, Struct<T> struct) {
    Topic topic = m_topics.get(name);
    if (topic instanceof StructArrayTopic<?>
        && ((StructArrayTopic<?>) topic).getStruct().equals(struct)) {
      @SuppressWarnings("unchecked")
      StructArrayTopic<T> wrapTopic = (StructArrayTopic<T>) topic;
      return wrapTopic;
    }

    int handle;
    if (topic == null) {
      handle = NetworkTablesJNI.getTopic(m_handle, name);
    } else {
      handle = topic.getHandle();
    }

    StructArrayTopic<T> wrapTopic = StructArrayTopic.wrap(this, handle, struct);
    m_topics.put(name, wrapTopic);

    // also cache by handle
    m_topicsByHandle.put(handle, wrapTopic);

    return wrapTopic;
  }

  private Topic[] topicHandlesToTopics(int[] handles) {
    Topic[] topics = new Topic[handles.length];
    for (int i = 0; i < handles.length; i++) {
      topics[i] = getCachedTopic(handles[i]);
    }
    return topics;
  }

  /**
   * Get all published topics.
   *
   * @return Array of topics.
   */
  public Topic[] getTopics() {
    return topicHandlesToTopics(NetworkTablesJNI.getTopics(m_handle, "", 0));
  }

  /**
   * Get published topics starting with the given prefix. The results are optionally filtered by
   * string prefix to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @return Array of topic information.
   */
  public Topic[] getTopics(String prefix) {
    return topicHandlesToTopics(NetworkTablesJNI.getTopics(m_handle, prefix, 0));
  }

  /**
   * Get published topics starting with the given prefix. The results are optionally filtered by
   * string prefix and data type to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @param types bitmask of data types; 0 is treated as a "don't care"
   * @return Array of topic information.
   */
  public Topic[] getTopics(String prefix, int types) {
    return topicHandlesToTopics(NetworkTablesJNI.getTopics(m_handle, prefix, types));
  }

  /**
   * Get published topics starting with the given prefix. The results are optionally filtered by
   * string prefix and data type to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @param types array of data type strings
   * @return Array of topic information.
   */
  public Topic[] getTopics(String prefix, String[] types) {
    return topicHandlesToTopics(NetworkTablesJNI.getTopicsStr(m_handle, prefix, types));
  }

  /**
   * Get information about all topics.
   *
   * @return Array of topic information.
   */
  public TopicInfo[] getTopicInfo() {
    return NetworkTablesJNI.getTopicInfos(this, m_handle, "", 0);
  }

  /**
   * Get information about topics starting with the given prefix. The results are optionally
   * filtered by string prefix to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @return Array of topic information.
   */
  public TopicInfo[] getTopicInfo(String prefix) {
    return NetworkTablesJNI.getTopicInfos(this, m_handle, prefix, 0);
  }

  /**
   * Get information about topics starting with the given prefix. The results are optionally
   * filtered by string prefix and data type to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @param types bitmask of data types; 0 is treated as a "don't care"
   * @return Array of topic information.
   */
  public TopicInfo[] getTopicInfo(String prefix, int types) {
    return NetworkTablesJNI.getTopicInfos(this, m_handle, prefix, types);
  }

  /**
   * Get information about topics starting with the given prefix. The results are optionally
   * filtered by string prefix and data type to only return a subset of all topics.
   *
   * @param prefix topic name required prefix; only topics whose name starts with this string are
   *     returned
   * @param types array of data type strings
   * @return Array of topic information.
   */
  public TopicInfo[] getTopicInfo(String prefix, String[] types) {
    return NetworkTablesJNI.getTopicInfosStr(this, m_handle, prefix, types);
  }

  /* Cache of created entries. */
  private final ConcurrentMap<String, NetworkTableEntry> m_entries = new ConcurrentHashMap<>();

  /**
   * Gets the entry for a key.
   *
   * @param name Key
   * @return Network table entry.
   */
  public NetworkTableEntry getEntry(String name) {
    NetworkTableEntry entry = m_entries.get(name);
    if (entry == null) {
      entry = new NetworkTableEntry(this, NetworkTablesJNI.getEntry(m_handle, name));
      NetworkTableEntry oldEntry = m_entries.putIfAbsent(name, entry);
      if (oldEntry != null) {
        entry = oldEntry;
      }
    }
    return entry;
  }

  /* Cache of created topics. */
  private final ConcurrentMap<String, Topic> m_topics = new ConcurrentHashMap<>();
  private final ConcurrentMap<Integer, Topic> m_topicsByHandle = new ConcurrentHashMap<>();

  Topic getCachedTopic(String name) {
    Topic topic = m_topics.get(name);
    if (topic == null) {
      int handle = NetworkTablesJNI.getTopic(m_handle, name);
      topic = new Topic(this, handle);
      Topic oldTopic = m_topics.putIfAbsent(name, topic);
      if (oldTopic != null) {
        topic = oldTopic;
      }
      // also cache by handle
      m_topicsByHandle.putIfAbsent(handle, topic);
    }
    return topic;
  }

  Topic getCachedTopic(int handle) {
    Topic topic = m_topicsByHandle.get(handle);
    if (topic == null) {
      topic = new Topic(this, handle);
      Topic oldTopic = m_topicsByHandle.putIfAbsent(handle, topic);
      if (oldTopic != null) {
        topic = oldTopic;
      }
    }
    return topic;
  }

  /* Cache of created tables. */
  private final ConcurrentMap<String, NetworkTable> m_tables = new ConcurrentHashMap<>();

  /**
   * Gets the table with the specified key.
   *
   * @param key the key name
   * @return The network table
   */
  public NetworkTable getTable(String key) {
    // prepend leading / if not present
    String theKey;
    if (key.isEmpty() || "/".equals(key)) {
      theKey = "";
    } else if (key.charAt(0) == NetworkTable.PATH_SEPARATOR) {
      theKey = key;
    } else {
      theKey = NetworkTable.PATH_SEPARATOR + key;
    }

    // cache created tables
    NetworkTable table = m_tables.get(theKey);
    if (table == null) {
      table = new NetworkTable(this, theKey);
      NetworkTable oldTable = m_tables.putIfAbsent(theKey, table);
      if (oldTable != null) {
        table = oldTable;
      }
    }
    return table;
  }

  /*
   * Callback Creation Functions
   */

  private static class ListenerStorage implements AutoCloseable {
    private final ReentrantLock m_lock = new ReentrantLock();
    private final Map<Integer, Consumer<NetworkTableEvent>> m_listeners = new HashMap<>();
    private Thread m_thread;
    private int m_poller;
    private boolean m_waitQueue;
    private final Event m_waitQueueEvent = new Event();
    private final Condition m_waitQueueCond = m_lock.newCondition();
    private final NetworkTableInstance m_inst;

    ListenerStorage(NetworkTableInstance inst) {
      m_inst = inst;
    }

    int add(
        String[] prefixes,
        EnumSet<NetworkTableEvent.Kind> eventKinds,
        Consumer<NetworkTableEvent> listener) {
      m_lock.lock();
      try {
        if (m_poller == 0) {
          m_poller = NetworkTablesJNI.createListenerPoller(m_inst.getHandle());
          startThread();
        }
        int h = NetworkTablesJNI.addListener(m_poller, prefixes, eventKinds);
        m_listeners.put(h, listener);
        return h;
      } finally {
        m_lock.unlock();
      }
    }

    int add(
        int handle,
        EnumSet<NetworkTableEvent.Kind> eventKinds,
        Consumer<NetworkTableEvent> listener) {
      m_lock.lock();
      try {
        if (m_poller == 0) {
          m_poller = NetworkTablesJNI.createListenerPoller(m_inst.getHandle());
          startThread();
        }
        int h = NetworkTablesJNI.addListener(m_poller, handle, eventKinds);
        m_listeners.put(h, listener);
        return h;
      } finally {
        m_lock.unlock();
      }
    }

    int addLogger(int minLevel, int maxLevel, Consumer<NetworkTableEvent> listener) {
      m_lock.lock();
      try {
        if (m_poller == 0) {
          m_poller = NetworkTablesJNI.createListenerPoller(m_inst.getHandle());
          startThread();
        }
        int h = NetworkTablesJNI.addLogger(m_poller, minLevel, maxLevel);
        m_listeners.put(h, listener);
        return h;
      } finally {
        m_lock.unlock();
      }
    }

    void remove(int listener) {
      m_lock.lock();
      try {
        m_listeners.remove(listener);
      } finally {
        m_lock.unlock();
      }
      NetworkTablesJNI.removeListener(listener);
    }

    @Override
    public void close() {
      if (m_poller != 0) {
        NetworkTablesJNI.destroyListenerPoller(m_poller);
      }
      m_poller = 0;
    }

    private void startThread() {
      m_thread =
          new Thread(
              () -> {
                boolean wasInterrupted = false;
                int[] handles = new int[] { m_poller, m_waitQueueEvent.getHandle() };
                while (!Thread.interrupted()) {
                  try {
                    WPIUtilJNI.waitForObjects(handles);
                  } catch (InterruptedException ex) {
                    m_lock.lock();
                    try {
                      if (m_waitQueue) {
                        m_waitQueue = false;
                        m_waitQueueCond.signalAll();
                      }
                    } finally {
                      m_lock.unlock();
                    }
                    Thread.currentThread().interrupt();
                    // don't try to destroy poller, as its handle is likely no longer valid
                    wasInterrupted = true;
                    break;
                  }
                  for (NetworkTableEvent event :
                      NetworkTablesJNI.readListenerQueue(m_inst, m_poller)) {
                    Consumer<NetworkTableEvent> listener;
                    m_lock.lock();
                    try {
                      listener = m_listeners.get(event.listener);
                    } finally {
                      m_lock.unlock();
                    }
                    if (listener != null) {
                      try {
                        listener.accept(event);
                      } catch (Throwable throwable) {
                        System.err.println(
                            "Unhandled exception during listener callback: "
                            + throwable.toString());
                        throwable.printStackTrace();
                      }
                    }
                  }
                  m_lock.lock();
                  try {
                    if (m_waitQueue) {
                      m_waitQueue = false;
                      m_waitQueueCond.signalAll();
                    }
                  } finally {
                    m_lock.unlock();
                  }
                }
                m_lock.lock();
                try {
                  if (!wasInterrupted) {
                    NetworkTablesJNI.destroyListenerPoller(m_poller);
                  }
                  m_poller = 0;
                } finally {
                  m_lock.unlock();
                }
              },
              "NTListener");
      m_thread.setDaemon(true);
      m_thread.start();
    }

    boolean waitForQueue(double timeout) {
      m_lock.lock();
      try {
        if (m_poller != 0) {
          m_waitQueue = true;
          m_waitQueueEvent.set();
          while (m_waitQueue) {
            try {
              if (timeout < 0) {
                m_waitQueueCond.await();
              } else {
                return m_waitQueueCond.await((long) (timeout * 1e9), TimeUnit.NANOSECONDS);
              }
            } catch (InterruptedException ex) {
              Thread.currentThread().interrupt();
              return true;
            }
          }
        }
      } finally {
        m_lock.unlock();
      }
      return true;
    }
  }

  private final ListenerStorage m_listeners = new ListenerStorage(this);

  /**
   * Remove a connection listener.
   *
   * @param listener Listener handle to remove
   */
  public void removeListener(int listener) {
    m_listeners.remove(listener);
  }

  /**
   * Wait for the listener queue to be empty. This is primarily useful for deterministic
   * testing. This blocks until either the listener queue is empty (e.g. there are no
   * more events that need to be passed along to callbacks or poll queues) or the timeout expires.
   *
   * @param timeout timeout, in seconds. Set to 0 for non-blocking behavior, or a negative value to
   *     block indefinitely
   * @return False if timed out, otherwise true.
   */
  public boolean waitForListenerQueue(double timeout) {
    return m_listeners.waitForQueue(timeout);
  }

  /**
   * Add a connection listener. The callback function is called asynchronously on a separate
   * thread, so it's important to use synchronization or atomics when accessing any shared state
   * from the callback function.
   *
   * @param immediateNotify Notify listener of all existing connections
   * @param listener Listener to add
   * @return Listener handle
   */
  public int addConnectionListener(
      boolean immediateNotify, Consumer<NetworkTableEvent> listener) {
    EnumSet<NetworkTableEvent.Kind> eventKinds = EnumSet.of(NetworkTableEvent.Kind.kConnection);
    if (immediateNotify) {
      eventKinds.add(NetworkTableEvent.Kind.kImmediate);
    }
    return m_listeners.add(m_handle, eventKinds, listener);
  }

  /**
   * Add a time synchronization listener. The callback function is called asynchronously on a
   * separate thread, so it's important to use synchronization or atomics when accessing any shared
   * state from the callback function.
   *
   * @param immediateNotify Notify listener of current time synchronization value
   * @param listener Listener to add
   * @return Listener handle
   */
  public int addTimeSyncListener(
      boolean immediateNotify, Consumer<NetworkTableEvent> listener) {
    EnumSet<NetworkTableEvent.Kind> eventKinds = EnumSet.of(NetworkTableEvent.Kind.kTimeSync);
    if (immediateNotify) {
      eventKinds.add(NetworkTableEvent.Kind.kImmediate);
    }
    return m_listeners.add(m_handle, eventKinds, listener);
  }

  /**
   * Add a listener for changes on a particular topic. The callback function is called
   * asynchronously on a separate thread, so it's important to use synchronization or atomics when
   * accessing any shared state from the callback function.
   *
   * <p>This creates a corresponding internal subscriber with the lifetime of the
   * listener.
   *
   * @param topic Topic
   * @param eventKinds set of event kinds to listen to
   * @param listener Listener function
   * @return Listener handle
   */
  public int addListener(
      Topic topic,
      EnumSet<NetworkTableEvent.Kind> eventKinds,
      Consumer<NetworkTableEvent> listener) {
    if (topic.getInstance().getHandle() != m_handle) {
      throw new IllegalArgumentException("topic is not from this instance");
    }
    return m_listeners.add(topic.getHandle(), eventKinds, listener);
  }

  /**
   * Add a listener for changes on a subscriber. The callback function is called
   * asynchronously on a separate thread, so it's important to use synchronization or atomics when
   * accessing any shared state from the callback function. This does NOT keep the subscriber
   * active.
   *
   * @param subscriber Subscriber
   * @param eventKinds set of event kinds to listen to
   * @param listener Listener function
   * @return Listener handle
   */
  public int addListener(
      Subscriber subscriber,
      EnumSet<NetworkTableEvent.Kind> eventKinds,
      Consumer<NetworkTableEvent> listener) {
    if (subscriber.getTopic().getInstance().getHandle() != m_handle) {
      throw new IllegalArgumentException("subscriber is not from this instance");
    }
    return m_listeners.add(subscriber.getHandle(), eventKinds, listener);
  }

  /**
   * Add a listener for changes on a subscriber. The callback function is called
   * asynchronously on a separate thread, so it's important to use synchronization or atomics when
   * accessing any shared state from the callback function. This does NOT keep the subscriber
   * active.
   *
   * @param subscriber Subscriber
   * @param eventKinds set of event kinds to listen to
   * @param listener Listener function
   * @return Listener handle
   */
  public int addListener(
      MultiSubscriber subscriber,
      EnumSet<NetworkTableEvent.Kind> eventKinds,
      Consumer<NetworkTableEvent> listener) {
    if (subscriber.getInstance().getHandle() != m_handle) {
      throw new IllegalArgumentException("subscriber is not from this instance");
    }
    return m_listeners.add(subscriber.getHandle(), eventKinds, listener);
  }

  /**
   * Add a listener for changes on an entry. The callback function is called
   * asynchronously on a separate thread, so it's important to use synchronization or atomics when
   * accessing any shared state from the callback function.
   *
   * @param entry Entry
   * @param eventKinds set of event kinds to listen to
   * @param listener Listener function
   * @return Listener handle
   */
  public int addListener(
      NetworkTableEntry entry,
      EnumSet<NetworkTableEvent.Kind> eventKinds,
      Consumer<NetworkTableEvent> listener) {
    if (entry.getTopic().getInstance().getHandle() != m_handle) {
      throw new IllegalArgumentException("entry is not from this instance");
    }
    return m_listeners.add(entry.getHandle(), eventKinds, listener);
  }

  /**
   * Add a listener for changes to topics with names that start with any of the given
   * prefixes. The callback function is called asynchronously on a separate thread, so it's
   * important to use synchronization or atomics when accessing any shared state from the callback
   * function.
   *
   * <p>This creates a corresponding internal subscriber with the lifetime of the
   * listener.
   *
   * @param prefixes Topic name string prefixes
   * @param eventKinds set of event kinds to listen to
   * @param listener Listener function
   * @return Listener handle
   */
  public int addListener(
      String[] prefixes,
      EnumSet<NetworkTableEvent.Kind> eventKinds,
      Consumer<NetworkTableEvent> listener) {
    return m_listeners.add(prefixes, eventKinds, listener);
  }

  /*
   * Client/Server Functions
   */

  /**
   * Get the current network mode.
   *
   * @return Enum set of NetworkMode.
   */
  public EnumSet<NetworkMode> getNetworkMode() {
    int flags = NetworkTablesJNI.getNetworkMode(m_handle);
    EnumSet<NetworkMode> rv = EnumSet.noneOf(NetworkMode.class);
    for (NetworkMode mode : NetworkMode.values()) {
      if ((flags & mode.getValue()) != 0) {
        rv.add(mode);
      }
    }
    return rv;
  }

  /**
   * Starts local-only operation. Prevents calls to startServer or startClient from taking effect.
   * Has no effect if startServer or startClient has already been called.
   */
  public void startLocal() {
    NetworkTablesJNI.startLocal(m_handle);
  }

  /**
   * Stops local-only operation. startServer or startClient can be called after this call to start
   * a server or client.
   */
  public void stopLocal() {
    NetworkTablesJNI.stopLocal(m_handle);
  }

  /**
   * Starts a server using the networktables.json as the persistent file, using the default
   * listening address and port.
   */
  public void startServer() {
    startServer("networktables.json");
  }

  /**
   * Starts a server using the specified persistent filename, using the default listening address
   * and port.
   *
   * @param persistFilename the name of the persist file to use
   */
  public void startServer(String persistFilename) {
    startServer(persistFilename, "");
  }

  /**
   * Starts a server using the specified filename and listening address, using the default port.
   *
   * @param persistFilename the name of the persist file to use
   * @param listenAddress the address to listen on, or empty to listen on any address
   */
  public void startServer(String persistFilename, String listenAddress) {
    startServer(persistFilename, listenAddress, kDefaultPort3, kDefaultPort4);
  }

  /**
   * Starts a server using the specified filename, listening address, and port.
   *
   * @param persistFilename the name of the persist file to use
   * @param listenAddress the address to listen on, or empty to listen on any address
   * @param port3 port to communicate over (NT3)
   */
  public void startServer(String persistFilename, String listenAddress, int port3) {
    startServer(persistFilename, listenAddress, port3, kDefaultPort4);
  }

  /**
   * Starts a server using the specified filename, listening address, and port.
   *
   * @param persistFilename the name of the persist file to use
   * @param listenAddress the address to listen on, or empty to listen on any address
   * @param port3 port to communicate over (NT3)
   * @param port4 port to communicate over (NT4)
   */
  public void startServer(String persistFilename, String listenAddress, int port3, int port4) {
    NetworkTablesJNI.startServer(m_handle, persistFilename, listenAddress, port3, port4);
  }

  /** Stops the server if it is running. */
  public void stopServer() {
    NetworkTablesJNI.stopServer(m_handle);
  }

  /**
   * Starts a NT3 client. Use SetServer or SetServerTeam to set the server name and port.
   *
   * @param identity network identity to advertise (cannot be empty string)
   */
  public void startClient3(String identity) {
    NetworkTablesJNI.startClient3(m_handle, identity);
  }

  /**
   * Starts a NT4 client. Use SetServer or SetServerTeam to set the server name and port.
   *
   * @param identity network identity to advertise (cannot be empty string)
   */
  public void startClient4(String identity) {
    NetworkTablesJNI.startClient4(m_handle, identity);
  }

  /** Stops the client if it is running. */
  public void stopClient() {
    NetworkTablesJNI.stopClient(m_handle);
  }

  /**
   * Sets server address and port for client (without restarting client). Changes the port to the
   * default port.
   *
   * @param serverName server name
   */
  public void setServer(String serverName) {
    setServer(serverName, 0);
  }

  /**
   * Sets server address and port for client (without restarting client).
   *
   * @param serverName server name
   * @param port port to communicate over (0=default)
   */
  public void setServer(String serverName, int port) {
    NetworkTablesJNI.setServer(m_handle, serverName, port);
  }

  /**
   * Sets server addresses and port for client (without restarting client). Changes the port to the
   * default port. The client will attempt to connect to each server in round robin fashion.
   *
   * @param serverNames array of server names
   */
  public void setServer(String[] serverNames) {
    setServer(serverNames, 0);
  }

  /**
   * Sets server addresses and port for client (without restarting client). The client will attempt
   * to connect to each server in round robin fashion.
   *
   * @param serverNames array of server names
   * @param port port to communicate over (0=default)
   */
  public void setServer(String[] serverNames, int port) {
    int[] ports = new int[serverNames.length];
    for (int i = 0; i < serverNames.length; i++) {
      ports[i] = port;
    }
    setServer(serverNames, ports);
  }

  /**
   * Sets server addresses and ports for client (without restarting client). The client will
   * attempt to connect to each server in round robin fashion.
   *
   * @param serverNames array of server names
   * @param ports array of port numbers (0=default)
   */
  public void setServer(String[] serverNames, int[] ports) {
    NetworkTablesJNI.setServer(m_handle, serverNames, ports);
  }

  /**
   * Sets server addresses and port for client (without restarting client). Changes the port to the
   * default port. The client will attempt to connect to each server in round robin fashion.
   *
   * @param team team number
   */
  public void setServerTeam(int team) {
    setServerTeam(team, 0);
  }

  /**
   * Sets server addresses and port for client (without restarting client). Connects using commonly
   * known robot addresses for the specified team.
   *
   * @param team team number
   * @param port port to communicate over (0=default)
   */
  public void setServerTeam(int team, int port) {
    NetworkTablesJNI.setServerTeam(m_handle, team, port);
  }

  /**
   * Disconnects the client if it's running and connected. This will automatically start
   * reconnection attempts to the current server list.
   */
  public void disconnect() {
    NetworkTablesJNI.disconnect(m_handle);
  }

  /**
   * Starts requesting server address from Driver Station. This connects to the Driver Station
   * running on localhost to obtain the server IP address, and connects with the default port.
   */
  public void startDSClient() {
    startDSClient(0);
  }

  /**
   * Starts requesting server address from Driver Station. This connects to the Driver Station
   * running on localhost to obtain the server IP address.
   *
   * @param port server port to use in combination with IP from DS (0=default)
   */
  public void startDSClient(int port) {
    NetworkTablesJNI.startDSClient(m_handle, port);
  }

  /** Stops requesting server address from Driver Station. */
  public void stopDSClient() {
    NetworkTablesJNI.stopDSClient(m_handle);
  }

  /**
   * Flushes all updated values immediately to the local client/server. This does not flush to the
   * network.
   */
  public void flushLocal() {
    NetworkTablesJNI.flushLocal(m_handle);
  }

  /**
   * Flushes all updated values immediately to the network. Note: This is rate-limited to protect
   * the network from flooding. This is primarily useful for synchronizing network updates with
   * user code.
   */
  public void flush() {
    NetworkTablesJNI.flush(m_handle);
  }

  /**
   * Gets information on the currently established network connections. If operating as a client,
   * this will return either zero or one values.
   *
   * @return array of connection information
   */
  public ConnectionInfo[] getConnections() {
    return NetworkTablesJNI.getConnections(m_handle);
  }

  /**
   * Return whether or not the instance is connected to another node.
   *
   * @return True if connected.
   */
  public boolean isConnected() {
    return NetworkTablesJNI.isConnected(m_handle);
  }

  /**
   * Get the time offset between server time and local time. Add this value to local time to get
   * the estimated equivalent server time. In server mode, this always returns 0. In client mode,
   * this returns the time offset only if the client and server are connected and have exchanged
   * synchronization messages. Note the time offset may change over time as it is periodically
   * updated; to receive updates as events, add a listener to the "time sync" event.
   *
   * @return Time offset in microseconds (optional)
   */
  public OptionalLong getServerTimeOffset() {
    return NetworkTablesJNI.getServerTimeOffset(m_handle);
  }

  /**
   * Starts logging entry changes to a DataLog.
   *
   * @param log data log object; lifetime must extend until StopEntryDataLog is called or the
   *     instance is destroyed
   * @param prefix only store entries with names that start with this prefix; the prefix is not
   *     included in the data log entry name
   * @param logPrefix prefix to add to data log entry names
   * @return Data logger handle
   */
  public int startEntryDataLog(DataLog log, String prefix, String logPrefix) {
    return NetworkTablesJNI.startEntryDataLog(m_handle, log, prefix, logPrefix);
  }

  /**
   * Stops logging entry changes to a DataLog.
   *
   * @param logger data logger handle
   */
  public static void stopEntryDataLog(int logger) {
    NetworkTablesJNI.stopEntryDataLog(logger);
  }

  /**
   * Starts logging connection changes to a DataLog.
   *
   * @param log data log object; lifetime must extend until StopConnectionDataLog is called or the
   *     instance is destroyed
   * @param name data log entry name
   * @return Data logger handle
   */
  public int startConnectionDataLog(DataLog log, String name) {
    return NetworkTablesJNI.startConnectionDataLog(m_handle, log, name);
  }

  /**
   * Stops logging connection changes to a DataLog.
   *
   * @param logger data logger handle
   */
  public static void stopConnectionDataLog(int logger) {
    NetworkTablesJNI.stopConnectionDataLog(logger);
  }

  /**
   * Add logger callback function. By default, log messages are sent to stderr; this function sends
   * log messages with the specified levels to the provided callback function instead. The callback
   * function will only be called for log messages with level greater than or equal to minLevel and
   * less than or equal to maxLevel; messages outside this range will be silently ignored.
   *
   * @param minLevel minimum log level
   * @param maxLevel maximum log level
   * @param func callback function
   * @return Listener handle
   */
  public int addLogger(int minLevel, int maxLevel, Consumer<NetworkTableEvent> func) {
    return m_listeners.addLogger(minLevel, maxLevel, func);
  }

  /**
   * Returns whether there is a data schema already registered with the given name that this
   * instance has published. This does NOT perform a check as to whether the schema has already
   * been published by another node on the network.
   *
   * @param name Name (the string passed as the data type for topics using this schema)
   * @return True if schema already registered
   */
  public boolean hasSchema(String name) {
    return m_schemas.containsKey("/.schema/" + name);
  }

  /**
   * Registers a data schema. Data schemas provide information for how a certain data type string
   * can be decoded. The type string of a data schema indicates the type of the schema itself (e.g.
   * "protobuf" for protobuf schemas, "struct" for struct schemas, etc). In NetworkTables, schemas
   * are published just like normal topics, with the name being generated from the provided name:
   * "/.schema/name". Duplicate calls to this function with the same name are silently ignored.
   *
   * @param name Name (the string passed as the data type for topics using this schema)
   * @param type Type of schema (e.g. "protobuf", "struct", etc)
   * @param schema Schema data
   */
  public void addSchema(String name, String type, byte[] schema) {
    m_schemas.computeIfAbsent("/.schema/" + name, k -> {
      RawPublisher pub = getRawTopic(k).publishEx(type, "{\"retained\":true}");
      pub.setDefault(schema);
      return pub;
    });
  }

  /**
   * Registers a data schema. Data schemas provide information for how a certain data type string
   * can be decoded. The type string of a data schema indicates the type of the schema itself (e.g.
   * "protobuf" for protobuf schemas, "struct" for struct schemas, etc). In NetworkTables, schemas
   * are published just like normal topics, with the name being generated from the provided name:
   * "/.schema/name". Duplicate calls to this function with the same name are silently ignored.
   *
   * @param name Name (the string passed as the data type for topics using this schema)
   * @param type Type of schema (e.g. "protobuf", "struct", etc)
   * @param schema Schema data
   */
  public void addSchema(String name, String type, String schema) {
    m_schemas.computeIfAbsent("/.schema/" + name, k -> {
      RawPublisher pub = getRawTopic(k).publishEx(type, "{\"retained\":true}");
      pub.setDefault(StandardCharsets.UTF_8.encode(schema));
      return pub;
    });
  }

  /**
   * Registers a protobuf schema. Duplicate calls to this function with the same name are silently
   * ignored.
   *
   * @param proto protobuf serialization object
   */
  public void addSchema(Protobuf<?, ?> proto) {
    proto.forEachDescriptor(
        this::hasSchema,
        (typeString, schema) -> addSchema(typeString, "proto:FileDescriptorProto", schema));
  }

  /**
   * Registers a struct schema. Duplicate calls to this function with the same name are silently
   * ignored.
   *
   * @param struct struct serialization object
   */
  public void addSchema(Struct<?> struct) {
    addSchemaImpl(struct, new HashSet<>());
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof NetworkTableInstance)) {
      return false;
    }

    return m_handle == ((NetworkTableInstance) other).m_handle;
  }

  @Override
  public int hashCode() {
    return m_handle;
  }

  private void addSchemaImpl(Struct<?> struct, Set<String> seen) {
    String typeString = struct.getTypeString();
    if (hasSchema(typeString)) {
      return;
    }
    if (!seen.add(typeString)) {
      throw new UnsupportedOperationException(typeString + ": circular reference with " + seen);
    }
    addSchema(typeString, "structschema", struct.getSchema());
    for (Struct<?> inner : struct.getNested()) {
      addSchemaImpl(inner, seen);
    }
    seen.remove(typeString);
  }

  private boolean m_owned;
  private int m_handle;
  private final ConcurrentMap<String, RawPublisher> m_schemas = new ConcurrentHashMap<>();
}