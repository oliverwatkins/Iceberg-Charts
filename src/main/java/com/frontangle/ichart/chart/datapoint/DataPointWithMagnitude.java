package com.frontangle.ichart.chart.datapoint;

/**
 * Used for bubble chart as bubble point (magnitutde is size of bubble)
 * 
 * @author oliver.watkins
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
