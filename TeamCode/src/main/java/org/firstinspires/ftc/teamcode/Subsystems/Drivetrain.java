package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain extends SubsystemBase {
    private HardwareMap hardwareMap;
    private MecanumDrive drive;
    private double multiplier = Constants.Drive.regularSpeedMultiplier;

    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;

    public Drivetrain(HardwareMap hM) {
        if (!Constants.Activation.enableDrivetrain) { return; }

        hardwareMap = hM;
        frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    // TODO: Fix inverted driving
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
