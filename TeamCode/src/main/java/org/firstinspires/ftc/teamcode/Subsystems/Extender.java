package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Libraries.PID;


public class Extender extends SubsystemBase {
    private HardwareMap hardwareMap;
    private DcMotorEx extender_motor;
    private Telemetry telemetry;
    private PID pidController;

    public Extender(HardwareMap hM, Telemetry telemetry) {
        if (!Constants.Activation.enableExtender) { return; }
        this.telemetry = telemetry;
        hardwareMap = hM;
        extender_motor = hardwareMap.get(DcMotorEx.class, "extender");
        extender_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extender_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pidController = new PID(0.006, 0.1, 0, 0.25,
                0.8, 0, 10, telemetry);
    }

    public void pidTest(double reference) {
        extender_motor.setPower(pidController.tick(reference, extender_motor.getCurrentPosition()));
    }

}
