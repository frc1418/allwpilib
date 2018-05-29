/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.shuffleboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Shuffleboard class provides a mechanism with which data can be added and laid out in the
 * Shuffleboard dashboard application from a robot program. Tabs and layouts can be specified, as
 * well as choosing which widgets to display with and setting properties of these widgets; for
 * example, programmers can specify a specific {@code boolean} value to be displayed with a toggle
 * button instead of the default colored box, or set custom colors for that box.
 *
 * <p>For example, displaying a boolean entry with a toggle button:
 * <pre>{@code
 * NetworkTableEntry myBoolean = Shuffleboard.add("My Boolean", NetworkTableType.kBoolean)
 *   .toTab("Example Tab")
 *   .withWidget("Toggle Button")
 *   .getEntry();
 * }</pre>
 *
 * Changing the colors of the boolean box:
 * <pre>{@code
 * NetworkTableEntry myBoolean = Shuffleboard.add("My Boolean", NetworkTableType.kBoolean)
 *   .toTab("Example Tab")
 *   .withWidget("Boolean Box")
 *   .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
 *   .getEntry();
 * }</pre>
 *
 * Specifying a parent layout. Note that the layout type must <i>always</i> be specified, even if
 * the layout has already been generated by a previously defined entry.
 * <pre>{@code
 * NetworkTableEntry myBoolean = Shuffleboard.add("My Boolean", NetworkTableType.kBoolean)
 *   .toTab("Example Tab")
 *   .toLayout("List Layout", "Example List")
 *   .getEntry();
 * }</pre>
 * </p>
 * 
 * <p>Teams are encouraged to set up shuffleboard layouts at the start of the robot program.</p>
 */
public final class Shuffleboard {

  public static final String BASE_TABLE_NAME = "/Shuffleboard";

  // Not final to allow tests to have fresh instances
  private static NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();

  private static boolean m_dirtyMetadata = false;
  private static final List<BuilderBase> m_builders = new ArrayList<>();
  private static final List<SendableData> m_sendables = new ArrayList<>();

  static {
    HLUsageReporting.reportShuffleboard();
  }

  private static final class SendableData {

    private final SendableBuilder m_sendable;
    private final SendableBuilderImpl m_builder;
    private boolean m_initialized = false;

    SendableData(SendableBuilder sendable, SendableBuilderImpl builder) {
      this.m_sendable = sendable;
      this.m_builder = builder;
    }

    /**
     * Initializes the sendable, if not already initialized. No-op if the sendable has been
     * initialized.
     */
    public void initialize() {
      if (!m_initialized) {
        m_builder.setTable(ntInstance.getTable(m_sendable.generateKey()));
        m_sendable.getSendable().initSendable(m_builder);
        m_builder.startListeners();
        m_initialized = true;
      }
    }

    /**
     * Updates the sendable.
     */
    public void update() {
      m_builder.updateTable();
    }
  }

  /**
   * Gets the NetworkTable instance that Shuffleboard is currently using. For internal use only.
   */
  static NetworkTableInstance getNtInstance() {
    return ntInstance;
  }

  /**
   * Sets the NetworkTable instance for Shuffleboard to use. This should only be used in tests to
   * make sure each test has a fresh instance. For internal use only.
   *
   * @param newInstance the new instance
   */
  static void setNtInstance(NetworkTableInstance newInstance) {
    ntInstance = newInstance;
  }

  /**
   * Gets the base Shuffleboard table in the current NetworkTables instance. This is a method
   * instead of stored as a field because the instance can change during tests, and a field would
   * still reference the original instance.
   */
  private static NetworkTable getBaseTable() {
    return ntInstance.getTable(BASE_TABLE_NAME);
  }

  /**
   * Updates all the values in Shuffleboard. This should be called repeatedly in a loop (for
   * example, in an iterative or timed robot's autonomousPeriodic and teleopPeriodic methods).
   */
  public static void update() {
    NetworkTable baseTable = getBaseTable();
    // Update tabs
    if (m_dirtyMetadata) {
      m_builders.stream()
          .map(b -> b.getTab().getName())
          .sorted()
          .forEach(tabName -> {
            NetworkTable subTable = getBaseTable().getSubTable(".tabs").getSubTable(tabName);
            subTable.getEntry("Name").setString(tabName);
            subTable.getEntry("AutopopulatePrefix").setString("/Shuffleboard/" + tabName + "/");
          });

      // Update layout and widget metadata
      NetworkTable metadata = baseTable.getSubTable(".metadata");
      for (BuilderBase builder : m_builders) {
        NetworkTable tabTable = metadata.getSubTable(builder.getTab().getName());

        // Add layout metadata (type, title, properties)
        NetworkTable parent = updateLayoutMetadata(builder, tabTable);

        // Add widget metadata (type, properties)
        updateWidgetMetadata(builder, parent);
      }
    }

    // Update sendables
    // Basic entries do not need to be updated by us; they are set by users via the
    // NetworkTableEntry instances they get when completing an add() operation
    m_sendables.forEach(data -> {
      data.initialize();
      data.update();
    });

    m_dirtyMetadata = false;
  }

