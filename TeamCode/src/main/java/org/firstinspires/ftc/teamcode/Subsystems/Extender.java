package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Misc.MotorUtils;

public class Extender extends SubsystemBase {
    private final DcMotorEx extenderMotor;
    private final PIDController extenderPID;

    private double extenderOffset = 0;
    private double extenderReference = 0;
    private double extenderMotorPosition;

    private final double MAXIMUM_EXTENSION = Constants.Extender.MAXIMUM_BASKET_EXTENSION;
    private final double MINIMUM_EXTENSION = Constants.Extender.MINIMUM_EXTENSION;

    public Extender(HardwareMap hardwareMap) {
        extenderMotor = hardwareMap.get(DcMotorEx.class, "extender");
        extenderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extenderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extenderMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extenderPID = new PIDController(0.007, 0, 0);
        extenderPID.setTolerance(40);

        extenderMotorPosition = extenderMotor.getCurrentPosition();
    }

    private double safeReference(double extenderReference) {
        return MathUtils.clamp(extenderReference, MINIMUM_EXTENSION, MAXIMUM_EXTENSION);
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
        } else if (extenderReference + x + extenderOffset > Constants.Extender.MAXIMUM_SUCK_EXTENSION) {
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

    public void resetExtenderEncoder() {
        extenderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extenderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double getExtenderReference() {
        return extenderPID.getSetPoint();
    }

    @Override
    public void periodic() {

        extenderMotorPosition = extenderMotor.getCurrentPosition();

        TelemetryPacket pack = new TelemetryPacket();

        pack.put("Extender PID output", extenderPID.calculate(extenderMotorPosition));
        pack.put("Extender position", extenderMotorPosition);
        pack.put("Extender reference", extenderPID.getSetPoint());
        pack.put("Extender is at reference", extenderPID.atSetPoint());

        FtcDashboard.getInstance().sendTelemetryPacket(pack);

        extenderMotor.setPower(
                MotorUtils.safePID(
                        extenderPID.calculate(extenderMotorPosition),
                        Constants.Extender.MAXIMUM_MOTOR_OUTPUT
                ));
    }
}
