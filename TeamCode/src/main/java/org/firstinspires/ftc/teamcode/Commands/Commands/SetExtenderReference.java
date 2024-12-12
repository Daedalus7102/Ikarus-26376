package org.firstinspires.ftc.teamcode.Commands.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Extender;

public class SetExtenderReference extends CommandBase {
    private final Extender extender;
    private final double reference;
    public SetExtenderReference(Extender extender, double reference) {
        this.extender = extender;
        this.reference = reference;
        addRequirements(extender);
    }

    @Override
    public void initialize() {
        extender.setExtenderReference(reference);
    }

    @Override
    public boolean isFinished() {
        return extender.extenderIsAtReference();
    }
}
