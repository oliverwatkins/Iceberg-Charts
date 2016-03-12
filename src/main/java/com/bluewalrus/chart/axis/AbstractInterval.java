package com.bluewalrus.chart.axis;


public abstract class AbstractInterval {

	
	public IntervalStyling styling = new IntervalStyling();
	
	
	public int level = -1;
	
	/**
	 * Can be active, and inactive
	 */
	public boolean active = true;


    
    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract boolean isValid();
	
	public abstract Object getInterval();
	
}
