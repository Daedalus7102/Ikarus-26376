package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

/* This class contains all the configuration constants for the robot. Each parameter is placed
under a respective sub-class. */

public class Configuration {

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

    @Config
    // This assumes that the extender is fully retracted at startup
    public static class Extender {
        // Encoder ticks for the extender on each mode
        public static  double HIGH_BASKET_EXTENSION = 2400;
        public static  double LOW_BASKET_EXTENSION = 1200;
        public static  double MAXIMUM_SUCK_EXTENSION = 1300;
        public static  double MINIMUM_EXTENSION = 0;

        // Maximum speed for the extender motor
        public static  double MAXIMUM_MOTOR_OUTPUT = 1;

        // How many encoder ticks of offset to add per cycle in suck mode
        public static  double OFFSET_ADD_SPEED = 15;
    }

    // This assumes that the arm is rotated to its low position at startup
    @Config
    public static class Arm {
        // Encoder ticks of rotation
        public static double HIGH_BASKET_ROTATION = 380;
        public static double LOW_BASKET_ROTATION = 320;
        public static double MINIMUM_ROTATION = 0;

        // Maximum speed of the arm
        public static double MAXIMUM_MOTOR_OUTPUT = 0.75;
    }

    @Config
    public static class Pincer {
        // Rotation to use when not in suck mode && Offset for calculating optimal pincer pivot when in suck mode
        public static double DEFAULT_ROTATION = 0.27;
    }

    // These define which buttons do what
    public static class Controls {
        // Driving
        public static final GamepadKeys.Button RESET_HEADING = GamepadKeys.Button.Y;
        public static final GamepadKeys.Trigger ACTIVATE_TANK_MODE = GamepadKeys.Trigger.LEFT_TRIGGER;
        public static final GamepadKeys.Trigger ACTIVATE_SLOW_MODE = GamepadKeys.Trigger.RIGHT_TRIGGER;


        // Mechanisms
        public static final GamepadKeys.Button EXTENDER_OFFSET_UP = GamepadKeys.Button.DPAD_UP;
        public static final GamepadKeys.Button EXTENDER_OFFSET_DOWN = GamepadKeys.Button.DPAD_DOWN;
        public static final GamepadKeys.Button RESET_ENCODER = GamepadKeys.Button.LEFT_STICK_BUTTON;
        public static final GamepadKeys.Button LOW_BASKET_BUTTON = GamepadKeys.Button.LEFT_BUMPER;
        public static final GamepadKeys.Button HIGH_RUNG_BUTTON = GamepadKeys.Button.RIGHT_BUMPER;
        public static final GamepadKeys.Button PINCER_OFFSET_UP = GamepadKeys.Button.X;
        public static final GamepadKeys.Button PINCER_OFFSET_DOWN = GamepadKeys.Button.B;
        public static final GamepadKeys.Button SPIT_PIECE = GamepadKeys.Button.Y;
        public static final GamepadKeys.Button SUCK_PIECE = GamepadKeys.Button.A;
        public static final GamepadKeys.Trigger HIGH_BASKET_TRIGGER = GamepadKeys.Trigger.LEFT_TRIGGER;
        public static final GamepadKeys.Trigger SUCK_MODE_TRIGGER = GamepadKeys.Trigger.RIGHT_TRIGGER;
    }
}
