package org.firstinspires.ftc.teamcode.Misc;

import com.arcrobotics.ftclib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.Configuration;

public class PincerLUT {
    public InterpLUT pincerLUT = new InterpLUT();

    public PincerLUT() {
        pincerLUT.add(-10000, 0);
        pincerLUT.add(600, 0);
        pincerLUT.add(1200, 0.1);
        pincerLUT.add(10000, 0.1);
    }

    public double calculate(double x) {
        pincerLUT.createLUT();
        return pincerLUT.get(x) + Configuration.Pincer.DEFAULT_ROTATION;
    }
}
