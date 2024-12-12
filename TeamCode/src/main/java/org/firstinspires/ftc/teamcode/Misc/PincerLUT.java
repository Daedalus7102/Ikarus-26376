package org.firstinspires.ftc.teamcode.Misc;

import com.arcrobotics.ftclib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.Constants;

public class PincerLUT {
    public InterpLUT pincerLUT = new InterpLUT();

    public PincerLUT() {
        pincerLUT.add(-10000, 0 + Constants.Pincer.PINCER_OFFSET);
        pincerLUT.add(600, 0 + Constants.Pincer.PINCER_OFFSET);
        pincerLUT.add(1200, 0.1 + Constants.Pincer.PINCER_OFFSET);
        pincerLUT.add(10000, 0.1 + Constants.Pincer.PINCER_OFFSET);
    }

    public double calculate(double x) {
        pincerLUT.createLUT();
        return pincerLUT.get(x);
    }
}
