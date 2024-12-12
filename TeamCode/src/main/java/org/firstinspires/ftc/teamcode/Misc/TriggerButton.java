package org.firstinspires.ftc.teamcode.Misc;

import com.arcrobotics.ftclib.command.button.Button;

import java.util.function.DoubleSupplier;

public class TriggerButton extends Button {
    DoubleSupplier leftTrigger;
    public TriggerButton(DoubleSupplier leftTrigger) {
        this.leftTrigger = leftTrigger;
    }

    @Override
    public boolean get() {
        return leftTrigger.getAsDouble() > 0.3;
    }
}
