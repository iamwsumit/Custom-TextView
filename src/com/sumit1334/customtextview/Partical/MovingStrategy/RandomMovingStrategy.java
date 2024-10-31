package com.sumit1334.customtextview.Partical.MovingStrategy;

import com.sumit1334.customtextview.Partical.Object.Particle;

public class RandomMovingStrategy extends MovingStrategy{

    @Override
    public void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition) {
        Double[][] path = new Double[2][2];
        path[0][0] = Math.random() * rangeWidth;
        path[0][1] = Math.random() * rangeHeight;
        path[1][0] = targetPosition[0];
        path[1][1] = targetPosition[1];
        particle.setPath(path);
    }
}
