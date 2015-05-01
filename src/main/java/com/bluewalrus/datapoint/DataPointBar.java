package com.bluewalrus.datapoint;

import java.awt.Color;

public class DataPointBar extends DataPoint {

    public String name;

    public Color color;

    public DataPointBar(double x, double y) {
        super(x, y);
    }

    public DataPointBar(double x, double y, String name) {
        super(x, y);
        this.name = name;
    }

    public DataPointBar(int i, int j, Color color2, String string) {
        super(i, j);
        this.name = string;
        this.color = color2;
    }
}
