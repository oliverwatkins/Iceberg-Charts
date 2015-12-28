package com.bluewalrus.chart.axis;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.draw.XAxisDraw;
import com.bluewalrus.chart.draw.YAxisDraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class XAxis extends Axis {

    /**
     * @param name axis name
     */
    public XAxis(String name) {
        super(name, AxisType.LINEAR_NUMERICAL);
    }

    /**
     * 
     * @param name axis name
     * @param type type
     */
    public XAxis(String name, AxisType type) {
        super(name, type);
    }

    public XAxis(Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public XAxis(Double minValue, Double maxValue, Double primaryIncrements, Double secondaryIncrements, Double tertiaryIncrements, String name) {
        super(minValue, maxValue, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
    }

    public XAxis(Double minValue, Double maxValue, Interval t1, Interval t2, Interval t3, String name) {
        super(minValue, maxValue, t1, t2, t3, name);
    }

    
    public String getName() {
        return "X Axis";
    }
    
    
	protected void drawIntervalLabel(Interval interval, Graphics g, 
			Chart chart, int incrementNumber, double incrementInPixel) {
    	
		Double increment = interval.getIncrement();
		
        double factor = getMultiplicationFactor(chart); 
        
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(increment, factor);
    	
    	double toFirst = getToFirstIntervalValueFromMin(increment);
    	
        String xLabel = "" + ((incrementNumber * increment) + toFirst);
        
        double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel,incrementNumber);
        
        /**
         * Draw X Label
         */
        XAxisDraw.drawXLabel(g, chart, fromLeft, xLabel, this);
	}



	protected void drawIntervalTick(Interval interval, Graphics g, 
			Chart chart, int i, double incrementInPixel) {
		
		double factor = getMultiplicationFactor(chart); 
		
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);
		
        double fromLeft = getFromLeft(chart, toFirstInPixels, incrementInPixel, i);
        
        XAxisDraw.drawIntervalTick(interval, g, chart, fromLeft, this);
		
	}




    @Override
    public void drawGridLine(Interval interval, Graphics2D g, Chart chart) {

		double factor = getMultiplicationFactor(chart); 
    	
        //to first increment
    	double toFirstInPixels = getToFirstIntervalValueFromMinInPixels(interval.getIncrement(), factor);

        if (type == AxisType.ENUMERATION) {
            return;
        }

        int incrementNo = (int) (maxValue / interval.getIncrement());
        
        double incrementInPixel = (double) (interval.getIncrement() * factor);

        g.setColor(interval.graphLine.color);

        for (int i = 0; i < incrementNo; i++) {
        	
            double fromLeft = getFromLeft(chart, toFirstInPixels,
					incrementInPixel, i);
            
            /**
             * Draw Grid line
             */
            XAxisDraw.drawGridLine(interval, g, chart, fromLeft);
        }
    }


    @Override
    public void drawLabel(Graphics g, Chart chart) {

    	XAxis axis = this;
    	
        Graphics2D g2d = (Graphics2D) g;
        
        XAxisDraw.drawLabel(chart, axis, g2d);

    }

    @Override
    protected void drawYGridLineOnZero(Graphics2D g, Chart chart) {
    	XAxisDraw.drawYGridLineOnZero(g, chart, -999, this);
    }

    @Override
    public void drawBorderLine(Graphics g, Chart chart) {

        int x1 = chart.leftOffset;
        int y1 = chart.topOffset + chart.heightChart + marginOffset;
        int x2 = chart.leftOffset + chart.widthChart;
        int y2 = y1;

    	g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }


	private double getFromLeft(Chart chart, double toFirstInPixels, double incrementInPixel, int i) {
		double fromLeft = chart.leftOffset +  (i * incrementInPixel) + toFirstInPixels;
		return fromLeft;
	}
    
	@Override
	protected double getMultiplicationFactor(Chart chart) {
    	return ((double) chart.widthChart / (double) (maxValue - minValue));

	}


}
