package org.firstinspires.ftc.teamcode.Commands.Groups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.Commands.RotateArmDown;
import org.firstinspires.ftc.teamcode.Commands.Commands.SetExtenderReference;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class ResetGroup extends SequentialCommandGroup {
    public ResetGroup(Arm arm) {
        addCommands(new SetExtenderReference(arm, Constants.Extender.MINIMUM_EXTENSION), new RotateArmDown(arm));
        addRequirements(arm);
    }
}
