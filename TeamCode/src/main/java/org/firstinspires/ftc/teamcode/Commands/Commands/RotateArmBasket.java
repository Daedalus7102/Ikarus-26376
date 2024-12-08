package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class RotateArmBasket extends CommandBase {
    private final Arm arm;
    public RotateArmBasket(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.riseToBasket();
    }

    @Override
    public boolean isFinished() {
        return arm.armIsAtReference();
    }
}
