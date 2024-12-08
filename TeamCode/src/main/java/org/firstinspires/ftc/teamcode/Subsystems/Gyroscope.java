package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Constants;


public class Gyroscope extends SubsystemBase {
    private final BHI260IMU imu;

    public Gyroscope(HardwareMap hM) {
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                Constants.Drive.LOGO_FACING_DIRECTION,
                Constants.Drive.USB_FACING_DIRECTION));
        imu = hM.get(BHI260IMU.class, "imu");
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
