package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configuration;
import org.firstinspires.ftc.teamcode.Misc.PincerLUT;

public class Pincer extends SubsystemBase {
    private final Servo pivotServo;
    private final CRServo suctionServo;
    private final PincerLUT pincerLUT = new PincerLUT();
    private final Extender extender;
    private boolean useLUT = true;
    private double reference;
    private double pivotOffset;

    // Jeremy
    public Pincer(HardwareMap hM, Extender extender) {
        pivotServo = hM.get(Servo.class, "pincer_pivot");
        suctionServo = hM.get(CRServo.class, "pincer_suck");
        this.extender = extender;
        this.reference = Configuration.Pincer.DEFAULT_ROTATION;
        this.pivotOffset = 0;
    }


    public void setPivotPosition(double reference) {
        this.useLUT = false;
        resetOffset();
        pivotServo.setPosition(reference);
    }

    public void setSuckMode() {
        this.useLUT = true;
    }

    // Do people actually read these?

    public void addOffset(double x) {
        pivotOffset += x;
    }

    public void resetOffset() {
        pivotOffset = 0;
        pivotServo.setPosition(reference);
    }

    public void setSuctionPower(double speed) {
        suctionServo.setPower(speed);
    }

    @Override
    public void periodic() {
        //pivotServo.setPosition(configuration.rotation);

        if (useLUT) {
            reference = pincerLUT.calculate(extender.getExtenderReference()) + pivotOffset;
        } else {
            reference = Configuration.Pincer.DEFAULT_ROTATION;
        }

        pivotServo.setPosition(reference);
    }
}
