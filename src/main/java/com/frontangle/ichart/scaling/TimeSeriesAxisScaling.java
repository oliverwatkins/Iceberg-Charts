package com.frontangle.ichart.scaling;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.DateUtils;
import com.frontangle.ichart.chart.Orientation;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.TimeInterval.Type;
import com.frontangle.ichart.chart.draw.XAxisDrawUtil;

/**
 * Scaling for Time based data.
 * 
 * @author Oliver Watkins
 */
public class TimeSeriesAxisScaling extends AxisScaling {

	public Date dateStart;
	public Date dateEnd;

	public TimeSeriesAxisScaling(Date dateStart, Date dateEnd,
			TimeInterval timeInt1, TimeInterval timeInt2, TimeInterval timeInt3) {

		this.dateStart = dateStart;
		this.dateEnd = dateEnd;

		this.interval1 = timeInt1;
		this.interval2 = timeInt2;
		this.interval3 = timeInt3;

		if (interval1 != null)
			this.interval1.setLevel(1);
		if (interval2 != null)
			this.interval2.setLevel(2);
		if (interval3 != null)
			this.interval3.setLevel(3);
	}

	
	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		drawGridFills(g2d, xyChart);
	}
	
	/**
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 */
	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		if (this.interval1.isValid() && this.interval1.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(
					(TimeInterval) this.interval1, g2d, xyChart, true);
		}
		if (this.interval2 != null && this.interval2.isValid()
				&& this.interval2.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(
					(TimeInterval) this.interval2, g2d, xyChart, true);
		}
		if (this.interval3 != null && this.interval3.isValid()
				&& this.interval3.isActive()) {
			drawIntervalTickAndLabelsAndGridLines(
					(TimeInterval) this.interval3, g2d, xyChart, false);
		}
	}
	
	/**
	 * Draw the intervals ticks and labels for one particular interval:
	 * 
	 * 10--| 
	 * 	   | 
	 * 	   | 
	 * 20--| 
	 *     |
	 * 
	 */

	public void drawIntervalTickAndLabelsAndGridLines(AbstractInterval aInterval,
			Graphics2D g, XYChart chart, boolean showLabel) {
		
		TimeInterval interval = (TimeInterval)aInterval;

		int incrementNo = getIncrementNumber(interval);

		double totalIncrementPixs = 0;

		double dayInPixel = getIncrementInPixels(TimeInterval.Type.DAY, chart);

		if (interval.isCentered() && showLabel) {
			//we may need to show label that is being cut by the y axis... only for centered intervals.
			drawFirstCenteredLabel(interval, g, chart);
		}
		
		for (int i = 1; i < (incrementNo + 1); i++) {
			double intervalInPixels = 0;

			/**
			 * TICK
			 */
			drawIntervalTick(interval, g, chart, i, totalIncrementPixs);

			/**
			 * LABEL
			 */
			if (showLabel)
				drawIntervalLabel(interval, g, chart, i-1, totalIncrementPixs);

			/**
			 * LINE
			 */
			drawGridLine(interval, g, chart, i, totalIncrementPixs);

			if (interval.type.equals(TimeInterval.Type.MONTH)) {
				intervalInPixels = getIncrementInPixelsForMonthAfterStartDate(
						i, dayInPixel);
			} else if (interval.type.equals(TimeInterval.Type.YEAR)) {
				intervalInPixels = getIncrementInPixelsForYearAfterStartDate(i,
						dayInPixel);
			} else {
				intervalInPixels = getIncrementInPixels(interval.type, chart);
			}
			totalIncrementPixs = totalIncrementPixs + intervalInPixels;
		}
	}
	
	private void drawGridLine(TimeInterval interval, Graphics2D g,
			XYChart chart, int i, double totalIncrementPixs) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment (edge of chart to first interval value)
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		double totalDistanceFromEdge = chart.leftOffset + toFirstInPixels
				+ totalIncrementPixs;

		XAxisDrawUtil.drawGridLine(interval, g, chart, totalDistanceFromEdge);

	}
	
	@Override
	protected void drawGridFills(AbstractInterval intv, Graphics2D g,
			XYChart chart) {

		TimeInterval interval = (TimeInterval) intv;

		double factor = getMultiplicationFactor(chart);
		double dayInPixel = getIncrementInPixels(TimeInterval.Type.DAY, chart);
		// to first increment (edge of chart to first interval value)
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		int incrementNo = getIncrementNumber(interval);

		double intervalInPixels = 0;

		XAxisDrawUtil.drawGridFill(interval, g, chart, chart.leftOffset,
				toFirstInPixels, 0);

		double sumOfIntervals = 0;

		for (int i = 1; i < (incrementNo + 1); i++) {

			intervalInPixels = getIntervalInPixels(chart, interval, dayInPixel, i);

			XAxisDrawUtil.drawGridFill(interval, g, chart, chart.leftOffset
					+ toFirstInPixels + sumOfIntervals, intervalInPixels, i);

			sumOfIntervals = sumOfIntervals + intervalInPixels;
		}
	}
	
	/**
	 * Draw the first label of a centered label interval. Only used for centered labels!!
	 */
	private void drawFirstCenteredLabel(TimeInterval interval, Graphics2D g, XYChart chart) {
		Date d = DateUtils.getDatePointToNearestDataType(dateStart, interval.type, false);

		SimpleDateFormat df;
		if (interval.dateFormat != null) {
			df = interval.dateFormat;
		} else {
			df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		}
		String xLabel = df.format(d);
		
		double factor = getMultiplicationFactor(chart);
		
		//pixels to the left
		double pixelsDistanceToFirstLeftPointOffTheChart = getToFirstPointOffTheChart(
				interval, factor, chart); 

		double pixelsToFirstIntervalPoint = chart.leftOffset - pixelsDistanceToFirstLeftPointOffTheChart;


		XAxisDrawUtil.drawXIntervalLabel(g, chart, pixelsToFirstIntervalPoint,
				xLabel, // + label,
				chart.xAxis, interval);
	}

	/**
	 * Get the pixels going to the left to the next interval point.
	 */
	private double getToFirstPointOffTheChart(TimeInterval interval,
			double factor, XYChart chart) {
		
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);
		
		double incrementInPixels = ChartUtils.getIncrementInPixels(interval, chart, this);
		
		double diff = (incrementInPixels - toFirstInPixels);
		
		return diff;
	}



	private double getIntervalInPixels(XYChart chart, TimeInterval interval,
			double dayInPixel, int i) {
		double intervalInPixels;
		if (interval.type.equals(TimeInterval.Type.MONTH)) {
			intervalInPixels = (double) getIncrementInPixelsForMonthAfterStartDate(
					i, dayInPixel);
		} else if (interval.type.equals(TimeInterval.Type.YEAR)) {
			intervalInPixels = (double) getIncrementInPixelsForYearAfterStartDate(
					i, dayInPixel);
		} else {
			intervalInPixels = (double) getIncrementInPixels(interval.type,
					chart);
		}
		return intervalInPixels;
	}


	/**
	 * given a number i, get the i'th month after the start date month and get
	 * its increments in pixels.
	 * 
	 */
	private double getIncrementInPixelsForMonthAfterStartDate(int i,
			double dayInPixel) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dateStart);
		cal.add(Calendar.MONTH, i);

		int days = DateUtils.getDaysInMonth(cal.getTime());
		return dayInPixel * days;
	}

	private double getIncrementInPixelsForYearAfterStartDate(int i,
			double dayInPixel) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dateStart);
		cal.add(Calendar.YEAR, i);

		int days = DateUtils.getDaysInYear(cal.getTime());
		return dayInPixel * days;
	}

	protected double getIncrementInPixels(TimeInterval.Type t, XYChart chart) {

		long increment = DateUtils.getMsForType(t);
		double factor = this.getMultiplicationFactor(chart);
		double incrementInPixel = (double) (increment * factor);

		return incrementInPixel;
	}

	/*
	 * Get number of increments to display on axis for a particular time
	 * interval type.
	 */
	protected int getIncrementNumber(AbstractInterval interval) {

		TimeInterval inter = (TimeInterval) interval;

		TimeInterval.Type t = inter.getInterval();

		if (t.equals(TimeInterval.Type.MONTH)) {

		} else if (t.equals(TimeInterval.Type.YEAR)) {

		}

		long increment = DateUtils.getMsForType(t); // :(

		/**
		 * TODO this code isn't very correct. The method getMsForType is
		 * incorrect for year and month because they are both variable and this
		 * could lead to the incrementNo below being incorrectly calculated.
		 * incrementNo could be out by one.
		 */
		int incrementNo = (int) ((dateEnd.getTime() - dateStart.getTime()) / increment); // shit!!

		return incrementNo;
	}

	protected void drawIntervalLabel(TimeInterval interval, Graphics g,
			XYChart chart, int incrementNumber, double totalIncrementPixs) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		//get ms to next point
		long ms = DateUtils.getMsToNearestDataType(this.dateStart,
				interval.type, true);

		long timePointAtFirstInterval = dateStart.getTime() + ms;

		long totalTime = getTotalTime(interval, incrementNumber,
				timePointAtFirstInterval);

		// FORMAT
		SimpleDateFormat df;

		if (interval.dateFormat != null) {
			df = interval.dateFormat;
		} else {
			df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		}

		String xLabel = df.format(totalTime);

		double totalDistanceFromEdge = chart.leftOffset + toFirstInPixels + totalIncrementPixs;

		XAxisDrawUtil.drawXIntervalLabel(g, chart, totalDistanceFromEdge,
				xLabel, chart.xAxis, interval);
	}

	private long getTotalTime(TimeInterval interval, int incrementNumber,
			long timePointAtFirstInterval) {
		long totalTime = -1;

		/**
		 * Add incrementNumber of time type to the first interval point
		 */
		
		switch (interval.type) {
			case YEAR:
				totalTime = DateUtils.addYear(timePointAtFirstInterval, incrementNumber);
				break;
			case MONTH:
				totalTime = DateUtils.addMonth(timePointAtFirstInterval, incrementNumber);
				break;
			case DAY:
				totalTime = DateUtils.addDay(timePointAtFirstInterval, incrementNumber);
				break;
			case WEEK:
				totalTime = DateUtils.addWeek(timePointAtFirstInterval, incrementNumber);
				break;
			default:
				throw new RuntimeException("Unknown interval type " + interval.type);
		}
		return totalTime;
	}

	/*
	 * The distance in pixels to the first displayable interval
	 * 
	 * Get the first interval that should be displayed on the axis. Eg. if the
	 * interval increment is 50, then we want the first value to be a multiple
	 * of 50.
	 * 
	 * if the min/max range is 3/101, then the first value would be 50 (and not
	 * 3) *
	 * 
	 */
	protected double getToFirstIntervalValueFromMinInPixels(
			AbstractInterval increment, double factor) {

		TimeInterval inter = (TimeInterval) increment;

		long ms = DateUtils.getMsToNearestDataType(this.dateStart, inter.type, true);

		double pix = (ms) * factor; // convert to pixels

		if (pix < 0)
			throw new RuntimeException(
					"pixels cannot be negative. First val to min = " + ms + ". dateStart.getTime() " + dateStart.getTime());

		return pix;
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		throw new RuntimeException("This has not yet been implemented");
	}

	@Override
	public double getMultiplicationFactor(XYChart chart) {
		return ((double) chart.widthChart / (double) (dateEnd.getTime() - dateStart
				.getTime()));
	}

	/*
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 */
	protected void drawIntervalTick(TimeInterval interval, Graphics g,
			XYChart chart, int i, double totalIncrementPixs) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment (edge of chart to first interval value)
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		double totalDistanceFromEdge = chart.leftOffset + toFirstInPixels
				+ totalIncrementPixs;

		XAxisDrawUtil.drawIntervalTick(interval, g, chart,
				totalDistanceFromEdge, chart.xAxis);
	}

	@Override
	protected double getToFirstIntervalValueFromMinInPixels(Double interval,
			double factor) {

		throw new RuntimeException("This has not yet been implemented");
	}

	// TODO!!! This method is identical to LinearNumerical!!
	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels,
			double incrementInPixel, int i) {
		double fromLeft = chart.leftOffset + (i * incrementInPixel)
				+ toFirstInPixels;
		return fromLeft;
	}

	public double getMaxValue() {
		return this.dateEnd.getTime(); // dangerous, casting to double??
	}

	public double getMinValue() {
		return this.dateStart.getTime();
	}
}
