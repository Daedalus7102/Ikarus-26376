package org.firstinspires.ftc.teamcode.Subsystems;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Constants;

public class Rotator extends SubsystemBase {

    private HardwareMap hardwareMap;
    private Servo arm_servo_right;
    private Servo arm_servo_left;
    private ServoController controller;

    public Rotator(HardwareMap hM) {
        if (!Constants.Activation.enableArm) { return; }

        hardwareMap= hM;
        arm_servo_right = hardwareMap.get(Servo.class, "arm_servo_right");
        arm_servo_left = hardwareMap.get(Servo.class, "arm_servo_left");
    }

    public void setBasketAngle() {
        if (!Constants.Activation.enableArm) { return; }

        arm_servo_right.setPosition(1.0f - Constants.Arm.basketArmPosition);
        arm_servo_left.setPosition(0.0f + Constants.Arm.basketArmPosition);
    }

    public void setBaseAngle() {
        if (!Constants.Activation.enableArm) { return; }

        arm_servo_right.setPosition(1.0f - Constants.Arm.baseArmPosition);
        arm_servo_left.setPosition(0.0f + Constants.Arm.baseArmPosition);
    }
}
