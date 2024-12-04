package org.firstinspires.ftc.teamcode.Commands.ExtenderCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Extender;

public class PartialExtendCommand extends CommandBase {
    private final Extender extender;

    public PartialExtendCommand(Extender extender) {
        this.extender = extender;
        addRequirements(extender);
    }

    @Override
    public void execute() {
        extender.partialExtend();
    }

    @Override
    public boolean isFinished() {
        return extender.isAtReference();
    }
}
