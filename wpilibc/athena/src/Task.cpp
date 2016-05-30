/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Task.h"

#include <cerrno>

#include "WPIErrors.h"

#ifndef OK
#define OK 0
#endif /* OK */
#ifndef ERROR
#define ERROR (-1)
#endif /* ERROR */

const uint32_t Task::kDefaultPriority;

Task& Task::operator=(Task&& task) {
  m_thread.swap(task.m_thread);
  m_taskName = std::move(task.m_taskName);

  return *this;
}

Task::~Task() {
  if (m_thread.joinable()) {
    std::cout << "[HAL] Exited task " << m_taskName << std::endl;
  }
}

bool Task::joinable() const noexcept { return m_thread.joinable(); }

void Task::join() { m_thread.join(); }

void Task::detach() { m_thread.detach(); }

std::thread::id Task::get_id() const noexcept { return m_thread.get_id(); }

std::thread::native_handle_type Task::native_handle() {
  return m_thread.native_handle();
}

/**
 * Verifies a task still exists.
 *
 * @return true on success.
 */
bool Task::Verify() {
  TASK id = (TASK)m_thread.native_handle();
  return verifyTaskID(id) == OK;
}

/**
 * Gets the priority of a task.
 *
 * @return task priority or 0 if an error occured
 */
int32_t Task::GetPriority() {
  int32_t priority;
  auto id = m_thread.native_handle();
  if (HandleError(getTaskPriority(&id, &priority)))
    return priority;
  else
    return 0;
}

/**
 * This routine changes a task's priority to a specified priority.
 * Priorities range from 1, the lowest priority, to 99, the highest priority.
 * Default task priority is 60.
 *
 * @param priority The priority at which the internal thread should run.
 * @return true on success.
 */
bool Task::SetPriority(int32_t priority) {
  auto id = m_thread.native_handle();
  return HandleError(setTaskPriority(&id, priority));
}

/**
 * Returns the name of the task.
 *
 * @return The name of the task.
 */
std::string Task::GetName() const { return m_taskName; }

/**
 * Handles errors generated by task related code.
 */
bool Task::HandleError(STATUS results) {
  if (results != ERROR) return true;
  int32_t errsv = errno;
  if (errsv == HAL_taskLib_ILLEGAL_PRIORITY) {
    wpi_setWPIErrorWithContext(TaskPriorityError, m_taskName.c_str());
  } else {
    printf("ERROR: errno=%i", errsv);
    wpi_setWPIErrorWithContext(TaskError, m_taskName.c_str());
  }
  return false;
}
