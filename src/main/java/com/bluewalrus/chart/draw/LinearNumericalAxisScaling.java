package com.bluewalrus.chart.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.chart.ChartUtils;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.Axis;
import com.bluewalrus.chart.axis.NumericalInterval;

public abstract class LinearNumericalAxisScaling extends AxisDraw{
	
	public Line zeroLine = new Line(Color.GRAY, false, 1);
	
	/**
	 * Default constructor
	 *
	 * @param name
	 * @param type
	 */
	public LinearNumericalAxisScaling(Orientation orientation) {
		this(0.0, 100.0, 50.0, 10.0, 5.0, orientation); //arbitrary values
	}
	
	public LinearNumericalAxisScaling(double min, double max, Orientation orientation) {
		
		this(min, max, new NumericalInterval(0,0.0), new NumericalInterval(0,0.0), new NumericalInterval(0,0.0), orientation);
	}

	public LinearNumericalAxisScaling(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, Orientation orientation) {
		
		this(0.0, 100.0, primaryIncrements, secondaryIncrements, tertiaryIncrements, orientation);
	}


	public LinearNumericalAxisScaling(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, Orientation orientation) {

		this(minValue, maxValue, new NumericalInterval(8, primaryIncrements), 
								new NumericalInterval(4, secondaryIncrements), 
								new NumericalInterval(2, tertiaryIncrements), orientation);
	}

