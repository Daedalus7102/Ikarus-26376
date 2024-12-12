package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Misc.PincerLUT;

public class Pincer extends SubsystemBase {
    private final Servo pivotServo;
    private final CRServo suctionServo;
    private final PincerLUT pincerLUT = new PincerLUT();
    private final Extender extender;
    private boolean useLUT = true;

    // Jeremy
    public Pincer(HardwareMap hM, Extender extender) {
        pivotServo = hM.get(Servo.class, "pincer_pivot");
        suctionServo = hM.get(CRServo.class, "pincer_suck");
        this.extender = extender;
    }


    public void setPivotPosition(double reference) {
        this.useLUT = false;
        pivotServo.setPosition(reference);
    }

    public void setSuckMode() {
        this.useLUT = true;
    }

    public void setSuctionPower(double speed) {
        suctionServo.setPower(speed);
    }

    @Override
    public void periodic() {
        //pivotServo.setPosition(configuration.rotation);
        if (useLUT) {
            pivotServo.setPosition(pincerLUT.calculate(extender.getExtenderReference()));
        }
    }
}
