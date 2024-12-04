package org.firstinspires.ftc.teamcode.Commands.ExtenderCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Extender;

public class FullyExtendCommand extends CommandBase {
    private final Extender extender;

    public FullyExtendCommand(Extender extender) {
        this.extender = extender;
        addRequirements(extender);
    }

    @Override
    public void execute() {
        extender.extend();
    }

    @Override
    public boolean isFinished() {
        return extender.isAtReference();
    }
}
