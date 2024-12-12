package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

@Autonomous(name = "auto1")
public class Auto extends LinearOpMode {
    private Drivetrain drivetrain;

    public Auto() {
        drivetrain = new Drivetrain(hardwareMap);
    }

    @Override
    public void runOpMode() {

    }
}
