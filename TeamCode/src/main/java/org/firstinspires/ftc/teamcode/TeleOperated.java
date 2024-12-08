package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Groups.BasketGroup;
import org.firstinspires.ftc.teamcode.Commands.Groups.ResetGroup;
import org.firstinspires.ftc.teamcode.Commands.Groups.SuckModeGroup;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Gyroscope;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

// Robot code written (mostly) by Andres Perez from team Ikarus 26376
// Thanks to all the FTClib contributors for creating this awesome library! :)

@TeleOp
public class TeleOperated extends OpMode {
    private GamepadEx chassisGamepad;
    private Gyroscope gyroscope;
    private Drivetrain drivetrain;
    private Arm arm;
    private double lastTime = 0;
    private FtcDashboard dash;

    @Override
    public void init() {

        // Subsystems
        chassisGamepad = new GamepadEx(gamepad1);
        GamepadEx mechanismsGamepad = new GamepadEx(gamepad2);
        drivetrain = new Drivetrain(hardwareMap);
        gyroscope = new Gyroscope(hardwareMap);
        arm = new Arm(hardwareMap, telemetry);
        Pincer pincer = new Pincer(hardwareMap);
        ElapsedTime timer = new ElapsedTime();
        dash = FtcDashboard.getInstance();

        // Chassis controls
        GamepadButton resetHeadingButton = chassisGamepad.getGamepadButton(Constants.Controls.RESET_HEADING);
        GamepadButton tankModeButton = chassisGamepad.getGamepadButton(Constants.Controls.ACTIVATE_TANK_MODE);
        GamepadButton slowModeButton = chassisGamepad.getGamepadButton(Constants.Controls.ACTIVATE_SLOW_MODE);

        // Chassis command assignment
        resetHeadingButton.whenHeld(new InstantCommand(() -> gyroscope.resetHeading()));
        tankModeButton.whenHeld(new InstantCommand(() -> drivetrain.setTankMode(true)));
        tankModeButton.whenReleased(new InstantCommand(() -> drivetrain.setTankMode(false)));
        slowModeButton.whenHeld(new InstantCommand(() -> drivetrain.setSlowMode(true)));
        slowModeButton.whenReleased(new InstantCommand(() -> drivetrain.setSlowMode(false)));

        // Mechanisms controls
        GamepadButton riseHighBasketButton = mechanismsGamepad.getGamepadButton(Constants.Controls.BASKET_MODE);
        GamepadButton suckModeButton = mechanismsGamepad.getGamepadButton(Constants.Controls.SUCK_MODE);
        GamepadButton offsetUpButton = mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_UP);
        GamepadButton offsetDownButton = mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_DOWN);
        GamepadButton ejectPieceButton = mechanismsGamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT);
        GamepadButton suckPieceButton = mechanismsGamepad.getGamepadButton(GamepadKeys.Button.A);

        // Mechanisms command assignment
        riseHighBasketButton.whenHeld(new ConditionalCommand(new SequentialCommandGroup(),
                new BasketGroup(arm), () -> mechanismsGamepad.getButton(Constants.Controls.SUCK_MODE)));
        riseHighBasketButton.whenReleased(new ConditionalCommand(new SequentialCommandGroup(),
                new ResetGroup(arm), () -> mechanismsGamepad.getButton(Constants.Controls.SUCK_MODE)));

        suckModeButton.whenHeld(new ConditionalCommand(new SequentialCommandGroup(),
                new SuckModeGroup(arm), () -> mechanismsGamepad.getButton(Constants.Controls.BASKET_MODE)));
        suckModeButton.whenReleased(new ConditionalCommand(new SequentialCommandGroup(),
                new ResetGroup(arm), () -> mechanismsGamepad.getButton(Constants.Controls.BASKET_MODE)));

        offsetUpButton.and(suckModeButton).whileActiveContinuous(new InstantCommand(() -> arm.addOffset(20)));
        offsetDownButton.and(suckModeButton).whileActiveContinuous(new InstantCommand(() -> arm.addOffset(-20)));
    }

    @Override
    public void loop() {
//
//        TelemetryPacket tele = new TelemetryPacket();
//        tele.put("Uhh is this key pressed", chassisGamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER));
//        dash.sendTelemetryPacket(tele);
//

        // Drives the robot
        drivetrain.drive(chassisGamepad.getLeftX(),
                    chassisGamepad.getLeftY(),
                    chassisGamepad.getRightX(),
                    gyroscope.getHeading());

        // Very important line, do not remove
        com.arcrobotics.ftclib.command.CommandScheduler.getInstance().run();
    }
}


