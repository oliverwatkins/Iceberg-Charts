package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;

public abstract class TimeSeriesAxisDraw extends AxisDraw{

	
	public Date dateStart;
	public Date dateEnd;
	public TimeInterval timeInt1;
	public TimeInterval timeInt2; 
	public TimeInterval timeInt3;
	
	
	public TimeSeriesAxisDraw(Date dateStart, Date dateEnd, TimeInterval timeInt1,
			TimeInterval timeInt2, TimeInterval timeInt3) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		
		this.timeInt1 = timeInt1;
		this.timeInt2 = timeInt2;
		this.timeInt3 = timeInt3;
	}
	
	
	
	/**
	 * Draw the intervals ticks and labels for one particular interval:
	 * 
	 * 10--| 
	 *     | 
	 *     | 
	 * 20--| 
	 *     |
	 * 
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param showLabel
	 */
	protected void drawIntervalTickAndLabels(TimeInterval interval, Graphics g,
			XYChart chart, boolean showLabel) {


		TimeInterval.Type t = interval.getInterval();
		
		long increment = DateUtils.getMsForType(t); // :(

		
		/**
		 * TODO this code isn't very correct. The method getMsForType is incorrect for year and month because they are both variable
		 * and this could lead to the incrementNo below being incorrectly calculated. incrementNo could be out by one.
		 */
		int incrementNo = (int) ((dateEnd.getTime() - dateStart.getTime()) / increment); //shit!!

		System.out.println("incrementNo " + incrementNo);
		
		double factor = this.getMultiplicationFactor(chart);
		
		double incrementInPixel = (double) (increment * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			
			drawIntervalTick(interval, g, chart, i, incrementInPixel);

			if (showLabel)
				drawIntervalLabel(interval, g, chart, i, incrementInPixel);
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
	protected abstract void drawIntervalLabel(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);

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

	protected abstract void drawIntervalTick(TimeInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);
	
	
	

}
