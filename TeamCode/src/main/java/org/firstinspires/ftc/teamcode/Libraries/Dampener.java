package org.firstinspires.ftc.teamcode.Libraries;

// A crude implementation of a dampening algorithm but i really don't want to get into
// implementing a discrete, real-time spring simulation

// and hey, it works! :)

public class Dampener {
    private double lastReference;
    private double aggressiveness;
    private double threshold;

    public Dampener(double aggressiveness, double threshold) {
        this.aggressiveness = aggressiveness;
        this.lastReference = 0;
        this.threshold = threshold;
    }

    public double calculate(double reference) {
        double difference = reference - lastReference;

        if (Math.abs(difference) > threshold) {
            lastReference += (difference > 0 ? 1 : -1) * aggressiveness;
        } else {
            lastReference = reference;
        }

        return lastReference;
    }
}
