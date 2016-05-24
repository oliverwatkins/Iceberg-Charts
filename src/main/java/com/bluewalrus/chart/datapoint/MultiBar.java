package com.bluewalrus.chart.datapoint;

import java.util.ArrayList;


/**
 * A set of bars.
 * 
 * @author Oliver Watkins
 */
public class MultiBar extends DataPoint{

    public MultiBarMode mode;

    public String name;
    public ArrayList<DataPointBar> bars;

    public MultiBar(ArrayList<DataPointBar> bars, String name, MultiBarMode mode) {

    	super();
    	
    	this.y = getMaxYValue(bars);
    	
    	this.valueType = ValueType.X_ENUMARABLE;
        this.bars = bars;
        this.name = name;
        this.mode = mode;
    }

    private double getMaxYValue(ArrayList<DataPointBar> bars2) {

    	double maxY = bars2.get(0).y;
    	for (DataPointBar dataPointBar : bars2) {
    		if (dataPointBar.y > maxY) {
    			maxY = dataPointBar.y;
    		}
		}
		return maxY;
	}

	public enum MultiBarMode {
        STACK_ON_TOP, SIDE_BY_SIDE
    }
}
