package com.frontangle.ichart.chart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.chart.draw.point.UIPointTriangle;
import com.frontangle.ichart.scaling.AxisScaling;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

/**
 * Utility class for geometric calculations on the chart.
 * 
 * @author Oliver Watkins
 */
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

	static Date getMaxXValueDate(ArrayList<DataPoint> values) {

		Date xMax = values.get(0).xDate;
		for (DataPoint dataPoint : values) {
			if (dataPoint.xDate.getTime() > xMax.getTime())
				xMax = dataPoint.xDate;
		}
		return xMax;
	}

	private static Date getMinXValueDate(ArrayList<DataPoint> values) {
		Date xMin = values.get(0).xDate;
		for (DataPoint dataPoint : values) {
			if (dataPoint.xDate.getTime() < xMin.getTime())
				xMin = dataPoint.xDate;
		}
		return xMin;
	}

	public static double getMinYValue(ArrayList<? extends DataPoint> values) {
		double yMin = values.get(0).y;
		for (DataPoint dataPoint : values) {
			if (dataPoint.y < yMin)
				yMin = dataPoint.y;
		}
		return yMin;
	}

	public static double getMaxYValue(ArrayList<? extends DataPoint> values) {
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

	public static double calculateYAxisMax(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		double max = getMaxYValue(xySeriesList.get(0).dataPoints);

		for (XYDataSeries xyDataSeries : xySeriesList) {
			ArrayList<DataPoint> dps = xyDataSeries.dataPoints;

			double m = getMaxYValue(dps);
			if (m > max)
				max = m;
		}
		return max;
	}

	public static DataRange getDataRangeX(ArrayList<XYDataSeries> xySeriesList) {
		// get Max/Min
		double xMax = ChartUtils.calculateXAxisMax(xySeriesList, true);
		double xMin = ChartUtils.calculateXAxisMin(xySeriesList, true);

		// range with padding
		DataRange drX = ChartUtils.getDataRange(xMax, xMin, 10);
		return drX;
	}

	public static DataRange getDataRangeY(ArrayList<XYDataSeries> xySeriesList) {
		// Get Max/Min
		double yMax = ChartUtils.calculateYAxisMax(xySeriesList, true);
		double yMin = ChartUtils.calculateYAxisMin(xySeriesList, true);

		// range with padding
		DataRange drY = ChartUtils.getDataRange(yMax, yMin, 10);
		return drY;
	}

	public static DateRange getDateRangeX(ArrayList<XYDataSeries> xySeriesList) {

		// get Max/Min
		Date xMax = ChartUtils.calculateXAxisMaxDate(xySeriesList, true);
		Date xMin = ChartUtils.calculateXAxisMinDate(xySeriesList, true);

		// range with padding
		DateRange drX = ChartUtils.getDateRange(xMax, xMin, 10);
		return drX;
	}

	public static DataRange getDataRange(double max, double min,
			int paddingPercent) {
		double yDiff = max - min;

		// pad out to 10%
		double minAdj = min - (yDiff / paddingPercent);
		double maxAdj = max + (yDiff / paddingPercent);

		DataRange drY = new DataRange();

		drY.min = minAdj;
		drY.max = maxAdj;
		return drY;
	}

	public static DateRange getDateRange(Date yMax, Date yMin,
			int paddingPercent) {
		long yDiff = yMax.getTime() - yMin.getTime();

		// pad out to 10%
		long yMinAdj = yMin.getTime() - (yDiff / paddingPercent);
		long yMaxAdj = yMax.getTime() + (yDiff / paddingPercent);

		DateRange drY = new DateRange();

		drY.min = new Date(yMinAdj);
		drY.max = new Date(yMaxAdj);
		return drY;
	}

	public static double calculateYAxisMin(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		double min = getMinYValue(xySeriesList.get(0).dataPoints);

		for (XYDataSeries xyDataSeries : xySeriesList) {
			ArrayList<DataPoint> dps = xyDataSeries.dataPoints;

			double m = getMinYValue(dps);
			if (m < min)
				min = m;
		}
		return min;
	}

	public static double calculateXAxisMax(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		double max = getMaxXValue(xySeriesList.get(0).dataPoints);

		for (XYDataSeries xyDataSeries : xySeriesList) {
			ArrayList<DataPoint> dps = xyDataSeries.dataPoints;

			double m = getMaxXValue(dps);
			if (m > max)
				max = m;

		}
		return max;
	}

	public static Date calculateXAxisMaxDate(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		ArrayList<DataPoint> dps = xySeriesList.get(0).dataPoints;

		Date max = getMaxXValueDate(dps);

		for (XYDataSeries xyDataSeries : xySeriesList) {

			Date m = getMaxXValueDate(dps);
			if (m.getTime() > max.getTime())
				max = m;
		}
		return max;
	}

	public static double calculateXAxisMin(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		double min = getMinXValue(xySeriesList.get(0).dataPoints);

		for (XYDataSeries xyDataSeries : xySeriesList) {
			ArrayList<DataPoint> dps = xyDataSeries.dataPoints;

			double m = getMinXValue(dps);
			if (m < min)
				min = m;
		}
		return min;
	}

	public static Date calculateXAxisMinDate(
			ArrayList<XYDataSeries> xySeriesList, boolean b) {

		ArrayList<DataPoint> dps = xySeriesList.get(0).dataPoints;

		Date min = getMinXValueDate(dps);

		for (XYDataSeries xyDataSeries : xySeriesList) {

			Date m = getMinXValueDate(dps);
			if (m.getTime() < min.getTime())
				min = m;
		}
		return min;
	}


	public static void setUpSeriesStyle(ArrayList<XYDataSeries> xySeriesList,
			XYChart chart) {
		/**
		 * Set up some default styles for the first 5 series, then random
		 */
		int i = 0;
		for (XYDataSeries xyDataSeries : xySeriesList) {
			if (i == 0) {

				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointSquare(Color.BLUE);
					xyDataSeries.line = new Line(Color.BLUE, false, 2);
				}
			} else if (i == 1) {

				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointCircle(Color.GREEN);
					xyDataSeries.line = new Line(Color.GREEN, false, 2);
				}
			} else if (i == 2) {

				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointTriangle(Color.RED);
					xyDataSeries.line = new Line(Color.RED, false, 2);
				}
			} else if (i == 3) {

				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointTriangle(Color.CYAN);
					xyDataSeries.line = new Line(Color.CYAN, false, 2);
				}
			} else if (i == 4) {

				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointCircle(Color.MAGENTA);
					xyDataSeries.line = new Line(Color.MAGENTA, false, 2);
				}
			} else {

				Color c = ChartUtils.createRandomColor();
				if (!xyDataSeries.hasStyleBeenSet()) {
					xyDataSeries.pointType = new UIPointCircle(c);
					xyDataSeries.line = new Line(c, false, 2);
				}
			}
			i++;
		}

		chart.rightOffset = 200;
	}

	private static Color createRandomColor() {

		Random random = new Random();
		final float hue = random.nextFloat();
		final float saturation = (random.nextInt(2000) + 1000) / 10000f;
		final float luminance = 0.9f;
		final Color color = Color.getHSBColor(hue, saturation, luminance);

		return color;
	}

	/**
	 * A DataPointBar, should have all its names set to a value, or no values
	 * set to name.
	 * 
	 * @param dataPoints data points
	 */
	public static void validityCheck(ArrayList<DataPoint> dataPoints) {

		DataPoint firstElem = dataPoints.get(0);
		if (firstElem.name != null) {
			// enumerable
			for (DataPoint dp : dataPoints) {
				if (dp.name == null) {
					throw new RuntimeException(
							"Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}

		if (firstElem.name == null) {
			// numerical
			for (DataPoint dp : dataPoints) {
				if (dp.name != null) {
					throw new RuntimeException(
							"Error : All data points need to be either enumarable or numerical. Some data points have an xName and others do not");
				}
			}
		}
	}

	public static String formatNumberValue(double value,
			NumericalInterval interval, DecimalFormat decimalFormat) {
		return decimalFormat.format(value);
	}

	/**
	 * Clip the chart area based on offsets
	 * 
	 * @param g graphic context
	 * @param chart the chart
	 * @return cached clip
	 */
	public static Shape clipChart(Graphics2D g, Chart chart) {

		Shape cachedClip = g.getClip();
		g.clip(new Rectangle(chart.leftOffset, chart.topOffset,
				chart.widthChart, chart.heightChart));

		return cachedClip;
	}

	/*
	 * Get a sensible interval between two points. Ie. (34 to 10,000) would be
	 * 1000 (36 to 132) would be 10
	 * 
	 * TODO less than 1 and greater than 10000. Need generic algorithm here
	 * 
	 */
	public static double getInterval(DataRange dr) {

		double max = dr.max;
		double min = dr.min;

		if (max - min < 0.0000001) {
			throw new RuntimeException(
					"data range cannot be less than XXXXXX. DataRange = " + dr);
		} else if (max - min > 100000000) {
			throw new RuntimeException(
					"data range cannot be more than XXXXXX. DataRange = " + dr);
		}

		// starting at 0.00001 we go up by factors of 10 and check if the
		// interval is acceptable.
		double magnitude = 0.00001;

		while (magnitude != 100000) {
			magnitude = magnitude * 10;

			if (isOrderMagnitudeAcceptableFirstInterval(max, min, magnitude)) {

				// found the correct magnitude
				break;
			}
		}
		return magnitude;
	}

	public static double getIncrementInPixels(AbstractInterval interval,
			XYChart chart, AxisScaling scaling) {

		double incrementInPixel = -1;
		double factor = scaling.getMultiplicationFactor(chart);

		if (scaling instanceof LinearNumericalAxisScaling) {
			incrementInPixel = (double) (((NumericalInterval) interval).getInterval() * factor);
		} else {

			TimeInterval inter = (TimeInterval) interval;
			TimeInterval.Type t = inter.getInterval();

			long increment = DateUtils.getMsForType(t);
			incrementInPixel = (double) (increment * factor);
		}
		return incrementInPixel;
	}

	/**
	 * Is the magnitude acceptable as a first interval.
	 * 
	 * ie. 10 would be acceptable for 8 to 164 (but not for 0.001 to 0.15)
	 * 
	 * This method checks if the magnitude is acceptable
	 * 
	 * @param maxValue the maximum value
	 * @param minValue the minimum value
	 * @param orderOfMagnitude order of magnitude
	 * @return is first interval acceptable
	 */
	public static boolean isOrderMagnitudeAcceptableFirstInterval(
			double maxValue, double minValue, double orderOfMagnitude) {

		double numberTicks = (maxValue - minValue) / orderOfMagnitude;

		if (numberTicks < 10) {
			return true;
		}
		return false;
	}

	/**
	 * Check if pixel "point" is within the bounds of the chart.
	 * 
	 * @param pixelsFromEdge pixels from edge of chart
	 * @param chart the chart
	 * @param orientation x or y
	 * @return is in bounds
	 */
	public static boolean inBounds(double pixelsFromEdge, Chart chart,
			Orientation orientation) {
		if (orientation == Orientation.X) {

			if ((pixelsFromEdge >= chart.leftOffset)
					&& (pixelsFromEdge <= (chart.leftOffset + chart.widthChart))) {
				return true;
			}

		} else if (orientation == Orientation.Y
				|| orientation == Orientation.Y2) {

			if (pixelsFromEdge >= chart.topOffset
					&& pixelsFromEdge <= (chart.topOffset + chart.heightChart)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/*
	 * TODO this method is completely wrong
	 * 
	 * @param chart
	 * 
	 * @param yAxis
	 * 
	 * @return
	 */
	public static double getYZeroOffsetInPixel(XYChart chart, YAxis yAxis) {

		double yMax = yAxis.axisScaling.getMaxValue();
		double yMin = yAxis.axisScaling.getMinValue();

		return (double) ((-yMin / (yMax - yMin)) * chart.heightChart);
	}

	/*
	 * I DO NOT UNDERSTAND THIS METHOD
	 * 
	 * TODO this method is completely wrong
	 * 
	 * Negative value :
	 * 
	 * @param chart
	 * 
	 * @param xAxis
	 * 
	 * @return
	 */

	public static double getXZeroOffsetInPixel(XYChart chart, XAxis xAxis) {

		double offset = -1;

		if (xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
			long xMax = ((TimeSeriesAxisScaling) xAxis.axisScaling).dateEnd.getTime();
			long xMin = ((TimeSeriesAxisScaling) xAxis.axisScaling).dateStart.getTime();

			long diffX = xMax - xMin;

			double v = (-xMin / (double) diffX);

			offset = (double) (v * chart.widthChart);

		} else {

			double xMax = xAxis.axisScaling.getMaxValue();
			double xMin = xAxis.axisScaling.getMinValue();

			double diffX = xMax - xMin;

			double v = (double) ((-xMin / (double) diffX) * chart.widthChart);

			offset = v;
		}

		return offset;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
