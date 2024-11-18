// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wpilib.wpilibj.shuffleboard;

import static org.wpilib.util.ErrorMessages.requireNonNullParam;

import org.wpilib.networktables.NetworkTable;
import java.util.Map;

/**
 * A generic component in Shuffleboard.
 *
 * @param <C> the self type
 */
public abstract class ShuffleboardComponent<C extends ShuffleboardComponent<C>>
    implements ShuffleboardValue {
  private final ShuffleboardContainer m_parent;
  private final String m_title;
  private String m_type;
  private Map<String, Object> m_properties;
  private boolean m_metadataDirty = true;
  private int m_column = -1;
  private int m_row = -1;
  private int m_width = -1;
  private int m_height = -1;

  /**
   * Constructs a ShuffleboardComponent.
   *
   * @param parent The parent container.
   * @param title The component title.
   * @param type The component type.
   */
  protected ShuffleboardComponent(ShuffleboardContainer parent, String title, String type) {
    m_parent = requireNonNullParam(parent, "parent", "ShuffleboardComponent");
    m_title = requireNonNullParam(title, "title", "ShuffleboardComponent");
    m_type = type;
  }

  /**
   * Constructs a ShuffleboardComponent.
   *
   * @param parent The parent container.
   * @param title The component title.
   */
  protected ShuffleboardComponent(ShuffleboardContainer parent, String title) {
    this(parent, title, null);
  }

  /**
   * Returns the parent container.
   *
   * @return The parent container.
   */
  public final ShuffleboardContainer getParent() {
    return m_parent;
  }

  /**
   * Sets the component type.
   *
   * @param type The component type.
   */
  protected final void setType(String type) {
    m_type = type;
    m_metadataDirty = true;
  }

  /**
   * Returns the component type.
   *
   * @return The component type.
   */
  public final String getType() {
    return m_type;
  }

  @Override
  public final String getTitle() {
    return m_title;
  }

  /** Gets the custom properties for this component. May be null. */
  final Map<String, Object> getProperties() {
    return m_properties;
  }

  /**
   * Sets custom properties for this component. Property names are case- and whitespace-insensitive
   * (capitalization and spaces do not matter).
   *
   * @param properties the properties for this component
   * @return this component
   */
  @SuppressWarnings("unchecked")
  public final C withProperties(Map<String, Object> properties) {
    m_properties = properties;
    m_metadataDirty = true;
    return (C) this;
  }

  /**
   * Sets the position of this component in the tab. This has no effect if this component is inside
   * a layout.
   *
   * <p>If the position of a single component is set, it is recommended to set the positions of
   * <i>all</i> components inside a tab to prevent Shuffleboard from automatically placing another
   * component there before the one with the specific position is sent.
   *
   * @param columnIndex the column in the tab to place this component
   * @param rowIndex the row in the tab to place this component
   * @return this component
   */
  @SuppressWarnings("unchecked")
  public final C withPosition(int columnIndex, int rowIndex) {
    m_column = columnIndex;
    m_row = rowIndex;
    m_metadataDirty = true;
    return (C) this;
  }

  /**
   * Sets the size of this component in the tab. This has no effect if this component is inside a
   * layout.
   *
   * @param width how many columns wide the component should be
   * @param height how many rows high the component should be
   * @return this component
   */
  @SuppressWarnings("unchecked")
  public final C withSize(int width, int height) {
    m_width = width;
    m_height = height;
    m_metadataDirty = true;
    return (C) this;
  }

  /**
   * Builds NT metadata.
   *
   * @param metaTable The NT metadata table.
   */
  protected final void buildMetadata(NetworkTable metaTable) {
    if (!m_metadataDirty) {
      return;
    }
    // Component type
    if (getType() == null) {
      metaTable.getEntry("PreferredComponent").unpublish();
    } else {
      metaTable.getEntry("PreferredComponent").setString(getType());
    }

    // Tile size
    if (m_width <= 0 || m_height <= 0) {
      metaTable.getEntry("Size").unpublish();
    } else {
      metaTable.getEntry("Size").setDoubleArray(new double[] {m_width, m_height});
    }

    // Tile position
    if (m_column < 0 || m_row < 0) {
      metaTable.getEntry("Position").unpublish();
    } else {
      metaTable.getEntry("Position").setDoubleArray(new double[] {m_column, m_row});
    }

    // Custom properties
    if (getProperties() != null) {
      NetworkTable propTable = metaTable.getSubTable("Properties");
      getProperties().forEach((name, value) -> propTable.getEntry(name).setValue(value));
    }
    m_metadataDirty = false;
  }
}
