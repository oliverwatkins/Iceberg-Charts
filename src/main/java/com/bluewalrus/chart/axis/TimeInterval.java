package com.bluewalrus.chart.axis;

import java.util.Date;

import com.bluewalrus.bar.Line;

public class TimeInterval extends AbstractInterval{

	
	public enum Type {
		MONTH, YEAR, DAY, HOUR, WEEK, MINUTE, NONE
	}
	
	public Type type;


	public TimeInterval(int i, Type intervalType, Line line) {
		super();
		
		lineLength = 4;
		graphLine = line;
		type = intervalType;
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
