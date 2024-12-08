package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class SetExtenderReference extends CommandBase {
    private final Arm arm;
    public SetExtenderReference(Arm arm, double reference) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setExtenderReference(Constants.Extender.MAXIMUM_EXTENSION);
    }

    @Override
    public boolean isFinished() {
        return arm.extenderIsAtReference();
    }
}
