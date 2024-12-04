package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain extends SubsystemBase {
    private MecanumDrive drive;
    private double multiplier = Constants.Drive.regularSpeedMultiplier;

    public Drivetrain(HardwareMap hM) {
        if (!Constants.Activation.enableDrivetrain) { return; }

        MotorEx frontLeft = new MotorEx(hM, "frontLeft", Motor.GoBILDA.RPM_312);
        MotorEx frontRight = new MotorEx(hM, "frontRight", Motor.GoBILDA.RPM_312);
        MotorEx backLeft = new MotorEx(hM, "backLeft", Motor.GoBILDA.RPM_312);
        MotorEx backRight = new MotorEx(hM, "backRight", Motor.GoBILDA.RPM_312);


        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void driveFieldOriented(double leftX, double leftY, double rightX, double heading) {
        if (!Constants.Activation.enableDrivetrain) { return; }

        leftX *= -multiplier;
        leftY *= -multiplier;
        rightX *= -multiplier;

        drive.driveFieldCentric(leftX, leftY, rightX, heading);
    }

    public void driveRobotOriented(double leftX, double leftY, double rightX) {
        if (!Constants.Activation.enableDrivetrain) { return;}

        leftX *= -multiplier;
        leftY *= -multiplier;
        rightX *= -multiplier;

        drive.driveRobotCentric(leftX, leftY, rightX);
    }

    public void setSlowMode(boolean option) {
        if (!Constants.Activation.enableDrivetrain) { return; }

        if (option) {
            multiplier = Constants.Drive.slowSpeedMultiplier;
            return;
        }
        multiplier = Constants.Drive.regularSpeedMultiplier;
    }

}
