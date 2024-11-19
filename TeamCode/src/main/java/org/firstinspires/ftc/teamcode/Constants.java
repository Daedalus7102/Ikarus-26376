package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Constants {

    // useful for avoiding accidents while testing
    public static class Activation {
        public static boolean enableDrivetrain = true;
        public static boolean enableArm = true;
        public static boolean enableExtender = false;
        public static boolean enableGyroscope = true;
    }

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
                usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    // These are standard servo position values (-1, 1) 0.06 is the optimal position for the base.
    public static class Arm {
        public static float baseArmPosition = 0.06f;
        public static float basketArmPosition = 0.5f;

    }

    // Parameters for assigning controls for each action
    public static class Controls {
        public static GamepadKeys.Button resetHeading = GamepadKeys.Button.Y;
        public static GamepadKeys.Button activateTankMode = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button activateSlowMode = GamepadKeys.Button.RIGHT_BUMPER;
    }
}
