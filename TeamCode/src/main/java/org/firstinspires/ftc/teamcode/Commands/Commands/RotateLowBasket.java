package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class RotateLowBasket extends CommandBase {
    private final Arm arm;
    public RotateLowBasket(Arm arm) {
        this.arm = arm;
    }

    @Override
    public void initialize() {
        arm.rotateLowBasket();
    }

    @Override
    public boolean isFinished() {
        return arm.armIsAtReference();
    }
}
