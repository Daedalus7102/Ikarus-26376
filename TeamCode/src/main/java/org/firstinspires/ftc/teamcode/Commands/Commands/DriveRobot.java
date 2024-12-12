package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Gyroscope;

import java.util.function.DoubleSupplier;

public class DriveRobot extends CommandBase {
    private final Drivetrain drivetrain;
    private final Gyroscope gyroscope;
    private final DoubleSupplier leftX;
    private final DoubleSupplier leftY;
    private final DoubleSupplier rightX;

    public DriveRobot(Drivetrain drivetrain, Gyroscope gyroscope, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX) {
        this.drivetrain = drivetrain;
        this.gyroscope = gyroscope;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
    }

    @Override
    public void execute() {
        drivetrain.drive(leftX.getAsDouble(), leftY.getAsDouble(), rightX.getAsDouble(), gyroscope.getHeading());
    }
}
