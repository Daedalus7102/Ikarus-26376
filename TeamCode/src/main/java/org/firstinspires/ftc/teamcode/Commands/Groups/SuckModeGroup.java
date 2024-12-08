package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RotateArmDown;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class SuckModeGroup extends SequentialCommandGroup {
    public SuckModeGroup(Arm arm) {

        addCommands(new ConditionalCommand(new SetExtenderReference(arm, Constants.Extender.PARTIAL_EXTENSION), new SequentialCommandGroup(
                new SetExtenderReference(arm, Constants.Extender.MINIMUM_EXTENSION),
                new RotateArmDown(arm),
                new SetExtenderReference(arm, Constants.Extender.PARTIAL_EXTENSION)),

                () -> arm.getArmReference() == Constants.Rotator.MINIMUM_ROTATION));
        addRequirements(arm);
    }
}
