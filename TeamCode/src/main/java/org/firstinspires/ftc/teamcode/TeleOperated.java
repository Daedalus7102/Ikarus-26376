package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ExtenderCommands.PartialExtendCommand;
import org.firstinspires.ftc.teamcode.Commands.HighBasketGroup;
import org.firstinspires.ftc.teamcode.Commands.ResetGroup;
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
    private Extender extender;
    private Rotator rotator;

    @Override
    public void init() {
        chassisGamepad = new GamepadEx(gamepad1);
        mechanismsGamepad = new GamepadEx(gamepad2);
        drivetrain = new Drivetrain(hardwareMap);
        gyroscope = new Gyroscope(hardwareMap);
        extender = new Extender(hardwareMap, telemetry);
        rotator = new Rotator(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        GamepadButton riseHighBasketButton = mechanismsGamepad.getGamepadButton(Constants.Controls.BASKET_MODE);
        GamepadButton suckModeButton = mechanismsGamepad.getGamepadButton(Constants.Controls.SUCK_MODE);
        GamepadButton offsetUpButton = mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_UP);
        GamepadButton offsetDownButton = mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_DOWN);

        riseHighBasketButton.whenHeld(new HighBasketGroup(rotator, extender));
        riseHighBasketButton.whenReleased(new ResetGroup(rotator, extender));

        offsetUpButton.and(riseHighBasketButton).whileActiveContinuous(new InstantCommand(() -> {
            extender.addOffset(0.02);
        }));
        offsetDownButton.and(riseHighBasketButton).whileActiveContinuous(new InstantCommand(() -> {
            extender.addOffset(-0.02);
        }));

        suckModeButton.whenHeld(new PartialExtendCommand(extender));
        suckModeButton.whenReleased(new ResetGroup(rotator, extender));

        offsetUpButton.and(suckModeButton).whileActiveContinuous(new InstantCommand(() -> {
            extender.addOffset(0.02);
        }));
        offsetDownButton.and(suckModeButton).whileActiveContinuous(new InstantCommand(() -> {
            extender.addOffset(-0.02);
        }));

        // Resets the field relative heading
        if (chassisGamepad.getButton(Constants.Controls.resetHeading)) {
            gyroscope.resetHeading();
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

        // Very important line, do not remove
        com.arcrobotics.ftclib.command.CommandScheduler.getInstance().run();
    }
}
