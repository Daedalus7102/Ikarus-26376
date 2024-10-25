package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain {
    private final HardwareMap hardwareMap;
    private final MecanumDrive drive;
    private double multiplier = Constants.Drive.regularSpeedMultiplier;
    public Drivetrain(HardwareMap hM) {
        hardwareMap = hM;
        drive = new MecanumDrive(
                new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312)
        );
    }

    // TODO: Fix inverted driving
    public void driveFieldOriented(double leftX, double leftY, double rightX, double heading) {
        leftX *= -multiplier;
        leftY *= -multiplier;
        rightX *= -multiplier;

        drive.driveFieldCentric(leftX, leftY, rightX, heading);
    }

    public void driveRobotOriented(double leftX, double leftY, double rightX) {
        leftX *= -multiplier;
        leftY *= -multiplier;
        rightX *= -multiplier;

        drive.driveRobotCentric(leftX, leftY, rightX);
    }

    public void setSlowMode(boolean option) {
        if (option) {
            multiplier = Constants.Drive.slowSpeedMultiplier;
            return;
        }
        multiplier = Constants.Drive.regularSpeedMultiplier;
    }

}
