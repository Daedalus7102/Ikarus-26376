package org.firstinspires.ftc.teamcode.Commands.ExtenderCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Extender;

public class RetractCommand extends CommandBase {
    private final Extender extender;

    public RetractCommand(Extender extender) {
        this.extender = extender;
        addRequirements(extender);
    }

    @Override
    public void execute() {
        extender.resetOffset();
        extender.retract();
    }

    @Override
    public boolean isFinished() {
        return extender.isAtReference();
    }

}
