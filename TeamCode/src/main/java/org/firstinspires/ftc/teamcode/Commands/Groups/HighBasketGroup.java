package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateHighBasket;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Configuration;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class HighBasketGroup extends SequentialCommandGroup {
    public HighBasketGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {

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
                    new SequentialCommandGroup(),
                    new SequentialCommandGroup(
                            new RetractExtender(extender),
                            new RotateHighBasket(arm)
                ),
                    () -> arm.getArmReference() == Configuration.Arm.HIGH_BASKET_ROTATION),
                new SequentialCommandGroup(
                        new WaitCommand(300),
                        new SetExtenderReference(extender, Configuration.Extender.HIGH_BASKET_EXTENSION)
                )
        );

        // testing
        // addCommands(new RotateArmBasket(arm));

        addRequirements(arm);
    }
}
