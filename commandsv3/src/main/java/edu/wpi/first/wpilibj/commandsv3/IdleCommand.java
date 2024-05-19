package edu.wpi.first.wpilibj.commandsv3;

import java.util.Set;

/**
 * An idle command that only parks the executing thread and does nothing else.
 *
 * @param resource the resource to idle.
 */
public record IdleCommand(RequireableResource resource) implements Command {
  @Override
  public void run(Coroutine coroutine) {
    coroutine.park();
  }

  @Override
  public Set<RequireableResource> requirements() {
    return Set.of(resource);
  }

  @Override
  public String name() {
    return resource.getName() + "[IDLE]";
  }

  @Override
  public int priority() {
    // lowest priority - an idle command can be interrupted by anything else
    return LOWEST_PRIORITY;
  }

  @Override
  public String toString() {
    return name();
  }

  @Override
  public RobotDisabledBehavior robotDisabledBehavior() {
    return RobotDisabledBehavior.RunWhileDisabled;
  }

  @Override
  public InterruptBehavior interruptBehavior() {
    return InterruptBehavior.SuspendOnInterrupt;
  }
}
