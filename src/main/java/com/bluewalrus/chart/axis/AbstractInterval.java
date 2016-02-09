package com.bluewalrus.chart.axis;

import com.bluewalrus.bar.GridLine;

public abstract class AbstractInterval {

	
	
	public int level = -1;
	
	/**
	 * Can be active, and inactive
	 */
	public boolean active = true;

	public GridLine graphLine;
	
    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    public int lineLength; 
    
    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract boolean isValid();
	
	public abstract Object getInterval();
	
}
