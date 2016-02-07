package com.bluewalrus.chart.axis;

import java.text.SimpleDateFormat;

import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Line;

public class TimeInterval extends AbstractInterval{

	
	public enum Type {
		MONTH, YEAR, DAY, HOUR, WEEK, MINUTE, NONE
	}
	
	public Type type;
	public SimpleDateFormat dateFormat;


	public TimeInterval(int lineLength, Type intervalType, GridLine line) {
		
		this.lineLength = lineLength;
		graphLine = line;
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
