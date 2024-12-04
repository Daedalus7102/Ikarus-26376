package org.firstinspires.ftc.teamcode.Libraries;

import com.qualcomm.robotcore.util.ElapsedTime;

/*
Proportional Integral Derivative Controller w/ Low pass filter, anti-windup, &
other convenience features.
Thanks to CTRL ALT FTC for the code reference. https://www.ctrlaltftc.com/the-pid-controller/
*/

public class PID {
    private double Kp; // proportional term
    private double Ki; // integral term
    private double Kd; // derivative term
    private double allowedError; // The maximum error allowed before the PID returns 0
    private double offset;

    private double lastReference;
    private double lastError;
    private double integralSum;
    private double lastFilterEstimate;
    private double filterEstimate;
    private double reference;
    private double currentPosition;
    private final ElapsedTime timer;

    public PID(double Kp, double Ki, double Kd, double allowedError) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;

        this.offset = 0 ;
        this.lastError = 0;
        this.integralSum = 0;
        this.reference = 0;
        this.lastReference = 0;
        this.filterEstimate = 0;
        this.lastFilterEstimate = 0;
        this.currentPosition=0;
        this.allowedError = allowedError;
        this.timer = new ElapsedTime();
    }

    public double getReference() {
        return reference;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }

    public boolean isAtReference() {
        return Math.abs(reference - currentPosition) < allowedError;
    }

    public void setAllowedError(double maxError) {
        allowedError = maxError;
    }

    public void setKp(double Kp) {
        this.Kp = Kp;
    }
    public void setKi(double Ki) {
        this.Ki = Ki;
    }
    public void setKd(double Kd) {
        this.Kd = Kd;
    }

    public void setPIDCoefficients(double kP, double Ki, double Kd) {
        this.Kp = kP;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double calculate(double currentPosition) {
        this.currentPosition = currentPosition;

        double error = reference - currentPosition;
        double errorDifference = error - lastError;

        // Filter derivative noise
        double LOW_PASS_THRESHOLD = 0.8;
        filterEstimate = (LOW_PASS_THRESHOLD *lastFilterEstimate) + (1- LOW_PASS_THRESHOLD) * errorDifference;
        lastFilterEstimate = filterEstimate;

        double derivative = filterEstimate / timer.seconds();
        integralSum += error * timer.seconds();

        // Maximum allowed accumulated value for the integral
        double MAX_INTEGRAL_SUM = 0.25;
        if (integralSum > MAX_INTEGRAL_SUM) {
            integralSum = MAX_INTEGRAL_SUM;
        }

        if (integralSum < -MAX_INTEGRAL_SUM) {
            integralSum = -MAX_INTEGRAL_SUM;
        }

        if (reference != lastReference) {
            integralSum = 0;
        }

        lastError = error;
        lastReference = reference;

        timer.reset();

        if (Math.abs(error) < allowedError) {
            return 0;
        }
        return Math.clamp(1, -1,(Kp * error) + (Ki * integralSum) + (Kd * derivative));
    }
}
