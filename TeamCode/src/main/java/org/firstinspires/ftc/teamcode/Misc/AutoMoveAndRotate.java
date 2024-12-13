package org.firstinspires.ftc.teamcode.Misc;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class AutoMoveAndRotate {
    private final ElapsedTime timer = new ElapsedTime();
    private double metersToSeconds(double meters) {
        return meters * 1.42857;
    }

    private double degreesToSeconds(double degrees) {
        return Math.abs(degrees * 0.0099009);
    }

    public void moveMeters(Drivetrain drivetrain, double meters) {
        timer.reset();
        double output = 0.5;
        if (meters < 0) {
            output = -0.5;
        }
        while (timer.seconds() < metersToSeconds(meters)) {
            drivetrain.drive(0, output, 0, 0);
        }
        drivetrain.drive(0,0,0,0);
    }

    public void rotateByDegrees(Drivetrain drivetrain, double degrees) {
        timer.reset();
        double output = 0.5;
        if (degrees < 0) {
            output = -0.5;
        }
        while (timer.seconds() < degreesToSeconds(degrees)) {
            drivetrain.drive(0, 0, output, 0);
        }
        drivetrain.drive(0,0,0,0);
    }
}
