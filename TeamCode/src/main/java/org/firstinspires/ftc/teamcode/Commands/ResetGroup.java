package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ArmCommands.LowerArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderCommands.RetractCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Rotator;

public class ResetGroup extends SequentialCommandGroup {
    public ResetGroup(Rotator rotator, Extender extender) {
        addCommands(
                new RetractCommand(extender),
                new LowerArmCommand(rotator)
        );
        addRequirements(rotator, extender);
    }
}
