package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Constants {

    // Parameters for controlling driving mechanics
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
        public static final double MAXIMUM_BASKET_EXTENSION = 2350;
        public static final double MAXIMUM_SUCK_EXTENSION = 1300;
        public static final double MINIMUM_EXTENSION = 0;
        public static final double MAXIMUM_MOTOR_OUTPUT = 1;
        public static final double OFFSET_SPEED = 10;
    }

    // This assumes that the arm is rotated to its low position at startup
    public static class Arm {
        public static final double MAXIMUM_ROTATION = 380;
        public static final double MINIMUM_ROTATION = 0;
        public static final double MAXIMUM_MOTOR_OUTPUT = 0.75;
    }

    public static class Pincer {
        public static double BASKET_ROTATION = 0.25;
        public static double PINCER_OFFSET = 0.29;
    }

    // Parameters for assigning controls for each action
    public static class Controls {
        // Driving
        public static final GamepadKeys.Button RESET_HEADING = GamepadKeys.Button.Y;
        public static final GamepadKeys.Trigger ACTIVATE_TANK_MODE = GamepadKeys.Trigger.LEFT_TRIGGER;
        public static final GamepadKeys.Trigger ACTIVATE_SLOW_MODE = GamepadKeys.Trigger.RIGHT_TRIGGER;


        // Mechanisms
        public static final GamepadKeys.Button OFFSET_UP = GamepadKeys.Button.DPAD_UP;
        public static final GamepadKeys.Button OFFSET_DOWN = GamepadKeys.Button.DPAD_DOWN;
        public static final GamepadKeys.Button RESET_ENCODER = GamepadKeys.Button.LEFT_STICK_BUTTON;
        public static final GamepadKeys.Button SPIT_PIECE = GamepadKeys.Button.Y;
        public static final GamepadKeys.Button SUCK_PIECE = GamepadKeys.Button.A;
        public static final GamepadKeys.Trigger BASKET_MODE = GamepadKeys.Trigger.LEFT_TRIGGER;
        public static final GamepadKeys.Trigger SUCK_MODE = GamepadKeys.Trigger.RIGHT_TRIGGER;
    }
}
