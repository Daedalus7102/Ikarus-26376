package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Constants {

    // Parameters for controlling driving mechanics (Speed, PIDs)
    public static class Drive {
        // These define the speed multipliers for regular driving and slow driving
        public static final double SLOW_SPEED_MULTIPLIER = 0.3;
        public static final double REGULAR_SPEED_MULTIPLIER = 1;

        /* These define how the control hub is oriented
         on the robot to adjust the heading accordingly */
        public static final RevHubOrientationOnRobot.LogoFacingDirection
                LOGO_FACING_DIRECTION = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public static final  RevHubOrientationOnRobot.UsbFacingDirection
                USB_FACING_DIRECTION = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    // This assumes that the extender is fully retracted at startup
    public static class Extender {
        public static final  double MAXIMUM_EXTENSION = 1564;
        public static final  double MINIMUM_EXTENSION = 0;

        public static final  double PARTIAL_EXTENSION = 700;
    }

    // This assumes that the arm is rotated to its initial position at startup
    public static class Rotator {
        public static final  double MAXIMUM_ROTATION = 400;
        public static final  double MINIMUM_ROTATION = 0;
    }

    public static class Pincer {
        public static final  double INIT_ROTATION = 1;
        public static final  double PLAY_ROTATION = 0;

    }

    // Parameters for assigning controls for each action
    public static class Controls {
        // Driving
        public static final  GamepadKeys.Button RESET_HEADING = GamepadKeys.Button.Y;
        public static final  GamepadKeys.Button ACTIVATE_TANK_MODE = GamepadKeys.Button.LEFT_BUMPER;
        public static final  GamepadKeys.Button ACTIVATE_SLOW_MODE = GamepadKeys.Button.RIGHT_BUMPER;

        // Mechanisms
        public static final  GamepadKeys.Button BASKET_MODE = GamepadKeys.Button.LEFT_BUMPER;
        public static final  GamepadKeys.Button SUCK_MODE = GamepadKeys.Button.RIGHT_BUMPER;
        public static final  GamepadKeys.Button OFFSET_UP = GamepadKeys.Button.DPAD_UP;
        public static final  GamepadKeys.Button OFFSET_DOWN = GamepadKeys.Button.DPAD_DOWN;
    }
}
