package edu.wpi.first.wpilibj3.command.async;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Time;
import java.util.Set;

public class HardwareResource {
  private final String name;

  private final AsyncScheduler registeredScheduler;

  public HardwareResource(String name) {
    this(name, AsyncScheduler.getInstance());
  }

  public HardwareResource(String name, AsyncScheduler scheduler) {
    this.name = name;
    this.registeredScheduler = scheduler;
    scheduler.registerResource(this);
  }

  public String getName() {
    return name;
  }

  /**
   * Sets the default command to run on the resource when no other command is scheduled. The default
   * command's priority is effectively the minimum allowable priority for any command requiring a
   * resource. For this reason, it's recommended that a default command have a priority no greater
   * than {@link AsyncCommand#DEFAULT_PRIORITY} to prevent it from blocking other, non-default
   * commands from running.
   *
   * <p>The default command is initially an idle command that merely parks the execution thread.
   * This command has the lowest possible priority so as to allow any other command to run.
   *
   * @param defaultCommand the new default command
   */
  public void setDefaultCommand(AsyncCommand defaultCommand) {
    registeredScheduler.setDefaultCommand(this, defaultCommand);
  }

  public AsyncCommandBuilder run(Runnable command) {
    return new AsyncCommandBuilder().requiring(this).executing(command);
  }

  public AsyncCommand idle() {
    return new IdleCommand(this);
  }

  public AsyncCommand idle(Measure<Time> duration) {
    return idle().withTimeout(duration);
  }

  /**
   * Gets the set of nested resources encapsulated by this one. For compound mechanisms that need
   * to have flexible control over individual components, this will allow the scheduler to permit
   * nested commands to have ownership of just those components and not the entire compound
   * mechanism. In effect, this will allow default commands to run for the components not directly
   * used by that nested command.
   *
   * @return the nested resources
   */
  public Set<HardwareResource> nestedResources() {
    return Set.of();
  }

  @Override
  public String toString() {
    return name;
  }
}
