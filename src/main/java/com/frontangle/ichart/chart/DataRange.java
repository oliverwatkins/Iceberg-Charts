package com.frontangle.ichart.chart;

/**
 * Describes a data range, ie a pair of values.
 * 
 * @author Oliver Watkins
 */
public class DataRange {

	@Override
	public String toString() {
		return "DataRange [min=" + min + ", max=" + max + "]";
	}

	double min, max;
}
