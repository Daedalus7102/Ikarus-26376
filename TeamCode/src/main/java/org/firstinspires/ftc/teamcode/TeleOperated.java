package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Gyroscope;


@TeleOp
public class TeleOperated extends OpMode {
    private GamepadEx chassisGamepad;
    private GamepadEx mechanismsGamepad;
    private Gyroscope gyroscope;
    private Drivetrain drivetrain;


    @Override
    public void init() {
        chassisGamepad = new GamepadEx(gamepad1);
        mechanismsGamepad = new GamepadEx(gamepad2);
        gyroscope = new Gyroscope();
        drivetrain = new Drivetrain();
    }

    @Override
    public void loop() {
        // Resets the field relative heading
        if (chassisGamepad.getButton(Constants.Controls.resetHeading)) {
            gyroscope.resetHeading();
        }

        // Drives the robot
        drivetrain.setSlowMode(chassisGamepad.getButton(Constants.Controls.activateSlowMode));
        if (chassisGamepad.getButton(Constants.Controls.activateTankMode)) {
            drivetrain.driveRobotOriented(chassisGamepad.getLeftX(),
                    chassisGamepad.getLeftY(),
                    chassisGamepad.getRightY());
        } else {
            drivetrain.driveFieldOriented(chassisGamepad.getLeftX(),
                    chassisGamepad.getLeftY(),
                    chassisGamepad.getRightY(),
                    gyroscope.getHeading());
        }

        // Pending
    }
}
