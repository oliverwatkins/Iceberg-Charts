package com.frontangle.ichart.chart.axis;

import java.text.SimpleDateFormat;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.draw.Line;

public class TimeInterval extends AbstractInterval{
	
	public enum Type {
		MONTH, YEAR, DAY, HOUR, WEEK, MINUTE, NONE, SECOND
	}
	
	public Type type;
	public SimpleDateFormat dateFormat;

	public TimeInterval(int lineLength, Type intervalType, Line graphLine) {
		
		this.styling.lineLength = lineLength;
		styling.graphLine = graphLine;
		type = intervalType;
		
		//per default. Time intervals have centered labels.
		this.centered = true;
	}
	
	
    public TimeInterval(int lineLength, Type intervalType, Line graphLine, SimpleDateFormat dateFormat) {
    	
    	this(lineLength, intervalType, graphLine);
    	
    	this.dateFormat = dateFormat;
	}

	/**
     * increment cannot be zero or less than zero
     */
	public boolean isValid() {
		if (type == Type.NONE)
			return false;
		return true;
	}

	@Override
	public Type getInterval() {
		return type;
	}

	@Override
	public void setIncrement(Object interval) {
		type = (Type)interval;
	}
	
	public int getIntervalInPixels(Chart chart) {
		return -1;
	}
}
