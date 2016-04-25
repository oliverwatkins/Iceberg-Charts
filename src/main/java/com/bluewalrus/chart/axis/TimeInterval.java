package com.bluewalrus.chart.axis;

import java.text.SimpleDateFormat;

import com.bluewalrus.chart.bar.GridLine;
import com.bluewalrus.chart.bar.Line;

public class TimeInterval extends AbstractInterval{

	
	public enum Type {
		MONTH, YEAR, DAY, HOUR, WEEK, MINUTE, NONE, SECOND
	}
	
	public Type type;
	public SimpleDateFormat dateFormat;


	public TimeInterval(int lineLength, Type intervalType, GridLine line) {
		
		this.styling.lineLength = lineLength;
		styling.graphLine = line;
		type = intervalType;
	}
	
	
    public TimeInterval(int lineLength, Type intervalType, GridLine graphLine, SimpleDateFormat dateFormat) {
    	this(lineLength, intervalType, graphLine);
    	
    	this.dateFormat = dateFormat;
	}


	/**
     * increment cannot be zero or less than zero
     * @return
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
}
