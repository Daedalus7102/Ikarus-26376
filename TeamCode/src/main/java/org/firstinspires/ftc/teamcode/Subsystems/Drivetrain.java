package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain extends SubsystemBase {
    private final MecanumDrive drive;
    private double multiplier = Constants.Drive.REGULAR_SPEED_MULTIPLIER;
    private boolean driveTank = false;

    public Drivetrain(HardwareMap hM) {

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

    public void drive(double leftX, double leftY, double rightX, double heading) {

        leftX *= -multiplier;
        leftY *= -multiplier;
        rightX *= -multiplier;

        if (driveTank) {
            drive.driveRobotCentric(leftX, leftY, rightX);
            return;
        }
        drive.driveFieldCentric(leftX, leftY, rightX, heading);
    }

    public void setTankMode(boolean option) {
        driveTank = option;
    }

    public void setSlowMode(boolean option) {
        if (option) {
            multiplier = Constants.Drive.SLOW_SPEED_MULTIPLIER;
            return;
        }
        multiplier = Constants.Drive.REGULAR_SPEED_MULTIPLIER;
    }
}
