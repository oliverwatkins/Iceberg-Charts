package com.frontangle.ichart.scaling;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.Orientation;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.AbstractInterval;
import com.frontangle.ichart.chart.axis.Axis;
import com.frontangle.ichart.chart.axis.TimeInterval;

/**
 * 
 * @author Oliver Watkins
 *
 */
public abstract class AxisScaling {
	
	protected Orientation orientation;
	
	protected double maxValue = -1; //101; // arbitrary
	protected double minValue = -1; //0; // arbitrary

	public AbstractInterval interval1; 
	public AbstractInterval interval2; 
	public AbstractInterval interval3; 

	
	protected AxisScaling(Orientation orientation) {
		this.orientation = orientation;
	}
	public AxisScaling() {
		// TODO Auto-generated constructor stub
	}


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

	
	protected Axis getAxis(XYChart chart) {
		if (orientation == Orientation.X) {
			return chart.xAxis;
		}else if (orientation == Orientation.Y) {
			return chart.yAxis;
		}else if (orientation == Orientation.Y2) {
			return chart.yAxis2;
		}else {
			throw new RuntimeException("not supported");
		}
	}
	
	/*
	 * Pre-rendering. Things that can overpaint things of another axis such as grid fills should go in here.
	 * 
	 * For example the grid fill of X would overpaint the grid lines of Y if they were in the drawAll. All drawAllPre are
	 * performed together followed by the drawAll.
	 * 
	 * @param g2d
	 * @param xyChart
	 * @param data
	 */
	public abstract void drawAllPre(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data);
	
	public abstract void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data);
	
	public abstract void drawIntervalTickAndLabelsAndGridLines(AbstractInterval interval,
			Graphics2D g, XYChart chart, boolean showLabel);
	
	
	
	protected abstract void drawGridLineOnZero(Graphics2D g);

	/*
	 * In order to convert a value interval into pixels, it needs to be multipled by a factor
	 * 
	 * @param chart
	 * @return
	 */
	public abstract double getMultiplicationFactor(XYChart chart);

	protected abstract void drawGridFills(AbstractInterval interval12, Graphics2D g, XYChart chart);
	
	protected abstract double getToFirstIntervalValueFromMinInPixels(Double interval, double factor);

	/*
	 * TODO rename
	 * 
	 * Get from left, or from top
	 * 
	 * @param chart
	 * @param toFirstInPixels
	 * @param incrementInPixel
	 * @param i
	 * @return
	 * 
	 */
	protected abstract double getFromStart(XYChart chart, double toFirstInPixels, double incrementInPixel, int incrementNo);

	
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
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}	
}


