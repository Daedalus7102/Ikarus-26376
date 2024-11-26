package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Gyroscope;
import org.firstinspires.ftc.teamcode.Subsystems.Rotator;


@TeleOp
public class TeleOperated extends OpMode {
    private GamepadEx chassisGamepad;
    private GamepadEx mechanismsGamepad;
    private Gyroscope gyroscope;
    private Drivetrain drivetrain;
    private Rotator rotator;
    private Extender extender;


    @Override
    public void init() {
        chassisGamepad = new GamepadEx(gamepad1);
        mechanismsGamepad = new GamepadEx(gamepad2);
        drivetrain = new Drivetrain(hardwareMap);
        gyroscope = new Gyroscope(hardwareMap);
        rotator = new Rotator(hardwareMap);
        extender = new Extender(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        // Resets the field relative heading
        if (chassisGamepad.getButton(Constants.Controls.resetHeading)) {
            gyroscope.resetHeading();
        }

        if (mechanismsGamepad.getButton(GamepadKeys.Button.DPAD_UP)) {
            rotator.setBasketAngle();
        } else {
            rotator.setBaseAngle();
        }
        
        // Drives the robot
        drivetrain.setSlowMode(chassisGamepad.getButton(Constants.Controls.activateSlowMode));
        if (chassisGamepad.getButton(Constants.Controls.activateTankMode)) {
            drivetrain.driveRobotOriented(chassisGamepad.getLeftX(),
                    chassisGamepad.getLeftY(),
                    chassisGamepad.getRightX());
        } else {
            drivetrain.driveFieldOriented(chassisGamepad.getLeftX(),
                    chassisGamepad.getLeftY(),
                    chassisGamepad.getRightX(),
                    gyroscope.getHeading());
        }

        // testing
        if (chassisGamepad.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
            extender.pidTest(Constants.Extender.maximumExtension);
        } else {
            extender.pidTest(Constants.Extender.minimumExtension);
        }
    }
}
