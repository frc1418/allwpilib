package edu.wpi.first.wpilibj.commands.wpilibj2.trapezoidprofilecommand;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.TrapezoidProfileCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ReplaceMeTrapezoidProfileCommand extends TrapezoidProfileCommand {

  public ReplaceMeTrapezoidProfileCommand() {
    super(new TrapezoidProfile(new TrapezoidProfile.Constraints(0, 0),
                               // Goal state
                               new TrapezoidProfile.State(),
                               // Initial state
                               new TrapezoidProfile.State()),
          (output) -> {
            // Use output here
          });
  }
}
