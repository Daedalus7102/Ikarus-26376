package org.firstinspires.ftc.teamcode.Commands.ArmCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Rotator;

public class LowerArmCommand extends CommandBase {

    private final Rotator rotator;

    public LowerArmCommand(Rotator rotator) {
        this.rotator = rotator;
        addRequirements(rotator);
    }

    @Override
    public boolean isFinished() {
        return rotator.isAtReference();
    }

    @Override
    public void execute() {
        rotator.lower();
    }
}
