package com.bluewalrus.chart.axis;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bluewalrus.bar.Line;
import com.bluewalrus.chart.axis.TimeInterval.Type;

public class TimeInterval extends AbstractInterval{

	
	public enum Type {
		MONTH, YEAR, DAY, HOUR, WEEK, MINUTE, NONE
	}
	
	public Type type;
	public SimpleDateFormat dateFormat;


	public TimeInterval(int lineLength, Type intervalType, Line line) {
		super();

		
		this.lineLength = lineLength;
		graphLine = line;
		type = intervalType;
	}
	
	
    public TimeInterval(int lineLength, Type intervalType, Line line, SimpleDateFormat dateFormat) {
    	this(lineLength, intervalType, line);
    	
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
