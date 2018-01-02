package com.frontangle.ichart.chart.datapoint;

import com.frontangle.ichart.pie.Segment;

import java.util.ArrayList;

/**
 * Datapoint for the crazy pie-bubble chart. Datapoint requires data needed for normal pie chart and magnitude
 * 
 * @author oliver
 */
public class DataPointPieChart extends DataPoint {

    public double magnitude;
    public String name;
    public ArrayList<Segment> pievalues;
    
    
    public DataPointPieChart(ArrayList<Segment> pievalues, double xPoint, double yPoint, double magnitude, String name) {
        super(xPoint, yPoint);
        this.name = name;
        this.magnitude = magnitude;
        this.pievalues = pievalues;
    }

}
