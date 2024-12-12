package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateArmDown;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class ResetGroup extends SequentialCommandGroup {
    public ResetGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {

        addCommands(
                new ScheduleCommand(
                        new InstantCommand(
                                pincer::setSuckMode
                )),
                new RetractExtender(extender),
                new RotateArmDown(arm),
                new ScheduleCommand(
                        new InstantCommand(
                                () -> drivetrain.setSlowMode(true)
                        )
                )
        );

        // testing
        // addCommands(new RotateArmDown(arm));

        addRequirements(arm);
    }
}
