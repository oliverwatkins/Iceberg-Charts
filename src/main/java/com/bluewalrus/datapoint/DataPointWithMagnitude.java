package com.bluewalrus.datapoint;

/**
 * A dataPoint on an XY Graph that also has a magnitude. Common applications of
 * this type of data point would be in a bubble graph and some types of bar
 * graphs.
 */
public class DataPointWithMagnitude extends DataPoint {

    public double magnitude;
    public String name;

    public DataPointWithMagnitude(double x, double y, double magnitude) {
        super(x, y);
        this.magnitude = magnitude;
    }

    public DataPointWithMagnitude(String name, double x, double y, double magnitude) {
        super(x, y);
        this.magnitude = magnitude;
        this.name = name;
    }
}
