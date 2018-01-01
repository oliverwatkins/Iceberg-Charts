/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frontangle.ichart.chart.axis;

import java.io.Serializable;

import com.frontangle.ichart.chart.draw.Line;

/**
 * Describes a graphical interval along an axis. 
 * 
 * @author Oliver Watkins
 */
public class NumericalInterval extends AbstractInterval implements Serializable{

    /**
     * Spacing of interval. If increment = 10.0, then the tick will appear
     * at 0,10,20,30 etc.
     */
    private Double increment; 

    
    public NumericalInterval(double increment) {
        this.increment = increment;
	}
    
	public NumericalInterval(int lineLength, Double increment) {
        this.increment = increment;
    
        styling.lineLength = lineLength;
    }
	
    public NumericalInterval(int lineLength, Double increment, Line graphLine) {
        this.increment = increment;
        
		styling.lineLength = lineLength;
		styling.graphLine = graphLine;
    }

	public Double getInterval() {
		return increment;
	}
	

	@Override
	public void setIncrement(Object interval) {
		this.increment = (Double)interval;
	}
	
    /**
     * increment cannot be zero or less than zero
     */
	public boolean isValid() {
		if (increment != null && increment > 0)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Interval [active=" + active + ", graphLine=" + styling.graphLine
				+ ", lineLength=" + styling.lineLength + ", increment=" + increment
				+ "]";
	}
}
