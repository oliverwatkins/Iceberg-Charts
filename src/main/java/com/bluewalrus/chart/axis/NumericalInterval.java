/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.chart.axis;

import java.io.Serializable;

import com.bluewalrus.bar.GridLine;

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

    
	public NumericalInterval(int lineLength, Double increment) {
		
        this.lineLength = lineLength;
        this.increment = increment;
    }
    
    public NumericalInterval(int lineLength, Double increment, GridLine graphLine) {
		
        this.lineLength = lineLength;
        this.increment = increment;
        this.graphLine = graphLine;
    }
    
    
    public Double getInterval() {
		return increment;
	}

	public void setIncrement(Double increment) {
		this.increment = increment;
	}
	
	
	
	



    /**
     * increment cannot be zero or less than zero
     * @return
     */
	public boolean isValid() {
		if (increment != null && increment > 0)
			return true;
		return false;
	}
	

	
	@Override
	public String toString() {
		return "Interval [active=" + active + ", graphLine=" + graphLine
				+ ", lineLength=" + lineLength + ", increment=" + increment
				+ "]";
	}
}
