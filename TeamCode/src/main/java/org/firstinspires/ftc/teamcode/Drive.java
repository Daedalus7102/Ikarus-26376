package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp
public class Drive extends OpMode {
    private MecanumDrive drive;
    private GamepadEx chassisGamepad;
    private GamepadEx mechanismsGamepad;
    private BHI260IMU imu;

    @Override
    public void init() {
        chassisGamepad = new GamepadEx(gamepad1);
        mechanismsGamepad = new GamepadEx(gamepad2);

        drive = new MecanumDrive(
                new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312),
                new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312)
        );

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        double multiplier;
        if (chassisGamepad.getButton(Constants.Controls.activateSlowMode)) {
            multiplier = Constants.Drive.slowSpeedMultiplier;
        } else {
            multiplier = Constants.Drive.regularSpeedMultiplier;
        }

        // TODO: Fix inverted driving
        double leftX = -chassisGamepad.getLeftX() * multiplier;
        double leftY = -chassisGamepad.getLeftY() * multiplier;
        double rightX = -chassisGamepad.getRightX() * multiplier;

        // In this case, the 'front' of the control hub is where the servo pins are
        double heading = imu.getRobotYawPitchRollAngles().getYaw();

        // Resets the field relative heading
        if (chassisGamepad.getButton(Constants.Controls.resetHeading)) {
            imu.resetYaw();
        }

        if (chassisGamepad.getButton(Constants.Controls.activateTankMode)) {
            drive.driveRobotCentric(leftX, leftY, rightX);
        } else {
            drive.driveFieldCentric(leftX, leftY, rightX, heading);
        }
    }
}
