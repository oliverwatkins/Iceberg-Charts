package com.frontangle.ichart.chart.datapoint;

import java.util.ArrayList;


/**
 * A set of bars.
 * 
 * @author Oliver Watkins
 */
public class DataPointMultiBar extends DataPoint{

    public MultiBarMode mode;

    public ArrayList<DataPointBar> bars;

    public DataPointMultiBar(ArrayList<DataPointBar> bars, String name, MultiBarMode mode) {

    	super();
    	
    	this.mode = mode;
    	
    	this.y = getMaxYValue(bars);
    	
    	this.valueType = ValueType.X_ENUMARABLE;
        this.bars = bars;
        this.name = name;
        
    }

    private double getMaxYValue(ArrayList<DataPointBar> bars) {
    	double maxY = 0;
    	if (mode == MultiBarMode.SIDE_BY_SIDE) {
        	maxY = bars.get(0).y;
        	for (DataPointBar dataPointBar : bars) {
        		if (dataPointBar.y > maxY) {
        			maxY = dataPointBar.y;
        		}
    		}
    	}else if (mode == MultiBarMode.STACK_ON_TOP) {
    		
        	for (DataPointBar dataPointBar : bars) {
    			maxY = maxY + dataPointBar.y;
    		}
    	}

		return maxY;
	}

	public enum MultiBarMode {
        STACK_ON_TOP, SIDE_BY_SIDE
    }
}
