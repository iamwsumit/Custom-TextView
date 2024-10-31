package com.sumit1334.customtextview.Partical.MovingStrategy;

import com.sumit1334.customtextview.Partical.Object.Particle;

public abstract class MovingStrategy {
    public abstract void setMovingPath(Particle particle, int rangeWidth, int rangeHeight, double[] targetPosition);
}
