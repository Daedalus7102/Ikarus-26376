package org.firstinspires.ftc.teamcode.Libraries;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
Proportional Integral Derivative Controller w/ Low pass filter and anti-windup
Thanks to CTRL ALT FTC for the code reference. https://www.ctrlaltftc.com/the-pid-controller/
*/

public class PID {
    private final double Kp; // proportional term
    private final double Ki; // integral term
    private final double Kd; // derivative term
    private final double maxIntegralSum; // maximum allowed accumulated value for the integral
    private final double lowPassThreshold; // low pass filter threshold

    private double lastReference;
    private double lastError;
    private double integralSum;
    private double lastFilterEstimate;
    private double filterEstimate;
    private final double allowedError;
    private final ElapsedTime timer;

    private Telemetry telemetry;

    public PID(double Kp, double Ki, double Kd, double maxIntegralSum,
               double lowPassThreshold, double currentPosition, double allowedError, Telemetry telemetry) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.maxIntegralSum = maxIntegralSum;
        this.lowPassThreshold = lowPassThreshold;
        this.lastError = 0;
        this.integralSum = 0;
        this.lastReference = currentPosition;
        this.filterEstimate = 0;
        this.lastFilterEstimate = 0;
        this.allowedError = allowedError;
        this.timer = new ElapsedTime();
        this.telemetry = telemetry;
    }

    // This function is meant to run once per cycle in Teleop
    public double tick(double reference, double currentPosition) {
        double error = reference - currentPosition;
        double errorDifference = error - lastError;

        telemetry.addData("current position", currentPosition);
        telemetry.addData("reference", reference);
        telemetry.addData("error", error);

        filterEstimate = (lowPassThreshold*lastFilterEstimate) + (1-lowPassThreshold) * errorDifference;
        lastFilterEstimate = filterEstimate;

        double derivative = filterEstimate / timer.seconds();
        integralSum += error * timer.seconds();

        if (integralSum > maxIntegralSum) {
            integralSum = maxIntegralSum;
        }

        if (integralSum < -maxIntegralSum) {
            integralSum = -maxIntegralSum;
        }

        if (reference != lastReference) {
            integralSum = 0;
        }

        lastError = error;
        lastReference = reference;

        timer.reset();

        if (error < allowedError && error > -allowedError) {
            return 0;
        } else {
            return (Kp * error) + (Ki * integralSum) + (Kd * derivative);
        }
    }
}
