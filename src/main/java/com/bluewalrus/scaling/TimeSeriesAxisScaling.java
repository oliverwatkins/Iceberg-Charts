package com.bluewalrus.scaling;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.DateUtils;
import com.bluewalrus.chart.Orientation;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;
import com.bluewalrus.chart.draw.XAxisDrawUtil;

public class TimeSeriesAxisScaling extends AxisScaling {

	public Date dateStart;
	public Date dateEnd;

	public TimeSeriesAxisScaling(Date dateStart, Date dateEnd,
			TimeInterval timeInt1, TimeInterval timeInt2, TimeInterval timeInt3) {

		super();

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

	/**
	 * Draw the intervals ticks and labels for one particular interval:
	 * 
	 * 10--| | | 20--| |
	 * 
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param showLabel
	 */
	protected void drawIntervalTickAndLabels(TimeInterval interval, Graphics g,
			XYChart chart, boolean showLabel) {

		int incrementNo = getIncrementNumber(interval);

		double incrementInPixel = getIncrementInPixels(interval, chart);

		if (interval.type.equals(TimeInterval.Type.MONTH)) {

			double dayInPixel = getIncrementInPixels(TimeInterval.Type.DAY, chart);

			int totalIncrementPixs = 0;
			for (int i = 0; i < (incrementNo + 1); i++) {
				
				int incrementForMonth = (int)getIncrementInPixelsForMonthAfterStartDate(interval.type, i, dayInPixel);
				totalIncrementPixs = totalIncrementPixs + incrementForMonth;
				
				System.out.println("incrementInPixel2 == " + incrementForMonth);
				
				drawIntervalTick(interval, g, chart, i, incrementForMonth, totalIncrementPixs);
				
				if (showLabel)
					drawIntervalLabel(interval, g, chart, i, incrementForMonth);
			}
			
			
		} else {

			/**
			 * TODO! This logic in incorrect for month and year. Both month and
			 * year are not the same size over time. They vary.
			 * 
			 * incrementInPixel varies if 28,29,30,21 days... same with leap
			 * years.
			 * 
			 */
			for (int i = 0; i < (incrementNo + 1); i++) {

				drawIntervalTick(interval, g, chart, i, incrementInPixel);

				if (showLabel)
					drawIntervalLabel(interval, g, chart, i, incrementInPixel);
			}

		}
	}

	
	/**
	 * TODO : similar code
	 */
	public void drawGridLines(AbstractInterval interval, Graphics2D g,
			XYChart chart) {

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		int incrementNo = getIncrementNumber(interval);

		double incrementInPixel = getIncrementInPixels(interval, chart);

		g.setColor(interval.styling.graphLine.color);

		
		
		
		
		if (((TimeInterval)interval).type.equals(TimeInterval.Type.MONTH)) {

			double dayInPixel = getIncrementInPixels(TimeInterval.Type.DAY, chart);
			
			for (int i = 0; i < (incrementNo + 1); i++) {
				
//				int incrementInPixel2 = (int)getIncrementInPixels(interval.type, i, dayInPixel);
//				
//				System.out.println("incrementInPixel2 == " + incrementInPixel2);
//				
//				drawIntervalTick(interval, g, chart, i, incrementInPixel2);
//				
//				if (showLabel)
//					drawIntervalLabel(interval, g, chart, i, incrementInPixel2);
				
				
				
			}
			
			
		} else {
			
			for (int i = 0; i < incrementNo; i++) {

				double fromLeft = getFromStart(chart, toFirstInPixels,
						incrementInPixel, i);

				/**
				 * Draw Grid line
				 */
				XAxisDrawUtil.drawGridLine(interval, g, chart, fromLeft);
			}
		}
	}
	
	/**
	 * for month
	 * 
	 * @param type
	 * @param i
	 * @param dateStart2
	 * @param dayInPixel 
	 * @return
	 */
	private double getIncrementInPixelsForMonthAfterStartDate(Type type, int i, double dayInPixel) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(this.dateStart);
		
		int m = cal.get(Calendar.MONTH);
		
		System.out.println("month = " + m + " i =  " + i);
		
		if (i == 0)
			return 0;
		
		
		cal.set(Calendar.MONTH, m+i);
		boolean isLeapYear = false;
		if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
			isLeapYear = true;
		}

		int days = -1;
		int m2 = cal.get(Calendar.MONTH);
		switch (m2) {
		case 0:
			days = 31;
			break;
		case 1:
			if (isLeapYear)
				days = 29;
			else
				days = 28;
			break;
		case 2:
			days = 31;
			break;
		case 3:
			days = 30;
			break;
		case 4:
			days = 31;
			break;
		case 5:
			days = 30;
			break;
		case 6:
			days = 31;
			break;
		case 7:
			days = 30;
			break;
		case 8:
			days = 31;
			break;
		case 9:
			days = 31;
			break;
		case 10:
			days = 30;
			break;
		case 11:
			days = 31;
			break;

		default:
			break;
		}
		
		
		
