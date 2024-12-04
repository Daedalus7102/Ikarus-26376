package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Libraries.PID;

public class Rotator extends SubsystemBase {
    private PID controller;

    private DcMotorEx motor_1;
    private DcMotorEx motor_2;
    private Telemetry tel;

    public Rotator(HardwareMap hM, Telemetry tele) {
        if (!Constants.Activation.enableRotator) { return;}

        motor_1 = hM.get(DcMotorEx.class, "rotator_1");
        motor_1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor_1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor_2 = hM.get(DcMotorEx.class, "rotator_2");
        motor_2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        tel = tele;

        controller = new PID(0.001,0,0,300);

    }

    private void setPower(double output) {
        if (!Constants.Activation.enableRotator) { return; }

        motor_1.setPower(output * 0.7);
        motor_2.setPower(output * 0.7);
    }

    public void riseToBasket() {
        if (!Constants.Activation.enableRotator) { return; }

        controller.setPIDCoefficients(0.01,0,0);
        controller.setAllowedError(10);
        controller.setReference(Constants.Rotator.maximumRotation);
    }

    public void lower() {
        if (!Constants.Activation.enableRotator) { return; }

        // we do a little bit of hacky PID black magic to get it to lower smoothly
        controller.setPIDCoefficients(0.001,0,0);
        controller.setAllowedError(Constants.Rotator.maximumRotation - 50);
        controller.setReference(Constants.Rotator.minimumRotation);
    }

    public boolean isAtReference() {
        if (!Constants.Activation.enableRotator) { return false; }

        return controller.isAtReference();
    }

    @Override
    public void periodic() {
        if (!Constants.Activation.enableRotator) { return; }
        setPower(controller.calculate(motor_1.getCurrentPosition()));
    }

}
