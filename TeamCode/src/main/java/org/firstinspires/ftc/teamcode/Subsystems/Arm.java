package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Libraries.Dampener;
import org.firstinspires.ftc.teamcode.Libraries.Math;

public class Arm extends SubsystemBase {
    private final PIDController armPID;

    private final DcMotorEx extenderMotor;
    
    private final PIDController extenderPID;
    private double extenderOffset = 0;
    private double extenderReference = 0;
    private Dampener dampener;
    private final double MAXIMUM_EXTENSION = Constants.Extender.MAXIMUM_EXTENSION;
    private final double MINIMUM_EXTENSION = Constants.Extender.MINIMUM_EXTENSION;
    private Telemetry telemetry;
    
    private final FtcDashboard ftcdash = FtcDashboard.getInstance();

    private final DcMotorEx armMotor1;
    private final DcMotorEx armMotor2;


    public Arm(HardwareMap hM, Telemetry telemetry) {
        extenderMotor = hM.get(DcMotorEx.class, "extender");
        extenderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extenderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extenderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extenderPID = new PIDController(0.005, 0, 0);
        extenderPID.setTolerance(20);

        armMotor1 = hM.get(DcMotorEx.class, "rotator_1");
        armMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor2 = hM.get(DcMotorEx.class, "rotator_2");
        armMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPID = new PIDController(0.001,0,0);
        armPID.setTolerance(20);

        this.telemetry = telemetry;
    }

    private void setArmPower(double output) {
        armMotor1.setPower(output * 0.7);
        armMotor2.setPower(output * 0.7);
    }

    private double safePID(double PIDOutput) {
        return Math.clamp(1,-1, PIDOutput);
    }

    public void riseToBasket() {
        armPID.setPID(0.01,0,0);
        armPID.setTolerance(10);
        armPID.setSetPoint(Constants.Rotator.MAXIMUM_ROTATION);
    }

    public void lower() {;
        // we do a little bit of hacky PID black magic so it lowers smoothly :)
        armPID.setPID(0.001,0,0);
        armPID.setTolerance(Constants.Rotator.MAXIMUM_ROTATION - 50);
        armPID.setSetPoint(Constants.Rotator.MINIMUM_ROTATION);
    }

    public boolean armIsAtReference() {
        return armPID.atSetPoint();
    }

    public double getArmReference() {
        return armPID.getSetPoint();
    }

    /*
    
    THIS IS ANOTHER SUBSYSTEM
    
    */

    // just to be absolutely sure that the extender will never exceed its extenderReference value
    private double safeReference(double extenderReference) {
        ftcdash.sendTelemetryPacket(new TelemetryPacket());
        return Math.clamp(MAXIMUM_EXTENSION, MINIMUM_EXTENSION, extenderReference);
    }

    public void setExtenderReference(double newReference) {
        resetOffset();
        newReference = safeReference(newReference);
        this.extenderReference = newReference;
        extenderPID.setSetPoint(newReference);
    }

    public void addOffset(double x) {
        if (extenderReference + x + extenderOffset < MINIMUM_EXTENSION) {
            return;
        } else if (extenderReference + x + extenderOffset > MAXIMUM_EXTENSION) {
            return;
        }

        extenderOffset += x;
        extenderPID.setSetPoint(safeReference(extenderOffset + extenderReference));
    }

    public void resetOffset() {
        extenderOffset = 0;
        extenderPID.setSetPoint(safeReference(extenderReference));
    }

    public boolean extenderIsAtReference() {

        return extenderPID.atSetPoint();
    }
    
    @Override
    public void periodic() {
        
        TelemetryPacket pack = new TelemetryPacket();

        pack.put("Arm PID output", armPID.calculate(armMotor1.getCurrentPosition()));
        pack.put("Arm position", armMotor1.getCurrentPosition());
        pack.put("Arm reference", getArmReference());
        pack.put("Arm is at reference", armPID.atSetPoint());

        pack.put("Extender PID output", extenderPID.calculate(extenderMotor.getCurrentPosition()));
        pack.put("Extender position", extenderMotor.getCurrentPosition());
        pack.put("Extender reference", extenderPID.getSetPoint());
        pack.put("Extender is at reference", extenderPID.atSetPoint());

        ftcdash.sendTelemetryPacket(pack);

        if (!armPID.atSetPoint()) {
            setArmPower(safePID(armPID.calculate(armMotor1.getCurrentPosition())));
        }
        if (!extenderPID.atSetPoint()) {
            extenderMotor.setPower(safePID(extenderPID.calculate(extenderMotor.getCurrentPosition())));
        }
    }
}
