package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Constants;

public class Gyroscope {
    private HardwareMap hardwareMap;
    private BHI260IMU imu;
    public Gyroscope(HardwareMap hM) {
        if (!Constants.Activation.enableGyroscope) { return; }

        hardwareMap = hM;
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                Constants.Drive.logoFacingDirection,
                Constants.Drive.usbFacingDirection));
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(parameters);
    }

    public double getHeading() {
        if (!Constants.Activation.enableGyroscope) { return 0; }

        // In this case, the 'front' of the control hub is where the servo pins are
        return imu.getRobotYawPitchRollAngles().getYaw();
    }

    public void resetHeading() {
        if (!Constants.Activation.enableGyroscope) { return; }

        imu.resetYaw();
    }
}
