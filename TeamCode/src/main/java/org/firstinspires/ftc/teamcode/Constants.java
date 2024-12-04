package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Constants {

    // useful for avoiding accidents while testing
    public static class Activation {

        public static boolean enableDrivetrain = false;
        public static boolean enableRotator = true;
        public static boolean enableExtender = true;
        public static boolean enableGyroscope = true; // Not sure why'd you turn this off but ok
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

    // This assumes that the extender is fully retracted at startup
    public static class Extender {
        public static double MAXIMUM_EXTENSION = 1564;
        public static double MINIMUM_EXTENSION = 0;

        public static double PARTIAL_EXTENSION = 700;
    }

    // This assumes that the arm is rotated to its initial position at startup
    public static class Rotator {
        public static double maximumRotation = 400;
        public static double minimumRotation= 0;
    }

    // Parameters for assigning controls for each action
    public static class Controls {
        // Driving
        public static GamepadKeys.Button resetHeading = GamepadKeys.Button.Y;
        public static GamepadKeys.Button activateTankMode = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button activateSlowMode = GamepadKeys.Button.A;

        // Mechanisms
        public static GamepadKeys.Button BASKET_MODE = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button SUCK_MODE = GamepadKeys.Button.RIGHT_BUMPER;
        public static GamepadKeys.Button OFFSET_UP = GamepadKeys.Button.DPAD_UP;
        public static GamepadKeys.Button OFFSET_DOWN = GamepadKeys.Button.DPAD_DOWN;
    }
}
