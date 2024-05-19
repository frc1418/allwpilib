package edu.wpi.first.wpilibj.commandsv3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A sequence of commands that run one after another. Each successive command only starts after its
 * predecessor completes execution. The priority of a sequence is equal to the highest priority of
 * any of its commands. If all commands in the sequence are able to run while the robot is disabled,
 * then the sequence itself will be allowed to run while the robot is disabled.
 */
public class Sequence implements Command {
  private final String name;
  private final List<Command> commands = new ArrayList<>();
  private final Set<RequireableResource> requirements = new HashSet<>();
  private final int priority;
  private RobotDisabledBehavior robotDisabledBehavior;

  /**
   * Creates a new command sequence.
   *
   * @param name the name of the sequence
   * @param commands the commands to execute within the sequence
   */
  public Sequence(String name, List<Command> commands) {
    this.name = name;
    this.commands.addAll(commands);

    for (var command : commands) {
      this.requirements.addAll(command.requirements());
    }

    this.priority =
        commands.stream().mapToInt(Command::priority).max().orElse(Command.DEFAULT_PRIORITY);

    this.robotDisabledBehavior = RobotDisabledBehavior.RunWhileDisabled;
    for (var command : commands) {
      if (command.robotDisabledBehavior() == RobotDisabledBehavior.CancelWhileDisabled) {
        this.robotDisabledBehavior = RobotDisabledBehavior.CancelWhileDisabled;
        break;
      }
    }
  }

  @Override
  public void run(Coroutine coroutine) {
    for (var command : commands) {
      coroutine.await(command);
    }
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public Set<RequireableResource> requirements() {
    return requirements;
  }

  @Override
  public int priority() {
    return priority;
  }

  @Override
  public RobotDisabledBehavior robotDisabledBehavior() {
    return robotDisabledBehavior;
  }
}
