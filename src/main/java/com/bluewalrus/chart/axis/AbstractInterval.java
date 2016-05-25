package com.bluewalrus.chart.axis;


public abstract class AbstractInterval {

	
	public IntervalStyling styling = new IntervalStyling();
	
	
	private int level = -1;
	


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
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public abstract boolean isValid();
	
	public abstract Object getInterval();
	
	public abstract void setIncrement(Object interval);
		
	
}
