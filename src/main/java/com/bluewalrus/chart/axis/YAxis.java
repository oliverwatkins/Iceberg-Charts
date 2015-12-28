package com.bluewalrus.chart.axis;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.draw.YAxisDraw;

/**
 * @copyright 2014
 * @author Oliver Watkins (www.blue-walrus.com)

 All Rights Reserved
 */
public class YAxis extends Axis {

    public boolean rightSide = false;
	
 
    public YAxis(String name) {
        super(name, Axis.AxisType.LINEAR_NUMERICAL);
    }

    public YAxis(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public YAxis(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(minValue, maxValue, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public YAxis(Double minValue, Double maxValue, Interval t1, Interval t2, Interval t3, String name) {
        super(minValue, maxValue, t1, t2, t3, name);
    }

    /**
     * If Y passes through ZERO then draw a line on the zero point.
     */
    public void drawYGridLineOnZero(Graphics2D g, Chart chart) {

        double factor = Utils.getFactor(chart.heightChart, maxValue, minValue);

        if (minValue < 0) {

            int fromTop = (int) (chart.heightChart + chart.topOffset + (minValue * factor));

            YAxisDraw.drawYGridLineOnZero(g, chart, fromTop, this);
        }
    }



    public void drawGridLine(Interval interval, Graphics2D g, Chart chart) {

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
            YAxisDraw.drawGridLine(interval, g, chart, fromTop);
            
        }
    }



	
    protected void drawIntervalLabel(Interval interval, Graphics g, 
			Chart chart, int i, double incrementInPixel) {
    	
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
    	YAxisDraw.drawYLabel(g, chart, fromTop, yLabel, this); 
	}



	@Override
	protected void drawIntervalTick(Interval interval, Graphics g, Chart chart, int i, double incrementInPixel) {
		
        //divide height of chart by actual height of chart to get the multiplaying factor
        double factor = getMultiplicationFactor(chart); 
		
    	double toZeroShift = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);

    	double fromTop = getFromTop(chart, i, incrementInPixel, toZeroShift);
    	
    	/**
    	 * Draw the tick
    	 */
    	YAxisDraw.drawIntervalTick(interval, g, chart, fromTop, this);
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

	
    protected double getMultiplicationFactor(Chart chart) {
    	return ((double) chart.heightChart / (double) (maxValue - minValue));
	}

    
    @Override
    public void drawBorderLine(Graphics g, Chart chart) {

        int x1 = chart.leftOffset - marginOffset;
        int y1 = chart.topOffset;
        int x2 = x1; //chart.leftOffset + chart.widthChart;
        int y2 = chart.topOffset + chart.heightChart;

        g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }

    
  
    public void drawLabel(Graphics g, Chart chart) {

    	YAxis axis = this;
    	
        Graphics2D g2d = (Graphics2D) g;
        
        YAxisDraw.drawLabel(chart, axis, g2d);
    }

    
    public String getName() {
        return "Y Axis";
    }

}
