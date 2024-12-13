package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateDown;
import org.firstinspires.ftc.teamcode.Configuration;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class ResetGroup extends SequentialCommandGroup {
    public ResetGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {

        addCommands(
                new ScheduleCommand(
                        new InstantCommand(
                                () -> pincer.setPivotPosition(Configuration.Pincer.DEFAULT_ROTATION)
                )),
                new RetractExtender(extender),
                new RotateDown(arm),
                new ScheduleCommand(
                        new InstantCommand(
                                () -> drivetrain.setSlowMode(true)
                        )
                ),
                new ScheduleCommand(
                        new InstantCommand(
                                () -> drivetrain.setSlowMode(false)
                        )
                )
        );

        // testing
        // addCommands(new RotateArmDown(arm));

        addRequirements(arm);
    }
}
