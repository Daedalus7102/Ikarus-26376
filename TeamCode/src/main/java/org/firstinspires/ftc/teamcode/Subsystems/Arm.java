package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Misc.MotorUtils;

public class Arm extends SubsystemBase {
    private final PIDController armPID;
    private final DcMotorEx armMotor1;
    private final DcMotorEx armMotor2;
    private boolean isLowering = false;
    private double armMotorPosition;


    public Arm(HardwareMap hM) {
        armMotor1 = hM.get(DcMotorEx.class, "rotator_1");
        armMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor1.setDirection(DcMotorSimple.Direction.REVERSE);

        armMotor2 = hM.get(DcMotorEx.class, "rotator_2");
        armMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armPID = new PIDController(0.001,0,0);
        armPID.setTolerance(300);

        armMotorPosition = armMotor1.getCurrentPosition();
    }

    private void setArmPower(double output) {
        armMotor1.setPower(output);
        armMotor2.setPower(output);
    }

    @Config
    private static class riseParams {
        public static double kP = 0.015;
        public static double kI = 0.1;
        public static double kD = 0.0005;
        public static double allowedError = 10;
    }

    public void riseToBasket() {
        isLowering = false;
        armPID.reset();
        armPID.setPID(riseParams.kP,riseParams.kI,riseParams.kD);
        armPID.setTolerance(riseParams.allowedError);
        armPID.setSetPoint(Constants.Arm.MAXIMUM_ROTATION);
    }

    public void lower() {
        isLowering = true;
        armPID.reset();
        // we do a little bit of hacky PID black magic so it lowers smoothly :)
        armPID.setPID(0.0015,0,0);
        armPID.setTolerance(Constants.Arm.MAXIMUM_ROTATION - 30);
        armPID.setSetPoint(Constants.Arm.MINIMUM_ROTATION);
    }

    public boolean armIsAtReference() {
        return armPID.atSetPoint();
    }

    public double getArmReference() {
        return armPID.getSetPoint();
    }
    
    @Override
    public void periodic() {
        armMotorPosition = armMotor1.getCurrentPosition();
        double PIDResult = MotorUtils.safePID(
                armPID.calculate(armMotorPosition), Constants.Arm.MAXIMUM_MOTOR_OUTPUT);

        TelemetryPacket pack = new TelemetryPacket();

        pack.put("Arm PID output", PIDResult);
        pack.put("Arm position", armMotorPosition);
        pack.put("Arm reference", getArmReference());
        pack.put("Arm is at reference", armPID.atSetPoint());

        FtcDashboard.getInstance().sendTelemetryPacket(pack);

        if (armPID.atSetPoint() && isLowering) {
            setArmPower(0);
        } else {
            setArmPower(PIDResult);
        }
    }
}
