#include "hal/DutyCycle.h"

extern "C" {
HAL_DutyCycleHandle HAL_InitializeDutyCycle(HAL_Handle digitalSourceHandle,
                                            HAL_AnalogTriggerType triggerType,
                                            int32_t* status) {
  return HAL_kInvalidHandle;
}
void HAL_FreeDutyCycle(HAL_DutyCycleHandle dutyCycleHandle) {}

int32_t HAL_GetDutyCycleFrequency(HAL_DutyCycleHandle dutyCycleHandle,
                                  int32_t* status) {
  return 0;
}
double HAL_GetDutyCycleOutput(HAL_DutyCycleHandle dutyCycleHandle,
                                  int32_t* status) {
  return 0;
}
int32_t HAL_GetDutyCycleOutputRaw(HAL_DutyCycleHandle dutyCycleHandle,
                                  int32_t* status) {
  return 0;
}
int32_t HAL_GetDutyCycleOutputScaleFactor(HAL_DutyCycleHandle dutyCycleHandle,
                                    int32_t* status) {
  return 0;
}
}
