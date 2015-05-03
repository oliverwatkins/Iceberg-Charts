package com.bluewalrus.point;

import java.awt.Color;

public abstract class ComplexXYPoint extends XYPoint {

    public ComplexXYPoint() {
        super();
    }

    public ComplexXYPoint(Color color) {
        super(color);
    }

    public ComplexXYPoint(Color color, int size) {
        super(size, color);
    }

}
