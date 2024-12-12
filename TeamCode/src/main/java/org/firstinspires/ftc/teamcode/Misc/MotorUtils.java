package org.firstinspires.ftc.teamcode.Misc;

import com.arcrobotics.ftclib.util.MathUtils;

public class MotorUtils {
    public static double safePID(double x) {
        return MathUtils.clamp(x, -1, 1);
    }

    public static double safePID(double x, double maxOutput) {
        return MathUtils.clamp(x, -maxOutput, maxOutput);
    }
}
