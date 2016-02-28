package com.bluewalrus.chart;

import java.util.ArrayList;

import com.bluewalrus.datapoint.DataPoint;

public class ChartUtils {

	
	static double calculateXAxisMax(ArrayList<DataPoint> values) {
		double d = getMaxXValue(values);
		return d;
	}

	static double calculateYAxisMin(ArrayList<DataPoint> values) {
		double d = getMinYValue(values);
		return d;
	}

	static double calculateYAxisMax(ArrayList<DataPoint> values) {
		double d = getMaxYValue(values);
		return d;
	}
	static double calculateXAxisMin(ArrayList<DataPoint> values) {
		double d = getMinXValue(values);
		return d;
	}
	
	static double getMinXValue(ArrayList<DataPoint> values) {
		
		double xMin = values.get(0).x;
		for (DataPoint dataPoint : values) {
			if (dataPoint.x < xMin)
				xMin = dataPoint.x;
		}
		return xMin;
	}

	static double getMinYValue(ArrayList<DataPoint> values) {
		double yMin = values.get(0).y;
		for (DataPoint dataPoint : values) {
			if (dataPoint.y < yMin)
				yMin = dataPoint.y;
		}
		return yMin;
	}

	static double getMaxYValue(ArrayList<DataPoint> values) {
		double yMax = values.get(0).y;
		for (DataPoint dataPoint : values) {
			if (dataPoint.y > yMax)
				yMax = dataPoint.y;
		}
		return yMax;
	}

	static double getMaxXValue(ArrayList<DataPoint> values) {
		double xMax = values.get(0).x;
		for (DataPoint dataPoint : values) {
			if (dataPoint.x > xMax)
				xMax = dataPoint.x;
		}
		return xMax;
	}
	
	
}
