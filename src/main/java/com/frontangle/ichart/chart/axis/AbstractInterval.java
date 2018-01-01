package com.frontangle.ichart.chart.axis;

import com.frontangle.ichart.chart.Chart;


public abstract class AbstractInterval {
	
	public IntervalStyling styling = new IntervalStyling();
	
	private int level = -1;
	
	/**
	 * Can be active, and inactive
	 */
	public boolean active = true;

	protected boolean centered = false;

    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public abstract boolean isValid();
	
	public abstract Object getInterval();
	
	public abstract void setIncrement(Object interval);

	public boolean isCentered() {
		return centered ;
	}
}
