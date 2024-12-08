package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class Pincer extends SubsystemBase {
    private final Servo pivotServo;
    private final CRServo suctionServo;
    private double reference;
    private double speed;

    // Jeremy
    public Pincer(HardwareMap hM) {
        pivotServo = hM.get(Servo.class, "pincer_pivot");
        suctionServo = hM.get(CRServo.class, "pincer_suck");
        reference = Constants.Pincer.INIT_ROTATION;
        speed = 0;
    }

    public void setPivotPosition(double reference) {
        this.reference = reference;
    }

    public void setSuctionPower(double speed) {
        this.speed = speed;
    }

    @Override
    public void periodic() {
        pivotServo.setPosition(reference);
        suctionServo.getPower();
    }


}
