package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class Constants {
    // Parameters for controlling driving mechanics (Speed, PIDs)
    public static class Drive {
        public static double slowSpeedMultiplier = 0.3;
        public static double regularSpeedMultiplier = 1;
    }

    // Parameters for assigning controls for each action
    public static class Controls {
        public static GamepadKeys.Button resetHeading = GamepadKeys.Button.Y;
        public static GamepadKeys.Button activateTankMode = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button activateSlowMode = GamepadKeys.Button.RIGHT_BUMPER;

    }
}
