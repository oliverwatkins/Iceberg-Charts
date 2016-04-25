package com.bluewalrus.scaling;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.Orientation;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;
import com.bluewalrus.chart.draw.DateUtils;
import com.bluewalrus.chart.draw.XAxisDrawUtil;

public class TimeSeriesAxisScalingX extends TimeSeriesAxisScaling{

	public TimeSeriesAxisScalingX(Date dateStart, Date dateEnd, TimeInterval timeInt1,
			TimeInterval timeInt2, TimeInterval timeInt3) {
		super(dateStart, dateEnd, timeInt1, timeInt2, timeInt2, Orientation.X);	
	}


	
	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data) {
		
		//NOTE! data is ignored here. It's only used for enumeration
		
		drawAllIntervalTickAndLabels(g2d, xyChart);
		
//		drawGridFills(g2d, xyChart);
		
		drawGridLines(g2d, xyChart);
		
	}
	
	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		
		drawGridFills(g2d, xyChart);
		
	}
	
	
	/**
	 * Draw all the intervals and ticks for all intervals
	 * @param g
	 * @param chart
	 */
	public void drawAllIntervalTickAndLabels(Graphics g, XYChart chart) {
		
		if (this.interval1.isValid() && this.interval1.isActive()) {
			drawIntervalTickAndLabels((TimeInterval)this.interval1, g, chart, true);
		}
		if (this.interval2 != null && this.interval2.isValid() && this.interval2.isActive()) {
			drawIntervalTickAndLabels((TimeInterval)this.interval2, g, chart, true);
		}
		if (this.interval3 != null && this.interval3.isValid() && this.interval3.isActive()) {
			drawIntervalTickAndLabels((TimeInterval)this.interval3, g, chart, false);
		}
	}
	

	
	protected void drawIntervalLabel(TimeInterval interval, Graphics g, 
			XYChart chart, int incrementNumber, double incrementInPixel) {
		
		g.setColor(chart.xAxis.axisColor);
		
		double factor = getMultiplicationFactor(chart); 
		
		//to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
		
		double pixFromLeft = getFromStart(chart, toFirstInPixels, incrementInPixel, incrementNumber);
		
		long ms = DateUtils.getMsToNearestDataType(this.dateStart, interval.type);
		
		
		long date = dateStart.getTime() + ms; 
		
		long date2 = -888;
		
		if (interval.type == Type.YEAR) {
			date2 = DateUtils.addYear(date, incrementNumber);
		}else if (interval.type == Type.MONTH) {
			date2 = DateUtils.addMonth(date, incrementNumber);
		}else if (interval.type == Type.DAY) {
			date2 = DateUtils.addDay(date, incrementNumber);
		}else if (interval.type == Type.WEEK) {
			date2 = DateUtils.addWeek(date, incrementNumber);
		}else {
			
			throw new RuntimeException("Unknown interval type " + interval.type);
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
		
		XAxisDrawUtil.drawXLabel(g, chart, pixFromLeft, xLabel, chart.xAxis, interval.level);
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
	protected double getToFirstIntervalValueFromMinInPixels(AbstractInterval increment,
			double factor) {

		
		TimeInterval inter = (TimeInterval) increment;
		
		long ms = DateUtils.getMsToNearestDataType(this.dateStart, inter.type);

		double pix = (ms) * factor; //convert to pixels
		
		if (pix < 0)
			throw new RuntimeException("pixels cannot be negative. First val to min = " + ms + ". dateStart.getTime() " + dateStart.getTime());
		
		return pix;
	}

    
	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		throw new RuntimeException("This has not yet been implemented");
	}
    
	
	
	public double getMaxValue() {
		return this.dateEnd.getTime(); //dangerous, casting to double??
	}
	public double getMinValue() {
		return this.dateStart.getTime();
	}
	
    
    
	private double getFromLeft(Chart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double fromLeftPixels = chart.leftOffset +  (i * incrementInPixel) + toFirstInPixels;
		return fromLeftPixels;
	}
    
	@Override
	protected double getMultiplicationFactor(XYChart chart) {
    	return ((double) chart.widthChart / (double) (dateEnd.getTime() - dateStart.getTime()));
	}


	protected void drawIntervalTick(TimeInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {

		g.setColor(chart.xAxis.axisColor);
		
		double factor = getMultiplicationFactor(chart); 
		
		//to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
		
		double pixFromLeft = getFromStart(chart, toFirstInPixels, incrementInPixel, i);
		
		XAxisDrawUtil.drawIntervalTick(interval, g, chart, pixFromLeft, chart.xAxis);
	}

    /**
     * TODO : similar code
     */
    public void drawGridLines(AbstractInterval interval, Graphics2D g, XYChart chart) {

		double factor = getMultiplicationFactor(chart); 
    	
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);

    	int incrementNo = getIncrementNumber(interval);
    	
        double incrementInPixel = getIncrementInPixels(interval, chart);

        g.setColor(interval.styling.graphLine.color);

        for (int i = 0; i < incrementNo; i++) {
        	
            double fromLeft = getFromStart(chart, toFirstInPixels, incrementInPixel, i);

            /**
             * Draw Grid line
             */
            XAxisDrawUtil.drawGridLine(interval, g, chart, fromLeft);
            
        }
    }


	@Override
	protected double getToFirstIntervalValueFromMinInPixels(Double interval,
			double factor) {
		
		throw new RuntimeException("This has not yet been implemented");
	}

	
	
//TODO!!! This method is identical to LinearNumerical!!
	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels,
			double incrementInPixel, int i) {
		double fromLeft = chart.leftOffset + (i * incrementInPixel)
				+ toFirstInPixels;
		return fromLeft;
	}


	@Override
	protected void drawGridFills(AbstractInterval interval12, Graphics2D g,
			XYChart chart) {
		throw new RuntimeException("This has not yet been implemented");
	}










}
