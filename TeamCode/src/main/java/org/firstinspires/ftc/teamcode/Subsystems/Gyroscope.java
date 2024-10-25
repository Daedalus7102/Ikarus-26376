package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Constants;

public class Gyroscope {
    private final HardwareMap hardwareMap;
    private final BHI260IMU imu;
    public Gyroscope(HardwareMap hM) {
        hardwareMap = hM;
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                Constants.Drive.logoFacingDirection,
                Constants.Drive.usbFacingDirection));
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(parameters);
    }

    public double getHeading() {
        // In this case, the 'front' of the control hub is where the servo pins are
        return imu.getRobotYawPitchRollAngles().getYaw();
    }

    public void resetHeading() {
        imu.resetYaw();
    }
}
