package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;

public class RetractExtender extends ParallelRaceGroup {
    public RetractExtender(Extender extender) {
        addCommands(
                new SequentialCommandGroup(
                        new WaitCommand(3000),
                        new ScheduleCommand(
                                new InstantCommand(extender::resetExtenderEncoder, extender)
                        )
                ),
                new SetExtenderReference(extender, Constants.Extender.MINIMUM_EXTENSION));
    }
}
