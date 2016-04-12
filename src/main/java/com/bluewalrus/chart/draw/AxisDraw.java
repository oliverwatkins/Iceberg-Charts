package com.bluewalrus.chart.draw;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.Axis;
import com.bluewalrus.chart.axis.NumericalInterval;

/**
 * TODO rename to Scale?
 * 
 * @author Oliver Watkins
 *
 */
public abstract class AxisDraw {
	
	Orientation orientation;
	

	public Orientation getOrientation() {
		return orientation;
	}


	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	protected double maxValue = -1; //101; // arbitrary
	protected double minValue = -1; //0; // arbitrary

	public AbstractInterval interval1; 
	public AbstractInterval interval2; 
	public AbstractInterval interval3; 

	
	protected AxisDraw(Orientation orientation) {
		this.orientation = orientation;
	}
	

	public void setMaxValue(double d) {
		this.maxValue = d;
	}
		
	public void setMinValue(double d) {
		this.minValue = d;
	}
	
	
	public double getMaxValue() {
		return maxValue;
	}
	public double getMinValue() {
		return minValue;
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
				&& interval1.styling != null
				&& interval1.styling.graphLine != null && interval1.styling.graphFill != null) {
			this.drawGridFills(interval1, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval2.styling != null
				&& interval2.styling.graphLine != null && interval2.styling.graphFill != null) {
			this.drawGridFills(interval2, g, chart);
		}
		if (interval3 != null && interval3.isValid()
				&& interval3.styling != null
				&& interval3.styling.graphLine != null && interval3.styling.graphFill != null) {
			this.drawGridFills(interval3, g, chart);
		}
	}

	



	public void drawGridLines(Graphics2D g, XYChart chart) {
		
		if (interval3 != null && interval3.isValid()
				&& interval3.styling != null
				&& interval3.styling.graphLine != null) {
			this.drawGridLines(interval3, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval2.styling != null
				&& interval2.styling.graphLine != null) {
			this.drawGridLines(interval2, g, chart);
		}
		
		if (interval1 != null && interval1.isValid()
				&& interval1.styling != null
				&& interval1.styling.graphLine != null) {
			this.drawGridLines(interval1, g, chart);
		}

	}


	
	protected Axis getAxis(XYChart chart) {
		if (orientation == Orientation.X) {
			return chart.xAxis;
		}else if (orientation == Orientation.Y) {
			return chart.yAxis;
		}else if (orientation == Orientation.Y2) {
			return ((XYYChart)chart).yAxis2;
		}else {
			throw new RuntimeException("not supported");
		}
	}
	
	
	protected abstract void drawGridLineOnZero(Graphics2D g);

	protected abstract double getMultiplicationFactor(XYChart chart);

	
	protected abstract void drawGridFills(AbstractInterval interval12, Graphics2D g,
			XYChart chart);
	
	
	protected abstract void drawGridLines(AbstractInterval interval,
			Graphics2D g, XYChart chart);
	

	
	
	
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


