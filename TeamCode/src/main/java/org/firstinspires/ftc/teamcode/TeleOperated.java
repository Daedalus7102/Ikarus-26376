package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Commands.DriveRobot;
import org.firstinspires.ftc.teamcode.Commands.Groups.BasketGroup;
import org.firstinspires.ftc.teamcode.Commands.Groups.ResetGroup;
import org.firstinspires.ftc.teamcode.Commands.Groups.SuckModeGroup;
import org.firstinspires.ftc.teamcode.Misc.TriggerButton;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Gyroscope;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

import java.util.List;

// Robot code written by Andres Perez from team Ikarus 26376 with the help of FTCLib
// Thanks to all the FTClib contributors for creating this awesome library! :)

@TeleOp(name = "TeleOp")
public class TeleOperated extends CommandOpMode {
    private Drivetrain drivetrain;
    private Gyroscope gyroscope;
    private Extender extender;
    private Pincer pincer;
    private Arm arm;

    private GamepadEx chassisGamepad;
    private GamepadEx mechanismsGamepad;

    @Override
    public void initialize() {


        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        drivetrain = new Drivetrain(hardwareMap);
        gyroscope = new Gyroscope(hardwareMap);
        extender = new Extender(hardwareMap);
        pincer = new Pincer(hardwareMap, extender);
        arm = new Arm(hardwareMap);

        chassisGamepad = new GamepadEx(gamepad1);
        mechanismsGamepad = new GamepadEx(gamepad2);

        // Chassis driving
        schedule(new DriveRobot(drivetrain, gyroscope,
                () -> chassisGamepad.getLeftX(),
                () -> chassisGamepad.getLeftY(),
                () -> chassisGamepad.getRightX()));

        // pincer.setPivotPosition(Constants.Pincer.DISABLED_ROTATION);

        TriggerButton tankModeTrigger = new TriggerButton(
                () -> chassisGamepad.getTrigger(Constants.Controls.ACTIVATE_TANK_MODE));
        TriggerButton slowModeTrigger = new TriggerButton(
                () -> chassisGamepad.getTrigger(Constants.Controls.ACTIVATE_SLOW_MODE));

        tankModeTrigger
                .whenHeld(new InstantCommand(() -> drivetrain.setTankMode(true)))
                .whenReleased(new InstantCommand(() -> drivetrain.setTankMode(false)));

        slowModeTrigger
                .whenHeld(new InstantCommand(() -> drivetrain.setSlowMode(true)))
                .whenReleased(new InstantCommand(() -> drivetrain.setSlowMode(false)));

        chassisGamepad.getGamepadButton(Constants.Controls.RESET_HEADING)
                .whenHeld(new InstantCommand(() -> gyroscope.resetHeading()));

        // Mechanisms controls

        TriggerButton basketModeTrigger = new TriggerButton(
                () -> mechanismsGamepad.getTrigger(Constants.Controls.BASKET_MODE));

        TriggerButton suckModeTrigger = new TriggerButton(
                () -> mechanismsGamepad.getTrigger(Constants.Controls.SUCK_MODE));

        basketModeTrigger
                .whenHeld(new BasketGroup(arm, extender, pincer, drivetrain))
                .whenReleased(new ConditionalCommand(
                        new SequentialCommandGroup(),
                        new ResetGroup(arm, extender, pincer, drivetrain),
                        suckModeTrigger::get
                ));

        suckModeTrigger
                .whenHeld(new SuckModeGroup(arm, extender, pincer, drivetrain))
                .whenReleased(new ConditionalCommand(
                        new SequentialCommandGroup(),
                        new ResetGroup(arm, extender, pincer, drivetrain),
                        basketModeTrigger::get
                ));

        mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_UP)
                .and(suckModeTrigger)
                .whileActiveContinuous(new InstantCommand(() -> extender.addOffset(
                        Constants.Extender.OFFSET_SPEED
                )));

        mechanismsGamepad.getGamepadButton(Constants.Controls.OFFSET_DOWN)
                .and(suckModeTrigger)
                .whileActiveContinuous(new InstantCommand(() -> extender.addOffset(
                        -Constants.Extender.OFFSET_SPEED
                )));

        mechanismsGamepad.getGamepadButton(Constants.Controls.SPIT_PIECE)
                .whenHeld(new InstantCommand(() -> pincer.setSuctionPower(1)))
                .whenReleased(new InstantCommand(() -> pincer.setSuctionPower(0)));

        mechanismsGamepad.getGamepadButton(Constants.Controls.SUCK_PIECE)
                .whenHeld(new InstantCommand(() -> pincer.setSuctionPower(-1)))
                .whenReleased(new InstantCommand(() -> pincer.setSuctionPower(0)));

        mechanismsGamepad.getGamepadButton(Constants.Controls.RESET_ENCODER)
                .whenHeld(new InstantCommand(() -> extender.resetExtenderEncoder()));
    }
}


