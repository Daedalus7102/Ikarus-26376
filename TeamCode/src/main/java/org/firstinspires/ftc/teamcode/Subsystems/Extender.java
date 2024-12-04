package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Libraries.Math;
import org.firstinspires.ftc.teamcode.Libraries.PID;

public class Extender extends SubsystemBase {
    private DcMotorEx extender_motor;
    private PID pidController;
    private double offset;
    private double reference;

    private Telemetry tele;

    public Extender(HardwareMap hM, Telemetry tel) {
        if (!Constants.Activation.enableExtender) { return; }
        extender_motor = hM.get(DcMotorEx.class, "extender");

        offset = 0;
        reference = 0;

        extender_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extender_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extender_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        pidController = new PID(0.007, 0, 0, 15);
        tele = tel;
    }

    public void extend() {
        if (!Constants.Activation.enableExtender) { return; }
        reference = Constants.Extender.MAXIMUM_EXTENSION;
        pidController.setReference(Constants.Extender.MAXIMUM_EXTENSION);
    }

    public void partialExtend() {
        if (!Constants.Activation.enableExtender) { return; }
        reference = Constants.Extender.PARTIAL_EXTENSION;
        pidController.setReference(Constants.Extender.PARTIAL_EXTENSION);
    }

    public void retract() {
        if (!Constants.Activation.enableExtender) { return; }
        reference = Constants.Extender.MINIMUM_EXTENSION;
        pidController.setReference(Constants.Extender.MINIMUM_EXTENSION);
    }

    public void addOffset(double x) {
        offset += x;
        pidController.setReference(Math.clamp(Constants.Extender.MAXIMUM_EXTENSION,
                Constants.Extender.MINIMUM_EXTENSION, offset+reference));
    }

    public void resetOffset() {
        offset = 0;
        pidController.setReference(Math.clamp(Constants.Extender.MAXIMUM_EXTENSION,
                Constants.Extender.MINIMUM_EXTENSION, reference));
    }

    public boolean isAtReference() {
        return pidController.isAtReference();
    }

    @Override
    public void periodic() {
        tele.addData("Current position", extender_motor.getCurrentPosition());
        tele.addData("pid output", pidController.calculate(extender_motor.getCurrentPosition()));
        tele.addData("reference with offset", pidController.getReference());
        if (!Constants.Activation.enableExtender) { return; }

        extender_motor.setPower(pidController.calculate(extender_motor.getCurrentPosition()));
    }
}