	public LinearNumericalAxisScaling(Double minValue, Double maxValue, 
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
	 * Draw the tick of the interval. Usually just a small line coming out
	 * perpendicular to the axis.
	 * 
	 * @param interval
	 * @param g
	 * @param chart
	 * @param i
	 * @param incrementInPixel
	 */
	protected void drawIntervalTick(NumericalInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);

        double fromStart = getFromStart(chart, toZeroShift, incrementInPixel, i);
    	
    	/**
    	 * Draw the tick
    	 */
    	
    	
		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawIntervalTick(interval, g, chart, fromStart, chart.xAxis);
		}else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawIntervalTick(interval, g, chart, fromStart, chart.yAxis);
		}else {
			throw new RuntimeException("not supported");
		}
	}
	
	protected void drawGridLine(NumericalInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);

        double fromStart = getFromStart(chart, toZeroShift, incrementInPixel, i);
        
		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawGridLine(interval, (Graphics2D)g, chart, fromStart);
		}else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawGridLine(interval, (Graphics2D)g, chart, fromStart);
		}else {
			throw new RuntimeException("not supported");
		}
	}
	
	
	/**
	 * Draw all the intervals and ticks for all intervals
	 * @param g
	 * @param chart
	 */
	public void drawAllIntervalTickAndLabels(Graphics2D g, XYChart chart) {
		
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
	protected void drawIntervalTickAndLabels(AbstractInterval interval, Graphics2D g,
			XYChart chart, boolean showLabel) {

		NumericalInterval inter = (NumericalInterval)interval;

		Double increment = inter.getInterval();

		int incrementNo = (int) ((maxValue - minValue) / increment);

		double factor = this.getMultiplicationFactor(chart);

		double incrementInPixel = (double) (increment * factor);

		for (int i = 0; i < (incrementNo + 1); i++) {
			
			drawIntervalTick(inter, g, chart, i, incrementInPixel);

			if (showLabel)
				drawIntervalLabel(inter, g, chart, getAxis(chart), i, incrementInPixel);
		}

	}
	
	private Axis getAxis(XYChart chart) {
		if (orientation == Orientation.X) {
			return chart.xAxis;
		}else if (orientation == Orientation.Y) {
			return chart.yAxis;
		}else {
			throw new RuntimeException("not supported");
		}
	}
	
	
	protected void drawIntervalLabel(NumericalInterval interval, Graphics2D g,
			XYChart chart, Axis axis, int incrementNumber, double incrementInPixel) {

		g.setColor(axis.axisColor);

		Double increment = interval.getInterval();

		double factor = getMultiplicationFactor(chart);

		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				increment, factor);

		double toFirst = getToFirstIntervalValueFromMin(increment);

		
		double value = ((incrementNumber * increment) + toFirst);
		

		
		
		
		String label = ChartUtils.formatNumberValue(value, interval);

		double fromStart = getFromStart(chart, toFirstInPixels, incrementInPixel,
				incrementNumber);

		
		if (orientation == Orientation.X) {
			XAxisDrawUtil.drawXLabel(g, chart, fromStart, label, chart.xAxis, 0);
		}else if (orientation == Orientation.Y) {
			YAxisDrawUtil.drawYLabel(g, chart, fromStart, label, chart.yAxis);
		}else {
			throw new RuntimeException("not supported");
		}
		
	}
	
	public void drawGridLines(AbstractInterval interval, Graphics2D g, XYChart chart) {
		
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
	
	
	protected void drawGridFills(AbstractInterval interval, Graphics2D g, XYChart chart) {
		
		
		double factor = getMultiplicationFactor(chart);
	
		// to first increment
		double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(
				((NumericalInterval) interval).getInterval(), factor);
	
		int incrementNo = (int) ((maxValue - minValue) / ((NumericalInterval) interval).getInterval());
		
		incrementNo++;
	
		double incrementInPixel = (double) (((NumericalInterval) interval)
				.getInterval() * factor);
		
		g.setColor(interval.styling.graphLine.color);
	
        for (int i = 0; i < (incrementNo + 1); i++) {
	
			double fromStart = getFromStart(chart, toFirstInPixels,
					incrementInPixel, i);
			
			fromStart = fromStart - incrementInPixel; //start left of the y Axis (invisible part)
	
			/**
			 * Draw Grid line
			 */
			
			if (this.orientation == Orientation.X)

				interval.styling.graphFill.fillAreaX(g, (int)fromStart, incrementInPixel, chart, i);
				
			else if (this.orientation == Orientation.Y)
				
				interval.styling.graphFill.fillAreaY(g, (int)fromStart, incrementInPixel, chart, i);
			else {
				throw new RuntimeException("asdfasdf");
			}
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
	 * if min/max range is 3/40, then?? TODO
	 * 
	 * if min/max range is 3.0005/4 and the interval is 0.1, then should be 3.1
	 * 
	 * 
	 * @param increment
	 * @param maxValue
	 * @param minValue
	 * @param factor
	 * @return
	 */
	protected double getToFirstIntervalValueFromMin(Double increment) {

		double val = this.minValue;
		
		if (val == 1.001) {

			System.out.println("");
		}
		
		/**
		 * Convert increment to a whole number. Get the multiplication factor and
		 * use that on the axis values. Find the first interval, then divide again.
		 */
		
		
  		int multiplicationFactor = 1;
		
		double adjustedInc = increment;
		
		/**
		 * 1. Adjust the increment
		 */
		
		while (!(isWholeNumber(adjustedInc))) {
			System.out.println("1");
			adjustedInc = adjustedInc*10;
			multiplicationFactor = multiplicationFactor*10;
		}
		
		
		double adjustedMinValue = this.minValue * multiplicationFactor;

		int multiplicationFactor2 = 1; //reset
		
		/**
		 * 2. Adjust starting point
		 */
		
		while (!(isWholeNumber(adjustedMinValue))) {
			System.out.println("2");

			adjustedMinValue = adjustedMinValue*10;
			multiplicationFactor2 = multiplicationFactor2*10;
		}
		
		
		adjustedInc = adjustedInc * multiplicationFactor2;

		if (adjustedInc < 0) {
			throw new RuntimeException("adjustedInc cannot be less than zero");
		}

			
		val = adjustedMinValue;
		
		while (val % adjustedInc != 0) {
			val++;
		}
		
		val = val / multiplicationFactor / multiplicationFactor2;
		
		return val;
	}
	
	
	private boolean isWholeNumber(double val) {
		return (val == Math.floor(val)) && !Double.isInfinite(val);
	}

}
