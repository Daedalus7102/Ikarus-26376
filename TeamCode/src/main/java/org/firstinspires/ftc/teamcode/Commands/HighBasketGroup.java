package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ArmCommands.RiseHighBasketCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderCommands.FullyExtendCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Rotator;

public class HighBasketGroup extends SequentialCommandGroup {
    public HighBasketGroup(Rotator rotator, Extender extender) {
        addCommands(
                new RiseHighBasketCommand(rotator),
                new FullyExtendCommand(extender)
        );
        addRequirements(rotator, extender);
    }
}
