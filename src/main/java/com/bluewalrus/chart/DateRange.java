package com.bluewalrus.chart;

import java.util.Date;

public class DateRange {

	Date min, max;

	public long howMany(long msRange) {

		long diff = max.getTime() - min.getTime();

		long result = diff / msRange; 
		
		return result;
	}
	
	
	@Override
	public String toString() {
		return "DateRange [min=" + min + ", max=" + max + "] ";
	}
}
