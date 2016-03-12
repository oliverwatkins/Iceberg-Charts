package com.bluewalrus.chart.draw;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.NumericalInterval;

/**
 * TODO rename to Scale?
 * 
 * @author Oliver Watkins
 *
 */
public abstract class AxisDraw {
	
	Orientation orientation;
	

	public double maxValue = -1; //101; // arbitrary
	public double minValue = -1; //0; // arbitrary

	public AbstractInterval interval1; 
	public AbstractInterval interval2; 
	public AbstractInterval interval3; 

	
	protected AxisDraw(Orientation orientation) {
		this.orientation = orientation;
	}
	
	
	/**
	 * Pre-rendering. Things that can overpaint things of another axis such as grid fills should go in here.
	 * 
	 * For example the grid fill of X would overpaint the grid lines of Y if they were in the drawAll. All drawAllPre are
	 * performed together followed by the drawAll.
	 * 
	 * @param g2d
	 * @param xyChart
	 * @param data
	 */
	public abstract void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data);
	
	public abstract void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data);




	public void drawGridFills(Graphics2D g, XYChart chart) {

		if (interval1 != null && interval1.isValid()
				&& interval1.graphLine != null && interval1.graphLine.gridFill != null) {
			this.drawGridFill(interval1, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval2.graphLine != null && interval2.graphLine.gridFill != null) {
			this.drawGridFill(interval2, g, chart);
		}
		if (interval3 != null && interval3.isValid()
				&& interval3.graphLine != null && interval3.graphLine.gridFill != null) {
			this.drawGridFill(interval3, g, chart);
		}
	}

	
	public void drawGridLines(Graphics2D g, XYChart chart) {
		
		if (interval3 != null && interval3.isValid()
				&& interval3.graphLine != null) {
			this.drawGridLine(interval3, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval2.graphLine != null) {
			this.drawGridLine(interval2, g, chart);
		}
		
		if (interval1 != null && interval1.isValid()
				&& interval1.graphLine != null) {
			this.drawGridLine(interval1, g, chart);
		}

	}

	protected abstract void drawGridLine(AbstractInterval interval,
			Graphics2D g, XYChart chart);
	
	protected abstract void drawGridLineOnZero(Graphics2D g);

	protected abstract double getMultiplicationFactor(XYChart chart);

	protected void drawGridFill(AbstractInterval interval, Graphics2D g, XYChart chart) {
		
		
		double factor = getMultiplicationFactor(chart);
	
		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				((NumericalInterval) interval).getInterval(), factor);
	
		int totalIncrementNo = (int) ((maxValue - minValue) / ((NumericalInterval) interval)
				.getInterval());
		
		totalIncrementNo++;
	
		double incrementInPixel = (double) (((NumericalInterval) interval)
				.getInterval() * factor);
		
		g.setColor(interval.graphLine.color);
	
		for (int incrementNo = 0; incrementNo <= totalIncrementNo; incrementNo++) {
	
			double fromStart = getFromStart(chart, toFirstInPixels,
					incrementInPixel, incrementNo);
			
			fromStart = fromStart - incrementInPixel; //start left of the y Axis (invisible part)
	
			/**
			 * Draw Grid line
			 */
			
			if (this.orientation == Orientation.X)

				interval.graphLine.fillAreaX(g, (int)fromStart, incrementInPixel, chart, incrementNo);
				
			else if (this.orientation == Orientation.Y)
				
				interval.graphLine.fillAreaY(g, (int)fromStart, incrementInPixel, chart, incrementNo);
			else {
				throw new RuntimeException("asdfasdf");
			}

		}
	
	}
	
	
	
	
	
//	
//	@Override
//	protected void drawGridFill(AbstractInterval interval, Graphics2D g, XYChart chart) {
//		NumericalInterval inter = (NumericalInterval) interval;
//		
//        int incrementNo = (int) ((maxValue - minValue) / inter.getInterval());
//
//        //divide height of chart by actual height of chart to get the multiplaying factor
//        double factor = getMultiplicationFactor(chart); 
//        
//        //to first increment
//    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(inter.getInterval(), factor);
//    	
//        double incrementInPixel = (double) (inter.getInterval() * factor);
//
//        for (int i = 0; i < incrementNo; i++) {
//
//            double fromTop = getFromTop(chart, i, incrementInPixel, toFirstInPixels);
//
//        	/**
//        	 * Draw grid line
//        	 */
//            YAxisDrawUtil.drawGridFill(inter, g, chart, fromTop, i , incrementInPixel, incrementNo);
//        }
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected abstract double getToFirstIntervalValueFromMinInPixels(Double interval, double factor);

	/**
	 * Get from left, or from bottom
	 * @param chart
	 * @param toFirstInPixels
	 * @param incrementInPixel
	 * @param i
	 * @return
	 * 
	 */
	protected abstract double getFromStart(XYChart chart, double toFirstInPixels, double incrementInPixel, int incrementNo);

}
