package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RotateArmBasket;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class BasketGroup extends SequentialCommandGroup {
    public BasketGroup(Arm arm) {

        addCommands(new ConditionalCommand(new SetExtenderReference(arm, Constants.Extender.MAXIMUM_EXTENSION), new SequentialCommandGroup(
                        new SetExtenderReference(arm, Constants.Extender.MINIMUM_EXTENSION),
                        new RotateArmBasket(arm),
                        new SetExtenderReference(arm, Constants.Extender.MAXIMUM_EXTENSION)),

                () -> arm.getArmReference() == Constants.Rotator.MAXIMUM_ROTATION));
        addRequirements(arm);
    }
}
