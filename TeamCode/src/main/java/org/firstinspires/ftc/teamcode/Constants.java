package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Constants {
    // Parameters for controlling driving mechanics (Speed, PIDs)
    public static class Drive {
        // These define the speed multipliers for regular driving and slow driving
        public static double slowSpeedMultiplier = 0.3;
        public static double regularSpeedMultiplier = 1;

        /* These define how the control hub is oriented
         on the robot to adjust the heading accordingly */
        public static RevHubOrientationOnRobot.LogoFacingDirection
                logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public static RevHubOrientationOnRobot.UsbFacingDirection
                usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
    }

    // Parameters for assigning controls for each action
    public static class Controls {
        public static GamepadKeys.Button resetHeading = GamepadKeys.Button.Y;
        public static GamepadKeys.Button activateTankMode = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button activateSlowMode = GamepadKeys.Button.RIGHT_BUMPER;
    }
}
