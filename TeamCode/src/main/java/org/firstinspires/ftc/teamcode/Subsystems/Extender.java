package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;


public class Extender extends SubsystemBase {
    private HardwareMap hardwareMap;
    private MotorEx extender_motor;
    private boolean isEnabled;

    public Extender(HardwareMap hM) {
        if (!Constants.Activation.enableExtender) { return; }

        hardwareMap = hM;
        extender_motor = new MotorEx(hardwareMap, "extender", Motor.GoBILDA.RPM_1150);
        extender_motor.setRunMode(Motor.RunMode.VelocityControl);
        extender_motor.resetEncoder();
    }

    public void test(int position) {
        if (!Constants.Activation.enableExtender) { return; }

        extender_motor.setTargetPosition(position);
        extender_motor.setVelocity(-50);
    }
}
