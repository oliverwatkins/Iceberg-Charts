package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.stream.events.StartDocument;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;

public class TimeSeriesAxisDrawX extends TimeSeriesAxisDraw{

	public TimeSeriesAxisDrawX(Date dateStart, Date dateEnd, TimeInterval timeInt1,
			TimeInterval timeInt2, TimeInterval timeInt3) {
		super(dateStart, dateEnd, timeInt1, timeInt2, timeInt2);	
	}

	
	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data) {
		
		//NOTE! data is ignored here. It's only used for enumeration

		
		drawAllIntervalTickAndLabels(g2d, xyChart);
		
//		drawGridLines(g2d, xyChart);
	}
	
	/**
	 * Draw all the intervals and ticks for all intervals
	 * @param g
	 * @param chart
	 */
	public void drawAllIntervalTickAndLabels(Graphics g, XYChart chart) {
		
		if (this.timeInt1.isValid() && this.timeInt1.isActive()) {
			drawIntervalTickAndLabels(this.timeInt1, g, chart, true);
		}
		if (this.timeInt2 != null && this.timeInt2.isValid() && this.timeInt2.isActive()) {
			drawIntervalTickAndLabels(this.timeInt2, g, chart, true);
		}
		if (this.timeInt3 != null && this.timeInt3.isValid() && this.timeInt3.isActive()) {
			drawIntervalTickAndLabels(this.timeInt3, g, chart, false);
		}
	}
	
	protected void drawIntervalTick(TimeInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {

		g.setColor(chart.xAxis.axisColor);
		
		double factor = getMultiplicationFactor(chart); 
		
		//to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
		
		double pixFromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, i);
		
		XAxisDrawUtil.drawIntervalTick(interval, g, chart, pixFromLeft, chart.xAxis);

}
	
	
	protected void drawIntervalLabel(TimeInterval interval, Graphics g, 
			XYChart chart, int incrementNumber, double incrementInPixel) {
		
		g.setColor(chart.xAxis.axisColor);
		
		double factor = getMultiplicationFactor(chart); 
		
		//to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
		
		double pixFromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, incrementNumber);
		
		long ms = DateUtils.getMsToNearestDataType(this.dateStart, interval.type);
		
		
		long date = dateStart.getTime() + ms; 
		
		long date2 = -888;
		if (interval.type == Type.YEAR) {
			
			date2 = DateUtils.addYear(date, incrementNumber);
		
		}else if (interval.type == Type.MONTH) {
			date2 = DateUtils.addMonth(date, incrementNumber);
		}
		
		
		SimpleDateFormat df;
		
		if (interval.dateFormat != null) {
			df = interval.dateFormat;
		}else {
			df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		}

		
		
		String xLabel = df.format(date2);
		
		/**
		 * Draw X Label
		 */
		XAxisDrawUtil.drawXLabel(g, chart, pixFromLeft, xLabel, chart.xAxis);

	}
	


	/**
	 * The distance in pixels to the first displayable interval
	 * 
	 * Get the first interval that should be displayed on the axis. Eg. if the
	 * interval increment is 50, then we want the first value to be a multiple
	 * of 50.
	 * 
	 * if the min/max range is 3/101, then the first value would be 50 (and not
	 * 3)	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected double getToFirstIntervalValueFromMinInPixels(TimeInterval increment,
			double factor) {

		
		long ms = DateUtils.getMsToNearestDataType(this.dateStart, increment.type);

		double pix = (ms) * factor; //convert to pixels
		
		if (pix < 0)
			throw new RuntimeException("pixels cannot be negative. First val to min = " + ms + ". dateStart.getTime() " + dateStart.getTime());
		
		return pix;
	}

    
	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO 
		
	}
    
    
    
	private double getFromLeft(Chart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double fromLeftPixels = chart.leftOffset +  (i * incrementInPixel) + toFirstInPixels;
		return fromLeftPixels;
	}
    
	@Override
	protected double getMultiplicationFactor(XYChart chart) {
    	return ((double) chart.widthChart / (double) (dateEnd.getTime() - dateStart.getTime()));
	}


}