		return dayInPixel * days;
	}
	
	
	protected double getIncrementInPixels(AbstractInterval interval,
			XYChart chart) {

		TimeInterval inter = (TimeInterval) interval;

		TimeInterval.Type t = inter.getInterval();
		
		return getIncrementInPixels(t, chart);

	}
	
	
	protected double getIncrementInPixels(TimeInterval.Type t,
			XYChart chart) {
		
		long increment = DateUtils.getMsForType(t); // :( what about year and
													// month....

		double factor = this.getMultiplicationFactor(chart);

		double incrementInPixel = (double) (increment * factor);

		return incrementInPixel;
	}
	
	


	/**
	 * Get number of increments to display on axis for a particular time
	 * interval type.
	 * 
	 * @param interval
	 * @return
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

	/**
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	//
	// protected abstract void drawIntervalTick(TimeInterval interval, Graphics
	// g,
	// XYChart chart, int i, double incrementInPixel);
	//
	//

	// public TimeSeriesAxisScalingX(Date dateStart, Date dateEnd, TimeInterval
	// timeInt1,
	// TimeInterval timeInt2, TimeInterval timeInt3) {
	// super(dateStart, dateEnd, timeInt1, timeInt2, timeInt2, Orientation.X);
	// }

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		// NOTE! data is ignored here. It's only used for enumeration

		drawAllIntervalTickAndLabels(g2d, xyChart);

		drawGridLines(g2d, xyChart);

	}

	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {

		drawGridFills(g2d, xyChart);
	}

	@Override
	protected void drawGridFills(AbstractInterval interval12, Graphics2D g,
			XYChart chart) {
		// throw new RuntimeException("This has not yet been implemented");
	}

	/**
	 * Draw all the intervals and ticks for all intervals
	 * 
	 * @param g
	 * @param chart
	 */
	public void drawAllIntervalTickAndLabels(Graphics g, XYChart chart) {

		if (this.interval1.isValid() && this.interval1.isActive()) {
			drawIntervalTickAndLabels((TimeInterval) this.interval1, g, chart,
					true);
		}
		if (this.interval2 != null && this.interval2.isValid()
				&& this.interval2.isActive()) {
			drawIntervalTickAndLabels((TimeInterval) this.interval2, g, chart,
					true);
		}
		if (this.interval3 != null && this.interval3.isValid()
				&& this.interval3.isActive()) {
			drawIntervalTickAndLabels((TimeInterval) this.interval3, g, chart,
					false);
		}
	}

	/**
	 * Draw the label next to the tick
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	protected void drawIntervalLabel(TimeInterval interval, Graphics g,
			XYChart chart, int incrementNumber, double incrementInPixel) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		double pixFromLeft = getFromStart(chart, toFirstInPixels,
				incrementInPixel, incrementNumber);

		long ms = DateUtils.getMsToNearestDataType(this.dateStart,
				interval.type);

		long date = dateStart.getTime() + ms;

		long date2 = -888;

		if (interval.type == Type.YEAR) {
			date2 = DateUtils.addYear(date, incrementNumber);
		} else if (interval.type == Type.MONTH) {
			date2 = DateUtils.addMonth(date, incrementNumber);
		} else if (interval.type == Type.DAY) {
			date2 = DateUtils.addDay(date, incrementNumber);
		} else if (interval.type == Type.WEEK) {
			date2 = DateUtils.addWeek(date, incrementNumber);
		} else {

			throw new RuntimeException("Unknown interval type " + interval.type);
		}

		SimpleDateFormat df;

		if (interval.dateFormat != null) {
			df = interval.dateFormat;
		} else {
			df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		}

		String xLabel = df.format(date2);

		/**
		 * Draw X Label
		 */

		XAxisDrawUtil.drawXIntervalLabel(g, chart, pixFromLeft, xLabel,
				chart.xAxis, interval);
	}

	/**
	 * The distance in pixels to the first displayable interval
	 * 
	 * Get the first interval that should be displayed on the axis. Eg. if the
	 * interval increment is 50, then we want the first value to be a multiple
	 * of 50.
	 * 
	 * if the min/max range is 3/101, then the first value would be 50 (and not
	 * 3) *
	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected double getToFirstIntervalValueFromMinInPixels(
			AbstractInterval increment, double factor) {

		TimeInterval inter = (TimeInterval) increment;

		long ms = DateUtils.getMsToNearestDataType(this.dateStart, inter.type);

		double pix = (ms) * factor; // convert to pixels

		if (pix < 0)
			throw new RuntimeException(
					"pixels cannot be negative. First val to min = " + ms
							+ ". dateStart.getTime() " + dateStart.getTime());

		return pix;
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		throw new RuntimeException("This has not yet been implemented");
	}

	public double getMaxValue() {
		return this.dateEnd.getTime(); // dangerous, casting to double??
	}

	public double getMinValue() {
		return this.dateStart.getTime();
	}

	private double getFromLeft(Chart chart, double toFirstInPixels,
			double incrementInPixel, int i) {
		double fromLeftPixels = chart.leftOffset + (i * incrementInPixel)
				+ toFirstInPixels;
		return fromLeftPixels;
	}

	@Override
	protected double getMultiplicationFactor(XYChart chart) {
		return ((double) chart.widthChart / (double) (dateEnd.getTime() - dateStart
				.getTime()));
	}

	/**
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 * @param totalIncrementPixs 
	 */

	protected void drawIntervalTick(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel, int totalIncrementPixs) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);
		
		double xx = chart.leftOffset + toFirstInPixels + totalIncrementPixs;
//
//		double pixFromLeft = getFromStart(chart, toFirstInPixels,
//				incrementInPixel, i);

		XAxisDrawUtil.drawIntervalTick(interval, g, chart, xx,
				chart.xAxis);
	}

	
	protected void drawIntervalTick(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel) {

		g.setColor(chart.xAxis.axisColor);

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				interval, factor);

		double pixFromLeft = getFromStart(chart, toFirstInPixels,
				incrementInPixel, i);

		XAxisDrawUtil.drawIntervalTick(interval, g, chart, pixFromLeft,
				chart.xAxis);
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

}
