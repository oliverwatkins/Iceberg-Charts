package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.TimeInterval.Type;

public class TimeSeriesAxisDrawX extends TimeSeriesAxisDraw{

	public TimeSeriesAxisDrawX(Date date, Date date2, TimeInterval timeInt1,
			TimeInterval timeInt2, TimeInterval timeInt3) {
		super(date, date2, timeInt1, timeInt2, timeInt2);	
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
		if (this.timeInt2.isValid() && this.timeInt2.isActive()) {
			drawIntervalTickAndLabels(this.timeInt2, g, chart, false);
		}
		if (this.timeInt3.isValid() && this.timeInt3.isActive()) {
			drawIntervalTickAndLabels(this.timeInt3, g, chart, false);
		}
	}
	
	protected void drawIntervalTick(TimeInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {

		g.setColor(chart.xAxis.axisColor);
		
		double factor = getMultiplicationFactor(chart); 
		
		//to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
		
		double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, i);

		System.out.println("toFirstInPixels " + toFirstInPixels);

		System.out.println("incrementInPixel " + incrementInPixel);


//		fromLeft = 4;
		
		System.out.println("pixels from left " + fromLeft);
		
		XAxisDrawUtil.drawIntervalTick(interval, g, chart, fromLeft, chart.xAxis);

}
	
	
	protected void drawIntervalLabel(TimeInterval interval, Graphics g, 
			XYChart chart, int incrementNumber, double incrementInPixel) {
		
		g.setColor(chart.xAxis.axisColor);

    	
		Type increment = interval.getInterval();
		
		
		
        double factor = getMultiplicationFactor(chart); 
        
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval, factor);
    	
    	
    	
//    	double toFirst = getToFirstIntervalValueFromMin(increment);
//    	
//        String xLabel = "" + ((incrementNumber * increment) + toFirst);
//        
//        double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel,incrementNumber);
//
//   
//        /**
//         * Draw X Label
//         */
//        XAxisDrawUtil.drawXLabel(g, chart, fromLeft, xLabel, chart.xAxis);
        
   	 

	}
	
	/**
	 * The distance in pixels to the first displayable interval
	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected double getToFirstIntervalValueFromMinInPixels(TimeInterval increment,
			double factor) {

		
		long ms = getToFirstIntervalValueFromMin(increment);

		double pix = (ms) * factor; //convert to pixels
		
		if (pix < 0)
			throw new RuntimeException("pixels cannot be negative. First val to min = " + ms + ". dateStart.getTime() " + dateStart.getTime());
		
		return pix;
	}
	
	
	/**
	 * Get the first interval that should be displayed on the axis. Eg. if the
	 * interval increment is 50, then we want the first value to be a multiple
	 * of 50.
	 * 
	 * if the min/max range is 3/101, then the first value would be 50 (and not
	 * 3)
	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected long getToFirstIntervalValueFromMin(TimeInterval increment) {

//		Date dateStart = this.dateStart;

		long ms = getMsToNearestDataType(this.dateStart, increment.type);
		
//		long ms = getMsForType(increment.type);
		
		
		// find first value which has a remainder of zero from increment
		// starting at min value.
//		while (val % increment != 0) {
//			val++;
//		}
		return ms;
	}
	
	
	
	
	
	
	
//
//
//
//	protected void drawIntervalTick(NumericalInterval interval, Graphics g, 
//			XYChart chart, int i, double incrementInPixel) {
//		
//		g.setColor(chart.xAxis.axisColor);
//		
//		double factor = getMultiplicationFactor(chart); 
//		
//        //to first increment
//    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);
//		
//        double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, i);
//        
//        XAxisDrawUtil.drawIntervalTick(interval, g, chart, fromLeft, chart.xAxis);
//		
//	}
//	
//    public void drawGridLine(NumericalInterval interval, Graphics2D g, XYChart chart) {
//
//		double factor = getMultiplicationFactor(chart); 
//    	
//        //to first increment
//    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);
//
//        int incrementNo = (int) (maxValue / interval.getInterval());
//        
//        double incrementInPixel = (double) (interval.getInterval() * factor);
//
//        g.setColor(interval.graphLine.color);
//
//        for (int i = 0; i < incrementNo; i++) {
//        	
//            double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, i);
//            
//            /**
//             * Draw Grid line
//             */
//            XAxisDrawUtil.drawGridLine(interval, g, chart, fromLeft);
//        }
//    }
    
	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO 
		
	}
    
    
    
	private double getFromLeft(Chart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double fromLeft = chart.leftOffset +  (i * incrementInPixel) + toFirstInPixels;
		return fromLeft;
	}
    
	@Override
	protected double getMultiplicationFactor(XYChart chart) {
    	return ((double) chart.widthChart / (double) (dateEnd.getTime() - dateStart.getTime()));
	}


}
