package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateDown;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Configuration;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class SuckModeGroup extends SequentialCommandGroup {
    public SuckModeGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {

        addCommands(
                new ScheduleCommand(
                        new InstantCommand(
                                () -> drivetrain.setSlowMode(true)
                        )
                ),
                new ScheduleCommand(
                        new InstantCommand(
                                pincer::setSuckMode)
                ),
                new ConditionalCommand(
                        new RetractExtender(extender),
                        new SequentialCommandGroup(
                                new SetExtenderReference(extender, Configuration.Extender.MINIMUM_EXTENSION),
                                new RotateDown(arm)),
                () -> arm.getArmReference() == Configuration.Arm.MINIMUM_ROTATION),
                new ScheduleCommand(
                        new InstantCommand(
                             () -> drivetrain.setSlowMode(true)
                        )
                )
        );

        addRequirements(arm);
    }
}
