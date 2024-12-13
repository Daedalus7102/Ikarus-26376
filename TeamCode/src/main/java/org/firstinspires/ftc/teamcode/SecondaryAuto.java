package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Misc.AutoMoveAndRotate;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extender;
import org.firstinspires.ftc.teamcode.Subsystems.Pincer;

@Autonomous(name="SecondaryAuto")
public class SecondaryAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Drivetrain drivetrain = new Drivetrain(hardwareMap);
        Extender extender = new Extender(hardwareMap);
        Pincer pincer = new Pincer(hardwareMap, extender);
        AutoMoveAndRotate controller = new AutoMoveAndRotate();
        CommandScheduler.getInstance().reset();
        waitForStart();

        pincer.setPivotPosition(Configuration.Pincer.DEFAULT_ROTATION);

        sleep(400);
        controller.moveMeters(drivetrain, .40);

        sleep(400);
        controller.rotateByDegrees(drivetrain, -80);

        sleep(5000);
    }
}
