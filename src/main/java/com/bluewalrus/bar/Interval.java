/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.bar;

import java.io.Serializable;

/**
 * Describes a graphical interval along an axis. 
 * 
 * @author Oliver Watkins
 */
public class Interval implements Serializable{

	
	


	/**
	 * Can be active, and inactive
	 */
	public boolean active = true;
	
	

	public Line graphLine;

    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    public int lineLength; 
    
    
    /**
     * Spacing of interval. If increment = 10.0, then the tick will appear
     * at 0,10,20,30 etc.
     */
    private Double increment; 

    public Double getIncrement() {
    	
		return increment;
	}

	public void setIncrement(Double increment) {
		
//		if (!(increment > 0.0))
			
		
		this.increment = increment;
	}

	public Interval(int lineLength, Double increment) {
        this.lineLength = lineLength;
        this.increment = increment;
    }
    
    public Interval(int lineLength, Double increment, Line graphLine) {
        this.lineLength = lineLength;
        this.increment = increment;
        this.graphLine = graphLine;
    }

    /**
     * increment cannot be zero or less than zero
     * @return
     */
	public boolean isValid() {
		if (increment > 0)
			return true;
		return false;
	}
	
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Interval [active=" + active + ", graphLine=" + graphLine
				+ ", lineLength=" + lineLength + ", increment=" + increment
				+ "]";
	}

}
