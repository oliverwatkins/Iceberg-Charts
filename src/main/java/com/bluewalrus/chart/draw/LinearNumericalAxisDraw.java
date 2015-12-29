package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Line;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.Axis.AxisType;

public abstract class LinearNumericalAxisDraw extends AxisDraw{
	
	public double maxValue = 100;
	public double minValue = 0;

	public Interval interval1 = new Interval(0, 0.0);
	public Interval interval2 = new Interval(0, 0.0);
	public Interval interval3 = new Interval(0, 0.0);
	
	public Line zeroLine = new Line(Color.GRAY, false, 1);
	

	
	/**
	 * Default constructor
	 *
	 * @param name
	 * @param type
	 */
	public LinearNumericalAxisDraw() {
		this(0.0, 100.0, 50.0, 10.0, 5.0); //arbitrary values
	}
	
	public LinearNumericalAxisDraw(double min, double max) {
		
		this(min, max, new Interval(0,0.0), new Interval(0,0.0), new Interval(0,0.0));
	}

	public LinearNumericalAxisDraw(Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {
		
		this(0.0, 100.0, primaryIncrements, secondaryIncrements,
				tertiaryIncrements);
	}


	public LinearNumericalAxisDraw(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements) {

		this.maxValue = maxValue;
		this.minValue = minValue;

//		this.labelText = name;

		if (primaryIncrements != null) {
			this.interval1 = new Interval(8, primaryIncrements);
		}
		if (secondaryIncrements != null) {
			this.interval2 = new Interval(4, secondaryIncrements);
		}
		if (tertiaryIncrements != null) {
			this.interval3 = new Interval(2, tertiaryIncrements);
		}
	}

	/**
	 * 
	 * @param minValue
	 *            minimum axis value
	 * @param maxValue
	 *            maximum axis value
	 * @param interval1
	 * @param interval2
	 * @param interval3
	 * @param name
	 */
	public LinearNumericalAxisDraw(Double minValue, Double maxValue, Interval interval1, Interval interval2, Interval interval3) {

		this.maxValue = maxValue;
		this.minValue = minValue;
		
//		this.labelText = name;

		if (interval1 == null) {
			this.interval1 = new Interval(0, 0.0);
			this.interval1.setActive(false);
		} else {
			this.interval1 = interval1;
		}
		if (interval2 == null) {
			this.interval2 = new Interval(0, 0.0);
			this.interval2.setActive(false);
		} else {
			this.interval2 = interval2;
		}
		if (interval3 == null) {
			this.interval3 = new Interval(0, 0.0);
			this.interval3.setActive(false);
		} else {
			this.interval3 = interval3;
		}
	}
	
	



	public abstract void drawGridLine(Interval interval, Graphics2D g, XYChart chart);

	public abstract void drawYGridLineOnZero(Graphics2D g, XYChart chart);
	
	/**
	 * Draw the label next to the tick
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	protected abstract void drawIntervalLabel(Interval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);
	
	
	public void drawGridLines(Graphics2D g, XYChart chart) {
		
		if (interval1 != null && interval1.getIncrement() != 0
			&& interval1.graphLine != null) {
			this.drawGridLine(interval1, g, chart);
		}
		if (interval2 != null && interval2.getIncrement() != 0
			&& interval2.graphLine != null) {
			this.drawGridLine(interval2, g, chart);
		}
		if (interval3 != null && interval3.getIncrement() != 0
			&& interval3.graphLine != null) {
			this.drawGridLine(interval3, g, chart);
		}
		
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
	protected void drawIntervalTickAndLabels(Interval interval, Graphics g,
			XYChart chart, boolean showLabel) {


		Double increment = interval.getIncrement();

		int incrementNo = (int) ((maxValue - minValue) / increment);

		double factor = this.getMultiplicationFactor(chart);

		double incrementInPixel = (double) (increment * factor);

//		g.setColor(this.axisColor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			
			drawIntervalTick(interval, g, chart, i, incrementInPixel);

			if (showLabel)
				drawIntervalLabel(interval, g, chart, i, incrementInPixel);
		}

	}
	
	
	/**
	 * Basically the width (or height) of the chart in pixels, divided by difference in max and min values.
	 * @param chart
	 * @return
	 */
	protected abstract double getMultiplicationFactor(XYChart chart);

	/**
	 * The distance in pixels to the first displayable interval
	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected double getToFirstIntervalValueFromMinInPixels(Double increment,
			double factor) {

		double val = getToFirstIntervalValueFromMin(increment);

		return (val - minValue) * factor;
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
	protected double getToFirstIntervalValueFromMin(Double increment) {

		double val = this.minValue;

		// find first value which has a remainder of zero from increment
		// starting at min value.
		while (val % increment != 0) {
			val++;
		}
		return val;
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

	protected abstract void drawIntervalTick(Interval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);

	public void drawAllIntervalTickAndLabels(Graphics g, XYChart chart) {
		
		if (this.interval1.isValid() && this.interval1.isActive()) {
			drawIntervalTickAndLabels(this.interval1, g, chart, true);
		}
		if (this.interval2.isValid() && this.interval2.isActive()) {
			drawIntervalTickAndLabels(this.interval2, g, chart, false);
		}
		if (this.interval3.isValid() && this.interval3.isActive()) {
			drawIntervalTickAndLabels(this.interval3, g, chart, false);
		}
		
	}

	
	
	
	
	
	
	
}
