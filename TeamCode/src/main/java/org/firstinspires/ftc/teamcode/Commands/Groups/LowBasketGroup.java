package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateLowBasket;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Configuration;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class LowBasketGroup extends SequentialCommandGroup {
    public LowBasketGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {
        addCommands(
            new ScheduleCommand(
                    new InstantCommand(
                            () -> drivetrain.setSlowMode(true)
                    )
            ),
                new ScheduleCommand(
                        new InstantCommand(
                                () -> pincer.setPivotPosition(Configuration.Pincer.DEFAULT_ROTATION))
                ),
                new ConditionalCommand(
                        new SetExtenderReference(extender, Configuration.Extender.LOW_BASKET_EXTENSION),
                        new SequentialCommandGroup(
                                new RetractExtender(extender),
                                new RotateLowBasket(arm),
                                new SetExtenderReference(extender, Configuration.Extender.LOW_BASKET_EXTENSION)
                        ),
                        () -> arm.getArmReference() == Configuration.Arm.LOW_BASKET_ROTATION));
    }
}
