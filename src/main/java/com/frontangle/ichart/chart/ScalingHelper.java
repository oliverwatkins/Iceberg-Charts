package com.frontangle.ichart.chart;

import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.scaling.EnumerationAxisScaling;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;


/**
 * Assigns the correct axis to the chart with the correct scaling.
 * 
 * @author Oliver Watkins
 */
public class ScalingHelper {


	/**
	 * Looks at the data and figures out what axis is needed. Then create xAxis, yAxis with correct scaling.
	 * 
	 * @param chart xyChart
	 * @param xySeriesList the data for the chart
	 */
	public static void initialiseAxisAndScaling(XYChart chart,
			ArrayList<XYDataSeries> xySeriesList) {
		XAxis xAxis = null;
		YAxis yAxis = null;
		YAxis yAxis2 = null;

		if (xySeriesList == null || xySeriesList.size() == 0) {
			System.err
					.println("xySeriesList == null || xySeriesList.size() == 0");
			return;
		}

		XYDataSeries xyDataSeries = xySeriesList.get(0);

		if (xyDataSeries == null || xyDataSeries.dataPoints.size() == 0) {
			System.err
					.println("xySeriesList.get(0).dataPoints == null || xySeriesList.get(0).dataPoints.size() == 0");
		}
		DataPoint dataPoint = (DataPoint) xySeriesList.get(0).dataPoints.get(0);

		/**
		 * Initialise X
		 */
		if (dataPoint != null) {
			switch (dataPoint.valueType) {
			case X_ENUMARABLE:
				xAxis = initialiseScalingX_enumeration(xySeriesList);
				break;
			case X_TIME:
				xAxis = initialiseScalingX_time(xySeriesList);
				break;
			case X_NUMERICAL:
				xAxis = initialiseScalingX_numerical(xySeriesList);
				break;
			default:
				break;
			}
		}
		/**
		 * Initialise Y
		 */
		yAxis = initialiseScalingY_numerical(xySeriesList);

		/**
		 * Initialise Y2
		 */
		if (chart.isYAxis2) {
			yAxis2 = initialiseScalingY_numerical(chart.dataY2);
			yAxis2.axisScaling.setOrientation(Orientation.Y2);
		}

		chart.xAxis = xAxis;
		chart.yAxis = yAxis;
		chart.yAxis2 = yAxis2;
	}