  private static NetworkTable updateLayoutMetadata(BuilderBase builder, NetworkTable parent) {
    Layout layout = builder.getLayout();
    if (layout != null) {
      NetworkTable layoutTable = parent.getSubTable(layout.getTitle());
      layoutTable.getEntry("Type").setString(layout.getType());
      layoutTable.getEntry("Title").setString(layout.getTitle());
      if (!layout.getProperties().isEmpty()) {
        NetworkTable layoutProperties = layoutTable.getSubTable("LayoutProperties");
        layout.getProperties().forEach((key, value) -> {
          layoutProperties.getEntry(key).forceSetValue(value);
        });
      }
      return layoutTable;
    }
    return parent;
  }

  private static void updateWidgetMetadata(BuilderBase builder, NetworkTable parent) {
    if (builder.getWidget() != null) {
      String name = builder.getName();
      NetworkTable entryTable = parent.getSubTable(name);
      entryTable.getEntry("PreferredWidget").setString(builder.getWidget().getType());
      NetworkTable widgetProperties = entryTable.getSubTable("WidgetProperties");
      builder.getWidget().getProperties().forEach((key, value) -> {
        widgetProperties.getEntry(key).forceSetValue(value);
      });
    }
  }

  /**
   * Adds an entry to Shuffleboard. This is intended to be used as the starting point for a fluent
   * builder, eg
   * <pre>{@code
   * NetworkTableEntry x = Shuffleboard.add("X", NetworkTableType.kDouble)
   *   .toTab("Tab Name")
   *   .toLayout("Layout Type", "Layout Title")
   *   .withWidget("Widget Type")
   *   .getEntry();
   *
   *   ...
   *
   * x.setNumber(12.34);
   * x.getNumber(defaultValue = 0.0);
   * }</pre>
   *
   * <p>Note that to be useful, the builder must finish with a {@code getEntry()} call to get a
   * reference to the entry you are adding to Shuffleboard. Omitting this call will still allow the
   * entry to be added to Shuffleboard in the specified configuration, but you will not be able to
   * easily reference it in your robot program.
   * </p>
   *
   * @param entryName the name of the entry
   * @param entryType the type of the values in the entry
   *
   * @return a starting builder object
   *
   * @throws NullPointerException     if the entry name or type is null
   * @throws IllegalArgumentException if the entry name is empty or contains slashes
   * @see #add(Sendable)
   * @see #add(String, Sendable)
   */
  public static EntryBuilder add(String entryName, NetworkTableType entryType) {
    Objects.requireNonNull(entryName, "Entry name must be set");
    Objects.requireNonNull(entryType, "Entry type must be set");
    if (entryName.isEmpty() || entryName.contains("/")) {
      throw new IllegalArgumentException("Invalid entry name: '" + entryName + "'");
    }
    EntryBuilder entry = new EntryBuilder(entryName, entryType);
    m_builders.add(entry);
    m_dirtyMetadata = true;
    return entry;
  }

  /**
   * Adds a sendable object to Shuffleboard. This is intended to be used as the starting point for a
   * fluent builder, eg
   * <pre>{@code
   * Shuffleboard.add("Wait 5 Seconds", new TimedCommand(5.0))
   *   .toTab("Commands")
   *   .toLayout("Layout Type", "Layout Title")
   *   .withWidget("Command Widget");
   * }</pre>
   *
   * <p>The fluent builder looks almost identical to the builder for entries, except that there is
   * no terminal {@code getEntry()} function call, since sendables are responsible for updating
   * their own tables and entries.</p>
   *
   * @param sendableName the name of the sendable to add
   * @param sendable     the sendable to add
   *
   * @return a starting builder object
   *
   * @throws NullPointerException     if the sendable name or the sendable is null
   * @throws IllegalArgumentException if the name is empty or contains slashes
   * @see #add(String, NetworkTableType)
   * @see #add(Sendable)
   */
  public static SendableBuilder add(String sendableName, Sendable sendable) {
    Objects.requireNonNull(sendable, "Cannot add a null sendable");
    Objects.requireNonNull(sendableName, "Sendable name must be set");
    if (sendableName.isEmpty() || sendableName.contains("/")) {
      throw new IllegalArgumentException("Invalid sendable name: '" + sendableName + "'");
    }
    SendableBuilder builder = new SendableBuilder(sendableName, sendable);
    m_builders.add(builder);
    SendableBuilderImpl sendableBuilder = new SendableBuilderImpl();
    m_sendables.add(new SendableData(builder, sendableBuilder));
    m_dirtyMetadata = true;
    return builder;
  }

  /**
   * Adds a sendable to Shuffleboard whose name has been set via {@link Sendable#setName}.
   *
   * @throws NullPointerException     if the sendable or its name is null
   * @throws IllegalArgumentException if the sendable's name is empty or contains slashes
   * @see #add(String, Sendable)
   */
  public static SendableBuilder add(Sendable sendable) {
    return add(sendable.getName(), sendable);
  }

}
