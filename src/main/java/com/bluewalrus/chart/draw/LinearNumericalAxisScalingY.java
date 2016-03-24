package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;
import com.bluewalrus.chart.axis.NumericalInterval;

public class LinearNumericalAxisScalingY extends LinearNumericalAxisScaling {

    public LinearNumericalAxisScalingY() {
		super(Orientation.Y);
	}
    
	public LinearNumericalAxisScalingY(double min, double max) {
		super(min, max, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements) {
		super(primaryIncrements, secondaryIncrements, tertiaryIncrements, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double minValue, Double maxValue,
			NumericalInterval interval1, NumericalInterval interval2, NumericalInterval interval3) {
		super(minValue, maxValue, interval1, interval2, interval3, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double minValue, Double maxValue,
			Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {
    	
		super(minValue, maxValue, primaryIncrements, secondaryIncrements,
				tertiaryIncrements, Orientation.Y);
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data) {
		
		//NOTE! data is ignored here. It's only used for enumeration axisdraw. TODO better way??
		
		drawAllIntervalTickAndLabels(g2d, xyChart);
		
		drawGridLines(g2d, xyChart);
		
	}
	
	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		
		drawGridFills(g2d, xyChart);
	}

	
    protected void drawIntervalLabel(NumericalInterval interval, Graphics g, 
    		XYChart chart, int i, double incrementInPixel) {
    	
    	Double increment = interval.getInterval();
    	
    	
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
        
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(increment, factor);
    	
    	double toFirst = getToFirstIntervalValueFromMin(increment);

    	
    	double fromTop = getFromTop(chart, i, incrementInPixel, toFirstInPixels);
    	
    	String yLabel = "" + ((i * increment) + toFirst);
    	
    	
    	/**
    	 * Draw the label
    	 */
    	YAxisDrawUtil.drawYLabel(g, chart, fromTop, yLabel, chart.yAxis); 
	}



	@Override
	protected void drawIntervalTick(NumericalInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);

    	double fromTop = getFromTop(chart, i, incrementInPixel, toZeroShift);
    	
    	/**
    	 * Draw the tick
    	 */
    	YAxisDrawUtil.drawIntervalTick(interval, g, chart, fromTop, chart.yAxis);
	}
	
	protected void drawGridLine(NumericalInterval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getInterval(), factor);

        double fromTop = getFromTop(chart, i, incrementInPixel, toZeroShift);
    	/**
    	 * Draw the tick
    	 */
 
    	YAxisDrawUtil.drawGridLine(interval, (Graphics2D)g, chart, fromTop);
	}
	
	/**
	 * Get the pixels from top based on increment number. Add top offset and chart height first,
	 * then move back multiplying the increment number with pixels, then adding the zeroshift.
	 * 
	 * Used for ticks, labels, grid lines.
	 * 
	 * @param chart
	 * @param incrementNo
	 * @param incrementInPixel
	 * @param toZeroShift
	 * @return
	 */
	private double getFromTop(Chart chart, int incrementNo, double incrementInPixel,
			double toZeroShift) {
		double fromTop = chart.heightChart + chart.topOffset - (incrementNo * incrementInPixel) - toZeroShift;
		return fromTop;
	}
	
	
	
    /**
     * If Y passes through ZERO then draw a line on the zero point.
     */
    public void drawGridLineOnZero(Graphics2D g, XYChart chart) {

        double factor = Utils.getFactor(chart.heightChart, maxValue, minValue);

        if (minValue < 0) {

            int fromTop = (int) (chart.heightChart + chart.topOffset + (minValue * factor));
            
            //TODO draw Y Line
//            YAxisDrawUtil.drawYGridLineOnZero(g, chart, fromTop, chart.yAxis);
        }
    }

    protected double getMultiplicationFactor(XYChart chart) {
    	return ((double) chart.heightChart / (double) (getMaxValue() - minValue));
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double offset = chart.topOffset + (i * incrementInPixel)
				+ toFirstInPixels;
		return offset;
	}

}