	/**
	 * Initialise the X Axis by adding a time scaling with max/min values
	 * calculated from the XY data.
	 * 
	 * @param xySeriesList list of chart data
	 * @return x-axis
	 */
	private static XAxis initialiseScalingX_time(
			ArrayList<XYDataSeries> xySeriesList) {

		// get date range on the x axis
		DateRange dateRangeX = ChartUtils.getDateRangeX(xySeriesList);

		// get sensible interval
		TimeInterval.Type interval1 = DateUtils.getIntervalTime(dateRangeX);
		TimeInterval.Type interval2 = DateUtils.getNextInterval(interval1);
		TimeInterval.Type interval3 = DateUtils.getNextInterval(interval2);

		TimeInterval t1x = new TimeInterval(4, interval1, null);
		TimeInterval t2x = new TimeInterval(2, interval2, null);
		TimeInterval t3x = new TimeInterval(1, interval3, null);

		t1x.styling.graphLine = new Line(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6;

		t2x.styling.graphLine = new Line(Color.LIGHT_GRAY, true, 1);
		t2x.styling.lineLength = 3;

		// invisible!!! But not null
		t3x.styling.graphLine = new Line(Color.WHITE, false, 0);
		t3x.styling.lineLength = 0;

		XAxis xAxis = new XAxis(new TimeSeriesAxisScaling(dateRangeX.min,
				dateRangeX.max, t1x, t2x, t3x), "X Ttime to do");
		return xAxis;
	}

	/**
	 * Iinitialise x acis scaling
	 * 
	 * @param xySeriesList data series list
	 * @return xAxis x axis
	 */
	public static XAxis initialiseScalingX_enumeration(
			ArrayList<XYDataSeries> xySeriesList) {

		EnumerationAxisScaling xd = new EnumerationAxisScaling();
		XAxis xAxis = new XAxis(xd, "");
		
		if (!doAllSeriesHaveSameNumberOfDataPoints(xySeriesList)) {
			throw new RuntimeException(
					"A list of series for an enumerable/category X Axis requires the exact "
							+ "same number of data points for each series. "
							+ "Variable number of series data points not currently supported");
		}
		
		ArrayList<DataPoint> dps = xySeriesList.get(0).dataPoints; // will only
																	// have one
																	// series

		double xMax = xd.maxValue; // 100
		double xMin = xd.minValue; // 0
		
		double xRange = (double) (xMax - xMin);

		// distance between points (bars)
		double pointDistance = (double) (xRange / (dps.size() + 1));

		rationaliseDataSeriesX(xySeriesList, pointDistance);

		ChartUtils.validityCheck(dps);

		return xAxis;
	}

	/**
	 * Iterate through all series and space out all X values equally based on xDiff. Eg
	 * if xDiff is 7, then all the x values of all the data series will be 7,14,21, etc.
	 * 
	 * @param xySeriesList the data to be rationalised
	 * @param xDiff the increment value
	 */
	private static void rationaliseDataSeriesX(ArrayList<XYDataSeries> xySeriesList,
			double xDiff) {
		
		for (XYDataSeries xyDataSeries : xySeriesList) {
			ArrayList<DataPoint> dps2 = xyDataSeries.dataPoints;

			int i = 1;
			for (DataPoint dp : dps2) {
				dp.x = (double) (xDiff * i);
				i++;
			}
		}
	}

	private static boolean doAllSeriesHaveSameNumberOfDataPoints(ArrayList<XYDataSeries> xySeriesList) {
		
		int lastLength = xySeriesList.get(0).dataPoints.size();
		
		for (XYDataSeries xyDataSeries : xySeriesList) {

			int sizeXPoints = xyDataSeries.dataPoints.size();
			if (sizeXPoints != lastLength) {
				return false;

			}
		}
		return true;
	}

	// Numerical only
	public static XAxis initialiseScalingX_numerical(
			ArrayList<XYDataSeries> xySeriesList) {
		// get padded range
		DataRange drX = ChartUtils.getDataRangeX(xySeriesList);

		// get sensible interval
		double initialIntervalX = ChartUtils.getInterval(drX);

		NumericalInterval t1x = new NumericalInterval(initialIntervalX);
		NumericalInterval t2x = new NumericalInterval(initialIntervalX / 10);
		NumericalInterval t3x = new NumericalInterval(initialIntervalX / 100);

		t1x.styling.graphLine = new Line(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6; // new GridLine(Color.GRAY, false, 1);

		t2x.styling.graphLine = new Line(Color.LIGHT_GRAY, true, 1);
		t2x.styling.lineLength = 3; // new GridLine(Color.LIGHT_GRAY, true, 1);

		// invisible!!! But not null
		t3x.styling.graphLine = new Line(Color.WHITE, false, 0);
		t3x.styling.lineLength = 0;

		XAxis xAxis = new XAxis(new LinearNumericalAxisScaling(drX.min,
				drX.max, t1x, t2x, t3x), "X TODO");
		return xAxis;
	}

	public static YAxis initialiseScalingY_numerical(
			ArrayList<XYDataSeries> xySeriesList) {
		// get padded range
		DataRange drY = ChartUtils.getDataRangeY(xySeriesList);

		// get sensible interval
		double initialIntervalY = ChartUtils.getInterval(drY);

		NumericalInterval t1 = new NumericalInterval(initialIntervalY);
		NumericalInterval t2 = new NumericalInterval(initialIntervalY / 10);

		t1.styling.graphLine = new Line(Color.GRAY, false, 1);
		t1.styling.lineLength = 6;

		t2.styling.graphLine = new Line(Color.LIGHT_GRAY, true, 1);
		t2.styling.lineLength = 3;

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(drY.min,
				drY.max, t1, t2, null), "Y TODO");
		return yAxis;
	}
}
