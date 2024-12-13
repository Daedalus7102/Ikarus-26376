package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class RotateHighBasket extends CommandBase {
    private final Arm arm;
    public RotateHighBasket(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.rotateHighBasket();
    }

    @Override
    public boolean isFinished() {
        return arm.armIsAtReference();
    }
}
