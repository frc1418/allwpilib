// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <stdint.h>

#include <array>
#include <memory>
#include <set>
#include <string>
#include <vector>

#include <networktables/NTSendable.h>
#include <wpi/SmallVector.h>
#include <wpi/StringMap.h>
#include <wpi/sendable/SendableHelper.h>

namespace frc {

/**
 * Persistent alert to be sent via NetworkTables. Alerts are tagged with a type
 * of kError, kWarning, or kInfo to denote urgency. See Alert::AlertType for
 * suggested usage of each type. Alerts can be displayed on supported
 * dashboards, and are shown in a priority order based on type and recency of
 * activation.
 *
 * Alerts should be created once and stored persistently, then updated to
 * "active" or "inactive" as necessary. Set(bool) can be safely called
 * periodically.
 *
 * This API is new for 2025, but is likely to change in future seasons to
 * facilitate deeper integration with the robot control system.
 *
 * <pre>
 * class Robot {
 *   frc::Alert alert{"Something went wrong", frc::Alert::AlertType::kWarning};
 * }
 *
 * Robot::periodic() {
 *   alert.Set(...);
 * }
 * </pre>
 */
class Alert {
 public:
  /**
   * Represents an alert's level of urgency.
   */
  enum class AlertType {
    /**
     * High priority alert - displayed first on the dashboard with a red "X"
     * symbol. Use this type for problems which will seriously affect the
     * robot's functionality and thus require immediate attention.
     */
    kError,

    /**
     * Medium priority alert - displayed second on the dashboard with a yellow
     * "!" symbol. Use this type for problems which could affect the robot's
     * functionality but do not necessarily require immediate attention.
     */
    kWarning,

    /**
     * Low priority alert - displayed last on the dashboard with a green "i"
     * symbol. Use this type for problems which are unlikely to affect the
     * robot's functionality, or any other alerts which do not fall under the
     * other categories.
     */
    kInfo
  };

  /**
   * Creates a new alert in the default group - "Alerts". If this is the first
   * to be instantiated, the appropriate entries will be added to NetworkTables.
   *
   * @param text Text to be displayed when the alert is active.
   * @param type Alert urgency level.
   */
  Alert(std::string_view text, AlertType type);

  /**
   * Creates a new alert. If this is the first to be instantiated in its group,
   * the appropriate entries will be added to NetworkTables.
   *
   * @param group Group identifier, used as the entry name in NetworkTables.
   * @param text Text to be displayed when the alert is active.
   * @param type Alert urgency level.
   */
  Alert(std::string_view group, std::string_view text, AlertType type);

  Alert(Alert&&);
  Alert& operator=(Alert&&);

  ~Alert();

  /**
   * Sets whether the alert should currently be displayed. This method can be
   * safely called periodically.
   *
   * @param active Whether to display the alert.
   */
  void Set(bool active);

  /**
   * Updates current alert text. Use this method to dynamically change the
   * displayed alert, such as including more details about the detected problem.
   *
   * @param text Text to be displayed when the alert is active.
   */
  void SetText(std::string_view text);

 private:
  class PublishedAlert {
   public:
    PublishedAlert(uint64_t timestamp, const std::string_view text)
        : timestamp{timestamp}, text{text} {}
    uint64_t timestamp;
    std::string text;  // todo: This could be a string_view since deleting the
                       // Alert will erase this from the set
    auto operator<=>(const PublishedAlert&) const = default;
  };

  class SendableAlerts : public nt::NTSendable,
                         public wpi::SendableHelper<SendableAlerts> {
   public:
    SendableAlerts();
    void InitSendable(nt::NTSendableBuilder& builder) override;

    std::set<PublishedAlert>& GetSetForType(AlertType type);
    // todo: not sure why i needed this, maybe when i was testing different
    // container types?
    const std::set<PublishedAlert>& GetSetForType(AlertType type) const;

   private:
    std::vector<std::string> GetStrings(AlertType type) const;
    std::array<std::set<PublishedAlert>, 3> m_alerts;
  };

  AlertType m_type;
  std::string m_text;
  SendableAlerts* m_group;  // could replace with pointer to type set
  bool m_active = false;
  uint64_t m_activeStartTime;

  /**
   * Returns the SendableAlerts for a given group, initializing and publishing
   * if it does not already exist.
   * @param group the group name
   * @return the SendableAlerts for the group
   */
  static SendableAlerts& GetGroupSendable(std::string_view group);
};

std::string format_as(Alert::AlertType type);

}  // namespace frc
