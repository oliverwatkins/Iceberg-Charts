package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.Orientation;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.NumericalInterval;

public abstract class LinearNumericalAxisDraw extends AxisDraw{
	
	public Line zeroLine = new Line(Color.GRAY, false, 1);
	
	/**
	 * Default constructor
	 *
	 * @param name
	 * @param type
	 */
	public LinearNumericalAxisDraw(Orientation orientation) {
		this(0.0, 100.0, 50.0, 10.0, 5.0, orientation); //arbitrary values
	}
	
	public LinearNumericalAxisDraw(double min, double max,Orientation orientation) {
		
		this(min, max, new NumericalInterval(0,0.0), new NumericalInterval(0,0.0), new NumericalInterval(0,0.0), orientation);
	}

	public LinearNumericalAxisDraw(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, Orientation orientation) {
		
		this(0.0, 100.0, primaryIncrements, secondaryIncrements, tertiaryIncrements, orientation);
	}


	public LinearNumericalAxisDraw(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, Orientation orientation) {

		this(minValue, maxValue, new NumericalInterval(8, primaryIncrements), 
								new NumericalInterval(4, secondaryIncrements), 
								new NumericalInterval(2, tertiaryIncrements), orientation);
	}

	public LinearNumericalAxisDraw(Double minValue, Double maxValue, 
			NumericalInterval interval1, NumericalInterval interval2, NumericalInterval interval3, Orientation orientation) {

		super(orientation);
		
		this.maxValue = maxValue;
		this.minValue = minValue;

		if (interval1 == null) {
			this.interval1 = new NumericalInterval(0, 0.0);
			this.interval1.setActive(false);
		} else {
			this.interval1 = interval1;
		}
		if (interval2 == null) {
			this.interval2 = new NumericalInterval(0, 0.0);
			this.interval2.setActive(false);
		} else {
			this.interval2 = interval2;
		}
		if (interval3 == null) {
			this.interval3 = new NumericalInterval(0, 0.0);
			this.interval3.setActive(false);
		} else {
			this.interval3 = interval3;
		}
	}
	

	public abstract void drawGridLineOnZero(Graphics2D g, XYChart chart);	
	/**
	 * Draw the label next to the tick
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	protected abstract void drawIntervalLabel(NumericalInterval interval, Graphics g,
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

	protected abstract void drawIntervalTick(NumericalInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);
	
	
	protected abstract void drawGridLine(NumericalInterval interval, Graphics g,
			XYChart chart, int i, double incrementInPixel);

	/**
	 * Draw all the intervals and ticks for all intervals
	 * @param g
	 * @param chart
	 */
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
	protected void drawIntervalTickAndLabels(AbstractInterval interval, Graphics g,
			XYChart chart, boolean showLabel) {

		NumericalInterval inter = (NumericalInterval)interval;

		Double increment = inter.getInterval();

		int incrementNo = (int) ((maxValue - minValue) / increment);

		double factor = this.getMultiplicationFactor(chart);

		double incrementInPixel = (double) (increment * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			
			drawIntervalTick(inter, g, chart, i, incrementInPixel);

			if (showLabel)
				drawIntervalLabel(inter, g, chart, i, incrementInPixel);
		}

	}
	
	
	public void drawGridLine(AbstractInterval interval, Graphics2D g, XYChart chart) {
		
		NumericalInterval inter = (NumericalInterval) interval;
		
		Double increment = inter.getInterval();
		
        int incrementNo = (int) ((maxValue - minValue) / increment);

        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
        
        double incrementInPixel = (double) (inter.getInterval() * factor);
        
        for (int i = 0; i < (incrementNo + 1); i++) {
        	drawGridLine(inter, g, chart, i, incrementInPixel);
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

		
		if ((val == Math.floor(val)) && !Double.isInfinite(val)) {
		    // integral type
			while (val % increment != 0) {
				//TODO Error here!!!!! If you ++ a real number then it will not necessary break out of this loop!!!
				val++;
			}
			
		}else {
			System.err.println("cannot have real number here " +val);
		}

		return val;
	}
	
	

}
