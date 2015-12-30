package com.bluewalrus.chart.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;

public class LinearNumericalAxisDrawY extends LinearNumericalAxisDraw {

    public LinearNumericalAxisDrawY() {
		super();
	}
    
	public LinearNumericalAxisDrawY(double min, double max) {
		super(min, max);
	}

	public LinearNumericalAxisDrawY(Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements) {
		super(primaryIncrements, secondaryIncrements, tertiaryIncrements);
	}

	public LinearNumericalAxisDrawY(Double minValue, Double maxValue,
			Interval interval1, Interval interval2, Interval interval3) {
		super(minValue, maxValue, interval1, interval2, interval3);
	}

	public LinearNumericalAxisDrawY(Double minValue, Double maxValue,
			Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {
    	
		super(minValue, maxValue, primaryIncrements, secondaryIncrements,
				tertiaryIncrements);
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data) {
		
		//NOTE! data is ignored here. It's only used for enumeration axisdraw. TODO better way??
		
		drawAllIntervalTickAndLabels(g2d, xyChart);
		
		drawGridLines(g2d, xyChart);
		
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}


	public void drawGridLine(Interval interval, Graphics2D g, XYChart chart) {

        int incrementNo = (int) ((maxValue - minValue) / interval.getIncrement());

        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
        
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);
    	
        double incrementInPixel = (double) (interval.getIncrement() * factor);

        for (int i = 0; i < incrementNo; i++) {

            double fromTop = getFromTop(chart, i, incrementInPixel, toFirstInPixels);

        	/**
        	 * Draw grid line
        	 */
            YAxisDrawUtil.drawGridLine(interval, g, chart, fromTop);
        }
    }

	
    protected void drawIntervalLabel(Interval interval, Graphics g, 
    		XYChart chart, int i, double incrementInPixel) {
    	
    	Double increment = interval.getIncrement();
    	
    	
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
	protected void drawIntervalTick(Interval interval, Graphics g, XYChart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);

    	double fromTop = getFromTop(chart, i, incrementInPixel, toZeroShift);
    	
    	/**
    	 * Draw the tick
    	 */
    	YAxisDrawUtil.drawIntervalTick(interval, g, chart, fromTop, chart.yAxis);
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
    	return ((double) chart.heightChart / (double) (maxValue - minValue));
	}

}
