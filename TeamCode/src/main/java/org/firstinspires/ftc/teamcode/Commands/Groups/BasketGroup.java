package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RetractExtender;
import org.firstinspires.ftc.teamcode.Commands.Commands.RotateArmBasket;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

public class BasketGroup extends SequentialCommandGroup {
    public BasketGroup(Arm arm, Extender extender, Pincer pincer, Drivetrain drivetrain) {

        addCommands(
                new ScheduleCommand(
                        new InstantCommand(
                                () -> drivetrain.setSlowMode(true)
                        )
                ),
                new ScheduleCommand(
                        new InstantCommand(
                                () -> pincer.setPivotPosition(Constants.Pincer.BASKET_ROTATION))
                ),
                new ConditionalCommand(
                    new SetExtenderReference(extender, Constants.Extender.MAXIMUM_BASKET_EXTENSION),
                    new SequentialCommandGroup(
                            new RetractExtender(extender),
                            new RotateArmBasket(arm),
                            new SetExtenderReference(extender, Constants.Extender.MAXIMUM_BASKET_EXTENSION)
                ),
                    () -> arm.getArmReference() == Constants.Arm.MAXIMUM_ROTATION));

        // testing
        // addCommands(new RotateArmBasket(arm));

        addRequirements(arm);
    }
}
