package com.frontangle.ichart.chart;

import java.util.Date;

/**
 * Describes a date range
 * 
 * @author Oliver Watkins
 */
public class DateRange {

	Date min, max;

	/**
	 * How many of the msIntervals are contained in the date range.
	 * 
	 * @param msInterval time interval
	 * @return number of occurances
	 */
	public long howMany(long msInterval) {
		long diff = max.getTime() - min.getTime();

		long result = diff / msInterval; 
		
		return result;
	}
	
	
	@Override
	public String toString() {
		return "DateRange [min=" + min + ", max=" + max + "] ";
	}
}
