package org.firstinspires.ftc.teamcode.Libraries;

public class Math {
    public static double clamp(double upper, double lower, double x) {
        if (x > upper) {
            return upper;
        }
        return java.lang.Math.max(x, lower);

    }

    public static int clamp(int upper, int lower, int x) {
        if (x > upper) {
            return upper;
        }
        return java.lang.Math.max(x, lower);
    }

    public static double abs(double x) {
        if (x < 0) {
            return -x;
        }
        return x;
    }
}
